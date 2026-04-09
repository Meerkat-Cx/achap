package ar.org.sicel.web.altaEdicion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import ar.org.sicel.persistence.ResponsableSistema;
import ar.org.sicel.persistence.ResponsableSistemaDAO;
import ar.org.sicel.persistence.Sistema;
import ar.org.sicel.persistence.SistemaDAO;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.SistemaForm;

public class EdicionSistemaAction extends DispatchAction {

	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.getSession().setAttribute("action", "add");
		return mapping.findForward("success");
	}


	public ActionForward initUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.getSession().setAttribute("action", "update");
		SistemaForm sistemaForm = (SistemaForm) form;
		
		String stringId = request.getParameter("id");
		
		Sistema sistema = SistemaDAO.findByPrimaryKey(Long.parseLong(stringId));
		copiarDatosAlForm(sistema, sistemaForm);
		return mapping.findForward("success");
	}
	
	/**
	 * Copiar los datos del sistema al formulario
	 * @param sistema
	 * @param sistemaForm
	 */
	private void copiarDatosAlForm(Sistema sistema, SistemaForm sistemaForm) {
		ResponsableSistema res = sistema.getResponsable();
		sistemaForm.setApellido(res.getApellido());
		sistemaForm.setComentario(sistema.getComentario());
		sistemaForm.setComentarioResponsable(res.getComentario());
		sistemaForm.setId(sistema.getId());
		sistemaForm.setNombre(sistema.getNombre());
		sistemaForm.setNombreEmpresa(res.getNombreContacto());
		sistemaForm.setNombreResponsable(res.getNombrePersona());
		sistemaForm.setNumeroDocumento((res.getDocumento()==null)?"":res.getDocumento().toString());
		sistemaForm.setTipoDocumento(res.getTipoDocumento());
		sistemaForm.setVersion(sistema.getVersion());	
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		if (isCancelled(request))
			return (mapping.findForward(Tokens.FAILURE));
		SistemaForm sistemaForm = (SistemaForm) form;
		Sistema sistemaOld = SistemaDAO.findByName(sistemaForm.getNombre());
		if (sistemaOld != null) {
	    	ActionMessages actionerrors = new ActionMessages();
        	actionerrors.add("uniqueConstraint",new ActionMessage("entidad.existe", "Sistema", sistemaForm.getNombre()));        
        	saveMessages(request, actionerrors);
            request.getSession().setAttribute("action", "add");
            return mapping.findForward("success");
		}
		
		
		Integer doc = (StringUtils.isEmpty(sistemaForm.getNumeroDocumento()))?null:new Integer(sistemaForm.getNumeroDocumento());
		
		ResponsableSistema responsable = ResponsableSistemaDAO.createPersistent(doc, sistemaForm.getTipoDocumento(), sistemaForm.getNombreResponsable(),
												sistemaForm.getApellido(), sistemaForm.getNombreEmpresa(), sistemaForm.getComentarioResponsable());
		SistemaDAO.createPersistent(sistemaForm.getNombre(), sistemaForm.getVersion(),
										sistemaForm.getComentario(), responsable);
		request.getSession().removeAttribute("sistemaForm");
		return mapping.findForward("list");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		if (isCancelled(request))
			return (mapping.findForward(Tokens.FAILURE));
		
		SistemaForm sistemaForm = (SistemaForm) form;
		Sistema sistemaOld = SistemaDAO.findByName(sistemaForm.getNombre());
		if (sistemaOld != null && !sistemaOld.getId().equals(sistemaForm.getId())) {
			// ya existe nombre pero no es el sistema que estoy modificando
	    	ActionMessages actionerrors = new ActionMessages();
        	actionerrors.add("uniqueConstraint",new ActionMessage("entidad.existe", "Sistema", sistemaForm.getNombre()));        
        	saveMessages(request, actionerrors);
            request.getSession().setAttribute("action", "update");
            return mapping.findForward("success");
		}
		
		
		Sistema sistema= SistemaDAO.findByPrimaryKey(sistemaForm.getId());
		actualizarSistema(sistema, sistemaForm);
		
		request.getSession().removeAttribute("sistemaForm");
		return mapping.findForward("list");
	}

	private void actualizarSistema(Sistema sistema, SistemaForm sistemaForm) {
		sistema.setComentario(sistemaForm.getComentario());
		sistema.setNombre(sistemaForm.getNombre());
		sistema.setVersion(sistemaForm.getVersion());
		
		ResponsableSistema res = sistema.getResponsable();
		Integer doc = (StringUtils.isEmpty(sistemaForm.getNumeroDocumento()))?null:new Integer(sistemaForm.getNumeroDocumento());
		res.setApellido(sistemaForm.getApellido());
		res.setComentario(sistemaForm.getComentarioResponsable());
		res.setDocumento(doc);
		res.setNombreContacto(sistemaForm.getNombreEmpresa());
		res.setNombrePersona(sistemaForm.getNombreResponsable());
		res.setTipoDocumento(sistemaForm.getTipoDocumento());
		
	}
}
