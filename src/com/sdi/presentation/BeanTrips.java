package com.sdi.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

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

	public void obtenerViajes(ActionEvent event) {
		TripsService service = Factories.services.createTripService();
		viajes = service.findAll();
	}
}
