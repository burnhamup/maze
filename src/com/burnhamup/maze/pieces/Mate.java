package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class Mate extends Piece {

	// public Mate(Position p, Color c) {
	// super(p, c);
	// }

	public Mate(Color c) {
		super(c);
	}

	@Override
	public Set<Position> getValidMoves(Board board) {
		// Go diagonally in all directions.
		Set<Position> list = new HashSet<Position>();
		if (isDead()) {
			return list;
		}
		Position checkPosition = this.position.clone();

		checkPosition.col -= 1;
		checkPosition.row -= 1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col -= 1;
			checkPosition.row -= 1;
		}

		checkPosition = this.position.clone();
		checkPosition.col -= 1;
		checkPosition.row += 1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col -= 1;
			checkPosition.row += 1;
		}

		checkPosition = this.position.clone();
		checkPosition.col += 1;
		checkPosition.row += 1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col += 1;
			checkPosition.row += 1;
		}

		checkPosition = this.position.clone();
		checkPosition.col += 1;
		checkPosition.row -= 1;
		while (board.isPositionEmpty(checkPosition)) {
			list.add(checkPosition.clone());
			checkPosition.col += 1;
			checkPosition.row -= 1;
		}
		return list;
	}
	
	public String toString() {
		return "M";
	}

}
