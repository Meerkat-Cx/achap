package ar.org.sicel.web.find;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;



import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.CONF;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Estancia;
import ar.org.sicel.persistence.EstanciaDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAlta;
import ar.org.sicel.persistence.EvtAltaDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AnimalForm;


/**
 * @struts.action 
 * 		path="/buscarAnim"
 * 		input=".datosBuscarAnim"
 * 		name="animalForm"
 * 		scope="request"
 * 		validate="false"
 * 		parameter="method"
 *
 * @struts.action-forward 
 * 		name="successPorId"
 * 		path=".animalDetalle"
 *
 * @struts.action-forward
 * 		name="successPorRPyEstab"
 * 		path=".animsTable"
 *
 * @struts.action-forward
 * 		name="successPorTipoyNumero"
 * 		path=".animsTable"
 *
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="failure" 
 * 		path=".datosBuscarAnim"
 *  
 * @struts.action-forward
 * 		name="edicion" 
 * 		path=".edicionAnimal"  
 */
public final class BuscarAnimAction extends DispatchAction {
	
	public ActionForward initBusqueda(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		
        AnimalForm animalForm = (AnimalForm)form;
		Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
        animalForm.setRol(usuarioActual.getRol().getNombre());
        animalForm.setDelSistema("no");
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		return mapping.findForward("success");		
		
	}

    public ActionForward porId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (isCancelled(request))
            return (mapping.findForward("main"));

