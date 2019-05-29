/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for checkConflict within activity
 * 
 * @author sarahworley
 *
 */
public class ActivityTest {

	/**
	 * Tests check conflict in activity.
	 * 
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "TH", 1330, 1445);
		Activity a3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "MW", 1445, 1600);
		Activity a4 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "MW", 1345, 1445);
		Activity a5 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "MW", 1245, 1345);
		Activity a6 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "MW", 1230, 1445);
		Activity a7 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "TWH", 1230, 1445);
		Activity a8 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "TH", 1230, 1445);
		Activity a9 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "TWH", 1230, 1445);

		try {
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		try { // switches to make sure they are commutative.
			a2.checkConflict(a1);
			assertEquals("Incorrect meeting string for this Activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM",
					a1.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}

		try { // switches to make sure they are commutative.
			a2.setMeetingDays("A");
			a2.checkConflict(a1);
			assertEquals("Incorrect meeting string for this Activity.", "Arranged", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM",
					a1.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		// Test two courses that the start time and end time of the two are the same
		try {
			a1.checkConflict(a3);

			fail();
		} catch (ConflictException e) {
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("MW 2:45PM-4:00PM", a3.getMeetingString());

		}
		try { // Reverses for commutative check
			a3.checkConflict(a1);

			fail();
		} catch (ConflictException e) {
			assertEquals("MW 2:45PM-4:00PM", a3.getMeetingString());
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString()); 

		}
		try { // tests two courses where one course starts in the middle of the other
			a1.checkConflict(a4);
			fail();
		} catch (ConflictException e) {
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("MW 1:45PM-2:45PM", a4.getMeetingString());
		}
		try { // test two courses when one courses end time was in the middle of the other
			a1.checkConflict(a5);
			fail();
		} catch (ConflictException e) {
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("MW 12:45PM-1:45PM", a5.getMeetingString());

		}
		try { // test two courses when one course starts before the other but they end at the
				// same time
			a1.checkConflict(a6);
			fail();
		} catch (ConflictException e) {
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("MW 12:30PM-2:45PM", a6.getMeetingString());
		}
		try { // test two courses when MW and the other is TWH the time over laps on W
			a1.checkConflict(a7);
			fail();
		} catch (ConflictException e) {
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("TWH 12:30PM-2:45PM", a7.getMeetingString());
		}
		try { // test two courses that do no over lap. they have different days. but same time
			a1.checkConflict(a8);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 12:30PM-2:45PM",
					a8.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		try { // test two courses that overlap completely
			a7.checkConflict(a9);
			fail();
		} catch (ConflictException e) {
			assertEquals("TWH 12:30PM-2:45PM", a7.getMeetingString());
			assertEquals("TWH 12:30PM-2:45PM", a9.getMeetingString());
		}
		try { // switches to make sure they are commutative.
			a2.setMeetingDays("A");
			a1.setMeetingDays("A");
			a2.checkConflict(a1);
			assertEquals("Incorrect meeting string for this Activity.", "Arranged", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "Arranged",
					a1.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		

	}

}
