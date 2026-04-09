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
import ar.org.sicel.persistence.EvtPrenez;
import ar.org.sicel.persistence.EvtPrenezDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.Prenez;

public class HandlerEvtPrenez extends HandlerEvt implements HandlerEvtAnimal {
	Logger log = Logger.getLogger(HandlerEvtPrenez.class);
	
	public EvtAnimal handleEvtAnimal(Establecimiento establecimiento,
			Animal objAnimal, Evt evento, List<ProcMsg> mensajes,ProcLote procLote, Date fechaEnvioLote)
	throws ExcepcionIntegridad {
		
		
		Prenez evtPrenez = evento.getPrenez();
		log.info("Procesando Evento Preñez" + evtPrenez.getIDEvt());
		
		if (evtPrenez.getModificaOBaja() != null) {
        	Evento evtOld = EventoDAO.findByPrimaryKey(evtPrenez.getModificaOBaja().getIDEvt());
        	
        	if (!(evtOld instanceof EvtPrenez)) { // se quiere modificar por tag de baja un evento baja realmente
        		log.error("Se quiere modificar un evento que no es una baja");
        		ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT,
                        new String[] { (evtOld==null)?"NO EXISTE":String.valueOf(evtOld.toString()),"Preniez"});
                throw e;
        	}
        	EvtPrenez evtPreniezModificar = (EvtPrenez) evtOld;
        	Long idEclo = evtPreniezModificar.getEstablecimiento().getEclo().getId(); // id de la eclo que informo la reproducción original
			Long idEcloInformada = evtPrenez.getModificaOBaja().getInformante(); // id de la eclo informa
			if (!idEclo.equals(idEcloInformada)) {				
				log.error("El establecimiento que informa la modificación de la reproducción no es el que informó la reproducción");
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString()});				
			}
			
			EvtAnimalModificacion eventoModificacion = evtPreniezModificar.ejecutarModificacion(evtPrenez, mensajes, objAnimal);
			EvtAnimalModificacionDAO.saveEvtAnimalModificacion(eventoModificacion);
			return eventoModificacion;

		}
		else {        
			if (!objAnimal.esHembra())
				throw new ExcepcionIntegridad(MENSAJES.NO_HEMBRA, new String[]{objAnimal.getRegIdentificador().getTipoRegistro().getId(),objAnimal.getRegIdentificador().getNumero()});
			Hembra hembra = (Hembra) objAnimal;
			
			EvtPrenez prenez = EvtPrenezDAO.create(establecimiento,hembra,evtPrenez.getFecha(),evtPrenez.getEsPositivo(),mensajes);
			super.setAnimalEntidades(objAnimal, establecimiento);
			EvtAnimalDAO.save(prenez);
			//HibernateFactory.getSession().save(prenez);
			return prenez;
		}
		
	}
	
}
