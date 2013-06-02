package com.burnhamup.maze;

import com.burnhamup.maze.pieces.Piece;

/**
 * A class representing the board for MAZE
 * 
 * The board looks like:
 * 
 * 
 *   XXXXXX
 *  XXXXXXXX
 * XXXXXXXXXX
 * XXXXXXXXXX
 *  XXXXXXXX
 *   XXXXXX
 * 
 * @author Chris
 *
 */
public class Board {
	protected Space board[][];
	public static final int rows = 6;
	public static final int cols = 10;
	
	
	public Board() {
		board = new Space[rows][cols];
		Color color = null;
		for (int row =0; row<rows;row++) {
			for (int col=0; col<cols;col++) {
				if (row==0 && (col<2 || col >7)) {
					continue;
				}
				if (row ==1 && (col==0 || col==9)) {
					continue;
				}
				if (row == 4 && (col==0 || col==9)) {
					continue;
				}
				if (row == 5 && (col <2 || col > 7)) {
					continue;
				}
				boolean isDesert = false;
				if (col == 3 || col == 6) {
					isDesert = true;
				}
				if ((row*rows +col) %2 == 0) {
					color = Color.BLACK;
				} else {
					color = Color.WHITE;
				}
				board[row][col] = new Space(new Position(row,col), color, isDesert);
			}
		}
	}
	
	/**
	 * Checks to determine if there is a space at that position.
	 * @param position Position to test
	 * @return true if the position is a legal position to place a piece
	 */
	protected boolean isValidPosition(Position position) {
		return getSpace(position) != null;
	}
	
	/**
	 * @param position
	 * @return Returns the Space at the given position 
	 */
	protected Space getSpace(Position position) {
		return board[position.row][position.col];
	}
	
	
	/**
	 * 
	 * @param position
	 * @return true if the position is empty because a piece can be placed there. 
	 */
	public boolean isPositionEmpty(Position position) {
		if (isValidPosition(position)) {
			return getSpace(position).getOccupyingPiece() == null;
		} 
		return false;
	}
	
	/**
	 * Adds the piece to the board at position.
	 * @param piece
	 * @param position
	 */
	public void addPiece(Piece piece, Position position) {
		if (!this.isValidPosition(position)) {
			throw new IllegalArgumentException("No such space");
		}
		if (!this.isPositionEmpty(position)) {
			throw new IllegalArgumentException("Current Position is occupied");
		}
		getSpace(position).setOccupyingPiece(piece);
		piece.setPosition(position);
	}
	
	/**
	 * Moves the piece from the current position to the newPosition
	 * @param current
	 * @param newPosition
	 * @throws Exception
	 */
	public void movePiece(Position current, Position newPosition)  {
		if (!this.isValidPosition(newPosition)) {
			throw new IllegalArgumentException("The new position is not a valid position.");
		}
		if (this.isPositionEmpty(current)) {
			throw new IllegalArgumentException("Current position is empty");
		}
		if (!this.isPositionEmpty(newPosition)) {
			throw new IllegalArgumentException("New position is not empty");
		}
		Piece movingPiece = getPiece(current);
		getSpace(current).setOccupyingPiece(null);
		movingPiece.setPosition(newPosition);
		getSpace(newPosition).setOccupyingPiece(movingPiece);
		
		if (getSpace(newPosition).isDesert()) {
			movingPiece.kill();
		}
		
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public Piece getPiece(Position pos) {
		if (isValidPosition(pos)) {
			return getSpace(pos).getOccupyingPiece();
		}
		return null;
	}
	
	/**
	 * Returns true when the game board is in a winning state
	 * @return
	 */
	public boolean isGameWon() {
		Piece blackMate1 = getPiece(new Position(2,0));
		Piece blackMate2 = getPiece(new Position(3,0));
		Piece whiteMate1 = getPiece(new Position(2,9));
		Piece whiteMate2 = getPiece(new Position(3,9));
		
		if (blackMate1 != null && blackMate1.getColor() == Color.BLACK &&
				blackMate2 != null && blackMate2.getColor() == Color.BLACK &&
				whiteMate1 != null && whiteMate1.getColor() == Color.WHITE &&
				whiteMate2 != null && whiteMate2.getColor() == Color.WHITE) {
			return true;
		}
		
		return false;
	}
	
	
}
