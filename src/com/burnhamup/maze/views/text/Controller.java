package com.burnhamup.maze.views.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.burnhamup.maze.Color;
import com.burnhamup.maze.Game;
import com.burnhamup.maze.Position;

public class Controller {

	public static void main(String[] args) throws FileNotFoundException {
		Game game = new Game();
		String whiteString = "2T2L311SRTLRSH3H";
		String blackString = "R2H1TSTH323LRLS1";
		game.loadStartingPositions(game.loadSetStartingPosition(whiteString, Color.WHITE), 
									game.loadSetStartingPosition(blackString, Color.BLACK));
		View view = new View(game);
		//Add game logic.
		System.setIn(new FileInputStream("input.txt"));
		Scanner scanner = new Scanner(System.in);
		int row;
		int col;
		String next;
		Position p;
		while(game.getBoard().isGameWon() == false) {
			view.print();
			System.out.println("Make your move:");
			next = scanner.nextLine();
			row = Character.getNumericValue(next.charAt(0));
			col = Character.getNumericValue(next.charAt(1));
			p = new Position(row,col);
			System.out.println("Valid Moves:");
			System.out.println(game.getValidMoves(p));
			next = scanner.nextLine();
			row = Character.getNumericValue(next.charAt(0));
			col = Character.getNumericValue(next.charAt(1));
			game.movePiece(new Position(row,col));
			if (game.getBoard().getPiece(new Position(row,col)).isDead()) {
				System.out.println("Dead");
			}
		}
		scanner.close();
		System.out.println("Game OVER!");
	}
}
