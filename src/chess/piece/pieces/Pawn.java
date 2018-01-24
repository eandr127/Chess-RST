package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class Pawn extends Piece
{
	private Team oppositeTeam;

	public Pawn(PieceType pieceType, Team team)
	{
		super(pieceType, team);
	}

	@Override
	public boolean canMove(Coordinates newCoords)
	{
		boolean valid = false, firstMove = getBoard().getMovesForPiece(this).size() == 0;

		int yModifier;

		// If it is the black team's turn
		if (getTeam().equals(Team.BLACK))
		{
			oppositeTeam = Team.WHITE;
			yModifier = -1;
		} else
		// If it is the white team's turn
		{
			oppositeTeam = Team.BLACK;
			yModifier = 1;
		}

		// If the x coordinate remains the same
		if (newCoords.getX() == getCoords().getX())
		{
			// If the piece 1 ahead is empty and is what the user choose
			if (newCoords.getY() == getCoords().getY() + yModifier
					&& getBoard().getPiece(newCoords).getTeam().equals(Team.NONE))
			{
				// The move is valid
				valid = true;
				// If it is the first move for the piece and the user choose the spot 2 ahead and that the 2 spots ahead
				// are empty
			} else if (firstMove == true && newCoords.getY() == getCoords().getY() + 2 * yModifier
					&& getBoard().getPiece(newCoords).getTeam().equals(Team.NONE)
					&& getBoard().getPiece(newCoords.add(0, yModifier)).getTeam().equals(Team.NONE))
			{
				// The move is valid
				valid = true;
			}
		} else if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam)
				&& newCoords.equals(getCoords().add(1, yModifier)))
		{
			// The move is valid
			valid = true;
		} else if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam)
				&& newCoords.equals(getCoords().add(-1, yModifier)))
		{
			// The move is valid
			valid = true;
		}

		// Returns whether the move is valid
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

			//Pawn promotion
			if (newCoords.getY() == 1)
			{
				//getBoard().replacePieceType(newCoords, pawnPromotion());
			}
		}
		// Otherwise...
		else
		{
			// The black team is the enemy team
			oppositeTeam = Team.BLACK;
			
			if(newCoords.getY() == 8)
			{
				//getBoard().replacePieceType(newCoords, pawnPromotion());
			}
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
