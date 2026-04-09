package ar.org.sicel.web.altaEdicion.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.EstablecimientoForm;

/** 

 * XDoclet definition:
 * @struts.action 
 * 		path="/preAltaEstablecimiento" 
 * 		name="establecimientoForm" 		 
 * 		scope="request" 
 * 		validate="false"
 * 
 * @struts.action-forward
 * 		name="success"
 * 		path=".altaEstablecimientoPage"
 * 
 * @struts.action-forward
 * 		name="edicion"
 * 		path=".edicionEstablecimiento"
 * 
 */

public class PreAltaEstablecimientoAction extends Action {
	
	Logger log = Logger.getLogger(PreAltaEstablecimientoAction.class);
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse arg3) throws Exception {
			
		/*EstablecimientoForm establecimientoForm = (EstablecimientoForm) form;
		Boolean esEdicion = establecimientoForm.getEsEdicion();
			
		log.info("Obteniendo Eclos");				
		List listaEclos = EcloDAO.findAll();	
		
		if(esEdicion) {
			listaEclos.add(0, ((Establecimiento)establecimientoForm.getContacto()).getEclo());
			req.setAttribute("eclos", listaEclos);
			
			return mapping.findForward("edicion");
		} else {
			req.setAttribute("eclos", listaEclos);
			return mapping.findForward(Tokens.SUCCESS);
		}*/
		return null;
	}

}
