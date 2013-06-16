package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class Lightning extends Piece {

	public Lightning(Color c) {
		super(c);
	}

	@Override
	public Set<Position> getValidMoves(Board board) {
		Set<Position> result = new HashSet<>();
		if (isDead()) {
			return result;
		}
		result.add(new Position(position.row-1,position.col-1));
		result.add(new Position(position.row-1,position.col+1));
		result.add(new Position(position.row+1,position.col-1));
		result.add(new Position(position.row+1,position.col+1));
        Iterator<Position> iterator = result.iterator();
        while (iterator.hasNext()) {
            if (!board.isPositionEmpty(iterator.next())) {
                iterator.remove();
            }
        }
		return result;	
	}
	
	public String toString() {
		return "L";
	}

}
