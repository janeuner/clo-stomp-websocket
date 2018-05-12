package us.neuner.clo.common;

/*
 * @author Jarod Neuner <jarod@neuner.us>
 * @author Mag Faris <mfaris627@gmail.com>
 * Maps a game piece to a game board location.
 */
public class PieceInfo {

	private final GameEntityId id;
	private GameEntityId loc;

	/*
	 * Create a new @see PieceInfo object.
	 * @param id this @see GameEntityId for this piece.  @see PieceInfo#isSuspect 
	 * 		or @see PieceInfo#isWeapon must be true.  
	 * @param loc the initial location.  @see PieceInfo#isLocation must be true.
	 */
	public PieceInfo(GameEntityId id, GameEntityId loc) {

		if (!isSuspect(id) && !isWeapon(id))
			throw new IllegalArgumentException("id must be a suspect or a weapon");
		if (!isLocation(loc))
			throw new IllegalArgumentException("loc must be a location");
		this.id = id;
		this.loc = loc;
	}

	/*
	 * Returns the current location of this piece
	 */
	public GameEntityId getLoc() {
		
		return this.loc;
	}

	/*
	 * Sets the new location of this piece.
	 * @param loc the new location.  Must return true when passed to @see PieceInfo#isLocation.
	 * @return true if loc was a location; otherwise, false. 
	 */
	public Boolean setLoc(GameEntityId loc) {
		Boolean moveSuccessful = false;
		if (PieceInfo.isLocation(loc)) {
			this.loc = loc;
			moveSuccessful = true;
		}
		return moveSuccessful;
	}

	/*
	 * Returns the id of this piece
	 */
	public GameEntityId getId() {
		
		return this.id;
	}
	
	/*
	 * Attempt to move this piece to a new location using the movement
	 * rules for an active player that is executing a move action.
	 * @param dest the destination of this move
	 * @returns True if the move is valid; otherwise, false.
	 */
	public Boolean moveTo(GameEntityId dest) {
		
		Boolean moveSuccessful = false;

		// these conditions are checked in the constructor... but no harm in confirming.
		assert(PieceInfo.isSuspect(this.getId()));
		assert(!PieceInfo.isLocation(this.getLoc()));
		
		if (PieceInfo.isValidMove(this.getLoc(), dest)) {
			this.setLoc(dest);
			moveSuccessful = true;
		}
		
		return moveSuccessful;
	}
		

	/*
	 * Returns true if the 'id' is a suspect 
	 */
	public static Boolean isSuspect(GameEntityId id) {
		
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

	/*
	 * Returns true if the 'id' is a weapon 
	 */
	public static Boolean isWeapon(GameEntityId id) {
		
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

	/*
	 * Returns true if the 'id' is a location on the game board 
	 */
	public static Boolean isLocation(GameEntityId id) {
		return isRoom(id) || isHallway(id) || isStartingLocation(id);
	}

	/*
	 * Returns true if the 'id' is a room on the game board 
	 */
	public static Boolean isRoom(GameEntityId id) {
		
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

	/*
	 * Returns true if the 'id' is a hallway on the game board 
	 */
	public static Boolean isHallway(GameEntityId id) {
		
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

	/*
	 * Returns true if the 'id' is a starting position on the game board 
	 */
	public static Boolean isStartingLocation(GameEntityId id) {
		
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

	public static Boolean isValidMove(GameEntityId src, GameEntityId dest) {
		
		Boolean moveSuccessful = false;

		if (PieceInfo.isLocation(src) && PieceInfo.isLocation(dest)) {
				
			switch (src) {
				case Study:
					moveSuccessful = (dest == GameEntityId.H01) || (dest == GameEntityId.H03) || (dest == GameEntityId.Kitchen);
					break;
				case Hall:
					moveSuccessful = (dest == GameEntityId.H01) || (dest == GameEntityId.H02) || (dest == GameEntityId.H03);
					break;
				case Lounge:
					moveSuccessful = (dest == GameEntityId.H02) || (dest == GameEntityId.H05) || (dest == GameEntityId.Conservatory);
					break;
				case Library:
					moveSuccessful = (dest == GameEntityId.H03) || (dest == GameEntityId.H06) || (dest == GameEntityId.H08);
					break;
				case BilliardRoom:
					moveSuccessful = (dest == GameEntityId.H04) || (dest == GameEntityId.H06) || (dest == GameEntityId.H07) || (dest == GameEntityId.H09);
					break;
				case DiningRoom:
					moveSuccessful = (dest == GameEntityId.H05) || (dest == GameEntityId.H07) || (dest == GameEntityId.H10);
					break;
				case Conservatory:
					moveSuccessful = (dest == GameEntityId.H08) || (dest == GameEntityId.H11) || (dest == GameEntityId.Lounge);
					break;
				case BallRoom:
					moveSuccessful = (dest == GameEntityId.H11) || (dest == GameEntityId.H09) || (dest == GameEntityId.H12);
					break;
				case Kitchen:
					moveSuccessful = (dest == GameEntityId.H12) || (dest == GameEntityId.H10) || (dest == GameEntityId.Study);
					break;
				case H01:
					moveSuccessful = (dest == GameEntityId.Study) || (dest == GameEntityId.Hall);
					break;
				case H02:
					moveSuccessful = (dest == GameEntityId.Hall) || (dest == GameEntityId.Lounge);
					break;
				case H03:
					moveSuccessful = (dest == GameEntityId.Study) || (dest == GameEntityId.Library);
					break;
				case H04:
					moveSuccessful = (dest == GameEntityId.Hall) || (dest == GameEntityId.BilliardRoom);
					break;
				case H05:
					moveSuccessful = (dest == GameEntityId.Lounge) || (dest == GameEntityId.DiningRoom);
					break;
				case H06:
					moveSuccessful = (dest == GameEntityId.Library) || (dest == GameEntityId.BilliardRoom);
					break;
				case H07:
					moveSuccessful = (dest == GameEntityId.BilliardRoom) || (dest == GameEntityId.DiningRoom);
					break;
				case H08:
					moveSuccessful = (dest == GameEntityId.Library) || (dest == GameEntityId.Conservatory);
					break;
				case H09:
					moveSuccessful = (dest == GameEntityId.BallRoom) || (dest == GameEntityId.BilliardRoom);
					break;
				case H10:
					moveSuccessful = (dest == GameEntityId.Kitchen) || (dest == GameEntityId.DiningRoom);
					break;
				case H11:
					moveSuccessful = (dest == GameEntityId.Conservatory) || (dest == GameEntityId.BallRoom);
					break;
				case H12:
					moveSuccessful = (dest == GameEntityId.BallRoom) || (dest == GameEntityId.Kitchen);
					break;
				case MissScarletStart:
					moveSuccessful = (dest == GameEntityId.H02);
					break;
				case MrsWhiteStart:
					moveSuccessful = (dest == GameEntityId.H12);
					break;
				case MrGreenStart:
					moveSuccessful = (dest == GameEntityId.H11);
					break;
				case MrsPeacockStart:
					moveSuccessful = (dest == GameEntityId.H08);
					break;
				case ProfessorPlumStart:
					moveSuccessful = (dest == GameEntityId.H03);
					break;
				case ColonelMustardStart:
					moveSuccessful = (dest == GameEntityId.H05);
					break;
				default:
					break;
			}
		}
		
		return moveSuccessful;
	}
}
