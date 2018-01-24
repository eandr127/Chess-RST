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
	 * pre: consoleOutput The {@code PrintStream} to print to
	 * pre: consoleInput The {@code InputStream} to read user input from
	 */
	public ConsoleIO(PrintStream consoleOutput, InputStream consoleInput)
	{
		this.consoleOutput = consoleOutput;
		this.consoleInput = consoleInput;
		this.input = new Scanner(consoleInput);

		// Close the scanner at the end of the program
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
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
	public void waitForEnter()
	{
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
	 * pre: string The string to be checked.
	 * pre: length The length to be checked.
	 * post: Did the string match length?
	 */
	public String stringToLength(String error, int length)
	{
		String word = null;
		// While the user's input is invalid anything
		while (word == null)
		{
			// Set word to user input
			word = input.next();
			// if the word length is a mismatch
			if (word.length() != length)
			{
				// Output error and retry
				word = null;
				consoleOutput.print(error);
			}
		}
		return word;
	}

	/**
	 * Gets user input against certain requirements, defined by the requirements argument.
	 * 
	 * pre: requirements The Requirements for the user input
	 * post: The user's input.
	 */
	public String getStringFromUser(Requirements requirements)
	{
		// Create string to hold user input
		String userInput;
		do
		{
			// Print the prompt to the user
			consoleOutput.print(requirements.message());

			// Get the user's input
			userInput = input.next();

			// Check if the input is valid
			if (!requirements.valid(userInput))
			{
				// Print out invalid message
				consoleOutput.print(requirements.invalid());
			}
		}
		// Continue looping until valid input is found
		while (!requirements.valid(userInput));

		// Return valid input
		return userInput;
	}

	/**
	 * Gets the user input as a boolean
	 * 
	 * post: Whether the user said yes or not
	 */
	public boolean getUserBoolean()
	{
		// Checks if user input is valid.
		boolean valid = false;
		// Checks if user said yes or not.
		boolean yes = false;

		// Until the user input is valid (y/n)...
		do
		{
			String userInput;
			// Get the user's input
			userInput = input.next();

			// If the user said yes, the input is valid and the response was "yes".
			if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes"))
			{
				valid = true;
				yes = true;
				// If the user said no, the response was valid and the response was "no".
			} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no"))
			{
				valid = true;
				yes = false;
				// Otherwise, the response is invalid and we ask the user to input again.
			} else
			{
				valid = false;
				System.out.println("Invalid input. Please type Y or N.");
			}
		} while (!valid);
		// Return whether the user said yes/no.
		return yes;
	}

	/**
	 * Gets the {@code PrintStream} used to print
	 * 
	 * post: The {@code PrintStream}
	 */
	public PrintStream getConsoleOutput()
	{
		return consoleOutput;
	}

	/**
	 * Gets the {@code Scanner} used to get user input
	 * 
	 * post: The {@code Scanner}
	 */
	public Scanner getInput()
	{
		return input;
	}

	/**
	 * Used to ensure user input is valid
	 */
	public static interface Requirements
	{

		/**
		 * Checks whether a user input is valid
		 * 
		 * pre: in The user input
		 * post: Whether the user input is valid
		 */
		public boolean valid(String in);

		/**
		 * The message to print when input is invalid
		 * 
		 * post: The message to print
		 */
		public String invalid();

		/**
		 * The initial message to print
		 * 
		 * post: The message to print
		 */
		public String message();
	}

}
