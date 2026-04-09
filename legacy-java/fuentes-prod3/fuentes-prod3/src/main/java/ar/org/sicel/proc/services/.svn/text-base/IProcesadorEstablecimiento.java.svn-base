/*
 * Created on 12/04/2005
 */
package ar.org.sicel.proc.services;

import java.util.Date;

import ar.org.sicel.persistence.CentroDeComputo;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.ProcEstablecimiento;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Estab;


/**
 * @author pablo
 */
public interface IProcesadorEstablecimiento {
    public ProcEstablecimiento procesarEstablecimiento(Date fechaEnvioLote, Eclo ecloInformante,
    		Estab establecimiento, ProcLote procLote,CentroDeComputo centro,Date fechaEntradaSicel) throws ExcepcionIntegridad;
}
