package com.sdi.presentation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

@ManagedBean(name = "cancelTrip")
@SessionScoped
public class BeanCancelarViaje implements Serializable {
	private static final long serialVersionUID = 55556L;

	private Map<Long,Trip> viajesCancelar = new  HashMap<Long, Trip>();
	
	
	public void addTrip(Trip viaje){
		if(viajesCancelar.containsKey(viaje.getId()))
			viajesCancelar.remove(viaje.getId());
		else
			viajesCancelar.put(viaje.getId(), viaje);
	}
	
	public String cancelarViajes(){
		if(!viajesCancelar.isEmpty()){
			TripsService trip = Factories.services.createTripService();
			trip.cancelarViajes(viajesCancelar);
		}
		return "cancelTrip";
	}
	

}