package chess;

import chess.board.Board;
import chess.board.console.ConsoleBoard;
import chess.board.console.ConsoleGrid;
import chess.piece.console.UTF8PieceRenderer;

public class Chess {

	public static void main(String[] args) {
		Board board = new ConsoleBoard(new UTF8PieceRenderer(), ConsoleGrid.UTF8_HEAVY);
		board.displayBoard();
	}
}
