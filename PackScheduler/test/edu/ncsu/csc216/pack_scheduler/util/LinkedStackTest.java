/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/** Test for LinkedStack
 * @author sarahworley
 *
 */
public class LinkedStackTest {


	/** New LinkedStack */
	private LinkedStack<String> lStack;
	/** test string */
	private String test1 = "one";
	/** test string */
	private String test2 = "two";
	/** test string */
	private String test3 = "three";
	/** test string */
	private String test4 = "four";
	
	
	

	
	
	/**
	 * Sets up new LinkedStack before each test
	 */
	@Before
	public void setUp() {
		lStack = new LinkedStack<String>(3);
	}
	/** 
	 * test for LinkedStack constructor
	 * 
	 */
	@Test
	public void testArrayStack() {
		assertEquals(lStack.size(), 0);
		assertTrue(lStack.isEmpty());
		
	}
	
	/** 
	 * test for LinkedStack Push
	 * 
	 */
	@Test
	public void testPush() {
		
		lStack.push(test1);
		lStack.push(test2);
		lStack.push(test3);
		assertEquals(lStack.size(), 3);
		
		try {
			lStack.push(test4);
		} catch ( IllegalArgumentException e) {
			assertEquals(lStack.size(), 3);
			
		}
		lStack.setCapacity(4);
		lStack.push(test4);
		assertEquals(lStack.size(), 4);
		
	}
	
	/** 
	 * test for LinkedStack Pop
	 * 
	 */
	@Test
	public void testPop() {
		lStack.push(test1);
		lStack.push(test2);
		lStack.push(test3);
		assertEquals(lStack.size(), 3);
		lStack.pop();
		assertEquals(lStack.size(), 2);
		lStack.pop();
		assertEquals(lStack.size(), 1);
		lStack.pop();
		assertEquals(lStack.size(), 0);
		
		try {
			lStack.pop();
		} catch ( EmptyStackException e) {
			assertEquals(lStack.size(), 0);
			
		}
		assertTrue(lStack.isEmpty());
	}
	
	



}
