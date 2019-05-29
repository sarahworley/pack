package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Activity is a super class it implements the interface Conflict. Generates
 * different types of activities for a schedule
 * 
 * @author sarah Worley
 *
 */

public abstract class Activity implements Conflict {

	/** Military time */
	private static final int UPPER_TIME = 2400;
	/** Hour max */
	private static final int UPPER_HOUR = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/*
	 * Implementation of checkConflict. to check if time conflicts exist
	 * 
	 * @param possibleConflictingActivity the activity in question
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean sameTime = false;
		boolean sameDay = false;

		if (this.getStartTime() <= possibleConflictingActivity.getStartTime()
				&& this.getEndTime() >= possibleConflictingActivity.getStartTime()) {
			sameTime = true;
		}
		if (this.getStartTime() >= possibleConflictingActivity.getStartTime()
				&& this.getEndTime() <= possibleConflictingActivity.getEndTime()) {
			sameTime = true;
		}
		if (this.getStartTime() <= possibleConflictingActivity.getStartTime()
				&& this.getEndTime() >= possibleConflictingActivity.getEndTime()) {
			sameTime = true;
		}
		if (this.getStartTime() <= possibleConflictingActivity.getEndTime()
				&& this.getEndTime() >= possibleConflictingActivity.getEndTime()) {
			sameTime = true;
		}

		int same = -1;
		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			char x = this.getMeetingDays().charAt(i);
			same = possibleConflictingActivity.getMeetingDays().indexOf(x);
			if (same > -1) {
				sameDay = true;
			}

		}
		if (this.getMeetingDays().equals("A") && possibleConflictingActivity.getMeetingDays().equals("A")) {
			sameTime = false;
			sameDay = false;
		}
		if (sameDay && sameTime) {
			throw new ConflictException();
		}

	}

	/**
	 * Creates an activity with the given title, meetingDays start time and end time
	 * 
	 *
	 * @param title       title of Activity
	 * @param meetingDays meeting Days of Activity
	 * @param startTime   start time of activity
	 * @param endTime     end time of activity
	 */

	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);

	}

	/**
	 * short version of the array of Activity's information to provide to the GUI.
	 * 
	 * @return shortArray
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * long version of the array of Avtivity's information to provide to the GUI.
	 * 
	 * @return longArray
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Returns Activity's title
	 * 
	 * @return the title
	 */
	public String getTitle() {

		return title;
	}

	/**
	 * Sets the Activity's title
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null || title.length() <= 0) {
			throw new IllegalArgumentException("Invalid course title");
		}
		this.title = title;
	}

	/**
	 * Returns Activity meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets An activity's meeting days
	 * 
	 * @param meetingDays the meetingDays to set
	 * 
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns Activity start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns Activity end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets Activity time
	 * 
	 * @param startTime the start time to set
	 * @param endTime   the end time to set
	 */
	public void setActivityTime(int startTime, int endTime) {
		int startMin = startTime % 100;
		int endMin = endTime % 100;
		
		if (meetingDays.equals("A") ) {
			this.startTime = 0;
			this.endTime = 0;
		} else {
			if (startTime < 0 || startTime >= UPPER_TIME || endTime < 0 || endTime >= UPPER_TIME) {
				throw new IllegalArgumentException("Invalid start time");
			}
			if (startMin < 0 || startMin >= UPPER_HOUR || endMin < 0 || endMin >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid end time");
			}
	
			if (startTime > endTime) {
				throw new IllegalArgumentException("Invalid course times");
			}
			
			this.startTime = startTime;
			this.endTime = endTime;	
		}
		
	}

	/**
	 * Returns a string of meeting time by converting the military time to standard
	 * time.
	 * 
	 * @return String of meeting times
	 */
	public String getMeetingString() {
		int startHour = startTime / 100;
		int endHour = endTime / 100;
		int startMin = startTime % 100;
		int endMin = endTime % 100;

		if (meetingDays.equals("A")) {
			return "Arranged";
		}

		String a = "";
		String b = "";
		if (startHour >= 12) {
			a = "PM";
			if (startHour > 12) {
				startHour = startHour - 12;
			}

		} else {
			a = "AM";
		}
		if (endHour >= 12) {
			b = "PM";
			if (endHour > 12) {
				endHour = endHour - 12;
			}
		} else {
			b = "AM";
		}

		String minS = Integer.toString(startMin);
		String minE = Integer.toString(endMin);
		if (minS.length() < 2) {
			minS = 0 + minS;
		}
		if (minE.length() < 2) {
			minE = 0 + minE;
		}

		return meetingDays + " " + startHour + ":" + minS + a + "-" + endHour + ":" + minE + b;
	}

	/**
	 * returns true if an activity is the same as another activity in the schedule
	 * 
	 * @param activity to check against
	 * @return true or false
	 */
	public abstract boolean isDuplicate(Activity activity);

	/*
	 * eclipse generated hashcode)
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * eclipse generated equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}