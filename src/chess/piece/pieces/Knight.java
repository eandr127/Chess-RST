package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class Knight extends Piece
{
	private Team oppositeTeam;

	public Knight(PieceType pieceType, Team team)
	{
		super(pieceType, team);
	}

	@Override
	public boolean canMove(Coordinates newCoords)
	{
		// By default the move is invalid
		boolean valid = false;

		//Gets the difference between the new x and y coordinate and the current x and y coordinate
		int xDifference = Math.abs(newCoords.getX() - getCoords().getX());
		int yDifference = Math.abs(newCoords.getY() - getCoords().getY());
		
		// If the piece is on the black team
		if (getTeam().equals(Team.BLACK))
		{
			// The white team is the enemy team
			oppositeTeam = Team.WHITE;
		}
		// Otherwise...
		else
		{
			// The black team is the enemy team
			oppositeTeam = Team.BLACK;
		}

		// Checks if the movement location is valid for the knight
		if (xDifference == 1 && yDifference == 2 || xDifference == 2 && yDifference == 1)
		{
			// If the movement location is empty or contains a piece from the opposite team
			if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam)
					|| getBoard().getPiece(newCoords).getTeam().equals(Team.NONE))
			{
				// The move is valid
				valid = true;
			}
		}

		return valid;
	}

	@Override
	protected void doMove(Coordinates newCoords)
	{
		// If the piece is on the black team
		if (getTeam().equals(Team.BLACK))
		{
			// The white team is the enemy team
			oppositeTeam = Team.WHITE;
		}
		// Otherwise...
		else
		{
			// The black team is the enemy team
			oppositeTeam = Team.BLACK;
		}

		// If the new location is on the opposite team
		if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam))
		{
			// Captures the designated piece
			getBoard().capture(newCoords);
		}
		
		setCoords(newCoords);
	}
}
