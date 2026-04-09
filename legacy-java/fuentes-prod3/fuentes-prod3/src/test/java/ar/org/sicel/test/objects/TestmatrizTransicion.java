/*
 * Created on 17/06/2005
 */
package ar.org.sicel.test.objects;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EvtCria;
import ar.org.sicel.persistence.EvtCriaDAO;
import ar.org.sicel.persistence.EvtReproduccionDAO;
import ar.org.sicel.persistence.EvtServicioDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.v1.lote.types.STTipoServicio;

/**
 * @author ala
 */
public class TestmatrizTransicion extends TestCase {
    @SuppressWarnings("deprecation")
	public void testTransicion() throws Exception {
        StandaloneHibernateStrategy.getInstance().openNewSession();
        // buscar un animal
        Hembra an1 = (Hembra) AnimalDAO.findExistentHembraByRegistry("RC", "12","HOLA");
               
        
        Macho padreGen = (Macho) AnimalDAO.findExistentMachoByRegistry("HBA", "211845","HOLA"
                );
        if (an1 == null || padreGen == null)
            return;
//        System.out.println("madre ANTES");
//        System.out.println("===========");
//        verAnimal(an1);
//        System.out.println();
//        System.out.println("padre");
//        System.out.println("=====");
//        verAnimal(padreGen);
        // crear un evento (lo mete automaticamente en el animal?)
        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
        EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(), an1,
                        STTipoServicio.INAR.toString(), new Date(104, 0, 1),
                        padreGen, msgs, new Date());
//        verMsgsYVaciar(evtServicio1, msgs);
        EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(), an1,
                        STTipoServicio.INAR.toString(), new Date(104, 1, 1),
                        padreGen, msgs, new Date());
//        verMsgsYVaciar(evtServicio2, msgs);
        EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(), an1,
                        STTipoServicio.INAR.toString(), new Date(104, 2, 1),
                         padreGen, msgs, new Date());
//        verMsgsYVaciar(evtServicio3, msgs);
        // aborto corto
        String esHembra=null;
        Float peso=null;
        EvtCria evtCria11 = EvtCriaDAO.create(null, false, false, null, null,
                peso, null, esHembra,null, null, 0);
        EvtCria[] crias = new EvtCria[] { evtCria11 };
        EvtReproduccionDAO.create(an1.getEstablecimiento(), an1,  new Date(104, 2, 15), null,
                false,false, crias, msgs, null, false,0,new Date());
//        verMsgsYVaciar(evtReprod1, msgs);
        // listar los eventos con su estado
//        System.out.println();
        
//        EvtProduccion evtProd1= EvtProduccionDAO.create(an1.getEstablecimiento(), an1,null , null, null, null, null, new Date(104,2,15), msgs);
  //y las mediciones?      
        System.out.println("madre DESPUES");
        System.out.println("===========");
        verAnimal(an1);
        // rollback
        StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
    }

    public void verMsgsYVaciar(Evento evento, List<ProcMsg> msgs) {
        // ver los msgs del evento
        System.out.println();
        System.out.println("mensajes del procesamiento del evento : "
                + evento.getResumenYEncab());
        System.out.println("=======================================");
        for (Iterator iter = msgs.iterator(); iter.hasNext();) {
            ProcMsg msg = (ProcMsg) iter.next();
            System.out.println(msg);
        }
        msgs.clear();
    }

    public void verAnimal(Animal an1) {
        // listar los eventos con su estado
        System.out.println(an1.toString());
        Set evs = an1.getAllEventos();
        if (evs.size() > 0) {
            System.out.println("EVTs.");
            System.out.println("=====");
            for (Iterator iter = evs.iterator(); iter.hasNext();) {
                Evento ev = (Evento) iter.next();
                System.out.println(ev.getResumenYEncab());
            }
        }
    }
}
