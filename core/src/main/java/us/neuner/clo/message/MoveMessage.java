package us.neuner.clo.message;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import us.neuner.clo.common.GameEntityId;

/*
 * @author Jonathon Yenovkian <jonathon.yenovkian@gmail.com>
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "move" message.
 */
@JsonPropertyOrder({ "type", "psid", "mid", "dest" })
public class MoveMessage extends Message {

    private GameEntityId dest;

    public MoveMessage(@JsonProperty("psid") String psid, @JsonProperty("dest") GameEntityId dest) {
    	super(psid);
        this.dest = dest;
    }
    
    @JsonCreator
    public MoveMessage(@JsonProperty("psid") String psid, @JsonProperty("mid") UUID mid, @JsonProperty("dest") GameEntityId dest) {
    	super(psid, mid);
        this.dest = dest;
    }

    public GameEntityId getDest() {
        return dest;
    }
}
