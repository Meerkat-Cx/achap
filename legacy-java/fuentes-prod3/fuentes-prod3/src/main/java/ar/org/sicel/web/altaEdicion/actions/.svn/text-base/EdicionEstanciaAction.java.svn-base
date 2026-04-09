package ar.org.sicel.web.altaEdicion.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Estancia;
import ar.org.sicel.persistence.EstanciaDAO;
import ar.org.sicel.persistence.LogContacto;
import ar.org.sicel.persistence.LogContactoDAO;
import ar.org.sicel.persistence.MetodoControl;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.EstanciaForm;

public class EdicionEstanciaAction extends DispatchAction {



	 /**
     * Prepara los valores para cargar la pantalla de alta para
     * de una estancia
     * @param actionmapping
     * @param actionform
     * @param httpservletrequest
     * @param httpservletresponse
     * @return ActionForward
     * @throws Exception
     */    
    public ActionForward initAdd(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws Exception
    {
    	//List eclos = EcloDAO.findAllActivas();
    	
		
		//httpservletrequest.getSession().setAttribute("eclos",eclos);
		//System.out.println("cantidad de ecos "+eclos.size());
    	EstanciaForm estanciaForm = (EstanciaForm)actionform;
    	estanciaForm.reset(actionmapping,httpservletrequest);
		//List propietarios = PropietarioDAO.findAllActivos();
    	List propietarios = PropietarioDAO.findAllActivosPorId();
		httpservletrequest.getSession().setAttribute("propietarios",propietarios);
		
        httpservletrequest.getSession().setAttribute("action", "add");
        return actionmapping.findForward("success");
    }
    
    /**
     * Crea una entidad con los datos obtenidos del formulario para enviar a la
     * capa de persistencia e impactar en la base de datos
     * @param actionmapping
     * @param actionform
     * @param httpservletrequest
     * @param httpservletresponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward add(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	if (isCancelled(httpservletrequest))
            return (actionmapping.findForward(Tokens.FAILURE));
    	
    	EstanciaForm estanciaForm = (EstanciaForm)actionform;

    	ActionMessages actionerrors = new ActionMessages();
        Estancia estancia = new Estancia();
        
        Date fechaI = new Date();
		fechaI= DateUtils.parse(estanciaForm.getFechaLog(),"dd/MM/yyyy");
		if(fechaI == null || new Date().before(fechaI) ){
			if(fechaI != null)
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaPosterior"));
			else
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaErronea"));
        	saveMessages(httpservletrequest, actionerrors);
        	httpservletrequest.getSession().setAttribute("action", "add");  
            estanciaForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
            return actionmapping.findForward("success");
		}
        
        
       /* if (EstanciaDAO.getEstanciaByCuig(estanciaForm.getCuig()) != null) {
        	actionerrors.add("uniqueConstraint",new ActionMessage("entidad.existe", "Estancia", estanciaForm.getCuig()));
        	//this.saveErrors(httpservletrequest, actionerrors);
        	saveMessages(httpservletrequest, actionerrors);
            httpservletrequest.getSession().setAttribute("action", "add");            
            return actionmapping.findForward("success");
        }*/
		Date f = new Date(fechaI.getTime());
        copiarPropiedades(estanciaForm, estancia,httpservletrequest,f);
        
        try {
        	EstanciaDAO.createEstancia(estancia);
        	httpservletrequest.setAttribute("nuevoid",estancia.getId());
        	httpservletrequest.setAttribute("tipo","El Establecimiento");
        } catch(HibernateException e ){        	
        }
        httpservletrequest.getSession().removeAttribute("usuarioForm");
        return actionmapping.findForward("mostrarId");
    }
    
    /**
     * Metodo agregado para cuando se quiere agregar un Establecimiento (Ex estancia nuevo)
     * @param estanciaForm
     * @param estancia
     */
    private void copiarPropiedadesAdd(EstanciaForm estanciaForm, Estancia estancia,Date f) {
    	//Eclo eclo = EcloDAO.findByPrimaryKey(estanciaForm.getIdEclo());
    	Propietario propietario = PropietarioDAO.findByPrimaryKey(estanciaForm.getIdPropietario());
    	
    	estancia.setCuig(estanciaForm.getCuig());
    	estancia.setCuit(estanciaForm.getCuit());
    	estancia.setRenspa(estanciaForm.getRenspa());
    	estancia.setNombreContacto(estanciaForm.getNombre());
    	estancia.setActivo(estanciaForm.getActivo());
    	//estancia.setEclo(eclo);
    	//estancia.setActivoEclo(eclo.getActivo());
    	estancia.setPropietario(propietario);
    	estancia.setActivoPropietario(propietario.getActivo());
    }

