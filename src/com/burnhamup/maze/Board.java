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
	public static final int rows = 10;
	public static final int cols = 6;
	
	
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
	
	public boolean isValidPosition(Position position) {
		return board[position.row][position.col] == null;
	}
	
	public boolean isPositionEmpty(Position position) {
		if (isValidPosition(position)) {
			return board[position.row][position.col].getOccupyingPiece() == null;
		}
		return false;
	}
	
	public void addPiece(Piece piece, Position position) {
		
	}
	
	public void movePiece(Position current, Position newPosition) {
		
	}
	
	public Piece getPiece(Position pos) {
		return null;
	}
	
	public boolean isGameOver() {
		return false;
	}
	
	
}
