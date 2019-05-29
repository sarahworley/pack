/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;


import org.junit.Test;

/**faculty test
 * @author sarahworley
 *
 */
public class FacultyTest {

	/** facultys first name */
	private static final String FIRST_NAME = "sarah";
	/** facultys last name */
	private static final String LAST_NAME = "worley";
	/** facultys user id */
	private static final String ID = "sworley";
	/** facultys email */
	private static final String EMAIL = "sw@ncsu.edu";
	/** facultys password */
	private static final String PASSWORD = "password";
	/** Automatic max credits for any faculty */
	private static final int MAX_COURSES = 2;

	/**
	 * Tests the faculty constructor with all field parameters.
	 */
	@Test
	public void testFacultyStringStringStringStringStringInt() {
		// Testing a valid faculty
		Faculty faculty1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		// testing valid construction
		assertEquals(FIRST_NAME, faculty1.getFirstName());
		assertEquals(LAST_NAME, faculty1.getLastName());
		assertEquals(EMAIL, faculty1.getEmail());
		assertEquals(PASSWORD, faculty1.getPassword());
		assertEquals(MAX_COURSES, faculty1.getMaxCourses());

		// TESTING NULL faculty first name
		User s = null; // Initialize a faculty reference to null
		try {
			s = new Faculty(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		try {// TESTING NULL faculty LAST NAME
			s = new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL faculty ID
			s = new Faculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL faculty EMAIL
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL faculty PASSWORD
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests the faculty constructor without default MAX_CREDITS
	 */
	@Test
	public void testfacultyStringStringStringStringString() {
		// Test Valid faculty//
		Faculty s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, s1.getFirstName());
		assertEquals(LAST_NAME, s1.getLastName());
		assertEquals(ID, s1.getId());
		assertEquals(EMAIL, s1.getEmail());
		assertEquals(PASSWORD, s1.getPassword());
		assertEquals(MAX_COURSES, s1.getMaxCourses());

		// TESTING NULL faculty
		User s = null; // Initialize a faculty reference to null
		try {// TESTING NULL faculty FIRST NAME
			s = new Faculty(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL faculty LAST NAME
			s = new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL faculty ID
			s = new Faculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// ID test for empty string//
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD,  MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		try {// TESTING NULL Faculty EMAIL
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL Faculty PASSWORD
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests SetFirstName()
	 */
	@Test
	public void testSetFirstName() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_COURSES, s.getMaxCourses());
		try {
			s.setFirstName(null);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_COURSES, s.getMaxCourses());
		}
		// TESTS FOR EMPTY STRING
		try {
			s.setFirstName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_COURSES, s.getMaxCourses());
		}

	}

	/**
	 * Tests SetLastName()
	 */
	@Test
	public void testSetLastName() {

		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals( MAX_COURSES, s.getMaxCourses());

		try {
			s.setLastName(null);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses(), MAX_COURSES);
		}
		// TESTS FOR EMPTY STRING
		try {
			s.setLastName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses());
		}

	}

	/**
	 * Tests SetEmail()
	 */
	@Test
	public void testSetEmail() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals( MAX_COURSES, s.getMaxCourses());

		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses());
		}
		// TESTS FOR EMPTY STRING
		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses());

		}
		try { // doesn’t contain an ‘@’ character
			s.setEmail("notanemail.com");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses());

		}
		try { // email doesn’t contain a ‘.’ character
			s.setEmail("notanemail@com");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses());

		}
		try { // index of the last ‘.’ character in the email string is earlier than the index
				// of the first ‘@’
			s.setEmail("first.last@address");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses());

		}

	}

	/**
	 * Tests SetPassword()
	 */
	@Test
	public void testSetPassword() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals( MAX_COURSES, s.getMaxCourses());
		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_COURSES, s.getMaxCourses());
		}
		// TESTS FOR EMPTY STRING
		try {
			s.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals( MAX_COURSES, s.getMaxCourses());

		}

	}

	/**
	 * Tests SetMaxCredits()
	 */
	@Test
	public void testSetMaxCredits() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals( MAX_COURSES, s.getMaxCourses());

		try { // tests for credits under 3
			s.setMaxCourses(0);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_COURSES, s.getMaxCourses());
		}
		try { // tests for credits over 18
			s.setMaxCourses(4);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_COURSES, s.getMaxCourses());
		}
	}

	/**
	 * Tests toString()
	 */
	@Test
	public void testToString() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD,  MAX_COURSES);
		String s2 = "sarah,worley,sworley,sw@ncsu.edu,password,2";

		assertEquals(s2, s1.toString());

		User s3 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

		assertEquals(s2, s3.toString());
	}
	/**
	 * tests hash code
	 */
	@Test
	public void testhash() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		// Objects are different
		//User s3 = new Faculty("differnt", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		//User s4 = new Faculty(FIRST_NAME, "different", ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(s1.hashCode(), s2.hashCode());
		assertEquals(s2.hashCode(), s1.hashCode());
		
	}
	/**
	 * Tests equalsObject()
	 */
	@Test
	public void testEqualsObject() {
		// Objects are equal
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		// Objects are different
		User s3 = new Faculty("differnt", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s4 = new Faculty(FIRST_NAME, "different", ID, EMAIL, PASSWORD, MAX_COURSES);
		User s5 = new Faculty(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_COURSES);
		User s6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "differnt@email.com", PASSWORD, MAX_COURSES);
		User s7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", MAX_COURSES);
		User s8 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", 2);
		
		
		// Test for equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));

		// Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s2.equals(s8));
		assertFalse(s2.equals(s7));
		assertFalse(s2.equals(s6));
		assertFalse(s2.equals(s5));
		assertFalse(s2.equals(s4));
		assertFalse(s2.equals(s3));
	}

	
	
}
