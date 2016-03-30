package com.sdi.business;

import java.util.List;

import com.sdi.model.Seat;

public interface SeatService {
	
	/**
	 * Devuelve los objetos Seat aceptados de un viaje
	 * cuyo id es pasado como parámetro
	 * 
	 * @param long1
	 * @return
	 */
	public List<Seat> findPlazasAceptadas(Long idViaje);

	/**
	 * Devuelve los Seat en los que el usuario ha sido aceptado
	 * 
	 * @param id
	 * @return 
	 */
	public List<Seat> findAceptadasByUser(Long id);
}
