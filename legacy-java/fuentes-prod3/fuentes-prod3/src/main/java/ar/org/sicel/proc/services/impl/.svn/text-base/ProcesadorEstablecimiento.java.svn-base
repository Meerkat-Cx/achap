/*
 * Created on 12/04/2005
 */
package ar.org.sicel.proc.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.CentroDeComputo;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.LogContacto;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcAnimal;
import ar.org.sicel.persistence.ProcEstablecimiento;
import ar.org.sicel.persistence.ProcEstablecimientoDAO;
import ar.org.sicel.persistence.ProcEvtEst;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.proc.services.IProcesadorAnimal;
import ar.org.sicel.proc.services.IProcesadorEstablecimiento;
import ar.org.sicel.proc.services.IProcesadorEventoEstablecimiento;
import ar.org.sicel.proc.services.locator.ServiceLocator;
import ar.org.sicel.proc.v1.lote.Alta;
import ar.org.sicel.proc.v1.lote.Animal;
import ar.org.sicel.proc.v1.lote.Anims;
import ar.org.sicel.proc.v1.lote.BajaEvt;
import ar.org.sicel.proc.v1.lote.Control;
import ar.org.sicel.proc.v1.lote.Estab;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.EvtsEstab;
import ar.org.sicel.util.DateUtils;
//import ar.org.sicel.proc.v1.lote.Semen;


/**
 * @author pablo
 */
public class ProcesadorEstablecimiento implements IProcesadorEstablecimiento {
    Logger log = Logger.getLogger(ProcesadorEstablecimiento.class);

    /* (non-Javadoc)
     * @see ar.org.sicel.proc.services.IProcesadorEstablecimiento#procesarEstablecimiento(ar.org.sicel.proc.gen.Estab)
     */
    int eventosEstOk = 0;
    int eventosEstFallidos = 0;
    //@RequiresTransaction 
    public ProcEstablecimiento procesarEstablecimiento(Date fechaEnvioLote,Eclo ecloInformante, 
    		Estab establecimiento, ProcLote procLote,CentroDeComputo centro,Date fechaEntradaSicel) throws ExcepcionIntegridad{
        log.info("Comenzando el proceso del tambo:" +  establecimiento.getIDEstab());
        Establecimiento est = EstablecimientoDAO.findExistentByPrimaryKey(establecimiento.getIDEstab());
       /* List r =new ArrayList(est.getBitacora());
        Date fechaBaja = null;
        if(!r.isEmpty()){
			LogContacto logBaja =(LogContacto) r.get(r.size()-1);
			
			if(logBaja.getAccion().trim().equals(LogContacto.DESACTIVAR))
				fechaBaja =logBaja.getFecha();
			else
				if(r.size()-2 >= 0){
					logBaja =(LogContacto) r.get(r.size()-2);
					fechaBaja =logBaja.getFecha();
				}
        }	*/
        LogContacto logBaja = LogContacto.getUltimoLogDeBaja(est);
        Date fechaBaja = null;
        if(logBaja != null)
        	fechaBaja = logBaja.getFecha();
        ProcEstablecimiento procEstablecimiento = ProcEstablecimientoDAO.create(est,ecloInformante,centro,fechaEntradaSicel,fechaBaja);
        
        ProcMsg nEstab = ProcMsgDAO.create(MENSAJES.NOMBRE_ESTAB,ProcMsg.INFO, new String[]{procEstablecimiento.getEstablecimiento().getNombreContacto()});
        procEstablecimiento.addMsg(nEstab);
        ResultadosUtil.addResultado(establecimiento,nEstab);

        //procesar los eventos de Establecimiento 
		// (por ahora Altas y Semens)
        //Integer eventosEstOk = new Integer(0);
        // Integer eventosEstFallidos = new Integer(0);
       procesarEventosEstab(fechaEnvioLote,ecloInformante,establecimiento,procEstablecimiento,establecimiento.getEvtsEstab(), procLote,fechaBaja/*,eventosEstOk,eventosEstFallidos*/);
        
        procesarAnimales(fechaEnvioLote,ecloInformante,establecimiento,procEstablecimiento, establecimiento.getAnims(), procLote,fechaBaja);
        
        procesarEventosControles(fechaEnvioLote,ecloInformante,establecimiento,procEstablecimiento,establecimiento.getEvtsEstab(), procLote,fechaBaja/*,eventosEstOk,eventosEstFallidos*/);
        
        ProcMsg evtsOk = ProcMsgDAO.create(MENSAJES.EVT_EST_OK,ProcMsg.INFO,new String[]{String.valueOf(eventosEstOk)});
        procEstablecimiento.addMsg(evtsOk);
		ResultadosUtil.addResultado(establecimiento,evtsOk);
		
		ProcMsg evtsFallidos = ProcMsgDAO.create(MENSAJES.EVT_EST_ERROR,ProcMsg.INFO,new String[]{String.valueOf(eventosEstFallidos)});
		procEstablecimiento.addMsg(evtsFallidos);
		ResultadosUtil.addResultado(establecimiento,evtsFallidos);
        
        //procesar los animales
        log.info("Finalizado el proceso del establecimiento:" + establecimiento.getIDEstab());
        return procEstablecimiento;
    }

