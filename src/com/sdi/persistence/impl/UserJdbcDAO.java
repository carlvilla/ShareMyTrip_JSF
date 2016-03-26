package com.sdi.persistence.impl;

import java.sql.*;
import java.util.*;

import com.sdi.model.User;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.exception.*;


/**
 * Implementaci��n de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podr��a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos m��s adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author alb
 *
 */
public class UserJdbcDAO implements UserDao  {

	// En una implemenntación más sofisticada estas constantes habría 
	// que sacarlas a un sistema de configuración: 
	// xml, properties, descriptores de despliege, etc 
	String SQL_DRV = "org.hsqldb.jdbcDriver";
	String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	public List<User> getUsers() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<User> usuarios = new ArrayList<User>();

		try {
	
			// Obtenemos la conexión a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from user");
			rs = ps.executeQuery();

			while (rs.next()) {
				User usuario = new User();
				usuario.setId(rs.getLong("ID"));
				usuario.setName(rs.getString("NOMBRE"));
				usuario.setSurname(rs.getString("APELLIDOS"));
				usuario.setEmail(rs.getString("EMAIL"));
				
				//COMPLETAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
				usuarios.add(usuario);
			}
		}
       catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuarios;
	}

	
	public User findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		User usuario = null;
		
		try {
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from alumno where id = ?");
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new User();
	
				
				//COMPLETAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
				
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuario;
	}

	public Long save(User u) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			
			
			//COMPLETAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into tusers (login,name, surname,email,password,status) " +
					"values (?, ?, ?, ?,?,0)");
			
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getName());
			ps.setString(3, u.getSurname());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("User " + u + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		return null;
	}


	@Override
	public int update(User dto) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
