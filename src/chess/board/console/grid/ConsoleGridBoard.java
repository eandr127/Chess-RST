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
		top = (grid.topLeftCorner + top.substring(1, top.length() - 1) + grid.topRightCorner).replaceAll("\\"+grid.cross, ""+grid.topSide);;
		console.println(top);
		
		for(int i = 0; i < arrangement.length; i++) {
			printPiecesLine(arrangement[i]);
			
			if(i != arrangement.length - 1) {
				console.println(makeDividerLine());
			}
		}
		
		String bottom = makeDividerLine();
		bottom = (grid.bottomLeftCorner + bottom.substring(1, bottom.length() - 1) + grid.bottomRightCorner).replaceAll("\\"+grid.cross, ""+grid.bottomSide);
		console.println(bottom);
		
		
	}
	
	private String makeDividerLine() {
		String line = "" + grid.leftSide;
		
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
	
	private void printPiecesLine(Piece[] pieces) {
		console.print(grid.vertical);
		
		for(int i = 0; i < 8; i++) {
			console.print(" ");
			renderer.render(pieces[i]);
			console.print(" " + grid.vertical);
		}
		
		console.println();
	}
}