        AnimalForm animalForm = (AnimalForm)form;
        Long idAn = null;
        if(animalForm.getAnimalId()==null)
        	idAn = Long.parseLong((String)request.getAttribute("id"));
        else
        	idAn =animalForm.getAnimalId();
        animalForm.setAnimalId(idAn);
        Animal animal = AnimalDAO.findByPrimaryKey(idAn);
        Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
        animalForm.setRol(usuarioActual.getRol().getNombre());
        if(animal != null)
        {
	        animalForm.setSexo(animal.esHembra()?Tokens.HEMBRA:Tokens.MACHO);
	        boolean mostrarDescendencia = true;
	        if (!animal.esHembra())
	        	if (usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR) &&
	        			!animal.getEstablecimiento().getEclo().getId().equals(usuarioActual.getContacto().getId()))
	        		mostrarDescendencia = false;
	        	else if (usuarioActual.getRol().getNombre().equals(Tokens.NOMBREROLPROPIETARIO) &&
	        			!animal.getPropietario().getId().equals(usuarioActual.getContacto().getId()))
	        		mostrarDescendencia = false;
	        animalForm.setMostrarDescendencia(mostrarDescendencia);
	        animalForm.setAnimalId(idAn);	
	        animalForm.setAnimal(animal);
	        animalForm.setEsHembra(animal.esHembra()? true:false);
        }
        if (animal == null){
        	List razas = RazaDAO.findAll();
    		request.setAttribute("razas",razas);    		
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalPorIdNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request, msgs);
			//saveMessages(request.getSession(), msgs);
            return mapping.findForward("success");
        }

        return (mapping.findForward("successPorId"));
    }

    /**
     * Hace la busqueda de animales por RP, id de establecimiento(estancia), año de nacimiento, y rol del usuario
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward porRPyEstab(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {       
        if (isCancelled(request))
            return (mapping.findForward("main"));

        AnimalForm animalForm = (AnimalForm)form;
        Estancia establecimiento = EstanciaDAO.findByPrimaryKey(animalForm.getEstabId());
        //Establecimiento establecimiento = EstablecimientoDAO.findByPrimaryKey(animalForm.getEstabId());
        Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
        animalForm.setRol(usuarioActual.getRol().getNombre());
        if (establecimiento == null ) { // no mando a consultar porque el select es pesado
        	List razas = RazaDAO.findAll();
    		request.setAttribute("razas",razas);    		
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("estancia_no_existe"));
			saveMessages(request, msgs);
            return mapping.findForward("success");
        }

       
        //List animals = AnimalDAO.findAnimalesPorFiltro(animalForm.getRp(), establecimiento, animalForm.getYear(), usuarioActual);
        List animals = AnimalDAO.findAnimalesPorFiltro(animalForm.getRp(), establecimiento, animalForm.getYear(), usuarioActual,animalForm.getDelSistema());

        if (animals.isEmpty()){
        	List razas = RazaDAO.findAll();
    		request.setAttribute("razas",razas);    		
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request, msgs);
            return mapping.findForward("success");
        }

        animalForm.setRol(usuarioActual.getRol().getNombre());
        log.warn("Busqueda de animales por rp y establecimiento ");
        request.setAttribute("animsData", animals);
        request.setAttribute("metodoUri","porRPyEstab");
        return (mapping.findForward("successPorRPyEstab"));
    }

    @SuppressWarnings("unchecked")
	public ActionForward porTipoyNumero(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       //comentado por que no se usa mas el metodo findByRegistry con 2 parametros
        if (isCancelled(request))
            return (mapping.findForward("main"));

        AnimalForm animalForm = (AnimalForm)form;
        
        Usuario usuario = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        animalForm.setRol(usuario.getRol().getNombre());
       
        animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
        // TODO una vez que tengamos la relacion entre el usuario y el contacto vamos a poder terminar correctamente
        // el filtrado de animales
        Long idEclo = usuario.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR) ? usuario.getContacto().getId() : null;
        Long idRegional = usuario.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL) ? usuario.getContacto().getId() : null;
        Animal animal = AnimalDAO.findByRegistryAndUserSinEclo(animalForm.getTipoReg(), animalForm.getNumReg(), animalForm.getRaza(), animalForm.getSexo());
        log.warn("Busqueda de animales por tipo y numero "+( (animal == null)?" no existe ": animal.getRegistroOrigen()));
        //Animal animal = AnimalDAO.findByRegistry(animalForm.getTipoReg(), animalForm.getNumReg(), animalForm.getRaza(), animalForm.getSexo());
        boolean animalOk = false;
        if(animal!=null){
    	   if(idEclo!=null){//si es eclo
    		   if(animalForm.getDelSistema().equals("si") ){//es busqueda todos y es eclo
    			   	animalOk = Configuracion.getValorReglaProceso(CONF.ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL, new Date());
    		   }
    		   else{//si es particular
    			   if(animal.getEstablecimiento()!=null 
    					   && animal.getEstablecimiento().getEclo().getId().equals(idEclo)){
    				   animalOk = true;
    			   }
    			   
    		   }
    		   
    	   }else
    		   if(idRegional!=null){
		    		   if(animalForm.getDelSistema().equals("si") ){//es busqueda todos y es eclo
		   			   	animalOk = Configuracion.getValorReglaProceso(CONF.ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL, new Date());
		    		   }
			   		   else{//si es particular
			   			   if(animal.getEstablecimiento()!=null 
			   					   &&  animal.getEstablecimiento().getEclo()!=null
			   					   && animal.getEstablecimiento().getEclo().getRegional()!=null
			   					   && animal.getEstablecimiento().getEclo().getRegional().getId().equals(idRegional)){
			   				   animalOk = true;
			   			   }
			   			   
			   		   	}
    		   }
    		   else animalOk = true;
       }
        
        
        
       /* if (animal != null && usuario.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR) 
        		&& idEclo != null 
        		&& animalForm.getDelSistema().equals("no") 
        		&& animal.getEstablecimiento()!=null 
        		&& !animal.getEstablecimiento().getEclo().getId().equals(idEclo))
        		//&& !(Configuracion.getValorReglaProceso(CONF.ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL, new Date())))
        	animal = null;
        else 
        	if (animal != null 
        			&& usuario.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL) 
        			&& idRegional != null 
        			&& animalForm.getDelSistema().equals("no") 
        			&& animal.getEstablecimiento()!=null 
        			&& (animal.getEstablecimiento().getEclo().getRegional() == null || !animal.getEstablecimiento().getEclo().getRegional().getId().equals(idRegional)))
        	animal = null;
        if (animal == null 
        			|| ((usuario.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR) 
        					|| usuario.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL) 
        					&& animalForm.getDelSistema().equals("no"))
        					&& animal.getEstablecimiento() == null) 
            		|| (usuario.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR) 
            				&& animal.esHembra() 
            				&& usuario.getContacto() != null 
            				&& animal.getEstablecimiento()!=null 
            				&& !animal.getEstablecimiento().getEclo().getId().equals(usuario.getContacto().getId())
            				&& !animal.getPropietario().getId().equals(usuario.getContacto().getId()))){*/
        if(!animalOk){
        	List razas = RazaDAO.findAll();
    		request.setAttribute("razas",razas);    		
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExistePorRegisro", new ActionMessage("animal_no_existe"));
			saveMessages(request, msgs);
            return mapping.findForward("success");
        }

        List animals = new ArrayList();
        animals.add(animal);
        request.setAttribute("animsData", animals);
        return (mapping.findForward("successPorTipoyNumero"));
    }
       
    public ActionForward porRPyPropiet(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {       
        if (isCancelled(request))
            return (mapping.findForward("main"));

        Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        if (!Tokens.NOMBREROLADMINISTRADOR.equals(usuarioActual.getRol().getNombre()) && !Tokens.NOMBREROLGENERAL.equals(usuarioActual.getRol().getNombre()))
        	return (mapping.findForward("errorAcceso"));

        AnimalForm animalForm = (AnimalForm)form;
        Propietario propietario =null;
        if(StringUtils.isNotEmpty(animalForm.getPropietId())){
        propietario= PropietarioDAO.findByPrimaryKey(new Long(animalForm.getPropietId()));
        
	        if (propietario == null) { // no mando a consultar porque el select es pesado
	        	List razas = RazaDAO.findAll();
	    		request.setAttribute("razas",razas);    		
				ActionMessages msgs = new ActionMessages();
				msgs.add("animalNoExistePorPropietario", new ActionMessage("propietario_no_existe"));
				saveMessages(request, msgs);
				animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
		        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
		        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
		        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
		        animalForm.setRol(usuarioActual.getRol().getNombre());
	            return mapping.findForward("success");
	        }
        }
        Eclo eclo = null;
        if(StringUtils.isNotEmpty(animalForm.getIdEclo())){
        	eclo = EcloDAO.findByPrimaryKey(Long.parseLong(animalForm.getIdEclo()));
        	if(eclo==null){
        		List razas = RazaDAO.findAll();
        		request.setAttribute("razas",razas);    		
    			ActionMessages msgs = new ActionMessages();
    			msgs.add("animalNoExistePorPropietario", new ActionMessage("eclo_no_existe"));
    			saveMessages(request, msgs);
    			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
    	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
    	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
    	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
    	        animalForm.setRol(usuarioActual.getRol().getNombre());
                return mapping.findForward("success");
        	}
        }
        Establecimiento tambo = null;
        if(StringUtils.isNotEmpty(animalForm.getTamboId())){
        	tambo = EstablecimientoDAO.findByPrimaryKey(Long.parseLong(animalForm.getTamboId()));
        	if(tambo==null){
        		List razas = RazaDAO.findAll();
        		request.setAttribute("razas",razas);    		
    			ActionMessages msgs = new ActionMessages();
    			msgs.add("animalNoExiste", new ActionMessage("tambo_no_existe"));
    			saveMessages(request, msgs);
    			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
    	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
    	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
    	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
    	        animalForm.setRol(usuarioActual.getRol().getNombre());
                return mapping.findForward("success");
        	}
        	else{
        		if(eclo!=null && !tambo.getEclo().equals(eclo)){
        			List razas = RazaDAO.findAll();
            		request.setAttribute("razas",razas);    		
        			ActionMessages msgs = new ActionMessages();
        			msgs.add("animalNoExiste", new ActionMessage("tambo_no_pertenece_a_eclo"));
        			saveMessages(request, msgs);
        			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
        	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
        	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
        	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
        	        animalForm.setRol(usuarioActual.getRol().getNombre());
                    return mapping.findForward("success");
        		}
        	}
        }
        List animals = AnimalDAO.findAnimalesPorRpyPropietarioyEclo(animalForm.getRp1(),propietario, eclo,tambo,usuarioActual.getContacto(),animalForm.getDelSistema());

        if (animals.isEmpty()){
        	List razas = RazaDAO.findAll();
    		request.setAttribute("razas",razas);    		
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExistePorPropietario", new ActionMessage("animal_no_existe"));
			saveMessages(request, msgs);
			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
	        animalForm.setRol(usuarioActual.getRol().getNombre());
            return mapping.findForward("success");
        }
        animalForm.setRol(usuarioActual.getRol().getNombre());
        request.setAttribute("animsData", animals);
        request.setAttribute("metodoUri","porRPyPropiet");
        return (mapping.findForward("successPorRPyEstab"));
    }

    public ActionForward porRazaySexo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {       
        if (isCancelled(request))
            return (mapping.findForward("main"));

        Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        if (!Tokens.NOMBREROLPROVEEDOR.equals(usuarioActual.getRol().getNombre()))
        	return (mapping.findForward("errorAcceso"));
        Eclo eclo = EcloDAO.findByPrimaryKey(usuarioActual.getContacto().getId());
        AnimalForm animalForm = (AnimalForm)form;
        Propietario propietario=null;
        if(StringUtils.isNotEmpty(animalForm.getPropietId())){
        	propietario= PropietarioDAO.findByPrimaryKey(new Long(animalForm.getPropietId()));
        	if(propietario ==null){
        		List razas = RazaDAO.findAll();
        		request.setAttribute("razas",razas);    		
    			ActionMessages msgs = new ActionMessages();
    			msgs.add("animalNoExistePorRazaySexo", new ActionMessage("propietario_no_existe"));
    			saveMessages(request, msgs);
    			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
    	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
    	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
    	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
    	        animalForm.setRol(usuarioActual.getRol().getNombre());
    			return mapping.findForward("success");
        	}
        }
        Establecimiento tambo=null;
        if(StringUtils.isNotEmpty(animalForm.getTamboId())){
        	tambo= EstablecimientoDAO.findByPrimaryKey(new Long(animalForm.getTamboId()));
        	if(tambo ==null){
        		List razas = RazaDAO.findAll();
        		request.setAttribute("razas",razas);    		
    			ActionMessages msgs = new ActionMessages();
    			msgs.add("animalNoExistePorRazaySexo", new ActionMessage("tambo_no_existe"));
    			saveMessages(request, msgs);
    			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
    	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
    	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
    	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
    	        animalForm.setRol(usuarioActual.getRol().getNombre());
                return mapping.findForward("success");
        	}
        	else{
        		if(eclo!=null && !tambo.getEclo().getId().equals(eclo.getId())){
        			List razas = RazaDAO.findAll();
            		request.setAttribute("razas",razas);    		
        			ActionMessages msgs = new ActionMessages();
        			msgs.add("animalNoExiste", new ActionMessage("tambo_no_pertenece_a_eclo"));
        			saveMessages(request, msgs);
        			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
        	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
        	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
        	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
        	        animalForm.setRol(usuarioActual.getRol().getNombre());
                    return mapping.findForward("success");
        		}
        	}
        }
        List animals = null;
        animals= AnimalDAO.findByRazaSexoyUser(animalForm.getRazaEclo(), animalForm.getSexoEclo(), usuarioActual,propietario,tambo,animalForm.getDelSistema());

        if (animals.isEmpty()){
        	List razas = RazaDAO.findAll();
    		request.setAttribute("razas",razas);    		
			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExistePorRazaySexo", new ActionMessage("animal_no_existe"));
			saveMessages(request, msgs);
			animalForm.setRolAdmin(Tokens.NOMBREROLADMINISTRADOR);
	        animalForm.setRolProv(Tokens.NOMBREROLPROVEEDOR);
	        animalForm.setRolGeneral(Tokens.NOMBREROLGENERAL);
	        animalForm.setRolRegional(Tokens.NOMBREROLREGIONAL);
	        animalForm.setRol(usuarioActual.getRol().getNombre());
            return mapping.findForward("success");
        }
        request.setAttribute("animsData", animals);
        request.setAttribute("metodoUri","porRazaySexo");
        return (mapping.findForward("successPorRPyEstab"));
    }
  
    public ActionForward initRegistros(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		 
    	AnimalForm animalForm = (AnimalForm)form;
		 System.out.println(animalForm.getAnimal().getRegistroOrigen());
		 System.out.println(animalForm.getNumReg());
		return mapping.findForward("initRegistros");
	}
    
    public ActionForward edicion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        
        if (isCancelled(request))
            return (mapping.findForward("main"));

        AnimalForm animalForm = (AnimalForm)form;
        Animal animal = AnimalDAO.findByPrimaryKey(animalForm.getAnimalId());

        if (animal == null){
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("animal_no_existe"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);
        }

        animalForm.setAnimal(animal);

        return (mapping.findForward("edicion"));
    }
    
    public ActionForward mostrarEventos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	//int cantidadMostrar = 10;
    	
    	String idAnimal = request.getParameter("animalId");
        Animal animal = AnimalDAO.findByPrimaryKey(Long.parseLong(idAnimal));        
        
        request.setAttribute("animal", animal);        
        /*Object[] arregloEventos = animal.getEvtAnimals().toArray();
        int init = (arregloEventos.length-cantidadMostrar<0)?0:arregloEventos.length-cantidadMostrar;
        
        Set rta = new HashSet();
        for (int i = init; i < arregloEventos.length; i++) {
        	rta.add(arregloEventos[i]);
        }*/
        List evts = new ArrayList();
        EvtAlta evt = EvtAltaDAO.findByAnimal(animal.getId()); 
        if (evt != null){
        	Evento eee = EventoDAO.findByPrimaryKey(evt.getId());
        	evts.add(eee);
        	List modificaciones = new ArrayList(eee.getModificaciones());
        	Iterator itm = (Iterator) modificaciones.iterator();
        	while(itm.hasNext()){
        		Evento evv = (Evento) itm.next();
        		Evento eese = EventoDAO.findByPrimaryKey(evv.getId());
        		evts.add(eese);
        		
        		
        	}
        	//evts.add(modificaciones);
        }
        if (!animal.getEvtAnimals().isEmpty())
        	evts.addAll(animal.getEvtAnimals());
        request.setAttribute("evtAnimals", evts);//rta);
        

    	//System.out.println("mostrando los eventos del animal "+aForm.getAnimal().getId());
    	return mapping.findForward("mostrarEventos");
    }
    public ActionForward initBuscarTambo(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		//request.setAttribute("tambo","tambo");
		AnimalForm pForm = (AnimalForm)form;
		pForm.setNombreEstablecimiento("");
		pForm.setTamboId("");
		return mapping.findForward("initBuscarTambo");
	}
   
    public ActionForward buscarTambos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
    	AnimalForm pForm = (AnimalForm)form;
		List tambos = null;
		Eclo ecloAux = null;
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		if( user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
		    	   ecloAux = EcloDAO.findByPrimaryKey(user.getContacto().getId());
		if( ( user.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR)) || ( user.getRol().getNombre().equals(Tokens.NOMBREROLGENERAL)))
				if (StringUtils.isNotEmpty(pForm.getIdEclo()))
						ecloAux = EcloDAO.findByPrimaryKey(Long.parseLong(pForm.getIdEclo()));
		tambos = EstablecimientoDAO.findEstablecimientos(ecloAux, pForm.getNombreEstablecimiento(), pForm.getTamboId(),new ArrayList<String>());
		
		if(tambos.isEmpty()){
				request.setAttribute("error","EL TAMBO NO EXISTE"); 
				//request.setAttribute("tambo","tambo");
				return mapping.findForward("initBuscarTambo");			
			}
		//request.setAttribute("tambo","tambo");
		request.setAttribute("listaTambos", tambos);
		return mapping.findForward("initBuscarTambo");		
	}
    /*public ActionForward seleccionarTambo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		//String idTambo = request.getParameter("id");
		AnimalForm reForm = (AnimalForm)form;
		//reForm.setTamboId(idTambo);
		
		return this.initBusqueda(mapping,reForm,request,response);//mapping.findForward("initAddEspecial");		
	}*/
    public ActionForward initBuscarPropietario(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		//request.setAttribute("tambo","tambo");
		AnimalForm pForm = (AnimalForm)form;
		pForm.setNombreProp("");
		pForm.setPropietId("");
		return mapping.findForward("initBuscarPropietario");
	}
    
    public ActionForward initBuscarEclo(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		//request.setAttribute("tambo","tambo");
		AnimalForm pForm = (AnimalForm)form;
		pForm.setNombreEclo("");
		pForm.setIdEclo("");
		return mapping.findForward("initBuscarEclo");
	}
   
    public ActionForward buscarPropietarios(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
    	AnimalForm pForm = (AnimalForm)form;
		List propietarios = PropietarioDAO.findPropietariosPorIDyNmbre(pForm.getPropietId(),pForm.getNombreProp());
		if(propietarios.isEmpty()){
				request.setAttribute("error","EL PROPIETARIO NO EXISTE"); 
				
				return mapping.findForward("initBuscarPropietario");			
			}
		request.setAttribute("listaPropietarios", propietarios);
		return mapping.findForward("initBuscarPropietario");		
	}
 public ActionForward buscarEclos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
    	AnimalForm pForm = (AnimalForm)form;
		List eclos = EcloDAO.findEclosPorFiltro(pForm.getNombreEclo(),pForm.getIdEclo(),null);
    	
		if(eclos.isEmpty()){
				request.setAttribute("error","LA ECLO NO EXISTE"); 
				
				return mapping.findForward("initBuscarEclo");			
			}
		request.setAttribute("listaEclos", eclos);
		return mapping.findForward("initBuscarEclo");		
	}
    /*public ActionForward seleccionarPropietario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		//String idProp = request.getParameter("id");
		AnimalForm reForm = (AnimalForm)form;
		//reForm.setPropietId(idProp);
		
		return this.initBusqueda(mapping,reForm,request,response);//mapping.findForward("initAddEspecial");		
	}*/





}
