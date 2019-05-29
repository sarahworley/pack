package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * GUI for FacultyScheduler
 * @author Sarah Worley 
 *
 */
public class FacultySchedulePanel extends JPanel {
	
	public static final long serialVersionUID = 1L;
	private JTable tableCourseRoll;
	/** Scroll pane for table */
	private JScrollPane scrollCourseRoll;
	private CourseRollTableModel courseRollTableModel;
	private CourseCatalog catalog;
	private FacultySchedule schedule;
	/** Scroll pane for table */
	private JScrollPane scrollFacultySchedule;
	/** TableModel for catalog of Courses */
	private FacultyScheduleTableModel facultyScheduleTableModel;
	private Faculty faculty;
	private JPanel pnlCourseDetails;
	/** Label for Course Details name title */
	private JLabel lblNameTitle = new JLabel("Name: ");
	/** Label for Course Details section title */
	private JLabel lblSectionTitle = new JLabel("Section: ");
	/** Label for Course Details title title */
	private JLabel lblTitleTitle = new JLabel("Title: ");
	/** Label for Course Details instructor title */
	private JLabel lblInstructorTitle = new JLabel("Instructor: ");
	/** Label for Course Details credit hours title */
	private JLabel lblCreditsTitle = new JLabel("Credits: ");
	/** Label for Course Details meeting title */
	private JLabel lblMeetingTitle = new JLabel("Meeting: ");
	/** Label for Course Details enrollment cap title */
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
	/** Label for Course Details open seats title */
	private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
	/** Label for Course Details waitlist title */
	private JLabel lblWaitlistTitle = new JLabel("Waitlist: ");
	/** Label for Course Details name */
	private JLabel lblName = new JLabel("");
	/** Label for Course Details section */
	private JLabel lblSection = new JLabel("");
	/** Label for Course Details title */
	private JLabel lblTitle = new JLabel("");
	/** Label for Course Details instructor */
	private JLabel lblInstructor = new JLabel("");
	/** Label for Course Details credit hours */
	private JLabel lblCredits = new JLabel("");
	/** Label for Course Details meeting */
	private JLabel lblMeeting = new JLabel("");
	/** Label for Course Details enrollment cap */
	private JLabel lblEnrollmentCap = new JLabel("");
	/** Label for Course Details open seats */
	private JLabel lblOpenSeats = new JLabel("");
	/** Label for Course Details waitlist */
	private JLabel lblWaitlist = new JLabel("");
	private JTable tableSchedule;
	private RegistrationManager manager = RegistrationManager.getInstance();


	/**
	 * Creates the faculty schedule panel
	 */
	public FacultySchedulePanel() {
		super(new GridBagLayout());
		faculty = (Faculty) manager.getCurrentUser();
		catalog = manager.getCourseCatalog();

		setUpFacultyScheduleTableView();
		setUpCourseDetailsPanel();
		setUpCourseRollTableView();
		
		updateTables();

		// add stuff to overall view
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = .2;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollFacultySchedule, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlCourseDetails, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = .5;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollCourseRoll, c);

