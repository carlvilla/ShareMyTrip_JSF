package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.SeatService;
import com.sdi.business.impl.classes.SeatBuscar;
import com.sdi.business.impl.classes.SeatInsertar;
import com.sdi.model.Seat;

public class SimpleSeatService implements SeatService {

	@Override
	public List<Seat> findPlazasAceptadas(Long long1) {
		return new SeatBuscar().getPlazasAceptadas(long1);
		
	}

	@Override
	public List<Seat> findAceptadasByUser(Long id) {
		return new SeatBuscar().getPlazasAcepByUser(id);	
	}

	@Override
	public void insert(Long idUsuario, Long idViaje) {
		new SeatInsertar().insert(idUsuario,idViaje);
		
	}


	
}
