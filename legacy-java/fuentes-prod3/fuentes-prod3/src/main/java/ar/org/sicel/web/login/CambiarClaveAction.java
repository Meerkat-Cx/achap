package ar.org.sicel.web.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.UsuarioDAO;
import ar.org.sicel.upload.dao.DAOs;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PropietarioForm;

/**
 * Actio para el posible cambio de clave de un usuario
 * @author Orona
 *
 */
public class CambiarClaveAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		CambiarClaveForm eForm = (CambiarClaveForm) form;
		Usuario usuario = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		Usuario user = UsuarioDAO.findByPrimaryKey(usuario.getId());
		eForm.setEmailActual(user.getEmail());
		return (mapping.findForward("init"));
	}
	/**
	 * Metodo para cuando se desea cambiar la clave, valida: que el password corresponda al user logeado
	 * que las 2 claves nuevas sean iguales, hay que tener en cuenta que los 3 campos vienen encriptados 
	 * desde el cliente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 public ActionForward cambiarClave(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		 ActionErrors actionErrors = new ActionErrors();
		 
		 CambiarClaveForm cForm = (CambiarClaveForm) form;		 
		 String password = cForm.getPasswdOld();
		 
		 Usuario usuario = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		// usuario.setEmail(cForm.getEmail()); 
		 
		 
		 if (usuario!=null){			 
			 if (cForm.getEmail().equals(cForm.getEmailConfirmacion())) 
				if (!cForm.getEmail().equals("")){ 
					usuario.setEmail(cForm.getEmail());
					UsuarioDAO.updateUser(usuario);					
				}
			 if (password.equals(usuario.getClave())) {			 
				 Logger.getLogger(CambiarClaveAction.class).info("Puede cambiar la clave");
				 String password1 = cForm.getPasswdNew1();
				 String password2 = cForm.getPasswdNew2();
				 if (password1.equals(password2)) {
					 usuario.setClave(password1);
					 UsuarioDAO.updateUser(usuario);
					 Logger.getLogger(CambiarClaveAction.class).info("Modificando la clave");
					 cForm.reset(mapping,request);
					 return mapping.findForward(Tokens.SUCCESS);
				 }
				 else 
					 //A OR (~A AND B ) 
					 if (!password1.equals("") || (password1.equals("") && !password2.equals(""))){			
					 /*( (!password1.equals("") && !password2.equals("")) 
							 || (!password1.equals("") && password2.equals(""))
							 	|| (password1.equals("") && !password2.equals("")) )*/ 
						 Logger.getLogger(CambiarClaveAction.class).info("las claves nuevas no son identicas");
						 actionErrors.add("loginInvalid", new ActionMessage("claves_nuevas_no_iguales"));
						 saveMessages(request,actionErrors);
						 cForm.reset(mapping,request);
				         return mapping.findForward(Tokens.FAILURE);				 
					 }
			}
			else
				if (!password.equals("")){ 
					Logger.getLogger(CambiarClaveAction.class).info("La clave actual es distinta a la que esta en el sistema");
					actionErrors.add("loginInvalid", new ActionMessage("clave_actual_distinta"));
					saveMessages(request,actionErrors);
					cForm.reset(mapping,request);
			        return mapping.findForward(Tokens.FAILURE);
				} 
	 	 }
		 else {
			 Logger.getLogger(CambiarClaveAction.class).info("No tiene permitido cambiar la clave");
			 actionErrors.add("loginInvalid", new ActionMessage("usuario_no_logeado"));
			 saveMessages(request,actionErrors);
			 cForm.reset(mapping,request);
	         return mapping.findForward(Tokens.FAILURE);
		 }		
		 cForm.reset(mapping,request);
		 return mapping.findForward("successMail");
	 }

}
