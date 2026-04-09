/*
 * Created on 20/04/2005
 */
package ar.org.sicel.proc.handlers.animal;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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
import ar.org.sicel.persistence.EvtTransferencia;
import ar.org.sicel.persistence.EvtTransferenciaDAO;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.Trans;
import ar.org.sicel.util.StringUtils;


/**
 * @author jivars
 */
public class HandlerEvtTransferencia extends HandlerEvt implements HandlerEvtAnimal {
    Logger log = Logger.getLogger(HandlerEvtTransferencia.class);

    /* (non-Javadoc)
     * @see ar.org.sicel.proc.handlers.HandlerEvtAnimal#handleEvtAnimal(java.lang.Object, ar.org.sicel.proc.gen.Evt)
     */
    public EvtAnimal handleEvtAnimal(Establecimiento est, Animal objAnimal,
    		Evt evento, List<ProcMsg> mensajes,ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
    	Trans evtTransferencia = evento.getTrans();
    	log.info("Procesando evento Transferencia" +evtTransferencia.getIDEvt());
    	
    	Propietario nuevoProp;
    	if (evtTransferencia.hasPropNuevo()) 
    		nuevoProp = PropietarioDAO.findExistentByPrimaryKey(evtTransferencia.getPropNuevo());
    	else
    		nuevoProp = est.getPropietario();
    	String viejoRp = objAnimal.getRP();
    	String nuevoRp = "";
    	if(!org.apache.commons.lang.StringUtils.isEmpty(evtTransferencia.getRpNuevo()))
    		nuevoRp = evtTransferencia.getRpNuevo();
    	if(!viejoRp.equalsIgnoreCase(nuevoRp))
    	{
	    	if (Configuracion.getValorReglaProceso(CONF.VALIDACIONES_NUMEROS_DE_RP,
					null) && !Animal.validarRP(nuevoRp))
				throw new ExcepcionIntegridad(MENSAJES.VALIDACIONES_NUMEROS_DE_RP,new String[]{nuevoRp});
    	}
       	if(evtTransferencia.getModificaOBaja() != null){//si es una modificacion
    		EvtTransferencia evtOld = (EvtTransferencia) EventoDAO.findByPrimaryKey(evtTransferencia.getModificaOBaja().getIDEvt());
    		if(this.validaModif(evtTransferencia, objAnimal, evtOld, nuevoProp, est, mensajes,nuevoRp)){
    			// LLAMO A EJECUTAR LA MODIFICACION, SE MODIFICARIA EL EVENTO TRANSFERENCIA Y EL ANIMAL DEL EVENTO TRANSFERENCIA  
    			EvtAnimalModificacion eventoModificacion = ((EvtTransferencia)evtOld).ejecutarModificacion(est, objAnimal, evtTransferencia,procLote,nuevoRp
    			);
    			EvtAnimalModificacionDAO.saveEvtAnimalModificacion(eventoModificacion);
    			return eventoModificacion;			
    		}
    	}
    	
    	EvtTransferencia result = EvtTransferenciaDAO.create(est,objAnimal,evtTransferencia.getFecha(),est,nuevoRp,nuevoProp,mensajes,procLote);
    	
    	try {
    		super.setAnimalEntidades(objAnimal, est);
    		//HibernateFactory.getSession().save(result);
    		EvtAnimalDAO.save(result);
    	} catch (HibernateException e) {
    		throw new ErrorFatal("No se pudo guardar el evento",e);
    	}
    	//    	guardo la ficha para tirarla alguna vez
		/*FichaAnimalPendiente ficha = new FichaAnimalPendiente();
		ficha.setAnimal(objAnimal);
		try {
			//HibernateFactory.getSession().save(ficha);
			FichaAnimalDAO.save(ficha);
		} catch (HibernateException e) {
			throw new ErrorFatal(
					"Error al querer guardar la ficha para el animal dado de Alta, HandlerEvtAlta", e);
		}*/
    	return result;
    }

    
    private boolean validaModif(Trans evtTransferencia, Animal objAnimal, EvtTransferencia evtOld,
    		Propietario nuevoProp, Establecimiento est, List<ProcMsg> mensajes,String nuevoRp)throws ExcepcionIntegridad {
    	if (! (evtOld instanceof EvtTransferencia)) {
			// VERIFICO QUE EL EVENTO INFORMADO ES REALMENTE UNA TRANSFERENCIA
			
    		throw new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT, new String[]{(evtOld==null)?"NO EXISTE":evtOld.toString(),"Transferencia"});				
		}
		Long idEclo = evtOld.getEstablecimiento().getEclo().getId(); // id de la eclo que informo la transferencia
		Long idEcloInformada = evtTransferencia.getModificaOBaja().getInformante(); // id de la eclo informa
		//if (!objEst.getId().equals(evtOld.getEstablecimiento().getId())) {
		if (!idEclo.equals(idEcloInformada)) {
			// VERIFICO QUE LA ECLO DEL ESTABLECIMIENTO SE LA INFORMADA EN EL XML
			log.error("El establecimiento que informa la transferencia a modificar no fue la que informo el evento");
			throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString()});				
		}
		//verifico que no se modifique el animal
		if(!evtOld.getAnimalesModificados().contains(objAnimal)){
			log.error("El animal no puede ser modificado, deberia darse de baja el evento, y realizarce uno nuevo con el animal correcto.");
			throw new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO, new String[]{((Animal)evtOld.getAnimalesModificados().toArray()[0]).getRegistroOrigen(), objAnimal.getRegistroOrigen()});
		}
		//verifico que no se modifique el propietario ni el establecimiento
		if((!est.equals(evtOld.getEstablecimiento()))||(!objAnimal.getPropietario().equals(nuevoProp))){
			log.error("El propietario y el establecimiento no pueden ser modificados, deberia darse de baja el evento, y realizarce uno nuevo con los datos correctos.");
			throw new ExcepcionIntegridad(MENSAJES.MODIF_PROP_O_ESTAB, new String[]{});
		}
		 //Controla la fecha que se quiere modificar, que cumpla con la regla: fecha_evento_anterior < fecha_que_informo < fecha_evento_posterior		
		for(Object evt : objAnimal.getAllEventos()){
			Evento evento = (Evento)evt;
			if(evento.getId() != null){
				if(evento.getId()<evtOld.getId()&&evento.getFecha().after(evtTransferencia.getFecha()))
					throw new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_ANT, new String[]{StringUtils.formatDate(evtTransferencia.getFecha()), StringUtils.formatDate(evento.getFecha())});
				if(evento.getId()>evtOld.getId()&&evento.getFecha().before(evtTransferencia.getFecha()))
					throw new ExcepcionIntegridad(MENSAJES.ERROR_EVENTO_POST, new String[]{StringUtils.formatDate(evtTransferencia.getFecha()),StringUtils.formatDate(evento.getFecha())});			
			}			
		}
		if(!evtOld.getAnimal().getRP().equals(nuevoRp))
			Animal.checkUnicidadRPEnEstabForUpdate(evtOld.getAnimal().getId(),est,nuevoRp,mensajes,evtOld.getAnimal().getFechaNac(),est,evtOld.getAnimal().getRegistroOrigen(),evtOld.getAnimal().getCategoria());
		return true;
    }
}
