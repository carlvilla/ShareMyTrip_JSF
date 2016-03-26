package com.sdi.business.impl.classes;

import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

public class UsersBuscar {

	public User find(Long id) throws EntityNotFoundException {
		UserDao dao = Factories.persistence.createUserDao();
		User u = dao.findById(id);
		if ( u == null) {
			throw new EntityNotFoundException("No se ha encontrado el alumno");
		}
		
		return u;
	}

}
