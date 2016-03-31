package com.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.business.ApplicationService;
import com.sdi.business.SeatService;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.TripImplicacion;
import com.sdi.model.User;

@ManagedBean(name = "trip")
@SessionScoped
public class BeanTrip implements Serializable {
	private static final long serialVersionUID = 55556L;

	
	private List<User> solicitantes;
	private List<User> aceptados;
	
	private TripImplicacion viaje;
	
	public List<User> getSolicitantes() {
		return solicitantes;
	}
	public void setSolicitantes(List<User> solicitantes) {
		this.solicitantes = solicitantes;
	}
	public List<User> getAceptados() {
		return aceptados;
	}
	public void setAceptados(List<User> aceptados) {
		this.aceptados = aceptados;
	}
	
	public void obtenerImplicados(TripImplicacion viaje){
		
		setViaje(viaje);
		obtenerSolicitantes();
		obtenerAceptados();
		
	}
	
	public void obtenerAceptados() {
		
			SeatService serviceS;
			UsersService serviceU;

			serviceS = Factories.services.createSeatService();
			serviceU = Factories.services.createUserService();
			List<Seat> plazasAceptadas = serviceS
					.findPlazasAceptadas(viaje.getId());

			aceptados = new LinkedList<User>();

			for (Seat plaza : plazasAceptadas) {

				User usuario = serviceU.findById(plaza.getUserId());
				aceptados.add(usuario);
			
			}
			
		
	}
	
	private void obtenerSolicitantes() {
			ApplicationService serviceA = Factories.services.createApplicationService();
			UsersService serviceU = Factories.services.createUserService();
			
			List<Application> solicitudes = serviceA.getSolicitudesViaje(viaje.getId());
	
			solicitantes = new LinkedList<User>();

			for (Application solicitud : solicitudes) {

				User usuario = serviceU.findById(solicitud.getUserId());
				solicitantes.add(usuario);
			
			}
	}
	
	public void aceptarSolicitud(User persona){
		SeatService serviceS = Factories.services.createSeatService();
		TripsService serviceT = Factories.services.createTripService();
	
		serviceS.insert(persona.getId(),viaje.getId());	
		serviceT.ocuparPlaza(viaje.getId());
		
		eliminarSolicitud(persona);
		
		aceptados.add(persona);
	}
	
	
	public void eliminarSolicitud(User persona){
		ApplicationService service = Factories.services.createApplicationService();
		service.deleteByUser(persona.getId(),viaje.getId());
		solicitantes.remove(persona);
	}
	
	public TripImplicacion getViaje() {
		return viaje;
	}
	public void setViaje(TripImplicacion viaje) {
		this.viaje = viaje;
	}
	

}
