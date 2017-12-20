package chess.piece;

public enum PieceType {
	
	
	
	ROOK,
	KNIGHT,
	BISHOP,
	QUEEN,
	KING,
	PAWN,
	EMPTY;
	
	public final Piece white;
	public final Piece black;
	
	private PieceType() {
		white = new Piece(this, Piece.WHITE);
		black = new Piece(this, Piece.BLACK);
	}
}
