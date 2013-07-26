package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class RabbitTest {
	
	@Test
	public final void testGetValidMovesFromCenter() {
		Board b = new Board();
		Rabbit r = new Rabbit(Color.BLACK);
		b.addPiece(r, new Position(2, 5));
		Set<Position> moveSet = r.getValidMoves(b);
		assertTrue(moveSet.size() == 0);
	}
	
	@Test
	public final void testGetValidMovesCompletelySurrounded() {
		Board b = new Board();
		Rabbit r = new Rabbit(Color.BLACK);
		b.addPiece(r,  new Position(2,5));
		b.addPiece(new Rabbit(Color.WHITE), new Position(1,4));
		b.addPiece(new Rabbit(Color.BLACK), new Position(1,5));
		b.addPiece(new Rabbit(Color.WHITE), new Position(1,6));
		b.addPiece(new Rabbit(Color.BLACK), new Position(2,4));
		b.addPiece(new Rabbit(Color.WHITE), new Position(2,6));
		b.addPiece(new Rabbit(Color.BLACK), new Position(3,4));
		b.addPiece(new Rabbit(Color.WHITE), new Position(3,5));
		b.addPiece(new Rabbit(Color.BLACK), new Position(3,6));
		
		Set<Position> moveSet = r.getValidMoves(b);
		assertEquals(8, moveSet.size());
		assertTrue(moveSet.contains(new Position(0,3)));
		assertTrue(moveSet.contains(new Position(0,5)));
		assertTrue(moveSet.contains(new Position(0,7)));
		assertTrue(moveSet.contains(new Position(2,3)));
		assertTrue(moveSet.contains(new Position(2,7)));
		assertTrue(moveSet.contains(new Position(4,3)));
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(4,7)));
	}
	
	@Test
	public final void testGetValidMovesOnePossibleJump() {
		Board b = new Board();
		Rabbit r = new Rabbit(Color.BLACK);
		b.addPiece(r,  new Position(2,5));
		b.addPiece(new Rabbit(Color.BLACK), new Position(1,4));
		
		Set<Position> moveSet = r.getValidMoves(b);
		assertEquals(1, moveSet.size());
		assertTrue(moveSet.contains(new Position(0,3)));
	}
	
	@Test
	public final void testGetValidMovesNoJump() {
		Board b = new Board();
		Rabbit r = new Rabbit(Color.BLACK);
		b.addPiece(r,  new Position(2,5));
		b.addPiece(new Rabbit(Color.BLACK), new Position(1,4));
		b.addPiece(new Rabbit(Color.BLACK), new Position(0,3));
		Set<Position> moveSet = r.getValidMoves(b);
		assertTrue(moveSet.size() == 0);
	}
	
	@Test
	public final void testNoJumpWhenPiecesAreFar() {
		Board b = new Board();
		Rabbit r = new Rabbit(Color.BLACK);
		b.addPiece(r,  new Position(2,5));
		b.addPiece(new Rabbit(Color.BLACK), new Position(5,5));
		b.addPiece(new Rabbit(Color.BLACK), new Position(0,3));
		Set<Position> moveSet = r.getValidMoves(b);
		assertTrue(moveSet.size() == 0);
	}
	
	@Test
	public final void testGetValidMovesIsDead() {
		Board b = new Board();
		Piece p = new Rabbit(Color.BLACK);
		b.addPiece(p, new Position(3,5));
		b.addPiece(new Rabbit(Color.WHITE), new Position(2,4));
		p.kill();
		assertTrue(p.getValidMoves(b).size() == 0);
	}

}
