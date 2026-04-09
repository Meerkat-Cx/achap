/*
 * Created on 12/04/2005
 *
 */
package ar.org.sicel.proc.services.impl;

import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcEstablecimiento;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcLoteDAO;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.proc.services.IProcesadorEstablecimiento;
import ar.org.sicel.proc.services.IProcesadorLote;
import ar.org.sicel.proc.services.locator.ServiceLocator;
import ar.org.sicel.proc.v1.lote.Estab;
import ar.org.sicel.proc.v1.lote.Lote;
import ar.org.sicel.util.StringUtils;

/**
 * @author pablo
 *
 */
public class ProcesadorLote implements IProcesadorLote {
    
       
    
	private static Logger log = Logger.getLogger(ProcesadorLote.class);

	//Obtengo los identificadores de eventos utilizados para la ECLO del lote
	public static volatile Map conjuntoIdsEclo = Collections.synchronizedMap(new HashMap());
	
	/* (non-Javadoc)
	 * @see ar.org.sicel.proc.services.IProcesadorLote#procesarLote(ar.org.sicel.proc.gen.Lote)
	 */
	//@RequiresTransaction 
	public ProcLote procesarLote(Lote lote,Date fechaEntrada) throws ExcepcionIntegridad, ErrorFatal {
			Date tInicio = new Date();
			log.info("Iniciando proceso de lote:" + lote.getIDLoteInte());
//			Session session = HibernateFactory.getSession();
			String nombreSistema = lote.getSistema().getNombre();	
			ProcLote proc = ProcLoteDAO.create(lote.getIDLoteInte(), lote.getTiempos().getTEnvi(), tInicio,lote.getInformante(), nombreSistema.toUpperCase(),lote.getCentroDeComputo());					
			IProcesadorEstablecimiento procEst =  ServiceLocator.getProcesadorEstablecimiento();
			
			int establecimientosOk = 0;
			int establecimientosFallidos = 0;
			Enumeration estabs = lote.getEstabs().enumerateEstab();
			while (estabs.hasMoreElements()) {
				try {
					//ProcEstablecimiento p = procEst.procesarEstablecimiento(proc.getTEnvio(),proc.getEclo(),(Estab)estabs.nextElement(), lote.getIDLoteInte());
					
					ProcEstablecimiento p = procEst.procesarEstablecimiento(proc.getTEnvio(),proc.getEclo(),(Estab)estabs.nextElement(), proc,proc.getCentroComputo(),fechaEntrada);
					
					proc.addProcEstablecimiento(p);
					establecimientosOk++; 
				} catch (ExcepcionIntegridad ei) {
					ProcMsg msg = ProcMsgDAO.create(ei.getCodigoError(),ProcMsg.ERROR,ei.getValores());
					proc.addMsg(msg);
					ResultadosUtil.addResultado(lote,msg);
					establecimientosFallidos++;
				}
			}
			
            ProcMsg nEclo = ProcMsgDAO.create(MENSAJES.NOMBRE_ECLO,ProcMsg.INFO,new String[]{proc.getEclo().getNombreContacto()} );
            proc.addMsg(nEclo);
            ResultadosUtil.addResultado(lote,nEclo);

            ProcMsg estOk = ProcMsgDAO.create(MENSAJES.ESTABLECIMIENTOS_OK,ProcMsg.INFO,new String[]{String.valueOf(establecimientosOk)} );
			proc.addMsg(estOk);
			ResultadosUtil.addResultado(lote,estOk);
			
			
			ProcMsg estFallidos = ProcMsgDAO.create(MENSAJES.ESTABLECIMIENTOS_ERROR,ProcMsg.INFO,new String[]{String.valueOf(establecimientosFallidos)} );
			proc.addMsg(estFallidos);
			ResultadosUtil.addResultado(lote,estFallidos);
			proc.setTiFinProc(new Date()); //ahora
			return proc;
	}

	
			
		
	

	
	
	
}
