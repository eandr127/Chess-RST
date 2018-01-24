package chess.board;

/**
 * Holds an X and Y value, where X is column and Y is row
 */
public class Coordinates
{
	/**
	 * The column and row
	 */
	private final int x, y;

	/**
	 * Creates coordinates from chess notation (column as letter, row from 8->1)
	 * 
	 * pre: column The column
	 * pre: row The row
	 */
	public Coordinates(char column, int row)
	{
		this((int) Character.toUpperCase(column) - (int) 'A' + 1, row);
	}

	/**
	 * Creates coordinates with a row and column value
	 * 
	 * pre: x The column
	 * pre: y The row
	 */
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the column
	 * 
	 * post: The column
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Gets the row
	 * 
	 * post: The row
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Adds another set of coordinates to this one
	 * 
	 * pre: coords The other coordinates
	 * post: The added coordinates
	 */
	public Coordinates add(Coordinates coords)
	{
		return add(coords.getX(), coords.getY());
	}

	/**
	 * Adds another set of coordinates to this one
	 * 
	 * pre: x The row of the other coordinates
	 * pre: y The column of the other coordinates
	 * post: The added coordinates
	 */
	public Coordinates add(int x, int y)
	{
		return new Coordinates(getX() + x, getY() + y);
	}

	@Override
	public boolean equals(Object other)
	{
		return other instanceof Coordinates && this.getX() == ((Coordinates) other).getX()
				&& this.getY() == ((Coordinates) other).getY();
	}
}
