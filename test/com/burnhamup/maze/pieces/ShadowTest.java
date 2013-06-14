package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class ShadowTest {

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testMateColorMustMatch() {
		Mate mate = new Mate(Color.BLACK);
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(mate);
		Piece shadow = new Shadow(Color.WHITE, mateSet);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testAllMateColorMustMatch() {
		Mate mate = new Mate(Color.BLACK);
		Mate mate2 = new Mate(Color.WHITE);
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(mate);
		mateSet.add(mate2);
		Piece shadow = new Shadow(Color.BLACK, mateSet);
	}

	@Test
	public void testNoMatesNoMoves() {
		Board b = new Board();
		Set<Mate> mateSet = new HashSet<>();
		Piece shadow = new Shadow(Color.WHITE, mateSet);
		b.addPiece(shadow, new Position(3, 3));

		Set<Position> moveSet = shadow.getValidMoves(b);
		assertTrue(moveSet.size() == 0);
	}
	
	@Test
	public void testNoMatesOnBoard() {
		Board b = new Board();
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(new Mate(Color.WHITE));
		Piece shadow = new Shadow(Color.WHITE, mateSet);
		b.addPiece(shadow, new Position(3, 3));

		Set<Position> moveSet = shadow.getValidMoves(b);
		assertTrue(moveSet.size() == 0);
	}

	@Test
	public void testCenterPositionOneMate() {
		Board b = new Board();
		Mate mate = new Mate(Color.WHITE);
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(mate);
		Piece shadow = new Shadow(Color.WHITE, mateSet);

		b.addPiece(mate, new Position(4, 4));
		b.addPiece(shadow, new Position(3, 0));

		Set<Position> moveSet = shadow.getValidMoves(b);
		assertTrue(moveSet.size() == 8);
		assertTrue(moveSet.contains(new Position(3, 3)));
		assertTrue(moveSet.contains(new Position(3, 4)));
		assertTrue(moveSet.contains(new Position(3, 5)));
		assertTrue(moveSet.contains(new Position(4, 3)));
		assertTrue(moveSet.contains(new Position(4, 5)));
		assertTrue(moveSet.contains(new Position(5, 3)));
		assertTrue(moveSet.contains(new Position(5, 4)));
		assertTrue(moveSet.contains(new Position(5, 5)));
	}

	@Test
	public void testCenterPositionTwoMates() {
		Board b = new Board();
		Mate mate = new Mate(Color.WHITE);
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(mate);
		b.addPiece(mate, new Position(3, 3));
		mate = new Mate(Color.WHITE);
		mateSet.add(mate);
		b.addPiece(mate, new Position(2, 7));
		Piece shadow = new Shadow(Color.WHITE, mateSet);
		b.addPiece(shadow, new Position(1, 1));

		Set<Position> moveSet = shadow.getValidMoves(b);
		assertTrue(moveSet.size() == 16);
		assertTrue(moveSet.contains(new Position(2, 2)));
		assertTrue(moveSet.contains(new Position(2, 3)));
		assertTrue(moveSet.contains(new Position(2, 4)));
		assertTrue(moveSet.contains(new Position(3, 2)));
		assertTrue(moveSet.contains(new Position(3, 4)));
		assertTrue(moveSet.contains(new Position(4, 2)));
		assertTrue(moveSet.contains(new Position(4, 3)));
		assertTrue(moveSet.contains(new Position(4, 4)));
		assertTrue(moveSet.contains(new Position(1, 6)));
		assertTrue(moveSet.contains(new Position(1, 7)));
		assertTrue(moveSet.contains(new Position(1, 8)));
		assertTrue(moveSet.contains(new Position(2, 6)));
		assertTrue(moveSet.contains(new Position(2, 8)));
		assertTrue(moveSet.contains(new Position(3, 6)));
		assertTrue(moveSet.contains(new Position(3, 7)));
		assertTrue(moveSet.contains(new Position(3, 8)));
	}

	@Test
	public void testCornerPosition() {
		Board b = new Board();
		Mate mate = new Mate(Color.WHITE);
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(mate);
		Piece shadow = new Shadow(Color.WHITE, mateSet);

		b.addPiece(mate, new Position(2, 0));
		b.addPiece(shadow, new Position(5, 5));

		Set<Position> moveSet = shadow.getValidMoves(b);
		assertTrue(moveSet.size() == 4);
		assertTrue(moveSet.contains(new Position(1, 1)));
		assertTrue(moveSet.contains(new Position(2, 1)));
		assertTrue(moveSet.contains(new Position(3, 1)));
		assertTrue(moveSet.contains(new Position(3, 0)));
	}

	@Test
	public void testOccupiedPosition() {
		Board b = new Board();
		Mate mate = new Mate(Color.WHITE);
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(mate);
		Piece shadow = new Shadow(Color.WHITE, mateSet);
		Piece otherMate = new Mate(Color.BLACK);
		b.addPiece(mate, new Position(4, 4));
		b.addPiece(shadow, new Position(3, 3));
		b.addPiece(otherMate, new Position(4, 3));

		Set<Position> moveSet = shadow.getValidMoves(b);
		assertTrue(moveSet.size() == 6);
		assertTrue(moveSet.contains(new Position(3, 4)));
		assertTrue(moveSet.contains(new Position(3, 5)));
		assertTrue(moveSet.contains(new Position(4, 5)));
		assertTrue(moveSet.contains(new Position(5, 3)));
		assertTrue(moveSet.contains(new Position(5, 4)));
		assertTrue(moveSet.contains(new Position(5, 5)));
	}
	
	@Test
	public final void testGetValidMovesIsDead() {
		Board b = new Board();
		Mate m = new Mate(Color.BLACK);
		Set<Mate> mateSet = new HashSet<>();
		mateSet.add(m);
		Piece p = new Shadow(Color.BLACK, mateSet);
		b.addPiece(p, new Position(3,5));
		b.addPiece(m, new Position(5,5));
		p.kill();
		assertTrue(p.getValidMoves(b).size() == 0);
	}
}