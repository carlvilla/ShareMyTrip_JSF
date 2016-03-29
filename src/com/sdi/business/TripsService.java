package com.sdi.business;

import java.util.List;

import com.sdi.model.Trip;


public interface TripsService {

	List<Trip> findAll();
	List<Trip> findAllDisponible();
	
	/**
	 * Busca los viajes disponibles para un usuario registrado, teniendo en
	 * cuenta que no se puede apuntar a viajes en los que es promotor,
	 * está aceptado o mandó una solicitud
	 * 
	 * @param login
	 * @return
	 */
	List<Trip> findAllDisponibleUser(String login);

}