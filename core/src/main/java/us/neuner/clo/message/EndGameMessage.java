package us.neuner.clo.message;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import us.neuner.clo.common.SuggestionInfo;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the endGame message.
 */
@JsonPropertyOrder({ "type", "psid", "mid", "victor", "solution" })
public class EndGameMessage extends Message {

	private final String victor;
	private final SuggestionInfo solution;

	/*
     * Creates a new @see EndGameMessage object.
     * @param psid the player-session identifier associated with this message
	 * @param victor the winning player (may be null - no victor)
	 * @param solution the solution cards for a game session
	 */
	public EndGameMessage(String psid, String victor, SuggestionInfo solution) {
		super(psid);
		this.victor = victor;
		this.solution = solution;
	}
	
	/*
     * Creates a new @see EndGameMessage object.
     * @param psid the player-session identifier associated with this message
     * @param mid the unique message identifier for this message
	 * @param victor the winning player (may be null - no victor)
	 * @param solution the solution cards for a game session
	 */
    @JsonCreator
	public EndGameMessage(
			@JsonProperty("psid") String psid, 
    		@JsonProperty("mid") UUID mid, 
			@JsonProperty("victor") String victor, 
			@JsonProperty("solution") SuggestionInfo solution) {
		super(psid, mid);
		this.victor = victor;
		this.solution = solution;
	}

    /*
     * Returns the winning player (may be null - no victor)
     * @return The name of the winning player, or null.
     */
    @JsonGetter("victor")
	public String getVictor() {
		return victor;
	}

    /*
     * Returns the solution for a game session.
     * @return A @see SuggestionInfo that contains the game session solution
     */
    @JsonGetter("solution")
	public SuggestionInfo getSolution() {
		return this.solution;
	}

}
