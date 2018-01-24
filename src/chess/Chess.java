package chess;

import chess.board.Board;
import chess.board.console.brackets.ConsoleBracketsBoard;
import chess.board.console.grid.ConsoleGrid;
import chess.board.console.grid.ConsoleGridBoard;
import chess.help.Help;
import chess.help.console.ConsoleHelp;
import chess.piece.Team;
import chess.piece.console.ConsolePieces;
import chess.player.Player;
import chess.player.console.ConsolePlayer;

/*
 * A game of chess
 * Chess.java
 * ICS3U
 * January 24th, 2018
 * Ryan Larkin, Caelan Douglas, Dmitry Tsarapkine
 */
public class Chess
{

	/**
	 * Chess help. Can be normal or console implementation.
	 */
	static Help help;

	/**
	 * The entrypoint for the program
	 * 
	 * @param args The arguments for the program (unused)
	 */
	public static void main(String[] args)
	{
		ConsoleIO console = new ConsoleIO(System.out, System.in);

		// Create board object that can be drawn anywhere
		Backend backend;

		// Show a little welcome thing
		console.getConsoleOutput().println("    Welcome to Chess\n~~~~~~~~~~~~~~~~~~~~~~~~\n");

		// Set up a board that prints to console
		backend = setUpConsoleBoard(console);

		// Is there a checkmate taking place?
		boolean checkmate = false;
		// While there is no checkmate
		while (!checkmate || (backend.getPlayer1().offerDraw() && backend.getPlayer2().offerDraw()))
		{
			// If there is a checkmate against Player 1 OR Player 1 wants to resign
			if (backend.getBoard().isCheckmate(backend.getPlayer1().getTeam()))
			{
				// Player 2 wins
				win(backend, console, backend.getPlayer2());
				break;
			}
			//Check for draw offers
			if (backend.getPlayer1().checkDrawOffer()) {
				win(backend, console, null);
				break;
			}
			// Take player 1's turn
			backend.getPlayer1().takeTurn();
			
			// If player 1 wants to resign
			if (backend.getPlayer1().resign()) {
				// Player 2 wins
				win(backend, console, backend.getPlayer2());
				break;
			}
			
			// If there is a checkmate against Player 2 OR Player 2 wants to resign
			if (backend.getBoard().isCheckmate(backend.getPlayer2().getTeam()) || backend.getPlayer2().resign())
			{
				// Player 1 wins
				win(backend, console, backend.getPlayer1());
				break;
			}
			//Check for draw offers
			if (backend.getPlayer2().checkDrawOffer()) {
				win(backend, console, null);
				break;
			}
			// Take player 2's turn
			backend.getPlayer2().takeTurn();
			
			// If player 2 wants to resign
			if (backend.getPlayer2().resign()) {
				// Player 1 wins
				win(backend, console, backend.getPlayer1());
				break;
			}
		}

		console.getConsoleOutput().print("Would you like to play again (Y/N): ");
		if (console.getUserBoolean())
		{
			main(args);
		}
	}
	
	/**
	 * The winner of the game.
	 * @param backend The backend
	 * @param console The ConsoleIO
	 * @param winner The player who won
	 */
	public static void win(Backend backend, ConsoleIO console, Player winner) {
		backend.getBoard().showBoard();
		if (winner == null) {
			console.getConsoleOutput().println("Draw!");
		} else {
			console.getConsoleOutput().println(winner.getName() + " wins!");
		}
	}

	/**
	 * Creates a console board from user preferences
	 * 
	 * @param scanner The scanner to get user input with
	 * @return The created board
	 */
	public static Backend setUpConsoleBoard(ConsoleIO console)
	{
		// Initialize booleans with default values
		boolean useGrid = true;
		boolean useUTF8 = false;

		// Output rules on first run
		console.getConsoleOutput().println(Help.GAME_RULES);

		// Wait for user to read and press enter.
		console.waitForEnter();

		// Create board variable
		Board board;

		// Create pieces variable
		ConsolePieces pieces;

		if (useUTF8)
		{
			pieces = ConsolePieces.UTF8;
		} else
		{
			pieces = ConsolePieces.ASCII;
		}

		// Initialize board based on what user wants
		if (useGrid)
		{
			ConsoleGrid grid;

			if (useUTF8)
			{
				grid = ConsoleGrid.UTF8_HEAVY;
			} else
			{
				grid = ConsoleGrid.ASCII;
			}

			board = new ConsoleGridBoard(pieces, grid, console);
		} else
		{
			board = new ConsoleBracketsBoard(pieces, console);
		}

		console.getConsoleOutput().print("Enter white player's name: ");
		String player1Name = console.getInput().next();

		console.getConsoleOutput().print("Enter black player's name: ");
		String player2Name = console.getInput().next();

		// Create console help
		help = new ConsoleHelp(pieces, console);

		// Make game players
		Player player1 = new ConsolePlayer(Team.WHITE, board, console, help, null, player1Name);
		Player player2 = new ConsolePlayer(Team.BLACK, board, console, help, null, player2Name);

		// Initialize the player opponents.
		player1.setOpponent(player2);
		player2.setOpponent(player1);

		// Let Board know who the players are
		board.setPlayer1(player1);
		board.setPlayer2(player2);

		// Send board back to calling statement
		return new Backend(board, player1, player2);
	}
}
