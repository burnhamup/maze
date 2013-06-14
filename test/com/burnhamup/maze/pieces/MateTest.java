package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class MateTest {

	@Test
	public void testCenterPosition() {
		Board board = new Board();
		Piece mate = new Mate(Color.BLACK);
		board.addPiece(mate, new Position(3, 5));
		Set<Position> moveList = mate.getValidMoves(board);
		assertEquals(9, moveList.size());

		assertTrue(moveList.contains(new Position(2, 4)));
		assertTrue(moveList.contains(new Position(1, 3)));
		assertTrue(moveList.contains(new Position(0, 2)));
		assertTrue(moveList.contains(new Position(2, 6)));
		assertTrue(moveList.contains(new Position(1, 7)));
		assertTrue(moveList.contains(new Position(4, 4)));
		assertTrue(moveList.contains(new Position(5, 3)));
		assertTrue(moveList.contains(new Position(4, 6)));
		assertTrue(moveList.contains(new Position(5, 7)));
		assertFalse(moveList.contains(new Position(0, 8)));
	}

	@Test
	public void testCornerPosition() {
		Board board = new Board();
		Piece mate = new Mate(Color.BLACK);
		board.addPiece(mate, new Position(0, 2));
		Set<Position> moveList = mate.getValidMoves(board);
		assertEquals(7, moveList.size());
		assertTrue(moveList.contains(new Position(1, 1)));
		assertTrue(moveList.contains(new Position(2, 0)));
		assertTrue(moveList.contains(new Position(1, 3)));
		assertTrue(moveList.contains(new Position(2, 4)));
		assertTrue(moveList.contains(new Position(3, 5)));
		assertTrue(moveList.contains(new Position(4, 6)));
		assertTrue(moveList.contains(new Position(5, 7)));
	}

	@Test
	public void testBlockedPosition() {
		Board board = new Board();
		Piece mate = new Mate(Color.BLACK);
		board.addPiece(mate, new Position(3, 5));
		board.addPiece(new Mate(Color.WHITE), new Position(2, 4));
		Set<Position> moveList = mate.getValidMoves(board);
		assertEquals(6, moveList.size());

		assertFalse(moveList.contains(new Position(2, 4)));
		assertFalse(moveList.contains(new Position(1, 3)));
		assertFalse(moveList.contains(new Position(0, 2)));
		assertTrue(moveList.contains(new Position(2, 6)));
		assertTrue(moveList.contains(new Position(1, 7)));
		assertTrue(moveList.contains(new Position(4, 4)));
		assertTrue(moveList.contains(new Position(5, 3)));
		assertTrue(moveList.contains(new Position(4, 6)));
		assertTrue(moveList.contains(new Position(5, 7)));
		assertFalse(moveList.contains(new Position(0, 8)));
	}
	
	@Test
	public final void testGetValidMovesIsDead() {
		Board b = new Board();
		Piece p = new Mate(Color.BLACK);
		b.addPiece(p, new Position(3,5));
		p.kill();
		assertTrue(p.getValidMoves(b).size() == 0);
	}

}