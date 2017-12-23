package chess.piece;

/**
 * Represents a black or white piece
 */
public class Piece {

	/**
	 * Means the piece is white
	 */
	public static final boolean WHITE = false;
	
	/**
	 * Means the piece is black
	 */
	public static final boolean BLACK = true;
	
	/**
	 * The type of piece the piece is
	 */
	private final PieceType pieceType;

	/**
	 * The colour of the piece
	 */
	private final boolean colour;
	
	/**
	 * Creates a piece
	 * 
	 * @param pieceType The type of piece
	 * @param colour The piece's colour
	 */
	public Piece(PieceType pieceType, boolean colour) {
		this.pieceType = pieceType;
		this.colour = colour;
	}
	
	/**
	 * Gets the piece type
	 * 
	 * @return The piece type
	 */
	public PieceType getPieceType() {
		return pieceType;
	}

	/**
	 * Gets the colour of the piece
	 * 
	 * @return The piece colour
	 */
	public boolean getColour() {
		return colour;
	}
}
