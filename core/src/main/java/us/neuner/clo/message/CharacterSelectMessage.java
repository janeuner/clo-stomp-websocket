package us.neuner.clo.message;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import us.neuner.clo.common.*;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "characterSelect" message.
 */
@JsonPropertyOrder({ "type", "psid", "mid", "player", "entity" })
public class CharacterSelectMessage extends Message {

	@JsonProperty
    private PlayerInfo player; 

	@JsonProperty
    private GameEntityId entity; 

	/*
     * Creates a new @see CharacterSelectMessage object.
     * @param psid the player-session identifier associated with this message
	 * @param player the player making the selection
	 * @param entity the character selected by the player
	 */
	public CharacterSelectMessage(String psid, PlayerInfo player, GameEntityId entity) {
		super(psid);
		// TODO Auto-generated constructor stub
	}

	/*
     * Creates a new @see CharacterSelectMessage object.
     * @param psid the player-session identifier associated with this message
     * @param mid the unique message identifier for this message
	 * @param player the player making the selection
	 * @param entity the character selected by the player
	 */
    @JsonCreator
	public CharacterSelectMessage(@JsonProperty("psid") String psid, 
    		@JsonProperty("mid") UUID mid, 
    		@JsonProperty("player") PlayerInfo player,
    		@JsonProperty("entity") GameEntityId entity) {
		super(psid, mid);
		this.player = player;
		this.entity = entity;
	}

    /*
     * Returns the player making the selection
     */
    @JsonIgnore
	public PlayerInfo getPlayer() {
		return player;
	}

    /*
     * Returns the character selected by the player
     */
    @JsonIgnore
	public GameEntityId getEntity() {
		return entity;
	}
}
