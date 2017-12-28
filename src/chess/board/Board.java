package chess.board;

import chess.piece.Piece;
import chess.piece.PieceType;

/**
 * Represents a board that can display somewhere
 */
public abstract class Board {

	/**
	 * What the starting arrangement of chess looks like
	 */
	private static final Piece[][] startingArrangement = new Piece[][] {
		new Piece[] {	PieceType.ROOK.black,	PieceType.KNIGHT.black,	PieceType.BISHOP.black,	PieceType.QUEEN.black,	PieceType.KING.black,	PieceType.BISHOP.black,	PieceType.KNIGHT.black,	PieceType.ROOK.black	},
		new Piece[] {	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black,	PieceType.PAWN.black	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white,	PieceType.PAWN.white	},
		new Piece[] {	PieceType.ROOK.white,	PieceType.KNIGHT.white,	PieceType.BISHOP.white,	PieceType.QUEEN.white,	PieceType.KING.white,	PieceType.BISHOP.white,	PieceType.KNIGHT.white,	PieceType.ROOK.white	}
	};
	
	/**
	 * 8x8 grid of pieces representing the chess board
	 */
	private Piece[][] arrangement = new Piece[8][8];
	
	/**
	 * Creates a board with the starting arrangement
	 */
	public Board() {
		this(startingArrangement);
	}
	
	/**
	 * Creates a board with another arrangement
	 * 
	 * @param arrangement The custom arrangement
	 */
	public Board(Piece[][] arrangement) {
		this.arrangement = arrangement;
	}
	
	/**
	 * Gets the arrangement of the board
	 * 
	 * @return The board arrangement
	 */
	protected Piece[][] getArrangement() {
		return arrangement;
	}
	
	/**
	 * Display the board
	 */
	public abstract void displayBoard();
}
