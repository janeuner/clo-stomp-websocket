package us.neuner.clo.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import us.neuner.clo.common.*;
import us.neuner.clo.message.*;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Models the global state of a CLO game session.
 */
public class CloGameSession {

	private static Logger LOG = LogManager.getLogger(CloGameSession.class);

	/*
	 * @author Jarod Neuner <jarod@neuner.us> Modes of operation for each @see
	 * CloGameSession instance.
	 */
	public enum State {

		/*
		 * The session is accepting client connections. The session relays chat amongst
		 * connected participants.
		 */
		Setup,

		/*
		 * The session does not accept new client connections The session monitors for
		 * client disconnects. The session responds to gameplay messages and updates
		 * game state IAW game rules.
		 */
		Play,

		/*
		 * The session monitors clients for disconnects. The session does not respond to
		 * gameplay messages The server drops focus on this session. The session starts
		 * a two (2) minute session-termination timer.
		 */
		Cleanup,
	}

	private static final int PLAYER_START_COUNT = 3; // Number of players that transitions game to Play state.
	private static final int PLAYER_NAME_LEN_MIN = 3; // Player names must be at least this long
	private static final int PLAYER_NAME_LEN_MAX = 16; // Player names must be no longer than this

	private final CloGameServer server;
	private final List<PlayerDetail> players;
	private List<ChatEntry> msgList;
	private CloGameSession.State state;
	private final UUID gameSessionId;
	private final Object lock;

	// Play State objects
	private PieceInfo[] pieces;

	// End Game Objects
	private String victor;
	private SuggestionInfo solution;
	private Timer disposeTimer;

	/*
	 * Create a new @see CloGameSession instance.
	 * 
	 * @param server The parent @see CloGameServer instance.
	 */
	public CloGameSession(CloGameServer server) {
		this.server = server;
		this.players = new ArrayList<PlayerDetail>();
		this.msgList = new LinkedList<ChatEntry>();
		this.state = State.Setup;
		this.lock = new Object();
		this.solution = SuggestionInfo.Random();
		this.gameSessionId = UUID.randomUUID();
		this.disposeTimer = new Timer(true);

		// TODO: Remove next statement when logic to complete character selection is
		// done
		pieces = new PieceInfo[] { new PieceInfo(GameEntityId.MrGreen, GameEntityId.MrGreenStart),
				new PieceInfo(GameEntityId.MrsPeacock, GameEntityId.MrsPeacockStart),
				new PieceInfo(GameEntityId.MissScarlet, GameEntityId.MissScarletStart),
				new PieceInfo(GameEntityId.MrsWhite, GameEntityId.MrsWhiteStart),
				new PieceInfo(GameEntityId.ProfessorPlum, GameEntityId.ProfessorPlum),
				new PieceInfo(GameEntityId.ColonelMustard, GameEntityId.ColonelMustard),
				new PieceInfo(GameEntityId.Knife, GameEntityId.Kitchen),
				new PieceInfo(GameEntityId.CandleStick, GameEntityId.Library),
				new PieceInfo(GameEntityId.LeadPipe, GameEntityId.Study),
				new PieceInfo(GameEntityId.Rope, GameEntityId.DiningRoom),
				new PieceInfo(GameEntityId.Wrench, GameEntityId.Conservatory),
				new PieceInfo(GameEntityId.Revolver, GameEntityId.BilliardRoom), };
	}

	// BEGIN: Public Methods
  
	/*
	 * Remove a player from the @see CloGameSession
	 * 
	 * @param pd the @see PlayerDetail for the player to remove
	 */
	public void removePlayer(PlayerDetail pd) {
		removePlayer(pd.getPsid());
	}

	/*
	 * Remove a player from the @see CloGameSession
	 * 
	 * @param psid the player-session id for the player to remove
	 */
	public void removePlayer(String psid) {
		Boolean removed = false;

		for (PlayerDetail pd : this.players) {
			if (pd.getPsid().equals(psid)) {
				removed = this.players.remove(pd);
				break;
			}
		}

		if (removed) {
			// TODO: Handling for players that are removed during play state
		}
	}

	/*
	 * Returns the current mode of operation for this session.
	 */
	public CloGameSession.State getState() {
		return state;
	}

	// END: Public Methods

	// BEGIN: Private Utilities
    
