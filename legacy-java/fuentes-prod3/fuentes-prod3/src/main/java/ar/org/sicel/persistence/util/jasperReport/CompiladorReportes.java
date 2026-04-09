package ar.org.sicel.persistence.util.jasperReport;

import net.sf.jasperreports.engine.JasperCompileManager;
import ar.org.sicel.util.Sicel3Conf;

public class CompiladorReportes {
	
	private static String dir_reportes;
	
	static{
		try{
			dir_reportes = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//compilarReporteProcesamiento(new String[]{});
		compilarFichaAnimal(new String[]{});
		//compilarCertificadoLactancia(new String[]{});
		//compilarReporteGenealogico(new String[]{});
	}

	
	public static void compilarReporteGenealogico(String[] args) {		
		try {
			/*
			 * Reporte Genealogico
			 */
			String reporteGenealogico = dir_reportes + "Reporte_Genealogico.jrxml";
	        JasperCompileManager.compileReportToFile(reporteGenealogico);
			
	        System.out.println("Reporte Genealogico compilado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void compilarCertificadoLactancia(String[] args) {		
		try {
		/*
		 * Reporte Certificado de Lactancia
		 */
        String certificadoLactancia_SubLactancias= dir_reportes + "subReporteLactancias.jrxml";
        JasperCompileManager.compileReportToFile(certificadoLactancia_SubLactancias);
        
        String certificadoLactancia_SubControles= dir_reportes + "subReporteControles.jrxml";
        JasperCompileManager.compileReportToFile(certificadoLactancia_SubControles);
        
        String certificadoLactancia= dir_reportes + "Certificado_Lactancia.jrxml";
        JasperCompileManager.compileReportToFile(certificadoLactancia);
        
        System.out.println("Reportes del certificado de lactancia compilados!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void compilarFichaAnimal(String[] args) {
		try {
		/*
		 * Reporte Ficha del Animal
		 */
		//String fichaAnimal_SubCompRacial= dir_reportes + "reporteComposicionRacial.jrxml";
       // JasperCompileManager.compileReportToFile(fichaAnimal_SubCompRacial);
        
        //String fichaAnimal_SubLactancias= dir_reportes + "reporteLactanciasMadreNuevo.jrxml";
       // JasperCompileManager.compileReportToFile(fichaAnimal_SubLactancias);			
		
		//String fichaAnimal = dir_reportes + "AnimalReport.jrxml";
		//JasperCompileManager.compileReportToFile(fichaAnimal);           
        
        //String wrapper_fichaAnimal= dir_reportes + "Ficha_Animal.jrxml";
		String wrapper_fichaAnimal= "C:\\proyectos\\Acha\\branches\\sinmaven\\fuentes\\webapp\\reports\\reports" + "\\Ficha_Animal.jrxml";
        JasperCompileManager.compileReportToFile(wrapper_fichaAnimal);
        
        System.out.println("Reportes de la ficha del animal compilados!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void compilarReporteProcesamiento(String[] args) {
		try {
		/*
		 * Reporte Resumen del Procesamiento
		 */
		String resumenProceso = dir_reportes  + "ResumenProceso.jrxml";
		JasperCompileManager.compileReportToFile(resumenProceso);
			
		System.out.println("Reportes de procesamiento compilados!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
