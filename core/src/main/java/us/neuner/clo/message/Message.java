package us.neuner.clo.message;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the supertype properties for all CLO messages.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({ 
	@Type(value = ClientJoinMessage.class, name = "clientJoin"), 
	@Type(value = GameSetupMessage.class, name = "gameSetup"),
	@Type(value = EndGameMessage.class, name = "endGame"),  
	@Type(value = ChatMessage.class, name = "chat"), 
	@Type(value = ChatMessageHistory.class, name = "chatHistory"),
})
public abstract class Message {

    private final String psid;
    private final UUID mid;

    /*
     * Creates a new @see Message object.
     * @param psid the player-session identifier associated with this message.
     */
    protected Message(String psid) {
        this.psid = psid;
        this.mid = UUID.randomUUID();
    }

    /*
     * Creates a new @see Message object.
     * @param psid the player-session identifier associated with this message.
     * @param mid the unique message identifier for this message
     */
	@JsonCreator
    protected Message(@JsonProperty("psid") String psid, @JsonProperty("mid") UUID mid) {
    	this.psid = psid;
    	this.mid = mid;
    }

	/*
	 * Returns the player-session identifier associated with this message.
	 */
	@JsonGetter("psid")
    public String getPsid() {
        return this.psid;
    }

	/*
	 * Returns the unique message identifier for this message
	 */
	@JsonGetter("mid")
    public UUID getMid() {
        return this.mid;
    }
}