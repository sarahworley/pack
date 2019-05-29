package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests CourseNameValidator
 * 
 * @author sarahworley
 *
 */
public class CourseNameValidatorTest {

	CourseNameValidator c;

	/**
	 * SETS US A NEW CourseNameValidatorFSM EACH TIME
	 * 
	 * @throws java.lang.Exception if cannot be created
	 */
	@Before
	public void setUp() throws Exception {
		c = new CourseNameValidator();
	}

	/**
	 * Tests valid courses
	 */
	@Test
	public void testIsValid() {

		// TESTING valid classes
		try {
			c.isValid("C116");
			assertTrue(c.isValid("C116"));
		} catch (InvalidTransitionException e1) {
			// should not get her because its valid
			fail();
		}
		try {
			c.isValid("CS116");
			assertTrue(c.isValid("CS116"));
		} catch (InvalidTransitionException e1) {
			// should not get her because its valid
			fail();
		}
		try {
			c.isValid("CSC116");
			assertTrue(c.isValid("CSC116"));
		} catch (InvalidTransitionException e1) {
			// should not get her because its valid
			fail();
		}
		try {
			c.isValid("CSC116X");
			assertTrue(c.isValid("CSC116X"));
		} catch (InvalidTransitionException e1) {
			// should not get her because its valid
			fail();
		}
		try {
			c.isValid("CSCX116");
			assertTrue(c.isValid("CSCX116"));
		} catch (InvalidTransitionException e1) {
			// should not get her because its valid
			fail();
		}
		try {
			c.isValid("CSCX116X");
			assertTrue(c.isValid("CSCX116X"));
		} catch (InvalidTransitionException e1) {
			// should not get her because its valid
			fail();
		}
	}

	/**
	 * Tests get letterCount
	 */
	@Test
	public void testGetLetterCount() {
		try {
			c.isValid("CLAS111");
			assertEquals(4, c.getLetterCount());
		} catch (InvalidTransitionException e) {
			fail();
		}

	}

	/**
	 * Tests get letterCount
	 */
	@Test
	public void testGetNumberCount() {
		try {
			c.isValid("CLAS111");
			assertEquals(3, c.getNumberCount());
		} catch (InvalidTransitionException e) {
			fail();
		}

	}

	/**
	 * tests for invalid character
	 */
	@Test
	public void testSpecialChar() {
		// TESTS FOR INVAILD CLASSES
		// INVALID CHARACTERS
		try {
			c.isValid("*SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
	}

	/**
	 * tests for invalid start of course name
	 */
	@Test
	public void testNonLetterStart() {
		// checking for valid start
		try {
			c.isValid("111CLAS");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
	}

	/**
	 * tests for Invalid number of letters
	 */
	@Test
	public void testInvalidNumberLetters() {
		// TOO MANY LETTERS
		try {
			c.isValid("CLASS12");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
	

	}

	/**
	 * tests for Invalid number of letters
	 */
	@Test
	public void testNumberState() {
		// valid
		try {
			c.isValid("CLS1222");
			
		} catch (InvalidTransitionException e) {
			
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		// not enough numbers
		try {
			c.isValid("CLS12");
			assertEquals(2, c.getNumberCount());
		} catch (InvalidTransitionException e) {
			assertEquals(2, c.getNumberCount());
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			c.isValid("CLS1222b");
			
		} catch (InvalidTransitionException e) {
			
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			c.isValid("CLS12b");
			assertEquals(2, c.getNumberCount());
		} catch (InvalidTransitionException e) {
			assertEquals(2, c.getNumberCount());
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
	}

	/**
	 * tests for Invalid number of letters
	 */
	@Test
	public void testNumberState2() {
		try {
			c.isValid("CLS1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		// not enough numbers
		try {
			c.isValid("CLS");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

	}

	/**
	 * tests for Invalid number of letters
	 */
	@Test
	public void testNumberSate2() {
		// valid
		try {
			c.isValid("CLS122");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		// too many numbers
		try {
			c.isValid("CLS1222");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
	}

	/**
	 * tests for too many number or letters
	 */
	@Test
	public void testSuffix() {
		// EXTRA Letter in SUFFIX
		try {
			c.isValid("CLAS123AB");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());

		}
	}

	/**
	 * tests for too many number or letters
	 */
	@Test
	public void testSuffix2() {
		// EXTRA NUMBERS AFTER SUFFIX
		try {
			c.isValid("CLA121A1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}

	}
}
