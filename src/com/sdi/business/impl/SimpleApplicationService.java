package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.ApplicationService;
import com.sdi.business.TripsService;
import com.sdi.business.impl.classes.ApplicationSave;
import com.sdi.business.impl.classes.TripsListado;
import com.sdi.model.Trip;


public class SimpleApplicationService implements ApplicationService {

	@Override
	public void save(String loginUsuario, Long idViaje) {
		new ApplicationSave().save(loginUsuario,idViaje);
	}

}