    private void procesarEventosEstab(Date fechaEnvioLote,Eclo ecloInformante,Estab estab,
    		ProcEstablecimiento procEstab, EvtsEstab eventos, ProcLote procLote,Date fechaBaja/*,Integer eventosEstOk, Integer eventosEstFallidos*/) {
        log.info("Procesando eventos establecimiento");
//        Session session = HibernateFactory.getSession();
        //int eventosEstOk = 0;
       // int eventosEstFallidos = 0;
        Establecimiento objEstab = procEstab.getEstablecimiento();
        if (eventos != null) { //si hay algun evento de establecimiento
        	IProcesadorEventoEstablecimiento procEvtEst = ServiceLocator.getProcesadorEventoEstablecimiento();
        	if (eventos.getAltas() != null) {
        		Enumeration altas = eventos.getAltas().enumerateAlta();
        		while (altas.hasMoreElements()) {
        			Alta alta = (Alta) altas.nextElement();
        			try {
        				/*Date fechaNac = null;
        				if (alta.getTEvtAltaChoice().getEmpadronar() != null)  // es un alta con empadronar
        					 fechaNac = alta.getTEvtAltaChoice().getEmpadronar()
        							.getFechaNacimiento();
        				else{
        					if (alta.getTEvtAltaChoice().getCompoRacial() != null)  
        							fechaNac = alta.getTEvtAltaChoice().getCompoRacial().getFechaNacimiento();
        					else
        						if (alta.getTEvtAltaChoice().getPadres() != null)  
        							fechaNac = alta.getTEvtAltaChoice().getPadres().getFechaNacimiento();
        				}*/
        				//chequearFechaProcEvento(alta,fechaNac,objEstab);
        				chequearFechaProcEvento(alta,alta.getFecha(),objEstab);
        				ProcEvtEst procEvt = procEvtEst.procesarEventoEstablecimiento(fechaEnvioLote,ecloInformante,objEstab, alta, procLote);
        				procEstab.addProcEvtEsts(procEvt);
        				eventosEstOk++;
        				System.gc();
        			} catch (ExcepcionIntegridad e1) {
        				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
        				procEstab.addMsg(msg);
        				//ResultadosUtil.addResultado(estab,msg);
        				//TODO agregamos los errores al evento alta
        				ResultadosUtil.addResultado(alta,msg);
        				eventosEstFallidos++;
        			}
        		}
        	}
/*           	if (eventos.getSemens() != null) {
        		Enumeration semens = eventos.getSemens().enumerateSemen();
        		while (semens.hasMoreElements()) {
        			Semen semen = (Semen) semens.nextElement();
        			try {
        				ProcEvtEst procEvt = procEvtEst.procesarEventoEstablecimiento(fechaEnvioLote,ecloInformante,objEstab, semen, procLote);
        				procEstab.addProcEvtEsts(procEvt);
        				eventosEstOk++;
        			} catch (ExcepcionIntegridad e1) {
        				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
        				procEstab.addMsg(msg);
        				ResultadosUtil.addResultado(semen,msg);
        				eventosEstFallidos++;
        			}
        		}
            }*/
         /*  	if (eventos.getControles() != null) {
        		Enumeration ordenies = eventos.getControles().enumerateControl();
        		while (ordenies.hasMoreElements()) {
        			Control ordenie = (Control) ordenies.nextElement();
        			try {
        				ProcEvtEst procEvt = procEvtEst.procesarEventoEstablecimiento(fechaEnvioLote,ecloInformante,objEstab, ordenie, procLote);
        				procEstab.addProcEvtEsts(procEvt);
        				eventosEstOk++;
        			} catch (ExcepcionIntegridad e1) {
        				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
        				procEstab.addMsg(msg);
        				//ResultadosUtil.addResultado(estab,msg);
        				//TODO agregamos los errores al evento control
        				ResultadosUtil.addResultado(ordenie,msg);
        				/*List<ProcMsg> msgs = e1.getMensajes();
        				if( msgs != null && !msgs.isEmpty()){
        					Iterator it_msgs = msgs.iterator();
        					while(it_msgs.hasNext()){
        						ProcMsg pm = (ProcMsg)it_msgs.next();
        						procEstab.addMsg(pm);
        						ResultadosUtil.addResultado(ordenie,pm);
        					}
        				}*/	
        		/*		eventosEstFallidos++;
        			}
        		}
        	}*/
           	if (eventos.getBajasEvt() != null) {
        		Enumeration bajasEvt = eventos.getBajasEvt().enumerateBajaEvt();
        		while (bajasEvt.hasMoreElements()) {
        			BajaEvt bajaEvt = (BajaEvt) bajasEvt.nextElement();
        			try {
        				ProcEvtEst procEvt = procEvtEst.procesarEventoEstablecimiento(fechaEnvioLote,ecloInformante,objEstab, bajaEvt, procLote);
        				procEstab.addProcEvtEsts(procEvt);
        				eventosEstOk++;
        				System.gc();
        			} catch (ExcepcionIntegridad e1) {
        				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
        				procEstab.addMsg(msg);
        				//ResultadosUtil.addResultado(estab,msg);
        				//TODO agregamos los errores al evento baja de evento
        				ResultadosUtil.addResultado(bajaEvt,msg);
        				eventosEstFallidos++;
        			}
        		}
        	}
        }
       /* ProcMsg evtsOk = ProcMsgDAO.create(MENSAJES.EVT_EST_OK,ProcMsg.INFO,new String[]{String.valueOf(eventosEstOk)});
		procEstab.addMsg(evtsOk);
		ResultadosUtil.addResultado(estab,evtsOk);
		
		ProcMsg evtsFallidos = ProcMsgDAO.create(MENSAJES.EVT_EST_ERROR,ProcMsg.INFO,new String[]{String.valueOf(eventosEstFallidos)});
		procEstab.addMsg(evtsFallidos);
		ResultadosUtil.addResultado(estab,evtsFallidos);*/
    }

