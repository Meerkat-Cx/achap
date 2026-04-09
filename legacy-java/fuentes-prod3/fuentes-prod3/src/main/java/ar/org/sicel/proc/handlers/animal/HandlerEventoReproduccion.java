/*
 * Created on 20/04/2005
 */
package ar.org.sicel.proc.handlers.animal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import ar.org.sicel.persistence.EvtCria;
import ar.org.sicel.persistence.EvtCriaDAO;
import ar.org.sicel.persistence.EvtLactanciaMigradaDAO;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.EvtReproduccionDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvt;
import ar.org.sicel.proc.handlers.HandlerEvtAnimal;
import ar.org.sicel.proc.v1.lote.Cria;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.Reprod;
import ar.org.sicel.proc.v1.lote.types.STSexo;
import ar.org.sicel.proc.v1.lote.types.STSexoD;
import ar.org.sicel.util.DateUtils;
/**
 * @author pablo
 */
public class HandlerEventoReproduccion extends HandlerEvt  implements HandlerEvtAnimal  {
    static Logger log = Logger.getLogger(HandlerEventoReproduccion.class);

    /* (non-Javadoc)
     * @see ar.org.sicel.proc.handlers.HandlerEvtAnimal#handleEvtAnimal(java.lang.Object, ar.org.sicel.proc.gen.Evt)
     * ahora este evento puede retornar null
     */
    public EvtAnimal handleEvtAnimal(Establecimiento est, Animal objAnimal,
    		Evt evento, List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
    	
    	Reprod reprod = evento.getReprod();
    	log.warn("Procesando evento reproduccion " + reprod.getIDEvt() +" "+ new Date());
    	if (!objAnimal.esHembra() )
    		throw new ExcepcionIntegridad(MENSAJES.NO_HEMBRA,new String[]{objAnimal.getRegIdentificador().getTipoRegistro().getId(),objAnimal.getRegIdentificador().getNumero()});
    	
    	if(reprod.getModificaOBaja()!=null){
    		log.debug("Procesando modificación de evento reproduccion animal " + reprod.getModificaOBaja().getIDEvt());
    		Long idEventoReproOrig = new Long(reprod.getModificaOBaja().getIDEvt());
    		Evento ev = EventoDAO.findByPrimaryKey(idEventoReproOrig);
    		if(!(ev instanceof EvtReproduccion)){
    			log.debug("El evento que se está intentando modificar no es de tipo reproducción " + reprod.getModificaOBaja().getIDEvt());
    			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT,
                        new String[] { (ev==null)?"NO EXISTE":String.valueOf(ev.toString()), "Reproduccion"});
                throw e;
    		}	
    		EvtReproduccion evtRepro = (EvtReproduccion)ev;
			Long idEclo = evtRepro.getEstablecimiento().getEclo().getId(); // id de la eclo que informo la reproducción original
			Long idEcloInformada = reprod.getModificaOBaja().getInformante(); // id de la eclo informa
			if (!idEclo.equals(idEcloInformada)) {
				log.error("El establecimiento que informa la modificación de la reproducción no es el que informó la reproducción");
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString()});				
			}
			EvtAnimalModificacion eventoModificacion = evtRepro.ejecutarModificacion(reprod, mensajes, objAnimal, procLote,fechaEnvioLote);
			objAnimal.setEstadoRetroactivo(reprod.getFecha(), mensajes,evtRepro.getNombreTipo());
			EvtAnimalModificacionDAO.saveEvtAnimalModificacion(eventoModificacion);
			return eventoModificacion;
    	}else{
	    	log.debug("Procesando evento reproduccion animal " + reprod.getIDEvt());	    	
	    	Hembra madreParto = (Hembra) objAnimal;
	    	Date fechaEvt = reprod.getFecha();
	    	
	    	/**
	    	 * esto es para la CARGA INCIAL de eventos, con el sistema en funcionamiento no va a entrar
	    	 */
	    	List lactanciasMigradas = EvtLactanciaMigradaDAO.findByAnimalFecha(madreParto, fechaEvt);
			if (lactanciasMigradas != null && !lactanciasMigradas.isEmpty()) { // si hay lactancias migradas para
				String fecha = (new SimpleDateFormat("dd/MM/yyyy")).format(fechaEvt);
				throw new ExcepcionIntegridad(MENSAJES.LACTANCIA_MIGRADA_EXISTENTE,
						new String[] {madreParto.getRP(), madreParto.getEstablecimiento().getId().toString(), fecha});
			}
	 
	    	
	    	boolean usarRazaMadre = reprod.getUsarRazaMadre();
	    	
	    	//MODIF esto de aca abajo esta al pedo
	    	//Enumeration crias = evtReprod.enumerateCria();
	    	EvtCria[] eCrias = new EvtCria[reprod.getCriaCount()];
	    	Cria[] xmlCrias  = reprod.getCria();
	    	Boolean esAbortoLargo = null;
	    	if(reprod.hasAbortoLargo())
	    		esAbortoLargo = reprod.getAbortoLargo();
	    	boolean inscribirAlgunaCria = false;
	    	
	    	//no uso directamente el xml que me viene, para que lo que esta
	    	//en el paquete de persistencia no conozca nada de castor ni de xml
	    	for (int i=0;i<xmlCrias.length;i++) {
	    		//Boolean esHembra = null;  
	    		//if (xmlCrias[i].getEstadoEsVivo()) {
	                if (xmlCrias[i].getSexo() == null)
	                    throw new ExcepcionIntegridad(MENSAJES.CRIA_SIN_SEXO,new String[0]);
	                if(xmlCrias[i].getSenasa()!=null){
	    				if(org.apache.commons.lang.StringUtils.isEmpty(xmlCrias[i].getSenasa().getRpSenasa()))
	    					throw new ExcepcionIntegridad(MENSAJES.INFORMO_PARTE_INFORMACION_SENASA,new String[]{});			
	    			}
	    			//esHembra = xmlCrias[i].getSexo().equals(STSexoD.F);
	    	//	}
	    	/*	else {// estadoEsVivio=false
	    			if (xmlCrias[i].getInscribir()) {
	    				ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_INSCRIBIR_MUERTO,
	    						new String[] { xmlCrias[i].getRp()});
	    				throw e;
	    			}	
	    		}*/
	    		if(xmlCrias[i].getInscribir() && xmlCrias[i].getSexo().equals(STSexoD.D) )
	    				throw new ExcepcionIntegridad(MENSAJES.CRIA_INSCRIBIR_SEXO_DESCONOCIDO,new String[0]);
	    		String sexo = "";
	    		if(xmlCrias[i].getSexo().equals(STSexoD.F))
	    			sexo = "F";
	    		else
	    			if(xmlCrias[i].getSexo().equals(STSexoD.M))
	    				sexo = "M";
	    			else
	    				sexo="D";
	    		if (xmlCrias[i].getRp() != null && Configuracion.getValorReglaProceso(CONF.VALIDACIONES_NUMEROS_DE_RP,
	    				null) && !Animal.validarRP(xmlCrias[i].getRp()))
	    			throw new ExcepcionIntegridad(MENSAJES.VALIDACIONES_NUMEROS_DE_RP,new String[]{xmlCrias[i].getRp()});
	    		eCrias[i] = EvtCriaDAO.create( 
	    				xmlCrias[i].getNombre(),
	    				xmlCrias[i].getEstadoEsVivo(),
	    				xmlCrias[i].getInscribir(),
	    				xmlCrias[i].getRp(),
	    				xmlCrias[i].getTamano(),
	    				xmlCrias[i].getPeso(),
	    				xmlCrias[i].getDificultad(),
	    				sexo,xmlCrias[i].getSenasa(),xmlCrias,i
	    		);
	    		
	    		inscribirAlgunaCria |= xmlCrias[i].getInscribir() && StringUtils.isNotEmpty(xmlCrias[i].getRp());//xmlCrias[i].getRp() != null;
	    		
	    	}	    	
	    	boolean fechaCorrecta = true;
	    	//Si se pide inscribir alguna cria e informan el rp, verifico los dias entre
	    	//el lote y el evento reproducicon
	    	if(inscribirAlgunaCria){
	    		int diasLoteEvt = Integer.parseInt(madreParto.getRaza().getParametro("Dias maximo entre fechas de evento y de lote"));
	    		fechaCorrecta = DateUtils.menos(fechaEnvioLote,diasLoteEvt).before(fechaEvt);
	    		if(!fechaCorrecta)
	    			mensajes.add(ProcMsgDAO.create(MENSAJES.SUPERA_TIEMPO_LIMITE_LOTE,ProcMsg.WARNING,new String[]{}));
	    	}
	    	
	    	java.util.Date fechaServicio = null;
	    	if (reprod.getFechaServicio() != null)
	    		fechaServicio = reprod.getFechaServicio();
	    		// fechaServicio = evtReprod.getFechaServicio().toDate();
	    	
	    	//Agregado para tener en la base el numero de lactancia actual del animal
	    	//**********comentado para que cuando tengan ganas de usarlo este disponible****************
			/*if((reprod.getNumLactancia() != 0) && !((reprod.getAbortoLargo()==false)&&!(reprod.getCriaCount()>0))){
	    	
				objAnimal.setNroLactInformado(reprod.getNumLactancia());
			}*/	    	
	    	
	    	EvtReproduccion evt = EvtReproduccionDAO.create(est,madreParto,fechaEvt,fechaServicio,usarRazaMadre,esAbortoLargo,eCrias,mensajes, procLote,fechaCorrecta,reprod.getNumLactancia(),fechaEnvioLote);
	    	
	    	try {
	    		super.setAnimalEntidades(objAnimal, est);
	    		EvtAnimalDAO.save(evt);
	  			//HibernateFactory.getSession().save(evt);
	  		} catch (HibernateException e) {
	  			throw new ErrorFatal("No se pudo guardar el evento",e);
	  		}
	    	return evt;
    	}	
    	
    }
    
    
}
