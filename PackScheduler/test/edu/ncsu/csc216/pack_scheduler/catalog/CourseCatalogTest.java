/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

/** Tests the course catalog class
 * @author sarahworley
 *
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course meeting days */
	private static final String MEETING_STRING = "TH 1:30PM-2:45PM";

	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		// Test that the CourseCatalog is initialized to an empty list
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("CSC216", "001"));
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests newCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {

		CourseCatalog cd = new CourseCatalog();

		cd.loadCoursesFromFile(validTestFile);
		assertEquals(8, cd.getCourseCatalog().length);

		cd.newCourseCatalog();
		assertEquals(0, cd.getCourseCatalog().length);
	}

	/**
	 * Test getCourseCatalog()
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cd = new CourseCatalog();
		cd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
		cd.addCourseToCatalog(NAME, TITLE, "209", CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);

		String[][] catalog = cd.getCourseCatalog();
		assertEquals(2, cd.getCourseCatalog().length);
		assertEquals(NAME, catalog[0][0]);
		assertEquals(SECTION, catalog[0][1]);
		assertEquals(TITLE, catalog[0][2]);
		assertEquals(MEETING_STRING, catalog[0][3]);

		assertEquals(NAME, catalog[1][0]);
		assertEquals("209", catalog[1][1]);
		assertEquals(TITLE, catalog[1][2]);
		assertEquals(MEETING_STRING, catalog[1][3]);

	}
	
	/**
	 * Tests loadCoursesFromFile.
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cd = new CourseCatalog();

		cd.loadCoursesFromFile(validTestFile);
		assertEquals(8, cd.getCourseCatalog().length);

		CourseCatalog cdFail = new CourseCatalog();
		try {
			cdFail.loadCoursesFromFile(invalidTestFile);
		} catch (IllegalArgumentException e) {
			assertEquals(0, cdFail.getCourseCatalog().length);
		}
	}


	/**
	 * Tests save curse to catalog
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cd = new CourseCatalog();

		// Add courses
		cd.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		cd.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		cd.addCourseToCatalog("CSC216", "Programming Concepts - Java", "601", 4, "jep", 10, "A");
		assertEquals(3, cd.getCourseCatalog().length);
		cd.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");

		// Saves to invalid file name //
		try {
			cd.saveCourseCatalog("/home/worley/actual_course_records.txt");

		} catch (IllegalArgumentException e) {
			assertEquals(3, cd.getCourseCatalog().length);
		}
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
				Scanner actScanner = new Scanner(new File(actFile));) {

			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Test removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cd = new CourseCatalog();
		// Attempts to remove from empty catalog
		assertFalse(cd.removeCourseFromCatalog(NAME, SECTION));

		cd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, cd.getCourseCatalog().length);
		assertTrue(cd.removeCourseFromCatalog(NAME, SECTION));
		String[][] courseCatalog = cd.getCourseCatalog();
		assertEquals(0, courseCatalog.length);
	}

	/**
	 * Tests addCourseToCatalog()
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cd = new CourseCatalog();

		// Test valid course
		cd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
		String[][] courseCatalog = cd.getCourseCatalog();
		assertEquals(1, cd.getCourseCatalog().length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(MEETING_STRING, courseCatalog[0][3]);

		cd = new CourseCatalog();
		cd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, "A");
		assertEquals(1, cd.getCourseCatalog().length);

		// Test invalid courses//
		cd = new CourseCatalog();
		try {
			cd.addCourseToCatalog(null, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}

		try {
			cd.addCourseToCatalog("", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}

		try {
			cd.addCourseToCatalog(NAME, "", SECTION, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}
		try {
			cd.addCourseToCatalog(NAME, TITLE, "", CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}
		try {
			cd.addCourseToCatalog(NAME, null, SECTION, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}
		try {
			cd.addCourseToCatalog(NAME, TITLE, null, CREDITS, INSTRUCTOR_ID, 11, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}

		try {
			cd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, null, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}

		try {
			cd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 11, "", START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cd.getCourseCatalog().length);
		}

	}

}
