package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.TripsService;
import com.sdi.business.impl.classes.TripRegistrar;
import com.sdi.business.impl.classes.TripsBuscar;
import com.sdi.business.impl.classes.TripsListado;
import com.sdi.business.impl.classes.TripsModificar;
import com.sdi.model.Trip;
import com.sdi.presentation.BeanModificarViaje;
import com.sdi.presentation.BeanRegisterTrip;


public class SimpleTripService implements TripsService {

	@Override
	public List<Trip> findAll() {
		return new TripsListado().getTrips();
	}

	@Override
	public List<Trip> findAllDisponible() {
		return new TripsListado().getTripsDisponibles();
	}

	@Override
	public List<Trip> findAllDisponibleUser(String login) {
		return new TripsListado().getTripsDisponiblesUser(login);
	}

	@Override
	public boolean registrar(BeanRegisterTrip trip) {
		return new TripRegistrar().registerTrip(trip);
	}

	public Trip findById(Long tripId) {
		return new TripsBuscar().getTrip(tripId);
	}

	@Override
	public List<Trip> findByPromoter(Long id) {
		return new TripsBuscar().getByPromoter(id);
	}

	@Override
	public boolean modificar(BeanModificarViaje trip) {
		return new TripsModificar().modificarTrip(trip);
	}

	@Override
	public void ocuparPlaza(Long idViaje) {
		new TripsModificar().disminuirPlazas(idViaje);	
	}
	
	@Override
	public void liberarPlaza(Long idViaje) {
		new TripsModificar().aumentarPlazas(idViaje);
		
	}


}
