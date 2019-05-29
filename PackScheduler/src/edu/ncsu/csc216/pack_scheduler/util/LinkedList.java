/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/** Linked list class
 * @author sarahworley
 *@param <E> general
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** front of list */
	ListNode front;
	/** back of list */
	ListNode back;
	/** size of list */
	int size;

	/**
	 * Constructs linked list
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null, front, null);
		front.next = back;
		size = 0;
	}

	/*
	 * The method returns a ListIterator<E> that is positioned such that a call to
	 * ListIterator.next() will return the element at given index
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator iter = new LinkedListIterator(index);
		return iter;
	}

	/*
	 *  override LinkedList.add(int, E) to add a check for a duplicate value.
	 */
	@Override
	public void add(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}

		listIterator(index).add(element);
	}

	/*
	 * override LinkedList.set(int, E) to add a check for a duplicate value.
	 */
	@Override
	public E set(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}

		E returnedElement = get(index);
		super.set(index, element);
		return returnedElement;
	}

	/*
	 * returns size
	 */
	@Override
	public int size() {
		return size;
	}

	/** node class
	 * @author sarahworley
	 *
	 */
	private class ListNode {
		/** the data in the node */
		E data;
		/** the next node in the list */
		ListNode next;
		/** the previous node in the list */
		ListNode prev;

		/**
		 * Constructor for node
		 * 
		 * @param data of node
		 */
		ListNode(E data) {
			this.data = data;
			next = null;
			prev = null;
		}

		/**
		 * constructor
		 * 
		 * @param data of node
		 * @param prev previous node
		 * @param next next node
		 */
		ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

	}

	/**
	 * Iterator
	 * @author sarahworley
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** the index that would be returned on a call to previousIndex() */
		int previousIndex;
		/** the index that would be returned on a call to nextIndex() */
		int nextIndex;
		/** represents the ListNode that would be returned on a call to previous() */
		ListNode previous;
		/** represents the ListNode that would be returned on a call to next() */
		ListNode next;
		/**last  */
		ListNode lastRetrieved;

		/** COnstructor */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			ListNode nextNode = front.next;
			for (int i = 0; i < index; i++) {
				nextNode = nextNode.next;
			}

			this.nextIndex = index;
			this.previousIndex = index - 1;

			this.next = nextNode;
			this.previous = nextNode.prev;

			lastRetrieved = null;

		}

		/*
		 * have next true/ false
		 */
		@Override
		public boolean hasNext() {
			return (next.data != null);
		}

		/*
		 * returns the element in the prev node
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			this.lastRetrieved = next;
			next = next.next;

			previousIndex++;
			nextIndex++;
			previous = previous.next;
			return lastRetrieved.data;
		}

		/*
		 * Does it have previous?? return yes no
		 */
		@Override
		public boolean hasPrevious() {
			return (previous.data != null);
		}

		/*
		 * returns the element in the prev node
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			next = next.prev;
			previous = previous.prev;
			previousIndex--;
			nextIndex--;
			return lastRetrieved.data;
		}

		/*
		 * return the element in the next node
		 */
		@Override
		public int nextIndex() {
			if (this.next == null) {
				throw new NoSuchElementException();
			}
			return this.nextIndex;
		}

		/*
		 * previous ListNode points to the ListNode at index-1
		 */
		@Override
		public int previousIndex() {
			if (this.previous == null) {
				return -1;
			}
			return this.previousIndex;
		}

		/*
		 * Inserts the specified element into the list (optional operation). The element
		 * is inserted immediately before the element that would be returned by next(),
		 * if any, and after the element that would be returned by previous(), if any.
		 * (If the list contains no elements, the new element becomes the sole element
		 * on the list.) The new element is inserted before the implicit cursor: a
		 * subsequent call to next would be unaffected, and a subsequent call to
		 * previous would return the new element. (This call increases by one the value
		 * that would be returned by a call to nextIndex or previousIndex.)
		 * 
		 * @param e element to add
		 */
		@Override
		public void add(E e) {
			// cannot add null
			if (e == null) {
				throw new NullPointerException();
			}
			// new node to add
			ListNode node = new ListNode(e, previous, next);
			// inserted immediately before the element that would be returned by next()??
			// set prev to node??
			previous.next = node;
			next.prev = node;
			// increment
			size++;
			previousIndex++;
			nextIndex++;
			// set to null
			this.lastRetrieved = null;

		}

		/*
		 * Replaces the last element returned by next() or previous() with the specified
		 * element (optional operation). This call can be made only if neither remove()
		 * nor add(E) have been called after the last call to next or previous.
		 */
		@Override
		public void set(E e) {
			// lastRetrieved cannot be null
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			// e cannot be nnull
			if (e == null) {
				throw new NullPointerException();
			}

			lastRetrieved.data = e;

		}

		/*
		 * Removes from the list the last element that was returned by next() or
		 * previous() (optional operation). This call can only be made once per call to
		 * next or previous. It can be made only if add(E) has not been called after the
		 * last call to next or previous.
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.prev.next = next;
			next = next.next;

			size--;

		}

	}
}
