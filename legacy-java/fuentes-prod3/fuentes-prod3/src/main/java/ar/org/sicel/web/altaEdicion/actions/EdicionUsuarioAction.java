package ar.org.sicel.web.altaEdicion.actions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.Contacto;
import ar.org.sicel.persistence.ContactoDAO;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.Rol;
import ar.org.sicel.persistence.RolDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.UsuarioDAO;
import ar.org.sicel.security.AuthenticacionManager;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.UsuarioForm;

/**
 * Acción que realiza el alta y modificación de usuarios
 * @author kjacobsen
 * @since 24-oct-2006
 */
public class EdicionUsuarioAction extends DispatchAction {

	
	public EdicionUsuarioAction(){
	}
	
    /**
     * Prepara los valores para cargar la pantalla de alta para
     * usuarios
     * @param actionmapping
     * @param actionform
     * @param httpservletrequest
     * @param httpservletresponse
     * @return ActionForward
     * @throws Exception
     */    
    public ActionForward initAdd(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws Exception
    {
    	List availables = RolDAO.findAll();		
		httpservletrequest.getSession().setAttribute("roles",availables);
        httpservletrequest.getSession().setAttribute("action", "add");
        
        limpiarForm((UsuarioForm)actionform);
        return actionmapping.findForward("success");
    }
    
    private void limpiarForm(UsuarioForm form) {
    	form.setActivo(false);
    	form.setApellido("");
    	form.setEmail("");
    	form.setId(null);
    	form.setContacto(null);
    	form.setNombre("");
    	form.setNombreContacto("");
    	form.setPassword("");
    	form.setRolId(null);
    	//form.setTipoContacto("");
    	form.setUsername("");
	}

	/**
     * Crea una entidad con los datos obtenidos del formulario para enviar a la
     * capa de persistencia e impactar en la base de datos
     * @param actionmapping
     * @param actionform
     * @param httpservletrequest
     * @param httpservletresponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward add(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	if (isCancelled(httpservletrequest))
            return (actionmapping.findForward(Tokens.FAILURE));
    	
    	UsuarioForm userForm = (UsuarioForm)actionform;
    	
    	ActionMessages actionerrors = new ActionMessages();
    	
        Usuario user = new Usuario();
        String passwordGenerado = copiarPropiedades(userForm, user);
        if (UsuarioDAO.findByUsername(userForm.getUsername()) != null) {        	
        	actionerrors.add("uniqueConstraint",new ActionMessage("entidad.existe", "Usuario", userForm.getUsername()));        
        	saveMessages(httpservletrequest, actionerrors);
            httpservletrequest.getSession().setAttribute("action", "add");            
            return actionmapping.findForward("success");
        }
        else {
        	
        	UsuarioDAO.createUser(user);
        	try { 
        		 Usuario usuarioActual = (Usuario) httpservletrequest.getSession().getAttribute(Tokens.CURRENTUSER);
        		UsuarioDAO.sendSignUpMail(user, passwordGenerado);
        		UsuarioDAO.sendSignUpMailCopia(usuarioActual, passwordGenerado,user);
        	}catch (EmailException e){
            	actionerrors.add("errorEmail",new ActionMessage("email.noenviado", user.getUsername(),passwordGenerado));        
            	saveMessages(httpservletrequest, actionerrors);
                httpservletrequest.getSession().setAttribute("action", "add");            
                return actionmapping.findForward("errorMail");
        	}
        }
      
        httpservletrequest.getSession().removeAttribute("usuarioForm");
        return actionmapping.findForward("list");
    }
    
    /**
     * Prepara los valores para cargar la pantalla de edición para un usuario
     * @param actionmapping
     * @param actionForm
     * @param httpservletrequest
     * @param httpservletresponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward initmod(ActionMapping actionmapping, ActionForm actionForm, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {

    	UsuarioForm userForm = (UsuarioForm)actionForm;
    	Usuario user = UsuarioDAO.findByPrimaryKey(userForm.getId());
    	List availables = RolDAO.findAll();
    	limpiarForm((UsuarioForm)actionForm);
		copyProperties(user,userForm);

		httpservletrequest.getSession().setAttribute("roles",availables);
        httpservletrequest.getSession().setAttribute("action", "update");
        return actionmapping.findForward("success");
    }

    /**
     * Modifica la entidad obteniendo los datos desde el formulario
     * @param actionmapping
     * @param actionform
     * @param httpservletrequest
     * @param httpservletresponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward update(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	if (isCancelled(httpservletrequest))
            return (actionmapping.findForward(Tokens.FAILURE));
        UsuarioForm userForm = (UsuarioForm)actionform;
        
        Usuario user = UsuarioDAO.findByPrimaryKey(userForm.getId());
        copiarPropiedades(userForm, user);
        String passwordGenerado="";
		if(userForm.isRegenerar()){
			passwordGenerado = user.generatePassword();
    		String aux = AuthenticacionManager.encriptar(passwordGenerado); 
    		user.setClave(aux);	
			    	}
        UsuarioDAO.updateUser(user);
        if(userForm.isRegenerar()){
        	ActionMessages actionerrors = new ActionMessages();
	        try { 
		   		 Usuario usuarioActual = (Usuario) httpservletrequest.getSession().getAttribute(Tokens.CURRENTUSER);
		   		UsuarioDAO.sendSignUpMail(user, passwordGenerado);
		   		UsuarioDAO.sendSignUpMailCopia(usuarioActual, passwordGenerado,user);
			 }catch (EmailException e){
			       	actionerrors.add("errorEmail",new ActionMessage("email.noenviado", user.getUsername(),passwordGenerado));        
			       	saveMessages(httpservletrequest, actionerrors);
			           httpservletrequest.getSession().setAttribute("action", "add");            
			           return actionmapping.findForward("errorMail");
			 }
        }
        httpservletrequest.getSession().removeAttribute("usuarioForm");
        return actionmapping.findForward("list");
    }
    
    public ActionForward regenerarClave(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception
    {
    	ActionMessages actionerrors = new ActionMessages();
	    UsuarioForm userForm = (UsuarioForm)actionform;
	    Usuario user = UsuarioDAO.findByPrimaryKey(userForm.getId());
	    String passwordGenerado = copiarPropiedades(userForm, user);
	    UsuarioDAO.updateUser(user);
        	try { 
        		 Usuario usuarioActual = (Usuario) httpservletrequest.getSession().getAttribute(Tokens.CURRENTUSER);
        		UsuarioDAO.sendSignUpMail(user, passwordGenerado);
        		UsuarioDAO.sendSignUpMailCopia(usuarioActual, passwordGenerado,user);
        	}catch (EmailException e){
            	actionerrors.add("errorEmail",new ActionMessage("email.noenviado", user.getUsername(),passwordGenerado));        
            	saveMessages(httpservletrequest, actionerrors);
                httpservletrequest.getSession().setAttribute("action", "add");            
                return actionmapping.findForward("errorMail");
        	}
        httpservletrequest.getSession().removeAttribute("usuarioForm");
	    return actionmapping.findForward("list");
}

    /**
     * Copio las propiedades del form a la entidad Usuario
     * Hay que tener en cuenta que se encripta el password ingresado por el usuario
     * @param userForm - UserForm
     * @param user - User
     * @return Se devuelve el password generado sin encriptar, null en el caso
     * que no se haya generado el password
     */
    private String copiarPropiedades(UsuarioForm userForm, Usuario user) {	
    	user.setActivo(userForm.isActivo());
    	user.setApellido(userForm.getApellido());
    	user.setNombre(userForm.getNombre());
    	String passwordGenerado = null;
    	if(user.getId()==null) { // es un Alta
    		/*String aux = user.generatePassword(); // genero
    		passwordGenerado = new String(aux); // clono asi no lo pierdo
    		user.setClave(AuthenticacionManager.encriptar(aux)); //TODO ENVIARLA AL USUARIO POR MAIL!!!!!*/
    		
    		passwordGenerado = user.generatePassword();
    		String aux = AuthenticacionManager.encriptar(passwordGenerado); 
    		user.setClave(aux);
    	}
    	user.setEmail(userForm.getEmail());
    	if(user.getId()==null)
    		user.setUsername(userForm.getUsername());
    	Rol rol = RolDAO.findByPrimaryKey(userForm.getRolId());
    	user.setRol(rol);
    	if (Tokens.NOMBREROLADMINISTRADOR.equalsIgnoreCase(rol.getNombre()) ||
    		Tokens.NOMBREROLCASANOVA.equalsIgnoreCase(rol.getNombre()) ||
    		Tokens.NOMBREROLGENERAL.equalsIgnoreCase(rol.getNombre()))
    		user.setContacto(null);
    	else
    		user.setContacto(userForm.getContacto());
    	return passwordGenerado;
    }
    
    /**
     * Copio las propiedades de la entidad al form
     * @param userForm - UsuarioForm
     * @param user - Usuario
     */
    private void copyProperties(Usuario user, UsuarioForm userForm) {
    	userForm.setId(user.getId());
    	userForm.setActivo(user.isActivo());
  		userForm.setEmail(user.getEmail());
   		userForm.setApellido(user.getApellido());
    	userForm.setNombre(user.getNombre());
    	userForm.setRolId(user.getRol().getId());
    	userForm.setUsername(user.getUsername());
    	userForm.setContacto(user.getContacto());
    }
    
    public ActionForward initSeleccionarContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception    {
    	System.out.println("iniciando seleccion de un contacto");
    	UsuarioForm uForm = (UsuarioForm) form;
    	boolean d = uForm.isActivo();
    	uForm.setActivo(d);
    	Rol rolSeleccionado = RolDAO.findByPrimaryKey(uForm.getRolId());
    	request.setAttribute("rolNombre", rolSeleccionado.getNombre());
    	return mapping.findForward("buscarContactos");
    }
    
    public ActionForward buscarContactos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception    {    	
    	UsuarioForm uForm = (UsuarioForm) form;
    	List listContactos = null;
    	Rol rolSeleccionado = RolDAO.findByPrimaryKey(uForm.getRolId());
    	
    	
    	if ("Propietario".equalsIgnoreCase(rolSeleccionado.getNombre())) {    		
    		listContactos = PropietarioDAO.findByNombreContacto(uForm.getNombreContacto());    		
    	}
    	/*if ("Establecimiento".equals(uForm.getTipoContacto())) {
    		listContactos = EstablecimientoDAO.findByNombreContacto(uForm.getNombreContacto());
    	}*/
    	if ("Proveedor".equalsIgnoreCase(rolSeleccionado.getNombre())) {
    		listContactos = EcloDAO.findByNombreContacto(uForm.getNombreContacto());
    	}
    	if ("Regional".equalsIgnoreCase(rolSeleccionado.getNombre())) {
    		listContactos = EntidadRegionalDAO.findByNombreContacto(uForm.getNombreContacto());
    	}
    	request.setAttribute("listaContactos", listContactos);
    	request.setAttribute("rolNombre", rolSeleccionado.getNombre());
    	
    	return mapping.findForward("buscarContactos");
    }
    
    public ActionForward setContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception    {
    	String idContacto = request.getParameter("idContacto");
    	System.out.println("id contacto "+idContacto);
    	Contacto contactoSeleccionado = ContactoDAO.findByPrimaryKey(Long.parseLong(idContacto));
    	UsuarioForm uForm = (UsuarioForm) form;
    	uForm.setEmail(contactoSeleccionado.getEmail());
    	uForm.setContacto(contactoSeleccionado);
    	
    	return mapping.findForward("success");
    }
}
