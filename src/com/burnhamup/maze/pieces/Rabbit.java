package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class Rabbit extends Piece {

	public Rabbit(Color c) {
		super(c);
	}

	@Override
	public Set<Position> getValidMoves(Board board) {
        Set<Position> result = new HashSet<>();
        if (isDead()) {
        	return result;
        }
        if (!board.isPositionEmpty(new Position(position.row-1,position.col-1)) &&
        	board.isPositionEmpty(new Position(position.row-2,position.col-2))) {
        	result.add(new Position(position.row-2, position.col-2));
        }
        if (!board.isPositionEmpty(new Position(position.row-1,position.col)) &&
            	board.isPositionEmpty(new Position(position.row-2,position.col))) {
            	result.add(new Position(position.row-2, position.col));
        }
        if (!board.isPositionEmpty(new Position(position.row-1,position.col+1)) &&
            	board.isPositionEmpty(new Position(position.row-2,position.col+2))) {
            	result.add(new Position(position.row-2, position.col+2));
        }
        if (!board.isPositionEmpty(new Position(position.row,position.col-1)) &&
            	board.isPositionEmpty(new Position(position.row,position.col-2))) {
            	result.add(new Position(position.row, position.col-2));
        }
        if (!board.isPositionEmpty(new Position(position.row,position.col+1)) &&
            	board.isPositionEmpty(new Position(position.row,position.col+2))) {
            	result.add(new Position(position.row, position.col+2));
        }
        if (!board.isPositionEmpty(new Position(position.row+1,position.col-1)) &&
            	board.isPositionEmpty(new Position(position.row+2,position.col-2))) {
            	result.add(new Position(position.row+2, position.col-2));
        }
        if (!board.isPositionEmpty(new Position(position.row+1,position.col)) &&
            	board.isPositionEmpty(new Position(position.row+2,position.col))) {
            	result.add(new Position(position.row+2, position.col));
        }
        if (!board.isPositionEmpty(new Position(position.row+1,position.col+1)) &&
            	board.isPositionEmpty(new Position(position.row+2,position.col+2))) {
            	result.add(new Position(position.row+2, position.col+2));
        }
		return result;
	}

}
