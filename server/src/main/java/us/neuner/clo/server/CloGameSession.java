package us.neuner.clo.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;

import us.neuner.clo.common.PlayerInfo;
import us.neuner.clo.common.SuggestionInfo;
import us.neuner.clo.message.ChatEntry;
import us.neuner.clo.message.ChatMessage;
import us.neuner.clo.message.ChatMessageHistory;
import us.neuner.clo.message.ClientJoinMessage;
import us.neuner.clo.message.EndGameMessage;
import us.neuner.clo.message.ErrorMessage;
import us.neuner.clo.message.GameSetupMessage;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Models the global state of a CLO game session.
 */
public class CloGameSession {
    
	/*
	 * @author Jarod Neuner <jarod@neuner.us>
	 * Modes of operation for each @see CloGameSession instance.
	 */
	public enum State {
		
		/*
		 * The session is accepting client connections.
		 * The session relays chat amongst connected participants.
		 */
		Setup,
		
		/*
		 * The session does not accept new client connections
		 * The session monitors for client disconnects.
		 * The session responds to gameplay messages and updates game state IAW game rules.
		 */
		Play,
		
		/*
		 * The session monitors clients for disconnects.
		 * The session does not respond to gameplay messages
		 * The server drops focus on this session.
		 * The session starts a two (2) minute session-termination timer.
		 */
		Cleanup,
	}
	
	private static final int PLAYER_START_COUNT = 3;		// Number of players that transitions game to Play state.
	private static final int PLAYER_NAME_LEN_MIN = 3;		// Player names must be at least this long
	private static final int PLAYER_NAME_LEN_MAX = 16;		// Player names must be no longer than this
	
	private static final SimpleDateFormat CHAT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	static {
		CHAT_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	private final CloGameServer server;
	private final List<PlayerDetail> players; 
    private List<ChatEntry> msgList;
    private CloGameSession.State state;
    private final Object lock;
    
    // End Game Objects
    private String victor;
    private SuggestionInfo solution;
    private Timer disposeTimer;

	/*
     * Create a new @see CloGameSession instance.
     * @param server The parent @see CloGameServer instance.
     */
	public CloGameSession(CloGameServer server) {
		this.server = server;
		this.players = new ArrayList<PlayerDetail>();
        this.msgList = new LinkedList<ChatEntry>();
        this.state = State.Setup;
        this.lock = new Object();
        this.solution = SuggestionInfo.Random();
        this.disposeTimer = new Timer(true);
	}

	// BEGIN: Public Methods
	
    /*
     * Returns the current mode of operation for this session.
     */
    public CloGameSession.State getState() {
		return state;
	}
    
	// END: Public Methods

	// BEGIN: Incoming message & event handlers
    
	/*
	 * Handle "clientJoin" messages from clients.
	 * @param join the clientJoin message
	 * @param sid the connection/session ID associated with the clientJoin
	 * @param pd the @see PlayerDetail that is mapped to this message (often null - new players)
	 */
	public void clientJoinHandler(ClientJoinMessage join, String sid, PlayerDetail pd) {

		if (pd == null) {
			String name = join.getPlayerName();
			Boolean joined = false;
			
			// Input validation on PlayerDetail parameters
			if ((name == null) || (name.length() < PLAYER_NAME_LEN_MIN) || (name.length() > PLAYER_NAME_LEN_MAX)) {
				
				String errMsg = "Players must provide name between %i and %i characters long.";
				sendErrorMessage(join, sid, String.format(errMsg, PLAYER_NAME_LEN_MIN, PLAYER_NAME_LEN_MIN));
				return;
			}

			// Create new PlayerDetail...
			synchronized (lock) {
				if ((this.state != State.Setup) && (this.players.size() < PLAYER_START_COUNT)) {
					pd = new PlayerDetail(this, sid, name);
					this.players.add(pd);
					joined = true;
				}
			}
			
			if (!joined) {
				String errMsg = "Game session in progress.  Please try back again later...";
				sendErrorMessage(join, sid, errMsg);
				return;
			}
			
		}
		
		if (state == State.Setup)
			sendGameSetup();
		else if (state == State.Play)
			; //TODO: rejoining in the play state...
		else
			sendEndGame(pd);
		
		sendChatHistory(pd);
		
		//TODO: Move this to CharacterSelectMessage at the appropriate time...
		if (players.size() >= PLAYER_START_COUNT)
			setCleanup();
		
		return;
	}
	
	/*
	 * Handles "chat" from clients.
	 * @param chat the @see ChatMessage that the server received
	 * @param playerName player name for the client that sent the message
	 */
	public void chatMessageHandler(ChatMessage chat, String playerName) {
		String msg = chat.getMsg();
		
		if ((msg != null) && !msg.isEmpty())
			msgList.add(new ChatEntry(playerName, msg, CHAT_DATE_FORMAT.format(new Date())));
                
        for (PlayerDetail pd : this.players)
        	sendChatHistory(pd);
	}
	
	// END: Incoming message & event handlers

	// BEGIN: State update operations
	
	private void setCleanup() {
		
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

				ChatEntry ce = new ChatEntry("System", "Terminating Session", CHAT_DATE_FORMAT.format(new Date()));
				this.session.msgList.add(ce);
		        for (PlayerDetail pd : this.session.players)
		        	this.session.sendChatHistory(pd);
				
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
	        for (PlayerDetail pd : this.players)
	        	sendEndGame(pd);
	        
	        this.disposeTimer.schedule(new SessionCleanupTimerTask(this), 120000);
		}
		
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

	private void sendEndGame(PlayerDetail pd) {
    	String psid = pd.getPsid();
		EndGameMessage egm = new EndGameMessage(psid, this.victor, this.solution);
		server.sendToClient(pd, egm);
	}
	
	/*
	 * Sends the session chat history to the specified player.
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
