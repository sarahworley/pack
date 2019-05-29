package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Contracts a course roll
 * 
 * @author sarahworley
 *
 */
public class CourseRoll {

	/** Linked list roll */
	private LinkedAbstractList<Student> roll;
	/** enrollment cap for a class */
	private int enrollmentCap;

	/** min class size */
	private static final int MIN_ENROLLMENT = 10;
	/** max class size */
	private static final int MAX_ENROLLMENT = 250;

	private static final int WAITLIST = 10;

	private LinkedQueue<Student> waitlist;

	private Course c;

	/**
	 * Constructor for roll
	 * 
	 * @param enrollmentCap roll’s enrollment capacity
	 * @param course        course to be add to scheduled
	 * 
	 */
	public CourseRoll(Course course, int enrollmentCap) {
		if (course == null || enrollmentCap > MAX_ENROLLMENT || enrollmentCap < MIN_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		waitlist = new LinkedQueue<Student>(WAITLIST);
		c = course;
		this.enrollmentCap = enrollmentCap;
		roll = new LinkedAbstractList<Student>(enrollmentCap);

	}

	/**
	 * Returns enrollmentCap h
	 * 
	 * @return enrollmentCap cap for enrollment
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}

	/**
	 * gets the number or open seats in a class room
	 * 
	 * @return open seats
	 */
	public int getOpenSeats() {
		return (getEnrollmentCap() - roll.size());
	}

	/**
	 * sets the enrollmentCap 8
	 * 
	 * @param newEnrollmentCap roll’s enrollment capacity
	 */
	public void setEnrollmentCap(int newEnrollmentCap) {
		if (newEnrollmentCap < MIN_ENROLLMENT || newEnrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (newEnrollmentCap < roll.size()) {
			throw new IllegalArgumentException();
		}
		this.enrollmentCap = newEnrollmentCap;
		roll.setCapacity(newEnrollmentCap);

	}

	/**
	 * Adds the student to the end of the roll
	 * 
	 * @param student ot enroll
	 */
	public void enroll(Student student) {

		if (student == null || roll == null) {
			throw new IllegalArgumentException("Student cannot be enrolled in this course.");
		}

		if (roll.contains(student)) {
			throw new IllegalArgumentException();
		}
		if (roll.size() == enrollmentCap) {
			if (waitlist.size() < WAITLIST) {
				waitlist.enqueue(student);
			} else {
				throw new IllegalArgumentException();
			}
		} else {

			roll.add(roll.size(), student);

		}
	}

	/**
	 * Removes a student from the roll
	 * 
	 * @param student to remove
	 */
	public void drop(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (roll == null || roll.size() == 0) {
			throw new IllegalArgumentException();
		}
		if (roll.contains(student)) {
			try {
				roll.remove(student);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}

			if (!waitlist.isEmpty()) {
				Student waitlisted = waitlist.dequeue();
				roll.add( waitlisted);
				waitlisted.getSchedule().addCourseToSchedule(c);
			}
		} else {
			for (int i = 0; i < waitlist.size(); i++) {
				Student waiting = waitlist.dequeue();
				if (!waiting.equals(student)) {
					waitlist.enqueue(waiting);
				}
			}
		}

	}

	/**
	 * Returns true if a student can enroll in a class
	 * 
	 * @param student being checked
	 * @return true if they can enroll
	 */
	public boolean canEnroll(Student student) {

		if ((getOpenSeats() == 0 && getNumberOnWaitlist() == 10) || roll.contains(student)) {
			return false;
		}

		return true;

	}

	/**
	 * Number of people on waitlist
	 * 
	 * @return number on wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
	/**gets course roll info
	 * @return list of students 
	 */
	public String[][] get2DArray() {
		String[][] returnArray = new String[roll.size()][3];
		for(int i = 0; i < roll.size(); i++) {
			Student s = roll.get(i);
			returnArray[i][0] = s.getFirstName();
			returnArray[i][1] = s.getLastName();
			returnArray[i][2] = s.getId();
		}
		return returnArray;
	}
}
