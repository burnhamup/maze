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
		Set<Position> result = new HashSet<Position>();
		if (isDead()) {
			return result;
		}
		Position p;
		Position above = new Position(position.row-1,position.col);		
		Position below = new Position(position.row+1,position.col);
		Position left = new Position(position.row,position.col-1);
		Position right = new Position(position.row-1,position.col+1);
		if (board.isPositionEmpty(above) ||
				board.isPositionEmpty(left)) {
			p = new Position(position.row-1, position.col-1);
			if (board.isPositionEmpty(p)) {
				result.add(p);
			}
		}
		if (board.isPositionEmpty(above) ||
				board.isPositionEmpty(right)) {
			p = new Position(position.row-1, position.col+1);
			if (board.isPositionEmpty(p)) {
				result.add(p);
			}
		}
		if (board.isPositionEmpty(below) ||
				board.isPositionEmpty(left)) {
			p = new Position(position.row+1, position.col-1);
			if (board.isPositionEmpty(p)) {
				result.add(p);
			}
		}
		if (board.isPositionEmpty(below) ||
				board.isPositionEmpty(right)) {
			p = new Position(position.row+1, position.col+1);
			if (board.isPositionEmpty(p)) {
				result.add(p);
			}
		}
		return result;
	}
	
	public String toString() {
		return "S";
	}

}
