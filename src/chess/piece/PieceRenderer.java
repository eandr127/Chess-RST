package chess.piece;

/**
 * Renders a piece to a chosen format
 */
public interface PieceRenderer
{

	/**
	 * Renders a piece
	 * 
	 * pre: piece The piece to render
	 */
	public void render(Piece piece);
}