    /*
     * Retrieves the PlayerDetail instances that are associated with a @see GameEntityId.
     * @param entity may be a Suspect, a Location, or @see GameEntityId#InvalidValue.
     * For a suspect, the result will be the PlayerDetail for the player acting as that suspect.
     * For a location, the result will be the PlayerDetail instances for all players at that location.
     */
	private List<PlayerDetail> getPlayerDetail(GameEntityId entity) {
		List<PlayerDetail> result;
		
		if (entity == GameEntityId.InvalidValue) {
			
			result = new ArrayList<PlayerDetail>(PLAYER_START_COUNT);

			for (PlayerDetail pd : this.players) {
				
				if (pd.getPlayerInfo().getPieceName() == entity) {
					result.add(pd);
					break;
				}
			}
		}
		else if (PieceInfo.isSuspect(entity)) {

			result = new ArrayList<PlayerDetail>(1);
			
			for (PlayerDetail pd : this.players) {
				
				if (pd.getPlayerInfo().getPieceName() == entity) {
					result.add(pd);
					break;
				}
			}
		}
		else if (PieceInfo.isLocation(entity)) {

			result = new ArrayList<PlayerDetail>(PLAYER_START_COUNT);
			
			for (PieceInfo pi : pieces) {

				if (PieceInfo.isSuspect(pi.getId()))
					continue;
				if (pi.getLoc() != entity)
					continue;

				result.addAll(getPlayerDetail(pi.getId()));
			}
		}
		else {
			result = new ArrayList<PlayerDetail>(0);
		}
		
		return result;
	}
	
	// END: Private Utilities

	// BEGIN: Incoming message & event handlers

	/*
	 * Handle "clientJoin" messages from clients.
	 * 
	 * @param join the clientJoin message
	 * 
	 * @param sid the connection/session ID associated with the clientJoin
	 * 
	 * @param pd the @see PlayerDetail that is mapped to this message (often null -
	 * new players)
	 */
	public void clientJoinHandler(ClientJoinMessage join, String sid, PlayerDetail pd) {

		if (pd != null)
			assert (this.players.contains(pd));

		if (pd == null) {
			String name = join.getPlayerName();
			Boolean joined = false;

			// Input validation on PlayerDetail parameters
			if ((name == null) || (name.length() < PLAYER_NAME_LEN_MIN) || (name.length() > PLAYER_NAME_LEN_MAX)) {

				String errMsg = "Players must provide name between %d and %d characters long.";
				sendErrorMessage(join, sid, String.format(errMsg, PLAYER_NAME_LEN_MIN, PLAYER_NAME_LEN_MIN));
				return;
			}

			// Create new PlayerDetail...
			synchronized (lock) {
				if ((this.state == State.Setup) && (this.players.size() < PLAYER_START_COUNT)) {
					pd = new PlayerDetail(this, sid, name);
					this.players.add(pd);
					joined = true;
				}
			}

			if (joined) {
				LOG.info("Adding player[session={}]: name={} psid={} sid={}", this.gameSessionId, name, pd.getPsid(),
						sid);
			} else /* if (!joined) */ {
				String errMsg = "Game session in progress.  Please try back again later...";
				sendErrorMessage(join, sid, errMsg);
				return;
			}

		}

		sessionStateUpdate();
		sendChatHistory(pd);

		return;
	}

	/*
	 * Handle "characterSelect" messages from clients.
	 * 
	 * @param select the characterSelect message
	 * 
	 * @param sid the connection/session ID associated with the characterSelect
	 * 
	 * @param pd the @see PlayerDetail that is mapped to this message
	 */
	public void characterSelectHandler(CharacterSelectMessage select, String sid, PlayerDetail pd) {

		Boolean selected = false;
		String errMsg = null;
		
		synchronized (lock) {
			if (this.state != State.Setup) {
				errMsg = "Game session in progress.  Please try back again later...";
			}
			else if (!PieceInfo.isSuspect(select.getEntity())) {
				errMsg = "Malformed message from client: Invalid entityId.";
			}
			else if (this.getPlayerDetail(select.getEntity()).size() > 0) {
				errMsg = "That suspect has already been claimed.  Please make a different selection.";
			}
			else {
				selected = true;
				pd.getPlayerInfo().setPieceName(select.getEntity());
			}
		}
		
		if (errMsg != null) {
			sendErrorMessage(select, sid, errMsg);
		}
		
		if (selected) {
			sessionStateUpdate();
		}
	}

	/*
	 * Handles "chat" from clients.
	 * 
	 * @param chat the @see ChatMessage that the server received
	 * 
	 * @param playerName player name for the client that sent the message
	 */
	public void chatMessageHandler(ChatMessage chat, String playerName) {

		String msg = chat.getMsg();
		this.sessionAddChatMessage(playerName, msg);
	}

