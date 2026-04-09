package ar.org.sicel.web.altaEdicion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Contacto;

/**
 * Formulario para la entidad usuario
 * @author kjacobsen
 * @since 24-oct-2006
 */
public class UsuarioForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3486158185968744596L;
	private Long id;
	private boolean activo=false;
	private boolean regenerar;
	private String nombre;
	private String apellido;
	private String username;
	private String password;
	private String email;
	private Long rolId;
	private Long indiceCombo = null;
	//private String idContacto;
	
	// campos para la busqueda de contactos
	private String nombreContacto;
	//private String tipoContacto;
	
	private Contacto contacto; // contacto que selecciono;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Long getRolId() {
		return rolId;
	}
	
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	
	 /**
     * Blanqueo de valores
     * @param actionmapping - ActionMapping
     * @param httpservletrequest - HttpServletRequest
     */
    public void reset()
    {
       
        this.setActivo(false);
        this.setNombre("");
        this.setId(null);
        this.setApellido("");
        this.setUsername("");
        this.setEmail("");
        this.setPassword("");
        this.setRolId(null);
        this.setRegenerar(false);
    }

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/*public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}*/

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public boolean isRegenerar() {
		return regenerar;
	}

	public void setRegenerar(boolean regenerar) {
		this.regenerar = regenerar;
	}

	public Long getIndiceCombo() {
		return indiceCombo;
	}

	public void setIndiceCombo(Long indiceCombo) {
		this.indiceCombo = indiceCombo;
	}
	
}
