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
import ar.org.sicel.persistence.EntidadRegionalDAO;

/**
 * 
 * @author Orona
 *
 */
public class ListarEntidadRegionalAction extends DispatchAction { 
	
	private static final Logger log; 
	
	static 
	{
		log = Logger.getLogger(ListarEntidadRegionalAction.class);
	}	
	
	public ListarEntidadRegionalAction() {
		
	}
	
	/**
	 * metodo para cuando desde el menu se listan las entidades regionales
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward listar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception
	{
		log.debug("listando las entidades regionales");
		try {
			List listEntidadesRegionales = EntidadRegionalDAO.findAll();
			httpservletrequest.setAttribute("listaEntidadesRegionales", listEntidadesRegionales);
		} catch (HibernateException he){
			log.error("La lista de estancias no puede ser recuperada",he);
			ActionMessages actionMessages = new ActionMessages();
			actionMessages.add("messageGlobalError",new ActionMessage("messageGlobalError"));
			actionMessages.add("exceptionMessage",new ActionMessage("exceptionMessage","No se pudo recuperar la lista de estancias desde la base de datos. Verifique la conexión a la misma."));
			saveMessages(httpservletrequest,actionMessages);
			return actionmapping.findForward("error");
		}
		return actionmapping.findForward("list");
	}
	
	
}
