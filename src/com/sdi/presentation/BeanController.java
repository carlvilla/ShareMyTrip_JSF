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

@ManagedBean(name="controller")
@SessionScoped
public class BeanController implements Serializable {
	private static final long serialVersionUID = 55556L;

	@ManagedProperty(value="#{user}") 
    private BeanUser user;
    public BeanUser getUser() { return user; }
    public void setUser(BeanUser user) {this.user = user;}
    
    //Usado para los viaje seleccionados 
    private Trip viaje;
    
    private List<User> participantes;
    
    public List<User> getParticipantes(){
    	return participantes;
    }
    
    public void setParticipantes(List<User> participantes){
    	this.participantes=participantes;
    }
    
    public void setViaje(Trip viaje){
    	this.viaje = viaje;
    	
    }
    
    public Trip getViaje(){
    	return viaje;
    }

  
    @PostConstruct
    public void init() {        
      System.out.println("BeanAlumnos - PostConstruct"); 

        user = new BeanUser();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "user", user);
        
      
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
			
			UserLogin userLogin = new UserLogin(user.getLogin(),user.getName());
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
	
	public String mostrarDatosViaje(Trip viaje){
	      if (userIsNotLoggedIn()) { 
	    	  return "fracaso";
	      }
	      
	      this.viaje = viaje;
	      cargarParticipantesViaje(viaje);
	      return "exito";
	      
	}
	
	private void cargarParticipantesViaje(Trip viaje) {
		SeatService serviceS;
		UsersService serviceU;

			serviceS = Factories.services.createSeatService();
			serviceU = Factories.services.createUserService();
			List<Seat> plazasAceptadas = serviceS.findPlazasAceptadas(viaje.getId());

			participantes = new LinkedList<User>();
			
			for(Seat plaza:plazasAceptadas){
				
				try {
					User usuario = serviceU.findById(plaza.getUserId());
					
					participantes.add(usuario);
					
				} catch (EntityNotFoundException e) {
					
				}
					
			}
	}
	
	
	
	private boolean userIsNotLoggedIn() {
		UserLogin usuariologueado = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		if (usuariologueado != null) {

			System.out.println("usuario activo: " + usuariologueado.getName());
			return false;
		}

		return true;
	}
	
	private Object getObjectFromSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(key);

	}

}
