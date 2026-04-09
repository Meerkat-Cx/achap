package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.EvtCria;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;
import ar.org.sicel.proc.v1.lote.Cria;
import ar.org.sicel.proc.v1.lote.Reprod;
import ar.org.sicel.proc.v1.lote.types.STSexoD;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;


/**
 * 
 * @hibernate.joined-subclass table="Ev_Reproduccion" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvReproduc_EvAnim"
 * 
 */
public class EvtReproduccion extends ar.org.sicel.persistence.EvtAnimal {
	
	static Logger log = Logger.getLogger(EvtReproduccion.class);

    // --------------- attributes ---------------------
    private java.util.List evtCrias;

    /**
     * Servicio encontrado para este EvtReproduccion (puede ser null si no se
     * encontro ninguno)
     */
    private EvtServicio evtServicio;

    /**
     * dias de gestacion (es necesario porque puede no haber servicio, pero sin
     * embargo siempre se tiene que tener los dias de gestacion)
     */
    private Integer diasDeGestacion;

    /**
     * fecha de servicio informada
     */
    private Date fechaServicio;

    /**
     * informado por el informante del evento bajo la condicion de que no hay
     * servicio y no hay cria viva, por lo cual no se puede determinar si es
     * abolargo y se le pide al informante
     */
    private Boolean esAbortoLargo;
    
    private Boolean usarRazaMadre;
    
    private Integer nroLactancia;
    
    private Boolean asociaSicel1;

    public Boolean getAsociaSicel1() {
		return asociaSicel1;
	}


	public void setAsociaSicel1(Boolean asociaSicel1) {
		this.asociaSicel1 = asociaSicel1;
	}


	protected EvtReproduccion() {
    	
    }
    
    
   // TODO hacer esto    
    protected EvtReproduccion(Establecimiento est, Date fecha, Hembra hem, EvtCria[] crias,Boolean usarRazaMadre ,Boolean esAbortoLargo, List msgs) throws ExcepcionIntegridad {
    	super(est,fecha,hem,msgs,Evento.EVT_TIPO_REP);
    	this.setAsociaSicel1(false);
    }
    
    protected EvtReproduccion(Establecimiento est, Date fecha, Hembra hem, List msgs) throws ExcepcionIntegridad {
    	super(est,fecha,hem,msgs,Evento.EVT_TIPO_REP);
    	this.setIniciaLactancia(false);
    	this.setAsociaSicel1(false);
    }
    
    // ------------- relations ------------------

    /**
     * 
     * @hibernate.list lazy="true" table="Ev_Cria" cascade="all"
     * @hibernate.collection-key column="evt_reproduccion"
     *                           foreign-key="FK_EvCria_EvReproduc"
     * @hibernate.collection-index column="orden"
     * @hibernate.collection-composite-element class="ar.org.sicel.persistence.EvtCria"
     * 
     */
    public java.util.List getEvtCrias() {
        return this.evtCrias;
    }

