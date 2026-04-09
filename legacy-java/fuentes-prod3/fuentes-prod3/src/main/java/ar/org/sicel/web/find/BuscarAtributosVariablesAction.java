package ar.org.sicel.web.find;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Valor;
import ar.org.sicel.persistence.ValorAdmAtrDAO;
import ar.org.sicel.persistence.ValorDAO;
import ar.org.sicel.web.altaEdicion.forms.AtributosVariablesForm;

/**
 * @struts.action 
 * 		path="/buscarAtributosVariables"        
 * 		name="atributosVariablesForm"
 * 		scope="request"
 * 		validate="false"
 * 
 * @struts.action-forward 
 * 		name="listado" 
 * 		path=".atributosVariables"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward 
 * 		name="failure"
 * 		path=".main"
 *
 */
public final class BuscarAtributosVariablesAction extends Action {
	
	@SuppressWarnings({"unchecked","unchecked", "unchecked", "unchecked"})
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AtributosVariablesForm atributosVariablesForm = (AtributosVariablesForm) form;
		
		Long id = atributosVariablesForm.getId();
		
		Establecimiento establecimiento = EstablecimientoDAO.findByPrimaryKey(id);
		
		if((establecimiento.getAtrVariablesEstab()!=null) && (establecimiento.getAtrVariablesEstab().getId()!=null)) {
			List listaAtributos = new ArrayList();
			Set atributos = establecimiento.getAtrVariablesEstab().getConjuntoAtributos().getAtributos();			
			Iterator it = atributos.iterator();
			while(it.hasNext()) {
				Atributo atributo = (Atributo) it.next();
				Valor valor = ValorDAO.create(null, ValorAdmAtrDAO.create(""));
				
				List list = new ArrayList(3); 								
				list.add(atributo);
				list.add(valor);
				
				listaAtributos.add(list);
				listaAtributos.add(list);
			}
			
			atributosVariablesForm.setAtributos(listaAtributos);
		}
		        
		return (mapping.findForward("listado"));
	}

}
