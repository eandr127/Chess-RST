package chess.player;

import chess.board.Board;
import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.Team;

public abstract class Player
{
	private final Team team;
	private final Board board;
	
	public Player(Team team, Board board) {
		this.team = team;
		this.board = board;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void takeTurn() {
		boolean done = false;
		
		do {
			Coordinates start = selectPiece();
			if (start.getAbort()) {
				this.abortTurn();
			}
			Coordinates end = selectDestination(board.getPiece(start));
			if (end.getAbort()) {
				this.abortTurn();
			}
			
			// Only valid pieces should be selected in the first place, but just in case
			done = board.movePiece(start, end);
			
			if(!done) {
				invalidMove();
			}
		}
		while(!done);
	}
	
	public void abortTurn() {
		//TODO: Abort code
	}
	
	public abstract Coordinates selectPiece();
	public abstract Coordinates selectDestination(Piece selected);
	public abstract void invalidMove();
	
	public abstract boolean offerDraw();
	public abstract void recieveDrawOffer();
	public abstract boolean draw();
}
