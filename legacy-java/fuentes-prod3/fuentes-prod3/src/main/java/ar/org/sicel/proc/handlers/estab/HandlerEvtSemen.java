/*
 * Created on 13/04/2005
 */
package ar.org.sicel.proc.handlers.estab;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EvtEstablecimiento;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.handlers.HandlerEvtEst;
import ar.org.sicel.proc.v1.lote.TEvento;


/**
 * 
 */
public class HandlerEvtSemen implements HandlerEvtEst {
    Logger log = Logger.getLogger(HandlerEvtSemen.class);

    /* (non-Javadoc)
     * @see ar.org.sicel.proc.handlers.HandlerEvtEst#handleEvt(java.lang.Object, ar.org.sicel.proc.gen.TEvento)
     */
    public EvtEstablecimiento handleEvt(Establecimiento objEst, TEvento evento, List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote)
        throws ExcepcionIntegridad {
        log.info("Procesando semen " + evento.getIDEvt());

        ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.NOT_IMPLEMENTED,
                new String[] { "No implementado el evento Semen" });
        throw e;
    }
}
