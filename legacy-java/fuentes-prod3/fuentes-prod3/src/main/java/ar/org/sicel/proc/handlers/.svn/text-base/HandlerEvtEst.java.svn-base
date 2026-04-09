/*
 * Created on 13/04/2005
 */
package ar.org.sicel.proc.handlers;

import java.util.Date;
import java.util.List;

import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EvtEstablecimiento;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.TEvento;


/**
 * @author pablo
 * 
 * Esta interface representa a un objeto capaz de procesar un determinado tipo de evento de establecimiento.
 * Es su responsabilidad tomarlo en su formato XML(castor), interpretarlo y producir los objetos
 * que seran persistidos en la base de datos.
 * Para mas informacion, ver HandlerEvtAnimal
 */
public interface HandlerEvtEst {
    /**
     * Procesa el evento <code>evento</code>(Castor), producido en el
     * establecimiento objEst. Devuelve como resultado el Evento (Hibernate)
     * resultado del procesamiento
     *
     * @param objEst
     * @param evento
     * @return eventoEnLaBase
     * @throws ExcepcionIntegridad
     */
    public EvtEstablecimiento handleEvt(Establecimiento objEst, TEvento evento, List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote)
        throws ExcepcionIntegridad;
}
