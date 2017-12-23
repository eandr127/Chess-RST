package chess.board.console.grid;

/**
 * Represents the look of a certain ConsoleBoard
 */
public enum ConsoleGrid {
	
	/**
	 * Prints only ASCII characters
	 */
	ASCII('+', '+', '+', '+', '+', '+', '+', '+', '+', '|', '-'),
	
	/**
	 * Prints light Unicode characters
	 */
	UTF8_LIGHT('┌', '┐', '└', '┘', '┤', '├', '┴', '┬', '┼', '│', '─'),
	
	/**
	 * Prints heavy Unicode characters
	 */
	UTF8_HEAVY('┏', '┓', '┗', '┛', '┫', '┣', '┻', '┳', '╋', '┃', '━');
	
	/**
	 * The top left corner of the board
	 */
	public final char topLeftCorner;
	
	/**
	 * The top right corner of the board
	 */
	public final char topRightCorner;
	
	/**
	 * The bottom left corner of the board
	 */
	public final char bottomLeftCorner;
	
	/**
	 * The bottom right corner of the board
	 */
	public final char bottomRightCorner;
	
	/**
	 * The right side of the board
	 */
	public final char rightSide;
	
	/**
	 * The left side of the board
	 */
	public final char leftSide;
	
	/**
	 * The bottom side of the board
	 */
	public final char bottomSide;
	
	/**
	 * The top side of the board
	 */
	public final char topSide;
	
	/**
	 * The four way cross of the board
	 */
	public final char cross;
	
	/**
	 * The vertical line of the board
	 */
	public final char vertical;
	
	/**
	 * The horizontal line of the board
	 */
	public final char horizontal;
	
	/**
	 * Creates a ConsoleGrid with a certain look
	 * 
	 * @param topLeftCorner The top left corner of the board
	 * @param topRightCorner The top right corner of the board
	 * @param bottomLeftCorner The bottom left corner of the board
	 * @param bottomRightCorner The bottom right corner of the board
	 * @param rightSide The left side of the board
	 * @param leftSide The left side of the board
	 * @param bottomSide The bottom side of the board
	 * @param topSide The top side of the board
	 * @param cross The four way cross of the board
	 * @param vertical The vertical line of the board
	 * @param horizontal The horizontal line of the board
	 */
	private ConsoleGrid(char topLeftCorner, char topRightCorner, char bottomLeftCorner, char bottomRightCorner,
			char rightSide, char leftSide, char bottomSide, char topSide, char cross, char vertical, char horizontal) {
		this.topLeftCorner = topLeftCorner;
		this.topRightCorner = topRightCorner;
		this.bottomLeftCorner = bottomLeftCorner;
		this.bottomRightCorner = bottomRightCorner;
		this.rightSide = rightSide;
		this.leftSide = leftSide;
		this.bottomSide = bottomSide;
		this.topSide = topSide;
		this.cross = cross;
		this.vertical = vertical;
		this.horizontal = horizontal;
	}
}
