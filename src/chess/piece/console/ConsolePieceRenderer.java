package chess.piece.console;

import java.io.PrintStream;

import chess.piece.Piece;
import chess.piece.PieceRenderer;

public class ConsolePieceRenderer implements PieceRenderer {

	private final ConsolePieces pieces;
	private final PrintStream console;
	
	public ConsolePieceRenderer(ConsolePieces pieces) {
		this(pieces, System.out);
	}
	
	public ConsolePieceRenderer(ConsolePieces pieces, PrintStream console) {
		this.pieces = pieces;
		this.console = console;
	}
	
	public ConsolePieces getPieces() {
		return pieces;
	}
	
	public PrintStream getConsole() {
		return console;
	}
	
	@Override
	public void render(Piece piece) {
		console.print(pieces.getOrDefault(piece, " "));
	}
}
