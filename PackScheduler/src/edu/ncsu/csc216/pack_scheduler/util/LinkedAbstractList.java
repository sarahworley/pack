
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom linkedAbstractList
 * 
 * @author sarahworley
 * @param <E> general 
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** front of list */
	private ListNode front;
	/** end of list */
	private ListNode back;
	/** size of the list */
	private int size;
	/** capacity of the list */
	private int capacity;

	/**
	 * Constructor initializes the state.
	 * 
	 * @param capacity capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		this.front = null;
		this.size = 0;
		this.capacity = capacity;
		this.back = front;
		back = front;
		if (capacity > 0) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * sets the capacity
	 * 
	 * @param cap what to set the capacity to
	 */
	public void setCapacity(int cap) {
		if (cap < 0 || cap < this.size) {
			throw new IllegalArgumentException();
		}
		this.capacity = cap;
	}

	/*
	 * add element based on position
	 * 
	 * @param position to remove
	 * 
	 * @param element to remove
	 */
	@Override
	public void add(int position, E element) {
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new NullPointerException();
		}

		for (int i = 0; i < size; i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}

		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		if (front == null) {
			front = new ListNode(element);
			back = front;
		}

		if (position == 0)
			front = new ListNode(element, front);
		else if (front != null && position > 0) {
			ListNode traveler = front;
			while (traveler != null && position > 1) {
				traveler = traveler.next;
				position--;
			}
			if (traveler != null) {
				traveler.next = new ListNode(element, traveler.next);
			}
		}
		back = front;
		while (back.next != null) {
			back = back.next;
		}
		size++;
	}

	/**
	 * remove an element from the list at a certain position
	 * 
	 * @param position to remove an element from
	 * @return element removed
	 */
	public E remove(int position) {
		if (front == null)
			throw new IndexOutOfBoundsException();

		if (position < 0 || position >= size()) {
			throw new IndexOutOfBoundsException();
		}

		if (position == 0) {
			E element = front.data;
			front = front.next;
			size--;
			return element;
		}

		ListNode temp = front;
		ListNode previous = null;
		for (int i = 0; i < position; i++) {
			previous = temp;
			temp = temp.next;
		}
		previous.next = temp.next;
		size--;
		return temp.data;
	}

	/*
	 * Returns an element from the list at a certain position
	 * 
	 * @param position to return
	 * 
	 * @return current
	 */
	@Override
	public E get(int position) {
		ListNode current = front;
		if (position < 0 || position >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (position == 0) {
			return current.data;

		} else {
			for (int i = 0; i < position; i++) {
				current = current.next;
			}

			return current.data;
		}
	}

	/*
	 * returns the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * sets a certain position of the list to a specific element
	 * 
	 * @returns toReplace element to replace
	 */
	@Override
	public E set(int position, E element) {
		ListNode current = front;
		E toReplace;
		if (element == null) {
			throw new NullPointerException();
		}

		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}

		if (position < 0 || position >= size()) {
			throw new IndexOutOfBoundsException();
		}

		current = front;
		for (int i = 0; i < position; i++) {
			current = current.next;
		}
		toReplace = current.data;
		current.data = (E) element;

		return toReplace;
	}
	
	
	
	/**
	 * Inner class for LinkedAbstractList
	 * 
	 * @author sarahworley
	 *
	 */
	private class ListNode {
		/** the data in the node */
		private E data;
		/** the next node in the list */
		private ListNode next;

		/**
		 * Constructor
		 * 
		 * @param data
		 */
		public ListNode(E data) {
			this.data = data;
		}

		/**
		 * constructor
		 * 
		 * @param data
		 * @param next
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
