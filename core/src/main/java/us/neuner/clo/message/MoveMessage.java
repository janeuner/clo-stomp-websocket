package us.neuner.clo.message;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "move" message.
 */
@JsonPropertyOrder({ "type", "psid", "mid", "dest" })
public class MoveMessage extends Message {

    private String dest;

    public ChatMessage(@JsonProperty("psid") String psid, @JsonProperty("dest") String dest) {
    	super(psid);
        this.dest = dest;
    }
    
    @JsonCreator
    public ChatMessage(@JsonProperty("psid") String psid, @JsonProperty("mid") UUID mid, @JsonProperty("dest") String dest) {
    	super(psid, mid);
        this.dest = dest;
    }

    public String getDest() {
        return dest;
    }
}
