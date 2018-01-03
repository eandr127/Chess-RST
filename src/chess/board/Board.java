package chess.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.piece.Move;
import chess.piece.Piece;
import chess.piece.PieceType;

/**
 * Represents a board that can display somewhere
 */
public abstract class Board {

	/**
	 * The side length of the board
	 */
	public static final int BOARD_SIZE = 8;
	
	/**
	 * What the starting arrangement of chess looks like
	 */
	private static final Piece[][] startingArrangement = new Piece[][] {
		new Piece[] {	PieceType.ROOK.black(),	PieceType.KNIGHT.black(),	PieceType.BISHOP.black(),	PieceType.QUEEN.black(),	PieceType.KING.black(),	PieceType.BISHOP.black(),	PieceType.KNIGHT.black(),	PieceType.ROOK.black()	},
		new Piece[] {	PieceType.PAWN.black(),	PieceType.PAWN.black(),		PieceType.PAWN.black(),		PieceType.PAWN.black(),		PieceType.PAWN.black(),	PieceType.PAWN.black(),		PieceType.PAWN.black(),		PieceType.PAWN.black()	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.PAWN.white(),	PieceType.PAWN.white(),		PieceType.PAWN.white(),		PieceType.PAWN.white(),		PieceType.PAWN.white(),	PieceType.PAWN.white(),		PieceType.PAWN.white(),		PieceType.PAWN.white()	},
		new Piece[] {	PieceType.ROOK.white(),	PieceType.KNIGHT.white(),	PieceType.BISHOP.white(),	PieceType.QUEEN.white(),	PieceType.KING.white(),	PieceType.BISHOP.white(),	PieceType.KNIGHT.white(),	PieceType.ROOK.white()	}
	};
	
	/**
	 * 8x8 grid of pieces representing the chess board
	 */
	private final Piece[][] arrangement = new Piece[BOARD_SIZE][BOARD_SIZE];
	
	/**
	 * The moves that take place on this board
	 */
	private final List<Move> moves;
	
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
		fillBoard(arrangement);
		this.moves = new ArrayList<>();
	}
	
	/**
	 * Fills the board with fully initialized pieces
	 * 
	 * @param pieces The arrangement to initialize the pieces with
	 */
	private void fillBoard(Piece[][] arrangement) {
		// Loop through each row
		for(int i = 0; i < BOARD_SIZE; i++) {
			// Loop through each column
			for(int j = 0; j < BOARD_SIZE; j++) {
				// Initialize piece
				arrangement[i][j].lateInit(convertFromArray(new Coordinates(i, j)), this);
				
				// Put it in the new array
				this.arrangement[i][j] = arrangement[i][j];
			}
		}
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
	
	/**
	 * Converts standard piece coordinates to ones that are properly interpreted by the array
	 * 
	 * @param coords The coordinates to convert
	 * @return The converted coordinates
	 */
	private Coordinates convertToArray(Coordinates coords) {
		return new Coordinates(8 - coords.getY(), coords.getX() - 1);
	}
	
	/**
	 * Convert array coordinates to piece coordinates
	 * 
	 * @param coords The coordinates to convert
	 * @return The converted coordinates
	 */
	private Coordinates convertFromArray(Coordinates coords) {
		return new Coordinates(coords.getY() + 1, 8 - coords.getX());
	}
	
	/**
	 * Gets a piece on the board
	 * 
	 * @param coords The location of the piece
	 * @return The piece
	 */
	public Piece getPiece(Coordinates coords) {
		coords = convertToArray(coords);
	    return getArrangement()[coords.getX()][coords.getY()];
	}
	
	/**
	 * Gets the location of a piece on the board
	 * 
	 * @param piece The piece to locate
	 * @return The location of the piece
	 */
	public Coordinates getLocation(Piece piece) {
		// Loop through each row
		for(int i = 0; i < BOARD_SIZE; i++) {
			// Loop through each column
			for(int j = 0; j < BOARD_SIZE; j++) {
				// See if the piece is a match
				if(getArrangement()[i][j] == piece) {
					// Return the coordinates of the matching piece
					return convertFromArray(new Coordinates(i, j));
				}
			}
		}
		
		// Piece was not found
		return null;
	}
	
	/**
	 * Moves a piece from one place to another
	 * 
	 * @param start The location of the piece to move
	 * @param end The destination of the piece
	 * @return Whether the piece was successfully moved or not
	 */
	public boolean movePiece(Coordinates start, Coordinates end) {
		// Get the piece at the coordinates
		Piece piece = getPiece(start);
		
		// Create the move to be performed
		Move move = new Move(piece, end.getX() - start.getX(), end.getY() - start.getY());
		
		// Try to move the piece
		if(move.execute()) {
			// Convert piece coordinates to array coordinates
			start = convertToArray(start);
			end = convertToArray(end);
			
			// Movement was successful so update the board
			getArrangement()[start.getX()][start.getY()] = PieceType.EMPTY_PIECE;
			getArrangement()[end.getX()][end.getY()] = piece;
			
			// Add piece to move history
			moves.add(move);
			
			// Return successful
			return true;
		}
		// Piece could not be moved
		else {
			// Return failure
			return false;
		}
	}
	
	/**
	 * Captures a piece
	 * 
	 * @param coords The location to capture
	 */
	public void capture(Coordinates coords) {
		// Convert piece coordinates to array coordinates
		coords = convertToArray(coords);
		
		// Make captured piece empty
		getArrangement()[coords.getX()][coords.getY()] = PieceType.EMPTY_PIECE;
	}
	
	/**
	 * Gets the all previous moves on the board
	 * 
	 * @return The list of moves
	 */
	public List<Move> getMoves() {
		return Collections.unmodifiableList(moves);
	}
	
	/**
	 * Gets the moves for a certain piece
	 * 
	 * @param piece The piece to get moves for
	 * @return The list of moves for a specific piece
	 */
	public List<Move> getMovesForPiece(Piece piece) {
		// Create a list of moves to be returned
		List<Move> pieceMoves = new ArrayList<>();
		
		// Loop through each move
		for(Move move : moves) {
			// Check if the piece made the move
			if(move.getPiece() == piece) {
				// Add the move to the list of moves made by the piece
				pieceMoves.add(move);
			}
		}
		
		// Return the list
		return pieceMoves;
	}
}
