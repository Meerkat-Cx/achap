/**
 * 
 */
package ar.org.sicel.web.proc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegional;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.util.StringUtils;
import ar.org.sicel.web.find.ReportesEcloForm;


/**
 * Accion de los reporte de facturacion y produccion lechera
 * @author jdivars
 * @since 13-jul-2007
 */
public class ReportesAchaAction extends DispatchAction {

	
	public ActionForward initFacturacion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//AnimalForm af = (AnimalForm)form;
		//af.reset();
		ReporteAchaForm  rForm =(ReporteAchaForm)form;
		rForm.reset();
		List regionales = EntidadRegionalDAO.findAll();
		List eclos = EcloDAO.findAll();
		//List anios = new ArrayList();
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		Date fecha = new Date();
		String anio = formatAnio.format(fecha);
		rForm.setAnioMax(anio);
		System.out.println(rForm.getAnioMax());
		//rForm.setAnio(anio);
		//request.setAttribute("anios",anios);
		request.setAttribute("regionales",regionales);
		request.setAttribute("eclos",eclos);
		return mapping.findForward("success");
		
		
	}
public ActionForward cambioEclo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		List regionales = EntidadRegionalDAO.findAll();
		List eclos = EcloDAO.findAll();
		request.setAttribute("regionales",regionales);
		request.setAttribute("eclos",eclos);
		return mapping.findForward("success");
	}
	public ActionForward filtrarEclos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			ReporteAchaForm  rForm =(ReporteAchaForm)form;
			if(rForm.getIdRegional().equals("")){
				List eclos = EcloDAO.findAll();
				request.setAttribute("eclos",eclos);
			}
			else{	
				EntidadRegional er = EntidadRegionalDAO.findByPrimaryKey(Long.parseLong(rForm.getIdRegional()));
				List eclos = EcloDAO.findEclosPorFiltro(null,er.getNombreContacto());
				request.setAttribute("eclos",eclos);
				}
			List regionales = EntidadRegionalDAO.findAll();
			request.setAttribute("regionales",regionales);
			return mapping.findForward("success");
			
			
		}
	public ActionForward initBuscarPropietario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		//System.out.println("iniciando la busqueda de PROPIETARIO");
		ReporteAchaForm reForm = (ReporteAchaForm) form;
		if(!reForm.getIdRegional().equals("")){
			EntidadRegional er = EntidadRegionalDAO.findByPrimaryKey(Long.parseLong(reForm.getIdRegional()));
			reForm.setNombreRegional(er.getNombreContacto());
		}
		else
			reForm.setNombreRegional("");
		if(!reForm.getIdEclo().equals("")){
			Eclo er = EcloDAO.findByPrimaryKey(Long.parseLong(reForm.getIdEclo()));
			reForm.setNombreEclo(er.getNombreContacto());
		}
		else{
			reForm.setNombreEclo("");
		}
		reForm.setIdProp("");
		reForm.setNombreProp("");
		return mapping.findForward("initBuscarPropietario");		
	}

	public ActionForward buscarPropietario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ReporteAchaForm reForm = (ReporteAchaForm) form;
		List propietarios =null;
		if(org.apache.commons.lang.StringUtils.isNotEmpty(reForm.getIdEclo()))
			propietarios = PropietarioDAO.findPropietariosPorEclo(reForm.getIdEclo(),reForm.getIdProp(),reForm.getNombreProp());
		else
		   propietarios = PropietarioDAO.findPropietariosPorIDyNmbre(reForm.getIdProp(),reForm.getNombreProp());
		
		if(propietarios.isEmpty()){
				request.setAttribute("error","EL PROPIETARIO NO EXISTE"); 
				return mapping.findForward("initBuscarPropietario");			
			}
		request.setAttribute("propietarios", propietarios);
		return mapping.findForward("initBuscarPropietario");	
		}
	
	public ActionForward seleccionarPropietario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
			
			String idProp = request.getParameter("id");
			ReporteAchaForm reForm = (ReporteAchaForm) form;
			Propietario p = PropietarioDAO.findByPrimaryKey(Long.valueOf(idProp));
			reForm.setIdProp(idProp);
			reForm.setNombreProp(p.getNombreContacto());
			List regionales = EntidadRegionalDAO.findAll();
			request.setAttribute("regionales",regionales);
			List eclos = EcloDAO.findAll();
			request.setAttribute("eclos",eclos);
			return mapping.findForward("success");		
		}
	public ActionForward reporteFacturacion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		
		ReporteAchaForm reForm = (ReporteAchaForm) form;
		
		List regionales = EntidadRegionalDAO.findAll();
		request.setAttribute("regionales",regionales);
		List eclos = EcloDAO.findAll();
		request.setAttribute("eclos",eclos);
		return mapping.findForward("success");		
	}
}
