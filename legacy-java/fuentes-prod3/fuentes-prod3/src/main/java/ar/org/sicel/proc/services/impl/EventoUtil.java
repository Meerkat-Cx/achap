/*
 * Created on 15/04/2005
 */
package ar.org.sicel.proc.services.impl;

import org.apache.log4j.Logger;

import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.TEvento;


/**
 * @author pablo
 */
public class EventoUtil {
	private static Logger log = Logger.getLogger(EventoUtil.class);
	
    /**
     * A castor hay que pedirle explicitamente que tipo de evento se quiere obtener del XML.
     * Este metodo se usa para dado un nodo de evento, devolver el objeto evento (de castor) 
     * correspondiente al tipo de evento del nodo. 
     * 
     * @param evt
     * @return
     */
	public static TEvento getEvento(Evt evt) {
    	log.debug("Intentando resolver la clase del evento: " + evt);
        if (evt.getBaja() != null) {
            return evt.getBaja();
        }

        if (evt.getEstado() != null) {
            return evt.getEstado();
        }

        if (evt.getInfo() != null) {
            return evt.getInfo();
        }

        if (evt.getCambioRP() != null) {
            return evt.getCambioRP();
        }

        if (evt.getReprod() != null) {
            return evt.getReprod();
        }

        if (evt.getServ() != null) {
            return evt.getServ();
        }

        if (evt.getTrans() != null) {
            return evt.getTrans();
        }
        if (evt.getSecada() != null) {
        	return evt.getSecada();
        }
        if (evt.getPrenez() != null) {
        	return evt.getPrenez();
        }
        log.error("El evento no es de ninguna clase conocida!");
        return null;
    }
}
