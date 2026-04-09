package ar.org.sicel.proc.handlers.animal;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtAnimalModificacion;
import ar.org.sicel.persistence.EvtAnimalModificacionDAO;
import ar.org.sicel.persistence.EvtSecada;
import ar.org.sicel.persistence.EvtSecadaDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.Secada;

public class HandlerEvtSecada extends HandlerEvt implements HandlerEvtAnimal {
	Logger log = Logger.getLogger(HandlerEvtSecada.class);

	public EvtAnimal handleEvtAnimal(Establecimiento establecimiento,
			Animal objAnimal, Evt evento, List<ProcMsg> mensajes,ProcLote procLote, Date fechaEnvioLote)
			throws ExcepcionIntegridad {

		Secada evtSecada = evento.getSecada();

		if (evtSecada.getModificaOBaja() != null) {
			log.debug("Procesando modificación de evento Secada"
					+ evtSecada.getModificaOBaja().getIDEvt());

			Long idEventoOrig = new Long(evtSecada.getModificaOBaja().getIDEvt());
			Evento ev = EventoDAO.findByPrimaryKey(idEventoOrig);
			if (!(ev instanceof EvtSecada)) {
				log
						.debug("El evento que se está intentando modificar no es de tipo Secada "
								+ evtSecada.getModificaOBaja().getIDEvt());
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.ERROR_TIPO_EVT, new String[] { (ev==null)?"NO EXISTE":String
								.valueOf(ev.toString()),"Secada" });
				throw e;
			}
			EvtSecada evtModificado = (EvtSecada) ev;

			// id de la eclo que informa la Secada original
			Long idEclo = evtModificado.getEstablecimiento().getEclo().getId();

			// id de la eclo que informa
			Long idEcloInformada = evtSecada.getModificaOBaja().getInformante();

			if (!idEclo.equals(idEcloInformada)) {
				log
						.error("El establecimiento que informa la modificación del evento Secada no es el que informó el evento original");
				throw new ExcepcionIntegridad(
						MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE,
						new String[] { idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString() });
			}

			EvtAnimalModificacion eventoModificacion = evtModificado
					.ejecutarModificacion(evtSecada, mensajes, objAnimal);
			EvtAnimalModificacionDAO
					.saveEvtAnimalModificacion(eventoModificacion);
			return eventoModificacion;
		} else {
			log.debug("Procesando Evento Secada" + evtSecada.getIDEvt());
			if (!objAnimal.esHembra())
				throw new ExcepcionIntegridad(MENSAJES.NO_HEMBRA, new String[] {
						objAnimal.getRegIdentificador().getTipoRegistro()
								.getId(),
						objAnimal.getRegIdentificador().getNumero() });
			Hembra hembra = (Hembra) objAnimal;
			EvtSecada secada = EvtSecadaDAO.create(establecimiento, hembra,
					evtSecada.getFecha(), evtSecada.getMotivo(), mensajes);
			super.setAnimalEntidades(objAnimal, establecimiento);
			EvtAnimalDAO.save(secada);
			//HibernateFactory.getSession().save(secada);
			return secada;
		}
	}

}
