package chess.board.console;

import java.io.PrintStream;

import chess.board.Board;
import chess.piece.console.ConsolePieceRenderer;
import chess.piece.console.ConsolePieces;

/**
 * A board that prints to a console
 */
public abstract class ConsoleBoard extends Board {
	
	/**
	 * Renders a piece to a the console
	 */
	protected final ConsolePieceRenderer renderer;
	
	/**
	 * The console to print to
	 */
	protected final PrintStream console;
	
	/**
	 * Creates a ConsoleBoard that prints to System.out
	 * 
	 * @param pieces What the pieces look like
	 */
	public ConsoleBoard(ConsolePieces pieces) {
		this(pieces, System.out);
	}
	
	/**
	 * Creates a ConsoleBoard that can print to a console
	 * 
	 * @param pieces What the pieces look like
	 * @param console The console to print to
	 */
	public ConsoleBoard(ConsolePieces pieces, PrintStream console) {
		this.renderer = new ConsolePieceRenderer(pieces, console);
		this.console = console;
	}
}
