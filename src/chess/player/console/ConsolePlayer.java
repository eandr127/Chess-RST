package chess.player.console;

import chess.ConsoleIO;
import chess.ConsoleIO.Requirements;
import chess.board.Board;
import chess.board.Coordinates;
import chess.help.Help;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;
import chess.piece.pieces.Pawn;
import chess.player.Player;

public class ConsolePlayer extends Player
{

	//Create a console input reference
	private final ConsoleIO console;
	
	/**
	 * Sets up a player for that allows for console input
	 * 
	 * @param team The team of the player being set up
	 * @param board The board for the player being set up
	 * @param console The ConsoleIO for the player being set up
	 */
	public ConsolePlayer(Team team, Board board, ConsoleIO console, Help help, String name)
	{
		super(team, board, help, name);
		
		this.console = console;
	}

	/**
	 * Get the current ConsoleIO for this player.
	 * 
	 * @return The current ConsoleIO for this player
	 */
	public ConsoleIO getConsole() {
		return console;
	}
	
	/**
	 * Get coordinates from user input, checked against CoordinateRequirements.
	 * @return The user's inputed coordinates.
	 */
	public Coordinates getCoordinates () {
		CoordinateRequirements coordinateRequirements = new CoordinateRequirements();
		String in = console.getStringFromUser(coordinateRequirements);
		Coordinates coords = new Coordinates(in.charAt(0), Character.getNumericValue(in.charAt(1)));
		return coords;
	}
	
	/**
	 * An implementation of Requirements that checks for a character length of two,
	 * a letter in the first character space that is less than "i", and a number
	 * in the second character space that is less than "9".
	 * 
	 */
	private static class CoordinateRequirements implements Requirements {

		@Override
		public boolean valid(String in) throws IllegalArgumentException
		{
			return in.length() == 2 && Character.isAlphabetic(in.charAt(0)) && Character.isDigit(in.charAt(1)) && in.charAt(0) < 'i' && Character.getNumericValue(in.charAt(1)) < 9;
		}

		@Override
		public String message()
		{
			return "Enter the coordinates of the piece: ";
		}
		
		public String invalid() {
			return "Invalid input. Try again!\n";
		}
		
	}
	
	/**
	 * Requirements for getting the type to promot a pawn
	 */
	private static class PawnPromotionRequirements implements Requirements {

		@Override
		public boolean valid(String in) throws IllegalArgumentException
		{
			return in.toLowerCase().equals("q") ||  in.toLowerCase().equals("k") || in.toLowerCase().equals("b") || in.toLowerCase().equals("r")
				|| in.toLowerCase().equals("queen") || in.toLowerCase().equals("knight") || in.toLowerCase().equals("bishop") || in.toLowerCase().equals("rook");
		}

		@Override
		public String message()
		{
			return "Your pawn is being promoted. What would you like to promote it to? You have these options:\nQueen\nKnight\nBishop\nRook\nChoose a promotion option (q/k/b/r): ";
		}
		
		public String invalid() {
			return "Invalid input. Try again!\n";
		}
		
	}
	
	/**
	 * Requirements for choosing what to do at the start of a turn
	 */
	private class TurnInitializationRequirements implements Requirements {

		@Override
		public boolean valid(String in) throws IllegalArgumentException
		{
			if (getBoard().canOfferDraw(getTeam())) {
				return in.toLowerCase().equals("h") ||  in.toLowerCase().equals("p") || in.toLowerCase().equals("r") || in.toLowerCase().equals("d")
					|| in.toLowerCase().equals("help") || in.toLowerCase().equals("play") || in.toLowerCase().equals("resign") || in.toLowerCase().equals("draw");
				
			} else return in.toLowerCase().equals("h") ||  in.toLowerCase().equals("p") || in.toLowerCase().equals("r")
					|| in.toLowerCase().equals("help") || in.toLowerCase().equals("play") || in.toLowerCase().equals("resign");
		}

		@Override
		public String message()
		{
			console.getConsoleOutput().println("\nIt's " + getName() + "'s turn");
			if (getBoard().canOfferDraw(getTeam())) {
				return "What do you want to do?\nGet help\nPlay your turn\nResign\nOffer Draw to other player\n([H]elp/[P]lay/[R]esign/[D]raw):";
			} else return "What do you want to do?\n([H]elp/[P]lay/[R]esign): ";
		}
		
		public String invalid() {
			return "Invalid input. Try again!\n";
		}
		
	}
	
	@Override
	public void pawnPromotion(Pawn pawn) {
		final String promotionRequest = "Your Pawn at location " + String.valueOf((char)(pawn.getCoords().getY() + 64)) + pawn.getCoords().getX() + " can be promoted. Would you like to promote it? (y/n)"; 
		
		System.out.println(promotionRequest);
		if (console.getUserBoolean()) {
			String in = console.getStringFromUser(new PawnPromotionRequirements());
			switch (in.toLowerCase()) {
				case "q":
				case "queen":
					getBoard().replacePieceType(pawn.getCoords(), PieceType.QUEEN);
					break;
				case "k":
				case "knight":
					getBoard().replacePieceType(pawn.getCoords(), PieceType.KNIGHT);
					break;
				case "b":
				case "bishop":
					getBoard().replacePieceType(pawn.getCoords(), PieceType.BISHOP);
					break;
				case "r":
				case "rook":
					getBoard().replacePieceType(pawn.getCoords(), PieceType.ROOK);
					break;
				default:
			}
		}
	}
	
	@Override
	public String turnInit()
	{
		String choice = null;
		
		String in = console.getStringFromUser(new TurnInitializationRequirements());
		switch (in.toLowerCase()) {
			case "h":
			case "help":
				choice = "help";
				break;
			case "p":
			case "play":
				choice = "play";
				break;
			case "r":
			case "resign":
				choice = "resign";
				break;
			case "d":
			case "draw":
				offerDraw();
				break;
		}
		
		return choice;
	}
	
	@Override
	public Coordinates selectPiece()
	{
		Coordinates coords = new Coordinates(0, 0);
		getBoard().showBoard(getTeam());
		do {
			console.getConsoleOutput().println("Select the piece to move");
			coords = (getCoordinates());
		} while (getBoard().getPiece(coords).getTeam() != this.getTeam());
		return coords;
	}

	@Override
	public Coordinates selectDestination(Piece selected)
	{
		getBoard().showBoard(selected);
		console.getConsoleOutput().println("Select the destination of the piece");
		return getCoordinates();
	}

	@Override
	public void invalidMove()
	{
		console.getConsoleOutput().println("Invalid move.");
	}

	@Override
	public boolean offerDraw()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recieveDrawOffer()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean draw()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resign()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
