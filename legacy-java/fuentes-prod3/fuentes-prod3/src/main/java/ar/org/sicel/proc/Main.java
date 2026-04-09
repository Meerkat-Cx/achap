/*
 * Creado el 01/07/2005
 */
package ar.org.sicel.proc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.mail.MessagingException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.Driver;
import org.apache.fop.messaging.MessageHandler;
import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.persistence.util.jasperReport.Report;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;
import ar.org.sicel.proc.services.locator.ServiceLocator;
import ar.org.sicel.proc.v1.anmodif.Anims;
import ar.org.sicel.proc.v1.anmodif.AnimsModificados;
import ar.org.sicel.proc.v1.lote.Lote;
import ar.org.sicel.util.Sicel3Conf;

/**
 * 
Procesamiento
=============

Los SCL entregan archivos XML con un lote de eventos para procesar.

[Versionado del XSD] El XMl conforma (o valida contra) el XSD de acha de una version. La versión está incluída en el nombre de archivo XSD, como en acha-eventos-v1.0.xsd. [referencia al XSD en el XML] El XSD debe estar en el encabezado del XML como referencia a un archivo local en el directorio padre donde se alamcenan los XML.
<lote xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="../acha-eventos-v1.0.xsd">
[Encoding del XML] La codificación (para permitir caracteres del castellano) será la ISO-8859-1
<?xml version="1.0" encoding="ISO-8859-1"?>

Esquema del procesamiento
-------------------------

Se copia el archivo de entrada en un directorio <id de proceso-informante-lote> de un repositorio fijo, indicado en [Sicel.conf<procesamiento><destinoDir>]
con el nombre de archivo <id de proceso>-lote.xml.
Se procesa obteniendo
<id de proceso>_resultado.xml 
<id de proceso>_resumen.pdf calculado por XSLT FO sobre el _resultado : resumen de cantidades de eventos aceptados y rechazados
<id de proceso>_mensajes.pdf calculado por XSLT FO sobre el _resultado : todos los mensajes producto del procesamiento del evento
<id de proceso>_anims_proc.xml animales afectados por el proceso
<id de proceso>_anims_proc.pdf por jasper sobre el _anims_proc.xml mas consultas a la BD : fichas de los animales afectados

 * 
 * 
 * @author ala
 */
public class Main {

    static Logger log = Logger.getLogger(Main.class);

    static String MENSAJES_XSLT = null;

    static String RESUMEN_XSLT = null;

    static String PROC_DIR = null;
    
    static int BUFFER_SIZE = 999999;
    
    String PROC_PROCESO_DIR = null;

    Lote loteResultado = null;

    AnimsModificados animsModificados = null;

    public Main() {
        try {
            PROC_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild(
                    "destinoDir").getValue();
            MENSAJES_XSLT = Sicel3Conf.getConf().getConfProcesamiento()
                    .getChild("xsltDir").getValue()
                    + "mensajes.xsl";
            RESUMEN_XSLT = Sicel3Conf.getConf().getConfProcesamiento()
                    .getChild("xsltDir").getValue()
                    + "resumen.xsl";
        } catch (ConfigurationException e) {
            log.error(e);
        }
    }

    public static void main(String[] args) {
    	Main main = new Main();
        main.doEjecutar(args[0]);
    }

