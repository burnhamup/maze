package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class LightningTest {

	@Test
	public final void testGetValidMovesFromCenter() {
		Board b = new Board();
		Piece lightning = new Lightning(Color.BLACK);
		b.addPiece(lightning, new Position(3,3));
		Set<Position> moveSet = lightning.getValidMoves(b);
		assertEquals(4, moveSet.size());
		assertTrue(moveSet.contains(new Position(2,2)));
		assertTrue(moveSet.contains(new Position(2,4)));
		assertTrue(moveSet.contains(new Position(4,2)));
		assertTrue(moveSet.contains(new Position(4,4)));
	}
	
	@Test
	public final void testGetValidMovesFromCorner() {
		Board b = new Board();
		Piece lightning = new Lightning(Color.BLACK);
		b.addPiece(lightning, new Position(2,0));
		Set<Position> moveSet = lightning.getValidMoves(b);
		assertEquals(2, moveSet.size());
		assertTrue(moveSet.contains(new Position(1,1)));
		assertTrue(moveSet.contains(new Position(3,1)));
	}
	
	@Test
	public final void testGetValidMovesWithOtherPiece() {
		Board b = new Board();
		Piece lightning = new Lightning(Color.BLACK);
		b.addPiece(lightning, new Position(3,5));
		b.addPiece(new Lightning(Color.BLACK), new Position(2,4));
		b.addPiece(new Lightning(Color.WHITE), new Position(4,6));
		Set<Position> moveSet = lightning.getValidMoves(b);
		assertEquals(2, moveSet.size());
		assertTrue(moveSet.contains(new Position(2,6)));
		assertTrue(moveSet.contains(new Position(4,4)));
	}
	
	@Test
	public final void testGetValidMovesIsDead() {
		Board b = new Board();
		Piece p = new Lightning(Color.BLACK);
		b.addPiece(p, new Position(3,5));
		p.kill();
		assertTrue(p.getValidMoves(b).size() == 0);
	}

}
