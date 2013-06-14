package com.burnhamup.maze;

public class Position implements Cloneable {

	public int row;
	public int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
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
		return "Position [row=" + row + ", col=" + col + "]";
	}

	public Position clone() {
		return new Position(row, col);
	}
}
