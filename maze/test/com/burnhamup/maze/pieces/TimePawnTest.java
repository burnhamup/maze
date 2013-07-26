package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class TimePawnTest {

	@Test
	public final void testGetValidMovesBlackOne() {
		Board b = new Board();
		TimePawn pawn = new TimePawn(Color.BLACK, 1);
		b.addPiece(pawn, new Position(3,5));
		Set<Position> moveSet = pawn.getValidMoves(b);
		assertTrue(moveSet.size() == 3);
		assertTrue(moveSet.contains(new Position(2, 5)));
		assertTrue(moveSet.contains(new Position(4, 5)));
		assertTrue(moveSet.contains(new Position(3, 4)));
	}
	
	@Test
	public final void testGetValidMovesWhiteOne() {
		Board b = new Board();
		TimePawn pawn = new TimePawn(Color.WHITE, 1);
		b.addPiece(pawn, new Position(3,5));
		Set<Position> moveSet = pawn.getValidMoves(b);
		assertTrue(moveSet.size() == 3);
		assertTrue(moveSet.contains(new Position(2, 5)));
		assertTrue(moveSet.contains(new Position(4, 5)));
		assertTrue(moveSet.contains(new Position(3, 6)));
	}
	
	@Test
	public final void testGetValidMovesBlackTwo() {
		Board b = new Board();
		TimePawn pawn = new TimePawn(Color.BLACK, 2);
		b.addPiece(pawn, new Position(3,5));
		Set<Position> moveSet = pawn.getValidMoves(b);
		assertTrue(moveSet.size() == 3);
		assertTrue(moveSet.contains(new Position(1, 5)));
		assertTrue(moveSet.contains(new Position(5, 5)));
		assertTrue(moveSet.contains(new Position(3, 3)));
	}
	
	@Test
	public final void testGetValidMovesWhiteThree() {
		Board b = new Board();
		TimePawn pawn = new TimePawn(Color.WHITE, 3);
		b.addPiece(pawn, new Position(3,5));
		Set<Position> moveSet = pawn.getValidMoves(b);
		assertTrue(moveSet.size() == 2);
		assertTrue(moveSet.contains(new Position(0, 5)));
		//There is no position 6,5
//		assertTrue(moveSet.contains(new Position(6, 5)));
		assertTrue(moveSet.contains(new Position(3, 8)));
	}
	
	@Test
	public final void testGetValidMovesBlackThreeBlocked() {
		Board b = new Board();
		TimePawn pawn = new TimePawn(Color.BLACK, 3);
		b.addPiece(pawn, new Position(3,5));
		b.addPiece(new TimePawn(Color.BLACK,1), new Position(2,5));
		b.addPiece(new TimePawn(Color.BLACK,2), new Position(5,5));
		b.addPiece(new TimePawn(Color.WHITE,3), new Position(3,2));
		Set<Position> moveSet = pawn.getValidMoves(b);
		assertTrue(moveSet.size() == 0);
	}
	
	@Test
	public final void testGetValidMovesDruben() {
		Board b = new Board();
		b.setDrubenVariation(true);
		TimePawn pawn = new TimePawn(Color.BLACK, 3);
		b.addPiece(pawn, new Position(3,5));
		Set<Position> moveSet = pawn.getValidMoves(b);
		System.out.println(moveSet);
		assertEquals(8,moveSet.size());
		assertTrue(moveSet.contains(new Position(3,4)));
		assertTrue(moveSet.contains(new Position(3,3)));
		assertTrue(moveSet.contains(new Position(3,2)));
		assertTrue(moveSet.contains(new Position(2,5)));
		assertTrue(moveSet.contains(new Position(1,5)));
		assertTrue(moveSet.contains(new Position(0,5)));
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(5,5)));
	}
	
	@Test
	public final void testGetValidMovesDrubenBlocked() {
		Board b = new Board();getClass();
		b.setDrubenVariation(true);
		TimePawn pawn = new TimePawn(Color.BLACK, 3);
		b.addPiece(pawn, new Position(3,5));
		b.addPiece(new TimePawn(Color.WHITE, 2), new Position(3,2));
		b.addPiece(new TimePawn(Color.WHITE,1), new Position(2,5));
		Set<Position> moveSet = pawn.getValidMoves(b);
		
		assertEquals(4,moveSet.size());
		assertTrue(moveSet.contains(new Position(3,3)));
		assertTrue(moveSet.contains(new Position(3,4)));
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(5,5)));	
	}
	
	
	@Test
	public final void testGetValidMovesIsDead() {
		Board b = new Board();
		Piece p = new TimePawn(Color.BLACK,1);
		b.addPiece(p, new Position(3,5));
		p.kill();
		assertTrue(p.getValidMoves(b).size() == 0);
	}

	@Test
	public final void testTimePawn() {
		TimePawn pawn = new TimePawn(Color.BLACK, 1);
		assertTrue(pawn.numberOfMoves == 1);
		pawn = new TimePawn(Color.BLACK, 2);
		assertTrue(pawn.numberOfMoves == 2);
		pawn = new TimePawn(Color.BLACK, 3);
		assertTrue(pawn.numberOfMoves == 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testTimePawnTooLow() {
		@SuppressWarnings("unused")
		TimePawn pawn = new TimePawn(Color.WHITE, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testTimePawnTooHigh() {
		@SuppressWarnings("unused")
		TimePawn pawn = new TimePawn(Color.WHITE, 4);
		assertTrue(false);
	}
	
	

}
