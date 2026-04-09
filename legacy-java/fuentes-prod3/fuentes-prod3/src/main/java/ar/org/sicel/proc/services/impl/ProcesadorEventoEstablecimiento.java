package ar.org.sicel.proc.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EvtEstablecimiento;
import ar.org.sicel.persistence.ProcEvtEst;
import ar.org.sicel.persistence.ProcEvtEstDAO;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.proc.handlers.HandlerEvtEst;
import ar.org.sicel.proc.services.IProcesadorEventoEstablecimiento;
import ar.org.sicel.proc.v1.lote.TEvento;
import ar.org.sicel.util.Sicel3Conf;


/**
 * @author pablo
 *
 */
public class ProcesadorEventoEstablecimiento implements
		IProcesadorEventoEstablecimiento {

    	
    private static Logger log = Logger.getLogger(ProcesadorEventoEstablecimiento.class);
	
	private Map<Class,HandlerEvtEst> handlers;
	
	public ProcesadorEventoEstablecimiento() {
		loadHandlers();
	}
	
	private void loadHandlers() {
		handlers = new HashMap<Class,HandlerEvtEst>();
		try {
			Configuration[] cHandlers = Sicel3Conf.getConf().getConfProcEstablecimientos().getChild("handlers").getChildren("handler");
			for (int i = 0; i < cHandlers.length; i++) {
				String castor = cHandlers[i].getChild("castor-class").getValue();
				String handler = cHandlers[i].getChild("handler-class").getValue();
				addHandler(castor, handler);
			}
			log.debug("Todos los handlers cargados");
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
		HandlerEvtEst handlerObj = null;
		try {
			handlerObj = (HandlerEvtEst)Class.forName(handler).newInstance();
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
		}
	}
	
	/**
	 * aca deveria usar la conf para obtener los procesadores disponibles
	 * y usar algun Map para mapear de un tipo de evento al procesador adecuado,
	 * por ahora son 2 unicamente: el que procesa altas y el que procesa semens
	 * 
	 * @see ar.org.sicel.proc.services.IProcesadorEventoEstablecimiento#procesarEventoEstablecimiento(java.lang.Object, ar.org.sicel.proc.gen.Evt)
	 */
	//@RequiresTransaction 
	public ProcEvtEst procesarEventoEstablecimiento(Date fechaEnvioLote,Eclo eclo, Establecimiento objEstab, TEvento evento, ProcLote procLote) throws ExcepcionIntegridad{
		ProcEvtEst p = ProcEvtEstDAO.create(fechaEnvioLote,evento.getFecha(),eclo,procLote.getSistema(),procLote.getCentroComputo(), evento.getIDEvt());
		if (handlers.containsKey(evento.getClass())) {
			HandlerEvtEst handler = this.handlers.get(evento.getClass());
			List<ProcMsg> mensajes = new LinkedList<ProcMsg>();
			
			EvtEstablecimiento evt = handler.handleEvt(objEstab,evento,mensajes, procLote, fechaEnvioLote);
            p.addEventoEstablecimiento(evt);
            evento.setIdEvtSicel(evt.getId());
            eclo.setUltimoNumeroEvento(evento.getIDEvt());
			ResultadosUtil.addAll(p,evento,mensajes);
		} else {
			String msg = "No hay ningun handler registrado rara el Evento <" + evento.getClass().getName() + ">. Compruebe que exista la entrada en sicel3.conf.xml y que se halla cargado bien";
			log.error(msg);
			throw new ErrorFatal(msg);
		}
	    return p;
	}

}
