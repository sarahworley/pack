package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student Class extends user Creates a student with given fields
 * 
 * @author sarahworley
 *
 */

public class Student extends User implements Comparable<Student> {
	
	/** individual max credits */
	private int maxCredits;
	
	/** all students max credits */
	public static final int MAX_CREDITS = 18;
	/** schedule  */
	private Schedule schedule;
	
	
	/**
	 * Constructor of student 
	 * @param firstName Students first name
	 * @param lastName Students last name
	 * @param id students id
	 * @param email students email
	 * @param password students password
	 * @param maxCredits max credits for this student
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	
	
	/**
	 * Constructs a general student with the default max credit hours
	 * @param firstName Students first name
	 * @param lastName Students last name
	 * @param id students id
	 * @param email students email
	 * @param password students password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}
	
	/**
	 * Returns Students max credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	/**
	 * Sets max credit for individual student
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if string is empty or null
	 */
	public void setMaxCredits(int maxCredits) {
		// checks for valid number of credits
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}
	
	/*Compares one students to another student. returns 0 if they are equal
	 *returns 1 if the student that is being compared is higher than original student
	 *returns -1 if the student being compared to the original student is lower
	 *@param s student to compare 
	 *@return a  0,1 or -1
	 */
	@Override
	public int compareTo(Student s) {
		if (s == null) {
			throw new NullPointerException();
		}

		if (this.getClass() != s.getClass()) {
			throw new ClassCastException();
		}

		int last = this.getLastName().compareTo(s.getLastName());
		int first = this.getFirstName().compareTo(s.getFirstName());
		int idCompare = this.getId().compareTo(s.getId());

		if (last < 0) {
			return -1;
		} else if (last > 0) {
			return 1;
		} else if (first < 0) {
			return -1;
		} else if (first > 0) {
			return 1;
		} else if (idCompare < 0) {
			return -1;
		} else if (idCompare > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/* 
	 * overrides toString method
	 * @return string of students information in proper format
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}


	/* 
	 * Generates Hash code
	 * @return result of hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}


	/* 
	 * Generates Equals
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}


	/**
	 * returns the schedule of courses 
	 * 
	 * @return schedule of courses 
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/** if course can be added
	 * @param course to compare 
	 * @return true if true
	 */
	public boolean canAdd(Course course) {
		if (!this.getSchedule().canAdd(course)) {
			return false; 
		}
		int credits = this.getSchedule().getScheduleCredits();
		if (credits + course.getCredits() > maxCredits) {
			return false;
		}

		return true;
	}

	
	
	
//this is here

	
	


	
	
	
	
	
	
	

}
