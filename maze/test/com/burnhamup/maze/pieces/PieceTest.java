package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class PieceTest {

	@SuppressWarnings("serial")
	private class MockPiece extends Piece {

		public MockPiece(Color c) {
			super(c);
		}

		@Override
		public Set<Position> getValidMoves(Board board) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PieceType getPieceType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Test
	public void testGetPosition() {
		Piece p = new MockPiece(Color.WHITE);
		p.position = new Position(0, 0);
		assertEquals(new Position(0, 0), p.getPosition());
		p.position = new Position(4, 1);
		assertEquals(new Position(4, 1), p.getPosition());
	}

	@Test
	public void testGetColor() {
		Piece p = new MockPiece(Color.WHITE);
		assertEquals(Color.WHITE, p.getColor());
		p.color = Color.BLACK;
		assertEquals(Color.BLACK, p.getColor());
	}

	@Test
	public void testIsDead() {
		Piece p = new MockPiece(Color.WHITE);
		p.isDead = false;
		assertFalse(p.isDead());
		p.isDead = true;
		assertTrue(p.isDead());
	}

	@Test
	public void testSetPosition() {
		Piece p = new MockPiece(Color.WHITE);
		p.setPosition(new Position(2, 2));
		assertEquals(new Position(2, 2), p.position);
	}

	@Test
	public void testPiece() {
		Piece p = new MockPiece(Color.WHITE);
		assertEquals(Color.WHITE, p.color);
		p = new MockPiece(Color.BLACK);
		assertEquals(Color.BLACK, p.color);
		assertNull(p.position);
		assertFalse(p.isDead);
	}

	@Test
	public void testMovePiece() {
		Piece p = new MockPiece(Color.WHITE);
		Position position = new Position(2,2);
		p.movePiece(position);
		assertEquals(new Position(2, 2), p.position);
		assertNotSame(p.getPosition(), position);
	}

	@Test
	public void testKill() {
		Piece p = new MockPiece(Color.WHITE);
		assertFalse(p.isDead);
		p.kill();
		assertTrue(p.isDead);
	}

	@Test
	public void testEqualsObject() {
		Piece p1 = new MockPiece(Color.WHITE);
		Piece p2 = new MockPiece(Color.WHITE);
		assertEquals(p1, p1);
		assertEquals(p2, p2);
		assertFalse(p1.equals(null));
		assertFalse(p1.equals(new Position(2, 1)));

		assertEquals(p1, p2);
		p1.movePiece(new Position(2, 1));
		assertFalse(p1.equals(p2));
		assertFalse(p2.equals(p1));
		p2.movePiece(new Position(2, 1));
		assertEquals(p1, p2);
		p2.color = Color.BLACK;
		assertFalse(p1.equals(p2));
		p1.color = Color.BLACK;
		assertEquals(p1, p2);
		p1.kill();
		assertFalse(p1.equals(p2));
		p2.kill();
		assertEquals(p1, p2);
	}

}
