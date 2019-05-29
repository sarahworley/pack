package edu.ncsu.csc216.pack_scheduler.course.validator;

/** class that checks if a course name is valid
 * @author sarahworley
 *
 */
public class CourseNameValidator {
	/** letterState*/
	private final State letterState = new LetterState();
	/** numberState*/
	private final State numberState = new NumberState();
	/** suffixState */
	private final State suffixState = new SuffixState();
	/**initialState */
	private final State initialState = new InitialState();
	/** initialState*/
	private State currentState = initialState;
	/** tells if course name is valid or not */
	private boolean validEndState;
	/** number of letters in course name */
	private int letterCount;
	/** number of numbers is course name  */
	private int digitCount;

	/**
	 * Uses states to determine if a course name is valid or not
	 * 
	 * @param courseName course being checked
	 * @return true or false
	 * @throws InvalidTransitionException if the transition is illegal
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		char x;
		digitCount = 0;
		letterCount = 0;
		currentState = initialState;

		for (int i = 0; i < courseName.length(); i++) {
			x = courseName.charAt(i);
			
			if (Character.isLetter(x)) {
				currentState.onLetter();
			} else if (Character.isDigit(x)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		if (currentState != letterState) {
			validEndState = true;
		}
		
		

		return validEndState;
	}

	/**
	 * gets the number of letters in a course name
	 * 
	 * @return letterCount
	 */
	public int getLetterCount() {
		return letterCount;
	}

	/**
	 * gets the number of numbers in a course name
	 * 
	 * @return numberCount
	 */
	public int getNumberCount() {
		return digitCount;
	}

	/**
	 * State abstract class
	 * 
	 * @author sarahworley
	 *
	 */
	abstract class State {
		/**
		 * abstract method for handling a letter input
		 */
		abstract void onLetter() throws InvalidTransitionException;

		/**
		 * abstract method for handling a digit input
		 */
		abstract void onDigit() throws InvalidTransitionException;

		/**
		 * concrete method for handling any other input.
		 * 
		 * @throws InvalidTransitionException
		 */
		void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * InitialState
	 * 
	 * @author Sarah Worley
	 *
	 */
	private class InitialState extends State {

		/**
		 * Checks for first letter. updates state adds to letter count
		 */
		@Override
		public void onLetter() {
			letterCount++;
			currentState = letterState;
		}

		/**
		 * If first char is a number
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * LetterState
	 * 
	 * @author Sarah Worley
	 *
	 */
	private class LetterState extends State {
		/** Max number of letters that can be in a course name */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 * Counts letters, if more than 4 :
		 * 
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			
			if (getLetterCount() < MAX_PREFIX_LETTERS) {
				letterCount++;
				currentState = letterState;
			} else if (getLetterCount() == MAX_PREFIX_LETTERS ) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			
		}
		/**
		 * Counts digits if more than 3:
		 * 
		 * @throws InvalidTransitionException
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			currentState = numberState;
			digitCount++;

		}
	}

	/**
	 * Defines the NumberState, which is a State
	 * 
	 * @author Sarah Worley
	 *
	 */
	private class NumberState extends State {
		private static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * Counts numbers, if more than three :
		 * 
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
				currentState = numberState;
				if (digitCount == 3) {
					validEndState = true;
				}
			} else if (digitCount == 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}

		/**
		 * If a letter comes after three numbers state is changes to suffix state other
		 * wise
		 * 
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = suffixState;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
	}

	/**
	 * SuffixState
	 * 
	 * @author Sarah Worley
	 *
	 */
	private class SuffixState extends State {
		
		/**
		 * handles a number after the suffix
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}
		/**
		 * handles a letter after the suffix
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		

	}

}
