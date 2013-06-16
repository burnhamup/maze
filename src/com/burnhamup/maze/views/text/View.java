package com.burnhamup.maze.views.text;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Game;
import com.burnhamup.maze.Position;

public class View {
	private Game game;

	public View(Game game) {
		this.game = game;
	}

	public void print() {
		Board board = game.getBoard();
		for (int row = 0; row < Board.rows; row++) {
			String top = "";
			String label = "";
			String number = "";
			String bottom = "";
			for (int col = 0; col<Board.cols; col++) {
				Position p = new Position(row,col);
				if (board.getSpace(p) == null) {
					top += "     ";
					label += "     ";
					number += "     ";
					bottom += "     ";
				} else {
					top += "-----";
					bottom+= "-----";
					if (board.getPiece(p) == null) {
						label += "|   |";
					} else {
						label += "| " + board.getPiece(p) + " |";
					}
					number += "| " + String.format("%02d", p.getSpaceNumber())  + "|";
				}
			}
			System.out.println(top);
			System.out.println(label);
			System.out.println(number);
			System.out.println(bottom);
		}
	}
	
	
}
