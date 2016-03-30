package com.sdi.business.impl.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.model.Waypoint;
import com.sdi.persistence.TripDao;
import com.sdi.presentation.BeanRegisterTrip;


public class TripsListado {

	public List<Trip> getTrips() {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findAll();
	}
	
	public List<Trip> getTripsDisponibles() {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findAllDisponible();
	}

	public List<Trip> getTripsDisponiblesUser(String login) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findAllDisponibleUser(login);
	}

	public boolean registerTrip(BeanRegisterTrip trip) {
		TripDao dao = Factories.persistence.createTripDao();
		
		Trip viaje = new Trip();
		
		Waypoint wSalida = new Waypoint(null,null);
		//Creamos AdreesPoint de la Salida
		if(trip.getLatZipCodeFrom().compareTo("")!=0 && trip.getLongZipCodeFrom().compareTo("")!=0){
			wSalida = new Waypoint(
					Double.parseDouble(trip.getLatZipCodeFrom()),
					Double.parseDouble(trip.getLongZipCodeFrom()));
		}
	
		AddressPoint addresSalida = new AddressPoint(trip.getAdressFrom(),
				trip.getCityFrom(), trip.getProvinceFrom(), trip.getCountryFrom(),
				trip.getPostalCodeFrom(), wSalida);
		
		//Creamos AdreesPoint del Destino
		Waypoint wDestino = new Waypoint(null,null);
		//Creamos AdreesPoint de la Salida
		if(trip.getLatZipCodeTo().compareTo("")!=0 && trip.getLongZipCodeTo().compareTo("")!=0){
			wDestino = new Waypoint(
					Double.parseDouble(trip.getLatZipCodeTo()),
					Double.parseDouble(trip.getLongZipCodeTo()));
		}
		
		AddressPoint addresDestino = new AddressPoint(trip.getAdressTo(),
				trip.getCityTo(), trip.getProvinceTo(), trip.getCountryTo(),
				trip.getPostalCodeTo(), wDestino);

		viaje.setDeparture(addresSalida);
		viaje.setDestination(addresDestino);
		
//		SimpleDateFormat formatoDeFecha = new SimpleDateFormat(
//				"dd/MM/yyyy-HH:mm:ss");
//		newTrip.setDepartureDate(formatoDeFecha.parse(fechaHoraSalida));
//		newTrip.setArrivalDate(formatoDeFecha.parse(fechaHoraLLegada));
//		newTrip.setClosingDate(formatoDeFecha.parse(fechaLimite));
//
//		Date fechaActual = new Date();
//		if(newTrip.getArrivalDate().before(fechaActual)
//				||newTrip.getDepartureDate().before(fechaActual)
//				||newTrip.getClosingDate().before(fechaActual)){
//			request.setAttribute("error", "Error al modificar: FECHAS ANTERIORES A LA FECHA ACTUAL");
//			return "FRACASO";
//		}
//		
//		//Comprobamos fechas
//		if(formatoDeFecha.parse(fechaHoraSalida)
//				.after(formatoDeFecha.parse(fechaHoraLLegada))||
//				formatoDeFecha.parse(fechaLimite).after(formatoDeFecha.parse(fechaHoraSalida))){
//			request.setAttribute("error", "Error al registrarse: FECHAS INCORRECTAS");
//			return "FRACASO";
//		}
		
		viaje.setEstimatedCost(Double.parseDouble(trip.getPriceTrip()));
		viaje.setComments(trip.getCommentTrip());
		viaje.setMaxPax(Integer.parseInt(trip.getMaxSeats()));
		viaje.setAvailablePax(Integer.parseInt(trip.getAvailableSeats()));
		viaje.setStatus(TripStatus.OPEN);
		
		//Comprobamos numeros negativos o concordancia de plazas
		if(viaje.getEstimatedCost()<=0||viaje.getMaxPax()<=0||
				viaje.getAvailablePax()<=0 
				|| viaje.getMaxPax() < viaje.getAvailablePax()){
			return false;
		}
		
		//Obtenemos el id del promotor 
		//newTrip.setPromoterId(userByLogin.getId());
		/*
		Trip tripMismaFecha = dao
				.findByPromoterIdAndArrivalDate(userByLogin.getId(), 
						formatoDeFecha.parse(fechaHoraSalida));
		if(tripMismaFecha!=null){
			request.setAttribute("error", "Error al registrarse: YA HAS CREADO UN VIAJE EN ESA MISMA FECHA");
			return "FRACASO";
		}
		
		//Añadimos el viaje a la BD
		TripDao trip = PersistenceFactory.newTripDao();
		trip.save(newTrip);
		
		SeatDao daoSeat = PersistenceFactory.newSeatDao();
		Seat asiento = new Seat();
		asiento.setStatus(SeatStatus.ACCEPTED);
		asiento.setUserId(usuario.getId());
		
		TripDao tripDao = PersistenceFactory.newTripDao();
		Trip viaje = tripDao.findByPromoterIdAndArrivalDate(usuario.getId(), newTrip.getArrivalDate());
		asiento.setTripId(viaje.getId());
		daoSeat.save(asiento);
		*/

		return true;
	} 

}
