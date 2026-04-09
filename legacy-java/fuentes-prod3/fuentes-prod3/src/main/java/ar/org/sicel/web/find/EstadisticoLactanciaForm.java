/**
 * 
 */
package ar.org.sicel.web.find;

import org.apache.struts.action.ActionForm;


/**
 * @author rrodriguez
 *
 */
public class EstadisticoLactanciaForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String fechaDesde = null;
	private String fechaHasta = null;
    private String raza = null;
    private Boolean conPed= false;
    private Boolean sinPed= false;
    private Boolean todas= true;
    private Boolean porCat= false;

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Boolean getConPed() {
		return conPed;
	}

	public void setConPed(Boolean conPed) {
		this.conPed = conPed;
	}

	public Boolean getSinPed() {
		return sinPed;
	}

	public void setSinPed(Boolean sinPed) {
		this.sinPed = sinPed;
	}

	public Boolean getTodas() {
		return todas;
	}

	public void setTodas(Boolean todas) {
		this.todas = todas;
	}

	public Boolean getPorCat() {
		return porCat;
	}

	public void setPorCat(Boolean porCat) {
		this.porCat = porCat;
	}


	
}
