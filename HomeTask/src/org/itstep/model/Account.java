package org.itstep.model;

public class Account {
	private String login;
	private String password;
	private String firstName;
	private String secondName;
		
	public Account() {		
	}
	
	public Account(String login, String password, String firstName, String secondName) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	

}
