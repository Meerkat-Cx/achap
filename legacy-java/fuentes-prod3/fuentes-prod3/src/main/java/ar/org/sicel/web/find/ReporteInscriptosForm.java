/**
 * 
 */
package ar.org.sicel.web.find;

import org.apache.struts.action.ActionForm;


/**
 * @author rrodriguez
 *
 */
public class ReporteInscriptosForm extends ActionForm {

	private String fechaDesde = null;
	private String fechaHasta = null;


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

	
}
