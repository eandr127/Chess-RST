package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class Queen extends Piece
{
	private Team oppositeTeam;
	
	public Queen(PieceType pieceType, Team team) {
		super(pieceType, team);
	}

	@Override
	protected boolean canMove(Coordinates newCoords)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doMove(Coordinates newCoords)
	{
		//If the piece is on the black team
		if(getTeam().equals(Team.BLACK))
		{
			//The white team is the enemy team
			oppositeTeam = Team.WHITE;
		}
		//Otherwise...
		else
		{
			//The black team is the enemy team
			oppositeTeam = Team.BLACK;
		}
		
//If the new location is on the opposite team
if(getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam))
{
	//Captures the designated piece
	getBoard().capture(newCoords);
}
		
	}
}
