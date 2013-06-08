package com.burnhamup.maze;

public class Position implements Cloneable {
	
	public int row;
	public int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col= col;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() == this.getClass()) {
			Position other = (Position) obj;
			if (other.row == this.row && other.col == this.col) {
				return true;
			}
		}
		return false; 
	}

        @Override
        public int hashCode() {
            int result = 448;
            result*= row;
            result+= col;
            return result;
        }

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + "]";
	}
	
	public Position clone() {
		return new Position(row,col);
	}
}
