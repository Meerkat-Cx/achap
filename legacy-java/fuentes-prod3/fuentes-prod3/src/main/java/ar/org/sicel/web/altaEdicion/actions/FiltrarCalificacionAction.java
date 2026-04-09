/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Calificacion;
import ar.org.sicel.persistence.CalificacionDAO;
import ar.org.sicel.persistence.Calificador;
import ar.org.sicel.persistence.CalificadorDAO;
import ar.org.sicel.persistence.ComparadorCalificaciones;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Estancia;
import ar.org.sicel.persistence.EstanciaDAO;
import ar.org.sicel.persistence.ModeloCalificacionDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Rol;
import ar.org.sicel.persistence.RolDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.UsuarioDAO;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.FiltroCalificacionForm;
import ar.org.sicel.web.util.Item;

/**
 * @author jdivars
 * Action que tiene como funcionalidad  filtros para:
 * buscar una boleta para edicion 
 * buscar una boleta para calificacion
 * buscar un animal para crear una boleta 
 * @since 20 mar 2007
 *  */
public class FiltrarCalificacionAction extends DispatchAction {
	
	private String filtro = null;

	
	public ActionForward initBuscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
		fc.reset();
		fc.setRol(((Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER)).getRol().getNombre());
		cargarCombos(request);
		
