package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.fail;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/** test for COurse Roll
 * @author sarahworley
 *
 */
public class CourseRollTest {
	
	private CourseRoll roll;
	
	
	private Student s1 = new Student ("Sarah", "Worley", "sworley", "sworley@ncsu.edu", "pw", 6);
	private Student s2 = new Student ("Old", "Mcdonald", "omcdonald", "omcdonald@ncsu.edu", "pw", 7);
	private Student s3 = new Student ("Chester", "Cheeto", "ccheeto", "ccheeto@ncsu.edu", "pw", 12);
	private Student s4 = new Student ("Kim", "Kardashian", "kkardashian", "kkardashian@ncsu.edu", "pw", 11);
	private Student s5 = new Student ("Sarah", "Worley", "sworley", "sworley@ncsu.edu", "pw", 6);
	private Student s6 = new Student ("Sara", "Worley", "sworley", "sworley@ncsu.edu", "pw", 14);
	private Student s7 = new Student ("Bob", "bill", "bbill", "bbill@ncsu.edu", "pw", 6);
	private Student s8 = new Student ("mister", "man", "mman", "mman@ncsu.edu", "pw", 8);
	private Student s9 = new Student ("student9", "nine", "snine", "snine@ncsu.edu", "pw", 8);
	private Student s10 = new Student ("student10", "ten", "sten", "sten@ncsu.edu", "pw", 8);
	private Student s11 = new Student ("student11", "eleven", "seleven", "seleven@ncsu.edu", "pw", 8);
	private Student s12 = new Student ("student12", "twelve", "stwelve", "stwelve@ncsu.edu", "pw", 8);
	private Student s13 = new Student ("student13", "thirteen", "sthriteen", "stwelve@ncsu.edu", "pw", 8);
	private Student s15 = new Student ("student15", "15", "s15", "stwelve@ncsu.edu", "pw", 8);
	private Student s16 = new Student ("student16", "16", "s16", "stwelve@ncsu.edu", "pw", 8);
	private Student s17 = new Student ("student17", "17", "s17", "stwelve@ncsu.edu", "pw", 8);
	private Student s18 = new Student ("student18", "18", "s18", "stwelve@ncsu.edu", "pw", 8);
	private Student s19 = new Student ("student19", "19", "s19", "stwelve@ncsu.edu", "pw", 8);
	private Student s20 = new Student ("student20", "20", "s20", "stwelve@ncsu.edu", "pw", 8);
	private Student s21 = new Student ("student21", "21", "s21", "stwelve@ncsu.edu", "pw", 8);
	private Student s22 = new Student ("student22", "22", "s22", "stwelve@ncsu.edu", "pw", 8);
	private Student s23 = new Student ("student23", "23", "s23", "stwelve@ncsu.edu", "pw", 8);
	//private Student s24 = new Student ("student24", "24", "s24", "stwelve@ncsu.edu", "pw", 8);
	private Student s14 = null;

	Course c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	
	
	

	
	/** sets up new roll each time 
	 * @throws java.lang.Exception if unable 
	 */
	@Before
	public void setUp() throws Exception {
		roll = c1.getCourseRoll();
	}
	
	/**
	 * Tests the roll constructor and capacity 
	 */
	@Test
	public void testCourseRoll() {
		
		
		
		
		roll.setEnrollmentCap(12);
		assertEquals(12, roll.getEnrollmentCap());
		
		try {
			roll.setEnrollmentCap(9);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(12, roll.getEnrollmentCap());
			
		}
		try {
			roll.setEnrollmentCap(300);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(12, roll.getEnrollmentCap());
			
		}
	}
	
	/**
	 * tests enroll
	 */
	@Test
	public void enroll() {
		assertEquals(true, roll.canEnroll(s1));
		roll.setEnrollmentCap(11);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		roll.enroll(s12);
		
		roll.enroll(s13);
		assertEquals(1, roll.getNumberOnWaitlist());
		
		try {
			roll.enroll(s5);
			fail();
		} catch (IllegalArgumentException e) {
			assertFalse(roll.canEnroll(s5));
			
		}
		
			
		
		
	}
	
	/**
	 * tests drop 
	 */
	@Test
	public void drop() { 
		roll.setEnrollmentCap(11);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		roll.enroll(s12);
		roll.enroll(s13);
		assertEquals(1, roll.getNumberOnWaitlist());
		//cannot add because at max
		
	
		//drop so we can add
		roll.drop(s3);
		
		//assertTrue(roll.canEnroll(s13));
		
	}
	/**
	 * tests waitlist capacity 
	 */
	@Test
	public void waitlist() { 
		roll.setEnrollmentCap(10);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		//now they will go on wait list 
		roll.enroll(s12);
		roll.enroll(s13);
		roll.enroll(s15);
		roll.enroll(s16);
		roll.enroll(s17);
		roll.enroll(s18);
		roll.enroll(s19);
		roll.enroll(s20);
		roll.enroll(s21);
		roll.enroll(s22);
		//reached cap for wait list 
		assertEquals(10, roll.getNumberOnWaitlist());
		assertEquals(10, roll.get2DArray().length);
		//try to add another 
		try {
			roll.enroll(s23);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, roll.getNumberOnWaitlist());
			
		}
		
		
		
	}
}
