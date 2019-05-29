/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * faculty test
 * 
 * @author sarahworley
 *
 */

public class Faculty extends User {

	/** max number of courses */
	private int maxCourses;

	/** minimum number of courses */
	public static final int MIN_COURSES = 1;

	/** maximum number of courses */
	public static final int MAX_COURSES = 3;

	/**
	 * faculty schedule
	 */
	private FacultySchedule schedule;

	/**
	 * constructor
	 * 
	 * @param firstName  first name
	 * @param lastName   last name
	 * @param id         id
	 * @param email      emails
	 * @param password   password
	 * @param maxCourses max courses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);

		schedule = new FacultySchedule(id);
	}

	/**
	 * get that facuty members schedule
	 * 
	 * @return the schedule
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * checks if courses are over max courses allowed
	 * 
	 * @return true if courses are overloaded
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;

	}

	/**
	 * Gets the max courses for the faculty
	 * 
	 * @return maxCourses the maximum courses for the faculty member
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * sets max
	 * 
	 * @param newMax new max
	 */
	public void setMaxCourses(int newMax) {
		if (newMax < 1 || newMax > 3) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = newMax;

	}

	/*
	 * hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/*
	 * equals
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ getMaxCourses();
	}

}
