package chess.piece.pieces;

import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class Rook extends Piece
{
	private Team oppositeTeam;
	
	public Rook(PieceType pieceType, Team team)
	{
		super(pieceType, team);
	}

	@Override
	protected boolean canMove(Coordinates newCoords)
	{
		boolean valid = false;
		
		// If the player's team is black
		if (getTeam().equals(Team.BLACK))
		{
			// If the new y value is equal to the current y value
			if (newCoords.getY() == getCoords().getY())
			{
				// If the new x value is less than the current x value
				if (newCoords.getX() < getCoords().getX())
				{
					for (int i = getCoords().getX(); i >= newCoords.getX(); i--)
					{
						// If the final x value has not been reached
						if (i != newCoords.getX())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(i, 0)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final x value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the black team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.BLACK))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the black team
							else
							{
								
							}
						}

					}
				}

				// If the new x value is greater than the current x value
				else if (newCoords.getX() > getCoords().getX())
				{
					for (int i = getCoords().getX(); i <= newCoords.getX(); i++)
					{
						// If the final x value has not been reached
						if (i != newCoords.getX())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(i, 0)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final x value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the black team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.BLACK))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the black team
							else
							{

							}
						}

					}
				}

				// The new x value is equal to the current x value
				else
				{
					try
					{
						throw new Exception("The new coordinate is identical to the current one.");
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			// If the new x value is equal to the current x value
			if (newCoords.getX() == getCoords().getX())
			{
				// If the new y value is less than the current y value
				if (newCoords.getY() < getCoords().getY())
				{
					for (int i = getCoords().getY(); i >= newCoords.getY(); i--)
					{
						// If the final y value has not been reached
						if (i != newCoords.getY())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(0, i)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final y value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the black team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.BLACK))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the black team
							else
							{

							}
						}

					}
				}

				// If the new y value is greater than the current y value
				else if (newCoords.getY() > getCoords().getY())
				{
					for (int i = getCoords().getY(); i <= newCoords.getY(); i++)
					{
						// If the final y value has not been reached
						if (i != newCoords.getY())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(0, i)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final y value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the black team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.BLACK))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the black team
							else
							{
								
							}
						}

					}
				}

				// The new y value is equal to the current y value
				else
				{
					try
					{
						throw new Exception("The new coordinate is identical to the current one.");
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}
		//If the player's team is white
		if (getTeam().equals(Team.WHITE))
		{
			// If the new y value is equal to the current y value
			if (newCoords.getY() == getCoords().getY())
			{
				// If the new x value is less than the current x value
				if (newCoords.getX() < getCoords().getX())
				{
					for (int i = getCoords().getX(); i >= newCoords.getX(); i--)
					{
						// If the final x value has not been reached
						if (i != newCoords.getX())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(i, 0)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final x value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the white team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.WHITE))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the white team
							else
							{

							}
						}

					}
				}

				// If the new x value is greater than the current x value
				else if (newCoords.getX() > getCoords().getX())
				{
					for (int i = getCoords().getX(); i <= newCoords.getX(); i++)
					{
						// If the final x value has not been reached
						if (i != newCoords.getX())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(i, 0)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final x value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the white team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.WHITE))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the white team
							else
							{
								
							}
						}

					}
				}

				// The new x value is equal to the current x value
				else
				{
					try
					{
						throw new Exception("The new coordinate is identical to the current one.");
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			// If the new x value is equal to the current x value
			if (newCoords.getX() == getCoords().getX())
			{
				// If the new y value is less than the current y value
				if (newCoords.getY() < getCoords().getY())
				{
					for (int i = getCoords().getY(); i >= newCoords.getY(); i--)
					{
						// If the final y value has not been reached
						if (i != newCoords.getY())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(0, i)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final y value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the white team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.WHITE))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the white team
							else
							{
								
							}
						}

					}
				}

				// If the new y value is greater than the current y value
				else if (newCoords.getY() > getCoords().getY())
				{
					for (int i = getCoords().getY(); i <= newCoords.getY(); i++)
					{
						// If the final y value has not been reached
						if (i != newCoords.getY())
						{
							// If the checked coordinate is not empty
							if (!getBoard().getPiece(getCoords().add(0, i)).getTeam().equals(Team.NONE))
							{
								break;
							}
						}
						// The final y value has been reached
						else
						{
							// If the square with the final coordinate contains a piece that is not on the white team
							if (!getBoard().getPiece(newCoords).getTeam().equals(Team.WHITE))
							{
								valid = true;
							}
							// The square with the final coordinate contains a piece that is on the black team
							else
							{
								
							}
						}

					}
				}

				// The new y value is equal to the current y value
				else
				{
					try
					{
						throw new Exception("The new coordinate is identical to the current one.");
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

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
