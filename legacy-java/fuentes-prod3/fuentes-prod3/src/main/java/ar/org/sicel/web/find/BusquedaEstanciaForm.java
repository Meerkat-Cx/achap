package ar.org.sicel.web.find;

import org.apache.struts.action.ActionForm;

public class BusquedaEstanciaForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idEst;
	private String nombre;
	private String nombrePropietario;
	private String rolAdmin;
	private String rol;
	private String idEclo;
	private Boolean activo;
	
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getIdEclo() {
		return idEclo;
	}
	public void setIdEclo(String idEclo) {
		this.idEclo = idEclo;
	}
	public String getIdEst() {
		return idEst;
	}
	public void setIdEst(String idEst) {
		this.idEst = idEst;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getRolAdmin() {
		return rolAdmin;
	}
	public void setRolAdmin(String rolAdmin) {
		this.rolAdmin = rolAdmin;
	}
	
	public void reset() {
		idEst=null;
		nombre=null;
		nombrePropietario=null;
		rolAdmin=null;
		rol=null;
		idEclo=null;
		activo=new Boolean(true);
	}
}
