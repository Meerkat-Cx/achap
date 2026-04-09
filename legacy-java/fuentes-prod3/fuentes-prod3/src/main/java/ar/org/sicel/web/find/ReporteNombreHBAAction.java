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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.Sicel3Conf;

/**
 * @author rrodriguez
 *
 */

public class ReporteNombreHBAAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		return mapping.findForward("init");
	}
	
	public ActionForward descargarReporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReporteNombreHBAForm formE = (ReporteNombreHBAForm)form;
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
				
		
		Map parametros = new HashMap();

		parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
	
		
		String select = "select an.id ID,an.nombre NOMBRE from an_animal an join AN_REGISTRO re on re.ID=an.REGORI ";
		String where = "where  re.treg='HBA' ";

		if(!formE.getRaza().equalsIgnoreCase("TODAS"))
			where +=" and an.RAZADECLARA='"+formE.getRaza()+"' ";

		String sexo = formE.getSexo();
		if(sexo.equals("H")){
			where +=" and an.ESHEMBRA=1 ";
		}
		if(sexo.equals("M")){
			where +=" and an.ESHEMBRA=0 ";
		}
		String orderBy = "order by (an.nombre)";
		
		String query = select + where + orderBy;
								
		parametros.put("QUERY", query);
		String nombreGenerado = "Listado_Nombres_HBA.txt";
		response.setContentType("application/txt");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		InputStream is=	new ByteArrayInputStream(report.makeNombresHBATXT(parametros, REPORTES_DIR+nombreGenerado));
		
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
