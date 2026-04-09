/**
 * 
 */
package ar.org.sicel.proc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Blob;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.Driver;
import org.apache.fop.messaging.MessageHandler;
import org.apache.log4j.Logger;
import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.exception.GenericJDBCException;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.proc.exceptions.ProcessException;
import ar.org.sicel.proc.services.IProcesadorXML;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;
import ar.org.sicel.proc.services.impl.ProcesadorLote;
import ar.org.sicel.proc.services.locator.ServiceLocator;
import ar.org.sicel.proc.servlet.ProcessThreadServlet;
import ar.org.sicel.proc.thread.ProcessThread;
import ar.org.sicel.proc.v1.anmodif.Anims;
import ar.org.sicel.proc.v1.anmodif.AnimsModificados;
import ar.org.sicel.proc.v1.lote.Alta;
import ar.org.sicel.proc.v1.lote.BajaEvt;
import ar.org.sicel.proc.v1.lote.Control;
import ar.org.sicel.proc.v1.lote.Estab;
import ar.org.sicel.proc.v1.lote.Lote;
import ar.org.sicel.upload.dao.ConstantsUpload;
import ar.org.sicel.util.Email;
import ar.org.sicel.util.EmailSender;
import ar.org.sicel.util.Sicel3Conf;
/**
 * @author jdivars
 *
 */
public class ProcessWork implements Runnable {
	static Logger log = Logger.getLogger(ProcessWork.class);

    static String MENSAJES_XSLT = null;
    static String RESUMEN_XSLT = null;
    static String PROC_DIR = null;
    static String REPORTES_DIR = null;
    static String IMAGENES_DIR = null;
    static int BUFFER_SIZE = 999999;
    
    String PROC_PROCESO_DIR = null;
    Lote loteResultado = null;
    AnimsModificados animsModificados = null;
    String fileName;
    ProcProces proc;
    private Usuario user=null;
    private ProcessThreadServlet servlet= null;
    
    /**
     * 
     * @param fileName
     * @param proc
     * @param obs
     * @param dirProc path absoluto dentro de la aplic a la direcctorio de procesamiento
     * @param dirReportes path absoluto dentro de la aplic a la direcctorio de los reportes
     * 
     */
    public ProcessWork(ProcessThreadServlet servlet) {
       	this.servlet= servlet;
    }
    
	/*public void run() {
		File f = new File(fileName);
		try {
		    execute(fileName);
			//perform();		    
		    makeAndSaveZip(proc);		    
	        //  this.saveSalida(path);
		} catch (IOException e) {
			log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
			
		}catch (Exception e){
			log.error(e.getMessage());
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                      ProcMsg.FATAL, new String[] { e.getMessage() });
            proc.addMsg(msg);
            ResultadosUtil.addResultado(loteResultado, msg);
		} 
		
		finally {// bien o mal el procesamiento, se borra el XML			
			f.delete();			
			log.debug("Se elimina el archivo que fue procesado");
		}
	}*/
    
