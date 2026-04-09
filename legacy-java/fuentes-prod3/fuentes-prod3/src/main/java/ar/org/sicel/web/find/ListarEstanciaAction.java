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

import ar.org.sicel.persistence.EstanciaDAO;
import ar.org.sicel.web.altaEdicion.forms.EstanciaForm;


/**
 * 
 * @author Orona
 *
 */
public class ListarEstanciaAction extends DispatchAction {
	
	private static final Logger log; 
	
	static 
	{
		log = Logger.getLogger(ListarEstanciaAction.class);
	}	
	
	public ListarEstanciaAction() {}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("inicio de la busqueda de Establecimientos");
		EstanciaForm f = (EstanciaForm) form;
		f.reset(mapping,request);
		return mapping.findForward("init");
	}
	
	public ActionForward listar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception
	{
		log.debug("listando las estancias");
		try {
			List listEstancias = EstanciaDAO.findAll();
			httpservletrequest.setAttribute("listaEstancias", listEstancias);
		} catch (HibernateException he){
			log.error("La lista de estancias no puede ser recuperada",he);
			ActionMessages actionMessages = new ActionMessages();
			actionMessages.add("messageGlobalError",new ActionMessage("messageGlobalError"));
			actionMessages.add("exceptionMessage",new ActionMessage("exceptionMessage","No se pudo recuperar la lista de estancias desde la base de datos. Verifique la conexión a la misma."));
			saveMessages(httpservletrequest,actionMessages);
			return actionmapping.findForward("error");
		}
		return actionmapping.findForward("init");
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		EstanciaForm f = (EstanciaForm) form;
		List listaEstancias = EstanciaDAO.findPorFiltroEstancia(f.getId(),f.getNombre(), f.getNombrePropietario());
		request.setAttribute("listaEstancias", listaEstancias);
		System.out.println("buscando establecimiento");
		return mapping.findForward("init");
	}

}
