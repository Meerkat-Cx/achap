/**
 * 
 */
package ar.org.sicel.upload.actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Contacto;
import ar.org.sicel.persistence.ContactoDAO;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.ProcessWork;
import ar.org.sicel.proc.thread.ProcessThread;
import ar.org.sicel.upload.dao.ConstantsUpload;
import ar.org.sicel.upload.forms.UploadFileForm;
import ar.org.sicel.web.Tokens;

/**
 * Clase que brinda la funcionalidad para procesar los archivos zip que se encuentran 
 * almacenados en la base
 * @author jdivars
 *
 */
public class ProcessFileAction extends DispatchAction {
	static Logger log = Logger.getLogger(ProcessFileAction.class);
	/**
	 * Metodo que carga la pantalla "procesarArchivos" con los procesos que se encuentren
	 * en estado "uploaded"
	 */
	
	public ActionForward init(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		UploadFileForm propcessForm = (UploadFileForm)formm;
		propcessForm.setRolUser(((Usuario)request.getSession().getAttribute(Tokens.CURRENTUSER)).getRol().getNombre());
		String indice = request.getParameter("indice");
		if (indice == null)
			indice = "";
		request.setAttribute("indice",indice);
		String ecloId = request.getParameter("ecloId2");
		if (ecloId == null){
			Long idEcl = propcessForm.getEcloId();
			if (idEcl != null)
				ecloId = idEcl.toString();
			else
				ecloId = "";
		}
		request.setAttribute("ecloId2",ecloId);
		List list = null;
		List eclos = null;
		if (!ecloId.equals("") && !ecloId.equals("0") && !ecloId.equals("Todas las eclos"))
			list = ProcProcesDAO.getAllProcessByStateAndIdEclo(ConstantsUpload.UPLOADED,new Long(ecloId));
		else{
			if (propcessForm.getRolUser().equals(Tokens.NOMBREROLADMINISTRADOR) ||
				propcessForm.getRolUser().equals(Tokens.NOMBREROLGENERAL))
				list = ProcProcesDAO.getAllProcessState(ConstantsUpload.UPLOADED);
			else if (propcessForm.getRolUser().equals(Tokens.NOMBREROLREGIONAL))
				list = ProcProcesDAO.getAllProcessStateAndRegional(ConstantsUpload.UPLOADED,((Usuario)request.getSession().getAttribute(Tokens.CURRENTUSER)).getContacto());
		}
		if (propcessForm.getRolUser().equals(Tokens.NOMBREROLADMINISTRADOR) ||
			propcessForm.getRolUser().equals(Tokens.NOMBREROLGENERAL))
			eclos = EcloDAO.findAllOrderById();
		else if (propcessForm.getRolUser().equals(Tokens.NOMBREROLREGIONAL))
			eclos = EcloDAO.findAllOrderByIdAndRegional(((Usuario)request.getSession().getAttribute(Tokens.CURRENTUSER)).getContacto());
		propcessForm.setComments("");
		request.setAttribute("procesos",list);
		request.setAttribute("eclos",eclos);
		return mapping.findForward("success");
	}
	public ActionForward pop(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)
	throws Exception {	
		UploadFileForm form = (UploadFileForm)formm;
		String indice = request.getParameter("indice");
		request.setAttribute("indice",indice);
		String ecloId = request.getParameter("ecloId2");
		request.setAttribute("ecloId2",ecloId);
		form.setComments("");
		Long id = form.getIdP();
		request.setAttribute("id",id.toString());
		return mapping.findForward("popup");
		
	
	}
	/**
	 * Método que realiza el procesamiento el procesamiento del archivo seleccionado en la pantalla
	 * "procesarArchivos"
	 * 1º se trae de la base el proceso que fue selecionado
	 * 2º se genera el XXXX.zip en disco
	 * 3º se procede a la descompresion de dicho archivo generando el CCCC.xml
	 * 4º se envia a procesar el archivo mediante el thread ProcessWork el cual genera las salidas,
	 * en disco, comprime dichas salidas en un zip el cual es almacenado en la base de datos
	 */
	public ActionForward process(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		
		UploadFileForm form = (UploadFileForm)formm;
		Long id = form.getIdP();
		String obs=form.getComments();
		ProcProces proc = ProcProcesDAO.findByPrimaryKey(id);
		if(!proc.getEstado().equals(ConstantsUpload.STANDBY))
			proc.setFecha(new Date());
		proc.setEstado(ConstantsUpload.PROCESSING);
		proc.setObservacionSalida(obs);
		proc.setUsuarioProcesador((Usuario)request.getSession().getAttribute(Tokens.CURRENTUSER));
		StandaloneHibernateStrategy.getInstance().getCurrentSession();
		ProcProcesDAO.updateProcProces(proc);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		//System.out.println("SE VA A DESPERTAR A ->"+ proc.getId());
		log.debug("SE VA A DESPERTAR A ->"+ proc.getId());
		ProcessThread.getInstance().despertar();

		List listt = ProcProcesDAO.getAllProcessState(ConstantsUpload.UPLOADED);
		request.setAttribute("procesos",listt);
		form.setComments("");
		request.setAttribute("mensaje", "Desea seguir procesando?");
		request.setAttribute("urlRetorno", "'processFileAction.do?method=init'");
		String indice = request.getParameter("indice");
		request.setAttribute("indice",indice);
		String ecloId = request.getParameter("ecloId2");
		request.setAttribute("ecloId2",ecloId);
		//return null;
		return mapping.findForward("message");
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		UploadFileForm form = (UploadFileForm)formm;
		Long id = form.getIdP();
		ProcProces proc = ProcProcesDAO.findByPrimaryKey(id);
		ProcProcesDAO.deleteProcProces(proc);
		return init(mapping, formm, request, response);
	}
	
	public ActionForward filtrarPorIdEclo(ActionMapping mapping, ActionForm formm,HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		UploadFileForm propcessForm = (UploadFileForm)formm;
		List list = ProcProcesDAO.getAllProcessByStateAndIdEclo(ConstantsUpload.UPLOADED,propcessForm.getEcloId());
		propcessForm.setComments("");
		request.setAttribute("procesos",list);
		List eclos = EcloDAO.findAllOrderById();
		request.setAttribute("eclos",eclos);
		return mapping.findForward("success");
	}
	
}
	

