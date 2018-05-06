package us.neuner.clo.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Jarod Neuner <jarod@neuner.us>
 * @author Tim Hanson <tim@timgineer.io>
 * Describes entries in the chat history of a CLO game session.
 */
@JsonPropertyOrder({ "playerName", "msg", "time" })
public class ChatEntry {

	private final String playerName;
    private final String msg;
    private final String time;

	/**
	 * Create a new @see ChatEntry object.
	 * @param playerName the sender of the message
	 * @param msg the message
	 * @param time the time the message was received by the server
	 */
    @JsonCreator
    public ChatEntry(
    		@JsonProperty("playerName") String playerName, 
    		@JsonProperty("msg") String msg, 
    		@JsonProperty("time") String time) {
    	this.playerName = playerName;
        this.msg = msg;
        this.time = time;
    }
    
    /*
     * Returns the sender of the message
     */
    public String getPlayerName() {
    	return playerName;
    }

    /*
     * Returns the message
     */
    public String getMsg() {
        return msg;
    }

	/*
	 * Returns a hash code value for the object.
	 * @see java.lang.Object#hashCode()
	 */
    public String getTime() {
    	return time;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	/*
	 * Indicates whether some other object is "equal to" this one.
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatEntry other = (ChatEntry) obj;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
    
    
}
