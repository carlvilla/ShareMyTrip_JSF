package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.persistence.SeatDao;

public class SeatBuscar {

	public List<Seat> getPlazasAceptadas(Long long1) {
		SeatDao dao = Factories.persistence.createSeatDao();
		List<Seat> seats = dao.findAllAceptadas(long1);
		return seats;
		
	}

}
