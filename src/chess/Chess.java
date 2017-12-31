package chess;

import java.io.IOException;
import java.util.Scanner;

import chess.board.Board;
import chess.board.console.brackets.ConsoleBracketsBoard;
import chess.board.console.grid.ConsoleGrid;
import chess.board.console.grid.ConsoleGridBoard;
import chess.help.Help;
import chess.help.console.ConsoleHelp;
import chess.piece.console.ConsolePieces;

/**
 * The class that holds the client code fo chess
 */
public class Chess {

	/**
	 * The entrypoint for the program
	 * @param args The arguments for the program (unused)
	 */
	public static void main(String[] args) {
		// Scanner to get user input
		Scanner scanner = new Scanner(System.in);
		
		// Create board object that can be drawn anywhere
		Board board;
		
		// Ask the user whether they would like to use the console
		System.out.print("Would you like to use the console (y/y): ");
		
		// Set up a board that prints to console
		if(scanner.next().equalsIgnoreCase("y")) {
			System.out.println();
			board = setUpConsoleBoard(scanner);
		}
		else {
			System.out.println();
			board = setUpConsoleBoard(scanner);
		}
		
		// Close the scanner
		scanner.close();
		
		// Draw the board to target
		board.displayBoard();
	}
	
	/**
	 * Creates a console board from user preferences
	 * 
	 * @param scanner The scanner to get user input with
	 * @return The created board
	 */
	public static Board setUpConsoleBoard(Scanner scanner) {
		// Initialize booleans with default values
		boolean useGrid = false;
		boolean useUTF8 = false;
		boolean useASCIIPieces = true;
		
		// Ask whether to use grid or not
		System.out.print("Do you have a large area to display a chess board (y/n): ");
		useGrid = getUserBoolean(scanner);
		System.out.println();
		
		// Ask whether to use unicode characters
		System.out.print("Can you display special characters (y/n): ");
		useUTF8 = getUserBoolean(scanner);
		System.out.println();
		
		// Only ask about not using UTF8 game pieces when using UTF8 board
		if(useUTF8) {
			// Ask whether to use ASCII game pieces
			System.out.println("Game pieces don't display correctly when the output is not using the Monospace font");
			System.out.print("Would you still like to keep the same game pieces (y/n): ");
			useASCIIPieces = !getUserBoolean(scanner);
			System.out.println();
		}
		
		String enterPrompt = "Press enter to continue";
		
		// Output rules on first run
		System.out.println(Help.gamerules());
		// "Press-any-key"-type for when user is done reading
		System.out.println(enterPrompt);
		try
		{
			System.in.read();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Create board variable and pieces dingo!!!
		Board board;
		ConsolePieces pieces;
		
		// Initialize board based on what user wants
		if(useGrid) {
			ConsoleGrid grid;
			
			if(useUTF8 && !useASCIIPieces) {
				pieces = ConsolePieces.UTF8;
			}
			else {
				pieces = ConsolePieces.ASCII;
			}
			
			if(useUTF8) {
				grid = ConsoleGrid.UTF8_HEAVY;
			}
			else {
				grid = ConsoleGrid.ASCII;
			}
			
			board = new ConsoleGridBoard(pieces, grid);
		
		}
		else {
			
			if(useUTF8 && !useASCIIPieces) {
				pieces = ConsolePieces.UTF8;
			}
			else {
				pieces = ConsolePieces.ASCII;
			}
			
			board = new ConsoleBracketsBoard(pieces);
		}
		
		// Prompt the user for any help (will be used later in game loop)
		/*
		ConsoleHelp helper = new ConsoleHelp(pieces, scanner);
		helper.consoleHelpPrompt();
		*/
		
		// Send board back to calling statement
		return board;
	}
	
	/**
	 * Gets the user input as a boolean
	 * 
	 * @param scanner The scanner to get user input for
	 * @return Whether the user said yes or not
	 */
	public static boolean getUserBoolean(Scanner scanner) {
		// User said y or Y
		return scanner.next().equalsIgnoreCase("y");
	}
}
