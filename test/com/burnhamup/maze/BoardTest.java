package com.burnhamup.maze;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testBoard() {
		Board board = new Board();
		
		Set<Integer> blankspot = new TreeSet<>(Arrays.asList(0,1,8,9,10,19,40,49,50,51,58,59));
		int current = -1;
		for (int row = 0; row<Board.rows;row++) {
			for (int col = 0; col<Board.cols;col++) {
				current = row*Board.rows + col;
				if (blankspot.contains(current)){
					assertNull(board.board[row][col]);
				} else {
					Space space = board.board[row][col];
					Color color = Color.WHITE;
					if (current % 2 == 0) {
						color = Color.BLACK;
					}
					assertEquals(new Position(row,col), space.getPosition());
					assertNull(space.getOccupyingPiece());
					assertEquals(color, space.getColor());
					if (col ==3 || col == 6) {
						assertTrue(space.isDesert());
					} else {
						assertFalse(space.isDesert());
					}
				}
				
			}
		}
		
	}

}
