package chess;

import chess.board.Board;
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
	 * Creates a backend
	 * 
	 * @param board The board to display with
	 * @param help The help prompt to give information to the player
	 */
	public Backend(Board board, Player player1, Player player2)
	{
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
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
	 * Gets the first player
	 * 
	 * @return The first player
	 */
	public Player getPlayer1()
	{
		return player1;
	}

	/**
	 * Gets the second player
	 * 
	 * @return The second player
	 */
	public Player getPlayer2()
	{
		return player2;
	}
}
