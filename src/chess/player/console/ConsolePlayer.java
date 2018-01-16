package chess.player.console;

import java.util.Scanner;

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
	
	public Coordinates getCoordinates () {
		Scanner scanner = console.getInput();
		char column;
		int row;

		System.out.println("what number part do you want (ex a-h)");
		column = console.stringToLength("ya dun did it now: ", 1).charAt(0);
		System.out.println("what number part do you want (ex 1-8)");
		row = console.getUserInteger("i wish my son played football: ", 1, 8);
		
		return new Coordinates(row, column);
		
	}
	
	@Override
	public Coordinates selectPiece()
	{
		System.out.println("what piece you want");
		return getCoordinates();
	}

	@Override
	public Coordinates selectDestination()
	{
		System.out.println("where you wanna go");
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
