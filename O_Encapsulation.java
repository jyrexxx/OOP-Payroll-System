package payroll;

// encapsulation setters and getters
public class O_Encapsulation {
	private String username;
	private String password;

	private void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setCredentials(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
}