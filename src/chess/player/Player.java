package chess.player;

import chess.board.Board;
import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
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
	 * Creates an instance of Player with a set team and board to use.
	 * @param team The team of this player.
	 * @param board The board this player will use.
	 */
	public Player(Team team, Board board) {
		this.team = team;
		this.board = board;
	}
	
	/**
	 * Gets the current team of this player.
	 * @return The current team of this player
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Gets the current board used by this player.
	 * @return The board used by this player
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Takes the player's turn. Asks the player to select a piece,
	 * select a destination for that piece, and if the destination
	 * is valid, then moves the piece to that destination.
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
	
	public abstract PieceType pawnPromotion();
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
