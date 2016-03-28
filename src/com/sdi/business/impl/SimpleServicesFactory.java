package com.sdi.business.impl;



import com.sdi.business.LoginService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.UsersService;

public class SimpleServicesFactory implements ServicesFactory {


	@Override
	public UsersService createUserService() {
		return new SimpleUserService();
	}

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

}
