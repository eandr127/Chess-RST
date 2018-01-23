package chess;

import chess.board.Board;
import chess.board.console.brackets.ConsoleBracketsBoard;
import chess.board.console.grid.ConsoleGrid;
import chess.board.console.grid.ConsoleGridBoard;
import chess.help.Help;
import chess.help.console.ConsoleHelp;
import chess.piece.Team;
import chess.piece.console.ConsolePieces;
import chess.player.console.ConsolePlayer;

/**
 * The class that holds the client code fo chess
 */
public class Chess {

	/**
	 * The entrypoint for the program
	 * @param args The arguments for the program (unused)
	 */
	public static void main(String[] args) {
		ConsoleIO console = new ConsoleIO(System.out, System.in);
		
		// Create board object that can be drawn anywhere
		Backend backend;
		
		// Ask the user whether they would like to use the console
		console.getConsoleOutput().print("Would you like to use the console (y/y): ");
		
		// Set up a board that prints to console
		if(console.getUserBoolean()) {
			console.getConsoleOutput().println();
			backend = setUpConsoleBoard(console);
		}
		else {
			console.getConsoleOutput().println();
			backend = setUpConsoleBoard(console);
		}
		
		// Enter the user into the help prompt
		backend.getHelp().helpPrompt();
		
		boolean checkmate = false;
		while(!checkmate) {
			//TODO: caelan - finish this bit
			if(backend.getBoard().isCheckmate(backend.getPlayer1().getTeam())) {
				backend.getBoard().showBoard();
				System.out.println("Player 2 wins");
				return;
			}
			backend.getPlayer1().takeTurn();
			
			if(backend.getBoard().isCheckmate(backend.getPlayer1().getTeam())) {
				System.out.println("Player 1 wins");
				backend.getBoard().showBoard();
				return;
			}
			backend.getPlayer2().takeTurn();
		}
	}
	
	/**
	 * Creates a console board from user preferences
	 * 
	 * @param scanner The scanner to get user input with
	 * @return The created board
	 */
	public static Backend setUpConsoleBoard(ConsoleIO console) {
		// Initialize booleans with default values
		boolean useGrid = false;
		boolean useUTF8 = false;
		
		// Ask whether to use grid or not
		console.getConsoleOutput().print("Do you have a large area to display a chess board (y/n): ");
		useGrid = console.getUserBoolean();
		console.getConsoleOutput().println();
		
		// Ask whether to use unicode characters
		console.getConsoleOutput().print("Can you display special characters that may not work on Windows and older versions of Eclipse (y/n): ");
		useUTF8 = console.getUserBoolean();
		console.getConsoleOutput().println();
		
		// Output rules on first run
		console.getConsoleOutput().println(Help.GAME_RULES);
		
		// Wait for user to read and press enter.
		console.waitForEnter();
		
		// Create board variable
		Board board;
		
		//Create pieces variable
		ConsolePieces pieces;
		
		if(useUTF8) {
			pieces = ConsolePieces.UTF8;
		}
		else {
			pieces = ConsolePieces.ASCII;
		}
		
		// Initialize board based on what user wants
		if(useGrid) {
			ConsoleGrid grid;
			
			if(useUTF8) {
				grid = ConsoleGrid.UTF8_HEAVY;
			}
			else {
				grid = ConsoleGrid.ASCII;
			}
			
			board = new ConsoleGridBoard(pieces, grid, console);
		}
		else {
			board = new ConsoleBracketsBoard(pieces, console);
		}
		
		// Send board back to calling statement
		return new Backend(board, new ConsolePlayer(Team.WHITE, board, console), new ConsolePlayer(Team.BLACK, board, console), new ConsoleHelp(pieces, console));
	}
	

}
