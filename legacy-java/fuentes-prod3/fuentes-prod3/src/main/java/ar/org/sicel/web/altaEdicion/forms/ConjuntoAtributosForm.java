package ar.org.sicel.web.altaEdicion.forms;

import org.apache.struts.action.ActionForm;

import ar.org.sicel.persistence.ConjuntoAtributos;
import ar.org.sicel.persistence.ConjuntoAtributosDAO;

/**
 * author Ramiro Trachsel
 *
 */
public class ConjuntoAtributosForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -19788600070543206L;
	ConjuntoAtributos conjuntoAtributos = ConjuntoAtributosDAO.create(null);
	Boolean esEdicion = new Boolean(false);
	Long id = new Long(-1);
	private String nombre="";
	private Long idAtributo= new Long(-1); 
	private String nombreAtributo="";
    private Boolean esIntervalo;
    
	public ConjuntoAtributosForm() {
		conjuntoAtributos.setEsIntervalo(false);		
	}

	/*public ConjuntoAtributos getConjuntoAtributos() {
		return conjuntoAtributos;
	}

	public void setConjuntoAtributos(ConjuntoAtributos conjuntoAtributos) {
		this.conjuntoAtributos = conjuntoAtributos;
	}*/

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
	
	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre= nombre;
	}

	public Long getIdAtributo() {
		return idAtributo;
	}

	public void setIdAtributo(Long idAtributo) {
		this.idAtributo = idAtributo;
	}
	
	public String getNombreAtributo(){
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo){
		this.nombreAtributo= nombreAtributo;
	}

	public Boolean getEsIntervalo() {
        return esIntervalo;
    }

    public void setEsIntervalo(Boolean esIntervalo) {
        this.esIntervalo = esIntervalo;
    }
}
