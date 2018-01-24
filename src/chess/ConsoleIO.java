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
	 * Reduces string to a certain length. Ex.: A13 shortened to a length of 2 would return A1.
	 * 
	 * @param string The string to be checked.
	 * @param length The length to be checked.
	 * @return Did the string match length?
	 */
	public String stringToLength (String error, int length) {
		String word = null;
		//While the user's input is invalid anything
		while (word == null) {
			//Set word to user input
			word = input.next();
			//if the word length is a mismatch
			if (word.length() != length) {
				//Output error and retry
				word = null;
				consoleOutput.print(error);
			}
		}
		return word;
	}
	
	/**
	 * Gets user input against certain requirements, defined by the requirements argument.
	 * 
	 * @param requirements The Requirements for the user input
	 * @return The user's input.
	 */
	public String getStringFromUser(Requirements requirements) {
		String userInput;
		do {
			consoleOutput.print(requirements.message());
			userInput = input.next();
			if (!requirements.valid(userInput)) {
				consoleOutput.print(requirements.invalid());
			}
		}
		while(!requirements.valid(userInput));
		
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
		
		//Until the user input is valid (y/n)...
		do {
			String userInput;
			//Get the user's input
			userInput = input.nextLine();
			
			//If the user said yes, the input is valid and the response was "yes".
			if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
				valid = true;
				yes = true;
			//If the user said no, the response was valid and the response was "no".
			} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
				valid = true;
				yes = false;
			//Otherwise, the response is invalid and we ask the user to input again.
			} else {
				valid = false;
				System.out.println("Invalid input. Please type Y or N.");
			}
		} while (!valid);
		//Return whether the user said yes/no.
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
	
	public static interface Requirements {
		
		public boolean valid(String in) throws IllegalArgumentException;
		public String invalid();
		public String message();
	}
	
}
