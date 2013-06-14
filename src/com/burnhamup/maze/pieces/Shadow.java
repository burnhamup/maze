package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;


public class Shadow extends Piece {

    private Set<Mate> mates;
    
    public Shadow(Color c, Set<Mate> mates) {
        super(c);
        for (Mate m : mates) {
            if (m.getColor() != this.color) {
                throw new IllegalArgumentException("The mates in the mate list must be the same color as the shadow");
            }
        }       
        this.mates = mates;
    }

    public Set<Position> getValidMoves(Board b) {
        Set<Position> result = new HashSet<>();
        if (this.isDead()) {
            return result;
        }
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mates == null) ? 0 : mates.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shadow other = (Shadow) obj;
		if (mates == null) {
			if (other.mates != null)
				return false;
		} else if (!mates.equals(other.mates))
			return false;
		return true;
	}

}