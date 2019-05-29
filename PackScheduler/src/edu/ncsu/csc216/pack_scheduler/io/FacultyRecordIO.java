/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * faculty record test io
 * 
 * @author sarahworley
 *
 */
public class FacultyRecordIO {

	/**
	 * Writes student record to file studentDirectory
	 * 
	 * @param fileName         name of file
	 * @param facultyDirectory Sorted list
	 * @throws IOException if unable to write file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
	}

	/**
	 * Reads students info line by line, constructs new student with info
	 * 
	 * @param line each line is the students info
	 * @return s student info
	 * @throws IllegalArgumentException if no such element
	 */
	private static Faculty processFaculty(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		int maxCourses = 0;
		try {
			String firstName = scan.next();
			String lastName = scan.next();
			String id = scan.next();
			String email = scan.next();
			String password = scan.next();
			maxCourses = scan.nextInt();
			scan.close();
			Faculty s = new Faculty(firstName, lastName, id, email, password, maxCourses);
			return s;
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Sets up a sorted list for student. gets file, checks if file is readable
	 * 
	 * @param fileName file that student info is located in
	 * @return student Sorted list
	 * @throws FileNotFoundException if file cannot be found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> s = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty s1 = processFaculty(fileReader.nextLine());
				s.add(s1);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to read file");

			}
		}
		fileReader.close();
		return s;
	}
}
