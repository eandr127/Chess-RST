package chess.board.console;

import chess.ConsoleIO;
import chess.board.Board;
import chess.piece.Piece;
import chess.piece.console.ConsolePieceRenderer;
import chess.piece.console.ConsolePieces;

/**
 * A board that prints to a console
 */
public abstract class ConsoleBoard extends Board
{

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
	public ConsoleBoard(ConsolePieces pieces, ConsoleIO console)
	{
		super();

		this.renderer = new ConsolePieceRenderer(pieces, console);
		this.console = console;
	}

	/**
	 * Creates a ConsoleBoard that can print to a console
	 * 
	 * @param pieces What the pieces look like
	 * @param console The console to print to
	 */
	public ConsoleBoard(Piece[][] arrangement, ConsolePieces pieces, ConsoleIO console)
	{
		super(arrangement);

		this.renderer = new ConsolePieceRenderer(pieces, console);
		this.console = console;
	}
}
