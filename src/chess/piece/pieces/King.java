package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.player.Player;

/*
 * King.java
 * Movement and capturing for the king
 * ICS3U
 * January 24th, 2018
 */

public class King extends Piece
{
	private Team oppositeTeam;

	/**
	 * Creates a new instance of a king
	 * 
	 * @param pieceType The type of piece
	 * @param team The team of this piece
	 * @param player The player owner of this piece
	 */
	public King(PieceType pieceType, Team team, Player player)
	{
		super(pieceType, team, player);
	}

	/**
	 * Checks the given location for the king is a valid move
	 * 
	 * @param the new coordinates
	 * @return whether the location is valid
	 */
	@Override
	public boolean canMove(Coordinates newCoords)
	{
		// Creates variable to store whether it is valid, by default it is false
		boolean valid = false;

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

		// Gets the difference between the x and the y of the new and the current coords
		int xDifference = Math.abs(newCoords.getX() - getCoords().getX());
		int yDifference = Math.abs(newCoords.getY() - getCoords().getY());
		int xRawDifference = newCoords.getX() - getCoords().getX();
		// By default xRelative is 0
		int xRelative = 0;
		// Gets the x direction of the move
		if (xDifference - xRawDifference != 0)
		{
			xRelative = xDifference / xRawDifference;
		}

		// Checks if the movement location is within a 1 tile radius around the current location of the king
		if (xDifference == 1 && yDifference == 1 || xDifference == 1 && yDifference == 0
				|| xDifference == 0 && yDifference == 1)

		{
			// If the new location has an empty tile or a piece from the opposite team
			if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam)
					|| getBoard().getPiece(newCoords).getTeam().equals(Team.NONE))
			{
				// The location is valid
				valid = true;
			}
		}

		// Returns valid
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
