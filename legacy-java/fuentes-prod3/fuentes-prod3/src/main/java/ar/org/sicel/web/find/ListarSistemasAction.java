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
import ar.org.sicel.persistence.SistemaDAO;


public class ListarSistemasAction extends DispatchAction {
	
	private static final Logger log; 
	
    static 
    {
        log = Logger.getLogger(ListarUsuarioAction.class);
    }	



	public ListarSistemasAction() {
	}

    public ActionForward listar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
    	try {
    		List listSistemas = SistemaDAO.findAll();
        	httpservletrequest.setAttribute("listaSistemas", listSistemas);
    	} catch (HibernateException he){
    		log.error("La lista de sistemas no puede ser recuperada",he);
            ActionMessages actionMessages = new ActionMessages();
            actionMessages.add("messageGlobalError",new ActionMessage("messageGlobalError"));
            actionMessages.add("exceptionMessage",new ActionMessage("exceptionMessage","No se pudo recuperar la lista de usuarios desde la base de datos. Verifique la conexión a la misma."));
            saveMessages(httpservletrequest,actionMessages);
    		return actionmapping.findForward("error");
    	}
    	return actionmapping.findForward("list");
    }

}
