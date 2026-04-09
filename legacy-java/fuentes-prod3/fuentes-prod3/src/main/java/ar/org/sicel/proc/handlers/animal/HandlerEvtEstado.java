/*
 * Created on 19/04/2005
 */
package ar.org.sicel.proc.handlers.animal;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtAnimalModificacion;
import ar.org.sicel.persistence.EvtAnimalModificacionDAO;
import ar.org.sicel.persistence.EvtEstado;
import ar.org.sicel.persistence.EvtEstadoDAO;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Estado;
import ar.org.sicel.proc.v1.lote.Evt;

/**
 * @author pablo
 */
public class HandlerEvtEstado extends HandlerEvt implements HandlerEvtAnimal {
	
	Logger log = Logger.getLogger(HandlerEvtEstado.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.org.sicel.proc.handlers.HandlerEvtAnimal#handleEvtAnimal(java.lang.Object,
	 *      ar.org.sicel.proc.gen.Evt)
	 */
	public EvtAnimal handleEvtAnimal(Establecimiento est, Animal objAnimal,
			Evt evento, List<ProcMsg> mensajes,ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
		
		Estado evtEstado = evento.getEstado();
		
		
		if (evtEstado.getModificaOBaja() != null) {
			log.debug("Procesando modificación de evento estado animal "
					+ evtEstado.getModificaOBaja().getIDEvt());
			
			Long idEventoOrig = new Long(evtEstado.getModificaOBaja()
					.getIDEvt());
			Evento ev = EventoDAO.findByPrimaryKey(idEventoOrig);
			if (!(ev instanceof EvtEstado)) {
				log
						.debug("El evento que se está intentando modificar no es de tipo estado "
								+ evtEstado.getModificaOBaja().getIDEvt());
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.ERROR_TIPO_EVT, new String[] { (ev==null)?"NO EXISTE":String
								.valueOf(ev.toString()),"Estado" });
				throw e;
			}
			
			EvtEstado evtModificado = (EvtEstado) ev;
			
			// id de la eclo que informa el info original
			Long idEclo = evtModificado.getEstablecimiento().getEclo().getId(); 
 
			//id de la eclo que informa
			Long idEcloInformada = evtEstado.getModificaOBaja().getInformante();
			
			if (!idEclo.equals(idEcloInformada)) {
				log
						.error("El establecimiento que informa la modificación del estado no es el que informó el estado");
				throw new ExcepcionIntegridad(
						MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE,
						new String[] { idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString() });
			}
			
			EvtAnimalModificacion eventoModificacion = evtModificado.ejecutarModificacion(evtEstado, mensajes, objAnimal);
			EvtAnimalModificacionDAO.saveEvtAnimalModificacion(eventoModificacion);
			return eventoModificacion;}
		else{			
			/*if(evtEstado.getNumLactancia() != 0){				
				objAnimal.setNroLactInformado(evtEstado.getNumLactancia());
			}	*/							
			log.info("Procesando Estado animal " + evtEstado.getIDEvt());
			EvtEstado evt = EvtEstadoDAO.create(est, objAnimal, evtEstado
					.getEstado().toString(), evtEstado.getCantLactTerm(), evtEstado
					.getFecha(), mensajes,
					evtEstado.getFechaIniUltLactancia() == null ? null : evtEstado
							.getFechaIniUltLactancia().toDate(),evtEstado.getNumLactancia());
			
			
			if (evt.getFechaInicioLactancia() == null && evt.isIniciaLactancia())
				throw new ExcepcionIntegridad(
						MENSAJES.ESTADO_INICIA_LACTANCIA_NO_INFORMA_FECHA,
						new String[] {});
			// VALIDAR LO QUE TENGO QUE VALIDAR
			
			try {
				super.setAnimalEntidades(objAnimal, est);
				EvtAnimalDAO.save(evt);
				//HibernateFactory.getSession().save(evt);
			} catch (HibernateException e) {
				throw new ErrorFatal("No se pudo guardar el evento", e);
			}
			return evt;
			
		}
		
	}
}
