package com.sdi.presentation;

import java.io.Serializable;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.ApplicationService;
import com.sdi.business.SeatService;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.ImplicacionStatus;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripImplicacion;
import com.sdi.model.TripStatus;
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

	// Viajes en los que tiene implicación el usuario
	private List<TripImplicacion> viajesImplicado;

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
	 * Se buscan los viajes en los que el usuario puede participar. Esto depende
	 * de si es un usuario público o registrado.
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

	public void apuntarse() {
		ApplicationService app;
		app = Factories.services.createApplicationService();
		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		app.save(usuario.getLogin(), viaje.getId());

		FacesContext context = FacesContext.getCurrentInstance();

		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msgs");

		context.addMessage(null, new FacesMessage(bundle.getString("Exito"),
				bundle.getString("ApuntadoTexto")));

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

	public List<TripImplicacion> getViajesImplicado() {
		return viajesImplicado;
	}

	public void setViajesImplicado(List<TripImplicacion> viajesImplicado) {
		this.viajesImplicado = viajesImplicado;
	}

	public void obtenerViajesImplicado() {

		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");

		viajesImplicado = new LinkedList<TripImplicacion>();

		obtenerViajesPromotor(usuario);
		obtenerViajesAceptado(usuario);
		obtenerViajesPendiente(usuario);

	}

	private void obtenerViajesPendiente(UserLogin usuario) {
		ApplicationService serviceA = Factories.services
				.createApplicationService();
		TripsService serviceT = Factories.services.createTripService();

		List<Application> solicitudes = serviceA.getSolicitudes(usuario
				.getLogin());

		for (Application app : solicitudes) {
			TripImplicacion viaje = new TripImplicacion(serviceT.findById(app
					.getTripId()));
			viaje.setImplicacion(ImplicacionStatus.PENDIENTE);
			viajesImplicado.add(viaje);
		}
	}

	private void obtenerViajesAceptado(UserLogin usuario) {

		SeatService serviceS = Factories.services.createSeatService();
		TripsService serviceT = Factories.services.createTripService();
		List<Seat> seats = serviceS.findAceptadasByUser(usuario.getId());

		List<Long> idsViajesEsPromotor = new LinkedList<Long>();
		
		for(TripImplicacion viajeAux:viajesImplicado){
			idsViajesEsPromotor.add(viajeAux.getId());
		}
		
		for (Seat seat : seats) {
			int idUsuario= Integer.parseInt(usuario.getId()+"");
			int idUsuarioSeat = Integer.parseInt(seat.getUserId()+"");
			if (seat.getStatus() != SeatStatus.EXCLUDED &&	idUsuario != idUsuarioSeat ) {
				TripImplicacion viaje = new TripImplicacion(
						serviceT.findById(seat.getTripId()));
				viaje.setImplicacion(ImplicacionStatus.ACEPTADO);
				viajesImplicado.add(viaje);
				
				
		//Esta comprobación es debida a que los promotores están 'aceptados'
		//en su propio viaje ya que ocupan una plaza. Por ello no nos 
		//interesa mostrar al promotor que está aceptado en su propio viaje 	
				if(!idsViajesEsPromotor.contains(viaje.getId())){
					viaje.setImplicacion(ImplicacionStatus.ACEPTADO);
					viajesImplicado.add(viaje); 
				}
					
			}
		}
	}

	private void obtenerViajesPromotor(UserLogin usuario) {
		TripsService service = Factories.services.createTripService();
		List<Trip> viajes = service.findByPromoter(usuario.getId());

		for (Trip viaje : viajes) {
			if (viaje.getStatus() != TripStatus.CANCELLED) {
				TripImplicacion viajeIm = new TripImplicacion(viaje);
				viajeIm.setImplicacion(ImplicacionStatus.PROMOTOR);
				viajesImplicado.add(viajeIm);
			}
		}
	}

}
