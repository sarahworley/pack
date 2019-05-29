/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Linked list test
 * 
 * @author sarahworley
 *
 */
public class LinkedListTest {

	private LinkedList<Object> ar;

	String string1 = "happy";
	String string2 = "birth";
	String string3 = "day";
	String string4 = "to";
	String string5 = "you";
	String string6 = "happy birthday dear";
	String string7 = "sarah";
	String string8 = "happy birthday to you";
	String string9 = "happy birthday to you!";

	String string12 = null;

	/**
	 * sets up a new array list each time
	 * 
	 * @throws java.lang.Exception if unable to create
	 */
	@Before
	public void setUp() throws Exception {
		ar = new LinkedList<Object>();
	}

	/**
	 * Tests the constructor
	 */
	@Test
	public void testLinkedList() {

		assertEquals(0, ar.size());
		ar.listIterator(0);

	}

	/**
	 * Tests invalid add
	 */
	@Test
	public void testAddInvalid() {

		// invalid element
		try {
			ar.add(-5, string1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, ar.size());
		}
		// invalid element
		try {
			ar.add(20, string1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, ar.size());
		}
		// null string
		try {
			ar.add(0, string12);
		} catch (NullPointerException e) {
			assertEquals(0, ar.size());
		}

		ar.add(0, string8);
		assertEquals(1, ar.size());
		assertEquals("happy birthday to you", ar.get(0));

		// try to add duplicate
		try {
			ar.add(1, string8);
		} catch (IllegalArgumentException e) {
			assertEquals(1, ar.size());

		}

	}

	/**
	 * tests vaild add
	 */
	@Test
	public void testAddValid() {
		ar.add(0, string1);
		assertEquals(1, ar.size());
		assertEquals("happy", ar.get(0));

		ar.add(1, string2);
		assertEquals(2, ar.size());
		assertEquals("birth", ar.get(1));

		ar.add(2, string3);
		assertEquals(3, ar.size());
		assertEquals("day", ar.get(2));

		ar.add(0, string8);
		assertEquals(4, ar.size());
		assertEquals("happy birthday to you", ar.get(0));

		ar.add(4, string4);
		assertEquals(5, ar.size());
		assertEquals("to", ar.get(4));

		ar.add(5, string5);
		assertEquals(6, ar.size());
		assertEquals("you", ar.get(5));

		ar.add(6, string9);
		assertEquals(7, ar.size());
		assertEquals("happy birthday to you!", ar.get(6));

		ar.add(6, string6);
		assertEquals(8, ar.size());
		assertEquals("happy birthday dear", ar.get(6));

		ar.add(7, string7);
		assertEquals("happy birthday to you", ar.listIterator().next());
		assertEquals(9, ar.size());
		assertEquals("sarah", ar.get(7));

		try {
			ar.listIterator().previous();
		} catch (NoSuchElementException e) {
			// nope
		}

		// checks order
		assertEquals("happy birthday to you", ar.get(0));
		assertEquals("happy birthday to you", ar.get(0));
		assertEquals("happy", ar.get(1));
		assertEquals("birth", ar.get(2));
		assertEquals("day", ar.get(3));
		assertEquals("to", ar.get(4));
		assertEquals("you", ar.get(5));
		assertEquals("happy birthday dear", ar.get(6));
		assertEquals("sarah", ar.get(7));
		assertEquals("happy birthday to you!", ar.get(8));

		assertEquals(-1, ar.listIterator().previousIndex());
		assertEquals(0, ar.listIterator().nextIndex());
		assertFalse(ar.listIterator().hasPrevious());
		assertTrue(ar.listIterator().hasNext());

	}

	/**
	 * tests valid remove
	 */
	@Test
	public void testRemoveValid() {
		ar.add(0, string1);
		ar.add(1, string2);
		ar.add(2, string3);
		ar.add(3, string4);
		ar.add(4, string5);
		ar.add(5, string6);
		ar.add(6, string7);
		ar.add(7, string8);
		ar.add(8, string9);

		ar.remove(7);
		assertEquals(8, ar.size());
		assertEquals("happy birthday to you!", ar.get(7));

		ar.remove(7);
		assertEquals(7, ar.size());
		assertEquals("sarah", ar.get(6));

		ar.remove(0);
		assertEquals(6, ar.size());

		assertEquals(-1, ar.listIterator().previousIndex());
	}

	/**
	 * tests invalid remove
	 */
	@Test
	public void testRemoveInvalid() {

		ar.add(0, string1);
		ar.add(1, string2);
		ar.add(2, string3);
		ar.add(3, string4);
		ar.add(4, string5);
		ar.add(5, string6);
		ar.add(6, string7);
		ar.add(7, string8);
		ar.add(8, string9);

		try {
			ar.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(9, ar.size());
		}

		try {
			ar.remove(10);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(9, ar.size());
		}

	}

	/**
	 * tests invalid remove
	 */
	@Test
	public void testSet() {
		ar.add(0, string1);

		ar.add(1, string3);
		ar.add(2, string4);
		ar.add(3, string5);
		ar.add(4, string6);
		ar.add(5, string7);
		ar.add(6, string8);
		ar.add(7, string9);
		assertEquals("day", ar.get(1));
		ar.set(1, string2);
		assertEquals("birth", ar.get(1));

		// null string
		try {
			ar.set(1, string12);
		} catch (NullPointerException e) {
			assertEquals("birth", ar.get(1));
		}
		// duplicate
		try {
			ar.set(1, string1);
		} catch (IllegalArgumentException e) {
			assertEquals("birth", ar.get(1));
		}
		// index out of bounds
		try {
			ar.set(9, string1);
		} catch (IllegalArgumentException e) {
			assertEquals(8, ar.size());
		}
	}

}
