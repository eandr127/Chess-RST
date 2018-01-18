package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class Rook extends Piece
{
	private Team oppositeTeam, sameTeam;

	public Rook(PieceType pieceType, Team team)
	{
		super(pieceType, team);
	}

	@Override
	protected boolean canMove(Coordinates newCoords)
	{
		// By default, the choice is not valid
		boolean valid = false;
		// Creates integers to store whether x and y is smaller or larger and to store x and y difference
		int yRelative, xRelative, xDifference, yDifference;

		// If it is the black team's turn
		if (getTeam().equals(Team.BLACK))
		{
			// The opposite team is the white team
			oppositeTeam = Team.WHITE;
			// The same team is the black team
			sameTeam = Team.BLACK;
		} else
		// If it is the white team's turn
		{
			// The opposite team is the black team
			oppositeTeam = Team.BLACK;
			// The same team is the white team
			sameTeam = Team.WHITE;
		}

		// If the y coordinate remains unchanged
		if (newCoords.getY() == getCoords().getY())
		{
			// Gets the difference between the x of the new location and the current location
			xDifference = newCoords.getX() - getCoords().getX();

			// If the new x coordinate value is less than the old x coordinate value
			if (newCoords.getX() < getCoords().getX())
			{
				// New x value has a negative relation to the current x value
				xRelative = -1;
			} else if (newCoords.getX() > getCoords().getX())
			{
				// New x value has a positive relation to the current x value
				xRelative = 1;
			}
			// The piece has not moved
			else
			{
				// Returns false
				return false;
			}

			// Allows to check for any pieces that are in the way for each square up until the desired location
			for (int i = xRelative; i != xDifference; i += xRelative)
			{
				// If there is a piece in the way and it is not the final location
				if (!getBoard().getPiece(getCoords().add(i, 0)).getTeam().equals(Team.NONE) && i != xDifference)
				{
					// Returns false
					return false;
					// If there is a piece on the final location that is not on the same team
				} else if (i == xDifference && !getBoard().getPiece(newCoords).getTeam().equals(sameTeam))
				{
					// Returns true
					return true;
				}
			}
			// If the x coordinate remains unchanged
		} else if (newCoords.getX() == getCoords().getX())
		{
			// Calculates the difference between the new coordinate and the old coordinate
			yDifference = newCoords.getY() - getCoords().getY();

			//If the new y coordinate is less than the current y coordinate
			if (newCoords.getY() < getCoords().getY())
			{
				//New y value has a negative relation to the current y value
				yRelative = -1;
			} else if (newCoords.getY() > getCoords().getY())
			{
				//New y value has a positive relation to the current y value
				yRelative = 1;
			} else
			{
				//Return false
				return false;
			}

			//Allows to cycle tiles until reaches the final location
			for (int i = yRelative; i != yDifference; i += yRelative)
			{
				//If the final location has not been reached and there is not an empty tile spot
				if (!getBoard().getPiece(getCoords().add(0, i)).getTeam().equals(Team.NONE) && i != yDifference)
				{
					//Return false 
					return false;
					//If the final location has been reached and there is a piece that is not on the same team
				} else if (i == yDifference && !getBoard().getPiece(newCoords).getTeam().equals(sameTeam))
				{
					//Return true
					return true;
				}

			}
		}
		//Returns valid
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
	}
}
