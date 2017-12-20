package chess.piece.console;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import chess.piece.Piece;
import chess.piece.PieceType;

public class UTF8PieceRenderer extends ConsolePieceRenderer {

	private static final Map<Piece, String> utf8Pieces;
	
	static {
		Map<Piece, String> pieces = new HashMap<>();
		pieces.put(PieceType.BISHOP.black,	"♝");
		pieces.put(PieceType.BISHOP.white,	"♗");
		pieces.put(PieceType.KING.black,	"♚");
		pieces.put(PieceType.KING.white,	"♔");
		pieces.put(PieceType.KNIGHT.black,	"♞");
		pieces.put(PieceType.KNIGHT.white,	"♘");
		pieces.put(PieceType.PAWN.black,	"♟");
		pieces.put(PieceType.PAWN.white,	"♙");
		pieces.put(PieceType.ROOK.black,	"♜");
		pieces.put(PieceType.ROOK.white,	"♖");
		pieces.put(PieceType.QUEEN.black,	"♛");
		pieces.put(PieceType.QUEEN.white,	"♕");
		pieces.put(PieceType.EMPTY.black,	" ");
		pieces.put(PieceType.EMPTY.white,	" ");
		
		utf8Pieces = Collections.unmodifiableMap(pieces);
	}

	public UTF8PieceRenderer() {
		super(utf8Pieces);
	}

}
