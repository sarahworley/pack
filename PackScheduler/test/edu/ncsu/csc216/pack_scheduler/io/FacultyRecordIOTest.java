/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/** faculty io test
 * @author sarahworley
 *
 */
public class FacultyRecordIOTest {


	/** Hashed password */
	private String hashPW;
	/** Hash code algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** valid Student records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid Student records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** Expected results for valid Students */
	private String validStudent0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	private String validStudent1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private String validStudent2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private String validStudent3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private String validStudent4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private String validStudent5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private String validStudent6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private String validStudent7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";
	//private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	//private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** Array to hold expected results */
	private String[] validStudents = { validStudent0, validStudent1, validStudent2, validStudent3, validStudent4,
			validStudent5, validStudent6, validStudent7 };
	
	
	
	/**
	 * Hashes password
	 * 
	 * @throws java.lang.Exception if files cannot be reset
	 */
	@Before
	public void setUp() throws Exception {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
		
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}

	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 * @throws IOException if file isn't found
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	
	
	
	/**
	 * Test method for Invalid student records. makes sure no invalid records are
	 * read
	 * 
	 *
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		LinkedList<Faculty> student;
		try {
			student = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, student.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}

	/**
	 * Tests readValidCourseRecords().
	 * 
	 * 
	 */
	@Test
	public void testReadValidStudentRecords() {
		try {
			LinkedList<Faculty> students = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, students.size());

			assertEquals(validStudents[0], students.get(0).toString());
			assertEquals(validStudents[1], students.get(1).toString());
			assertEquals(validStudents[2], students.get(2).toString());
			assertEquals(validStudents[3], students.get(3).toString());
			assertEquals(validStudents[4], students.get(4).toString());
			
			assertEquals(validStudents[5], students.get(5).toString());
			
			assertEquals(validStudents[6], students.get(6).toString());
			assertEquals(validStudents[7], students.get(7).toString());
 
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Test method for write student records. tries to writes students to a files
	 * 
	 * 
	  	 */
	@Test
	public void testWriteStudentRecords() {
		LinkedList<Faculty> students = new LinkedList<Faculty>();
		students.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		students.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		students.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));

		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to faculty record file");
		}

		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");

	}

	/**
	 * Test method for write student records to a file with no permissions. tries to
	 * writes students to a files
	 * 
	 * 
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		LinkedList<Faculty> students = new LinkedList<Faculty>();
		students.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		// Assumption that you are using a hash of "pw" stored in hashPW

		try {
			FacultyRecordIO.writeFacultyRecords("/home/actual_faculty_records.txt", students);
			fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
		} catch (IOException e) {
			assertEquals("/home/actual_faculty_records.txt (Permission denied)", e.getMessage());
			// The actual error message on Jenkins!
		}

	}

}
