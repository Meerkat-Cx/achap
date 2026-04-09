/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.CentroDeComputoDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Estancia;
import ar.org.sicel.persistence.EstanciaDAO;
import ar.org.sicel.persistence.EventosPorFechaYTipo;
import ar.org.sicel.persistence.LogContacto;
import ar.org.sicel.persistence.LogContactoDAO;
import ar.org.sicel.persistence.LogPorFecha;
import ar.org.sicel.persistence.MetodoControl;
import ar.org.sicel.persistence.MetodoControlDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.PropietarioExpd;
import ar.org.sicel.persistence.PropietarioExpdDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PropietarioForm;

/**
 * @author jdivars
 *
 */
public class EdicionPropietarioAction  extends DispatchAction {
	
	
	public ActionForward listar(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	throws Exception {		
		try {
			PropietarioForm eForm = (PropietarioForm)actionform;
			eForm.reset();
			List listPropietarios = PropietarioDAO.findAll();			
			httpservletrequest.setAttribute("listaPropietarios", listPropietarios);
			
		} catch (HibernateException he){
			log.error("La lista de Propietarios no puede ser recuperada",he);
			ActionMessages actionMessages = new ActionMessages();
			actionMessages.add("messageGlobalError",new ActionMessage("messageGlobalError"));
			actionMessages.add("exceptionMessage",new ActionMessage("exceptionMessage","No se pudo recuperar la lista de propietarios desde la base de datos. Verifique la conexión a la misma."));
			saveMessages(httpservletrequest,actionMessages);
			return actionmapping.findForward("error");
		}
		return actionmapping.findForward("list");
	}
	/**
	 * Prepara los valores para cargar la pantalla de alta para
	 * Propietario
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
		PropietarioForm eForm = (PropietarioForm)actionform;
		eForm.reset();
		
		eForm.setActionBack("add");
		eForm.setPropietarioSRA(null);
    	return actionmapping.findForward("success");
	}
	
	public ActionForward initAgregarNroSRA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// TODO blanquear los campos de propietarioForm numero, estab cuig
		PropietarioForm pForm = (PropietarioForm) form;
		pForm.setNroExpd("");
		pForm.setEstab("");
		pForm.setCuig("");
		pForm.setRaza("");
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		return mapping.findForward("agregarNroSRA");
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
		ActionMessages actionerrors = new ActionMessages();
		PropietarioForm eForm = (PropietarioForm)actionform;
		Propietario prop = new Propietario();
		Date fechaI = new Date();
		fechaI= DateUtils.parse(eForm.getFechaLog(),"dd/MM/yyyy");
		if(fechaI == null || new Date().before(fechaI) ){
			if(fechaI != null)
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaPosterior"));
			else
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaErronea"));
        	saveMessages(httpservletrequest, actionerrors);
        	
            //httpservletrequest.getSession().setAttribute("action", "add");  
            eForm.setActionBack("add");
            eForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
            return actionmapping.findForward("success");
		}
		Date f = new Date(fechaI.getTime());
		this.copiarPropiedades(eForm,prop,httpservletrequest,f);
		//prop.setPrefijo(null);
		if(PropietarioDAO.getPropietarioByPrefijo(eForm.getPrefijo())!=null){
			actionerrors.add("uniqueConstraint",new ActionMessage("propietario.existe.prefijo", eForm.getPrefijo()));
			//this.saveErrors(httpservletrequest, actionerrors);
			saveMessages(httpservletrequest, actionerrors);
			eForm.setActionBack("add");
			return this.volver(actionmapping,eForm,httpservletrequest,httpservletresponse);
		}
		
		PropietarioDAO.save(prop);
		
		httpservletrequest.setAttribute("nuevoid",prop.getId());
		httpservletrequest.setAttribute("tipo","El Propietario");
		HibernateFactory.getSession().flush();
		/*try { 
			PropietarioDAO.sendEMail(prop,"nuevo");
		}catch (EmailException e){
			actionerrors.add("errorEmail",new ActionMessage("email.noenviadoPropietario", prop.getId()));        
			saveMessages(httpservletrequest, actionerrors);
			// httpservletrequest.getSession().setAttribute("action", "init");            
			return actionmapping.findForward("errorMail");
		}  */ 	
		eForm.reset();
		return actionmapping.findForward("mostrarId");
	}
	
	
	public ActionForward initmod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PropietarioForm eForm = (PropietarioForm)form;
		String oldIdProp = eForm.getIdProp();
		
