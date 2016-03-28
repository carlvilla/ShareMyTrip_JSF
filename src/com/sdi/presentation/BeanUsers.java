package com.sdi.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

  
    @PostConstruct
    public void init() {        
      System.out.println("BeanAlumnos - PostConstruct"); 
      user = (BeanUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("user"));
      if (user == null) { 
        System.out.println("BeanAlumnos - No existia");
        user = new BeanUser();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "user", user);
      }
    }
    @PreDestroy
    public void end()  {
        System.out.println("BeanAlumnos - PreDestroy");
    }
    
	public String registrar() {
		UsersService service;
		try {

			service = Factories.services.createUserService();
			service.saveUser(user);

			return "principal";
		}

		catch (Exception e) {
			return null;
		}

	}
}
