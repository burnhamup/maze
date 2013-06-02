package com.burnhamup.maze;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.burnhamup.maze.pieces.Mate;
import com.burnhamup.maze.pieces.Piece;

public class BoardTest {

	@Test
	public void testBoardCreation() {
		Board board = new Board();
		
		Set<Integer> blankspot = new TreeSet<>(Arrays.asList(0,1,8,9,10,19,40,49,50,51,58,59));
		int current = -1;
		for (int row = 0; row<Board.rows;row++) {
			for (int col = 0; col<Board.cols;col++) {
				current = row*Board.cols + col;
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
	
	@Test
	public void testIsValidPositionFalse() {
		Board board = new Board();
		board.board[0][0] = null;
		assertFalse(board.isValidPosition(new Position(0,0)));
	}
	
	@Test
	public void testIsValidPositionTrue() {
		Board board = new Board();
		board.board[0][0] = new Space(new Position(0,0), Color.BLACK, false);
		assertTrue(board.isValidPosition(new Position(0,0)));
	}
	
	@Test
	public void testIsSpaceReturnsNull() {
		Board board = new Board();
		board.board[0][0] = null;
		assertNull(board.getSpace(new Position(0,0)));
	}
	
	@Test
	public void testIsSpaceReturnsCorrectSpace() {
		Board board = new Board();
		Space space = new Space(new Position(0,0), Color.BLACK, false);
		board.board[0][0] = space;
		assertEquals(space, board.getSpace(new Position(0,0)));
	}
	
	@Test
	public void testIsPositionEmptyTrue() {
		Board board = new Board();
		Space space = new Space(new Position(0,0), Color.BLACK, false);
		board.board[0][0] = space;
		assertTrue(board.isPositionEmpty(new Position(0,0)));
	}
	
	@Test
	public void testIsPositionEmptyFalse() {
		Board board = new Board();
		Space space = new Space(new Position(0,0), Color.BLACK, false);
		board.board[0][0] = space;
		board.addPiece(new Mate(Color.BLACK), new Position(0,0));
		assertFalse(board.isPositionEmpty(new Position(0,0)));
		board.board[0][0] = null;
		assertFalse(board.isPositionEmpty(new Position(0,0)));
	}
	
	@Test
	public void testAddPiece() {
		Board board = new Board();
		assertNull(board.getPiece(new Position(3,3)));
		Piece piece = new Mate(Color.BLACK);
		board.addPiece(piece, new Position(3,3));
		assertEquals(new Position(3,3), board.getPiece(new Position(3,3)).getPosition());
		assertEquals(piece, board.getPiece(new Position(3,3)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddPieceInvalidPositionException() {
		Board board = new Board();
		Piece piece = new Mate(Color.BLACK);
		board.addPiece(piece, new Position(0,0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddPieceOccupiedException() {
		Board board = new Board();
		Piece piece = new Mate(Color.BLACK);
		board.addPiece(piece, new Position(3,3));
		Piece secondPiece = new Mate(Color.WHITE);
		board.addPiece(secondPiece, new Position(3,3));
		
	}
	
	@Test
	public void testMovePiece() {
		Board board = new Board();
		Piece piece = new Mate(Color.BLACK);
		board.addPiece(piece, new Position(3,0));
		board.movePiece(new Position(3,0), new Position(4,1));
		assertTrue(board.isPositionEmpty(new Position(3,0)));
		assertFalse(board.isPositionEmpty(new Position(4,1)));
		
		assertEquals(piece, board.getPiece(new Position(4,1)));
		assertEquals(new Position(4,1), board.getPiece(new Position(4,1)).getPosition());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMovePieceAtEmptyPosition() {
		Board board = new Board();
		board.movePiece(new Position(3,0),  new Position(4,1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMovePieceToOccupiedPositionException(){
		Board board = new Board();
		Piece mate1 = new Mate(Color.BLACK);
		Piece mate2 = new Mate(Color.WHITE);
		board.addPiece(mate1, new Position(3,0));
		board.addPiece(mate2, new Position(4,1));
		board.movePiece(new Position(3,0), new Position(4,1));
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMovePieceToInvalidPositionException() {
		Board board = new Board();
		Piece piece = new Mate(Color.BLACK);
		board.addPiece(piece, new Position(3,0));
		board.movePiece(new Position(3,0), new Position(0,0));
	}
	
	@Test
	public void testGetPieceNull() {
		Board board = new Board();
		assertNull(board.getPiece(new Position(3,0)));
		assertNull(board.getPiece(new Position(0,0)));
	}
	
	@Test
	public void testGetPiece() {
		Board board = new Board();
		Piece piece = new Mate(Color.BLACK);
		board.addPiece(piece, new Position(3,0));
		assertEquals(piece, board.getPiece(new Position(3,0)));
	}
	
	@Test
	public void testKillPiece() {
		Board board = new Board();
		Piece piece = new Mate(Color.BLACK);
		board.addPiece(piece, new Position(3,2));
		board.movePiece(new Position(3,2), new Position(4,3));
		assertTrue(board.getPiece(new Position(4,3)).isDead());
	}
	
	@Test
	public void testIsGameWonFalse() {
		Board board = new Board();
		assertFalse(board.isGameWon());
		board.addPiece(new Mate(Color.BLACK), new Position(3,0));
		assertFalse(board.isGameWon());
	}
	
	@Test
	public void testIsGameWonTrue() {
		Board board = new Board();
		Piece blackmate1 = new Mate(Color.BLACK);
		Piece blackmate2 = new Mate(Color.BLACK);
		Piece whitemate1 = new Mate(Color.WHITE);
		Piece whitemate2 = new Mate(Color.WHITE);
		
		board.addPiece(blackmate1, new Position(2,0));
		board.addPiece(blackmate2, new Position(3,0));
		board.addPiece(whitemate1, new Position(2,9));
		board.addPiece(whitemate2, new Position(3,9));
		
		assertTrue(board.isGameWon());
	}
	

}
