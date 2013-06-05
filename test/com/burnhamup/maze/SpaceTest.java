package com.burnhamup.maze;

import static org.junit.Assert.*;

import org.junit.Test;

import com.burnhamup.maze.pieces.Mate;
import com.burnhamup.maze.pieces.Piece;

public class SpaceTest {

	@Test
	public final void testSpace() {
		Space s = new Space(new Position(1,1), Color.BLACK, true);
		assertEquals(new Position(1,1), s.getPosition());
		assertEquals(Color.BLACK, s.getColor());
		assertTrue(s.isDesert());
		s = new Space(new Position(3,1), Color.WHITE, false);
		assertEquals(new Position(3,1), s.getPosition());
		assertEquals(Color.WHITE, s.getColor());
		assertFalse(s.isDesert());
		assertNull(s.getOccupyingPiece());
	}

	@Test
	public final void testGetColor() {
		Space s = new Space(new Position(1,1), Color.BLACK, true);
		s.setColor(Color.BLACK);
		assertEquals(Color.BLACK, s.getColor());
		s.setColor(Color.WHITE);
		assertEquals(Color.WHITE, s.getColor());
		Color.values();
		Color.valueOf("BLACK");
	}

	@Test
	public final void testIsDesert() {
		Space s = new Space(new Position(1,1), Color.BLACK, true);
		s.setDesert(true);
		assertTrue(s.isDesert());
		s.setDesert(false);
		assertFalse(s.isDesert());
	}


	@Test
	public final void testGetPosition() {
		Space s = new Space(new Position(1,1), Color.BLACK, true);
		s.setPosition(new Position(3,1));
		assertEquals(new Position(3,1), s.getPosition());
		s.setPosition(new Position(1,3));
		assertEquals(new Position(1,3), s.getPosition());
	}

	@Test
	public final void testGetOccupyingPiece() {
		Space s = new Space(new Position(1,1), Color.BLACK, true);
		Piece p = new Mate(Color.WHITE);
		s.setOccupyingPiece(new Mate(Color.WHITE));
		assertNotNull(s.getOccupyingPiece());
		assertTrue(s.getOccupyingPiece().getClass() == p.getClass());
	}


	@Test
	public final void testEqualsObject() {
		Space s1 = new Space(new Position(1,1), Color.BLACK, true);
		Space s2 = new Space(new Position(1,2), Color.WHITE, false);
		assertEquals(s1,s1);
		assertEquals(s2,s2);
		assertFalse(s1.equals(new Position(1,2)));
		assertFalse(s1.equals(null));
		assertFalse(s2.equals(null));
		assertFalse(s1.equals(s2));
		assertFalse(s2.equals(s1));
		
		s1.setPosition(new Position(1,2));
		assertFalse(s1.equals(s2));
		s1.setColor(Color.WHITE);
		assertFalse(s1.equals(s2));
		s1.setDesert(false);
		assertTrue(s1.equals(s2));
		
		s2.setOccupyingPiece(new Mate(Color.BLACK));
		assertFalse(s1.equals(s2));
		s1.setOccupyingPiece(new Mate(Color.WHITE));
		assertFalse(s1.equals(s2));
		s1.setOccupyingPiece(new Mate(Color.BLACK));
		assertTrue(s1.equals(s2));
		
		s1.setPosition(null);
		assertFalse(s1.equals(s2));
		assertFalse(s2.equals(s1));
	}

}
