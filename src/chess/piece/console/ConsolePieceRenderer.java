package chess.piece.console;

import chess.ConsoleIO;
import chess.piece.Piece;
import chess.piece.PieceRenderer;

/**
 * Renders console pieces to the console
 */
public class ConsolePieceRenderer implements PieceRenderer
{

	/**
	 * The look of the pieces to print
	 */
	private final ConsolePieces pieces;

	/**
	 * Where to print the pieces
	 */
	private final ConsoleIO console;

	/**
	 * Creates a new console renderer that prints to System.out
	 * 
	 * @param pieces What the pieces look like
	 * @param console Where to print output
	 */
	public ConsolePieceRenderer(ConsolePieces pieces, ConsoleIO console)
	{
		this.pieces = pieces;
		this.console = console;
	}

	/**
	 * Gets the pieces
	 * 
	 * @return The pieces
	 */
	public ConsolePieces getPieces()
	{
		return pieces;
	}

	/**
	 * Gets the console to print to
	 * 
	 * @return The console to print to
	 */
	public ConsoleIO getConsole()
	{
		return console;
	}

	/**
	 * Renders a piece to the console
	 */
	@Override
	public void render(Piece piece)
	{
		console.getConsoleOutput().print(pieces.get(piece.getPieceType(), piece.getTeam()));
	}
}
