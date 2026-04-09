/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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

import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AtrVariablesDAO;
import ar.org.sicel.persistence.CentroDeComputoDAO;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Estancia;
import ar.org.sicel.persistence.EstanciaDAO;
import ar.org.sicel.persistence.LogContacto;
import ar.org.sicel.persistence.LogPorFecha;
import ar.org.sicel.persistence.MetodoControl;
import ar.org.sicel.persistence.MetodoControlDAO;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.UsuarioDAO;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.EstablecimientoForm;
import ar.org.sicel.web.altaEdicion.forms.EstanciaForm;
import ar.org.sicel.web.find.ReporteEventosForm;

/**
 * @author jdivars
 *
 */
public class EdicionEstablecimientoAction extends DispatchAction {

	public EdicionEstablecimientoAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActionForward listar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception
	{
		try {
			List listEstablecimientos = EstablecimientoDAO.findAll();
			
			
			httpservletrequest.setAttribute("listaEstablecimientos", listEstablecimientos);
		} catch (HibernateException he){
			log.error("La lista de establecimientos no puede ser recuperada",he);
			ActionMessages actionMessages = new ActionMessages();
			actionMessages.add("messageGlobalError",new ActionMessage("messageGlobalError"));
			actionMessages.add("exceptionMessage",new ActionMessage("exceptionMessage","No se pudo recuperar la lista de tambos desde la base de datos. Verifique la conexión a la misma."));
			saveMessages(httpservletrequest,actionMessages);
			return actionmapping.findForward("error");
		}
		return actionmapping.findForward("list");
	}
	 /**
     * Prepara los valores para cargar la pantalla de alta para
     * Establecimiento
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
    	EstablecimientoForm eForm = (EstablecimientoForm)actionform;
    	eForm.reset();
    	List metodosControl = MetodoControlDAO.findAll();
    	httpservletrequest.setAttribute("metodos", metodosControl);
    	List centrosDeComputo = CentroDeComputoDAO.findAllCentroDeComputo();
    	httpservletrequest.setAttribute("centros", centrosDeComputo);
    	
    	eForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
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
        
    	EstablecimientoForm eForm = (EstablecimientoForm)actionform;
    	
    	ActionMessages actionerrors = new ActionMessages();

    	Establecimiento estab = new Establecimiento();
    	/*03/11/2008 el cliente dijo que no se haga mas este chequeo
    	 * if (EstablecimientoDAO.getEstablecimientoByCuig(eForm.getCuig()) != null) {
        	actionerrors.add("uniqueConstraint",new ActionMessage("establecimiento.existe", "Tambo", eForm.getCuig()));
        	//this.saveErrors(httpservletrequest, actionerrors);
        	saveMessages(httpservletrequest, actionerrors);
        	List metodosControl = MetodoControlDAO.findAll();
        	httpservletrequest.setAttribute("metodos", metodosControl);
            httpservletrequest.getSession().setAttribute("action", "add");            
            return actionmapping.findForward("success");
        }*/
    	Date fechaI = new Date();
		fechaI= DateUtils.parse(eForm.getFechaLog(),"dd/MM/yyyy");
		if(fechaI == null || new Date().before(fechaI) ){
			if(fechaI != null)
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaPosterior"));
			else
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaErronea"));
        	saveMessages(httpservletrequest, actionerrors);
        	List metodosControl = MetodoControlDAO.findAll();
        	httpservletrequest.setAttribute("metodos", metodosControl);
            httpservletrequest.getSession().setAttribute("action", "add");  
            List centrosDeComputo = CentroDeComputoDAO.findAllCentroDeComputo();
        	httpservletrequest.setAttribute("centros", centrosDeComputo);
            eForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
            return actionmapping.findForward("success");
		}
		
		Date f = new Date(fechaI.getTime());
    	this.copiarPropiedades(eForm,estab,httpservletrequest,f);
    	
    	
    	EstablecimientoDAO.save(estab);
    	
