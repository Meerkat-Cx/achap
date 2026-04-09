/**
 * 
 */
package ar.org.sicel.web.bajas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ar.org.sicel.persistence.ValorAdmAtr;
import ar.org.sicel.persistence.ValorAdmAtrDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AtributoForm;

/**
 * @author Ramiro Trachsel
 *
 */
/**
 * @struts.action
 * 		path="/bajaValorAdmitido" 
 * 		name="atributoForm" 				
 * 		scope="request"
 *
 * @struts.action-forward
 * 		name="refreshPopup"
 * 		path=".refreshPopup"
 *
 * @struts.action-forward
 * 		name="failure"
 * 		path=".main"
 *
 */
public class BajaValorAdmitidoAction extends Action {
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AtributoForm atributoForm = (AtributoForm) form;
		Long valorAdmitidoId = atributoForm.getValorAdmitidoId();
		Long id = atributoForm.getId();
		
		ValorAdmAtr valorAdmitido = ValorAdmAtrDAO.findByPrimaryKey(valorAdmitidoId);		
		
		if (valorAdmitido == null){
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.valorAdmitidoNoExiste"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);
        }
		
		try {			
			 HibernateFactory.getSession().delete(valorAdmitido);
			 response.sendRedirect("/sicel3/buscarAtributo.do?id="+id);
			 return mapping.findForward("refreshPopup");			 
		} catch (Exception e) {
			return mapping.findForward(Tokens.FAILURE);			
		}
	}
}
