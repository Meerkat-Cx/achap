package ar.org.sicel.web.find;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.PropietarioExpdDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PropietarioForm;


public class BuscarPropietarioAction extends DispatchAction {
	
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
		PropietarioForm eForm = (PropietarioForm) form;
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		String rol = "admin";
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO))
			rol = "externo";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
			rol = "tecnica";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL))
			rol = "regional";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			rol = "eclo";
		request.setAttribute("rol", rol); 
		request.setAttribute("metodo","");
        List propietariosData = new ArrayList();
        request.setAttribute("propietariosData", propietariosData);   
        eForm.reset();
        request.setAttribute("error","El filtro no arrojo ningun resultado");
		return (mapping.findForward(Tokens.SUCCESS));
	}
	
	
	/**
	 * Metodo que realiza la busqueda de propietarios segun los filtros correspondientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ejecutar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		if (isCancelled(request))
			return (mapping.findForward("main"));
		request.setAttribute("metodo", "ejecutar");
		List listaPropietarios = new ArrayList();
		PropietarioForm propietarioForm = (PropietarioForm) form;
		String enSistema = propietarioForm.getDelSistema();
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO)){
			
			if (StringUtils.isNotEmpty(propietarioForm.getCuit()))
				//listaPropietarios = PropietarioDAO.finByCuit("30-70973444-6");
				listaPropietarios = PropietarioDAO.finByCuit(propietarioForm.getCuit());
			else
				//listaPropietarios = PropietarioDAO.finByTambosRenspa("20.018.0.01277/00");
				listaPropietarios = PropietarioDAO.finByTambosRenspa(propietarioForm.getRenspa());
		}
		else{
			/*List lista = PropietarioDAO.finByCuit("30-70973444-6");
			
			List lista1 = PropietarioDAO.finByTambosRenspa("20.018.0.01277/00");*/
			Long idEstablecimiento = null;
			Long idProp = null;
			if (StringUtils.isNotEmpty(propietarioForm.getIdEstablecimiento()))
				idEstablecimiento = Long.parseLong(propietarioForm.getIdEstablecimiento());
			if (StringUtils.isNotEmpty(propietarioForm.getIdProp()))
				idProp = Long.parseLong(propietarioForm.getIdProp());
			if (usuarioActual.getRol().getNombre().equals((Tokens.NOMBREROLPROVEEDOR)))
				listaPropietarios = PropietarioDAO.findPropietariosBajoEclo(propietarioForm.getSocRural(),idProp,
						propietarioForm.getNombre(), idEstablecimiento, propietarioForm.getNombreEstancia(),usuarioActual,enSistema.equals("si") ? true:false);
			else if (usuarioActual.getRol().getNombre().equals((Tokens.NOMBREROLREGIONAL))){
				listaPropietarios = PropietarioDAO.findPropietariosBajoRegional(propietarioForm.getSocRural(),idProp,
						propietarioForm.getNombre(), idEstablecimiento, propietarioForm.getNombreEstancia(),usuarioActual,enSistema.equals("si") ? true:false);
			}else/*rol ADMINISTRADOR, todo el sistema*/
				listaPropietarios = PropietarioDAO.findPropietarios(propietarioForm.getSocRural(),idProp,
					propietarioForm.getNombre(), idEstablecimiento, propietarioForm.getNombreEstancia(),usuarioActual,true);
		}
		if(listaPropietarios.isEmpty()){
				request.setAttribute("error","El filtro no arrojo ningun resultado");
				String rol = "admin";
				if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO))
					rol = "externo";
				else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
					rol = "tecnica";
				else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL))
					rol = "regional";
				else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
					rol = "eclo";
				request.setAttribute("rol", rol);   
				return mapping.findForward(Tokens.SUCCESS);		
		}
		String rol = "admin";
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO))
			rol = "externo";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
			rol = "tecnica";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL))
			rol = "regional";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			rol = "eclo";
		request.setAttribute("rol", rol);   
		request.setAttribute("propietariosData", listaPropietarios);        
			return (mapping.findForward(Tokens.SUCCESS));     
	
	}
	
	public ActionForward ejecutarSRA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		if (isCancelled(request))
			return (mapping.findForward("main"));
		request.setAttribute("metodo", "ejecutarSRA");
		List listaPropietarios = new ArrayList();
		PropietarioForm propietarioForm = (PropietarioForm) form;	
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		String sra = propietarioForm.getRaza();
		Long idEclo = usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR) ? usuarioActual.getContacto().getId() : null;
		listaPropietarios = PropietarioExpdDAO.findByNumero(new Long(sra),idEclo,propietarioForm.getDelSistema());
		if(listaPropietarios.isEmpty()){
				request.setAttribute("error","El filtro no arrojo ningun resultado");
				String rol = "admin";
				if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO))
					rol = "externo";
				else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
					rol = "tecnica";
				else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
					rol = "eclo";
				request.setAttribute("rol", rol);   
				return mapping.findForward(Tokens.SUCCESS);		
		}
		String rol = "admin";
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO))
			rol = "externo";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
			rol = "tecnica";
		else if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			rol = "eclo";
		request.setAttribute("rol", rol);   
		request.setAttribute("propietariosData", listaPropietarios);        
			return (mapping.findForward(Tokens.SUCCESS));     
	
	}
		
	public ActionForward initView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		PropietarioForm eForm = (PropietarioForm)form;
		String idEst = request.getParameter("id");
		eForm.reset();
		eForm.setIdProp(idEst);
		Propietario prop = PropietarioDAO.findByPrimaryKey(new Long(eForm.getIdProp()));
		this.copyProperties(prop,eForm);
		//Set logs = prop.getLogs();
		
		//request.setAttribute("conjunto", prop.getLogs());       
		request.setAttribute("bitacora", prop.getBitacora());
		request.setAttribute("expds", prop.getExpds());
		//request.setAttribute("listaEstablecimientos", prop.getEstablecimientos()); // son los tambos
		//request.setAttribute("listaEstancias", prop.getEstancias()); // son los tambos

        return mapping.findForward("detalle");		
	}
	
	private void copyProperties(Propietario prop,PropietarioForm pForm){
		
		pForm.setIdProp(prop.getId().toString());
		/*if(prop.getSra()!=null)
			pForm.setSocRural(prop.getSra().toString());*/
		pForm.setNombre(prop.getNombreContacto());
		if(prop.getSocio()!=null)
			pForm.setSocio(prop.getSocio().toString());
		if(prop.getHar()!=null)
			pForm.setHar(prop.getHar().toString());
		if(prop.getS1Eclo()!=null)
			pForm.setS1Eclo(prop.getS1Eclo().toString());
		if(prop.getS1Prop()!=null)
			pForm.setS1Prop(prop.getS1Prop().toString());
		if(prop.getActivo()!=null)
			pForm.setActivo(prop.getActivo());
		if(prop.getEsPersonaFisica()!=null)
			pForm.setEsPersonaFisica(prop.getEsPersonaFisica());
		if(prop.getActivo()!=null){
			pForm.setActivo(prop.getActivo());
		}
		if (StringUtils.isNotEmpty(prop.getCuig())) {
			pForm.setCuig(prop.getCuig());
		}
		if (StringUtils.isNotEmpty(prop.getCuit())) {
			pForm.setCuit(prop.getCuit());
		}
		if (StringUtils.isNotEmpty(prop.getRenspa())) {
			pForm.setRenspa(prop.getRenspa());
		}
		/*if (prop.getSraExpd() != null) {
			pForm.setNroExpd(prop.getSraExpd().getNumero().toString());
		}*/
		if (StringUtils.isNotEmpty(prop.getPrefijo())) {
			pForm.setPrefijo(prop.getPrefijo());
		}
		if (pForm.getDelSistema()!=null) {
			pForm.setDelSistema(pForm.getDelSistema());
		}
	}

}