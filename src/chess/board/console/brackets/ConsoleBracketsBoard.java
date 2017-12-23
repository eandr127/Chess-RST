package chess.board.console.brackets;

import java.io.PrintStream;

import chess.board.console.ConsoleBoard;
import chess.piece.console.ConsolePieces;

/**
 * A console board that prints using brackets
 */
public class ConsoleBracketsBoard extends ConsoleBoard {

	/**
	 * Creates a ConsoleBracketsBoard with System.out
	 * 
	 * @param pieces What the pieces look like
	 */
	public ConsoleBracketsBoard(ConsolePieces pieces) {
		super(pieces);
	}
	
	/**
	 * Creates a ConsoleBracketsBoard with a specified console
	 * 
	 * @param pieces What the pieces look like
	 * @param console The console to print to
	 */
	public ConsoleBracketsBoard(ConsolePieces pieces, PrintStream console) {
		super(pieces, console);
	}

	/**
	 * Prints the board to the console
	 */
	@Override
	public void displayBoard() {
		// Print each row
		for(int i = 0; i < getArrangement().length; i++) {
			// Print row number
			console.print(i + 1 + " ");
			
			// Print each grid cell
			for(int j = 0; j < getArrangement()[0].length; j++) {
				// Open cell
				console.print("[");
				// Print piece
				this.renderer.render(getArrangement()[i][j]);
				// Close cell
				console.print("]");
			}
			
			// Move cursor to next line
			console.println();
		}
		
		// Move console cursor to under first game piece
		console.print("  ");
		
		// Print each column letter
		for(int i = 0; i < getArrangement().length; i++) {
			// Add space
			console.print(" ");
			
			// Print column letter
			console.print((char)(((int)'A') + i));
			
			// Print enough spaces to move to cursor to under next piece
			for(int j = 0; j < renderer.getPieces().length; j++) {
				console.print(" ");
			}
		}
	}
}
