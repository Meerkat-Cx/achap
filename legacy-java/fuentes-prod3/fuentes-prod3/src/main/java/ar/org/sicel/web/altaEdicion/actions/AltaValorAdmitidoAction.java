/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AtributoForm;

/**
 * @author Ramiro Trachsel
 *
 */
/**
 * XDoclet definition:
 * @struts.action 
 * 		path="/altaValorAdmitido" 
 * 		name="valorAdmitidoForm" 		 
 * 		scope="request" 
 * 		validate="false"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 * 
 * @struts.action-forward
 * 		name="refreshPopup"
 * 		path=".refreshPopup"
 *
 *	@struts.action-forward
 * 		name="closePopup"
 * 		path=".closePopup" 
 */
 
public class AltaValorAdmitidoAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AtributoForm atributoForm = (AtributoForm) form;
		
		Long id = atributoForm.getId();
		
		if(isCancelled(request)) {
			if(id==null)
				return mapping.findForward(Tokens.MAIN);
			else return mapping.findForward("closePopup");
		}
		
		Atributo atributo = AtributoDAO.findByPrimaryKey(id);
		if(atributo!=null) {
			String valor = atributoForm.getValorAdmitido();
		//	ValorAdmAtr valorAdmAtr = ValorAdmAtrDAO.create(atributo, valor);			
			atributo.agregarValorPermitido(valor);
			
			response.sendRedirect("/sicel3/buscarAtributo.do?id="+id);
			return mapping.findForward("refreshPopup");
		} else return mapping.findForward("closePopup");
		
		
	}
}
