package pojo;

public class Test {
	private String login ;
	private String password ;
	
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Test [login=" + login + ", password=" + password + "]";
	}
	
	
	
	
}