    private void chequearFechaProcEvento(Object evento,Date fechaEvento,Establecimiento estab) throws ExcepcionIntegridad {
    	//if(fechaBajaTambo!=null && fechaEvento.after(fechaBajaTambo))
    	if(!estab.estaActivoEnFecha(evento,fechaEvento))
    		throw new ExcepcionIntegridad(MENSAJES.TAMBO_INACTIVO, new String[] {estab.getId().toString(),DateUtils.format(fechaEvento,"dd/MM/yyyy")});
		
	}

	private void procesarEventosControles(Date fechaEnvioLote,Eclo ecloInformante,Estab estab,
    		ProcEstablecimiento procEstab, EvtsEstab eventos, ProcLote procLote,Date fechaBaja/*,Integer eventosEstOk,Integer eventosEstFallidos*/) {
        log.info("Procesando eventos controles");
//        Session session = HibernateFactory.getSession();
        //int eventosEstOk = 0;
        //int eventosEstFallidos = 0;
        Establecimiento objEstab = procEstab.getEstablecimiento();
        if (eventos != null) { //si hay algun evento de establecimiento
        	IProcesadorEventoEstablecimiento procEvtEst = ServiceLocator.getProcesadorEventoEstablecimiento();
           	if (eventos.getControles() != null) {
        		Enumeration ordenies = eventos.getControles().enumerateControl();
        		while (ordenies.hasMoreElements()) {
        			Control ordenie = (Control) ordenies.nextElement();
        			
        			try {
        				chequearFechaProcEvento(ordenie,ordenie.getFecha(),objEstab);
        				ProcEvtEst procEvt = procEvtEst.procesarEventoEstablecimiento(fechaEnvioLote,ecloInformante,objEstab, ordenie, procLote);
        				procEstab.addProcEvtEsts(procEvt);
        				eventosEstOk ++;
        				System.gc();
        			} catch (ExcepcionIntegridad e1) {
        				ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
        				procEstab.addMsg(msg);
        				//ResultadosUtil.addResultado(estab,msg);
        				//TODO agregamos los errores al evento control
        				ResultadosUtil.addResultado(ordenie,msg);
        				/*List<ProcMsg> msgs = e1.getMensajes();
        				if( msgs != null && !msgs.isEmpty()){
        					Iterator it_msgs = msgs.iterator();
        					while(it_msgs.hasNext()){
        						ProcMsg pm = (ProcMsg)it_msgs.next();
        						procEstab.addMsg(pm);
        						ResultadosUtil.addResultado(ordenie,pm);
        					}
        				}*/	
        				eventosEstFallidos ++;
        			}
        		}
        	}
        }
       /* ProcMsg evtsOk = ProcMsgDAO.create(MENSAJES.EVT_EST_OK,ProcMsg.INFO,new String[]{String.valueOf(eventosEstOk)});
		procEstab.addMsg(evtsOk);
		ResultadosUtil.addResultado(estab,evtsOk);
		
		ProcMsg evtsFallidos = ProcMsgDAO.create(MENSAJES.EVT_EST_ERROR,ProcMsg.INFO,new String[]{String.valueOf(eventosEstFallidos)});
		procEstab.addMsg(evtsFallidos);
		ResultadosUtil.addResultado(estab,evtsFallidos);*/
    }

