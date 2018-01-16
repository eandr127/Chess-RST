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
	 * 
	 * @param string: The string to be checked.
	 * @param length: The length to be checked.
	 * @return Did the string match length?
	 */
	public String stringToLength (String error, int length) {
		String word = null;
		while (word == null) {
			word = input.next();
			if (word.length() != length) {
				word = null;
				consoleOutput.print(error);
			}
		}
		return word;
	}
	
	/**
	 * Gets the user input as an int with set limits
	 * 
	 * @param error: The error message to display when user input out of bounds
	 * @param min: The minimum bounds
	 * @param max: The maximum bounds
	 */
	public int getUserInteger (String error, int min, int max) {
		Integer number = null;
		while (number == null) {
			number = input.nextInt();
			//TODO: hacky !!! yikes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if (number < min || number > max) {
				number = null;
				consoleOutput.print(error);
			}
		}
		return number;
	}
	
	/**
	 * Gets the user input as a boolean
	 * 
	 * @return Whether the user said yes or not
	 */
	public boolean getUserBoolean() {
		// User said y or Y
		return input.next().equalsIgnoreCase("y");
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
	
}
