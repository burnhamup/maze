package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class Stone extends Piece {

	public Stone(Color c) {
		super(c);
	}

	@Override
	public Set<Position> getValidMoves(Board board) {
		//TODO fix Stone. It appears to be able to move diagonally as long as either space is free.
		Set<Position> result = new HashSet<>();
		if (isDead()) {
			return result;
		}
		if (board.isPositionEmpty(new Position(position.row-1,position.col))) {
			if (board.isPositionEmpty(new Position(position.row-1, position.col-1))) {
				result.add(new Position(position.row-1, position.col-1));
			}
			if (board.isPositionEmpty(new Position(position.row-1, position.col+1))) {
				result.add(new Position(position.row-1, position.col+1));
			}
		}
		if (board.isPositionEmpty(new Position(position.row+1,position.col))) {
			if (board.isPositionEmpty(new Position(position.row+1, position.col-1))) {
				result.add(new Position(position.row+1, position.col-1));
			}
			if (board.isPositionEmpty(new Position(position.row+1, position.col+1))) {
				result.add(new Position(position.row+1, position.col+1));
			}
		}
		return result;
	}
	
	public String toString() {
		return "S";
	}

}
