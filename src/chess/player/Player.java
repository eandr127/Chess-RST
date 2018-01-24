package chess.player;

import chess.board.Board;
import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public abstract class Player
{
	private final Team team;
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
		boolean done = false;
		
		do {
			Coordinates start = selectPiece();
			Coordinates end = selectDestination(board.getPiece(start));
			
			// Only valid pieces should be selected in the first place, but just in case
			done = board.movePiece(start, end);
			
			if(!done) {
				invalidMove();
			}
		}
		while(!done);
	}
	
	public abstract PieceType pawnPromotion();
	public abstract Coordinates selectPiece();
	public abstract Coordinates selectDestination(Piece selected);
	public abstract void invalidMove();
	
	public abstract boolean offerDraw();
	public abstract void recieveDrawOffer();
	public abstract boolean draw();
}
