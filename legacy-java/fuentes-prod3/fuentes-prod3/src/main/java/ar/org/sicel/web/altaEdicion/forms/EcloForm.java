/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


public class EcloForm extends ActionForm  {
	
	private static final long serialVersionUID = 761684042321732452L;
	
	private Long id;
	private String nombre;
	private Long entidadRegionalId = new Long(0);
	private String nombreRegional;
	private String comentario;
	protected FormFile foto;
	
	private String apellidoResponsable;
	private String nombreResponsable;
	private String tipoDocumentoResponsable;
	private String numeroDocumentoResponsable;
	private String comentarioResponsable;
	protected FormFile fotoResponsable;
	private Boolean activo = new Boolean(false);
	private String email;
	
	private Set addedSet;
	private List availables;
	
	private long[] added;
	
	
	private Long idFoto;
	private String cuit;

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	
	public String getApellidoResponsable() {
		return apellidoResponsable;
	}
	public void setApellidoResponsable(String apellidoResponsable) {
		this.apellidoResponsable = apellidoResponsable;
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
	public Long getEntidadRegionalId() {
		return entidadRegionalId;
	}
	public void setEntidadRegionalId(Long entidadRegionalId) {
		this.entidadRegionalId = entidadRegionalId;
	}
	public String getNombreRegional() {
		return nombreRegional;
	}
	public void setNombreRegional(String nombreRegional) {
		this.nombreRegional = nombreRegional;
	}
	public FormFile getFoto() {
		return foto;
	}
	public void setFoto(FormFile foto) {
		this.foto = foto;
	}
	public FormFile getFotoResponsable() {
		return fotoResponsable;
	}
	public void setFotoResponsable(FormFile fotoResponsable) {
		this.fotoResponsable = fotoResponsable;
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
	public String getNumeroDocumentoResponsable() {
		return numeroDocumentoResponsable;
	}
	public void setNumeroDocumentoResponsable(String numeroDocumentoResponsable) {
		this.numeroDocumentoResponsable = numeroDocumentoResponsable;
	}
	public String getTipoDocumentoResponsable() {
		return tipoDocumentoResponsable;
	}
	public void setTipoDocumentoResponsable(String tipoDocumentoResponsable) {
		this.tipoDocumentoResponsable = tipoDocumentoResponsable;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set getAddedSet() {
		return addedSet;
	}
	public void setAddedSet(Set addedSet) {
		this.addedSet = addedSet;
	}
	public List getAvailables() {
		return availables;
	}
	public void setAvailables(List availables) {
		this.availables = availables;
	}
	public long[] getAdded() {
		return added;
	}
	public void setAdded(long[] added) {
		this.added = added;
	}
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.setAddedSet(null);
		this.setApellidoResponsable("");
		this.setAvailables(null);
		this.setComentario("");
		this.setComentarioResponsable("");
		this.setEntidadRegionalId(new Long(0));
		this.setNombreRegional("");
		this.setFoto(null);
		this.setFotoResponsable(null);
		this.setId(null);
		this.setNombre("");
		this.setNombreResponsable("");
		this.setNumeroDocumentoResponsable("");
		this.setTipoDocumentoResponsable("");
		this.setIdFoto(null);
		this.setCuit("");
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Long getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 
	
}
