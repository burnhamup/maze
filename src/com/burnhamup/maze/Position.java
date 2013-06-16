package com.burnhamup.maze;

public class Position implements Cloneable {

	public int row;
	public int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Position(int spaceNumber) {
		if (spaceNumber  == 1 || spaceNumber == 2 ) {
			this.row = spaceNumber + 1;
			this.col = 0;
		}
		else if (spaceNumber > 2 && spaceNumber <= 6) {
			this.row = spaceNumber-2;
			this.col = 1;
		}
		else if (spaceNumber > 6 && spaceNumber <= 42) {
			this.row = (spaceNumber -1) %6;
			this.col = (spaceNumber-1) / 6 + 1;
		}
		else if (spaceNumber > 42 && spaceNumber <= 46) {
			spaceNumber -= 42;
			this.row = spaceNumber;
			this.col = 8;
		}
		else if (spaceNumber == 47 || spaceNumber == 48) {
			spaceNumber -= 46;
			this.row  = spaceNumber + 1;
			this.col = 9;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public int getSpaceNumber() {
		if (col == 0) {
			return row -1;
		} 
		else if (col == 1) {
			return row +2;
		}
		else if (col > 1 && col < 8) {
			return 1 + row + (col-1) *6;
		} else if (col == 8) {
			return 42 + row;
		} else if (col == 9) {
			return 45 + row;
		} else {
			throw new IllegalStateException("Somehow this position is invalid.");
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public String toString() {
		return "" + getSpaceNumber();
	}

	public Position clone() {
		return new Position(row, col);
	}
}