	public void doEjecutar(String fileName) {
		log.info("Inicio de procesador para " + fileName);
        // Del archivo de entrada, ejecutamos el procso del mismo.
        execPROC(fileName);
        
      //   sacado para que no haga xslt
        try {
            // Del resultado del proceso, creamos los pdf dado los xslt.
            makePdfXslt();
        } catch (TransformerException e) {
            log.error(e);
        } catch (FileNotFoundException e) {
            log.error(e);
        }
        
        
        try {         
        	//Animales modificados a partir del resultado del procesamiento.
        	/*AnimsModificados animsModificados = */makeXMLAnimalesModificados();
            AnimsModificados lactanciasOficiales = makeXMLLactanciasOficiales();
            // Creamos las fichas de los animales que fueron modificados
            
            //MODIF no se devuelven mas las fichas de los animales por default 
//            makeFichasAnimalesModificados(animsModificados);
            
            makeCertificadosLactancia(lactanciasOficiales);
        } catch (ValidationException e) {
            log.error(e);
        } catch (MarshalException e) {
            log.error(e);
        } catch (FileNotFoundException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        } catch (HibernateException e) {
            log.error(e);
        }
      
//        try {
//            makeZip();
//        } catch (IOException e) {
//            log.error(e);
//        }
        
//        
//        try {
//             unMakeZip();
//        } catch (IOException e) {
//            log.error(e);
//        }
        
        
        // TODO Enviar por mail el zip.
        // El de entrada no, la fichas tampoco. El mail sacarlo de resultado de
        // la ECLO.

        try {
            sendEmail("", "", "", String.valueOf(loteResultado.getIDLoteSicel()));
        } catch (MessagingException e1) {
            log.error(e1);
        }
        
        // TODO Hacer un servdor que este controlando un dir y cuando le tiras
        // un archivo lo procese.
        
        
        log.info("Fin de procesador para " + fileName);
	}
    
    
    public AnimsModificados makeXMLLactanciasOficiales()     throws ValidationException, MarshalException, IOException  {
        Lote lote = loteResultado;
        AnimsModificados animsModificados = new AnimsModificados();
        animsModificados.setInformante(lote.getInformante());
        animsModificados.setIDLoteInte(lote.getIDLoteInte());
        animsModificados.setIDLoteSicel(lote.getIDLoteSicel());
//        Long idProceso = lote.getIDLoteSicel();

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
        //FileOutputStream out = new FileOutputStream(salida);

        //StringWriter sw = new StringWriter();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(salida), "ISO-8859-1"));
        // loteResultado.marshal(out2); no permite setear el encoding
        org.exolab.castor.xml.Marshaller marshaller = new org.exolab.castor.xml.Marshaller(
                out);
        marshaller.setEncoding("ISO-8859-1");
        marshaller.setSupressXMLDeclaration(false);
        marshaller.marshal(animsModificados);
        
        
        
        //animsModificados.marshal(sw);
        //out.write(sw.toString().getBytes());
        out.flush();
        out.close();
        return animsModificados;
    }

