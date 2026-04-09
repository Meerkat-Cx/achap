package ar.org.sicel.web.altaEdicion.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.excel.CargaMasivaAccessor;
import ar.org.sicel.excel.excepciones.CargaMasivaException;
import ar.org.sicel.excel.excepciones.CellError;
import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Rol;
import ar.org.sicel.persistence.RolDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.UsuarioDAO;
import ar.org.sicel.security.AuthenticacionManager;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.CargaMasivaForm;
import ar.org.sicel.web.altaEdicion.forms.PedigreeForm;
import ar.org.sicel.web.altaEdicion.forms.UsuarioForm;
import ar.org.sicel.web.login.LoginAction;


public class CargaMasivaAction extends DispatchAction {
	
	public static String sheetNameCarga = "Hoja1";
	public static String sheetNameModificacion  = "Hoja1";

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){		
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		return mapping.findForward("init");
	}
	public ActionForward initCambiosRp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){		
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		Logger.getLogger(CargaMasivaAction.class).warn("Iniciando Cambio de RP por excel");
		return mapping.findForward("initCambioRP");
	}
	
	public ActionForward cargar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){		
		String directorio = this.getContextPath();
		CargaMasivaForm cForm = (CargaMasivaForm) form;
		CargaMasivaAccessor accessor = null;
		int cantidadOk = 0;
		boolean cambRP = false;
		if(StringUtils.isEmpty(cForm.getTipoActualizacion()))
			cambRP = true;
		Set<CellError> listaErrores = new TreeSet<CellError>();
		try {
			accessor = new CargaMasivaAccessor(directorio, cForm.getArchivoExcel().getFileData());
			boolean esHembra = (cForm.getSexo().equalsIgnoreCase("Hembra"))?true:false;
			
			if (cForm.getTipoActualizacion().equalsIgnoreCase("Altas"))
				cantidadOk = accessor.realizarAltas(cForm.getOrigen(), cForm.getRaza(), esHembra, listaErrores, CargaMasivaAction.sheetNameCarga);
			else if (cForm.getTipoActualizacion().equalsIgnoreCase("Modificaciones")) {
				if ("Nacional".equalsIgnoreCase(cForm.getOrigen()))
					// modificacion normal
					cantidadOk = accessor.realizarModificaciones(cForm.getOrigen(), cForm.getRaza(), esHembra, listaErrores, CargaMasivaAction.sheetNameModificacion,cForm.isCambioRaza(),cForm.isCambioSexo(),cForm.getRazaAnterior());
				if ("Internacional".equalsIgnoreCase(cForm.getOrigen()))
					cantidadOk = accessor.realizarModificacionesInternacionales(cForm.getRaza(), esHembra, listaErrores, CargaMasivaAction.sheetNameModificacion,cForm.isCambioRaza(),cForm.isCambioSexo(),cForm.getRazaAnterior());
				
			}
			else{
				Logger.getLogger(CargaMasivaAction.class).warn("realizando Cambio de RP por excel");
				cantidadOk = accessor.realizarModificacionesRP(cForm.getRaza(), esHembra, listaErrores, CargaMasivaAction.sheetNameModificacion);
				
			}
			if (!listaErrores.isEmpty()){
				request.setAttribute("errores", listaErrores);
			}
			request.setAttribute("resultado", "Se procesaron correctamente "+cantidadOk+" filas ");			
		} catch (CargaMasivaException e) {			
			request.setAttribute("resultado", e.getMessage());
		} catch (FileNotFoundException e) {
			request.setAttribute("resultado", "Problemas en el servidor");
		} catch (IOException e) {
			request.setAttribute("resultado", "Problemas en el servidor");		
		} 
		finally {
			if (accessor != null)
				try {
					accessor.close();
				} catch (CargaMasivaException e1) {
					log.debug("Problemas cuando se quiere eliminar el archivo");
				}
		}
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		//Envio de mail
		ActionMessages actionerrors = new ActionMessages();		
	    try{
			Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
			UsuarioDAO.sendCargaMasivaMail(usuarioActual,listaErrores,cantidadOk,cForm.getArchivoExcel().getFileName(),cambRP);			
		}
		catch (EmailException e){
	    	actionerrors.add("errorEmail",new ActionMessage("email.noenviadoCargaMasiva"));        
	    	saveMessages(request, actionerrors);
	    	//request.getSession().setAttribute("action", "add");	   
		}
		//Fin envio mail
		if(cambRP)
			return mapping.findForward("initCambioRP");
		return mapping.findForward("init");		
	}
	
	private String getContextPath()   {
		return getServlet().getServletContext().getRealPath("//cargamasiva//");
	}
}
