package ar.org.sicel.web.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.org.sicel.persistence.EntidadRegionalDAO;
import ar.org.sicel.web.Tokens;

/**
 * @struts.action
 * 		path="/buscarEntidadRegional"
 * 		name="busquedaForm" 		
 * 		scope="request"
 * 		validate="true"
 *
 * @struts.action-forward
 * 		name="success"
 * 		path=".regionalTable"
 *
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="failure"
 * 		path=".buscarEntidadRegional"
 *
 */

public class BuscarEntidadRegionalAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
       
        if (isCancelled(request))
            return (mapping.findForward("main"));

        Criteria criteria = EntidadRegionalDAO.getCriteria();
        
        Boolean entidadRegionalActivo = (Boolean) PropertyUtils.getSimpleProperty(form,"entidadRegionalActivo");        
        if (entidadRegionalActivo) {
        	System.out.println("Entidad Regional activo");
        	Long entidadRegionalId = (Long)PropertyUtils.getSimpleProperty(form,"entidadRegionalId");        	
        	criteria.add(Restrictions.eq("id", entidadRegionalId));
        }
        
        Boolean ecloIdActivo = (Boolean) PropertyUtils.getSimpleProperty(form,"ecloIdActivo");        
        if (ecloIdActivo) {
        	System.out.println("Nombre Eclo activo");
        	Long ecloId = (Long)PropertyUtils.getSimpleProperty(form,"ecloId");       	        	
        	criteria.createAlias("eclos", "eclosAlias").add(Restrictions.eq("eclosAlias.id", ecloId));
        }
                    
        /*
        Boolean nombreEstablecimientoActivo =(Boolean)PropertyUtils.getSimpleProperty(form,"nombreEstablecimientoActivo");
        if (nombreEstablecimientoActivo) {
        	System.out.println("Establecimiento activo");        	
        	String nombreEstablecimiento = (String)PropertyUtils.getSimpleProperty(form,"nombreEstablecimiento");
        	String nombreEstablecimientoPattern = "%" + nombreEstablecimiento.replace(" ","%") + "%";
        	criteria.createAlias("eclos", "eclosAlias").add(Restrictions.like("establecimientosAlias.nombreContacto", nombreEstablecimientoPattern));
        }
        */
        
        Boolean comentarioActivo =(Boolean)PropertyUtils.getSimpleProperty(form,"comentarioActivo");
        if (comentarioActivo) {
        	System.out.println("Comentario activo");        	
        	String comentario= (String)PropertyUtils.getSimpleProperty(form,"comentario");
        	String comentarioPattern = "%" + comentario.replace(" ","%") + "%"; 
        	criteria.add(Restrictions.like("comentario", comentarioPattern));
        }
        
        List listaEntidadesRegionales =  criteria.list();        
        request.setAttribute("regionalData", listaEntidadesRegionales);        
		return (mapping.findForward(Tokens.SUCCESS));                
	}

}
