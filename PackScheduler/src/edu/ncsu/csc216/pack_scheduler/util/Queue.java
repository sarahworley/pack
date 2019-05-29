/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue interface
 * 
 * @author sarahworley
 *@param <E> general
 */
public interface Queue<E> {

	/**
	 * Adds the element to the back of the Queue If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element element to be added to back of queue
	 */
	void enqueue(E element);

	/**
	 * Removes and returns the element at the front of the Queue If the Queue is
	 * empty, an NoSuchElementException is thrown
	 * 
	 * @return the element at the front of the Queue
	 */
	E dequeue();

	/**
	 * Checks if empty
	 * 
	 * @return true if the queue is empty, false if the queue is not empty
	 */
	boolean isEmpty();

	/**
	 *  Returns size
	 * 
	 * @return elements in the queue
	 */
	int size();

	/**
	 * Sets the capacity
	 * 
	 * @param capacity sets cap
	 * 
	 */
	void setCapacity(int capacity);

}
