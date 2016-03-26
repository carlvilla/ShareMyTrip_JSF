package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;

@ManagedBean(name="controller")
@SessionScoped
public class BeanUsers implements Serializable {
	private static final long serialVersionUID = 55556L;

	@ManagedProperty(value="#{user}") 
    private BeanUser user;
    public BeanUser getUser() { return user; }
    public void setUser(BeanUser user) {this.user = user;}

	public String registrar() {
		UsersService service;
		try {

			service = Factories.services.createUserService();
			service.saveUser(user);

			return "principal";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "registrar";
		}

	}
}
