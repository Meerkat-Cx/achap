/**
 * 
 */
package ar.org.sicel.web.proc;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.googleChart.ControlesLactanciaChart;
import ar.org.sicel.googleChart.ControlesTotalesLactanciasChart;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.CentroDeComputo;
import ar.org.sicel.persistence.CentroDeComputoDAO;
import ar.org.sicel.persistence.Contacto;
import ar.org.sicel.persistence.ContactoDAO;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtControlAnimal;
import ar.org.sicel.persistence.EvtCria;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.EvtReproduccionDAO;
import ar.org.sicel.persistence.FichaAnimalDAO;
import ar.org.sicel.persistence.FichaAnimalPendiente;
import ar.org.sicel.persistence.FichaAnimalesOrdenadosPorRP;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Lactancia;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcLoteDAO;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Sistema;
import ar.org.sicel.persistence.SistemaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AnimalForm;


/**
 * Clase que tiene como funcionalidad relizar un filtro sobre los animales para despues
 * poder descargar los reportes
 * @author jdivars
 * @since 25-ene-2007
 *
 */
public class VisualizarReportesAchaAction extends DispatchAction {

	static Logger log = Logger.getLogger(VisualizarReportesAchaAction.class);
	
	
		public ActionForward initMasivos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			AnimalForm af = (AnimalForm)form;
			af.reset();
			List razas = RazaDAO.findAll();
			request.setAttribute("razas",razas);
			return mapping.findForward("reporteMasivo");
		}
		public ActionForward mostrar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			AnimalForm formA = (AnimalForm)form;
			String tipo = formA.getTipoReg();
			String numero = formA.getNumReg();
			String raza = formA.getRaza();
			String sexo = formA.getSexo();
			
			try{
			Animal anim = AnimalDAO.findExistentByRegistry(tipo.toUpperCase(),numero,raza.toUpperCase(),sexo.toUpperCase()); 
			EvtCria cr = anim.getEvCria();
			if(anim!=null){	
			    if (formA.getListaAnimal()==null)
			    	formA.setListaAnimal(new ArrayList());
			    else
			    	if (!formA.getListaAnimal().contains(anim))
			    		formA.getListaAnimal().add(anim);	
			    if ("HBA".equals(anim.getRegOrigen().getTipoRegistro().getId().toUpperCase().trim()))
			    	formA.setExisteHBA(true);
			    List razas = RazaDAO.findAll();
			    request.setAttribute("listaAnimal",formA.getListaAnimal());
				request.setAttribute("razas",razas);
				return mapping.findForward("reporteMasivo");
			}
			else{
				List razas = RazaDAO.findAll();
	    		request.setAttribute("razas",razas);    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("error", new ActionMessage("error","El animal no existe"));
				saveMessages(request, msgs);
				return mapping.findForward("reporteMasivo");				
			}
			} catch (ExcepcionIntegridad e1) {
				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(), ProcMsg.ERROR,e1.getValores());
				List razas = RazaDAO.findAll();
				request.setAttribute("listaAnimal",formA.getListaAnimal());
	    		request.setAttribute("razas",razas);    
	    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("error", new ActionMessage("error",msg.getInformacion()));
				saveMessages(request, msgs);
				return mapping.findForward("reporteMasivo");
			}
			 catch (ErrorFatal e2) {
				 	List razas = RazaDAO.findAll();
		    		request.setAttribute("razas",razas);    		
		    		request.setAttribute("listaAnimal",formA.getListaAnimal());
					ActionMessages msgs = new ActionMessages();
					msgs.add("error", new ActionMessage("error",e2.getMessage()));
					saveMessages(request, msgs);
					return mapping.findForward("reporteMasivo");
			}			
		}
		/**
		 *Método que carga el combo de razas y e inicializa la pagina de buscar el animal
		 *para obtener algun reporte
		 */
		public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			AnimalForm af = (AnimalForm)form;
			af.reset();
			List razas = RazaDAO.findAll();
			List eclos = EcloDAO.findAll();
			request.setAttribute("razas",razas);
			request.setAttribute("eclos",eclos);
			return mapping.findForward("success");
			
			
		}
		public ActionForward buscar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			 AnimalForm formA = (AnimalForm)form;
			String tipo = formA.getTipoReg();
			String numero = formA.getNumReg();
			String raza = formA.getRaza();
			String sexo = formA.getSexo();
			/*Animal anim2 = AnimalDAO.findExistentByRegistry(tipo.toUpperCase(),numero,raza.toUpperCase(),sexo.toUpperCase()); 
			EvtCria evcr2 = anim2.getEvCria();*/
			try{
			Animal anim = AnimalDAO.findExistentByRegistry(tipo.toUpperCase(),numero,raza.toUpperCase(),sexo.toUpperCase()); 
			EvtCria evcr = anim.getEvCria();
			
			if(anim!=null){	
				if(anim.getEstablecimiento()!=null){
					formA.setNombreEstablecimiento(anim.getEstablecimiento().getNombreContacto());
					formA.setNombreEclo(anim.getEstablecimiento().getEclo().getNombreContacto());
				}
				
				formA.setRaza(anim.getRaza().getNombre());
				formA.setAnimalId(anim.getId());
				formA.setRp(anim.getRP());
				formA.setCategoria(anim.getCategoria());
				if(sexo.equalsIgnoreCase("H"))
					formA.setSexo("Hembra");
				else
					formA.setSexo("Macho");
				String id = anim.getId().toString();
			
				request.setAttribute("id",id);
				return mapping.findForward("list");
			}
			else{
				List razas = RazaDAO.findAll();
	    		request.setAttribute("razas",razas);    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("error", new ActionMessage("error","El animal no existe"));
				saveMessages(request, msgs);
				return mapping.findForward("success");
				//request.setAttribute("mensaje","No existen reportes para descargar con las datos cargados");
			}
			} catch (ExcepcionIntegridad e1) {
				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(), ProcMsg.ERROR,e1.getValores());
				//request.setAttribute("mensaje",msg.getInformacion());
				List razas = RazaDAO.findAll();
	    		request.setAttribute("razas",razas);    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("error", new ActionMessage("error",msg.getInformacion()));
				saveMessages(request, msgs);
				return mapping.findForward("success");
			}
			 catch (ErrorFatal e2) {
				 List razas = RazaDAO.findAll();
		    		request.setAttribute("razas",razas);    		
					ActionMessages msgs = new ActionMessages();
					msgs.add("error", new ActionMessage("error",e2.getMessage()));
					saveMessages(request, msgs);
					return mapping.findForward("success");
				//request.setAttribute("mensaje",e2.getMessage());
			}
			//return mapping.findForward("confirma");
			
		}
		/**
		 * Metodo por el cual se descarga la lactancia en curso de un animal
		 */
		public ActionForward descargarLactanciaCurso(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			log.debug("El context path del reporte lactancia en curso es: "+REPORTES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			
			Animal an = AnimalDAO.findByPrimaryKey(id);            
			String nombreGenerado =  "Constancia_Lactancia_Curso_RP_"+an.getRP()+".pdf";
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(nombreGenerado));

			try {
				
				InputStream is=	new ByteArrayInputStream(report.makeReportCertificadosLactancia(an));
				PrintWriter pw = response.getWriter();
				int c = -1;
				while ((c = is.read()) != -1) {
					pw.print((char) c);
				}
				is.close();
				pw.flush();
				pw = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
			
		}
		public ActionForward recategorizar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			AnimalForm formA = (AnimalForm)form;
			//Integer numeroLactancia = formA.getNumLactDescarga();
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			Animal an = AnimalDAO.findByPrimaryKey(id);   
			an.setearCategoria(true);
			AnimalDAO.updateAnimal(an);
			formA.setCategoria(an.getCategoria());
			request.setAttribute("id",id);
			
			return mapping.findForward("list");
		}
		public ActionForward recalcularComposicion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			AnimalForm formA = (AnimalForm)form;
			//Integer numeroLactancia = formA.getNumLactDescarga();
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			Animal an = AnimalDAO.findByPrimaryKey(id);   
			//an.setearCategoria(true);
			//an.calcularComposicionRacial(an.getEspecie());
			an.calcularComposicionRacialTotal(an.getEspecie());
			Long numReg = Long.valueOf(an.getRegOrigen().getNumero());
			if( (numReg <=Animal.MAYOR_RC_SICEL1 && AtributoDAO.findByNombre("RECALCULAR_RECATEGORIZAR_SICEL_1").getValorPorDefecto().getValor()=="1") || numReg >Animal.MAYOR_RC_SICEL1)//Si es animal de sicel 2 o es sicel 1 y admito recategorizar
			{
				EvtReproduccion rep =EvtReproduccionDAO.findNacimientoCria(an);
				Raza razaDeclarada = an.getRaza();
				if(rep!=null){
					if(rep.getEvtServicio()==null && an.getPadre()== null){//si no tengo padre
						 if (rep.getUsarRazaMadre()) 
								razaDeclarada = rep.getAnimal().getRaza();
							else
								razaDeclarada = rep.getAnimal().getRaza().getEspecie().getCruza();
					}
					else{//tengo servicio/padre
						if(rep.getAnimal().getRaza().getId().equals(an.getPadre().getRaza().getId())){
							razaDeclarada = rep.getAnimal().getRaza();
						}
					}
				}
				else{
					if(an.getMadreGenetica()!=null){
						if(an.getPadre()== null){//si no tengo padre
							 	razaDeclarada = an.getMadreGenetica().getRaza().getEspecie().getCruza();
						}
						else{//tengo servicio/padre
							if(an.getRaza().getId().equals(an.getPadre().getRaza().getId())){
								razaDeclarada = an.getRaza();
							}
						}
					}
				}
				an.getComposicionRacial().setRazaDeclarada(razaDeclarada);
				if((an.getMadreGenetica()!=null && an.getPadre()!=null)&&(!an.getMadreGenetica().getRaza().getId().equals(an.getPadre().getRaza().getId()))){
					if(!an.getComposicionRacial().getRazaCalculada().getEsDesconocido())
						an.getComposicionRacial().setRazaDeclarada(an.getComposicionRacial().getRazaCalculada());
					else
						an.getComposicionRacial().setRazaDeclarada(an.getMadreGenetica().getRaza().getEspecie().getCruza());
				}
				AnimalDAO.updateAnimal(an);
				formA.setCategoria(an.getCategoria());
			}
			request.setAttribute("id",id);
			
			return mapping.findForward("list");
		}
		/**
		metodo que toma la secada y agrega la lactancia, anda a saber por que la secada no tiene lactancia
		*/
		public ActionForward agregarLactanciaSecada(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);
		Animal an = AnimalDAO.findByPrimaryKey(id);   
		Hembra he = (Hembra)an;
		EventoDAO.findByPrimaryKey(24399720L);
		//Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
		he.actualizarEventosPosterioresARetroactivo(he, (EvtAnimal)EventoDAO.findByPrimaryKey(24399720L), new ArrayList());
		
		return mapping.findForward("list");
		}/**
		Esto todavia no se esta usando, habria que probar, pero la idea es que al apretar un boton esto haga una actualizacion de todos los eventos de un animal
		**/
		public ActionForward recalcularEventos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			Animal an = AnimalDAO.findByPrimaryKey(id);   
			Hembra he = (Hembra)an;
			EvtAnimal ev = EvtAnimalDAO.findPrimerEvento(he);
			//Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
			if(ev!=null)
				he.setEstadoRetroactivo(ev.getFecha(), new ArrayList(), null);
			request.setAttribute("id",idParameter);
			log.warn("eventos recalculados");
			return mapping.findForward("list");
			}
		public ActionForward recalcularLactancia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			Integer numeroLactancia = new Integer(request.getParameter("numLactDescarga"));
			//AnimalForm formA = (AnimalForm)form;
			//Integer numeroLactancia = formA.getNumLactDescarga();
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			Animal an = AnimalDAO.findByPrimaryKey(id);   
			Hembra he = (Hembra)an;
			Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
			
			if(l==null){
				List razas = RazaDAO.findAll();
	    		request.setAttribute("razas",razas);    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("error", new ActionMessage("error","La lactancia no existe o se encuentra en curso"));
				saveMessages(request, msgs);
				request.setAttribute("id",idParameter);
				return mapping.findForward("list");
			}
			EvtLactancia evl=null;
			if(l instanceof EvtLactancia){
				 evl = (EvtLactancia)l;
				if(!evl.isEsCerrada()){
					List razas = RazaDAO.findAll();
		    		request.setAttribute("razas",razas);    		
					ActionMessages msgs = new ActionMessages();
					msgs.add("error", new ActionMessage("error","La lactancia que desea descargar esta en curso, debe descargarla desde la opcion descargar lactancia en curso"));
					saveMessages(request, msgs);
					request.setAttribute("id",idParameter);
					return mapping.findForward("list");
				}
				else{
					if(an.borrarLactanciasColgadas(evl)){
						
						//recalcularEventos
						//commit
						//pedirla nuevamente por numero de lactancia
						EvtAnimal ev = EvtAnimalDAO.findPrimerEvento(he);
						//Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
						if(ev!=null)
							he.setEstadoRetroactivo(ev.getFecha(), new ArrayList(), null);
					}
					evl.recalcularLactancia(an, evl);
				}
			}
			else{
				List razas = RazaDAO.findAll();
	    		request.setAttribute("razas",razas);    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("error", new ActionMessage("error","La lactancia que desea recalcular es una lactancia cerrada en sicel 1, no puede ser recalculada "));
				saveMessages(request, msgs);
				request.setAttribute("id",idParameter);
				return mapping.findForward("list");
			}
			request.setAttribute("id",id);
			return mapping.findForward("list");
		}

		
		public ActionForward descargarLactanciaNumero(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			Integer numeroLactancia = new Integer(request.getParameter("numLactDescarga"));
			//AnimalForm formA = (AnimalForm)form;
			//Integer numeroLactancia = formA.getNumLactDescarga();
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			log.debug("El context path del reporte lactancia en curso es: "+REPORTES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			Animal an = AnimalDAO.findByPrimaryKey(id);   
			Hembra he = (Hembra)an;
			Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
			
			if(l==null){
				//request.setAttribute("mensaje","No existe el la lactancia con el numero informado");
				//return mapping.findForward("confirma");
				List razas = RazaDAO.findAll();
	    		request.setAttribute("razas",razas);    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("error", new ActionMessage("error","La lactancia no existe o se encuentra en curso"));
				saveMessages(request, msgs);
				request.setAttribute("id",idParameter);
				return mapping.findForward("list");
			}
			EvtLactancia evl=null;
			if(l instanceof EvtLactancia){
				 evl = (EvtLactancia)l;
				 //System.out.println("PRIMERO"+ STProdMedObj.GR.toString()+(Float)evl.getPorcentajeGrasa());
				if(!evl.isEsCerrada()){
					//request.setAttribute("mensaje","La lactancia que desea descargar esta en curso, debe descargarla desde la opcion descargar lactancia en curso");
					//return mapping.findForward("confirma");
					List razas = RazaDAO.findAll();
		    		request.setAttribute("razas",razas);    		
					ActionMessages msgs = new ActionMessages();
					msgs.add("error", new ActionMessage("error","La lactancia que desea descargar esta en curso, debe descargarla desde la opcion descargar lactancia en curso"));
					saveMessages(request, msgs);
					request.setAttribute("id",idParameter);
					return mapping.findForward("list");
				}
				/*else{
					 //EvtLactancia result = EvtLactancia.recalcularLactanciaCerrada(an);
				        	evl.recalcularLactancia(an, evl);
				            //HibernateFactory.getSession().update(evl);
				            //HibernateFactory.getSession().flush();	
				}*/
				
				
					
			}
			
			String nombreGenerado =  "Informacion_Lactancia_Cerrada_RP_"+an.getRP()+".pdf";
			he.setNumeroLactanciaReporte(numeroLactancia.intValue());
			//System.out.println("DESPUES"+STProdMedObj.GR.toString()+(Float)evl.getPorcentajeGrasa());
			/*Iterator it = he.getControlesLactanciaNumero().iterator();
			while(it.hasNext()){
				EvtControlAnimal evt =(EvtControlAnimal)it.next();
				System.out.println("CONTROL "+ evt.getFecha());
				Iterator it2 = evt.getOrdeniesAnimal().iterator();
				while(it2.hasNext()){
					EvtOrdenieAnimal evo = (EvtOrdenieAnimal)it2.next();
					
					if(evo.tieneAnalisis()){
						System.out.println(STProdMedObj.LE.toString()+(Float)evo.getMediciones().get(STProdMedObj.LE.toString()));
						System.out.println(STProdMedObj.GR.toString()+(Float)evo.getMediciones().get(STProdMedObj.GR.toString()));
						System.out.println(STProdMedObj.PR.toString()+(Float)evo.getMediciones().get(STProdMedObj.PR.toString()));
					}
				}
			}*/
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(nombreGenerado));

			try {
				
				InputStream is=	new ByteArrayInputStream(report.makeReportCertificadoLactanciaNumero(he));
				PrintWriter pw = response.getWriter();
				int c = -1;
				while ((c = is.read()) != -1) {
					pw.print((char) c);
				}
				is.close();
				pw.flush();
				pw = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
			
		}

		
		public ActionForward downloadFichaMasivas(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			String hojaTipo = request.getParameter("hoja")!=null?request.getParameter("hoja"):"A4";
			AnimalForm formAnimal = (AnimalForm)form;
			List listaAnimal = new ArrayList<Animal>(); 
			for (Iterator i = formAnimal.getListaAnimal().iterator();i.hasNext();){			
				Animal an = AnimalDAO.findByPrimaryKey(((Animal)i.next()).getId());		
				if ("RC".equals(an.getRegOrigen().getTipoRegistro().getId().toUpperCase().trim()))
						listaAnimal.add(an);		
			}
			if(!listaAnimal.isEmpty()){
										
				String nombreGenerado =  "Fichas_Masiva.pdf";
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(nombreGenerado));
				try {
					InputStream is=	new ByteArrayInputStream(report.makeReportLote(listaAnimal,hojaTipo));
					PrintWriter pw = response.getWriter();
					int c = -1;
					while ((c = is.read()) != -1) {
						pw.print((char) c);
					}
					is.close();
					pw.flush();
					pw = null;
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			else{
				request.setAttribute("mensaje","Debe ingresar un animal con tipo de registro RC");
				return mapping.findForward("listLotes");
			}
			return null;
		}
		public ActionForward downloadFichaIds(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			String hojaTipo = request.getParameter("hoja")!=null?request.getParameter("hoja"):"";
			AnimalForm formAnimal = (AnimalForm)form;
			List listaAnimal = new ArrayList<Animal>(); 
			listaAnimal = AnimalDAO.findAnimalesId();
			if(!listaAnimal.isEmpty()){
										
				String nombreGenerado =  "Fichas_Masiva_Cambio_Registro.pdf";
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(nombreGenerado));
				try {
					InputStream is=	new ByteArrayInputStream(report.makeReportLote(listaAnimal,hojaTipo));
					PrintWriter pw = response.getWriter();
					int c = -1;
					while ((c = is.read()) != -1) {
						pw.print((char) c);
					}
					is.close();
					pw.flush();
					pw = null;
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			else{
				request.setAttribute("mensaje","Debe ingresar un animal con tipo de registro RC");
				return mapping.findForward("listLotes");
			}
			return null;
		}

		public ActionForward descargarExportacionMasiva(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
			log.debug("El context path del reporte exportacion es: "+REPORTES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			
			String destinat = request.getParameter("destinatario");
			String firmante = request.getParameter("firmante");
			
			AnimalForm formAnimal = (AnimalForm)form;
			List listaAnimal = new ArrayList<Animal>(); 
			for (Iterator i = formAnimal.getListaAnimal().iterator();i.hasNext();){			
				Animal an = AnimalDAO.findByPrimaryKey(((Animal)i.next()).getId());				
				listaAnimal.add(an);
			}
			if(!listaAnimal.isEmpty()){
				
					String nombreGenerado =  "Certificado_Exportacion_RP_Masiva.pdf";				
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(nombreGenerado));
			
					try {						
						InputStream is=	new ByteArrayInputStream(report.makeReportLoteExport(listaAnimal,destinat,firmante));
						PrintWriter pw = response.getWriter();
						int c = -1;
						while ((c = is.read()) != -1) {
							pw.print((char) c);
						}
						is.close();
						pw.flush();
						pw = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			else{
				request.setAttribute("mensaje","Debe ingresar un animal primero");
				return mapping.findForward("listLotes");
			}
			
			return null;
		}

		public ActionForward descargarExportacion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
			log.debug("El context path del reporte exportacion es: "+REPORTES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			Animal animal = AnimalDAO.findByPrimaryKey(id);
			String destinat = request.getParameter("destinatario");
			String firmante = request.getParameter("firmante");
			String hojaTipo = request.getParameter("hoja")!=null?request.getParameter("hoja"):"";
			if(animal!=null){
				
				String nombreGenerado =  "Certificado_Exportacion_RP_"+animal.getRP()+".pdf";
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(nombreGenerado));
		
				try {
					
					InputStream is=	new ByteArrayInputStream(report.makeReportCertificacionExportacion(animal,destinat,firmante,hojaTipo));
					PrintWriter pw = response.getWriter();
					int c = -1;
					while ((c = is.read()) != -1) {
						pw.print((char) c);
					}
					is.close();
					pw.flush();
					pw = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return null;
		}

		
		/**
		 * Metodo que recibe como parametro de request el animal del cual se quiere generar el pdf FichaAnimal
		 * se arman los path para la generacion del reporte, una vez generado el reporte se crea el PDF para ser descargado
		 */
		@SuppressWarnings("deprecation")
		public ActionForward downloadFicha(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
			log.debug("El context path del reporte ficha del animal es: "+REPORTES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			
			
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			String hojaTipo = request.getParameter("hoja")!=null?request.getParameter("hoja"):"";
			Animal animal = AnimalDAO.findByPrimaryKey(id);//f.getAnimal().getId());
			
			if(animal!=null){
				//Date fechaActual = new Date();
				//SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
				//String nombreGenerado = sd.format(fechaActual) + "_" + animal.getId()+ "_e.pdf";
				String nombreGenerado =  "Ficha_Animal_RP_"+animal.getRP()+".pdf";
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(nombreGenerado));
	
				try {
					
					InputStream is=	new ByteArrayInputStream(report.makeReport(id,hojaTipo));//f.getAnimal().getId()));
					PrintWriter pw = response.getWriter();
					int c = -1;
					while ((c = is.read()) != -1) {
						pw.print((char) c);
					}
					is.close();
					pw.flush();
					pw = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return null;
			
		}
		
		
		public ActionForward descargarGenealogico(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			REPORTES_DIR = this.getContextPath(REPORTES_DIR);
			IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
			log.debug("El context path del reporte genealogia es: "+REPORTES_DIR);
			ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
			String idParameter = request.getParameter("id");
			Long id = new Long(idParameter);
			
			Animal animal = AnimalDAO.findByPrimaryKey(id);
			
			if(animal!=null){
				String nombreGenerado =  "Reporte_Genealogico_RP_"+animal.getRP()+".pdf";
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(nombreGenerado));
				try {
					
					InputStream is=	new ByteArrayInputStream(report.makeReportGenealogico(animal.getId()));
					PrintWriter pw = response.getWriter();
					int c = -1;
					while ((c = is.read()) != -1) {
						pw.print((char) c);
					}
					is.close();
					pw.flush();
					pw = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return null;
				}
		
		
	
	
	public ActionForward removeFicha(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);
		FichaAnimalPendiente f = FichaAnimalDAO.load(id);
		f.setPendiente(false);
		FichaAnimalDAO.update(f);
		
		return mapping.findForward("init");
	}
	
	
	private String getContextPath(String subPath)
	{
		return getServlet().getServletContext().getRealPath(subPath);
	}		
	
	
	
	
	public ActionForward initLotes(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		AnimalForm af = (AnimalForm)form;
		af.reset();
		List listaProcLote = ProcLoteDAO.findByFichas();	
		if(!listaProcLote.isEmpty()){
				request.setAttribute("fichas",listaProcLote);
				return mapping.findForward("listLotes");
				
			}
			else
				request.setAttribute("mensaje","No hay lotes con fichas para descargar en este momento.");
		
		return mapping.findForward("listLotes");
		
	}
	public ActionForward initFiltroLotes(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		AnimalForm af = (AnimalForm)form;
		af.reset();
		List sistemas = SistemaDAO.findAll();
		request.setAttribute("sistemas",sistemas);
		return mapping.findForward("filtroLotes");
		
	}
		
	
	public ActionForward buscarLote(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		AnimalForm formA = (AnimalForm)form;
		Long ecloId = formA.getEcloId();
		Long loteNum = formA.getLoteNum();
		Long loteId = formA.getLoteId();
		Long sistemaI = formA.getSistema();
		Sistema sistema = SistemaDAO.findByPrimaryKey(sistemaI);
		Long centroId = null;
		CentroDeComputo centro = null;
		if(formA.getCentroId()!=null && formA.getCentroId().intValue()!=0){
			centroId = formA.getCentroId();
			centro = CentroDeComputoDAO.findByPrimaryKey(centroId);
		}
		Eclo eclo = EcloDAO.findByPrimaryKey(ecloId);
		if(eclo!=null){
			ProcLote lote = null;
			if(loteId==null || loteId.intValue()==0)
				lote = ProcLoteDAO.findByEcloSistemaCentro(eclo,sistema,centro,loteNum);
			else
				lote = ProcLoteDAO.findByEcloSistemaCentroId(eclo,sistema,centro,loteId);
			if(lote!=null){
				List lotes = new ArrayList();
				if(!lote.getFichasAnimal().isEmpty()){
					lotes.add(lote);
					request.setAttribute("fichas",lotes);
				}
				else{
					request.setAttribute("mensaje","El lote no contiene fichas para descargar.");
				}
			}
			else{
				request.setAttribute("mensaje","El lote no existe para la eclo informada");
			}
				
		}
		else
			request.setAttribute("mensaje","La ECLO no existe");
		
		
		return mapping.findForward("listLotes");
	}
	
	@SuppressWarnings("deprecation")
	public ActionForward downloadFichaLoteRCD(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		String loteIdParameter = request.getParameter("loteId");
		String hojaTipo = request.getParameter("hoja")!=null?request.getParameter("hoja"):""; 
		Long loteId = new Long(loteIdParameter);
		//String ecloIdParameter = request.getParameter("ecloId");
		///Long ecloId = new Long(ecloIdParameter);
			ProcLote procLote = ProcLoteDAO.findByPrimaryKey(loteId);
			//List fichas = FichaAnimalDAO.findExistentByLote(/*ecloId,*/ loteId);
			//Set fichas = procLote.getFichasAnimal();
			SortedSet fichas = new TreeSet(new FichaAnimalesOrdenadosPorRP());
			fichas.addAll(procLote.getFichasAnimal());
			if (!fichas.isEmpty()){

				List<Animal> animales =  new ArrayList<Animal>();
				for(Object f:fichas){
					FichaAnimalPendiente ficha = (FichaAnimalPendiente)f;
					Animal animal = AnimalDAO.findByPrimaryKey(ficha.getAnimal().getId());
					//if((!animal.getRegOrigen().getTipoRegistro().equals("HBA"))&&(animal.getRaza().getCategoriaPura()!=null 
					if((!animal.getRegistroOrigen().contains("HBA"))&&(animal.getRaza().getCategoriaPura()!=null
							&& animal.getCategoria().equals(animal.getRaza().getCategoriaPura()))||(animal.getCategoria().equals(Animal.CAT_PU)))//si es pura o es RCD(por las transferencias)
						animales.add(animal);
					/*if((animal.getCategoria().equals(Animal.CAT_PURA_BUFALO))||
							(animal.getCategoria().equals(Animal.CAT_PURA_HOLANDO))||
							(animal.getCategoria().equals(Animal.CAT_PURA_JERSEY))||
							(animal.getCategoria().equals(Animal.CAT_PU))||
							(animal.getCategoria().equals(Animal.CAT_PURA_PARDO)||
									(animal.getCategoria().equals(Animal.CAT_PURA_SUECA))))
						animales.add(animal);*/
	
				}
				
				if(!animales.isEmpty()){
					
					String nombreGenerado =  "Fichas_puras_porLote_Eclo"+procLote.getEclo().getId()+"_"+procLote.getSistema().getId()+"_"+((procLote.getCentroComputo()!=null)?procLote.getCentroComputo().getId():"SC")+"_Lote_"+procLote.getNumLote()+".pdf";
					//String nombreGenerado =  "Fichas_puras_porLote_Eclo"+procLote.getEclo().getId()+"_Lote_"+procLote.getNumLote()+".pdf";
					
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(nombreGenerado));
					if(hojaTipo.equals("A4"))
						nombreGenerado =  "Fichas_regulares_porLote_Eclo"+procLote.getEclo().getId()+"_"+procLote.getSistema().getId()+"_"+((procLote.getCentroComputo()!=null)?procLote.getCentroComputo().getId():"SC")+"_Lote_"+procLote.getNumLote()+"_A4_.pdf";
					else
						nombreGenerado =  "Fichas_regulares_porLote_Eclo"+procLote.getEclo().getId()+"_"+procLote.getSistema().getId()+"_"+((procLote.getCentroComputo()!=null)?procLote.getCentroComputo().getId():"SC")+"_Lote_"+procLote.getNumLote()+".pdf";
					try {
						
						InputStream is=	new ByteArrayInputStream(report.makeReportLote(animales,hojaTipo));
						PrintWriter pw = response.getWriter();
						int c = -1;
						while ((c = is.read()) != -1) {
							pw.print((char) c);
						}
						is.close();
						pw.flush();
						pw = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					request.setAttribute("mensaje","El lote "+procLote.getNumLote().toString()+" no contiene animales con categoría PURA");
					request.setAttribute("popUp","El lote "+procLote.getNumLote().toString()+" no contiene animales con categoría PURA");
					return mapping.findForward("initLotes");
				}
			}
			else
					request.setAttribute("mensaje","No existen reportes para descargar con las datos cargados");
		
		
		 return null;
	}		
	public ActionForward downloadFichaLoteOtros(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		String loteIdParameter = request.getParameter("loteId");
		String hojaTipo = request.getParameter("hoja")!=null?request.getParameter("hoja"):"";
		Long loteId = new Long(loteIdParameter);
		
			ProcLote procLote = ProcLoteDAO.findByPrimaryKey(loteId);
			//Set fichas = procLote.getFichasAnimal();
			SortedSet fichas = new TreeSet(new FichaAnimalesOrdenadosPorRP());
			fichas.addAll(procLote.getFichasAnimal());
			if (!fichas.isEmpty()){

				List<Animal> animales = new ArrayList<Animal>();
				for(Object f:fichas){
					FichaAnimalPendiente ficha = (FichaAnimalPendiente)f;
					Animal animal = AnimalDAO.findByPrimaryKey(ficha.getAnimal().getId());
					if((!animal.getRegistroOrigen().contains("HBA"))&&(animal.getRaza().getCategoriaPura()==null ||!animal.getCategoria().equals(animal.getRaza().getCategoriaPura()))&&(!animal.getCategoria().equals(Animal.CAT_PU))){//si no es pura
						animales.add(animal);
						//System.out.println(animal.getRegOrigen().getNumero());
					}
					/*if((!animal.getCategoria().equals(Animal.CAT_PURA_BUFALO))&&(!animal.getCategoria().equals(Animal.CAT_PURA_HOLANDO))&&(!animal.getCategoria().equals(Animal.CAT_PURA_JERSEY))&&(!animal.getCategoria().equals(Animal.CAT_PURA_PARDO)&&(!animal.getCategoria().equals(Animal.CAT_PURA_SUECA))))
						animales.add(animal);*/
	
				}
				
				if(!animales.isEmpty()){
					
					String nombreGenerado =  "Fichas_regulares_porLote_Eclo"+procLote.getEclo().getId()+"_"+procLote.getSistema().getId()+"_"+((procLote.getCentroComputo()!=null)?procLote.getCentroComputo().getId():"SC")+"_Lote_"+procLote.getNumLote()+".pdf";
					//String nombreGenerado =  "Fichas_regulares_porLote_Eclo"+procLote.getEclo().getId()+"_Lote_"+procLote.getNumLote()+".pdf";
					
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(nombreGenerado));
					if(hojaTipo.equals("A4"))
						nombreGenerado =  "Fichas_regulares_porLote_Eclo"+procLote.getEclo().getId()+"_"+procLote.getSistema().getId()+"_"+((procLote.getCentroComputo()!=null)?procLote.getCentroComputo().getId():"SC")+"_Lote_"+procLote.getNumLote()+"_A4_.pdf";
					else
						nombreGenerado =  "Fichas_regulares_porLote_Eclo"+procLote.getEclo().getId()+"_"+procLote.getSistema().getId()+"_"+((procLote.getCentroComputo()!=null)?procLote.getCentroComputo().getId():"SC")+"_Lote_"+procLote.getNumLote()+".pdf";
					try {
						
						InputStream is=	new ByteArrayInputStream(report.makeReportLote(animales,hojaTipo));
						PrintWriter pw = response.getWriter();
						int c = -1;
						while ((c = is.read()) != -1) {
							pw.print((char) c);
						}
						is.close();
						pw.flush();
						pw = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					request.setAttribute("mensaje","El lote "+procLote.getNumLote().toString()+" no contiene animales de otra categoría");
					request.setAttribute("popUp","El lote "+procLote.getNumLote().toString()+" no contiene animales de otra categoría");
					return mapping.findForward("initLotes");
				}
			}
			else
					request.setAttribute("mensaje","No existen reportes para descargar con las datos cargados");
		
		
		 return null;
	}		
	
	@SuppressWarnings("deprecation")
	public ActionForward borrarFichaLote(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String loteIdParameter = request.getParameter("loteId");
		Long loteId = new Long(loteIdParameter);
		ProcLote procLote = ProcLoteDAO.findByPrimaryKey(loteId);
		Set fichas = procLote.getFichasAnimal();
		FichaAnimalDAO.borrarFichas(fichas);
		return mapping.findForward("initLotes");
	}
	
	/*Trae el listado de todas las eclos para que el usuario pueda elegir sobre cuales hacer el reporte de propietarios*/
	/*Juan Alejo Scornaienqui*/
	public ActionForward buscarPropietarios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		if(user.getContacto()!=null){
			if(user.getRol().getNombre().equalsIgnoreCase("REGIONAL")){
				request.setAttribute("eclos",new ArrayList());
				List regionales = EntidadRegionalDAO.findAll();
				request.setAttribute("regionales",regionales);
				}
		}
		else
		{
			List eclos = EcloDAO.findAll();
			request.setAttribute("eclos",eclos);
			List regionales = EntidadRegionalDAO.findAll();
			request.setAttribute("regionales",regionales);
		}
		request.setAttribute("usuario",user.getRol().getNombre());
		return mapping.findForward("buscarPropietarios");
	}
	
	/*Se encarga de armar el reporte. Accion disparada cuando el usuario hace click en Aceptar*/
	/*Juan Alejo Scornaienqui*/
	@SuppressWarnings("deprecation")
	public ActionForward downloadReportePropietariosPorEclo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		String idEclo = request.getParameter("ecloId");
		String idRegional;
		String tipoBusqueda;
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		if(user.getRol().getNombre().equalsIgnoreCase("REGIONAL")){
			Contacto contac = ContactoDAO.findByPrimaryKey(user.getContacto().getId());
			idRegional = contac.getId().toString();
			tipoBusqueda= "regional";
		}
		else{
			idRegional = request.getParameter("idRegional");
			tipoBusqueda= request.getParameter("tipoBusqueda");
		}
		String estado = request.getParameter("estado");
		String duenio = request.getParameter("duenio");
		String nombreGenerado;
		if (idEclo!=null)
			nombreGenerado =  "/Listado_Propietarios_por_Id_Eclo_"+idEclo+".xls";
		else	
			nombreGenerado =  "/Listado_Propietarios.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		String select = "select distinct c.NOMBRE,u.DIRECCION,u.CODIGO_POSTAL,u.CIUDAD,u.PROV_REG,prop.SOCIO,c.EMAIL,u.TELEFONO,c.CUIT,prop.PREFIJO,"+(duenio.equals("nada") ? "'' ECLO" : "e.ECLO")+",prop.ID as PROPIETARIOID"+
					  (duenio.equals("tambos") && estado.equals("todos") ? ",cantTambosActivos.cant TAMBOESTADO" : (duenio.equals("animales") || duenio.equals("nada") || duenio.equals("todos")) ? ", -1 TAMBOESTADO" : ", cantTambosActivos.cant TAMBOESTADO");
		String from = " from "+ ((duenio.equals("animales") || duenio.equals("todos"))  ? "an_animal a," : "") +(duenio.equals("nada") ? " " : "en_establecimiento e,")+" en_propietario prop, en_contacto c,en_ubicacion u"+ (tipoBusqueda.equals("regional") ? ",en_eclo ee " : " " )+
						",(select p2.id,cantidadTambosActivos.cant "+
						"from en_propietario p2, "+
						    "(select p3.id,count(e2.id) as cant "+
						    "from en_establecimiento e2,en_propietario p3 "+
						    "where p3.id = e2.PROPIETARIO and e2.ACTIVOESTABLECIMIENTO = 1 "+
						    "group by p3.id) cantidadTambosActivos "+
						"where p2.id = cantidadTambosActivos.id(+)) cantTambosActivos";
		String query = select + from +
					   " where " + (!duenio.equals("nada") ? (tipoBusqueda.equals("eclo") ? "e.ECLO = "+idEclo+" and " : tipoBusqueda.equals("regional") ? "ee.REGIONAL = "+ idRegional +" and ee.ID = e.ECLO and " : "") : "" )+
					   (duenio.equals("animales") ? " e.id = a.estab and a.PROPIETARIO = prop.ID and " : 
						duenio.equals("tambos") ? " e.propietario = prop.ID and " : 
						(duenio.equals("todos") ) ? " ((e.id = a.estab and a.PROPIETARIO = prop.ID) or (e.propietario = prop.ID) or (not exists (select * from an_animal where propietario =prop.id) "+
                                  				    " and not exists (select * from en_establecimiento where propietario =prop.id))) and " : "")+
					   (estado.equals("1") ? " prop.ID = cantTambosActivos.ID and cantTambosActivos.cant is not null " :
                        estado.equals("0") ? " prop.ID = cantTambosActivos.ID and cantTambosActivos.cant is null  " : 
                                             " prop.ID = cantTambosActivos.ID ")+
                       (duenio.equals("nada") ? " and not exists (select * from an_animal where propietario =prop.id) "+
                                  				" and not exists (select * from en_establecimiento where propietario =prop.id) " : " ") +                                             
					   " and prop.id = c.ID"+
		               " and c.ID = u.CONTACTO (+) order by c.NOMBRE";
		
		InputStream is=	new ByteArrayInputStream(report.makeReportPropietarios(query,nombreGenerado));
		PrintWriter pw = response.getWriter();
		int c = -1;
		while ((c = is.read()) != -1) {
			pw.print((char) c);
		}
		is.close();
		pw.flush();
		pw = null;
		return null;
	}
	public ActionForward graficarLactancia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Integer numeroLactancia = new Integer(request.getParameter("numLactDescarga"));
		//AnimalForm formA = (AnimalForm)form;
		//Integer numeroLactancia = formA.getNumLactDescarga();
		String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);
		Animal an = AnimalDAO.findByPrimaryKey(id);   
		Hembra he = (Hembra)an;
		Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
		if(l.isSicel3()){
			ControlesLactanciaChart lacCon = new ControlesLactanciaChart();
			lacCon.setLactancia((EvtLactancia)l);
			lacCon.actionConsultar();
			request.setAttribute("chartData",lacCon.getChartData());
		}
		request.setAttribute("id",id);
		return mapping.findForward("list");
	
	}
	public ActionForward graficarTodasLactancias(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
				String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);
		Animal an = AnimalDAO.findByPrimaryKey(id);   
		Hembra he = (Hembra)an;
		
		ControlesTotalesLactanciasChart lacCon = new ControlesTotalesLactanciasChart();
		lacCon.setHembra(he);
		lacCon.actionConsultar();
		request.setAttribute("chartData",lacCon.getChartData());
		request.setAttribute("id",id);
		return mapping.findForward("list");
	
	}
}