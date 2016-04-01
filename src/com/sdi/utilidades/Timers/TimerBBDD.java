package com.sdi.utilidades.Timers;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.sdi.business.ApplicationService;
import com.sdi.business.SeatService;
import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Trip;

public class TimerBBDD {
	
    public static void mantenimientoBBDD() {
    Timer timer;
    timer = new Timer();

    TimerTask task = new TimerTask() {
        int tic=0;

        @Override
        public void run()
        {
        	TripsService serviceT = Factories.services.createTripService();
        	ApplicationService serviceA = Factories.services.createApplicationService();
        	SeatService serviceS = Factories.services.createSeatService();
        	
        	List<Trip> viajes = serviceT.viajesFechaCierrePasada();
        	
        	for(Trip viaje:viajes){
        		List<Application> solicitudes = serviceA.getSolicitudesViaje(viaje.getId());
        		
        		for(Application app:solicitudes){
        			serviceA.delete(app.getUserId(), app.getTripId());
        			serviceS.insertSinPlazas(app.getUserId(),app.getTripId());
        			
        		}
        		
        	}
        	
        /*	
            if (tic%2==0)
            System.out.println("TIC");
            else
            System.out.println("TOC");
            tic++;
        */
        }
        };
        // Empezamos dentro de 10ms y luego lanzamos la tarea cada 5000ms
        timer.schedule(task, 10, 5000);
    }
}