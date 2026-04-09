package ar.org.sicel.web.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.ConfiguracionDAO;
import ar.org.sicel.persistence.ConjuntoAtributosDAO;
import ar.org.sicel.persistence.Especie;
import ar.org.sicel.persistence.EspecieDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Valor;
import ar.org.sicel.persistence.ValorDAO;
import ar.org.sicel.web.altaEdicion.forms.ConjuntoAtributosForm;

public class ConjuntoAtributosAction extends DispatchAction {
	static Logger log = Logger.getLogger(ConjuntoAtributosAction.class);
	
	public ConjuntoAtributosAction() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("inicio de Conjunto de Atributos");
		request.getSession().removeAttribute("hash");
		List listaConjuntoAtributos = ConjuntoAtributosDAO.findAll();
        request.setAttribute("listaConjuntoAtributos", listaConjuntoAtributos);
		return mapping.findForward("init");
	}
	
	public ActionForward detalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("detalle de Conjunto de Atributos");
		ConjuntoAtributosForm conjuntoAtributosForm = (ConjuntoAtributosForm) form;
		List listaAtributos = AtributoDAO.findPorEntidad(conjuntoAtributosForm.getId().toString());  
		request.setAttribute("listaAtributos", listaAtributos);
    	return mapping.findForward("detalle");  
	}
	
	public ActionForward listaRedefinidos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("Rededinicion de atributos");
		ConjuntoAtributosForm cForm = (ConjuntoAtributosForm) form;
		List lista= ValorDAO.find(cForm.getIdAtributo().toString(),cForm.getId().toString());
		Valor valor;
		for(int i=0; i< lista.size();i++){
			valor= (Valor)lista.get(i);
			setEntidad(cForm.getId(),valor.getAtrVariable().getId(),valor);
		}
		request.setAttribute("listadoConjunto", lista);
    	return mapping.findForward("redefinition");  
	}
	
	private void setEntidad(Long idConjunto,Long idAtrVariable,Valor valor){
		switch(idConjunto.intValue()){
		case 2:
			Establecimiento e= EstablecimientoDAO.findAtrVariable(idAtrVariable);
			valor.setIdEntidad(e.getId().toString());
			valor.setNombreEntidad(e.getNombreContacto());
			break;
		case 3:
			Especie es= EspecieDAO.findAtrVariable(idAtrVariable);
			valor.setIdEntidad(es.getId());
			valor.setNombreEntidad(es.getNombre());
			break;
		case 4:
			Raza r= RazaDAO.findAtrVariable(idAtrVariable);
			valor.setIdEntidad(r.getId());
			valor.setNombreEntidad(r.getNombre());
			break;
		case 5:
			Configuracion co= ConfiguracionDAO.findAtrVariable(idAtrVariable);
			valor.setIdEntidad(co.getNombre());
			valor.setNombreEntidad(co.getNombre());
			break;
		}
	}
}
