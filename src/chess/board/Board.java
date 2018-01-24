package chess.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.piece.Move;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.piece.pieces.King;
import chess.player.Player;

/**
 * Represents a board that can display somewhere
 */
public abstract class Board
{

	/**
	 * The side length of the board
	 */
	public static final int BOARD_SIZE = 8;

	/**
	 * The players
	 */
	private Player player1, player2;

	/**
	 * What the starting arrangement of chess looks like
	 */
	private static final Piece[][] startingArrangement = new Piece[][] {
		new Piece[] {	PieceType.ROOK.black(),	PieceType.KNIGHT.black(),	PieceType.BISHOP.black(),	PieceType.QUEEN.black(),	PieceType.KING.black(),	PieceType.BISHOP.black(),	PieceType.KNIGHT.black(),	PieceType.ROOK.black()	},
		new Piece[] {	PieceType.PAWN.black(),	PieceType.PAWN.black(),		PieceType.PAWN.black(),		PieceType.PAWN.black(),		PieceType.PAWN.black(),	PieceType.PAWN.black(),		PieceType.PAWN.black(),		PieceType.PAWN.black()	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,		PieceType.EMPTY_PIECE,	},
		new Piece[] {	PieceType.PAWN.white(),	PieceType.PAWN.white(),		PieceType.PAWN.white(),		PieceType.PAWN.white(),		PieceType.PAWN.white(),	PieceType.PAWN.white(),		PieceType.PAWN.white(),		PieceType.PAWN.white()	},
		new Piece[] {	PieceType.ROOK.white(),	PieceType.KNIGHT.white(),	PieceType.BISHOP.white(),	PieceType.QUEEN.white(),	PieceType.KING.white(),	PieceType.BISHOP.white(),	PieceType.KNIGHT.white(),	PieceType.ROOK.white()	}
	};

	/**
	 * 8x8 grid of pieces representing the chess board
	 */
	private final Piece[][] arrangement = new Piece[BOARD_SIZE][BOARD_SIZE];

	/**
	 * Holds the state of the board at every turn for the white player
	 */
	private final List<Piece[][]> whiteSetup = new ArrayList<>();

	/**
	 * Holds the state of the board at every turn for the black player
	 */
	private final List<Piece[][]> blackSetup = new ArrayList<>();

	/**
	 * The time since a move that resets the draw timer has taken place
	 */
	private int movesSincePawnOrCapture = 0;

	/**
	 * The moves that take place on this board
	 */
	private final List<Move> moves;

	/**
	 * Determines whether to ensure that moves will not put king in check
	 */
	private boolean checkSafe = true;

	/**
	 * Creates a board with the starting arrangement
	 */
	public Board()
	{
		this(startingArrangement);
	}

	/**
	 * Creates a board with another arrangement
	 * 
	 * pre: arrangement The custom arrangement
	 */
	public Board(Piece[][] arrangement)
	{
		fillBoard(arrangement);
		this.moves = new ArrayList<>();
		this.blackSetup.add(arrangement);
	}

