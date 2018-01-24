package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

/*
 * Queen.java
 * Movement and capturing for the queen
 * Dmitry Tsarapkine
 * ICS3U
 * January 24th, 2018
 */

public class Queen extends Piece
{
	private Team oppositeTeam;

	/**
	 * Creates a new instance of a queen
	 * 
	 * @param pieceType and team
	 */
	public Queen(PieceType pieceType, Team team)
	{
		super(pieceType, team);
	}

	/**
	 * Checks the given location for the queen is a valid move
	 * 
	 * @param the new coordinates
	 * @return whether the location is valid
	 */
	@Override
	public boolean canMove(Coordinates newCoords)
	{
		// Variable to store whether the move is valid, by default, it is invalid
		boolean valid = false;
		// Gets difference between new coordinates and old coordinates
		int xDifference = newCoords.getX() - getCoords().getX();
		int yDifference = newCoords.getY() - getCoords().getY();
		// Gets absolute value of xDifference and yDifference
		int absYDifference = Math.abs(yDifference);
		int absXDifference = Math.abs(xDifference);
		int xRelative, yRelative;
		// Variable to store the team of the player
		Team sameTeam;

		// If the piece is on the black team
		if (getTeam().equals(Team.BLACK))
		{
			// The white team is the enemy team
			oppositeTeam = Team.WHITE;
			sameTeam = Team.BLACK;
		}
		// Otherwise...
		else
		{
			// The black team is the enemy team
			oppositeTeam = Team.BLACK;
			sameTeam = Team.WHITE;
		}

		// If the new coordinate is not diagonal from the current location
		if (absXDifference - absYDifference != 0 || xDifference == 0 || yDifference == 0)
		{
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
				for (int i = xRelative; i != xDifference + xRelative; i += xRelative)
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

				// If the new y coordinate is less than the current y coordinate
				if (newCoords.getY() < getCoords().getY())
				{
					// New y value has a negative relation to the current y value
					yRelative = -1;
				} else if (newCoords.getY() > getCoords().getY())
				{
					// New y value has a positive relation to the current y value
					yRelative = 1;
				} else
				{
					// Return false
					return false;
				}

				// Allows to cycle tiles until reaches the final location
				for (int i = yRelative; i != yDifference + yRelative; i += yRelative)
				{
					// If the final location has not been reached and there is not an empty tile spot
					if (!getBoard().getPiece(getCoords().add(0, i)).getTeam().equals(Team.NONE) && i != yDifference)
					{
						// Return false
						return false;
						// If the final location has been reached and there is a piece that is not on the same team
					} else if (i == yDifference && !getBoard().getPiece(newCoords).getTeam().equals(sameTeam))
					{
						// Return true
						return true;
					}

				}
			}

		} else
		{
			// Moves 1 tile towards the final destination
			for (int i = xDifference / absXDifference, j = yDifference / absYDifference; i != xDifference
					+ absXDifference / xDifference; i += xDifference
							/ absXDifference, j += yDifference / absYDifference)
			{
				// If there is a piece in the way and it is not the final location
				if (i != xDifference && j != yDifference
						&& !getBoard().getPiece(getCoords().add(i, j)).getTeam().equals(Team.NONE))
				{
					// Breaks the loop
					break;
					// If it is checking the final location
				} else if (i == xDifference && j == yDifference)
				{
					// If there is not a piece from the same team
					if (!getBoard().getPiece(newCoords).getTeam().equals(sameTeam))
					{
						// Returns true
						return true;
					} else
					{
						// Breaks the loop
						break;
					}
				}

			}
		}

		//Returns valid
		return valid;

	}

	/**
	 * Does the move and capturing if applicable
	 * 
	 * @param the new coordinates
	 * @return none
	 */
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

		// Sets new coordinates for the piece
		setCoords(newCoords);
	}
}
