package ar.org.sicel.persistence.util.jasperReport;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.util.DateUtils;



/**
 * @author 
 *
 */
public class ProjectReport {
	
	

	static Logger log = Logger.getLogger(ProjectReport.class);
	
	static String REPORT_NAME = null;
	static String REPORT_NAME_A4 = null;
	static String REPORT_DIR = null;
	static String IMAGENES_DIR = null;
	static String REPORT_CERTIFICADO_LACTANCIA = null;
	static String REPORT_NAME_GENEALOGICO = null;
	static String REPORT_RC_NUM_PDF = null;
	static String REPORT_RC_NUM_TXT = null;
	//static String REPORT_PADRON_PEDIGREE = null;
	static String REPORT_PADRON_HBA = null;
	static String REPORT_LOTE = null;
	static String REPORT_EVENTOS = null;
	static String REPORT_EVENTOS_QUERY = null;
	static String REPORT_NAME_EXPORTACION = null;
	static String REPORT_NAME_EXPORTACION_A4 = null;
	static String REPORT_MODELO = null;
	static String REPORT_TAMBOS_PROPIETARIO = null;
	static String REPORT_PROPIETARIO = null;
	static String REPORT_FORMULARIO_C89 = null;	
	static String REPORT_PROCESOS_XLS = null;
	static String REPORT_PROCESOS_XLS_CRIAS = null;
	static String REPORT_PROCESOS_PDF = null;
	static String REPORT_PROCESOS_PDF_CRIAS = null;
	static String REPORT_TAMBO_CRIAS_PDF = null;
	static String REPORT_TAMBO_CRIAS_XLS = null;
	static String REPORT_ICAR = null;
	static String REPORT_INSCRIPTOS = null;
	static String REPORT_ESTAD_LACT = null;
	static String REPORT_ESTAD_LACT_XLS = null;

	static String REPORT_NOMBRES_HBA_TXT = null;
	/**
	 * 
	 * @param dirReportes path al directorio de los reportes      
	 * @param dirImagenes path al directorio de las imagenes
	 */
	public ProjectReport(String dirReportes, String dirImagenes){        
		log.info("iniciando");
		REPORT_DIR = dirReportes;
		IMAGENES_DIR = dirImagenes;
		REPORT_NAME = REPORT_DIR + "/Ficha_Animal.jasper";
		REPORT_NAME_A4 = REPORT_DIR + "/Ficha_AnimalA4.jasper";
		//REPORT_NAME = REPORT_DIR + "Ficha_Animal";
		REPORT_NAME_GENEALOGICO = REPORT_DIR + "/ReporteGenealogico.jasper";
		REPORT_RC_NUM_PDF = REPORT_DIR + "/ReporteRCNUMPDF.jasper";
		REPORT_RC_NUM_TXT = REPORT_DIR + "/ReporteRCNUMTXT.jasper";
		REPORT_PADRON_HBA = REPORT_DIR + "/animalesHBA.jasper";
		//REPORT_PADRON_PEDIGREE = REPORT_DIR + "/animalesHBA.jasper";
		REPORT_NAME_EXPORTACION = REPORT_DIR + "/ExportacionReport.jasper";
		REPORT_NAME_EXPORTACION_A4 = REPORT_DIR + "/ExportacionReportA4.jasper";
		REPORT_CERTIFICADO_LACTANCIA = REPORT_DIR + "/Certificado_Lactancia.jasper" ;
		REPORT_EVENTOS = REPORT_DIR + "/ResumenEventos.jasper";
		REPORT_EVENTOS_QUERY = REPORT_DIR + "/ResumenEventosQuery.jasper";
		REPORT_MODELO = REPORT_DIR + "/ReporteBoletaCalificacion.jasper";
		REPORT_TAMBOS_PROPIETARIO = REPORT_DIR + "/reporteDatosTambosPropietario.jasper";
		REPORT_PROPIETARIO = REPORT_DIR + "/listadoProp.jasper";
REPORT_FORMULARIO_C89 = REPORT_DIR + "/Formulario_C89.jasper";		
REPORT_PROCESOS_XLS = REPORT_DIR + "/ConsultasLotesQuery.jasper";
		REPORT_PROCESOS_XLS_CRIAS = REPORT_DIR + "/ConsultasLotesQueryConCria.jasper";
		REPORT_PROCESOS_PDF = REPORT_DIR + "/ConsultasLotesQueryPDF.jasper";
		REPORT_PROCESOS_PDF_CRIAS = REPORT_DIR + "/ConsultasLotesQueryPDFConCria.jasper";
		REPORT_TAMBO_CRIAS_PDF = REPORT_DIR + "/ReporteTamboCriaInscriptas.jasper";
		REPORT_TAMBO_CRIAS_XLS = REPORT_DIR + "/ReporteTamboCriaInscriptasXLS.jasper";
		REPORT_ICAR = REPORT_DIR + "/ReporteIcar.jasper";
		REPORT_INSCRIPTOS = REPORT_DIR + "/ReporteInscriptos.jasper";
		REPORT_ESTAD_LACT = REPORT_DIR + "/ReporteEstadisticoLactancia.jasper";
		REPORT_ESTAD_LACT_XLS = REPORT_DIR + "/ReporteEstadisticoLactanciaXLS.jasper";

		REPORT_NOMBRES_HBA_TXT = REPORT_DIR + "/ReporteNombresHBA.jasper";
	}
	
