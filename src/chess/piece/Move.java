package chess.piece;

/**
 * Represents a move that a piece can take
 */
public class Move
{
	/**
	 * The piece making the move
	 */
	private final Piece piece;

	/**
	 * The change in columns and rows
	 */
	private final int deltaX, deltaY;

	/**
	 * Creates a move
	 * 
	 * pre: piece The piece making the move
	 * pre: deltaX The change in rows
	 * pre: deltaY The change in columns
	 */
	public Move(Piece piece, int deltaX, int deltaY)
	{
		this.piece = piece;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	/**
	 * Gets the piece making the move
	 * 
	 * post: The piece
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * Gets the change in columns
	 * 
	 * post: The change in columns
	 */
	public int getDeltaX()
	{
		return deltaX;
	}

	/**
	 * Gets the change in rows
	 * 
	 * post: The change in rows
	 */
	public int getDeltaY()
	{
		return deltaY;
	}

	/**
	 * Attempts to perform the turn with the piece
	 * 
	 * post: Whether the move was successful or not
	 */
	public boolean execute()
	{
		if (piece.isInitialized())
		{
			return piece.move(piece.getCoords().add(deltaX, deltaY));
		} else
		{
			return false;
		}
	}
}
