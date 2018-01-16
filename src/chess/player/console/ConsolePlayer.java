package chess.player.console;

import chess.ConsoleIO;
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
		char column;
		int row;

		System.out.println("Select column (A-H): ");
		column = console.stringToLength("Column must be between A and H: ", 1).charAt(0);
		System.out.println("Select Row (1-8): ");
		row = console.getUserInteger("Row must be between 1 and 8: ", 1, 8);
		
		return new Coordinates(column, row);
		
	}
	
	@Override
	public Coordinates selectPiece()
	{
		getBoard().showBoard(getTeam());
		System.out.println("Select the piece to move");
		return getCoordinates();
	}

	@Override
	public Coordinates selectDestination(Piece selected)
	{
		getBoard().showBoard(selected);
		System.out.println("Select the destination of the piece");
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
