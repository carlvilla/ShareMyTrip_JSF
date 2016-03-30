package com.sdi.business;

import java.util.List;

import com.sdi.model.Application;


public interface ApplicationService {
	
	public void save(String string,Long idViaje);
	public List<Application> getSolicitudes(String login);
}
