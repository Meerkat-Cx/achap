package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.v1.lote.Prenez;
import ar.org.sicel.util.DateUtils;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Prenez" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvPrenez_EvAnim"
 * 
 */
public class EvtPrenez extends EvtAnimal {
	
	static Logger log = Logger.getLogger(EvtPrenez.class);
	
	// --------------- attributes ---------------------
	Boolean esPositivo;
	
	
	protected EvtPrenez() {
		super();
	}
	
	public EvtPrenez(Establecimiento est, Date fecha, Hembra hem,List msgs) throws ExcepcionIntegridad {
		super(est,fecha,hem,msgs,Evento.EVT_TIPO_PRE);
	}


	/**
     * 
     * @hibernate.property column="esPositivo"
     * @hibernate.column name="esPositivo" not-null="true" 
     * 
     */	
	public Boolean getEsPositivo() {
		return esPositivo;
	}


	public void setEsPositivo(Boolean esPositivo) {
		this.esPositivo = esPositivo;
	}


	
	
    // ---------------- business methods ----------------------
	
	protected String getResumen() {
		return "Preñez: " + esPositivo;
	}

	
	public String getNombreTipo() {
		return EvtAnimal.EVT_TIPO_PRE;
	}

	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		if(validarBaja()){
    		//Session session = HibernateFactory.getSession();
    		try {
    			/*ProcEvtAnimal pr= this.getProcEvtAnimal();
    			ProcAnimal pa = pr.getProcAnimal();
    			pr.getProcMsgsses().size();
    			int r = pa.getProcMsgsses().size();
    			Long id = pr.getId();
    			System.out.print(id + pa.getId() + r );*/
    			super.ejecutarBaja();
        		EvtAnimalDAO.deleteEvtAnim(this);
        		
        		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        		Eclo ed = EcloDAO.findByPrimaryKey(9L);
    	    	System.out.print(ed.getId());
    			//session.delete(this);
    		} catch (HibernateException he){
                ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                        new String[] { String.valueOf(getId()), he.toString() });
                throw e;
    		}
    	}
	}

	public EvtAnimalModificacion ejecutarModificacion(Prenez evtPrenez, List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		// EL ANIMAL DE LA BAJA NO SE PUEDE MODIFICAR
		if(!this.getAnimal().getId().equals(objAnimal.getId())){
			log.debug("El animal informado no posee el evento informado como evento reproducción asociado " + evtPrenez.getModificaOBaja().getIDEvt());
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO,
					new String[] { this.getAnimal().getRegistroOrigen(), objAnimal.getRegistroOrigen()});
			throw e;
		}    
		
//		 VALIDAR QUE NO SE SOLAPE CON LA FECHA DEL EVENTO ANTERIOR NI CON LA FECHA DEL EVENTO POSTERIOR
		Evento evtAnt = this.findEventoAnterior();
		if(evtAnt!=null){
			if(evtPrenez.getFecha().before(evtAnt.getFecha())){ // ok
				log.debug("La fecha del evento que modifica es menor que el evento anterior al evento modificado: " + this.getId());
				ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_ANT,
						new String[] { DateUtils.format(evtPrenez.getFecha(),null), DateUtils.format(evtAnt.getFecha(),null)});
				throw e;
				
			}
		}
		Evento evtPost = this.findEventoPosterior();
		if(evtPost!=null){
			if(evtPrenez.getFecha().after(evtPost.getFecha())){ // ok
				log.debug("La fecha del evento que modifica es mayor que el evento posterior al evento modificado: " + this.getId());
				ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_POST,
						new String[] { DateUtils.format(evtPrenez.getFecha(),null), DateUtils.format(evtPost.getFecha(),null)});
				throw e;
			}
		}					
		
		this.setEsPositivo(evtPrenez.getEsPositivo()); // preguntar que es lo de cambio de estado		
		
		this.setFecha(evtPrenez.getFecha()); // actualizo la fecha
		
		/*
		 * SAQUE ESTA VALIDACION PORQUE EL EVENTO PREÑEZ NO FINALIZA LACTANCIA		 * 
		 */
		/*if (!this.getFecha().equals(evtPrenez.getFecha())) {
			this.setFecha(evtPrenez.getFecha()); // actualizo la fecha, tengo que recalcular las lactancias posteriores
			
			Iterator eventosDependientes = this.getDependientes().iterator();
			while (eventosDependientes.hasNext()) {
				Evento evento =(Evento)eventosDependientes.next();
				if (evento instanceof EvtLactancia) {
					EvtLactancia evtLactancia = (EvtLactancia) evento;
					if (evtLactancia.isEsCerrada()) { // esta condicion esta demas siempre el evento lactancia asociado con el evento preñez
														// esta cerrada
						log.debug("El evento preñez tiene asociado un evento lactancia"); //
						ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_LACTANCIA_ASOCIADA,
									new String[] {this.getId().toString(), evtLactancia.getId().toString()});
						throw e;
					}
				}
			}
		}*/
		
		EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO.create(objAnimal.getEstablecimiento(), new Date(), objAnimal, this);
		this.addModificaciones(eventoModificacion);
		EvtPrenezDAO.updateEvento(this);
		return eventoModificacion;

	}

}
