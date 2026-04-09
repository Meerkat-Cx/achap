package ar.org.sicel.web.proc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import ar.org.sicel.persistence.AnimalSinonimo;
import ar.org.sicel.persistence.AnimalSinonimoDAO;
import ar.org.sicel.persistence.Calificacion;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventosPorFechaYTipo;
import ar.org.sicel.persistence.EvtAlta;
import ar.org.sicel.persistence.EvtAltaDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtCria;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Lactancia;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ProcAnimal;
import ar.org.sicel.persistence.ProcAnimalDAO;
import ar.org.sicel.persistence.ProcCodMsg;
import ar.org.sicel.persistence.ProcCodMsgDAO;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Registro;
import ar.org.sicel.persistence.RegistroDAO;
import ar.org.sicel.persistence.TipoRegistroDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * Action cuya funcionalidad es el chequeo y union de dos animales
 * 
 * Pasos:
 * Realiza los chequeos para corroborar que ambos animales puedan unirse (Lo realiza en la union y en el chequeo)
 * Crea los dos animales sinonimos, para historial, con los datos de los originales
 * Crea el nuevo animal y lo comienza a cargar con los datos seleccionados por pantalla.
 * Le setea los registros para poder operar, pero solo momentaneamente
 * Carga los eventos de ambos animales, chequeando y generando las lactancias correspondientes
 * Luego los hijos de ambos y las calificaciones
 * Actualiza los eventos cargados con el nuevo animal 
 * Agrega el evento alta, en caso que lo hubiera, del animal cuyo regOrig fue seleccionado y elimina el evento de alta del otro animal,
 * en caso que lo tuviera tambien
 * Luego se asocia el animal a la cria de los padres seleccionados en el form y se elimina
 * la cria de los padres del otro animal(en caso que tuviera), sólo la cria no la reproduccion
 * Luego crea realmente los registros  para el nuevo animal, si regOri y RegIdent son iguales se crea uno solo, sino los dos
 * Se crean en base a los registros seleccionados en el form. Los registros viejos se eliminan
 * Luego se actualizan los animales sinonimos, cargandoles el nuevo animal, para saber en cual quedo la union y a dicho animal se le setea 
 * la variable "sinonimo" true que denota que proviene de una union.
 * Finalmente se eliminan los dos animales ingresados
 * 
 * 
 * @author jdivars y rrodriguez
 * 
 */

public class RealizarUnionSinonimosAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SinonimosForm sForm = (SinonimosForm) form;
		sForm.reset();
		List razas = RazaDAO.findAll();
//		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		List tipos = TipoRegistroDAO.findAll();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("mostrarUnir", false);
		request.setAttribute("mostrarChequear", false);
		return mapping.findForward("init");
	}

	/**
	 * Metodo cuya funcion es recuperar el animal, cargado en la pantalla por el id, y mostrar todos sus datos en el form
	 * Lo mismo con el segundo animal
	 * En caso que alguno de los animales no exista informa en la misma pantalla dicha situacion 
	 * 
	 */
	public ActionForward buscarAnimalPorId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SinonimosForm sForm = (SinonimosForm) form;
		Long idAn = null;
		if (sForm.getAnimalId() != null)
			idAn = Long.parseLong((String) sForm.getAnimalId());
		Animal animal = AnimalDAO.findByPrimaryKey(idAn);
		if (animal == null) {
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request.getSession(), msgs);
			List razas = RazaDAO.findAll();
			List tipos = TipoRegistroDAO.findAll();
			request.setAttribute("tipos",tipos);
			request.setAttribute("razas",razas);
			request.setAttribute("mostrarUnir", false);
			request.setAttribute("mostrarChequear", false);
			if(sForm.getAnimalSelec().equalsIgnoreCase("1")){
				sForm.setIdAnimal1("");
				sForm.setAnimal1(null);
			}
			else{
				sForm.setIdAnimal2("");
				sForm.setAnimal2(null);
			}
			return mapping.findForward("init");
		}
		if(sForm.getAnimalSelec().equalsIgnoreCase("1")){
			sForm.setIdAnimal1(idAn.toString());
			sForm.setAnimal1(animal);
			if (sForm.getAnimal2() != null) {
				Animal anima = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm
						.getIdAnimal2()));
				sForm.setIdAnimal2(anima.getId().toString());
				sForm.setAnimal2(anima);
			}
		}
		else{
			sForm.setIdAnimal2(idAn.toString());
			sForm.setAnimal2(animal);
			if (sForm.getAnimal1() != null) {
				Animal anima = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm
						.getIdAnimal1()));
				sForm.setIdAnimal1(anima.getId().toString());
				sForm.setAnimal1(anima);
			}
		}
		List razas = RazaDAO.findAll();
