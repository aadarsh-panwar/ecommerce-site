package com.example.shop.models.request;

public class RegistrationRequest {
	private String username;
	private String password;
	
	public RegistrationRequest() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegistrationRequest [username=" + username + ", password=" + password + "]";
	}
	
}
