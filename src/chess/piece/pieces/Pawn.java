package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

/*
 * Pawn.java
 * Movement and capturing for the pawn
 * Dmitry Tsarapkine
 * ICS3U
 * January 24th, 2018
 */

public class Pawn extends Piece
{
	// Creates variable to store opposite team and current
	private Team oppositeTeam, currentTeam;

	/**
	 * Creates a new instance of a pawn
	 * 
	 * @param pieceType and team
	 */
	public Pawn(PieceType pieceType, Team team)
	{
		super(pieceType, team);
	}

	/**
	 * Checks the given location for the pawn is a valid move
	 * 
	 * @param the new coordinates
	 * @return whether the location is valid
	 */
	@Override
	public boolean canMove(Coordinates newCoords)
	{
		// Creates variable to store whether it is valid, by default it is false and whether it is the piece's first
		// move
		boolean valid = false, firstMove = getBoard().getMovesForPiece(this).size() == 0;

		int yModifier;

		// If it is the black team's turn
		if (getTeam().equals(Team.BLACK))
		{
			oppositeTeam = Team.WHITE;
			currentTeam = Team.BLACK;
			yModifier = -1;
		} 
		else
		// If it is the white team's turn
		{
			oppositeTeam = Team.BLACK;
			currentTeam = Team.WHITE;
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
			}
			// Allows the pawn to move 2 forward if it is the first move
			else if (firstMove == true && newCoords.getY() == getCoords().getY() + 2 * yModifier
					&& getBoard().getPiece(newCoords).getTeam().equals(Team.NONE)
					&& getBoard().getPiece(getCoords().add(0, yModifier)).getTeam().equals(Team.NONE))
			{
				// The move is valid
				valid = true;
			}
		} 
		// If the new destination has a piece that belongs to the opposite team and it is 1 tile ahead on x and y
		else if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam)
				&& newCoords.equals(getCoords().add(1, yModifier)))
		{
			// The move is valid
			valid = true;
		} 
		// If the new destination has a piece that belongs to the opposite team and it is 1 tile ahead on x and y
		else if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam)
				&& newCoords.equals(getCoords().add(-1, yModifier)))
		{
			// The move is valid
			valid = true;
		} 
		else if (getCoords().getY() == 4 && currentTeam.equals(Team.BLACK)
				|| getCoords().getY() == 5 && currentTeam.equals(Team.WHITE))
		{
			//Checks if en passant is applicable
		}
			if (Math.abs(newCoords.getX() - getCoords().getX()) == 1
					&& newCoords.getY() - getCoords().getY() == yModifier
					&& getBoard().getPiece(newCoords.add(0, -yModifier)).getTeam().equals(oppositeTeam)
					&& getBoard().getPiece(newCoords.add(0, -yModifier)).getPieceType().equals(PieceType.PAWN)
					&& getBoard().getMovesForPiece(getBoard().getPiece(newCoords.add(0, -yModifier))).size() == 1
					&& getBoard().getMoves().get(getBoard().getMoves().size() - 1).getPiece()
							.equals(getBoard().getPiece(newCoords.add(0, -yModifier))))
			{
				//The move is valid
				valid = true;
			}
		

		// Returns whether the move is valid
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
		int yModifier;

		// If the piece is on the black team
		if (getTeam().equals(Team.BLACK))
		{
			// The white team is the enemy team
			oppositeTeam = Team.WHITE;
			yModifier = -1;

			// Pawn promotion
			if (newCoords.getY() == 1)
			{
				// getBoard().replacePieceType();
			}
		}
		// Otherwise...
		else
		{
			// The black team is the enemy team
			oppositeTeam = Team.BLACK;
			yModifier = 1;

			if (newCoords.getY() == 8)
			{
				// getBoard().replacePieceType(newCoords, pawnPromotion());
			}
		}

		// If the new location is on the opposite team
		if (getBoard().getPiece(newCoords).getTeam().equals(oppositeTeam))
		{
			// Captures the designated piece
			getBoard().capture(newCoords);
		} 
		//Checks if en passant is applicable
		else if (Math.abs(newCoords.getX() - getCoords().getX()) == 1
				&& newCoords.getY() - getCoords().getY() == yModifier
				&& getBoard().getPiece(newCoords.add(0, -yModifier)).getTeam().equals(oppositeTeam)
				&& getBoard().getPiece(newCoords.add(0, -yModifier)).getPieceType().equals(PieceType.PAWN)
				&& getBoard().getMovesForPiece(getBoard().getPiece(newCoords.add(0, -yModifier))).size() == 1
				&& getBoard().getMoves().get(getBoard().getMoves().size() - 1).getPiece()
						.equals(getBoard().getPiece(newCoords.add(0, -yModifier))))
		{
			//Captures the piece 1 behind the new desination
			getBoard().capture(newCoords.add(0, -yModifier));
		}

		// Sets new coordinates for the piece
		setCoords(newCoords);
	}
}
