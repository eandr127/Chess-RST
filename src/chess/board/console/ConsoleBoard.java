package chess.board.console;

import chess.ConsoleIO;
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
	protected final ConsoleIO console;
	
	/**
	 * Creates a ConsoleBoard that can print to a console
	 * 
	 * @param pieces What the pieces look like
	 * @param console The console to print to
	 */
	public ConsoleBoard(ConsolePieces pieces, ConsoleIO console) {
		this.renderer = new ConsolePieceRenderer(pieces, console);
		this.console = console;
	}
}
