/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.PersonaOtroDAO;

/**
 * @author Ramiro Trachsel
 *
 */
/**
*
* struts.form
*      name="personaForm"
*
*/
public class PersonaForm extends ContactoForm {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8658873366733092279L;
	private String headerText;	
	private Boolean mostrarEmpresa = new Boolean(false);	
	private int ALTURA_MAX = -1;
    private int ANCHO_MAX = -1;  
	
	
	public PersonaForm() {
		super.contacto = PersonaOtroDAO.create(null, null, null, null, null, null, null);		
	}	
	
	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public Boolean getMostrarEmpresa() {
		return mostrarEmpresa;
	}

	public void setMostrarEmpresa(Boolean mostrarEmpresa) {
		this.mostrarEmpresa = mostrarEmpresa;
	}

	protected int getAlturaMax() {
		return ALTURA_MAX;
	}
	
	protected int getAnchoMax() {
		return ANCHO_MAX;		
	}
	
	
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO validar los campos 
		return super.validate(arg0, arg1);
	}

	
	
	
}
