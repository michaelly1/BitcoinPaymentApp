package bitcoinapp;

public class User {
	private String guid;
	private String password;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String id, String pass)
	{
		guid = id;
		password = pass;
	}

	
}