    public AnimsModificados makeXMLAnimalesModificados()
            throws ValidationException, MarshalException, IOException,
            HibernateException {
        StandaloneHibernateStrategy.getInstance().openNewSession();
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
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        // Copiamos en el directorio del procesamiento el xml según lo
        // creado en el método anterior.
        // FORMATO: <idProceso>_anims_proc.xml
        File salida = new File(PROC_PROCESO_DIR + "Proc"
                + lote.getIDLoteSicel() + "_anims_proc.xml");
        //FileOutputStream out = new FileOutputStream(salida);

        //StringWriter sw = new StringWriter();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(salida), "ISO-8859-1"));
        // loteResultado.marshal(out2); no permite setear el encoding
        org.exolab.castor.xml.Marshaller marshaller = new org.exolab.castor.xml.Marshaller(
                out);
        marshaller.setEncoding("ISO-8859-1");
        marshaller.setSupressXMLDeclaration(false);
        marshaller.marshal(animsModificados);
        
        
        
        //animsModificados.marshal(sw);
        //out.write(sw.toString().getBytes());
        out.flush();
        out.close();

        return animsModificados;
    }

//MODIF No se lanza este reporte desde aca nunca mas
//    private void makeFichasAnimalesModificados(AnimsModificados animsModificados)
//            throws MarshalException, ValidationException, IOException {
//    	// Desactivado por ahora
//    	
//    	
//        Report report = new Report();
//        if (animsModificados.getAnims() != null) {
//            // Creamos las fichas de los animales que fueron modificados.
//            byte[] bytes = report.makeReport(animsModificados.getAnims().getAnimal());
//            // Copiamos en el directorio del procesamiento el pdf de las fichas.
//            // FORMATO: <idProceso>_anims_proc.pdf
//            File pdf = new File(PROC_PROCESO_DIR + "Proc"
//                    + loteResultado.getIDLoteSicel() + "_anims_proc.pdf");
//            FileOutputStream outPdf = new FileOutputStream(pdf);
//            outPdf.write(bytes);
//            outPdf.flush();
//            outPdf.close();
//        }         
//    }
    
    
    private void makeCertificadosLactancia(AnimsModificados animsModificados)
    throws MarshalException, ValidationException, IOException {
    	// Desactivado por ahora


    	Report report = new Report();
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
    	try {
    	Report report = new Report();
        byte[] bytes = report.makeReportResumen(resumen,eclo,numLote);
        File pdf = new File(PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_resumen_proc.pdf");
        FileOutputStream outPdf = new FileOutputStream(pdf);
        outPdf.write(bytes);
        outPdf.flush();
        outPdf.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    private void makePdfXslt() throws FileNotFoundException,
            TransformerException {
        String posfijoPdfFileName = "resumen";
        execFOP(RESUMEN_XSLT, posfijoPdfFileName);
        posfijoPdfFileName = "mensajes";
        execFOP(MENSAJES_XSLT, posfijoPdfFileName);
    }

    private void execPROC(String xmlFileName) {
        Lote lote = null;
        log.debug("Iniciando el unmarshaling de " + xmlFileName);
        File f = null;
        try {
            f = new File(xmlFileName);
            lote = (Lote) Lote.unmarshal(new FileReader(f));
            log.debug("Unmarshaling terminado.");
        } catch (MarshalException e) {
            log.error(e);
            throw new RuntimeException("Error procesando el archivo. Error en el formato de xml.", e);
        } catch (ValidationException e) {
            log.error(e);
            throw new RuntimeException("Error procesando el archivo. Error de validación.", e);
        } catch (FileNotFoundException e) {
            log.error(e);
            throw new RuntimeException("No se encontro el archivo", e);
        }

        log.debug("Iniciando el procesamiento propiamente.");
//        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
        ServiceLocator.getProcesadorXML();
        
        StandaloneHibernateStrategy.getInstance().openNewSession();
        //AnimsModificados animsModificados = new AnimsModificados();
        new AnimsModificados();
        // TODO Completar el id de proceso con ceros!!!!
        
        Collection resumen = new LinkedList();
  //      loteResultado = proc.procesar(lote, animsModificados,resumen);
        
        File destDir = new File(PROC_DIR + "Proc"
                + loteResultado.getIDLoteSicel());
        PROC_PROCESO_DIR = destDir.getPath() + "/";
        destDir.mkdir();
        try {
        	Long idEclo = lote.getInformante();
        	Long numLote = lote.getIDLoteInte();
        	Eclo eclo = EcloDAO.findByPrimaryKey(idEclo);
            makeResumenProcesoJasper(resumen,eclo,numLote);
    	    } catch (Exception e) {
    	    	e.printStackTrace();
    	    }
        
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        log.debug("Procesamiento terminado, commit.");
        log.debug("generacion de archivos de salida");
        try {
            // Creamos el directorio destino de todos los archivos
          
            
            
            // Copiamos en el directorio anterior el archivo que nos paso el
            // usuario
            // FORMATO: <idProceso>_lote.xml
            File salida = new File(PROC_PROCESO_DIR + "Proc"
                    + loteResultado.getIDLoteSicel() + "_lote.xml");
            FileOutputStream out = new FileOutputStream(salida);
            String entrada = IOUtils.toString(new FileInputStream(f));
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
            marshaller.setSupressXMLDeclaration(false);
            marshaller.marshal(loteResultado);
            out2.flush();
            out2.close();
            log.debug("_resultado.xml terminado");
        } catch (MarshalException e1) {
            log.error(e1);
        } catch (ValidationException e1) {
            log.error(e1);
        } catch (FileNotFoundException e1) {
            log.error(e1);
        } catch (IOException e1) {
            log.error(e1);
        }
    }

    private void execFOP(String xsltFileName, String posfijoPdfFileName)
            throws TransformerException, FileNotFoundException {
        Driver driver = new Driver();
        org.apache.avalon.framework.logger.Logger logger = new org.apache.avalon.framework.logger.ConsoleLogger(
                org.apache.avalon.framework.logger.ConsoleLogger.LEVEL_INFO);
        MessageHandler.setScreenLogger(logger);
        driver.setLogger(logger);
        driver.setRenderer(Driver.RENDER_PDF);

        // Setup Transformer
        File xslFile = new File(xsltFileName);
        Source xsltSrc = new StreamSource(xslFile);
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
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

    /*
    private void makeZip() throws IOException {
        // Reference to the file we will be adding to the zipfile
        BufferedInputStream origin = null;

        // Reference to our zip file
        FileOutputStream dest = new FileOutputStream(PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_all.zip");

        // Wrap our destination zipfile with a ZipOutputStream
        ZipOutputStream out = new ZipOutputStream(
                new BufferedOutputStream(dest));

        String sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_anims_proc.xml";
        String entryName = "Proc" + loteResultado.getIDLoteSicel()
                + "_anims_proc.xml";
        origin = makeEntry(origin, out, sourceFileName, entryName);

        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_lote.xml";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_lote.xml";
        origin = makeEntry(origin, out, sourceFileName, entryName);

        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_resultado.xml";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_resultado.xml";
        origin = makeEntry(origin, out, sourceFileName, entryName);

        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_resumen.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_resumen.pdf";
        origin = makeEntry(origin, out, sourceFileName, entryName);

        sourceFileName = PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_mensajes.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_mensajes.pdf";
        origin = makeEntry(origin, out, sourceFileName, entryName);

        sourceFileName = PROC_PROCESO_DIR + "Proc"
        		+ loteResultado.getIDLoteSicel() + "_resumen_proc.pdf";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_resumen_proc.pdf";
        origin = makeEntry(origin, out, sourceFileName, entryName);

        sourceFileName = PROC_PROCESO_DIR + "Proc"
        		+ loteResultado.getIDLoteSicel() + "_lactancias_oficiales.xml";
        entryName = "Proc" + loteResultado.getIDLoteSicel() + "_lactancias_oficiales.xml";
        
        if ((new File(sourceFileName)).exists()) {
            origin = makeEntry(origin, out, sourceFileName, entryName);
        }

        // Close the source file
        origin.close();

        // Close the zip file
        out.close();

    }

    private BufferedInputStream makeEntry(BufferedInputStream origin,
            ZipOutputStream out, String sourceFileName, String entryName)
            throws IOException {
        // Create a byte[] buffer that we will read data from the source
        // files into and then transfer it to the zip file
        int BUFFER_SIZE = Short.MAX_VALUE;
        byte[] data = new byte[BUFFER_SIZE];

        FileInputStream fi = new FileInputStream(sourceFileName);
        origin = new BufferedInputStream(fi, BUFFER_SIZE);

        // Setup the entry in the zip file
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);

        // Read data from the source file and write it out to the zip file
        int count;
        while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
            out.write(data, 0, count);
        }
        return origin;
    }
    */

//    private class SAuthenticator extends Authenticator 
//    {
//      public PasswordAuthentication getPasswordAuthentication() {
//        return new PasswordAuthentication("adrianallende", "turn873data880");
//      }
//    }
    
    
    
    private void sendEmail(String from, String to, String subject, String body)
            throws MessagingException {
    	log.info("Enviando mail...");
    	/*
        String userx = "asociacionholandoargentino";        
        String hostx = "smtp.mail.yahoo.com.ar";
        String fromx = "asociacionholandoargentino@yahoo.com.ar";
        String tox = "0229315520090@personal-net.com.ar";
        // Get system properties
        Properties props = System.getProperties();
        // Setup mail server
        props.put("mail.smtp.user", userx);
        props.put("mail.smtp.host", hostx);
        props.put("mail.smtp.auth", "true");
        // Get session
        Authenticator auth = new SAuthenticator();
        Session session = Session.getDefaultInstance(props,auth);
        // Define message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromx));
        message
                .addRecipient(Message.RecipientType.TO,
                        new InternetAddress(tox));
        message.setHeader("X-Mailer", "JavaMailer");
        message.setSentDate(new Date());
        message.setSubject("Proceso");
        message.setText("Proceso "+ body+" terminado.");
        // Send message
        Transport.send(message);
        log.info("Mail enviado.");
        */
    	log.info("Envio de mail desactivado");
    	
    }
  /*  private void unMakeZip() throws IOException {  	
	//  Create a ZipInputStream to read the zip file
	    BufferedOutputStream dest = null;
	    FileInputStream fis = new FileInputStream(PROC_PROCESO_DIR + "Proc"
                + loteResultado.getIDLoteSicel() + "_all.zip");
 
    //  Carpeta de salida    
	    File destDir = new File((PROC_DIR+"Proc"+loteResultado.getIDLoteSicel()+"/unZip"));
	    destDir.mkdir();
	    
	    ZipInputStream zis = new ZipInputStream( new BufferedInputStream( fis ) );
	//     Loop over all of the entries in the zip file
	    int count;
	    byte data[] = new byte[ BUFFER_SIZE ];
	    ZipEntry entry;
	    while( ( entry = zis.getNextEntry() ) != null )
	    {
	    if( !entry.isDirectory() )
	    {
	    String destFN = destDir + File.separator + entry.getName();
	//     Write the file to the file system
	    FileOutputStream fos = new FileOutputStream( destFN );
	    dest = new BufferedOutputStream( fos, BUFFER_SIZE );
	    while( (count = zis.read( data, 0, BUFFER_SIZE ) ) != -1 )
	    {
	    dest.write( data, 0, count );
	    }
	    dest.flush();
	    dest.close();
	    }
	    }
	    zis.close();
    }*/
}
