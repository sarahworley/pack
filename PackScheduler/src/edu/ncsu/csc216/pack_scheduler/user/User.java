package edu.ncsu.csc216.pack_scheduler.user;

/** user superclass of student. creates a user for the pack scheduler
 * @author sarahworley
 *
 */
public abstract class User {

	/** First Name of Student */
	private String firstName;
	/** Last name of Student */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email address */
	private String email;
	/** Student's password */
	private String password;

	
	/** User constructor uses setters and getters to generate a user
	 * @param firstName first name of user
	 * @param lastName last name of user 
	 * @param id id of user 
	 * @param email email of user 
	 * @param password password of user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email); 
		setPassword(password);
	}

	/**
	 * gets Students first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets Students first name
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if string is empty or null
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("") ) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns Students last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the Students last name
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if string is empty or null
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns Students id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the Students id
	 * @param id the id to set
	 * @throws IllegalArgumentException if string is empty or null
	 */
	protected void setId(String id) {
		if ( id == null || id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns Students email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the Students email
	 * @param email the email to set
	 * @throws IllegalArgumentException if string is empty or null
	 * @throws IllegalArgumentException email does not contain the '@' or '.' character
	 * @throws IllegalArgumentException the '@' character comes after the '.' character
	 */
	public void setEmail(String email) {
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Invalid email");
		}
		// checks for valid email
		if (!(email.contains("@"))) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!(email.contains("."))) {
			throw new IllegalArgumentException("Invalid email");
		}
		
		if (email.indexOf('@') > email.lastIndexOf('.')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns students password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the Students password 
	 * @param password the password to set
	 * @throws IllegalArgumentException if string is empty or null
	 * 
	 */
	public void setPassword(String password) {
		if ( password == null || password.equals("") ) {
			throw new IllegalArgumentException("Invalid password");
		} else {
			this.password = password;
		}
		
	}

	/* Generated Hash code
	 * @return result of hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/* Generated Equals
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	

}