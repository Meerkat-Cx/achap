/*
 * Created on 12/04/2005
 */
package ar.org.sicel.proc.services;

import java.util.Date;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.ProcEvtAnimal;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Evt;


/**
 * @author pablo
 */
public interface IProcesadorEventoAnimal {
    public ProcEvtAnimal procesarEventoAnimal(Date fechaEnvioLote,Eclo eclo, Establecimiento est,Animal objAnimal,
    		Evt evento,ProcLote procLote) throws ExcepcionIntegridad;
}
