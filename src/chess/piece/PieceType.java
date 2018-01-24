package chess.piece;

import chess.board.Board;
import chess.board.Coordinates;
import chess.piece.pieces.Bishop;
import chess.piece.pieces.King;
import chess.piece.pieces.Knight;
import chess.piece.pieces.Pawn;
import chess.piece.pieces.Queen;
import chess.piece.pieces.Rook;
import chess.player.Player;

/**
 * The type of chess piece
 */
public enum PieceType
{

	/**
	 * A piece that represents a rook
	 */
	ROOK(new PieceFactory()
	{

		@Override
		public Piece makePiece(Team team)
		{
			return new Rook(ROOK, team, null);
		}

	}),

	/**
	 * A piece that represents a knight
	 */
	KNIGHT(new PieceFactory()
	{

		@Override
		public Piece makePiece(Team team)
		{
			return new Knight(KNIGHT, team, null);
		}

	}),

	/**
	 * A piece that represents a bishop
	 */
	BISHOP(new PieceFactory()
	{

		@Override
		public Piece makePiece(Team team)
		{
			return new Bishop(BISHOP, team, null);
		}

	}),

	/**
	 * A piece that represents a queen
	 */
	QUEEN(new PieceFactory()
	{

		@Override
		public Piece makePiece(Team team)
		{
			return new Queen(QUEEN, team, null);
		}

	}),

	/**
	 * A piece that represents a king
	 */
	KING(new PieceFactory()
	{

		@Override
		public Piece makePiece(Team team)
		{
			return new King(KING, team, null);
		}

	}),

	/**
	 * A piece that represents a pawn
	 */
	PAWN(new PieceFactory()
	{

		@Override
		public Piece makePiece(Team team)
		{
			return new Pawn(PAWN, team, null);
		}

	}),

	/**
	 * Indicates that there is no piece
	 */
	EMPTY(new PieceFactory()
	{

		@Override
		public Piece makePiece(Team team)
		{
			return EMPTY_PIECE;
		}

	});

	/**
	 * Indicates that there is no piece
	 */
	// Black and white are same piece
	public static final Piece EMPTY_PIECE = new Piece(PieceType.EMPTY, Team.NONE, null)
	{
		@Override
		public void lateInit(Coordinates coords, Board board, Player player)
		{
			// Not tied to any specific board
		}

		@Override
		public boolean canMove(Coordinates newCoords)
		{
			return false;
		}

		@Override
		protected void doMove(Coordinates newCoords)
		{
		}
	};

	/**
	 * The factory to make pieces with
	 */
	private final PieceFactory factory;

	/**
	 * Creates a type of piece
	 * 
	 * pre: factory The factory to make the piece with
	 */
	private PieceType(PieceFactory factory)
	{
		this.factory = factory;
	}

	/**
	 * Creates a new black piece
	 * 
	 * post: The new piece
	 */
	public Piece black()
	{
		return factory.makePiece(Team.BLACK);
	}

	/**
	 * Creates a new white piece
	 * 
	 * post: The new piece
	 */
	public Piece white()
	{
		return factory.makePiece(Team.WHITE);
	}

	@Override
	public String toString()
	{
		String name = name();
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

		return name;
	}

	/**
	 * Factory that can make new pieces for a certain team
	 */
	private static interface PieceFactory
	{
		/**
		 * Makes a new piece for a given team
		 * 
		 * pre: team The team to make the piece for
		 * post: The new piece
		 */
		public Piece makePiece(Team team);
	}
}
