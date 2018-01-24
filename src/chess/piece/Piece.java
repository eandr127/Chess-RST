package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.board.Board;
import chess.board.Coordinates;
import chess.player.Player;

/**
 * Represents a black or white piece
 */
public abstract class Piece {
	
	/**
	 * The type of piece the piece is
	 */
	private final PieceType pieceType;
	
	/**
	 * The team of the piece
	 */
	private final Team team;
	
	/**
	 * The player that owns this piece
	 */
	private Player player;
	
	/**
	 * The board that the piece is a part of
	 */
	private Board board;

	/**
	 * The coordinates of the piece on the board
	 */
	private Coordinates coords;
	
	/**
	 * Whether the piece has been given a board and coordinates or not
	 */
	private boolean initialized = false;
	
	/**
	 * Creates a piece
	 * 
	 * @param pieceType The type of piece
	 * @param team The piece's team
	 */
	protected Piece(PieceType pieceType, Team team, Player player) {
		this.pieceType = pieceType;
		this.team = team;
		this.player = player;
	}
	
	/**
	 * Gets the piece type
	 * 
	 * @return The piece type
	 */
	public PieceType getPieceType() {
		return pieceType;
	}
	
	/**
	 * Gets the team of the piece
	 * 
	 * @return The piece team
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Gets the board the piece is on
	 * 
	 * @return The board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Gets the player owner of this piece.
	 * 
	 * @return The player owner of this piece
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Gets the coordinates of the piece
	 * 
	 * @return The coordinates
	 */
	public Coordinates getCoords()
	{
		return coords;
	}
	
	protected void setCoords(Coordinates coords)
	{
		this.coords = coords;
	}
	
	/**
	 * Checks whether the piece has a board and coordinates
	 * 
	 * @return Whether the piece is fully initialized or not
	 */
	public boolean isInitialized() {
		return initialized;
	}
	
	/**
	 * Initializes the piece with coordinates and a board
	 * 
	 * @param coords The location of the piece on the board
	 * @param board The board the piece is on
	 * @param player The player owner of this piece
	 */
	public void lateInit(Coordinates coords, Board board, Player player) {
		this.coords = coords;
		this.board = board;
		this.player = player;
		
		this.initialized = true;
	}
	
	/**
	 * Moves the piece to a new location
	 * 
	 * @param newCoords The new location of the piece
	 * @return Whether the move was successful
	 * @throws IllegalStateException When the piece has not been fully initialized
	 */
	public boolean move(Coordinates newCoords) throws IllegalStateException {
		if(!isInitialized()) {
			throw new IllegalStateException("Piece must be initialized before it can be moved");
		}
		
		if((!board.isCheckSafe() || checkSafe(newCoords)) && canMove(newCoords)) {
			doMove(newCoords);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks whether the piece has any valid moves
	 * 
	 * @return Whether the piece has a valid move
	 */
	public Coordinates[] getValidMoves() {
		List<Coordinates> coordsList = new ArrayList<>();
		
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				Coordinates coords = new Coordinates(i, j);
				
				if(checkSafe(coords) && canMove(coords)) {
					coordsList.add(coords);
				}
			}
		}
		
		return coordsList.toArray(new Coordinates[0]);
	}
	
	private boolean checkSafe(Coordinates coords) {
		Move move = new Move(this, coords.getX() - getCoords().getX(), coords.getY() - getCoords().getY());
		if(board.kingInCheck(getTeam())) {
			return board.savesKingFromCheck(move);
		}
		else {
			return !board.putsKingInCheck(move);
		}
	}
	
	/**
	 * Determines whether the piece can move or not
	 * 
	 * @param newCoords The location to test
	 * @return Whether the move is possible
	 */
	public abstract boolean canMove(Coordinates newCoords);
	
	/**
	 * Completes the move and captures pieces as necessary
	 * @param newCoords The coordinates to move to
	 */
	protected abstract void doMove(Coordinates newCoords);
}