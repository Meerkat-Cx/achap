package ar.org.sicel.persistence.util.jasperReport;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.avalon.framework.configuration.ConfigurationException;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.util.Sicel3Conf;

/**
 * @author <a href="mailto:diegopalmisano@yahoo.com.ar">Diego Palmisano</a>
 *
 */
public class Report {

    static String REPORT_NAME = null;
    static String REPORT_DIR = null;
    static String REPORT_CERTIFICADO_LACTANCIA = null;

    public Report(){
        try {
        	REPORT_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
            //REPORT_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("jasperDir").getValue();
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        REPORT_NAME = REPORT_DIR + "/Ficha_Animal.jasper";
        REPORT_CERTIFICADO_LACTANCIA = REPORT_DIR + "/Certificado_Lactancia.jasper" ;
    }

    // Methods ----------------------------------------------------------------
    @SuppressWarnings({"unchecked","unchecked"})
	public byte[] makeReport(Long idAnimal) {

        StandaloneHibernateStrategy.getInstance().openNewSession();
        byte[] bytes = null;

        
        try {
            AnimalUnicoDataSource animalDataSource = new AnimalUnicoDataSource(idAnimal);
            Map parametros = new HashMap();
            
            String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
            parametros.put("DIR_IMG",dir_img);                        
            parametros.put("DIR_REPORTES",REPORT_DIR);
                        
            bytes = JasperRunManager.runReportToPdf(REPORT_NAME, parametros, animalDataSource);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StandaloneHibernateStrategy.getInstance().commitCurrentSession();

        return bytes;

    }

//    public static void main(String[] args) throws MarshalException, ValidationException, FileNotFoundException, IOException {
//        Report report = new Report();
//
//        File f = new File(REPORT_DIR + "animsModificados.xml");
//        String entrada = IOUtils.toString(new FileInputStream(f));
//        AnimsModificados anims = (AnimsModificados)AnimsModificados.unmarshal(new StringReader(entrada));
//
//        byte[] bytes = report.makeReport(anims.getAnims().getAnimal());
//
//        // Copiamos el pdf resultado
//        File salida = new File(REPORT_DIR + "animsModificados.pdf");
//        FileOutputStream out = new FileOutputStream(salida);
//        out.write(bytes);
//        out.flush();
//        out.close();
//
//        System.out.println("Termino");
//    }

	@SuppressWarnings({"unchecked","unchecked", "unchecked"})
	public byte[] makeReportResumen(Collection resumen, Eclo eclo, Long numLote) {
		   byte[] bytes = null;
		   String REPORT_RESUMEN=null;
		try {
			//REPORT_RESUMEN = getClass().getResource(Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue() + "ResumenProceso.jasper").getPath();
			REPORT_RESUMEN = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue() + "ResumenProceso.jasper";
		} catch (ConfigurationException e1) {
			throw new RuntimeException(e1);
		}
	        try {
	            
	            Map parametros = new HashMap();
	            String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
	            parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");        
	            parametros.put("ECLO",eclo);
	            parametros.put("LOTE",numLote);
	            bytes = JasperRunManager.runReportToPdf(REPORT_RESUMEN, parametros, new JRBeanCollectionDataSource(resumen));
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return bytes;
	}
	
	@SuppressWarnings({"unchecked","unchecked"})
	public byte[] makeReportCertificadosLactancia(ar.org.sicel.proc.v1.anmodif.Animal[] ans) {

        StandaloneHibernateStrategy.getInstance().openNewSession();
        byte[] bytes = null;

        try {
            AnimalDataSource animalDataSource = new AnimalDataSource(ans);
            Map parametros = new HashMap();
            
            parametros.put("DIR_REPORTES",REPORT_DIR);
            parametros.put("titulo", "Certificado de Lactancia");
            
            bytes = JasperRunManager.runReportToPdf(REPORT_CERTIFICADO_LACTANCIA, parametros, animalDataSource);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StandaloneHibernateStrategy.getInstance().commitCurrentSession();

        return bytes;
    }
}
