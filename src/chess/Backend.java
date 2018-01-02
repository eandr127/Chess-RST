package chess;

import chess.board.Board;
import chess.help.Help;

/**
 * Holds information required for a backend implementation
 */
public class Backend
{
	/**
	 * The board to display with
	 */
	private final Board board;
	
	/**
	 * The help prompt to give information to the player
	 */
	private final Help help;

	/**
	 * Creates a backend
	 * 
	 * @param board The board to display with
	 * @param help The help prompt to give information to the player
	 */
	public Backend(Board board, Help help) {
		this.board = board;
		this.help = help;
	}
	
	/**
	 * Gets the board to display with
	 * 
	 * @return The board
	 */
	public Board getBoard()
	{
		return board;
	}

	/**
	 * Gets the help prompt to give information to the player
	 * 
	 * @return The help prompt
	 */
	public Help getHelp()
	{
		return help;
	}
}
