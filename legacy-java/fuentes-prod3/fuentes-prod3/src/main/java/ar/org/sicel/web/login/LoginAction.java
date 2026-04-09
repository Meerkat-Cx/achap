package ar.org.sicel.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorActionForm;

import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.UsuarioDAO;
import ar.org.sicel.upload.actions.UploadFileAction;
import ar.org.sicel.web.Tokens;

/**
 * @struts.action
 *     path="/login"
 *     name="loginForm"
 *     scope="request"
 *     validate="false"
 *
 * @struts.action-forward
 *     name="success"
 *     path=".main"
 *
 * @struts.action-forward
 *     name="failure"
 *     path=".enterLogin"
 *
 */
public class LoginAction extends DispatchAction {

	
    public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	DynaValidatorActionForm dvForm = (DynaValidatorActionForm)form;
    	Logger.getLogger(LoginAction.class).warn("Ingresando al sistema SICEL ....");
        if (isCancelled(request))
            return (mapping.findForward(Tokens.FAILURE));

        String username = (String)dvForm.get("userName");
        String password = (String)dvForm.get("password");
       
        ActionErrors actionErrors = new ActionErrors();

        if (request.getSession().getAttribute(Tokens.CURRENTUSER) != null)
                request.getSession().setAttribute(Tokens.CURRENTUSER,null);

        Usuario usuario = UsuarioDAO.findByUsername(username);

        if ((usuario != null)&&(password.equals(usuario.getClave()))) {
        	if(usuario.isActivo())
        		request.getSession().setAttribute(Tokens.CURRENTUSER,usuario);
        	else{
                request.getSession().setAttribute(Tokens.CURRENTUSER,null);
                actionErrors.add("loginInvalid", new ActionMessage("user_inactive"));
                saveMessages(request,actionErrors);
                return mapping.findForward(Tokens.FAILURE);
        	}
        } else {
            request.getSession().setAttribute(Tokens.CURRENTUSER,null);
            actionErrors.add("loginInvalid", new ActionMessage("authentication_failed"));
            saveMessages(request,actionErrors);
            return mapping.findForward(Tokens.FAILURE);
        }

        Logger.getLogger(LoginAction.class).warn("El usuario "+ username + " ingreso exitoso al sistema!");

		return mapping.findForward(Tokens.SUCCESS);

    }    
    
    /**
     * Default method to show login form
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Tokens.FAILURE);
    }
/*	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        log.info("Ingresando al sistema SICEL ....");*/

		// La siguiente linea solo agregada para que entre siempre.

        // Note la siguiente linea: en caso de que el usuario haya
        // presionado CANCEL, se volvera a la página de inicio,
        // y al no ingresar ningún tipo de usuario, en el menú
        // no se  presentaran las opciones de búsqueda.
      /*  if (isCancelled(request))
            return (mapping.findForward(Tokens.SUCCESS));

		HttpSession s = request.getSession();
        s.setAttribute(Tokens.USER_NAME, "SYSDBA");

        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,
            new ActionMessage(Tokens.IN_SYSTEM));
        saveMessages(request, messages);

        log.info("Ingreso exitoso al sistema!");

		return mapping.findForward(Tokens.SUCCESS);*/

/*
		String userName = (String) PropertyUtils.getSimpleProperty(form,
				Tokens.USER_NAME);
		String password = (String) PropertyUtils.getSimpleProperty(form,
				Tokens.PASSWORD);
		HttpSession s = request.getSession();
		Connection con = null;
		org.firebirdsql.pool.FBWrappingDataSource ds = null;
		try {
			ds = new org.firebirdsql.pool.FBWrappingDataSource();
			ds.setDatabase("localhost/3050:sicel3");
			ds.setType("TYPE4");
			ds.setEncoding("NONE");
			ds.setLoginTimeout(20);
			con = ds.getConnection(userName, password);
			//con = ds.getConnection("SYSDBA", "masterkey");
			s.setAttribute("dBCon", con);
			s.setAttribute("dataSourceS3", ds);
			s.setAttribute("userName","SYSDBA");
		} catch (Exception e) {
			ActionMessages errors = new ActionMessages();
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage(Tokens.AUTHENTICATION_FAILED));
            saveErrors(request, errors);

			return mapping.findForward(Tokens.FAILURE);
		}
		return mapping.findForward(Tokens.SUCCESS);
*/

//	}

}

