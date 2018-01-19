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
		String in = console.getStringFromUser(new CoordinateRequirements());
		return new Coordinates(in.charAt(0), Character.getNumericValue(in.charAt(1)));
		
	}
	
	private static class CoordinateRequirements implements Requirements {

		@Override
		public boolean valid(String in) throws IllegalArgumentException
		{
			return in.length() == 2 && Character.isAlphabetic(in.charAt(0)) && Character.isDigit(in.charAt(1));
		}

		@Override
		public String message()
		{
			return "Enter the coordinates of the piece: ";
		}
		
		@Override
		public String abortMessage() 
		{
			return "cancel";
		}
		
	}
	
	@Override
	public Coordinates selectPiece()
	{
		getBoard().showBoard(getTeam());
		console.getConsoleOutput().println("Select the piece to move");
		return getCoordinates();
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
		console.getConsoleOutput().println("Invalid move");
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
