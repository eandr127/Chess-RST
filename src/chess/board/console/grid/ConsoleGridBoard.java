package chess.board.console.grid;

import java.io.PrintStream;

import chess.board.console.ConsoleBoard;
import chess.piece.Piece;
import chess.piece.console.ConsolePieces;

public class ConsoleGridBoard extends ConsoleBoard {

	private final ConsoleGrid grid;
	
	public ConsoleGridBoard(ConsolePieces pieces, ConsoleGrid grid) {
		super(pieces);
		this.grid = grid;
	}
	
	public ConsoleGridBoard(ConsolePieces pieces, ConsoleGrid grid, PrintStream console) {
		super(pieces, console);
		this.grid = grid;
	}
	
	@Override
	public void displayBoard() {
		Piece[][] arrangement = getArrangement();
		
		String top = makeDividerLine();
		top = (grid.topLeftCorner + top.substring(3, top.length() - 1) + grid.topRightCorner).replaceAll("\\"+grid.cross, ""+grid.topSide);
		console.println("  " + top);
		
		for(int i = 0; i < arrangement.length; i++) {
			printPiecesLine(arrangement[i], i + 1);
			
			if(i != arrangement.length - 1) {
				console.println(makeDividerLine());
			}
		}
		
		String bottom = makeDividerLine();
		bottom = (grid.bottomLeftCorner + bottom.substring(3, bottom.length() - 1) + grid.bottomRightCorner).replaceAll("\\"+grid.cross, ""+grid.bottomSide);
		console.println("  " + bottom);
		
		console.print("   ");
		for(int i = 0; i < arrangement.length; i++) {
			console.print(" ");
			console.print((char)(((int)'A') + i));
			
			for(int j = 0; j < renderer.getPieces().length + 1; j++) {
				System.out.print(" ");
			}
		}
	}
	
	private String makeDividerLine() {
		String line = "  " + grid.leftSide;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 2 + renderer.getPieces().length; j++) {
				line += grid.horizontal;
			}
			if(i != 7) {
				line += grid.cross;
			}
		}
		line += grid.rightSide;
		
		return line;
	}
	
	private void printPiecesLine(Piece[] pieces, int row) {
		console.print(row + " " + grid.vertical);
		
		for(int i = 0; i < 8; i++) {
			console.print(" ");
			renderer.render(pieces[i]);
			console.print(" " + grid.vertical);
		}
		
		console.println();
	}
}
