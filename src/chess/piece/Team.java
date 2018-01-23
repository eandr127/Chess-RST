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
