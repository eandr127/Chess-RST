package chess.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.piece.Move;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.piece.pieces.King;

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
	
	private boolean checkSafe = true;
	
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
	 * Displays board with no highlight
	 */
	public void showBoard() {
		displayBoard(new Coordinates[0]);
	}
	
	/**
	 * Displays board with all pieces that can be moved selected highlighted
	 * 
	 * @param team The team to highlight pieces for
	 */
	public void showBoard(Team team) {
		List<Coordinates> coords = new ArrayList<>();
		for(Piece[] row : arrangement) {
			for(Piece piece : row) {
				if(piece != PieceType.EMPTY_PIECE  && piece.getTeam() == team && piece.getValidMoves().length != 0) {
					coords.add(convertToArray(piece.getCoords()));
				}
			}
		}
		
		displayBoard(coords.toArray(new Coordinates[0]));
	}
	
	
	/**
	 * Displays the board with all locations that a piece can be moved highlighted
	 * 
	 * @param piece The piece to highlight locations for
	 */
	public void showBoard(Piece piece) {
		Coordinates[] coords = piece.getValidMoves();
		
		for(int i = 0; i < coords.length; i++) {
			coords[i] = convertToArray(coords[i]);
		}
		
		displayBoard(coords);
	}
	
	/**
	 * Display the board
	 * 
	 * @param pieces All array coordinates to highlight
	 */
	protected abstract void displayBoard(Coordinates[] pieces);
	
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
			addMove(move);
			
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
	
	public King findKingForTeam(Team team) {
		for(Piece[] row : arrangement) {
			for(Piece piece : row) {
				if(piece instanceof King && piece.getTeam() == team) {
					return (King) piece;
				}
			}
		}
		
		return null;
	}
	
	public boolean kingInCheck(Team team) {
		return kingInCheck(team, this);
	}
	
	public boolean kingInCheck(Team team, Board board) {
		King king = board.findKingForTeam(team);
		
		for(Piece[] row : board.arrangement) {
			for(Piece piece : row) {
				if(piece.canMove(king.getCoords()) && piece.getTeam() != team) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean putsKingInCheck(Move move) {
		return kingInCheck(move.getPiece().getTeam(), showMove(move));
	}
	
	public boolean savesKingFromCheck(Move move) {
		if(kingInCheck(move.getPiece().getTeam())) {
			return !kingInCheck(move.getPiece().getTeam(), showMove(move));
		}
		else {
			return true;
		}
	}
	
	public boolean isCheckmate(Team team) {
		if(kingInCheck(team)) {
			for(Piece[] pieces : arrangement) {
				for(Piece piece : pieces) {
					if(piece.getTeam() == team) {
						for(Coordinates coords : piece.getValidMoves()) {
							Move move = new Move(piece, coords.getX() - piece.getCoords().getX(), coords.getX() - piece.getCoords().getX());
							if(savesKingFromCheck(move)) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	private Board showMove(Move move) {
		Board copy = copy();
		copy.movePiece(move.getPiece().getCoords(), move.getPiece().getCoords().add(move.getDeltaX(), move.getDeltaY()));
		return copy;
	}
	
	/**
	 * Replaces one piece with another with a different type
	 * 
	 * @param coords The piece to change
	 * @param newPieceType The new piece type
	 */
	public void replacePieceType(Coordinates coords, PieceType newPieceType) {
		// Get the team of the new piece
		Team team = getPiece(coords).getTeam();
		
		// Create the piece with the appropriate team
		Piece newPiece;
		if(team == Team.WHITE) {
			newPiece = newPieceType.white();
		}
		else {
			newPiece = newPieceType.black();
		}
		
		// Initialize the piece with its coordinates
		newPiece.lateInit(coords, this);
		
		// Convert coordinates to array coordinates to update board
		coords = convertToArray(coords);
		
		// Update board
		arrangement[coords.getX()][coords.getY()] = newPiece;
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
	
	protected abstract Board make(Piece[][] arrangement, List<Move> moves);
	
	public Board copy() {
		Map<Piece, Piece> pieceMapping = new HashMap<>();
		for(Piece[] row : arrangement) {
			for(Piece piece : row) {
				if(!pieceMapping.containsKey(piece)) {
					switch(piece.getTeam()) {
						case BLACK:
							pieceMapping.put(piece, piece.getPieceType().black());
							break;
						case WHITE:
						default:
							pieceMapping.put(piece, piece.getPieceType().white());
					}
					
				}
			}
		}
		
		Piece[][] newArrangement = new Piece[BOARD_SIZE][BOARD_SIZE];
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				newArrangement[i][j] = pieceMapping.get(arrangement[i][j]);
			}
		}
		
		List<Move> moves = new ArrayList<Move>(this.moves);
		for(Move move : this.moves) {
			moves.add(new Move(pieceMapping.get(move.getPiece()), move.getDeltaX(), move.getDeltaY()));
		}
		
		return make(newArrangement, moves);
	}
	
	public void addMove(Move move) {
		moves.add(move);
	}
	
	public void setCheckSafe(boolean checkSafe) {
		this.checkSafe = checkSafe;
	}
	
	public boolean isCheckSafe() {
		return checkSafe;
	}
}
