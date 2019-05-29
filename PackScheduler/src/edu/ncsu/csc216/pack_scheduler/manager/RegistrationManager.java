/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Registration manager handles login / logout for users creates a new registry
 * and provides information about the user
 * 
 * @author sarahworley
 *
 */
public class RegistrationManager {

	/** RegistrationManager single instance */
	private static RegistrationManager instance;
	/** CourseCatalog that will be used for the registration manager */
	private CourseCatalog courseCatalog;
	/** StudentDirectory that will be used for the registration manager */
	private StudentDirectory studentDirectory;
	/** User of the registration directory */
	private User registrar;
	/** Current user of the registration directory */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** properties file used for creating a new registrar */
	private static final String PROP_FILE = "registrar.properties";
	/** boolean for determining if there is a user logged in or not */
	boolean loggedIn = false;
	private FacultyDirectory facultyDirectory;

	/**
	 * Sets up the registration manager and creates a new course and Student catalog
	 */
	private RegistrationManager() {
		createRegistrar();
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();
		this.facultyDirectory = new FacultyDirectory();

	}

	/**
	 * reads from the registrar.properties file to create a registrar creates a
	 * hashed version of the registrar password
	 * 
	 * @throws IllegalArgumentException if file cannot be read
	 * 
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Hashes the users password for protection
	 * 
	 * @param pw users password to hash
	 * @return the hashed version of the users password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * get the faculty directory
	 * 
	 * @return facultyDirectory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Gets a single instance of the registrationManager ensures that the GUI
	 * interacts with a single instance of the RegistrationManager,
	 * 
	 * @return instance single instance of registration manager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/** adds facuty to course 
	 * @param c course to add faculty to 
	 * @param f to add
	 * @return c ourse
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser instanceof Registrar) {

			return f.getSchedule().addCourseToSchedule(c);

		} else {
			throw new IllegalArgumentException("Course cannot be added to faculty's schedule.");
		}

	}

	/** removes faculty from a course
	 * @param c to remove from
	 * @param f faculty to remove 
	 * @return course
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser instanceof Registrar) {
			return f.getSchedule().removeCourseFromSchedule(c);
		} else {
			throw new IllegalArgumentException("Course cannot be removed from faculty's schedule.");
		}
	}

	/** Reesets the faculty schedule
	 * @param f faculty to reset
	 */
	public void resetFacultySchedule(Faculty f) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException();
		}
		f.getSchedule().resetSchedule();

	}

	/**
	 * get the courseCatalog
	 * 
	 * @return courseCatalog to return
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Gets the student directory
	 * 
	 * @return studentDirectory to return
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Logs a user in by checking id and password. Updates current user
	 * 
	 * @param id       of user logging in
	 * @param password of user logging in
	 * @return true or false if the user can be logged in. if a user is already
	 *         logged in it is automatically false
	 */
	public boolean login(String id, String password) {
		// what if logged in?
		// what if user does not exist

		if (loggedIn) {
			return false;
		}

		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					loggedIn = true; // registrar logged in
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}

		User loggingin = getStudentDirectory().getStudentById(id);
		if (loggingin == null) {
			loggingin = getFacultyDirectory().getFacultyById(id);
			if (loggingin == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}

		}
		try {
			MessageDigest digest;
			digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if (loggingin.getPassword().equals(localHashPW)) {
				loggedIn = true; //// student logged in
				currentUser = loggingin;
				return true;
			}

		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Invalid id or password");

		}

		return false;

	}

	/**
	 * Logs a user off
	 */
	public void logout() {
		currentUser = null;
		loggedIn = false;
	}

	/**
	 * Returns the current user
	 * 
	 * @return currentUser user that is logged on
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears data gathered by RegistrationManager
	 */
	public void clearData() {

		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		getFacultyDirectory().newFacultyDirectory();
		loggedIn = false;

	}

	/**
	 * Registrar class extends user. Sets up a new registrar user
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the user id and password in the
		 * registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}
}
