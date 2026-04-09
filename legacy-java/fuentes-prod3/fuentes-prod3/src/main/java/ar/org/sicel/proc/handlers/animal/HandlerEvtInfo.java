package ar.org.sicel.proc.handlers.animal;

import java.util.Date;
import java.util.Enumeration;
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
import ar.org.sicel.persistence.EvtInfo;
import ar.org.sicel.persistence.EvtInfoDAO;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.Info;

public class HandlerEvtInfo extends HandlerEvt implements HandlerEvtAnimal {

	static Logger log = Logger.getLogger(HandlerEvtInfo.class);

	public EvtAnimal handleEvtAnimal(Establecimiento establecimiento,
			Animal objAnimal, Evt evento, List<ProcMsg> mensajes,ProcLote procLote, Date fechaEnvioLote)
			throws ExcepcionIntegridad {

		Info info = evento.getInfo();

		if (info.getModificaOBaja() != null) {
			log.debug("Procesando modificación de evento información animal "
					+ info.getModificaOBaja().getIDEvt());
			
			Long idEventoOrig = new Long(info.getModificaOBaja()
					.getIDEvt());
			Evento ev = EventoDAO.findByPrimaryKey(idEventoOrig);
			if (!(ev instanceof EvtInfo)) {
				log
						.debug("El evento que se está intentando modificar no es de tipo info "
								+ info.getModificaOBaja().getIDEvt());
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.ERROR_TIPO_EVT, new String[] { (ev==null)?"NO EXISTE":String
								.valueOf(ev.toString()), "Informacion" });
				throw e;
			}
			EvtInfo evtModificado = (EvtInfo) ev;
			
			// id de la eclo que informa el info original
			Long idEclo = evtModificado.getEstablecimiento().getEclo().getId(); 
 
			//id de la eclo que informa
			Long idEcloInformada = info.getModificaOBaja().getInformante();
			
			if (!idEclo.equals(idEcloInformada)) {
				log
						.error("El establecimiento que informa la modificación del evento Info no es el que informó el evento original");
				throw new ExcepcionIntegridad(
						MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE,
						new String[] { idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString() });
			}
			
			EvtAnimalModificacion eventoModificacion = evtModificado.ejecutarModificacion(info, mensajes, objAnimal);
			EvtAnimalModificacionDAO.saveEvtAnimalModificacion(eventoModificacion);
			return eventoModificacion;
		} else {
			String vss = "";
			EvtInfo evt =null;
			if(info.getPesoAnimal()!=null){
				evt = EvtInfoDAO.create(establecimiento, objAnimal, info.getFecha(), "PESO", String.valueOf(info.getPesoAnimal().getValor()), mensajes);
			}
			else{
				if(info.getOtraInfo().getValores()!=null){
					Enumeration vs = info.getOtraInfo().getValores().enumerateValor();
					while (vs.hasMoreElements()) {
						String valor = (String) vs.nextElement();
						vss = vss + " " + valor;
					}
				}
				evt = EvtInfoDAO.create(establecimiento, objAnimal, info.getFecha(), info.getOtraInfo().getDescrip(), vss, mensajes);
				}
			try {
				super.setAnimalEntidades(objAnimal, establecimiento);
				//HibernateFactory.getSession().save(evt);
				EvtAnimalDAO.save(evt);
			} catch (HibernateException e) {
				throw new ErrorFatal("No se pudo guardar el evento", e);
			}
			return evt;
			// throw new ErrorFatal("Falta arreglar el evento info");
		}
	}

}
