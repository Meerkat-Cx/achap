/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.FotoDAO;
import ar.org.sicel.persistence.Persona;
import ar.org.sicel.persistence.ResponsableEclo;
import ar.org.sicel.persistence.ResponsableEcloDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PersonaForm;

/**
 * @author Ramiro Trachsel
 *
 */
/** 

 * XDoclet definition:
 * @struts.action 
 * 		path="/edicionResponsableEclo" 
 * 		name="personaForm" 		 
 * 		input=".altaPersonaPage" 
 * 		scope="request" 
 * 		validate="false"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 * 
 * @struts.action-forward
 * 		name="closePopup"
 * 		path=".closePopup"
 * 
 * @struts.action-forward
 *       name="refreshClosePopup"
 *		path=".refreshParentAndClosePopup"
 * 
 */

public class EdicionResponsableEcloAction extends Action {

static Logger log = Logger.getLogger(EdicionResponsableEcloAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PersonaForm personaForm = (PersonaForm) form;				
		Long id = personaForm.getId();
		
		if(isCancelled(request)) {
			if(id==null)
				return mapping.findForward(Tokens.MAIN);
			else return mapping.findForward("closePopup");
		}
		
		Boolean esEdicion = personaForm.getEsEdicion();				
		String nombrePersona = ((Persona)personaForm.getContacto()).getNombrePersona();
		String apellido = ((Persona)personaForm.getContacto()).getApellido();
		String tipoDocumento = ((Persona)personaForm.getContacto()).getTipoDocumento();
		Integer documento = ((Persona)personaForm.getContacto()).getDocumento();
		String comentario = ((Persona)personaForm.getContacto()).getComentario();
		InputStream foto = personaForm.getFoto().getInputStream();
				
		
		if(esEdicion) {			
			ResponsableEclo responsableEclo = ResponsableEcloDAO.findByPrimaryKey(id);
			if(responsableEclo!=null) {
				responsableEclo.setNombreContacto(apellido);
				responsableEclo.setNombrePersona(nombrePersona);
				responsableEclo.setApellido(apellido);
				responsableEclo.setTipoDocumento(tipoDocumento);
				responsableEclo.setDocumento(documento);
				responsableEclo.setComentario(comentario);
				
				if(personaForm.getFoto().getFileSize()!=0) 
					if(responsableEclo.getFoto()==null)
						responsableEclo.setFoto(FotoDAO.createPersistent(IOUtils.toByteArray(foto)));
					else {
						HibernateFactory.getSession().delete(responsableEclo.getFoto());
						responsableEclo.setFoto(FotoDAO.createPersistent(IOUtils.toByteArray(foto)));
					}
				
				return mapping.findForward("refreshClosePopup");
			} else return mapping.findForward("closePopup");
			
			
		} 
		return mapping.findForward("main");
	}		
	
}
