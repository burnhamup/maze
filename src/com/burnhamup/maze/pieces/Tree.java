package com.burnhamup.maze.pieces;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class Tree extends Piece {

	public Tree(Color c) {
		super(c);
	}

	@Override
	public Set<Position> getValidMoves(Board board) {
		if (isDead()) {
			return new HashSet<Position>();
		}
		Set<Position> result = board.getAllColorPieces(getColor());
        Iterator<Position> iterator = result.iterator();
        while (iterator.hasNext()) {
            if (!board.isPositionEmpty(iterator.next())) {
                iterator.remove();
            }
        }
		return result;	
	}

	/* (non-Javadoc)
	 * @see com.burnhamup.maze.pieces.Piece#movePiece(com.burnhamup.maze.Position)
	 */
	@Override
	public void movePiece(Position p) {
		super.movePiece(p);
		kill();
	}
	
	public String toString() {
		return "T";
	}

}
