package com.burnhamup.maze;

import com.burnhamup.maze.pieces.Piece;

public class Space {
	
	private Color color;
	private boolean isDesert;
	private Position position;
	private Piece occupyingPiece;
	
	public Space(Position position, Color color, boolean isDesert) {
		this.position = position;
		this.occupyingPiece = null;
		this.isDesert = isDesert;
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isDesert() {
		return isDesert;
	}
	public void setDesert(boolean isDesert) {
		this.isDesert = isDesert;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Piece getOccupyingPiece() {
		return occupyingPiece;
	}
	public void setOccupyingPiece(Piece occupyingPiece) {
		this.occupyingPiece = occupyingPiece;
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
		if (!(obj instanceof Space))
			return false;
		Space other = (Space) obj;
		if (color != other.color)
			return false;
		if (isDesert != other.isDesert)
			return false;
		if (occupyingPiece == null) {
			if (other.occupyingPiece != null)
				return false;
		} else if (!occupyingPiece.equals(other.occupyingPiece))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	


}