	private void copiarPropiedades(EstanciaForm estanciaForm, Estancia estancia,HttpServletRequest httpservletrequest,Date f) {
		boolean actualizarTambos = false;
		estancia.setCuig(estanciaForm.getCuig());
		estancia.setCuit(estanciaForm.getCuit());
		estancia.setRenspa(estanciaForm.getRenspa());
		estancia.setNombreContacto(estanciaForm.getNombre());	
		Propietario propietario = PropietarioDAO.findByPrimaryKey(estanciaForm.getIdPropietario());
		estancia.setPropietario(propietario);
		
		LogContacto log = new LogContacto();
		//log.setActivo(eForm.getActivo().booleanValue());
		if (estanciaForm.getActivo().booleanValue())
			log.setAccion(LogContacto.ACTIVAR);
		else
			log.setAccion(LogContacto.DESACTIVAR);
		//log.setFecha(new Date());
		log.setFecha(f);
		log.setContacto(estancia);
		Usuario user = (Usuario) httpservletrequest.getSession().getAttribute(Tokens.CURRENTUSER); 
		log.setUsuario(user);
		List r =new ArrayList(estancia.getBitacora());
		if(!r.contains(log)){
			estancia.getBitacora().add(log);
			LogContacto logEstado = LogContacto.getUltimoLogDeEstado(estancia);
			estancia.setActivo(logEstado.getAccion().trim().equals(LogContacto.ACTIVAR)?true:false);
			Iterator it2 = estancia.getUsuarios().iterator();
			while(it2.hasNext()){
				Usuario us = (Usuario)it2.next();
				us.setActivo(estancia.getActivo());
			}
			if(estancia.getEstablecimientos()!=null){
			Iterator it = estancia.getEstablecimientos().iterator();
				while(it.hasNext()){
					Establecimiento es = (Establecimiento)it.next();
					es.setActivoEstancia(estancia.getActivo());
					//es.setPropietario(estancia.getPropietario());
					if((!es.getPropietario().equals(estancia.getPropietario()))){
						es.setPropietario(estancia.getPropietario());
						//a todos los animales del tambo ponerle la estancia nueva
						EstablecimientoDAO.actualizarAnimales(es, estancia);
						}
					if(log.getAccion().trim().equals(LogContacto.DESACTIVAR)){
						List r1 =new ArrayList(es.getBitacora());
						LogContacto lMet = LogContacto.getUltimoLogFecha(es,log.getFecha());
						MetodoControl metodoLogAnterior = null; 
						if(lMet!=null)
							metodoLogAnterior = (lMet!=null? lMet.getMetodoControlNuevo():null);
						LogContacto log2 = LogContactoDAO.create(log.getAccion(),log.getCentro(),es,log.getFecha(),metodoLogAnterior,log.getUsuario());
						if(!r1.contains(log2)){
							es.getBitacora().add(log2);
							LogContacto logEstadoEs = LogContacto.getUltimoLogDeEstado(es);
							es.setActivoEstablecimiento(logEstadoEs.getAccion().trim().equals(LogContacto.ACTIVAR)?true:false);
						}
					}
				}
			}
			
	}
		
		
		
		
		
		/*if(estancia.getId()!=null){//es modificacion
			if(estancia.getPropietario()!= null && 
					!estancia.getPropietario().getId().equals(estanciaForm.getIdPropietario())){
				estancia.setPropietario(propietario);
				estancia.setActivoPropietario(estancia.getPropietario().getActivo());
				actualizarTambos = true;
			}		
			if(!estanciaForm.getActivo().equals(estancia.getActivo())){
				estancia.setActivo(estanciaForm.getActivo());
				actualizarTambos = true;
				
			}
			if(actualizarTambos){
				Iterator it = estancia.getEstablecimientos().iterator();
				while(it.hasNext()){
					Establecimiento es = (Establecimiento)it.next();
					es.setActivoEstancia(estanciaForm.getActivo());
					//es.setEclo(estancia.getEclo());
					//es.setActivoEclo(estancia.getEclo().getActivo());
					es.setPropietario(estancia.getPropietario());
					es.setActivoPropietario(estancia.getPropietario().getActivo());
				}			
			}
		}
		else{
			estancia.setPropietario(propietario);
	    	estancia.setActivoPropietario(propietario.getActivo());
		}*/
		
	}
	
