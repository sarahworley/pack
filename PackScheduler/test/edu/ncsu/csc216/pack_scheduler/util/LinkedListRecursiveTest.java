/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * recursive test
 * 
 * @author sarahworley
 *
 */
public class LinkedListRecursiveTest {

	private LinkedListRecursive<String> lr;

	private String s1 = "a";
	private String s2 = "b";
	private String s3 = "c";
	private String s4 = "d";
	private String s5 = null;

	/** sets up 
	 * @throws java.lang.Exception if fails 
	 */
	@Before
	public void setUp() throws Exception {
		lr = new LinkedListRecursive<String>();
	}

	/**
	 * test constructor
	 */
	@Test
	public void testLinkedListRecursive() {
		assertEquals(0, lr.size());
		assertTrue(lr.isEmpty());

	}

	/**
	 * tests add
	 */
	@Test
	public void testAdd() {

		// tries to add null
		try {
			lr.add(s5);
			fail();
		} catch (NullPointerException e) {
			assertEquals(0, lr.size());
		}
		// tried to add duplicate
		lr.add(s1);
		try {
			lr.add(s1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, lr.size());
		}
		try {
			lr.add(0, s1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, lr.size());
		}

		try {
			lr.add(-1, s3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, lr.size());
		}
		try {
			lr.add(5, s3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, lr.size());
		}
		// adding element in the middle using index
		lr.add(s3);
		lr.add(1, s2);
		assertEquals(3, lr.size());
		assertEquals(s2, lr.get(1));

	}

	/**
	 * tests remove
	 */
	@Test
	public void testRemove() {
		lr.add(s1);
		lr.add(s2);
		lr.add(s3);
		assertEquals(3, lr.size());
		lr.remove(s1);
		assertEquals(2, lr.size());
		lr.remove(0);
		assertEquals(s3, lr.get(0));
		lr.add(s1);
		lr.add(s2);
		lr.remove(2);
		assertEquals(2, lr.size());
		try {
			lr.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, lr.size());
		}
		try {
			lr.remove(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, lr.size());
		}

		assertFalse(lr.remove(s4));

	}

	/**
	 * tests set 
	 */
	@Test
	public void testSet() {
		lr.add(s1);
		lr.add(s2);
		lr.add(s3);

		lr.set(2, s4);
		assertEquals(s4, lr.get(2));
		lr.set(0, s3);
		assertEquals(s3, lr.get(0));

		try {
			lr.set(-1, s4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, lr.size());
		}
		try {
			lr.set(3, s4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, lr.size());
		}
		
		try {
			lr.set(2, s5);
			fail();
		} catch (NullPointerException  e) {
			assertEquals(3, lr.size());
		}
		
		try {
			lr.set(2, s3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, lr.size());
		}
	}

	/**
	 * tests contains
	 */
	@Test
	public void testContains() {
		lr.add(s1);
		lr.add(s2);
		lr.add(s3);
		assertFalse(lr.contains(s4));
		assertTrue(lr.contains(s1));

	}
	
	/**
	 * Test get
	 */
	@Test
	public void testGet() {
		lr.add(s1);
		lr.add(s2);
		lr.add(s3);
		assertEquals(s1, lr.get(0));
		try {
			lr.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, lr.size());
		}
		
		try {
			lr.get(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, lr.size());
		}

	}

}
