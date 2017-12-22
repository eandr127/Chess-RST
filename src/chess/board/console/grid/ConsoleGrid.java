package chess.board.console.grid;

public enum ConsoleGrid {
	
	ASCII('+', '+', '+', '+', '+', '+', '+', '+', '+', '|', '-'),
	UTF8_LIGHT('┌', '┐', '└', '┘', '┤', '├', '┴', '┬', '┼', '│', '─'),
	UTF8_HEAVY('┏', '┓', '┗', '┛', '┫', '┣', '┻', '┳', '╋', '┃', '━');
	
	public final char topLeftCorner;
	public final char topRightCorner;
	public final char bottomLeftCorner;
	public final char bottomRightCorner;
	public final char rightSide;
	public final char leftSide;
	public final char bottomSide;
	public final char topSide;
	public final char cross;
	public final char vertical;
	public final char horizontal;
	
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
