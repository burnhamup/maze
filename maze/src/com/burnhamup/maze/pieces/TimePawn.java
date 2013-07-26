package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class TimePawn extends Piece {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 455895558523263389L;
	protected int numberOfMoves;

	public TimePawn(Color c, int numberOfMoves) {
		super(c);
		if (numberOfMoves <= 0 || numberOfMoves > 3) {
			throw new IllegalArgumentException("TimePawn must be 1, 2 or 3");
		}
		this.numberOfMoves = numberOfMoves;
		
	}

	@Override
	public Set<Position> getValidMoves(Board board) {
		Set<Position> result = new HashSet<Position>();
		if (isDead) {
			return result;
		}
		if (board.isDrubenVariation()) {
			return getValidDrubenMoves(board);
		}
		//Check above.
		int currentNumberOfMoves = numberOfMoves;
		Position above = position.clone();
		above.row -= numberOfMoves;
		Position current = above.clone();
		while (board.isPositionEmpty(current) && currentNumberOfMoves > 0) {
			currentNumberOfMoves--;
			current.row+=1;
		}
		if (currentNumberOfMoves == 0) {
			result.add(above);
		}
		//Check below
		currentNumberOfMoves = numberOfMoves;
		Position below = position.clone();
		below.row+= numberOfMoves;
		current = below.clone();
		while(board.isPositionEmpty(current) && currentNumberOfMoves > 0) {
			currentNumberOfMoves--;
			current.row-=1;
		}
		if (currentNumberOfMoves == 0) {
			result.add(below);
		}
		//Check side
		currentNumberOfMoves = numberOfMoves;
		Position side = position.clone();
		if (getColor() == Color.WHITE) {
			side.col += numberOfMoves;
		} else {
			side.col -= numberOfMoves;
		}
		current = side.clone();
		while(board.isPositionEmpty(current) && currentNumberOfMoves > 0) {
			currentNumberOfMoves--;
			if (getColor() == Color.WHITE){
				current.col-=1;
			} else {
				current.col+=1;
			}
		}
		if (currentNumberOfMoves == 0) {
			result.add(side);
		}
		return result;
	}

	private Set<Position> getValidDrubenMoves(Board board) {
		Set<Position> result = new HashSet<Position>();
		//Check above
		Position current = position.clone();
		int currentNumberOfMoves = numberOfMoves;
		current.row--;
		while (board.isPositionEmpty(current) && currentNumberOfMoves > 0) {
			result.add(current.clone());
			current.row--;
			currentNumberOfMoves--;
		}
		//Check Below
		current = position.clone();
		current.row++;
		currentNumberOfMoves = numberOfMoves;
		while (board.isPositionEmpty(current) &&currentNumberOfMoves > 0) {
			result.add(current.clone());
			current.row++;
			currentNumberOfMoves--;
		}
		//Check Side
		current = position.clone();
		if (color == Color.BLACK) {
			current.col--;
		} else {
			current.col++;
		}
		currentNumberOfMoves = numberOfMoves;
		while (board.isPositionEmpty(current) && currentNumberOfMoves > 0 ) {
			result.add(current.clone());
			if (color == Color.BLACK) {
				current.col--;
			} else {
				current.col++;
			}
			currentNumberOfMoves--;
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
		result = prime * result + numberOfMoves;
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
		TimePawn other = (TimePawn) obj;
		if (numberOfMoves != other.numberOfMoves)
			return false;
		return true;
	}
	
	public String toString() {
		return "" + numberOfMoves;
	}
	
	public PieceType getPieceType() {
		switch (numberOfMoves) {
		case 1:
			return PieceType.Pawn1;
		case 2:
			return PieceType.Pawn2;
		case 3:
			return PieceType.Pawn3;
		}
		return null;
	}

}
