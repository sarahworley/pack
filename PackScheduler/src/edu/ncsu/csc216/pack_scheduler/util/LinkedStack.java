/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedStack creates a stack using LinkedAbstractList
 * 
 * @author sarahworley
 * @param <E> general 
 */
public class LinkedStack<E> implements Stack<E> {

	/*
	 * New linked list 
	 * 
	 */
	private LinkedAbstractList<E> lStack;
	
	

	/**
	 * COnstructor for LinkedStack. Constructs a LinkedAbstractList and delegate to
	 * the LinkedAbstractList
	 * 
	 * @param cap capacity 
	 */
	public LinkedStack(int cap) {
		lStack = new LinkedAbstractList<E>(cap);
	}

	/*
	 * Adds the element to the top of the stack If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element to push
	 * 
	 */
	@Override
	public void push(E element) {
		lStack.add(0, element);

	}

	/*
	 * Pop the top item off the stack.
	 * 
	 * @return the top item, or throw new EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (lStack.isEmpty()) {
			throw new EmptyStackException();
		}
		return lStack.remove(0);
	}

	/*
	 * Returns true if the stack is empty
	 * 
	 * @return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return lStack.isEmpty();
	}

	/*
	 * sets the capacity
	 * 
	 * @param cap what to set the capacity to
	 */
	@Override
	public void setCapacity(int capacity) {
		lStack.setCapacity(capacity);

	}

	/*
	 * returns the size of the list
	 * 
	 * @return size
	 */
	@Override
	public int size() {
		return lStack.size();
	}

}
