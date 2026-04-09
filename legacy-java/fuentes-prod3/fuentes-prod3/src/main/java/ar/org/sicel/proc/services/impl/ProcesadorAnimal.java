/*
 * Created on 12/04/2005
 *
 */
package ar.org.sicel.proc.services.impl;

import java.util.Date;
import java.util.Enumeration;
import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcAnimal;
import ar.org.sicel.persistence.ProcAnimalDAO;
import ar.org.sicel.persistence.ProcEvtAnimal;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.proc.services.IProcesadorAnimal;
import ar.org.sicel.proc.services.IProcesadorEventoAnimal;
import ar.org.sicel.proc.services.locator.ServiceLocator;
import ar.org.sicel.proc.v1.lote.Animal;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.TEvento;
import ar.org.sicel.util.DateUtils;


/**
 * @author pablo
 *
 */
public class ProcesadorAnimal implements IProcesadorAnimal {
    private static Logger log = Logger.getLogger(ProcesadorAnimal.class);
	
    /* (non-Javadoc)
     * @see ar.org.sicel.proc.services.IProcesadorAnimal#procesarAnimal(java.lang.Object, ar.org.sicel.proc.gen.Animal)
     */

    // @RequiresTransaction
    public ProcAnimal procesarAnimal(Date fechaEnvioLote,Eclo eclo, Establecimiento objEstab, Animal animal,
    		ProcLote procLote,Date fechaBaja) throws ExcepcionIntegridad {
    	String tReg = TipoRegistroUtil.getTipoRegistroEnBase(animal.getReg().getTReg());
    	String nReg = animal.getReg().getNReg();
        //boolean esHembra = TipoRegistroUtil.esHembra(animal.getReg().getSexo());
        log.debug("Iniciando procesamiento del animal " + tReg + " - " + nReg);
        
    	ProcAnimal proc =  ProcAnimalDAO.create(animal,objEstab,tReg,nReg,animal.getRp());
    	IProcesadorEventoAnimal procEvt = ServiceLocator.getProcesadorEventoAnimal();
    	Enumeration evts = animal.getEvts().enumerateEvt();
    	boolean errorEnEvento = false;
    	int procesados =0;
    	int noProcesados = 0;
    	while (evts.hasMoreElements() && !errorEnEvento) {
    		Evt evento = (Evt) evts.nextElement();
    		TEvento evt = EventoUtil.getEvento(evento);
    		try {
    			
    			chequearFechaProcEvento(evento,evt.getFecha(),objEstab);
    			ProcEvtAnimal p = procEvt.procesarEventoAnimal(fechaEnvioLote,eclo,objEstab,proc.getAnimal(), evento, procLote);
    			proc.addProcEvtAnimal(p);
    			procesados = procesados +1;
    		} catch (ExcepcionIntegridad ei) {
    			ProcMsg msg = ProcMsgDAO.create(ei.getCodigoError(),ProcMsg.ERROR,ei.getValores());
				proc.addMsg(msg);
				ResultadosUtil.addResultado(evt,msg);
				errorEnEvento = true;
				noProcesados = 1;
    		}
    	}
    	while (evts.hasMoreElements()) { //estos faltaron procesar
    		Evt evento = (Evt) evts.nextElement();
    		long idEvento = EventoUtil.getEvento(evento).getIDEvt();
    		String tipoyNumeroRegistro = ("C".equals(animal.getReg().getTReg().toString())?"RC":"HBA")+" "+animal.getReg().getNReg();
    		ProcMsg msg = ProcMsgDAO.create(MENSAJES.EVENTO_NO_PROCESADO,ProcMsg.INFO_WARNING,new String[] {String.valueOf(idEvento),tipoyNumeroRegistro});
			proc.addMsg(msg);
			ResultadosUtil.addResultado(animal,msg);
			noProcesados ++;
    	}
    	
    	ProcMsg msgOk = ProcMsgDAO.create(MENSAJES.EVT_ANIMAL_OK, ProcMsg.INFO,new String[] {String.valueOf(procesados)});
    	proc.addMsg(msgOk);
    	ResultadosUtil.addResultado(animal,msgOk);
    	
    	ProcMsg msgError = ProcMsgDAO.create(MENSAJES.EVT_ANIMAL_ERROR, ProcMsg.INFO,new String[] {String.valueOf(noProcesados)});
    	proc.addMsg(msgError);
    	ResultadosUtil.addResultado(animal,msgError);
    	log.info("Finalizado el proceso del animal " +proc.getAnimal().getRegistroID());
    	return proc;
    }
    /**
     * Se chequea que para la fecha de evento el tambo este activo, salgo el caso de que 
     * de que el evento sea un parto o un servicio
     * @param evento
     * @param fechaEvento
     * @param estab
     * @throws ExcepcionIntegridad
     */
    private void chequearFechaProcEvento(Object evento,Date fechaEvento,Establecimiento estab) throws ExcepcionIntegridad {
    	//if(fechaBajaTambo!=null && fechaEvento.after(fechaBajaTambo))
    	if(!estab.estaActivoEnFecha(evento,fechaEvento))
			throw new ExcepcionIntegridad(MENSAJES.TAMBO_INACTIVO, new String[] {estab.getId().toString(),DateUtils.format(fechaEvento,"dd/MM/yyyy")});
		
	}
    
      
}
