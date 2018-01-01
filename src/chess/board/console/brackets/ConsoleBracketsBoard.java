package chess.board.console.brackets;

import chess.ConsoleIO;
import chess.board.Board;
import chess.board.console.ConsoleBoard;
import chess.piece.console.ConsolePieces;

/**
 * A console board that prints using brackets
 */
public class ConsoleBracketsBoard extends ConsoleBoard {
	
	/**
	 * Creates a ConsoleBracketsBoard with a specified console
	 * 
	 * @param pieces What the pieces look like
	 * @param console The console to print to
	 */
	public ConsoleBracketsBoard(ConsolePieces pieces, ConsoleIO console) {
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
			console.getConsoleOutput().print(Board.BOARD_SIZE - i + " ");
			
			// Print each grid cell
			for(int j = 0; j < getArrangement()[0].length; j++) {
				// Open cell
				console.getConsoleOutput().print("[");
				// Print piece
				this.renderer.render(getArrangement()[i][j]);
				// Close cell
				console.getConsoleOutput().print("]");
			}
			
			// Move cursor to next line
			console.getConsoleOutput().println();
		}
		
		// Move console cursor to under first game piece
		console.getConsoleOutput().print("  ");
		
		// Print each column letter
		for(int i = 0; i < getArrangement().length; i++) {
			// Add space
			console.getConsoleOutput().print(" ");
			
			// Print column letter
			console.getConsoleOutput().print((char)(((int)'A') + i));
			
			// Print enough spaces to move to cursor to under next piece
			for(int j = 0; j < renderer.getPieces().length; j++) {
				console.getConsoleOutput().print(" ");
			}
		}
	}
}
