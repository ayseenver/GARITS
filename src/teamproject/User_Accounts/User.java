package teamproject.User_Accounts;

public class User {

	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String roleName;

    public User() {
    }
        
	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		// TODO - implement User.login
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement User.logout
		throw new UnsupportedOperationException();
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * 
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}