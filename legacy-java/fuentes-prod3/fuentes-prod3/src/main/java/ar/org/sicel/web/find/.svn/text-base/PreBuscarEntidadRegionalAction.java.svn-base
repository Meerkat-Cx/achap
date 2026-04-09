package ar.org.sicel.web.find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.web.Tokens;

/** 

 * XDoclet definition:
 * @struts.action 
 * 		path="/preBuscarEntidadRegional" 
 * 		name="busquedaForm" 		 
 * 		scope="request" 
 * 		validate="false"
 * 
 * @struts.action-forward
 * 		name="success"
 * 		path=".buscarEntidadRegional"
 */

public class PreBuscarEntidadRegionalAction extends Action {
	Logger log = Logger.getLogger(PreBuscarEstablecimientoAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse arg3) throws Exception {
			
		log.info("Obteniendo Entidades Regionales");				
		req.setAttribute("regionales", EntidadRegionalDAO.findAll());
		
		log.info("Obteniendo Eclos");				
		req.setAttribute("eclos", EcloDAO.findAll());	
				
		return mapping.findForward(Tokens.SUCCESS);
	}

}
