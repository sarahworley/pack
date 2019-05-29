
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**Tests schedule class
 * @author sarahworley
 *
 */
public class ScheduleTest {

	/** Schedule*/
	Schedule schedule;
	/**Courses for testing */
	Course course0;
	/** Courses for testing*/
	Course course1;
	/** Courses for testing*/
	Course course2;
	/**Courses for testing */
	Course course3;
	/** Courses for testing*/
	Course course4;
	/** Courses for testing*/
	Course course5;
	/** Courses for testing*/
	Course course6;

	/**
	 * Sets up courses and new schedule to use for testing
	 * 
	 * @throws Exception if unable to set up 
	 */
	@Before
	public void setUp() throws Exception {
		schedule = new Schedule();
		course0 = null;
		course1 = new Course("CSC116", "Intro to Programming", "001", 3, "jdyoung2", 11, "MW", 910, 1100);
		course2 = new Course("CSC116", "Intro to Programming", "002", 3, "spbalik", 11, "MW", 1120, 1210);
		course3 = new Course("CSC216", "Programming Concepts", "001", 4, "sesmith5", 11, "TH", 1330, 1445);
		course4 = new Course("CSC226", "Discrete Mathematics for Computer Scientists", "001", 3, "tmbarnes", 11, "TH", 935,
				1025);
		course5 = new Course("CSC230", "C and Software Tool", "001", 3, "dbsturgi", 11, "MW", 1145, 1300);
		course6 = new Course("CSC111", "Programing for Beginers", "001", 4, "dbsturgi", 11, "MW", 1145, 1300);

	}
	
	/**tests constructor
	 * tests constructor
	 */
	@Test
	public void testShedule() {
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
	}
	
	/**tests add course
	 * tests add course
	 */
	@Test
	public void testAddCourse() {

		schedule.addCourseToSchedule(course5);
		schedule.addCourseToSchedule(course4);
		schedule.addCourseToSchedule(course3);
		schedule.addCourseToSchedule(course1);
		assertEquals(4, schedule.getScheduledCourses().length);

		// adding duplicate course
		try {
			schedule.addCourseToSchedule(course2);
		} catch (IllegalArgumentException e) {
			assertEquals(4, schedule.getScheduledCourses().length);
		}

		// adding course with conflict
		try {
			schedule.addCourseToSchedule(course6);
		} catch (IllegalArgumentException e) {
			assertEquals(4, schedule.getScheduledCourses().length);
		}
		// adding null course
		try {
			schedule.addCourseToSchedule(course0);
		} catch (NullPointerException e) {
			assertEquals(4, schedule.getScheduledCourses().length);
		}
	}

	/**
	 * tests remove course
	 */
	@Test
	public void testRemoveCourse() {
		schedule.addCourseToSchedule(course5);
		schedule.addCourseToSchedule(course4);
		schedule.addCourseToSchedule(course3);
		schedule.addCourseToSchedule(course1);
		assertEquals(4, schedule.getScheduledCourses().length);
		schedule.removeCourseFromSchedule(course5);
		assertEquals(3, schedule.getScheduledCourses().length);
		schedule.removeCourseFromSchedule(course6);
		assertEquals(3, schedule.getScheduledCourses().length);
		
	}
	
	/** tests set title 
	 * 
	 */
	@Test
	public void testSetTitle() {
		
		schedule.setTitle("Custom Title");
		assertEquals("Custom Title", schedule.getTitle());
		
		
	}
	
	/**
	 * tests reset schedule
	 */
	@Test
	public void testResetSchedule() {
		schedule.addCourseToSchedule(course5);
		schedule.addCourseToSchedule(course4);
		schedule.addCourseToSchedule(course3);
		schedule.addCourseToSchedule(course1);
		assertEquals(4, schedule.getScheduledCourses().length);
		schedule.resetSchedule();
		assertEquals(0, schedule.getScheduledCourses().length);
		
	}
	
	

}
