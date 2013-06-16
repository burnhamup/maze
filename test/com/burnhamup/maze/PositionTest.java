package com.burnhamup.maze;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public final void testPositionInt() {
		assertEquals(new Position(2,0), new Position(1));
		assertEquals(new Position(3,0), new Position(2));
		
		assertEquals(new Position(1,1), new Position(3));
		assertEquals(new Position(2,1), new Position(4));
		assertEquals(new Position(3,1), new Position(5));
		assertEquals(new Position(4,1), new Position(6));
		
		assertEquals(new Position(0,2), new Position(7));
		assertEquals(new Position(1,2), new Position(8));
		assertEquals(new Position(2,2), new Position(9));
		assertEquals(new Position(3,2), new Position(10));
		assertEquals(new Position(4,2), new Position(11));
		assertEquals(new Position(5,2), new Position(12));
		
		assertEquals(new Position(0,3), new Position(13));
		assertEquals(new Position(1,3), new Position(14));
		assertEquals(new Position(2,3), new Position(15));
		assertEquals(new Position(3,3), new Position(16));
		assertEquals(new Position(4,3), new Position(17));
		assertEquals(new Position(5,3), new Position(18));
		
		assertEquals(new Position(0,4), new Position(19));
		assertEquals(new Position(1,4), new Position(20));
		assertEquals(new Position(2,4), new Position(21));
		assertEquals(new Position(3,4), new Position(22));
		assertEquals(new Position(4,4), new Position(23));
		assertEquals(new Position(5,4), new Position(24));
		
		assertEquals(new Position(2,6), new Position(33));
		
		assertEquals(new Position(1,8), new Position(43));
		assertEquals(new Position(2,8), new Position(44));
		assertEquals(new Position(3,8), new Position(45));
		assertEquals(new Position(4,8), new Position(46));
		
		assertEquals(new Position(2,9), new Position(47));
		assertEquals(new Position(3,9), new Position(48));
	}

	@Test
	public final void testGetSpaceNumber() {
		assertEquals(1, new Position(2,0).getSpaceNumber());
		assertEquals(2, new Position(3,0).getSpaceNumber());
		
		assertEquals(3, new Position(1,1).getSpaceNumber());
		assertEquals(4, new Position(2,1).getSpaceNumber());
		assertEquals(5, new Position(3,1).getSpaceNumber());
		assertEquals(6, new Position(4,1).getSpaceNumber());
		
		assertEquals(33, new Position(2,6).getSpaceNumber());
		
		assertEquals(47, new Position(2,9).getSpaceNumber());
		assertEquals(48, new Position(3,9).getSpaceNumber());
		
		assertEquals(43, new Position(1,8).getSpaceNumber());
		assertEquals(44, new Position(2,8).getSpaceNumber());
		assertEquals(45, new Position(3,8).getSpaceNumber());
		assertEquals(46, new Position(4,8).getSpaceNumber());
	}

}
