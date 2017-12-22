package chess;

import java.util.Scanner;

import chess.board.Board;
import chess.board.console.brackets.ConsoleBracketsBoard;
import chess.board.console.grid.ConsoleGrid;
import chess.board.console.grid.ConsoleGridBoard;
import chess.piece.console.ConsolePieces;

public class Chess {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Board board;
		
		System.out.print("Would you like to use the console (y/y): ");
		if(scanner.next().equalsIgnoreCase("y")) {
			board = setUpConsoleBoard(scanner);
		}
		else {
			board = setUpConsoleBoard(scanner);
		}
		
		scanner.close();
		
		board.displayBoard();
	}
	
	public static Board setUpConsoleBoard(Scanner scanner) {
		boolean useGrid = false;
		boolean useUTF8 = false;
		boolean useASCIIPieces = true;
		
		System.out.print("Do you have a large area to display a chess board (y/n): ");
		useGrid = getUserBoolean(scanner);
		System.out.println();
		
		System.out.print("Can you display Unicode characters (y/n): ");
		useUTF8 = getUserBoolean(scanner);
		System.out.println();
		
		if(useUTF8) {
			System.out.println("Game pieces don't display correctly when the output is not using the Monospace font");
			System.out.print("Would you still like to keep the same game pieces (y/n): ");
			useASCIIPieces = !getUserBoolean(scanner);
			System.out.println();
		}
		
		Board board;
		if(useGrid) {
			ConsolePieces pieces;
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
			ConsolePieces pieces;
			
			if(useUTF8 && !useASCIIPieces) {
				pieces = ConsolePieces.UTF8;
			}
			else {
				pieces = ConsolePieces.ASCII;
			}
			
			board = new ConsoleBracketsBoard(pieces);
		}
		
		return board;
	}
	
	public static boolean getUserBoolean(Scanner scanner) {
		return scanner.next().equalsIgnoreCase("y");
	}
}
