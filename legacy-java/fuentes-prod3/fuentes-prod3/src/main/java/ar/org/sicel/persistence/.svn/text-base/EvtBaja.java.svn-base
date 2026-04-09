package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;
import ar.org.sicel.proc.v1.lote.Baja;
import ar.org.sicel.proc.v1.lote.types.STDestino;
import ar.org.sicel.util.DateUtils;


/**
 *
 * @hibernate.joined-subclass
 *    table="Ev_Baja"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvBaja_EvAnim"
 *
 */
public class EvtBaja extends ar.org.sicel.persistence.EvtAnimal {
	
	static Logger log = Logger.getLogger(EvtBaja.class);
	
    // --------------- attributes ---------------------
    private java.lang.String destino;
    private java.lang.String motivo;
    private java.lang.String comentario;

    protected EvtBaja(Establecimiento est, Date fecha, Animal animal, List msgs) throws ExcepcionIntegridad {
    	super(est,fecha,animal,msgs,Evento.EVT_TIPO_BAJ);
    }
    
    protected EvtBaja() {
    	
    }
    
    

    /**
     *
     * @hibernate.property
     *     column="destino"
     * @hibernate.column
     *     name="destino"
     *     not-null="true"
     *
     */
    public java.lang.String getDestino() {
        return this.destino;
    }

    protected void setDestino(java.lang.String destino) {
        this.destino = destino;
    }

    /**
     *
     * @hibernate.property
     *     column="motivo"
     *
     */
    public java.lang.String getMotivo() {
        return this.motivo;
    }

    protected void setMotivo(java.lang.String motivo) {
        this.motivo = motivo;
    }

    /**
     *
     * @hibernate.property
     *     column="comentario"
     *
     */
    public java.lang.String getComentario() {
        return this.comentario;
    }

    protected void setComentario(java.lang.String comentario) {
        this.comentario = comentario;
    }

	/* (non-Javadoc)
	 * @see ar.org.sicel.persistence.Evento#getResumen()
	 */
	public String getResumen() {
		//return null;
		 String result = "Destino: "+this.getDestino() + ((this.getComentario()!=null)?this.getComentario():"");
		 return result;
	}

	/*
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		return EVT_TIPO_BAJ;
	}

	/*
	public String getClazzParaTransicion() {
		return EvtBaja.class.getSimpleName(); 
	}
	*/

    // ------------- relations ------------------
    // ---------------- business methods  ----------------------

