package ar.org.sicel.persistence;

import java.util.Set;

/**
 * Entidad que representa un ESTABLECIMENTO, un conjunto de tambos, en nuestro sistema
 * llamados Establecimiento
 * @author Orona
 *
 */
public class Estancia extends Contacto {

	//private String nombre;
	//private String cuig;
	
	/**
	 * Eclo a la que pertenece la estancia
	 */
	//private Eclo eclo;
	
	/**
	 * Propietario de la estancia
	 */
	private Propietario propietario;
	private Set establecimientos;
	private Boolean activoPropietario = new Boolean(true);
	//private Boolean activoEclo = new Boolean(true);
	private Boolean activo = new Boolean(true);
	   
   /* public Boolean getActivoEclo() {
		return activoEclo;
	}

	public void setActivoEclo(Boolean activoEclo) {
		this.activoEclo = activoEclo;
	}*/

	public Boolean getActivoPropietario() {
		return activoPropietario;
	}

	public void setActivoPropietario(Boolean activoPropietario) {
		this.activoPropietario = activoPropietario;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	/*public String getCuig() {
		return cuig;
	}
	public void setCuig(String cuig) {
		this.cuig = cuig;
	}*/
	/*public Eclo getEclo() {
		return eclo;
	}
	public void setEclo(Eclo eclo) {
		this.eclo = eclo;
	}*/
	/*public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}*/
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
	public Set getEstablecimientos() {
		return establecimientos;
	}
	
	public void setEstablecimientos(Set establecimientos) {
		this.establecimientos = establecimientos;
	}


}
