/*
 * Created on 20/04/2005
 */
package ar.org.sicel.proc.handlers.animal;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Evt;



/**
 * @author pablo
 */
public class HandlerEvtProduccion implements HandlerEvtAnimal {
    Logger log = Logger.getLogger(HandlerEvtProduccion.class);


    /* (non-Javadoc)
     * @see ar.org.sicel.proc.handlers.HandlerEvtAnimal#handleEvtAnimal(java.lang.Object, ar.org.sicel.proc.gen.Evt)
     */
    public EvtAnimal handleEvtAnimal(Establecimiento objEst, Animal objAnimal,
    					Evt evento, List<ProcMsg> mensajes,ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad { 
    	/*
        Prod evtProd = evento.getProd();
        log.debug("Procesando evento Produccion " + evtProd.getIDEvt());
       
        
        Date fecha = evtProd.getFecha();
        EvtOrdenie ordenie = objEst.getOrdenieEnFecha(fecha);
        if (! ordenie.getProcEvtEst().getIdEvtECLO().equals(evtProd.getIDEvtOrdenie())) {
        	throw new ExcepcionIntegridad(MENSAJES.ID_ORDENIE_INCORRECTO, new String[] {ordenie.getProcEvtEst().getIdEvtECLO().toString()});
        }
        	
        	
        
        EvtProduccion evt = EvtProduccionDAO.create(objEst,objAnimal,fecha,ordenie,mensajes);
        
        Enumeration mediciones = evtProd.getMeds().enumerateMedicion();
        while (mediciones.hasMoreElements()) {
        	Medicion med = (Medicion)mediciones.nextElement();
        	evt.addMedicion(med.getObjeto().toString(),med.getValor());
        }
        //Es necesarioa validar aqui explicitamente las mediciones construidas, ya que no se validan
        //individualmente.
        evt.validar(mensajes);
        try {
			HibernateFactory.getSession().save(evt);
		} catch (HibernateException e) {
			throw new ErrorFatal("No se pudo guardar el evento",e);
		}
       return evt;
       */
    		throw new ErrorFatal("No existen mas los eventos produccion");
    }
}
