/*
 * Created on 15/04/2005
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
import ar.org.sicel.persistence.EvtAlta;
import ar.org.sicel.persistence.EvtAltaDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtAnimalModificacion;
import ar.org.sicel.persistence.EvtAnimalModificacionDAO;
import ar.org.sicel.persistence.EvtBaja;
import ar.org.sicel.persistence.EvtBajaDAO;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Baja;
import ar.org.sicel.proc.v1.lote.Evt;


/**
 * @author pablo
 */
public class HandlerBajaAnimal implements HandlerEvtAnimal {
    private static Logger log = Logger.getLogger(HandlerBajaAnimal.class);

    /* (non-Javadoc)
     * @see ar.org.sicel.proc.handlers.HandlerEvtAnimal#handleEvtAnimal(java.lang.Object, ar.org.sicel.proc.gen.Evt)
     */
    public EvtAnimal handleEvtAnimal(Establecimiento est, Animal objAnimal, Evt evento, List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
        log.info("Procesando baja animal " + evento.getBaja().getIDEvt());
        Baja baja =evento.getBaja();
        
        
        if (baja.getModificaOBaja()!= null){
        	Evento evtOld = EventoDAO.findByPrimaryKey(baja.getModificaOBaja().getIDEvt());
        	
        	if (!(evtOld instanceof EvtBaja)) { // se quiere modificar por tag de baja un evento baja realmente
        		log.error("Se quiere modificar un evento que no es una baja");
        		ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT,
                        new String[] { (evtOld==null)?"NO EXISTE":String.valueOf(evtOld.toString()), "Baja"});
                throw e;
        	}
        	EvtBaja evtBajaModificar = (EvtBaja) evtOld;
        	Long idEclo = evtBajaModificar.getEstablecimiento().getEclo().getId(); // id de la eclo que informo la reproducción original
			Long idEcloInformada = baja.getModificaOBaja().getInformante(); // id de la eclo informa
			if (!idEclo.equals(idEcloInformada)) {				
				log.error("El establecimiento que informa la modificación de la reproducción no es el que informó la reproducción");
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString()});				
			}
			
			EvtAnimalModificacion eventoModificacion = evtBajaModificar.ejecutarModificacion(baja, mensajes, objAnimal);
			EvtAnimalModificacionDAO.saveEvtAnimalModificacion(eventoModificacion);
			return eventoModificacion;
        }
        else {
        	EvtBaja evtBaja = EvtBajaDAO.create(est,objAnimal,baja.getDestino().toString(),baja.getMotivo().toString(),baja.getComentariosBaja(),baja.getFecha(),mensajes);
	       
	
	        try {
	        	EvtAnimalDAO.save(evtBaja);
	  			//HibernateFactory.getSession().save(evtBaja);
	  		} catch (HibernateException e) {
	  			throw new ErrorFatal("No se pudo guardar el evento",e);
	  		}
	        return evtBaja;
        }
    }
}
