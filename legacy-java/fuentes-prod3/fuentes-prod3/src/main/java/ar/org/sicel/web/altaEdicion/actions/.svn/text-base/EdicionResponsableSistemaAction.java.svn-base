package ar.org.sicel.web.altaEdicion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Persona;
import ar.org.sicel.persistence.ResponsableSistema;
import ar.org.sicel.persistence.ResponsableSistemaDAO;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PersonaForm;


/** 

 * XDoclet definition:
 * @struts.action 
 * 		path="/edicionResponsableSistema" 
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



public class EdicionResponsableSistemaAction extends Action {
	static Logger log = Logger.getLogger(EdicionResponsableSistemaAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PersonaForm personaForm = (PersonaForm) form;
				
		
		Long id = personaForm.getId();
		
		if(isCancelled(request)) {
			if(id==null)
				return mapping.findForward(Tokens.MAIN);
			else return mapping.findForward("closePopup");
		}
		
		Boolean esEdicion = personaForm.getEsEdicion();		
		String nombreContacto = ((Persona)personaForm.getContacto()).getNombreContacto();		
		String nombrePersona = ((Persona)personaForm.getContacto()).getNombrePersona();
		String apellido = ((Persona)personaForm.getContacto()).getApellido();
		String tipoDocumento = ((Persona)personaForm.getContacto()).getTipoDocumento();
		Integer documento = ((Persona)personaForm.getContacto()).getDocumento();
		String comentario = ((Persona)personaForm.getContacto()).getComentario();
				
		
		if(esEdicion) {			
			ResponsableSistema responsableSistema = ResponsableSistemaDAO.findByPrimaryKey(id);
			if(responsableSistema!=null) {
				responsableSistema.setNombreContacto(nombreContacto);
				responsableSistema.setNombrePersona(nombrePersona);
				responsableSistema.setApellido(apellido);
				responsableSistema.setTipoDocumento(tipoDocumento);
				responsableSistema.setDocumento(documento);
				responsableSistema.setComentario(comentario);
				
				return mapping.findForward("refreshClosePopup");
			} else return mapping.findForward("closePopup");
			
			
		} 
		return mapping.findForward("main");
	}		
	

}
