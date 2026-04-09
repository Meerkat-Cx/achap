package ar.org.sicel.web.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.web.altaEdicion.forms.EcloForm;

public class ListarEclosAction extends DispatchAction {

	private static final Logger log; 
	
	static 
	{
		log = Logger.getLogger(ListarEntidadRegionalAction.class);
	}	
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("inicio de la busqueda de eclos");
		return mapping.findForward("list");
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		EcloBuscarForm f = (EcloBuscarForm) form;
		List listaEclos = EcloDAO.findEclosPorFiltro(f.getNombre(), f.getIdentificador() ,f.getNombreRegional());
		request.setAttribute("listaEclos", listaEclos);
		System.out.println("filtrando eclos ");
		return mapping.findForward("list");
	}

	public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception	{
		return mapping.findForward("list"); 
	}

}
