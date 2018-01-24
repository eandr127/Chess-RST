package chess.help.console;

import chess.ConsoleIO;
import chess.help.Help;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.piece.console.ConsolePieces;

/**
 * Class to be used specifically for the console. Contains functions that allow console users to select docs / info on
 * different piece types and moves.
 * 
 * @author Caelan
 *
 */
public class ConsoleHelp extends Help
{

	/**
	 * The command to type when the user wants help with help
	 */
	private static final String HELP_COMMAND = "HELP";
	
	/**
	 * The command to type when the user wants to exit
	 */
	private static final String EXIT_COMMAND = "EXIT";
	
	/**
	 * The different looks of console pieces
	 */
	private final ConsolePieces pieces;

	/**
	 * Scanner for user input
	 */
	private final ConsoleIO console;

	/**
	 * Create constructor for pieces and input
	 * 
	 * @param pieces The different looks of console pieces
	 * @param console The console to interact with the user with
	 */
	public ConsoleHelp(ConsolePieces pieces, ConsoleIO console)
	{
		this.pieces = pieces;
		this.console = console;
	}

	/**
	 * Handles which doc to go to for the console implementation.
	 */
	@Override
	public void helpPrompt()
	{
		// Get all the different PieceTypes that have help for them
		PieceType[] helpTypes = HELP_MAP.keySet().toArray(new PieceType[0]);

		// Display how to get info for each piece
		displayHelpMessage(helpTypes);
		
		// Don't stop until user types exit
		while (true)
		{
			// Display prompt for user to enter input
			displayUserPrompt();
			
			try
			{
				// Get the next line from the input
				String choice = console.getInput().next();

				// If the user wants to exit help
				if (choice.equalsIgnoreCase(EXIT_COMMAND))
				{
					// Stop the loop
					break;
				}
				// User wants to see the help options again
				else if(choice.equalsIgnoreCase(HELP_COMMAND))
				{
					// Display messages again
					displayHelpMessage(helpTypes);
				}
				else
				{
					// Make the number the user entered 0 indexed
					int num = Integer.parseInt(choice) - 1;
					
					// Number is bigger than the last option
					if(num > helpTypes.length) {
						// Throw exception to go to invalid input line
						throw new Exception();
					}
					// User wants help with a piece type
					else if(num < helpTypes.length) {
						// Find the piece type they want
						PieceType pieceType = helpTypes[num];
						
						// Output the helpmap of the specified piece type
						console.getConsoleOutput().println(Help.HELP_MAP.get(pieceType));
						
						// Wait for user to press enter
						console.waitForEnter();
					}
					else {
						// Call method for game rules
						console.getConsoleOutput().println(Help.GAME_RULES);
					}
				}
			} catch (Exception e)
			{
				console.getConsoleOutput().println("That is not a valid choice. Try again!");
			}
		}

	}

	/**
	 * Displays each numbered help option
	 * 
	 * @param helpTypes The different pieces that have help options
	 */
	private void displayHelpMessage(PieceType[] helpTypes) {
		// Loop through each piece type that has help
		for(int i = 0; i < helpTypes.length; i++) {
			// Get the piece type at the index of the loop
			PieceType pieceType = helpTypes[i];
			
			// Print the help number (1 indexed), and the white/black pieces for the piece type
			console.getConsoleOutput().println((i + 1) + ": " + pieceType + " (White: " + pieces.get(pieceType, Team.WHITE)
				+ " / Black: " + pieces.get(pieceType, Team.BLACK) + ")");
		}
		
		// Print separator line and then general rules
		console.getConsoleOutput().println("=-=-=-=-=-=-=-=-=-=");
		console.getConsoleOutput().println((helpTypes.length + 1) + ": General rules");
	}
	
	/**
	 * Displays the prompt for user input
	 */
	private void displayUserPrompt() {
		console.getConsoleOutput().println("Type " + HELP_COMMAND + " for help on choosing an option.");
		console.getConsoleOutput().println("Type " + EXIT_COMMAND + " to exit help.");
	}
}
