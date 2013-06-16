package com.burnhamup.maze.views.text;

import java.util.Scanner;

import com.burnhamup.maze.Color;
import com.burnhamup.maze.Game;
import com.burnhamup.maze.Position;

public class Controller {

	public static void main(String[] args) {
		Game game = new Game();
		String whiteString = "1S3T1ST3RLH2R2HL";
		String blackString = "THLR22RS3T3TS1H1";
		game.loadStartingPositions(game.loadSetStartingPosition(whiteString, Color.WHITE), 
									game.loadSetStartingPosition(blackString, Color.BLACK));
		View view = new View(game);
		//Add game logic.
		Scanner scanner = new Scanner(System.in);
		while(game.getBoard().isGameWon() == false) {
			view.print();
			System.out.println("Make your move:");
			Position p = new Position(scanner.nextInt());
			System.out.println("Valid Moves:");
			System.out.println(game.getValidMoves(p));
			game.movePiece(new Position(scanner.nextInt()));
		}
		scanner.close();
		System.out.println("Game OVER!");
	}
}
