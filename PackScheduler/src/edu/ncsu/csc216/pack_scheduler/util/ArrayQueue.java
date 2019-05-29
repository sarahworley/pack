/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/** ArrayQueue class
 * @author sarahworley
 *@param <E> general 
 */
public class ArrayQueue<E> implements Queue<E> {

	/** new array list*/
	private ArrayList<E> aQueue;
	
	int cap;
	

	/** COnstuctor 
	 * @param capacity initial capacity 
	 */
	public ArrayQueue(int capacity) {
		aQueue = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds the element to the back of the Queue If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element element to be added to back of queue
	 */
	@Override
	public void enqueue(E element) {
		if (aQueue.size() == cap) {
			throw new IllegalArgumentException();
		}
		
		aQueue.add(aQueue.size(), element);

	}

	/**
	 * Removes and returns the element at the front of the Queue If the Queue is
	 * empty, an NoSuchElementException is thrown
	 * 
	 * @return the element at the front of the Queue
	 */
	@Override
	public E dequeue() {
		if (aQueue.isEmpty()) {
			throw new NoSuchElementException();
		}
		E dequeued = aQueue.remove(0);
		return dequeued;

	}

	/**
	 * Checks if empty
	 * 
	 * @return true if the queue is empty, false if the queue is not empty
	 */
	@Override
	public boolean isEmpty() {
		
		return aQueue.isEmpty();
	}

	/**
	 * Gets size of queue
	 * 
	 * @return elements in the queue
	 */
	@Override
	public int size() {
		
		return aQueue.size();
	}

	/**
	 * Sets the capacity
	 * 
	 * @param capacity to set 
	 * 
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < aQueue.size()) {
			throw new IllegalArgumentException();
		}
		this.cap = capacity;

	}

}
