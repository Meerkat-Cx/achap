package ar.org.sicel.web.altaEdicion.actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.CentroDeComputo;
import ar.org.sicel.persistence.CentroDeComputoDAO;
import ar.org.sicel.persistence.LogContacto;
import ar.org.sicel.persistence.Usuario;

import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.CentroDeComputoForm;

public class EdicionCentroDeComputoAction extends DispatchAction {
	
	public EdicionCentroDeComputoAction() {
		super();
	}
	
	private void limpiarForm(CentroDeComputoForm f) {
		f.setCiudad("");
		f.setActivo(false);
		f.setCodigoPostal("");
		f.setDireccion("");
		f.setMail("");
		f.setId("");
		f.setNombre("");
		f.setTelefono("");
		f.setNombreFind("");
	}


	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("inicio de la busqueda de Centros de Computos");		
		this.limpiarForm((CentroDeComputoForm) form);
		return mapping.findForward("init");
	}
	
	public ActionForward initAdd(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception
	{
		this.limpiarForm((CentroDeComputoForm)actionform);
		httpservletrequest.getSession().setAttribute("action", "add");
		return actionmapping.findForward("edit");
	}
	
	public ActionForward initmod(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws Exception
	{
		Long id = new Long(httpservletrequest.getParameter("id"));
		if(id!=null) {
			CentroDeComputo c = CentroDeComputoDAO.findByPrimaryKey(id);
			if(c!=null) {
				copiarPropiedades(c,(CentroDeComputoForm)actionform);				
				httpservletrequest.getSession().setAttribute("action", "update");
				return actionmapping.findForward("edit");
			}			
		}
		return actionmapping.findForward("failure");
	}
	
	private void copiarPropiedades(CentroDeComputo centro, CentroDeComputoForm centroForm) {
		centroForm.setCiudad(centro.getCiudad());
		centroForm.setDireccion(centro.getDireccion());
		centroForm.setMail(centro.getMail());
		centroForm.setNombre(centro.getNombre());
		centroForm.setCodigoPostal(centro.getCodigoPostal());
		centroForm.setTelefono(centro.getTelefono());
		centroForm.setId(centro.getId().toString());	
		centroForm.setActivo(centro.getActivo());
	}
	
	private void copyProperties(CentroDeComputoForm centroForm, CentroDeComputo centro, HttpServletRequest httpservletrequest) {
		centro.setCiudad(centroForm.getCiudad());
		centro.setDireccion(centroForm.getDireccion());
		centro.setMail(centroForm.getMail());
		centro.setNombre(centroForm.getNombre());
		centro.setCodigoPostal(centroForm.getCodigoPostal());
		centro.setTelefono(centroForm.getTelefono());
		//centro.setId(new Long(centroForm.getId()));
		if(centro.getActivo()==null || !centro.getActivo().equals(centroForm.getActivo())) {
			LogContacto log = new LogContacto();
			if (centroForm.getActivo().booleanValue())
				log.setAccion(LogContacto.ACTIVAR);
			else
				log.setAccion(LogContacto.DESACTIVAR);
			log.setFecha(new Date());
			log.setCentro(centro);
			Usuario user = (Usuario) httpservletrequest.getSession().getAttribute(Tokens.CURRENTUSER); 
			log.setUsuario(user);
			centro.getBitacora().add(log);			
		}
		centro.setActivo(centroForm.getActivo());
		
	}
	
	public ActionForward update(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws Exception
	{
		 if (isCancelled(httpservletrequest)) {
			 	this.limpiarForm((CentroDeComputoForm) actionform);
	            return (actionmapping.findForward("init"));
		 }
		 
		Long id = new Long(((CentroDeComputoForm)actionform).getId());
		CentroDeComputo c = CentroDeComputoDAO.findByPrimaryKey(id);
		if(c!=null) {
			this.copyProperties((CentroDeComputoForm)actionform, c, httpservletrequest);
			c.setTelefono(((CentroDeComputoForm)actionform).getTelefono());
			CentroDeComputoDAO.updateCentroDeComputo(c);
			this.limpiarForm((CentroDeComputoForm) actionform);
			return actionmapping.findForward("init");
		}		
		return actionmapping.findForward("failuire");				
	}
	
	public ActionForward add(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception
	{
		if (isCancelled(httpservletrequest))
			return (actionmapping.findForward(Tokens.FAILURE));
		
		CentroDeComputoForm centroForm = (CentroDeComputoForm)actionform;		
		ActionMessages actionerrors = new ActionMessages();		
		saveMessages(httpservletrequest, actionerrors);		
		CentroDeComputo centro = new CentroDeComputo();		
		this.copyProperties(centroForm, centro,httpservletrequest);
		CentroDeComputoDAO.createCentroDeComputo(centro);		
		httpservletrequest.setAttribute("nuevoid",centro.getId());
    	httpservletrequest.setAttribute("tipo","El Centro de Computos");
		
		return actionmapping.findForward("mostrarId");
	}
	
	public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		CentroDeComputoForm f = (CentroDeComputoForm) form;
		Long id = null;
		if(StringUtils.isNotEmpty(f.getId())) {
			id = new Long(f.getId());
		}
		List listaCentros = CentroDeComputoDAO.findCentrosDeComputo(id,f.getNombreFind());
		request.setAttribute("listaCentros", listaCentros);
		log.debug("buscando Centros de computos");
		return mapping.findForward("init");
	}	
}
