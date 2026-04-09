/*
 * Created on 18/05/2005
 *
 */
package ar.org.sicel.proc.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.persistence.util.jasperReport.Resumen;
import ar.org.sicel.proc.services.IProcesadorLote;
import ar.org.sicel.proc.services.IProcesadorXML;
import ar.org.sicel.proc.services.locator.ServiceLocator;
import ar.org.sicel.proc.v1.anmodif.AnimsModificados;
import ar.org.sicel.proc.v1.lote.Estab;
import ar.org.sicel.proc.v1.lote.Estabs;
import ar.org.sicel.proc.v1.lote.Lote;
import ar.org.sicel.proc.v1.lote.Sistema;
import ar.org.sicel.proc.v1.lote.Tiempos;
import ar.org.sicel.upload.dao.ConstantsUpload;


/**
 * @author pablo
 * 
 */
public class ProcesadorXML implements IProcesadorXML {
    // TODO versionado de XSD y XSL
    // TODO fechas de proceso no se rellenan en la salida
    // TODO que animales se modificaron por el proceso
    // TODO fichas de animales modificados (para el MAIN)
    static Logger log = Logger.getLogger(ProcesadorXML.class);

   

    private Lote crearLoteVacio() {
        Lote lote = new Lote();
        lote.setInformante(-1);
        lote.setIDLoteInte(-1);
        Tiempos tiempos = new Tiempos();
        lote.setTiempos(tiempos);
        tiempos.setTEnvi(new Date());
        lote.setEstabs(new Estabs());
        Estab est = new Estab();
        est.setIDEstab(-1);
        lote.getEstabs().addEstab(est);
        Sistema sis = new Sistema();
        sis.setNombre("-1");
        sis.setVersion("-1");
        lote.setSistema(sis);
        //BigDecimal d = new BigDecimal(1.0);
        return lote;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.org.sicel.proc.services.IProcesadorXML#procesar(ar.org.sicel.proc.gen.Lote,
     *      ar.org.sicel.proc.gen.Lote,
     *      ar.org.sicel.proc.v1.anmodif.AnimsModificados)
     */
    @SuppressWarnings({"unchecked","unchecked"})
	public Lote procesar(ProcProces proc,Lote loteEntrada, AnimsModificados animsModificados, Collection resumen,Usuario usuario) {

        
        IProcesadorLote procesadorLotes = ServiceLocator.getProcesadorLote();
       // Session session = HibernateFactory.getSession();
        
      //  String usuario = "usuario";
        // TODO: aca en vez de eso supongo que habria que obtener el usuario
        // logeado,
        // ver despues como hacerlo
        //Usuario user = 
        Lote loteSalida = loteEntrada;
        
        
        //log.info("Usuario" + usuario.getUsername() + " ha iniciando procesamiento");
        //proc.setFecha(new Date());
       // ProcProcesDAO.updateProcProces(proc);
       // ProcProces proces = ProcProcesDAO.create(usuario, new Date());
        if (loteEntrada == null) {
            ProcMsg msg = ProcMsgDAO.create(MENSAJES.XML_NO_VALIDO,
                    ProcMsg.ERROR, new String[0]);
            proc.addMsg(msg);
            loteSalida = crearLoteVacio();
            loteSalida.getTiempos().setTProcIni(new Date());
            ResultadosUtil.addResultado(loteSalida, msg);
        } else {
            try {
                loteSalida.getTiempos().setTProcIni(new Date());
                AnimalesModificados.inicializar();
                AnimalesLactanciasOficiales.init();
                log.info("Lote a procesar: " + loteEntrada.getIDLoteInte() + " de la ECLO "  + loteEntrada.getInformante());
                
                ProcLote procLote = procesadorLotes.procesarLote(loteEntrada,proc.getFechaEntrada());
                
                proc.addProcLote(procLote);
            } catch (ExcepcionIntegridad ei) {
                ProcMsg msg = ProcMsgDAO.create(ei.getCodigoError(),
                        ProcMsg.ERROR, ei.getValores());
                proc.addMsg(msg);
                ResultadosUtil.addResultado(loteSalida, msg);
               // StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
            } catch (ErrorFatal ef) {
                ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                        ProcMsg.FATAL, new String[] { ef.getMessage() });
                proc.addMsg(msg);
                ResultadosUtil.addResultado(loteSalida, msg);
                proc.setEstado(ConstantsUpload.STANDBY);
              //  StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
            }
        }
        loteSalida.getTiempos().setTProcFin(new Date());
        try {
            //session.save(proces);
        	
        	ProcProcesDAO.updateProcProces(proc);
        	ProcesadorLote.conjuntoIdsEclo.clear();
            loteSalida.setIDLoteSicel(proc.getId()); // OJO, acordarce que
                                                        // este id NO es el del
                                                        // lote, sino el del
                                                        // proces
        } catch (HibernateException e) {
        	ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_FATAL,
                     ProcMsg.FATAL, new String[] { e.getMessage() });
            proc.addMsg(msg);
            ResultadosUtil.addResultado(loteSalida, msg);
            //throw new ErrorFatal(e);
            // TODO nivel de error FATAL
           // StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
        }
        Resumen a = new Resumen();
        Map mResumen = a.contarEventosInformados(loteEntrada);
        if (proc.getProcLote() != null)
        	a.contarEventosAceptados(proc.getProcLote(),mResumen);
        resumen.addAll(mResumen.values());
        
        log.info("Procesamiento finalizado");
        return loteSalida;
    }

}
