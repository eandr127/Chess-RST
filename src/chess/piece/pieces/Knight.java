package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.player.Player;

/*
 * Knight.java
 * Movement and capturing for the knight
 * ICS3U
 * January 24th, 2018
 */

public class Knight extends Piece
{
	private Team oppositeTeam;

	/**
	 * Creates a new instance of a knight
	 * 
	 * @param pieceType The type of piece
	 * @param team The team of this piece
	 * @param player The player owner of this piece
	 */
	public Knight(PieceType pieceType, Team team, Player player)
	{
		super(pieceType, team, player);
	}

	/**
	 * Checks the given location for the knight is a valid move
	 * 
	 * @param the new coordinates
	 * @return whether the location is valid
	 */
	@Override
	public boolean canMove(Coordinates newCoords)
	{
		// By default the move is invalid
		boolean valid = false;

		// Gets the difference between the new x and y coordinate and the current x and y coordinate
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
