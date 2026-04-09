package ar.org.sicel.web.find;


import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.apache.commons.lang.StringUtils;

import ar.org.sicel.persistence.CentroDeComputo;
import ar.org.sicel.persistence.CentroDeComputoDAO;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;

import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;


public class BuscarCentroDeComputoAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BusquedaCentroDeComputoForm eForm = (BusquedaCentroDeComputoForm) form;
        eForm.reset();
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		eForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
		eForm.setRol(usuarioActual.getRol().getNombre());
        
		return (mapping.findForward("init"));      
	}
	
	public ActionForward ejecutar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        if (isCancelled(request))
            return (mapping.findForward("init"));
        BusquedaCentroDeComputoForm eForm = (BusquedaCentroDeComputoForm) form;
        Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        List centros = null;
        Long idEclo=null;
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR)){
			Eclo eclo = EcloDAO.findByPrimaryKey(usuarioActual.getContacto().getId());
			idEclo = eclo.getId();
		}
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR)){			
			if(StringUtils.isNotEmpty(eForm.getIdEclo())) idEclo = new Long(eForm.getIdEclo());
		}			
		Long idC = null;
		if(StringUtils.isNotEmpty(eForm.getIdC())) idC = new Long(eForm.getIdC()); 
		centros = CentroDeComputoDAO.findByFilter(idC,eForm.getNombre(),idEclo,eForm.getActivo());
		
		request.setAttribute("centroData", centros);        
		return (mapping.findForward("init"));       
	}
	
	public ActionForward initView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		BusquedaCentroDeComputoForm eForm = (BusquedaCentroDeComputoForm)form;
		eForm.reset();
		Long idC = new Long(request.getParameter("id"));
		eForm.setIdC(idC.toString());
		CentroDeComputo centro = CentroDeComputoDAO.findByPrimaryKey(idC);
		this.copyProperties(centro,eForm);
	
		Set tambos = centro.getTambos();

		request.getSession().setAttribute("action", "view");
		
		request.setAttribute("tambos",tambos); //ir a la base y buscar la lista de tambos
		request.setAttribute("bitacora", centro.getBitacora());
		
		return mapping.findForward("detalle");		
	}
	/**
     * Copio las propiedades de la entidad al form
     * @param BusquedaCentroDeComputoForm - BusquedaCentroDeComputoForm
     * @param CentroDeComputo - CentroDeComputo
     */
    private void copyProperties(CentroDeComputo e, BusquedaCentroDeComputoForm eForm) {
    	eForm.setNombre(e.getNombre());    	
    	eForm.setActivo(e.getActivo());
    	eForm.setCiudad(e.getCiudad());
    	eForm.setCodigoPostal(e.getCodigoPostal());
    	eForm.setDireccion(e.getDireccion());
    	eForm.setMail(e.getMail());
    	eForm.setTelefono(e.getTelefono());    	
    }
}
