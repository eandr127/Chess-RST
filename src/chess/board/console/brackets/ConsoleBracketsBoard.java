package chess.board.console.brackets;

import java.io.PrintStream;

import chess.board.console.ConsoleBoard;
import chess.piece.console.ConsolePieces;

public class ConsoleBracketsBoard extends ConsoleBoard {

	public ConsoleBracketsBoard(ConsolePieces pieces) {
		super(pieces);
	}
	
	public ConsoleBracketsBoard(ConsolePieces pieces, PrintStream console) {
		super(pieces, console);
	}

	@Override
	public void displayBoard() {
		for(int i = 0; i < getArrangement().length; i++) {
			System.out.print(i + 1 + " ");
			
			for(int j = 0; j < getArrangement()[0].length; j++) {
				System.out.print("[");
				this.renderer.render(getArrangement()[i][j]);
				System.out.print("]");
			}
			
			System.out.println();
		}
		
		
		System.out.print("  ");
		for(int i = 0; i < getArrangement().length; i++) {
			System.out.print(" ");
			System.out.print((char)(((int)'A') + i));
			
			for(int j = 0; j < renderer.getPieces().length; j++) {
				System.out.print(" ");
			}
		}
	}
}
