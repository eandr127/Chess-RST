package chess.board.console.grid;

import java.io.PrintStream;

import chess.board.console.ConsoleBoard;
import chess.piece.Piece;
import chess.piece.console.ConsolePieces;

/**
 * A console board that prints using a grid
 */
public class ConsoleGridBoard extends ConsoleBoard {

	/**
	 * The look of the grid to print
	 */
	private final ConsoleGrid grid;
	
	/**
	 * Creates a ConsoleGridBoard using System.out
	 * 
	 * @param pieces The look of the pieces
	 * @param grid The look of the grid
	 */
	public ConsoleGridBoard(ConsolePieces pieces, ConsoleGrid grid) {
		super(pieces);
		this.grid = grid;
	}
	
	/**
	 * Creates a ConsoleGridBoard using a specified console
	 * 
	 * @param pieces The look of the pieces
	 * @param grid The look of the grid
	 * @param console Where to print the board
	 */
	public ConsoleGridBoard(ConsolePieces pieces, ConsoleGrid grid, PrintStream console) {
		super(pieces, console);
		this.grid = grid;
	}
	
	/**
	 * Prints the board to the console
	 */
	@Override
	public void displayBoard() {
		// Get the arrangement of the board
		Piece[][] arrangement = getArrangement();
		
		// Start the top line as a divider
		String top = makeDividerLine();
		
		// Remove initial space, change crosses to top sides, and replace first and last characters with top corners
		top = (grid.topLeftCorner + top.substring(3, top.length() - 1) + grid.topRightCorner).replaceAll("\\"+grid.cross, ""+grid.topSide);
		
		// Print top with two spaces that were removed
		console.println("  " + top);
		
		// Print in between first and last lines of grid
		for(int i = 0; i < arrangement.length; i++) {
			// Print the board pieces
			printPiecesLine(arrangement[i], i + 1);
			
			// Print a divider except last iteration
			if(i != arrangement.length - 1) {
				console.println(makeDividerLine());
			}
		}
		
		// Start the bottom line as a divider
		String bottom = makeDividerLine();
		
		// Remove initial space, change crosses to bottom sides, and replace first and last characters with bottom corners
		bottom = (grid.bottomLeftCorner + bottom.substring(3, bottom.length() - 1) + grid.bottomRightCorner).replaceAll("\\"+grid.cross, ""+grid.bottomSide);
		
		// Print bottom with two spaces that were removed
		console.println("  " + bottom);
		
		// Print 2 spaces to be at start of grid and another to move past the corner
		console.print("   ");
		
		// Print each letter at bottom
		for(int i = 0; i < arrangement.length; i++) {
			// Print space to move cursor under
			console.print(" ");
			
			// Convert the number to the correct letter
			console.print((char)(((int)'A') + i));
			
			// Move cursor past vertical bar in gid
			for(int j = 0; j < renderer.getPieces().length + 1; j++) {
				System.out.print(" ");
			}
		}
	}
	
	/**
	 * Makes a divider line
	 * 
	 * @return The divider line
	 */
	private String makeDividerLine() {
		// Move the divider so it lines up with the letters at the bottom of the board and put first edge
		String line = "  " + grid.leftSide;
		
		// Print each part of the grid
		for(int i = 0; i < 8; i++) {
			// Add horizontal line for the grid space taking piece length into account
			for(int j = 0; j < 2 + renderer.getPieces().length; j++) {
				line += grid.horizontal;
			}
			
			// Add cross on all but last grid
			if(i != 7) {
				line += grid.cross;
			}
		}
		
		// Add right edge
		line += grid.rightSide;
		
		// Return the line
		return line;
	}
	
	/**
	 * Print line of pieces to grid
	 * 
	 * @param pieces The row of pieces
	 * @param row The row number starting at 1
	 */
	private void printPiecesLine(Piece[] pieces, int row) {
		// Print row number and first vertical
		console.print(row + " " + grid.vertical);
		
		// Print each grid space
		for(int i = 0; i < pieces.length; i++) {
			// Start with space
			console.print(" ");
			
			// Add piece
			renderer.render(pieces[i]);
			
			// Print a space and vertical line
			console.print(" " + grid.vertical);
		}
		
		// Move cursor to next line
		console.println();
	}
}
