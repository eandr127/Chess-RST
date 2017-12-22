package chess;

import chess.board.Board;
import chess.board.console.grid.ConsoleGrid;
import chess.board.console.grid.ConsoleGridBoard;
import chess.piece.console.ConsolePieces;

public class Chess {

	public static void main(String[] args) {
		Board board = new ConsoleGridBoard(ConsolePieces.UTF8, ConsoleGrid.UTF8_HEAVY);
		board.displayBoard();
	}
}
