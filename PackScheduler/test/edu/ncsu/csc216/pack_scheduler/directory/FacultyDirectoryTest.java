/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;


/**Faculty directory
 * @author sarahworley
 *
 */
public class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt" ;
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 2;
	/** Hashed password */
	//private String hashPW;
	/** Hash code algorithm */
	//private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("nothing"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are Facultys in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();
		
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(3, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(3, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		//Test invalid facultys
		sd = new FacultyDirectory(); 
		try { //adding with null first name
			sd.addFaculty(null, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, sd.getFacultyDirectory().length);
		}
		
		try { // adding with different passwords
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "password", "pw", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, sd.getFacultyDirectory().length);
		}
		
		try { // adding a duplicate Faculty 
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals(1, sd.getFacultyDirectory().length);
		}
		
		
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Add Facultys and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(3, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("awitt"));
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(2, facultyDirectory.length);
		
	}

	
	
	
	
	/**
	 * Tests getfacultyById().
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory sd = new FacultyDirectory();
		//Load in valid files//
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(3, sd.getFacultyDirectory().length);
		
		Faculty s = sd.getFacultyById("awitt");
		assertTrue(s.getFirstName().equals("Ashely")); 
		assertTrue(s.getLastName().equals("Witt"));
		assertTrue(s.getId().equals("awitt"));
		
		try {
			s = sd.getFacultyById("sworley");
		} catch (IllegalArgumentException e){
			assertTrue(s.getId().equals("awitt"));
		}
		
		
	}
}
