package com.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.SeatService;
import com.sdi.business.UsersService;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.UserLogin;

@ManagedBean(name = "controller")
@SessionScoped
public class BeanController implements Serializable {
	private static final long serialVersionUID = 55556L;

	@ManagedProperty(value = "#{user}")
	private BeanUser user;

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	
	@PostConstruct
	public void init() {
		System.out.println("BeanController - PostConstruct");

		user = (BeanUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	/*	FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("user", user);*/

	}

	@PreDestroy
	public void end() {
		System.out.println("BeanController - PreDestroy");
	}

	public String registrar() {
		UsersService service;
		try {

			service = Factories.services.createUserService();
			service.saveUser(user);
			
			User usuario = service.finByLogin(user.getLogin());
			UserLogin userLogin = new UserLogin(usuario.getLogin(), usuario.getName(), usuario.getId());
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
			putUserInSession(userLogin);

			return "principal";
		}

		catch (Exception e) {
			return null;
		}

	}

	private void putUserInSession(UserLogin user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}





}
