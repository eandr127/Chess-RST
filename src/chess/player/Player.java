package chess.player;

import chess.board.Board;
import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.Team;

/**
 * A player that can take a turn in chess
 */
public abstract class Player
{
	/**
	 * The team of the player
	 */
	private final Team team;
	
	/**
	 * The board that the player is on
	 */
	private final Board board;
	
	/**
	 * Creates a new player
	 * 
	 * @param team The team of the player
	 * @param board The board that the player is on
	 */
	public Player(Team team, Board board) {
		this.team = team;
		this.board = board;
	}
	
	/**
	 * Gets the team of the player
	 * 
	 * @return The team
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Gets the board of the player
	 * 
	 * @return The board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Takes the turn on the chess board
	 */
	public void takeTurn() {
		// Start not done
		boolean done = false;
		
		// Take turn
		do {
			// Gets the location of the piece that the player wants to move
			Coordinates start = selectPiece();
			
			// Gets the location that the player want to move the piece to
			Coordinates end = selectDestination(board.getPiece(start));
			
			// Only valid pieces should be selected in the first place, but just in case
			done = board.movePiece(start, end);
			
			// Tell the user that their move was wrong
			if(!done) {
				invalidMove();
			}
		}
		// Continue to take turn until done
		while(!done);
	}
	
	/**
	 * Gets the location of the piece to be moved
	 * @return The location of the piece
	 */
	public abstract Coordinates selectPiece();
	
	/**
	 * Gets the location to move the piece to
	 * 
	 * @param selected The piece to be moved
	 * @return The new location
	 */
	public abstract Coordinates selectDestination(Piece selected);
	
	/**
	 * Tells the player that their move was invalid
	 */
	public abstract void invalidMove();
	
	/**
	 * Finds out whether the player wants to offer a draw
	 * 
	 * @return Whether to offer draw
	 */
	public abstract boolean offerDraw();
	
	/**
	 * Does something with the draw offer
	 */
	public abstract void recieveDrawOffer();
	
	/**
	 * Finds out whether to accept a draw
	 * 
	 * @return Whether to accept draw
	 */
	public abstract boolean draw();
	
	/**
	 * Determine if the player wants to resign
	 * 
	 * @return Whether to resign
	 */
	public abstract boolean resign();
}