    protected void setEvtCrias(java.util.List evtCrias) {
        this.evtCrias = evtCrias;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.org.sicel.persistence.Evento#getResumen()
     */
    public String getResumen() {
        String result = "";
        result = result + "\n\t\tGestación :" + getDiasDeGestacion()
                + " dias \n\t\t\tServicio = ";
        if (getEvtServicio() != null) {
            result = result + getEvtServicio().toString();
            result = result + getResumenCrias();
        } else {
            result = result + "(sin servicio)";
        }
        return result;
    }

    private String getResumenCrias() {
        String result = "";
        for (Iterator iter = getEvtCrias().iterator(); iter.hasNext();) {
            EvtCria evtCria = (EvtCria) iter.next();
            result = result + "\n\tCria ("
                    + (evtCria.getEstadoPerinatalEsVivo() ? "viva" : "muerta")
                    + ") ";
            if (evtCria.getCria() != null) {
                result = result + "RP= " + evtCria.getCria().getRP();
                result = result + " Reg= "
                        + evtCria.getCria().getRegOrigen();
            }
        }
        return result;
    }

    /*
     * @see ar.org.sicel.persistence.Evento#getNombreTipo()
     */
    public String getNombreTipo() {
        return EVT_TIPO_REP;
    }

    /**
     * 
     * @hibernate.property column="fechaServicio"
     * @hibernate.column name="fechaServicio" not-null="false"
     *                   type="java.util.Date"
     * 
     */
    public Date getFechaServicio() {    	   
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    /**
     * 
     * @hibernate.property column="diasDeGestacion"
     * @hibernate.column name="diasDeGestacion" not-null="true"
     * 
     */
    public Integer getDiasDeGestacion() {
        return diasDeGestacion;
    }

    public void setDiasDeGestacion(Integer diasDeGestacion) {
        this.diasDeGestacion = diasDeGestacion;
    }

    /**
     * 
     * @hibernate.property column="esAbortoLargo"
     * @hibernate.column name="esAbortoLargo" not-null="false"
     * 
     */
    public Boolean getAbortoLargo() {
        return esAbortoLargo;
    }

    public void setAbortoLargo(Boolean esAbortoLargo) {
        this.esAbortoLargo = esAbortoLargo;
    }

    public Boolean getUsarRazaMadre() {
		return usarRazaMadre;
	}

	public void setUsarRazaMadre(Boolean usarRazaMadre) {
		this.usarRazaMadre = usarRazaMadre;
	}

	/**
     * 
     * @hibernate.many-to-one column="ev_servicio" outer-join="auto"
     *                        foreign-key="FK_EvRepr_EvServ" not-null="true"
     *                        unique="true" cascade="none"
     * 
     * @hibernate.column name="ev_servicio" not-null="false" unique="true"
     *                   unique-key="UN_EvRepr_EvServ"
     * 
     */
    public EvtServicio getEvtServicio() {
        return evtServicio;
    }

    public void setEvtServicio(EvtServicio evtServicio) {
        this.evtServicio = evtServicio;
    }

    // ---------------- business methods ----------------------
/*
    public String getClazzParaTransicion() {
        return EvtReproduccion.class.getSimpleName();
    }
    */

    protected void eliminarColateralesPost() throws ExcepcionIntegridad {
        // buscar los animales que se crearon como efecto de las crias y
        // borrarlos, cracial y regs estan en cascada
        try {
            Session session = HibernateFactory.getSession();
            Iterator it = this.getEvtCrias().iterator();
            while (it.hasNext()) {
                EvtCria cria = (EvtCria) it.next();
                if (cria.getCria() != null)
                    session.delete(cria.getCria());
            }
        } catch (HibernateException he) {
            ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                    new String[] { String.valueOf(getId()), he.toString() });
            throw e;
        }
    }

    /**
     * @return
     * @throws ExcepcionIntegridad
     */
    public boolean getGestacionIniciaLactancia(boolean esAbortoLargo) throws ExcepcionIntegridad {
    	if (this.getEvtServicio() != null)
    		//TODO el parametro paso a la Raza (29/03/2007)
    		return (getDiasDeGestacion() >= Integer.parseInt(this.getAnimal().getRaza().getParametro(Raza.DIAS_MIN_ABL)));
    	else
    	{
    		//si no se conoce el servicio, debe haber transcurrido desde el ultimo parto
    		//y sin eventos servicios en el medio, el 75% del periodo de gestacion promedio.
    		//Raza.PERIODO_GESTACION_PROMEDIO;
    		EvtAnimal ultimoEvento = null;
    		Iterator eventos = this.getAnimal().getEvtAnimals().iterator();
    		while (eventos.hasNext()) {
    			EvtAnimal evt = (EvtAnimal) eventos.next();
    			if (evt.getNombreTipo().equals(Evento.EVT_TIPO_SVC))
    					ultimoEvento = (EvtServicio) evt;
    			if (evt.getNombreTipo().equals(Evento.EVT_TIPO_REP))
    					ultimoEvento = (EvtReproduccion) evt;
    		}
    		if (ultimoEvento == null)
    			return esAbortoLargo;
    		if(ultimoEvento.getNombreTipo().equals(Evento.EVT_TIPO_SVC))
    			return false;
    		else {
    			int diasTranscurridos = DateUtils.diasEntre(ultimoEvento.getFecha(),this.getFecha());
    			double diasNecesarios = this.getAnimal().getParametroRazaAsInteger(Raza.PERIODO_GESTACION_PROMEDIO) * 0.75;
    			return diasTranscurridos > diasNecesarios;
    		}
    		//TODO se podria hacer de un modo mas eficiente que recorriendo toda la lista de eventos 
    	}
    }

    @SuppressWarnings("unchecked")
	public Set getAnimalesModificados() {
        Set animalesModificados = super.getAnimalesModificados();
        Iterator iterator = this.evtCrias.iterator();
        while (iterator.hasNext()) {
            animalesModificados.add((Animal) iterator.next());
        }
        return animalesModificados;
    }

    @SuppressWarnings("unchecked")
	public void addAnimalesModificados(Set animalesModificados) {
        super.addAnimalesModificados(animalesModificados);
        Iterator iterator = this.evtCrias.iterator();
        while (iterator.hasNext()) {
            EvtCria cria = (EvtCria) iterator.next();
            if (cria.getCria() != null)
                animalesModificados.add(cria.getCria());
        }
    }

    /**
     * Verdadero si alguna de las crias esta viva
     * @return
     */
    public boolean criasVivas() {
       boolean criasVivas = false; //hay alguna cria viva? (de lo contrario se lo considera un aborto)
	   Iterator crias = getEvtCrias().iterator();
	   while (crias.hasNext() && !criasVivas) {
         EvtCria cria = (EvtCria)crias.next();
         criasVivas = cria.getEstadoPerinatalEsVivo();
	   }
	  return criasVivas;
    }
    
    
    /**
     * Refina la implementacion de EvtAnimal, para incluir los eventos de los hijos
     */
    public void recolectarEventosDependientesEn(SortedSet set) {
    	super.recolectarEventosDependientesEn(set);
    	Iterator crias = this.getEvtCrias().iterator();
    	while (crias.hasNext()) {
    		EvtCria cria = (EvtCria) crias.next();
    		if (cria.getCria() != null) { //si la cria se inscribio (y por ende esta en la base)
    			Iterator evtCrias = cria.getCria().getEvtAnimals().iterator();
    			while(evtCrias.hasNext()) { //agrego los eventos de las crias (es recursivo, si las crias tienen a su ves crias, se agregan los eventos tambien)
    				EvtAnimal evtAnimal = (EvtAnimal) evtCrias.next();
    				evtAnimal.recolectarEventosDependientesEn(set);
    			}
    		}
    	}
    	
    }
    /**
     * Método que realiza una serie de validaciones para poder dar de baja el evento
     * No se puede dar de baja si existen eventro de tipo control animal o secadas posteriores
     * a la ejecucion de este evento
     * Tampoco se puede dar de baja si alguna de las crias de la reproduccion ya tiene algun evento
     * asociado
     * 
     */
    public boolean validarBaja() throws ExcepcionIntegridad {
    	List eventosInicianLactancias = (this.getAnimal().getEventos(EVT_TIPO_EST));
    	eventosInicianLactancias.addAll(this.getAnimal().getEventos(EVT_TIPO_REP));
    	Collections.sort(eventosInicianLactancias, new EventosPorFechaYTipo());
    	//busco el evento posterior(eventoPosIniLac) que inicia lactancia
    	//una vez encontrado verifico que no haya un control animal ni un evento que finalice lactancia entre this y eventoPosIniLac
    	EvtAnimal eventoPosIniLac = null;
    	Iterator ite = eventosInicianLactancias.iterator();
		while(ite.hasNext()){
				EvtAnimal ev = (EvtAnimal)ite.next();
				if(this.getFecha().before(ev.getFecha())&&(ev.isIniciaLactancia())){
					eventoPosIniLac =ev;
					break;
				}
				
		}
		List eventosControl = this.getAnimal().getEventos(EVT_TIPO_CONTROL_ANIMAL);
		List eventosFinalizanLactancia = this.getAnimal().getEventos(EVT_TIPO_BAJ);
		eventosFinalizanLactancia.addAll(this.getAnimal().getEventos(EVT_TIPO_REP));
		eventosFinalizanLactancia.addAll(this.getAnimal().getEventos(EVT_TIPO_EST));
		eventosFinalizanLactancia.addAll(this.getAnimal().getEventos(EVT_TIPO_SEC));
		eventosFinalizanLactancia.addAll(this.getAnimal().getEventos(EVT_TIPO_PRE));
		Iterator it = eventosControl.iterator();
		Iterator it2 = eventosFinalizanLactancia.iterator(); 
		while(it.hasNext()){
			EvtControlAnimal ev = (EvtControlAnimal)it.next();
			if(ev.getFecha().after(this.getFecha())){
				if(eventoPosIniLac==null || ev.getFecha().before(eventoPosIniLac.getFecha())){
					
					String mensaje =" Id Control: "+ev.getControlEstablecimiento().getId()+", ordeñe: "+ev.getIdOrdenie()+" Tipo del evento: "+ev.getNombreTipo()+"\n"; 
					ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_BAJA_ALTA_WITHDEP,
		                    new String[] {this.getId().toString()+" "+this.getNombreTipo(),mensaje});
		            throw e;
					//return false;
				}
			}
		}
		while(it2.hasNext()){
			EvtAnimal ev = (EvtAnimal)it2.next();
			if(ev.getFecha().after(this.getFecha())&& ev.isFinalizaLactancia()){
				
				if(eventoPosIniLac==null || ev.getFecha().before(eventoPosIniLac.getFecha())){
						String mensaje =" Id evento: "+ev.getId()+" Tipo del evento: "+ev.getNombreTipo()+"\n"; 
						ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_BAJA_ALTA_WITHDEP,
			                    new String[] {this.getId().toString()+" "+this.getNombreTipo(),mensaje});
			            throw e;
						//return false;
				}
			}
		}
		Iterator crias = getEvtCrias().iterator();
		   while (crias.hasNext()) {
	         EvtCria criaE = (EvtCria)crias.next();
	         Animal c =  criaE.getCria();
	         this.validarBajaCria(c);
	         /*if(c!=null && !this.getAsociaSicel1()){ 
	        		 if(!c.getAllEventos().isEmpty()){//si es >0
	        			 if(c.getAllEventos().size()>1)
	        			 //si alguna de las crias tiene ya eventos asociados se rechaza la baja
	        				 throw new ExcepcionIntegridad(MENSAJES.EVT_BAJA_REP_CRIAS,new String[] {c.getRegistroOrigen()});
	        			 else{//si es uno
	        				//Evento ev=(Evento) ((List) c.getAllEventos()).get(0);
	        				 EvtAlta evAlta =(c.getId()!=null)?EvtAltaDAO.findByAnimal(c.getId()):null;
	        				 List evAltaBaja =  c.getEventos(Evento.EVT_TIPO_BAJ);
	        				if(evAltaBaja.isEmpty() || evAlta !=null)
	        					throw new ExcepcionIntegridad(MENSAJES.EVT_BAJA_REP_CRIAS,new String[] {c.getRegistroOrigen()});
	        			 }
	        			 }
	        		 if(!c.getCalificacions().isEmpty())
	        			 throw new ExcepcionIntegridad(MENSAJES.CRIA_TIENE_CALIFICACION,new String[] {c.getRegistroOrigen()});
	         }*/
		   }
		return true;
	}
    
	public void validarBajaCria(Animal c) throws ExcepcionIntegridad{
		//if(c!=null && !this.getAsociaSicel1()){ 
		if(c!=null){
   		 if(!c.getAllEventos().isEmpty()){//si es >0
   			 if(c.getAllEventos().size()>1)
   			 //si alguna de las crias tiene ya eventos asociados se rechaza la baja
   				 throw new ExcepcionIntegridad(MENSAJES.EVT_BAJA_REP_CRIAS,new String[] {c.getRegistroOrigen()});
   			 else{//si es uno
   				//Evento ev=(Evento) ((List) c.getAllEventos()).get(0);
   				 EvtAlta evAlta =(c.getId()!=null)?EvtAltaDAO.findByAnimal(c.getId()):null;
   				 List evAltaBaja =  c.getEventos(Evento.EVT_TIPO_BAJ);
   				if(evAltaBaja.isEmpty() &&  evAlta ==null)//si evAltaBaja es empty significa que tiene un evento y no es una baja
   					throw new ExcepcionIntegridad(MENSAJES.EVT_BAJA_REP_CRIAS,new String[] {c.getRegistroOrigen()});
   			 }
   			 }
   		 if(!c.getCalificacions().isEmpty())
   			 throw new ExcepcionIntegridad(MENSAJES.CRIA_TIENE_CALIFICACION,new String[] {c.getRegistroOrigen()});
		}
	}
	
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		
		if(validarBaja()){
			super.ejecutarBaja();
			//Session session = HibernateFactory.getSession();
    	try {
    		
			if(isIniciaLactancia()){
				Animal a = this.getAnimal();
				List lactancias = a.getEventos(Evento.EVT_TIPO_LAC);
				 if(lactancias.size()>0){
					 Collections.sort(lactancias, new EventosPorFechaYTipo());
					 int i = lactancias.size()-1;
					 //EvtLactancia lac = (EvtLactancia) lactancias.get(i);
					// a.setNroLactancia(lac.getNroLact());
				 }
				 //else
					 //a.setNroLactancia(0);
			}
			if(this.getEvtServicio()!=null){
				
				this.getEvtServicio().setEvtReproduccion(null);
				HibernateFactory.getSession().update(this.getEvtServicio());
				this.setEvtServicio(null);
			}		
				Iterator crias = getEvtCrias().iterator();
				   while (crias.hasNext()) {
			         EvtCria criaE = (EvtCria)crias.next();
			         Animal c =  criaE.getCria();
			         if(c!=null){ 
			        	 criaE.setCria(null);
			        	//si no es asociacion borro la cria
			        	 //si tiene evento baja asociado lo borro
			        	 //EvtBaja evba = (EvtBaja) c.getEventos(Evento.EVT_TIPO_BAJ);
			        	 List evBajaL =  c.getEventos(Evento.EVT_TIPO_BAJ);
	        			if(!evBajaL.isEmpty()){
	        				  EvtBaja evba= (EvtBaja) evBajaL.get(0);
				        		 evba.ejecutarBaja();
	        			}
	        			if(!this.getAsociaSicel1()){
			        		 EvtAlta evAlta =(c.getId()!=null)?EvtAltaDAO.findByAnimal(c.getId()):null;
			        		//si tiene evento alta asociado lo borro y automaticamente borra el animal por eso el else
							if(evAlta!=null)
									EvtEstablecimientoDAO.deleteEvtEstab(evAlta);
							else
								AnimalDAO.delete(c);
						 }
			         }
			        }
			EvtAnimalDAO.deleteEvtAnim(this);
			//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
    	} catch (HibernateException he){
                ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                        new String[] { String.valueOf(getId()), he.toString() });
                throw e;
    		}
    	}
	}

    public boolean validarModificacion() throws ExcepcionIntegridad {
    	return true;
    }
    
    @SuppressWarnings("unchecked")
	public EvtAnimalModificacion ejecutarModificacion(Reprod reprod, List mensajes, Animal objAnimal, ProcLote procLote,Date fechaEnvioLote) throws ExcepcionIntegridad {
		if(!this.getAnimal().getId().equals(objAnimal.getId())){
			log.debug("El animal informado no posee el evento informado como evento reproducción asociado " + reprod.getModificaOBaja().getIDEvt());
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO,
                    new String[] { this.getAnimal().getRegistroOrigen(), objAnimal.getRegistroOrigen()});
            throw e;
		}   
		if(this.evaluarFechaInformada(reprod, objAnimal)){ //OK
			
			if(this.getAbortoLargo().booleanValue()!=reprod.getAbortoLargo()){ // ok
				log.debug("La indicación de aborto largo no puede ser modificada "+ this.getId().toString());
				ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_MODIF_ABORTO_LARGO,
						new String[] {this.getId().toString(),String.valueOf(this.getAbortoLargo().booleanValue())});
				throw e;
			}
			if((!this.getAbortoLargo().booleanValue())&& (!this.getEvtCrias().isEmpty())){
				if(reprod.getCriaCount()!=this.getEvtCrias().size()){
					System.out.println("ERRORRR, debe informar las mismas");
					throw new ExcepcionIntegridad(MENSAJES.NO_MISMA_CANTIDAD_CRIAS, new String[] {this.getId().toString()});
						
				}
				/*else{
					chequeosRPsInscribir(reprod);
					evaluarCriasSexoEstado(reprod,mensajes);
				}*/	
			}
			
			if(validarNuevaFechaReproduccion(objAnimal,reprod.getFecha())){
				this.setFecha(reprod.getFecha());
				this.setFechaServicio(reprod.getFechaServicio());
				Hembra madreParto = (Hembra)objAnimal;
				madreParto.completarConServicioPara(this,mensajes,fechaEnvioLote);
				EvtServicio serv = this.getEvtServicio();
				Hembra madreGen = madreParto;
				if((!this.getAbortoLargo().booleanValue())&& (!this.getEvtCrias().isEmpty())){
					chequeosRPsInscribir(reprod,serv);
					evaluarCriasSexoEstado(reprod,mensajes);
					chequearCambioNumeroLactancia(reprod,mensajes);
					this.setNroLactancia(reprod.getNumLactancia());
				}
				
				Macho padreGen = null;
				if (serv == null) {
							if (!Configuracion.getValorReglaProceso(CONF.ACEPTA_PARTO_SIN_SERVICIO, this.getFecha()))
								throw new ExcepcionIntegridad(MENSAJES.PARTO_SIN_SERVICIO,new String[] {madreParto.getRaza().
									getParametro(Raza.PERIODO_GESTACION_PROMEDIO),
									madreParto.getRaza().getParametro(Raza.CICLO_CELO_MINIMO) });
							List evtCrias = this.getEvtCrias();
							Iterator it_crias = evtCrias.iterator();
							Raza razaDeclarada=null;
							if (reprod.getUsarRazaMadre())
								razaDeclarada = madreParto.getRaza();
							else//si no tengo servicio y usarRazaMadre es false entonces es cruza
								razaDeclarada = madreParto.getRaza().getEspecie().getCruza();
							if(!evtCrias.isEmpty()){
									if(evtCrias.size()>1)
											while(it_crias.hasNext()){
												EvtCria evtCria = (EvtCria)it_crias.next();
												abmAnimal(evtCria,reprod,mensajes,razaDeclarada,madreParto,madreParto,procLote,null);
											}
									else{
										EvtCria evtCria = (EvtCria)evtCrias.get(0);
										abmAnimal(evtCria,reprod,mensajes,razaDeclarada,madreParto,madreParto,procLote,null);
										}
							}
					this.setUsarRazaMadre(reprod.getUsarRazaMadre());
				}
				else {
					// si hay servicio
					padreGen = serv.getPadreGenetico();
					madreGen = serv.getMadreGenetica();
					ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_UTILIZADO,ProcMsg.INFO, new String[] { StringUtils.formatDate(serv.getFecha()) });
					mensajes.add(msg);
					if(this.getEvtCrias()!=null){
						Iterator crias = this.getEvtCrias().iterator();
						Raza razaDeclarada=null;
						if(madreGen.getRaza().getId().equals(padreGen.getRaza().getId()))
							razaDeclarada = madreGen.getRaza();  
						if(evtCrias.size()!=1){
							while(crias.hasNext()){
								EvtCria evtCria = (EvtCria)crias.next();
								abmAnimal(evtCria,reprod,mensajes,razaDeclarada,madreParto,madreGen,procLote,padreGen);
								//abmAnimal(evtCria,reprod,mensajes,razaDeclarada,madreParto,procLote,padreGen);
							}
						}
						else{
								EvtCria evtCria = (EvtCria)evtCrias.get(0);
								abmAnimal(evtCria,reprod,mensajes,razaDeclarada,madreParto,madreGen,procLote,padreGen);
								//abmAnimal(evtCria,reprod,mensajes,razaDeclarada,madreParto,procLote,padreGen);
							}
					}
					}
				}
			EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO.create(objAnimal.getEstablecimiento(), new Date(), objAnimal, this);
			this.addModificaciones(eventoModificacion);
			EvtReproduccionDAO.updateEventoReproduccion(this);
			return eventoModificacion;
			
		}
		return null;
    }
    
    
    
    
   
    private void chequearCambioNumeroLactancia(Reprod nuevaRepro, List mensajes) throws ExcepcionIntegridad {
		if(this.getNroLactancia().intValue()==(nuevaRepro.getNumLactancia()))
			return;//no cambió el numero
    	
    	Lactancia lactAnterior = ((this.getAnimal().getEventoAnteriorFecha(nuevaRepro.getFecha(), EVT_TIPO_LAC))!=null)
    	?(Lactancia)(this.getAnimal().getEventoAnteriorFecha(nuevaRepro.getFecha(), EVT_TIPO_LAC)):
    		(Lactancia)(this.getAnimal().getEventoAnteriorFecha(nuevaRepro.getFecha(), EVT_TIPO_LMI));
    	int nroLacAnterior = (lactAnterior!=null)?lactAnterior.getNroLact():0;
    	
    	Lactancia lactPosterior = ((this.getAnimal().getEventoPosteriorFecha(nuevaRepro.getFecha(), EVT_TIPO_LAC))!=null)
    	?(Lactancia)(this.getAnimal().getEventoPosteriorFecha(nuevaRepro.getFecha(), EVT_TIPO_LAC)):
    		(Lactancia)(this.getAnimal().getEventoPosteriorFecha(nuevaRepro.getFecha(), EVT_TIPO_LMI));
    	
    	if(lactPosterior!=null){
    		
    		//if(this.getFechaLactancia().equals(lactPosterior.getFechaInicio())){
    		if(DateUtils.mismoDia(this.getFechaLactancia(), lactPosterior.getFechaInicio())){
    			lactPosterior = ((this.getAnimal().getEventoPosteriorFecha(lactPosterior.getFecha(), EVT_TIPO_LAC))!=null)
    	    	?(Lactancia)(this.getAnimal().getEventoPosteriorFecha(lactPosterior.getFecha(), EVT_TIPO_LAC)):
    	    		(Lactancia)(this.getAnimal().getEventoPosteriorFecha(lactPosterior.getFecha(), EVT_TIPO_LMI));
    		}
    	}
    	int nroLacPosterior = (lactPosterior!=null)?lactPosterior.getNroLact():10000;
    	if((lactAnterior!=null)&& (nuevaRepro.getNumLactancia() <= nroLacAnterior ))
    		throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_NUMERO_LACTANCIA_MAL_ANT , new String[] {String.valueOf(nuevaRepro.getNumLactancia()),String.valueOf(nroLacAnterior)});
    	
    	if((lactPosterior!=null) && (nuevaRepro.getNumLactancia() >= nroLacPosterior ))
    		throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_NUMERO_LACTANCIA_MAL_POST , new String[] {String.valueOf(nuevaRepro.getNumLactancia()),String.valueOf(nroLacPosterior)});
    		
    	
    	if(nuevaRepro.getNumLactancia()> nroLacAnterior){
    		if(lactAnterior!=null){
	    		int cantLact = (int)(nuevaRepro.getNumLactancia() - nroLacAnterior);
	    		Integer diasMin = Integer.parseInt(this.getAnimal().getRaza().getParametro(Raza.DIAS_MIN_ENTRE_LACT));
	    		if(DateUtils.mas(lactAnterior.getFechaInicio(), cantLact*diasMin).after(this.getFechaLactancia())){
	    			throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_NUMERO_LACTANCIA_LAC_ANTERIOR , new String[] {String.valueOf(nuevaRepro.getNumLactancia()),DateUtils.format(this.getFechaLactancia(), null),String.valueOf(nroLacAnterior),DateUtils.format(lactAnterior.getFechaInicio(), null)});
	    			}
    		}
    		if(lactPosterior!=null){
	    		int cantLact2 = (int)(nroLacPosterior - nuevaRepro.getNumLactancia());
	    		Integer diasMin2 = Integer.parseInt(this.getAnimal().getRaza().getParametro(Raza.DIAS_MIN_ENTRE_LACT));
	    		if(DateUtils.mas(this.getFechaLactancia(), cantLact2*diasMin2).after(lactPosterior.getFechaInicio())){
	    			throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_NUMERO_LACTANCIA_LAC_POSTERIOR , new String[] {String.valueOf(nuevaRepro.getNumLactancia()),DateUtils.format(this.getFechaLactancia(), null),String.valueOf(nroLacPosterior),DateUtils.format(lactPosterior.getFechaInicio(), null)});
	    			}
    		}
    	}
    	
    	ProcMsg msg = this.getAnimal().chequearNumLactanciaYEdad(nuevaRepro.getNumLactancia(),this.getFechaLactancia());
		if(msg!=null)
			mensajes.add(msg);
		System.out.println("numeracion ok ");
    	
	}



	/**
     * Falta hacer lo de inscribir
     * Si solamente se informa una sola cria y el rp informado es distinto al que se encuentra en la base
     * se informa el error. En caso de que se informe rp y originalmente no se habia informado se va a aceptar 
     * cambiar el rp, osea setear dicho rp, siempre y cuando se haya informado inscribir.
     * En caso de que se informe mas de una cria en el mismo parto se deben enviar las mismas crias y con los mismos 
     * datos. En caso que no se realice esto se informará el error correspondiente.
     * Para poder detectar si son las mismas crias se tiene en cuenta la lista de crias y a medida que se encuentra las coincidencias 
     * con lo informado se van sacando de la lista hasta que quede vacia, en caso de q no sea asi se informa el error de que 
     * no se informaron las mismas crias.
     * SE CHEQUEA QUE ESTEN LOS MISMOS RP'S EN LAS CRIAS EN CASO DE QUE SEAN MAS DE UNA.
     * @param reprod
     * @throws ExcepcionIntegridad 
     */
    private void chequeosRPsInscribir(Reprod reprod,EvtServicio serv) throws ExcepcionIntegridad {
    	if(this.getEvtCrias().size()==1){
			Cria cri = reprod.getCria(0);
			EvtCria evt = (EvtCria) this.getEvtCrias().get(0);
			//si tiene RP el animal e informan el rp en la modif y es distinto error
			//si tiene RP el animal y no informan rp en la modif
			if((evt.getRP()!=null && cri.getRp()!=null && !evt.getRP().equals(cri.getRp()))
					|| (evt.getRP()!=null && cri.getRp()==null )){
				System.out.println("El rp de la cria no es el mismo que se tiene informado para la cria");
				throw new ExcepcionIntegridad(MENSAJES.NO_COINCIDE_RP_CRIA , new String[] {(evt.getRP()!=null)?evt.getRP():"",(cri.getRp()!=null)?cri.getRp():""});
			}
			//si no tenia rp(por lo tanto no inscripto y ahora informan el rp)
			if(evt.getRP()==null && cri.getRp()!=null && cri.getInscribir()==true){
				if (Configuracion.getValorReglaProceso(CONF.VALIDACIONES_NUMEROS_DE_RP,
						this.getFecha()) && !Animal.validarRP(cri.getRp()))
					throw new ExcepcionIntegridad(MENSAJES.VALIDACIONES_NUMEROS_DE_RP,new String[]{cri.getRp()});
				evt.setRP(cri.getRp());
			}
			//si pone no inscribir y tenia cria (o sea inscribir) tengo que chequear que los padres sean de pedigree, en cambio si no tengo cria no pasa nada
			if((!cri.getInscribir()) && (evt.getCria()!=null) ){
				Hembra mad = ((Animal)evt.getCria()).getMadreGenetica();
				//Macho pad = ((Animal)evt.getCria()).getPadre();
				Macho pad = (serv!=null)?serv.getPadreGenetico():null;
				if(mad==null || pad==null || !mad.getRegistroOrigen().contains("HBA") || !pad.getRegistroOrigen().contains("HBA"))
					throw new ExcepcionIntegridad(MENSAJES.INSCRIBIR_HEMBRA_OBLIGA,
							new String[0]);
			}
				
			
		}
    	else{
    		if(this.getEvtCrias().size()>1){
	    		Cria[] xmlCrias  = reprod.getCria();
	    		List criasAux = new ArrayList();
	    		criasAux.addAll(this.getEvtCrias());
	    		for (int i=0;i<xmlCrias.length;i++) {
	    				Iterator it = criasAux.iterator();
	    				while(it.hasNext()){
	    					EvtCria evc = (EvtCria)it.next();
	    					if((evc.getRP()== null && xmlCrias[i].getRp()==null)
	    							||((evc.getRP()!= null && xmlCrias[i].getRp()!=null)&& evc.getRP().equals(xmlCrias[i].getRp()))){
	    						
	    						if((!xmlCrias[i].getInscribir()) && (evc.getCria()!=null) ){
	    							Hembra mad = ((Animal)evc.getCria()).getMadreGenetica();
	    							Macho pad = (serv!=null)?serv.getPadreGenetico():null;
	    							if(mad==null || pad==null || !mad.getRegistroOrigen().contains("HBA") || !pad.getRegistroOrigen().contains("HBA"))
	    								throw new ExcepcionIntegridad(MENSAJES.INSCRIBIR_HEMBRA_OBLIGA,
	    										new String[0]);
	    						}
	    						
	    						criasAux.remove(evc);
	    						break;//encontro la cria, sea nula o no. La saco de la lista auxiliar
	    					}
	    				}
	    		}
	    		if(!criasAux.isEmpty()){//si no esta vacia significa que no son las mismas crias que informaron originalmente
	    			System.out.println("EN LA MODIFICACION DE REPRODUCCION NO SE PERMITE ASIGNAR RP CUANDO SE INFORMA MAS DE UNA CRIA");
	    			throw new ExcepcionIntegridad(MENSAJES.NO_ASIGNACION_MAS_DE_UNA_CRIA_RP , new String[] {});
	    		}
	    		
	    		
	    		
	    			
	    		}
	    	}
    	}
	

    /**
     * Se debe tener en cuenta que deben estar todos los rp´s de las crias
     * @param reprod
     * @throws ExcepcionIntegridad 
     */
	private void evaluarCriasSexoEstado(Reprod reprod, List mensajes) throws ExcepcionIntegridad {
		//tener en cuenta que deben estar los rp's de las crias
    		if(this.getEvtCrias().size()>1){
	    		Cria[] xmlCrias  = reprod.getCria();
	    		
	    		for (int i=0;i<xmlCrias.length;i++) {
	    				String sexo ="";
	    				boolean esHembra = xmlCrias[i].getSexo().equals(STSexoD.F);
	    				if(esHembra)
	        				sexo = "F";
	        			else{
	        				esHembra = xmlCrias[i].getSexo().equals(STSexoD.M);
	        				if(esHembra)//si es macho
	        					sexo = "M";
	        				else//sino desconocido
	        					sexo = "D";
	        			}
	    				EvtCria evCri = this.encontrarCria(xmlCrias[i].getRp(), this.getEvtCrias());
	    				if(evCri!=null){
	    					this.chequearCambiosCria(xmlCrias[i].getInscribir(), xmlCrias[i].getEstadoEsVivo(),sexo, evCri);
	    					/*if(!this.chequearCambiosCria(xmlCrias[i].getInscribir(), xmlCrias[i].getEstadoEsVivo(),sexo, evCri))
	    						System.out.println("no se puede hacer el cambio");
	    					else{
	    						//evCri.setSexo(sexo);
	    						//evCri.setEstadoPerinatalEsVivo(xmlCrias[i].getEstadoEsVivo());
	    						System.out.println("hay que actualizar evtcria cambio");
	    					}*/
	    				}
	    				else{
	    					log.warn("SI SE INFORMA MAS DE UNA CRIA Y SE DESEASE CAMBIAR EL SEXO, ESTADO PERINATAL O INSCRIBIR LAS CRIAS DEBEN TENER LOS RP'S INFORMADOS PARA PODER IDENTIFICAR LAS MISMAS ");
	    					mensajes.add(ProcMsgDAO.create(MENSAJES.MAS_DE_UNA_CRIA_Y_MODIFICA_SIN_RP,ProcMsg.WARNING,new String[]{}));
	    					break;
	    				}
	    						//
	    		}	
    		}
    		else{
    			if(this.getEvtCrias().size()==1){
    			Cria cri = reprod.getCria(0);
    			String sexo ="";
    			boolean esHembra = cri.getSexo().equals(STSexoD.F);
    			if(esHembra)
    				sexo = "F";
    			else{
    				esHembra = cri.getSexo().equals(STSexoD.M);
    				if(esHembra)
    					sexo = "M";
    				else
    					sexo = "D";
    			}
    			EvtCria evt = (EvtCria) this.getEvtCrias().get(0);
    			this.chequearCambiosCria(cri.getInscribir(), cri.getEstadoEsVivo(), sexo, evt);
    			/*if(!this.chequearCambiosCria(cri.getInscribir(), cri.getEstadoEsVivo(), sexo, evt))
    				System.out.println("no se puede hacer el cambio");
				else{
					//evt.setSexo(sexo);
					//evt.setEstadoPerinatalEsVivo(cri.getEstadoEsVivo());
					System.out.println("hay que actualizar evtcria cambio");
				}*/	
    		}
    	}
    }



	private void abmAnimal(EvtCria evtCria, Reprod reprod, List mensajes,Raza razaDeclarada, Hembra madreParto,Hembra madreGen, ProcLote procLote,Macho padreGen) throws ExcepcionIntegridad {
    	   		
		if(!org.apache.commons.lang.StringUtils.isEmpty(evtCria.getRP())){
			Cria cr = getCria(reprod.enumerateCria(),evtCria.getRP());
			boolean isInscribir=evtCria.getCria()!=null?true:false;
			if(isInscribir){//si antes puso inscribir con rp
				if(!cr.getInscribir() && (madreGen!=null && padreGen !=null && madreGen.getRegistroOrigen().contains("HBA") && padreGen.getRegistroOrigen().contains("HBA"))){//se esta cambiando inscribir de true a false
					//borrar el evCria
					Animal c =  evtCria.getCria();
					 this.validarBajaCria(c);
			         if(c!=null){ 
			        	 evtCria.setCria(null);
			        	//si no es asociacion borro la cria
			        	 //si tiene evento baja asociado lo borro
			        	 //EvtBaja evba = (EvtBaja) c.getEventos(Evento.EVT_TIPO_BAJ);
			        	 List evBajaL =  c.getEventos(Evento.EVT_TIPO_BAJ);
	        			if(!evBajaL.isEmpty()){
	        				  EvtBaja evba= (EvtBaja) evBajaL.get(0);
				        		 evba.ejecutarBaja();
	        			}
	        			if(!this.getAsociaSicel1()){
			        		 EvtAlta evAlta =(c.getId()!=null)?EvtAltaDAO.findByAnimal(c.getId()):null;
			        		//si tiene evento alta asociado lo borro y automaticamente borra el animal por eso el else
							if(evAlta!=null)
									EvtEstablecimientoDAO.deleteEvtEstab(evAlta);
							else
								AnimalDAO.delete(c);
						 }
			         }
			         return;
				}
				boolean estabaVivo = evtCria.getEstadoPerinatalEsVivo();
				boolean eraHembra = evtCria.getSexo().equals("F");
				if(eraHembra != (cr.getSexo().equals(STSexoD.F))){ //si cambio Sexo
					if(estabaVivo != cr.getEstadoEsVivo()){//si cambio estado
						if(!estabaVivo && cr.getEstadoEsVivo()){//si de muerto a vivo
							List eventoBaja = evtCria.getCria().getEventos(EVT_TIPO_BAJ);//saco el evento baja
							if(!eventoBaja.isEmpty()){
								EvtBaja evBajaAni = (EvtBaja) eventoBaja.get(0);
								evBajaAni.ejecutarBaja();
							}
							Animal aniNuevo = AnimalDAO.createCambioSexoPadres(evtCria.getCria(), mensajes);
							EvtAlta evAlta =(evtCria.getCria()!=null && evtCria.getCria().getId()!=null)?EvtAltaDAO.findByAnimal(evtCria.getCria().getId()):null;
							//AnimalDAO.delete(evtCria.getCria());//borro el viejo
							if(evAlta!=null)
								EvtEstablecimientoDAO.deleteEvtEstab(evAlta);
							else
								AnimalDAO.delete(evtCria.getCria());//borro el viejo
							AnimalDAO.save(aniNuevo);
							evtCria.setCria(aniNuevo);
							
						}
						if(estabaVivo && !cr.getEstadoEsVivo()){
							Animal aniNuevo = AnimalDAO.createCambioSexoPadres(evtCria.getCria(), mensajes);
							EvtAlta evAlta =(evtCria.getCria()!=null && evtCria.getCria().getId()!=null)?EvtAltaDAO.findByAnimal(evtCria.getCria().getId()):null;
							//AnimalDAO.delete(evtCria.getCria());//borro el viejo
							if(evAlta!=null)
								EvtEstablecimientoDAO.deleteEvtEstab(evAlta);
							else
								AnimalDAO.delete(evtCria.getCria());//borro el viejo
							AnimalDAO.save(aniNuevo);
							evtCria.setCria(aniNuevo);
							EvtBaja evtBaja = EvtBajaDAO.create(evtCria.getCria().getEstablecimiento(),evtCria.getCria(),"MUER","REPR",null,reprod.getFecha(),mensajes);
							HibernateFactory.getSession().save(evtBaja);
							//CREAR NUEVO ANIMAL
							//AGREGAR AL NUEVO ANIMAL EL EVENTO BAJA
						}
						evtCria.setEstadoPerinatalEsVivo(cr.getEstadoEsVivo());
					}
					else{//si no cambio el estado
						Animal aniNuevo = AnimalDAO.createCambioSexoPadres(evtCria.getCria(), mensajes);
						EvtAlta evAlta =(evtCria.getCria()!=null && evtCria.getCria().getId()!=null)?EvtAltaDAO.findByAnimal(evtCria.getCria().getId()):null;
						//AnimalDAO.delete(evtCria.getCria());//borro el viejo
						if(evAlta!=null)
							EvtEstablecimientoDAO.deleteEvtEstab(evAlta);
						else
							AnimalDAO.delete(evtCria.getCria());//borro el viejo
						AnimalDAO.save(aniNuevo);
						evtCria.setCria(aniNuevo);
					}
					evtCria.setSexo(cr.getSexo().equals(STSexoD.F)?"F":"M");
				}else{//si no cambio el sexo
					if(estabaVivo != cr.getEstadoEsVivo()){//si cambio estado
						//si vivo a muerto ---> y no tiene eventos(esto no lo tengo que preguntar pq ya se valido antes),
						//asociarle el evento baja al bicho
						if(estabaVivo && !cr.getEstadoEsVivo()){
							EvtBaja evtBaja = EvtBajaDAO.create(evtCria.getCria().getEstablecimiento(),evtCria.getCria(),"MUER","REPR",null,reprod.getFecha(),mensajes);
							evtBaja.setComentario("Informado como cría muerta");
							HibernateFactory.getSession().save(evtBaja);
						}
						//si de muerto a vivo --> eliminar el evento baja del animal y activar el registro
						if(!estabaVivo && cr.getEstadoEsVivo()){
							List eventoBaja = evtCria.getCria().getEventos(EVT_TIPO_BAJ);
							if(!eventoBaja.isEmpty()){
								EvtBaja evBajaAni = (EvtBaja) eventoBaja.get(0);
								evBajaAni.ejecutarBaja();
							}
						}
						evtCria.setEstadoPerinatalEsVivo(cr.getEstadoEsVivo());
					}
					
					
				}
				
				
				actulizarAnimal(cr,evtCria,reprod,mensajes,razaDeclarada,madreGen,padreGen);
				evtCria.getCria().setMadreParto(madreParto);
				
				Registro regAsignado = evtCria.getCria().getRegOrigen();
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.REGISTRO_ASIGNADO, ProcMsg.INFO,new String[] {evtCria.getCria().getRP(), regAsignado.getNumero(), regAsignado.getTipoRegistro().getId(), evtCria.getCria().getCategoria()});
				mensajes.add(msg);			
				FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
				fichaAnimal.setAnimal(evtCria.getCria());
				procLote.agregarFichaAnimal(fichaAnimal);
				//}
			}
			else{//si antes no era inscribir
			evtCria.setEstadoPerinatalEsVivo(cr.getEstadoEsVivo());
			evtCria.setSexo(cr.getSexo().equals(STSexoD.F)?"F":"M");
			
			if(cr.getInscribir()){//si antes no era inscribir y ahora si
				//boolean esHembra = cr.getSexo().equals("F");
				boolean esHembra  = cr.getSexo().equals(STSexoD.F);
				Animal an = AnimalDAO.create(this.getEstablecimiento(), this.getEstablecimiento().getPropietario(), null, esHembra, evtCria.getRP(), razaDeclarada, madreParto,
						padreGen, mensajes, this.getFecha(),evtCria.getRpSenasa(),evtCria.getCodigo(),true);
				an.setNombre(evtCria.getNombre());
				an.setRP(evtCria.getRP());
				an.setFechaNac(this.getFecha());
				madreParto.addHijoParto(an); // setea la madre de parto
				evtCria.setCria(an);	
				an.setearCategoria(true);
				Registro regAsignado = an.getRegOrigen();
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.REGISTRO_ASIGNADO, ProcMsg.INFO,new String[] {an.getRP(), regAsignado.getNumero(), regAsignado.getTipoRegistro().getId(), an.getCategoria()});
				mensajes.add(msg);				
				//actulizarAnimal(cr,evtCria,reprod,mensajes,razaDeclarada,madreParto,padreGen);
				FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
				fichaAnimal.setAnimal(evtCria.getCria());
				procLote.agregarFichaAnimal(fichaAnimal);
				HibernateFactory.getSession().save(evtCria.getCria());
				if(!evtCria.getEstadoPerinatalEsVivo()){
					EvtBaja evtBaja = EvtBajaDAO.create(this.getEstablecimiento(),evtCria.getCria(),"MUER","REPR",null,this.getFecha(),mensajes);
			        
					try {
			  			HibernateFactory.getSession().save(evtBaja);
			  		} catch (HibernateException e) {
			  			throw new ErrorFatal("No se pudo guardar el evento",e);
			  		}
				}
			}
			}
		}
		
	}


	/**
     * 
     * @param cria es la cria que viene en la modificacion del evento
     * @param evtCria es la cria a actualizar que se encuentra ya almacenada en la base
     * @throws ExcepcionIntegridad 
     */
	private void actulizarAnimal(Cria cri, EvtCria evtCria,Reprod reprod, List mensajes,Raza razaDeclarada,Hembra madre,Macho padre) throws ExcepcionIntegridad {
		//evtCria.get
		if(cri.getRp()!=null && evtCria.getCria()!=null){
			//if((!evtCria.getCria().getRP().equals(cri.getRp()))||(evtCria.getCria().getFechaNac().compareTo(reprod.getFecha())!=0)){//si cambio el rp o fecha nac chequo unicidad
			if((!evtCria.getCria().getRP().equals(cri.getRp()))||(!DateUtils.mismoDia(evtCria.getCria().getFechaNac(),reprod.getFecha()))){//si cambio el rp o fecha nac chequo unicidad
				Animal.checkUnicidadRPEnEstab(this.getEstablecimiento(),cri.getRp(),mensajes,reprod.getFecha(),evtCria.getCria().getEstablecimiento(),evtCria.getCria().getRegistroOrigen());
				evtCria.getCria().setRP(cri.getRp());
			}
			if(cri.getNombre()!=null)
				evtCria.getCria().setNombre(cri.getNombre());
			evtCria.setPesoKg(cri.getPeso());
			evtCria.setTamanio(cri.getTamano());
			evtCria.getCria().setFechaNac(reprod.getFecha());			
			if(!evtCria.getDificultadNac().equals(String.valueOf(cri.getDificultad())))
				evtCria.setDificultadNac(String.valueOf(cri.getDificultad()));
			//actualizoCria=true;
			//evtCria.setDificultadNac(cri.getDificultad());
			evtCria.getCria().setFechaNac(reprod.getFecha());
			String rpSe = null;
			Integer dig = null;
			if(cri.getSenasa()!=null){
				rpSe = cri.getSenasa().getRpSenasa();
				dig = new Integer(cri.getSenasa().getDigitoVerficador());
			}
			this.actualizar(evtCria.getCria(),razaDeclarada,madre,padre,rpSe,dig);
		}
	}


