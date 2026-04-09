/**
 * 
 */
package ar.org.sicel.web.find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ar.org.sicel.persistence.ResponsableEclo;
import ar.org.sicel.persistence.ResponsableEcloDAO;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PersonaForm;

/**
 * @author Ramiro Trachsel
 *
 */
/**
 * @struts.action
 * 		path="/buscarResponsableEclo"
 * 		name="personaForm" 		
 * 		scope="request"
 * 		validate="true"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="failure"
 * 		path=".main"
 * 
 * @struts.action-forward
 * 		name="edicion"
 * 		path=".edicionResponsableEclo" 
 * 
 * @struts.action-forward
 * 		name="closePopup"
 * 		path=".closePopup"
 * 
 * @struts.action-forward
 * 		name="detalle"
 * 		path=".personaDetalle"
 * 
 *   
*/





public class BuscarResponsableEcloAction extends Action {
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
       
        if (isCancelled(request))
            return (mapping.findForward("main"));

        PersonaForm personaForm = (PersonaForm) form;
        
        Long id = personaForm.getId();       
        Boolean esEdicion = personaForm.getEsEdicion();
        
        //if-else para disernir entre una busqueda para edicion o busqueda para visualizacion 
        //de datos, respectivamente
        ResponsableEclo responsableEclo = ResponsableEcloDAO.findByPrimaryKey(id);
        if(esEdicion) {
        	if(responsableEclo!=null) { 
        		personaForm.setContacto(responsableEclo);
        		personaForm.setHeaderText("Ficha de Responsable de ECLO");        		
        		
        		return mapping.findForward("edicion");
        	}        	
        	else {
        		return mapping.findForward("closePopup");
        	}      	
        } else if(responsableEclo!=null) {  
        	personaForm.setContacto(responsableEclo);
        	personaForm.setHeaderText("Ficha de Responsable de Eclo");    		
    		
        	return mapping.findForward("detalle");
        	
        } else {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Mensaje sin asignar"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);
        }        
	}
	
	

}