    private void procesarAnimales(Date fechaEnvioLote,Eclo ecloInformante,Estab estab,
    		ProcEstablecimiento procEstab, Anims animales, ProcLote procLote,Date fechaBaja) {
        log.info("Procesando animales");
        int animalesOk = 0;
        int animalesFallidos = 0;
        Establecimiento objEstab = procEstab.getEstablecimiento();
        IProcesadorAnimal procesadorAnimales = ServiceLocator.getProcesadorAnimal();
        if (animales != null) {
        	Enumeration anims = animales.enumerateAnimal();
        	while (anims.hasMoreElements()) {
        		Animal animal = (Animal) anims.nextElement();
        		try {
        			ProcAnimal procAnim = procesadorAnimales.procesarAnimal(fechaEnvioLote,ecloInformante,objEstab, animal, procLote,fechaBaja);
        			procEstab.addProcAnimal(procAnim);
        			animalesOk++;
        			System.gc();
        		} catch (ExcepcionIntegridad e1) {
        			ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(), ProcMsg.ERROR,e1.getValores());
        			procEstab.addMsg(msg);
        			ResultadosUtil.addResultado(estab,msg);
        			animalesFallidos++;
        		}
        	}
        }
    	ProcMsg animsOk = ProcMsgDAO.create(MENSAJES.ANIMALES_OK,ProcMsg.INFO, new String[]{String.valueOf(animalesOk )});
		procEstab.addMsg(animsOk);
		ResultadosUtil.addResultado(estab,animsOk);
		
		ProcMsg animsFallidos = ProcMsgDAO.create(MENSAJES.ANIMALES_ERROR,ProcMsg.INFO,new String[] {String.valueOf(animalesFallidos)});
		procEstab.addMsg(animsFallidos);
		ResultadosUtil.addResultado(estab,animsFallidos);
    }
}
