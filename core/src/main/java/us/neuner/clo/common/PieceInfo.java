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
	
	public static void moveTo(GameBoardLocation loc, Boolean b) {
		if (b==True) {
			this.setLoc(location)
		}
		else if (this.isSuspect()){
			switch (this.loc.toString()) {
			case "Study":
				if(location.equals("Hallway 01") || location.equals("Hallway 03") || location.equals("Kitchen")) {
					this.setLoc(location)
				}
			case "Hall":
				if(location.equals("Hallway 01") || location.equals("Hallway 02") || location.equals("Hallway 03")) {
					this.setLoc(location)
				}
			case "Lounge":
				if(location.equals("Hallway 02") || location.equals("Hallway 05") || location.equals("Conservatory")) {
					this.setLoc(location)
				}
			case "Library":
				if(location.equals("Hallway 03") || location.equals("Hallway 06") || location.equals("Hallway 08")) {
					this.setLoc(location)
				}
			case "Billiard Room":
				if(location.equals("Hallway 04") || location.equals("Hallway 06") || location.equals("Hallway 07")) || location.equals("Hallway 09") {
					this.setLoc(location)
				}
			case "Dining Room":
				if(location.equals("Hallway 05") || location.equals("Hallway 07") || location.equals("Hallway 10")) {
					this.setLoc(location)
				}
			case "Convervatory":
				if(location.equals("Hallway 08") || location.equals("Hallway 11") || location.equals("Lounge")) {
					this.setLoc(location)
				}
			case "Ballroom":
				if(location.equals("Hallway 11") || location.equals("Hallway 09") || location.equals("Hallway 12")) {
					this.setLoc(location)
				}
			case "Kitchen":
				if(location.equals("Hallway 12") || location.equals("Hallway 10") || location.equals("Study")) {
					this.setLoc(location)
				}
			case "Hallway 01":
				if(location.equals("Study") || location.equals("Hall")) {
					this.setLoc(location)
				}
			case "Hallway 02":
				if(location.equals("Hall") || location.equals("Lounge")) {
					this.setLoc(location)
				}
			case "Hallway 03":
				if(location.equals("Study") || location.equals("Library")) {
					this.setLoc(location)
				}
			case "Hallway 04":
				if(location.equals("Hall") || location.equals("Billiard Room")) {
					this.setLoc(location)
				}
			case "Hallway 05":
				if(location.equals("Lounge") || location.equals("Dining Room")) {
					this.setLoc(location)
				}
			case "Hallway 06":
				if(location.equals("Library") || location.equals("Billiard Room")) {
					this.setLoc(location)
				}
			case "Hallway 07":
				if(location.equals("Billiard Room") || location.equals("Dining Room")) {
					this.setLoc(location)
				}
			case "Hallway 08":
				if(location.equals("Library") || location.equals("Conservatory")) {
					this.setLoc(location)
				}
			case "Hallway 09":
				if(location.equals("Ballroom") || location.equals("Billiard Room")) {
					this.setLoc(location)
				}
			case "Hallway 10":
				if(location.equals("Kitchen") || location.equals("Dining Room")) {
					this.setLoc(location)
				}
			case "Hallway 11":
				if(location.equals("Conservatory") || location.equals("Ballroom")) {
					this.setLoc(location)
				}
			case "Hallway 12":
				if(location.equals("Ballroom") || location.equals("Kitchen")) {
					this.setLoc(location)
				}
			case "Start - Miss Scarlet":
				if(location.equals("Hallway 02")) {
					this.setLoc(location)
				}
			case "Start - Mrs White":
				if(location.equals("Hallway 12")) {
					this.setLoc(location)
				}
			case "Start - Mr Green":
				if(location.equals("Hallway 11")) {
					this.setLoc(location)
				}
			case "Start - Mrs Peacock":
				if(location.equals("Hallway 08")) {
					this.setLoc(location)
				}
			case "Start Professor Plum":
				if(location.equals("Hallway 03")) {
					this.setLoc(location)
				}
			case "Start - Colonel Mustard":
				if(location.equals("Hallway 05")) {
					this.setLoc(location)
				}
			}
		}
		else if (this.isWeapon()){
			
		}
	}
}