    public void run() throws HibernateException{
    	File f = null;
    	try {
    		proc= ProcProcesDAO.getFirstProcesseing();
    		log.warn("Comienza a procesar:"+proc.getNombreArchivoEntrada());
    		setDataProcc();
   		 	f = new File(fileName);
   		 	StandaloneHibernateStrategy.getInstance().getCurrentSession();
   			log.warn("Ejecutando "+fileName);
   			execute(fileName);
   			log.warn("Termino de procesar "+fileName+ "se comienza a armar la salida");
   			makeAndSaveZip(proc);
   			log.warn("Finalizo la salida "+fileName);
   			if(f!=null)
	    		f.delete();	
   			/**
   			 * Cuando esta todo ok, hago el commit
   			 */
   			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
   				
    	} catch (ProcessException e) {			
   			log.error(e.getMessage());
   			//e.printStackTrace();
   			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
   			sendMailErrorProcesamiento(user, proc, e.getMessage(),e.getLote());
   			
   		} catch (HibernateException e) {	
   			log.error(e.getMessage()+"se desconectó la base");
   			//this.run();
   			
   		}////////yo
   		finally {	
   			log.warn("Se termino con el proceso "+ fileName);
			ProcessThread.getInstance().setProcesando(false);
			ProcessThread.getInstance().despertar();
			System.gc();
		}
	}
    
	
    private synchronized void setDataProcc() {
		try {
			log.warn("Configurando");
			PROC_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("destinoDir").getValue();
			REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
			String dirXSLT = Sicel3Conf.getConf().getConfProcesamiento().getChild("xsltDir").getValue();
	       	MENSAJES_XSLT = dirXSLT + "/mensajes.xsl";
	       	RESUMEN_XSLT = dirXSLT + "/resumen.xsl";

			user= proc.getUsuarioProcesador();
			log.info("Context path" + this.getContextPath(PROC_DIR));
			String aux = PROC_DIR + "entradas/" + "entrada_" + new Date().getTime();

			PROC_DIR= getContextPath(PROC_DIR); 
	       	IMAGENES_DIR= getContextPath(IMAGENES_DIR); 
	       	REPORTES_DIR= getContextPath(REPORTES_DIR); 
	       	MENSAJES_XSLT= getContextPath(MENSAJES_XSLT); 
	       	RESUMEN_XSLT= getContextPath(RESUMEN_XSLT); 
			
			String pathDir = this.getContextPath(aux);
			String path =  pathDir + ".zip";
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(path);
	        InputStream is = proc.getContenidoEntrada().getBinaryStream();
	        int BUFFER_SIZE = (int)proc.getContenidoEntrada().length();
	        byte[] data = new byte[BUFFER_SIZE];
	        origin = new BufferedInputStream(is, BUFFER_SIZE);
	        int count;
	        while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
	            dest.write(data, 0, count);
	        }
	        dest.close();
	        fileName= this.unZip(path,pathDir);
            
		} catch (Exception e) {
			log.error(e);
		}
	}

    private String getContextPath(String subPath){
		return servlet.getServletContext().getRealPath(subPath);
	}

	/**
     * 
     * @param fileName
     * @throws ProcessException - se lanza cuando hay alguna excepcion en el procesamiento 
     */
	public synchronized void execute(String fileName) throws ProcessException,HibernateException {
	//	File f = new File(fileName);//para despues borrar el xml
		log.warn("Inicio de procesador para " + fileName);
        // Del archivo de entrada, ejecutamos el procso del mismo.
        execPROC(fileName);
        
        try {// Del resultado del proceso, creamos los pdf dado los xslt.
            makePdfXslt();
        } catch (TransformerException e) {
            log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
        } catch (FileNotFoundException e) {
            log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
        }        
        
        try { // Animales modificados a partir del resultado del procesamiento.
            makeXMLAnimalesModificados();
            AnimsModificados lactanciasOficiales = makeXMLLactanciasOficiales();
            makeCertificadosLactancia(lactanciasOficiales);
        } catch (ValidationException e) {
            log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
        } catch (MarshalException e) {
            log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
        } catch (FileNotFoundException e) {
            log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
        } catch (IOException e) {
            log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
        } catch (HibernateException e) {
            log.error(e);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                    ProcMsg.FATAL, new String[] { e.getMessage() });
			proc.addMsg(msg);
			ResultadosUtil.addResultado(loteResultado, msg);
        }
        
        File f = new File(fileName);
        if (f.delete())
        	log.debug("Se borro el archivo "+fileName);
        log.info("Fin de procesador para " + fileName);
	}
    
    
    public AnimsModificados makeXMLLactanciasOficiales()     throws ValidationException, MarshalException, IOException  {
        Lote lote = loteResultado;
        AnimsModificados animsModificados = new AnimsModificados();
        animsModificados.setInformante(lote.getInformante());
        animsModificados.setIDLoteInte(lote.getIDLoteInte());
        animsModificados.setIDLoteSicel(lote.getIDLoteSicel());

        Set animalesModificados = AnimalesLactanciasOficiales.getAnimales();
        if (animalesModificados.size() > 0) {
            animsModificados.setAnims(new Anims());
            for (Iterator iter = animalesModificados.iterator(); iter.hasNext();) {
                Long idAnimal = (Long) iter.next();
                ar.org.sicel.proc.v1.anmodif.Animal animalCastor = new ar.org.sicel.proc.v1.anmodif.Animal();
                animalCastor.setIDAnim(idAnimal);
               
                animsModificados.getAnims().addAnimal(animalCastor);
            }
        }
        
        File salida = new File(PROC_PROCESO_DIR + "Proc"
                + lote.getIDLoteSicel() + "_lactancias_oficiales.xml");
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(salida), "ISO-8859-1"));
        // loteResultado.marshal(out2); no permite setear el encoding
        org.exolab.castor.xml.Marshaller marshaller = new org.exolab.castor.xml.Marshaller(
                out);
        marshaller.setEncoding("ISO-8859-1");
        marshaller.setSupressXMLDeclaration(false);
        marshaller.marshal(animsModificados);
       
        out.flush();
        out.close();
        return animsModificados;
    }

    public AnimsModificados makeXMLAnimalesModificados()
            throws ValidationException, MarshalException, IOException,
            HibernateException {
        //StandaloneHibernateStrategy.getInstance().openNewSession();
        Lote lote = loteResultado;
        AnimsModificados animsModificados = new AnimsModificados();
        animsModificados.setInformante(lote.getInformante());
        animsModificados.setIDLoteInte(lote.getIDLoteInte());
        animsModificados.setIDLoteSicel(lote.getIDLoteSicel());

        Long idProceso = lote.getIDLoteSicel();
        ProcProces procProces = ProcProcesDAO.findByPrimaryKey(idProceso);

        Set animalesModificados = procProces.getAnimalesModificados();
        if (animalesModificados.size() > 0) {
            animsModificados.setAnims(new Anims());
            for (Iterator iter = animalesModificados.iterator(); iter.hasNext();) {
                Animal animal = (Animal) iter.next();
                ar.org.sicel.proc.v1.anmodif.Animal animalCastor = new ar.org.sicel.proc.v1.anmodif.Animal();
                animalCastor.setIDAnim(animal.getId());
                // TODO Llenar los datos del registro.
                // ar.org.sicel.proc.v1.anmodif.Reg nuevoReg = new
                // ar.org.sicel.proc.v1.anmodif.Reg();
                // ar.org.sicel.proc.gen.Reg procReg = anims[j].getReg();
                // nuevoReg.setNReg(procReg.getNReg());
                // nuevoReg.setPais(ar.org.sicel.proc.v1.anmodif.types.STPais
                // .valueOf(procReg.getPais().toString()));
                // nuevoReg.setRaza(ar.org.sicel.proc.v1.anmodif.types.STRaza
                // .valueOf(procReg.getRaza().toString()));
                // nuevoReg.setSexo(ar.org.sicel.proc.v1.anmodif.types.STSexo
                // .valueOf(procReg.getSexo().toString()));
                // nuevoReg.setTReg(ar.org.sicel.proc.v1.anmodif.types.STTReg
                // .valueOf(procReg.getTReg().toString()));
                //
                // animal.setReg(nuevoReg);
                animsModificados.getAnims().addAnimal(animalCastor);
            }
        }
        //StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        // Copiamos en el directorio del procesamiento el xml según lo
        // creado en el método anterior.
        // FORMATO: <idProceso>_anims_proc.xml
        File salida = new File(PROC_PROCESO_DIR + "Proc"
                + lote.getIDLoteSicel() + "_anims_proc.xml");
       
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(salida), "ISO-8859-1"));
        // loteResultado.marshal(out2); no permite setear el encoding
        org.exolab.castor.xml.Marshaller marshaller = new org.exolab.castor.xml.Marshaller(
                out);
        marshaller.setEncoding("ISO-8859-1");
        marshaller.setSupressXMLDeclaration(false);
        marshaller.marshal(animsModificados);
        
        out.flush();
        out.close();

        return animsModificados;
    }
    
    private void makeCertificadosLactancia(AnimsModificados animsModificados)
    throws MarshalException, ValidationException, IOException {
    	// Desactivado por ahora


    	ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
    	if (animsModificados.getAnims() != null) {
    // 	Creamos las fichas de los animales que fueron modificados.
    		byte[] bytes = report.makeReportCertificadosLactancia(animsModificados.getAnims().getAnimal());
    // Copiamos en el directorio del procesamiento el pdf de las fichas.
    // FORMATO: <idProceso>_anims_proc.pdf
    		File pdf = new File(PROC_PROCESO_DIR + "Proc"+ 
    				loteResultado.getIDLoteSicel() + "_lactancias_oficiales.pdf");
    		FileOutputStream outPdf = new FileOutputStream(pdf);
    		outPdf.write(bytes);
    		outPdf.flush();
    		outPdf.close();
    	}
    }
    
    private void makeResumenProcesoJasper(Collection resumen, Eclo eclo, Long numLote) throws MarshalException, ValidationException, IOException {
    	ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
        Long cantEstab = new Long(loteResultado.getEstabs().getEstab().length);
         Enumeration enu = loteResultado.getEstabs().enumerateEstab();
        Long cantEvtEstab = 0L;
        Long cantEvtAni = 0L;
        Long cantAnim = 0L;
        Set todos = new HashSet();
        while(enu.hasMoreElements()){
        	Estab es = (Estab)enu.nextElement();
        	if(es.getEvtsEstab()!=null){
        		if(es.getEvtsEstab().getAltas()!=null){
        				cantEvtEstab = cantEvtEstab +  es.getEvtsEstab().getAltas().getAltaCount();
        				Enumeration enu1 = es.getEvtsEstab().getAltas().enumerateAlta();
        				while(enu1.hasMoreElements()){
        					Alta al = (Alta)enu1.nextElement();
        					todos.add(al.getIDAnim());
        				}
        				
        		}
        		if(es.getEvtsEstab().getBajasEvt()!=null){
        				cantEvtEstab= cantEvtEstab + es.getEvtsEstab().getBajasEvt().getBajaEvtCount();
        				Enumeration enu1 = es.getEvtsEstab().getBajasEvt().enumerateBajaEvt();
        				while(enu1.hasMoreElements()){
        					BajaEvt be = (BajaEvt)enu1.nextElement();
        					todos.add(be.getIDAnim());
        				}
        		}
        		if(es.getEvtsEstab().getControles()!=null){
        				cantEvtEstab = cantEvtEstab + es.getEvtsEstab().getControles().getControlCount();
		        		Enumeration enu1 = es.getEvtsEstab().getControles().enumerateControl();
						while(enu1.hasMoreElements()){
							Control be = (Control)enu1.nextElement();
							todos.add(be.getIDAnim());
						}
        		}
        		cantAnim = cantAnim + todos.size();
        		todos.clear();
        	}
        	if(es.getAnims()!=null){
        		//cantEvtAni = cantEvtAni + es.getAnims().getAnimalCount();
        		//ar.org.sicel.proc.v1.lote.Animal[] an =es.getAnims().getAnimal();
        		Enumeration enu2 = es.getAnims().enumerateAnimal();
        		while(enu2.hasMoreElements()){
        			ar.org.sicel.proc.v1.lote.Animal a = (ar.org.sicel.proc.v1.lote.Animal)enu2.nextElement();
        			cantEvtAni = cantEvtAni + a.getEvts().getEvtCount();
        			todos.add(a.getRp());
        		}
        		cantAnim = cantAnim + todos.size();
        	}
        	
        	
        }
        
        Hashtable h = new Hashtable();
        h.put("CANT_ESTAB",cantEstab);
        h.put("CANT_EVT_ESTAB",cantEvtEstab);
        h.put("CANT_EVT_ANIM",cantEvtAni);
        h.put("CANT_ANIM",cantAnim);
    	byte[] bytes = report.makeReportResumen(resumen,eclo,numLote,proc,h);
        File pdf = new File(PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_resumen_proc.pdf");
        FileOutputStream outPdf = new FileOutputStream(pdf);
        outPdf.write(bytes);
        outPdf.flush();
        outPdf.close();
    }
    
    private void makePdfXslt() throws FileNotFoundException,
            TransformerException {
       // String posfijoPdfFileName = "resumen";
       // execFOP(RESUMEN_XSLT, posfijoPdfFileName);
    	String posfijoPdfFileName = "mensajes";
        execFOP(MENSAJES_XSLT, posfijoPdfFileName);
    }

    private void execPROC(String xmlFileName) throws ProcessException,HibernateException {
        Lote lote = null;
        log.debug("Iniciando el unmarshaling de " + xmlFileName);
        File f = null;
        FileReader fileReader = null;        
        try {
            f = new File(xmlFileName);
            fileReader = new FileReader(f);
            lote = (Lote) Lote.unmarshal(fileReader);
            log.debug("Unmarshaling terminado.");
        } catch (MarshalException e) {
            log.error(e);
            //proc.setEstado(ConstantsUpload.PROCESSERROR);
            proc.setEstado(ConstantsUpload.STANDBY);
            throw new ProcessException(proc,ProcessException.MENSAJE_FORMATO_INVALIDO,e);
            //throw new RuntimeException("Error procesando el archivo. Error en el formato de xml.", e);
        } catch (ValidationException e) {
            log.error(e);
            //proc.setEstado(ConstantsUpload.PROCESSERROR);
            proc.setEstado(ConstantsUpload.STANDBY);
            throw new ProcessException(proc,ProcessException.MENSAJE_ERROR_VALIDACION, e);
            //throw new RuntimeException("Error procesando el archivo. Error de validación.", e);
        } catch (FileNotFoundException e) {
            log.error(e);
            //proc.setEstado(ConstantsUpload.PROCESSERROR);
            proc.setEstado(ConstantsUpload.STANDBY);
            throw new ProcessException(proc,ProcessException.MENSAJE_ARCHIVO_NO_ENCONTRADO, e);
            //throw new RuntimeException("No se encontro el archivo", e);
        }
        finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					log.error(e);
					 proc.setEstado(ConstantsUpload.STANDBY);
					throw new ProcessException(proc,ProcessException.MENSAJE_CERRAR_ARCHIVO, e);
					//throw new RuntimeException("Problemas mientras se estaba cerrando el stream de entrada", e);
				}
			}
		}
        log.debug("Iniciando el procesamiento propiamente.");
        IProcesadorXML proce = ServiceLocator.getProcesadorXML();
        //StandaloneHibernateStrategy.getInstance().openNewSession();
        AnimsModificados animsModificados = new AnimsModificados();
        // TODO Completar el id de proceso con ceros!!!!
    	Long numLote = lote.getIDLoteInte();
        Collection resumen = new LinkedList();
        try{
        	loteResultado = proce.procesar(proc,lote, animsModificados,resumen,user);
        } catch (HibernateException he) {
        	log.error(he);
        	throw new HibernateException(he.getMessage());
        	//throw new ProcessException(proc,ProcessException.MENSAJE_PROBLEMA_BASE_DATOS,String.valueOf(numLote.longValue()), he);
        	//throw new RuntimeException("Problema al obtener la entidad de control lechero con identificador: "+idEclo.toString(), he);
        } catch (ErrorFatal fe) {
        	if(fe.getCause()  instanceof GenericJDBCException) {
        		throw new HibernateException(fe.getMessage());
			}
        	log.error(fe.getCause());
        	
        	throw new ProcessException(proc,ProcessException.MENSAJE_PROBLEMA_BASE_DATOS,String.valueOf(numLote.longValue()), fe);
        	//throw new RuntimeException("Problema al obtener la entidad de control lechero con identificador: "+idEclo.toString(), he);
    	}
        File destDir = new File(PROC_DIR + "Proc"
                + loteResultado.getIDLoteSicel());
        PROC_PROCESO_DIR = destDir.getPath() + "/";
        destDir.mkdir();
        Long idEclo = lote.getInformante();
        try {
        	Eclo eclo = EcloDAO.findByPrimaryKey(idEclo);
            makeResumenProcesoJasper(resumen,eclo,numLote);
        } catch (MarshalException e1) {
        	log.error(e1);
        	throw new ProcessException(proc,ProcessException.MENSAJE_FORMATO_INVALIDO,String.valueOf(numLote.longValue()), e1);
        	//throw new RuntimeException("Problema con la construcción del reporte de resumen de proceso.", e1);
        } catch (ValidationException e1) {
            log.error(e1);
            throw new ProcessException(proc,ProcessException.MENSAJE_ERROR_VALIDACION,String.valueOf(numLote.longValue()), e1);
        	//throw new RuntimeException("Problema con la construcción del reporte de resumen de proceso.", e1);           
        } catch (FileNotFoundException e1) {
            log.error(e1);
            throw new ProcessException(proc,ProcessException.MENSAJE_ARCHIVO_NO_ENCONTRADO,String.valueOf(numLote.longValue()), e1);
        	//throw new RuntimeException("Problema con la construcción del reporte de resumen de proceso.", e1);
        } catch (IOException e1) {
            log.error(e1);
            throw new ProcessException(proc,ProcessException.MENSAJE_PROBLEMA_IO,String.valueOf(numLote.longValue()), e1);
        	//throw new RuntimeException("Problema con la construcción del reporte de resumen de proceso.", e1);
        } /*catch (HibernateException he) {
        	log.error(he);
        	throw new ProcessException(proc,ProcessException.MENSAJE_PROBLEMA_BASE_DATOS,String.valueOf(numLote.longValue()), he);
        	//throw new RuntimeException("Problema al obtener la entidad de control lechero con identificador: "+idEclo.toString(), he);
    	}*/
        
        //StandaloneHibernateStrategy.getInstance().commitCurrentSession(); lo saque y lo puse mas adelante
        log.debug("Procesamiento terminado, commit.");
        log.debug("generacion de archivos de salida");
        FileInputStream inputStream = null;
        try {
            // Creamos el directorio destino de todos los archivos
          
            
            
            // Copiamos en el directorio anterior el archivo que nos paso el
            // usuario
            // FORMATO: <idProceso>_lote.xml
            File salida = new File(PROC_PROCESO_DIR + "Proc"
                    + loteResultado.getIDLoteSicel() + "_lote.xml");
            FileOutputStream out = new FileOutputStream(salida);
            inputStream = new FileInputStream(f);
            String entrada = IOUtils.toString(inputStream);
            out.write(entrada.getBytes());
            out.flush();
            out.close();
            // Copiamos en el directorio anterior el archivo resultado del
            // procesamiento,
            // FORMATO: <idProceso>_resultado.xml
            File salida2 = new File(PROC_PROCESO_DIR + "Proc"
                    + loteResultado.getIDLoteSicel() + "_resultado.xml");
            // FileWriter out2 = new FileWriter(salida2);
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(salida2), "ISO-8859-1"));
            // loteResultado.marshal(out2); no permite setear el encoding
            org.exolab.castor.xml.Marshaller marshaller = new org.exolab.castor.xml.Marshaller(
                    out2);
            marshaller.setEncoding("ISO-8859-1");
            marshaller.setMarshalExtendedType(false);
            marshaller.setSupressXMLDeclaration(false);
            marshaller.marshal(loteResultado);
            out2.flush();
            out2.close();
            log.debug("_resultado.xml terminado");
        } catch (MarshalException e1) {
            log.error(e1);
            throw new ProcessException(proc,ProcessException.MENSAJE_FORMATO_INVALIDO, String.valueOf(numLote.longValue()),e1);
            //throw new RuntimeException("Problema con la construcción de los archivos XML con la respuesta del procesamiento.", e1);
        } catch (ValidationException e1) {
            log.error(e1);
            throw new ProcessException(proc,ProcessException.MENSAJE_ERROR_VALIDACION, String.valueOf(numLote.longValue()),e1);
            //throw new RuntimeException("Problema con la construcción de los archivos XML con la respuesta del procesamiento.", e1);
        } catch (FileNotFoundException e1) {
            log.error(e1);
            throw new ProcessException(proc,ProcessException.MENSAJE_ARCHIVO_NO_ENCONTRADO, String.valueOf(numLote.longValue()), e1);
            //throw new RuntimeException("Problema con la construcción de los archivos XML con la respuesta del procesamiento.", e1);
        } catch (IOException e1) {
            log.error(e1);
            throw new ProcessException(proc,ProcessException.MENSAJE_PROBLEMA_IO, String.valueOf(numLote.longValue()), e1);
            //throw new RuntimeException("Problema con la construcción de los archivos XML con la respuesta del procesamiento.", e1);
        }
        finally {
        	if (inputStream != null) {
        		try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e);
					throw new ProcessException(proc,ProcessException.MENSAJE_CERRAR_ARCHIVO, String.valueOf(numLote.longValue()), e);
					//throw new RuntimeException("Problemas mientras se estaba cerrando el stream.", e);
				}
        	}
        }
    }

    private void execFOP(String xsltFileName, String posfijoPdfFileName)
            throws TransformerException, FileNotFoundException {
        Driver driver = new Driver();
        org.apache.avalon.framework.logger.Logger logger = new org.apache.avalon.framework.logger.ConsoleLogger(
                org.apache.avalon.framework.logger.ConsoleLogger.LEVEL_DISABLED);
        MessageHandler.setScreenLogger(logger);
        driver.setLogger(logger);
        driver.setRenderer(Driver.RENDER_PDF);

        // Setup Transformer
        File xslFile = new File(xsltFileName);
        Source xsltSrc = new StreamSource(xslFile);
        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
        TransformerFactory transformerFactory =  org.apache.xalan.xsltc.trax.TransformerFactoryImpl.newInstance();
        
        Transformer transformer = transformerFactory.newTransformer(xsltSrc);
        org.apache.fop.configuration.Configuration.put("baseDir",
                PROC_PROCESO_DIR);

        // Setup XML input
        File xmlFile = new File(PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_resultado.xml");
        Source src = new StreamSource(xmlFile);

        // Setup the OutputStream for FOP
        File outFile = new File(PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_" + posfijoPdfFileName
                + ".pdf");
        FileOutputStream fos = new java.io.FileOutputStream(outFile);
        
        try{
        
	        driver.setOutputStream(fos);
	
	        // Make sure the XSL transformation's result is piped through to FOP
	        Result res = new SAXResult(driver.getContentHandler());
	        
	        // Start the transformation and rendering process
	        transformer.transform(src, res);
        }
        finally{
        	try {
				fos.close();
			} catch (IOException e) {
				throw new RuntimeException("Imposible cerrar el archivo: " + outFile.getAbsolutePath());
			}
        }
        
    }

   
   
   private BufferedInputStream makeEntry(BufferedInputStream origin,
            ZipOutputStream out, String sourceFileName, String entryName) throws IOException {
        // Create a byte[] buffer that we will read data from the source
        // files into and then transfer it to the zip file
        int BUFFER_SIZE = Short.MAX_VALUE;
        byte[] data = new byte[BUFFER_SIZE];

        FileInputStream fi;
		
        try{
        	fi = new FileInputStream(sourceFileName);
        }catch(FileNotFoundException fe){
        	return null;
        }
		
        
        origin = new BufferedInputStream(fi, BUFFER_SIZE);
        
        // Setup the entry in the zip file
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);

        // Read data from the source file and write it out to the zip file
        int count;
        while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
            out.write(data, 0, count);
        }
        fi.close();
        return origin;
    }

	// private class SAuthenticator extends Authenticator
	// {
	// public PasswordAuthentication getPasswordAuthentication() {
	// return new PasswordAuthentication("adrianallende", "turn873data880");
	// }
	// }
    
    /**
     * 
     * @param proc
     * @throws ConfigurationException 
     * @throws IOException - problemas con ENTRADA/SALIDA
     */
    private synchronized void makeAndSaveZip(ProcProces proc) throws ProcessException {
        // Reference to the file we will be adding to the zipfile
        BufferedInputStream origin = null;
        BufferedInputStream originAux = null;
        // Reference to our zip file
        try{
        FileOutputStream dest = new FileOutputStream(PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_all.zip");

        // Wrap our destination zipfile with a ZipOutputStream
        ZipOutputStream out = new ZipOutputStream(
                new BufferedOutputStream(dest));
        
        String sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_anims_proc.xml";
        String entryName = "Proc" + loteResultado.getIDLoteSicel()
                + "_anims_proc.xml";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
        if(originAux!=null)
        	origin = originAux;
        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_lote.xml";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_lote.xml";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
        if(originAux!=null)
        	origin = originAux;
        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_resultado.xml";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_resultado.xml";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
        if(originAux!=null)
        	origin = originAux;
        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_resumen.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_resumen.pdf";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
        if(originAux!=null)
        	origin = originAux;
        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_mensajes.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_mensajes.pdf";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
        if(originAux!=null)
        	origin = originAux;
        sourceFileName = PROC_PROCESO_DIR + "Proc"
        		+ loteResultado.getIDLoteSicel() + "_resumen_proc.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_resumen_proc.pdf";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
        if(originAux!=null)
        	origin = originAux;
        sourceFileName = PROC_PROCESO_DIR + "Proc"
		+ loteResultado.getIDLoteSicel() + "_anims_proc.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_anims_proc.pdf";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
        if(originAux!=null)
        	origin = originAux;        
        sourceFileName = PROC_PROCESO_DIR + "Proc"
		+ loteResultado.getIDLoteSicel() + "_lactancias_oficiales.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_lactancias_oficiales.pdf";
        originAux = makeEntry(origin, out, sourceFileName, entryName);
		if(originAux!=null)
        	origin = originAux;
        sourceFileName = PROC_PROCESO_DIR + "Proc"
        		+ loteResultado.getIDLoteSicel() + "_lactancias_oficiales.xml";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_lactancias_oficiales.xml";
        
        if ((new File(sourceFileName)).exists()) {
        	originAux = makeEntry(origin, out, sourceFileName, entryName);
        	 if(originAux!=null)
             	origin = originAux;
        }    
        
        origin.close();
        originAux.close();
	    out.close();
	    dest.close();
	    String s = PROC_PROCESO_DIR + "Proc"+ loteResultado.getIDLoteSicel() + "_all.zip";
	    //this.saveSalida(s);
	    FileInputStream fu = new FileInputStream(s);
		   Blob b = 	Hibernate.createBlob(IOUtils.toByteArray(fu));
		   if(!proc.getEstado().equals(ConstantsUpload.STANDBY))
			   proc.setEstado(ConstantsUpload.PROCESSED);
		   proc.setFechaSalida(new Date());
		   proc.setContenidoSalida(b);
		   ProcProcesDAO.updateProcProces(proc);
		   fu.close();
	   }catch(FileNotFoundException fe){
		   throw new ProcessException(proc,ProcessException.MENSAJE_ARCHIVO_NO_ENCONTRADO,
				   String.valueOf(proc.getProcLote().getNumLote().longValue()),fe);
	   }catch(IOException ioe){
		   throw new ProcessException(proc,ProcessException.MENSAJE_PROBLEMA_IO,
				   String.valueOf(proc.getProcLote().getNumLote().longValue()),ioe);
	   }/*catch(HibernateException he){
		   throw new ProcessException(proc,ProcessException.MENSAJE_PROBLEMA_BASE_DATOS,
				   String.valueOf(proc.getProcLote().getNumLote().longValue()),he);
	   }*/
	   
	  /* try {
		String PROC_DIRs = Sicel3Conf.getConf().getConfProcesamiento().getChild("destinoDirEntrada").getValue();
		PROC_DIRs= getContextPath(PROC_DIRs);  
		File rootE = new File(PROC_DIRs);
		    File[] files = rootE.listFiles(); 
		    for(int i =0;i<files.length;i++){
		    	files[i].delete();
		   }
		    rootE.delete();
		    log.warn("Borro las entradas");
	} catch (ConfigurationException e) {
		log.warn("Problema al borrar las entradas");
		e.printStackTrace();
	}*/
	   
	   
	   
	    File root = new File(PROC_PROCESO_DIR);
	    File[] files = root.listFiles(); 
	    for(int i =0;i<files.length;i++){
	    	if(files[i].delete())
	    		log.warn("BORRO");
	    	else
	    		log.warn(" no BORRO");
	    }
	    root.delete();
		//return (s);
	    
	        
    }
    
   private void saveSalida(String path) throws FileNotFoundException,IOException {
	   //try{
		   FileInputStream fu = new FileInputStream(path);
		   Blob b = 	Hibernate.createBlob(IOUtils.toByteArray(fu));
		   if(!proc.getEstado().equals(ConstantsUpload.STANDBY))
			   proc.setEstado(ConstantsUpload.PROCESSED);
		   proc.setFechaSalida(new Date());
		   proc.setContenidoSalida(b);
		   ProcProcesDAO.updateProcProces(proc);
		   fu.close();
	   /*}catch(FileNotFoundException fe){
		   throw new ProcessException(ProcessException.MENSAJE_ARCHIVO_NO_ENCONTRADO,
				   String.valueOf(proc.getProcLote().getNumLote().longValue()),fe);
	   }catch(IOException ioe){
		   throw new ProcessException(ProcessException.MENSAJE_PROBLEMA_IO,
				   String.valueOf(proc.getProcLote().getNumLote().longValue()),ioe);
	   }*/
       
	}
   
   /**
    * Se envia un mail por el mal procesamiento de un xml
    * @param usuario - se le envia mail
    * @param userAdmin - se le envia mail
    * @param lote - se saca la informacion necesaria para generar el mail
    * @param mensaje - mensaje del error a informar
    */
   private static void sendMailErrorProcesamiento(Usuario userAdmin, ProcProces proceso, String problema, String lote){
	   try{  
		   ResourceBundle res = ResourceBundle.getBundle("Config");
		   Email mail = new Email();
		   mail.setHTMLMode(true);
		   mail.addTo(proceso.getUsuario().getEmail());
		   mail.addTo(userAdmin.getEmail());		   
		   mail.setSubject(res.getString("error.procesamiento.subject"));
		   String mensaje = res.getString("error.procesamiento.problema")+problema;
		   mensaje+=res.getString("error.procesamiento.fechaEnvio")+proceso.getFechaEntrada();
		   mensaje+=res.getString("error.procesamiento.usuario")+proceso.getUsuario().getApellido()+", "+proceso.getUsuario().getNombre();
		   if (lote!= null)
			   mensaje+=res.getString("error.procesamiento.numeroLote") + lote;
		   if(proceso.getNombreArchivoEntrada()!=null)
			   mensaje+=res.getString("error.procesamiento.nombreArchivoProcesado")+proceso.getNombreArchivoEntrada();
		   mail.setHtmlBody(mensaje);
		   
		   EmailSender.sendEmail(mail);
	   }catch(Exception e){
		   log.debug("Problemas con el envio del mail, el mail no fue enviado");
		   
	   }	   
   }
   
	/**
	 * Metodo encargado de descomprimir el archivo que se encuentra referenciado por
	 * el parametro fileName
	 * @param fileName path del archivo a descomprimir
	 * @param path path del archivo xml que tiene que ser procesado
	 * @return
	 */
	private String unZip(String fileName,String path){
		
		BufferedOutputStream dest = null;
       FileInputStream fis;
       String pathReturn="";
       try {
			fis = new 
			FileInputStream(fileName);
		
       ZipInputStream zis = new 
	   ZipInputStream(new BufferedInputStream(fis));
       ZipEntry entry;
     
       if((entry = zis.getNextEntry()) != null) {
       	   System.out.println("Extracting: " +entry);
      	 	   int count;
	           int BUFFER = (int)entry.getSize();
	           byte data[] = new byte[BUFFER];
	           pathReturn = path +entry.getName();
	           // Escribir los archivos en el disco
	           FileOutputStream fos = new FileOutputStream(pathReturn);//entry.getName());
	           dest = new BufferedOutputStream(fos, BUFFER);
	           while ((count = zis.read(data, 0, BUFFER)) != -1) 
	        	   	dest.write(data, 0, count);
	           dest.flush();
	           fos.close();
          }
       dest.close();
       zis.close();
       return pathReturn;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return pathReturn;
	
	}	
}
