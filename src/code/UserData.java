package code;

public class UserData {

	private int id; // ±àºÅ
	private String userName; // ÓÃ»§Ãû
	private String password; // ÃÜÂë
	
	
	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserData(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
