package com.sdi.business.impl;



import com.sdi.business.ServicesFactory;
import com.sdi.business.UsersService;

public class SimpleServicesFactory implements ServicesFactory {


	@Override
	public UsersService createUserService() {
		return new SimpleUserService();
	}

}
