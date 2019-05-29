/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedListRecursive
 * 
 * @author sarahworley
 * @param <E> general
 */
public class LinkedListRecursive<E> {
	/**
	 * Size of list
	 */
	private int size;
	/**
	 * front of list
	 */
	private ListNode front;

	/**
	 * Constuctor initializes the state to represent an empty list.
	 */
	public LinkedListRecursive() {
		this.size = 0;
		front = null;
	}

	/** true if empty 
	 * @return true if empty 
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/** list size
	 * @return size
	 */
	public int size() {
		return this.size;
	}

	/**functionality that adds an element to the end of the list.
	 * @param element to add
	 * @return true if it canbe added
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}

		add(size(), element);
		return true;
	}

	/**
	 * functionality that checks if an element already exists in the list.
	 * 
	 * @param element to check
	 * @return true or false
	 */
	public boolean contains(E element) {
		if (element == null) {
			return false;
		}
		if (front == null) {
			return false;
		} else if (front.data == element) {
			return true;
		} else {
			return front.contains(element);
		}
	}

	/** functionality that adds an element at the specified index 
	 * @param index place to add
	 * @param element to add
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (front != null && front.contains(element)) {
			throw new IllegalArgumentException();
		}
		if (index == 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			front.add(index - 1, element);
		}
	}

	/** functionality that gets an element at an index.
	 * @param index to get 
	 * @return element gotten 
	 */
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			E got = front.data;
			return got;
		} else {
			E got1 = front.get(index);
			return got1;
		}

	}

	/**
	 * functionality that removes the provided element
	 * 
	 * @param element to remove
	 * @return removed element 
	 */
	public boolean remove(E element) {
		if(element == null || isEmpty()) {
			return false;
		}
			if (front.data.equals(element)) {
				front = front.next;
				size--;
				return true;
			} else {
				return front.remove(element);
			}
		
		
	}

	/**
	 * functionality that sets an element at an index
	 * 
	 * @param index to set
	 * @param element to set
	 * @return set element 
	 */
	public E set(int index, E element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (index == 0) {
			E setting = front.data;
			front.data = element;
			return setting;
		}
		return front.set(index - 1, element);

	}

	/**
	 * functionality that removes an element at an index.
	 * 
	 * @param index to remove from
	 * @return element removed
	 */
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			E returnMe = front.data;
			front = front.next;
			size--;
			return returnMe;
		} else {
			return front.remove(index - 1);
		}

	}

	/**
	 * inner class of LinkedListRecursive
	 * 
	 * @author sarahworley
	 *
	 */
	private class ListNode {
		/**
		 * he data in the node
		 */
		E data;
		/**
		 * the next node in the list
		 */
		ListNode next;

		/**
		 * constructor should assign the parameter to the associated fields:
		 * 
		 * @param data he data in the node
		 * @param next next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * functionality that gets an element at an index
		 * 
		 * @param index to get
		 * @return gotten index
		 */
		private E get(int index) {
			if (index == 1) {
				return next.data;
			} else {
				index--;
				return next.get(index);
			}

		}

		/**
		 * functionality that checks if an element already exists in the list.
		 * 
		 * @param element
		 * @return
		 */
		private boolean contains(E element) {
			if (next == null) {
				return false;
			} else if (next.data == element) {
				return true;
			} else {
				return next.contains(element);
			}

		}

		/**
		 * Private add method
		 * 
		 * @param index   index of where to add
		 * @param element element to add
		 */
		private void add(int index, E element) {
			int count = 0;
			if (index == count) {
				next = new ListNode(element, next);
				size++;
			} else {
				index--;
				next.add(index, element);
			}
		}

		/**
		 * functionality that sets an element at an index.
		 * 
		 * @param index   index to set
		 * @param element element to set
		 * @return the set element
		 */
		private E set(int index, E element) {
			int count = 0;
			if (index == count) {
				E returnMe = next.data;
				next.data = element;
				return returnMe;
			} else {
				index--;
				return next.set(index, element);
			}
		}

		/**
		 * functionality that removes an element at an index.
		 * 
		 * @param index to remove
		 * @return removed element
		 */
		private E remove(int index) {
			int count = 0;
			if (next != null) {
				if (index == count) {
					E returnMe = next.data;
					next = next.next;
					size--;
					return returnMe;
				} else {
					index--;
					next.remove(index);
				}
			}
			return null;
		}

		/**
		 * functionality that removes the provided element.
		 * 
		 * @param element
		 * @return
		 */
		private boolean remove(E element) {
			if (next != null) {
				if (next.data.equals(element)) {
					next = next.next;
					size--;
					return true;
				} else {
					next.remove(element);
				}
			}
			return false;
		}

	}

}
