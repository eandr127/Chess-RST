package chess.piece;

/**
 * The different teams a piece can be on
 */
public enum Team
{
	/**
	 * Black team
	 */
	BLACK,
	
	/**
	 * White team
	 */
	WHITE,
	
	/**
	 * No team
	 */
	NONE;
	
	/**
	 * Gets the opposite for a team
	 * 
	 * @return The opposite team
	 */
	public Team opposite() {
		if(this == BLACK) {
			return WHITE;
		}
		else if(this == WHITE) {
			return BLACK;
		}
		else {
			return NONE;
		}
	}
}
