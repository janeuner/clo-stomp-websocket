package us.neuner.clo.message;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "chat" message.
 */
@JsonPropertyOrder({ "type", "psid", "mid", "msg" })
public class ChatMessage extends Message {

    private String msg;

    public ChatMessage(@JsonProperty("psid") String psid, @JsonProperty("msg") String msg) {
    	super(psid);
        this.msg = msg;
    }
    
    @JsonCreator
    public ChatMessage(@JsonProperty("psid") String psid, @JsonProperty("mid") UUID mid, @JsonProperty("msg") String msg) {
    	super(psid, mid);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
