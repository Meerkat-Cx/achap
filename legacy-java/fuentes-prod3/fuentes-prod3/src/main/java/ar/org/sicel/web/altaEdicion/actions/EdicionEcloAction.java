package ar.org.sicel.web.altaEdicion.actions;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegional;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Estancia;
import ar.org.sicel.persistence.EstanciaDAO;
import ar.org.sicel.persistence.Foto;
import ar.org.sicel.persistence.FotoDAO;
import ar.org.sicel.persistence.LogContacto;
import ar.org.sicel.persistence.ResponsableEclo;
import ar.org.sicel.persistence.ResponsableEcloDAO;
import ar.org.sicel.persistence.Sistema;
import ar.org.sicel.persistence.SistemaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.EcloForm;


public class EdicionEcloAction extends DispatchAction {

	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List regionales = EntidadRegionalDAO.findAll();
		request.setAttribute("regionales", regionales);
		request.getSession().setAttribute("action", "add");
		EcloForm ecloForm = (EcloForm) form;
		//limpiarDatos(ecloForm);
		//System.out.println("inicio de alta de una ECLO");
		
		List availables = SistemaDAO.findAll();
		Set added = new HashSet();
		
		ecloForm.setAvailables(availables);
		ecloForm.setAddedSet(added);
		request.getSession().setAttribute("availables",availables);
		
		return mapping.findForward("success");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//System.out.println("Aca se dio de alta la eclo");
		if (isCancelled(request))
			return (mapping.findForward(Tokens.FAILURE));
		EcloForm ecloForm = (EcloForm) form;
		
		Set added = copyAddedFromIds(ecloForm); // en added estan los agregados
		List availables = (List)request.getSession().getAttribute("availables");
		availables.removeAll(added); // disponibles son lo que sobran
		ecloForm.setAvailables(availables);
		ecloForm.setAddedSet(added);
		
		Integer doc = (StringUtils.isEmpty(ecloForm.getNumeroDocumentoResponsable()))?null:new Integer(ecloForm.getNumeroDocumentoResponsable());
		
		byte[] fotoResponsable = (ecloForm.getFotoResponsable() != null && ecloForm.getFotoResponsable().getFileData().length > 0)?ecloForm.getFotoResponsable().getFileData():null;
		ResponsableEclo responsable = null;
		if (StringUtils.isNotEmpty(ecloForm.getApellidoResponsable()) && StringUtils.isNotEmpty(ecloForm.getNombreResponsable()))
			responsable = ResponsableEcloDAO.createPersistent(doc, ecloForm.getTipoDocumentoResponsable(),
								ecloForm.getNombreResponsable(), ecloForm.getApellidoResponsable(), ecloForm.getApellidoResponsable()+", "+ecloForm.getNombreResponsable(), 
								ecloForm.getComentarioResponsable(), fotoResponsable);
		EntidadRegional entidadRegional = null;
		if (ecloForm.getEntidadRegionalId() != null)
			entidadRegional = EntidadRegionalDAO.findByPrimaryKey(ecloForm.getEntidadRegionalId());
		byte[] foto = (ecloForm.getFoto() != null && ecloForm.getFoto().getFileData().length > 0)?ecloForm.getFoto().getFileData():null;
		
		Eclo eclo = EcloDAO.createPersistent(ecloForm.getNombre(), ecloForm.getComentario(), entidadRegional, responsable,foto, added,ecloForm.getActivo());
		
		
		request.getSession().removeAttribute("ecloForm");		
		request.setAttribute("nuevoid",eclo.getId());
		request.setAttribute("tipo","La Eclo");
		
