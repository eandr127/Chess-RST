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
	 * The change in rows and columns
	 */
	private final int deltaX, deltaY;
	
	/**
	 * Creates a move
	 * 
	 * @param piece The piece making the move
	 * @param deltaX The change in rows
	 * @param deltaY The change in columns
	 */
	public Move(Piece piece, int deltaX, int deltaY) {
		this.piece = piece;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	/**
	 * Gets the piece making the move
	 * 
	 * @return The piece
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * Gets the change in rows
	 * 
	 * @return The change in rows
	 */
	public int getDeltaX()
	{
		return deltaX;
	}

	/**
	 * Gets the change in columns
	 * 
	 * @return The change in columns
	 */
	public int getDeltaY()
	{
		return deltaY;
	}
	
	/**
	 * Attempts to perform the turn with the piece
	 * 
	 * @return Whether the move was successful or not
	 */
	public boolean execute()
	{
		if(piece.isInitialized()) {
			return piece.move(piece.getCoords().add(deltaX, deltaY));
		}
		else {
			return false;
		}
	}
}
