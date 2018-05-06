package us.neuner.clo.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "clientJoin" message.
 */
public class ClientJoinMessage extends Message {

	private String sessionPassword;
	private String playerName;

	/*
     * Creates a new @see ClientJoinMessage object.
     * @param psid the player-session identifier associated with this message
	 * @param sessionPassword the password used to authorize access to a game session
	 * @param playerName the name of the player that is joining the session
	 */
    @JsonCreator
	public ClientJoinMessage(@JsonProperty("psid") String psid, @JsonProperty("sessionPassword") String sessionPassword, @JsonProperty("playerName") String playerName) {
		super(psid);
		this.setSessionPassword(sessionPassword);
		this.setPlayerName(playerName);
	}

    /*
     * Returns the player-session identifier associated with this message
     */
	public String getSessionPassword() {
		return sessionPassword;
	}

	/*
	 * Sets the player-session identifier associated with this message
	 */
	public void setSessionPassword(String sessionPassword) {
		this.sessionPassword = sessionPassword;
	}

	/*
	 * Returns the password used to authorize access to a game session
	 */
	public String getPlayerName() {
		return playerName;
	}

	/*
	 * Returns the name of the player that is joining the session
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
