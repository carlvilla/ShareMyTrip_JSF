package com.sdi.model;

import java.io.Serializable;

public class UserLogin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public UserLogin(String login, String name) {
		super();
		this.login = login;
		this.name = name;
	}
	private String login;
	private String name;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
