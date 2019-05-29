package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the student class methods for all getters have been
 *         omitted. will be tested through other methods.
 *
 * @author sarahworley 
 *
 */
public class StudentTest {

	/** Students first name */
	private static final String FIRST_NAME = "sarah";
	/** Students last name */
	private static final String LAST_NAME = "worley";
	/** Students user id */
	private static final String ID = "sworley";
	/** Students email */
	private static final String EMAIL = "sw@ncsu.edu";
	/** Students password */
	private static final String PASSWORD = "password";
	/** Automatic max credits for any student */
	private static final int MAX_CREDITS = 18;

	/**
	 * Tests the Student constructor with all field parameters.
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		// Testing a valid student
		Student student1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		// testing valid construction
		assertEquals(FIRST_NAME, student1.getFirstName());
		assertEquals(LAST_NAME, student1.getLastName());
		assertEquals(EMAIL, student1.getEmail());
		assertEquals(PASSWORD, student1.getPassword());
		assertEquals(MAX_CREDITS, student1.getMaxCredits());

		// TESTING NULL STUDENT first name
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		try {// TESTING NULL STUDENT LAST NAME
			s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL STUDENT ID
			s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL STUDENT EMAIL
			s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL STUDENT PASSWORD
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests the Student constructor without default MAX_CREDITS
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		// Test Valid Student//
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s1.getFirstName());
		assertEquals(LAST_NAME, s1.getLastName());
		assertEquals(ID, s1.getId());
		assertEquals(EMAIL, s1.getEmail());
		assertEquals(PASSWORD, s1.getPassword());
		assertEquals(MAX_CREDITS, s1.getMaxCredits());

		// TESTING NULL STUDENT
		User s = null; // Initialize a student reference to null
		try {// TESTING NULL STUDENT FIRST NAME
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL STUDENT LAST NAME
			s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL STUDENT ID
			s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// ID test for empty string//
		try {
			s = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		try {// TESTING NULL STUDENT EMAIL
			s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {// TESTING NULL STUDENT PASSWORD
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null);
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
		// Construct a valid Student
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
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
			assertEquals(MAX_CREDITS, s.getMaxCredits());
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
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}

	}

	/**
	 * Tests SetLastName()
	 */
	@Test
	public void testSetLastName() {

		// Construct a valid Student
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());

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
			assertEquals(MAX_CREDITS, s.getMaxCredits());
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
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}

	}

	/**
	 * Tests SetEmail()
	 */
	@Test
	public void testSetEmail() {
		// Construct a valid Student
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());

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
			assertEquals(MAX_CREDITS, s.getMaxCredits());
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
			assertEquals(MAX_CREDITS, s.getMaxCredits());

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
			assertEquals(MAX_CREDITS, s.getMaxCredits());

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
			assertEquals(MAX_CREDITS, s.getMaxCredits());

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
			assertEquals(MAX_CREDITS, s.getMaxCredits());

		}

	}

	/**
	 * Tests SetPassword()
	 */
	@Test
	public void testSetPassword() {
		// Construct a valid Student
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
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
			assertEquals(MAX_CREDITS, s.getMaxCredits());
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
			assertEquals(MAX_CREDITS, s.getMaxCredits());

		}

	}

	/**
	 * Tests SetMaxCredits()
	 */
	@Test
	public void testSetMaxCredits() {
		// Construct a valid Student
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());

		try { // tests for credits under 3
			s.setMaxCredits(1);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		try { // tests for credits over 18
			s.setMaxCredits(19);
			fail();
		} catch (IllegalArgumentException e) {
			// CHECKING IS FIELD STAYED THE SAME
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
	}

	/**
	 * Tests toString()
	 */
	@Test
	public void testToString() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		String s2 = "sarah,worley,sworley,sw@ncsu.edu,password,18";

		assertEquals(s2, s1.toString());

		User s3 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);

		assertEquals(s2, s3.toString());
	}

	/**
	 * Tests equalsObject()
	 */
	@Test
	public void testEqualsObject() {
		// Objects are equal
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		// Objects are different
		User s3 = new Student("differnt", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "different", ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "differnt@email.com", PASSWORD, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", 5);

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

	/**
	 * Tests hashCode()
	 */
	@Test
	public void testHashCode() {
		// Objects are equal
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		// Objects are different
		User s3 = new Student("differnt", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "different", ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "differnt@email.com", PASSWORD, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", 5);

		// Test for equality
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());

	}
	
	/**
	 * Test the compares method. Compares two objects 
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		// Objects are different
		Student s3 = new Student("amy", "adams", "aadams", EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student("anna", "zar", "azar", EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student("tracy", "adams", "tadams", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, "sworley2", EMAIL, PASSWORD, MAX_CREDITS);
		
		Student s7 = null;
		
		// Test for equality
		assertEquals(0, s1.compareTo(s2));
		assertEquals(1, s1.compareTo(s3));
		assertEquals(-1, s1.compareTo(s4));
		assertEquals(1, s1.compareTo(s5));
		assertEquals(-1, s1.compareTo(s6));
		
		
		try {
			s1.compareTo(s7);
			fail();
		} catch (NullPointerException e) {
			assertNull(s7);
		}
		
		
		
		
	}
}