private Cria getCria(Enumeration enumeration,String rp) {
		
		while(enumeration.hasMoreElements()){
			Cria cr = (Cria)enumeration.nextElement();
			if((cr.getRp()!=null)&& cr.getRp().equals(rp))
				return cr;
		}
		return null;
	}


	/**
     * Realiza comparación de fechas entre la fecha del evento original y la nueva fecha informada para la
     * modificación de dicho evento. 
     * Chequea que no existan eventos lactancia posteriores a la fecha del evento,
     * ya que generaría un cambio en la lactancia que ya está cerrada y esto no debe permitirse.
     * Chequea que la fecha nueva no este antes que la fecha del evento anterior
     * Chequea que la fecha nueva no este despues que la fecha del evento posterior
     * @param reprod
     * @param objAnimal
     * @return boolean
     */
    private boolean evaluarFechaInformada(Reprod reprod, Animal objAnimal) throws ExcepcionIntegridad {
    	if(this.getFecha().equals(reprod.getFecha()))
    		return true;
    	else{    		
    		Evento evtPost = this.findEventoPosterior();
    		if(evtPost!=null && this.isIniciaLactancia()){
       			if (evtPost.getNombreTipo().equals(Evento.EVT_TIPO_CONTROL_ANIMAL)) {
       				int cantDiasEntreRepControl = DateUtils.diasEntre(reprod.getFecha(),evtPost.getFecha());
       				int cantDiasMinimo = Integer.valueOf(this.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_PRIMER_CONTROL)).intValue();
       				if(cantDiasEntreRepControl<cantDiasMinimo){
       					ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVT_CONTROL_POST,
                            new String[] { DateUtils.format(reprod.getFecha(),"dd/MM/yyyy"),evtPost.getId().toString(), DateUtils.format(evtPost.getFecha(),"dd/MM/yyyy")});
       					throw e;
       				}	
       			}
    		}
    		Iterator i = this.getDependientes().iterator();
    		while (i.hasNext()) { // ok
    			Evento evtDependiente = (Evento) i.next();

    			if (evtDependiente.getNombreTipo().equals(Evento.EVT_TIPO_LAC)) {
         			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVT_LACTANCIA_POST,
                            new String[] { DateUtils.format(reprod.getFecha(),null),evtDependiente.getId().toString(), DateUtils.format(evtDependiente.getFecha(),null)});
                    throw e;
    			}	
    		}

    		 Evento evtAnt = this.findEventoAnterior();
    		 if(evtAnt!=null){ // ok
    			 if(reprod.getFecha().before(evtAnt.getFecha())){
    				 log.debug("La fecha del evento que modifica es menor que el evento anterior al evento modificado: " + this.getId());
		         	 ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_ANT,
		         			 new String[] { DateUtils.format(reprod.getFecha(),null), DateUtils.format(evtAnt.getFecha(),null)});
		             throw e;
    				 
    			 }
    		 }
    		 
    		 if(evtPost!=null){ // ok
    			 if(reprod.getFecha().after(evtPost.getFecha())){
    				 log.debug("La fecha del evento que modifica es mayor que el evento posterior al evento modificado: " + this.getId());
		         	 ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_POST,
		                     new String[] { DateUtils.format(reprod.getFecha(),null), DateUtils.format(evtPost.getFecha(),null)});
		             throw e;
    			 }
    		 }
    		 return true;
    	}
    }
    
    private boolean validarNuevaFechaReproduccion(Animal objAnimal, Date nuevaFechaEvento) throws ExcepcionIntegridad {
    	Hembra madreParto = (Hembra)objAnimal;
		
    	// Chequea edad fértil de la madre
    	//TODO el parametro paso a la Raza (29/03/2007)
    	int diasMinima = 0;
    	Integer edadFertil = Integer.parseInt(madreParto.getRaza().getParametro(Raza.EDAD_MAX_FERTIL));
		if (this.getEvtCrias().size() > 0){ 
			//se toma la gestación mínima más la cantidad de días que debe haber entre partos
			diasMinima = Integer.parseInt(madreParto.getRaza().getParametro(
					Raza.GESTACION_MINIMA));
			int edadServMin = Integer.parseInt(madreParto.getRaza().getParametro(
					Raza.EDAD_MINIMA_SERVICIO_NATURAL));
			Date fechaMinimaServicio = DateUtils.mas(madreParto.getFechaNac(), edadServMin);
			Date fechaMinParto = DateUtils.mas(fechaMinimaServicio, diasMinima);
			if (fechaMinParto.after(nuevaFechaEvento))
				throw new ExcepcionIntegridad(MENSAJES.NO_RESPETA_PERIODO_SERVICIO_PARTO,new String[] {madreParto.getRegistroOrigen()});
		}
		else{//si no hay crias 
			if(esAbortoLargo == null)
				throw new ExcepcionIntegridad(MENSAJES.EDAD_MAX_FERTIL,	new String[] { edadFertil.toString() });
			if(esAbortoLargo){
			//se toma la cantidad de días mínima para considerar aborto largo más la cantidad de días que debe haber entre reproducciones
			diasMinima = Integer.parseInt(madreParto.getRaza().getParametro(Raza.DIAS_MIN_ABL));
			int edadServMin = Integer.parseInt(madreParto.getRaza().getParametro(
					Raza.EDAD_MINIMA_SERVICIO_NATURAL));
			Date fechaMinimaServicio = DateUtils.mas(madreParto.getFechaNac(), edadServMin);
			Date fechaMinParto = DateUtils.mas(fechaMinimaServicio, diasMinima);
			if (fechaMinParto.after(nuevaFechaEvento))
				throw new ExcepcionIntegridad(MENSAJES.NO_RESPETA_PERIODO_SERVICIO_PARTO,new String[] {madreParto.getRegistroOrigen()});
			}
			else{//es aborto corto
				diasMinima = 1;
				int edadServMin = Integer.parseInt(madreParto.getRaza().getParametro(
						Raza.EDAD_MINIMA_SERVICIO_NATURAL));
				Date fechaMinimaServicio = DateUtils.mas(madreParto.getFechaNac(), edadServMin);
				Date fechaMinParto = DateUtils.mas(fechaMinimaServicio, diasMinima);
				if (fechaMinParto.after(nuevaFechaEvento))
					throw new ExcepcionIntegridad(MENSAJES.NO_RESPETA_PERIODO_SERVICIO_PARTO,new String[] {madreParto.getRegistroOrigen()});
			}
		}
		if (madreParto.getEdadEnDiasAl(nuevaFechaEvento) > edadFertil)
			throw new ExcepcionIntegridad(MENSAJES.EDAD_MAX_FERTIL,new String[] { edadFertil.toString() });
		
		// Chequea período de descanso entre reproducciones
		// para probar esto tengo que ingresar una reproduccion nueva
		if (esAbortoLargo || this.getEvtCrias().size() > 0) {
			//TODO el parametro paso a la Raza (29/03/2007)
			int diasEntreParto = Integer.parseInt(madreParto.getRaza().getParametro(Raza.GESTACION_MINIMA))
					+ Integer.parseInt(madreParto.getRaza().getParametro(Raza.PERIDODO_DESCANZO_MINIMO));
			Date minParto = DateUtils.menos(nuevaFechaEvento, diasEntreParto);
			List partosConflictos = madreParto.getEventosEntre(minParto,nuevaFechaEvento, Evento.EVT_TIPO_REP);
			// partosConflictos devuelve el evento reproduccion que estamos modificando, no habria que sacarlo antes de preguntar
			partosConflictos.remove(this); // asi pasa este metodo ok
			if (!partosConflictos.isEmpty()) { // OK, ENTRO Y SALIO
				EvtReproduccion posibleConf = (EvtReproduccion) partosConflictos.get(0);
				throw new ExcepcionIntegridad(MENSAJES.PERIODO_PARTOS_INVALIDO,new String[] { StringUtils.formatDate(posibleConf.getFecha()) });
			}
		}
		
		return true;

    }
    
    /**
     * Evalúa si la cantidad de crías informada por el evento modificador es igual a la cantidad
     * de crías informada por el evento que se quiere modificar. Chequea que los RP de las crías informadas por
     * el evento modificador sean los mismos que los que poseían las crías del evento modificado.
     * @param reprod
     * @return boolean
     * @throws ExcepcionIntegridad
     */
   /* private boolean evaluarCrias(Reprod reprod) throws ExcepcionIntegridad {
    	if(reprod.getCriaCount()!=this.getEvtCrias().size())
    		return false;
    	else{
    		if(this.getEvtCrias().size()>1){
	    		Cria[] xmlCrias  = reprod.getCria();
	    		
	    		for (int i=0;i<xmlCrias.length;i++) {
		    		//if (xmlCrias[i].getEstadoEsVivo()) {
	    				String sexo ="";
	    				boolean esHembra = xmlCrias[i].getSexo().equals(STSexoD.F);
	    				if(esHembra)
	        				sexo = "F";
	        			else{
	        				esHembra = xmlCrias[i].getSexo().equals(STSexoD.M);
	        				if(esHembra)//si es macho
	        					sexo = "M";
	        				else//sino desconocido
	        					sexo = "D";
	        			}
	    				EvtCria evCri = this.encontrarCria(xmlCrias[i].getRp(), this.getEvtCrias());
	    				if(evCri!=null){
	    					if(!this.chequearCambiosCria(xmlCrias[i].getInscribir(), xmlCrias[i].getEstadoEsVivo(),sexo, evCri))
	    						return false;
	    				}else		    	
		    				return false;//ok
		    		//}
	    		}	
    		}
    		else{
    			if(this.getEvtCrias().size()==1){
    			Cria cri = reprod.getCria(0);
    			String sexo ="";
    			boolean esHembra = cri.getSexo().equals(STSexoD.F);
    			if(esHembra)
    				sexo = "F";
    			else{
    				esHembra = cri.getSexo().equals(STSexoD.M);
    				if(esHembra)
    					sexo = "M";
    				else
    					sexo = "D";
    			}
    			EvtCria evt = (EvtCria) this.getEvtCrias().get(0);
    			return this.chequearCambiosCria(cri.getInscribir(), cri.getEstadoEsVivo(), sexo, evt);
    			
    		}
    	}
    		
    	}
    	return true;
    }*/
   
    /**
     * dentro de las crias del parto buscar el animal que tenga el rp informado
     * @param rp
     * @param eventosCria
     * @return
     */
    private EvtCria encontrarCria(String rp,List eventosCria){
    	Iterator it = eventosCria.iterator();
    	while(it.hasNext()){
    		EvtCria evtCria = (EvtCria)it.next();
    		if(((evtCria.getRP()!=null && rp!=null)&&(evtCria.getRP().equals(rp))))//si tiene rp informado puedo identificar la cria
    			return evtCria;
    		}
    	System.out.println("NO encontro la cria a modificar");
    	return null;
    	
    	
    }
    /**
     * Metodo que chequea si son validas las modificaciones uqe se le quieren hacer a la cria
     * inscribir no se puede modificar
     * si estan inscripta la cria, 
     			* se permite el cambio sexo siemrpe que no tenga eventos asociados
     			* se permite el cambio de estado perinatal de muerto a vivo y se vivo a muerto(si no tuvo eventos)
     * @param rp
     * @param inscribir
     * @param estadoEsVivo
     * @param sexo
     * @param evtCria
     * @return
     * @throws ExcepcionIntegridad 
     */
    private void chequearCambiosCria(boolean inscribir,boolean estadoEsVivo,String sexo,EvtCria evtCria) throws ExcepcionIntegridad{
    
    		
    			boolean isInscribir=evtCria.getCria()!=null?true:false;//si estaba inscripto
    			
    			//if((inscribir!= isInscribir))
    			//	return false;//no se puede cambiar el inscribir
    			//else{
    				if(isInscribir){//si esta inscripta//si no estaba inscripta no hay problema con hacer el cambio
    					
    					if(((!sexo.equals(evtCria.getSexo()))&& (!evtCria.getCria().getEvtAnimals().isEmpty()))
    							&&!(evtCria.getCria().getEvtAnimals().size() == 1 
    									&& !(evtCria.getCria().getEventos(EVT_TIPO_BAJ).isEmpty()) 
    									&& (((estadoEsVivo!=evtCria.getEstadoPerinatalEsVivo().booleanValue())) && !evtCria.getEstadoPerinatalEsVivo().booleanValue())) ){
    									//si cambio de sexo y tiene eventos y !(eventos = 1, es bajaAnimal y cambia muerto a vivo)
    						System.out.println("ERROR: DISTINTO SEXO, TIENE + DE UN EVENTO O TIENE UNO Y NO ES UN EVENTO BAJA");
    						//return false;//ver si se puede que retorne la exception para que la tome el que la llama
    						throw new ExcepcionIntegridad(MENSAJES.CAMBIO_SEXO_Y_TIENE_EVENTOS , new String[] {evtCria.getRP()});
    					}
    					if((estadoEsVivo!=evtCria.getEstadoPerinatalEsVivo().booleanValue())){//si cambio el estado perinatal
    						System.out.println("DISTINTO ESTaDO");
    						if((evtCria.getEstadoPerinatalEsVivo().booleanValue())&& (!evtCria.getCria().getEvtAnimals().isEmpty())){//si es vivo a muerto y tiene eventos
    							System.out.println("ERROR: DISTINTO ESTaDO--> VIVO A MUERTO y tiene eventos");
    							//return false;
    							throw new ExcepcionIntegridad(MENSAJES.CAMBIO_ESTADO_VIVO_MUERTO_TIENE_EVENTOS , new String[] {evtCria.getRP()});
    						}
    					}
    				}
    			//}
    		//}    	
    	//return true;
    }
    /**
     * actualiza el animal con los campos enviados como parametro, recalcula la composicion genealogica
     * @param an
     * @param razaDeclaradaNueva
     * @param madreGen
     * @param padreGen
     * @throws ExcepcionIntegridad
     */
    private void actualizar(Animal an,Raza razaDeclaradaNueva,Hembra madreGen,Macho padreGen,String rpSe,Integer dig) throws ExcepcionIntegridad{
    	Especie especie = null;
		if (razaDeclaradaNueva != null)  
			especie = razaDeclaradaNueva.getEspecie();
		else
			if (madreGen != null)	
				especie = madreGen.getEspecie();
			else
				if (padreGen != null)
					especie = padreGen.getEspecie();
				else
					throw new ExcepcionIntegridad(MENSAJES.IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL,new String [0]);
		an.setRpSenasa(rpSe);
		an.setCodigoVerificador(dig);
		an.inicializarPadresGen(especie,madreGen,padreGen);
		an.getComposicionRacial().setRazaDeclarada(razaDeclaradaNueva);
		if((razaDeclaradaNueva ==null)&&(madreGen!=null && padreGen!=null)&&(!madreGen.getRaza().getId().equals(padreGen.getRaza().getId()))){
			if(!an.getComposicionRacial().getRazaCalculada().getEsDesconocido())
				an.getComposicionRacial().setRazaDeclarada(an.getComposicionRacial().getRazaCalculada());
			else
				an.getComposicionRacial().setRazaDeclarada(especie.getCruza());
		}
		an.setPadre(padreGen);
		an.setearCategoria(true);
		
    }


	public Integer getNroLactancia() {
		return nroLactancia;
	}


	public void setNroLactancia(Integer nroLactancia) {
		this.nroLactancia = nroLactancia;
	}


}
