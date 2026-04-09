/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import org.apache.struts.validator.ValidatorActionForm;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;

/**
 * @author Ramiro Trachsel
 *
 */
/**
*
* struts.form
*      name="atributoForm"
*
*/
public class AtributoForm extends ValidatorActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5819615032461301870L;
	Atributo atributo = AtributoDAO.create(null);
	Long id = new Long(-1);
	String nombre= "";
	String descripcion= "";
	String valorPorDefecto = "";
	Boolean esEdicion = new Boolean(false);
	Long conjuntoAtributosId = new Long(-1);
	String valorAdmitido = "";
	Long valorAdmitidoId = new Long(-1);
	
	public AtributoForm() {
		
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public Boolean getEsEdicion() {
		return esEdicion;
	}

	public void setEsEdicion(Boolean esEdicion) {
		this.esEdicion = esEdicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre= nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion= descripcion;
	}

	public String getValorPorDefecto() {
		return valorPorDefecto;
	}

	public void setValorPorDefecto(String valorPorDefecto) {
		this.valorPorDefecto = valorPorDefecto;
	}

	public Long getConjuntoAtributosId() {
		return conjuntoAtributosId;
	}

	public void setConjuntoAtributosId(Long conjuntoAtributosId) {
		this.conjuntoAtributosId = conjuntoAtributosId;
	}

	public String getValorAdmitido() {
		return valorAdmitido;
	}

	public void setValorAdmitido(String valorAdmitido) {
		this.valorAdmitido = valorAdmitido;
	}

	public Long getValorAdmitidoId() {
		return valorAdmitidoId;
	}

	public void setValorAdmitidoId(Long valorAdmitidoId) {
		this.valorAdmitidoId = valorAdmitidoId;
	}

	

	
	
	
}