	/*
	 * Handles "move" from clients.
	 * 
	 * @param move the @see MoveMessage that the server received
	 * 
	 * @param playerName player name for the client that sent the message
	 */
	public void moveMessageHandler(MoveMessage move) {
		//get destination from message
		GameEntityId dest = move.getDest();
		//get the name of the piece of the player sending the message
		GameEntityId player=PlayerDetail.getPlayerDetail(move.getMid()).getPlayerInfo().getPieceName();
		PieceInfo info=null;
		boolean isOccupied=false;
		//iterates through pieces for player piece, and check for occupied hallways
		for(PieceInfo piece : pieces) {
			if(piece.getId().equals(player)) {
				info=piece;
			}
			//if any susepct is in the desintation, and that destination is a hallway
			if(piece.isSuspect() && piece.getLoc().equals(dest) && PieceInfo.isHallway(dest)) {
				isOccupied=true;
			}
		}
		//PieceInfo.moveTo() checks for isValid move, 
		if(!isOccupied) {
			info.moveTo(loc);
		}
	}

	// END: Incoming message & event handlers

	// BEGIN: State update operations

	/*
	 * Send a global update appropriate to the current session state
	 */
	private void sessionStateUpdate() {

		if (state == State.Setup) {
			sendGameSetup();

			//TODO: Move this to CharacterSelectMessage at the appropriate time...
			if ((players.size() >= PLAYER_START_COUNT) && (getPlayerDetail(GameEntityId.InvalidValue).size() == 0)) {
				LOG.info("Starting CLO session[session={}]: {}", this.gameSessionId, this.players);
				sessionCleanup();
			}
		}
		else if (state == State.Play) {
			; //TODO: rejoining in the play state...
		}
		else
			sendEndGame();
	}
	
	//TODO: get back to this asap!
//	private void sessionSetup() {
//		
//	}
  
	private void sessionCleanup() {

		/*
		 * Cleans up the @see CloGameSession after a timeout.
		 */
		final class SessionCleanupTimerTask extends java.util.TimerTask {

			private CloGameSession session;

			public SessionCleanupTimerTask(CloGameSession session) {

				this.session = session;
			}

			@Override
			public void run() {

				this.session.sessionAddChatMessage("System", "Terminating chat session.");

				for (PlayerDetail pd : this.session.players)
					pd.close();
				this.session.players.clear();
			}
		}

		Boolean startCleanup = false;

		synchronized (this.lock) {
			if (state != State.Cleanup) {
				state = State.Cleanup;
				startCleanup = true;
			}
		}

		if (startCleanup) {
			sendEndGame();
			this.sessionAddChatMessage("System", "Game Session has ended.");

			this.disposeTimer.schedule(new SessionCleanupTimerTask(this), 120000);
		}

	}

	/*
	 * Create a new @see ChatEntry object.
	 * 
	 * @param playerName the sender of the message
	 * 
	 * @param msg the message
	 */
	private void sessionAddChatMessage(String playerName, String msg) {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		fmt.setTimeZone(TimeZone.getTimeZone("GMT"));

		ChatEntry ce = new ChatEntry(playerName, msg, fmt.format(new Date()));
		this.msgList.add(ce);
		for (PlayerDetail pd : this.players)
			this.sendChatHistory(pd);
	}

	// END: State update operations

	// BEGIN: Outgoing message operations

	private void sendGameSetup() {
		List<PlayerInfo> pInfoList = new ArrayList<PlayerInfo>(players.size());

		for (PlayerDetail pd : this.players)
			pInfoList.add(pd.getPlayerInfo());

		for (PlayerDetail pd : this.players) {
			String psid = pd.getPsid();
			GameSetupMessage gsm = new GameSetupMessage(psid, pInfoList);
			server.sendToClient(pd, gsm);
		}
	}

	private void sendEndGame() {
		for (PlayerDetail pd : this.players) {
			String psid = pd.getPsid();
			EndGameMessage egm = new EndGameMessage(psid, this.victor, this.solution);
			server.sendToClient(pd, egm);
		}
	}

	/*
	 * Sends the session chat history to the specified player.
	 * 
	 * @param pd the player which will receive a @see ChatMessageHistory
	 */
	private void sendChatHistory(PlayerDetail pd) {

		String psid = pd.getPsid();
		if (psid != null) {
			ChatMessageHistory h = new ChatMessageHistory(psid, msgList);
			server.sendToClient(pd, h);
		}
	}

	private void sendErrorMessage(us.neuner.clo.message.Message clientMsg, String sid, String errMsg) {
		ErrorMessage err = new ErrorMessage(clientMsg, errMsg);
		server.sendToClient(sid, err);
	}

	// BEGIN: Outgoing message operations

}
