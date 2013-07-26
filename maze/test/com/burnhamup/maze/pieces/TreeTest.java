package com.burnhamup.maze.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.burnhamup.maze.Board;
import com.burnhamup.maze.Color;
import com.burnhamup.maze.Position;

public class TreeTest {

	@Test
	public final void testGetValidMovesBlack() {
		Board b = new Board();
		Tree tree = new Tree(Color.BLACK);
		b.addPiece(tree, new Position(3,6));
		Set<Position> moveSet = tree.getValidMoves(b);
		
		assertEquals(23, moveSet.size());
		assertTrue(moveSet.contains(new Position(0,3)));
		assertTrue(moveSet.contains(new Position(0,5)));
		assertTrue(moveSet.contains(new Position(0,7)));
		assertTrue(moveSet.contains(new Position(1,2)));
		assertTrue(moveSet.contains(new Position(1,4)));
		assertTrue(moveSet.contains(new Position(1,6)));
		assertTrue(moveSet.contains(new Position(1,8)));
		assertTrue(moveSet.contains(new Position(2,1)));
		assertTrue(moveSet.contains(new Position(2,3)));
		assertTrue(moveSet.contains(new Position(2,5)));
		assertTrue(moveSet.contains(new Position(2,7)));
		assertTrue(moveSet.contains(new Position(2,9)));
		assertTrue(moveSet.contains(new Position(3,0)));
		assertTrue(moveSet.contains(new Position(3,2)));
		assertTrue(moveSet.contains(new Position(3,4)));
		assertTrue(moveSet.contains(new Position(3,8)));
		assertTrue(moveSet.contains(new Position(4,1)));
		assertTrue(moveSet.contains(new Position(4,3)));
		assertTrue(moveSet.contains(new Position(4,5)));
		assertTrue(moveSet.contains(new Position(4,7)));
		assertTrue(moveSet.contains(new Position(5,2)));
		assertTrue(moveSet.contains(new Position(5,4)));
		assertTrue(moveSet.contains(new Position(5,6)));
	}
	
	@Test
	public final void testGetValidMovesWhite() {
		Board b = new Board();
		Tree tree = new Tree(Color.WHITE);
		b.addPiece(tree, new Position(3,5));
		Set<Position> moveSet = tree.getValidMoves(b);
		
		assertEquals(23, moveSet.size());
		assertTrue(moveSet.contains(new Position(0,2)));
		assertTrue(moveSet.contains(new Position(0,4)));
		assertTrue(moveSet.contains(new Position(0,6)));
		assertTrue(moveSet.contains(new Position(1,1)));
		assertTrue(moveSet.contains(new Position(1,3)));
		assertTrue(moveSet.contains(new Position(1,5)));
		assertTrue(moveSet.contains(new Position(1,7)));
		assertTrue(moveSet.contains(new Position(2,0)));
		assertTrue(moveSet.contains(new Position(2,2)));
		assertTrue(moveSet.contains(new Position(2,4)));
		assertTrue(moveSet.contains(new Position(2,6)));
		assertTrue(moveSet.contains(new Position(2,8)));
		assertTrue(moveSet.contains(new Position(3,9)));
		assertTrue(moveSet.contains(new Position(3,1)));
		assertTrue(moveSet.contains(new Position(3,3)));
		assertTrue(moveSet.contains(new Position(3,7)));
		assertTrue(moveSet.contains(new Position(4,2)));
		assertTrue(moveSet.contains(new Position(4,4)));
		assertTrue(moveSet.contains(new Position(4,6)));
		assertTrue(moveSet.contains(new Position(4,8)));
		assertTrue(moveSet.contains(new Position(5,3)));
		assertTrue(moveSet.contains(new Position(5,5)));
		assertTrue(moveSet.contains(new Position(5,7)));
	}
	
	@Test
	public final void testGetValidMovesBlockingPiece() {
		Board b = new Board();
		Tree tree = new Tree(Color.WHITE);
		b.addPiece(tree, new Position(3,5));
		b.addPiece(new Tree(Color.WHITE), new Position(5,3));
		Set<Position> moveSet = tree.getValidMoves(b);
		
		assertEquals(22, moveSet.size());
		assertTrue(moveSet.contains(new Position(0,2)));
		assertTrue(moveSet.contains(new Position(0,4)));
		assertTrue(moveSet.contains(new Position(0,6)));
		assertTrue(moveSet.contains(new Position(1,1)));
		assertTrue(moveSet.contains(new Position(1,3)));
		assertTrue(moveSet.contains(new Position(1,5)));
		assertTrue(moveSet.contains(new Position(1,7)));
		assertTrue(moveSet.contains(new Position(2,0)));
		assertTrue(moveSet.contains(new Position(2,2)));
		assertTrue(moveSet.contains(new Position(2,4)));
		assertTrue(moveSet.contains(new Position(2,6)));
		assertTrue(moveSet.contains(new Position(2,8)));
		assertTrue(moveSet.contains(new Position(3,9)));
		assertTrue(moveSet.contains(new Position(3,1)));
		assertTrue(moveSet.contains(new Position(3,3)));
		assertTrue(moveSet.contains(new Position(3,7)));
		assertTrue(moveSet.contains(new Position(4,2)));
		assertTrue(moveSet.contains(new Position(4,4)));
		assertTrue(moveSet.contains(new Position(4,6)));
		assertTrue(moveSet.contains(new Position(4,8)));
		assertTrue(moveSet.contains(new Position(5,5)));
		assertTrue(moveSet.contains(new Position(5,7)));
	}
	
	@Test
	public final void testPieceIsDeadAfterMoving() {
		Board b = new Board();
		Tree tree = new Tree(Color.BLACK);
		b.addPiece(tree, new Position(3,5));
		b.movePiece(new Position(3,5), new Position(0,2));
		assertTrue(tree.isDead());
	}
	
	@Test
	public final void testGetValidMovesIsDead() {
		Board b = new Board();
		Piece p = new Tree(Color.BLACK);
		b.addPiece(p, new Position(3,5));
		p.kill();
		assertTrue(p.getValidMoves(b).size() == 0);
	}

}
