package chess.board.console.brackets;

import java.util.List;

import chess.ConsoleIO;
import chess.board.Board;
import chess.board.Coordinates;
import chess.board.console.ConsoleBoard;
import chess.piece.Move;
import chess.piece.Piece;
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
	 * Creates a ConsoleBracketsBoard with a specified console
	 * 
	 * @param pieces What the pieces look like
	 * @param console The console to print to
	 */
	public ConsoleBracketsBoard(Piece[][] arrangement, ConsolePieces pieces, ConsoleIO console) {
		super(arrangement, pieces, console);
	}

	/**
	 * Prints the board to the console
	 */
	@Override
	public void displayBoard(Coordinates[] coordinates) {
		// Print each row
		for(int i = 0; i < getArrangement().length; i++) {
			// Print row number
			console.getConsoleOutput().print(Board.BOARD_SIZE - i + " ");
			
			// Print each grid cell
			for(int j = 0; j < getArrangement()[0].length; j++) {
				boolean selected = false;
				for(Coordinates coords : coordinates) {
					if(coords.equals(new Coordinates(i, j))) {
						selected = true;
						break;
					}
				}
				
				// Open cell
				console.getConsoleOutput().print(selected ? "{" : "[");
				// Print piece
				this.renderer.render(getArrangement()[i][j]);
				// Close cell
				console.getConsoleOutput().print(selected ? "}" : "]");
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
		
		console.getConsoleOutput().println();
	}
	
	/**
	 * Creates a board with a set arrangement a list of moves made.
	 * @param arrangement The arrangement to use for this board.
	 * @param moves The list of moves made.
	 */
	@Override
	protected Board make(Piece[][] arrangement, List<Move> moves)
	{
		Board board = new ConsoleBracketsBoard(arrangement, renderer.getPieces(), console);
		for(Move move : moves) {
			board.addMove(move);
		}
		
		board.setCheckSafe(false);
		
		return board;
	}
}
