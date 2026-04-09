package ar.org.sicel.web.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.ConjuntoAtributos;
import ar.org.sicel.persistence.ConjuntoAtributosDAO;
import ar.org.sicel.web.altaEdicion.forms.ConjuntoAtributosForm;

/**
 * @struts.action 
 * 		path="/buscarConjuntoAtributos"
 * 		name="conjuntoAtributosForm"
 * 		scope="request"
 * 		validate="false"
 * 
 * @struts.action-forward 
 * 		name="listado" 
 * 		path=".conjuntoAtTable"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="edicion" 
 * 		path=".edicionConjuntoAtributos"
 *
 * @struts.action-forward
 * 		name="detalle" 
 * 		path=".conjuntoAtributosDetalle"
  
 */
public final class BuscarConjuntoAtributosAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        if (isCancelled(request))
            return (mapping.findForward("main"));
        
        ConjuntoAtributosForm conjuntoAtributosForm = (ConjuntoAtributosForm) form;
        
        Boolean esEdicion = conjuntoAtributosForm.getEsEdicion();
        Long id = conjuntoAtributosForm.getId();
        if(esEdicion) {
        	ConjuntoAtributos conjuntoAtributos = ConjuntoAtributosDAO.findByPrimaryKey(id);
        	
        	if(conjuntoAtributos!=null) {
        		//conjuntoAtributosForm.setConjuntoAtributos(conjuntoAtributos);
        		
        		return mapping.findForward("edicion");
        	} else return mapping.findForward("main");
        } else {
        	if(id!= -1) {
        		ConjuntoAtributos conjuntoAtributos = ConjuntoAtributosDAO.findByPrimaryKey(id);            	           	
            	//conjuntoAtributosForm.setConjuntoAtributos(conjuntoAtributos);
            		
            	return mapping.findForward("detalle");            	 
        	} else {
        		List listaConjuntoAtributos = ConjuntoAtributosDAO.findAll();
                request.setAttribute("listaConjuntoAtributos", listaConjuntoAtributos);
        		return (mapping.findForward("listado"));
        	}
        }        
	}
}
