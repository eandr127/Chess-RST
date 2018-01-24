package chess.piece.pieces;

import chess.board.Board;
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
	 * pre: pieceType The type of piece
	 * pre: team The team of this piece
	 * pre: player The player owner of this piece
	 */
	public King(PieceType pieceType, Team team, Player player)
	{
		super(pieceType, team, player);
	}

	/**
	 * Checks the given location for the king is a valid move
	 * 
	 * pre: the new coordinates
	 * post: whether the location is valid
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
		} else if (getBoard().getMovesForPiece(this).size() == 0 && xDifference == 2 && yDifference == 0
				&& !getBoard().kingInCheck(getTeam()))
		{
			boolean direction = newCoords.getX() - getCoords().getX() < 0;
			Piece rook = findRook(getCoords(), getTeam(), direction);

			if (getBoard().getMovesForPiece(rook).size() > 0)
			{
				return false;
			}

			Board copy = getBoard().copy();
			copy.setCheckSafe(true);

			Coordinates movement = getCoords();
			for (int i = 0; i < 2; i++)
			{
				Coordinates oldCoords = movement;

				if (direction)
				{
					movement = movement.add(-1, 0);
				} else
				{
					movement = movement.add(1, 0);
				}

				if (!copy.movePiece(oldCoords, movement))
				{
					return false;
				}
			}

			return true;
		}

		// Returns valid
		return valid;

	}

	private Piece findRook(Coordinates coords, Team team, boolean direction)
	{
		Piece piece = null;
		while (piece == null)
		{
			if (direction)
			{
				coords = coords.add(-1, 0);
			} else
			{
				coords = coords.add(1, 0);
			}

			Piece testedPiece = getBoard().getPiece(coords);
			if (testedPiece.getPieceType() == PieceType.ROOK && testedPiece.getTeam() == team)
			{
				piece = testedPiece;
			}
		}

		return piece;
	}

	/**
	 * Does the move and capturing if applicable
	 * 
	 * pre: the new coordinates
	 * post: none
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

		// Doing castle
		if (Math.abs(newCoords.getX() - getCoords().getX()) == 2
				&& Math.abs(newCoords.getY() - getCoords().getY()) == 0)
		{
			boolean direction = newCoords.getX() - getCoords().getX() < 0;
			Piece rook = findRook(getCoords(), getTeam(), direction);

			Coordinates rookCoords = newCoords;
			if (direction)
			{
				rookCoords = rookCoords.add(1, 0);
			} else
			{
				rookCoords = rookCoords.add(-1, 0);
			}

			rook.setCoords(rookCoords);
			getBoard().updatePieceLocation(rook);
		}

		// Sets new coordinates for the piece
		setCoords(newCoords);
	}
}
