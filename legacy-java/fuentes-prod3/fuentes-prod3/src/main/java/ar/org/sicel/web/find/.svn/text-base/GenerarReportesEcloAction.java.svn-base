package ar.org.sicel.web.find;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.impl.SQLQueryImpl;

import ar.org.sicel.googleChart.ControlesLactanciaChart;
import ar.org.sicel.googleChart.ControlesTotalesLactanciasChart;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Contacto;
import ar.org.sicel.persistence.ContactoDAO;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.EventosPorFechaYTipo;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Lactancia;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.jasperReport.LactanciasDataSource;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.RCHBANumePagination;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;

public class GenerarReportesEcloAction extends DispatchAction {
	boolean RCNUM = false;

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RCNUM = false;
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		reForm.reset();
		if (request.getSession().getAttribute("reportesEcloForm") != null)
			request.getSession().removeAttribute("reportesEcloForm");
		return mapping.findForward("success");
	}

	public ActionForward initRCNUM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RCNUM = true;
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		reForm.reset();
		
		request.setAttribute("unEst", "si");
		request.setAttribute("unEstTodos", "si");
		request.setAttribute("unEstPropios", "si");
		request.setAttribute("tambos", "todos");
		request.setAttribute("unPro", "si");
		request.setAttribute("unEcl", "si");
		request.setAttribute("animales", "propios");
		request.setAttribute("calificados", "si");
		request.setAttribute("orden", "0");
		request.setAttribute("tamboId", "");
		request.setAttribute("indiceCombo",0);
		if (request.getSession().getAttribute("reportesEcloForm") != null)
			request.getSession().getAttribute("reportesEcloForm");
		
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		if(user.getContacto()!=null){
			Contacto contac = ContactoDAO.findByPrimaryKey(user.getContacto().getId());
			if (user.getRol().getNombre().equalsIgnoreCase("PROPIETARIO")){
				reForm.setIdProp(contac.getId().toString());
				reForm.setNombreProp(contac.getNombreContacto());
				request.setAttribute("propId", contac.getId().toString());
				request.setAttribute("ecloId", "");
				request.setAttribute("eclosOrPropietarios","propietarios");
				reForm.setEcloIdCombo(null);
				List eclos = EcloDAO.findAllOrderById();
				request.setAttribute("eclos",eclos);
			} else if(user.getRol().getNombre().equalsIgnoreCase("REGIONAL")){
				reForm.setIdEclo("");
				reForm.setNombreEclo("");
				request.setAttribute("propId","");
				request.setAttribute("ecloId","");
				request.setAttribute("eclosOrPropietarios","");
				reForm.setEcloIdCombo(null);
				List eclos = EcloDAO.findAllOrderByIdAndRegional(contac);
				request.setAttribute("eclos",eclos);
				}else{ 
					user.getRol().getNombre().equalsIgnoreCase("PROVEEDOR");
					reForm.setIdEclo(contac.getId().toString());
					reForm.setNombreEclo(contac.getNombreContacto());
					request.setAttribute("propId", "");
					request.setAttribute("ecloId", contac.getId().toString());
					request.setAttribute("eclosOrPropietarios","eclos");
					reForm.setEcloIdCombo(new Integer(contac.getId().intValue()));
					request.setAttribute("eclos",new ArrayList());
				}
		}
		else{ //entra como ADMIN o GENERAL, tiene que elegir propietario o eclo
			reForm.setIdEclo("");
			reForm.setNombreEclo("");
			request.setAttribute("propId","");
			request.setAttribute("ecloId","");
			request.setAttribute("eclosOrPropietarios","");
			reForm.setEcloIdCombo(null);
			List eclos = EcloDAO.findAllOrderById();
			request.setAttribute("eclos",eclos);
		}
		
		
		request.setAttribute("tipoLogin",user.getRol().getNombre());
		return mapping.findForward("successRC");
	}

	public ActionForward initBuscarEstablecimiento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReportesEcloForm reForm = (ReportesEcloForm) form;
		copyParameters(request,form);
		reForm.setIdEstablecimiento("");
		reForm.setNombreEstablecimiento("");
		cargarEclos(request);
		
		return mapping.findForward("initBuscarEstablecimiento");
	}

	public ActionForward initBuscarPropietario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// System.out.println("iniciando la busqueda de PROPIETARIO");
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		copyParameters(request,form);
		reForm.setIdProp("");
		reForm.setNombreProp("");
		cargarEclos(request);
		return mapping.findForward("initBuscarPropietario");
	}
	
	public ActionForward initBuscarEclo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// System.out.println("iniciando la busqueda de PROPIETARIO");
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		reForm.setIdEclo("");
		reForm.setNombreEclo("");
		copyParameters(request,form);
		return mapping.findForward("initBuscarEclo");
	}

	public ActionForward buscarPropietario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReportesEcloForm reForm = (ReportesEcloForm) form;
		Eclo ecloAux = null;
		List propietarios =null;
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		if (user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			ecloAux = EcloDAO.findByPrimaryKey(user.getContacto().getId());
		else {
			 String ecloId = (String)request.getParameter("ecloId");
			 if (!ecloId.equalsIgnoreCase("")){
				 ecloAux = EcloDAO.findByPrimaryKey(new Long(ecloId));
			 }
		}
		if(ecloAux!=null)
			propietarios = PropietarioDAO.findPropietariosPorEclo(ecloAux.getId().toString(),reForm.getIdProp(), reForm.getNombreProp());
		else
			propietarios = PropietarioDAO.findPropietariosPorIDyNmbre(reForm.getIdProp(), reForm.getNombreProp());
		copyParameters(request,form);
		if (propietarios.isEmpty()) {
			request.setAttribute("error", "EL PROPIETARIO NO EXISTE");
			return mapping.findForward("initBuscarPropietario");
		}
		request.setAttribute("propietarios", propietarios);
		return mapping.findForward("initBuscarPropietario");
	}

	public ActionForward seleccionarPropietario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String idProp = request.getParameter("id");
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		Propietario p = PropietarioDAO.findByPrimaryKey(Long.valueOf(idProp));
		reForm.setIdProp(idProp);
		reForm.setNombreProp(p.getNombreContacto());
		((ReportesEcloForm)form).setPropId(idProp.toString());
		copyParameters(request,form);
		if (RCNUM) {
			if (!reForm.getIdProp().equals(""))
				request.setAttribute("unPro", "si");
			else
				request.setAttribute("unPro", "no");
			cargarEclos(request);
			return mapping.findForward("successRC");
		}
		return mapping.findForward("success");
	}

	public ActionForward buscarEstablecimientos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReportesEcloForm reForm = (ReportesEcloForm) form;

		Long idEst = null;
		try {
			if (!reForm.getIdEstablecimiento().equals("")) {
				idEst = new Long(reForm.getIdEstablecimiento());
			}
		} catch (Exception e) {
			request.setAttribute("error", "EL CAMPO ID DEBE SER UN NUMERO");
			return mapping.findForward("initBuscarEstablecimiento");
		}

		String unProp = (String)request.getParameter("unPro");
		String unEcl = (String)request.getParameter("unEcl");
		String tambos = (String)request.getParameter("tambos");
		String propId = (String)request.getParameter("propId");
 		String ecloId = (String)request.getParameter("ecloId");

 		Session session = HibernateFactory.getSession();
 		String condicion1 = (unEcl.equalsIgnoreCase("si") && !ecloId.equalsIgnoreCase("") ? " est.eclo.id = "+ecloId+" " : "");
 		String condicion2 = (unProp.equalsIgnoreCase("si") && !propId.equalsIgnoreCase("") && tambos.equalsIgnoreCase("propios") && !propId.equalsIgnoreCase("") ? " p.id = "+propId+" and p.id = est.propietario.id " : 
		      unProp.equalsIgnoreCase("no") &&  tambos.equalsIgnoreCase("propios") ? " p.id = est.propietario.id " : "");
 		String condicion3 = (!reForm.getNombreEstablecimiento().equalsIgnoreCase("") ? " est.nombreContacto LIKE '" + reForm.getNombreEstablecimiento() + "' " : "");
 		String condicion4 = (idEst != null ? " est.id = "+idEst.toString() : "");
 		String where = "";
 		
 		if (!condicion1.equalsIgnoreCase("") ||
 			!condicion2.equalsIgnoreCase("") ||
 			!condicion3.equalsIgnoreCase("") ||
 			!condicion4.equalsIgnoreCase("")){
 			where = " where ";
 			
 			if (!condicion1.equalsIgnoreCase(""))
 				where = where + condicion1;
 			
 			if (!condicion1.equalsIgnoreCase("") && !condicion2.equalsIgnoreCase(""))
 				where = where + " and " + condicion2;
 			else
 				where = where + condicion2;
 			
 			if (!condicion2.equalsIgnoreCase("") && !condicion3.equalsIgnoreCase(""))
 				where = where + " and " + condicion3;
 			else if (!condicion1.equalsIgnoreCase("") && !condicion3.equalsIgnoreCase(""))
 				where = where + " and " + condicion3;
 			else where = where + condicion3;
 			
 			if (!condicion3.equalsIgnoreCase("") && !condicion4.equalsIgnoreCase(""))
 				where = where + " and " + condicion4;
 			else if (!condicion2.equalsIgnoreCase("") && !condicion4.equalsIgnoreCase(""))
 				where = where + " and " + condicion4;
 			else if (!condicion1.equalsIgnoreCase("") && !condicion4.equalsIgnoreCase(""))
 				where = where + " and " + condicion4;
 			else where = where + condicion4;
 		}
 			
 			
    	String queryString = "select est "+
    						 "from Establecimiento est " + 
    						 (tambos.equalsIgnoreCase("propios")? ",Propietario p " : "") + 
    						 where;

    	Query query = session.createQuery(queryString);
    	List establecimientos = query.list();
    	copyParameters(request,form);
		
		if ((establecimientos.isEmpty()) && (idEst != null)) {
			Establecimiento est = EstablecimientoDAO.findByPrimaryKey(idEst);
			if (est == null) {
				request.setAttribute("error", "EL TAMBO NO EXISTE");
				return mapping.findForward("initBuscarEstablecimiento");
			} else {
				if (!establecimientos.contains(est)) {
					request.setAttribute("error",
							"EL TAMBO PERTENECE A OTRA ECLO");
					return mapping.findForward("initBuscarEstablecimiento");
				}

			}
		} else if (establecimientos.isEmpty()){
				request.setAttribute("error", "No hay tambos para mostrar. Volver a la pantalla inicial de RC-HBANUME.");
				return mapping.findForward("initBuscarEstablecimiento");
				} 
		request.setAttribute("listaEstablecimientos", establecimientos);

		return mapping.findForward("initBuscarEstablecimiento");
	}

	public ActionForward seleccionarEstablecimiento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String idEstablecimiento = request.getParameter("id");
		if (idEstablecimiento != null && !idEstablecimiento.equalsIgnoreCase("")){
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		reForm.setIdEstablecimiento(idEstablecimiento);
		reForm.setNombreEstablecimiento(EstablecimientoDAO.findByPrimaryKey(
				Long.valueOf(idEstablecimiento)).getNombreContacto());
		((ReportesEcloForm)form).setTamboId(idEstablecimiento);
		request.setAttribute("tamboId", idEstablecimiento);
		copyParameters(request,form);
		}
		
		if (RCNUM) {
			cargarEclos(request);
			return mapping.findForward("successRC");
		}
		return mapping.findForward("success");
	}
	
	public ActionForward buscarEclos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReportesEcloForm reForm = (ReportesEcloForm) form;

		Long idEcl = null;
		try {
			if (!reForm.getIdEclo().equals("")) {
				idEcl = new Long(reForm.getIdEclo());
			}
		} catch (Exception e) {
			request.setAttribute("error", "EL CAMPO ID DEBE SER UN NUMERO");
			return mapping.findForward("initBuscarEclo");
		}
		
		List<Eclo> eclos = new Vector<Eclo>();
		if (!reForm.getIdEclo().equals("") && !reForm.getNombreEclo().equals("")){
			Eclo ecloAux = EcloDAO.findByIdAndNombreContacto(idEcl,reForm.getNombreEclo()); 
			if (ecloAux != null)
				eclos.add(ecloAux);
		}else if (!reForm.getIdEclo().equals("")){
			Eclo ecloAux = EcloDAO.findByPrimaryKey(idEcl); 
			if (ecloAux != null)
				eclos.add(ecloAux);
		}else if (!reForm.getNombreEclo().equals(""))
			eclos = EcloDAO.findByNombreContacto(reForm.getNombreEclo());
		else
			eclos = EcloDAO.findAll();
	
		if ((eclos.isEmpty()) && (idEcl != null)) {
			Eclo ecloAux = EcloDAO.findByPrimaryKey(idEcl);
			if (ecloAux == null) {
				request.setAttribute("error", "LA ECLO NO EXISTE");
				return mapping.findForward("initBuscarEclo");
			} else {
				if (!eclos.contains(ecloAux)) {
					request.setAttribute("error",
							"LA ECLO NO EXISTE CON ESE NOMBRE");
					return mapping.findForward("initBuscarEclo");
				}

			}
		}
		request.setAttribute("listaEclos", eclos);
		copyParameters(request,form);

		return mapping.findForward("initBuscarEclo");
	}

	public ActionForward seleccionarEclo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String idEclo = request.getParameter("id");
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		
		reForm.setIdEclo(idEclo);
		reForm.setNombreEclo(EcloDAO.findByPrimaryKey(
				Long.valueOf(idEclo)).getNombreContacto());
		reForm.setEcloId(idEclo.toString());
		reForm.setEclosOrPropietarios("eclos");
		copyParameters(request,form);
		cargarEclos(request);
		return mapping.findForward("successRC");
	}
	
	public ActionForward initBuscar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
				
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		reForm.reset();
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		return mapping.findForward("success");
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		if (isCancelled(request)){
			reForm.reset();
			return (mapping.findForward("main"));
		}
		else{			
			//try{
				Usuario usuario = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);	       
		        Animal animal = AnimalDAO.findByRegistryAndUser(reForm.getTipoReg(), reForm.getNumReg(), reForm.getRaza(), "F", usuario);
		        List razas = RazaDAO.findAll();
				request.setAttribute("razas",razas);
		        if (animal!=null) {	
					request.setAttribute("animal", animal);
					List lactancias = ((Hembra)animal).getLactanciasCerradas();
					Collections.sort((List) lactancias, new EventosPorFechaYTipo());
//					if(((Hembra)animal).getLactanciaEnCurso()!= null)
//						lactancias.add(0, ((Hembra)animal).getLactanciaEnCurso());
					request.setAttribute("enCurso", ((Hembra)animal).getLactanciaEnCurso());
					request.setAttribute("lactancias", lactancias);
					return mapping.findForward("success");
				}
				else{
					ActionMessages msgs = new ActionMessages();
					msgs.add("error", new ActionMessage("error", "No existe el animal con los datos ingresados en esta eclo"));
					saveMessages(request, msgs);
					return mapping.findForward("success");
				}
				
			/*
			
				Eclo ecloAux = null;
				Usuario user = (Usuario) request.getSession().getAttribute(
						Tokens.CURRENTUSER);
				if (user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
					ecloAux = EcloDAO.findByPrimaryKey(user.getContacto().getId());
				
				
				Animal animal = AnimalDAO.findByRegistryAndUser(reForm.getTipoReg(), reForm.getNumReg(),reForm.getRaza(),"F",user);
			
				if (animal!=null) {		
					List razas = RazaDAO.findAll();
					request.setAttribute("razas",razas);
					request.setAttribute("animal", animal);
					return mapping.findForward("success");
				}
				else{
					ActionMessages msgs = new ActionMessages();
					msgs.add("error", new ActionMessage("error", "No existe animal con los datos ingresados"));
					saveMessages(request, msgs);
					return mapping.findForward("success");
				}
			/*}
			catch (ExcepcionIntegridad e1) {
				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(), ProcMsg.ERROR,e1.getValores());				
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
			}			*/
		}
	}

	public ActionForward seleccionarAnimal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReportesEcloForm rForm = (ReportesEcloForm) form;
		Long idAn = null;
		if (rForm.getAnimalId() == null)
			idAn = Long.parseLong((String) request.getAttribute("id"));
		else
			idAn = rForm.getAnimalId();
		rForm.setAnimalId(idAn);
		Animal animal = AnimalDAO.findByPrimaryKey(idAn);
		if (!animal.esHembra()) {
			ActionMessages msgs = new ActionMessages();
			msgs.add("error", new ActionMessage("error", "El animal es Macho"));
			saveMessages(request, msgs);
			return mapping.findForward("success");
		}
		request.setAttribute("animal", animal);
		return mapping.findForward("success");

	}

	@SuppressWarnings("deprecation")
	public ActionForward descargarExportacionPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		String unEst = request.getParameter("unEst");
		String unPro = request.getParameter("unPro");
		String calificados = request.getParameter("calificados").toString().equals("si") ? "and hijos.ID = calificaciones.ANIMAL and calificaciones.PUNTAJE IS NOT NULL and" :
			                 request.getParameter("calificados").toString().equals("no") ? "and hijos.ID = calificaciones.ANIMAL and calificaciones.PUNTAJE IS NULL and" :
							 "and hijos.ID = calificaciones.ANIMAL and";
		String orden = request.getParameter("orden").toString().equals("0") ? "order by RP" :
            				 request.getParameter("orden").toString().equals("1") ? "order by FNAC" :
            				 "order by RP,FNAC";
		// System.out.println(unEs);
		// Long id = new Long(idParameter);
		// System.out.println("EXPORTACION PDF");
		Eclo eclo = null;
		Usuario user = (Usuario) request.getSession().getAttribute(
				Tokens.CURRENTUSER);

		if (user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			eclo = EcloDAO.findByPrimaryKey(user.getContacto().getId());
		else {
			 String ecloId = (String)request.getParameter("ecloId");
			 if (!ecloId.equalsIgnoreCase("")){
				 eclo = EcloDAO.findByPrimaryKey(new Long(ecloId));
			 }
		}
		
		Long idEstablecimiento;
		if (unEst.equals("si"))
			idEstablecimiento = Long.valueOf(reForm.getTamboId());
		else
			idEstablecimiento = null;
		
		Long idPropietario;
		if (unPro.equals("si"))
			idPropietario = Long.valueOf(reForm.getPropId());
		else
			idPropietario = null;

		
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirImagenesReportes").getValue();
		// System.out.println(REPORTES_DIR);
		ProjectReport report = new ProjectReport(this
				.getContextPath(REPORTES_DIR), IMAGENES_DIR);
		// String idParameter = request.getParameter("id");

		String nombreGenerado = "Reporte_RC-HBA_NUM_eclo" + (eclo != null ? eclo.getId() : "") + ".pdf";
		String pagina = request.getParameter("pagina");
		String queryEncabezado = pagina == null ? "select count(*) as CANTIDAD " : 
			"select hijos.ECLO as ECLO,hijos.PROPIETARIO as PROPIETARIO,hijos.ESTAB as TAMBO,hijos.RAZADECLARA,hijos.RP,hijos.FNAC,hijos.CATEGORIA,reg.TREG as TIPOREG,reg.NUMERO as NUMEROREG,hijos.ESHEMBRA as SEXO,calificaciones.FECHA_CALIF,calificaciones.PUNTAJE,madres.RP as MADRERP,madres.TREG as TIPOREGMADRE,madres.NUMERO as NROREGMADRE,madres.CATEGORIA as CATEGORIAMADRE,madres.RAZADECLARA as RAZAMADRE,padres.NUMERO as NUMEROPADRE,padres.RAZADECLARA as RAZAPADRE";
		String queryCuerpo = " from (select hijo.ID,madre.RP,madre.CATEGORIA,regMadre.TREG,regMadre.NUMERO,madre.RAZADECLARA"+
	      " from an_animal hijo,an_animal madre,en_establecimiento est,an_registro regMadre"+
	      " where hijo.MADREGEN = madre.ID (+) and madre.REGORI = regMadre.ID (+) and ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+" is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = hijo.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or hijo.ESTAB  = "+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+")) madres,"+
	     " (select hijo.ID,regPadre.NUMERO,padre.RAZADECLARA"+
	      " from an_animal hijo,an_animal padre,en_establecimiento est,an_registro regPadre"+
	      " where hijo.PADRE = padre.ID (+) and padre.REGORI = regPadre.ID (+) and ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+" is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = hijo.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or hijo.ESTAB  = "+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+")) padres,"+
	     " (select anim.*,est.ECLO"+ 
	     " from an_animal anim,en_establecimiento est"+ 
	     " where ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString()) +"is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = anim.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or anim.ESTAB = "+(idEstablecimiento==null? "NULL" : idEstablecimiento.toString())+") and ("+(idPropietario==null? " NULL " : idPropietario.toString())+" is NULL or anim.PROPIETARIO = "+(idPropietario==null? "NULL" : idPropietario.toString())+")) hijos,"+
	    " (select calificacion.ANIMAL,max(calificacion.fecha) as FECHA_CALIF,max(calificacion.puntaje) as PUNTAJE"+ 
	     " from (select an_animal.id as ANIMAL,ca_calif.fecha,ca_calif.puntaje"+
	           " from ca_calif,an_animal,en_establecimiento est"+
	           " where an_animal.id = ca_calif.animal (+) and ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString()) +" is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = an_animal.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or an_animal.ESTAB = "+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+")) calificacion"+
	           " group by calificacion.ANIMAL) calificaciones,"+
	      " an_registro reg"+(reForm.getTambos().equals("propios") ? ",en_establecimiento est" : " ")+
	    " where hijos.ID = madres.ID and hijos.ID = padres.ID "+calificados+" hijos.REGORI = reg.ID and (reg.TREG = 'RC' or reg.TREG = 'HBA') "+
			(reForm.getUnEst().equals("si") ? " and hijos.ESTAB = " + idEstablecimiento.toString() : " ")+
			(reForm.getTambos().equals("propios") ? " and hijos.PROPIETARIO = est.PROPIETARIO and hijos.ESTAB = est.ID "+ (unPro.equals("si") && reForm.getAnimales().equals("propios") ? " and hijos.PROPIETARIO = " + idPropietario.toString() : " ") : " ");
		String queryPaginado = pagina != null ? " and rownum <= " + (new Integer((new Integer(pagina)+1)*10000)).toString():""; /*+ " and rownum <= " + (new Integer((new Integer(pagina)+1)*10000)).toString()*/ 
		if (pagina == null){
			Session session = HibernateFactory.getSession();
			SQLQuery sqlQuery = session.createSQLQuery(queryEncabezado+" "+queryCuerpo+" "+queryPaginado);
			sqlQuery.addScalar( "CANTIDAD", Hibernate.LONG);
			Long cant = new Long(sqlQuery.uniqueResult().toString());
			if (cant > 10000){
				List<RCHBANumePagination> pagination = armarPaginado(cant);
				copyParameters(request,form);
				request.setAttribute("paginacion",pagination);
				request.setAttribute("pagina","");
				request.setAttribute("cantidadTotalPaginas",cant);
				return mapping.findForward("rchbanumepagination");
			}
		}
		if (!queryPaginado.equals(""))
			queryEncabezado = "select * from (select rownum numeroFila,hijos.ECLO as ECLO,hijos.PROPIETARIO as PROPIETARIO,hijos.ESTAB as TAMBO,hijos.RAZADECLARA,hijos.RP,hijos.FNAC,hijos.CATEGORIA,reg.TREG as TIPOREG,reg.NUMERO as NUMEROREG,hijos.ESHEMBRA as SEXO,calificaciones.FECHA_CALIF,calificaciones.PUNTAJE,madres.RP as MADRERP,madres.TREG as TIPOREGMADRE,madres.NUMERO as NROREGMADRE,madres.CATEGORIA as CATEGORIAMADRE,madres.RAZADECLARA as RAZAMADRE,padres.NUMERO as NUMEROPADRE,padres.RAZADECLARA as RAZAPADRE";
		else
			queryEncabezado = "select hijos.ECLO as ECLO,hijos.PROPIETARIO as PROPIETARIO,hijos.ESTAB as TAMBO,hijos.RAZADECLARA,hijos.RP,hijos.FNAC,hijos.CATEGORIA,reg.TREG as TIPOREG,reg.NUMERO as NUMEROREG,hijos.ESHEMBRA as SEXO,calificaciones.FECHA_CALIF,calificaciones.PUNTAJE,madres.RP as MADRERP,madres.TREG as TIPOREGMADRE,madres.NUMERO as NROREGMADRE,madres.CATEGORIA as CATEGORIAMADRE,madres.RAZADECLARA as RAZAMADRE,padres.NUMERO as NUMEROPADRE,padres.RAZADECLARA as RAZAPADRE";
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		try {

			InputStream is = new ByteArrayInputStream(report												
					.makeRCNUMPDF(queryEncabezado+" "+queryCuerpo+" "+queryPaginado+(!queryPaginado.equals("") ? ") datos where datos.numeroFila >= "+(new Integer((new Integer(pagina))*10000+1)).toString():" ")+" "+orden));
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

	@SuppressWarnings({ "deprecation", "deprecation", "deprecation" })
	public ActionForward descargarExportacionTXT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReportesEcloForm reForm = (ReportesEcloForm) form;
		String unEst = request.getParameter("unEst");
		String unPro = request.getParameter("unPro");
		String calificados = request.getParameter("calificados").toString().equals("si") ? "and hijos.ID = calificaciones.ANIMAL and calificaciones.PUNTAJE IS NOT NULL and" :
			                 request.getParameter("calificados").toString().equals("no") ? "and hijos.ID = calificaciones.ANIMAL and calificaciones.PUNTAJE IS NULL and" :
							 "and hijos.ID = calificaciones.ANIMAL and";
		String orden = request.getParameter("orden").toString().equals("0") ? "order by RP" :
            				 request.getParameter("orden").toString().equals("1") ? "order by FNAC" :
            				 "order by RP,FNAC";
		// System.out.println(unEs);
		// Long id = new Long(idParameter);
		// System.out.println("EXPORTACION PDF");
		Eclo eclo = null;
		Usuario user = (Usuario) request.getSession().getAttribute(
				Tokens.CURRENTUSER);

		if (user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			eclo = EcloDAO.findByPrimaryKey(user.getContacto().getId());
		else {
			 String ecloId = (String)request.getParameter("ecloId");
			 if (!ecloId.equalsIgnoreCase("")){
				 eclo = EcloDAO.findByPrimaryKey(new Long(ecloId));
			 }
		}
		
		Long idEstablecimiento;
		if (unEst.equals("si"))
			idEstablecimiento = Long.valueOf(reForm.getTamboId());
		else
			idEstablecimiento = null;
		
		Long idPropietario;
		if (unPro.equals("si"))
			idPropietario = Long.valueOf(reForm.getPropId());
		else
			idPropietario = null;

		
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirImagenesReportes").getValue();
		// System.out.println(REPORTES_DIR);
		ProjectReport report = new ProjectReport(this
				.getContextPath(REPORTES_DIR), IMAGENES_DIR);
		// String idParameter = request.getParameter("id");

		String nombreGenerado = "Reporte_RC-HBA_NUM_eclo" + (eclo != null ? eclo.getId() : "") + ".txt";
		String pagina = request.getParameter("pagina");
		String queryEncabezado = pagina == null ? "select count(*) as CANTIDAD " : 
			"select hijos.ECLO as ECLO,hijos.PROPIETARIO as PROPIETARIO,hijos.ESTAB as TAMBO,hijos.RAZADECLARA,hijos.RP,hijos.FNAC,hijos.CATEGORIA,reg.TREG as TIPOREG,reg.NUMERO as NUMEROREG,hijos.ESHEMBRA as SEXO,calificaciones.FECHA_CALIF,calificaciones.PUNTAJE,madres.RP as MADRERP,madres.TREG as TIPOREGMADRE,madres.NUMERO as NROREGMADRE,madres.CATEGORIA as CATEGORIAMADRE,madres.RAZADECLARA as RAZAMADRE,padres.NUMERO as NUMEROPADRE,padres.RAZADECLARA as RAZAPADRE";
		String queryCuerpo = " from (select hijo.ID,madre.RP,madre.CATEGORIA,regMadre.TREG,regMadre.NUMERO,madre.RAZADECLARA"+
	      " from an_animal hijo,an_animal madre,en_establecimiento est,an_registro regMadre"+
	      " where hijo.MADREGEN = madre.ID (+) and madre.REGORI = regMadre.ID (+) and ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+" is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = hijo.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or hijo.ESTAB  = "+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+")) madres,"+
	     " (select hijo.ID,regPadre.NUMERO,padre.RAZADECLARA"+
	      " from an_animal hijo,an_animal padre,en_establecimiento est,an_registro regPadre"+
	      " where hijo.PADRE = padre.ID (+) and padre.REGORI = regPadre.ID (+) and ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+" is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = hijo.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or hijo.ESTAB  = "+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+")) padres,"+
	     " (select anim.*,est.ECLO"+ 
	     " from an_animal anim,en_establecimiento est"+ 
	     " where ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString()) +"is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = anim.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or anim.ESTAB = "+(idEstablecimiento==null? "NULL" : idEstablecimiento.toString())+") and ("+(idPropietario==null? " NULL " : idPropietario.toString())+" is NULL or anim.PROPIETARIO = "+(idPropietario==null? "NULL" : idPropietario.toString())+")) hijos,"+
	    " (select calificacion.ANIMAL,max(calificacion.fecha) as FECHA_CALIF,max(calificacion.puntaje) as PUNTAJE"+ 
	     " from (select an_animal.id as ANIMAL,ca_calif.fecha,ca_calif.puntaje"+
	           " from ca_calif,an_animal,en_establecimiento est"+
	           " where an_animal.id = ca_calif.animal (+) and ("+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString()) +" is NULL or est.ECLO = "+((eclo == null || eclo.getId() == null || eclo.getId().toString() == null) ? " NULL " : eclo.getId().toString())+") and est.ID = an_animal.ESTAB and ("+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+" is NULL or an_animal.ESTAB = "+(idEstablecimiento==null? " NULL " : idEstablecimiento.toString())+")) calificacion"+
	           " group by calificacion.ANIMAL) calificaciones,"+
	      " an_registro reg"+(reForm.getTambos().equals("propios") ? ",en_establecimiento est" : " ")+
	    " where hijos.ID = madres.ID and hijos.ID = padres.ID "+calificados+" hijos.REGORI = reg.ID and (reg.TREG = 'RC' or reg.TREG = 'HBA') "+
			(reForm.getUnEst().equals("si") ? " and hijos.ESTAB = " + idEstablecimiento.toString() : " ")+
			(reForm.getTambos().equals("propios") ? " and hijos.PROPIETARIO = est.PROPIETARIO and hijos.ESTAB = est.ID "+ (unPro.equals("si") && reForm.getAnimales().equals("propios") ? " and hijos.PROPIETARIO = " + idPropietario.toString() : " ") : " ");
		String queryPaginado = pagina != null ? " and rownum <= " + (new Integer((new Integer(pagina)+1)*10000)).toString():""; /*+ " and rownum <= " + (new Integer((new Integer(pagina)+1)*10000)).toString()*/ 
		if (pagina == null){
			Session session = HibernateFactory.getSession();
			SQLQuery sqlQuery = session.createSQLQuery(queryEncabezado+" "+queryCuerpo+" "+queryPaginado);
			sqlQuery.addScalar( "CANTIDAD", Hibernate.LONG);
			Long cant = new Long(sqlQuery.uniqueResult().toString());
			if (cant > 10000){
				List<RCHBANumePagination> pagination = armarPaginado(cant);
				copyParameters(request,form);
				request.setAttribute("paginacion",pagination);
				request.setAttribute("pagina","");
				request.setAttribute("cantidadTotalPaginas",cant);
				return mapping.findForward("rchbanumepagination");
			}
		}
		if (!queryPaginado.equals(""))
			queryEncabezado = "select * from (select rownum numeroFila,hijos.ECLO as ECLO,hijos.PROPIETARIO as PROPIETARIO,hijos.ESTAB as TAMBO,hijos.RAZADECLARA,hijos.RP,hijos.FNAC,hijos.CATEGORIA,reg.TREG as TIPOREG,reg.NUMERO as NUMEROREG,hijos.ESHEMBRA as SEXO,calificaciones.FECHA_CALIF,calificaciones.PUNTAJE,madres.RP as MADRERP,madres.TREG as TIPOREGMADRE,madres.NUMERO as NROREGMADRE,madres.CATEGORIA as CATEGORIAMADRE,madres.RAZADECLARA as RAZAMADRE,padres.NUMERO as NUMEROPADRE,padres.RAZADECLARA as RAZAPADRE";
		else
			queryEncabezado = "select hijos.ECLO as ECLO,hijos.PROPIETARIO as PROPIETARIO,hijos.ESTAB as TAMBO,hijos.RAZADECLARA,hijos.RP,hijos.FNAC,hijos.CATEGORIA,reg.TREG as TIPOREG,reg.NUMERO as NUMEROREG,hijos.ESHEMBRA as SEXO,calificaciones.FECHA_CALIF,calificaciones.PUNTAJE,madres.RP as MADRERP,madres.TREG as TIPOREGMADRE,madres.NUMERO as NROREGMADRE,madres.CATEGORIA as CATEGORIAMADRE,madres.RAZADECLARA as RAZAMADRE,padres.NUMERO as NUMEROPADRE,padres.RAZADECLARA as RAZAPADRE";
		response.setContentType("application/txt");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		try {

			InputStream is = new ByteArrayInputStream(report												
					.makeRCNUMTXT(queryEncabezado+" "+queryCuerpo+" "+queryPaginado+(!queryPaginado.equals("") ? ") datos where datos.numeroFila >= "+(new Integer((new Integer(pagina))*10000+1)).toString():" ")+" "+orden));
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

	public String replaceDosEOL(String text) {
		if (text == null || text.length() < 2) {
			return text;
		}
		int length = text.length();
		char[] chars = text.toCharArray();
		int r = 0;
		for (int i = 0; i < length; ++i) {
			char ch = chars[i];
			if (!(ch == '\r' && i + 1 < length && chars[i + 1] == '\n')) {
				if (r > 0) {
					chars[i - r] = ch;
				}
			} else {
				++r;
			}
		}
		return r > 0 ? new String(chars, 0, length - r) : text;
	}

	/**
	 * Metodo por el cual se descarga la lactancia en curso de un animal
	 */
	@SuppressWarnings("deprecation")
	public ActionForward descargarLactanciaCurso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		log.debug("El context path del reporte lactancia en curso es: "
				+ REPORTES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);

		Animal an = AnimalDAO.findByPrimaryKey(id);
		String nombreGenerado = "Constancia_Lactancia_Curso_RP_" + an.getRP()
				+ ".pdf";

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(nombreGenerado));

		try {

			InputStream is = new ByteArrayInputStream(report
					.makeReportCertificadosLactancia(an));
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

	@SuppressWarnings("deprecation")
	public ActionForward descargarLactanciaNumero(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer numeroLactancia = new Integer(request
				.getParameter("numLactDescarga"));
		// AnimalForm formA = (AnimalForm)form;
		// Integer numeroLactancia = formA.getNumLactDescarga();
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		log.debug("El context path del reporte lactancia en curso es: "
				+ REPORTES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);
		Animal an = AnimalDAO.findByPrimaryKey(id);
		Hembra he = (Hembra) an;
		Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
		ReportesEcloForm rForm = (ReportesEcloForm) form;
		rForm.reset();
		rForm.setAnimalId(id);
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		request.setAttribute("animal", an);
		if (l == null) {
			// request.setAttribute("mensaje","No existe el la lactancia con el
			// numero informado");
			// return mapping.findForward("confirma");
			ActionMessages msgs = new ActionMessages();
			msgs.add("error", new ActionMessage("error",
					"No existe la lactancia con el numero informado"));
			saveMessages(request, msgs);
			// request.setAttribute("id",idParameter);
			return mapping.findForward("success");
		}
		if (l instanceof EvtLactancia) {
			EvtLactancia evl = (EvtLactancia) l;
			if (!evl.isEsCerrada()) {
				// request.setAttribute("mensaje","La lactancia que desea
				// descargar esta en curso, debe descargarla desde la opcion
				// descargar lactancia en curso");
				// return mapping.findForward("confirma");
				ActionMessages msgs = new ActionMessages();
				msgs.add("error",new ActionMessage("error",
					"La lactancia que desea descargar esta en curso, debe descargarla desde la opcion descargar lactancia en curso"));
				saveMessages(request, msgs);
				// request.setAttribute("id",idParameter);
				return mapping.findForward("success");
			}
		}
		String nombreGenerado = "Informacion_Lactancia_Cerrada_RP_"
				+ an.getRP() + ".pdf";
		he.setNumeroLactanciaReporte(numeroLactancia.intValue());
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(nombreGenerado));
		try {
			InputStream is = new ByteArrayInputStream(report
					.makeReportCertificadoLactanciaNumero(he));
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
	private String getContextPath(String subPath) {
		return getServlet().getServletContext().getRealPath(subPath);
	}
	
	private void copyParameters(HttpServletRequest request,ActionForm form){
		
		ReportesEcloForm f = (ReportesEcloForm) form;
		String unProp = (String)request.getParameter("unPro");
		if (unProp == null)
			unProp = f.getUnPro();
		String unEcl = (String)request.getParameter("unEcl");
		if (unEcl == null)
			unEcl = f.getUnEcl();
		String tambos = (String)request.getParameter("tambos");
		if (tambos == null)
			tambos = f.getTambos();
		String propId = (String)request.getParameter("propId");
		if (propId == null)
			propId = f.getPropId();
		String ecloId = (String)request.getParameter("ecloId");
		if (ecloId == null)
			ecloId = f.getEcloId();
		String animales = (String)request.getParameter("animales");
		if (animales == null)
			animales = f.getAnimales();
		String calificados = (String)request.getParameter("calificados");
		if (calificados == null)
			calificados = f.getCalificados();
		String orden = (String)request.getParameter("orden");
		if (orden == null)
			orden = f.getOrden();
		String tamboId = (String)request.getParameter("tamboId");
		if (tamboId == null)
			tamboId = f.getTamboId();
		String tipoLogin = (String)request.getParameter("tipoLogin");
		if (tipoLogin == null)
			tipoLogin = f.getTipoLogin();
		String unEst = (String)request.getParameter("unEst");
		if (unEst == null)
			unEst = f.getUnEst();
		String unEstTodos = (String)request.getParameter("unEstTodos");
		if (unEstTodos == null)
			unEstTodos = f.getUnEstTodos();
		String unEstPropios = (String)request.getParameter("unEstPropios");
		if (unEstPropios == null)
			unEstPropios = f.getUnEstPropios();
		String eclosOrPropietarios = (String)request.getParameter("eclosOrPropietarios");
		if (eclosOrPropietarios == null)
			eclosOrPropietarios = f.getEclosOrPropietarios();
		String ecloIdCombo = (String)request.getParameter("ecloIdCombo");
		if (ecloIdCombo == null || ecloIdCombo.equals(""))
			ecloIdCombo = ecloId;
		else
			ecloIdCombo = f.getEcloIdCombo().toString();
		
		String ecloIdCombo2 = (String)request.getParameter("ecloIdCombo2");
		if (ecloIdCombo2 == null || ecloIdCombo2.equals(""))
			ecloIdCombo2 = ecloId;
		else
			ecloIdCombo2 = f.getEcloIdCombo2().toString();
		
		Integer indiceCombo;
		if (request.getParameter("indiceCombo") == null || request.getParameter("indiceCombo").equals(""))
			indiceCombo = null;
		else	
			indiceCombo = new Integer(request.getParameter("indiceCombo"));
		if (indiceCombo == null)
			indiceCombo = f.getIndiceCombo();
				
		request.setAttribute("unPro", unProp);		
		request.setAttribute("unEcl", unEcl);
		request.setAttribute("tambos", tambos);
		request.setAttribute("propId", propId);
		request.setAttribute("ecloId", ecloId);
		request.setAttribute("animales",animales);
		request.setAttribute("calificados",calificados);
		request.setAttribute("orden",orden);
		request.setAttribute("tamboId",tamboId);
		request.setAttribute("tipoLogin",tipoLogin);
		request.setAttribute("unEst",unEst);
		request.setAttribute("unEstTodos", unEstTodos);
		request.setAttribute("unEstPropios", unEstPropios);
		request.setAttribute("eclosOrPropietarios", eclosOrPropietarios);
		request.setAttribute("ecloIdCombo", ecloIdCombo);
		request.setAttribute("indiceCombo", indiceCombo);
						
		f.setUnPro(unProp);
		f.setUnEcl(unEcl);
		f.setTambos(tambos);
		f.setPropId(propId);
		f.setEcloId(ecloId);
		f.setAnimales(animales);
		f.setCalificados(calificados);
		f.setOrden(orden);
		f.setTamboId(tamboId);
		f.setTipoLogin(tipoLogin);
		f.setUnEst(unEst);
		f.setUnEstTodos(unEstTodos);
		f.setUnEstPropios(unEstPropios);
		f.setEclosOrPropietarios(eclosOrPropietarios);
		f.setIndiceCombo(indiceCombo);
		
			if (ecloIdCombo.equals(""))
				if (!ecloId.equals(""))
					f.setEcloIdCombo(new Integer(ecloId));
				else
					f.setEcloIdCombo(null);
			else
				f.setEcloIdCombo(new Integer(ecloIdCombo));
	}
	
	
	@SuppressWarnings("deprecation")
	public ActionForward volverDesdeBusquedaEstablecimiento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		copyParameters(request,(ReportesEcloForm) form);
		cargarEclos(request);
		return mapping.findForward("successRC");
	}
	
	private void cargarEclos(HttpServletRequest request){
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		if(user.getContacto()!=null){
			Contacto contac = ContactoDAO.findByPrimaryKey(user.getContacto().getId());
			if (user.getRol().getNombre().equalsIgnoreCase("PROPIETARIO")){
				List eclos = EcloDAO.findAllOrderById();
				request.setAttribute("eclos",eclos);
			}else if(user.getRol().getNombre().equalsIgnoreCase("REGIONAL")){
					List eclos = EcloDAO.findAllOrderByIdAndRegional(contac);
					request.setAttribute("eclos",eclos);
					}else 
						request.setAttribute("eclos",new ArrayList());
		}
		else{ //entra como ADMIN o GENERAL, tiene que elegir propietario o eclo
			List eclos = EcloDAO.findAllOrderById();
			request.setAttribute("eclos",eclos);
		}
	}
	
	private List<RCHBANumePagination> armarPaginado(Long cant){
		float p = (float)cant/(float)10000;
		Integer paginas = (new Float(Math.ceil(p))).intValue();
		List<RCHBANumePagination> list = new ArrayList<RCHBANumePagination>();
		for(int i = 0;i<paginas-1;i++){
			list.add(new RCHBANumePagination(i*10000+1,(i+1)*10000,new Integer(i)));
		}
		list.add(new RCHBANumePagination((paginas-1)*10000+1,new Integer(cant.toString()),new Integer(paginas-1)));
		return list;
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
		//return mapping.findForward("list");
		return this.buscar(mapping, form, request, response);
	
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
		//return mapping.findForward("list");
		return this.buscar(mapping, form, request, response);
	
	}

}
