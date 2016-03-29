package com.sdi.business.impl;

import com.sdi.business.LoginService;
import com.sdi.business.impl.classes.LoginVerify;
import com.sdi.model.UserLogin;

public class SimpleLoginService implements LoginService {
	@Override
	public UserLogin verify(String login, String password) {
		if (!validLogin(login, password))
			return null;
		return new UserLogin(login, "Sr Antúnez");
	}

	private boolean validLogin(String login, String password) {
		return new LoginVerify().verifyPassword(login, password);
	}
}