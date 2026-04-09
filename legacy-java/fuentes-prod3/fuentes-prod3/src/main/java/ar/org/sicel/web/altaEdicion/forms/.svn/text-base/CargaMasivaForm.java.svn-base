package ar.org.sicel.web.altaEdicion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import ar.org.sicel.persistence.Usuario;


public class CargaMasivaForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String tipoActualizacion = "";
	private String raza = "";
	private String sexo = "";
	private String origen = "";
	private FormFile archivoExcel;
	private Usuario user;
	private boolean cambioRaza=false;
	private boolean cambioSexo=false;
	private String razaAnterior = "HOLA";

	public String getTipoActualizacion() {
		return tipoActualizacion;
	}

	public void setTipoActualizacion(String tipoActualizacion) {
		this.tipoActualizacion = tipoActualizacion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getRaza() {
		return raza;
	}

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.setOrigen("");
		this.setRaza("");
		this.setSexo("");
		this.setTipoActualizacion("");
		this.setCambioRaza(false);
		this.setCambioSexo(false);
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public FormFile getArchivoExcel() {
		return archivoExcel;
	}

	public void setArchivoExcel(FormFile archivoExcel) {
		this.archivoExcel = archivoExcel;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isCambioRaza() {
		return cambioRaza;
	}

	public void setCambioRaza(boolean cambioRaza) {
		this.cambioRaza = cambioRaza;
	}

	public boolean isCambioSexo() {
		return cambioSexo;
	}

	public void setCambioSexo(boolean cambioSexo) {
		this.cambioSexo = cambioSexo;
	}

	public String getRazaAnterior() {
		return razaAnterior;
	}

	public void setRazaAnterior(String razaAnterior) {
		this.razaAnterior = razaAnterior;
	}
	
	


}
