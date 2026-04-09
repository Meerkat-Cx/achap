package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;
import ar.org.sicel.proc.v1.lote.Secada;
import ar.org.sicel.util.DateUtils;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Secada" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvSecada_EvAnim"
 * 
 */
public class EvtSecada extends EvtAnimal {

	// --------------- attributes ---------------------

	private String motivo;
	
	static Logger log = Logger.getLogger(EvtSecada.class);

	protected EvtSecada() {
		super();
	}

	public EvtSecada(Establecimiento est, Date fecha, Animal animal, List msgs)
			throws ExcepcionIntegridad {
		super(est, fecha, animal, msgs,Evento.EVT_TIPO_SEC);
	}

	/**
	 * 
	 * @hibernate.property column="motivo"
	 * @hibernate.column name="motivo" not-null="false" length="32"
	 * 
	 */
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	// ---------------- business methods ----------------------

	protected String getResumen() {
		return "Secada, motivo: " + motivo;
	}

	public String getNombreTipo() {
		return EvtAnimal.EVT_TIPO_SEC;
	}

	public boolean validarBaja() throws ExcepcionIntegridad {
		// siempre se aceptan las bajas de la secada, solo se debe tener en
		// cuenta si existe alguna lactancia relacionada que dar de baja
		return true;
	}

	public void ejecutarBaja() throws ExcepcionIntegridad {
		if (validarBaja()) {
			super.ejecutarBaja();
			Session session = HibernateFactory.getSession();
			try {
				/*EvtLactancia lacti =null;
				List eventosLactancias = this.getAnimal().getEventos(EVT_TIPO_LAC);
				Iterator it = eventosLactancias.iterator();
				while(it.hasNext()){
					EvtLactancia ev = (EvtLactancia)it.next();
					if(ev.getFecha().equals(this.getFecha())){
						lacti = ev;
						break;
					}
				}
				if(lacti!=null){
					this.getDependientes().remove(lacti);
					this.getAnimal().getEvtAnimals().remove(lacti);
					if (lacti.isEsOficial())
		        		AnimalesLactanciasOficiales.getAnimales().remove(this);
					if(lacti.getId()!=null)
						lacti.ejecutarBaja();
				}
				AnimalDAO.updateAnimal(this.getAnimal());*/

				//borro la Secada
				//if(this.getDependientes()!=null){
				ProcEvtAnimal pr= this.getProcEvtAnimal();
				ProcAnimal pa = pr.getProcAnimal();
				pr.getProcMsgsses().size();
				int r = pa.getProcMsgsses().size();
				Long id = pr.getId();
				System.out.print(id + pa.getId() + r );
				
				
				EvtAnimalDAO.deleteEvtAnim(this);
				//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
				//Eclo ed = EcloDAO.findByPrimaryKey(9L);
    	    	//System.out.print(ed.getId());
				
			} catch (HibernateException he) {
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.EVT_ELIM, new String[] {
								String.valueOf(getId()), he.toString() });
				throw e;
			}
		}
	}

	public EvtAnimalModificacion ejecutarModificacion(Secada evtSecada,
			List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		if (!this.getAnimal().getId().equals(objAnimal.getId())) {
			log
					.debug("El animal informado no posee el evento informado como evento reproducción asociado "
							+ evtSecada.getModificaOBaja().getIDEvt());
			ExcepcionIntegridad e = new ExcepcionIntegridad(
					MENSAJES.ERROR_ANIMAL_INFO, new String[] {
							this.getAnimal().getRegistroOrigen(),objAnimal.getRegistroOrigen()});
			throw e;
		}

		Evento evtAnt = this.findEventoAnterior();
		if (evtAnt != null) {
			if (evtSecada.getFecha().before(evtAnt.getFecha())) {
				log
						.debug("La fecha del evento que modifica es menor que el evento anterior al evento modificado: "
								+ this.getId());
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.ERROR_EVENTO_ANT, new String[] {
								DateUtils.format(evtSecada.getFecha(), null),
								DateUtils.format(evtAnt.getFecha(), null) });
				throw e;

			}
		}
		Evento evtPost = this.findEventoPosterior();
		if (evtPost != null) {
			if (evtSecada.getFecha().after(evtPost.getFecha())) {
				log
						.debug("La fecha del evento que modifica es mayor que el evento posterior al evento modificado: "
								+ this.getId());
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.ERROR_EVENTO_POST, new String[] {
								DateUtils.format(evtSecada.getFecha(), null),
								DateUtils.format(evtPost.getFecha(), null) });
				throw e;
			}
		}
		
		//TODO pruggia Aca deberiamos cambiar la fecha de la lactancia o recalcularla no ?

		this.setFecha(evtSecada.getFecha());
		this.setMotivo(evtSecada.getMotivo());
		
		EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO
				.create(objAnimal.getEstablecimiento(), new Date(), objAnimal,
						this);
		this.addModificaciones(eventoModificacion);
		EvtSecadaDAO.updateEventoSecada(this);
		return eventoModificacion;
	}

}