	public ActionForward initmod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		EstanciaForm estanciaForm = (EstanciaForm) form;
		Long idEst = new Long(estanciaForm.getId());
		Estancia estancia = EstanciaDAO.findByPrimaryKey(idEst);
		
    	estanciaForm.reset(mapping,request);
		copyProperties(estancia, estanciaForm);
		/*Eclo aux = estancia.getEclo();
		List eclos = EcloDAO.findAllActivas();
		eclos.remove(aux);
		eclos.add(aux);
		request.getSession().setAttribute("eclos",eclos);*/
		Propietario p = estancia.getPropietario();
		List propietarios = PropietarioDAO.findAll();
		propietarios.remove(p);
		propietarios.add(p);
		request.getSession().setAttribute("propietarios",propietarios);
		
		request.setAttribute("ubicaciones", estancia.getUbicacions()!=null?estancia.getUbicacions(): new HashSet());
		
        request.getSession().setAttribute("action", "update");
        
        return mapping.findForward("success");		
	}
	
	 /**
     * Copio las propiedades de la entidad al form
     * @param userForm - UsuarioForm
     * @param user - Usuario
     */
    private void copyProperties(Estancia estancia, EstanciaForm estanciaForm) {
    	estanciaForm.setId(estancia.getId().toString());
    	estanciaForm.setCuig(estancia.getCuig());
    	estanciaForm.setCuit(estancia.getCuit());
    	estanciaForm.setRenspa(estancia.getRenspa());    	
    	estanciaForm.setIdPropietario(estancia.getPropietario().getId());
    	estanciaForm.setNombre(estancia.getNombreContacto());
    	/*if(estancia.getActivoEclo()!=null)
    		estanciaForm.setActivoEclo(estancia.getActivoEclo());*/
    	if(estancia.getActivoPropietario()!=null)
    		estanciaForm.setActivoPropietario(estancia.getActivoPropietario());
    	if(estancia.getActivo()!=null)
    		estanciaForm.setActivo(estancia.getActivo());
    }
    
    /**
     * Modifica la entidad obteniendo los datos desde el formulario
     * @param actionmapping
     * @param actionform
     * @param httpservletrequest
     * @param httpservletresponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward update(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	if (isCancelled(httpservletrequest))
            return (actionmapping.findForward(Tokens.FAILURE));
        
    	EstanciaForm estanciaForm = (EstanciaForm)actionform;
        
        if(!StringUtils.isNotEmpty(estanciaForm.getId())) 
        	 return (actionmapping.findForward(Tokens.FAILURE));
        
        Estancia estancia = EstanciaDAO.findByPrimaryKey(new Long(estanciaForm.getId()));
        ActionMessages actionerrors = new ActionMessages();
        Date fechaI = new Date();
		fechaI= DateUtils.parse(estanciaForm.getFechaLog(),"dd/MM/yyyy");
		if(fechaI == null || new Date().before(fechaI) ){
			if(fechaI != null)
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaPosterior"));
			else
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaErronea"));
        	saveMessages(httpservletrequest, actionerrors);
        	httpservletrequest.getSession().setAttribute("action", "update");  
            estanciaForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
            return actionmapping.findForward("success");
		}
		Date f = new Date(fechaI.getTime());
		//boolean cambioProp = (estanciaForm.getIdPropietario().equals(estancia.getPropietario().getId()))?false:true;
			
        copiarPropiedades(estanciaForm, estancia,httpservletrequest,f);
        EstanciaDAO.updateEstancia(estancia);
        
       /* if(cambioProp && estancia.getEstablecimientos()!=null){
			Iterator it = estancia.getEstablecimientos().iterator();
				while(it.hasNext()){
					Establecimiento es = (Establecimiento)it.next();
					es.setPropietario(estancia.getPropietario());
					EstablecimientoDAO.update(es);
				}
        }*/
        estanciaForm.reset(actionmapping,httpservletrequest);
        httpservletrequest.getSession().removeAttribute("estanciaForm");
        return actionmapping.findForward("list");
    }
}
