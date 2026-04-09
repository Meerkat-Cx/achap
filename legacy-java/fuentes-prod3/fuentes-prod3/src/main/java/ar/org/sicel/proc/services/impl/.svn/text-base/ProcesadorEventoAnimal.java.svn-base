/*
 * Created on 12/04/2005
 *
 */
package ar.org.sicel.proc.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.ProcEvtAnimal;
import ar.org.sicel.persistence.ProcEvtAnimalDAO;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.services.IProcesadorEventoAnimal;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.TEvento;
import ar.org.sicel.util.Sicel3Conf;

/**
 * @author pablo
 *
 */
public class ProcesadorEventoAnimal implements IProcesadorEventoAnimal {
       
	private static Logger log = Logger.getLogger(ProcesadorEventoAnimal.class);
	private Map<Class,HandlerEvtAnimal> handlers;
	
	
	public ProcesadorEventoAnimal() {
		log.debug("Creando procesador animal");
		loadHandlers();
	}
	
	private void loadHandlers() {
		handlers = new HashMap<Class,HandlerEvtAnimal>();
		try {
			Configuration[] cHandlers = Sicel3Conf.getConf().getConfProcEventoAnimal().getChild("handlers").getChildren("handler");
			for (int i = 0; i < cHandlers.length; i++) {
				String castor = cHandlers[i].getChild("castor-class").getValue();
				String handler = cHandlers[i].getChild("handler-class").getValue();
				addHandler(castor, handler);
			}
			log.debug("Cargados todos los Handlers");
		} catch (ConfigurationException e) {
			log.error("Error en la configuracion", e);
		}
	}
	
	private void addHandler(String castor, String handler) {
		Class castorClass = null;
		try {
			castorClass = Class.forName(castor);
		} catch (ClassNotFoundException e) {
			log.error("Clase castor no encontrada",e);
		}
		HandlerEvtAnimal handlerObj = null;
		try {
			handlerObj = (HandlerEvtAnimal)Class.forName(handler).newInstance();
		} catch (InstantiationException e1) {
			log.error("Error al instanciar el Handler",e1);
		} catch (IllegalAccessException e1) {
			log.error("Error al instanciar el Handler",e1);
		} catch (ClassNotFoundException e1) {
			log.error("Clase del handler no encontrada",e1);
		}
		if(castorClass != null && handlerObj != null) {
			handlers.put(castorClass,handlerObj);
			log.debug("Agregado Handler: " + handlerObj.getClass().getSimpleName());
		} else {
			log.error("algun error paso");
		}
	}
	
	
	/**
	 * 
	 * 
	 * @see ar.org.sicel.proc.services.IProcesadorEventoAnimal#procesarEventoAnimal(java.lang.Object, ar.org.sicel.proc.gen.Evt)
	 */
	// @RequiresTransaction 
	public ProcEvtAnimal procesarEventoAnimal(Date fechaEnvioLote,Eclo eclo, Establecimiento est, Animal objAnimal,
			Evt evento, ProcLote procLote) throws ExcepcionIntegridad {
		TEvento evt = EventoUtil.getEvento(evento);
		ProcEvtAnimal p =  ProcEvtAnimalDAO.create(fechaEnvioLote,evt.getFecha(),eclo,procLote.getSistema(),procLote.getCentroComputo(),evt.getIDEvt());

		if ((evt != null) && (handlers.containsKey(evt.getClass()))) {
			HandlerEvtAnimal handler = this.handlers.get(evt.getClass());
			List<ProcMsg> mensajes = new LinkedList<ProcMsg>();
			if(objAnimal.getEstablecimiento()==null){
				//objAnimal.setEstablecimiento(est);
				Animal.checkUnicidadRPEnEstabForUpdate(objAnimal.getId(),est,objAnimal.getRP(),mensajes,objAnimal.getFechaNac(),objAnimal.getEstablecimiento(),objAnimal.getRegistroOrigen(),objAnimal.getCategoria());
				//objAnimal.setEstablecimiento(est);
			}
			EvtAnimal evAnimal = handler.handleEvtAnimal(est,objAnimal,evento,mensajes, procLote, fechaEnvioLote);
			if (evAnimal.isFinalizaLactancia() && !evAnimal.getDependientes().isEmpty() && evAnimal.getDependientes().first() instanceof EvtLactancia) {
				EvtLactancia evtLactancia = (EvtLactancia)evAnimal.getDependientes().first();
				if (evtLactancia.isEsOficial()){
	        		//System.out.println("Lactancia Oficial,  animal:" + objAnimal.getId());
	        		AnimalesLactanciasOficiales.addAnimal(objAnimal);
	        	}
			}
			
			p.addEvento(evAnimal);
			eclo.setUltimoNumeroEvento(evt.getIDEvt());
            evt.setIdEvtSicel(evAnimal.getId());
			ResultadosUtil.addAll(p,evt,mensajes);
		} else {
			String msg = "No hay ningun handler registrado para el Evento <" + evt.getClass().getName() + ">. Comprueba que exista la entrada en sicel3.conf.xml y que se halla cargado bien";
			log.error(msg);
			throw new ErrorFatal(msg);
		}
		return p;
	}

}
