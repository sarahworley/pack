
package edu.ncsu.csc216.pack_scheduler.course.validator;



/** Invalid Transition exception throws an exception when a invalid FSM Transition occurs 
 * @author sarahworley
 *
 */
public class InvalidTransitionException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * InvalidTransitionException constructor with a String specifying a message for the
	 * Exception object
	 * 
	 * @param message exception message to be passed along to the parent
	 */
	public InvalidTransitionException(String message) {
		super(message);

	}

	/**
	 * InvalidTransitionException constructor with the default error message
	 * 
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}

}
