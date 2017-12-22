package chess.board.console;

import java.io.PrintStream;

import chess.board.Board;
import chess.piece.console.ConsolePieceRenderer;
import chess.piece.console.ConsolePieces;

public abstract class ConsoleBoard extends Board {
	
	protected final ConsolePieceRenderer renderer;
	protected final PrintStream console;
	
	public ConsoleBoard(ConsolePieces pieces) {
		this(pieces, System.out);
	}
	
	public ConsoleBoard(ConsolePieces pieces, PrintStream console) {
		this.renderer = new ConsolePieceRenderer(pieces, console);
		this.console = console;
	}
}
