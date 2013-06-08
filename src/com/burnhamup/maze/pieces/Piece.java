package com.burnhamup.maze.pieces;

import java.util.Set;

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
	public abstract Set<Position> getValidMoves(Board board);
	
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
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		if (isDead != other.isDead)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

        @Override
        public int hashCode() {
            int result = 1;
            //Color
            if (color == Color.WHITE) {
                result+=1;
            }
            if (isDead) {
                result+=1;
            }
            if (position != null) {
                result *= position.hashCode();
            }
            return result;
        }
	
	
	
}
