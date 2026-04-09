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
public class EstadisticoLactanciaAction extends DispatchAction {
	
	private String query;
	private String query305;
	private String subquery;


	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		return mapping.findForward("init");
	}
	
	public void generarQuery(EstadisticoLactanciaForm formE)throws Exception 
	{
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String fechaDesde = DateUtils.format(fechaI, "yyyy/MM/dd");
		String fechaHasta = DateUtils.format(fechaF, "yyyy/MM/dd");
		
		String groupBy = " group by an.RAZADECLARA";
		String orderBy = " order by an.RAZADECLARA";
		String select = "select an.RAZADECLARA RAZA, ";
		String select2 = "select an.RAZADECLARA RAZA, ";
		String andCat = "";
		if(formE.getConPed()){
			select += "'PEDIGREE' CATEGORIA, ";
			select2 += "'PEDIGREE' CATEGORIA, ";
			andCat += " and  re.TREG='HBA'";
		}
		if(formE.getSinPed()){
			select += "'Registro Cría' CATEGORIA, ";
			select2 += "'Registro Cría' CATEGORIA, ";
			andCat += " and  re.TREG!='HBA'";
		}
		if(formE.getPorCat()){
			select2 += " an.CATEGORIA CATEGORIA, ";
			select += " an.CATEGORIA CATEGORIA, ";
			groupBy += ", an.CATEGORIA";
			orderBy += ", an.CATEGORIA";
		}
		if(formE.getTodas()){
			select2 += " 'TODAS' CATEGORIA, ";
			select += " 'TODAS' CATEGORIA, ";
		}
		String raza="";
		if(!formE.getRaza().equalsIgnoreCase("TODAS"))
			raza=" and an.RAZADECLARA='"+formE.getRaza()+"'";
		String select3 = select + " case when (count(*)) is null then 0 else count(*) end  CANTLACT, case when (avg(la.DIASTOTALES)) is null then 0 else round(avg(la.DIASTOTALES)) end TIEMPOLACT, case when (avg(pl.leche)) is null then 0 else round(avg(pl.leche)) end LECHEVACA, case when (avg(pl.grasa)) is null then 0.0 else round(avg(pl.grasa),2) end GRASA, case when (avg(pl.proteinas)) is null then 0.0 else round(avg(pl.proteinas),2) end PROTEINAS";
		select +=" case when (count(*)) is null then 0 else count(*) end  CANTLACT, case when (avg(la.DIASTOTALES)) is null then 0 else round(avg(la.DIASTOTALES)) end TIEMPOLACT, case when (avg(pl.leche)) is null then 0 else round(avg(pl.leche)) end LECHEVACA, case when (avg(pl.grasa)) is null then 0.0 else round(avg(pl.grasa),2) end GRASA, case when (avg(pl.proteinas)) is null then 0.0 else round(avg(pl.proteinas),2) end PROTEINAS";
		String from = " from ev_lactancia la join EV_PRODUCCION_LACTANCIA pl on la.id=pl.LACTANCIA join ev_evento ev on ev.id=la.id join ev_animal ea on ea.id=ev.id join an_animal an on an.id=ea.animal join AN_REGISTRO re on re.ID=an.REGORI ";
		query = select + from +
						" where ev.FECHA between to_date('"+fechaDesde+"','yyyy/mm/dd') and to_date('"+fechaHasta+"','yyyy/mm/dd') and pl.NOMBRE='REAL' "+
						raza + andCat + groupBy + orderBy;
		query305 = select3 + from +" where ev.FECHA between to_date('"+fechaDesde+"','yyyy/mm/dd') and to_date('"+fechaHasta+"','yyyy/mm/dd') and pl.NOMBRE='305' "+
		                  raza + andCat + groupBy + orderBy;
		
		select2 += " case when (avg(trunc(l1.FECHAINICIO) - trunc(l2.FECHAINICIO))) is null then 0 else round(avg(trunc(l1.FECHAINICIO) - trunc(l2.FECHAINICIO))) end as DiasEntrePartos, case when (count(*)) is null then 0 else count(*) end  CANTLACT ";
		String from2 = " from EV_LACTANCIA l1 join ev_animal a1 on l1.id=a1.id join an_animal an on an.id=a1.animal join ev_evento ev on ev.id=l1.id join ev_animal a2 on a2.animal=a1.animal join EV_LACTANCIA l2 on l2.id=a2.id join AN_REGISTRO re on re.ID=an.REGORI "; 
		subquery = select2 + from2 +
						" where ev.FECHA between to_date('"+fechaDesde+"','yyyy/mm/dd') and to_date('"+fechaHasta+"','yyyy/mm/dd') "+
						" and exists (select * from EV_LACTANCIA la where la.NROLACT = l1.NROLACT-1 and rownum=1 and l2.id=la.id)"+
						raza + andCat + groupBy + orderBy;
		
	}
	
	
	
	public ActionForward descargarReporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EstadisticoLactanciaForm formE = (EstadisticoLactanciaForm)form;
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		Map parametros = new HashMap();

		 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
		 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
		 parametros.put("FECHA_DESDE_T", DateUtils.format(fechaI, "dd/MM/yyyy"));
		 parametros.put("FECHA_HASTA_T", DateUtils.format(fechaF, "dd/MM/yyyy"));
		 parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
	
		 generarQuery(formE);
		if(formE.getConPed() || formE.getSinPed())
			parametros.put("REG", "registro");
		else
			parametros.put("REG", "categoria");
		parametros.put("QUERY", query);
		parametros.put("QUERY305", query305);
		parametros.put("SUBQUERY", subquery);
		String nombreGenerado;
		nombreGenerado =  "Reporte_Estadistico_Lactancia.pdf";
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		InputStream is=	new ByteArrayInputStream(report.makeReportEstadisticoLactancia(parametros));
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
	
	public ActionForward descargarReporteXLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EstadisticoLactanciaForm formE = (EstadisticoLactanciaForm)form;
		Date fechaI = new Date();
		fechaI= DateUtils.parse(formE.getFechaDesde(),"dd/MM/yyyy");
		Date fechaF = new Date();
		fechaF= DateUtils.parse(formE.getFechaHasta(),"dd/MM/yyyy");
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		Map parametros = new HashMap();

		 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
		 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
		 parametros.put("FECHA_DESDE_T", DateUtils.format(fechaI, "dd/MM/yyyy"));
		 parametros.put("FECHA_HASTA_T", DateUtils.format(fechaF, "dd/MM/yyyy"));
		 parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
	
		 generarQuery(formE);
		 if(formE.getConPed() || formE.getSinPed())
				parametros.put("REG", "registro");
			else
				parametros.put("REG", "categoria");
		parametros.put("QUERY", query);
		parametros.put("QUERY305", query305);
		parametros.put("SUBQUERY", subquery);
		 
		 
		String nombreGenerado;
		nombreGenerado =  "/Reporte_Estadistico_Lactancia.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(nombreGenerado));
		InputStream is=	new ByteArrayInputStream(report.makeReportEstadisticoLactanciaXLS(nombreGenerado,parametros));
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