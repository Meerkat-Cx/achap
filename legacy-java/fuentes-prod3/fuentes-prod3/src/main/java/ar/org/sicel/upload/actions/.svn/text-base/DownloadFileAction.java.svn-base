/**
 * 
 */
package ar.org.sicel.upload.actions;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.CentroDeComputo;
import ar.org.sicel.persistence.CentroDeComputoDAO;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcLoteDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.Sistema;
import ar.org.sicel.persistence.SistemaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.thread.ProcessThread;
import ar.org.sicel.upload.dao.ConstantsUpload;
import ar.org.sicel.upload.dao.DAOs;
import ar.org.sicel.upload.forms.UploadFileForm;
import ar.org.sicel.upload.model.Bajada;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.Tokens;

/**
 * Clase que que brinda la funcionalidad de descargar los archivos que se
 * encuentran en la base
 * 
 * @author jdivars
 * 
 */
public class DownloadFileAction extends DispatchAction {

	/**
	 * Metodo que carga la pantalla "Archivos Procesados" con los procesos del
	 * usuario logueado
	 */
	@SuppressWarnings("unchecked")
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		UploadFileForm upForm = (UploadFileForm)form;
		Long index = upForm.getIndiceCombo();
		if (index==null || index.intValue() == 0)
			request.setAttribute("procesos",ProcProcesDAO.getAllProcessByUser(user));
		else
			request.setAttribute("procesos",ProcProcesDAO.getAllProcessByEcloAndUser(EcloDAO.findByPrimaryKey(upForm.getEcloId()),user));
		List eclos = null;
		if (user.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL))
			eclos = EcloDAO.findAllOrderByIdAndRegional(user.getContacto());
		else if (user.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR) ||
				 user.getRol().getNombre().equals(Tokens.NOMBREROLGENERAL))
				eclos = EcloDAO.findAllOrderById();
		request.setAttribute("eclos",eclos);
		if (user.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR))
			request.setAttribute("rol",Tokens.NOMBREROLADMINISTRADOR);
		else if (user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			request.setAttribute("rol",Tokens.NOMBREROLPROVEEDOR);
		else if (user.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL))
			request.setAttribute("rol",Tokens.NOMBREROLREGIONAL);
		else 
			request.setAttribute("rol",Tokens.NOMBREROLGENERAL);
		upForm.setRolUser(user.getRol().getNombre());
	    request.setAttribute("method","init");
		return mapping.findForward("successProcesados");
	}

	/**
	 * Metodo que recupera todos los lotes de la eclo logueada que ya han sido
	 * procesados por el administrador
	 */
	public ActionForward initProcesados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {// /////////////////////////no se esta usando
								// este metodo
		Usuario user = (Usuario) request.getSession().getAttribute(
				Tokens.CURRENTUSER);

		request.setAttribute("procesos", ProcProcesDAO
				.getAllProcessProcesados(user));
		request.setAttribute("method", "initProcesados");
		request.setAttribute("rol", Tokens.NOMBREROLPROVEEDOR);
		return mapping.findForward("success");
	}

	/**
	 * Metodo que inicia la muestra del filtro de fechas para la descarga
	 */
	public ActionForward initFechas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadFileForm uForm = (UploadFileForm) form;
		uForm.reset();
		request.setAttribute("procesos", null);
		request.setAttribute("sistemas", SistemaDAO.findAll());
		request.setAttribute("method", "buscarFechas");
		Usuario user = (Usuario) request.getSession().getAttribute(
				Tokens.CURRENTUSER);
		request.setAttribute("rol", user.getRol().getNombre());
		return mapping.findForward("successFecha");
	}

	/**
	 * Metodo que muestra los procesos descargados
	 * 
	 * @param form
	 *            ---- Se utiliza el form UploadFileForm para guardar las fechas
	 *            de inicio y fin en un form
	 * @param request
	 *            ----- Se le pasa el rol y la lista de procesos descargados
	 *            ESTE METODO NO SE ESTA USANDO
	 */
	/*
	 * public ActionForward buscarFechas(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * Usuario user = (Usuario)
	 * request.getSession().getAttribute(Tokens.CURRENTUSER);
	 * 
	 * List list=null; UploadFileForm uForm= (UploadFileForm)form;
	 * 
	 * Date fechaI = DateUtils.parse(uForm.getFechaInicio(),"dd/MM/yyyy"); if
	 * (fechaI==null){ ActionMessages actionerrors = new ActionMessages();
	 * actionerrors.add("errorDate",new ActionMessage("errors.date", "Fecha
	 * Inicio")); saveMessages(request, actionerrors);
	 * request.setAttribute("procesos",null); return
	 * mapping.findForward("successFecha"); } Date fechaF =
	 * DateUtils.parse(uForm.getFechaFin(),"dd/MM/yyyy"); if (fechaF==null){
	 * ActionMessages actionerrors = new ActionMessages();
	 * actionerrors.add("errorDate",new ActionMessage("errors.date", "Fecha
	 * Fin")); saveMessages(request, actionerrors);
	 * request.setAttribute("procesos",null); return
	 * mapping.findForward("successFecha"); } Calendar d = new
	 * GregorianCalendar(); d.setTimeInMillis(fechaF.getTime());
	 * d.set(Calendar.HOUR,23); d.set(Calendar.MINUTE,59);
	 * d.set(Calendar.SECOND,59); fechaF.setTime(d.getTimeInMillis()); String
	 * rol; if( user.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR))
	 * rol=Tokens.NOMBREROLADMINISTRADOR; else rol=Tokens.NOMBREROLPROVEEDOR;
	 * 
	 * list = ProcProcesDAO.getAllProcessEntreFechas(fechaI,fechaF,user,rol);
	 * request.setAttribute("rol",rol); request.setAttribute("procesos",list);
	 * request.setAttribute("method","buscarFechas"); return
	 * mapping.findForward("successFecha"); }
	 */
	/**
	 * Metodo que recupera lotes descargados segun 2 filtros, entre fechas o por
	 * numero de lote, eclo, centro y sistema
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward buscarLotes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Usuario user = (Usuario) request.getSession().getAttribute(
				Tokens.CURRENTUSER);

		List list = new ArrayList();
		UploadFileForm uForm = (UploadFileForm) form;
		String rol;
		Set users = new HashSet();
		if (user.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR)) {
			rol = Tokens.NOMBREROLADMINISTRADOR;

			if (uForm.getEcloIdF() != null
					&& uForm.getEcloIdF().intValue() != 0) {
				Long ecloIdF = null;
				ecloIdF = uForm.getEcloIdF();
				Eclo ec = EcloDAO.findByPrimaryKey(ecloIdF);
				if (ec == null){
					ActionMessages actionerrors = new ActionMessages();
					actionerrors.add("errorEcloNoExiste", new ActionMessage("errors.ecloNoExiste",
							ecloIdF.toString()));
					saveMessages(request, actionerrors);
					request.setAttribute("rol", rol);
					request.setAttribute("procesos", null);
					request.setAttribute("sistemas", SistemaDAO.findAll());
					return mapping.findForward("successFecha");
				}
				users.addAll(ec.getUsuarios());
			}
		} else if (user.getRol().getNombre().equals(Tokens.NOMBREROLGENERAL)) {
			rol = Tokens.NOMBREROLGENERAL;
			if (uForm.getEcloIdF() != null
					&& uForm.getEcloIdF().intValue() != 0) {
				Long ecloIdF = null;
				ecloIdF = uForm.getEcloIdF();
				Eclo ec = EcloDAO.findByPrimaryKey(ecloIdF);
				users.addAll(ec.getUsuarios());
			}
		} else if (user.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL)) {
			rol = Tokens.NOMBREROLREGIONAL;
			if (uForm.getEcloIdF() != null
					&& uForm.getEcloIdF().intValue() != 0) {
				Long ecloIdF = null;
				ecloIdF = uForm.getEcloIdF();
				Eclo ec = EcloDAO.findEcloByIdAndRegional(ecloIdF,user.getContacto());
				if (ec == null){
					ActionMessages actionerrors = new ActionMessages();
					actionerrors.add("errorEclo", new ActionMessage("errors.eclo",
							ecloIdF.toString()));
					saveMessages(request, actionerrors);
					request.setAttribute("rol", rol);
					request.setAttribute("procesos", null);
					request.setAttribute("sistemas", SistemaDAO.findAll());
					return mapping.findForward("successFecha");
				}
				users.addAll(ec.getUsuarios());
			}
		}
		else if( user.getRol().getNombre().equals(Tokens.NOMBREROLGENERAL)){
				rol=Tokens.NOMBREROLGENERAL;
				if(uForm.getEcloIdF()!=null && uForm.getEcloIdF().intValue()!=0){
					Long ecloIdF = null;
					ecloIdF = uForm.getEcloIdF();
					Eclo ec = EcloDAO.findByPrimaryKey(ecloIdF);
					users.addAll(ec.getUsuarios());
				}
			}
		else{
			rol = Tokens.NOMBREROLPROVEEDOR;
			users.add(user);
		}
		request.setAttribute("rol", rol);
		request.setAttribute("sistemas", SistemaDAO.findAll());
		if (!StringUtils.isEmpty(uForm.getFechaInicio())
				&& (!StringUtils.isEmpty(uForm.getFechaFin()))) {// si es por
																	// fechas
			Date fechaI = DateUtils.parse(uForm.getFechaInicio(), "dd/MM/yyyy");
			if (fechaI == null) {
				ActionMessages actionerrors = new ActionMessages();
				actionerrors.add("errorDate", new ActionMessage("errors.date",
						"Fecha Inicio"));
				saveMessages(request, actionerrors);
				request.setAttribute("procesos", null);
				request.setAttribute("sistemas", SistemaDAO.findAll());

				return mapping.findForward("successFecha");
			}
			Date fechaF = DateUtils.parse(uForm.getFechaFin(), "dd/MM/yyyy");
			if (fechaF == null) {
				ActionMessages actionerrors = new ActionMessages();
				actionerrors.add("errorDate", new ActionMessage("errors.date",
						"Fecha Fin"));
				saveMessages(request, actionerrors);
				request.setAttribute("procesos", null);
				request.setAttribute("sistemas", SistemaDAO.findAll());

				return mapping.findForward("successFecha");
			}
			Calendar d = new GregorianCalendar();
			d.setTimeInMillis(fechaF.getTime());
			d.set(Calendar.HOUR, 23);
			d.set(Calendar.MINUTE, 59);
			d.set(Calendar.SECOND, 59);
			fechaF.setTime(d.getTimeInMillis());

			// list =
			// ProcProcesDAO.getAllProcessEntreFechas(fechaI,fechaF,user,rol);
			list = ProcProcesDAO.getAllProcessEntreFechas(fechaI, fechaF,
					users, rol);
		} else {// si es por lote
			Long ecloId;
			if(!rol.equals(Tokens.NOMBREROLPROVEEDOR))
				ecloId = uForm.getEcloId();
			else
				ecloId = user.getContacto().getId();
			Long loteNum = uForm.getLoteNum();
			Long sistemaI = uForm.getSistema();
			Sistema sistema = SistemaDAO.findByPrimaryKey(sistemaI);
			Long centroId = null;
			CentroDeComputo centro = null;
			if (uForm.getCentroId() != null
					&& uForm.getCentroId().intValue() != 0) {
				centroId = uForm.getCentroId();
				centro = CentroDeComputoDAO.findByPrimaryKey(centroId);
			}
			Eclo eclo = !rol.equals(Tokens.NOMBREROLREGIONAL) ? EcloDAO.findByPrimaryKey(ecloId) : EcloDAO.findEcloByIdAndRegional(ecloId,user.getContacto()); ;
			if (eclo != null) {
				List lote = ProcLoteDAO.findListByEcloSistemaCentro(eclo,
						sistema, centro, loteNum);
				if (lote != null)
					for (int i = 0; i < lote.size(); i++)
						list.add(((ProcLote) lote.get(i)).getProcProces());
			}
			else{
				ActionMessages actionerrors = new ActionMessages();
				if (rol.equals(Tokens.NOMBREROLREGIONAL))
					actionerrors.add("errorEclo", new ActionMessage("errors.eclo",
							ecloId.toString()));
				else
					actionerrors.add("errorEcloNoExiste", new ActionMessage("errors.ecloNoExiste",
							ecloId.toString()));
				saveMessages(request, actionerrors);
				request.setAttribute("rol", rol);
				request.setAttribute("procesos", null);
				request.setAttribute("sistemas", SistemaDAO.findAll());
				return mapping.findForward("successFecha");
			}
		}
		request.setAttribute("procesos", list);
		request.setAttribute("method", "buscarLotes");
		return mapping.findForward("successFecha");
	}

	/**
	 * Metodo que recupera todos los lotes cargados que todavia no se procesaron
	 * (de la eclo logueada)
	 */
	public ActionForward initCargados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Usuario user = (Usuario) request.getSession().getAttribute(
				Tokens.CURRENTUSER);
		request.setAttribute("procesos", ProcProcesDAO.getAllProcess(user,
				ConstantsUpload.UPLOADED));
		return mapping.findForward("cargadosEclo");
	}

	/**
	 * Metodo que recupera todos los lotes que se estan procesando en ese
	 * momento (de la eclo logueada)
	 */

	public ActionForward initEnProceso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		List proces = ProcProcesDAO.getAllProcess(user,ConstantsUpload.PROCESSING);
		request.setAttribute("procesos",proces);
		if (!proces.isEmpty())
			request.setAttribute("primerProces",((ProcProces)proces.get(0)).getId());
		request.setAttribute("idUsuario",user.getId());
		request.setAttribute("method","initEnProceso");
		request.setAttribute("rol",user.getRol().getNombre());
		return mapping.findForward("success");
	}

	public ActionForward initEnStandBy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("procesos", ProcProcesDAO
				.getAllProcessState(ConstantsUpload.STANDBY));
		request.setAttribute("method", "initEnStandBy");
		return mapping.findForward("standBy");
	}

	/**
	 * Metodo que sirve para descargar el archivo ZIP de Salidas el cual incluye
	 * el conjunto de salidas generadas por el pricesamiento
	 */

	@SuppressWarnings("deprecation")
	public ActionForward downloadZIPOut(ActionMapping mapping,
			ActionForm formm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);
		// Proceso proc =DAOs.getProcesoDAO().load(id);
		ProcProces proc = ProcProcesDAO.load(id);
		// Salida sal=DAOs.getSalidaDAO().buscarPorIdProceso(proc);
		Date fechaActual = proc.getFechaSalida();
		if (!proc.getEstado().equals(ConstantsUpload.STANDBY))
			proc.setEstado(ConstantsUpload.DOWNLOADED);
		Usuario user = (Usuario) request.getSession().getAttribute(
				Tokens.CURRENTUSER);
		if (user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			proc.setDescargadaEclo(true);
		if ((user.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR))
				&& (!proc.getEstado().equals(ConstantsUpload.STANDBY)))
			proc.setDescargadaAdmin(true);
		ProcProcesDAO.updateProcProces(proc);
		Bajada bajada = new Bajada();
		bajada.setProceso(proc);
		bajada.setFecha(new Date());
		DAOs.getBajadaDAO().save(bajada);
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
		String nombreGenerado = "";
		if (proc.getProcLote() == null)
			nombreGenerado = "Lote_[sin_informacion]" + sd.format(fechaActual)
					+ "_" + proc.getUsuario().getUsername() + "_e.zip";
		else
			nombreGenerado = "Lote_" + proc.getProcLote().getNumLote() + "_"
					+ sd.format(fechaActual) + "_"
					+ proc.getUsuario().getUsername() + "_e.zip";
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(nombreGenerado));
		try {
			// int fSize = ent.getContenido().length;
			InputStream is = proc.getContenidoSalida().getBinaryStream();
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
	public ActionForward downloadZIP(ActionMapping mapping, ActionForm formm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idParameter = request.getParameter("id");
		Long id = new Long(idParameter);
		// Proceso proc =DAOs.getProcesoDAO().load(id);
		ProcProces proc = ProcProcesDAO.load(id);
		// Entrada ent=DAOs.getEntradaDAO().buscarPorIdProceso(proc);
		Date fechaActual = proc.getFechaEntrada();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
		String nombreGenerado = "";
		if (proc.getProcLote() == null)
			nombreGenerado = "Lote_[sin_informacion]" + sd.format(fechaActual)
					+ "_" + proc.getUsuario().getUsername() + "_e.zip";
		else
			nombreGenerado = "Lote_" + proc.getProcLote().getNumLote() + "_"
					+ sd.format(fechaActual) + "_"
					+ proc.getUsuario().getUsername() + "_e.zip";

		// String nombreGenerado =
		// "Lote_"+proc.getProcLote().getNumLote()+"_"+sd.format(fechaActual) +
		// "_" + proc.getUsuario().getUsername() + "_e.zip";
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(nombreGenerado));

		try {
			InputStream is = proc.getContenidoEntrada().getBinaryStream();
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

	/**
	 * metodo que realiza el cambio de estado de un proceso que se encuentra en
	 * estado "procesessing" y se vuelve a estado "uploaded"
	 * 
	 * @param mapping
	 * @param formm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward changeToUploaded(ActionMapping mapping,
			ActionForm formm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UploadFileForm upForm = (UploadFileForm)formm;
		Long id = upForm.getIdDeletedProcess();
		ProcProces proc = ProcProcesDAO.load(id);
		proc.setFecha(null);
		proc.setEstado(ConstantsUpload.UPLOADED);
		proc.setUsuarioProcesador(null);
		StandaloneHibernateStrategy.getInstance().getCurrentSession();
		ProcProcesDAO.updateProcProces(proc);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		//ProcessThread thread = new ProcessThread();
		//thread.stopProceso();
		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		return (this.initEnProceso(mapping,formm,request,response));
	}
	
	public ActionForward mostrarObservaciones(ActionMapping mapping, ActionForm formm,	HttpServletRequest request, HttpServletResponse response){
		String obs = (String)request.getParameter("obs");
		request.setAttribute("obs",obs);
		return mapping.findForward("mostrarObservaciones");
	}
	

}