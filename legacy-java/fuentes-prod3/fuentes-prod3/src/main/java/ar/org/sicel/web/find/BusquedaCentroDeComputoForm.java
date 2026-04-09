package ar.org.sicel.web.find;

import org.apache.struts.action.ActionForm;

public class BusquedaCentroDeComputoForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idC;
	private String nombre;
	private String rolAdmin;
	private String rol;
	private String idEclo;
	private Boolean activo;
	
	private java.lang.String ciudad;
    private java.lang.String direccion;
    private java.lang.String codigoPostal;
    private java.lang.String mail;
    private java.lang.String telefono;
	
	public void reset() {
		idC=null;
		nombre=null;		
		rolAdmin=null;
		rol=null;
		idEclo=null;
		activo=new Boolean(true);
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getIdC() {
		return idC;
	}

	public void setIdC(String idC) {
		this.idC = idC;
	}

	public String getIdEclo() {
		return idEclo;
	}

	public void setIdEclo(String idEclo) {
		this.idEclo = idEclo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public java.lang.String getCiudad() {
		return ciudad;
	}

	public void setCiudad(java.lang.String ciudad) {
		this.ciudad = ciudad;
	}

	public java.lang.String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(java.lang.String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public java.lang.String getDireccion() {
		return direccion;
	}

	public void setDireccion(java.lang.String direccion) {
		this.direccion = direccion;
	}

	public java.lang.String getMail() {
		return mail;
	}

	public void setMail(java.lang.String mail) {
		this.mail = mail;
	}

	public java.lang.String getTelefono() {
		return telefono;
	}

	public void setTelefono(java.lang.String telefono) {
		this.telefono = telefono;
	}
}
