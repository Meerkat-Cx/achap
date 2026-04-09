package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Estado;
import ar.org.sicel.util.DateUtils;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Estado" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvEstado_EvAnim"
 * 
 */
public class EvtEstado extends ar.org.sicel.persistence.EvtAnimal {
	
	static Logger log = Logger.getLogger(EvtEstado.class);
    // --------------- attributes ---------------------

    // TODO Puede hacer falta el dato "fechaInicioLactancia" en el evento
    // Estado, si bien al dar de alta el animal no se deberia permitir eventos
    // anteriores al alta.
	
	private Date fechaInicioLactancia;

    private java.lang.String estado;

    private java.lang.Integer cantLactTerminadas;
    
    private java.lang.Integer numeroLactancia;
    

    protected EvtEstado() {
    }
    
    protected EvtEstado(Establecimiento est, Date fecha, Animal an, String estado, int cantLactTerminadas, List msgs,
    		Date fechaInicioUltimaLactancia, Integer numeroLactancia) throws ExcepcionIntegridad {
    	super(est,fecha,an,msgs,Evento.EVT_TIPO_EST);
    	this.setEstado(estado);
    	//this.estado = estado;
    	this.setCantLactTerminadas(cantLactTerminadas);
    	//this.cantLactTerminadas = cantLactTerminadas;
    	this.setFechaInicioLactancia(fechaInicioUltimaLactancia);
    	//this.fechaInicioLactancia = fechaInicioUltimaLactancia;
    	this.setNumeroLactancia(numeroLactancia);
    	//this.numeroLactancia = numeroLactancia;
    	an.addEventoAnimal(this,msgs);
    	//an.setEstadoRetroactivo(this, msgs);
    	an.setEstadoRetroactivo(this.getFecha(), msgs,this.getNombreTipo());
    }
    
    
    
    

    /**
     * 
     * @hibernate.property column="estado"
     * @hibernate.column name="estado" not-null="true" length="4"
     * 
     */
    public java.lang.String getEstado() {
        return this.estado;
    }

    protected void setEstado(java.lang.String estado) {
        this.estado = estado;
    }

    /**
     * 
     * @hibernate.property column="cantLactTerminadas"
     * 
     */
    public java.lang.Integer getCantLactTerminadas() {
        return this.cantLactTerminadas;
    }

    protected void setCantLactTerminadas(java.lang.Integer cantLactTerminadas) {
        this.cantLactTerminadas = cantLactTerminadas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.org.sicel.persistence.Evento#getResumen()
     */
    public String getResumen() {
        return null;
    }

    /*
     * @see ar.org.sicel.persistence.Evento#getNombreTipo()
     */
    public String getNombreTipo() {
        return EVT_TIPO_EST;
    }
    
    
    public Date getFechaInicioLactancia() {
		return fechaInicioLactancia;
	}

	public void setFechaInicioLactancia(Date fechaInicioLactancia) {
		this.fechaInicioLactancia = fechaInicioLactancia;
	}

   /* public Date getFechaLactancia() {
    	return getFechaInicioLactancia();
    }*/

    // ------------- relations ------------------
    // ---------------- business methods ----------------------
/*
    public String getClazzParaTransicion() {
        return EvtEstado.class.getSimpleName();
    }
    */
    
	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		//no se aceptan bajas del evento estado
		return false;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		//no se aceptan bajas para el evento estado
		//TODO pruggia Agregar el mensaje de que no se puede dar de baja
		throw new ExcepcionIntegridad(MENSAJES.BAJA_ESTADO, new String[]{});
	}

	public EvtAnimalModificacion ejecutarModificacion(Estado evtEstado, List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		
		//Lo saque de aca por q no buscaba bien los evt anteriores y posteriores
		//this.setFecha(evtEstado.getFecha()); 		
		if(!this.getAnimal().getId().equals(objAnimal.getId())){
			log.debug("El animal informado no posee el evento informado como evento reproducción asociado " + evtEstado.getModificaOBaja().getIDEvt());
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO,
                    new String[] { this.getAnimal().getRegistroOrigen(), objAnimal.getRegistroOrigen()});
            throw e;
		}		
		Evento evtAnt = this.findEventoAnterior();
		 if(evtAnt!=null){
			 if(evtEstado.getFecha().before(evtAnt.getFecha())){
				 log.debug("La fecha del evento que modifica es menor que el evento anterior al evento modificado: " + this.getId());
	         	 ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_ANT,
	         			 new String[] { DateUtils.format(evtEstado.getFecha(),null), DateUtils.format(evtAnt.getFecha(),null)});
	             throw e;				 
			 }
		 }
		 Evento evtPost = this.findEventoPosterior();
		 if(evtPost!=null){
			 if(evtEstado.getFecha().after(evtPost.getFecha())){
				 log.debug("La fecha del evento que modifica es mayor que el evento posterior al evento modificado: " + this.getId());
	         	 ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_POST,
	                     new String[] { DateUtils.format(evtEstado.getFecha(),null), DateUtils.format(evtPost.getFecha(),null)});
	             throw e;
			 }
		 }		
		 //no se permite que se cambie el estado informado
		if(!this.getEstadoAnimal().equals(evtEstado.getEstado().toString())){
			throw new ExcepcionIntegridad(MENSAJES.MODIF_ESTADO_INFORMADO,
                    new String[] {evtEstado.getEstado().toString(), this.getEstadoAnimal()});
		}		
		this.setFecha(evtEstado.getFecha());
		this.cantLactTerminadas = evtEstado.getCantLactTerm();		
		/*
		 * Se rechaza la modificacion de la fecha de inicio de la ultima lactancia si en el evento estado original
		 * se informo una fecha, en la modificacion se informo otra, y la lactancia ya fue cerrada, o en el
		 * caso de que en el evento original no se haya informado fecha de inicio de ultima lactancia 
		 * y en la madificacion del evento si se informe.
		 */
		if(evtEstado.getFechaIniUltLactancia()!= null){ 
			if(this.getFechaInicioLactancia() == null) 
				throw new ExcepcionIntegridad(MENSAJES.FECHA_NO_INFORMADA,new String[] {});
			if(!evtEstado.getFechaIniUltLactancia().toDate().equals(this.getFechaInicioLactancia()))
				for(Object evt : this.getAnimal().getAllEventosSinLactanciaAbierta()){				
					if(evt instanceof EvtLactancia){
						EvtLactancia evtLact = (EvtLactancia)evt;
						if(!evtLact.getFechaInicio().equals(this.getFechaInicioLactancia()))
							throw new ExcepcionIntegridad(MENSAJES.LACTANCIA_CERRADA,new String[] {});
					}
				}
			this.fechaInicioLactancia = evtEstado.getFechaIniUltLactancia().toDate();
		}		
		EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO.create(objAnimal.getEstablecimiento(), new Date(), objAnimal, this);
		this.addModificaciones(eventoModificacion);
		EvtEstadoDAO.updateEventoEstado(this);
		return eventoModificacion;		
	}

	public java.lang.Integer getNumeroLactancia() {
		return numeroLactancia;
	}

	public void setNumeroLactancia(java.lang.Integer numeroLactancia) {
		this.numeroLactancia = numeroLactancia;
	}
}
