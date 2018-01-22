package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class King extends Piece
{
	private Team oppositeTeam;
	
	public King(PieceType pieceType, Team team) {
		super(pieceType, team);
	}

	@Override
	public boolean canMove(Coordinates newCoords)
	{
		boolean valid = false;
		
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
		
		int xDifference = Math.abs(newCoords.getX() - getCoords().getX());
		int yDifference = Math.abs(newCoords.getY() - getCoords().getY());
		
		if(xDifference == 1 && yDifference == 1 || xDifference == 1 && yDifference == 0 || xDifference == 0 && yDifference == 1 )
				{
					if(getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam)
						|| getBoard().getPiece(newCoords).getTeam().equals(Team.NONE))
					{
						valid = true;
					}
				}
		
		return valid;
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
