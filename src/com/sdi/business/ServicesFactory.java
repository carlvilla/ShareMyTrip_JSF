package com.sdi.business;

public interface ServicesFactory {
	
	UsersService createUserService();
	LoginService createLoginService();
}
