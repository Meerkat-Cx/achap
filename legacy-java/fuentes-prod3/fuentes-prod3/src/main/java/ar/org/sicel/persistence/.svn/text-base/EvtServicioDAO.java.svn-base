package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.types.STTipoServicio;
import ar.org.sicel.upload.dao.ConstantsUpload;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtServicio.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtServicio
 */
public abstract class EvtServicioDAO {
    // ---------------- create method --------------------

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtServicio object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtServicio findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtServicio object = (EvtServicio) session.get(EvtServicio.class, id);

        return object;
    }
    
    public static void checkEdadAdmisibleServNatural(Hembra animal,Date fechaServ, List <ProcMsg> msgs) throws ExcepcionIntegridad{
     	int edadMinima = animal.getParametroRazaAsInteger(Raza.EDAD_MINIMA_SERVICIO_NATURAL);
     	int edadEnDias = animal.getEdadEnDiasAl(fechaServ);
     	if (edadEnDias < edadMinima) {
     	   	if (Configuracion.getValorReglaProceso(CONF.EDAD_MINIMA_PARA_SERVICIO_NATURAL,fechaServ)) {
     	   		throw new ExcepcionIntegridad(MENSAJES.EDAD_MINIMA_INVALIDA_SERVICIO,new String[] {String.valueOf(edadMinima), String.valueOf(edadEnDias)});
     	   	} else {
    		    ProcMsg warn = ProcMsgDAO.create(MENSAJES.EDAD_MINIMA_INVALIDA_SERVICIO,ProcMsg.WARNING,new String[] {String.valueOf(edadMinima), String.valueOf(edadEnDias)});
    		    msgs.add(warn);
     	   	}
     	}
    	
    }

    public static void checkEdadAdmisibleServArtificial(Hembra animal,Date fechaServ, List <ProcMsg> msgs) throws ExcepcionIntegridad {
     	int edadMinima = animal.getParametroRazaAsFloat(Raza.EDAD_MINIMA_SERVICIO_ARTIFICIAL).intValue();
     	int edadEnDias = animal.getEdadEnDiasAl(fechaServ);
     	if (edadEnDias < edadMinima) {
     	   	if (Configuracion.getValorReglaProceso(CONF.EDAD_MINIMA_PARA_SERVICIO_ARTIFICIAL,fechaServ)) {
     	   		throw new ExcepcionIntegridad(MENSAJES.EDAD_MINIMA_INVALIDA_SERVICIO,new String[] {String.valueOf(edadMinima), String.valueOf(edadEnDias)});
     	   	} else {
    		    ProcMsg warn = ProcMsgDAO.create(MENSAJES.EDAD_MINIMA_INVALIDA_SERVICIO,ProcMsg.WARNING,new String[] {String.valueOf(edadMinima), String.valueOf(edadEnDias)});
    		    msgs.add(warn);
     	   	}
     	}
    }

    public static void checkServiciosSolapados(Hembra hembra, Date fechaServicio, List<ProcMsg> msgs, Date fechaFinCorral,Integer diasDeGestacion) throws ExcepcionIntegridad {
    	/////////hacer a modo de warnig!!!!!!!!!!
    	//TODO el parametro paso a la Raza (29/03/2007)
    	int gMin = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MINIMA));
    	int gMax = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MAXIMA));
    	long fe = fechaServicio.getTime() - diasDeGestacion;
		Date fecha = new Date(fe);
    	int cicloCeloMinimo = (gMax - gMin) /2;
    	List serviciosSolapados = null;
    	serviciosSolapados = hembra.getEventosEntre(DateUtils.menos(fechaServicio,cicloCeloMinimo),DateUtils.mas(fechaFinCorral,cicloCeloMinimo),EvtAnimal.EVT_TIPO_SVC);
		if (!serviciosSolapados.isEmpty()) {
			if (Configuracion.getValorReglaProceso(CONF.ACEPTA_SERVICIOS_SOLAPADOS,fechaServicio)) {
				Iterator it = serviciosSolapados.iterator();
				while (it.hasNext()) {
					EvtServicio otro = (EvtServicio)it.next();
					ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_SOLAPADO,ProcMsg.WARNING, new String[]{
                                    (otro.getId()!=null?otro.getId().toString():"null"),String.format("%tF",new Object[]{ otro.getFecha() }
                                      )
                                    }
                            );
					msgs.add(msg);
				}
			} else {
				EvtServicio otro = (EvtServicio)serviciosSolapados.get(0);
				throw new ExcepcionIntegridad(MENSAJES.SERVICIO_SOLAPADO,new String[]{StringUtils.formatDate(otro.getFecha()), StringUtils.formatDate(fechaServicio)});
			}
		}
		
    }
    
    
    public static void checkServiciosSolapadosX(Hembra hembra, Date fechaServicio, List<ProcMsg> msgs, Date fechaFinCorral, EvtNuevoInd servModif) throws ExcepcionIntegridad {
    	//TODO el parametro paso a la Raza (29/03/2007)
    	int gMin = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MINIMA));
    	int gMax = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MAXIMA));
    	int cicloCeloMinimo = (gMax - gMin) /2;
    	List serviciosSolapados = null;
    	if(fechaFinCorral == null)
    		serviciosSolapados = hembra.getEventosEntre(DateUtils.menos(fechaServicio,cicloCeloMinimo),DateUtils.mas(fechaServicio,cicloCeloMinimo),EvtAnimal.EVT_TIPO_SVC);
    	else
    		serviciosSolapados = hembra.getEventosEntre(DateUtils.menos(fechaServicio,cicloCeloMinimo),DateUtils.mas(fechaFinCorral,cicloCeloMinimo),EvtAnimal.EVT_TIPO_SVC);
    	EvtNuevoInd servicioModif = null;
		for(Object svc : serviciosSolapados){
			EvtNuevoInd servicio = (EvtNuevoInd)svc;
			if(servicio.equals(servModif))
				servicioModif = servicio;
		}
		if(servicioModif != null)
			serviciosSolapados.remove(servicioModif);
		if (!serviciosSolapados.isEmpty()) {			
			if (Configuracion.getValorReglaProceso(CONF.ACEPTA_SERVICIOS_SOLAPADOS,fechaServicio)) {
				Iterator it = serviciosSolapados.iterator();
				while (it.hasNext()) {
					EvtServicio otro = (EvtServicio)it.next();
					ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_SOLAPADO,	ProcMsg.WARNING,new String[]{
									(otro.getId()!=null?otro.getId().toString():"null"),
									String.format("%tF",new Object[]{ otro.getFecha()})});
					msgs.add(msg);
				}
			} else {
				EvtServicio otro = (EvtServicio)serviciosSolapados.get(0);
				throw new ExcepcionIntegridad(MENSAJES.SERVICIO_SOLAPADO,new String[]{StringUtils.formatDate(otro.getFecha()), StringUtils.formatDate(fechaServicio)});
			}		
		}
		
    }
    /**
	 * chequea si el servicio es valido. Para que sea valido el servicio no debe
	 * haber otro servicio entre [(fechaServicio - varible),(fechaServcio + varible) ]
	 * @param servicio
	 * @return
     * @throws ExcepcionIntegridad 
	 */
	public static void checkFechaServicioValido(EvtServicio servicio, Animal animal) throws ExcepcionIntegridad {
		if(animal!=null && servicio!=null){
			EvtAnimal evS = animal.getEventoEnFechaHora(servicio.getFecha(), Evento.EVT_TIPO_SVC);
			if (!Configuracion.getValorReglaProceso(CONF.ACEPTA_SERVICIOS_MISMO_DIA_Y_HORA,servicio.getFecha()) && evS!=null) {
				throw new ExcepcionIntegridad(MENSAJES.SERVICIO_REPETIDO_FECHA_HORA,new String[]{StringUtils.formatDate(servicio.getFecha())});
			}
		}
		/*
		int diasMinimo = Integer.parseInt(animal.getRaza().getParametro(
				Raza.RANGO_DE_SERVICIO_VALIDO));
		Date fechaLimiteInferior = DateUtils.menos(servicio.getFecha(),
				diasMinimo);
		Date fechaLimiteSuperior = DateUtils.mas(servicio.getFecha(),
				diasMinimo);
		if(!animal.getEventosEntre(fechaLimiteInferior, fechaLimiteSuperior, Evento.EVT_TIPO_SVC).isEmpty())
			throw new ExcepcionIntegridad(MENSAJES.SERVICIO_REPETIDO,new String[]{StringUtils.formatDate(fechaLimiteInferior), StringUtils.formatDate(fechaLimiteSuperior)});
	*/
		}
    
    
	public static EvtServicio createNuevoIndividuoServSemen(Establecimiento est, Hembra animal, String tipoServicio,Date fechaServicio, Macho padreGen, List<ProcMsg> msgs, Date fechaFinCorral) throws ExcepcionIntegridad {
		//		TODO comentado por uqe daniel no sabe de donde salio
		//if(fechaFinCorral != null)
			//checkServiciosSolapados(animal,fechaServicio,msgs, fechaFinCorral,0);
		if (!Configuracion.getValorReglaProceso(CONF.ACEPTA_MACHO_CON_RC_PARA_SERVICIO,fechaServicio) &&
				padreGen.getRegIdentificador().getTipoRegistro().getId().equals(Animal.CAT_RC))
 	   		throw new ExcepcionIntegridad(MENSAJES.NO_ACEPTA_PADRE_CON_RC,new String[] {padreGen.getRegistroOrigen()});
		if (tipoServicio.equals(STTipoServicio.INAR.toString())) 
			checkEdadAdmisibleServArtificial(animal,fechaServicio,msgs);	
		else
			checkEdadAdmisibleServNatural(animal,fechaServicio,msgs);
       
		// EvtNuevoInd serv = new EvtNuevoInd();
		EvtNuevoInd serv = new EvtNuevoInd(est,fechaServicio,animal,msgs);
		checkFechaServicioValido(serv, animal);
		/*
		serv.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
        serv.setEstablecimiento(est);
        serv.setFecha(fechaServicio);
        */
        serv.setTipoServ(tipoServicio);
        serv.setIniciaLactancia(false);
        serv.setFinalizaLactancia(false);
        serv.setFinCorral(fechaFinCorral);
        // serv.setAnimal(animal);
        if (animal.dentroDePeriodoDescanzo(serv)) 
        	msgs.add(ProcMsgDAO.create(MENSAJES.SERVICIO_EN_PERIODO_DESCANZO,ProcMsg.WARNING,new String[0]));
        padreGen.addServicioEfectuado(est,serv,msgs);
        animal.addEventoAnimal(serv,msgs);
        //animal.setEstadoRetroactivo(serv,msgs);
        animal.setEstadoRetroactivo(serv.getFecha(), msgs,serv.getNombreTipo());
                
        return serv;
    }

	public static EvtServicio createNuevoIndividuoPorTE(Establecimiento est, Hembra animal,Date fechaServicio, Hembra madreGen,Macho padreGen, Integer diasGestacion, String idEmbrion, List<ProcMsg> msgs) throws ExcepcionIntegridad{
		//		TODO comentado por uqe daniel no sabe de donde salio
		//checkServiciosSolapados(animal,fechaServicio,msgs, null);
		checkEdadAdmisibleServArtificial(animal,fechaServicio,msgs);
		// EvtTransEmb serv = new EvtTransEmb();
		EvtTransEmb serv = new EvtTransEmb(est,fechaServicio,animal,msgs);
		checkFechaServicioValido(serv, animal);
		/*
		serv.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
		serv.setEstablecimiento(est);
		serv.setFecha(fechaServicio);
		*/
		serv.setIdEmbrion(idEmbrion);
		serv.setDiasGestacion(diasGestacion);
		serv.setTipoServ("TE"); //esto es a mano porque en el xml si es una transferencia embrionaria no biene
								//cuando hisimos el modelo de objetos se nos paso, podriamos hacer refactoring de esto
		padreGen.addServicioEfectuado(est,serv,msgs);
		madreGen.addEvtTransfEmbrinaria(serv,msgs); 
		if (animal.dentroDePeriodoDescanzo(serv)) 
        	msgs.add(ProcMsgDAO.create(MENSAJES.SERVICIO_EN_PERIODO_DESCANZO,ProcMsg.WARNING,new String[0]));
		serv.setIniciaLactancia(false);
		serv.setFinalizaLactancia(false);
	//	serv.setAnimal(animal);
		animal.addEventoAnimal(serv,msgs);
		//animal.setEstadoRetroactivo(serv,msgs);
		animal.setEstadoRetroactivo(serv.getFecha(),msgs,serv.getNombreTipo());
	    
		return serv;
	}

	public static EvtServicio createClon(Establecimiento est, Hembra hembra, Date fechaServicio, Animal donante, String tegido, List<ProcMsg> msgs) throws ExcepcionIntegridad {
		throw new ExcepcionIntegridad(MENSAJES.NOT_IMPLEMENTED, new String[]{"Clones no implementados"});
		//ver que se setea por ejemplo en padres del animal
		//Deviera haber un metodo en Animal que sea Clonar() :: Animal
	}
	
	public static List getServiciosByAnimal(Hembra hembra) throws ExcepcionIntegridad {
		
		Criteria c = HibernateFactory.getSession().createCriteria(EvtServicio.class);
		c.add(Expression.eq("animal", hembra));
		c.addOrder(Order.asc("fecha"));
		return c.list();
	} 

	public static void checkNuevaFechaNacimiento(Hembra hembra, Date fechaNacimientoNuevo, List mensajes) throws ExcepcionIntegridad {
		 
		EvtServicioDAO.getServiciosByAnimal(hembra);
		
	}
	public static void updateEvtTransEmb(EvtTransEmb trans){
    	HibernateFactory.getSession().update(trans);
    }
	public static void updateEvtNuevoInd(EvtNuevoInd sem){
    	HibernateFactory.getSession().update(sem);
    }
	
}