		setVisible(true);
	}

	/**
	 * faculty schedule
	 */
	public void updateTables() {
		faculty = (Faculty) manager.getCurrentUser();
		facultyScheduleTableModel.updateData();
		courseRollTableModel.updateData();
	}

	private void updateCourseDetails(Course c) {
		if (c != null) {
			lblCredits.setText(Integer.toString(c.getCredits()));
			lblEnrollmentCap.setText(Integer.toString(c.getCourseRoll().getEnrollmentCap()));
			lblInstructor.setText(faculty.getId());
			lblMeeting.setText(c.getMeetingString());
			lblName.setText(c.getName());
			lblOpenSeats.setText(Integer.toString(c.getCourseRoll().getOpenSeats()));
			lblSection.setText(c.getSection());
			lblTitle.setText(c.getTitle());
			lblWaitlist.setText(Integer.toString(c.getCourseRoll().getNumberOnWaitlist()));
			// courseRollTableModel = new CourseRollTableModel();
		}
	}

	/**
	 * {@link CourseRollTableModel} is the object underlying the {@link JTable}
	 * object that displays the list of Students to the user.
	 * 
	 * @author Sarah Heckman
	 
	 */
	private class CourseRollTableModel extends AbstractTableModel {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "First Name", "Last Name", "Student ID" };
		/** Data stored in the table */
		private Object[][] data;
		private Course c;

		/**
		 * Constructs the {@link CourseRollTableModel} by requesting the latest
		 * information from the {@link RequirementTrackerModel}.
		 */
		public CourseRollTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * 
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * 
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null)
				return 0;
			return data.length;
		}

		/**
		 * Returns the column name at the given index.
		 * 
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * 
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * 
		 * @param value
		 *            Object to modify in the data.
		 * @param row
		 *            location to modify the data.
		 * @param column
		 *            location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		/**
		 * course roll??
		 */
		public void updateData() {
			try {
				String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
				c = catalog.getCourseFromCatalog(name, section);
			} catch (NullPointerException e) {
				c = null;
			}
			if (c != null) {
				data = c.getCourseRoll().get2DArray();

				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();
			}
		}
	}

	/**
	 * {@link FacultyScheduleTableModel} is the object underlying the
	 * {@link JTable} object that displays the list of Courses to the user.
	 * 
	 * @author Sarah Heckman
	
	 */
	private class FacultyScheduleTableModel extends AbstractTableModel {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "Name", "Section", "Title", "Meeting Days", "Open Seats" };
		/** Data stored in the table */
		private Object [][] data;

		/**
		 * Constructs the {@link FacultyScheduleTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public FacultyScheduleTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}

		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null || row < 0 || row > data[0].length || col < 0 || col > data[0].length) {
				return null;
			}
			return data[row][col];
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		/**
		 * Updates the given model with {@link Course} information from the {@link CourseCatalog}.
		 */
		public void updateData() {
			if (faculty != null) {
				schedule = faculty.getSchedule();
				data = schedule.getScheduledCourses();
				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();
			}
		}
	}
	
	private void setUpCourseDetailsPanel() {
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

		// Set up the course details panel
		pnlCourseDetails = new JPanel();
		pnlCourseDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		pnlName.add(lblSectionTitle);
		pnlName.add(lblSection);

		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);

		JPanel pnlInstructor = new JPanel(new GridLayout(1, 4));
		pnlInstructor.add(lblInstructorTitle);
		pnlInstructor.add(lblInstructor);
		pnlInstructor.add(lblCreditsTitle);
		pnlInstructor.add(lblCredits);

		JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		pnlMeeting.add(lblMeetingTitle);
		pnlMeeting.add(lblMeeting);

		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 4));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblOpenSeats);
		pnlEnrollment.add(lblWaitlistTitle);
		pnlEnrollment.add(lblWaitlist);

		pnlCourseDetails.add(pnlName);
		pnlCourseDetails.add(pnlTitle);
		pnlCourseDetails.add(pnlInstructor);
		pnlCourseDetails.add(pnlMeeting);
		pnlCourseDetails.add(pnlEnrollment);

		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");
	}



	private void setUpFacultyScheduleTableView() {
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Faculty Schedule");

		facultyScheduleTableModel = new FacultyScheduleTableModel();

		// set up schedule table so courses are able to be clicked on
		tableSchedule = new JTable(facultyScheduleTableModel) {
			private static final long serialVersionUID = 1L;

			/**
			 * Set custom tool tips for cells
			 * 
			 * @param e
			 *            MouseEvent that causes the tool tip
			 * @return tool tip text
			 */
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);

				return (String) facultyScheduleTableModel.getValueAt(rowIndex, realColumnIndex);
			}
		};
		tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableSchedule.setFillsViewportHeight(true);
		tableSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
				Course c = catalog.getCourseFromCatalog(name, section);
				courseRollTableModel.updateData();
				updateCourseDetails(c);
			}


		});

		scrollFacultySchedule = new JScrollPane(tableSchedule /* JTable */, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollFacultySchedule.setBorder(border);
		scrollFacultySchedule.setToolTipText("Faculty Schedule");
	}

	private void setUpCourseRollTableView() {
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");

		courseRollTableModel = new CourseRollTableModel();
		tableCourseRoll = new JTable(courseRollTableModel);
		tableCourseRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCourseRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableCourseRoll.setFillsViewportHeight(true);

		scrollCourseRoll = new JScrollPane(tableCourseRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollCourseRoll.setBorder(border);
		scrollCourseRoll.setToolTipText("Course Roll");
	}
}