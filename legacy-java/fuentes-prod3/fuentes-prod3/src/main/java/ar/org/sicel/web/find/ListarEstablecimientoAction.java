/**
 * 
 */
package ar.org.sicel.web.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;


/**
 * @author jdivars
 *
 */
public class ListarEstablecimientoAction extends DispatchAction {
private static final Logger log; 
	
	static 
	{
		log = Logger.getLogger(ListarEstablecimientoAction.class);
	}	
	
	public ListarEstablecimientoAction() {
		
	}
	
	public ActionForward listar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception {
		
		try {
			List listEstablecimientos = EstablecimientoDAO.findAll();
			
			
			httpservletrequest.setAttribute("listaEstablecimientos", listEstablecimientos);
		} catch (HibernateException he){
			log.error("La lista de establecimientos no puede ser recuperada",he);
			ActionMessages actionMessages = new ActionMessages();
			actionMessages.add("messageGlobalError",new ActionMessage("messageGlobalError"));
			actionMessages.add("exceptionMessage",new ActionMessage("exceptionMessage","No se pudo recuperar la lista de establecimientos desde la base de datos. Verifique la conexión a la misma."));
			saveMessages(httpservletrequest,actionMessages);
			return actionmapping.findForward("error");
		}
		return actionmapping.findForward("list");
	}
}
