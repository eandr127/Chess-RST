package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class King extends Piece
{
	private Team oppositeTeam;

	public King(PieceType pieceType, Team team)
	{
		super(pieceType, team);
	}

	/**
	 * Checks the given location for the king is a valid move
	 * @param the new coordinates
	 * @return whether the location is valid
	 */
	@Override
	public boolean canMove(Coordinates newCoords)
	{
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
		} else if (getBoard().getMovesForPiece(getBoard().getPiece(getCoords())).size() == 0
				&& getBoard().getMovesForPiece(getBoard().getPiece(newCoords)).size() == 0
				&& getBoard().getPiece(newCoords).getPieceType().equals(PieceType.ROOK)
				&& newCoords.getX() == getCoords().getX())
		{
			valid = true;
		}

		// Returns valid
		return valid;
	}

	/**
	 * Does the move
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
		

		if(getBoard().getMovesForPiece(getBoard().getPiece(getCoords())).size() == 0
				&& getBoard().getMovesForPiece(getBoard().getPiece(newCoords)).size() == 0
				&& getBoard().getPiece(newCoords).getPieceType().equals(PieceType.ROOK))
				{
					setCoords(getCoords().add(newCoords.getX() - getCoords().getX() * 2, 0));
					
				}
		else
		{
			setCoords(newCoords);
		}

	}
}
