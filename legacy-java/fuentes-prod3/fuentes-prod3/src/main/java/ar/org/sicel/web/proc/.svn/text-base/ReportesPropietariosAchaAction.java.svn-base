package ar.org.sicel.web.proc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PropietarioForm;
import ar.org.sicel.web.find.ReportesEcloForm;

public class ReportesPropietariosAchaAction extends DispatchAction {
	
	public ActionForward initBusquedaTambosPropietario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		PropietarioForm pf = (PropietarioForm)form;
		pf.reset();
		Propietario prop = PropietarioDAO.findByPrimaryKey(user.getContacto().getId());
		pf.setIdProp(prop.getId().toString());
		pf.setNombre(prop.getNombreContacto());
		pf.setCuit(prop.getCuit());
		pf.setCuig(prop.getCuig());
		pf.setRenspa(prop.getRenspa());
		pf.setPrefijo(prop.getPrefijo());
		pf.setSocio(prop.getSocio().toString());
		pf.setHar(prop.getHar() != null ? prop.getHar().toString() : "");
		pf.setEsPersonaFisica(prop.getEsPersonaFisica());
		pf.setActivo(prop.getActivo());
		List estabs = EstablecimientoDAO.getEstablecimientosByIdPropietarioOrdered(prop.getId());
		request.setAttribute("listaEstablecimientos", estabs);
		return mapping.findForward("reporteTambos");
	}
	
	public ActionForward downloadReportesTambos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		PropietarioForm pf = (PropietarioForm) form;
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento()
				.getChild("dirImagenesReportes").getValue();
		ProjectReport report = new ProjectReport(this
				.getContextPath(REPORTES_DIR), IMAGENES_DIR);
		String nombreGenerado = "Reporte_De_Tambos_Del_Propietario_" + pf.getIdProp() + ".pdf";

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(nombreGenerado));

		try {

			Propietario prop = PropietarioDAO.findByPrimaryKey(new Long(pf.getIdProp()));
			InputStream is = new ByteArrayInputStream(report
					.makeReportTambosPropietarios(prop));
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

}