		return mapping.findForward("initBuscar");
	
	}
	
	private void cargarCombos(HttpServletRequest request){
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);

		List calificadores = CalificadorDAO.findAll();
		request.setAttribute("califcadores",calificadores);
		//Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		//if(!user.getNombre().equals(Tokens.NOMBREROLDATAENTRY)){
			Rol rol = RolDAO.findByNombre("DATAENTRY");
			List dataEntries = UsuarioDAO.findByRol(rol);
			request.setAttribute("dataEntries",dataEntries);
		//}
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		List modelos = ModeloCalificacionDAO.findAll(user);
		request.setAttribute("modelos",modelos);
	}
    
	/*private List<Item> armarRangosImpresion(int tam){
		List<Item> lista= new ArrayList<Item>();
		Item item;
		int cant = tam/1000+1;
		int j=0;
		int k=1000;
		for (int i = 0; i < cant; i++) {
			String s="";
			if (k<tam){
				s=String.valueOf(j)+"-"+String.valueOf(k);
				j+=1000;
				k=j+1000;
			}else{
				s=String.valueOf(j)+"-"+String.valueOf(tam);
			}
			item=new Item();
			item.setId(s);
			item.setNombre(s);
			lista.add(item);
		}
		return lista;
	}*/
	private List<Item> armarRangosImpresion(int tam){
	List<Item> lista= new ArrayList<Item>();
	Item item;
	int tamCombo = 900;
	int cant = tam/tamCombo+1;
	int j=0;
	int k=tamCombo;
	for (int i = 0; i < cant; i++) {
		String s="";
		if (k<tam){
			s=String.valueOf(j)+"-"+String.valueOf(k);
			j=(i+1)*tamCombo+1;
			k=j+tamCombo-1;
		}else{
			s=String.valueOf(j)+"-"+String.valueOf(tam-1);
		}
		item=new Item();
		item.setId(s);
		item.setNombre(s);
		lista.add(item);
	}
	return lista;
}
    @SuppressWarnings("unchecked")
	public ActionForward porBoleta(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

      
        if (isCancelled(request))
            return (mapping.findForward("main"));

        FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
      Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
      
      List cali =null;
		if(!user.getRol().getNombre().equals(Tokens.NOMBREROLPROPIETARIO))
			cali= CalificacionDAO.findByBoleta(fc.getNumeroBoleta(),user);
		else{
			Propietario prop = PropietarioDAO.findByPrimaryKey(user.getContacto().getId());
			cali= CalificacionDAO.findByBoletaYProp(fc.getNumeroBoleta(),prop);
		}
        if(cali.isEmpty()){
    		cargarCombos(request);

    		ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExistePorBoleta", new ActionMessage("animal_no_existe_boleta"));
			saveMessages(request, msgs);

            return mapping.findForward("initBuscar");
        }
        
        List califi= new  ArrayList();
        califi.addAll(cali);
        Collections.sort(califi, new ComparadorCalificaciones());
        Collections.reverse(califi);
        request.setAttribute("calif",califi);
        
        //request.getSession().setAttribute("ids",armarParamIds(califi));
        armarParamIds(califi, request);
        request.setAttribute("rol",user.getRol().getNombre());
       // System.out.println("rol--->"+user.getRol().getNombre());
        filtro = "porBoleta";
        return (mapping.findForward("success"));
    }
    
    private void armarParamIds(List calificaciones, HttpServletRequest request){
    	String ids="";
    	Calificacion c;
    	int taman =0;
    	for (int i = 0; i < calificaciones.size(); i++) {
			c= (Calificacion) calificaciones.get(i);
			if((c.getModeloCalificacion().getId()!=1L)&&(c.getModeloCalificacion().getId()!=89L)){
				ids+=c.getId().toString()+",";
				taman++;
			}
		}
    	if(ids.length()>=1)
    		request.getSession().setAttribute("ids", ids.substring(0,ids.length()-1));
    	else 
    		request.getSession().setAttribute("ids",null);
    	request.setAttribute("tam",taman);
    	request.setAttribute("listaValores",armarRangosImpresion(taman));
    	
    }
	/**
     * Hace la busqueda de animales por RP, id de establecimiento, año de nacimiento
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ActionForward porRPyEstab(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {       
        if (isCancelled(request))
            return (mapping.findForward("main"));

        FiltroCalificacionForm fc = (FiltroCalificacionForm)form;

        Establecimiento establecimiento = EstablecimientoDAO.findByPrimaryKey(fc.getEstabId());
        
        if (establecimiento == null) { // no mando a consultar porque el select es pesado
    		cargarCombos(request);
    		
    		ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("tambo_no_existe"));
			saveMessages(request, msgs);

            return mapping.findForward("initBuscar");
        }

        //Usuario usuarioActual = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
       // Animal animal = AnimalDAO.findAnimal(fc.getRp(), establecimiento, fc.getYear());
        Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        //Animal animal =null;
       /* if(!user.getNombre().equals(Tokens.NOMBREROLPROPIETARIO))
        	animal = AnimalDAO.findAnimalRpTamboProp(fc.getRp(), establecimiento, fc.getYear(),(Propietario)user.getContacto());
        else
        	animal = AnimalDAO.findAnimal(fc.getRp(), establecimiento, fc.getYear());*/
        List animals = AnimalDAO.findAnimalesPorFiltro(fc.getRp(), establecimiento, fc.getYear(), user,fc.getSexo());
       // if ((animal==null)||(!animal.esHembra())){
       // if (animal==null){
        if (animals.isEmpty()){
    		cargarCombos(request);

			ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExiste", new ActionMessage("animal_no_existe"));
			saveMessages(request, msgs);

            return mapping.findForward("initBuscar");
        }
        //Set califi = animal.getCalificacions();
       
        List calif= new  ArrayList();
        Iterator it = animals.iterator();
        while(it.hasNext()){
        	calif.addAll(((Animal)it.next()).getCalificacions());
        }
        //calif.addAll(califi);
        Collections.sort(calif, new ComparadorCalificaciones());
        Collections.reverse(calif);
        request.setAttribute("calif",calif);
        //request.setAttribute("listaValores",armarRangosImpresion(calif.size()));
        //request.getSession().setAttribute("ids",armarParamIds(calif));
        armarParamIds(calif, request);
        //Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        request.setAttribute("rol",user.getRol().getNombre());
       // request.setAttribute("animsData", animals);
        filtro = "porRPyEstab";
        return (mapping.findForward("success"));
    }

    @SuppressWarnings("unchecked")
	public ActionForward porTipoyNumero(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

      
        if (isCancelled(request))
            return (mapping.findForward("main"));

        FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
        //Animal animal = AnimalDAO.findByRegistry(fc.getTipoReg(),fc.getNumReg(),fc.getRaza(),"H");
        //Animal animal = AnimalDAO.findByRegistry(fc.getTipoReg(),fc.getNumReg(),fc.getRaza(),fc.getSexo());
       // Animal animal = null;
        Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        /*if(!user.getNombre().equals(Tokens.NOMBREROLPROPIETARIO))
        	animal = AnimalDAO.findByRegistry(fc.getTipoReg(),fc.getNumReg(),fc.getRaza(),fc.getSexo());
        else
        	animal = AnimalDAO.findByRegistryAndUser(fc.getTipoReg(),fc.getNumReg(),fc.getRaza(),fc.getSexo(), user);*/
        Animal animal = AnimalDAO.findByRegistryAndUser(fc.getTipoReg(),fc.getNumReg(),fc.getRaza(),fc.getSexo(), user);
        if (animal == null){
    		cargarCombos(request);
    		
    		ActionMessages msgs = new ActionMessages();
			msgs.add("animalNoExistePorRegisro", new ActionMessage("animal_no_existe"));
			saveMessages(request, msgs);

            return mapping.findForward("initBuscar");
        }
        Set calif = animal.getCalificacions();
        List califi= new  ArrayList();
        califi.addAll(calif);
        Collections.sort(califi, new ComparadorCalificaciones());
        Collections.reverse(califi);
        request.setAttribute("calif",califi);
       // request.setAttribute("listaValores",armarRangosImpresion(califi.size()));
       // request.getSession().setAttribute("ids",armarParamIds(califi));
        armarParamIds(califi, request);
        //Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        request.setAttribute("rol",user.getRol().getNombre());
        filtro = "porTipoyNumero";
        return (mapping.findForward("success"));
    }
	
    @SuppressWarnings("unchecked")
	public ActionForward porFechayEstancia(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      
        if (isCancelled(request))
            return (mapping.findForward("main"));

        FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
        
        Date fecha = null;
        if (!fc.getFechaCalificacion().equals("")){
            fecha = new Date();
    		fecha= this.parse(fc.getFechaCalificacion(),"dd/MM/yyyy");
    		if(fecha == null){
    			cargarCombos(request);

        		ActionMessages msgs = new ActionMessages();
    			msgs.add("fechaErronea", new ActionMessage("fecha_erronea_buscar_calificacion"));
    			saveMessages(request, msgs);

    			return mapping.findForward("initBuscar");
    		}
        }
        
        Estancia estancia = null;
        if (fc.getEstanciaId() != null && !fc.getEstanciaId().equals("")){
        	estancia = EstanciaDAO.findByPrimaryKey(Long.parseLong(fc.getEstanciaId()));
        	if (estancia == null){
    			cargarCombos(request);

        		ActionMessages msgs = new ActionMessages();
    			msgs.add("fechaErronea", new ActionMessage("establecimiento_no_existe"));
    			saveMessages(request, msgs);

    			return mapping.findForward("initBuscar");
        	}
        }
        
        Calificador calificador = null;
        if (fc.getCalificadorId() != null && !fc.getCalificadorId().equals("")){
        	calificador = CalificadorDAO.findByPrimaryKey(new Long(fc.getCalificadorId()));
        }
        
        Usuario dataEntry = null;
        if (fc.getDataEntryId() != null && !fc.getDataEntryId().equals("")){
        	dataEntry = UsuarioDAO.findByPrimaryKey(new Long(fc.getDataEntryId()));
        }
        
        if (fecha == null && estancia == null && calificador == null && dataEntry == null){
			cargarCombos(request);

			ActionMessages msgs = new ActionMessages();
			msgs.add("fechaErronea", new ActionMessage("al_menos_un_parametro"));
			saveMessages(request, msgs);

			return mapping.findForward("initBuscar");
        }
        Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        List calif = CalificacionDAO.findByFechaEstancia(fecha, estancia, calificador, dataEntry,user);
        List califi= new  ArrayList();
        califi.addAll(calif);
        Collections.sort(califi, new ComparadorCalificaciones());
        Collections.reverse(califi);
        request.setAttribute("calif",califi);
        //request.setAttribute("listaValores",armarRangosImpresion(califi.size()));
        //request.getSession().setAttribute("ids",armarParamIds(califi));
        armarParamIds(califi, request);
        //Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        request.setAttribute("rol",user.getRol().getNombre());
        filtro = "porFechayEstancia";
        return (mapping.findForward("success"));
    }
    
    public ActionForward initBuscarEstancia(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request,HttpServletResponse response)throws Exception{
        FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
		fc.setEstanciaNombre("");
		//fc.setEstanciaId(null);
		return mapping.findForward("initBuscarEstancia");
	}
	public ActionForward buscarEstancia(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
		String estid = null;
		
		List estancias = EstanciaDAO.findEstancias(fc.getEstanciaNombre(), fc.getEstanciaId());
		
		if(estancias.isEmpty()){
			request.setAttribute("error","EL ESTABLECIMIENTO NO EXISTE");
			return mapping.findForward("initBuscarEstancia");	
		}
		request.setAttribute("estancias",estancias);
		return mapping.findForward("initBuscarEstancia");		
	}
	public ActionForward seleccionarEstancia(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)throws Exception {	
		String idEstancia = request.getParameter("id");
		FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
		fc.setEstanciaId(idEstancia);
		cargarCombos(request);
		return mapping.findForward("initBuscar");
	}

	private Date parse(String s, String s1){
		try{
	        SimpleDateFormat simpledateformat;
	        (simpledateformat = new SimpleDateFormat(s1 != null ? s1 : "dd-MM-yyyy HH:mm:ss")).setLenient(false);
	        return simpledateformat.parse(s);
	    }catch(Exception exception){
	        return null;
	    }
	}
	
    @SuppressWarnings("unchecked")
	public ActionForward porFiltro(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	if (filtro != null){
    		if(filtro.equals("porBoleta")){
    			return porBoleta(mapping, form, request, response);
    		}else if(filtro.equals("porRPyEstab")){
    			return porRPyEstab(mapping, form, request, response);
    		}else if(filtro.equals("porTipoyNumero")){
    			return porTipoyNumero(mapping, form, request, response);
    		}else if(filtro.equals("porFechayEstancia")){
    			return porFechayEstancia(mapping, form, request, response);
    		}else if(filtro.equals("entreDosFechasYModelo"))
    			return entreDosFechasYModelo(mapping, form, request, response);
    	}
    	return (mapping.findForward("success"));
    }
    
    public ActionForward entreDosFechasYModelo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	if (isCancelled(request))
            return (mapping.findForward("main"));
    	FiltroCalificacionForm fc = (FiltroCalificacionForm)form;
    	if (fc.getFechaDesde() == null || fc.getFechaDesde().equals("")){
			cargarCombos(request);
			ActionMessages msgs = new ActionMessages();
			msgs.add("errorFiltroSRA", new ActionMessage("fecha_desde_ausente"));
			saveMessages(request, msgs);
			return mapping.findForward("initBuscar");
        }
    	if (fc.getFechaHasta() == null || fc.getFechaHasta().equals("")){
			cargarCombos(request);
			ActionMessages msgs = new ActionMessages();
			msgs.add("errorFiltroSRA", new ActionMessage("fecha_hasta_ausente"));
			saveMessages(request, msgs);
			return mapping.findForward("initBuscar");
        }
    	if (fc.getModelo() == null || fc.getModelo().equals("")){
			cargarCombos(request);
			ActionMessages msgs = new ActionMessages();
			msgs.add("errorFiltroSRA", new ActionMessage("modelo_ausente"));
			saveMessages(request, msgs);
			return mapping.findForward("initBuscar");
        }
    	Date fechaDesde = parse(fc.getFechaDesde(),"dd/MM/yyyy");
    	Date fechaHasta = parse(fc.getFechaHasta(),"dd/MM/yyyy");
    	if (fechaDesde != null && fechaHasta != null && fechaDesde.after(fechaHasta)){
			cargarCombos(request);
			ActionMessages msgs = new ActionMessages();
			msgs.add("errorFiltroSRA", new ActionMessage("fecha_desde_mayor_fecha_hasta"));
			saveMessages(request, msgs);
			return mapping.findForward("initBuscar");
        }
    	List calif = CalificacionDAO.entreFechasYModelo(fechaDesde,fechaHasta,fc.getModelo());
        List califi= new  ArrayList();
        califi.addAll(calif);
        Collections.sort(califi, new ComparadorCalificaciones());
        Collections.reverse(califi);
        request.setAttribute("calif",califi);
        armarParamIds(califi,request);
        filtro = "entreDosFechasYModelo";
        Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
        request.setAttribute("rol",user.getRol().getNombre());
    	return (mapping.findForward("success"));
    }
}
