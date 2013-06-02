package com.burnhamup.maze.pieces;

import java.util.List;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public abstract class Piece {
	
	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	protected Position position;
	protected Color color;
	protected boolean isDead;
	
//	public Piece(Position p, Color c) {
//		position = p;
//		color = c;
//		isDead =false;
//	}
	
	public Piece(Color c) {
		position = null;
		color = c;
		isDead = false;
	}
	
	/**
	 * Returns a list of moves that this piece can move to. 
	 * @return
	 */
	public abstract List<Position> getValidMoves(Board board);
	
	/**
	 * Moves this piece to the new position
	 * @param p
	 */
	public void movePiece(Position p) {
		this.position = p;
	}
	
	public void kill() {
		isDead = true;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass()) {
			Piece other = (Piece) obj;
			if (other.position.equals(this.position) &&
					other.color == this.color &&
					other.isDead == this.isDead) {
				return true;
			}
		}
		return false;
	}
	
	
}
