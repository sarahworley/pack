/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayStack creates a stack using arrayList
 * 
 * @author sarahworley
 *
 * @param <E> parameter
 */
public class ArrayStack<E> implements Stack<E> {

	/*
	 * new array list 
	 * 
	 */
	private ArrayList<E> aStack;
	/*
	 * Array stack capacity
	 * 
	 */
	private int cap;

	
	
	/**
	 * Constructs the stack using arraylist sets the capacity
	 * @param capacity capacity to set
	 */
	public ArrayStack(int capacity) {
		aStack = new ArrayList<E>();
		setCapacity(capacity);
	}

	/*
	 * Adds the element to the top of the stack If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element to push
	 */
	@Override
	public void push(E element) {
		if (size() == cap) {
			throw new IllegalArgumentException();
		}
		aStack.add(0, element);

	}

	/*
	 * Removes and returns the element at the top of the stack If the stack is
	 * empty, an EmptyStackException() is thrown
	 * 
	 * @return element to remove
	 */
	@Override
	public E pop() {
		if (aStack.isEmpty()) {
			throw new EmptyStackException();
		}
		return aStack.remove(0);
	}

	/*
	 * Returns true if the stack is empty
	 * 
	 * @return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return aStack.isEmpty();
	}

	/*
	 * Sets the stack’s capacity If the actual parameter is negative or if it is
	 * less than the number of elements in the stack, an IllegalArgumentException is
	 * thrown
	 * 
	 * @param capacity to set the stack to
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}

		this.cap = capacity;

	}

	/**
	 * Returns the number of elements in the stack
	 * 
	 * @return elements in the stack
	 */
	@Override
	public int size() {
		return aStack.size();
	}

}