    	httpservletrequest.setAttribute("nuevoid",estab.getId());
    	httpservletrequest.setAttribute("tipo","El Tambo");
    	try { 
    		EstablecimientoDAO.sendEMail(estab,"nuevo");
    	}catch (EmailException e){
        	actionerrors.add("errorEmail",new ActionMessage("email.noenviadoTambo", estab.getId()));        
        	saveMessages(httpservletrequest, actionerrors);
            httpservletrequest.getSession().setAttribute("action", "add");  
            
            return actionmapping.findForward("errorMail");
    	}
        return actionmapping.findForward("mostrarId");
    }

	
	public ActionForward initmod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		EstablecimientoForm eForm = (EstablecimientoForm)form;
		eForm.reset();
		String idEst = request.getParameter("id");
		eForm.setIdEst(idEst);
		Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(new Long(eForm.getIdEst()));		
		
    	List metodosControl = MetodoControlDAO.findAll();
    	request.setAttribute("metodos", metodosControl);
   	
		request.setAttribute("ubicaciones", estab.getUbicacions()!=null?estab.getUbicacions(): new HashSet());
    	
    	List centrosDeComputo = CentroDeComputoDAO.findAllCentroDeComputo();
    	request.setAttribute("centros", centrosDeComputo);
		
		this.copyProperties(estab,eForm);
		eForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));//seria fecha de modificacion
		request.getSession().setAttribute("action", "update");
		List r =new ArrayList(estab.getBitacora());
    	if(!r.isEmpty())
			Collections.sort(r, new LogPorFecha());  
    	request.setAttribute("bitacora",r);
        return mapping.findForward("success");		
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
			 eForm.setIdPropietario(estab.getPropietario().getId().toString());
			 eForm.setNombreContactoPropietario(estab.getPropietario().getNombreContacto());
		 }
		 if(estab.getActivoEstablecimiento()!=null)
			 eForm.setActivo(estab.getActivoEstablecimiento());
		 if(estab.getActivoEclo()!=null)
			 eForm.setActivoEclo(estab.getActivoEclo());
		 if(estab.getActivoPropietario()!=null)
			 eForm.setActivoPropietario(estab.getActivoPropietario());
		 if(estab.getActivoEstancia()!=null)
			 eForm.setActivoEstancia(estab.getActivoEstancia());
		 if (estab.getMetodoControl() != null)
				eForm.setMetodoControl(estab.getMetodoControl().getCodigo());
		 eForm.setCuig(estab.getCuig());
		 eForm.setCuit(estab.getCuit());
		 eForm.setRenspa(estab.getRenspa());
		 
		 
		 if(estab.getCentroComputo()!=null) {
			 eForm.setIdCentro(estab.getCentroComputo().getId());
		 }
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

    	ActionMessages actionerrors = new ActionMessages();
        
    	EstablecimientoForm eForm = (EstablecimientoForm)actionform;
    	
    	Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(new Long(eForm.getIdEst()));
    	/*03/11/2008 el cliente dijo que no se haga mas este chequeo
    	String cuig = estab.getCuig();
    	if((cuig!=null && !cuig.equals(eForm.getCuig()))||(cuig==null)){
    		Establecimiento es  = EstablecimientoDAO.getEstablecimientoByCuig(eForm.getCuig());
	    	if (es != null && !es.equals(estab)) {
	        	actionerrors.add("uniqueConstraint",new ActionMessage("establecimiento.existe", "Tambo", eForm.getCuig()));
	        	//this.saveErrors(httpservletrequest, actionerrors);
	        	saveMessages(httpservletrequest, actionerrors);
	        	List metodosControl = MetodoControlDAO.findAll();
	        	httpservletrequest.setAttribute("metodos", metodosControl);
	            httpservletrequest.getSession().setAttribute("action", "add");            
	            return actionmapping.findForward("success");
	        }
    	}*/
    	Date fechaI = new Date();
		fechaI= DateUtils.parse(eForm.getFechaLog(),"dd/MM/yyyy");//es de modificacion
		if(fechaI == null || new Date().before(fechaI) ){
			if(fechaI != null)
				actionerrors.add("uniqueConstraint",new ActionMessage("establecimiento.fechaPosterior"));
			else
				actionerrors.add("uniqueConstraint",new ActionMessage("establecimiento.fechaErronea"));
        	saveMessages(httpservletrequest, actionerrors);
        	List metodosControl = MetodoControlDAO.findAll();
        	httpservletrequest.setAttribute("metodos", metodosControl);
            httpservletrequest.getSession().setAttribute("action", "update");  
            List centrosDeComputo = CentroDeComputoDAO.findAllCentroDeComputo();
        	httpservletrequest.setAttribute("centros", centrosDeComputo);
            eForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
            return actionmapping.findForward("success");
		}
		
		Date f = new Date(fechaI.getTime());
    	this.copiarPropiedades(eForm,estab,httpservletrequest,f);
    	EstablecimientoDAO.update(estab);
    	try { 
    		EstablecimientoDAO.sendEMail(estab,"modificado");
    	}catch (EmailException e){
        	actionerrors.add("errorEmail",new ActionMessage("email.noenviadoTambo", estab.getId()));        
        	saveMessages(httpservletrequest, actionerrors);
            httpservletrequest.getSession().setAttribute("action", "update");            
            return actionmapping.findForward("errorMail");
    	}    	
    	

        return actionmapping.findForward("listar");
    }
    /**
     * direcciona a la jsp de busqueda de Estancias
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward initBuscarEstancia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
    	EstablecimientoForm eForm = (EstablecimientoForm)form;
		eForm.setIdEstancia("");
		eForm.setEstancia("");
		request.setAttribute("estancias",new ArrayList());
		request.setAttribute("error","Para el filtro seleccionado no hay Establecimientos");
		return mapping.findForward("initBuscarEstancia");		
	}
    /**
     * Realiza el filtro de Estancias
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward buscarEstancia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
    	
    	
    	Long esta=null;
    	EstablecimientoForm eForm = (EstablecimientoForm)form;
    	try{
			if(!eForm.getIdEstancia().equals(""))
				esta = new Long(eForm.getIdEstancia());
			}
			catch (Exception e) {
				request.setAttribute("error","EL CAMPO IDENTIFICADOR DEBE SER UN NUMERO");
				return mapping.findForward("initBuscarEstancia");			
			}
    	List estancias =  EstanciaDAO.findEstanciasActivas(eForm.getEstancia(),eForm.getIdEstancia());
    	request.setAttribute("estancias",estancias);
		if(estancias.isEmpty())
			request.setAttribute("error","No hay Estancias");
		return mapping.findForward("initBuscarEstancia");		
	}
    /**
     * Setea la estancia seleccionada en el formulario y la direcciona al establecimiento
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
public ActionForward seleccionarEstancia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		String idEstancia = request.getParameter("id");
		EstablecimientoForm eForm = (EstablecimientoForm)form;
		eForm.setIdEstancia(idEstancia);
		request.setAttribute("idEstancia",idEstancia);
		Estancia est = EstanciaDAO.findByPrimaryKey(new Long(idEstancia));
		eForm.setEstancia(est.getNombreContacto());
		eForm.setActivoEstancia(est.getActivo());
		//eForm.setActivoEclo(est.getActivoEclo());
		eForm.setActivoPropietario(est.getActivoPropietario());
		//eForm.setNombreContactoEclo(est.getEclo().getNombreContacto());
		eForm.setIdPropietario(est.getPropietario().getId().toString());
		eForm.setNombreContactoPropietario(est.getPropietario().getNombreContacto());
		
		List metodosControl = MetodoControlDAO.findAll();
    	request.setAttribute("metodos", metodosControl);
    	List centrosDeComputo = CentroDeComputoDAO.findAllCentroDeComputo();
    	request.setAttribute("centros", centrosDeComputo);
    	if(!eForm.getIdEst().equals("")){
	    	Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(Long.parseLong(eForm.getIdEst()));
	    	List r =new ArrayList(estab.getBitacora());
	    	if(!r.isEmpty())
				Collections.sort(r, new LogPorFecha());  
	    	request.setAttribute("bitacora",r);
    	}
		return mapping.findForward("success");		
	}
/**
 * Metodo que incia la pa pantalla para poder buscar una eclo
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward initBuscarEclo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		EstablecimientoForm eForm = (EstablecimientoForm)form;
		eForm.setIdEclo("");
		eForm.setNombreContactoEclo("");
		request.setAttribute("eclo",new ArrayList());
		request.setAttribute("error","No hay Eclos");
		return mapping.findForward("initBuscarEclo");		
	}
	/**
	 * Metodo que buscar eclos segun el filtro indicado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 public ActionForward buscarEclo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	    	
	    	
	    	Long esta=null;
	    	EstablecimientoForm eForm = (EstablecimientoForm)form;
	    	try{
				if(!eForm.getIdEclo().equals(""))
					esta = new Long(eForm.getIdEstancia());
				}
				catch (Exception e) {
					request.setAttribute("error","EL CAMPO IDENTIFICADOR DEBE SER UN NUMERO");
					return mapping.findForward("initBuscarEclo");			
				}
	    	List eclos=  EcloDAO.findEclos(eForm.getNombreContactoEclo(),eForm.getIdEclo());
	    	request.setAttribute("eclos",eclos);
			if(eclos.isEmpty())
				request.setAttribute("error","No existen eclos con el filtro indicado");
			return mapping.findForward("initBuscarEclo");		
		}
	 /**
	  * Metodo que se encarga de seleccionar la eclo y asignarla al form para luego ser cargada en el tambo
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	 public ActionForward seleccionarEclo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
			
			String idEclo = request.getParameter("id");
			EstablecimientoForm eForm = (EstablecimientoForm)form;
			eForm.setIdEclo(idEclo);
			request.setAttribute("idEclo",idEclo);
			Eclo eclo = EcloDAO.findByPrimaryKey(new Long(idEclo));
			eForm.setNombreContactoEclo(eclo.getNombreContacto());
			eForm.setIdEclo(eclo.getId().toString());
			eForm.setActivoEclo(eclo.getActivo());
			List metodosControl = MetodoControlDAO.findAll();
	    	request.setAttribute("metodos", metodosControl);
	    	//eForm.set
	    	List centrosDeComputo = CentroDeComputoDAO.findAllCentroDeComputo();
	    	request.setAttribute("centros", centrosDeComputo);
	    	if(!eForm.getIdEst().equals("")){
		    	Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(Long.parseLong(eForm.getIdEst()));
		    	List r =new ArrayList(estab.getBitacora());
		    	if(!r.isEmpty())
					Collections.sort(r, new LogPorFecha());  
		    	request.setAttribute("bitacora",r);
	    	}
			return mapping.findForward("success");		
		}
/**
 * Copia propiedades
 * @param eForm
 * @param estab
 */
    private void copiarPropiedades(EstablecimientoForm eForm, Establecimiento estab,HttpServletRequest request,Date fechaCambio) {
		estab.setNombreContacto(eForm.getNombre());
		Estancia est = EstanciaDAO.findByPrimaryKey(new Long(eForm.getIdEstancia()));
		estab.setPropietario(est.getPropietario());
		estab.setActivoPropietario(est.getActivoPropietario());
		Eclo eclo = EcloDAO.findByPrimaryKey(new Long(eForm.getIdEclo()));
		estab.setEclo(eclo);
		estab.setActivoEclo(eclo.getActivo());
		if(estab.getAtrVariables()!=null)
			estab.setAtrVariables(estab.getAtrVariables());
		if(estab.getAtrVariablesEstab()!=null)
			estab.setAtrVariablesEstab(estab.getAtrVariablesEstab());
		estab.setCuit(eForm.getCuit().equals("")?null:eForm.getCuit());
		estab.setRenspa(eForm.getRenspa().equals("")?null:eForm.getRenspa());
		estab.setCuig(eForm.getCuig().equals("")?null:eForm.getCuig());
		if((estab.getCentroComputo()==null)||(eForm.getIdCentro()!=estab.getCentroComputo().getId())) {
			//si selecciono otro centro de computo, lo voy a buscar a la BD y se lo asigno
				estab.setCentroComputo(CentroDeComputoDAO.findByPrimaryKey(eForm.getIdCentro()));
			}
		//UPDATE
		if (estab.getId()!=null){
			/*if((!est.equals(estab.getEstancia()))&& !estab.getAnimals().isEmpty()){
				//a todos los animales del tambo ponerle la estancia nueva
				Iterator it = estab.getAnimals().iterator();
				while(it.hasNext()){
					Animal an = (Animal)(it.next());
					an.setEstancia(est);
				}
			}*/
			if((!est.equals(estab.getEstancia()))){
				//a todos los animales del tambo ponerle la estancia nueva
				EstablecimientoDAO.actualizarAnimales(estab, est);
				}
			
			estab.setEstancia(est);
			estab.setActivoEstancia(est.getActivo());
			//tengo que poner el ultimo metodo de control segun el log
			//idem para el estado
			if(this.cambioLog(eForm,fechaCambio,estab)){
				Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER); 
				LogContacto log1 = new LogContacto();
				log1.setFecha(fechaCambio);
				log1.setContacto(estab);
				log1.setUsuario(user);
				log1.setEclo(eclo);
				LogContacto logAnterior = LogContacto.getUltimoLogAccionFecha(estab,fechaCambio);
				MetodoControl metodoLogAnterior = null;
				if(logAnterior!=null){
					LogContacto lMet = LogContacto.getUltimoLogFecha(estab,fechaCambio);
					metodoLogAnterior = (lMet!=null? lMet.getMetodoControlNuevo():null);
					boolean logAccion = logAnterior.getAccion().trim().equals(LogContacto.ACTIVAR)?true:false;
					if(logAccion != eForm.getActivo().booleanValue()){
						//si cambio accion
						if (eForm.getActivo().booleanValue())
							log1.setAccion(LogContacto.ACTIVAR);
						else
							log1.setAccion(LogContacto.DESACTIVAR);
					}
					else{//si no es accion deberia ser metodo de control pero si no cambia el metodo no habria que generarlo
						log1.setAccion(LogContacto.MODIFICAR_METODO_CONTROL);
					}
					}
				else{
					if (eForm.getActivo().booleanValue())
						log1.setAccion(LogContacto.ACTIVAR);
					else
						log1.setAccion(LogContacto.DESACTIVAR);
				}
				log1.setMetodoControl(metodoLogAnterior);
				log1.setMetodoControlNuevo(MetodoControlDAO.findByPrimaryKey(eForm.getMetodoControl()));
				
			
			List r =new ArrayList(estab.getBitacora());
			if(!r.contains(log1)){
				estab.getBitacora().add(log1);
				LogContacto logEstado = LogContacto.getUltimoLogDeEstado(estab);
				estab.setActivoEstablecimiento(logEstado.getAccion().trim().equals(LogContacto.ACTIVAR)?true:false);
				LogContacto lMet = LogContacto.getUltimoLogFecha(estab,new Date());
				estab.setMetodoControl((lMet!=null? ((lMet.getMetodoControlNuevo()!=null)?lMet.getMetodoControlNuevo():estab.getMetodoControl()):estab.getMetodoControl()));
				//estab.setMetodoControl((lMet!=null? lMet.getMetodoControlNuevo():null));
				//estab.setMetodoControl((lMet!=null? lMet.getMetodoControlNuevo():estab.getMetodoControl()));
			}
			}
		}
		//ADD
		else{
			estab.setEstancia(est);
			estab.setActivoEstancia(est.getActivo());
			estab.setAtrVariables(null);
			estab.setAtrVariablesEstab(null);
			estab.setMetodoControl(MetodoControlDAO.findByPrimaryKey(eForm.getMetodoControl()));
			LogContacto log1 = new LogContacto();
			//log.setActivo(eForm.getActivo().booleanValue());
			if (eForm.getActivo().booleanValue()) // pasa a activo
				log1.setAccion(LogContacto.ACTIVAR);
			else
				log1.setAccion(LogContacto.DESACTIVAR);
			//log.setMetodoControl(estab.getMetodoControl());
			log1.setMetodoControl(null);
			log1.setMetodoControlNuevo(estab.getMetodoControl());
			//log.setFecha(new Date());
			log1.setFecha(fechaCambio);
			log1.setContacto(estab);
			log1.setEclo(eclo);
			Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER); 
			log1.setUsuario(user);
			//log.setMetodoControl(estab.getMetodoControl());
			estab.getBitacora().add(log1);
			estab.setActivoEstablecimiento(eForm.getActivo());
		}		
		
		
		
		
	}
    private boolean cambioLog(EstablecimientoForm form, Date fechaCambio,Establecimiento tambo){
    	
    	if((tambo== null)||(!tambo.getActivoEstablecimiento().equals(form.getActivo()))||(!DateUtils.mismoDia(fechaCambio,new Date()))||(this.cambioMetodoDeControl(tambo.getMetodoControl(),form.getMetodoControl())))
    			return true;
    	return false;
    }

	private boolean cambioMetodoDeControl(MetodoControl metodoControl, String metodoControl2) {
		if (metodoControl == null && StringUtils.isEmpty(metodoControl2))
			return false;
		if (metodoControl == null && StringUtils.isNotEmpty(metodoControl2))
			return true;
		// aca metodoControl != null
		if (StringUtils.equals(metodoControl.getCodigo(), metodoControl2))
			return false;
		return true;
		
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
    	EstablecimientoForm eForm = (EstablecimientoForm)form;
    	eForm.setIdEst("");
    	eForm.setNombre("");
		return mapping.findForward("init");
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		EstablecimientoForm f = (EstablecimientoForm) form;
		List listaEstablecimientos = EstablecimientoDAO.findEstablecimientos(null,f.getNombre(), f.getIdEst(),new ArrayList<String>());
		request.setAttribute("listaEstablecimientos", listaEstablecimientos);
		//System.out.println("buscando establecimiento");
		return mapping.findForward("init");
	}
}
