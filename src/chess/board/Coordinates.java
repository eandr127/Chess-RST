package chess.board;

/**
 * Holds an X and Y value, where X is row and Y is column
 */
public class Coordinates
{
	/**
	 * The row and column
	 */
	private final int x, y;
	
	/**
	 * Converts chess notation (column as letter, row from 8->1) to coordinates (row, column)
	 * 
	 * @param column The column
	 * @param row The row
	 * @return The converted coordinates
	 */
	public static Coordinates fromChessNotation(char column, int row) {
		return fromChessNotation((int)Character.toUpperCase(column) - (int)'A' + 1, row);
	}
	
	/**
	 * Converts chess notation (column as letter, row from 8->1) to coordinates (row, column)
	 * 
	 * @param column The column as a number
	 * @param row The row
	 * @return The converted coordinates
	 */
	public static Coordinates fromChessNotation (int column, int row) {
		return new Coordinates(8 - row, column - 1);
	}
	
	/**
	 * Creates coordinates with a row and column value
	 * 
	 * @param x The row
	 * @param y The column
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the row
	 * 
	 * @return The row
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the column
	 * 
	 * @return The column
	 */
	public int getY() {
		return y;
	}

	/**
	 * Adds another set of coordinates to this one
	 * 
	 * @param coords The other coordinates
	 * @return The added coordinates
	 */
	public Coordinates add(Coordinates coords) {
		return add(coords.getX(), coords.getY());
	}
	
	/**
	 * Adds another set of coordinates to this one
	 * 
	 * @param x The row of the other coordinates
	 * @param y The column of the other coordinates
	 * @return The added coordinates
	 */
	public Coordinates add(int x, int y) {
		return new Coordinates(getX() + x, getY() + y);
	}
}
