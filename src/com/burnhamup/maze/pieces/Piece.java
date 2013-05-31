package com.burnhamup.maze.pieces;

import java.util.List;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public abstract class Piece {
	
	protected Position position;
	protected Color color;
	protected boolean isDead;
	
	public Piece(Position p, Color c) {
		position = p;
		color = c;
		isDead =false;
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
	public abstract void movePiece(Position p);
	
	public void kill() {
		isDead = true;
	}
	
	
}
