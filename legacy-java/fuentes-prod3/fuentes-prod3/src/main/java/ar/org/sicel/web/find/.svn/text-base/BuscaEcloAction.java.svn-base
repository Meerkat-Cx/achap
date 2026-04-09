package ar.org.sicel.web.find;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EstablecimientoDAO;

public class BuscaEcloAction extends DispatchAction {

	public BuscaEcloAction() {
		super();
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("init");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		EcloBuscarForm f = (EcloBuscarForm) form;
		List listaEclos = EcloDAO.findEclosPorFiltro(f.getNombre(), f.getIdentificador(), f.getNombreRegional());
		request.setAttribute("listaEclos", listaEclos);
		return mapping.findForward("init");
	}

	public ActionForward filtrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		EcloBuscarForm f = (EcloBuscarForm) form;
		List listaEclos = EcloDAO.findEclosPorFiltro(f.getNombre(), f.getNombreRegional());
		request.setAttribute("listaEclos", listaEclos);
		return mapping.findForward("init");
	}

	
	public ActionForward mostrarDetalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		String ecloId = request.getParameter("ecloId");
		Eclo eclo = EcloDAO.findByPrimaryKey(Long.parseLong(ecloId));
		request.setAttribute("eclo", eclo);
		ArrayList<String> orders = new ArrayList<String>();
		orders.add("propietario");
		orders.add("estancia");
		orders.add("id");
		request.setAttribute("establecimientos", EstablecimientoDAO.findEstablecimientos(eclo,null,null,orders));
		
		return mapping.findForward("mostrarDetalle");
	}
	
	

	

}
