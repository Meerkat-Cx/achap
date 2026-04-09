/**
 * 
 */
package ar.org.sicel.upload.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Hibernate;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.Sistema;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.upload.dao.ConstantsUpload;
import ar.org.sicel.upload.dao.DAOs;
import ar.org.sicel.upload.forms.UploadFileForm;
import ar.org.sicel.upload.model.Bajada;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;



/**
 * Clase que se encarga de la validar y guardar en base el archivo ZIP que ingreso el usuario
 * @author jdivars
 *
 */
public class UploadFileAction extends DispatchAction {
	
	static Logger log = Logger.getLogger(UploadFileAction.class);
	public ActionForward initCambiarLote(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		UploadFileForm form = (UploadFileForm)formm;
		String id = (String)request.getParameter("loteId");
		form.setIdP(Long.parseLong(id));
		ProcProces proc = ProcProcesDAO.findByPrimaryKey(form.getIdP());
		form.setNombre(proc.getNombreArchivoEntrada());
		form.setUsername(proc.getUsuario().getUsername());
	return mapping.findForward("cambiarLote");
		
	}
	public ActionForward cambiarLote(ActionMapping mapping, ActionForm formm,	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		UploadFileForm form = (UploadFileForm)formm;
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		ProcProces proc = ProcProcesDAO.create(user);
		//ProcProces proc = ProcProcesDAO.findByPrimaryKey(form.getIdP());
		//Usuario user = proc.getUsuario();
		int max = 512*512*2;
       //int max = 512*512;
		//log.warn("Eclo "+user.getContacto().getId()+" esta REcargando un lote");
		//log.warn("FileData.length->"+form.getFileUpload().getFileData().length);
		//log.warn("FileSize->"+form.getFileUpload().getFileSize());
		if((form.getFileUpload().getFileData().length)>=max){
			if(user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR)){
				Eclo eclo = EcloDAO.findByPrimaryKey(user.getContacto().getId());
				Iterator it = eclo.getSistemas().iterator();
				boolean syscord = false;
				while(it.hasNext()){
					Sistema s = (Sistema)it.next();
					if(s.getId().longValue()==1L)//si es syscord se hace la excepcion y se permite archivos de 1 mega
						syscord=true;
				}
				if(syscord){
					max = 1024*1024;
					if((form.getFileUpload().getFileData().length)>=max){
						request.setAttribute("error", "El tamaño del archivo supera el limite establecido de 1024 K.\n Por favor, cargue un archivo mas pequeño.");
						return mapping.findForward("cambiarLote");//llamaba a comunica.jsp
					}
				}
				else{
					request.setAttribute("error", "El tamaño del archivo supera el limite establecido de 512 K.\n Por favor, cargue un archivo mas pequeño.");
					return mapping.findForward("cambiarLote");//llamaba a comunica.jsp
				}
			}
			
		}
		Blob b = 	Hibernate.createBlob(form.getFileUpload().getFileData());   
		//ArrayList f = new ArrayList();
		//long [] iArray = new long[f.size()];
		proc.setContenidoEntrada(b);
		proc.setObservaciones(form.getComments());
		proc.setFechaEntrada(new Date());
		proc.setNombreArchivoEntrada(form.getFileUpload().getFileName());
		try{
			validate(proc);			
		} 
		catch (IllegalArgumentException e) {
			//request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("error", "El nombre del archivo posee caracteres ñ,&,%,$, etc.\n Por favor, cambielo y vuelva a intentarlo");
			
			return mapping.findForward("cambiarLote");//llamaba a comunica.jsp		
		}
		catch (FileNotFoundException e) {
			//request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("error", "Hubo un incoveniente con el archivo.\n Por favor, vuelva a intentarlo");
			return mapping.findForward("cambiarLote");//llamaba a comunica.jsp		
		}
		catch(ConfigurationException e){
			//request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("error", "Hubo un inconveniente en el procesamiento.\n Por favor, vuelva a intentarlo");
			return mapping.findForward("cambiarLote");//llamaba a comunica.jsp
		}
		catch(SQLException e){
			//request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("error", "Hubo un inconveniente en el procesamiento.\n Por favor, vuelva a intentarlo");
			return mapping.findForward("cambiarLote");//llamaba a comunica.jsp
		}
		catch(IOException e){
			//request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("error", "Hubo un inconveniente en el procesamiento.\n Por favor, vuelva a intentarlo");
			return mapping.findForward("cambiarLote");//llamaba a comunica.jsp
		}
		catch (SAXParseException e) {
			//request.getSession().setAttribute("CURRENTUSER",user);
			String text = "Falló la validación del xml.\n Por favor, verifique su archivo.\n";
			text+="Problema encontrado en la linea numero "+e.getLineNumber()+"\n";
			text+="en la columna numero "+e.getColumnNumber();
			request.setAttribute("error", text);
			return mapping.findForward("cambiarLote");//llamaba a comunica.jsp
		}
		catch (Exception e) {
			//request.getSession().setAttribute("CURRENTUSER",user);
			String text = "Falló la validación del xml.\n Por favor, verifique su archivo y la compresion del mismo.\n";
			request.setAttribute("error", text);
			return mapping.findForward("cambiarLote");//llamaba a comunica.jsp
		}
		ProcProces proc2 = ProcProcesDAO.findByPrimaryKey(form.getIdP());
		proc2.setContenidoEntrada(proc.getContenidoEntrada());
		proc2.setObservaciones(proc.getObservaciones());
		proc2.setFechaEntrada(new Date());
		proc2.setNombreArchivoEntrada(proc.getNombreArchivoEntrada());
		ProcProcesDAO.updateProcProces(proc2);
		form.setComments("");
		form.setNombre(proc2.getNombreArchivoEntrada());
		request.setAttribute("cerrar", "si");
		//return mapping.findForward("volverStandBy");
		return mapping.findForward("cambiarLote");
		
	}

	/**
	 * Método que tiene que funcionalidad tomar el archivo ZIP que usuario ingreso, 
	 * validar su tamaño, en caso de que este supere 1MB se redireccionara a una pantalla
	 * que indique este inveniente; caso contrario graba el archivo en la base y redirecciona
	 * a la pantalla "ArchivosCargados"
	 * El proceso que es almacenado en la base se carga con estado "uploaded"
	 */
	public ActionForward uploadZIP(ActionMapping mapping, ActionForm formm,	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		UploadFileForm form = (UploadFileForm)formm;
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);

		ProcProces proc = ProcProcesDAO.create(user);
		int max = 512*512*2;
       //int max = 512*512;
		log.warn("Eclo "+user.getContacto().getId()+" esta cargando un lote");
		//log.warn("FileData.length->"+form.getFileUpload().getFileData().length);
		//log.warn("FileSize->"+form.getFileUpload().getFileSize());
		if((form.getFileUpload().getFileData().length)>=max){
			if(user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR)){
				Eclo eclo = EcloDAO.findByPrimaryKey(user.getContacto().getId());
				Iterator it = eclo.getSistemas().iterator();
				boolean syscord = false;
				while(it.hasNext()){
					Sistema s = (Sistema)it.next();
					if(s.getId().longValue()==1L)//si es syscord se hace la excepcion y se permite archivos de 1 mega
						syscord=true;
				}
				if(syscord){
					max = 1024*1024;
					if((form.getFileUpload().getFileData().length)>=max){
						request.setAttribute("mensaje", "El tamaño del archivo supera el limite establecido de 1024 K.\n Por favor, cargue un archivo mas pequeño.");
						request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
						return mapping.findForward("message");//llamaba a comunica.jsp
					}
				}
				else{
					request.setAttribute("mensaje", "El tamaño del archivo supera el limite establecido de 512 K.\n Por favor, cargue un archivo mas pequeño.");
					request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
					return mapping.findForward("message");//llamaba a comunica.jsp
				}
			}
			
		}
		Blob b = 	Hibernate.createBlob(form.getFileUpload().getFileData());    		
		proc.setContenidoEntrada(b);
		proc.setObservaciones(form.getComments());
		proc.setFechaEntrada(new Date());
		proc.setNombreArchivoEntrada(form.getFileUpload().getFileName());
		try{
			validate(proc);			
		} 
		catch (IllegalArgumentException e) {
			request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("mensaje", "El nombre del archivo posee caracteres ñ,&,%,$, etc.\n Por favor, cambielo y vuelva a intentarlo");
			request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
			return mapping.findForward("message");//llamaba a comunica.jsp		
		}
		catch (FileNotFoundException e) {
			request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("mensaje", "Hubo un incoveniente con el archivo.\n Por favor, vuelva a intentarlo");
			request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
			return mapping.findForward("message");//llamaba a comunica.jsp		
		}
		catch(ConfigurationException e){
			request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("mensaje", "Hubo un inconveniente en el procesamiento.\n Por favor, vuelva a intentarlo");
			request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
			return mapping.findForward("message");//llamaba a comunica.jsp
		}
		catch(SQLException e){
			request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("mensaje", "Hubo un inconveniente en el procesamiento.\n Por favor, vuelva a intentarlo");
			request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
			return mapping.findForward("message");//llamaba a comunica.jsp
		}
		catch(IOException e){
			request.getSession().setAttribute("CURRENTUSER",user);
			request.setAttribute("mensaje", "Hubo un inconveniente en el procesamiento.\n Por favor, vuelva a intentarlo");
			request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
			return mapping.findForward("message");//llamaba a comunica.jsp
		}
		catch (SAXParseException e) {
			request.getSession().setAttribute("CURRENTUSER",user);
			String text = "Falló la validación del xml.\n Por favor, verifique su archivo.\n";
			text+="Problema encontrado en la linea numero "+e.getLineNumber()+"\n";
			text+="en la columna numero "+e.getColumnNumber();
			request.setAttribute("mensaje", text);
			request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
			return mapping.findForward("message");//llamaba a comunica.jsp
		}
		catch (Exception e) {
			request.getSession().setAttribute("CURRENTUSER",user);
			String text = "Falló la validación del xml.\n Por favor, verifique su archivo y la compresion del mismo.\n";
			request.setAttribute("mensaje", text);
			request.setAttribute("urlRetorno", "'uploadProcesoPage.do'");
			return mapping.findForward("message");//llamaba a comunica.jsp
		}
		
		
		//proc.setUsuario((Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER));
		proc.setEstado(ConstantsUpload.UPLOADED);
		ProcProcesDAO.save(proc);
		//	DAOs.getProcesoDAO().save(proc);
		//ent.setProceso(proc); 
		//	DAOs.getEntradaDAO().save(ent);
		
		//List process =DAOs.getProcesoDAO().getAllProcess((Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER));
		/*List process = ProcProcesDAO.getAllProcess((Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER));
			//ProcProcesDAO.getAllProcess((Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER));
		request.setAttribute("procesos",process);
		Bajada ba = DAOs.getBajadaDAO().obtenerBajadaMasReciente(proc);
		request.setAttribute("bajada",ba);*/
		form.setComments("");
		return mapping.findForward("loadedFiles");
		
	}
	
	private void validate(ProcProces proc) throws ConfigurationException, SQLException, IOException,
		/*MarshalException,ValidationException,*/FileNotFoundException, SAXException, IllegalArgumentException,Exception {
		
		String PROC_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("destinoDir").getValue();
		String aux = PROC_DIR + "entradas/" + "entrada_" + new Date().getTime();
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
		
		String xmlFileName=this.unZip(path,pathDir);
		String xmlXsdName = getContextPath("/xsd/acha_eventos_v1.5.6.4.xsd");
		//System.out.println(xmlXsdName);
		try {
			validate(xmlFileName, xmlXsdName);
		}
		catch (IOException e) {
			throw e;
		}		
		catch (SAXException e) {
			throw e;
		}
		finally {
			File file = new File(path);
			file.delete();			
		}
	}
	
	/**
	 * 
	 * @param xmlFileName
	 * @param xmlXsdName
	 * @throws SAXException
	 * @throws IOException
	 */
	private void validate(String xmlFileName, String xmlXsdName) throws SAXException, IOException {
		SchemaFactory factory =	SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		
		
		// 2. Compile the schema. 
		// Here the schema is loaded from a java.io.File, but you could use 
		// a java.net.URL or a javax.xml.transform.Source instead.
		File schemaLocation = new File(xmlXsdName);        		
		try {			
			Schema schema = factory.newSchema(schemaLocation);			
			// 3. Get a validator from the schema.
			long init = new Date().getTime();
			Validator validator = schema.newValidator();
			// hasta aqui solo una vez
			
			// 4. Parse the document you want to check.
			Source source = new StreamSource(xmlFileName);
			validator.setErrorHandler(new MyErrorHandler());
			// 5. Check the document	
			//validator.setResourceResolver(new LSResourceResolver());
			validator.validate(source);
			
			long end = new Date().getTime();
			log.debug(xmlFileName + " is valid. Tiempo de validacion : "+(end-init));
		}		
		catch (IOException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
			throw e;
			
		}
		catch (SAXException ex) {
			log.debug(xmlFileName + " is not valid because ");						
			log.debug(ex.getMessage());
			throw ex;
		}
		finally {
			// borro el archivo que se creo con el xml subido
			File f = new File(xmlFileName);
			f.delete();			
		}
		
	}
	
	class MyErrorHandler implements ErrorHandler {

		public void warning(SAXParseException exception) throws SAXException {
	        printInfo(exception);
	        throw exception;	        
		}

		public void error(SAXParseException exception) throws SAXException {
	        printInfo(exception);
	        throw exception;
	    }

		public void fatalError(SAXParseException exception) throws SAXException {
	        printInfo(exception);
	        throw exception;
	        //throw new SAXException(exception);			
		}
		
		private void printInfo(SAXParseException e) {
	     	 log.debug("   Public ID: "+e.getPublicId());
	     	 log.debug("   System ID: "+e.getSystemId());
	     	 log.debug("   Line number: "+e.getLineNumber());
	     	 log.debug("   Column number: "+e.getColumnNumber());
	     	 log.debug("   Message: "+e.getMessage());
	     }
		
	}

	/**
	 * Metodo encargado de descomprimir el archivo que se encuentra referenciado por
	 * el parametro fileName
	 * @param fileName path del archivo a descomprimir
	 * @param path path del archivo xml que tiene que ser procesado
	 * @return
	 */
	private String unZip(String fileName,String path) throws IllegalArgumentException, 
		FileNotFoundException, IOException {
		
		/*ZipInputStream zipinputstream = null;
		ZipEntry zipentry;
		String pathReturn = "";
		zipinputstream = new ZipInputStream(new FileInputStream
		(fileName));
		zipentry = zipinputstream.getNextEntry();
		while (zipentry != null) { //for each entry to be extracted
		    String entryName = zipentry.getName();
		    int n;
		    pathReturn = path + entryName;
		    FileOutputStream fileoutputstream = new FileOutputStream( pathReturn );
			int BUFFER = (int)zipentry.getSize();
			byte buf[] = new byte[BUFFER];
		    while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
		        fileoutputstream.write(buf, 0, n);
		    fileoutputstream.close(); 
		    zipinputstream.closeEntry();
		    zipentry = zipinputstream.getNextEntry();
		}//while
		zipinputstream.close();
		return pathReturn;*/
		
		BufferedOutputStream dest = null;
		FileInputStream fis;
		String pathReturn="";
		//try {
			fis = new 
			FileInputStream(fileName);
			
			ZipInputStream zis = new 
			ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			
			if((entry = zis.getNextEntry()) != null) {
				//System.out.println("Extracting: " +entry);
				log.warn("el nombre del archivo es: " +entry);
				int count;
				int BUFFER = (int)entry.getSize();
				byte data[] = new byte[BUFFER];
				pathReturn = path +entry.getName();
				FileOutputStream fos = new FileOutputStream(pathReturn);
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) 
					dest.write(data, 0, count);
				dest.flush();
				fos.close();
			}
			dest.close();
			zis.close();
			
			return pathReturn;
		/*} catch (Exception e) {
			
			e.printStackTrace();
		}*/
		
		
	}	
	
	private String getContextPath(String subPath){
		return getServlet().getServletContext().getRealPath(subPath);
	}
	public ActionForward eliminar(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		UploadFileForm form = (UploadFileForm)formm;
		Long id = form.getIdP();
		ProcProces proc = ProcProcesDAO.findByPrimaryKey(id);
		log.warn("El usuario de la eclo "+user.getContacto().getId()+" esta eliminando el archivo: "+proc.getNombreArchivoEntrada());
		ProcProcesDAO.deleteProcProces(proc);
		
		//List process = ProcProcesDAO.getAllProcess((Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER));
		//ProcProcesDAO.getAllProcess((Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER));
	//request.setAttribute("procesos",process);
	//Bajada ba = DAOs.getBajadaDAO().obtenerBajadaMasReciente(proc);
	//request.setAttribute("bajada",ba);
	//form.setComments("");
	return mapping.findForward("loadedFiles");
		//return mapping.findForward("success");
	}
}
