package com.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.business.SeatService;
import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;

@ManagedBean(name = "trip")
@SessionScoped
public class BeanTrip implements Serializable {
	private static final long serialVersionUID = 55556L;

	
	private List<User> solicitantes;
	private List<User> aceptados;
	
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
	
	public void obtenerImplicados(Trip viaje){
		
		obtenerSolicitantes(viaje);
		obtenerAceptados(viaje);
		
	}
	
	private void obtenerAceptados(Trip viaje) {
		
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
	
	private void obtenerSolicitantes(Trip viaje) {
		// TODO Auto-generated method stub
		
	}
	

}
