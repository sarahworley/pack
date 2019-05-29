package edu.ncsu.csc216.pack_scheduler.io;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import java.io.*;
import java.util.*;

/**
 * Student record class
 * @author Sarah Worley
 * 
 */
public class StudentRecordIO {
	
	
	/**
	 * Sets up a sorted list for student. gets file, checks if file is readable 
	 * @param fileName file that student info is located in
	 * @return student Sorted list
	 * @throws FileNotFoundException if file cannot be found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> student = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student s = processStudent(fileReader.nextLine());
				student.add(s);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to read file");
		
			}
		}
		fileReader.close();
		return student;
	}
	


	
	/**
	 * Reads students info line by line, constructs new student with info
	 * @param line each line is the students info
	 * @return s student info
	 * @throws IllegalArgumentException if no such element 
	 */
	private static Student processStudent(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		int maxCredits = 0;
		try {
			String firstName = scan.next();
			String lastName = scan.next();
			String id = scan.next();
			String email = scan.next();
			String password = scan.next();
			maxCredits = scan.nextInt();
			scan.close();
			Student s = new Student (firstName, lastName, id, email, password, maxCredits);
			return s;
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Writes student record to file studentDirectory
	 * @param fileName name of file
	 * @param studentDirectory Sorted list
	 * @throws IOException if unable to write file 
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
	}
		

	

}
