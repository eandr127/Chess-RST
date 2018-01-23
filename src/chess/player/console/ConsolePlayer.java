package chess.player.console;

import chess.ConsoleIO;
import chess.ConsoleIO.Requirements;
import chess.board.Board;
import chess.board.Coordinates;
import chess.piece.Piece;
import chess.piece.Team;
import chess.player.Player;

public class ConsolePlayer extends Player
{

	private final ConsoleIO console;
	
	public ConsolePlayer(Team team, Board board, ConsoleIO console)
	{
		super(team, board);
		
		this.console = console;
	}

	public ConsoleIO getConsole() {
		return console;
	}
	
	public Coordinates getCoordinates () {
		CoordinateRequirements coordinateRequirements = new CoordinateRequirements();
		String in = console.getStringFromUser(coordinateRequirements);
		Coordinates coords = new Coordinates(in.charAt(0), Character.getNumericValue(in.charAt(1)));
		return coords;
	}
	
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

}
