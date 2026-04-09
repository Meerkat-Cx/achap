package ar.org.sicel.web.find;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.apache.commons.lang.StringUtils;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Estancia;
import ar.org.sicel.persistence.EstanciaDAO;

import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;


public class BuscarEstanciaAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BusquedaEstanciaForm eForm = (BusquedaEstanciaForm) form;
        eForm.reset();
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		eForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
		eForm.setRol(usuarioActual.getRol().getNombre());
        //List establecimientos =new ArrayList();
        //request.setAttribute("estabData", establecimientos);   

		return (mapping.findForward("init"));      
	}
	
	public ActionForward ejecutar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        if (isCancelled(request))
            return (mapping.findForward("init"));
        BusquedaEstanciaForm eForm = (BusquedaEstanciaForm) form;
        Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        List estancias = null;
        Long idEclo=null;
        Long regional = usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL) ? usuarioActual.getContacto().getId() : null;
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR)){
			Eclo eclo = EcloDAO.findByPrimaryKey(usuarioActual.getContacto().getId());
			idEclo = eclo.getId();
		}
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR)){			
			if(StringUtils.isNotEmpty(eForm.getIdEclo())) idEclo = new Long(eForm.getIdEclo());
		}			
		Long idEst = null;
		if(StringUtils.isNotEmpty(eForm.getIdEst())) idEst = new Long(eForm.getIdEst()); 
		estancias = EstanciaDAO.findByFilter(idEst,eForm.getNombre(),eForm.getNombrePropietario(),idEclo,eForm.getActivo(),regional);
		
		request.setAttribute("estanciaData", estancias);        
		return (mapping.findForward("init"));       
	}
	
	public ActionForward initView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		BusquedaEstanciaForm eForm = (BusquedaEstanciaForm)form;
		eForm.reset();
		Long idEst = new Long(request.getParameter("id"));
		eForm.setIdEst(idEst.toString());
		Estancia estab = EstanciaDAO.findByPrimaryKey(idEst);
		this.copyProperties(estab,eForm);

		request.getSession().setAttribute("action", "view");
		request.setAttribute("tambos",estab.getEstablecimientos());
		request.setAttribute("bitacora", estab.getBitacora());
		
		return mapping.findForward("detalle");		
	}
	/**
     * Copio las propiedades de la entidad al form
     * @param EstanciaForm - BusquedaEstanciaForm
     * @param Estancia - Estancia
     */
    private void copyProperties(Estancia e, BusquedaEstanciaForm eForm) {
    	eForm.setNombre(e.getNombreContacto());
    	eForm.setNombrePropietario(e.getPropietario().getNombreContacto());
    	eForm.setActivo(e.getActivo());    	
    }
}
