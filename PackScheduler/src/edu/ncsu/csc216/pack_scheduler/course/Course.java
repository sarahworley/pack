/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class, subclass of Activity. Sets specific details about a course
 * 
 * 
 * @author sarah Worley
 *
 */
public class Course extends Activity implements Comparable<Course> {

	/** Section length. */
	private static final int SECTION_LENGTH = 3;
	/** maximum name length. */
	private static final int MAX_NAME_LENGTH = 6;
	/** Minimum name length */
	private static final int MIN_NAME_LENGTH = 4;
	/** max credit hours. */
	private static final int MAX_CREDITS = 5;
	/** Minimum credit hours */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;

	private CourseRoll roll;

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credits of Course
	 * @param instructorId instructor ID of Course
	 * @param meetingDays  meeting Days of Course
	 * @param enrollmentCap capacity for enrollment
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Constructs a course object with values for all fields
	 * 
	 * @param name         Name of Course
	 * @param title        Title of Course
	 * @param section      Section of Course
	 * @param credits      Credits of Course
	 * @param instructorId Instructor ID of Course
	 * @param meetingDays  Meeting Days of Course
	 * @param startTime    Start time of Course
	 * @param endTime      End time of Course
	 * @param enrollmentCap capacity for enrollment
	 */

	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Returns the courses name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets courses name
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentsException of name is null or if name length is larger
	 *                                   than MAX_NAME_LENGTH or smaller than
	 *                                   MIN_NAME_LENGTH
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name");
		}
		try {
			CourseNameValidator c = new CourseNameValidator();
			c.isValid(name);
		} catch (InvalidTransitionException e1) {
			throw new IllegalArgumentException("Invalid course name");
		}

		this.name = name;
	}

	/**
	 * Returns the course section
	 * 
	 * @return the section
	 */
	public String getSection() {

		return section;
	}

	/**
	 * Sets the course section
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null, not equal to section
	 *                                  length or is not numeric
	 */
	public void setSection(String section) {
		int sum = 0;
		if (section == null) {
			throw new IllegalArgumentException("Invalid Section Number");
		}
		for (int i = 0; i < section.length(); i++) {
			char a = section.charAt(i);
			if (!Character.isDigit(a)) {
				throw new IllegalArgumentException("Invalid Section Number");
			}
			sum++;
		}
		if (sum != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid Section Number");
		}
		this.section = section;
	}

	/**
	 * Returns the course credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the courses credits
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credit is not a number or if the number
	 *                                  of credits is greater than 5 or less than 1
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credit hours");
		}
		this.credits = credits;
	}

	/**
	 * Returns the course Instructor ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the course Instructor ID
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is null or empty
	 */
	public void setInstructorId(String instructorId) {
		
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/*
	 * eclipse generated hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/*
	 * eclipse generated equals method
	 * 
	 * @returns true if course and obj are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/*
	 * Returns an array of Strings containing name, section, title, and meeting
	 * string
	 * 
	 * @return shortArray
	 */
	@Override
	public String[] getShortDisplayArray() {
		int seats = roll.getOpenSeats();
		String[] shortArray = { this.name, this.section, this.getTitle(), this.getMeetingString(), Integer.toString(seats) };
		return shortArray;
	}

	/*
	 * Returns an array of Strings containing name, section, title, credits,
	 * instructorId, meeting string, and empty string.
	 * nee
	 * @return longArray
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = { this.name, this.section, this.getTitle(), "" + this.credits, this.instructorId,
				this.getMeetingString(), "" };
		return longArray;

	}

	/**
	 * sets meeting days for Course overrides Activity’s setMeetingDays().
	 * 
	 * @param meetingDays the days the course will meet
	 * @throws IllegalArgumentException if if the parameter is null or an empty
	 *                                  String or if meeting days consist of any
	 *                                  characters other than ‘M’, ‘T’, ‘W’, ‘H’,
	 *                                  ‘F’, or ‘A’
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (meetingDays.contains("A") && meetingDays.length() != 1) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char a = meetingDays.charAt(i);
			if (!(a == 'M' || a == 'T' || a == 'W' || a == 'H' || a == 'F' || a == 'A')) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/*
	 * Checks to see if course is a duplicate of an activity already in the schedule
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			return this.getName().toLowerCase().equals(course.getName().toLowerCase());
		} else {
			return false;
		}

	}

	@Override
	public int compareTo(Course o) {
		if (o == null) {
			throw new NullPointerException();
		}

		if (this.getClass() != o.getClass()) {
			throw new ClassCastException();
		}

		int nameCompare = this.getName().compareTo(o.getName());
		int sectionCompare = this.getSection().compareTo(o.getSection());

		if (nameCompare < 0) {
			return -1;
		} else if (nameCompare > 0) {
			return 1;
		} else if (sectionCompare < 0) {
			return -1;
		} else if (sectionCompare > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * roll for this course 
	 * @return the roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
}
