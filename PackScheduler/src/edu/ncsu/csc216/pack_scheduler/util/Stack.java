/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Stack interface
 * @author sarahworley
 *
 * @param <E> paramater
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the stack If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element to push
	 */
	void push(E element);

	/**
	 * Removes and returns the element at the top of the stack If the stack is
	 * empty, an EmptyStackException() is thrown
	 * 
	 * @return element to remove
	 */
	E pop();

	/**
	 * Returns true if the stack is empty
	 * 
	 * @return true if stack is empty
	 */
	boolean isEmpty();

	/**
	 * Sets the stack’s capacity If the actual parameter is negative or if it is
	 * less than the number of elements in the stack, an IllegalArgumentException is
	 * thrown
	 * 
	 * @param capacity to set the stack to
	 */
	void setCapacity(int capacity);

	/**
	 * Returns the number of elements in the stack
	 * 
	 * @return elements in the stack
	 */
	int size();

}