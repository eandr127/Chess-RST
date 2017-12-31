package chess.help.console;

import java.util.Scanner;

import chess.help.Help;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.piece.console.ConsolePieces;


/**
 * Class to be used specifically for the console. Contains
 * functions that allow console users to select docs /
 * info on different piece types and moves.
 * 
 * @author Caelan
 *
 */
public class ConsoleHelp extends Help {
	
	private final ConsolePieces pieces;
	private final Scanner input;
	
	public ConsoleHelp (ConsolePieces pieces, Scanner input) {
	
		this.pieces = pieces;
		this.input = input;
		
	}
	
	/*
	 * Handles which doc to go to for the console
	 * implementation.
	 */
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
		System.out.println("Type EXIT to exit help.");
		
		while(true) {
			try {
				String choice = input.next();
				String enterPrompt = "Press enter to continue";
				
				// If the user wants to exit or EXIT
				if (choice.equalsIgnoreCase("exit")) {
					break;
				} else {
					
					// Determine which piece user wants to learn about
					switch(choice) {
					case "1":
						System.out.println(Help.helpMap.get(PieceType.BISHOP));
						System.out.println(enterPrompt);
						System.in.read();
						break;
					case "2":
						System.out.println(Help.helpMap.get(PieceType.KING));
						System.out.println(enterPrompt);
						System.in.read();
						break;
					case "3":
						System.out.println(Help.helpMap.get(PieceType.KNIGHT));
						System.out.println(enterPrompt);
						System.in.read();
						break;
					case "4":
						System.out.println(Help.helpMap.get(PieceType.PAWN));
						System.out.println(enterPrompt);
						System.in.read();
						break;
					case "5":
						System.out.println(Help.helpMap.get(PieceType.ROOK));
						System.out.println(enterPrompt);
						System.in.read();
						break;
					case "6":
						System.out.println(Help.helpMap.get(PieceType.QUEEN));
						System.out.println(enterPrompt);
						System.in.read();
						break;
					case "7":
						System.out.println(gamerules());
						System.out.println(enterPrompt);
						System.in.read();
						break;
					default:
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
