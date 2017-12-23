package chess.piece.console;

import java.io.PrintStream;

import chess.piece.Piece;
import chess.piece.PieceRenderer;

/**
 * Renders console pieces to the console
 */
public class ConsolePieceRenderer implements PieceRenderer {

	/**
	 * The look of the pieces to print
	 */
	private final ConsolePieces pieces;
	
	/**
	 * Where to print the pieces
	 */
	private final PrintStream console;
	
	/**
	 * Creates a new console renderer that prints to System.out
	 * 
	 * @param pieces What the pieces look like
	 */
	public ConsolePieceRenderer(ConsolePieces pieces) {
		this(pieces, System.out);
	}
	
	/**
	 * Creates a new console renderer that prints to System.out
	 * 
	 * @param pieces What the pieces look like
	 * @param console Where to print output
	 */
	public ConsolePieceRenderer(ConsolePieces pieces, PrintStream console) {
		this.pieces = pieces;
		this.console = console;
	}
	
	/**
	 * Gets the pieces
	 * 
	 * @return The pieces
	 */
	public ConsolePieces getPieces() {
		return pieces;
	}
	
	/**
	 * Gets the console to print to
	 * 
	 * @return The console to print to
	 */
	public PrintStream getConsole() {
		return console;
	}
	
	/**
	 * Renders a piece to the console
	 */
	@Override
	public void render(Piece piece) {
		console.print(pieces.getOrDefault(piece, " "));
	}
}
