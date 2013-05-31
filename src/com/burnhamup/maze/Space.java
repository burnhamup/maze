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

}
