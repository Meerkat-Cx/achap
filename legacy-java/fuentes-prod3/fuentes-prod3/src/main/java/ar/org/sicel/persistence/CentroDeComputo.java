/**
 * @author jivars
 * @since 14/05/2008
 */
package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jivars
 *
 */
public class CentroDeComputo {

	private java.lang.Long id;
    private java.lang.String nombre;
    private java.lang.String ciudad;
    private java.lang.String direccion;
    private java.lang.String codigoPostal;
    private java.lang.String mail;
    private java.lang.String telefono;
    private Set bitacora=new HashSet();
    private Set tambos;
    private Boolean activo;
    
    public String getIdNombre(){
    	String nombreId;
    	if(nombre!=null && !nombre.equals("")) nombreId = id + ", " + nombre; 
    	else nombreId="Sin nombre";
    	return nombreId;
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
	public java.lang.String getNombre() {
		return nombre;
	}
	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}
	public java.lang.String getTelefono() {
		return telefono;
	}
	public void setTelefono(java.lang.String telefono) {
		this.telefono = telefono;
	}
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Set getBitacora() {
		return bitacora;
	}
	public void setBitacora(Set bitacora) {
		this.bitacora = bitacora;
	}

	public Set getTambos() {
		return tambos;
	}

	public void setTambos(Set tambos) {
		this.tambos = tambos;
	}
	
}
