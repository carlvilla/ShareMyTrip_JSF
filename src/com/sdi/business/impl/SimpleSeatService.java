package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.SeatService;
import com.sdi.business.impl.classes.SeatBuscar;
import com.sdi.model.Seat;

public class SimpleSeatService implements SeatService {

	@Override
	public List<Seat> findPlazasAceptadas(Long long1) {
		return new SeatBuscar().getPlazasAceptadas(long1);
		
	}


	
}
