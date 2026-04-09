package ar.org.sicel.proc.handlers.estab;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAlta;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalModificacion;
import ar.org.sicel.persistence.EvtBajaEvento;
import ar.org.sicel.persistence.EvtControlAnimal;
import ar.org.sicel.persistence.EvtControlEstablecimiento;
import ar.org.sicel.persistence.EvtEstModificacion;
import ar.org.sicel.persistence.EvtEstablecimiento;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.handlers.HandlerEvtEst;
import ar.org.sicel.proc.v1.lote.BajaEvt;
import ar.org.sicel.proc.v1.lote.TEvento;

public class HandlerBajaEvento implements HandlerEvtEst {
	
	private static Logger log = Logger.getLogger(HandlerBajaEvento.class);
	
	//MIRAR No aparece en el resumen como el ordenie
	//MIRAR No acepta el evento pero dice que está ok y no tira errores

	

	/*public EvtEstablecimiento handleEvt(Establecimiento objEst, TEvento evento,
			List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
		throw new ExcepcionIntegridad(MENSAJES.NO_SE_PUEDE_DAR_DE_BAJA_EVENTOS, new String[]{});
	}*/
	
	public EvtEstablecimiento handleEvt(Establecimiento objEst, TEvento evento,
			List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {

		
		
		if(evento.getModificaOBaja()!=null){
			Long idEventoBaja = new Long(evento.getModificaOBaja().getIDEvt());
			log.warn("Baja de evento: "+evento.getIDEvt());
			log.warn("baja evento" +idEventoBaja);
			if(evento.getIDEvt()==752945L 
					||evento.getIDEvt()==216L
					||evento.getIDEvt()==1357892L
					||evento.getIDEvt()==992136L)
			//if(idEventoBaja ==23417339L)
				log.warn("baja evento" +idEventoBaja);
			
			Long idEcloInformante = new Long(evento.getModificaOBaja().getInformante());
			if (objEst.getEclo().getId().equals(idEcloInformante)) {
				Evento ev = EventoDAO.findByPrimaryKey(idEventoBaja);
				if(ev==null){
					log.error("NO EXISTE EL EVENTO");
					throw new ExcepcionIntegridad(MENSAJES.NO_EXISTE_EVENTO_A_DAR_DE_BAJA, new String[]{idEventoBaja.toString()});				
				}
				//ev.darDeBaja(); // by kjacobsen
				
				if(ev instanceof EvtControlEstablecimiento){
					if(evento.getModificaOBaja().getIdOrdenie()!= 0L){
						EvtControlEstablecimiento eev = (EvtControlEstablecimiento)ev;
						eev.ejecutarBaja(evento.getModificaOBaja().getIdOrdenie());
					}
					else
						throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_BAJA_EVENTO_CONTROL, new String[]{idEventoBaja.toString()});
				}
				/*if(evento.getModificaOBaja().getIdOrdenie()!= 0L){//quieren informar la baja de un ordenie
					if(ev instanceof EvtControlEstablecimiento){
						EvtControlEstablecimiento eev = (EvtControlEstablecimiento)ev;

						eev.ejecutarBaja(evento.getModificaOBaja().getIdOrdenie());
					//HibernateFactory.getSession().update(eev); 
					}
					else
						throw new ExcepcionIntegridad(MENSAJES.EVENTO_NO_CONTROL, new String[]{idEventoBaja.toString()});
					
				}*/
				else{
					if(evento.getModificaOBaja().getIdOrdenie()!= 0L)
						throw new ExcepcionIntegridad(MENSAJES.EVENTO_NO_CONTROL, new String[]{idEventoBaja.toString()});
					if(ev instanceof EvtAlta){
						EvtAlta alta = (EvtAlta)ev;
						alta.ejecutarBaja(mensajes);
					}
					else{
						if(ev instanceof EvtBajaEvento)
							throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_BAJA_EVENTO, new String[]{idEventoBaja.toString()});
						if(ev instanceof EvtLactancia)
							throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_LACTANCIA, new String[]{idEventoBaja.toString()});			
						if(ev instanceof EvtControlAnimal)
							throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_ORDEÑE, new String[]{idEventoBaja.toString()});
						if(ev instanceof EvtAnimalModificacion || ev instanceof EvtEstModificacion )
							throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_MODFICACION, new String[]{idEventoBaja.toString()});
						
						EvtAnimal ea = (EvtAnimal)ev;
						Animal an = ea.getAnimal();
						
						log.debug("baja evento " +idEventoBaja + ea.getNombreTipo());
						HibernateFactory.getSession().update(an); 
						ev.ejecutarBaja(); 
						an.setEstadoRetroactivo(ea.getFecha(), mensajes, ea.getNombreTipo());
						Eclo ed = EcloDAO.findByPrimaryKey(9L);
						log.warn(ed.getId());
					
					}
				}
				
				BajaEvt bajaEvt = (BajaEvt)evento;
				EvtBajaEvento bajaEvento = new EvtBajaEvento(objEst,bajaEvt.getFecha());
				bajaEvento.setIdEventoBaja(idEventoBaja);
				HibernateFactory.getSession().save(bajaEvento); 
				HibernateFactory.getSession().flush();
				//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
				return bajaEvento;
			}
			else {
				log.error("LA ECLO INFORMADA NO ES LA QUE GENERO EL EVENTO");
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{objEst.getEclo().getId().toString(),(idEcloInformante==null)?"NO EXISTE":idEcloInformante.toString()});				
			}
		}
		else
			throw new ExcepcionIntegridad(MENSAJES.NO_INFORMA_EVENTO_PARA_DAR_BAJA, new String[]{});				

		
		}

}
