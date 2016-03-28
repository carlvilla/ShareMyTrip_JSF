package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.TripsService;
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


}
