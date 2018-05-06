package us.neuner.clo.message;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "error" message.
 */
@JsonPropertyOrder({ "type", "psid", "mid", "errorId", "errorType", "errorMessage" })
public class ErrorMessage extends Message {

	private final UUID errorId;
	private final String errorType;
	private final String errorMessage;
	
	public ErrorMessage(Message msg, String errorMessage) {
		super(msg.getPsid());
		this.errorId = msg.getMid();
		this.errorType = getMessageTypeString(msg);
		this.errorMessage = errorMessage;
	}

	/*
     * Creates a new @see ErrorMessage object.
     * @param psid the player-session identifier associated with this message
     * @param mid the unique message identifier for this message
	 * @param errorId the message identifier of the erroneous message
	 * @param errorType the message type of the erroneous message
	 * @param errorMessage text detailing the cause of this error message 
	 */
    @JsonCreator
	public ErrorMessage(@JsonProperty("psid") String psid, 
			@JsonProperty("mid") UUID mid, 
			@JsonProperty("errorId") UUID errorId, 
			@JsonProperty("errorType") String errorType,
			@JsonProperty("errorMessage") String errorMessage) {
		super(psid, mid);
		this.errorId = errorId;
		this.errorType = errorType;
		this.errorMessage = errorMessage;
	}

	private static String getMessageTypeString(Message msg) {
		final JsonSubTypes st = Message.class.getAnnotation(JsonSubTypes.class);
		JsonSubTypes.Type[] types = st.value();
		for (int i = 0; i < types.length; i++) {
			if (msg.getClass().equals(types[i].value()))
				return types[i].name();
		}
		return null;
	}

	/*
	 * Returns the message identifier of the erroneous message
	 */
	public UUID getErrorId() {
		return errorId;
	}

	/*
	 * Returns the message type of the erroneous message
	 */ 
	public String getErrorType() {
		return errorType;
	}

	/*
	 * Returns text detailing the cause of this error message 
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
