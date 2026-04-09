/**
 * 
 */
package ar.org.sicel.web.find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.web.altaEdicion.forms.AtributoForm;

/**
 * @author Ramiro Trachsel
 *
 */
/**
 * @struts.action
 * 		path="/buscarAtributo"
 * 		name="atributoForm" 		
 * 		scope="request"
 * 		validate="true"
 *
 * @struts.action-forward
 * 		name="success"
 * 		path=".atributoFormPage"
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
 * 		name="detalle"
 * 		path=".atributoDetalle" 

 */
public class BuscarAtributoAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	AtributoForm atributoForm = (AtributoForm)form;
    	
        if (isCancelled(request))
            return (mapping.findForward("main"));

        Boolean esEdicion = atributoForm.getEsEdicion();      
        Long id = atributoForm.getId();
        if(esEdicion) {        	
        	Atributo atributo = AtributoDAO.findByPrimaryKey(id);
        	
        	if(atributo!=null) {
        		atributoForm.setAtributo(atributo);        		        		
        		request.setAttribute("valoresAdmitidos", atributo.getValoresAdmitidos());
        		return mapping.findForward("success");
        	} else return mapping.findForward("closePopup");       	
     
        } else {
        	Long conjuntoAtributosId = atributoForm.getConjuntoAtributosId();
        	if(conjuntoAtributosId!=-1)         		
        		return mapping.findForward("success");
        	
        	else {
        		Atributo atributo = AtributoDAO.findByPrimaryKey(id);
        		if(atributo!=null) {
    				atributoForm.setAtributo(atributo);            		
    				return mapping.findForward("detalle");
        		}
        	}
        }
        return mapping.findForward("main");        
	}       
}