	// Methods ----------------------------------------------------------------
	@SuppressWarnings({"unchecked","unchecked"})
	/**
	 * Este metodo se encarga de generar el reporte Ficha de Animal, dado el id del
	 * animal.
	 */
	public byte[] makeReport(Long idAnimal,String hoja) {
		
	//	StandaloneHibernateStrategy.getInstance().openNewSession();
	
		byte[] bytes = null;
		
		
		try {
			 //ReportManager rm = ReportManager.getInstance();
			 // JasperReport fileJasperReport = rm.getCompiledReport(REPORT_NAME + ".jasper");
			//compilarFicha(context);
			//JasperCompileManager.compileReportToFile(wrapper_fichaAnimal,"C:\\proyectos\\Acha\\branches\\sinmaven\\fuentes\\webapp\\reports\\reports\\FichaAnimal.jasper"); 
			// JasperReport jasperReport = (JasperReport) JRLoader.loadObject(REPORT_DIR + "Ficha_Animal.jasper");
			//  System.out.println("taaaaaa");
	           
			AnimalUnicoDataSource animalDataSource = new AnimalUnicoDataSource(idAnimal);
			Map parametros = new HashMap();
			
			//String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			parametros.put("DIR_IMG",IMAGENES_DIR);                        
			parametros.put("DIR_REPORTES",REPORT_DIR);
			parametros.put("servicio",null);
			parametros.put("exportacion",null);
			if(hoja.equals("A4"))
				bytes = JasperRunManager.runReportToPdf(REPORT_NAME_A4, parametros, animalDataSource);
			else
				bytes = JasperRunManager.runReportToPdf(REPORT_NAME, parametros, animalDataSource);
			//bytes = JasperRunManager.runReportToPdf(REPORT_NAME, parametros, animalDataSource);
			//bytes = JasperRunManager.runReportToPdf(jasperReport, parametros, animalDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//	StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		
		return bytes;
		
	}
	private void compilarFicha(String context) {
		try {
			System.setProperty("jasper.reports.compile.class.path",context+"\\lib/jasperreports-1.2.0.jar" + 
					System.getProperty("path.separator") +context+"\\classes/");
		System.setProperty("jasper.reports.compile.temp",REPORT_DIR); 
		String s = context+"\\lib/jasperreports-1.2.0.jar" + 
		System.getProperty("path.separator") +context+"\\classes/";
			String fichaAnimal_SubCompRacial= REPORT_DIR + "reporteComposicionRacial.jrxml";
	     JasperCompileManager.compileReportToFile(fichaAnimal_SubCompRacial);
	        
	     String fichaAnimal_SubLactancias= REPORT_DIR + "reporteLactanciasMadreNuevo.jrxml";
	      JasperCompileManager.compileReportToFile(fichaAnimal_SubLactancias);			
			
		String fichaAnimal = REPORT_DIR + "AnimalReport.jrxml";
		JasperCompileManager.compileReportToFile(fichaAnimal);           
		
		System.out.println(s);
			String wrapper_fichaAnimal= REPORT_NAME+".jrxml";
			JasperCompileManager.compileReportToFile(wrapper_fichaAnimal,REPORT_NAME+".jasper");
		} catch (JRException e) {
			
			e.printStackTrace();
		} 
		
	}

	public byte[] makeReportGenealogico(Long idAnimal) {
		
		//	StandaloneHibernateStrategy.getInstance().openNewSession();
		
			byte[] bytes = null;
			
			
			try {
				AnimalUnicoDataSource animalDataSource = new AnimalUnicoDataSource(idAnimal);
				Map parametros = new HashMap();
				
				//String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
				parametros.put("DIR_IMG",IMAGENES_DIR);                        
				parametros.put("DIR_REPORTES",REPORT_DIR);
				bytes = JasperRunManager.runReportToPdf(REPORT_NAME_GENEALOGICO, parametros, animalDataSource);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//	StandaloneHibernateStrategy.getInstance().commitCurrentSession();
			
			return bytes;
			
		}
	public byte[] makeReportCertificacionExportacion(Animal animal,String expor, String firma,String hojaTipo) {
		
		//	StandaloneHibernateStrategy.getInstance().openNewSession();
		
			byte[] bytes = null;
			
			
			try {
				AnimalUnicoDataSource animalDataSource = new AnimalUnicoDataSource(animal.getId());
				Map parametros = new HashMap();
				
				
			/*EvtServicio ser=null;	
	    	LinkedList servicios = (LinkedList) animal.getEventos(Evento.EVT_TIPO_SVC);
			Collections.sort(servicios, new EventosPorFechaYTipo());
			if(!servicios.isEmpty())
				ser= (EvtServicio)servicios.get(servicios.size()-1);
			*/

				//String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
				parametros.put("DIR_IMG",IMAGENES_DIR);                        
				parametros.put("DIR_REPORTES",REPORT_DIR);
				//parametros.put("servicio",ser);
				parametros.put("exportacion",expor);
				parametros.put("firma",firma);
				if(hojaTipo.equals("A4"))
					bytes = JasperRunManager.runReportToPdf(REPORT_NAME_EXPORTACION_A4, parametros, animalDataSource);
				else
					bytes = JasperRunManager.runReportToPdf(REPORT_NAME_EXPORTACION, parametros, animalDataSource);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//	StandaloneHibernateStrategy.getInstance().commitCurrentSession();
			
			return bytes;
			
		}
	 @SuppressWarnings("unchecked")
	public byte[] makeReport(ar.org.sicel.proc.v1.anmodif.Animal[] ans) {

	        StandaloneHibernateStrategy.getInstance().openNewSession();
	        byte[] bytes = null;

	        
	        try {
	            AnimalDataSource animalDataSource = new AnimalDataSource(ans);
	            Map parametros = new HashMap();
	            
	            //String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
	            parametros.put("DIR_IMG",IMAGENES_DIR);//dir_img);                        
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
	
//	public static void main(String[] args) throws MarshalException, ValidationException, FileNotFoundException, IOException {
//	Report report = new Report();
//	
//	File f = new File(REPORT_DIR + "animsModificados.xml");
//	String entrada = IOUtils.toString(new FileInputStream(f));
//	AnimsModificados anims = (AnimsModificados)AnimsModificados.unmarshal(new StringReader(entrada));
//	
//	byte[] bytes = report.makeReport(anims.getAnims().getAnimal());
//	
//	// Copiamos el pdf resultado
//	File salida = new File(REPORT_DIR + "animsModificados.pdf");
//	FileOutputStream out = new FileOutputStream(salida);
//	out.write(bytes);
//	out.flush();
//	out.close();
//	
//	System.out.println("Termino");
//	}
	
	@SuppressWarnings({"unchecked","unchecked", "unchecked"})
	public byte[] makeReportResumen(Collection resumen, Eclo eclo, Long numLote,ProcProces proc,Hashtable h) {
		/*byte[] bytes = null;		   
		try {
			
			Map parametros = new HashMap();
			//String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			String dir_img = IMAGENES_DIR;	            
			
			parametros.put("IMG_ACHA",dir_img+ "\\acha.jpg");        
			parametros.put("ECLO",eclo);
			parametros.put("LOTE",numLote);
			//bytes = JasperRunManager.runReportToPdf(REPORT_RESUMEN, parametros, new JRBeanCollectionDataSource(resumen));
			bytes = JasperRunManager.runReportToPdf(REPORT_DIR+"/ResumenProceso.jasper", parametros, new JRBeanCollectionDataSource(resumen));
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;*/
		byte[] bytes = null;		   
		try {
			
			Map parametros = new HashMap();
			//String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			String dir_img = IMAGENES_DIR;	            
			
			parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
			parametros.put("IMG_SICEL",dir_img+ "/sicel.jpg");
			parametros.put("ECLO",eclo);
				parametros.put("LOTE",numLote);
				parametros.put("PROCESO",proc.getId());
				parametros.put("FECHA_RECEPCION",proc.getFechaEntrada());
				parametros.put("FECHA_PROCESO_LOTE",proc.getFecha());
				parametros.put("CANT_ESTAB",h.get("CANT_ESTAB"));
				parametros.put("CANT_EVT_ESTAB",h.get("CANT_EVT_ESTAB"));
				parametros.put("CANT_EVT_ANIM",h.get("CANT_EVT_ANIM"));
				parametros.put("CANT_ANIM",h.get("CANT_ANIM"));
		
			//bytes = JasperRunManager.runReportToPdf(REPORT_RESUMEN, parametros, new JRBeanCollectionDataSource(resumen));
			bytes = JasperRunManager.runReportToPdf(REPORT_DIR+"/ResumenProceso.jasper", parametros, new JRBeanCollectionDataSource(resumen));
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	/**
	 * Metodo al cual se le pasa una hashtable con la cantidad de eventos por tipo para luego ser mostrado
	 * Este metodo está reemplazando a makeReportEventos el cual se le pasaba una coleccion con todos los eventos
	 * con el costo que genera eso
	 * @param h
	 * @return
	 */
	public byte[] makeReportResumenEventos(Map parametros) {
		
		
		byte[] bytes = null;		   
		try {
			
			//Map parametros = new HashMap();
			//String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			String dir_img = IMAGENES_DIR;	            
			parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
			parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
			parametros.put("IMG_SICEL",dir_img+ "/sicel.jpg");
			//Connection con = null;
			
			bytes = JasperRunManager.runReportToPdf(this.REPORT_EVENTOS_QUERY, parametros);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
		
		   
	
			
			
			
			
			
			
			
	}
	public byte[] makeReportEventos(Collection resumen) {
		
		byte[] bytes = null;		   
		try {
			
			Map parametros = new HashMap();
			//String dir_img = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			String dir_img = IMAGENES_DIR;	            
			
			parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
			parametros.put("IMG_SICEL",dir_img+ "/sicel.jpg");
			bytes = JasperRunManager.runReportToPdf(this.REPORT_EVENTOS, parametros, new JRBeanCollectionDataSource(resumen));
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	@SuppressWarnings({"unchecked","unchecked"})
	public byte[] makeReportCertificadosLactancia(ar.org.sicel.proc.v1.anmodif.Animal[] ans) {
		
		//StandaloneHibernateStrategy.getInstance().openNewSession();
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
		
		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		
		return bytes;
	}
public byte[] makeReportCertificadosLactancia(Animal a) {
		byte[] bytes = null;
		try {
			AnimalDataSource animalDataSource = new AnimalDataSource(a);
			Map parametros = new HashMap();
			parametros.put("DIR_REPORTES",REPORT_DIR);
			parametros.put("titulo", "Constancia de Lactancia en Curso");
			
			bytes = JasperRunManager.runReportToPdf(REPORT_CERTIFICADO_LACTANCIA, parametros, animalDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	/**
	 * Metodo que es usado cuando se informa un numero de lactancia en especial para obtener el reporte
	 * @param a
	 * @return
	 */
	public byte[] makeReportCertificadoLactanciaNumero(Hembra a) {
		byte[] bytes = null;
		try {
			AnimalDataSource animalDataSource = new AnimalDataSource(a);
			Map parametros = new HashMap();
			parametros.put("DIR_REPORTES",REPORT_DIR);
			
			if(a.getLactancias().size()==0)
				parametros.put("titulo", "Constancia de Lactancia en Curso");
			else
				parametros.put("titulo", "Informacion de Lactancia Cerrada");
			
			bytes = JasperRunManager.runReportToPdf(REPORT_CERTIFICADO_LACTANCIA, parametros, animalDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public byte[] makeRCNUMPDF(String query) {
			
			byte[] bytes = null;
			
			try {
				
				Map parametros = new HashMap();
				
				parametros.put("DIR_REPORTES",REPORT_DIR);
				parametros.put("QUERY",query);
				bytes = JasperRunManager.runReportToPdf(REPORT_RC_NUM_PDF, parametros,StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return bytes;
		}
		
		public byte[] makeRCNUMTXT(String query) {
			
			
			byte[] bytes = null;
			
			try {
				
				Map parametros = new HashMap();
				
				parametros.put("DIR_REPORTES",REPORT_DIR);
				parametros.put("QUERY",query);
				//bytes = JasperRunManager.runReportToPdf(REPORT_RC_NUM_PDF, parametros, new AnimalesDataSource(animales));
				bytes = this.createReportCSV(this.getJasperReport(REPORT_RC_NUM_TXT),parametros);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
			
			return bytes;
		} 
		
		public byte[] makeNombresHBATXT(Map parametros,String path) {
			
			
			byte[] bytes = null;
			
			try {
				
				
				parametros.put("DIR_REPORTES",REPORT_DIR);
				//bytes = JasperRunManager.runReportToPdf(REPORT_RC_NUM_PDF, parametros, new AnimalesDataSource(animales));
				bytes = this.createReportTXT(this.getJasperReport(REPORT_NOMBRES_HBA_TXT),parametros,path);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
			
			return bytes;
		} 
		
		public byte[] makePadronPedigree(List animales,String path) {
			
			
			byte[] bytes = null;
			
			try {
				
				Map parametros = new HashMap();
				
				parametros.put("DIR_REPORTES",REPORT_DIR);
				//bytes = this.createReportTXT(this.getJasperReport(REPORT_PADRON_PEDIGREE),new AnimalesDataSource(animales),parametros);
				bytes = this.createReportExcel(this.getJasperReport(REPORT_PADRON_HBA),new AnimalesHBADataSource(animales),parametros,path);
				//bytes = this.createReportTXT(this.getJasperReport(REPORT_PADRON_PEDIGREE),new MapListDataSource(animales),parametros);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
			
			return bytes;
		} 
		
		  public static byte[] createReportCSV(JasperReport jr,Map mapParameters) {
		    	JRCsvExporter exporter = new JRCsvExporter();
		    	
		    	ByteArrayOutputStream rtfReport = new ByteArrayOutputStream();

		    	try {    		
					JasperPrint jasperPrint = JasperFillManager.fillReport(jr, mapParameters, StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
					exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, rtfReport);				
					exporter.setParameter(JRCsvExporterParameter.IGNORE_PAGE_MARGINS, true);
					exporter.exportReport();
					} catch (JRException e) {
						System.out.println("ERROR al crear el reporte en txt");
				}
					byte[] bytes = rtfReport.toByteArray();
				return bytes;
			}
		  
		  
		  public static byte[] createReportTXT(JasperReport jr,Map mapParameters,String path) {
			  	JRTextExporter exporter = new JRTextExporter();
		    	ByteArrayOutputStream rtfReport = new ByteArrayOutputStream();

		    	try {  
		    		JasperPrint jasperPrint = JasperFillManager.fillReport(jr, mapParameters, StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
					exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, rtfReport);
					exporter.setParameter(JRTextExporterParameter.OUTPUT_FILE, path);
				    exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(250));
			        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(50));
			        exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(4));
			        exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(8));
					//exporter.setParameter(JRTextExporterParameter.IGNORE_PAGE_MARGINS, true);
					exporter.exportReport();
					} catch (JRException e) {
						System.out.println("ERROR al crear el reporte en txt");
				}
					byte[] bytes = rtfReport.toByteArray();
				return bytes;
			}

	     
		  
		  public static byte[] createReportExcel(JasperReport jr, JRDataSource source, Map mapParameters,String path) {
		    	JRXlsExporter exporter = new JRXlsExporter();
		    	
		    	ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();

		    	try {    		
					JasperPrint jasperPrint = JasperFillManager.fillReport(jr, mapParameters,source);
					exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, path);
					exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS , true);
					exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET , 60000);
					exporter.exportReport();
					
					} catch (JRException e) {
						System.out.println("ERROR al crear el reporte en excel");
					
				}
					byte[] bytes = xlsReport.toByteArray();
				return bytes;
		    	
				
			}
		  
		  /**
			 * Obtengo el reporte para ejecutarlo
			 * @param s - Nombre del archivo
			 * @return JasperReport
			 */
		    public static JasperReport getJasperReport(String s) {
		        JasperReport jasperreport = null;
		        try
		        {
		            jasperreport = (JasperReport)JRLoader.loadObject(s);
		        }
		        catch(JRException jrexception)
		        {
		        	jrexception.printStackTrace();
		        }
		        return jasperreport;
		    }

		    
		    
		    
		    @SuppressWarnings("unchecked")
			public byte[] makeReportLote(List<Animal> ans,String hojaTipo) {

		        byte[] bytes = null;

		        try {
			       AnimalListaDataSource animalDataSource = new AnimalListaDataSource(ans);
			            Map parametros = new HashMap();
			     
			            parametros.put("DIR_IMG",IMAGENES_DIR);//dir_img);                        
			            parametros.put("DIR_REPORTES",REPORT_DIR);
			            if(hojaTipo.equals("A4"))
			            	bytes = JasperRunManager.runReportToPdf(REPORT_NAME_A4, parametros, animalDataSource);
			            else
			            	bytes = JasperRunManager.runReportToPdf(REPORT_NAME, parametros, animalDataSource);
			    } catch (JRException e) {
			        e.printStackTrace();
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		        return bytes;

		    }
		    /**
		     * Metodo que se usa para descargar varios certificados de expotacion
		     * @param ans
		     * @return
		     */
		    public byte[] makeReportLoteExport(List<Animal> ans,String expor,String firma) {

		        byte[] bytes = null;

		        try {
			       AnimalListaDataSource animalDataSource = new AnimalListaDataSource(ans);
			            Map parametros = new HashMap();
			     
			            parametros.put("DIR_IMG",IMAGENES_DIR);                        
						parametros.put("DIR_REPORTES",REPORT_DIR);
						parametros.put("exportacion",expor);
						parametros.put("firma",firma);
						bytes = JasperRunManager.runReportToPdf(REPORT_NAME_EXPORTACION_A4, parametros, animalDataSource);
			    } catch (JRException e) {
			        e.printStackTrace();
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		        return bytes;

		    }
		    
			/**
			 * Metodo al cual se le pasa una hashtable con la cantidad de eventos por tipo para luego ser mostrado
			 * Este metodo está reemplazando a makeReportEventos el cual se le pasaba una coleccion con todos los eventos
			 * con el costo que genera eso
			 * @param h
			 * @return
			 */
			public byte[] makeReportBoleta(Map parametros) {
				byte[] bytes = null;		   
				try {
					//String dir_img = IMAGENES_DIR;	            
					//parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
					//parametros.put("IMG_SICEL",dir_img+ "/sicel.jpg");
					//StandaloneHibernateStrategy.getInstance().getCurrentSession().connection()
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
					bytes = JasperRunManager.runReportToPdf(REPORT_MODELO, parametros,
							StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
				} catch (JRException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return bytes;
			}
@SuppressWarnings("unchecked")
			public byte[] makeReportTambosPropietarios(Propietario prop) {
				byte[] bytes = null;		   
				try {
					Map parametros = new HashMap();
					parametros.put("ID",prop.getId());
					parametros.put("NOMBRE_Y_APELLIDO",prop.getNombreContacto());
					parametros.put("CUIT_CUIL",prop.getCuit());
					parametros.put("CUIG",prop.getCuig());
					parametros.put("RENSPA",prop.getRenspa());
					parametros.put("PREFIJO",prop.getPrefijo());
					parametros.put("SOCIO",prop.getSocio() != null ? prop.getSocio().toString() : "");
					parametros.put("HAR",prop.getHar() != null ? prop.getHar().toString() : "");
					parametros.put("ESTADO",prop.getActivo());
					parametros.put("PROPIETARIO_ID",prop.getId());
					parametros.put("PERSONA_FISICA",prop.getEsPersonaFisica());
					Calendar cal = new GregorianCalendar();
					parametros.put("FECHA",cal.getTime());
					parametros.put("DIR_REPORTES",REPORT_DIR);
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
					bytes = JasperRunManager.runReportToPdf(REPORT_TAMBOS_PROPIETARIO, parametros,
							StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
				} catch (JRException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return bytes;
			}
		 public static byte[] createReportExcel(JasperReport jr, Connection conx, Map mapParameters,String path) {
		    	JRXlsExporter exporter = new JRXlsExporter();
		    	ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		    	try {    		
					
		    		JasperPrint jasperPrint = JasperFillManager.fillReport(jr, mapParameters,conx);
					exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, xlsReport);
					exporter.exportReport();
					
					} catch (JRException e) {
						System.out.println("ERROR al crear el reporte en excel");
					
				}
					
					byte[] bytes = xlsReport.toByteArray();
					
				return bytes;
		
			}

			public byte[] makeReportPropietarios(String query,String path) {
				byte[] bytes = null;
				
				try {
					
					Map parametros = new HashMap();
					
					parametros.put("DIR_REPORTES",REPORT_DIR);
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
					parametros.put("QUERY", query);
					
					bytes = ProjectReport.createReportExcel(ProjectReport.getJasperReport(REPORT_PROPIETARIO),StandaloneHibernateStrategy.getInstance().getCurrentSession().connection()
														,parametros,path);
					//bytes = JasperRunManager.runReportToPdf(REPORT_PROPIETARIO,parametros,
						//	StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bytes;
			}
			public byte[] makeReportProcesos(String path,Date fechaI,Date fechaF,boolean conCrias) {
				byte[] bytes = null;
				
				try {
					
					Map parametros = new HashMap();
					
					parametros.put("DIR_REPORTES",REPORT_DIR);
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
					//parametros.put("QUERY", query);
					parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
					 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
					 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
					
					bytes = ProjectReport.createReportExcel(ProjectReport.getJasperReport((conCrias)?REPORT_PROCESOS_XLS_CRIAS:REPORT_PROCESOS_XLS),StandaloneHibernateStrategy.getInstance().getCurrentSession().connection()
														,parametros,path);
					//bytes = JasperRunManager.runReportToPdf(REPORT_PROPIETARIO,parametros,
						//	StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bytes;
			}
			public byte[] makeReportProcesos(Date fechaI,Date fechaF,boolean conCrias) {
				
				byte[] bytes = null;
				
				try {
					
					Map parametros = new HashMap();
					
					parametros.put("DIR_REPORTES",REPORT_DIR);
					String dir_img = IMAGENES_DIR;	            
					parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
					parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
					 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
					 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
					 parametros.put("FECHA_DESDE_P", DateUtils.format(fechaI, "dd/MM/yyyy"));
					 parametros.put("FECHA_HASTA_P", DateUtils.format(fechaF, "dd/MM/yyyy"));
					
					//parametros.put("QUERY",query);
					bytes = JasperRunManager.runReportToPdf((conCrias)?REPORT_PROCESOS_PDF_CRIAS:REPORT_PROCESOS_PDF, parametros,StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
				} catch (JRException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			return bytes;
			}
			
			public byte[] makeReportTambosCriasPDF(Date fechaI,Date fechaF) {
				
				byte[] bytes = null;
				
				try {
					
					Map parametros = new HashMap();
					
					parametros.put("DIR_REPORTES",REPORT_DIR);
					String dir_img = IMAGENES_DIR;	            
					parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
					parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
					 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
					 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
					 parametros.put("FECHA_DESDE_P", DateUtils.format(fechaI, "dd/MM/yyyy"));
					 parametros.put("FECHA_HASTA_P", DateUtils.format(fechaF, "dd/MM/yyyy"));
					
					//parametros.put("QUERY",query);
					bytes = JasperRunManager.runReportToPdf(REPORT_TAMBO_CRIAS_PDF, parametros,StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
				} catch (JRException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			return bytes;
			}

			public byte[] makeReportTambosCriasXLS(String path, Date fechaI,Date fechaF) {
				byte[] bytes = null;
				
				try {
					
					Map parametros = new HashMap();
					
					parametros.put("DIR_REPORTES",REPORT_DIR);
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
					//parametros.put("QUERY", query);
					parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
					parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
					parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
				
					bytes = ProjectReport.createReportExcel(ProjectReport.getJasperReport(REPORT_TAMBO_CRIAS_XLS),StandaloneHibernateStrategy.getInstance().getCurrentSession().connection()
														,parametros,path);
					//bytes = JasperRunManager.runReportToPdf(REPORT_PROPIETARIO,parametros,
						//	StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bytes;
			}
			
			
			public byte[] makeReportIcar(Date fechaI, Date fechaF) {
				byte[] bytes = null;
				
				try {
					
					Map parametros = new HashMap();
					String dir_img = IMAGENES_DIR;
					parametros.put("DIR_REPORTES",REPORT_DIR);
					parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
					 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
					 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
					 parametros.put("FECHA_DESDE_T", DateUtils.format(fechaI, "dd/MM/yyyy"));
					 parametros.put("FECHA_HASTA_T", DateUtils.format(fechaF, "dd/MM/yyyy"));
					 parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
					//parametros.put("QUERY", query);

					bytes = JasperRunManager.runReportToPdf(REPORT_ICAR, parametros,StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());

					//bytes = JasperRunManager.runReportToPdf(REPORT_PROPIETARIO,parametros,
						//	StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
					
				} catch (JRException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			return bytes;
		}
			
			
			public byte[] makeReportinscriptos(Date fechaI, Date fechaF) {
				byte[] bytes = null;
				
				try {
					
					Map parametros = new HashMap();
					String dir_img = IMAGENES_DIR;
					parametros.put("DIR_REPORTES",REPORT_DIR);
					parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
					 parametros.put("FECHA_DESDE", DateUtils.format(fechaI, "yyyy/MM/dd"));
					 parametros.put("FECHA_HASTA", DateUtils.format(fechaF, "yyyy/MM/dd"));
					 parametros.put("FECHA_DESDE_T", DateUtils.format(fechaI, "dd/MM/yyyy"));
					 parametros.put("FECHA_HASTA_T", DateUtils.format(fechaF, "dd/MM/yyyy"));
					 parametros.put("DIA_DE_HOY", DateUtils.format(new Date(), "dd/MM/yyyy"));
					//parametros.put("QUERY", query);

					bytes = JasperRunManager.runReportToPdf(REPORT_INSCRIPTOS, parametros,StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());

					//bytes = JasperRunManager.runReportToPdf(REPORT_PROPIETARIO,parametros,
						//	StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
					
				} catch (JRException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			return bytes;
		}	

			

			public byte[] makeReportEstadisticoLactancia(Map parametros) {
				byte[] bytes = null;
				try {
				
					String dir_img = IMAGENES_DIR;
					parametros.put("DIR_REPORTES",REPORT_DIR);
					parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
					parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");

					bytes = JasperRunManager.runReportToPdf(REPORT_ESTAD_LACT, parametros,StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());

					
				} catch (JRException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bytes;
		} 
		
		
		public byte[]makeReportEstadisticoLactanciaXLS(String path, Map parametros) {
			byte[] bytes = null;
			
			try {
				
				String dir_img = IMAGENES_DIR;
				parametros.put("DIR_REPORTES",REPORT_DIR);
				parametros.put("IMG_ACHA",dir_img+ "/acha.jpg");
				parametros.put("SUBREPORT_DIR",REPORT_DIR+"/");
				
				bytes = ProjectReport.createReportExcel(ProjectReport.getJasperReport(REPORT_ESTAD_LACT_XLS),StandaloneHibernateStrategy.getInstance().getCurrentSession().connection()
													,parametros,path);
				//bytes = JasperRunManager.runReportToPdf(REPORT_PROPIETARIO,parametros,
					//	StandaloneHibernateStrategy.getInstance().getCurrentSession().connection());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return bytes;
		}
		
}
