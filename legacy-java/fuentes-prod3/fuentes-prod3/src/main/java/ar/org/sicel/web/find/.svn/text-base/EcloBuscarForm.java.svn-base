package ar.org.sicel.web.find;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EcloBuscarForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String identificador;
	private String nombre;
	private String nombreRegional;
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreRegional() {
		return nombreRegional;
	}
	public void setNombreRegional(String nombreRegional) {
		this.nombreRegional = nombreRegional;
	}
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.setIdentificador(null);
		this.setNombre("");
		this.setNombreRegional("");
	}
}
