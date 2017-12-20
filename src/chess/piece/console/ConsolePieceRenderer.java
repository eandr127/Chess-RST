package chess.piece.console;

import java.io.PrintStream;
import java.util.Map;

import chess.piece.Piece;
import chess.piece.PieceRenderer;

public class ConsolePieceRenderer implements PieceRenderer {

	private final PrintStream console;

	private final Map<Piece, String> pieceMap;
	
	protected ConsolePieceRenderer(Map<Piece, String> pieceMap) {
		this(System.out, pieceMap);
	}
	
	protected ConsolePieceRenderer(PrintStream console, Map<Piece, String> pieceMap) {
		this.console = console;
		this.pieceMap = pieceMap;
	}
	
	public PrintStream getConsole() {
		return console;
	}
	
	@Override
	public void render(Piece piece) {
		System.out.print(pieceMap.getOrDefault(piece, " "));
	}
}
