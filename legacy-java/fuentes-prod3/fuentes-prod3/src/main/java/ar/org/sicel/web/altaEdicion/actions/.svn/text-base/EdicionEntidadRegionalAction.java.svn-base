package ar.org.sicel.web.altaEdicion.actions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.EntidadRegional;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.Foto;
import ar.org.sicel.persistence.FotoDAO;
import ar.org.sicel.persistence.Ubicacion;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.EntidadRegionalForm;


public class EdicionEntidadRegionalAction extends DispatchAction {
	
	/**
	 * Prepara los valores para cargar la pantalla de alta para
	 * usuarios
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return ActionForward
	 * @throws Exception
	 */    
	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception   {
		request.getSession().setAttribute("action", "add");
		EntidadRegionalForm rform = (EntidadRegionalForm) form;
		rform.setComentario("");
		rform.setFileUpload(null);
		rform.setId(null);
		rform.setIdFoto(null);
		rform.setNombre("");
		
		return mapping.findForward("success");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse repsonse)
	throws Exception {		
		if (isCancelled(request))
			return (mapping.findForward(Tokens.FAILURE));
		EntidadRegionalForm regionalForm = (EntidadRegionalForm) form;		
		
		EntidadRegional entidadRegionalNew =EntidadRegionalDAO.create(regionalForm.getNombre(), 
																	regionalForm.getComentario(), 
							(regionalForm.getFileUpload() != null && regionalForm.getFileUpload().getFileData().length > 0)?regionalForm.getFileUpload().getFileData():null);		
		
		EntidadRegionalDAO.saveEntidadRegional(entidadRegionalNew);
		request.setAttribute("nuevoid",entidadRegionalNew.getId());
		request.setAttribute("tipo","La Entidad Regional");
		
		request.getSession().removeAttribute("entidadRegionalForm");
		
		return mapping.findForward("mostrarId");
	}
	
	public ActionForward initUpdate(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception   {   	
		httpservletrequest.getSession().setAttribute("action", "update");
		EntidadRegionalForm entidadForm = (EntidadRegionalForm) actionform;
		String stringId = httpservletrequest.getParameter("id");
		System.out.println("entidad regional con id "+stringId);
		EntidadRegional entidadRegional = EntidadRegionalDAO.findByPrimaryKey(Long.parseLong(stringId));
		
		httpservletrequest.setAttribute("ubicaciones", entidadRegional.getUbicacions()!=null?entidadRegional.getUbicacions(): new HashSet());
		copiarDatosAlForm(entidadForm, entidadRegional);
		return actionmapping.findForward("success");
	}
	
	private void copiarDatosAlForm(EntidadRegionalForm entidadForm, EntidadRegional entidadRegional) {
		entidadForm.setEmail(entidadRegional.getEmail());
		entidadForm.setComentario(entidadRegional.getComentario());
		entidadForm.setId(entidadRegional.getId());
		entidadForm.setNombre(entidadRegional.getNombreContacto());
		entidadForm.setIdFoto((entidadRegional.getFoto()==null)?null:entidadRegional.getFoto().getId());
	}

	/**
	 * Actualiza la entidad regional
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception {
		
		if (isCancelled(httpservletrequest))
			return (actionmapping.findForward(Tokens.FAILURE));
		EntidadRegionalForm entidadForm = (EntidadRegionalForm) actionform;
		EntidadRegional entidadRegional = EntidadRegionalDAO.findByPrimaryKey(entidadForm.getId());		
		actualizarRegional(entidadRegional, entidadForm);
		httpservletrequest.getSession().removeAttribute("entidadRegionalForm");
		
		return actionmapping.findForward("list");
	}

	/**
	 * 
	 * @param entidadRegional
	 * @param entidadForm
	 * @throws Exception
	 */
	private void actualizarRegional(EntidadRegional entidadRegional, EntidadRegionalForm entidadForm) throws Exception {		
		entidadRegional.setComentario(entidadForm.getComentario());
		entidadRegional.setNombreContacto(entidadForm.getNombre());
		entidadRegional.setEmail(entidadForm.getEmail());
		if (entidadForm.getFileUpload() != null && entidadForm.getFileUpload().getFileData().length != 0) {
			if (entidadRegional.getFoto() != null)
				FotoDAO.deleteFoto(entidadRegional.getFoto());
			Foto newFoto = FotoDAO.create(entidadForm.getFileUpload().getFileData());		
			entidadRegional.setFoto(newFoto);
		}
		EntidadRegionalDAO.updateEntidadRegional(entidadRegional);		
	}	
	
	/*public ActionForward agregarUbicacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("Vamos a la pagina de agregar ubicacion a la entidad regional");
		return mapping.findForward("initAddUbicacion");		
	}*/
	
}
