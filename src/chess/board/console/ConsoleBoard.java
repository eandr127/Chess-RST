package chess.board.console;

import chess.board.Board;
import chess.piece.Piece;
import chess.piece.console.ConsolePieceRenderer;

public class ConsoleBoard extends Board {

	private final ConsolePieceRenderer renderer;
	private final ConsoleGrid grid;
	
	public ConsoleBoard(ConsolePieceRenderer renderer, ConsoleGrid grid) {
		this.renderer = renderer;
		this.grid = grid;
	}
	
	@Override
	public void displayBoard() {
		Piece[][] arrangement = getArrangement();
		
		String top = makeDividerLine();
		top = (grid.topLeftCorner + top.substring(1, top.length() - 1) + grid.topRightCorner).replaceAll("\\"+grid.cross, ""+grid.topSide);;
		System.out.println(top);
		
		for(int i = 0; i < arrangement.length; i++) {
			printPiecesLine(arrangement[i]);
			
			if(i != arrangement.length - 1) {
				System.out.println(makeDividerLine());
			}
		}
		
		String bottom = makeDividerLine();
		bottom = (grid.bottomLeftCorner + bottom.substring(1, bottom.length() - 1) + grid.bottomRightCorner).replaceAll("\\"+grid.cross, ""+grid.bottomSide);
		System.out.println(bottom);
		
		
	}
	
	private String makeDividerLine() {
		String line = "" + grid.leftSide;
		
		for(int i = 0; i < 8; i++) {
			line += "" + grid.horizontal + grid.horizontal + grid.horizontal;
			if(i != 7) {
				line += grid.cross;
			}
		}
		line += grid.rightSide;
		
		return line;
	}
	
	private void printPiecesLine(Piece[] pieces) {
		System.out.print(grid.vertical);
		
		for(int i = 0; i < 8; i++) {
			System.out.print(" ");
			renderer.render(pieces[i]);
			System.out.print(" " + grid.vertical);
		}
		
		System.out.println();
	}
}
