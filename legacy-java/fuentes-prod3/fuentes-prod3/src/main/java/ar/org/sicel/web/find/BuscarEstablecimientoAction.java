package ar.org.sicel.web.find;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.EntidadRegional;
import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.LogContacto;
import ar.org.sicel.persistence.LogPorFecha;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.EstablecimientoForm;

/**
 * @struts.action
 * 		path="/buscarEstablecimiento"
 * 		name="establecimientoForm"
 * 		scope="request"
 * 		validate="true"
 *
 * @struts.action-forward
 * 		name="success"
 * 		path=".estabTable"
 *
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="failure"
 * 		path=".buscarEstablecimiento"
 * 
 * @struts.action-forward 
 * 		name="detalle" 
 * 		path=".estabDetalle"
 * 
 * @struts.action-forward 
 * 		name="edicion" 
 * 		path="/preAltaEstablecimiento.do"
 *
 */
public class BuscarEstablecimientoAction extends DispatchAction {

	public ActionForward iniciar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
		EstablecimientoForm eForm = (EstablecimientoForm) form;
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		eForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
		eForm.setRolProp(Tokens.NOMBREROLPROPIETARIO);
		eForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
		eForm.setRol(usuarioActual.getRol().getNombre());
        List establecimientos =new ArrayList();
        request.setAttribute("estabData", establecimientos);   
        eForm.reset();
		return (mapping.findForward(Tokens.SUCCESS));
       
       
	}
	public ActionForward ejecutar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        if (isCancelled(request))
            return (mapping.findForward("main"));
        EstablecimientoForm eForm = (EstablecimientoForm) form;
        Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        List establecimientos = null;
		if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR)){
			Eclo eclo = EcloDAO.findByPrimaryKey(usuarioActual.getContacto().getId());
			establecimientos = EstablecimientoDAO.findEstablecimientos(eForm.getIdEst(),eclo.getNombreContacto(),eForm.getNombre(),eForm.getNombreContactoPropietario(),eForm.getActivo(),null);
		}else
			if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROPIETARIO)){
				Propietario prop = PropietarioDAO.findByPrimaryKey(usuarioActual.getContacto().getId());	
				establecimientos = EstablecimientoDAO.findEstablecimientos(eForm.getIdEst(),eForm.getNombreContactoEclo(),eForm.getNombre(),prop.getNombreContacto(),eForm.getActivo(),null);
			
			}else
				if(usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL)){
					EntidadRegional reg = EntidadRegionalDAO.findByPrimaryKey(usuarioActual.getContacto().getId());
					establecimientos = EstablecimientoDAO.findEstablecimientos(eForm.getIdEst(),eForm.getNombreContactoEclo(),eForm.getNombre(),eForm.getNombreContactoPropietario(),eForm.getActivo(),reg.getId());
				}
				else
					establecimientos = EstablecimientoDAO.findEstablecimientos(eForm.getIdEst(),eForm.getNombreContactoEclo(),eForm.getNombre(),eForm.getNombreContactoPropietario(),eForm.getActivo(),null);
		request.setAttribute("estabData", establecimientos);        
		return (mapping.findForward(Tokens.SUCCESS));
       
       
	}
	public ActionForward initView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		EstablecimientoForm eForm = (EstablecimientoForm)form;
		eForm.reset();
		String idEst = request.getParameter("id");
		eForm.setIdEst(idEst);
		Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(new Long(eForm.getIdEst()));
		this.copyProperties(estab,eForm);
		//Iterator it  = estab.getBitacora().iterator();
		//Set bita = new HashSet();
		//bita.addAll(estab.getBitacora());
		/*while(it.hasNext()){
			LogContacto l = (LogContacto)it.next();
			bita.add(l);
		}*/
		request.getSession().setAttribute("action", "view");
		List r =new ArrayList(estab.getBitacora());
    	if(!r.isEmpty())
			Collections.sort(r, new LogPorFecha());  
    	request.setAttribute("bitacora",r);
		//request.setAttribute("bitacora", bita);
		return mapping.findForward("detalle");		
	}
	/**
     * Copio las propiedades de la entidad al form
     * @param userForm - UsuarioForm
     * @param user - Usuario
     */
    private void copyProperties(Establecimiento estab, EstablecimientoForm eForm) {
    	
    	eForm.setIdEst(estab.getId().toString());
    	eForm.setNombre(estab.getNombreContacto());
    	if(estab.getS1Eclo()!=null)
    		eForm.setS1Eclo(estab.getS1Eclo().toString());
    	if(estab.getS1Prop()!=null)
    		eForm.setS1Propietario(estab.getS1Prop().toString());
    	if(estab.getS1Tbo()!=null)
    		eForm.setS1Tambo(estab.getS1Tbo().toString());
    	if(estab.getEstancia()!=null){
		    	eForm.setIdEstancia(estab.getEstancia().getId().toString());
		    	eForm.setEstancia(estab.getEstancia().getNombreContacto());
    	}
		 if(estab.getEclo()!=null){
		 eForm.setNombreContactoEclo(estab.getEclo().getNombreContacto());
		 eForm.setIdEclo(estab.getEclo().getId().toString());
		 }
		 if(estab.getPropietario()!=null){
			 	eForm.setNombreContactoPropietario(estab.getPropietario().getNombreContacto());
			 	eForm.setIdPropietario(estab.getPropietario().getId().toString());
		 }
		 if(estab.getActivoEstablecimiento()!=null)
			 eForm.setActivo(estab.getActivoEstablecimiento());
		 if(estab.getActivoEclo()!=null)
			 eForm.setActivoEclo(estab.getActivoEclo());
		 if(estab.getActivoPropietario()!=null)
			 eForm.setActivoPropietario(estab.getActivoPropietario());
		 if (estab.getMetodoControl() != null)
			 eForm.setMetodoControl(estab.getMetodoControl().getCodigo());
		 eForm.setCuig(estab.getCuig());
		 eForm.setCuit(estab.getCuit());
		 eForm.setRenspa(estab.getRenspa());
    }
}
