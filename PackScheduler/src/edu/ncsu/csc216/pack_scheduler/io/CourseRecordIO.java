/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.PrintStream;

/**
 * CourseRecordIO class
 * 
 * @author sarahworley
 *
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;

	}

	/**
	 * Processes each course by reading from a file
	 * 
	 * @param next next line
	 * @return course returns an arranged time or set time course
	 * @throws FileNotFoundException
	 */
	private static Course readCourse(String line) {
		Scanner s = new Scanner(line);
		s.useDelimiter(",");
		String name = "";
		String title = "";
		String section = "";
		int credits = 0;
		String id = null;
		

		int enroll = 0;
		String meetDays = "";
		int start = 0;
		int end = 0;

		try {
			name = s.next();
			title = s.next();
			section = s.next();
			credits = s.nextInt();
			if (!s.hasNextInt()) {
				id = s.next();

			}
			enroll = s.nextInt();
			meetDays = s.next();

			// handles arranged classes
			if (meetDays.equals("A")) {
				if (s.hasNext()) {
					s.close();
					throw new IllegalArgumentException();

				} else {
					s.close();
					// Course arranged = null;
					Course arranged = new Course(name, title, section, credits, null, enroll, meetDays);
					Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(id);

					if (f != null) {
						// arranged.setInstructorId(id);
						f.getSchedule().addCourseToSchedule(arranged);

					}

					return arranged;
				}
			} else {
				// handles set time classes

				start = s.nextInt();
				end = s.nextInt();
				s.close();

				Course set = new Course(name, title, section, credits, null, enroll, meetDays, start, end);
				Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(id);

				if (f != null) {

					// set.setInstructorId(id);
					f.getSchedule().addCourseToSchedule(set);

				}

				return set;

			}

		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Writes information about each course
	 * 
	 * @param fileName name of file
	 * @param courses  the courses
	 * @throws FileNotFoundException if file cannot be found
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws FileNotFoundException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
