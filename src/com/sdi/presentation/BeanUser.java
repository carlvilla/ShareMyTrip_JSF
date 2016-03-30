package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.model.User;

@ManagedBean
@SessionScoped
public class BeanUser extends User implements Serializable {
	  private static final long serialVersionUID = 55556L;
	  
	  private String confPassword;
	 
	  public BeanUser() {
	   // iniciaUser(null);
	  }
/*	
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
*/
	    
		public String getConfPassword() {
			return confPassword;
		}
		public void setConfPassword(String confPassword) {
			this.confPassword = confPassword;
		}  
		
		public void validarContraseñas(){	
		/*	if(!getPassword().equals(getConfPassword())){

				FacesMessage message =
						new FacesMessage("#{msgs.formAlta_confContraseña_validatorMessage}");
				throw new ValidatorException(message);
			
			}
		}*/
		
		}
		
}