		eForm.reset();
		String idProp = request.getParameter("id");
		if (StringUtils.isEmpty(idProp)) // viene de la paginacion
			eForm.setIdProp(oldIdProp);
		else
			eForm.setIdProp(idProp);
		Propietario prop = PropietarioDAO.findByPrimaryKey(new Long(eForm.getIdProp()));
		this.copyProperties(prop,eForm);
		
		request.setAttribute("expds", prop.getExpds());
		request.setAttribute("ubicaciones", prop.getUbicacions()!=null?prop.getUbicacions(): new HashSet());
		
		eForm.setActionBack("update");
		
		return mapping.findForward("success");		
	}
	
	/**
	 * Copia los datos de la entidad Propietario al FORM
	 * @param prop
	 * @param pForm
	 */
	private void copyProperties(Propietario prop,PropietarioForm pForm){		
		pForm.setIdProp(prop.getId().toString());		
		pForm.setNombre(prop.getNombreContacto());
		if (prop.getCuig()!=null)
			pForm.setCuig(prop.getCuig());
		if (prop.getCuit()!=null)
			pForm.setCuit(prop.getCuit());
		if (prop.getRenspa()!=null)
			pForm.setRenspa(prop.getRenspa());
		if(prop.getSocio()!=null)
			pForm.setSocio(prop.getSocio().toString());
		if(prop.getHar()!=null)
			pForm.setHar(prop.getHar().toString());
		if(prop.getS1Eclo()!=null)
			pForm.setS1Eclo(prop.getS1Eclo().toString());
		if(prop.getS1Prop()!=null)
			pForm.setS1Prop(prop.getS1Prop().toString());
		if(prop.getEsPersonaFisica()!=null)
			pForm.setEsPersonaFisica(prop.getEsPersonaFisica());
		if(prop.getActivo()!=null)
			pForm.setActivo(prop.getActivo());
		if (StringUtils.isNotEmpty(prop.getPrefijo())) {
			pForm.setPrefijo(prop.getPrefijo());
		}
		if (StringUtils.isNotEmpty(prop.getEmail())) 
			pForm.setMail(prop.getEmail());
		pForm.setExpds(prop.getExpds());
		//pForm.setUbicacions(prop.getUbicacions());

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
		PropietarioForm eForm = (PropietarioForm)actionform;
		Propietario prop = PropietarioDAO.findByPrimaryKey(new Long(eForm.getIdProp()));
		ActionMessages actionerrors = new ActionMessages();
		String prefijo = prop.getPrefijo();
		
		Date fechaI = new Date();
		fechaI= DateUtils.parse(eForm.getFechaLog(),"dd/MM/yyyy");
		if(fechaI == null || new Date().before(fechaI) ){
			if(fechaI != null)
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaPosterior"));
			else
				actionerrors.add("uniqueConstraint",new ActionMessage("entidad.fechaErronea"));
        	saveMessages(httpservletrequest, actionerrors);
        	
            //httpservletrequest.getSession().setAttribute("action", "add");  
            eForm.setActionBack("update");
            eForm.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
            return actionmapping.findForward("success");
		}
		Date f = new Date(fechaI.getTime());
		this.copiarPropiedades(eForm,prop,httpservletrequest,f);
		
		
		
		//this.copiarPropiedades(eForm,prop,httpservletrequest);
		prop.setPrefijo(prefijo);
		if((eForm.getPrefijo()!=null) && (prop.getPrefijo()!=null) && (!eForm.getPrefijo().equals(prop.getPrefijo()))&&(eForm.getPrefijo().equals(""))){
			actionerrors.add("uniqueConstraint",new ActionMessage("propietario.prefijo.blanco"));
			saveMessages(httpservletrequest, actionerrors);
			return this.volver(actionmapping,eForm,httpservletrequest,httpservletresponse);
		}
		
		if((eForm.getPrefijo()!=null) && ((prop.getPrefijo()!=null) && (!eForm.getPrefijo().equals(prop.getPrefijo()))||(prop.getPrefijo()==null))&&
				(PropietarioDAO.getPropietarioByPrefijo(eForm.getPrefijo())!=null)){
			actionerrors.add("uniqueConstraint",new ActionMessage("propietario.existe.prefijo", eForm.getPrefijo()));
			saveMessages(httpservletrequest, actionerrors);
			 eForm.setActionBack("update");
			return this.volver(actionmapping,eForm,httpservletrequest,httpservletresponse);
		}
		
		prop.setPrefijo(eForm.getPrefijo().equals("")?null:eForm.getPrefijo());
		PropietarioDAO.update(prop); // acutalizo el propietario
		//HibernateFactory.getSession().flush();
		/*try { 
			PropietarioDAO.sendEMail(prop,"modificado");
		}catch (EmailException e){
			actionerrors.add("errorEmail",new ActionMessage("email.noenviadoPropietario", prop.getId()));        
			saveMessages(httpservletrequest, actionerrors);
			// httpservletrequest.getSession().setAttribute("action", "init");            
			return actionmapping.findForward("errorMail");
		}*/
		eForm.reset();
		return actionmapping.findForward("init");
	}
	
	/**
	 * Copia propiedades del form a la entidad Propietario y borra los expd que fueron eliminados
	 * @param eForm
	 * @param estab
	 */
	private void copiarPropiedades(PropietarioForm eForm, Propietario prop,HttpServletRequest httpservletrequest,Date fechaLog) {		
		
		prop.setNombreContacto(eForm.getNombre());
		prop.setSocio(eForm.getSocio().equals("")?null:new Integer(eForm.getSocio()));
		prop.setHar(eForm.getHar().equals("")?null:new Integer(eForm.getHar()));
		prop.setCuig(eForm.getCuig().equals("")?null:eForm.getCuig());
		prop.setCuit(eForm.getCuit().equals("")?null:eForm.getCuit());
		prop.setRenspa(eForm.getRenspa().equals("")?null:eForm.getRenspa());
		prop.setEsPersonaFisica(eForm.getEsPersonaFisica());    	
		prop.setPrefijo(eForm.getPrefijo().equals("")?null:eForm.getPrefijo());
		prop.setEmail(eForm.getMail().equals("")?null:eForm.getMail());
		if(prop.getExpds()==null)
			prop.setExpds(new HashSet());
		Set res = new HashSet();
		Set borrar = new HashSet();
		Iterator ite = prop.getExpds().iterator();
		while(ite.hasNext()){
			PropietarioExpd pr1 = (PropietarioExpd)ite.next();
			if(!eForm.getExpds().contains(pr1))
				borrar.add(pr1);
			else{
				Iterator ite2 = eForm.getExpds().iterator();
				while(ite2.hasNext()){
					PropietarioExpd pr2 = (PropietarioExpd)ite2.next();
					if(pr1.equals(pr2)){
						pr1.setCuig(pr2.getCuig());
						pr1.setEstab(pr2.getEstab());
						pr1.setFechaAlta(pr2.getFechaAlta());
						pr1.setFechaBaja(pr2.getFechaBaja());
						res.add(pr1);
					}
				}
			}
		}
		if(!borrar.isEmpty()){
			Iterator ite2 = borrar.iterator();
			while(ite2.hasNext()){
				PropietarioExpd p = (PropietarioExpd)ite2.next();
				prop.getExpds().remove(p);
				if(p.getId()!=null)
					PropietarioExpdDAO.remove(p);
			}
		}
		if(!eForm.getExpds().isEmpty()){
			Iterator de = eForm.getExpds().iterator();
			while(de.hasNext()){
				PropietarioExpd p = (PropietarioExpd)de.next();
				if(!prop.getExpds().contains(p)){
					prop.getExpds().add(p);
					p.setPropietario(prop);
				}
			}
		}
		if(!res.isEmpty()){
			Iterator rd = res.iterator();
			while(rd.hasNext()){
				PropietarioExpd f = (PropietarioExpd)rd.next();
				prop.getExpds().remove(f);
				prop.getExpds().add(f);
			}
		}
		
		//if((eForm.getActionBack().equals("add"))||(!eForm.getActivo().equals(prop.getActivo()))){
			
			LogContacto log = new LogContacto();
			//log.setActivo(eForm.getActivo().booleanValue());
			if (eForm.getActivo().booleanValue())
				log.setAccion(LogContacto.ACTIVAR);
			else
				log.setAccion(LogContacto.DESACTIVAR);
			//log.setFecha(new Date());
			log.setFecha(fechaLog);
			log.setContacto(prop);
			Usuario user = (Usuario) httpservletrequest.getSession().getAttribute(Tokens.CURRENTUSER); 
			log.setUsuario(user);
			List r =new ArrayList(prop.getBitacora());
			if(!r.contains(log)){
				prop.getBitacora().add(log);
				//r =new ArrayList(prop.getBitacora());
				//prop.setActivo(eForm.getActivo().booleanValue());		
				//prop.getBitacora().size()
				//List bita = new ArrayList(prop.getBitacora());
				/*LogContacto logBaja = null;
				Collections.sort(r, new LogPorFecha());   
				if(!r.isEmpty())
						 logBaja =(LogContacto) r.get(r.size()-1);
			    //prop.setActivo(eForm.getActivo().booleanValue());
			    prop.setActivo(logBaja.getAccion().trim().equals(LogContacto.ACTIVAR)?true:false);*/
				LogContacto logEstado = LogContacto.getUltimoLogDeEstado(prop);
				prop.setActivo(logEstado.getAccion().trim().equals(LogContacto.ACTIVAR)?true:false);
				Iterator it2 = prop.getUsuarios().iterator();
				while(it2.hasNext()){
					Usuario us = (Usuario)it2.next();
					us.setActivo(prop.getActivo());
				}
				
				Set estancias = prop.getEstancias();
				if(estancias!=null){
				Iterator it3 = estancias.iterator();
				while(it3.hasNext()){
					Estancia esta = (Estancia)it3.next();
					if(esta.getEstablecimientos()!=null){
						Iterator it = esta.getEstablecimientos().iterator();
						while(it.hasNext()){
							Establecimiento es = (Establecimiento)it.next();
							es.setActivoPropietario(prop.getActivo());
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
						esta.setActivoPropietario(prop.getActivo());
						if(log.getAccion().trim().equals(LogContacto.DESACTIVAR)){
							List r2 =new ArrayList(esta.getBitacora());
							LogContacto log3 = LogContactoDAO.create(log.getAccion(),log.getCentro(),esta,log.getFecha(),log.getMetodoControl(),log.getUsuario());
							//log3.setContacto(esta);
							if(!r2.contains(log3)){
								esta.getBitacora().add(log3);
								LogContacto logEstadoEst = LogContacto.getUltimoLogDeEstado(esta);
								esta.setActivo(logEstadoEst.getAccion().trim().equals(LogContacto.ACTIVAR)?true:false);
							}
						}
					}
				}
			}
				
		}
		//}
	}
	
	/**
	 * metodo que recupera todos los SRA del propietario para luego elegir alguno para darlo de baja
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	public ActionForward initEliminarNroSRA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		PropietarioForm pForm = (PropietarioForm) form;
		Propietario prop = null;
		if(StringUtils.isNotEmpty(pForm.getIdProp())){
			prop = PropietarioDAO.findByPrimaryKey(new Long(pForm.getIdProp()));	
			request.setAttribute("ubicaciones", prop.getUbicacions()!=null?prop.getUbicacions(): new HashSet());
		}
		request.setAttribute("expds",pForm.getExpds());    	
		return mapping.findForward("initEliminarSra");
	}
	
	/**
	 * Metodo donde se recupera el numero y la raza del expd que se va a eliminar,
	 * es quitado de el set de expd del form y se vuelve a la misma pantalla para seguir eliminando
	 */
	public ActionForward eliminarNroSRA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		PropietarioForm pForm = (PropietarioForm) form;
		String nume = request.getParameter("numero");
		String raz = request.getParameter("raza");
		Long numeroExpd = Long.parseLong(nume);
		Raza raza = RazaDAO.findByPrimaryKey(raz);
		PropietarioExpd paraBorrar = new PropietarioExpd();
		paraBorrar.setNumero(numeroExpd);
		paraBorrar.setRaza(raza);
		PropietarioExpd paraBorrar2 = null;
		
		Iterator it = pForm.getExpds().iterator();
		while(it.hasNext()){
			PropietarioExpd pe = (PropietarioExpd)it.next();
			if(pe.equals(paraBorrar))
				paraBorrar2=pe;
		}
		
		pForm.getExpds().remove(paraBorrar2);
		Propietario prop = null;
		if(StringUtils.isNotEmpty(pForm.getIdProp())){
			prop = PropietarioDAO.findByPrimaryKey(new Long(pForm.getIdProp()));	
			request.setAttribute("ubicaciones", prop.getUbicacions()!=null?prop.getUbicacions(): new HashSet());
		}
		request.setAttribute("expds",pForm.getExpds());
		return mapping.findForward("initEliminarSra");
	}
	
	public ActionForward initCambiarEstadoSRA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		PropietarioForm pForm = (PropietarioForm) form;
		Propietario prop = null;
		if(StringUtils.isNotEmpty(pForm.getIdProp())){
			prop = PropietarioDAO.findByPrimaryKey(new Long(pForm.getIdProp()));	
			request.setAttribute("ubicaciones", prop.getUbicacions()!=null?prop.getUbicacions(): new HashSet());
		}
		request.setAttribute("expds",pForm.getExpds());
		return mapping.findForward("initCambiarEstadoSra");
	}
	
	public ActionForward cambiarEstadoSRA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		PropietarioForm pForm = (PropietarioForm) form;
		String nume = request.getParameter("numero");
		String raz = request.getParameter("raza");
		
		Long numeroExpd = Long.parseLong(nume);
		Raza raza = RazaDAO.findByPrimaryKey(raz);
		
		PropietarioExpd paraModificar= new PropietarioExpd();
		paraModificar.setNumero(numeroExpd);
		paraModificar.setRaza(raza);
		
		PropietarioExpd aux = null;
		Iterator it = pForm.getExpds().iterator();
		boolean estadoActual=false;
		while(it.hasNext()){
			PropietarioExpd pe = (PropietarioExpd)it.next();
			if(pe.equals(paraModificar)){
				estadoActual=!(pe.getFechaBaja()==null);
				aux=pe;
			}
		}
		pForm.getExpds().remove(aux);
		if (!estadoActual)
			aux.setFechaBaja(new Date());
		else{
			aux.setFechaAlta(new Date());
			aux.setFechaBaja(null);
		}
		pForm.getExpds().add(aux);
		
		Propietario prop = null;
		if(StringUtils.isNotEmpty(pForm.getIdProp())){
			prop = PropietarioDAO.findByPrimaryKey(new Long(pForm.getIdProp()));	
			request.setAttribute("ubicaciones", prop.getUbicacions()!=null?prop.getUbicacions(): new HashSet());
		}
		request.setAttribute("expds",pForm.getExpds());
		
		return mapping.findForward("initCambiarEstadoSra");
	}
	
	/**
	 * metodo que se invoca cuando se quiere agregar un nuevo expd a un propietario,
	 * en caso de que el expd coincida con alguno que ya tiene el propietario entonces se modificara la fecha de baja
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward agregarSRA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PropietarioForm pForm = (PropietarioForm) form;
		//Lugares de contacto
		Set expds = new HashSet();
		TreeSet aux = new TreeSet(new Comparator() {
			public int compare(Object o1, Object o2) {
				PropietarioExpd prop1 = (PropietarioExpd) o1;
				PropietarioExpd prop2 = (PropietarioExpd) o2;
				return prop1.compareTo(prop2);					
			}});
		
		
		
		Propietario prop =null;
		if (StringUtils.isNotEmpty(pForm.getIdProp())) {
			prop = PropietarioDAO.findByPrimaryKey(Long.parseLong(pForm.getIdProp()));    
			request.setAttribute("ubicaciones", prop.getUbicacions()!=null?prop.getUbicacions(): new HashSet());
		}
		if (isCancelled(request)) {
			if (StringUtils.isNotEmpty(pForm.getIdProp())) {
				if(!pForm.getExpds().isEmpty()){
					Iterator it = pForm.getExpds().iterator();
					while(it.hasNext()){
						PropietarioExpd p = (PropietarioExpd)it.next();
						expds.add(p);
						aux.add(p);
					}
				}
				/*if(expdNew!=null){
					aux.add(expdNew);
					expds.add(expdNew);
				}*/
				pForm.setExpds(new HashSet());
				pForm.setExpds(expds);
				request.setAttribute("expds", aux);    		
				//request.setAttribute("expds", prop.getExpds());
			}
			return (mapping.findForward("success"));
		}
		
		Long numeroExpd = Long.parseLong(pForm.getNroExpd());
		Raza raza = RazaDAO.findByPrimaryKey(pForm.getRaza());
		PropietarioExpd expdOld = PropietarioExpdDAO.findByNumeroyRaza(numeroExpd,raza);
		boolean actualizarExpd =false;
		if (expdOld != null){
			if(prop==null || prop.getExpds()==null ||!prop.equals(expdOld.getPropietario())|| prop.getExpds().contains(expdOld)){		    	
				ActionMessages actionerrors = new ActionMessages();
				actionerrors.add("expdExistente",new ActionMessage("expd.existente"));        
				saveMessages(request, actionerrors);                        
				List razas = RazaDAO.findAll();
				request.setAttribute("razas",razas);
				return mapping.findForward("agregarNroSRA");    		
			}
			else
				actualizarExpd = true;
		}
		PropietarioExpd expdNew = null;
		if(!actualizarExpd){
			if (StringUtils.isNotEmpty(pForm.getNroExpd())) {
				Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
				expdNew = PropietarioExpdDAO.createPropietarioExpd(Long.parseLong(pForm.getNroExpd()), pForm.getEstab(), pForm.getCuig(),user,raza);
				//pForm.setPropietarioSRA(expdNew);
			}
		}
		/*Set expds = new HashSet();
		TreeSet aux = new TreeSet(new Comparator() {
			public int compare(Object o1, Object o2) {
				PropietarioExpd prop1 = (PropietarioExpd) o1;
				PropietarioExpd prop2 = (PropietarioExpd) o2;
				return prop1.compareTo(prop2);					
			}}); // clon*/
		/*aux.addAll(pForm.getExpds());
		if (aux.size() > 0) { 			
			Iterator it = aux.iterator();
			while(it.hasNext()){
				PropietarioExpd p = (PropietarioExpd)it.next();
				expds.add(p);
			}
		}*/
		if(!pForm.getExpds().isEmpty()){
			Iterator it = pForm.getExpds().iterator();
			while(it.hasNext()){
				PropietarioExpd p = (PropietarioExpd)it.next();
				expds.add(p);
				aux.add(p);
			}
		}
		if(expdNew!=null){
			aux.add(expdNew);
			expds.add(expdNew);
		}
		pForm.setExpds(new HashSet());
		pForm.setExpds(expds);
		request.setAttribute("expds", aux);    		
		
		
		return mapping.findForward("success");
	}
	
	/**
	 * Desde la pantalla donde se eliminan los expd al presionar el boton volver se invoca
	 * a este metodo el cual recarga los expd del propietario en la estructura TreeSet
	 */
	@SuppressWarnings("unchecked")
	public ActionForward volver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		PropietarioForm pForm = (PropietarioForm) form;
		TreeSet aux = new TreeSet(new Comparator() {
			public int compare(Object o1, Object o2) {
				PropietarioExpd prop1 = (PropietarioExpd) o1;
				PropietarioExpd prop2 = (PropietarioExpd) o2;
				return prop1.compareTo(prop2);					
			}}); // clon
		
		Propietario prop = null;
		if(StringUtils.isNotEmpty(pForm.getIdProp())){
			prop = PropietarioDAO.findByPrimaryKey(new Long(pForm.getIdProp()));	
			request.setAttribute("ubicaciones", prop.getUbicacions()!=null?prop.getUbicacions(): new HashSet());
		}
		request.setAttribute("expds",pForm.getExpds());
		return mapping.findForward("success");
	}
	
	public ActionForward mostrarEstablecimientos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String propietarioId  = request.getParameter("propietarioId"); 
		if (StringUtils.isNotEmpty(propietarioId)) {
			Propietario prop = PropietarioDAO.findByPrimaryKey(Long.parseLong(propietarioId));
			List estabs = EstablecimientoDAO.findByIdPropietariosOrderByIdEstab(prop.getId());
			request.setAttribute("listaEstablecimientos", estabs); // son los tambos
		}
		return mapping.findForward("mostrarEstablecimientos");
	}
	
	public ActionForward mostrarEstancias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String propietarioId  = request.getParameter("propietarioId");    	
		if (StringUtils.isNotEmpty(propietarioId)) {
			Propietario prop = PropietarioDAO.findByPrimaryKey(Long.parseLong(propietarioId));
			List estancias = EstanciaDAO.findByIdPropietariosOrderByIdEstancia(prop.getId());
			request.setAttribute("listaEstancias", estancias); // son los establecimientos
		}
		return mapping.findForward("mostrarEstancias");
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PropietarioForm eForm = (PropietarioForm)form;
		//eForm.reset(mapping,request);
		eForm.setIdProp("");
		eForm.setNombre("");
		return mapping.findForward("init");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		PropietarioForm f = (PropietarioForm) form;
		List listaPropietarios = PropietarioDAO.findPropietariosPorIDyNmbre(f.getIdProp(), f.getNombre());
		request.setAttribute("listaPropietarios", listaPropietarios);
		return mapping.findForward("init");
	}
	
}
