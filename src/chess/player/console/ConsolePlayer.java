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
		// TODO Auto-generated method stub
		return null;
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
