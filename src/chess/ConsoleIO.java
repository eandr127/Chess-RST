package chess;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Holds information for using a console
 */
public class ConsoleIO
{
	/**
	 * The user input from the console
	 */
	private final Scanner input;

	/**
	 * The {@code PrintStream} to display information to
	 */
	private final PrintStream consoleOutput;
	
	/**
	 * The {@code InputStream} to get user input with
	 */
	private final InputStream consoleInput;
	
	/**
	 * The {@code String} to print when asking user to press enter
	 */
	private static final String ENTER_PROMPT = "Press enter to continue";
	
	/**
	 * Creates a new console 
	 * 
	 * @param consoleOutput The {@code PrintStream} to print to
	 * @param consoleInput The {@code InputStream} to read user input from
	 */
	public ConsoleIO(PrintStream consoleOutput, InputStream consoleInput) {
		this.consoleOutput = consoleOutput;
		this.consoleInput = consoleInput;
		this.input = new Scanner(consoleInput);
		
		// Close the scanner at the end of the program
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run()
			{
				input.close();
			}
		}));
	}
	
	/**
	 * Waits for the user to press enter in the console before returning
	 */
	public void waitForEnter () {
		// "Press-any-key"-type for when user is done reading
		consoleOutput.println(ENTER_PROMPT);
		
		// Wait to receive character
		try
		{
			consoleInput.read();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets user input against certain requirements, defined by the requirements argument.
	 * 
	 * @param requirements The Requirements for the user input
	 * @return The user's input.
	 */
	public String getStringFromUser(Requirements requirements) {
		// Create string to hold user input
		String userInput;
		do {
			// Print the prompt to the user
			consoleOutput.print(requirements.message());
			
			// Get the user's input
			userInput = input.next();
			
			// Check if the input is valid
			if (!requirements.valid(userInput)) {
				// Print out invalid message
				consoleOutput.print(requirements.invalid());
			}
		}
		// Continue looping until valid input is found
		while(!requirements.valid(userInput));
		
		// Return valid input
		return userInput;
	}
	
	/**
	 * Gets the user input as a boolean
	 * 
	 * @return Whether the user said yes or not
	 */
	public boolean getUserBoolean() {
		//Checks if user input is valid.
		boolean valid = false;
		//Checks if user said yes or not.
		boolean yes = false;
		
		do {
			String userInput;
			userInput = input.nextLine();
			
			if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
				valid = true;
				yes = true;
			} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
				valid = true;
				yes = false;
			} else {
				valid = false;
				System.out.println("Invalid input. Please type Y or N.");
			}
		} while (!valid);
		return yes;
	}
	
	/**
	 * Gets the {@code PrintStream} used to print
	 * 
	 * @return The {@code PrintStream}
	 */
	public PrintStream getConsoleOutput()
	{
		return consoleOutput;
	}

	/**
	 * Gets the {@code Scanner} used to get user input
	 * 
	 * @return The {@code Scanner}
	 */
	public Scanner getInput()
	{
		return input;
	}
	
	/**
	 * Used to ensure user input is valid
	 */
	public static interface Requirements {
		
		/**
		 * Checks whether a user input is valid
		 * 
		 * @param in The user input
		 * @return Whether the user input is valid
		 * @throws IllegalArgumentException (Unused)
		 * @deprecated Not actually, just needs to be reworked to use the IllegalArgumentException
		 */
		@Deprecated
		public boolean valid(String in) throws IllegalArgumentException;
		
		/**
		 * The message to print when input is invalid
		 * 
		 * @return The message to print
		 */
		public String invalid();
		
		/**
		 * The initial message to print
		 * 
		 * @return The message to print
		 */
		public String message();
	}
	
}
