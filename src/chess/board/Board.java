package chess.board;

import chess.piece.Piece;
import chess.piece.PieceType;

public abstract class Board {

	private static final Piece[][] startingArrangement = new Piece[][] {
		new Piece[] {	PieceType.ROOK.black,	PieceType.KNIGHT.black,	PieceType.BISHOP.black,	PieceType.QUEEN.black,	PieceType.KNIGHT.black,	PieceType.BISHOP.black,	PieceType.KNIGHT.black,	PieceType.ROOK.black	},
		new Piece[] {	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black	},
		new Piece[] {	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	},
		new Piece[] {	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	},
		new Piece[] {	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	},
		new Piece[] {	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	PieceType.EMPTY.black,	},
		new Piece[] {	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white	},
		new Piece[] {	PieceType.ROOK.white,	PieceType.KNIGHT.white,	PieceType.BISHOP.white,	PieceType.QUEEN.white,	PieceType.KNIGHT.white,	PieceType.BISHOP.white,	PieceType.KNIGHT.white,	PieceType.ROOK.white	}
	};
	
	private Piece[][] arrangement = new Piece[8][8];
	
	public Board() {
		this(startingArrangement);
	}
	
	public Board(Piece[][] arrangement) {
		this.arrangement = arrangement;
	}
	
	protected Piece[][] getArrangement() {
		return arrangement;
	}
	
	public abstract void displayBoard();
}
