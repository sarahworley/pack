
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface that handles time conflicts of different types of activities
 * 
 * @author sarahworley
 *
 */
public interface Conflict {

	/**
	 * Accepts An activity and throws a conflict exception if the time of the
	 * activity overlaps another activity
	 * 
	 * @param possibleConflictingActivity Activity that is being check for a time
	 *                                    conflict
	 * @throws ConflictException is thrown when there is a time conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
		

}
