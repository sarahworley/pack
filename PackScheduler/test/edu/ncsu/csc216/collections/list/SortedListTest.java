/**
 * 
 */
package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/** Tests the sortedList class 
 * @author sarahworley
 * Tests the sortedList class 
 *
 */

public class SortedListTest {

	/**
	 * Tests to makes sure a sorted list can be built correctly 
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		list.add("apple");
		list.add("banana");
		list.add("cucumber");
		list.add("dragon fruit");
		list.add("egg plant");
		list.add("fennel");
		list.add("grape");
		list.add("huckleberry");
		list.add("ice plant");
		list.add("jicama");
		list.add("kale");

		assertEquals(11, list.size());

		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cucumber", list.get(2));
		assertEquals("dragon fruit", list.get(3));
		assertEquals("egg plant", list.get(4));
		assertEquals("fennel", list.get(5));
		assertEquals("grape", list.get(6));
		assertEquals("huckleberry", list.get(7));
		assertEquals("ice plant", list.get(8));
		assertEquals("jicama", list.get(9));
		assertEquals("kale", list.get(10));

	}

	/**
	 * Tests the add function of sortedList 
	 * adds item to list 
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		// add in front of banana
		list.add("apricot");
		assertEquals(2, list.size());
		assertEquals("apricot", list.get(0));
		assertEquals("banana", list.get(1));
		// adds to the end
		list.add("dragon fruit");
		assertEquals(3, list.size());
		assertEquals("apricot", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("dragon fruit", list.get(2));
		// adds in the middle
		list.add("cucumber");
		assertEquals(4, list.size());
		assertEquals("apricot", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("dragon fruit", list.get(3));
		assertEquals("cucumber", list.get(2));
		// tries to add a null element
		try {
			list.add(null);
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		// tries to add a duplicate element
		try {
			list.add("apricot");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}

	}

	/**
	 * Tests the get() function of sortedList 
	 * Gets item from list by index
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		// tries to get() from empty list
		try {
			list.get(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("apple");
		list.add("banana");
		list.add("cucumber");
		// tries to get() from - index
		try {
			list.get(-3);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cucumber", list.get(2));

		}

		// Test getting an element at size
		try {
			list.get(list.size());
		} catch (IndexOutOfBoundsException e) {
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cucumber", list.get(2));
		}

	}

	/**
	 * Tests the remove() function of sortedList 
	 * Removes an item from list by index
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements to the list - at least 4
		list.add("apple");
		list.add("banana");
		list.add("cucumber");
		list.add("dragon fruit");

		// Test removing an element at an index < 0
		try {
			list.remove(-3);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cucumber", list.get(2));
			assertEquals("dragon fruit", list.get(3));

		}

		// Test removing an element at size
		try {
			list.remove(list.size());
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cucumber", list.get(2));
			assertEquals("dragon fruit", list.get(3));
		}

		// Test removing a middle element
		list.remove(2);
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));

		assertEquals("dragon fruit", list.get(2));

		// Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));

		// Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());

	}

	/**
	 * Tests the indexOf() function of sortedList 
	 * get the index of a specific item
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		try {
			list.indexOf("apple");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cucumber");
		list.add("dragon fruit");

		// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(0, list.indexOf("apple"));

		assertEquals(3, list.indexOf("dragon fruit"));

		assertEquals(-1, list.indexOf("watermelon"));

		// Test checking the index of null
		try {
			list.indexOf(null);
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}

	}

	/**
	 * Tests the clear() function of sortedList 
	 * Clears a list 
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//  Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cucumber");
		list.add("dragon fruit");
		//  Clear the list
		list.clear();
		// Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests the isEmpty() function of sortedList 
	 * Checks if the list is empty 
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Test that the list starts empty
		assertEquals(0, list.size());

		//  Add at least one element
		list.add("apple");

		// Check that the list is no longer empty
		assertEquals(1, list.size());
	}

	/**
	 * Tests the contains() function of sortedList 
	 * checks to see if a list contains a specific item 
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		//  Test the empty list case
		try {
			list.contains("apple");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cucumber");
		list.add("dragon fruit");

		// Test some true and false cases
		list.contains("apple");
		assertTrue(list.contains("apple"));
		
		list.contains("banana");
		assertTrue(list.contains("banana"));
		
		list.contains("watermelon");
		assertFalse(list.contains("watermelon"));
		
	}

	/**
	 * Tests the equals() function of sortedList 
	 * compares two sorted lists to see if they contain the same information
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		//  Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cucumber");
		list1.add("dragon fruit");
		
		list2.add("apple");
		list2.add("banana");
		list2.add("cucumber");
		list2.add("dragon fruit");
		
		list3.add("huckleberry");
		list3.add("ice plant");
		list3.add("jicama");
		list3.add("kale");
		

		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list1.equals(list3));
	}

	/**
	 * Tests the hashcode() function of sortedList 
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cucumber");
		list1.add("dragon fruit");
		
		list2.add("apple");
		list2.add("banana");
		list2.add("cucumber");
		list2.add("dragon fruit");
		
		list3.add("huckleberry");
		list3.add("ice plant");
		list3.add("jicama");
		list3.add("kale");

		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
