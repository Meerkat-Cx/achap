package ar.org.sicel.web.find;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Rol;
import ar.org.sicel.persistence.RolDAO;

import ar.org.sicel.persistence.UsuarioDAO;
import ar.org.sicel.web.altaEdicion.forms.UsuarioForm;

public class ListarUsuarioAction extends DispatchAction {

	private static final Logger log; 
	
    static 
    {
        log = Logger.getLogger(ListarUsuarioAction.class);
    }	

    public ListarUsuarioAction(){
	}

	/**
	 * Action que lista los usuarios
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return ActionForward
	 * @throws Exception
	 */
    public ActionForward listar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
    		UsuarioForm form = (UsuarioForm) actionform;
    		form.setNombre("");
    		form.setApellido("");
    		httpservletrequest.setAttribute("listaUsuarios", null);
        	List availables = RolDAO.findAll();		
    		httpservletrequest.getSession().setAttribute("roles",availables);
    		httpservletrequest.getSession().setAttribute("method","listar");
    	return actionmapping.findForward("list");
    }
    

    
    
    public ActionForward buscar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
    	UsuarioForm form = (UsuarioForm) actionform;
    	List listUsers = new ArrayList();
    	String nombre = "";
    	if(httpservletrequest.getParameter("nombre")!= null && !httpservletrequest.getParameter("nombre").equalsIgnoreCase(""))
    		nombre =httpservletrequest.getParameter("nombre").trim();
    	String apellido = "";
    	if(httpservletrequest.getParameter("apellido")!= null && !httpservletrequest.getParameter("apellido").equalsIgnoreCase(""))
    		apellido =httpservletrequest.getParameter("apellido").trim();
    	Rol rol = null;
    	String indexs =httpservletrequest.getParameter("index"); 
    	if(indexs != null && !indexs.equalsIgnoreCase("0"))
            rol = RolDAO.findByPrimaryKey(new Long(indexs));
    	listUsers = UsuarioDAO.findByFiltros(nombre, apellido, rol);
    	httpservletrequest.setAttribute("listaUsuarios", listUsers);
        List availables = RolDAO.findAll();		
    	httpservletrequest.getSession().setAttribute("roles",availables);
    	httpservletrequest.getSession().setAttribute("method","buscar");
    	return actionmapping.findForward("list");
    }
}
