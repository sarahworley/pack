/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An exception created to handle time conflicts
 * 
 * @author sarah worley
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * ConflictException constructor with a String specifying a message for the
	 * Exception object
	 * 
	 * @param message exception message to be passed along to the parent
	 */
	public ConflictException(String message) {
		super(message);

	}

	/**
	 * ConflictException constructor with the default error message
	 * 
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
