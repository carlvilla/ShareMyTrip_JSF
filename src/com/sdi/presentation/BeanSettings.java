package com.sdi.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sdi.model.User;

@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	@ManagedProperty(value = "#{user}")
	private BeanUser user;

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	// Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera
	// creado
	// y en caso contrario se crea.
	// (hay que tener en cuenta que es un Bean de sesión)

	// Se usa @PostConstruct, ya que en el contructor no se sabe todavía si
	// el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");

		user = (BeanUser) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("user"));

		if (user == null) {
			System.out.println("BeanSettings - No existia");
			user = new BeanUser();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("user", user);

		}

	}

	// Es sólo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

	public Locale getLocale() {
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (user != null)
				user.iniciaUser(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (user != null)
				user.iniciaUser(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
