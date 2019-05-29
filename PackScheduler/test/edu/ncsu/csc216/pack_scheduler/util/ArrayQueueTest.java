/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;


import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/** Arrayqueue test
 * @author sarahworley
 *
 */
public class ArrayQueueTest {
	/** New Arrayqueue */
	private ArrayQueue<String> aQueue;
	/** test string */
	private String test1 = "one";
	/** test string */
	private String test2 = "two";
	/** test string */
	private String test3 = "three";
	/** test string */
	private String test4 = "four";
	
	private String test5 = null;
	
	

	
	
	/**
	 * Sets up new Arrayqueue before each test
	 */
	@Before
	public void setUp() {
		aQueue = new ArrayQueue<String>(3);
	}
	/** 
	 * test for Arrayqueue constructor
	 * 
	 */
	@Test
	public void testArrayqueue() {
		assertEquals(aQueue.size(), 0);
		assertTrue(aQueue.isEmpty());
		
	}
	
	/** 
	 * test for Arrayqueue Push
	 * 
	 */
	@Test
	public void testPush() {
		aQueue.setCapacity(3);
		aQueue.enqueue(test1);
		aQueue.enqueue(test2);
		aQueue.enqueue(test3);
		assertEquals(aQueue.size(), 3);
		
		aQueue.setCapacity(4);
		aQueue.enqueue(test4);
		assertEquals(aQueue.size(), 4);
		aQueue.setCapacity(5);
		
		
		try {
			aQueue.enqueue(test5);
		} catch ( NullPointerException e) {
			assertEquals(aQueue.size(), 4);
			
		}
		
	}
	
	/** 
	 * test for Arrayqueue Pop
	 * 
	 */
	@Test
	public void testPop() {
		aQueue.enqueue(test1);
		aQueue.enqueue(test2);
		aQueue.enqueue(test3);
		assertEquals(aQueue.size(), 3);
		aQueue.dequeue();
		assertEquals(aQueue.size(), 2);
		aQueue.dequeue();
		assertEquals(aQueue.size(), 1);
		aQueue.dequeue();
		assertEquals(aQueue.size(), 0);
		
		try {
			aQueue.dequeue();
		} catch ( NoSuchElementException e) {
			assertEquals(aQueue.size(), 0);
			
		}
		assertTrue(aQueue.isEmpty());
	}
	
	

}

