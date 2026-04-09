/*
 * Created on 20/04/2005
 */
package ar.org.sicel.proc.handlers.animal;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtAnimalModificacion;
import ar.org.sicel.persistence.EvtAnimalModificacionDAO;
import ar.org.sicel.persistence.EvtNuevoInd;
import ar.org.sicel.persistence.EvtServicio;
import ar.org.sicel.persistence.EvtServicioDAO;
import ar.org.sicel.persistence.EvtTransEmb;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.services.impl.TipoRegistroUtil;
import ar.org.sicel.proc.v1.lote.Clon;
import ar.org.sicel.proc.v1.lote.Embrion;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.Serv;
import ar.org.sicel.proc.v1.lote.Servsemen;
import ar.org.sicel.proc.v1.lote.types.STTipoServicio;
import ar.org.sicel.util.StringUtils;


/**
 * @author pablo
 */
public class HandlerEvtServicio extends HandlerEvt  implements HandlerEvtAnimal {
   static Logger log = Logger.getLogger(HandlerEvtServicio.class);

    /* (non-Javadoc)
     * @see ar.org.sicel.proc.handlers.HandlerEvtAnimal#handleEvtAnimal(java.lang.Object, ar.org.sicel.proc.gen.Evt)
     */
    public EvtAnimal handleEvtAnimal(Establecimiento est, Animal objAnimal,
    		Evt evento, List<ProcMsg> msgs,ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
    	EvtServicio result = null;
    	if (!objAnimal.esHembra())
    		throw new ExcepcionIntegridad(MENSAJES.NO_HEMBRA,new String[] {objAnimal.getRegIdentificador().getTipoRegistro().getId(), objAnimal.getRegIdentificador().getId().toString()});
    	
    	Hembra hembra = (Hembra) objAnimal;
    	
    	Serv evtServicio = evento.getServ();
    	
    	log.warn("Procesando evento Servicio " + evtServicio.getIDEvt() +" "+ new Date());
    	
    	Date fechaServicio = evtServicio.getFecha();

    	if(evtServicio.getModificaOBaja()!=null){
    		log.debug("Procesando modificación de evento servicio animal " + evtServicio.getModificaOBaja().getIDEvt());
    		Long idEventoReproOrig = new Long(evtServicio.getModificaOBaja().getIDEvt());
    		Evento ev = EventoDAO.findByPrimaryKey(idEventoReproOrig);
    		if(!(ev instanceof EvtNuevoInd)&&!(ev instanceof EvtTransEmb)){
    			log.debug("El evento que se está intentando modificar no es de tipo servicio " + evtServicio.getModificaOBaja().getIDEvt());
    			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT,
                        new String[] { (ev==null)?"NO EXISTE":String.valueOf(ev.toString()),"Servicio"});
                throw e;
    		}	
    		EvtServicio evtSer = (EvtServicio)ev;
			Long idEclo = evtSer.getEstablecimiento().getEclo().getId(); // id de la eclo que informo en el servicio original
			Long idEcloInformada = evtServicio.getModificaOBaja().getInformante(); // id de la eclo informa
			if (!idEclo.equals(idEcloInformada)) {
				log.error("El establecimiento que informa la modificación del servicio no es el que informó el servicio");
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString()});				
			}
			EvtAnimalModificacion eventoModificacion = evtSer.ejecutarModificacion(evtServicio, msgs, objAnimal);
			EvtAnimalModificacionDAO.saveEvtAnimalModificacion(eventoModificacion);
			return eventoModificacion;
    	}    	
    	else{
	    	if (evtServicio.getNuevo() != null) { //es un nuevo individuo
	    		if (evtServicio.getNuevo().getServsemen() != null) {
	    			//es inseminacion
	    			Servsemen servSemen = evtServicio.getNuevo().getServsemen();
	    			String tipoServicio = servSemen.getTipoServ().toString();
	    			String tRegPadre = TipoRegistroUtil.getTipoRegistroEnBase(servSemen.getPadreGen().getTReg());
	    			String nRegPadre = servSemen.getPadreGen().getNReg();
	    			String raza = RazaUtil.getRazaEnBase(servSemen.getPadreGen().getRaza());
	    			Macho padreGen = AnimalDAO.findExistentMachoByRegistry(tRegPadre,nRegPadre,raza);
	    			result = EvtServicioDAO.createNuevoIndividuoServSemen(est,hembra,tipoServicio,fechaServicio,padreGen,msgs, 
	    					servSemen.getFinCorral() != null ? servSemen.getFinCorral().toDate() : null);
	    			
	    			if((padreGen.getEstablecimiento()==null)&&(servSemen.getTipoServ().equals(STTipoServicio.CAMP.toString()) ||  //si es inseminacion a campo o a corral  
	    					servSemen.getTipoServ().equals(STTipoServicio.CORR.toString()))){
	    				padreGen.setEstablecimiento(est);
	    				padreGen.setEstancia(est.getEstancia());
	    			}
	    		}
	    		else {
	    			// es una transferencia embrionaria
	    			Embrion em  = evtServicio.getNuevo().getEmbrion();
	    			int diasMaduracion = em.getDias();
	    			
	    			String idEmbrion = em.getIdEmbrion();
	    			if (idEmbrion ==null || org.apache.commons.lang.StringUtils.isEmpty(idEmbrion)) 
	    				throw new ExcepcionIntegridad(MENSAJES.OLBLIGATORIO_INFORMAR_EMBRION, new String[]{});				
	    			
	    			String tRegMadre = TipoRegistroUtil.getTipoRegistroEnBase(em.getMadreGen().getTReg());
	    			String nRegMadre = em.getMadreGen().getNReg();
	    			String raza = RazaUtil.getRazaEnBase(em.getMadreGen().getRaza());
	    			Hembra madreGen = AnimalDAO.findExistentHembraByRegistry(tRegMadre,nRegMadre,raza);
	    			
	    			String tRegPadre = TipoRegistroUtil.getTipoRegistroEnBase(em.getPadreGen().getTReg());
	    			String nRegPadre = em.getPadreGen().getNReg();
	    			String raza1 = RazaUtil.getRazaEnBase(em.getPadreGen().getRaza());
	    			Macho padreGen = AnimalDAO.findExistentMachoByRegistry(tRegPadre,nRegPadre,raza1);
	    			result = EvtServicioDAO.createNuevoIndividuoPorTE(est,hembra,fechaServicio,madreGen,padreGen,diasMaduracion,idEmbrion,msgs);	
	    		} 
	    	} else { //es un clon
	    			Clon clon = evtServicio.getClon();
	    			String tRegDonante = TipoRegistroUtil.getTipoRegistroEnBase(clon.getDonante().getTReg());
	    			String nRegDonante = clon.getDonante().getNReg();
	                //boolean esDonanteHembra = TipoRegistroUtil.esHembra(clon.getDonante().getSexo());
	    			String raza2 = RazaUtil.getRazaEnBase(clon.getDonante().getRaza());
	    			//tendria uqe se con sexo
	    			Animal donante = AnimalDAO.findExistentByRegistry(tRegDonante,nRegDonante,raza2,clon.getDonante().getSexo().toString());
	    			// String tegido = clon.getTegido();
	    			String tegido = "to-do";
	    			result = EvtServicioDAO.createClon(est,hembra,fechaServicio,donante,tegido,msgs);
	    		}
    		
    	 
        try {
        	super.setAnimalEntidades(hembra, est);
        	EvtAnimalDAO.save(result);
            //HibernateFactory.getSession().save(result);
        } catch (HibernateException e) {
            throw new ErrorFatal("No se pudo guardar el evento",e);
        }
    		return result;
    	}	
    	}
}
