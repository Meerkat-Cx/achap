package ar.org.sicel.web.altaEdicion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.EstablecimientoForm;




/** 

 * XDoclet definition:
 * @struts.action 
 * 		path="/altaEstablecimiento" 
 * 		name="establecimientoForm" 		 
 * 		input=".altaEstablecimientoPage" 
 * 		scope="request" 
 * 		validate="false"
 * 
 * @struts.action-forward 
 * 		name="success" 
 * 		path=".estabDetalle"
 * 
 * @struts.action-forward 
 * 		name="failure" 
 * 		path=".altaEstablecimientoPage"
 * 
 * @struts.action-forward 
 * 		name="main" 
 * 		path=".main"
 * 
 * @struts.action-forward
 * 		name="refreshClosePopup"
 * 		path=".refreshParentAndClosePopup"
 *
 * @struts.action-forward
 * 		name="closePopup"
 * 		path=".closePopup" 
 */

public class AltaEstablecimientoAction extends Action {
/*
	@SuppressWarnings({"unchecked","unchecked", "unchecked", "unchecked"})
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		
		EstablecimientoForm establecimientoForm = (EstablecimientoForm)form;
		
		Long id = establecimientoForm.getId();
		
		if(isCancelled(request)) {
			if(id==null)
				return mapping.findForward(Tokens.MAIN);
			else return mapping.findForward("closePopup");
		}
						
		Boolean esEdicion = establecimientoForm.getEsEdicion();
		Long propietarioId = establecimientoForm.getPropietarioId();
		Propietario propietario = PropietarioDAO.findByPrimaryKey(propietarioId);
		if(propietario==null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("propietario_no_existe"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);			
		}
				
		Long ecloId = establecimientoForm.getEcloId();
		Eclo eclo = EcloDAO.findByPrimaryKey(ecloId);
		if(eclo==null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.ecloNoExiste"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);			
		}
				
		String nombreContacto = ((Establecimiento)establecimientoForm.getContacto()).getNombreContacto();		
		String comentario = ((Establecimiento)establecimientoForm.getContacto()).getComentario();
		
		
		//if-else para disernir alta o modificacion respectivamente
		if(!esEdicion) {
			Establecimiento establecimiento = EstablecimientoDAO.createPersistent(nombreContacto, comentario, propietario, eclo);
			propietario.getEstablecimientos().add(establecimiento);
			eclo.getEstablecimientos().add(establecimiento);		
			
			
			establecimientoForm.setContacto(establecimiento);		
			return mapping.findForward(Tokens.SUCCESS);
			
		} else {
			Establecimiento establecimiento = EstablecimientoDAO.findByPrimaryKey(id);
			
			if(establecimiento!=null) {
				establecimiento.setNombreContacto(nombreContacto);
				establecimiento.setComentario(comentario);
				
				//si el establecimiento cambia de eclo, hay que desregistrarlo del antiguo y registrarlo en el nuevo
				if(!establecimiento.getEclo().equals(eclo)) {
					establecimiento.getEclo().getEstablecimientos().remove(establecimiento);
					establecimiento.setEclo(eclo);
					eclo.getEstablecimientos().add(establecimiento);
				}
				
				//si el establecimiento cambia de propietario, hay que desregistrarlo del antiguo y registrarlo en el nuevo
				if(!establecimiento.getPropietario().equals(propietario)) {
					establecimiento.getPropietario().getEstablecimientos().remove(establecimiento);
					establecimiento.setPropietario(propietario);
					propietario.getEstablecimientos().add(establecimiento);
				}
			
				return mapping.findForward("refreshClosePopup");
			} else {
				return mapping.findForward("closePopup");
			}
			
		}
		
		
		
		
				
	}*/
	
}
