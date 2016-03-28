package com.sdi.business;

import java.util.List;

import com.sdi.model.Trip;


public interface TripsService {

	List<Trip> findAll();
	List<Trip> findAllDisponible();

}