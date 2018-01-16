package chess.help;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import chess.piece.PieceType;

/**
 * Class used for help about various topics; pieces, game rules, etc. Can also be used in any UI implementation.
 * 
 * @author Caelan
 *
 */
public abstract class Help
{
	/**
	 * Handles which doc to go to for the UI implementation.
	 */
	public abstract void helpPrompt();

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
	public static final String GAME_RULES = "Chess is a two-player board game played on a chessboard, a checkered game board with 64 squares \n"
			+ "arranged in an eight-by-eight grid.\n" + "\n"
			+ "Each player begins the game with 16 pieces: one king, one queen, \n"
			+ "two rooks, two knights, two bishops, and eight pawns. Each of the \n"
			+ "six piece types moves differently. The most powerful piece is the\n"
			+ "queen and the least powerful piece is the pawn. The objective is \n"
			+ "to 'checkmate' the opponent's king by placing it under an \n"
			+ "inescapable threat of capture. To this end, a player's pieces \n"
			+ "are used to attack and capture the opponent's pieces, while \n"
			+ "supporting their own. In addition to checkmate, the game can \n"
			+ "be won by voluntary resignation by the opponent, which typically \n"
			+ "occurs when too much material is lost, or if checkmate appears \n" + "unavoidable.\n";
}
