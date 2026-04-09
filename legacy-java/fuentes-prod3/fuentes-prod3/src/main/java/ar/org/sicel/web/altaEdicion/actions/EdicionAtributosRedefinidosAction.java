package ar.org.sicel.web.altaEdicion.actions;

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

import ar.org.sicel.persistence.AtrVariables;
import ar.org.sicel.persistence.AtrVariablesDAO;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.ConfiguracionDAO;
import ar.org.sicel.persistence.Especie;
import ar.org.sicel.persistence.EspecieDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Valor;
import ar.org.sicel.persistence.ValorDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.ValorForm;
import ar.org.sicel.web.util.Item;

public class EdicionAtributosRedefinidosAction extends DispatchAction {
	static Logger log = Logger.getLogger(EdicionAtributosRedefinidosAction.class);

	public ActionForward editValor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("edicion de Valor");
		ValorForm vForm = (ValorForm) form;		
		String id = request.getParameter("id");
		String nombreEntidad= request.getParameter("nombreEntidad");
		Valor valor= ValorDAO.findByPrimaryKey(Long.valueOf(id));
		this.copiarPropiedades(valor,vForm);
		vForm.setNombreEntidad(nombreEntidad);
        request.getSession().setAttribute("action", "update");
        return mapping.findForward("edicion");  
	}

    private void copiarPropiedades(Valor valor, ValorForm form) {
    	form.setId(valor.getId());
    	form.setInicio(valor.getInicio()!=null?DateUtils.format(valor.getInicio(),null):"");
    	form.setValorAdmitido(valor.getValorAdmAtr().getValor());
		form.setNombre(valor.getValorAdmAtr().getAtributo().getNombre());
	}

    public ActionForward update(ActionMapping actionmapping, ActionForm form, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception{
    	if (isCancelled(httpservletrequest))
    		return (actionmapping.findForward(Tokens.FAILURE));

    	ValorForm vForm = (ValorForm) form;	
		Valor valor= ValorDAO.findByPrimaryKey(Long.valueOf(vForm.getId()));
		if(vForm.getEsIntervalo()){
			String f= DateUtils.format(new Date(),null);	
			Date fecha = DateUtils.parse(f, null);
			valor.setFin(fecha);
			AtrVariables atrVariable= getAtrVariable(vForm.getIdConjunto().intValue(),vForm.getIdEntidad());
    		atrVariable.setValor(vForm.getNombre(),fecha,vForm.getValorAdmitido());
		}else
			valor.getValorAdmAtr().setValor(vForm.getValorAdmitido());
		Configuracion.suciaCache = true;
		Configuracion.getConfiguracionActual();
		httpservletrequest.getSession().removeAttribute("action");
		return actionmapping.findForward("list");
	}

	public ActionForward insertValor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("Alta de Valor");
		ValorForm vForm = (ValorForm) form;		
		String idAtributo = request.getParameter("idAtributo");
		String nombreAtributo= request.getParameter("nombreAtributo");
		//Se utiliza el id de valor para guardar el del atributo asi no se agrega mas property 
		vForm.setInicio(DateUtils.format(new Date(),null));
		vForm.setId(Long.valueOf(idAtributo));
		vForm.setNombre(nombreAtributo);
		vForm.setNombreEntidad("");
		vForm.setValorAdmitido("");
		vForm.setValorPorDefecto("");
		return mapping.findForward("select");  
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		ValorForm f = (ValorForm) form;
		List lista = getLista(f.getIdConjunto(),f.getId(),f.getNombreEntidad());
		request.setAttribute("listadoEntidades", lista);
		return mapping.findForward("select");
	}

		
	public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("Seleccion de Valor");
        request.getSession().setAttribute("action", "insert");
        return mapping.findForward("edicion");  
	}

	public ActionForward insert(ActionMapping actionmapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse)
    throws Exception{
    	if (isCancelled(request))
    		return (actionmapping.findForward(Tokens.FAILURE));

    	ValorForm vForm = (ValorForm) form;
    	//recupero el atributo
    	AtrVariables atrVariable= getAtrVariable(vForm.getIdConjunto().intValue(),vForm.getIdEntidad());
    	if(vForm.getEsIntervalo()){
			String f= DateUtils.format(new Date(),null);	
			Date fechaInicio = DateUtils.parse(f,null);
    		atrVariable.setValor(vForm.getNombre(),fechaInicio,vForm.getValorAdmitido());
    	}else
    		atrVariable.setValor(vForm.getNombre(),vForm.getValorAdmitido());
		request.getSession().removeAttribute("action");
		Configuracion.suciaCache = true;
		Configuracion.getConfiguracionActual();
    	return actionmapping.findForward("list");
	}

	public ActionForward delete(ActionMapping actionmapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse)
    throws Exception{
		log.debug("Borrado de Valor");
		String id = request.getParameter("idValor");
		Valor valor= ValorDAO.findByPrimaryKey(Long.valueOf(id));
		HibernateFactory.getSession().delete(valor);
		request.getSession().removeAttribute("action");
		Configuracion.suciaCache = true;
		Configuracion.getConfiguracionActual();
		return actionmapping.findForward("list");
	}

	private List getLista(Long id,Long idAtributo, String nombre){
		List list= null;
		List result = new ArrayList();
		Item item;
		switch(id.intValue()){
		case 2:
			list= (List) EstablecimientoDAO.findNombreNotAtributos(idAtributo,nombre);
			Establecimiento e;
			for(int i=0; i < list.size();i++){
				e= (Establecimiento)list.get(i);
				item= new Item();
				item.setId(e.getId().toString());
				item.setNombre(e.getNombreContacto());
				result.add(item);
			}
			break;
		case 3:
			list= (List) EspecieDAO.findNombreNotAtributos(idAtributo,nombre);
			Especie es;
			for(int i=0; i < list.size();i++){
				es= (Especie)list.get(i);
				item= new Item();
				item.setId(es.getId().toString());
				item.setNombre(es.getNombre());
				result.add(item);
			}
			break;
		case 4:
			list= (List) RazaDAO.findNombreNotAtributos(idAtributo,nombre);
			Raza r;
			for(int i=0; i < list.size();i++){
				r= (Raza)list.get(i);
				item= new Item();
				item.setId(r.getId().toString());
				item.setNombre(r.getNombre());
				result.add(item);
			}
			break;
		case 5:
			list= (List) ConfiguracionDAO.findNombreNotAtributos(idAtributo,nombre);
			Configuracion co;
			for(int i=0; i < list.size();i++){
				co= (Configuracion)list.get(i);
				item= new Item();
				item.setId(co.getNombre());
				item.setNombre(co.getNombre());
				result.add(item);
			}
			break;
		}
		return result;
	}    

	private AtrVariables getAtrVariable(int id,String idEntidad){
		AtrVariables atrVariables=null;
		switch(id){
		case 2:
			Establecimiento e= EstablecimientoDAO.findByPrimaryKey(Long.valueOf(idEntidad));
			if(e.getAtrVariablesEstab()!=null)
				atrVariables= e.getAtrVariablesEstab();
			else{
				atrVariables= AtrVariablesDAO.create();
				e.setAtrVariablesEstab(atrVariables);
			}
			break;
		case 3:
			Especie es= EspecieDAO.findByPrimaryKey(idEntidad);
			if(es.getAtrVariables()!=null)
				atrVariables= es.getAtrVariables();
			else{
				atrVariables= AtrVariablesDAO.create();
				es.setAtrVariables(atrVariables);
			}
			break;
		case 4:
			Raza r= RazaDAO.findByPrimaryKey(idEntidad);
			if(r.getAtrVariables()!=null)
				atrVariables= r.getAtrVariables();
			else{
				 atrVariables= AtrVariablesDAO.create();
				r.setAtrVariables(atrVariables);
			}
			break;
		case 5:
			Configuracion co= ConfiguracionDAO.findByPrimaryKey(idEntidad);
			if(co.getAtrVariables()!=null)
				atrVariables= co.getAtrVariables();
			else{
				atrVariables= AtrVariablesDAO.create();
				co.setAtrVariables(atrVariables);
			}
			break;
		}
		return atrVariables;
	}    
 	
}