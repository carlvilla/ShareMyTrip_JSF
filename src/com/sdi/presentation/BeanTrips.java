package com.sdi.presentation;

import java.io.Serializable;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.ApplicationService;
import com.sdi.business.SeatService;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.UserLogin;

@ManagedBean(name = "trips")
@SessionScoped
public class BeanTrips implements Serializable {
	private static final long serialVersionUID = 55556L;

	private List<Trip> viajes;

	// Usado para los viaje seleccionados
	private Trip viaje;
	private User promotorViaje;

	private List<User> participantes;

	public List<User> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<User> participantes) {
		this.participantes = participantes;
	}

	public void setViaje(Trip viaje) {
		this.viaje = viaje;

	}

	public Trip getViaje() {
		return viaje;
	}

	public User getPromotorViaje() {
		return promotorViaje;
	}

	public void setPromotorViaje(User promotorViaje) {
		this.promotorViaje = promotorViaje;
	}

	public String mostrarDatosViaje(Trip viaje) {
		if (userIsNotLoggedIn()) {
			return "fracaso";
		}

		this.viaje = viaje;
		cargarParticipantesViaje(viaje);
		cargarPromotor(viaje.getPromoterId());
		return "exito";

	}

	private void cargarPromotor(Long promoterId) {
		UsersService service;

		service = Factories.services.createUserService();
		promotorViaje = service.findById(promoterId);

	}

	private void cargarParticipantesViaje(Trip viaje) {
		SeatService serviceS;
		UsersService serviceU;

		serviceS = Factories.services.createSeatService();
		serviceU = Factories.services.createUserService();
		List<Seat> plazasAceptadas = serviceS
				.findPlazasAceptadas(viaje.getId());

		participantes = new LinkedList<User>();

		for (Seat plaza : plazasAceptadas) {

			User usuario = serviceU.findById(plaza.getUserId());
			participantes.add(usuario);

		}
	}

	public List<Trip> getViajes() {
		return viajes;
	}

	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

	/**
	 * Se buscan los viajes en los que el usuario puede participar.
	 * Esto depende de si es un usuario público o registrado.
	 * 
	 * @return
	 */
	public String obtenerViajesDisponibles() {
		TripsService service = Factories.services.createTripService();

		if (userIsNotLoggedIn()) {
			viajes = service.findAllDisponible();
			return "listadoPublico";
		}

		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		viajes = service.findAllDisponibleUser(usuario.getLogin());
		return "listadoRegistrado";
	}
	
	public void apuntarse(){
		ApplicationService app;
		app = Factories.services.createApplicationService();
		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		app.save(usuario.getLogin(), viaje.getId());
		
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("#{msgs.Apuntado}","#{msgs.ApuntadoTexto}"));

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
