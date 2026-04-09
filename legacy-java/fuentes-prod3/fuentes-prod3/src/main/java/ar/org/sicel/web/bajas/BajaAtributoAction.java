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

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.ConjuntoAtributosForm;

/**
 * @author Ramiro Trachsel
 *
 */
/**
 * @struts.action
 * 		path="/bajaAtributo" 
 * 		name="conjuntoAtributosForm" 				
 * 		scope="request"
 *
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="failure"
 * 		path=".main"
 *
 */
public class BajaAtributoAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ConjuntoAtributosForm conjuntoAtributosForm = (ConjuntoAtributosForm) form;
		Long atributoId = conjuntoAtributosForm.getId();
		
		Atributo atributo = AtributoDAO.findByPrimaryKey(atributoId);		
		
		if (atributo == null){
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.valorAdmitidoNoExiste"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);
        }
		
		try {			
			 HibernateFactory.getSession().delete(atributo);
			 /*Long conjuntoAtributosId = conjuntoAtributosForm.getConjuntoAtributos().getId();
			 //TODO conjuntoAtributosId es null
			 response.sendRedirect("/sicel3/buscarConjuntoAtributos.do?id="+conjuntoAtributosId);*/
			 return mapping.findForward("main");			 
		} catch (Exception e) {
			return mapping.findForward(Tokens.FAILURE);			
		}
	}

	
}
