/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/** LinkedQueue class
 * @author sarahworley
 * @param <E> general param 
 *
 */
public class LinkedQueue<E> implements Queue<E> {

	/**new LinkedAbstractList */
	private LinkedAbstractList<E> lQueue;
	

	/** COnstuctor 
	 * @param capacity capacity to set 
	 */
	public LinkedQueue(int capacity) {
		lQueue = new LinkedAbstractList<E>(capacity);
	}

	/* 
	 Adds the element to the back of the Queue If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element element to be added to back of queue
	 */
	@Override
	public void enqueue(E element) {
		lQueue.add(lQueue.size(), element);

	}

	/* 
	* Removes and returns the element at the front of the Queue If the Queue is
	 * empty, an NoSuchElementException is thrown
	 * 
	 * @return the element at the front of the Queue
	 */
	@Override
	public E dequeue() {
		if (lQueue.isEmpty()) {
			throw new NoSuchElementException();
		}
		E dequeued = lQueue.remove(0);
		return dequeued;

	}

	/* 
	 * Checks if empty
	 * 
	 * @return true if the queue is empty, false if the queue is not empty
	 */
	@Override
	public boolean isEmpty() {
		return lQueue.isEmpty();
	}

	/* 
	  *  Returns size
	 * 
	 * @return elements in the queue
	 */
	@Override
	public int size() {
		return lQueue.size();
	}

	/* 
	  * Sets the capacity
	 * 
	 * @param capacity sets cap
	 */
	@Override
	public void setCapacity(int capacity) {
		lQueue.setCapacity(capacity);

	}

}
