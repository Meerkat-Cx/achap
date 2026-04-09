/**
 * 
 */
package ar.org.sicel.web.find;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.Lactancia;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.TipoRegistroDAO;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.find.ReporteEventosForm;



/**
 * @author rrodriguez
 *
 */
public class ReporteInscriptosAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("init");
	}
	
	public ActionForward descargarReporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReporteInscriptosForm formE = (ReporteInscriptosForm)form;
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		
		String nombreGenerado;
		nombreGenerado =  "/Reporte_INSCRIPTOS.pdf";
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		InputStream is=	new ByteArrayInputStream(report.makeReportinscriptos(fechaI,fechaF));
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
	

	private String getContextPath(String subPath)
	{
		return getServlet().getServletContext().getRealPath(subPath);
	}		
}
