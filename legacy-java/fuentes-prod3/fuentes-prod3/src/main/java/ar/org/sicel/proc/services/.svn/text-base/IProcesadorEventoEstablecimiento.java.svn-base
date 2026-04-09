/*
 * Created on 12/04/2005
 */
package ar.org.sicel.proc.services;

import java.util.Date;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.ProcEvtEst;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.TEvento;


/**
 * @author pablo
 */
public interface IProcesadorEventoEstablecimiento {
    public ProcEvtEst procesarEventoEstablecimiento(Date fechaEnvioLote,Eclo eclo,Establecimiento estab,
        TEvento evento, ProcLote procLote) throws ExcepcionIntegridad;
}
