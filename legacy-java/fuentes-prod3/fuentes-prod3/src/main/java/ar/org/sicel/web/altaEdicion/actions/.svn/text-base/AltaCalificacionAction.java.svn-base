/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Boleta;
import ar.org.sicel.persistence.Calificacion;
import ar.org.sicel.persistence.CalificacionDAO;
import ar.org.sicel.persistence.Calificador;
import ar.org.sicel.persistence.CalificadorDAO;
import ar.org.sicel.persistence.CaracteristicaCalificacion;
import ar.org.sicel.persistence.CatPuntajeValor;
import ar.org.sicel.persistence.DefectoCalificacion;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventosPorFechaYTipo;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ModeloCalificacionDAO;
import ar.org.sicel.persistence.ParteCalificacion;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.CalificacionForm;
import ar.org.sicel.web.util.Item;



/**
 * @author jdivars
 *	Action que tiene como funcionalidad la creacion de una nueva calificacion
 */
public class AltaCalificacionAction extends DispatchAction {

	public static final Object lock = new Object();
	
	public ActionForward initSetValores(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (isCancelled(request))
            return (mapping.findForward("iniciar"));
		CalificacionForm cForm = (CalificacionForm)form;
		cForm.reset();
		List calificadores = CalificadorDAO.findAll();
		request.setAttribute("califcadores",calificadores);
		return mapping.findForward("initValores");
	}
	public ActionForward initEspecial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (isCancelled(request))
            return (mapping.findForward("iniciar"));
		List calificadores = CalificadorDAO.findAll();
		request.setAttribute("califcadores",calificadores);
		return mapping.findForward("initValores");
	}
	
	public ActionForward buscarAnimal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (isCancelled(request))
            return (mapping.findForward("iniciar"));
		
		CalificacionForm cForm = (CalificacionForm)form;
		Establecimiento establecimiento = EstablecimientoDAO.findByPrimaryKey(new Long(cForm.getIdEstab())); 
		if (establecimiento == null) { 
			List calificadores = CalificadorDAO.findAll();
			request.setAttribute("califcadores",calificadores);
			request.setAttribute("error","EL TAMBO NO EXISTE");
	        return mapping.findForward("initValores");
		}
		Date fechaI = new Date();
		fechaI= this.parse(cForm.getFecha(),"dd/MM/yyyy");
		if(fechaI == null){
			request.setAttribute("error","LA FECHA TIENE FORMATO ERRONEO");
			List calificadores = CalificadorDAO.findAll();
			request.setAttribute("califcadores",calificadores);
			return mapping.findForward("initValores");
		}else{
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date min = new Date("1/1/2000");
			Date max = new Date();
			if (cForm.getNumModel().equals("3") && !(DateUtils.entre(fechaI,min,max))){
				request.setAttribute("error","La fecha debe estar entre "+formateador.format(min)+" y "+formateador.format(max));
				List calificadores = CalificadorDAO.findAll();
				request.setAttribute("califcadores",calificadores);
				return mapping.findForward("initValores");
			}
			if (!cForm.getNumModel().equals("3")){
					if(new Date().compareTo(fechaI) <= 0 ){
						request.setAttribute("error","La fecha no debe ser mayor al día actual.");
						List calificadores = CalificadorDAO.findAll();
						request.setAttribute("califcadores",calificadores);
						return mapping.findForward("initValores");
					}
			}
		}
		request.getSession().setAttribute("idEstab",cForm.getIdEstab());
		request.getSession().setAttribute("fecha",cForm.getFecha());
		request.getSession().setAttribute("idCalif",cForm.getIdCalif());
		String idEs=cForm.getIdEstab();
		cForm.reset();
		cForm.setIdEstab(idEs);
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);    	
		return mapping.findForward("buscarAnimal");
	}
	public ActionForward seguirCargando(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CalificacionForm cForm = (CalificacionForm)form;
		
		String idEstab = (String) request.getSession().getAttribute("idEstab");
	    String fecha = (String) request.getSession().getAttribute("fecha");
	    String idCalif = (String) request.getSession().getAttribute("idCalif");
		cForm.reset();
		cForm.setIdEstab(idEstab);
		cForm.setFecha(fecha);
		cForm.setIdCalif(idCalif);
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);    	
		return mapping.findForward("buscarAnimal");
	}
	
	public ActionForward initBuscarEstablecimiento(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		CalificacionForm cForm = (CalificacionForm)form;
		cForm.setIdEstab("");
		return mapping.findForward("initBuscarEstablecimiento");		
	}
	public ActionForward buscarEstablecimientos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		CalificacionForm cForm = (CalificacionForm)form;
		List establecimientos = EstablecimientoDAO.findByNombreContacto(cForm.getNombreEstab());
		
		if(establecimientos.isEmpty()){
			request.setAttribute("error","EL TAMBO NO EXISTE");
			return mapping.findForward("initBuscarEstablecimiento");	
		}
		request.setAttribute("establecimientos",establecimientos);
		return mapping.findForward("initBuscarEstablecimiento");		
	}
	public ActionForward seleccionarEstablecimiento(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
			
			String idEstab = request.getParameter("id");
			CalificacionForm cForm = (CalificacionForm)form;
			cForm.setIdEstab(idEstab);
			return mapping.findForward("init");		
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
	public ActionForward porRPyEstab(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	        throws Exception {       
	    if (isCancelled(request))
	        return (mapping.findForward("init"));
	
	    CalificacionForm fc = (CalificacionForm)form;
	    String idEstab = (String) request.getSession().getAttribute("idEstab");
	    String fecha = (String) request.getSession().getAttribute("fecha");
	    String idCalif = (String) request.getSession().getAttribute("idCalif");
	    
	    Establecimiento establecimiento = EstablecimientoDAO.findByPrimaryKey(new Long(idEstab));
	    
	   
	    Animal animal = AnimalDAO.findAnimal(fc.getRp(), establecimiento, fc.getYear(),fc.getSexo());
	    //if ((animal==null)||(!animal.esHembra())){
	    if (animal==null){
	    	List razas = RazaDAO.findAll();
			request.setAttribute("razas",razas);    		
			request.setAttribute("error1","EL ANIMAL NO EXISTE");
			return mapping.findForward("buscarAnimal");
	    }
	   /* if(!mesesParto(animal,fecha)){
	    	List razas = RazaDAO.findAll();
			request.setAttribute("razas",razas);    		
			request.setAttribute("error1","EL ANIMAL NO HA TENIDO UN PARTO EN LOS ULTIMOS 24 MESES");
			return mapping.findForward("buscarAnimal");
	    }*/
	    //fc.setIdEclo(idEclo);
	   // fc.setIdProp(idProp);
	    String sexo = new String(fc.getSexo());
	    fc.reset();
	    fc.setIdEstab(idEstab);
	    fc.setFecha(fecha);
	    fc.setIdAninal(animal.getId().toString());
	    fc.setIdCalif(idCalif);
	    fc.setSexo(sexo.equals("Macho") ? "M":"H");
	  
	    return ((fc.getNumModel().equals("2"))?(mapping.findForward("initAdd")):(mapping.findForward("initAddM3")));
	}

	private boolean mesesParto(Animal animal,String fecha) throws ParseException {
		SimpleDateFormat gf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = gf.parse(fecha);
		Long f = d.getTime();
		Long dosAn= new Long(365 * 2 * 24 * 60 * 60 * 1000);
		int r = f.intValue() - dosAn.intValue();
		Date fechaC = new Date(r);
		List eve = animal.getEventos(Evento.EVT_TIPO_REP);
		if(eve!=null){
			if(eve.size()>0){
					Collections.sort(eve, new EventosPorFechaYTipo());
					int i = eve.size()-1;
					EvtReproduccion rep=null;
					while(i>=0){
						rep = (EvtReproduccion) eve.get(i);
						if(rep.isIniciaLactancia()&&(rep.getFecha().after(fechaC))){
							return true;
						}
						else
							i--;
					}
			}
		}
		return false;
		}
	
	@SuppressWarnings("unchecked")
	public ActionForward porTipoyNumero(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {

		if (isCancelled(request))
			return (mapping.findForward("main"));

		CalificacionForm fc = (CalificacionForm)form;
		//Animal animal = AnimalDAO.findByRegistry(fc.getTipoReg(),fc.getNumReg(),fc.getRaza(),"H");
		Animal animal = AnimalDAO.findByRegistry(fc.getTipoReg(),fc.getNumReg(),fc.getRaza(),fc.getSexo());
		String idEstab = (String) request.getSession().getAttribute("idEstab");
		
		if (animal == null){
			List razas = RazaDAO.findAll();
			request.setAttribute("razas",razas);    		
			request.setAttribute("error2","EL ANIMAL NO EXISTE");
			return mapping.findForward("buscarAnimal");
		}
		String fecha = (String) request.getSession().getAttribute("fecha");
		/*if(!mesesParto(animal,fecha)){
	    	List razas = RazaDAO.findAll();
			request.setAttribute("razas",razas);    		
			request.setAttribute("error2","EL ANIMAL NO HA TENIDO UN PARTO EN LOS ULTIMOS 24 MESES");
			return mapping.findForward("buscarAnimal");
	    }*/
		fc.reset();
		fc.setIdAninal(animal.getId().toString());
		fc.setIdEstab(idEstab);
		String idCalif= (String)request.getSession().getAttribute("idCalif");
		fc.setIdCalif(idCalif);
		
		fc.setFecha(fecha);
    //return mapping.findForward("initAdd");
		return ((fc.getNumModel().equals("2"))?(mapping.findForward("initAdd")):(mapping.findForward("initAddM3")));
	}
	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CalificacionForm fc = (CalificacionForm)form;
		
		if(fc.getIdAninal()!=null){
			Calificacion c = new Calificacion();	
			cargarEncabezado(c,fc);
			cargarForm(fc,c,"add");
		}
		request.setAttribute("action","add");
		return mapping.findForward("boleta");

	}
	
	public ActionForward initAddM3(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CalificacionForm fc = (CalificacionForm)form;
		if(fc.getIdAninal()!=null){
			Calificacion c = new Calificacion();	
			cargarEncabezado(c,fc);
			cargarFormM3(fc,c,"add");
		}
		List<Item> lista= new ArrayList<Item>();
		Item item;
		for (int i = 40; i <=99; i++) {
			item=new Item();
			item.setId(String.valueOf(i));
			item.setNombre(String.valueOf(i));
			lista.add(item);
		}
		request.setAttribute("listaValores",lista);    	
		request.setAttribute("action","add");
		return mapping.findForward("boletaM3");
	}

	private void cargarEncabezado(Calificacion c, CalificacionForm fc) throws ParseException {
		Animal ani= AnimalDAO.findByPrimaryKey(new Long(fc.getIdAninal()));
		c.setAnimal(ani);
		Establecimiento est = EstablecimientoDAO.findByPrimaryKey(new Long(fc.getIdEstab()));
		c.setEstablecimiento(est);
		SimpleDateFormat gf = new SimpleDateFormat("dd/MM/yyyy");
		c.setFecha(gf.parse(fc.getFecha()));
		Calificador calificador = CalificadorDAO.findByPrimaryKey(new Long(fc.getIdCalif()));
		c.setCalificador(calificador);
		c.setEstancia((est!=null)?est.getEstancia():null);
		c.setPropietario(c.getEstancia() != null ? c.getEstancia().getPropietario() : null);
		//para que no tire error
		//c.setEspecial(true);
		
		
		
		
	}
	public ActionForward initUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String idBol= (String) request.getParameter("id");
		CalificacionForm fc = (CalificacionForm)form;
		Calificacion calif = CalificacionDAO.findByPrimaryKey(new Long(idBol));
		if(calif!=null){
			fc.setIdBol(idBol);
			fc.setFecha(calif.getFecha().toString());
			if(calif.getModeloCalificacion().getId().equals(new Long(2)))
				cargarForm(fc,calif,"update");
			else if(calif.getModeloCalificacion().getId().equals(new Long(3))){
				cargarFormM3(fc,calif,"update");
				List<Item> lista= new ArrayList<Item>();
				Item item;
				for (int i = 40; i <=99; i++) {
					item=new Item();
					item.setId(String.valueOf(i));
					item.setNombre(String.valueOf(i));
					lista.add(item);
				}
				request.setAttribute("listaValores",lista);    	
			}
		}
		request.setAttribute("action","update");
//		return mapping.findForward("boleta");
		return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
	}
	public ActionForward initBaja(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String idBol= (String) request.getParameter("id");
		System.out.println(idBol);
		CalificacionForm fc = (CalificacionForm)form;
		Calificacion calif = CalificacionDAO.findByPrimaryKey(new Long(idBol));
		fc.setIdBol(idBol);
		fc.setFecha(calif.getFecha().toString());
		if(calif.getModeloCalificacion().getId().equals(new Long(2)))
			cargarForm(fc,calif,"update");
		else if(calif.getModeloCalificacion().getId().equals(new Long(1)))
			cargarFormM1(fc,calif);
		else if(calif.getModeloCalificacion().getId().equals(new Long(3)))
			cargarFormM3(fc,calif,"update");
		
		request.setAttribute("action","remove");
		//TODO ver el forward m3
	return mapping.findForward("boletaRemove");

}
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	//	Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
	   // if( user.getRol().getNombre().equals(Tokens.NOMBREROLCASANOVA))
	   // 	   eclo = EcloDAO.findByPrimaryKey(user.getContacto().getId());
		//String idBol= (String) request.getParameter("id");
		CalificacionForm fc = (CalificacionForm)form;
		String idBol= fc.getIdBol();
		Calificacion calif = CalificacionDAO.findByPrimaryKey(new Long(idBol));
		if(calif!=null){
			Session session = HibernateFactory.getSession();
			try {
				session.delete(calif);
			} catch (HibernateException he){
				he.printStackTrace();
			}
		}
		return mapping.findForward("filtro");
	}
	
	private void cargarForm(CalificacionForm fc, Calificacion calif,String tipo) {
		Establecimiento estab = calif.getEstablecimiento();
		Animal an = calif.getAnimal();
		fc.setIdAninal(an.getId().toString());
		if(an.getEstablecimiento()!=null){
			fc.setIdEstabAn(an.getEstablecimiento().getId().toString());
			fc.setIdEcloAn(an.getEstablecimiento().getEclo().getId().toString());
		}
		if(an.getPropietario()!=null)
			fc.setIdPropAn(an.getPropietario().getId().toString());
		Integer puntajeAnt = CalificacionDAO.getPuntajeAnterior(calif,an);
		if(puntajeAnt!=null)
			fc.setPuntAnt(puntajeAnt.toString());
		fc.setFechaNac(DateUtils.format(an.getFechaNac(),"dd/MM/yyyy HH:mm:ss"));
		if(calif.getBoleta()!=null)
			fc.setNumBoleta(calif.getBoleta().toString());
		fc.setRp(an.getRP());
		fc.setTipoReg(an.getRegOrigen().getTipoRegistro().getId());
		fc.setNumReg(an.getRegOrigen().getNumero());
		if(calif.getCalificador()!=null){
			fc.setIdCalif(calif.getCalificador().getId().toString());
			fc.setNombreCalif(calif.getCalificador().getNombreCompleto());
		}
		if(estab!=null){
			fc.setIdEstab(estab.getId().toString());
			fc.setIdEclo(estab.getEclo().getId().toString());
			fc.setIdProp(estab.getEstancia().getPropietario().getId().toString());
			fc.setIdEstancia(estab.getEstancia().getId().toString());
			fc.setNombreEstancia(estab.getEstancia().getNombreContacto());
			fc.setNombreEstab(estab.getNombreContacto());
			fc.setNombreEclo(estab.getEclo().getNombreContacto());
			fc.setNombreProp(estab.getEstancia().getPropietario().getNombreContacto());
		}
		fc.setEspecial(calif.getEspecial());
		if(tipo.equals("add")){
			List eve = an.getEventos(Evento.EVT_TIPO_REP);
			if(eve!=null){
				if(eve.size()>0){
						Collections.sort(eve, new EventosPorFechaYTipo());
						int i = eve.size()-1;
						boolean x = true;
						/*EvtReproduccion rep=null;
						while(i>=0 && x){
							rep = (EvtReproduccion) eve.get(i);
							if(rep.isIniciaLactancia()){
								x=false;
								fc.setFechaPar(DateUtils.format(rep.getFecha(),"dd/MM/yyyy HH:mm:ss"));
								fc.setNumParto(String.valueOf(eve.size()));
							}
							else
								i--;
						}*/
						EvtReproduccion rep=null;
						EvtReproduccion repAux=null;
						while(i>=0){
							rep = (EvtReproduccion) eve.get(i);
							if(rep.isIniciaLactancia()&& rep.getFecha().before(calif.getFecha()) ){
									repAux = rep; 
									break;
							}
							i--;
						}
						if(repAux!=null){
							fc.setFechaPar(DateUtils.format(repAux.getFecha(),"dd/MM/yyyy HH:mm:ss"));
							fc.setNumParto(String.valueOf(repAux.getNroLactancia()));
						}
			}
			}
		}else{
			/*List eve = an.getEventos(Evento.EVT_TIPO_REP);
			if(eve!=null){
				if(eve.size()>0){
				fc.setFechaPar(DateUtils.format(calif.getInicioLactancia(),"dd/MM/yyyy HH:mm:ss"));
				fc.setNumParto(calif.getNumeroLactancia().toString());
				}
			}*/
			if(calif.getInicioLactancia()!=null)
				fc.setFechaPar(DateUtils.format(calif.getInicioLactancia(),"dd/MM/yyyy HH:mm:ss"));
			if(calif.getNumeroLactancia()!=null)
				fc.setNumParto(calif.getNumeroLactancia().toString());
		
		}
			
		Boleta bol = new Boleta();
		if(tipo.equals("update")){
		Map mapPartes = calif.getCalificacionDePartes();
		Map mapCarac = calif.getCalificacionDeCaracteristicas();
		Map mapDefe = calif.getCalificacionDeDefectos();
		if(calif.getModeloCalificacion()!=null){
			bol.setNivelPun(calif.getCatPuntaje().trim());
			bol.setValorPun(calif.getPuntaje().toString());
			fc.setComentarios(calif.getComentarios());
			Set partes = calif.getModeloCalificacion().getParteCalificacions();
			Iterator it = partes.iterator();
			while(it.hasNext()){
				ParteCalificacion parte = (ParteCalificacion)it.next();
				if(parte.getCodigo().equals("E")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Estatura")&& mapCarac.get(c)!=null)
							bol.setCaracEst1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Tren anterior")&& mapCarac.get(c)!=null)
							bol.setCaracEst2(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Tamaño")&& mapCarac.get(c)!=null)
							bol.setCaracEst3(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Ancho de pecho")&& mapCarac.get(c)!=null)
							bol.setCaracEst4(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Profundidad del cuerpo")&& mapCarac.get(c)!=null)
							bol.setCaracEst5(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Fortaleza de lomo")&& mapCarac.get(c)!=null)
							bol.setCaracEst6(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							if(mapDefe.containsKey(d)){
								if(d.getNombre().equals("Cara torcida")&& mapDefe.get(d)!=null)
									bol.setDefeEst1(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Cabeza indeseable")&& mapDefe.get(d)!=null)
									bol.setDefeEst2(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Retroescápula débil")&& mapDefe.get(d)!=null)
									bol.setDefeEst3(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Línea dorsal débil")&& mapDefe.get(d)!=null)
									bol.setDefeEst4(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Falta de armonía")&& mapDefe.get(d)!=null)
									bol.setDefeEst5(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Falta arco costal")&& mapDefe.get(d)!=null)
									bol.setDefeEst6(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Lomo bajo")&& mapDefe.get(d)!=null)
									bol.setDefeEst7(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Reg. card. estrecha")&& mapDefe.get(d)!=null)
									bol.setDefeEst8(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Frágil")&& mapDefe.get(d)!=null)
									bol.setDefeEst9(((Integer)mapDefe.get(d)).toString());
							}
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelEst(cat.getCategoriaPuntaje().trim());
						bol.setValorEst(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("F")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Colocacion de isquiones")&& mapCarac.get(c)!=null)
							bol.setCaracGru1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Separación de isquiones")&& mapCarac.get(c)!=null)
							bol.setCaracGru2(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							if(mapDefe.containsKey(d)){
								if(d.getNombre().equals("Ano adelantado")&& mapDefe.get(d)!=null)
									bol.setDefeGru1(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Ins. Cola baja")&& mapDefe.get(d)!=null)
									bol.setDefeGru2(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Ins. Cola alta")&& mapDefe.get(d)!=null)
									bol.setDefeGru3(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Ins. Cola adelantada")&& mapDefe.get(d)!=null)
									bol.setDefeGru4(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Cola torcida")&& mapDefe.get(d)!=null)
									bol.setDefeGru5(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Art. demasiado atrás")&& mapDefe.get(d)!=null)
									bol.setDefeGru6(((Integer)mapDefe.get(d)).toString());
							}
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelGru(cat.getCategoriaPuntaje().trim());
						bol.setValorGru(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("G")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Ángulo de pezuña")&& mapCarac.get(c)!=null)
							bol.setCaracPat1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Profundidad de talón")&& mapCarac.get(c)!=null)
							bol.setCaracPat2(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Calidad de hueso")&& mapCarac.get(c)!=null)
							bol.setCaracPat3(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Coloc. patas tras.(costado)")&& mapCarac.get(c)!=null)
							bol.setCaracPat4(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Coloc. patas tras.(de atrás)")&& mapCarac.get(c)!=null)
							bol.setCaracPat5(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							if(mapDefe.containsKey(d)){
								if(d.getNombre().equals("Cuartillas débiles")&& mapDefe.get(d)!=null)
									bol.setDefePat1(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Calambres")&& mapDefe.get(d)!=null)
									bol.setDefePat2(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Garrones toscos")&& mapDefe.get(d)!=null)
									bol.setDefePat3(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Dedos abiertos")&& mapDefe.get(d)!=null)
									bol.setDefePat4(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Postura indeseable")&& mapDefe.get(d)!=null)
									bol.setDefePat5(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Falta de hueso")&& mapDefe.get(d)!=null)
									bol.setDefePat6(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pez. ant. hacia afuera")&& mapDefe.get(d)!=null)
									bol.setDefePat7(((Integer)mapDefe.get(d)).toString());
							}
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelPat(cat.getCategoriaPuntaje().trim());
						bol.setValorPat(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("H")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Profundidad de ubre")&& mapCarac.get(c)!=null)
							bol.setCaracSis1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Textura")&& mapCarac.get(c)!=null)
							bol.setCaracSis2(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Ligamento medio")&& mapCarac.get(c)!=null)
							bol.setCaracSis3(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							if(mapDefe.containsKey(d)){
								if(d.getNombre().equals("Cuarteada")&& mapDefe.get(d)!=null)
									bol.setDefeSis1(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Oblicua")&& mapDefe.get(d)!=null)
									bol.setDefeSis2(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pesada adelante")&& mapDefe.get(d)!=null)
									bol.setDefeSis3(((Integer)mapDefe.get(d)).toString());
							}
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelSis(cat.getCategoriaPuntaje().trim());
						bol.setValorSis(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("I")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Inserción anterior")&& mapCarac.get(c)!=null)
							bol.setCaracAnt1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Colocación del pezón")&& mapCarac.get(c)!=null)
							bol.setCaracAnt2(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Largo del pezón")&& mapCarac.get(c)!=null)
							bol.setCaracAnt3(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							if(mapDefe.containsKey(d)){
								if(d.getNombre().equals("Abultada")&& mapDefe.get(d)!=null)
									bol.setDefeAnt1(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pesada")&& mapDefe.get(d)!=null)
									bol.setDefeAnt2(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Desbalanceada")&& mapDefe.get(d)!=null)
									bol.setDefeAnt3(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Corta")&& mapDefe.get(d)!=null)
									bol.setDefeAnt4(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pezones desviados")&& mapDefe.get(d)!=null)
									bol.setDefeAnt5(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pezón palmípedo")&& mapDefe.get(d)!=null)
									bol.setDefeAnt6(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Cuarto ciego")&& mapDefe.get(d)!=null)
									bol.setDefeAnt7(((Integer)mapDefe.get(d)).toString());
							}
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelAnt(cat.getCategoriaPuntaje().trim());
						bol.setValorAnt(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("J")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Altura de inserción")&& mapCarac.get(c)!=null){
							bol.setCaracPos1(((Integer)mapCarac.get(c)).toString());
						}
						if(c.getNombre().equals("Ancho de inserción")&& mapCarac.get(c)!=null){
							bol.setCaracPos2(((Integer)mapCarac.get(c)).toString());
						}
						if(c.getNombre().equals("Colocación de pezones")&& mapCarac.get(c)!=null){
							bol.setCaracPos3(((Integer)mapCarac.get(c)).toString());
						}
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							if(mapDefe.containsKey(d)){
								if(d.getNombre().equals("Desbalanceada")&& mapDefe.get(d)!=null)
									bol.setDefePos1(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Corta")&& mapDefe.get(d)!=null)
									bol.setDefePos2(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pezones desviados")&& mapDefe.get(d)!=null)
									bol.setDefePos3(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pez. demasiado atrás")&& mapDefe.get(d)!=null)
									bol.setDefePos4(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Pezón palmípedo")&& mapDefe.get(d)!=null)
									bol.setDefePos5(((Integer)mapDefe.get(d)).toString());
								if(d.getNombre().equals("Cuarto ciego")&& mapDefe.get(d)!=null)
									bol.setDefePos6(((Integer)mapDefe.get(d)).toString());
							}
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelPos(cat.getCategoriaPuntaje().trim());
						bol.setValorPos(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("D")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Forma lechera")&& mapCarac.get(c)!=null){
							bol.setCaracCar1(((Integer)mapCarac.get(c)).toString());
						}
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							if(mapDefe.containsKey(d)){
								if(d.getNombre().equals("Costillas juntas")&& mapDefe.get(d)!=null){
									bol.setDefeCar1(((Integer)mapDefe.get(d)).toString());
								}
							}
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelCar(cat.getCategoriaPuntaje().trim());
						bol.setValorCar(cat.getValor().toString());
					}
				}
			}
			
			fc.setPartes(calif.getCalificacionDePartes());
			fc.setCaracteristicas(calif.getCalificacionDeCaracteristicas());
			fc.setDefectos(calif.getCalificacionDeDefectos());
			
		}
		fc.setBoleta(bol);
		fc.setModelo(calif.getModeloCalificacion());
		fc.setNumModel(calif.getModeloCalificacion().getId().toString());
		}
	}

	private void cargarFormM3(CalificacionForm fc, Calificacion calif,String tipo) {
		Establecimiento estab = calif.getEstablecimiento();
		Animal an = calif.getAnimal();
		fc.setIdAninal(an.getId().toString());
		fc.setSexo(an.esHembra()?"H":"M");
		if(an.getEstablecimiento()!=null){
			fc.setIdEstabAn(an.getEstablecimiento().getId().toString());
			fc.setIdEcloAn(an.getEstablecimiento().getEclo().getId().toString());
		}
		if(an.getEstancia()!=null){
			fc.setIdEstanAn(an.getEstancia().getId().toString());
		}
		if(an.getPropietario()!=null)
			fc.setIdPropAn(an.getPropietario().getId().toString());
		Integer puntajeAnt = CalificacionDAO.getPuntajeAnterior(calif,an);
		if(puntajeAnt!=null)
			fc.setPuntAnt(puntajeAnt.toString());
		fc.setFechaNac(DateUtils.format(an.getFechaNac(),"dd/MM/yyyy HH:mm:ss"));
		if(calif.getBoleta()!=null)
			fc.setNumBoleta(calif.getBoleta().toString());
		fc.setRp(an.getRP());
		fc.setTipoReg(an.getRegOrigen().getTipoRegistro().getId());
		fc.setNumReg(an.getRegOrigen().getNumero());
		if(calif.getCalificador()!=null){
			fc.setIdCalif(calif.getCalificador().getId().toString());
			fc.setNombreCalif(calif.getCalificador().getNombreCompleto());
		}
		if(estab!=null){
			fc.setIdEstab(estab.getId().toString());
			fc.setIdEclo(estab.getEclo().getId().toString());
			fc.setIdProp(estab.getEstancia().getPropietario().getId().toString());
			fc.setIdEstancia(estab.getEstancia().getId().toString());
			fc.setNombreEstancia(estab.getEstancia().getNombreContacto());
			fc.setNombreEstab(estab.getNombreContacto());
			fc.setNombreEclo(estab.getEclo().getNombreContacto());
			fc.setNombreProp(estab.getEstancia().getPropietario().getNombreContacto());
		}
		fc.setEspecial(calif.getEspecial());
		if(tipo.equals("add")){
			List eve = an.getEventos(Evento.EVT_TIPO_REP);
			if(eve!=null){
				if(eve.size()>0){
					Collections.sort(eve, new EventosPorFechaYTipo());
					int i = eve.size()-1;
					/*boolean x = true;
					EvtReproduccion rep=null;
					while(i>=0 && x){
						rep = (EvtReproduccion) eve.get(i);
						if(rep.isIniciaLactancia()){
							x=false;
							fc.setFechaPar(DateUtils.format(rep.getFecha(),"dd/MM/yyyy HH:mm:ss"));
							fc.setNumParto(String.valueOf(eve.size()));
						}
						else i--;
					}*/
					EvtReproduccion rep=null;
					EvtReproduccion repAux=null;
					while(i>=0){
						rep = (EvtReproduccion) eve.get(i);
						if(rep.isIniciaLactancia()&& rep.getFecha().before(calif.getFecha()) ){
								repAux = rep; 
								break;
						}
						i--;
					}
					if(repAux!=null){
						fc.setFechaPar(DateUtils.format(repAux.getFecha(),"dd/MM/yyyy HH:mm:ss"));
						fc.setNumParto(String.valueOf(repAux.getNroLactancia()));
					}
					
				}
			}
		}else{
			if(calif.getInicioLactancia()!=null)
				fc.setFechaPar(DateUtils.format(calif.getInicioLactancia(),"dd/MM/yyyy HH:mm:ss"));
			if(calif.getNumeroLactancia()!=null)
				fc.setNumParto(calif.getNumeroLactancia().toString());
		}
			
		Boleta bol = new Boleta();
		if(tipo.equals("update")){
			Map mapPartes = calif.getCalificacionDePartes();
			Map mapCarac = calif.getCalificacionDeCaracteristicas();
			Map mapDefe = calif.getCalificacionDeDefectos();
			if(calif.getModeloCalificacion()!=null){
				bol.setNivelPun(calif.getCatPuntaje().trim());
				bol.setValorPun(calif.getPuntaje().toString());
				fc.setComentarios(calif.getComentarios());
				Set partes = calif.getModeloCalificacion().getParteCalificacions();
				Iterator it = partes.iterator();
				while(it.hasNext()){
					ParteCalificacion parte = (ParteCalificacion)it.next();
					if(parte.getCodigo().equals("A")){
						Iterator itc = parte.getCaracteristicaCalificacions().iterator();
						while(itc.hasNext()){
							CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
							if(c.getNombre().equals("Declive Grupa")&& mapCarac.get(c)!=null)
								bol.setCaracGru1(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Sep. Isquiones")&& mapCarac.get(c)!=null)
								bol.setCaracGru2(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Fortaleza Lomo")&& mapCarac.get(c)!=null)
								bol.setCaracGru3(((Integer)mapCarac.get(c)).toString());

						}
						if(parte.getDefectoCalificacions()!=null){
							Iterator itd = parte.getDefectoCalificacions().iterator();
							while(itd.hasNext()){
								DefectoCalificacion d = (DefectoCalificacion)itd.next();
								if(mapDefe.containsKey(d)){
									if(d.getNombre().equals("ano adelantado")&& mapDefe.get(d)!=null)
										bol.setDefeGru1(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("cola adelantada")&& mapDefe.get(d)!=null)
										bol.setDefeGru2(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("ins. cola baja")&& mapDefe.get(d)!=null)
										bol.setDefeGru3(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("ins. cola alta")&& mapDefe.get(d)!=null)
										bol.setDefeGru4(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("cola torcida")&& mapDefe.get(d)!=null)
										bol.setDefeGru5(((Integer)mapDefe.get(d)).toString());
								}
							}
						}
						CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
						if(cat!=null){
							//bol.setNivelEst(cat.getCategoriaPuntaje().trim());
							bol.setValorGru(cat.getValor().toString());
						}
					}
					else if(parte.getCodigo().equals("B")){
						Iterator itc = parte.getCaracteristicaCalificacions().iterator();
						while(itc.hasNext()){
							CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
							if(c.getNombre().equals("Prof. Ubre")&& mapCarac.get(c)!=null)
								bol.setCaracSis1(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Textura Ubre")&& mapCarac.get(c)!=null)
								bol.setCaracSis2(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Lig. Medio")&& mapCarac.get(c)!=null)
								bol.setCaracSis3(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Inserción Ant.")&& mapCarac.get(c)!=null)
								bol.setCaracSis4(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Coloc. Pez. Ant.")&& mapCarac.get(c)!=null)
								bol.setCaracSis5(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Alt. Ins. Post.")&& mapCarac.get(c)!=null)
								bol.setCaracSis6(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Anch. Ins. Post.")&& mapCarac.get(c)!=null)
								bol.setCaracSis7(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Coloc. Pez. Post.")&& mapCarac.get(c)!=null)
								bol.setCaracSis8(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Largo Pezones")&& mapCarac.get(c)!=null)
								bol.setCaracSis9(((Integer)mapCarac.get(c)).toString());
						}
						if(parte.getDefectoCalificacions()!=null){
							Iterator itd = parte.getDefectoCalificacions().iterator();
							
							while(itd.hasNext()){
								DefectoCalificacion d = (DefectoCalificacion)itd.next();
								if(mapDefe.containsKey(d)){
									if(d.getNombre().equals("oblicua")&& mapDefe.get(d)!=null)
										bol.setDefeSis1(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("oblicua al revés")&& mapDefe.get(d)!=null)
										bol.setDefeSis2(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("anterior abultada")&& mapDefe.get(d)!=null)
										bol.setDefeSis3(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("anterior corta")&& mapDefe.get(d)!=null)
										bol.setDefeSis4(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("posterior corta")&& mapDefe.get(d)!=null)
										bol.setDefeSis5(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("mala forma")&& mapDefe.get(d)!=null)
										bol.setDefeSis6(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("cuarto desbalanceado posterior")&& mapDefe.get(d)!=null)
										bol.setDefeSis7(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("cuarto desbalanceado anterior")&& mapDefe.get(d)!=null)
										bol.setDefeSis21(mapDefe.get(d) != null ? ((Integer)mapDefe.get(d)).toString() : "");
									else if(d.getNombre().equals("cuarto ciego")&& mapDefe.get(d)!=null)
										bol.setDefeSis8(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("pezón palmípedo")&& mapDefe.get(d)!=null)
										bol.setDefeSis9(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("juntos lateralmente")&& mapDefe.get(d)!=null)
										bol.setDefeSis10(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("p. post. muy atrás")&& mapDefe.get(d)!=null)
										bol.setDefeSis11(((Integer)mapDefe.get(d)).toString());
								}
							}
						}
						CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
						if(cat!=null){
							//bol.setNivelGru(cat.getCategoriaPuntaje().trim());
							bol.setValorSis(cat.getValor().toString());
						}
					}
					else if(parte.getCodigo().equals("C")){
						Iterator itc = parte.getCaracteristicaCalificacions().iterator();
						while(itc.hasNext()){
							CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
							if(c.getNombre().equals("Ángulo de Pez.")&& mapCarac.get(c)!=null)
								bol.setCaracPat1(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Calid. Hueso")&& mapCarac.get(c)!=null)
								bol.setCaracPat2(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Patas de Costado")&& mapCarac.get(c)!=null)
								bol.setCaracPat3(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Locomoción")&& mapCarac.get(c)!=null)
								bol.setCaracPat4(((Integer)mapCarac.get(c)).toString());
						}
						if(parte.getDefectoCalificacions()!=null){
							Iterator itd = parte.getDefectoCalificacions().iterator();
							while(itd.hasNext()){
								DefectoCalificacion d = (DefectoCalificacion)itd.next();
								if(mapDefe.containsKey(d)){
									if(d.getNombre().equals("dedo tirabuzón")&& mapDefe.get(d)!=null)
										bol.setDefePat1(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("cuartillas débiles")&& mapDefe.get(d)!=null)
										bol.setDefePat2(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("garrón avejigado")&& mapDefe.get(d)!=null)
										bol.setDefePat3(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("falta de hueso")&& mapDefe.get(d)!=null)
										bol.setDefePat4(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("calambres")&& mapDefe.get(d)!=null)
										bol.setDefePat5(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("art. coxofemoral atrás")&& mapDefe.get(d)!=null)
										bol.setDefePat6(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("pez. ant. hacia fuera")&& mapDefe.get(d)!=null)
										bol.setDefePat7(((Integer)mapDefe.get(d)).toString());
								}
							}
						}
						CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
						if(cat!=null){
							//bol.setNivelPat(cat.getCategoriaPuntaje().trim());
							bol.setValorPat(cat.getValor().toString());
						}
					}
					else if(parte.getCodigo().equals("D")){
						Iterator itc = parte.getCaracteristicaCalificacions().iterator();
						while(itc.hasNext()){
							CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
							if(c.getNombre().equals("Estatura")&& mapCarac.get(c)!=null)
								bol.setCaracFor1(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Anch. Pecho")&& mapCarac.get(c)!=null)
								bol.setCaracFor2(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Prof. Cuerpo")&& mapCarac.get(c)!=null)
								bol.setCaracFor3(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Angulosidad")&& mapCarac.get(c)!=null)
								bol.setCaracFor4(((Integer)mapCarac.get(c)).toString());
							else if(c.getNombre().equals("Cond. Corporal")&& mapCarac.get(c)!=null)
								bol.setCaracFor5(((Integer)mapCarac.get(c)).toString());
						}
						if(parte.getDefectoCalificacions()!=null){
							Iterator itd = parte.getDefectoCalificacions().iterator();
							while(itd.hasNext()){
								DefectoCalificacion d = (DefectoCalificacion)itd.next();
								if(mapDefe.containsKey(d)){
									if(d.getNombre().equals("cara torcida")&& mapDefe.get(d)!=null)
										bol.setDefeFor1(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("mandíbula malformada")&& mapDefe.get(d)!=null)
										bol.setDefeFor2(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("reg. cardíaca estrecha")&& mapDefe.get(d)!=null)
										bol.setDefeFor3(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("retroescápula débil")&& mapDefe.get(d)!=null)
										bol.setDefeFor4(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("dorso débil")&& mapDefe.get(d)!=null)
										bol.setDefeFor5(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("tren anterior bajo")&& mapDefe.get(d)!=null)
										bol.setDefeFor6(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("falta arco costal")&& mapDefe.get(d)!=null)
										bol.setDefeFor7(((Integer)mapDefe.get(d)).toString());
									else if(d.getNombre().equals("desarmónica")&& mapDefe.get(d)!=null)
										bol.setDefeFor8(((Integer)mapDefe.get(d)).toString());
								}
							}
						}
						CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
						if(cat!=null){
							//bol.setNivelSis(cat.getCategoriaPuntaje().trim());
							bol.setValorFor(cat.getValor().toString());
						}
					}
				}
				fc.setPartes(calif.getCalificacionDePartes());
				fc.setCaracteristicas(calif.getCalificacionDeCaracteristicas());
				fc.setDefectos(calif.getCalificacionDeDefectos());
			}
			fc.setBoleta(bol);
			fc.setModelo(calif.getModeloCalificacion());
			fc.setNumModel(calif.getModeloCalificacion().getId().toString());
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CalificacionForm fc = (CalificacionForm)form;
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		//List fd = CalificacionDAO.findByBoleta(fc.getNumBoleta(),user);
		/*if(!fd.isEmpty()){
			request.setAttribute("action","add");
			request.setAttribute("error","YA EXISTE OTRA CALIFCACION CON EL NUMERO "+fc.getNumBoleta());
			List<Item> lista= new ArrayList<Item>();
			Item item;
			for (int i = 40; i <=99; i++) {
				item=new Item();
				item.setId(String.valueOf(i));
				item.setNombre(String.valueOf(i));
				lista.add(item);
			}
			request.setAttribute("listaValores",lista);    	
		    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
			//return mapping.findForward("boleta");
		}*/
		if (fc.getBoleta().getNivelPun() == null || fc.getBoleta().getNivelPun().equals("")){
			request.setAttribute("action","add");
			request.setAttribute("error","LA CATEGORIA DEL PUNTAJE DEBE CARGARSE");
			List<Item> lista= new ArrayList<Item>();
			Item item;
			for (int i = 40; i <=99; i++) {
				item=new Item();
				item.setId(String.valueOf(i));
				item.setNombre(String.valueOf(i));
				lista.add(item);
			}
			request.setAttribute("listaValores",lista);    	
		    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
		}
		if(!fc.getFechaPar().equals("")){
			String f = fc.getFechaPar().replace("-","/");
			Date fechaI = new Date();
			fechaI= this.parse(f,"dd/MM/yyyy");
			if(fechaI == null){
				request.setAttribute("action","add");
				request.setAttribute("errorFechaParto","La fecha de parto tiene formato erróneo");
				List<Item> lista= new ArrayList<Item>();
				Item item;
				for (int i = 40; i <=99; i++) {
					item=new Item();
					item.setId(String.valueOf(i));
					item.setNombre(String.valueOf(i));
					lista.add(item);
				}
				request.setAttribute("listaValores",lista);    	
			    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
				//return mapping.findForward("boleta");
			}else{
				SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				Date min = new Date("1/1/2000");
				Date max = formateador.parse(fc.getFecha()); 
				max = DateUtils.menos(max,1);
				/*solo para hembras porque es la fecha de parto*/
				if (!fc.getSexo().equals("M") && fc.getNumModel().equals("3") && !(DateUtils.entre(fechaI,min,max))){
					request.setAttribute("action","add");
					request.setAttribute("errorFechaParto","La fecha de parto debe estar entre "+formateador.format(min)+" y "+formateador.format(max));
					List<Item> lista= new ArrayList<Item>();
					Item item;
					for (int i = 40; i <=99; i++) {
						item=new Item();
						item.setId(String.valueOf(i));
						item.setNombre(String.valueOf(i));
						lista.add(item);
					}
					request.setAttribute("listaValores",lista); 
				    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
				}
			}
		}
		Calificacion calif = new Calificacion();
		this.cargarEncabezado(calif,fc);
		calif.setDataEntry(user);
		if(fc.getNumModel().equals("2"))
			cargarCalifcacion(fc,calif);
		else if(fc.getNumModel().equals("3"))
			cargarCalifcacion3(fc,calif);
		try{
			
			synchronized (this.getLock()) {
				CalificacionDAO.save(calif);
			}
//			try {
//				Animal an = AnimalDAO.findByPrimaryKey(Long.valueOf(fc.getIdAninal()));
//				if(an.getEstablecimiento()==null) {
//						if((an.getPropietario().equals(calif.getEstablecimiento().getPropietario())) ){
//								an.setEstablecimiento(calif.getEstablecimiento());
//								an.setEstancia(calif.getEstablecimiento().getEstancia());
//								an.setPropietario(calif.getEstablecimiento().getPropietario());
//								HibernateFactory.getSession().update(an);
//						}
//						else{
//							request.setAttribute("action","add");
//							request.setAttribute("error","El animal no se encuentra en ningún tambo y el propietario del tambo "+ calif.getEstablecimiento() + "no es igual al del animal. Debe enviar un evento transferencia");
//							List<Item> lista= new ArrayList<Item>();
//							Item item;
//							for (int i = 40; i <=99; i++) {
//								item=new Item();
//								item.setId(String.valueOf(i));
//								item.setNombre(String.valueOf(i));
//								lista.add(item);
//							}
//							request.setAttribute("listaValores",lista);    	
//						    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
//				  		
//						}
//						
//				}
//				
//	  		} catch (HibernateException e) {
//	  			request.setAttribute("action","add");
//				request.setAttribute("error","NO SE PUDO ACTUALIZAR LOS DATOS DEL ANIMAL ");
//				List<Item> lista= new ArrayList<Item>();
//				Item item;
//				for (int i = 40; i <=99; i++) {
//					item=new Item();
//					item.setId(String.valueOf(i));
//					item.setNombre(String.valueOf(i));
//					lista.add(item);
//				}
//				request.setAttribute("listaValores",lista);    	
//			    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
//	  		}
		}
		catch(HibernateException e){
			//throw new BoletaUnicaException("YA EXISTE OTRA CALIFCACION CON EL NUMERO "+fc.getNumBoleta(),e);
			request.setAttribute("action","add");
			request.setAttribute("error","YA EXISTE OTRA CALIFCACION CON EL NUMERO "+fc.getNumBoleta());
			List<Item> lista= new ArrayList<Item>();
			Item item;
			for (int i = 40; i <=99; i++) {
				item=new Item();
				item.setId(String.valueOf(i));
				item.setNombre(String.valueOf(i));
				lista.add(item);
			}
			request.setAttribute("listaValores",lista);    	
		    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
		}
		fc.setIdEstab(calif.getEstablecimiento().getId().toString());
			
		/*try {
			Animal an = AnimalDAO.findByPrimaryKey(Long.valueOf(fc.getIdAninal()));
			if(an.getEstablecimiento()==null) {
					if((an.getPropietario().equals(calif.getEstablecimiento().getPropietario())) ){
							an.setEstablecimiento(calif.getEstablecimiento());
							an.setEstancia(calif.getEstablecimiento().getEstancia());
							HibernateFactory.getSession().update(an);
					}
					else{
						
					}
					
			}
			
  		} catch (HibernateException e) {
  			throw new ErrorFatal("No se pudo actualizar el animal",e);
  		}*/
		
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas); 
		return mapping.findForward("seguir");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CalificacionForm fc = (CalificacionForm)form;
		
		Calificacion calif = CalificacionDAO.findByPrimaryKey(new Long(fc.getIdBol()));
		if(calif!=null){
			if(!fc.getFechaPar().equals("")){
				String f = fc.getFechaPar().replace("-","/");
				Date fechaI = new Date();
				fechaI= this.parse(f,"dd/MM/yyyy");
				if(fechaI == null){
					request.setAttribute("action","update");
					request.setAttribute("error","LA FECHA TIENE FORMATO ERRONEO");
				    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
					//return mapping.findForward("boleta");
				}
			}
			
			if(fc.getNumModel().equals("2"))
				cargarCalifcacion(fc,calif);
			else if(fc.getNumModel().equals("3"))
				cargarCalifcacion3(fc,calif);
			CalificacionDAO.update(calif);
		}
		else{
			request.setAttribute("action","update");
			request.setAttribute("error","LA BOLETA HA SIDO ELIMINADA POR OTRO USUARIO");
		    return ((fc.getNumModel().equals("2"))?(mapping.findForward("boleta")):(mapping.findForward("boletaM3")));
			//return mapping.findForward("boleta");
		}
		
		return mapping.findForward("filtro");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	return mapping.findForward("filtro");

}
	private void cargarCalifcacion(CalificacionForm fc, Calificacion calif) throws ParseException {
		Boleta bole = fc.getBoleta();
		if(calif.getModeloCalificacion()==null)
			calif.setModeloCalificacion(ModeloCalificacionDAO.findByPrimaryKey(new Long(2)));
		Set partes = calif.getModeloCalificacion().getParteCalificacions();
		calif.setBoleta(new Integer(fc.getNumBoleta()));
		calif.setEspecial(fc.getEspecial());
		if(!fc.getFechaPar().equals("")){
			SimpleDateFormat gf = new SimpleDateFormat("dd/MM/yyyy");
			String f = fc.getFechaPar().replace("-","/");
			calif.setInicioLactancia(gf.parse(f));
			calif.setNumeroLactancia(new Integer(fc.getNumParto()));
		}
		else//hay que tomar una decision
		{
			//calif.setInicioLactancia(new Date());
			//calif.setNumeroLactancia(1);
			
		}
		Map<ParteCalificacion,CatPuntajeValor> partesAux = new HashMap<ParteCalificacion,CatPuntajeValor>();
		Map<CaracteristicaCalificacion,Integer> caracAux = new HashMap<CaracteristicaCalificacion,Integer>();
		Map<DefectoCalificacion,Integer> defeAux = new HashMap<DefectoCalificacion,Integer>();
		Iterator it = partes.iterator();
		calif.setCatPuntaje(bole.getNivelPun());
		calif.setPuntaje(new Integer(bole.getValorPun()));
		calif.setComentarios(fc.getComentarios());
		while(it.hasNext()){
			ParteCalificacion parte = (ParteCalificacion)it.next();
			if(parte.getCodigo().equals("E")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setCategoriaPuntaje(bole.getNivelEst());
				cat.setValor(new Integer(bole.getValorEst()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Estatura"))
						caracAux.put(c,new Integer(bole.getCaracEst1()));
					if(c.getNombre().equals("Tren anterior"))
						caracAux.put(c,new Integer(bole.getCaracEst2()));
					if(c.getNombre().equals("Tamaño"))
						caracAux.put(c,new Integer(bole.getCaracEst3()));
					if(c.getNombre().equals("Ancho de pecho"))
						caracAux.put(c,new Integer(bole.getCaracEst4()));
					if(c.getNombre().equals("Profundidad del cuerpo"))
						caracAux.put(c,new Integer(bole.getCaracEst5()));
					if(c.getNombre().equals("Fortaleza de lomo"))
						caracAux.put(c,new Integer(bole.getCaracEst6()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("Cara torcida")&&(!bole.getDefeEst1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst1()));
					if(d.getNombre().equals("Cabeza indeseable")&&(!bole.getDefeEst2().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst2()));
					if(d.getNombre().equals("Retroescápula débil")&&(!bole.getDefeEst3().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst3()));
					if(d.getNombre().equals("Línea dorsal débil")&&(!bole.getDefeEst4().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst4()));
					if(d.getNombre().equals("Falta de armonía")&&(!bole.getDefeEst5().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst5()));
					if(d.getNombre().equals("Falta arco costal")&&(!bole.getDefeEst6().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst6()));
					if(d.getNombre().equals("Lomo bajo")&&(!bole.getDefeEst7().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst7()));
					if(d.getNombre().equals("Reg. card. estrecha")&&(!bole.getDefeEst8().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst8()));
					if(d.getNombre().equals("Frágil")&&(!bole.getDefeEst9().equals("")))
						defeAux.put(d,new Integer(bole.getDefeEst9()));
				}
			}
			if(parte.getCodigo().equals("F")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setCategoriaPuntaje(bole.getNivelGru());
				cat.setValor(new Integer(bole.getValorGru()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Colocacion de isquiones"))
						caracAux.put(c,new Integer(bole.getCaracGru1()));
					if(c.getNombre().equals("Separación de isquiones"))
						caracAux.put(c,new Integer(bole.getCaracGru2()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("Ano adelantado")&&(!bole.getDefeGru1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru1()));
					if(d.getNombre().equals("Ins. Cola baja")&&(!bole.getDefeGru2().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru2()));
					if(d.getNombre().equals("Ins. Cola alta")&&(!bole.getDefeGru3().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru3()));
					if(d.getNombre().equals("Ins. Cola adelantada")&&(!bole.getDefeGru4().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru4()));
					if(d.getNombre().equals("Cola torcida")&&(!bole.getDefeGru5().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru5()));
					if(d.getNombre().equals("Art. demasiado atrás")&&(!bole.getDefeGru6().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru6()));
				}
			}
			if(parte.getCodigo().equals("G")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setCategoriaPuntaje(bole.getNivelPat());
				cat.setValor(new Integer(bole.getValorPat()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Ángulo de pezuña"))
						caracAux.put(c,new Integer(bole.getCaracPat1()));
					if(c.getNombre().equals("Profundidad de talón"))
						caracAux.put(c,new Integer(bole.getCaracPat2()));
					if(c.getNombre().equals("Calidad de hueso"))
						caracAux.put(c,new Integer(bole.getCaracPat3()));
					if(c.getNombre().equals("Coloc. patas tras.(costado)"))
						caracAux.put(c,new Integer(bole.getCaracPat4()));
					if(c.getNombre().equals("Coloc. patas tras.(de atrás)"))
						caracAux.put(c,new Integer(bole.getCaracPat5()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("Cuartillas débiles")&&(!bole.getDefePat1().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat1()));
					if(d.getNombre().equals("Calambres")&&(!bole.getDefePat2().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat2()));
					if(d.getNombre().equals("Garrones toscos")&&(!bole.getDefePat3().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat3()));
					if(d.getNombre().equals("Dedos abiertos")&&(!bole.getDefePat4().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat4()));
					if(d.getNombre().equals("Postura indeseable")&&(!bole.getDefePat5().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat5()));
					if(d.getNombre().equals("Falta de hueso")&&(!bole.getDefePat6().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat6()));
					if(d.getNombre().equals("Pez. ant. hacia afuera")&&(!bole.getDefePat7().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat7()));
				}
			}
			if(parte.getCodigo().equals("H")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setCategoriaPuntaje(bole.getNivelSis());
				cat.setValor(new Integer(bole.getValorSis()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Profundidad de ubre"))
						caracAux.put(c,new Integer(bole.getCaracSis1()));
					if(c.getNombre().equals("Textura"))
						caracAux.put(c,new Integer(bole.getCaracSis2()));
					if(c.getNombre().equals("Ligamento medio"))
						caracAux.put(c,new Integer(bole.getCaracSis3()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("Cuarteada")&&(!bole.getDefeSis1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis1()));
					if(d.getNombre().equals("Oblicua")&&(!bole.getDefeSis2().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis2()));
					if(d.getNombre().equals("Pesada adelante")&&(!bole.getDefeSis3().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis3()));
				}
			}
			if(parte.getCodigo().equals("I")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setCategoriaPuntaje(bole.getNivelAnt());
				cat.setValor(new Integer(bole.getValorAnt()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Inserción anterior"))
						caracAux.put(c,new Integer(bole.getCaracAnt1()));
					if(c.getNombre().equals("Colocación del pezón"))
						caracAux.put(c,new Integer(bole.getCaracAnt2()));
					if(c.getNombre().equals("Largo del pezón"))
						caracAux.put(c,new Integer(bole.getCaracAnt3()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("Abultada")&&(!bole.getDefeAnt1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeAnt1()));
					if(d.getNombre().equals("Pesada")&&(!bole.getDefeAnt2().equals("")))
						defeAux.put(d,new Integer(bole.getDefeAnt2()));
					if(d.getNombre().equals("Desbalanceada")&&(!bole.getDefeAnt3().equals("")))
						defeAux.put(d,new Integer(bole.getDefeAnt3()));
					if(d.getNombre().equals("Corta")&&(!bole.getDefeAnt4().equals("")))
						defeAux.put(d,new Integer(bole.getDefeAnt4()));
					if(d.getNombre().equals("Pezones desviados")&&(!bole.getDefeAnt5().equals("")))
						defeAux.put(d,new Integer(bole.getDefeAnt5()));
					if(d.getNombre().equals("Pezón palmípedo")&&(!bole.getDefeAnt6().equals("")))
						defeAux.put(d,new Integer(bole.getDefeAnt6()));
					if(d.getNombre().equals("Cuarto ciego")&&(!bole.getDefeAnt7().equals("")))
						defeAux.put(d,new Integer(bole.getDefeAnt7()));
				}
			}
			if(parte.getCodigo().equals("J")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setCategoriaPuntaje(bole.getNivelPos());
				cat.setValor(new Integer(bole.getValorPos()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Altura de inserción"))
						caracAux.put(c,new Integer(bole.getCaracPos1()));
					if(c.getNombre().equals("Ancho de inserción"))
						caracAux.put(c,new Integer(bole.getCaracPos2()));
					if(c.getNombre().equals("Colocación de pezones"))
						caracAux.put(c,new Integer(bole.getCaracPos3()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("Desbalanceada")&&(!bole.getDefePos1().equals("")))
						defeAux.put(d,new Integer(bole.getDefePos1()));
					if(d.getNombre().equals("Corta")&&(!bole.getDefePos2().equals("")))
						defeAux.put(d,new Integer(bole.getDefePos2()));
					if(d.getNombre().equals("Pezones desviados")&&(!bole.getDefePos3().equals("")))
						defeAux.put(d,new Integer(bole.getDefePos3()));
					if(d.getNombre().equals("Pez. demasiado atrás")&&(!bole.getDefePos4().equals("")))
						defeAux.put(d,new Integer(bole.getDefePos4()));
					if(d.getNombre().equals("Pezón palmípedo")&&(!bole.getDefePos5().equals("")))
						defeAux.put(d,new Integer(bole.getDefePos5()));
					if(d.getNombre().equals("Cuarto ciego")&&(!bole.getDefePos6().equals("")))
						defeAux.put(d,new Integer(bole.getDefePos6()));
				}
			}
			if(parte.getCodigo().equals("D")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setCategoriaPuntaje(bole.getNivelCar());
				cat.setValor(new Integer(bole.getValorCar()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Forma lechera"))
						caracAux.put(c,new Integer(bole.getCaracCar1()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("Costillas juntas")&&(!bole.getDefeCar1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeCar1()));
				}
			}
		}
		calif.setCalificacionDePartes(partesAux);
		calif.setCalificacionDeCaracteristicas(caracAux);
		calif.setCalificacionDeDefectos(defeAux);
		calif.setEclo(calif.getEstablecimiento().getEclo());
	}
	
	private void cargarCalifcacion3(CalificacionForm fc, Calificacion calif) throws ParseException {
		Boleta bole = fc.getBoleta();
		if(calif.getModeloCalificacion()==null)
			calif.setModeloCalificacion(ModeloCalificacionDAO.findByPrimaryKey(new Long(3)));
		Set partes = calif.getModeloCalificacion().getParteCalificacions();
		calif.setBoleta(new Integer(fc.getNumBoleta()));
		calif.setEspecial(fc.getEspecial());
		if(!fc.getFechaPar().equals("")){
			SimpleDateFormat gf = new SimpleDateFormat("dd/MM/yyyy");
			String f = fc.getFechaPar().replace("-","/");
			calif.setInicioLactancia(gf.parse(f));
			calif.setNumeroLactancia(new Integer(fc.getNumParto()));
		}
		else//hay que tomar una decision
		{
			//calif.setInicioLactancia(new Date());
			//calif.setNumeroLactancia(1);
			
		}
		Map<ParteCalificacion,CatPuntajeValor> partesAux = new HashMap<ParteCalificacion,CatPuntajeValor>();
		Map<CaracteristicaCalificacion,Integer> caracAux = new HashMap<CaracteristicaCalificacion,Integer>();
		Map<DefectoCalificacion,Integer> defeAux = new HashMap<DefectoCalificacion,Integer>();
		Iterator it = partes.iterator();
		calif.setCatPuntaje(bole.getNivelPun());
		calif.setPuntaje(new Integer(bole.getValorPun()));
		calif.setComentarios(fc.getComentarios());
		while(it.hasNext()){
			ParteCalificacion parte = (ParteCalificacion)it.next();
			if(parte.getCodigo().equals("A")){
				CatPuntajeValor cat = new CatPuntajeValor();
				cat.setValor(new Integer(bole.getValorGru()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Declive Grupa"))
						caracAux.put(c,new Integer(bole.getCaracGru1()));
					else if(c.getNombre().equals("Sep. Isquiones"))
						caracAux.put(c,new Integer(bole.getCaracGru2()));
					else if(c.getNombre().equals("Fortaleza Lomo"))
						caracAux.put(c,new Integer(bole.getCaracGru3()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("ano adelantado")&&(!bole.getDefeGru1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru1()));
					else if(d.getNombre().equals("cola adelantada")&&(!bole.getDefeGru2().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru2()));
					else if(d.getNombre().equals("ins. cola baja")&&(!bole.getDefeGru3().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru3()));
					else if(d.getNombre().equals("ins. cola alta")&&(!bole.getDefeGru4().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru4()));
					else if(d.getNombre().equals("cola torcida")&&(!bole.getDefeGru5().equals("")))
						defeAux.put(d,new Integer(bole.getDefeGru5()));
				}
			} else if(parte.getCodigo().equals("B")){
				CatPuntajeValor cat = new CatPuntajeValor();
				//cat.setCategoriaPuntaje(bole.getNivelGru());
				cat.setValor(new Integer(bole.getValorSis()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Prof. Ubre"))
						caracAux.put(c,new Integer(bole.getCaracSis1()));
					else if(c.getNombre().equals("Textura Ubre"))
						caracAux.put(c,new Integer(bole.getCaracSis2()));
					else if(c.getNombre().equals("Lig. Medio"))
						caracAux.put(c,new Integer(bole.getCaracSis3()));
					else if(c.getNombre().equals("Inserción Ant."))
						caracAux.put(c,new Integer(bole.getCaracSis4()));
					else if(c.getNombre().equals("Coloc. Pez. Ant."))
						caracAux.put(c,new Integer(bole.getCaracSis5()));
					else if(c.getNombre().equals("Alt. Ins. Post."))
						caracAux.put(c,new Integer(bole.getCaracSis6()));
					else if(c.getNombre().equals("Anch. Ins. Post."))
						caracAux.put(c,new Integer(bole.getCaracSis7()));
					else if(c.getNombre().equals("Coloc. Pez. Post."))
						caracAux.put(c,new Integer(bole.getCaracSis8()));
					else if(c.getNombre().equals("Largo Pezones"))
						caracAux.put(c,new Integer(bole.getCaracSis9()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("oblicua")&&(!bole.getDefeSis1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis1()));
					else if(d.getNombre().equals("oblicua al revés")&&(!bole.getDefeSis2().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis2()));
					else if(d.getNombre().equals("anterior abultada")&&(!bole.getDefeSis3().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis3()));
					else if(d.getNombre().equals("anterior corta")&&(!bole.getDefeSis4().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis4()));
					else if(d.getNombre().equals("posterior corta")&&(!bole.getDefeSis5().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis5()));
					else if(d.getNombre().equals("mala forma")&&(!bole.getDefeSis6().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis6()));
					else if(d.getNombre().equals("cuarto desbalanceado posterior")&&(!bole.getDefeSis7().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis7()));
					else if(d.getNombre().equals("cuarto desbalanceado anterior")&&(!bole.getDefeSis21().equals("")))
					defeAux.put(d,new Integer(bole.getDefeSis21()));
					else if(d.getNombre().equals("cuarto ciego")&&(!bole.getDefeSis8().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis8()));
					else if(d.getNombre().equals("pezón palmípedo")&&(!bole.getDefeSis9().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis9()));
					else if(d.getNombre().equals("juntos lateralmente")&&(!bole.getDefeSis10().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis10()));
					else if(d.getNombre().equals("p. post. muy atrás")&&(!bole.getDefeSis11().equals("")))
						defeAux.put(d,new Integer(bole.getDefeSis11()));
				}
			}else if(parte.getCodigo().equals("C")){
				CatPuntajeValor cat = new CatPuntajeValor();
				//cat.setCategoriaPuntaje(bole.getNivelPat());
				cat.setValor(new Integer(bole.getValorPat()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Ángulo de Pez."))
						caracAux.put(c,new Integer(bole.getCaracPat1()));
					else if(c.getNombre().equals("Calid. Hueso"))
						caracAux.put(c,new Integer(bole.getCaracPat2()));
					else if(c.getNombre().equals("Patas de Costado"))
						caracAux.put(c,new Integer(bole.getCaracPat3()));
					else if(c.getNombre().equals("Locomoción"))
						caracAux.put(c,new Integer(bole.getCaracPat4()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("dedo tirabuzón")&&(!bole.getDefePat1().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat1()));
					if(d.getNombre().equals("cuartillas débiles")&&(!bole.getDefePat2().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat2()));
					if(d.getNombre().equals("garrón avejigado")&&(!bole.getDefePat3().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat3()));
					if(d.getNombre().equals("falta de hueso")&&(!bole.getDefePat4().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat4()));
					if(d.getNombre().equals("calambres")&&(!bole.getDefePat5().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat5()));
					if(d.getNombre().equals("art. coxofemoral atrás")&&(!bole.getDefePat6().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat6()));
					if(d.getNombre().equals("pez. ant. hacia fuera")&&(!bole.getDefePat7().equals("")))
						defeAux.put(d,new Integer(bole.getDefePat7()));
				}
			}else if(parte.getCodigo().equals("D")){
				CatPuntajeValor cat = new CatPuntajeValor();
				//cat.setCategoriaPuntaje(bole.getNivelSis());
				cat.setValor(new Integer(bole.getValorFor()));
				partesAux.put(parte,cat);
				Iterator itc = parte.getCaracteristicaCalificacions().iterator();
				while(itc.hasNext()){
					CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
					if(c.getNombre().equals("Estatura"))
						caracAux.put(c,new Integer(bole.getCaracFor1()));
					else if(c.getNombre().equals("Anch. Pecho"))
						caracAux.put(c,new Integer(bole.getCaracFor2()));
					else if(c.getNombre().equals("Prof. Cuerpo"))
						caracAux.put(c,new Integer(bole.getCaracFor3()));
					else if(c.getNombre().equals("Angulosidad"))
						caracAux.put(c,new Integer(bole.getCaracFor4()));
					else if(c.getNombre().equals("Cond. Corporal"))
						caracAux.put(c,new Integer(bole.getCaracFor5()));
				}
				Iterator itd = parte.getDefectoCalificacions().iterator();
				while(itd.hasNext()){
					DefectoCalificacion d = (DefectoCalificacion)itd.next();
					if(d.getNombre().equals("cara torcida")&&(!bole.getDefeFor1().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor1()));
					else if(d.getNombre().equals("mandíbula malformada")&&(!bole.getDefeFor2().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor2()));
					else if(d.getNombre().equals("reg. cardíaca estrecha")&&(!bole.getDefeFor3().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor3()));
					else if(d.getNombre().equals("retroescápula débil")&&(!bole.getDefeFor4().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor4()));
					else if(d.getNombre().equals("dorso débil")&&(!bole.getDefeFor5().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor5()));
					else if(d.getNombre().equals("tren anterior bajo")&&(!bole.getDefeFor6().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor6()));
					else if(d.getNombre().equals("falta arco costal")&&(!bole.getDefeFor7().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor7()));
					else if(d.getNombre().equals("desarmónica")&&(!bole.getDefeFor8().equals("")))
						defeAux.put(d,new Integer(bole.getDefeFor8()));

				}
			}
		}
		calif.setCalificacionDePartes(partesAux);
		calif.setCalificacionDeCaracteristicas(caracAux);
		calif.setCalificacionDeDefectos(defeAux);
		calif.setEclo(calif.getEstablecimiento().getEclo());
	}
	
	public ActionForward initView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String idBol= (String) request.getParameter("id");
		CalificacionForm fc = (CalificacionForm)form;
		Calificacion calif = CalificacionDAO.findByPrimaryKey(new Long(idBol));
		fc.setSexo(calif.getAnimal().esHembra() ? "H" : "M");
		fc.setIdBol(idBol);
		fc.setFecha(calif.getFecha().toString());
		request.setAttribute("action","view");
		if(calif.getModeloCalificacion().getId().equals(new Long(2))){
			cargarForm(fc,calif,"update");
			return mapping.findForward("boletaM2");
		}else if(calif.getModeloCalificacion().getId().equals(new Long(3))){
			cargarFormM3(fc,calif,"update");
			//TODO ver el forward m3
			return mapping.findForward("boletaM3View");
		}else{
			cargarFormM1(fc,calif);
			return mapping.findForward("boletaM1");
		}

	}
	
	private void cargarFormM1(CalificacionForm fc, Calificacion calif) {
		Establecimiento estab = calif.getEstablecimiento();
		Animal an = calif.getAnimal();
		fc.setIdAninal(an.getId().toString());
		fc.setIdEstabAn(an.getEstablecimiento().getId().toString());
		fc.setIdEcloAn(an.getEstablecimiento().getEclo().getId().toString());
		fc.setIdPropAn(an.getPropietario().getId().toString());
		fc.setFechaNac(DateUtils.format(an.getFechaNac(),"dd/MM/yyyy HH:mm:ss"));
		if(calif.getBoleta()!=null)
			fc.setNumBoleta(calif.getBoleta().toString());
		fc.setRp(an.getRP());
		//if(an.getUltimaCalificacion()!=null)
		//	fc.setPuntAnt(an.getUltimaCalificacion().getPuntaje().toString());
		Integer puntajeAnt = CalificacionDAO.getPuntajeAnterior(calif,an);
		if(puntajeAnt!=null)
			fc.setPuntAnt(puntajeAnt.toString());
		if(calif.getCalificador()!=null){
			fc.setIdCalif(calif.getCalificador().getId().toString());
			fc.setNombreCalif(calif.getCalificador().getNombreCompleto());
		}
		fc.setIdEstab(estab.getId().toString());
		fc.setIdEclo(estab.getEclo().getId().toString());
		fc.setIdProp(estab.getEstancia().getPropietario().getId().toString());
		fc.setIdEstancia(estab.getEstancia().getId().toString());
		fc.setNombreEstancia(estab.getEstancia().getNombreContacto());
		fc.setNombreEstab(estab.getNombreContacto());
		fc.setNombreEclo(estab.getEclo().getNombreContacto());
		fc.setNombreProp(estab.getEstancia().getPropietario().getNombreContacto());
		//fc.setEspecial(calif.getEspecial());
		fc.setFechaPar(calif.getInicioLactancia().toString());
		fc.setNumParto(calif.getNumeroLactancia().toString());
		Boleta bol = new Boleta();
		Map mapPartes = calif.getCalificacionDePartes();
		Map mapCarac = calif.getCalificacionDeCaracteristicas();
		Map mapDefe = calif.getCalificacionDeDefectos();
		if(calif.getModeloCalificacion()!=null){
			bol.setNivelPun(calif.getCatPuntaje());
			bol.setValorPun(calif.getPuntaje().toString());
			fc.setComentarios(calif.getComentarios());
			Set partes = calif.getModeloCalificacion().getParteCalificacions();
			Iterator it = partes.iterator();
			while(it.hasNext()){
				ParteCalificacion parte = (ParteCalificacion)it.next();
				if(parte.getCodigo().equals("E")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Pecho")&& mapCarac.get(c)!=null)
							bol.setCaracEst1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Lomo")&& mapCarac.get(c)!=null)
							bol.setCaracEst2(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							
								if(d.getNombre().equals("Paletas Desprendidas"))
									bol.setDefeEst1(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Paletas Pesadas"))
									bol.setDefeEst2(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Retrosc. Débil"))
									bol.setDefeEst3(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Dorso Débil"))
									bol.setDefeEst4(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Reg. Card. Estrecha"))
									bol.setDefeEst5(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Poco Profundo"))
									bol.setDefeEst6(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Falta Arco Costal"))
									bol.setDefeEst7(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Costillas Juntas"))
									bol.setDefeEst8(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Lomo Angosto"))
									bol.setDefeEst9(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Lomo Bajo"))
									bol.setDefeEst10(getDefecto(mapDefe,d));
						
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelEst(cat.getCategoriaPuntaje());
						bol.setValorEst(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("F")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Amplitud")&& mapCarac.get(c)!=null)
							bol.setCaracGru1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Colocación Isquiones")&& mapCarac.get(c)!=null)
							bol.setCaracGru2(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							
								if(d.getNombre().equals("Grupa Corta"))
									bol.setDefeGru1(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Sacro Tosco"))
									bol.setDefeGru2(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Sacro Alto"))
									bol.setDefeGru3(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Isquiones Juntos"))
									bol.setDefeGru4(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Cola Torcida"))
									bol.setDefeGru5(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Coxofemoral Baja"))
									bol.setDefeGru6(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Inserción Cola Alta"))
									bol.setDefeGru7(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Inserción Cola Tosca"))
									bol.setDefeGru8(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Inserción Cola Adelantada"))
									bol.setDefeGru9(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Inserción Cola Rígida"))
									bol.setDefeGru10(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Inserción Cola Baja"))
									bol.setDefeGru11(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ano Adelantado"))
									bol.setDefeGru12(getDefecto(mapDefe,d));;
							
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelGru(cat.getCategoriaPuntaje());
						bol.setValorGru(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("G")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Talones Posteriores")&& mapCarac.get(c)!=null)
							bol.setCaracPat1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Hueso")&& mapCarac.get(c)!=null)
							bol.setCaracPat2(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Aplomos Posteriores")&& mapCarac.get(c)!=null)
							bol.setCaracPat3(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							
								if(d.getNombre().equals("Cuartillas Débiles"))
									bol.setDefePat1(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Falta Hueso"))
									bol.setDefePat2(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Garrones Juntos"))
									bol.setDefePat3(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Calambres"))
									bol.setDefePat4(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Nalgas Gruesas"))
									bol.setDefePat5(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Garrones Toscos"))
									bol.setDefePat6(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezuñas Anter. Hacia Afuera"))
									bol.setDefePat7(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pez. Ant. Dedos Abiertos"))
									bol.setDefePat8(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezuñas Post. Hacia Afuera"))
									bol.setDefePat9(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pez. Post. Dedos Abiertos"))
									bol.setDefePat10(getDefecto(mapDefe,d));
							
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelPat(cat.getCategoriaPuntaje());
						bol.setValorPat(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("H")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Textura")&& mapCarac.get(c)!=null)
							bol.setCaracSis1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Inserción Anterior")&& mapCarac.get(c)!=null)
							bol.setCaracSis2(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Inserción Posterior")&& mapCarac.get(c)!=null)
							bol.setCaracSis3(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Ligamento Medio")&& mapCarac.get(c)!=null)
							bol.setCaracSis4(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Colocación Pezones Anteriores")&& mapCarac.get(c)!=null)
							bol.setCaracSis5(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Colocación Pezones Posteriores")&& mapCarac.get(c)!=null)
							bol.setCaracSis6(((Integer)mapCarac.get(c)).toString());
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							
								if(d.getNombre().equals("Ubre muy Profunda"))
									bol.setDefeSis1(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Olicua"))
									bol.setDefeSis2(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Pesada Adelante"))
									bol.setDefeSis3(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Ant. Desbalanceada"))
									bol.setDefeSis4(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Ant. Corta"))
									bol.setDefeSis5(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Cuarteada"))
									bol.setDefeSis6(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre con Ins. Post. Angosta"))
									bol.setDefeSis7(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Abultada Adelante"))
									bol.setDefeSis8(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Ant. Desbalanceada"))
									bol.setDefeSis9(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Ubre Post. Corta"))
									bol.setDefeSis10(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones juntos Lateral"))
									bol.setDefeSis11(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Palmípedo"))
									bol.setDefeSis12(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Ant. Largos"))
									bol.setDefeSis13(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Ant. Embudo"))
									bol.setDefeSis14(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Ant. Desviados"))
									bol.setDefeSis15(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Ciego"))
									bol.setDefeSis16(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Perforada de Lado"))
									bol.setDefeSis17(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Post. Largos"))
									bol.setDefeSis18(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Post. Embudo"))
									bol.setDefeSis19(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Pezones Post. Desviados"))
									bol.setDefeSis20(getDefecto(mapDefe,d));
							
						}
					}
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelSis(cat.getCategoriaPuntaje());
						bol.setValorSis(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("I")){
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelAnt(cat.getCategoriaPuntaje());
						bol.setValorAnt(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("J")){
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelPos(cat.getCategoriaPuntaje());
						bol.setValorPos(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("D")){
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelCar(cat.getCategoriaPuntaje());
						bol.setValorCar(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("C")){
					CatPuntajeValor cat = (CatPuntajeValor) mapPartes.get(parte);
					if(cat!=null){
						bol.setNivelAsp(cat.getCategoriaPuntaje());
						bol.setValorAsp(cat.getValor().toString());
					}
				}
				if(parte.getCodigo().equals("A")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Alzada")&& mapCarac.get(c)!=null)
							bol.setCaracGen1(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Tamaño")&& mapCarac.get(c)!=null)
							bol.setCaracGen2(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Estilo")&& mapCarac.get(c)!=null)
							bol.setCaracGen3(((Integer)mapCarac.get(c)).toString());
						if(c.getNombre().equals("Aspecto Lechero")&& mapCarac.get(c)!=null)
							bol.setCaracGen4(((Integer)mapCarac.get(c)).toString());
					}
				}
				if(parte.getCodigo().equals("B")){
					Iterator itc = parte.getCaracteristicaCalificacions().iterator();
					while(itc.hasNext()){
						CaracteristicaCalificacion c = (CaracteristicaCalificacion)itc.next();
						if(c.getNombre().equals("Cabeza")&& mapCarac.get(c)!=null)
							bol.setCaracTre1(((Integer)mapCarac.get(c)).toString());
						
					}
					if(parte.getDefectoCalificacions()!=null){
						Iterator itd = parte.getDefectoCalificacions().iterator();
						while(itd.hasNext()){
							DefectoCalificacion d = (DefectoCalificacion)itd.next();
							
								if(d.getNombre().equals("Angosta"))
									bol.setDefeTre1(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Tosca"))
									bol.setDefeTre2(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Corta"))
									bol.setDefeTre3(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Cara Torcida"))
									bol.setDefeTre4(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Mandibula Débil"))
									bol.setDefeTre5(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Atípica"))
									bol.setDefeTre6(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Corto"))
									bol.setDefeTre7(getDefecto(mapDefe,d));
								if(d.getNombre().equals("Garganta Gruesa"))
									bol.setDefeTre8(getDefecto(mapDefe,d));
							
						}
					}
				}
			}
			fc.setBoleta(bol);
			fc.setPartes(calif.getCalificacionDePartes());
			fc.setCaracteristicas(calif.getCalificacionDeCaracteristicas());
			fc.setDefectos(calif.getCalificacionDeDefectos());
			fc.setModelo(calif.getModeloCalificacion());
		}
		
	}
	private String getDefecto(Map map,DefectoCalificacion d){
		
		if(map.containsKey(d)){
			String valor = ((Integer)map.get(d)).toString();
			if(valor.equals(""))
				return "-";
			if(valor.equals("1"))
				return "L";
			if(valor.equals("2"))
				return "G";
		}
		return "-";
		
	}
	public Date parse(String s, String s1) 
	{
	   
		try
	    {
	        SimpleDateFormat simpledateformat;
	        (simpledateformat = new SimpleDateFormat(s1 != null ? s1 : "dd-MM-yyyy HH:mm:ss")).setLenient(false);
	      
	        return simpledateformat.parse(s);
	    }
	    catch(Exception exception)
	    {
	        return null;
	    }
	}
	
	public ActionForward descargarReporte(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String id = request.getParameter("id");
		String rango ="";
		if(id==null){
			id= (String) request.getSession().getAttribute("ids");
			rango = request.getParameter("rango");
		}
		generarReporte(response,id,rango);
		
		return null;
		
	}
	
	private void generarReporte(HttpServletResponse response,String ids,String rango) throws Exception{
		String REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
		String IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		REPORTES_DIR = this.getContextPath(REPORTES_DIR);
		IMAGENES_DIR = this.getContextPath(IMAGENES_DIR);
		ProjectReport report = new ProjectReport(REPORTES_DIR, IMAGENES_DIR);
		//<"String","String">
		Map parametros = new HashMap();
		String[] iden = ids.split(",");
		String nombreGenerado = "";
		if(iden.length == 1){
			parametros.put("calificacion",ids);
			Calificacion ca = CalificacionDAO.findByPrimaryKey(new Long(iden[0]));
			nombreGenerado = ca.getBoleta()+"_calificacion.pdf";
		}
		else{
			//rango = "0-998";
			int para1 = 0;
			int para2 = 0;
			if(!rango.equals("")){
				String[] rangos = rango.split("-");
				para1 = Integer.parseInt(rangos[0]);
				para2 = Integer.parseInt(rangos[1]);
			}
			
			
			String re = "";
			for (int i = para1; i <= para2; i++) {
				re+=iden[i]+",";
			}
	    	if(re.length()>=1)
	    		 re=re.substring(0,re.length()-1);
	    	parametros.put("calificacion",re);
			nombreGenerado =  "calificaciones.pdf";
		}
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(nombreGenerado));
		try {
			InputStream is=	new ByteArrayInputStream(report.makeReportBoleta(parametros));
			PrintWriter pw = response.getWriter();
			int c = -1;
			while ((c = is.read()) != -1) {
				pw.print((char) c);
			}
			is.close();
			pw.flush();
			pw = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getContextPath(String subPath){
		return getServlet().getServletContext().getRealPath(subPath);
	}
	public static Object getLock() {
		return lock;
	}
	
}