	/**
	 * si el evento finaliza lactancia y la generó tambien la da de baja 
	 */
	public boolean validarBaja() throws ExcepcionIntegridad {
		 
		/*if (!this.getDependientes().isEmpty() && this.getDependientes().first() instanceof EvtLactancia){ 
				EvtLactancia evtLactancia = (EvtLactancia)this.getDependientes().first();
				if(evtLactancia.getFecha().equals(this.getFecha())){
					if (evtLactancia.isEsOficial())
		        		AnimalesLactanciasOficiales.getAnimales().remove(this.getAnimal());
					evtLactancia.ejecutarBaja();
				}
		   }*/
		/*if(!this.getDependientes().isEmpty()){
			   Iterator it_dependientes = this.getDependientes().iterator(); 
			   while(it_dependientes.hasNext()){
				   EvtAnimal evento = (EvtAnimal)it_dependientes.next();
				   if(evento instanceof EvtLactancia){
					   EvtLactancia evtLactancia = (EvtLactancia)evento;
						if(evtLactancia.getFecha().equals(this.getFecha())){
							if (evtLactancia.isEsOficial())
				        		AnimalesLactanciasOficiales.getAnimales().remove(this.getAnimal());
							evtLactancia.ejecutarBaja();
						}	
				   }
			   }
		   }*/
		/*List eventosLactancias = this.getAnimal().getEventos(EVT_TIPO_LAC);
		Iterator it3 = eventosLactancias.iterator();
		while(it3.hasNext()){
			EvtLactancia ev = (EvtLactancia)it3.next();
			if(ev.getFecha().equals(this.getFecha())){
				if (ev.isEsOficial())
	        		AnimalesLactanciasOficiales.getAnimales().remove(this.getAnimal());
				ev.ejecutarBaja();
			}
		}*/
		return true;
	}
	
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		/*if(validarBaja()){
			super.ejecutarBaja();
			try{
			HibernateFactory.getSession().delete(this);
			}catch (HibernateException he){
                ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                        new String[] { String.valueOf(getId()), he.toString() });
                throw e;
    		}
		}*/
		//copiado de baja de secada
		if (validarBaja()) {
			Session session = HibernateFactory.getSession();
			super.ejecutarBaja();
			try {
			/*if(this.isFinalizaLactancia()){
				try {
					EvtLactancia lacti =null;
					List eventosLactancias = this.getAnimal().getEventos(EVT_TIPO_LAC);
					Iterator it = eventosLactancias.iterator();
					while(it.hasNext()){
						EvtLactancia ev = (EvtLactancia)it.next();
						if(ev.getFecha().equals(this.getFecha())){
							lacti = ev;
							
							break;
						}
					}
					if(lacti!=null)
					{
						this.getDependientes().remove(lacti);
						this.getAnimal().getEvtAnimals().remove(lacti);
						if (lacti.isEsOficial())
			        		AnimalesLactanciasOficiales.getAnimales().remove(this);
						if(lacti.getId()!=null)
							lacti.ejecutarBaja();
						
						AnimalDAO.updateAnimal(this.getAnimal());
		
						//borro la Secada
						//if(this.getDependientes()!=null){
						ProcEvtAnimal pr= this.getProcEvtAnimal();
						ProcAnimal pa = pr.getProcAnimal();
						pr.getProcMsgsses().size();
						int r = pa.getProcMsgsses().size();
						Long id = pr.getId();
						System.out.print(id + pa.getId() + r );	    	    	
						//session.delete(this);
				}
				} catch (HibernateException he) {
					ExcepcionIntegridad e = new ExcepcionIntegridad(
							MENSAJES.EVT_ELIM, new String[] {
									String.valueOf(getId()), he.toString() });
					throw e;
				}
			}
			session.delete(this);*/
			EvtAnimalDAO.deleteEvtAnim(this);
			//super.ejecutarBaja();
			} catch (HibernateException he) {
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.EVT_ELIM, new String[] {
								String.valueOf(getId()), he.toString() });
				throw e;
			}
		
			
		}

	}

	public EvtAnimalModificacion ejecutarModificacion(Baja baja, List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		// EL ANIMAL DE LA BAJA NO SE PUEDE MODIFICAR
		if(!this.getAnimal().getId().equals(objAnimal.getId())){
			log.debug("El animal informado no posee el evento informado como evento reproducción asociado " + baja.getModificaOBaja().getIDEvt());
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO,
					new String[] { this.getAnimal().getRegistroOrigen(), objAnimal.getRegistroOrigen()});
			throw e;
		}    
		
		// VALIDAR QUE NO SE SOLAPE CON LA FECHA DEL EVENTO ANTERIOR NI CON LA FECHA DEL EVENTO POSTERIOR
		Evento evtAnt = this.findEventoAnterior();
		if(evtAnt!=null){
			if(baja.getFecha().before(evtAnt.getFecha())){ // ok
				log.debug("La fecha del evento que modifica es menor que el evento anterior al evento modificado: " + this.getId());
				ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_ANT,
						new String[] { DateUtils.format(baja.getFecha(),null), DateUtils.format(evtAnt.getFecha(),null)});
				throw e;
				
			}
		}
		Evento evtPost = this.findEventoPosterior();
		if(evtPost!=null){
			if(baja.getFecha().after(evtPost.getFecha())){ // ok
				log.debug("La fecha del evento que modifica es mayor que el evento posterior al evento modificado: " + this.getId());
				ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_POST,
						new String[] { DateUtils.format(baja.getFecha(),null), DateUtils.format(evtPost.getFecha(),null)});
				throw e;
			}
		}		
		this.setFecha(baja.getFecha()); // actualizo la fecha
		
		
		// si es por VENTA por venta, verificar si hay evento transferencia posterior a la fecha nueva
		// destino es obligatorio
		if (baja.getDestino().equals(STDestino.VETA)) {
			if (hayTansferenciaPosterior(baja.getFecha())) { // ok
				log.error("Existe un evento transferencia posterior a la fecha nueva");
				ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_POST,
						new String[] { DateUtils.format(baja.getFecha(),null), DateUtils.format(evtPost.getFecha(),null)});
				throw e;
			}
		}		
		this.setDestino(baja.getDestino().toString());
		this.setMotivo(baja.getMotivo().toString());
		this.setComentario(baja.getComentariosBaja());
		
		EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO.create(objAnimal.getEstablecimiento(), new Date(), objAnimal, this);
		this.addModificaciones(eventoModificacion);
		EvtBajaDAO.updateEventoBaja(this);
		return eventoModificacion;

		

	}

	/**
	 * Verifica si hay un evento del tipo Transferencia, posterior a la fecha dada
	 * @param fecha
	 * @return
	 */
	private boolean hayTansferenciaPosterior(Date fecha) {
		List eventosLactancias = this.getAnimal().getEventos(EVT_TIPO_TRA);
		Iterator i = eventosLactancias.iterator();
		while (i.hasNext()) {
			Evento e = (Evento) i.next();
			if (e.getFecha().after(fecha)) { // existe un evento posteior
				return true;
			}
		}
		return false;
	}
	
}
