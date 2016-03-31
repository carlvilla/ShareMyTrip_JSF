package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class SeatInsertar {

	public void insert(Long idUsuario, Long idViaje) {
		SeatDao dao = Factories.persistence.createSeatDao();
		Seat seat = new Seat();
		seat.setUserId(idUsuario);
		seat.setTripId(idViaje);
		seat.setStatus(SeatStatus.ACCEPTED);
		try {
			dao.save(seat);
		} catch (AlreadyPersistedException e) {
			System.out.println("Ya se aceptó esta solicitud");
		}
	}

}
