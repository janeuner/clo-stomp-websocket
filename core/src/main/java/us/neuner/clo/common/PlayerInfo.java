package us.neuner.clo.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author Jarod Neuner <jarod@neuner.us>
 * @author Tim Hanson <tim@timgineer.io>
 * Describes the player participants in a CLO game session.
 */
@JsonPropertyOrder({ "playerName", "pieceName", "active", "hasAccused" })
public class PlayerInfo {
	
	private String playerName;
	private GameEntityId pieceName;
	private Boolean active;
	private Boolean hasAccused;

	/*
	 * Create a new @see PlayerInfo object.
	 * @param playerName the sender of the message
	 */
	@JsonCreator
	public PlayerInfo(@JsonProperty("playerName") String playerName) {
		this.playerName = playerName;
		setPieceName(GameEntityId.InvalidValue);
		setActive(false);
		setHasAccused(false);
	}

	/*
	 * Returns the sender of the message
	 */
	@JsonGetter("playerName")
	public String getPlayerName() {
		return playerName;
	}

	/*
	 * Sets the suspect being used by the player
	 */
	@JsonSetter("playerName") 
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/*
	 * Returns the suspect being used by the player.
	 */
	@JsonGetter("pieceName")
	public GameEntityId getPieceName() {
		return pieceName;
	}

	/*
	 * Sets the suspect being used by the player
	 */
	@JsonSetter("pieceName") 
	public void setPieceName(GameEntityId pieceName) {
		this.pieceName = pieceName;
	}

	/*
	 * Returns a boolean that indicates if it is this player's turn
	 */
	@JsonGetter("active")
	public Boolean getActive() {
		return active;
	}

	/*
	 * Sets the boolean that indicates if it is this player's turn
	 */
	@JsonSetter("active") 
	public void setActive(Boolean active) {
		this.active = active;
	}

	/*
	 * Returns a boolean that indicates if this player has made an accusation
	 */
	@JsonGetter("hasAccused")
	public Boolean getHasAccused() {
		return hasAccused;
	}

	/*
	 * Sets the PlayerInfo#hasAccused boolean that indicates if this player has made an accusation 
	 */
	@JsonSetter("hasAccused") 
	public void setHasAccused(Boolean hasAccused) {
		this.hasAccused = hasAccused;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((hasAccused == null) ? 0 : hasAccused.hashCode());
		result = prime * result + ((pieceName == null) ? 0 : pieceName.hashCode());
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
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
		PlayerInfo other = (PlayerInfo) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (hasAccused == null) {
			if (other.hasAccused != null)
				return false;
		} else if (!hasAccused.equals(other.hasAccused))
			return false;
		if (pieceName != other.pieceName)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}

}
