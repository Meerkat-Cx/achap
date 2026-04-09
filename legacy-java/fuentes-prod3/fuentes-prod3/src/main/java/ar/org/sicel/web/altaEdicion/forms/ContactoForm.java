/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

import ar.org.sicel.persistence.Contacto;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.Ubicacion;
import ar.org.sicel.persistence.UbicacionDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * @author Ramiro Trachsel
 *
 */
/**
*
* struts.form
*      name="contactoForm"
*
*/
public abstract class ContactoForm extends ValidatorActionForm {
	
	protected Ubicacion ubicacion = UbicacionDAO.create(null, null, null, null, null, null, null, null);
	protected Contacto contacto;
	protected Boolean esEdicion = new Boolean(false);
	protected Boolean validar = new Boolean(false);
	protected Long id = new Long(0);
	protected FormFile foto;	
		
	/*
	 * Para formulario de busquedas
	 */
	private Boolean idActivo = new Boolean(false);
	private Boolean nombreContactoActivo = new Boolean(false);
	private Boolean comentarioActivo = new Boolean(false);	
	
	public ContactoForm() {	
	}
	
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
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

	public Boolean getComentarioActivo() {
		return comentarioActivo;
	}

	public void setComentarioActivo(Boolean comentarioActivo) {
		this.comentarioActivo = comentarioActivo;
	}

	public Boolean getIdActivo() {
		return idActivo;
	}

	public void setIdActivo(Boolean idActivo) {
		this.idActivo = idActivo;
	}

	public Boolean getNombreContactoActivo() {
		return nombreContactoActivo;
	}

	public void setNombreContactoActivo(Boolean nombreContactoActivo) {
		this.nombreContactoActivo = nombreContactoActivo;
	}

	public FormFile getFoto() {
		return foto;   
	}


	public void setFoto(FormFile foto) {
		this.foto = foto;
	}
	
	public Boolean getValidar() {
		return validar;
	}


	public void setValidar(Boolean validar) {
		this.validar = validar;
	}


	protected abstract int getAlturaMax();
	   
	protected abstract int getAnchoMax();
	
	//-------------//
	
	
	
	
	protected boolean dimensionesCorrectas(FormFile foto) throws Exception{		
		   Image image = null;
		   
		   try {
			   image = ImageIO.read(foto.getInputStream()); 
		   } catch (IOException e) {			   
			   throw new ExcepcionIntegridad(MENSAJES.FOTO_NO_LEIDA,new String[]{});			   
		   }
		   	   		
		   int height = image.getHeight(null);
		   int width = image.getWidth(null);
		   if ((height > getAlturaMax())|| (width > getAnchoMax()))
			   return false; 
		   
		   //throw new ExcepcionIntegridad(MENSAJES.TAMANIO_FOTO_INCORRECTO,new String[]{String.valueOf(width),String.valueOf(height),String.valueOf(getAnchoMax()),String.valueOf(getAlturaMax())});
		   return true;		      	
	   }
	
	
	
}
