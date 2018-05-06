package us.neuner.clo.message;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import us.neuner.clo.common.PlayerInfo;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * Encapsulates the "gameSetup" message.
 */
public class GameSetupMessage extends Message {

    private List<PlayerInfo> players; 

	/*
     * Creates a new @see GameSetupMessage object.
     * @param psid the player-session identifier associated with this message
	 * @param players the participating players in the current game session
	 */
    @JsonCreator
	public GameSetupMessage(@JsonProperty("psid") String psid, @JsonProperty("players") List<PlayerInfo> players) {
		super(psid);
		this.players = new ArrayList<PlayerInfo>(players);
	}

    /*
     * Returns the participating players in the current game session
     * @param index index of the @see PlayerInfo to return.
     */
    public PlayerInfo getPlayer(int index) {
        return players.get(index);
    }

    /*
     * Returns the participating players in the current game session
     */
    public int getPlayerCount() {
        return players.size();
    }
}
