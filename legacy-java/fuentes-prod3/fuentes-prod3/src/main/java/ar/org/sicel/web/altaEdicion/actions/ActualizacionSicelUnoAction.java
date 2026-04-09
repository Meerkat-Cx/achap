package ar.org.sicel.web.altaEdicion.actions;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Especie;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.TipoRegistroDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.web.altaEdicion.forms.ActualizacionSicelUnoForm;

public class ActualizacionSicelUnoAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("razas", RazaDAO.findAll());
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		actForm.reset();
		return mapping.findForward("init");
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		Long nroReg = new Long(actForm.getNroRegistro());
		if (nroReg > Animal.MAYOR_RC_SICEL1) {
			request.setAttribute("razas", RazaDAO.findAll());
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoSicelUno", new ActionMessage("animal_no_sicel_uno"));
			saveMessages(request.getSession(), msgs);
			return init(mapping, form, request, response);
		}
		Animal animal = AnimalDAO.findByRegistry("RC",
				actForm.getNroRegistro(), actForm.getRaza(), actForm.getSexo());
		if (animal == null) {
			request.setAttribute("razas", RazaDAO.findAll());
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request.getSession(), msgs);
			return init(mapping, form, request, response);
		}
		Set listEventos = animal.getAllEventos();
		if (!listEventos.isEmpty()) {
			boolean seguir = true;
			Iterator it = listEventos.iterator();
			while (seguir && it.hasNext())
				if (!((Evento) it.next()).getNombreTipo().equals(
						Evento.EVT_TIPO_LMI))
					seguir = false;
			if (!seguir) {
				// NO DEBE TENER EVENTOS. SI LOS TIENE, DEBEN SER DE LACTANCIA
				// MIGRADA
				request.setAttribute("razas", RazaDAO.findAll());
				ActionMessages msgs = new ActionMessages();
				msgs.add("eventosAnimalSicel1", new ActionMessage(
						"eventos_animal_sicel1"));
				saveMessages(request.getSession(), msgs);
				return init(mapping, form, request, response);
			}
		}
		if (animal.getMadreGenetica()!=null && animal.getPadre()==null){
			actForm.setActivoRazaMadre("si");
			actForm.setUsarRazaMadre("no");
		}
		else{
			actForm.setActivoRazaMadre("no");
			actForm.setUsarRazaMadre("no");
			request.setAttribute("razas", RazaDAO.findAll());
		}
		if (animal.getMadreGenetica() == null){
			actForm.setMostrarCombo("si");
			request.setAttribute("razas", RazaDAO.findAll());
		}
		else
			actForm.setMostrarCombo("no");
		actForm.setCategoriaAnimal(animal.getCategoria());
		actForm.setNombre(animal.getNombre());
		actForm.setNroRegistro(animal.getRegOrigen().getNumero());
		actForm.setRaza(animal.getRaza().getId());
		actForm.setRegOri(animal.getRegOrigen().getNumero());
		actForm.setRegOriMadreActual(animal.getMadreGenetica() != null ? animal
				.getMadreGenetica().getRegOrigen().getNumero() : actForm
				.getRegOriMadre());
		actForm.setRegOriPadreActual(animal.getPadre() != null ? animal.getPadre()
				.getRegOrigen().getNumero() : actForm.getRegOriPadre());
		actForm.setRp(animal.getRP());
		actForm.setRpMadreActual(animal.getMadreGenetica() != null ? (animal
				.getMadreGenetica().getRP() != null ? animal
						.getMadreGenetica().getRP() : "") : actForm.getRpMadre());
		actForm.setRpPadreActual(animal.getPadre() != null ? (animal.getPadre()
				.getRP() != null ? animal.getPadre()
						.getRP() : "") : actForm.getRpPadre());
		actForm.setTipoRegOriMadreActual(animal.getMadreGenetica() != null ? animal
				.getMadreGenetica().getRegOrigen().getTipoRegistro().getId()
				: actForm.getTipoRegOriMadre());
		actForm.setTipoRegOriPadreActual(animal.getPadre() != null ? animal
				.getPadre().getRegOrigen().getTipoRegistro().getId() : actForm
				.getTipoRegOriPadre());
		actForm.setRazaMadre(animal.getMadreGenetica() != null ? animal
				.getMadreGenetica().getRaza().getId() : actForm
				.getRazaMadre());
		actForm.setRazaPadre(animal.getPadre() != null ? animal
				.getPadre().getRaza().getId() : actForm
				.getRazaPadre());
		actForm.setFechaNacMadreActual(animal.getMadreGenetica() != null && animal.getMadreGenetica().getFechaNac() != null ? animal.getMadreGenetica().getFechaNacFormateada() : actForm.getFechaNacMadre());
		actForm.setFechaNacPadreActual(animal.getPadre() != null && animal.getPadre().getFechaNac() != null ? animal.getPadre().getFechaNacFormateada() : actForm.getFechaNacPadre());
		actForm.setRazaActual(animal.getRaza().getId());
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}

	public ActionForward cambiarMadre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("razas", RazaDAO.findAll());
		request.setAttribute("tiposRegistros", TipoRegistroDAO.findAll());
		return mapping.findForward("cambiarMadre");
	}

	public ActionForward asignarMadre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		Animal madre = AnimalDAO.findByRegistry(actForm.getTipoRegOriMadre(),
				actForm.getRegOriMadre(), actForm.getRazaMadre(), "H");
		if (madre == null) {
			request.setAttribute("razas", RazaDAO.findAll());
			request.setAttribute("tiposRegistros", TipoRegistroDAO.findAll());
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request.getSession(), msgs);
			return mapping.findForward("cambiarMadre");
		}
		Animal hijo = AnimalDAO.findByRegistry("RC", actForm.getNroRegistro(),
				actForm.getRaza(), actForm.getSexo());
		if (madre.getFechaNac() != null && hijo.getFechaNac() != null && !madre.getFechaNac().before(hijo.getFechaNac())) {
			request.setAttribute("razas", RazaDAO.findAll());
			request.setAttribute("tiposRegistros", TipoRegistroDAO.findAll());
			ActionMessages msgs = new ActionMessages();
			msgs.add("fechaErronea", new ActionMessage("fecha_madre_hijo"));
			saveMessages(request.getSession(), msgs);
			return mapping.findForward("cambiarMadre");
		}
		actForm.setRazaMadre(madre.getRaza().getId());
		actForm.setRpMadre(madre.getRP() != null ? madre.getRP() : "");
		actForm.setRegOriMadre(madre.getRegOrigen().getNumero());
		actForm.setTipoRegOriMadre(madre.getRegOrigen().getTipoRegistro()
				.getId());
		actForm.setFechaNacMadre(madre.getFechaNac() != null ? madre.getFechaNacFormateada() : "");
		if (actForm.getRegOriPadre().equals("") && actForm.getRegOriPadreActual().equals(""))
			actForm.setActivoRazaMadre("si");
		else
			actForm.setActivoRazaMadre("no");
		actForm.setUsarRazaMadre("no");
		actForm.setMostrarCombo("no");
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}

	public ActionForward cambiarPadre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("razas", RazaDAO.findAll());
		request.setAttribute("tiposRegistros", TipoRegistroDAO.findAll());
		return mapping.findForward("cambiarPadre");
	}

	public ActionForward asignarPadre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		Animal padre = AnimalDAO.findByRegistry(actForm.getTipoRegOriPadre(),
				actForm.getRegOriPadre(), actForm.getRazaPadre(), "M");
		if (padre == null) {
			request.setAttribute("razas", RazaDAO.findAll());
			request.setAttribute("tiposRegistros", TipoRegistroDAO.findAll());
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request.getSession(), msgs);
			return mapping.findForward("cambiarPadre");
		}
		Animal hijo = AnimalDAO.findByRegistry("RC", actForm.getNroRegistro(),
				actForm.getRaza(), actForm.getSexo());
		if (padre.getFechaNac() != null && hijo.getFechaNac() != null && !padre.getFechaNac().before(hijo.getFechaNac())) {
			request.setAttribute("razas", RazaDAO.findAll());
			request.setAttribute("tiposRegistros", TipoRegistroDAO.findAll());
			ActionMessages msgs = new ActionMessages();
			msgs.add("fechaErronea", new ActionMessage("fecha_padre_hijo"));
			saveMessages(request.getSession(), msgs);
			return mapping.findForward("cambiarPadre");
		}
		actForm.setRazaPadre(padre.getRaza().getId());
		actForm.setRpPadre(padre.getRP() != null ? padre.getRP() : "");
		actForm.setRegOriPadre(padre.getRegOrigen().getNumero());
		actForm.setTipoRegOriPadre(padre.getRegOrigen().getTipoRegistro()
				.getId());
		actForm.setFechaNacPadre(padre.getFechaNac() != null ? padre.getFechaNacFormateada() : "");
		actForm.setActivoRazaMadre("no");
		actForm.setUsarRazaMadre("no");
		if (actForm.getRegOriMadre().equals("") && actForm.getRegOriMadreActual().equals("")){
			actForm.setMostrarCombo("si");
			request.setAttribute("razas", RazaDAO.findAll());
		}
		else
			actForm.setMostrarCombo("no");
		request.setAttribute("razas", RazaDAO.findAll());
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}

	public ActionForward volverCambioMadre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		Animal madre = AnimalDAO.findByRegistry(actForm.getTipoRegOriMadre(),
				actForm.getRegOriMadre(), actForm.getRazaMadre(), "H");
		if (madre == null){
			actForm.setRpMadre("");
			actForm.setRegOriMadre("");
			actForm.setTipoRegOriMadre("");
			actForm.setRazaMadre("");
			actForm.setFechaNacMadre("");
		}
		if (actForm.getActivoRazaMadre().equals("no"));
			request.setAttribute("razas", RazaDAO.findAll());
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}
	
	public ActionForward volverCambioPadre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		Animal padre = AnimalDAO.findByRegistry(actForm.getTipoRegOriPadre(),
				actForm.getRegOriPadre(), actForm.getRazaPadre(), "M");
		if (padre == null){
			actForm.setRpPadre("");
			actForm.setRegOriPadre("");
			actForm.setTipoRegOriPadre("");
			actForm.setRazaPadre("");
			actForm.setFechaNacPadre("");
		}
		if (actForm.getActivoRazaMadre().equals("no"));
			request.setAttribute("razas", RazaDAO.findAll());
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}

	public ActionForward actualizarDatos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ExcepcionIntegridad {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		if (actForm.getBorrarMadre().equals("si"))
			this.eliminarMadre(mapping, form, request, response);
		if (actForm.getBorrarPadre().equals("si"))
			this.eliminarPadre(mapping, form, request, response);
		Animal madre = AnimalDAO.findByRegistry(actForm.getTipoRegOriMadre(),
				actForm.getRegOriMadre(), actForm.getRazaMadre(), "H");
		Animal padre = AnimalDAO.findByRegistry(actForm.getTipoRegOriPadre(),
				actForm.getRegOriPadre(), actForm.getRazaPadre(), "M");
		String categoria = actForm.getCategoriaAnimal();
		Animal animalSicelUno = AnimalDAO.findByRegistry("RC", actForm
				.getNroRegistro(), actForm.getRaza(), actForm.getSexo());
		Animal madreActual = animalSicelUno.getMadreGenetica();
		Animal padreActual = animalSicelUno.getPadre();
		if ((padre != null && (padreActual == null || !padreActual.getId().equals(padre.getId()))) ||
		   (madre != null && (madreActual == null || !madreActual.getId().equals(madre.getId())))){
			Especie especie = null;
			if (animalSicelUno.getRaza().getEspecie() != null)  
				especie = animalSicelUno.getEspecie();
			else
				if (madre != null)	
					especie = madre.getEspecie();
				else{
					if (padre != null)
						especie = padre.getEspecie();
					else
						especie = (RazaDAO.findByPrimaryKey(actForm.getRazaActual())).getEspecie();
				}
			animalSicelUno.inicializarPadresGen(especie,madre != null ? (Hembra)madre : animalSicelUno.getMadreGenetica(),padre != null ? (Macho)padre : animalSicelUno.getPadre());
		}
		if (animalSicelUno.getMadreGenetica() != null && animalSicelUno.getPadre() == null)
			actForm.setActivoRazaMadre("si");
		else
			actForm.setActivoRazaMadre("no");
		if (animalSicelUno.getMadreGenetica() == null){
			actForm.setMostrarCombo("si");
			request.setAttribute("razas", RazaDAO.findAll());
		}
		else
			actForm.setMostrarCombo("no");
		if(actForm.getActivoRazaMadre().equals("si") && actForm.getUsarRazaMadre().equals("si"))
			animalSicelUno.getComposicionRacial().setRazaDeclarada(animalSicelUno.getMadreGenetica().getRaza());
		else
			 animalSicelUno.getComposicionRacial().setRazaDeclarada(RazaDAO.findByPrimaryKey(actForm.getRazaActual()));
		animalSicelUno.setCategoria(categoria);
		AnimalDAO.save(animalSicelUno);
		actForm.setCategoriaAnimal(animalSicelUno.getCategoria());
		actForm.setNombre(animalSicelUno.getNombre());
		actForm.setNroRegistro(animalSicelUno.getRegOrigen().getNumero());
		actForm.setRaza(animalSicelUno.getRaza().getId());
		actForm.setRazaActual(animalSicelUno.getRaza().getId());
		actForm.setRegOri(animalSicelUno.getRegOrigen().getNumero());
		actForm.setRegOriMadre("");
		actForm.setRegOriMadreActual(animalSicelUno.getMadreGenetica() != null ? animalSicelUno
				.getMadreGenetica().getRegOrigen().getNumero() : actForm
				.getRegOriMadre());
		actForm.setRegOriPadre("");
		actForm.setRegOriPadreActual(animalSicelUno.getPadre() != null ? animalSicelUno.getPadre()
				.getRegOrigen().getNumero() : actForm.getRegOriPadre());
		actForm.setRp(animalSicelUno.getRP());
		actForm.setRpMadre("");
		actForm.setRpMadreActual(animalSicelUno.getMadreGenetica() != null ? (animalSicelUno
				.getMadreGenetica().getRP() != null ? animalSicelUno
						.getMadreGenetica().getRP() : "") : actForm.getRpMadre());
		actForm.setRpPadre("");
		actForm.setRpPadreActual(animalSicelUno.getPadre() != null ? (animalSicelUno.getPadre()
				.getRP() != null ? animalSicelUno.getPadre()
						.getRP() : "") : actForm.getRpPadre());
		actForm.setTipoRegOriMadre("");
		actForm.setTipoRegOriMadreActual(animalSicelUno.getMadreGenetica() != null ? animalSicelUno
				.getMadreGenetica().getRegOrigen().getTipoRegistro().getId()
				: actForm.getTipoRegOriMadre());
		actForm.setTipoRegOriPadre("");
		actForm.setTipoRegOriPadreActual(animalSicelUno.getPadre() != null ? animalSicelUno
				.getPadre().getRegOrigen().getTipoRegistro().getId() : actForm
				.getTipoRegOriPadre());
		actForm.setRazaMadre(animalSicelUno.getMadreGenetica() != null ? animalSicelUno.getMadreGenetica().getRaza().getId() : "");
		actForm.setRazaPadre(animalSicelUno.getPadre() != null ? animalSicelUno.getPadre().getRaza().getId() : "");
		actForm.setFechaNacMadre("");
		actForm.setFechaNacPadre("");
		actForm.setFechaNacMadreActual(animalSicelUno.getMadreGenetica() != null && animalSicelUno.getMadreGenetica().getFechaNac() != null ? animalSicelUno.getMadreGenetica().getFechaNacFormateada() : actForm.getFechaNacMadre());
		actForm.setFechaNacPadreActual(animalSicelUno.getPadre() != null && animalSicelUno.getPadre().getFechaNac() != null ? animalSicelUno.getPadre().getFechaNacFormateada() : actForm.getFechaNacPadre());
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}
	
	public ActionForward eliminarPadre(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ExcepcionIntegridad {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		Animal animalSicelUno = AnimalDAO.findByRegistry("RC", actForm
				.getNroRegistro(), actForm.getRaza(), actForm.getSexo());
		animalSicelUno.setPadre(null);
		actForm.setRpPadreActual("");
		actForm.setRpPadre("");
		actForm.setRegOriPadreActual("");
		actForm.setRegOriPadre("");
		actForm.setTipoRegOriPadreActual("");
		actForm.setTipoRegOriPadre("");
		actForm.setFechaNacPadreActual("");
		actForm.setFechaNacPadre("");
		actForm.setRazaPadre("");
		if (!actForm.getRegOriMadre().equals("") || !actForm.getRegOriMadreActual().equals(""))
			actForm.setActivoRazaMadre("si");
		else
			actForm.setActivoRazaMadre("no");
		actForm.setUsarRazaMadre("no");
		if (actForm.getRegOriMadre().equals("") && actForm.getRegOriMadreActual().equals("")){
			actForm.setMostrarCombo("si");
			request.setAttribute("razas", RazaDAO.findAll());
		}
		else
			actForm.setMostrarCombo("no");
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}
	
	public ActionForward eliminarMadre(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ExcepcionIntegridad {
		ActualizacionSicelUnoForm actForm = (ActualizacionSicelUnoForm) form;
		Animal animalSicelUno = AnimalDAO.findByRegistry("RC", actForm
				.getNroRegistro(), actForm.getRaza(), actForm.getSexo());
		animalSicelUno.setMadreGenetica(null);
		actForm.setRpMadreActual("");
		actForm.setRpMadre("");
		actForm.setRegOriMadreActual("");
		actForm.setRegOriMadre("");
		actForm.setTipoRegOriMadreActual("");
		actForm.setTipoRegOriMadre("");
		actForm.setFechaNacMadreActual("");
		actForm.setFechaNacMadre("");
		actForm.setRazaMadre("");
		actForm.setActivoRazaMadre("no");
		actForm.setUsarRazaMadre("no");
		actForm.setMostrarCombo("si");
		request.setAttribute("razas", RazaDAO.findAll());
		actForm.setRazaActual(animalSicelUno.getRaza().getId());
		actForm.setRaza(animalSicelUno.getRaza().getId());
		actForm.setBorrarMadre("no");
		actForm.setBorrarPadre("no");
		return mapping.findForward("mostrarDatos");
	}

}
