/*
 * Created on 12/04/2005
 */
package ar.org.sicel.proc.services;

import java.util.Date;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.ProcAnimal;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Animal;


/**
 * @author pablo
 */
public interface IProcesadorAnimal {
    public ProcAnimal procesarAnimal(Date fechaEnvioLote,Eclo eclo, Establecimiento objEstab,
    		Animal animal, ProcLote procLote,Date fechaBaja) throws ExcepcionIntegridad;
}
