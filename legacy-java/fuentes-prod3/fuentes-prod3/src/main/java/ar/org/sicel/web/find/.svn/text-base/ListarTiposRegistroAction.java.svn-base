package ar.org.sicel.web.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;
import ar.org.sicel.persistence.RegistroDAO;
import ar.org.sicel.persistence.TipoRegistro;
import ar.org.sicel.persistence.TipoRegistroDAO;
import ar.org.sicel.persistence.util.HibernateFactory;



public class ListarTiposRegistroAction extends DispatchAction {

	public ActionForward init(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
    	try {
    		
    		List tipos = TipoRegistroDAO.findTiposValidosEditables();
        	httpservletrequest.setAttribute("tipos", tipos);
    	} catch (HibernateException he){
    		log.error("La lista de tipos de registros no puede ser recuperada",he);
            ActionMessages actionMessages = new ActionMessages();
            actionMessages.add("messageGlobalError",new ActionMessage("messageGlobalError"));
            actionMessages.add("exceptionMessage",new ActionMessage("exceptionMessage","No se pudo recuperar la lista de tipos de registro desde la base de datos. Verifique la conexión a la misma."));
            saveMessages(httpservletrequest,actionMessages);
    		return actionmapping.findForward("error");
    	}
    	return actionmapping.findForward("list");
    }
	public ActionForward initEdit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
		TiposRegistroForm tiposForm = (TiposRegistroForm)actionform;
    	TipoRegistro tr = TipoRegistroDAO.findByPrimaryKey(tiposForm.getId());
    	List registros = RegistroDAO.findByTipo(tiposForm.getId());
    	if(registros.isEmpty())
    		tiposForm.setTiene(0);
    	else
    		tiposForm.setTiene(1);
	  	tiposForm.setId(tr.getId());
    	tiposForm.setDescrip(tr.getDescripcion());
    	httpservletrequest.getSession().setAttribute("action", "update");
        return actionmapping.findForward("success");
    }
	public ActionForward initAdd(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
		TiposRegistroForm tiposForm = (TiposRegistroForm)actionform;
    	tiposForm.setId("");
    	tiposForm.setDescrip("");
    	httpservletrequest.getSession().setAttribute("action", "add");
        return actionmapping.findForward("success");
    }
	public ActionForward add(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
		TiposRegistroForm tiposForm = (TiposRegistroForm)actionform;
		if (isCancelled(httpservletrequest)){
			tiposForm.setId("");
	    	tiposForm.setDescrip("");
	    	List tipos = TipoRegistroDAO.findTiposValidosEditables();
	    	httpservletrequest.setAttribute("tipos", tipos);
	    	return actionmapping.findForward("list");
       }
		String idReg = tiposForm.getId().toUpperCase();
		TipoRegistro tr = TipoRegistroDAO.findByPrimaryKey(idReg);
		if(tr!=null){
			ActionMessages actionMessages = new ActionMessages();
			actionMessages.add("uniqueConstraint",new ActionMessage("tipoRegistro.existe", tiposForm.getId()));
        	//this.saveErrors(httpservletrequest, actionerrors);
        	saveMessages(httpservletrequest, actionMessages);
        	tiposForm.setId("");
        	tiposForm.setDescrip("");
        	httpservletrequest.getSession().setAttribute("action", "add");
            return actionmapping.findForward("success");
		}
		TipoRegistro tipo = TipoRegistroDAO.create(idReg,tiposForm.getDescrip());
		HibernateFactory.getSession().save(tipo);
		tiposForm.setId("");
    	tiposForm.setDescrip("");
    	List tipos = TipoRegistroDAO.findTiposValidosEditables();
    	httpservletrequest.setAttribute("tipos", tipos);
    	
    	return actionmapping.findForward("list");
    }
	public ActionForward update(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
		TiposRegistroForm tiposForm = (TiposRegistroForm)actionform;
		if (isCancelled(httpservletrequest)){
			tiposForm.setId("");
	    	tiposForm.setDescrip("");
	    	List tipos = TipoRegistroDAO.findTiposValidosEditables();
	    	httpservletrequest.setAttribute("tipos", tipos);
	    	return actionmapping.findForward("list");
       }
		TipoRegistro tr = TipoRegistroDAO.findByPrimaryKey(tiposForm.getId());
		tr.setDescripcion(tiposForm.getDescrip());
		HibernateFactory.getSession().update(tr);
		tiposForm.setId("");
    	tiposForm.setDescrip("");
    	List tipos = TipoRegistroDAO.findTiposValidosEditables();
    	httpservletrequest.setAttribute("tipos", tipos);
    	return actionmapping.findForward("list");
		
    }
	public ActionForward baja(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
		TiposRegistroForm tiposForm = (TiposRegistroForm)actionform;
		
		TipoRegistro tr = TipoRegistroDAO.findByPrimaryKey(tiposForm.getId());
		
		HibernateFactory.getSession().delete(tr);
		tiposForm.setId("");
    	tiposForm.setDescrip("");
    	List tipos = TipoRegistroDAO.findTiposValidosEditables();
    	httpservletrequest.setAttribute("tipos", tipos);
    	return actionmapping.findForward("list");
		
    }
}
