package chess.piece;

public class Piece {

	public static final boolean WHITE = false;
	public static final boolean BLACK = true;
	
	private final PieceType pieceType;

	private final boolean colour;
	
	public Piece(PieceType pieceType, boolean colour) {
		this.pieceType = pieceType;
		this.colour = colour;
	}
	
	public PieceType getPieceType() {
		return pieceType;
	}

	public boolean getColour() {
		return colour;
	}
}
