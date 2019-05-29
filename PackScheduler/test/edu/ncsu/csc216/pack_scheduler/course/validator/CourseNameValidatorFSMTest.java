/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * TESTS COURSENAME VALIDATOR
 * 
 * @author sarahworley
 *
 */
public class CourseNameValidatorFSMTest {

	CourseNameValidatorFSM c;

	/**
	 * SETS US A NEW CourseNameValidatorFSM EACH TIME
	 * 
	 * @throws java.lang.Exception if cannot be created 
	 */
	@Before
	public void setUp() throws Exception {
		c = new CourseNameValidatorFSM();
	}
	/** tests isValid
	 * 
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

		// TESTS FOR INVAILD CLASSES
		// INVALID CHARACTERS
		try {
			c.isValid("*SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

		// checking for valid start
		try {
			c.isValid("111CLAS");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must start with a letter.");
		}
		// TOO MANY LETTERS
		try {
			c.isValid("CLASS12");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot start with more than 4 letters.");
		}
		// EXTRA SUFFIX
		try {
			c.isValid("CLAS123AB");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");

		}
		// NOT ENOUGH NUMBERS
		try {
			c.isValid("CLAS12");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		//too many numbers
		try {
			c.isValid("CLAS1222");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		// EXTRA NUMBERS AFTER SUFFIX
		try {
			c.isValid("CLAS121A1");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
		}

	}

}
