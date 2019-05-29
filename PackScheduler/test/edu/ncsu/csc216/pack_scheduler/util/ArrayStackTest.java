/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/** Test for ArrayStack
 * @author sarahworley
 *
 */
public class ArrayStackTest {

	/** New ArrayStack */
	private ArrayStack<String> aStack;
	/** test string */
	private String test1 = "one";
	/** test string */
	private String test2 = "two";
	/** test string */
	private String test3 = "three";
	/** test string */
	private String test4 = "four";
	
	
	

	
	
	/**
	 * Sets up new ArrayStack before each test
	 */
	@Before
	public void setUp() {
		aStack = new ArrayStack<String>(3);
	}
	/** 
	 * test for ArrayStack constructor
	 * 
	 */
	@Test
	public void testArrayStack() {
		assertEquals(aStack.size(), 0);
		assertTrue(aStack.isEmpty());
		
	}
	
	/** 
	 * test for ArrayStack Push
	 * 
	 */
	@Test
	public void testPush() {
		
		aStack.push(test1);
		aStack.push(test2);
		aStack.push(test3);
		assertEquals(aStack.size(), 3);
		
		try {
			aStack.push(test4);
		} catch ( IllegalArgumentException e) {
			assertEquals(aStack.size(), 3);
			
		}
		aStack.setCapacity(4);
		aStack.push(test4);
		assertEquals(aStack.size(), 4);
		
	}
	
	/** 
	 * test for ArrayStack Pop
	 * 
	 */
	@Test
	public void testPop() {
		aStack.push(test1);
		aStack.push(test2);
		aStack.push(test3);
		assertEquals(aStack.size(), 3);
		aStack.pop();
		assertEquals(aStack.size(), 2);
		aStack.pop();
		assertEquals(aStack.size(), 1);
		aStack.pop();
		assertEquals(aStack.size(), 0);
		
		try {
			aStack.pop();
		} catch ( EmptyStackException e) {
			assertEquals(aStack.size(), 0);
			
		}
		assertTrue(aStack.isEmpty());
	}
	
	

}
