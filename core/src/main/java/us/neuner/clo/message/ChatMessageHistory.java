package us.neuner.clo.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "gameSetup" message.
 */
@JsonPropertyOrder({ "type", "psid", "msgList" })
public class ChatMessageHistory extends Message {

	@JsonProperty
    private List<ChatEntry> msgList;

	/*
     * Creates a new @see GameSetupMessage object.
     * @param psid the player-session identifier associated with this message
	 */
    public ChatMessageHistory(String psid) {
    	super(psid);
        this.msgList = new ArrayList<ChatEntry>();
    }

	/*
     * Creates a new @see ChatMessageHistory object.
     * @param psid the player-session identifier associated with this message
	 * @param msgList the chat messages that have been sent in the current game session
	 */
    @JsonCreator
    public ChatMessageHistory(@JsonProperty("psid") String psid, @JsonProperty("msgList") List<ChatEntry> msgList) {
    	super(psid);
        this.msgList = new ArrayList<ChatEntry>(msgList);
    }

    /*
     * Returns the chat messages that have been sent in the current game session
     * @param index index of the @see PlayerInfo to return.
     */
    @JsonIgnore
    public ChatEntry getEntry(int index) {
        return msgList.get(index);
    }

    /*
     * Returns the count of chat messages that have been sent in the current game session
     */
    @JsonIgnore
    public int getEntryCount() {
        return msgList.size();
    }
}
