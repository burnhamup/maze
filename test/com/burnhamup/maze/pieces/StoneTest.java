package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class StoneTest {

	@Test
	public final void testGetValidMoves() {
		Board b = new Board();
		Stone s = new Stone(Color.BLACK);
		b.addPiece(s, new Position(3,6));
		Set<Position> moveSet = s.getValidMoves(b);
		assertTrue(moveSet.size() == 4);
		assertTrue(moveSet.contains(new Position(2,5)));
		assertTrue(moveSet.contains(new Position(2,7)));
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(4,7)));
	}
	
	@Test
	public final void testGetValidMovesBlocked() {
		Board b = new Board();
		Stone s = new Stone(Color.BLACK);
		b.addPiece(s, new Position(3,6));
		b.addPiece(new Stone(Color.BLACK), new Position(2,5));
		Set<Position> moveSet = s.getValidMoves(b);
		assertTrue(moveSet.size() == 3);
		assertTrue(moveSet.contains(new Position(2,7)));
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(4,7)));
	}
	
	@Test
	//TODO I don't know if the space above or below this piece must ALWAYS be empty.
	public final void testGetValidMovesPathBlocked() {
		Board b = new Board();
		Stone s = new Stone(Color.BLACK);
		b.addPiece(s, new Position(3,6));
		b.addPiece(new Stone(Color.BLACK), new Position(2,6));
		Set<Position> moveSet = s.getValidMoves(b);
		assertTrue(moveSet.size() == 2);
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(4,7)));
		
	}
	
	@Test
	public final void testGetValidMovesPieceToSide() {
		Board b = new Board();
		Stone s = new Stone(Color.BLACK);
		b.addPiece(s, new Position(3,6));
		b.addPiece(new Stone(Color.WHITE), new Position(3,5));
		Set<Position> moveSet = s.getValidMoves(b);
		assertTrue(moveSet.size() == 4);
		assertTrue(moveSet.contains(new Position(2,5)));
		assertTrue(moveSet.contains(new Position(2,7)));
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(4,7)));
	}
	
	@Test
	public final void testGetValidMovesIsDead() {
		Board b = new Board();
		Piece p = new Stone(Color.BLACK);
		b.addPiece(p, new Position(3,5));
		p.kill();
		assertTrue(p.getValidMoves(b).size() == 0);
	}

}
