/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class RazaForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id;
	private FormFile foto;
	private String nombre;
	private String idEspecie;
	private boolean escruza;
	private boolean esdesconocido;
	private String categoriaPura;
	
	//	 Constructors -----------------------------------------------------------
    public RazaForm() {
    }


	public FormFile getFoto() {
		return foto;
	}

	public void setFoto(FormFile foto) {
		this.foto = foto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(String idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {		
		super.reset(arg0, arg1);
		this.setEscruza(false);
		this.setEsdesconocido(false);
	}


	public boolean isEscruza() {
		return escruza;
	}


	public void setEscruza(boolean escruza) {
		this.escruza = escruza;
	}


	public boolean isEsdesconocido() {
		return esdesconocido;
	}


	public void setEsdesconocido(boolean esdesconocido) {
		this.esdesconocido = esdesconocido;
	}


	public String getCategoriaPura() {
		return categoriaPura;
	}


	public void setCategoriaPura(String categoriaPura) {
		this.categoriaPura = categoriaPura;
	}


}
