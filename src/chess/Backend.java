package chess;

import chess.board.Board;
import chess.help.Help;
import chess.player.Player;

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
	 * The players in the game
	 */
	private final Player player1, player2;
	
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
	public Backend(Board board, Player player1, Player player2, Help help) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
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

	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
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