//		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		List tipos = TipoRegistroDAO.findAll();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("mostrarUnir", false);
		request.setAttribute("mostrarChequear", false);
		if(sForm.getAnimal1()!=null && sForm.getAnimal2()!= null)
			request.setAttribute("mostrarChequear", true);
		return (mapping.findForward("init"));
	}
	
	public ActionForward buscarAnimalPorRegistro(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)throws Exception {
		SinonimosForm form = (SinonimosForm)formm;
		String tipo = form.getTipoReg();
		String numero = form.getNumReg();
		String raza = form.getRaza();
		String sexo = form.getSexo();
		Animal anim = (Animal) AnimalDAO.findByRegistry(tipo.toUpperCase(),numero,raza.toUpperCase(),sexo.toUpperCase()); 
			//if(anim!=null && anim.getCategoria().equals(Animal.CAT_PED)){	
		if (anim == null) {
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request.getSession(), msgs);
			List razas = RazaDAO.findAll();
			List tipos = TipoRegistroDAO.findAll();
			request.setAttribute("tipos",tipos);
			request.setAttribute("razas",razas);
			request.setAttribute("mostrarUnir", false);
			request.setAttribute("mostrarChequear", false);
			if(form.getAnimalSelec().equalsIgnoreCase("1")){
				form.setIdAnimal1("");
				form.setAnimal1(null);
			}
			else{
				form.setIdAnimal2("");
				form.setAnimal2(null);
			}
			return mapping.findForward("init");
		}
		if(anim!=null){
				if(form.getAnimalSelec().equalsIgnoreCase("1")){
					form.setIdAnimal1(anim.getId().toString());
					form.setAnimal1(anim);
				}
				else{
					form.setIdAnimal2(anim.getId().toString());
					form.setAnimal2(anim);
				}
		}
		List razas = RazaDAO.findAll();
//		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		List tipos = TipoRegistroDAO.findAll();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("mostrarUnir", false);
		request.setAttribute("mostrarChequear", false);
		if(form.getAnimal1()!=null && form.getAnimal2()!= null)
			request.setAttribute("mostrarChequear", true);
		return (mapping.findForward("init"));
	}

	/**
	 * Metodo que se utiliza para chequear si los dos animales son compatibles para ser sinonimos
	 */
	public ActionForward chequear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		SinonimosForm sForm = (SinonimosForm) form;
		Animal ani2 = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm.getIdAnimal2()));
		Animal ani1 = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm.getIdAnimal1()));
		String error = this.chequearSinonimos(ani1, ani2, false, sForm);
		if (!error.equals("")) {
			ActionMessages msgs = new ActionMessages();
			msgs.add("error", new ActionMessage("error", error));
			saveMessages(request.getSession(), msgs);
			List razas = RazaDAO.findAll();
			List tipos = TipoRegistroDAO.findAll();
			request.setAttribute("tipos",tipos);
			request.setAttribute("razas",razas);
			request.setAttribute("mostrarUnir", false);
			request.setAttribute("mostrarChequear", true);
			return mapping.findForward("init");
		}
		request.setAttribute("mensaje", "El chequeo ha resultado exitoso, por favor verifique los datos antes de realizar la unión");
		List razas = RazaDAO.findAll();
		List tipos = TipoRegistroDAO.findAll();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("mostrarUnir", true);
		request.setAttribute("mostrarChequear", false);
		return (mapping.findForward("init"));
	}
	
	/**
	 * Metodo que es llamado para realizar a union de los sinonimos.
	 * Tanto éste metodo como el anterior invocan a "chequearSinonimos" por lo cual si se ingreso
	 * por aca sin antes haber chequeado, igualmente realiza el chequeo
	 */
	public ActionForward unir(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		SinonimosForm sForm = (SinonimosForm) form;
		Animal ani2 = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm.getIdAnimal2()));
		Animal ani1 = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm.getIdAnimal1()));
		String error = this.chequearSinonimos(ani1, ani2, true, sForm);
		if (!error.equals("")) {
			ActionMessages msgs = new ActionMessages();
			msgs.add("error", new ActionMessage("error", error));
			saveMessages(request.getSession(), msgs);
			List razas = RazaDAO.findAll();
			List tipos = TipoRegistroDAO.findAll();
			request.setAttribute("tipos",tipos);
			request.setAttribute("razas",razas);
			request.setAttribute("mostrarUnir", true);
			request.setAttribute("mostrarChequear", false);
			return mapping.findForward("init");
		}
		HibernateFactory.getSession().flush();
		HibernateFactory.getSession().clear();
		//limpio la sesion y los vuelvo a levantar sino mantiene las asociaciones y elimina los eventos relacionados
		ani2 = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm.getIdAnimal2()));
		ani1 = AnimalDAO.findByPrimaryKey(Long.parseLong(sForm.getIdAnimal1()));
		HibernateFactory.getSession().delete(ani1);
		HibernateFactory.getSession().delete(ani2);
		request.setAttribute("mensajeOK", "Fue exitosa la union de los animales " + sForm.getIdAnimal1()+ " y " + sForm.getIdAnimal2());
		sForm.reset();
		sForm.setIdAnimal1(ani1.getId().toString());
		sForm.setIdAnimal2(ani2.getId().toString());
		request.setAttribute("mostrarUnir", false);
		request.setAttribute("mostrarChequear", false);
		List razas = RazaDAO.findAll();
