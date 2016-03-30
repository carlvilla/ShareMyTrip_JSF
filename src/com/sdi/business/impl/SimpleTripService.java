package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.TripsService;
import com.sdi.business.impl.classes.TripsBuscar;
import com.sdi.business.impl.classes.TripsListado;
import com.sdi.model.Trip;


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
	public Trip findById(Long tripId) {
		return new TripsBuscar().getTrip(tripId);
	}

	@Override
	public List<Trip> findByPromoter(Long id) {
		return new TripsBuscar().getByPromoter(id);
	}


}
