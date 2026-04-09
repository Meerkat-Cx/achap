/**
 * 
 */
package ar.org.sicel.web.proc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.TipoRegistroDAO;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.find.ReporteEventosForm;



/**
 * @author jivars
 *
 */
public class InformeProcesosAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("regionales",EntidadRegionalDAO.findAll());
		request.setAttribute("eclos",EcloDAO.findAll());
		request.setAttribute("unaRegional","no");
		//request.setAttribute("propietarios",PropietarioDAO.findAll());
		//request.setAttribute("tambos",EstablecimientoDAO.findAll());
		
		return mapping.findForward("init");
	}
	public ActionForward descargarReporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InformeProcesoForm formE = (InformeProcesoForm)form;
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		//String idEclo = request.getParameter("ecloId");
		//String idRegional = request.getParameter("idRegional");
		//String tipoBusqueda = request.getParameter("tipoBusqueda");
		////String estado = request.getParameter("estado");
		String duenio = request.getParameter("duenio");
		String nombreGenerado;
		//if (idEclo!=null)
		//	nombreGenerado =  "/Listado_Propietarios_por_Id_Eclo_"+idEclo+".xls";
		//else	
			nombreGenerado =  "/Listado_Procesos.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		InputStream is=	new ByteArrayInputStream(report.makeReportProcesos(nombreGenerado,fechaI,fechaF,false));
		PrintWriter pw = response.getWriter();
		int c = -1;
		while ((c = is.read()) != -1) {
			pw.print((char) c);
		}
		is.close();
		pw.flush();
		pw = null;
		return null;
		//return mapping.findForward("init");
	}
	public ActionForward descargarReporteCrias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InformeProcesoForm formE = (InformeProcesoForm)form;
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		//String idEclo = request.getParameter("ecloId");
		//String idRegional = request.getParameter("idRegional");
		//String tipoBusqueda = request.getParameter("tipoBusqueda");
		////String estado = request.getParameter("estado");
		String duenio = request.getParameter("duenio");
		String nombreGenerado;
		//if (idEclo!=null)
		//	nombreGenerado =  "/Listado_Propietarios_por_Id_Eclo_"+idEclo+".xls";
		//else	
			nombreGenerado =  "/Listado_Procesos_crias.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		InputStream is=	new ByteArrayInputStream(report.makeReportProcesos(nombreGenerado,fechaI,fechaF,true));
		PrintWriter pw = response.getWriter();
		int c = -1;
		while ((c = is.read()) != -1) {
			pw.print((char) c);
		}
		is.close();
		pw.flush();
		pw = null;
		return null;
		//return mapping.findForward("init");
	}
	public ActionForward descargarReportePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InformeProcesoForm formE = (InformeProcesoForm)form;
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		//String idEclo = request.getParameter("ecloId");
		//String idRegional = request.getParameter("idRegional");
		//String tipoBusqueda = request.getParameter("tipoBusqueda");
		////String estado = request.getParameter("estado");
		
		String nombreGenerado ="Listado_Procesos.pdf";
		//if (idEclo!=null)
		//	nombreGenerado =  "/Listado_Propietarios_por_Id_Eclo_"+idEclo+".xls";
		//else	
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));

		try {
			
			InputStream is=	new ByteArrayInputStream(report.makeReportProcesos(fechaI,fechaF,false));
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
		//return mapping.findForward("init");
	}
	public ActionForward descargarReportePDFCrias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InformeProcesoForm formE = (InformeProcesoForm)form;
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		//String idEclo = request.getParameter("ecloId");
		//String idRegional = request.getParameter("idRegional");
		//String tipoBusqueda = request.getParameter("tipoBusqueda");
		////String estado = request.getParameter("estado");
		
		String nombreGenerado ="Listado_Procesos.pdf";
		//if (idEclo!=null)
		//	nombreGenerado =  "/Listado_Propietarios_por_Id_Eclo_"+idEclo+".xls";
		//else	
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));

		try {
			
			InputStream is=	new ByteArrayInputStream(report.makeReportProcesos(fechaI,fechaF,true));
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
		//return mapping.findForward("init");
	}

	private String getContextPath(String subPath)
	{
		return getServlet().getServletContext().getRealPath(subPath);
	}		
}
