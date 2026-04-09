/**
 * 
 */
package ar.org.sicel.proc.handlers.animal;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.CONF;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtAnimalModificacion;
import ar.org.sicel.persistence.EvtAnimalModificacionDAO;
import ar.org.sicel.persistence.EvtCambioRp;
import ar.org.sicel.persistence.EvtCambioRpDAO;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.CambioRP;
import ar.org.sicel.proc.v1.lote.Evt;

/**
 * @author jdivars
 *
 */
public class HandlerEvtCambioRp extends HandlerEvt implements HandlerEvtAnimal {

	public EvtAnimal handleEvtAnimal(Establecimiento establecimiento, Animal objAnimal, Evt evento, List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
		// TODO Auto-generated method stub
		CambioRP cambio = evento.getCambioRP();
		if(cambio.getModificaOBaja()!=null){
    		
    		Long idEventoCambioRpOrig = new Long(cambio.getModificaOBaja().getIDEvt());
    		Evento ev = EventoDAO.findByPrimaryKey(idEventoCambioRpOrig);
    		if(!(ev instanceof EvtCambioRp)){
    			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT,
                        new String[] { (ev==null)?"NO EXISTE":String.valueOf(ev.toString()), "CambioRp"});
                throw e;
    		}	
    		ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.MODIFICA_BAJA_EVT_CAMBIO_RP,
                    new String[] { });
            throw e;
    	}
		String rpNuevo = cambio.getRpNuevo();
		if (Configuracion.getValorReglaProceso(CONF.VALIDACIONES_NUMEROS_DE_RP,
				null) && !Animal.validarRP(rpNuevo))
			throw new ExcepcionIntegridad(MENSAJES.VALIDACIONES_NUMEROS_DE_RP,new String[]{rpNuevo});
		EvtCambioRp evtCambio = EvtCambioRpDAO.create(establecimiento,objAnimal,cambio.getFecha(),mensajes,rpNuevo,procLote);
		try {
			super.setAnimalEntidades(objAnimal, establecimiento);
			EvtAnimalDAO.save(evtCambio);
  			//HibernateFactory.getSession().save(evtCambio);
  		} catch (HibernateException e) {
  			throw new ErrorFatal("No se pudo guardar el evento",e);
  		}
    	return evtCambio;
		
	}

}
