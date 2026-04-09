package ar.org.sicel.web.altaEdicion.actions;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Contacto;
import ar.org.sicel.persistence.ContactoDAO;
import ar.org.sicel.persistence.Ubicacion;
import ar.org.sicel.persistence.UbicacionDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.UbicacionForm;

/**
 * 
 * @author 
 *
 */
public class EdicionUbicacionAction extends DispatchAction {

	public EdicionUbicacionAction() {
	}
	
	/**
	 * Metodo para el listado de las ubicaciones de un contacto
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listarUbicaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {		
		//String idContacto = request.getParameter("idContacto");
		UbicacionForm ubicacionForm = (UbicacionForm) form;
		//ubicacionForm.setContactoId(Long.parseLong(idContacto));
		Long idContacto = ubicacionForm.getContactoId();
		
		Contacto contacto = ContactoDAO.findByPrimaryKey(idContacto);
		request.setAttribute("idContacto", idContacto);
		request.setAttribute("listaUbicaciones", contacto.getUbicacions());
		
		String actionBack = request.getParameter("actionBack");
		
		request.setAttribute("actionBack",actionBack);
		return mapping.findForward("listarUbicaciones");
	}
	
	/**
	 * Metodo para la iniciacion del alta de una ubicacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initAddUbicacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		String idContacto = request.getParameter("idContacto");		
		UbicacionForm uForm = (UbicacionForm) form;
		uForm.setContactoId(Long.parseLong(idContacto));
		
		System.out.println("EdicionUbicacionAction init add ubicacion del contacto "+uForm.getContactoId());
		
		request.setAttribute("metodo", "addUbicacion");
		return mapping.findForward("initAdd");
	}
	
	/**
	 * Metodo para el alta de una ubicacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addUbicacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {

		UbicacionForm uform = (UbicacionForm) form;
		Long idContacto = uform.getContactoId();
		
		request.setAttribute("idContacto", idContacto);
		
		Contacto contacto = ContactoDAO.findByPrimaryKey(idContacto);
		
		request.setAttribute("actionBack",uform.getActionBack());
		
		if (isCancelled(request)) {
			request.setAttribute("listaUbicaciones", contacto.getUbicacions());
			return (mapping.findForward("listarUbicaciones"));
		}

		Ubicacion ubicacionNew = UbicacionDAO.create(uform.getNombre(), uform.getCiudad(), uform.getProvinciaRegion(), 
													 uform.getPais(), uform.getDireccion(), uform.getCodigoPostal(), 
													 uform.getMail(), uform.getTelefono());
		
		
		ubicacionNew.setContacto(contacto);		
		//Set ubicaciones = (Set) httpservletrequest.getSession().getAttribute("ubicaciones");
		contacto.getUbicacions().add(ubicacionNew);
		ContactoDAO.updateContacto(contacto);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		request.setAttribute("listaUbicaciones", contacto.getUbicacions());
		return mapping.findForward("success");
	}
	
	/**
	 * Metodo para la inicializacion de la actualizacion de una ubicacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initUpdateUbicacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		request.setAttribute("metodo", "updateUbicacion");
		String idContacto = request.getParameter("idContacto");
		String idUbicacion = request.getParameter("idUbicacion");
		//request.setAttribute("idContacto", idContacto);
		
		System.out.println("actualizando Contacto "+idContacto + " ubicacion "+idUbicacion);
		UbicacionForm uForm = (UbicacionForm) form;
		uForm.setContactoId(Long.parseLong(idContacto));		
		
		Ubicacion ubicacion = UbicacionDAO.findByPrimaryKey(Long.parseLong(idUbicacion));		
		copiarPropiedadesAlForm(ubicacion, uForm);		
		
		return mapping.findForward("initAdd");
	}

	/**
	 * Copia los atributos de una ubicacion al formulario
	 * @param ubicacion
	 * @param form
	 */
	private void copiarPropiedadesAlForm(Ubicacion ubicacion, UbicacionForm form) {
		form.setCiudad(ubicacion.getCiudad());
		form.setCodigoPostal(ubicacion.getCodigoPostal());
		form.setDireccion(ubicacion.getDireccion());
		form.setMail(ubicacion.getMail());
		form.setNombre(ubicacion.getNombre());
		form.setPais(ubicacion.getPais());
		form.setProvinciaRegion(ubicacion.getProvinciaRegion());
		form.setTelefono(ubicacion.getTelefono());
		form.setId(ubicacion.getId());		
	}
	
	/**
	 * Metodo para la actualizacion de una ubicacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUbicacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		UbicacionForm uForm = (UbicacionForm) form; 
		request.setAttribute("idContacto", uForm.getContactoId());
		Contacto contacto = ContactoDAO.findByPrimaryKey(uForm.getContactoId());
		request.setAttribute("listaUbicaciones", contacto.getUbicacions());
		request.setAttribute("actionBack",uForm.getActionBack());
		
		if (isCancelled(request))
			return (mapping.findForward("listarUbicaciones"));
		
		System.out.println("Ubicacion " + uForm.getId());
		Ubicacion ubicacion = UbicacionDAO.findByPrimaryKey(uForm.getId());		
		copiarPropiedadesALaUbicacion(ubicacion, uForm);
		UbicacionDAO.updateUbicacion(ubicacion);
		return mapping.findForward("success"); // se re dirige  a la lista de ubicaciones
	}

	/**
	 * Copia los datos de un formulario a la entidad ubicacion
	 * @param ubicacion
	 * @param form
	 */
	private void copiarPropiedadesALaUbicacion(Ubicacion ubicacion, UbicacionForm form) {
		ubicacion.setCiudad(form.getCiudad());
		ubicacion.setCodigoPostal(form.getCodigoPostal());
		ubicacion.setDireccion(form.getDireccion());
		ubicacion.setMail(form.getMail());
		ubicacion.setNombre(form.getNombre());
		ubicacion.setPais(form.getPais());
		ubicacion.setProvinciaRegion(form.getProvinciaRegion());
		ubicacion.setTelefono(form.getTelefono());		
	}



}
