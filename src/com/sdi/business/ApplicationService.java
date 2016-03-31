package com.sdi.business;

import java.util.List;

import com.sdi.model.Application;


public interface ApplicationService {
	
	public void save(String string,Long idViaje);
	
	/**
	 * Devuelve las solicitudes realizadas por un usuario
	 * @param login
	 * @return
	 */
	public List<Application> getSolicitudes(String login);
	

	/**
	 * Devuelve las solicitudes realizadas para un viaje
	 * @param id
	 * @return
	 */
	public List<Application> getSolicitudesViaje(Long id);
}
