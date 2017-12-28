package chess.piece;

/**
 * Represents a black or white piece
 */
public class Piece {
    
	/**
	 * Means the piece is white
	 */
	public static final int WHITE = 0;
	
	/**
	 * Means the piece is black
	 */
	public static final int BLACK = 1;
	
	/**
	 * The type of piece the piece is
	 */
	private final PieceType pieceType;

	/**
	 * The team of the piece
	 */
	private final boolean team;
	
	/**
	 * Creates a piece
	 * 
	 * @param pieceType The type of piece
	 * @param team The piece's team
	 */
	public Piece(PieceType pieceType, int team) {
		this.pieceType = pieceType;
		this.team = team == BLACK;
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
	 * Gets the team of the piece
	 * 
	 * @return The piece team
	 */
	public int getTeam() {
		return team ? BLACK : WHITE;
	}
}
