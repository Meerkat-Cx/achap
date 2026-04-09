package ar.org.sicel.web.altaEdicion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SistemaForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	// datos del sistema
    private Long id;
    private String nombre;
    private String version;
    private String comentario;
    
    // datos del responsable    
    private String nombreEmpresa; // nombre de la empresa requerido
    private String apellido; // apellido del responsable 
    private String nombreResponsable;
    private String tipoDocumento;
    private String numeroDocumento;
    private String comentarioResponsable;

	/**
	 * 
	 */
	public SistemaForm() {	
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentarioResponsable() {
		return comentarioResponsable;
	}

	public void setComentarioResponsable(String comentarioResponsable) {
		this.comentarioResponsable = comentarioResponsable;
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
		this.nombre = nombre;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.setApellido("");
		this.setComentario("");
		this.setComentarioResponsable("");
		this.setId(null);
		this.setNombre("");
		this.setNombreEmpresa("");
		this.setNombreResponsable("");
		this.setNumeroDocumento("");
		this.setTipoDocumento("");
		this.setVersion("");
	}
	
	

		

}
