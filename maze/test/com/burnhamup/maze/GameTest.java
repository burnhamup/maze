package com.burnhamup.maze;

import static org.junit.Assert.*;

import org.junit.Test;

import com.burnhamup.maze.pieces.Piece;

public class GameTest {

	@Test
	public final void testGame() {
		Game g = new Game();
		Board b = g.getBoard();
		Piece p = b.getPiece(new Position(1));
		
		assertTrue(p.getPieceType() == Piece.PieceType.Mate);
		assertTrue(p.getColor() == Color.WHITE);
		p = b.getPiece(new Position(2));
		assertTrue(p.getPieceType() == Piece.PieceType.Mate);
		assertTrue(p.getColor() == Color.WHITE);
		
		p = b.getPiece(new Position(47));
		assertTrue(p.getPieceType() == Piece.PieceType.Mate);
		assertTrue(p.getColor() == Color.BLACK);
		p = b.getPiece(new Position(47));
		assertTrue(p.getPieceType() == Piece.PieceType.Mate);
		assertTrue(p.getColor() == Color.BLACK);
		
		assertTrue(g.getCurrentTurn() == Color.WHITE);
		
	}

	@Test
	public final void testLoadStartingPositions() {
		fail("Not yet implemented");
	}

	@Test
	public final void testLoadRandomStartingPositions() {
		fail("Not yet implemented");
	}

	@Test
	public final void testLoadSetStartingPosition() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetValidMoves() {
		fail("Not yet implemented");
	}

	@Test
	public final void testMovePiece() {
		fail("Not yet implemented");
	}

	@Test
	public final void testUndoMove() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetBoard() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetCurrentTurn() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSetCurrentTurn() {
		fail("Not yet implemented");
	}

}
