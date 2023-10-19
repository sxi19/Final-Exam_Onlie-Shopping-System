package UserManagement;

public abstract class User {
	
	private int UserID;
	private String name;
	private String email;
	
	
	
	public User(String name, String email) {
		//super();
		UserID = this.generateUserID();
		this.name = name;
		this.email = email;
	}

	
	private int generateUserID() {
		int min = 100000;
		int max = 999999;
		return java.util.concurrent.ThreadLocalRandom.current().nextInt(max - min + 1) + min;
	}

	public int getUserID() {
		return UserID;
	}




	public void setUserID(int userID) {
		UserID = userID;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}


}
