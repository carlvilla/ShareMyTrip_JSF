package com.sdi.business.impl;



import com.sdi.business.LoginService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;

public class SimpleServicesFactory implements ServicesFactory {


	@Override
	public UsersService createUserService() {
		return new SimpleUserService();
	}

	@Override
<<<<<<< HEAD
	public TripsService createTripService() {
		return new SimpleTripService();
=======
	public LoginService createLoginService() {
		return new SimpleLoginService();
>>>>>>> origin/master
	}

}
