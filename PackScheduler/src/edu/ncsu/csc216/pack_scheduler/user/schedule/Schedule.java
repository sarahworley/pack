package edu.ncsu.csc216.pack_scheduler.user.schedule;



import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * class schedule. creates a schedule of courses
 * 
 * @author sarahworley
 * 
 *
 */
public class Schedule {

	/** Schedule title */
	private String title;
	/** a custom ArrayList of Courses */
	private ArrayList<Course> schedule;

	/**
	 * Constructs a Schedule object with a default empty ArrayList and title "My
	 * Schedule"
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}

	/**
	 * Adds a course to the schedule if course exists and isn't already on schedule
	 * and does not have a schedule conflict
	 *
	 * @param course name of course
	 * 
	 * @return true if course can be added to the schedule
	 */
	public boolean addCourseToSchedule(Course course) throws IllegalArgumentException {

		if (course == null) {
			
			throw new NullPointerException();
		}

		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(course.getName())) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());

			} else {

				try {
					course.checkConflict(schedule.get(i));
				} catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
					
				}
			}
		}
		

		schedule.add(schedule.size(), course);
		return true;
	}

	/**
	 * remove the Course from the schedule and return true if the Course was removed
	 * and false if there was not a Course to remove.
	 *
	 * @param course to be removed
	 * 
	 * @return true if course can be removed from schedule
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (schedule.contains(course)) {
			schedule.remove(course);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * creates a new schedule ArrayList and reset the title to the default value.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";

	}

	/**
	 * returns a 2D array of Course information
	 * @return scheduled courses
	 */
	public String[][] getScheduledCourses() {

		String[][] scheduled = new String[schedule.size()][];
		for (int i = 0; i < scheduled.length; i++) {
			scheduled[i] = schedule.get(i).getShortDisplayArray();
		}
		return scheduled;
	}

	/**
	 * Sets the title
	 * 
	 * @param title of schedule
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}

	/**
	 * gets the title the title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/** cumulative sum that returns the total credits in the Schedule.
	 * @return number of credits
	 */
	public int getScheduleCredits() {

		if (schedule.size() == 0) {
			return 0;
		}

		int credits = 0;
		for (int i = 0; i < schedule.size(); i++) {
			credits += schedule.get(i).getCredits();
		}
		return credits;
	}
	
	/** returns true if a course can be added 
	 * @param course to check 
	 * @return true 
	 */
	public boolean canAdd(Course course) {
		if (course == null) { 
			return false; 
		}

		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(course.getName())){
				return false;
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				return false;
			}
		}
 
		return true;
	}
}
