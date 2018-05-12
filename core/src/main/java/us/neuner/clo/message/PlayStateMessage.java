package us.neuner.clo.message;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import us.neuner.clo.common.*;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "playState" message.
 */
@JsonPropertyOrder({ "type", "psid", "mid", "players", "hand", "pieces", "validMoves", "suggestion" })
public class PlayStateMessage extends Message {

	@JsonProperty
    private List<PlayerInfo> players; 

	@JsonProperty
    private List<GameEntityId> hand; 

	@JsonProperty
    private List<PieceInfo> pieces; 

	@JsonProperty
    private List<GameEntityId> validMoves; 

	@JsonProperty
    private SuggestionInfo suggestion; 

	/*
     * Creates a new @see PlayStateMessage object.
     * @param psid the player-session identifier associated with this message
     * @param mid the unique message identifier for this message
	 * @param players the players currently bound to the game session
	 * @param hand the cards in the hand of the player receiving the playState
	 * @param pieces the game pieces on the game board
	 * @param validMoves valid moves for the current player
	 * @param suggestion the suggestion made by the current player
	 */
	public PlayStateMessage(String psid, List<PlayerInfo> players, List<GameEntityId> hand, 
			List<PieceInfo> pieces, List<GameEntityId> validMoves, SuggestionInfo suggestion) {
		super(psid);
		this.players = players;
		this.hand = hand;
		this.pieces = pieces;
		this.validMoves = validMoves;
		this.suggestion = suggestion;
	}

	/*
     * Creates a new @see PlayStateMessage object.
     * @param psid the player-session identifier associated with this message
     * @param mid the unique message identifier for this message
	 * @param players the players currently bound to the game session
	 * @param hand the cards in the hand of the player receiving the playState
	 * @param pieces the game pieces on the game board
	 * @param validMoves valid moves for the current player
	 * @param suggestion the suggestion made by the current player
	 */
    @JsonCreator
	public PlayStateMessage(@JsonProperty("psid") String psid, 
			@JsonProperty("mid") UUID mid, 
			@JsonProperty("players") List<PlayerInfo> players, 
			@JsonProperty("hand") List<GameEntityId> hand, 
			@JsonProperty("pieces") List<PieceInfo> pieces,
			@JsonProperty("validMoves") List<GameEntityId> validMoves,
			@JsonProperty("suggestion") SuggestionInfo suggestion) {
		super(psid, mid);
		this.players = players;
		this.hand = hand;
		this.pieces = pieces;
		this.validMoves = validMoves;
		this.suggestion = suggestion;
	}
    
    /*
     * Returns the players currently bound to the game session
     * @param index index of the @see PlayerInfo to return.
     */
    @JsonIgnore
	public PlayerInfo getPlayer(int index) {
		return players.get(index);
	}

    /*
     * Returns the count of players currently bound to the game session
     */
	public int getPlayerCount() {
		return players.size();
	}

    /*
     * Returns the cards in the hand of the player receiving the playState
     */
	public GameEntityId[] getHand() {
		return hand.toArray(new GameEntityId[hand.size()]);
	}

    /*
     * Returns the cards in the hand of the player receiving the playState
     */
	public GameEntityId getHandCard(int index) {
		return hand.get(index);
	}
	
    /*
     * Returns the count of cards in the hand of the player receiving the playState
     */
	public int getHandCardCount() {
		return hand.size();
	}
	
    /*
     * Returns the game pieces on the game board
     */
	public PieceInfo[] getPieces() {
		return pieces.toArray(new PieceInfo[pieces.size()]);
	}

    /*
     * Returns the valid moves for the current player
     */
	public GameEntityId[] getValidMoves() {
		return validMoves.toArray(new GameEntityId[validMoves.size()]);
	}

    /*
     * Returns the suggestion made by the current player
     */
	public SuggestionInfo getSuggestion() {
		return suggestion;
	}
}
