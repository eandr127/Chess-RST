package chess.player.console;

import chess.ConsoleIO;
import chess.board.Board;
import chess.board.Coordinates;
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
	
	@Override
	public Coordinates selectPiece()
	{
		int x = 0, y = 0;
		String choice;
		boolean validCoord = false;
		
		while(validCoord == false)
		{
			console.getConsoleOutput().print("Select a location: ");
			choice = console.getInput().nextLine().toLowerCase();
		
			x = choice.charAt(0);
			y = choice.charAt(1);
			
			if(x > 1 && x < 8 && y > 1 && y < 8)
			{
				validCoord = true;
			}
		}
		
		Coordinates coords = new Coordinates (x, y);
		
		return coords;
	}

	@Override
	public Coordinates selectDestination()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalidMove()
	{
		console.getConsoleOutput().println("Invalid move");
	}

}
