package com.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import com.sdi.model.User;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser extends User implements Serializable {
	  private static final long serialVersionUID = 55556L;
	  
	  private String confPassword;
	 
	  public BeanUser() {
	    iniciaUser(null);
	  }
	//Este método es necesario para copiar el alumno a editar cuando
	//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
	//por un método editar en BeanAlumnos.
	  public void setUser(User user) {
	    setId(user.getId());
	    setLogin(user.getLogin());
	    setName(user.getName());
	    setSurname(user.getSurname());
	    setEmail(user.getEmail());
	  }
	//Iniciamos los datos del alumno con los valores por defecto 
	//extraídos del archivo de propiedades correspondiente
	    public void iniciaUser(ActionEvent event) {
	      FacesContext facesContext = FacesContext.getCurrentInstance();
	          ResourceBundle bundle = 
	           facesContext.getApplication().getResourceBundle(facesContext, "msgs");
	          setId(null);
	          setLogin(bundle.getString("valorDefectoUserId"));
	          setName(bundle.getString("valorDefectoNombre"));
	          setSurname(bundle.getString("valorDefectoApellidos"));
	          setEmail(bundle.getString("valorDefectoCorreo"));
	          setPassword(bundle.getString("valorDefectoContraseña"));
	          setConfPassword(bundle.getString("valorDefectoConfContraseña"));
	    }
		public String getConfPassword() {
			return confPassword;
		}
		public void setConfPassword(String confPassword) {
			this.confPassword = confPassword;
		}  
		
		public void validarContraseñas(){	
			if(!getPassword().equals(getConfPassword())){
				FacesMessage message =
						new FacesMessage("#{msgs.formAlta_confContraseña_validatorMessage}");
				throw new ValidatorException(message);
			}
			
			
		}
		
}
