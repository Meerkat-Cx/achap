package ar.org.sicel.web.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;

/**
 * Form para los datos necesarios en el cambio de clave de un usuario
 * @author Orona
 *
 */
public class CambiarClaveForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String passwdOld;
	private String passwdNew1;
	private String passwdNew2;
	
	private String emailActual;
	private String email;
	private String emailConfirmacion;

	public String getEmailConfirmacion() {
		return emailConfirmacion;
	}
	public void setEmailConfirmacion(String emailConfirmacion) {
		this.emailConfirmacion = emailConfirmacion;
	}
	public String getPasswdNew1() {
		return passwdNew1;
	}
	public void setPasswdNew1(String passwdNew1) {
		this.passwdNew1 = passwdNew1;
	}
	public String getPasswdNew2() {
		return passwdNew2;
	}
	public void setPasswdNew2(String passwdNew2) {
		this.passwdNew2 = passwdNew2;
	}
	public String getPasswdOld() {
		return passwdOld;
	}
	public void setPasswdOld(String passwdOld) {
		this.passwdOld = passwdOld;
	}
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.setPasswdNew1("");
		this.setPasswdNew2("");
		this.setPasswdOld("");
		Usuario usuario = (Usuario) arg1.getSession().getAttribute(Tokens.CURRENTUSER);
		this.setEmailActual(usuario.getEmail());
		this.setEmail("");
		this.setEmailConfirmacion("");
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailActual() {
		return emailActual;
	}
	public void setEmailActual(String emailActual) {
		this.emailActual = emailActual;
	}
	
	
	
	


}
