/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * custom implementation of an array list that doesn’t allow for null elements
 * or duplicate elements as defined by the equals() method
 * 
 * @author sarahworley
 * @param <E> typeS
 */
public class ArrayList<E> extends AbstractList<E> {

	/** initializing the list size */
	public static final int INIT_SIZE = 10;
	/** array of type E */
	private E[] list;
	/** size of the list */
	private int size;

	/**
	 * creates an empty ArrayList (e.g., size is zero) with a list of capacity
	 * INIT_SIZE.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}

	/**
	 * Adds an element to the list at a specific index If index is larger than size
	 * capacity, then the list capacity is doubled
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object e) {

		// checks for null
		if (e == null) {
			throw new NullPointerException();
		}

		// checks for duplicate
		for (int i = 0; i < size(); i++) {
			if (list[i].equals(e)) {
				throw new IllegalArgumentException();
			}
		}

		// checks for out of bounds index
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		// If at the end
		if (index == size()) {
			list[index] = (E) e;
			// Beginning or middle
		} else {
			for (int i = size(); i > index; i--) {
				list[i] = list[i - 1];
			}
			list[index] = (E) e;
		}
		size++;

		if (size() == list.length) {
			growArray();
		}
	}

	/**
	 * doubles the capacity
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {

		E[] temp = (E[]) new Object[list.length * 2];
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}

	/**
	 * removes elements at the given index
	 */
	@Override
	public E remove(int index) {
		E elementToRemove = list[index];
		// If the index is out of range
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		// If at the end
		if (index == size() - 1) {
			list[index] = null;
			size--;
			return elementToRemove;
		// Beginning or middle
		} else {
			for (int i = index; i < size(); i++) {
				list[i] = list[i + 1];
			}
		}
		list[size() - 1] = null;
		size--;
		return elementToRemove;
	}

	/**
	 * element at the specified index is replaced with the given element
	 */
	@Override
	public E set(int index, E e) {

		E elementToReplace = list[index];

		// If the element to set is null
		if (e == null) {
			throw new NullPointerException();
		}

		// If the element to set is a duplicate of an element already in the list
		for (int i = 0; i < size(); i++) {
			if (list[i].equals(e)) {
				throw new IllegalArgumentException();
			}
		}
		// If the index is out of range
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		list[index] = e;
		return elementToReplace;

	}

	/**
	 * Helper method to check contents of the list after adding elements.
	 * 
	 * @return i element at i
	 */
	@Override
	public E get(int i) {
		if (i >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[i];
	}

	/**
	 * Returns size of list
	 * 
	 * @return size of list
	 */
	@Override
	public int size() {
		return size;
	}
}
