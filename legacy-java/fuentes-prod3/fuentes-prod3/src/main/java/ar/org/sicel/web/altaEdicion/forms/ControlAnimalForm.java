/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import java.util.Date;
import java.util.Set;

import org.apache.struts.action.ActionForm;

/**
 * @author jdivars
 *
 */
public class ControlAnimalForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date fecha;
	private String rp;
	private int ordenies;
	
	private String nombreTambo;
	private String nombreEstablecimiento;
	private String nombrePropietario;
	private String cantidadLeche;
	
	public int getOrdenies() {
		return ordenies;
	}
	public void setOrdenies(int ordenies) {
		this.ordenies = ordenies;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCantidadLeche() {
		return cantidadLeche;
	}
	public void setCantidadLeche(String cantidadLeche) {
		this.cantidadLeche = cantidadLeche;
	}
	public String getNombreEstablecimiento() {
		return nombreEstablecimiento;
	}
	public void setNombreEstablecimiento(String nombreEstablecimiento) {
		this.nombreEstablecimiento = nombreEstablecimiento;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	public String getNombreTambo() {
		return nombreTambo;
	}
	public void setNombreTambo(String nombreTambo) {
		this.nombreTambo = nombreTambo;
	} 
	
}
