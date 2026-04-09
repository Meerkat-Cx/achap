/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.ArchivoExcel;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;

/**
 * @author jdIvars
 *
 */
public class DescargaDocumentacionAction extends DispatchAction {

	
	public ActionForward listarDocumentos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		Logger.getLogger(DescargaDocumentacionAction.class).warn("el usuario "+usuarioActual.getUsername() +"ha accedido a descargar documentacion");
		//animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
		/*
		 * <dirReporteDocAdmin>reports/doc/administrador/</dirReporteDocAdmin>
         <dirReporteDocEclo>reports/doc/eclo/</dirReporteDocEclo>
         <dirReporteDocProp>reports/doc/propietario/</dirReporteDocProp>
         <dirReporteDocGral>reports/doc/general/</dirReporteDocGral>
         <dirReporteDocReg>reports/doc/regional/</dirReporteDocReg>
         <dirReporteDocData>reports/doc/data/</dirReporteDocData>
         <dirReporteDocTecnica>reports/doc/tecnica/</dirReporteDocTecnica>*/
		String REPORTES_DIR_DOC = "";
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocAdmin").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocTecnica").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLGENERAL))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocGral").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROPIETARIO))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocProp").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocEclo").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocReg").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLDATAENTRY))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocData").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocExterno").getValue();
		 File root = new File(this.getContextPath(REPORTES_DIR_DOC));
		    File[] files = root.listFiles(); 
		    List archivos = new ArrayList();
		    for(int i =0;i<files.length;i++){
		    	ArchivoExcel ar = new ArchivoExcel();
		    	if(files[i].getName().contains(".rar")){
			    	ar.setName(files[i].getName());
			    	ar.setModificacion(String.format("%tF %<tT", new Date(files[i].lastModified())));
			    	archivos.add(ar);
		    	}
		   }
		 request.setAttribute("archivos",archivos);
		return mapping.findForward("listadoDOC");

	}
	
	
	public ActionForward descargarArchivo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String nombre = request.getParameter("name");	
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		String REPORTES_DIR_DOC = "";
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocAdmin").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocTecnica").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLGENERAL))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocGral").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROPIETARIO))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocProp").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocEclo").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocReg").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLDATAENTRY))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocData").getValue();
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLEXTERNO))
			REPORTES_DIR_DOC = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteDocExterno").getValue(); 
		File root = new File(this.getContextPath(REPORTES_DIR_DOC));
		    File[] files = root.listFiles(); 
		    File arch = null;
		    for(int i =0;i<files.length;i++){
		    	if(files[i].getName().equals(nombre)){
		    		Logger.getLogger(DescargaDocumentacionAction.class).warn("el usuario "+usuarioActual.getUsername() +"se está descargando el archivo "+nombre);
		    		arch = files[i];
		    		break;
		    	}
		    }
		    response.setContentType("application/txt");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(nombre));
		    InputStream is = new FileInputStream(arch);
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