	/**
	 * Fills the board with fully initialized pieces
	 * 
	 * pre: pieces The arrangement to initialize the pieces with
	 */
	private void fillBoard(Piece[][] arrangement)
	{
		// Loop through each row
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			// Loop through each column
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				// Initialize piece
				if (arrangement[i][j].getTeam().equals(Team.WHITE))
				{
					arrangement[i][j].lateInit(convertFromArray(new Coordinates(i, j)), this, getPlayer1());
				} else
				{
					arrangement[i][j].lateInit(convertFromArray(new Coordinates(i, j)), this, getPlayer2());
				}
				// Put it in the new array
				this.arrangement[i][j] = arrangement[i][j];
			}
		}
	}

	/**
	 * Gets the arrangement of the board
	 * 
	 * post: The board arrangement
	 */
	protected Piece[][] getArrangement()
	{
		return arrangement;
	}

	/**
	 * Displays board with no highlight
	 */
	public void showBoard()
	{
		displayBoard(new Coordinates[0]);
	}

	/**
	 * Displays board with all pieces that can be moved selected highlighted
	 * 
	 * pre: team The team to highlight pieces for
	 */
	public void showBoard(Team team)
	{
		// Create list of coordinates to highlight
		List<Coordinates> coords = new ArrayList<>();

		// Loop over every piece on board
		for (Piece[] row : arrangement)
		{
			for (Piece piece : row)
			{
				// Check if the piece has any possible moves
				if (piece != PieceType.EMPTY_PIECE && piece.getTeam() == team && piece.getValidMoves().length != 0)
				{
					// Add the coordinates to the list to highlight
					coords.add(convertToArray(piece.getCoords()));
				}
			}
		}

		// Display the board with the selected coordinates highlighted
		displayBoard(coords.toArray(new Coordinates[0]));
	}

	/**
	 * Displays the board with all locations that a piece can be moved highlighted
	 * 
	 * pre: piece The piece to highlight locations for
	 */
	public void showBoard(Piece piece)
	{
		// Get all possible moves for piece
		Coordinates[] coords = piece.getValidMoves();

		// Convert to array coordinates
		for (int i = 0; i < coords.length; i++)
		{
			coords[i] = convertToArray(coords[i]);
		}

		// Display the board witht the selected coordinates highlighted
		displayBoard(coords);
	}

	/**
	 * Display the board
	 * 
	 * pre: pieces All array coordinates to highlight
	 */
	protected abstract void displayBoard(Coordinates[] pieces);

	/**
	 * Converts standard piece coordinates to ones that are properly interpreted by the array
	 * 
	 * pre: coords The coordinates to convert
	 * post: The converted coordinates
	 */
	private Coordinates convertToArray(Coordinates coords)
	{
		return new Coordinates(8 - coords.getY(), coords.getX() - 1);
	}

	/**
	 * Convert array coordinates to piece coordinates
	 * 
	 * pre: coords The coordinates to convert
	 * post: The converted coordinates
	 */
	private Coordinates convertFromArray(Coordinates coords)
	{
		return new Coordinates(coords.getY() + 1, 8 - coords.getX());
	}

	/**
	 * Gets a piece on the board
	 * 
	 * pre: coords The location of the piece
	 * post: The piece
	 */
	public Piece getPiece(Coordinates coords)
	{
		coords = convertToArray(coords);
		return getArrangement()[coords.getX()][coords.getY()];
	}

	/**
	 * Gets the location of a piece on the board
	 * 
	 * pre: piece The piece to locate
	 * post: The location of the piece
	 */
	public Coordinates getLocation(Piece piece)
	{
		// Loop through each row
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			// Loop through each column
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				// See if the piece is a match
				if (getArrangement()[i][j] == piece)
				{
					// Return the coordinates of the matching piece
					return convertFromArray(new Coordinates(i, j));
				}
			}
		}

		// Piece was not found
		return null;
	}

	/**
	 * Moves a piece from one place to another
	 * 
	 * pre: start The location of the piece to move
	 * pre: end The destination of the piece
	 * post: Whether the piece was successfully moved or not
	 */
	public boolean movePiece(Coordinates start, Coordinates end)
	{
		// Get the piece at the coordinates
		Piece piece = getPiece(start);

		// Create the move to be performed
		Move move = new Move(piece, end.getX() - start.getX(), end.getY() - start.getY());

		// Try to move the piece
		if (move.execute())
		{
			if (piece.getPieceType() == PieceType.PAWN)
			{
				movesSincePawnOrCapture = 0;
			}

			// Update the location of the piece on the board
			updatePieceLocation(piece);

			// Add piece to move history
			addMove(move);

			// Return successful
			return true;
		}
		// Piece could not be moved
		else
		{
			// Return failure
			return false;
		}
	}

	/**
	 * Updates the location of the piece
	 * 
	 * pre: piece The piece to update the location for
	 */
	public void updatePieceLocation(Piece piece)
	{
		// Convert piece coordinates to array coordinates
		Coordinates start = convertToArray(getLocation(piece));
		Coordinates end = convertToArray(piece.getCoords());

		// Movement was successful so update the board
		getArrangement()[start.getX()][start.getY()] = PieceType.EMPTY_PIECE;
		getArrangement()[end.getX()][end.getY()] = piece;
	}

	/**
	 * Captures a piece
	 * 
	 * pre: coords The location to capture
	 */
	public void capture(Coordinates coords)
	{
		movesSincePawnOrCapture = 0;

		// Convert piece coordinates to array coordinates
		coords = convertToArray(coords);

		// Make captured piece empty
		getArrangement()[coords.getX()][coords.getY()] = PieceType.EMPTY_PIECE;
	}

	/**
	 * Gets the king for a certain team
	 * 
	 * pre: team The team to find the king for
	 * post: The king for the team
	 */
	public King findKingForTeam(Team team)
	{
		// Loop over each piece
		for (Piece[] row : arrangement)
		{
			for (Piece piece : row)
			{
				// Check if the piece is a king and on the same team
				if (piece instanceof King && piece.getTeam() == team)
				{
					// Return the piece as a king
					return (King) piece;
				}
			}
		}

		// No king was found
		return null;
	}

	/**
	 * Checks if the king is in check in the current board configuration
	 * 
	 * pre: team The team of the king
	 * post: Whether the king is in check
	 */
	public boolean kingInCheck(Team team)
	{
		return kingInCheck(team, this);
	}

	/**
	 * Checks if the king is in check for a board configuration
	 * 
	 * pre: team The team of the king
	 * pre: board The board configuration
	 * post: Whether the king is in check
	 */
	public boolean kingInCheck(Team team, Board board)
	{
		// Get the king to determine if checked
		King king = board.findKingForTeam(team);

		// Loop over each piece on board
		for (Piece[] row : board.arrangement)
		{
			for (Piece piece : row)
			{
				// Check if the piece is on the opposite team and can capture the king
				if (piece.getTeam() != team && piece.canMove(king.getCoords()))
				{
					// At least one piece can capture the king
					return true;
				}
			}
		}

		// No pieces can capture king
		return false;
	}

	/**
	 * Checks if a move will put the king in check
	 * 
	 * pre: move The move to check
	 * post: Whether the move will put the king in check
	 */
	public boolean putsKingInCheck(Move move)
	{
		return kingInCheck(move.getPiece().getTeam(), showMove(move));
	}

	/**
	 * Checks if a move will save the king from check
	 * 
	 * pre: move The move to check
	 * post: Whether the move will save the king from check
	 */
	public boolean savesKingFromCheck(Move move)
	{
		// Ensure the king is actually in check
		if (kingInCheck(move.getPiece().getTeam()))
		{
			// See if the the king is in check when the move is applied
			return !kingInCheck(move.getPiece().getTeam(), showMove(move));
		} else
		{
			// King doesn't have to worry about check
			return true;
		}
	}

	/**
	 * Determines if a king is in checkmate
	 * 
	 * pre: team The team of the king
	 * post: Whether the king is in checkmate
	 */
	public boolean isCheckmate(Team team)
	{
		// Checks whether the king is even in check
		if (kingInCheck(team))
		{
			// Loop over each piece
			for (Piece[] pieces : arrangement)
			{
				for (Piece piece : pieces)
				{
					// Get only pieces on the same team
					if (piece.getTeam() == team)
					{
						// See if the pieces have any valid moves
						for (Coordinates coords : piece.getValidMoves())
						{
							// Creates the move from the coordinates of the destination
							Move move = new Move(piece, coords.getX() - piece.getCoords().getX(),
									coords.getX() - piece.getCoords().getX());

							// See if the king is saved by the move
							if (savesKingFromCheck(move))
							{
								// Checkmate averted
								return false;
							}
						}
					}
				}
			}
			return true;
		} else
		{
			// King not in check so not in checkmate
			return false;
		}
	}

	/**
	 * Creates a board that shows a move applied to it
	 * 
	 * pre: move The move to apply
	 * post: A copy of the board with a move applied
	 */
	private Board showMove(Move move)
	{
		// Make a copy of the board
		Board copy = copy();

		// Move the piece on the copy
		copy.movePiece(move.getPiece().getCoords(),
				move.getPiece().getCoords().add(move.getDeltaX(), move.getDeltaY()));

		// Return the board with the move applied
		return copy;
	}

	/**
	 * Replaces one piece with another with a different type
	 * 
	 * pre: coords The piece to change
	 * pre: newPieceType The new piece type
	 */
	public void replacePieceType(Coordinates coords, PieceType newPieceType)
	{
		// Get the team of the new piece
		Team team = getPiece(coords).getTeam();

		// Create the piece with the appropriate team
		Piece newPiece;
		if (team == Team.WHITE)
		{
			newPiece = newPieceType.white();
		} else
		{
			newPiece = newPieceType.black();
		}

		// Initialize the piece with its coordinates
		if (newPiece.getTeam().equals(Team.WHITE))
		{
			newPiece.lateInit(coords, this, getPlayer1());
		} else
		{
			newPiece.lateInit(coords, this, getPlayer2());
		}

		// Convert coordinates to array coordinates to update board
		coords = convertToArray(coords);

		// Update board
		arrangement[coords.getX()][coords.getY()] = newPiece;
	}

	/**
	 * Gets the all previous moves on the board
	 * 
	 * post: The list of moves
	 */
	public List<Move> getMoves()
	{
		return Collections.unmodifiableList(moves);
	}

	/**
	 * Gets the moves for a certain piece
	 * 
	 * pre: piece The piece to get moves for
	 * post: The list of moves for a specific piece
	 */
	public List<Move> getMovesForPiece(Piece piece)
	{
		// Create a list of moves to be returned
		List<Move> pieceMoves = new ArrayList<>();

		// Loop through each move
		for (Move move : moves)
		{
			// Check if the piece made the move
			if (move.getPiece() == piece)
			{
				// Add the move to the list of moves made by the piece
				pieceMoves.add(move);
			}
		}

		// Return the list
		return pieceMoves;
	}

	/**
	 * Makes a board with a pre-existing set of moves
	 * 
	 * pre: arrangement The arrangement of the board
	 * pre: moves The already executed moves
	 * post: The new board
	 */
	protected abstract Board make(Piece[][] arrangement, List<Move> moves);

	/**
	 * Makes a copy of the board
	 * 
	 * post: The copy of the board
	 */
	public Board copy()
	{
		// Map old pieces to the new pieces
		Map<Piece, Piece> pieceMapping = new HashMap<>();

		// Loop over each piece
		for (Piece[] row : arrangement)
		{
			for (Piece piece : row)
			{
				// Only add piece if it doesn't already have a mapping
				if (!pieceMapping.containsKey(piece))
				{
					// Create piece for correct team
					switch (piece.getTeam())
					{
					case BLACK:
						pieceMapping.put(piece, piece.getPieceType().black());
						break;
					case WHITE:
						pieceMapping.put(piece, piece.getPieceType().white());
						break;
					default:
						pieceMapping.put(piece, PieceType.EMPTY_PIECE);
					}

				}
			}
		}

		// Create a copy of the arrangement
		Piece[][] newArrangement = new Piece[BOARD_SIZE][BOARD_SIZE];

		// Loop through each index in the 2D array
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				// Assign the correctly mapped piece the same array position
				newArrangement[i][j] = pieceMapping.get(arrangement[i][j]);
			}
		}

		// Create a list of the same size
		List<Move> moves = new ArrayList<Move>(this.moves.size());

		// Add each move to the new list of moves
		for (Move move : this.moves)
		{
			// Add the move with the correctly mapped piece
			moves.add(new Move(pieceMapping.get(move.getPiece()), move.getDeltaX(), move.getDeltaY()));
		}

		// Make the new board with the arrangement and move history
		return make(newArrangement, moves);
	}

	/**
	 * Adds a move to the list of moves
	 * 
	 * pre: move The move to add
	 */
	public void addMove(Move move)
	{
		moves.add(move);

		// Add board configuration to list to check for Threefold and Fivefold Repetition
		if (move.getPiece().getTeam() == Team.WHITE)
		{
			whiteSetup.add(arrangement);
		} else if (move.getPiece().getTeam() == Team.BLACK)
		{
			blackSetup.add(arrangement);
		}
	}

	/**
	 * Set whether to check for moves that would put the king in checkmate
	 * 
	 * pre: checkSafe Whether to move check-safe or not
	 */
	public void setCheckSafe(boolean checkSafe)
	{
		this.checkSafe = checkSafe;
	}

	/**
	 * Whether to ensure moves do not put king in check
	 * 
	 * post: Whether to move check-safe or not
	 */
	public boolean isCheckSafe()
	{
		return checkSafe;
	}

	/**
	 * Sets this board's Player 1 to a new player.
	 * 
	 * pre: player The new player.
	 */
	public void setPlayer1(Player player)
	{
		player1 = player;
	}

	/**
	 * Sets this board's Player 2 to a new player.
	 * 
	 * pre: player The new player.
	 */
	public void setPlayer2(Player player)
	{
		player2 = player;
	}

	/**
	 * Returns this board's player 1.
	 * 
	 * post: The player 1 this board has
	 */
	public Player getPlayer1()
	{
		return player1;
	}

	/**
	 * Returns this board's player 2.
	 * 
	 * post: The player 2 this board has
	 */
	public Player getPlayer2()
	{
		return player2;
	}

	/**
	 * Checks if there is a forced draw
	 * 
	 * pre: team The team to check for
	 * post: Whether it is a forced draw
	 */
	public boolean isDraw(Team team)
	{
		return isStalemate(team) || numIdenticalPositions(team) >= 5 || turnLimit75(team);
	}

	/**
	 * Checks whether a player can move any pieces
	 * 
	 * pre: team The team of the player
	 * post: Whether it is a stalemate
	 */
	private boolean isStalemate(Team team)
	{
		// Can't be stalemate when king is in check
		if (kingInCheck(team))
		{
			return false;
		}

		// Loop over each piece
		for (Piece[] row : arrangement)
		{
			for (Piece piece : row)
			{
				// Check whether the piece can move anywhere
				if (piece.getTeam() == team && piece.getValidMoves().length != 0)
				{
					// At least one piece can move, so not stalemate
					return false;
				}
			}
		}

		// No pieces can move
		return true;
	}

	/**
	 * Checks to see whether a team can offer a draw to the other
	 * 
	 * pre: team The team to offer the draw
	 * post: Whether it is legal to offer a draw
	 */
	public boolean canOfferDraw(Team team)
	{
		return insufficientMaterial() || numIdenticalPositions(team) >= 3 || turnLimit50(team);
	}

	/**
	 * Checks to see if it has been 50 turns since a pawn has moved or a piece has been captured
	 * 
	 * pre: team The team to check for
	 * post: Whether the 50 turn rule has passed
	 */
	private boolean turnLimit50(Team team)
	{
		if (team == Team.WHITE)
		{
			return movesSincePawnOrCapture == 101;
		} else if (team == Team.BLACK)
		{
			return movesSincePawnOrCapture == 100;
		} else
		{
			return false;
		}
	}

	/**
	 * Checks to see if it has been 75 turns since a pawn has moved or a piece has been captured
	 * 
	 * pre: team The team to check for
	 * post: Whether the 75 turn rule has passed
	 */
	private boolean turnLimit75(Team team)
	{
		if (team == Team.WHITE)
		{
			return movesSincePawnOrCapture == 151;
		} else if (team == Team.BLACK)
		{
			return movesSincePawnOrCapture == 150;
		} else
		{
			return false;
		}
	}

	/**
	 * Gets the number of times that the board has been in its current state
	 * 
	 * pre: team The team to check the state of
	 * post: The number of identical configurations
	 */
	private int numIdenticalPositions(Team team)
	{
		if (team == Team.WHITE)
		{
			return findLargestEqual(whiteSetup);
		} else if (team == Team.BLACK)
		{
			return findLargestEqual(blackSetup);
		} else
		{
			return 0;
		}
	}

	/**
	 * Checks whether there is insufficient material to lead to checkmate
	 * 
	 * post: Whether checkmate is possible
	 */
	private boolean insufficientMaterial()
	{
		// Separate pieces into black and white
		List<Piece> whitePieces = new ArrayList<>();
		List<Piece> blackPieces = new ArrayList<>();

		// Add pieces to lists for each type
		for (Piece[] row : arrangement)
		{
			for (Piece piece : row)
			{
				if (piece.getTeam() == Team.WHITE)
				{
					whitePieces.add(piece);
				} else if (piece.getTeam() == Team.BLACK)
				{
					blackPieces.add(piece);
				}
			}
		}

		// Check whether any insufficient piece configurations exist
		if (whitePieces.size() == 1 && getPieceForType(whitePieces, PieceType.KING) != null && blackPieces.size() == 1
				&& getPieceForType(blackPieces, PieceType.KING) != null)
		{
			return true;
		} else if (whitePieces.size() == 2 && getPieceForType(whitePieces, PieceType.KING) != null
				&& getPieceForType(whitePieces, PieceType.BISHOP) != null && blackPieces.size() == 1
				&& getPieceForType(blackPieces, PieceType.KING) != null)
		{
			return true;
		} else if (whitePieces.size() == 1 && getPieceForType(blackPieces, PieceType.KING) != null
				&& blackPieces.size() == 2 && getPieceForType(whitePieces, PieceType.KING) != null
				&& getPieceForType(whitePieces, PieceType.BISHOP) != null)
		{
			return true;
		} else if (whitePieces.size() == 2 && getPieceForType(whitePieces, PieceType.KING) != null
				&& getPieceForType(whitePieces, PieceType.KNIGHT) != null && blackPieces.size() == 1
				&& getPieceForType(blackPieces, PieceType.KING) != null)
		{
			return true;
		} else if (whitePieces.size() == 1 && getPieceForType(blackPieces, PieceType.KING) != null
				&& blackPieces.size() == 2 && getPieceForType(whitePieces, PieceType.KING) != null
				&& getPieceForType(whitePieces, PieceType.KNIGHT) != null)
		{
			return true;
		} else if (whitePieces.size() == 2 && getPieceForType(whitePieces, PieceType.KING) != null
				&& getPieceForType(whitePieces, PieceType.BISHOP) != null && blackPieces.size() == 2
				&& getPieceForType(blackPieces, PieceType.KING) != null
				&& getPieceForType(blackPieces, PieceType.BISHOP) != null
				&& isBlackSquare(getPieceForType(whitePieces, PieceType.BISHOP).getCoords()) == isBlackSquare(
						getPieceForType(whitePieces, PieceType.BISHOP).getCoords()))
		{
			return true;
		} else
		{
			// Pieces can cause checkmate
			return false;
		}
	}

	/**
	 * Checks if the square at a set of coordinates is black
	 * 
	 * pre: coords The coordinates
	 * post: Whether the square is black or not
	 */
	private boolean isBlackSquare(Coordinates coords)
	{
		return (coords.getX() % 2 == 0 && coords.getY() % 2 == 0) || (coords.getX() % 2 != 0 && coords.getY() % 2 != 0);
	}

	/**
	 * Gets the first piece found for a given type of pieces
	 * 
	 * pre: pieces The pieces to search through
	 * pre: type The type of piece to search through
	 * post: The first occurrence of the piece type
	 */
	private Piece getPieceForType(List<Piece> pieces, PieceType type)
	{
		// Loop over each piece
		for (int i = 0; i < pieces.size(); i++)
		{
			Piece piece = pieces.get(i);
			// Check if piece is same type
			if (piece.getPieceType() == type)
			{
				return piece;
			}
		}

		// No piece of type found
		return null;
	}

	/**
	 * Finds number of identical board configurations to the current one
	 * 
	 * pre: pieces The different board configurations
	 * post: The number of equal configurations (including itself)
	 */
	private int findLargestEqual(List<Piece[][]> pieces)
	{
		if (pieces.size() == 0)
		{
			return 0;
		}

		// Hold the number of equal pieces
		int numEqual = 0;

		// Get the latest configuration
		Piece[][] current = pieces.get(pieces.size() - 1);

		// Check each configuration to see if it's the same as the latest
		for (int i = 0; i < pieces.size(); i++)
		{
			if (isEqual(current, pieces.get(i)))
			{
				// Increment the number of repetitions
				numEqual++;
			}
		}

		// Return the number of repetitions
		return numEqual;
	}

	/**
	 * Checks whether to board configurations are equal
	 * 
	 * pre: arrangement1 The first board configuration
	 * pre: arrangement2 The second board configuration
	 * post: Whether they are the same
	 */
	private boolean isEqual(Piece[][] arrangement1, Piece[][] arrangement2)
	{
		// Loop over each spot on board
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				// Check if the values are the same
				if (arrangement1[i][j] != arrangement2[i][j])
				{
					return false;
				}
			}
		}

		// All pieces are the same
		return true;
	}
}
