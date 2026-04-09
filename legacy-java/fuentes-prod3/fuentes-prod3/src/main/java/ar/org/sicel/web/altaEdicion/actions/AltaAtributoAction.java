/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.ConjuntoAtributos;
import ar.org.sicel.persistence.ConjuntoAtributosDAO;
import ar.org.sicel.persistence.ValorAdmAtr;
import ar.org.sicel.persistence.ValorAdmAtrDAO;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AtributoForm;

/**
 * @author Ramiro Trachsel
 *
 */
/** 

 * XDoclet definition:
 * @struts.action 
 * 		path="/altaAtributo" 
 * 		name="atributoForm" 		 
 * 		scope="request" 
 * 		validate="false"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 * 
 * @struts.action-forward
 * 		name="refreshClosePopup"
 * 		path=".refreshParentAndClosePopup"
 *
 *	@struts.action-forward
 * 		name="closePopup"
 * 		path=".closePopup" 
 */
public class AltaAtributoAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AtributoForm atributoForm = (AtributoForm) form;
		
		Long id = atributoForm.getId();
		
		if(isCancelled(request)) {
			if(id==null)
				return mapping.findForward(Tokens.MAIN);
			else return mapping.findForward("closePopup");
		}
		
		Boolean esEdicion = atributoForm.getEsEdicion();
		String descripcion = atributoForm.getAtributo().getDescripcion();
		String nombre = atributoForm.getAtributo().getNombre();
		
		
		if(esEdicion) {
			Atributo atributo = AtributoDAO.findByPrimaryKey(id);
			ValorAdmAtr valorPorDefecto = ValorAdmAtrDAO.findByPrimaryKey(new Long(atributoForm.getValorPorDefecto()));
			 
			
			if(atributo!=null & valorPorDefecto!=null) {				
				atributo.setDescripcion(descripcion);
				atributo.setNombre(nombre);
				atributo.setValorPorDefecto(valorPorDefecto);				
				
				atributoForm.setAtributo(atributo);
				return mapping.findForward("refreshClosePopup");				
			} else return mapping.findForward("closePopup"); 
			
		} else {
			String valorPorDefecto = atributoForm.getValorPorDefecto();
			Long conjuntoAtributosId = atributoForm.getConjuntoAtributosId();
			ConjuntoAtributos conjuntoAtributos = ConjuntoAtributosDAO.findByPrimaryKey(conjuntoAtributosId);
			
			if(conjuntoAtributos!=null) {
				Atributo atributo = AtributoDAO.createPersistent(conjuntoAtributos, nombre, descripcion, valorPorDefecto);
				
				atributoForm.setAtributo(atributo);			
				return mapping.findForward("refreshClosePopup");
				
			} else return mapping.findForward("closePopup");				
		}
	}

}
