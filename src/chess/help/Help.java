package chess.help;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import chess.piece.PieceType;

/**
 * Class used for help about various topics; pieces, game rules, etc. Can also be used in any UI implementation.
 *
 */
public class Help
{
	/**
	 * Handles which doc to go to for the UI implementation.
	 */
	public void helpPrompt()
	{
	}

	/**
	 * Holds piece type and documentation for each piece
	 */
	public static final Map<PieceType, String> HELP_MAP;
	static
	{
		HashMap<PieceType, String> helpHashMap = new HashMap<>();
    
		// TODO Actual descriptions for pieces
		helpHashMap.put(PieceType.BISHOP,
				"Bishops can move to any tile in any diagonal direction, as long as nothing is blocking their way.");
		
		helpHashMap.put(PieceType.KING,
				"The king can move one space in any direction, as long as it does not put them in check.");
		
		helpHashMap.put(PieceType.KNIGHT,
				"Knights move in a Knights pattern, which is:\n"
						+ "Forward, downwards, left or right 2 squares, THEN:\n"
						+ "If Knight moved vertically, they must move 1 square right/left\n"
						+ "If Knight moved horizontally, they must move 1 square up/down");
		
		helpHashMap.put(PieceType.PAWN,
				"Pawns can move one tile forward, except:\n" + "On their first turn they can move two tiles forward.\n"
						+ "Once the Pawn reaches the end of the board, they can be traded for any piece (except King)");
		
		helpHashMap.put(PieceType.ROOK,
				"Rook can move 1 to 7 squares horizontally and vertically, as long as nothing is blocking their way.");
		
		helpHashMap.put(PieceType.QUEEN,
				"Queen can move any direction horizontally, vertically, or diagonally as long as nothing is blocking their way.\n"
						+ "In addition, Queens can capture other any piece of the opponent.");
		
		HELP_MAP = Collections.unmodifiableMap(helpHashMap);
	}

	/**
	 * The rules of chess
	 */
	public static final String GAME_RULES = "Chess is a two-player-game that takes place on an 8 by 8\n"
			+ "tile board. Each player starts off with a set of pieces of varying power and movement types, as well as a king.\n"
			+ "In order to win the game of chess, the player must 'checkmate' the opponent's king by putting it in a place\n"
			+ "of capture that is unavoidable. In addition, players can call to draw or forfeit to end the game.";
	
	/**
	 * The rules of chess
	 */
	public static final String CASTLING = "Castling is a well-known Chess tactic. It is a move performed by the king\n"
			+ "which involves moving it alongside the Rook ";
}
