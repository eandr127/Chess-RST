package chess.help.console;

import java.util.Scanner;

import chess.help.Help;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.piece.console.ConsolePieces;
import chess.Chess;

/**
 * Class to be used specifically for the console. Contains
 * functions that allow console users to select docs /
 * info on different piece types and moves.
 * 
 * @author Caelan
 *
 */
public class ConsoleHelp extends Help {
	
	// Make variable for all console pieces
	private final ConsolePieces pieces;
	// Create an input scanner
	private final Scanner input;
	
	// Create constructor for pieces and input
	public ConsoleHelp (ConsolePieces pieces, Scanner input) {
		this.pieces = pieces;
		this.input = input;
	}
	
	 // Handles which doc to go to for the console implementation.
	public void consoleHelpPrompt () {
		
		System.out.println("What do you need help with?");
		System.out.println("1: Bishop (" + pieces.get(PieceType.BISHOP, Team.WHITE) + ")");
		System.out.println("2: King (" + pieces.get(PieceType.KING, Team.WHITE) + ")");
		System.out.println("3: Knight (" + pieces.get(PieceType.KNIGHT, Team.WHITE) + ")");
		System.out.println("4: Pawn (" + pieces.get(PieceType.PAWN, Team.WHITE) + ")");
		System.out.println("5: Rook (" + pieces.get(PieceType.ROOK, Team.WHITE) + ")");
		System.out.println("6: Queen (" + pieces.get(PieceType.QUEEN, Team.WHITE) + ")");
		System.out.println("=-=-=-=-=-=-=-=-=-=");
		System.out.println("7: General rules");
		System.out.println("Type CANCEL to cancel help.");
		
		while(true) {
			try {
				String choice = input.next();
				
				// If the user wants to exit help
				if (choice.equalsIgnoreCase("cancel")) {
					break;
				} else {
					// Determine which piece user wants to learn about
					switch(choice) {
						case "1":
							//Output the helpmap of BISHOP
							System.out.println(Help.helpMap.get(PieceType.BISHOP));
							//Wait for user to press enter
							Chess.waitForEnter();
							break;
						case "2":
							//Output the helpmap of KING
							System.out.println(Help.helpMap.get(PieceType.KING));
							//Wait for user to press enter
							Chess.waitForEnter();
							break;
						case "3":
							//Output the helpmap of KNIGHT
							System.out.println(Help.helpMap.get(PieceType.KNIGHT));
							//Wait for user to press enter
							Chess.waitForEnter();
							break;
						case "4":
							//Output the helpmap of PAWN
							System.out.println(Help.helpMap.get(PieceType.PAWN));
							//Wait for user to press enter
							Chess.waitForEnter();
							break;
						case "5":
							//Output the helpmap of ROOK
							System.out.println(Help.helpMap.get(PieceType.ROOK));
							//Wait for user to press enter
							Chess.waitForEnter();
							break;
						case "6":
							//Output the helpmap of QUEEN
							System.out.println(Help.helpMap.get(PieceType.QUEEN));
							//Wait for user to press enter
							Chess.waitForEnter();
							break;
						case "7":
							//Call method for game rules
							System.out.println(gamerules());
							//Wait for user to press enter
							Chess.waitForEnter();
							break;
						default:
							//Throws exception - exceptions handle invalid input.
							throw new Exception();
					}
				}
				break;
			} catch (Exception e) {
				System.out.println("That is not a valid choice. Try again!");
			}
		}
		
	}

}
