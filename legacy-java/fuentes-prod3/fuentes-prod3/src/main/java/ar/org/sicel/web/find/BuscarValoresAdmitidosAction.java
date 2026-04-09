package ar.org.sicel.web.find;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;

/**
 * @struts.action 
 * 		path="/BuscarValoresAdmitidos"
 * 		scope="request"
 * 		validate="false"
 * 
 * @struts.action-forward 
 * 		name="listado" 
 * 		path=".valoresAdmitidosTable"
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
public final class BuscarValoresAdmitidosAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        
        if (isCancelled(request))
            return (mapping.findForward("main"));

        Long atributoId = new Long(request.getParameter("atributoId"));
        Atributo atributo = AtributoDAO.findByPrimaryKey(atributoId);
        Set set = atributo.getValoresAdmitidos();
        request.setAttribute("listaValoresAdmitidos", set);
		return (mapping.findForward("listado"));
	}

}
