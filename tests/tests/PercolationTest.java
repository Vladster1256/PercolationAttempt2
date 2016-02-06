package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import a01.Percolation;

public class PercolationTest {

	private Percolation p4;
	
	@Before
	public void setUp() throws Exception {
		p4 = new Percolation(4);
	}

	@Test
	public void testPercolationGridBigEnough() {
		Percolation p2 = new Percolation(2);
		// test that a 2x2 grid has been created
		try {
			p2.open(0, 0);
			p2.open(0, 1);
			p2.open(1, 0);
			p2.open(1, 1);
		}
		catch (Exception ex) {
			fail("grid too small");
		}
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPercolationGridNotTooBig() {	
		Percolation p2 = new Percolation(2);
		// test that there is no third column
		p2.open(0,2);
	}
	
	@Test 
	public void testOpen() {
		p4.open(1, 1);
		assertTrue(p4.isOpen(1, 1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testOpenInvalidIndex() {
		p4.open(0, 4);
	}

	@Test
	public void testIsOpenTrue() {
		p4.open(1, 1);
		assertTrue(p4.isOpen(1, 1));
	}	

	@Test
	public void testIsOpenFalse() {
		assertFalse(p4.isOpen(1, 1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIsOpenInvalidIndex() {
		assertFalse(p4.isOpen(0, 4));
	}

	@Test
	public void testIsFullLeft() {
		p4.open(0, 0);
		p4.open(1, 0);
		p4.open(2, 0);
		assertTrue(p4.isFull(2, 0));
	}
	
	@Test
	public void testIsFullCenter() {
		p4.open(0, 2);
		p4.open(1, 2);
		p4.open(1, 1);
		p4.open(2, 1);
		p4.open(3, 1);
		p4.open(3, 2);
		assertTrue(p4.isFull(3, 2));
	}
	
	@Test
	public void testIsFullRight() {
		p4.open(0, 3);
		p4.open(1, 3);
		p4.open(2, 3);
		assertTrue(p4.isFull(2, 3));
	}
	
	@Test
	public void testIsFullLeftDiagonal() {
		p4.open(0, 2);
		p4.open(1, 1);
		assertFalse(p4.isFull(1, 1));
	}		
	
	@Test
	public void testIsFullLRightDiagonal() {
		p4.open(0, 1);
		p4.open(1, 2);
		assertFalse(p4.isFull(1, 2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIsFullInvalidIndex() {
		p4.isFull(0, 4);
	}

	@Test
	public void testPercolates() {
		p4.open(0, 2);
		p4.open(1, 2);
		p4.open(1, 1);
		p4.open(2, 1);
		p4.open(3, 1);
		assertTrue(p4.percolates());
	}
	
	@Test
	public void testPercolatesNot() {
		p4.open(0, 2);
		p4.open(1, 2);
		p4.open(1, 1);
		p4.open(2, 1);
		p4.open(3, 2);
		assertFalse(p4.percolates());
	}
	
	@Test
	public void testPercolatesNoBackwash() {
		p4.open(0, 2);
		p4.open(1, 2);
		p4.open(1, 1);
		p4.open(2, 1);
		p4.open(3, 1);
		p4.open(2, 3);
		p4.open(3, 3);
		assertTrue(p4.percolates());
		assertFalse(p4.isFull(3, 3));
	}
}
