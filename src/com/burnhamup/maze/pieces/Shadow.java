package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;


public class Shadow extends Piece {
    
    public Shadow(Color c) {
        super(c);
    }

    public Set<Position> getValidMoves(Board b) {
        Set<Position> result = new HashSet<>();
        if (this.isDead()) {
            return result;
        }
        Set<Mate> mates = b.getMates(getColor());
        for (Mate m: mates) {
        	if (m.getPosition() == null) {
        		continue;
        	}
            int row = m.getPosition().row;
            int col = m.getPosition().col;
            result.add(new Position(row-1,col-1));
            result.add(new Position(row-1,col));
            result.add(new Position(row-1,col+1));
            result.add(new Position(row,col-1));
            result.add(new Position(row,col+1));
            result.add(new Position(row+1,col-1));
            result.add(new Position(row+1,col));
            result.add(new Position(row+1,col+1));
        }
        Iterator<Position> iterator = result.iterator();
        while (iterator.hasNext()) {
            if (!b.isPositionEmpty(iterator.next())) {
                iterator.remove();
            }
        }
        return result;
    }
    
	public String toString() {
		return "H";
	}

}