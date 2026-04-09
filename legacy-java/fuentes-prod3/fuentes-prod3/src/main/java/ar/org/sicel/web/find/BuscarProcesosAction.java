package ar.org.sicel.web.find;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcLoteDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.upload.dao.ConstantsUpload;
import ar.org.sicel.upload.dao.DAOs;
import ar.org.sicel.upload.model.Bajada;
import ar.org.sicel.web.Tokens;

/**
 * @struts.action 
 * 		path="/buscarProcesos"
 * 		scope="request"
 * 		validate="false"
 * 
 * @struts.action-forward 
 * 		name="listado" 
 * 		path=".procProcesTable"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="failure" 
 * 		path=".main"
 *  
 */
public final class BuscarProcesosAction extends DispatchAction {
	
	private static final String BUSCAR_POR_NUMERO_LOTE_Y_ECLO = "buscarPorNumeroLoteYEclo";
	private static final String BUSCAR_POR_ID_EVENTO_RESULTADO = "buscarPorIdEventoResultado";
	private static final String BUSCAR_POR_ID_EVENTO_INFORMADO_Y_ECLO = "buscarPorIdEventoInformadoYEclo";
	private static final String BUSCAR_CONTROLES = "buscarControles";
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuscarProcesosForm bForm = (BuscarProcesosForm) form;
		bForm.reset();
		List listaProcesos = new ArrayList();
        request.setAttribute("listaProcesos", listaProcesos);
		return (mapping.findForward("listado"));
	}
	
	public ActionForward buscarPorNumeroLoteYEclo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuscarProcesosForm bForm = (BuscarProcesosForm) form;
		Long lote = null;
		if (bForm.getNroLote() != null && !bForm.getNroLote().equals(""))
			lote = new Long(bForm.getNroLote());
		Eclo eclo = EcloDAO.findByPrimaryKey(new Long(bForm.getNroEclo1()));
		List listaProcesos = new ArrayList();
		listaProcesos = ProcLoteDAO.findAllByECLO(eclo, lote);
        request.setAttribute("listaProcesos", listaProcesos);
        bForm.setMetodo(BUSCAR_POR_NUMERO_LOTE_Y_ECLO);
		return (mapping.findForward("listado"));
	}
	
	public ActionForward buscarPorIdEventoResultado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuscarProcesosForm bForm = (BuscarProcesosForm) form;
		Long idEventRes = new Long(bForm.getIdEventoResultado());
		ProcLote proceso = ProcLoteDAO.findByNumeroResultado(idEventRes);
		List listaProcesos = new ArrayList();
		if (proceso != null)
			listaProcesos.add(proceso);
        request.setAttribute("listaProcesos", listaProcesos);
        bForm.setMetodo(BUSCAR_POR_ID_EVENTO_RESULTADO);
		return (mapping.findForward("listado"));
	}
	
	public ActionForward buscarPorIdEventoInformadoYEclo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuscarProcesosForm bForm = (BuscarProcesosForm) form;
		Long idEventInf = new Long(bForm.getIdEventoInformado());
		Eclo eclo = EcloDAO.findByPrimaryKey(new Long(bForm.getNroEclo2()));
		List procesos = ProcLoteDAO.findByNumeroInformadoAndEclo(idEventInf, eclo);
		request.setAttribute("listaProcesos", procesos);
        bForm.setMetodo(BUSCAR_POR_ID_EVENTO_INFORMADO_Y_ECLO);
		return (mapping.findForward("listado"));
	}
	
	public ActionForward paginado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuscarProcesosForm bForm = (BuscarProcesosForm) form;
		String metodo = bForm.getMetodo();
		if (metodo.equals(BUSCAR_POR_ID_EVENTO_INFORMADO_Y_ECLO))
			return this.buscarPorIdEventoInformadoYEclo(mapping, form, request, response);
		if (metodo.equals(BUSCAR_POR_ID_EVENTO_RESULTADO))
			return this.buscarPorIdEventoResultado(mapping, form, request, response);
		else if (metodo.equals(BUSCAR_POR_NUMERO_LOTE_Y_ECLO)) 
			return this.buscarPorNumeroLoteYEclo(mapping, form, request, response);
		else return this.buscarControles(mapping, form, request, response);
	}
	
	public ActionForward buscarControles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BuscarProcesosForm bForm = (BuscarProcesosForm) form;
		String idEventInf = bForm.getIdEventoResultado2();
		String idOrdenie = bForm.getOrdenieInformado();
		String controlInformado = bForm.getControlInformado();
		List procesos = ProcLoteDAO.findLoteControl(idOrdenie,controlInformado,idEventInf);
		request.setAttribute("listaProcesos", procesos);
        bForm.setMetodo(BUSCAR_CONTROLES);
		return (mapping.findForward("listado"));
	}
	
	
	/**
	 * Metodo que sirve para descargar el archivo ZIP de Salidas el cual incluye
	 * el conjunto de salidas generadas por el procesamiento
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

}
