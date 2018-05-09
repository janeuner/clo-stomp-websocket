package us.neuner.clo.common;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * @author Mag Faris <mfaris627@gmail.com>
 * Maps a game piece to a game board location.
 */
public class PieceInfo {

	private final GameEntityId id;
	private GameEntityId loc;
	
	public PieceInfo(GameEntityId id, GameEntityId loc) {

		this.id = id;
		this.loc = loc;
	}

	public GameEntityId getLoc() {
		return this.loc;
	}

	public void setLoc(GameEntityId loc) {
		this.loc = loc;
	}

	public GameEntityId getId() {
		return this.id;
	}

	static Boolean isSuspect(GameEntityId id) {
		switch (id) {
		case MissScarlet:
			return true;
		case MrsWhite:
			return true;
		case MrGreen:
			return true;
		case MrsPeacock:
			return true;
		case ProfessorPlum:
			return true;
		case ColonelMustard:
			return true;
		default:
			break;
		}
		return false;
	}

	static Boolean isWeapon(GameEntityId id) {
		switch (id) {
		case LeadPipe:
			return true;
		case CandleStick:
			return true;
		case Rope:
			return true;
		case Knife:
			return true;
		case Wrench:
			return true;
		case Revolver:
			return true;	
		default:
			break;
		}
		return false;
	}

	static Boolean isLocation(GameEntityId id) {
		return isRoom(id) || isHallway(id) || isStartingLocation(id);
	}

	static Boolean isRoom(GameEntityId id) {
		switch (id) {
		case BilliardRoom:
			return true;
		case Kitchen:
			return true;
		case BallRoom:
			return true;
		case Conservatory:
			return true;
		case Library:
			return true;
		case Study:
			return true;
		case Hall:
			return true;
		case Lounge:
			return true;
		case DiningRoom:
			return true;
		default:
			break;
		}
		return false;
	}

	static Boolean isHallway(GameEntityId id) {
		switch (id) {
			//TODO: Add remaining hallways
		case H01:
			return true;
		case H02:
			return true;
		case H03:
			return true;
		case H04:
			return true;
		case H05:
			return true;
		case H06:
			return true;
		case H07:
			return true;
		case H08:
			return true;
		case H09:
			return true;	
		case H10:
			return true;
		case H11:
			return true;
		case H12:
			return true;
		default:
			break;
		}
		return false;
	}

	static Boolean isStartingLocation(GameEntityId id) {
		switch (id) {
		case MissScarletStart:
			return true;
		case MrsWhiteStart:
			return true;
		case MrGreenStart:
			return true;
		case MrsPeacockStart:
			return true;
		case ProfessorPlumStart:
			return true;
		case ColonelMustardStart:
			return true;
		default:
			break;
		}
		return false;
	}
}
