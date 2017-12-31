package chess.help;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import chess.piece.PieceType;

/**
 * Class used for help about various topics; pieces, game rules, etc.
 * Can also be used in any UI implementation.
 * 
 * @author Caelan
 *
 */
public class Help {
	
	/*
	 * Handles which doc to go to for the UI
	 * implementation.
	 */
	public void helpPrompt () {
		//TODO ui implementation of docs selector
	}
	
	public static final Map<PieceType, String> helpMap;
	static {
		HashMap<PieceType, String> helpHashMap = new HashMap<>();
		//TODO Actual descriptions for pieces
		helpHashMap.put(PieceType.BISHOP, "you leanred bout bishop!!!");
		helpHashMap.put(PieceType.KING, "you leanred bout king!!!");
		helpHashMap.put(PieceType.KNIGHT, "you leanred bout knight!!");
		helpHashMap.put(PieceType.PAWN, "you leanred bout PAWN!!!");
		helpHashMap.put(PieceType.ROOK, "you leanred bout rppl!!!");
		helpHashMap.put(PieceType.QUEEN, "you leanred bout qeiem!!!");
		helpMap = Collections.unmodifiableMap(helpHashMap);
	}
	
	public static String gamerules () {
		//TODO actual rules
		return "thos are som good RULES";
	}
}
