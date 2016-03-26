package com.sdi.business.impl;

import com.sdi.business.UsersService;
import com.sdi.business.impl.classes.*;

import java.util.List;



import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.User;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleUserService implements UsersService {

	@Override
	public List<User> getUsers() throws Exception{
		return new UsersListado().getUsers();
	}

	@Override
	public void saveUser(User user) throws EntityAlreadyExistsException {
		new UsersAlta().save(user);
	}

	@Override
	public User findById(Long id) throws EntityNotFoundException {
		return new UsersBuscar().find(id);
	}
}
