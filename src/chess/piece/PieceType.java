package chess.piece;

/**
 * The type of chess piece
 */
public enum PieceType {
	
	/**
	 * A piece that represents a rook
	 */
	ROOK,
	
	/**
	 * A piece that represents a knight
	 */
	KNIGHT,
	
	/**
	 * A piece that represents a bishop
	 */
	BISHOP,
	
	/**
	 * A piece that represents a queen
	 */
	QUEEN,
	
	/**
	 * A piece that represents a king
	 */
	KING,
	
	/**
	 * A piece that represents a pawn
	 */
	PAWN,
	
	/**
     * Indicates that there is no piece
     */
	EMPTY(-1);
	
    /**
     * Indicates that there is no piece
     */
    // Black and white are same piece
    public static final Piece EMPTY_PIECE = PieceType.EMPTY.black;
    
	/**
	 * The white representation of a piece
	 */
	public final Piece white;
	
	/**
	 * The black representation of a piece
	 */
	public final Piece black;
	
	/**
	 * Creates a white and black piece representing this piece type
	 */
	private PieceType() {
		white = new Piece(this, Piece.WHITE);
		black = new Piece(this, Piece.BLACK);
	}
	
	private PieceType(int team) {
	    white = new Piece(this, team);
	    black = new Piece(this, team);
	}
}
