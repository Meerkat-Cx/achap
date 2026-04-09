/*
 * Created on 12/04/2005
 */
package ar.org.sicel.proc.services;

import java.util.Date;

import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Lote;


/**
 * @author pablo
 */
public interface IProcesadorLote {
    public ProcLote procesarLote(Lote lote,Date fechaEntrada) throws ExcepcionIntegridad;
}
