package com.sdi.presentation;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.UserLogin;

@ManagedBean(name = "trips")
@SessionScoped
public class BeanTrips implements Serializable {
	private static final long serialVersionUID = 55556L;

	private List<Trip> viajes;

	public List<Trip> getViajes() {
		return viajes;
	}

	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

	public String obtenerViajesDisponibles() {
		TripsService service = Factories.services.createTripService();
		
		if(userIsNotLoggedIn()){
			viajes = service.findAllDisponible();
			return "listadoPublico";
		}
		
		//AQUI HAY QUE BUSCAR LOS VIAJES EN LOS QUE EL USUARIO NO ES 
		//PROMOTOR, NI SOLICITANTE NI ACEPTADO.
		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		viajes = service.findAllDisponibleUser(usuario.getLogin());
		return "listadoRegistrado";
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