		return mapping.findForward("mostrarId");
	}
	
	private Set copyAddedFromIds(EcloForm ecloForm) {
		Set result = new HashSet();
		long[] added = ecloForm.getAdded();
		if (added == null)
			return result; 
		
		List availables = SistemaDAO.findAll();
		for (int i = 0; i < added.length; i++) {
			for (Iterator iter = availables.iterator(); iter.hasNext();) {
				Sistema next = (Sistema)iter.next();
				long id = ((Sistema) next).getId().longValue();
				if (added[i] == id) {
					result.add(next);
					break;
				}
			}
		}
		return result;
	}

	public ActionForward initUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception   {   	
		request.getSession().setAttribute("action", "update");
		
		List regionales = EntidadRegionalDAO.findAll();
		request.setAttribute("regionales", regionales);
		
		EcloForm ecloForm = (EcloForm) form;
		//limpiarDatos(ecloForm);
		String stringId = request.getParameter("id");
		System.out.println("Eclo con id "+stringId);
		Eclo eclo = EcloDAO.findByPrimaryKey(Long.parseLong(stringId));
		
		request.setAttribute("ubicaciones", eclo.getUbicacions()!=null?eclo.getUbicacions(): new HashSet());
		
		copiarDatosAlForm(ecloForm, eclo);
		
		/**
		 * para el manejo de los sistemas 
		 */		
		List availables = SistemaDAO.findAll();
		Set added = new HashSet();
		added = eclo.getSistemas();
		availables.removeAll(added);
		
		ecloForm.setAvailables(availables);
		ecloForm.setAddedSet(added);
		request.getSession().setAttribute("availables",availables);
		
		return mapping.findForward("success");
	}

	private void copiarDatosAlForm(EcloForm ecloForm, Eclo eclo) {
		ResponsableEclo responsable = eclo.getResponsable();
		if (responsable != null) {
			ecloForm.setApellidoResponsable(responsable.getApellido());
			ecloForm.setNombreResponsable(responsable.getNombrePersona());
			ecloForm.setComentarioResponsable(responsable.getComentario());
			ecloForm.setNumeroDocumentoResponsable((responsable.getDocumento()!=null)?responsable.getDocumento().toString():"");
			ecloForm.setTipoDocumentoResponsable(responsable.getTipoDocumento());
		}
		ecloForm.setEmail(eclo.getEmail());
		ecloForm.setEntidadRegionalId((eclo.getRegional()!=null)?eclo.getRegional().getId():null);
		ecloForm.setId(eclo.getId());
		ecloForm.setCuit(eclo.getCuit());
		ecloForm.setNombre(eclo.getNombreContacto());
		ecloForm.setComentario(eclo.getComentario());
		ecloForm.setIdFoto((eclo.getFoto()==null)?null:eclo.getFoto().getId());
		ecloForm.setActivo(eclo.getActivo());
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		if (isCancelled(request))
			return (mapping.findForward(Tokens.FAILURE));
		EcloForm ecloForm = (EcloForm)form;
		Eclo eclo = EcloDAO.findByPrimaryKey(ecloForm.getId());
		
		actualizarEclo(eclo, ecloForm,request);
		
		request.getSession().removeAttribute("ecloForm");
		
		return mapping.findForward("list");
	}

	private void actualizarEclo(Eclo eclo, EcloForm ecloForm,HttpServletRequest request) throws Exception {
		ResponsableEclo responsableEclo = eclo.getResponsable();
		if (responsableEclo == null && StringUtils.isNotEmpty(ecloForm.getApellidoResponsable()) && StringUtils.isNotEmpty(ecloForm.getNombreResponsable())) {
			// tengo que crear un responsable nuevo (desde cero)
			Integer doc = (StringUtils.isEmpty(ecloForm.getNumeroDocumentoResponsable()))?null:new Integer(ecloForm.getNumeroDocumentoResponsable());
			
			byte[] fotoResponsable = (ecloForm.getFotoResponsable() != null && ecloForm.getFotoResponsable().getFileData().length > 0)?ecloForm.getFotoResponsable().getFileData():null;
			responsableEclo = ResponsableEcloDAO.createPersistent(doc, ecloForm.getTipoDocumentoResponsable(),
					ecloForm.getNombreResponsable(), ecloForm.getApellidoResponsable(), ecloForm.getApellidoResponsable()+", "+ecloForm.getNombreResponsable(), 
					ecloForm.getComentarioResponsable(), fotoResponsable);
			eclo.setResponsable(responsableEclo);
		}
		else if (responsableEclo != null && StringUtils.isNotEmpty(ecloForm.getApellidoResponsable()) && StringUtils.isNotEmpty(ecloForm.getNombreResponsable())) {
			// la eclo ya tiene un responsable y vienen datos desde el formulario, entonces ACTUALIZO EL RESPONSABLE EXISTENTE
			responsableEclo.setApellido(ecloForm.getApellidoResponsable());
			responsableEclo.setComentario(ecloForm.getComentarioResponsable());
			Integer doc = (StringUtils.isEmpty(ecloForm.getNumeroDocumentoResponsable()))?null:new Integer(ecloForm.getNumeroDocumentoResponsable());
			responsableEclo.setDocumento(doc);
			responsableEclo.setTipoDocumento(ecloForm.getTipoDocumentoResponsable());
			responsableEclo.setNombrePersona(ecloForm.getNombreResponsable());
			
			if (ecloForm.getFotoResponsable() != null && ecloForm.getFotoResponsable().getFileData().length != 0) {
				if (responsableEclo.getFoto() != null)
					FotoDAO.deleteFoto(responsableEclo.getFoto());
				Foto newFoto = FotoDAO.create(ecloForm.getFotoResponsable().getFileData());		
				responsableEclo.setFoto(newFoto);
			}// hasta aqui se modifico el responsable de la eclo
		}		
	
		// ACTUALIZACION DE LA ECLO CORRESPONDIENTE
		eclo.setCuit(ecloForm.getCuit());
		eclo.setComentario(ecloForm.getComentario());
		if (ecloForm.getFoto() != null && ecloForm.getFoto().getFileData().length != 0) {
			if (eclo.getFoto() != null)
				FotoDAO.deleteFoto(eclo.getFoto());
			Foto newFoto = FotoDAO.create(ecloForm.getFoto().getFileData());		
			eclo.setFoto(newFoto);
		}
		eclo.setEmail(ecloForm.getEmail());
		Set added = copyAddedFromIds(ecloForm); // en added estan los agregados
		eclo.getSistemas().clear();
		eclo.getSistemas().addAll(added);
		eclo.setNombreContacto(ecloForm.getNombre());
		EntidadRegional regional = EntidadRegionalDAO.findByPrimaryKey(ecloForm.getEntidadRegionalId());
		eclo.setRegional(regional);
		if(!eclo.getActivo().equals(ecloForm.getActivo())){
			boolean esActivo = ecloForm.getActivo(); 
			eclo.setActivo(esActivo);
			/*Set estancias = eclo.getEstancias();
			Iterator it3 = estancias.iterator();
			while(it3.hasNext()){
				Estancia esta = (Estancia)it3.next();
				Iterator it = esta.getEstablecimientos().iterator();
				while(it.hasNext()){
					Establecimiento es = (Establecimiento)it.next();
					es.setActivoEclo(esActivo);
				}
				esta.setActivoEclo(esActivo);
				//EstanciaDAO.updateEstancia(esta);
			}*/
			Set estab = eclo.getEstablecimientos();
			Iterator it = estab.iterator();
			while(it.hasNext()){
				Establecimiento es = (Establecimiento)it.next();
				es.setActivoEclo(eclo.getActivo());
			}
			Set users = eclo.getUsuarios();
			Iterator it2 = users.iterator();
			while(it2.hasNext()){
				Usuario user = (Usuario)it2.next();
				user.setActivo(eclo.getActivo());
			}
		
			LogContacto log = new LogContacto();			
			//log.setActivo(ecloForm.getActivo().booleanValue());
			if (ecloForm.getActivo().booleanValue()) // activarse
				log.setAccion(LogContacto.ACTIVAR);
			else
				log.setAccion(LogContacto.DESACTIVAR);				
			
			log.setFecha(new Date());
			log.setContacto(eclo);
			Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER); 
			log.setUsuario(user);
			eclo.getBitacora().add(log);
			
		}
		EcloDAO.updateEclo(eclo);
	}	

}
