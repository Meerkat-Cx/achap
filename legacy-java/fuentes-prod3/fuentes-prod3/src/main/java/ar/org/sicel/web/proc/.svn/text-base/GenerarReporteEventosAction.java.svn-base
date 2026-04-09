/**
 * 
 */
package ar.org.sicel.web.proc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.ProcEvtDAO;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.persistence.util.jasperReport.Resumen;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.find.ReporteEventosForm;


/**
 * Action que tiene como objetivo generar el reporte de Eventos
 * @author jdivars
 * 13-feb-2007
 */
public class GenerarReporteEventosAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		request.setAttribute("inicio","no");
		request.setAttribute("mostrar","no");
		request.setAttribute("mostrarDescarga","si");
		
		return mapping.findForward("success");
	}
	public ActionForward initEspecial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		request.setAttribute("inicio","si");
		request.setAttribute("mostrar","no");
		request.setAttribute("todas","no");
		request.setAttribute("mostrarDescarga","si");
		ReporteEventosForm reForm = (ReporteEventosForm) form;
		reForm.reset();
		
		return mapping.findForward("success");
	}
	
	public ActionForward initBuscarEstablecimiento(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		ReporteEventosForm reForm = (ReporteEventosForm) form;
		reForm.setIdEst("");
		if(!reForm.getIdEclo().equals(""))
			reForm.setIdEclo(reForm.getIdEclo());
		return mapping.findForward("initBuscarEstablecimiento");		
	}
	public ActionForward initBuscarEclos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		List eclos = new ArrayList();
		request.setAttribute("listaEclos", eclos);
		request.getSession().removeAttribute("selected_eclos");
		ReporteEventosForm reForm = (ReporteEventosForm) form;
		reForm.reset();
		return mapping.findForward("initBuscarEclos");		
	}
	
	private static List saveSelectedEclosIDs(HttpServletRequest request) {
	    List presidents = (List) request.getSession().getAttribute("selected_eclos");
	    if (presidents == null) {
	        presidents = new ArrayList();
	        request.getSession().setAttribute("selected_eclos", presidents);
	    }
	    Enumeration parameterNames = request.getParameterNames();
	    while (parameterNames.hasMoreElements()) {
	        String parameterName = (String) parameterNames.nextElement();
	        if (parameterName.startsWith("chkbx_")) {
	            String presidentId = StringUtils.substringAfter(parameterName, "chkbx_");
	            String parameterValue = request.getParameter(parameterName);
	            if (parameterValue.equals("SELECTED")) {
	                if (!presidents.contains(presidentId)) {
	                    presidents.add(presidentId);
	                }
	            } else {
	                presidents.remove(presidentId);
	            }
	        }
	    }
	    return presidents;
	}

	public ActionForward buscarEclos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		ReporteEventosForm reForm = (ReporteEventosForm) form;
		Long idEcl;
		try{
			if(!"".equals(reForm.getIdEclo())){
				idEcl = new Long(reForm.getIdEclo());
				}
			}
			catch (Exception e) {
				request.setAttribute("error","EL CAMPO ID DEBE SER UN NUMERO");
				return mapping.findForward("initBuscarEclos");			
			}
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		Long idRegional = usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL) ? usuarioActual.getContacto().getId() : null;
		//List eclos = EcloDAO.findEclos(reForm.getNombreEclo(), reForm.getIdEclo());
		String nombreRegional = "";
		if(idRegional!=null){
			EntidadRegional regional = EntidadRegionalDAO.findByPrimaryKey(idRegional);
			nombreRegional = regional.getNombreContacto();
		}
		List eclos = EcloDAO.findEclosPorFiltro(reForm.getNombreEclo(), reForm.getIdEclo(),nombreRegional);
		saveSelectedEclosIDs(request);
		request.setAttribute("listaEclos", eclos);
		return mapping.findForward("initBuscarEclos");		
	}
	
	
	public ActionForward seleccionarEclos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		ReporteEventosForm reForm = (ReporteEventosForm) form;
		String todasEclos = request.getParameter("todas");
		if(todasEclos!=null){
			request.setAttribute("mostrarDescarga","si");
			request.setAttribute("inicio","no");
			request.setAttribute("mostrar","no");
			request.setAttribute("todas","si");
			request.getSession().removeAttribute("selected_eclos");
			reForm.setIdEclo("todas");
			reForm.setIdEclos("todas");
			reForm.setListaEclos(new ArrayList());
			reForm.setIdEclosSession("");
			request.setAttribute("inicio","si");
			return mapping.findForward("success");		
		}
		List s = saveSelectedEclosIDs(request);
		if(s.isEmpty()){
			request.setAttribute("error","DEBE SELECCIONAR ALGUNA ECLO");
			return mapping.findForward("initBuscarEclos");
		}
		else{
			String eclos = s.toString();
			eclos= eclos.replace("[","");
			eclos= eclos.replace("]",",");
			eclos=eclos.replace(" ","");
			List ecloss = EcloDAO.findByIds(eclos);
			reForm.setListaEclos(ecloss);
			request.setAttribute("mostrarDescarga","si");
			if(ecloss.size() == 1){
				Iterator it = ecloss.iterator();
				reForm.setIdEclo(((Eclo)it.next()).getId().toString());
				request.setAttribute("inicio","no");
				request.setAttribute("mostrar","si");
			}
			else{
				request.setAttribute("inicio","no");
				request.setAttribute("mostrar","no");
			}
			request.getSession().removeAttribute("selected_eclos");
			request.setAttribute("todas","no");
			return mapping.findForward("success");		
		}
	}
	
	public ActionForward buscarEstablecimientos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		ReporteEventosForm reForm = (ReporteEventosForm) form;
		Long idEst=null;
		try{
			if(!reForm.getIdEst().equals(""))
				idEst = new Long(reForm.getIdEst());
			}
			catch (Exception e) {
				request.setAttribute("error","EL CAMPO ID DEBE SER UN NUMERO");
				return mapping.findForward("initBuscarEstablecimiento");			
			}
		/*	List eclos = new ArrayList();
		if(reForm.getIdEclos()!=null){
			String[] strEclos = reForm.getIdEclos().split(",");
			
		}*/
		Long idEclo = new Long(reForm.getIdEclo());
		Eclo ecloAux = EcloDAO.findByPrimaryKey(idEclo);
		List establecimientos = EstablecimientoDAO.findEstablecimientos(ecloAux, reForm.getNombreEstablecimiento(), reForm.getIdEst(),new ArrayList<String>());
		//List establecimientos = EstablecimientoDAO.findEstablecimientosEclos(eclos, reForm.getNombreEstablecimiento(), reForm.getIdEst(),new ArrayList<String>());
		
		if((establecimientos.isEmpty())&&(idEst!=null)){
			Establecimiento est = EstablecimientoDAO.findByPrimaryKey(idEst);  
			if(est == null){
				request.setAttribute("error","EL TAMBO NO EXISTE"); // ANTES ESTABLECIMIENTO
				return mapping.findForward("initBuscarEstablecimiento");			
			}
			else{
				if(!establecimientos.contains(est)){
					request.setAttribute("error","EL TAMBO PERTENECE A OTRA ECLO"); // ANTES ESTABLECIMIENTO
					return mapping.findForward("initBuscarEstablecimiento");		
				}
					
			}
		}
		request.setAttribute("listaEstablecimientos", establecimientos);
		return mapping.findForward("initBuscarEstablecimiento");		
	}
	
	public ActionForward seleccionarEstablecimiento(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		String idEstablecimiento = request.getParameter("id");
		ReporteEventosForm reForm = (ReporteEventosForm) form;
		reForm.setIdEst(idEstablecimiento);
		request.setAttribute("idEs",idEstablecimiento);
		request.setAttribute("nombreEstablecimiento", EstablecimientoDAO.findByPrimaryKey(Long.valueOf(idEstablecimiento)).getNombreContacto());
		request.setAttribute("mostrarDescarga","si");
		request.setAttribute("todas","no");
		return mapping.findForward("success");		
	}
	/**
	 * Metodo que se encarga se generar el reporte por eventos, dado una o varias eclos y filtrando tambien por tambo
	 * e informando un rango de fechas se recuperan todos los eventos dentro del rango.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward descargarReporteEventos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		ReporteEventosForm formE = (ReporteEventosForm)form;
		List ec = formE.getListaEclos();
		Date fechaI = new Date();
		fechaI= this.parse(formE.getFechaInic(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= this.parse(formE.getFechaFin(),"dd/MM/yyyy");
		if(ec.isEmpty()&& !formE.getIdEclo().equals("todas")){
			request.setAttribute("error","DEBE BUSCAR ALGUNA ECLO");
			request.setAttribute("inicio","si");
			request.setAttribute("mostrarDescarga","si");
			request.setAttribute("mostrar","no");
			return mapping.findForward("success");
		}
		else
			request.setAttribute("mostrarDescarga","si");
		if(ec.size()==1)
			request.setAttribute("mostrar","si");
		else
			request.setAttribute("mostrar","no");
		if(formE.getFechaInic().equals("")){
			request.setAttribute("error","LA FECHA INICIO NO PUEDE SER VACIA");
			return mapping.findForward("success");
		}
		if(formE.getFechaFin().equals("")){
			request.setAttribute("error","LA FECHA FIN NO PUEDE SER VACIA");
			return mapping.findForward("success");
		}
		if(fechaI == null){
			request.setAttribute("error","LA FECHA INICIO TIENE FORMATO ERRONEO");
			return mapping.findForward("success");
		}
		if(fechaF == null){
			request.setAttribute("error","LA FECHA FIN TIENE FORMATO ERRONEO");
			return mapping.findForward("success");
		}
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		//Eclo eclo = null;
		//Set procLotes = null;
		///Resumen a = new Resumen();
		//Map mResumen = a.cargarEventos();
		Establecimiento estab = null;
		if(ec.size()==1 && StringUtils.isNotEmpty(formE.getIdEst()) )
			estab = EstablecimientoDAO.findByPrimaryKey(Long.parseLong(formE.getIdEst()));
		 //Hashtable h = new Hashtable();
		 Map parametros = new HashMap();
		// parametros.put("ECLO",);
		 Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		Long idRegional = usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL) ? usuarioActual.getContacto().getId() : null;
			//List eclos = EcloDAO.findEclos(reForm.getNombreEclo(), reForm.getIdEclo());
			String nombreRegional = "";
			EntidadRegional regional =null;
			if(idRegional!=null){
				regional = EntidadRegionalDAO.findByPrimaryKey(idRegional);
				nombreRegional = regional.getNombreContacto();
			}
		 
		 List eclos = new ArrayList();
	    	if(ec.isEmpty()){
	    	 	//ec = EcloDAO.findAllOrderById();
	    		ec = EcloDAO.findAllOrderByIdAndRegional(regional);
	    	}
	    	
	    	String eclosIds= "";
	    	Iterator it = ec.iterator();
		    	while(it.hasNext()){
					Eclo ecl = (Eclo)it.next();
					eclos.add(ecl.getId());
					eclosIds = (eclosIds.equals(""))?ecl.getId().toString():eclosIds + ", "+ ecl.getId();
				}
		 parametros.put("ECLOS", eclosIds);
		 parametros.put("TAMBOS", (estab!=null)?estab.getIdNombre()+ " PROPIETARIO:" +estab.getPropietario().getIdNombre():"TODOS");
		 Long cantidadEventosAlta = ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtAlta");
		 parametros.put("ALTA",cantidadEventosAlta);
		 parametros.put("BAJAANIMAL",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtBaja"));
		 parametros.put("BAJAEVENTO",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtBajaEvento"));
		 parametros.put("CONTROL",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtControlEstablecimiento"));
		 parametros.put("CAMBIORP",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtCambioRp"));
		 parametros.put("ESTADO",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtEstado"));
		 parametros.put("INFO",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtInfo"));
		 parametros.put("PRENEZ",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtPrenez"));
		 Long cantidadEventosReproduccion = ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtReproduccion");
		 parametros.put("PARTO",cantidadEventosReproduccion);
		 parametros.put("SECADA",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtSecada"));
		 parametros.put("SERVICIO",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtServicio"));
		 parametros.put("TRANS",ProcEvtDAO.cantidadEventos(eclos,estab,fechaI,fechaF,"EvtTransferencia"));
		 Long cantidadHembrasAltas = ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,true,true);
		 Long cantidadMachosAltas = ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,false,true);
		 Long cantidadHembrasParto = ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,true,false);
		 Long cantidadMachosParto = ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,false,false);
		// Long cantidadHembras = 0L;
		// Long cantidadMachos = 0L;
		// ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,true,cantidadHembrasAltas,cantidadHembrasParto);
		// ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,false,cantidadMachosAltas,cantidadMachosParto);
		 //Long cantidadHembras = ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,true,cantidadHembrasAltas,cantidadHembrasParto);
		 //Long cantidadMachos = ProcEvtDAO.cantidadSexoEnEventos(eclos,estab,fechaI,fechaF,false,cantidadMachosAltas,cantidadMachosParto);
		 parametros.put("HEMBRAS", cantidadHembrasAltas+cantidadHembrasParto);
		 parametros.put("MACHOS",cantidadMachosAltas+cantidadMachosParto );
		 parametros.put("HEMBRAS_ALTA", cantidadHembrasAltas);
		 parametros.put("HEMBRAS_PARTO", cantidadHembrasParto);
		 parametros.put("MACHOS_ALTA", cantidadMachosAltas);
		 parametros.put("MACHOS_PARTO", cantidadMachosParto);
		 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "dd/MM/yyyy"));
		 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "dd/MM/yyyy"));
		 String nombreGenerado =  "Resumen_de_Eventos.pdf";
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(nombreGenerado));
			try {
				InputStream is=	new ByteArrayInputStream(report.makeReportResumenEventos(parametros));
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
		

		/*if(!formE.getIdEclo().equals("")){
			eclo = EcloDAO.findByPrimaryKey(new Long(formE.getIdEclo()));
			procLotes = eclo.getProcLotes();
			cargarMap(a,mResumen,formE.getIdEst(),fechaI,fechaF,procLotes);
		}
		else{
			Iterator it = ec.iterator();
			while(it.hasNext()){
				Eclo ecl = (Eclo)it.next();
				eclo = EcloDAO.findByPrimaryKey(ecl.getId());
				procLotes = eclo.getProcLotes();
				cargarMap(a,mResumen,"",fechaI,fechaF,procLotes);
			}
		}
		Collection resumen = new LinkedList();
		resumen.addAll(mResumen.values());
		String nombreGenerado =  "Resumen_de_Eventos.pdf";
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(nombreGenerado));
		try {
			InputStream is=	new ByteArrayInputStream(report.makeReportEventos(resumen));
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
		}*/
	
	
	return null;
		
	}
	private void cargarMap(Resumen a,Map mResumen,String idEstab,Date fechaI,Date fechaF,Set procLotes ){
		if(!idEstab.equals("")){
			Establecimiento est = EstablecimientoDAO.findByPrimaryKey(new Long(idEstab));
			Iterator it = procLotes.iterator();
			while(it.hasNext()){
				ProcLote p = (ProcLote)it.next();
				//si esta entre ls fechas pasadas
				if(estaEntre(p.getProcProces().getFecha(),fechaI,fechaF))
				//if(DateUtils.entre(p.getProcProces().getFecha(),fechaI,fechaF))
					a.contarEventosAceptadosEstab(p,mResumen,est.getId());
			}
		}
		else{
			Iterator it2 = procLotes.iterator();
			while(it2.hasNext()){
				ProcLote p = (ProcLote)it2.next();
				if(estaEntre(p.getProcProces().getFecha(),fechaI,fechaF))
				//if(DateUtils.entre(p.getProcProces().getFecha(),fechaI,fechaF))
						a.contarEventosAceptados(p,mResumen);
			}
		}
	}
	/**
	 * No se usa Date Utis ya que la fecha de fin esta tomada a las 00 horas
	 *  del dia entonces los procesamientos
	 * del mismo dia no entrarian en el intervalo
	 * @param fech
	 * @param fechaI
	 * @param fechaF
	 * @return
	 */
	private boolean estaEntre(Date fech, Date fechaI, Date fechaF) {
		Date fecha = new Date(fech.getTime());
			if(((fecha.after(fechaI))&&(fecha.before(fechaF)))||(DateUtils.mismoDia(fecha,fechaI))||(DateUtils.mismoDia(fecha,fechaF)))
				return true;
		return false;
	}
	
	public Date parse(String s, String s1) 
	{
	   
		try
	    {
	        SimpleDateFormat simpledateformat;
	        (simpledateformat = new SimpleDateFormat(s1 != null ? s1 : "dd-MM-yyyy HH:mm:ss")).setLenient(false);
	      
	        return simpledateformat.parse(s);
	    }
	    catch(Exception exception)
	    {
	        return null;
	    }
	}
	private String getContextPath(String subPath)
	{
		return getServlet().getServletContext().getRealPath(subPath);
	}
	
}