//		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		List tipos = TipoRegistroDAO.findAll();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		return (mapping.findForward("init"));
	}

	/**
	 * 
	 * @param animal1
	 * @param animal2
	 * @param union
	 * @param form
	 * @return
	 * Metodo que realiza tanto los chequeos como la union de dos animales transformandolos en uno solo(sinonimo)
	 * 
	 */
	private String chequearSinonimos(Animal animal1, Animal animal2, boolean union, SinonimosForm form) {
		String se = animal1.esHembra() ? "Hembra" : "Macho";
		String se2 = animal2.esHembra() ? "Hembra" : "Macho";
		String msge = "";
		 
		//CHEQUEOS
			if (animal1 == null || animal2 == null) {
				msge = "[para poder chequear deben estar cargados los dos animales]";
				return msge;
			}

			if (animal1.esHembra() != animal2.esHembra()) {
				msge = "[los dos animales deben ser del mismo sexo, animal 1 es "+ se + ", animal 2 es " + se2 + "]";
				return msge;
			}
	
			if (!animal1.getRaza().equals(animal2.getRaza())) {
				msge = "[los dos animales deben ser de la misma raza, la raza del animal 1 es "+ animal1.getRaza().getNombre()+ "y la del animal 2 es "
						+ animal2.getRaza().getNombre() + "]";
				return msge;
			}
	

			//Para verificacion de que no haya crias iguales en ambos animales
			List hijos1 = new ArrayList(); 
			List hijos2 = new ArrayList(); 
			if (animal1.esHembra()) {
				Hembra h1 = (Hembra) animal1;
				Hembra h2 = (Hembra) animal2;
				hijos1.addAll(h1.getHijosGeneticos());
				hijos1.addAll(h1.getHijosParto());
				hijos2.addAll(h2.getHijosGeneticos());
				hijos2.addAll(h2.getHijosParto());

			} else {
				Macho m = (Macho) animal1;
				hijos1.addAll(m.getHijosGeneticos());
				Macho m2 = (Macho) animal2;
				hijos2.addAll(m2.getHijosGeneticos());
			}
			Iterator it1= hijos1.iterator();
			while(it1.hasNext())
			{
				Animal c1 = (Animal) it1.next();
				Animal c2 = contieneAnimal(hijos2, c1); 
				if(c2 != null)
				{
					msge = "[Existen dos crias iguales en ambos animales, el id de la cria 1 es "+ c1.getId()+ " y de la cria 2 es "
							+ c2.getId()+ "]";
					return msge;
				}
			}
		
		if (union) {
			Animal ani = null;
			//Animales sinonimos (los dos ingresados se cargan en ANIMAL_SINONIMO) para historial
			AnimalSinonimo sinonimo1 = cargarAnimalSinonimo(animal1);
			AnimalSinonimo sinonimo2 = cargarAnimalSinonimo(animal2);
			HibernateFactory.getSession().save(sinonimo1);
			HibernateFactory.getSession().save(sinonimo2);
	
			//Empieza a cargar el nuevo animal
			if (animal1.esHembra())
				ani = new Hembra();
			else
				ani = new Macho();
	
			ani.setFechaNac(null);
		/*	if (animal1.getFechaNac() != null || animal2.getFechaNac() != null) {
				if (animal1.getFechaNac() != null)
					ani.setFechaNac(animal1.getFechaNac());
				else if (animal2.getFechaNac() != null)
					ani.setFechaNac(animal2.getFechaNac());
			} else
				ani.setFechaNac(animal1.getFechaNac().before(animal2.getFechaNac()) ? animal1.getFechaNac(): animal2.getFechaNac());*/
			
			if (form.getFnac().equals("1")) 
				ani.setFechaNac(animal1.getFechaNac());
			else
				ani.setFechaNac(animal2.getFechaNac());
	
			ani.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
			Macho padre = null;
			Hembra madre = null;
	
			//Levanta datos ingresados por pantalla para ser seteados al nuevo animal
			Establecimiento tambo = null;
			if (!form.getTambo().equals(""))
				tambo = EstablecimientoDAO.findByPrimaryKey(Long.parseLong(form.getTambo()));
			Establecimiento tamboCri = null;
			if (!form.getTamboCri().equals(""))
				tamboCri = EstablecimientoDAO.findByPrimaryKey(Long.parseLong(form.getTamboCri()));
			Propietario prop = null;
			if (!form.getProp().equals(""))
				prop = PropietarioDAO.findByPrimaryKey(Long.parseLong(form.getProp()));
			Propietario propCri = null;
			if (!form.getPropCri().equals(""))
				propCri = PropietarioDAO.findByPrimaryKey(Long.parseLong(form.getPropCri()));
			if (form.getPadres().equals("1")) {
				if (form.getAnimal1().getPadre() != null)
					padre = (Macho) AnimalDAO.findByPrimaryKey(animal1.getPadre().getId());
				if (form.getAnimal1().getMadreGenetica() != null)
					madre = (Hembra) AnimalDAO.findByPrimaryKey(animal1.getMadreGenetica().getId());
			}
			if (form.getPadres().equals("2")) {
				if (form.getAnimal2().getPadre() != null)
					padre = (Macho) AnimalDAO.findByPrimaryKey(animal2.getPadre().getId());
				if (form.getAnimal2().getMadreGenetica() != null)
					madre = (Hembra) AnimalDAO.findByPrimaryKey(animal2.getMadreGenetica().getId());
			}
			
			//Carga datos al nuevo animal
			List msg1 = new ArrayList();
			try {
				if (padre == null && madre == null)
					ani = AnimalDAO.createAnimalPedEmpadronarSinonimo(tamboCri,propCri, animal1.getRaza(), tambo, prop, null,animal1.esHembra(),
							msg1, ani.getFechaNac(), form.getRp(), form.getNombre(),animal1.getId(), animal2.getId());
				else {
					ani = AnimalDAO.createAnimalPedPadres(tamboCri, propCri,padre, madre, animal1.getRaza(), tambo, prop, null,animal1.esHembra(),
							msg1, ani.getFechaNac(), form.getRp(), form.getNombre(),animal1.getId(), animal2.getId());
					ani.setMadreParto(madre);
				}
			} catch (ExcepcionIntegridad e) {
				ProcCodMsg cod = null;
				try {
					cod = ProcCodMsgDAO.findByPrimaryKey(e.getCodigoError());
				} catch (Exception e2) {
					msge = e.getCodigoError();
					return msge;
				}
				if (cod == null)
					msge = e.getCodigoError();
				else {
					ProcMsg ms = ProcMsgDAO.create(e.getCodigoError(),
							ProcMsg.ERROR, e.getValores());
					msge = ms.getInformacion();
				}
				return msge;
			}
			ani.setCategoria(form.getCategoria());
			ani.setRpSenasa(form.getRpSenasa());
			ani.setApodo(form.getApodo());
			
			if(form.getAsoc().length()>0)
				ani.setAsoc(Integer.valueOf(form.getAsoc()));
			if(form.getAsom().length()>0)
				ani.setAsom(Integer.valueOf(form.getAsom()));
			if(form.getAsop().length()>0)
				ani.setAsop(Integer.valueOf(form.getAsop()));
			if(form.getDadorSemen().length()>0)
				ani.setDadorSemen(Integer.valueOf(form.getDadorSemen()));
			if(form.getNumeroAnalADN().length()>0)
				ani.setNumeroAnalADN(Integer.valueOf(form.getNumeroAnalADN()));
			ani.setDonante(form.getDonante());
			if(form.getMellizo().length()>0)
				ani.setMellizo(Integer.valueOf(form.getMellizo()));
			ani.setTransferencia(form.getTransferencia());
			if(form.getTipoServicio().length()>0)
				ani.setTipoServicio(Integer.valueOf(form.getTipoServicio()));
			if(form.getCodigoVerificador().length()>0)
				ani.setCodigoVerificador(Integer.valueOf(form.getCodigoVerificador()));

			
			//Carga al nuevo animal los registros (origen e identificados) seleccionados, pero solo momentaneamente
			Registro re1 = null;
			Registro re2 = null;
			Registro re3 = null;
			Registro re4 = null;
			try {
				re1 = RegistroDAO.create(animal1.getRegOrigen().getTipoRegistro().getId(), animal1.getRegOrigen().getNumero());
				re2 = RegistroDAO.create(animal2.getRegOrigen().getTipoRegistro().getId(), animal2.getRegOrigen().getNumero());
				re3 = RegistroDAO.create(animal1.getRegIdentificador().getTipoRegistro().getId(), animal1.getRegIdentificador().getNumero());
				re4 = RegistroDAO.create(animal2.getRegIdentificador().getTipoRegistro().getId(), animal2.getRegIdentificador().getNumero());
			} catch (ExcepcionIntegridad e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (form.getRegOri().equals("1")) {
				ani.setRegistros(new HashSet());
				ani.setRegOrigen(re1);
				ani.addRegistro(re1);
			}
			if (form.getRegOri().equals("2")) {
				ani.setRegistros(new HashSet());
				ani.setRegOrigen(re2);
				ani.addRegistro(re2);
			}
			if (form.getRegId().equals("1")) {
				if (!re3.equals(ani.getRegOrigen())) {
					ani.setRegIdentificador(re3);
					ani.addRegistro(re3);
				} else {
					ani.setRegIdentificador(ani.getRegOrigen());
				}
			}
			if (form.getRegId().equals("2")) {
				if (!re4.equals(ani.getRegOrigen())) {
					ani.setRegIdentificador(re4);
					ani.addRegistro(re4);
				} else {
					ani.setRegIdentificador(ani.getRegOrigen());
				}
			}

			HibernateFactory.getSession().save(ani);
			
			//Carga los eventos de ambos animales, chequeando y generando las lactancias correspondientes
			List evts = new ArrayList(animal1.getEvtAnimals());
			evts.addAll(animal2.getEvtAnimals());
			Collections.sort(evts, new EventosPorFechaYTipo());
			List msg = new ArrayList();
			if (!evts.isEmpty()) {
				Iterator eventos = evts.iterator();
	
				while (eventos.hasNext()) {
					EvtAnimal evt = (EvtAnimal) eventos.next();
					try {

						Animal aa = null;
						
						aa = evt.getAnimal();
						aa.getEvtAnimals().remove(evt);
	
						if (!evt.getNombreTipo().equals(Evento.EVT_TIPO_LAC)&& !(evt.getNombreTipo().equals(Evento.EVT_TIPO_LMI)) && !evt.getNombreTipo().equals(Evento.EVT_TIPO_BAJ)) {
							ani.addEventoAnimal(evt, msg);
						} else{
							if(evt.getNombreTipo().equals(Evento.EVT_TIPO_LMI)){
								Lactancia evtl = (Lactancia) evt;
								if (evtl.getDias().intValue() != 999&& evtl.getLeche() != null) 
									ani.addEventoAnimal(evt, msg);
							}
							if(evt.getNombreTipo().equals(Evento.EVT_TIPO_LAC)){//con esto evito calcular el estado retroactivo
								evt.ejecutarBaja();                             //solo da de baja el evento, ya que la lactancia	
								HibernateFactory.getSession().delete(evt);      // se auto genera con ciertos eventos   
							}	
							if(evt.getNombreTipo().equals(Evento.EVT_TIPO_BAJ))
							{
								EvtAnimal ev= (EvtAnimal)ani.getEvtAnimals().last();
								if(!ev.getNombreTipo().equals(Evento.EVT_TIPO_REP) && !ev.getNombreTipo().equals(Evento.EVT_TIPO_SEC))
									evt.setAnimal(ani);//Esto se hace para el caso que hay un evento baja y antes un COA asi genera lactancia
								ani.addEventoAnimal(evt, msg);
							}
						}
					} catch (ExcepcionIntegridad e) {
						ProcCodMsg cod = null;
						try {
							cod = ProcCodMsgDAO
									.findByPrimaryKey(e.getCodigoError());
						} catch (Exception e2) {
							msge = e.getCodigoError();
							return msge;
						}
						if (cod == null)
							msge = e.getCodigoError();
						else {
							ProcMsg ms = ProcMsgDAO.create(e.getCodigoError(),
									ProcMsg.ERROR, e.getValores());
							msge = ms.getInformacion();
						}
						return msge;
					}
				}
			}
	
			Set procsAnimal = new HashSet();
			procsAnimal.addAll(animal1.getProcsAnimal());
			Set calif = new HashSet();
			List hijos = new ArrayList();
			List hijosP = new ArrayList();

			// carga de HIJOS Y CALIFICACIONES
			if (animal1.esHembra()) {
				Hembra h = (Hembra) animal1;
				hijos.addAll(h.getHijosGeneticos());
				hijosP.addAll(h.getHijosParto());
				Hembra h2 = (Hembra) animal2;
				hijos.addAll(h2.getHijosGeneticos());
				hijosP.addAll(h2.getHijosParto());
				if (h.getCalificacions() != null)
					calif.addAll(h.getCalificacions());
				if (h2.getCalificacions() != null)
					calif.addAll(h2.getCalificacions());
			} else {
				Macho m = (Macho) animal1;
				hijos.addAll(m.getHijosGeneticos());
				Macho m2 = (Macho) animal2;
				hijos.addAll(m2.getHijosGeneticos());
				if (m.getCalificacions() != null)
					calif.addAll(m.getCalificacions());
				if (m2.getCalificacions() != null)
					calif.addAll(m2.getCalificacions());
			}
			Iterator itca = calif.iterator();
			while (itca.hasNext()) {
				Calificacion ca = (Calificacion) itca.next();
				Animal ad = ca.getAnimal();
				ad.getCalificacions().remove(ca);
				ani.getCalificacions().add(ca);
				ca.setAnimal(ani);
				HibernateFactory.getSession().update(ca);
			}
			
			//SE ACTUALIZAN LOS EVENTOS CON EL NUEVO ANIMAL
			//esto es, setear a los eventos ya cargados en la lista del animal, el animal propiamente dicho
			
			if (!ani.getEvtAnimals().isEmpty()) {
				Iterator it = ani.getEvtAnimals().iterator();
				while (it.hasNext()) {
					EvtAnimal ev = (EvtAnimal) it.next();
					ev.setAnimal(ani);
					if(ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC))//esta creada sólo virtual, entonces se crea realmente
						HibernateFactory.getSession().save(ev);
					else
						HibernateFactory.getSession().update(ev);
				}
			}
			HibernateFactory.getSession().flush();
			
			//Eventos ALTAS si los hay
			//en caso que haya algun evento de alta, solo se asocia el del animal cuyo regOrigen fue seleccionado y 
			// el evento de alta del otro animal, en caso que lo hubiera, es eliminado
			if (form.getRegOri().equals("1")) {
			        EvtAlta evt = EvtAltaDAO.findByAnimal(animal1.getId()); 
			        if (evt != null)
			        	evt.setAnimal(ani);
			        EvtAlta evtSustituto = EvtAltaDAO.findByAnimal(animal2.getId()); 
			        if (evtSustituto != null)
			        	HibernateFactory.getSession().delete(evtSustituto);
			}
			else
			{
				   EvtAlta evt = EvtAltaDAO.findByAnimal(animal2.getId()); 
			        if (evt != null)
			        	evt.setAnimal(ani);
			        EvtAlta evtSustituto = EvtAltaDAO.findByAnimal(animal1.getId()); 
			        if (evtSustituto != null)
			        	HibernateFactory.getSession().delete(evtSustituto);
			}
		    		        
			//PADRES 
			//Se asocia el animal a la cria de los padres seleccionados en el form y se elimina
			//la cria de los padres del otro animal(en caso que tuviera), sólo la cria no la reproduccion
			EvtReproduccion evtRep= null;
			EvtCria evc = null;
			if (form.getPadres().equals("1")) {//asocio el animal al evento cria  seleccionado
				if (madre != null && animal1.getFechaNac() != null) {
					evtRep = (EvtReproduccion) madre.getEventoEnFecha(animal1.getFechaNac(), Evento.EVT_TIPO_REP);
					if (evtRep != null) {
						List evtCrias = evtRep.getEvtCrias();
						Iterator it = evtCrias.iterator();
						while (it.hasNext()) {
							evc = (EvtCria) it.next();
							if (evc.getCria() != null && (evc.getCria().getId().equals(animal1.getId())))
								break;
						}
					}
				} //elimino el evento cria del otro animal en caso q tenga padres
				if(animal2.getMadreGenetica()!= null && animal2.getMadreParto()!=null){
					
						Hembra madreSustituta = (Hembra) AnimalDAO.findByPrimaryKey(animal2.getMadreParto().getId());
						if (madreSustituta != null && animal2.getFechaNac() != null) {
							EvtReproduccion evtRepSustituto = (EvtReproduccion) madreSustituta.getEventoEnFecha(animal2.getFechaNac(),Evento.EVT_TIPO_REP);
							if (evtRepSustituto != null) {
								List evtCrias = evtRepSustituto.getEvtCrias();
								EvtCria evcSustituta = null;
								Iterator it = evtCrias.iterator();
								while (it.hasNext()) {
									evcSustituta = (EvtCria) it.next();
									if (evcSustituta.getCria() != null && (evcSustituta.getCria().getId().equals(animal2.getId())))
									{
										evcSustituta.setCria(null);
										evtRepSustituto.getEvtCrias().remove(evcSustituta);
										HibernateFactory.getSession().update(evtRepSustituto);
										break;
									}
								}
							}
						}
				
				}

			}else {//asocio el animal al evento cria  seleccionado
					if (madre != null && animal2.getFechaNac() != null) {
						evtRep = (EvtReproduccion) madre.getEventoEnFecha(animal2.getFechaNac(),Evento.EVT_TIPO_REP);
						if (evtRep != null) {
							List evtCrias = evtRep.getEvtCrias();
							Iterator it = evtCrias.iterator();
							while (it.hasNext()) {
								evc = (EvtCria) it.next();
								if (evc.getCria() != null && (evc.getCria().getId().equals(animal2.getId())))
									break;
							}
						}
					}
					if(animal1.getMadreGenetica()!= null){
					Hembra madreSustituta = (Hembra) AnimalDAO.findByPrimaryKey(animal1.getMadreParto().getId());
						if (madreSustituta != null && animal1.getFechaNac() != null) {
							EvtReproduccion evtRepSustituto = (EvtReproduccion) madreSustituta.getEventoEnFecha(animal1.getFechaNac(),Evento.EVT_TIPO_REP);
							if (evtRepSustituto != null) {
								List evtCrias = evtRepSustituto.getEvtCrias();
								EvtCria evcSustituta = null;
								Iterator it = evtCrias.iterator();
								while (it.hasNext()) {
									evcSustituta = (EvtCria) it.next();
									if (evcSustituta.getCria() != null && (evcSustituta.getCria().getId().equals(animal1.getId())))
									{
										evcSustituta.setCria(null);
										evtRepSustituto.getEvtCrias().remove(evcSustituta);
										HibernateFactory.getSession().update(evtRepSustituto);
										break;
									}
								}
							}
						}
					}
				}
				if (evc != null) //Actualizo el evento cria con el animal actual
				{
						evc.setCria(ani);
						Set msgs = evtRep.getProcEvtAnimal().getProcMsgsses();
						ProcMsg procMsg = null;
						for (Iterator iter = msgs.iterator(); iter.hasNext();) {
							procMsg = (ProcMsg) iter.next();
							if (procMsg.getId().equals("REGASIGN"))
								break;
						}
						if (procMsg != null) {
							evtRep.getProcEvtAnimal().getProcMsgsses().remove(procMsg);
							Registro regAsignado = ani.getRegOrigen();
							ProcMsg msgq = ProcMsgDAO.create(MENSAJES.REGISTRO_ASIGNADO, ProcMsg.INFO,new String[] {
											ani.getRP(), regAsignado.getNumero(),regAsignado.getTipoRegistro().getId(),	ani.getCategoria()});
							evtRep.getProcEvtAnimal().addMsg(msgq);
						}

						HibernateFactory.getSession().update(evtRep);
			}
		
			//HIJOS
			// Setea los hijos de ambos animales al nuevo animal					
			Iterator it2 = hijosP.iterator();
			while (it2.hasNext()) {//si no es hembra no tiene hijos de parto, por eso no es necesario preguntar si es hembra
				Animal hijo = (Animal) it2.next();
				hijo.setMadreParto((Hembra) ani);
				HibernateFactory.getSession().update(hijo);
			}
			Iterator it = hijos.iterator();
			while (it.hasNext()) {
				Animal hijo = (Animal) it.next();
				if (ani.esHembra())
					try {
						hijo.cambiarMadreGen((Hembra) ani);
					} catch (ExcepcionIntegridad e) {
						ProcCodMsg cod = null;
						try {
							cod = ProcCodMsgDAO.findByPrimaryKey(e.getCodigoError());
						} catch (Exception e2) {
							msge = e.getCodigoError();
							return msge;
						}
						if (cod == null)
							msge = e.getCodigoError();
						else {
							ProcMsg ms = ProcMsgDAO.create(e.getCodigoError(),ProcMsg.ERROR, e.getValores());
							msge = ms.getInformacion();
						}
						return msge;
					}
				else
					try {
						hijo.cambiarPadreGen((Macho) ani);
					} catch (ExcepcionIntegridad e) {
						ProcCodMsg cod = null;
						try {
							cod = ProcCodMsgDAO.findByPrimaryKey(e.getCodigoError());
						} catch (Exception e2) {
							msge = e.getCodigoError();
							return msge;
						}
						if (cod == null)
							msge = e.getCodigoError();
						else {
							ProcMsg ms = ProcMsgDAO.create(e.getCodigoError(),ProcMsg.ERROR, e.getValores());
							msge = ms.getInformacion();
						}
						return msge;
					}
				HibernateFactory.getSession().update(hijo);
			}
			
			//Creo el/los registros para el nuevo animal
			//Si regOri y RegIdent son iguales se crea uno solo, sino los dos
			//Los registros viejos se eliminan
			Registro vRegistro = new Registro();
			vRegistro.setAnimal(ani);
			vRegistro.setNumero(ani.getRegIdentificador().getNumero());
			vRegistro.setTipoRegistro(ani.getRegIdentificador().getTipoRegistro());
			vRegistro.setCodigoBaja(ani.getRegIdentificador().getCodigoBaja());
			vRegistro.setFechaBaja(ani.getRegIdentificador().getFechaBaja());
			HibernateFactory.getSession().save(vRegistro);
				
			if(ani.getRegOrigen().equals(ani.getRegIdentificador())){//seteo registros nuevos con los datos del anterior
				ani.getRegistros().remove(ani.getRegIdentificador());
				ani.addRegistro(vRegistro);
				ani.setRegIdentificador(vRegistro);
				ani.setRegOrigen(vRegistro);
			}
			else{//en caso q no sean iguales tengo que crear ambos registros
				Registro vRegistro1 = new Registro();
				vRegistro1.setAnimal(ani);
				vRegistro1.setNumero(ani.getRegOrigen().getNumero());
				vRegistro1.setTipoRegistro(ani.getRegOrigen().getTipoRegistro());
				vRegistro1.setCodigoBaja(ani.getRegOrigen().getCodigoBaja());
				vRegistro1.setFechaBaja(ani.getRegOrigen().getFechaBaja());
				HibernateFactory.getSession().save(vRegistro1);

				ani.getRegistros().remove(ani.getRegIdentificador());
				ani.getRegistros().remove(ani.getRegOrigen());
				ani.addRegistro(vRegistro);
				ani.addRegistro(vRegistro1);
				ani.setRegIdentificador(vRegistro);
				ani.setRegOrigen(vRegistro1);
			}


			Registro Reg1= animal1.getRegOrigen();
			Registro Reg2= animal1.getRegIdentificador();
			Registro Reg3= animal2.getRegOrigen();
			Registro Reg4= animal2.getRegIdentificador();
			
			animal1.setRegistros(null);
			animal1.setRegIdentificador(null);
			animal1.setRegOrigen(null);
			animal2.setRegistros(null);
			animal2.setRegIdentificador(null);
			animal2.setRegOrigen(null);
			animal1.setEvtAnimals(null);
			animal2.setEvtAnimals(null);
			HibernateFactory.getSession().update(animal1);
			HibernateFactory.getSession().update(animal2);
			
			HibernateFactory.getSession().flush();
			
		
			//Elimino los viejos registros
			if(Reg1!=Reg2)
				RegistroDAO.remove(Reg2);
			
			if(Reg3!=Reg4)
				RegistroDAO.remove(Reg4);
			RegistroDAO.remove(Reg1);
			RegistroDAO.remove(Reg3);

			List procesos = ProcAnimalDAO.findByAnimal(animal1.getId());
			procesos.addAll(ProcAnimalDAO.findByAnimal(animal2.getId()));
			Iterator itProcesos = procesos.iterator();
			while(itProcesos.hasNext())
			{
				ProcAnimal procAnim = (ProcAnimal) itProcesos.next();
				procAnim.setAnimal(ani);
				HibernateFactory.getSession().update(procAnim);
			}
			//Verifico que el animal 1 ya sea sinonimo y en ese caso 
			if(animal1.isSinonimo())
			{
				List sinonimos = AnimalSinonimoDAO.findByAnimal(animal1.getId());
				Iterator itSin= sinonimos.iterator();
				while(itSin.hasNext())
				{
					AnimalSinonimo anSin = (AnimalSinonimo) itSin.next();
					anSin.setSinonimo(ani);
					HibernateFactory.getSession().update(anSin);
				}
			}
			
			//Verifico que el animal 2 ya sea sinonimo y en ese caso 
			if(animal2.isSinonimo())
			{
				List sinonimos = AnimalSinonimoDAO.findByAnimal(animal2.getId());
				Iterator itSin= sinonimos.iterator();
				while(itSin.hasNext())
				{
					AnimalSinonimo anSin = (AnimalSinonimo) itSin.next();
					anSin.setSinonimo(ani);
					HibernateFactory.getSession().update(anSin);
				}
			}
			
			
			
			ani.setEsSinonimo(true);//Al nuevo animal se le setea la variable sinonimo en true, lo que significa que es creado por una union de sinonimos
			HibernateFactory.getSession().update(ani);
			sinonimo1.setSinonimo(ani); //A los sinonimos se le carga el nuevo animal, para saber cual es el que los unifico 
			sinonimo2.setSinonimo(ani);
			HibernateFactory.getSession().update(sinonimo1);
			HibernateFactory.getSession().update(sinonimo2);
		}
		return msge;
	}
	
	
	
	//Carga lso datos del animmal pasado como parametro, al animal sinonimo (para historial)
	private AnimalSinonimo cargarAnimalSinonimo(Animal anim){
		AnimalSinonimo animSino = new AnimalSinonimo();
		animSino.setApodo(anim.getApodo());
		animSino.setAsoc(anim.getAsoc());
		animSino.setAsom(anim.getAsom());
		animSino.setAsop(anim.getAsop());
		animSino.setCategoria(anim.getCategoria());
		animSino.setCategoriaV3(anim.getCategoriaV3());
		animSino.setCodigoVerificador(anim.getCodigoVerificador());
		animSino.setDadorSemen(anim.getDadorSemen());
		animSino.setDonante(anim.getDonante());
		animSino.setEsBaja(anim.getEsBaja());
		animSino.setEstablecimiento(anim.getEstablecimiento());
		animSino.setEstablecimientoCriador(anim.getEstablecimientoCriador());
		animSino.setEstancia(anim.getEstancia());
		animSino.setFechaNac(anim.getFechaNac());
		animSino.setFechaServicio(anim.getFechaServicio());
		animSino.setFechaTransf(anim.getFechaTransf());
		animSino.setFechaUltObs(anim.getFechaUltObs());
		animSino.setFoto(anim.getFoto());
		animSino.setMadreGenetica(anim.getMadreGenetica());
		animSino.setMadreParto(anim.getMadreParto());
		animSino.setMellizo(anim.getMellizo());
		animSino.setNombre(anim.getNombre());
		animSino.setNroLactancia(anim.getNroLactancia());
		animSino.setNroLactInformado(anim.getNroLactInformado());
		animSino.setNumeroAnalADN(anim.getNumeroAnalADN());
		animSino.setNumeroTransf(anim.getNumeroTransf());
		animSino.setPadre(anim.getPadre());
		animSino.setPropietario(anim.getPropietario());
		animSino.setPropietarioCriador(anim.getPropietarioCriador());
		animSino.setRazaDeclarada(anim.getRaza());
		animSino.setNumeroRegistro(anim.getRegOrigen().getNumero());
		animSino.setTipoRegistro(anim.getRegOrigen().getTipoRegistro().getId());
		animSino.setRP(anim.getRP());
		animSino.setRpSenasa(anim.getRpSenasa());
		animSino.setRpti(anim.getRpti());
		animSino.setSRAFesb(anim.getSRAFesb());
		animSino.setTaraG(anim.getTaraG());
		animSino.setTipoServicio(anim.getTipoServicio());
		animSino.setTransferencia(anim. getTransferencia());
		return animSino;
	}


	public Animal contieneAnimal(List crias, Animal cria)
	{
		Iterator it= crias.iterator();
		while(it.hasNext())
		{
			Animal c1 = (Animal) it.next();
			if(cria.getRP().equalsIgnoreCase(c1.getRP()) && cria.getFechaNac().getYear() == c1.getFechaNac().getYear())
				return c1;
		}
		return null;
	}

}

