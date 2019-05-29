/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**LinkedQueue test 
 * @author sarahworley
 *
 */
public class LinkedQueueTest {
	/** New LinkedQueue */
	private LinkedQueue<String> lQueue;
	/** test string */
	private String test1 = "one";
	/** test string */
	private String test2 = "two";
	/** test string */
	private String test3 = "three";
	/** test string */
	private String test4 = "four";
	/** null */
	private String test5 = null;
	
	

	
	
	/**
	 * Sets up new LinkedQueue before each test
	 */
	@Before
	public void setUp() {
		lQueue = new LinkedQueue<String>(3);
	}
	/** 
	 * test forLinkedQueue constructor
	 * 
	 */
	@Test
	public void testArrayqueue() {
		assertEquals(lQueue.size(), 0);
		assertTrue(lQueue.isEmpty());
		
	}
	
	/** 
	 * test for LinkedQueue Push
	 * 
	 */
	@Test
	public void testPush() {
		
		lQueue.enqueue(test1);
		lQueue.enqueue(test2);
		lQueue.enqueue(test3);
		assertEquals(lQueue.size(), 3);
		
		try {
			lQueue.enqueue(test4);
		} catch ( IllegalArgumentException e) {
			assertEquals(lQueue.size(), 3);
			
		}
		lQueue.setCapacity(4);
		lQueue.enqueue(test4);
		assertEquals(lQueue.size(), 4);
		
		lQueue.setCapacity(5);
		try {
			lQueue.enqueue(test5);
		} catch ( NullPointerException e) {
			assertEquals(lQueue.size(), 4);
			
		}
		
	}
	
	/** 
	 * test for LinkedQueue Pop
	 * 
	 */
	@Test
	public void testPop() {
		lQueue.enqueue(test1);
		lQueue.enqueue(test2);
		lQueue.enqueue(test3);
		assertEquals(lQueue.size(), 3);
		lQueue.dequeue();
		assertEquals(lQueue.size(), 2);
		lQueue.dequeue();
		assertEquals(lQueue.size(), 1);
		lQueue.dequeue();
		assertEquals(lQueue.size(), 0);
		
		try {
			lQueue.dequeue();
		} catch ( NoSuchElementException e) {
			assertEquals(lQueue.size(), 0);
			
		}
		assertTrue(lQueue.isEmpty());
	}
	
	

}

