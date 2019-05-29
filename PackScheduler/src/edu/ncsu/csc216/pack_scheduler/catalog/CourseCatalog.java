/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Course catalog created a sorted array of courses to act as a catalog
 * 
 * @author sarahworley
 *
 */
public class CourseCatalog {

	/** Creates the arrayList for course catalog */
	private SortedList<Course> catalog;

	/**
	 * Constructor for course catalog
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Creates a new course catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs the student directory by reading in student information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of students
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * adds a course object to the catalog with values for all fields
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
	 * @return true if added
	 */

	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, 
			int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);

		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
				return false;
			}
		}
		catalog.add(course);
		return true;
	}

	/**
	 * Adds a Course with the given name, title, section, credits, instructorId, and
	 * meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credits of Course
	 * @param instructorId instructor ID of Course
	 * @param meetingDays  meeting Days of Course
	 * @param enrollmentCap capacity for enrollment
	 */

	public void addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		addCourseToCatalog(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Removes the course with the given section and name from the catalog of course
	 * with the given id. Returns true if the course is removed and false if the
	 * course is not in the list.
	 * 
	 * @param section the section of the course to remove
	 * @param name    name of the course to remove
	 * @return true if removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a specific course from catalog
	 * 
	 * @param name    name of course
	 * @param section name of section
	 * @return specific course from catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;

	}

	/**
	 * Constructs a course catalog workable course catalog
	 * 
	 * @return course catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] courses = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			courses[i][0] = course.getName();
			courses[i][1] = course.getSection();
			courses[i][2] = course.getTitle();
			courses[i][3] = course.getMeetingString();
			courses[i][4] = "" + course.getCourseRoll().getOpenSeats();
		}
		return courses;

	}

	/**
	 * Saves all courses in the directory to a file.
	 * 
	 * @param fileName name of file to save students to.
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}
