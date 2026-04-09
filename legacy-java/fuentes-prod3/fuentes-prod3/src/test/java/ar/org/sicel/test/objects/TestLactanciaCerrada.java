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
import ar.org.sicel.persistence.EvtOrdenieAnimalDAO;
import ar.org.sicel.persistence.EvtReproduccionDAO;
import ar.org.sicel.persistence.EvtServicioDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.v1.lote.types.STTipoServicio;

/**
 * @author ala
 */
public class TestLactanciaCerrada extends TestCase {
    @SuppressWarnings("deprecation")
	public void testCerrada() throws Exception {
        StandaloneHibernateStrategy.getInstance().openNewSession();
        // buscar un animal
        Hembra an1 = (Hembra) AnimalDAO.findExistentHembraByRegistry("RC", "1111","HOLA");
       
        Macho padreGen = (Macho) AnimalDAO.findExistentMachoByRegistry("HBA",
                "200000","HOLA");

        verAnimal(padreGen);
        if (an1 == null || padreGen == null)
            return;
        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
        try {
            EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),
                            an1, STTipoServicio.INAR.toString(), new Date(100,
                                    5, 18),  padreGen, msgs, new Date());
            // verMsgsYVaciar(evtServicio1, msgs);
            EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),
                            an1, STTipoServicio.INAR.toString(), new Date(101,
                                    1, 1),  padreGen, msgs, new Date());
            // verMsgsYVaciar(evtServicio2, msgs);
            EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),
                            an1, STTipoServicio.INAR.toString(), new Date(101,
                                    2, 1),  padreGen, msgs, new Date());
            // verMsgsYVaciar(evtServicio3, msgs);
            // aborto
            String esHembra = null;
            Float peso = null;
            EvtCria evtCria11 = EvtCriaDAO.create(null, false, false, null,
                    null, peso, null, esHembra,null, null, 0);
            EvtCria[] crias = new EvtCria[] { evtCria11 };
            EvtReproduccionDAO.create(an1.getEstablecimiento(), an1, new Date(101, 2, 25),
                    new Date(100, 5, 18), false, false, crias, msgs, null, false,0,new Date());
            // verMsgsYVaciar(evtReprod1, msgs);
            // listar los eventos con su estado
            // System.out.println();

            EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),
                    an1, STTipoServicio.INAR.toString(), new Date(101,
                            2, 29),  padreGen, msgs, new Date());
            
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 3, 8), msgs)
                    .addMed("LE", 28.2f,an1).addMed("GR", 3.65f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 4, 6), msgs)
                    .addMed("LE", 24.8f,an1).addMed("GR", 3.45f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 5, 5), msgs)
                    .addMed("LE", 26.6f,an1).addMed("GR", 3.4f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 6, 7), msgs)
                    .addMed("LE", 23.2f,an1).addMed("GR", 3.55f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 7, 2), msgs)
                    .addMed("LE", 20.2f,an1).addMed("GR", 3.85f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 7, 30), msgs)
                    .addMed("LE", 17.8f,an1).addMed("GR", 4.05f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 8, 25), msgs)
                    .addMed("LE", 13.2f,an1).addMed("GR", 4.45f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 9, 27), msgs)
                    .addMed("LE", 9.6f,an1).addMed("GR", 4.65f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 10, 22), msgs)
                    .addMed("LE", 5.8f,an1).addMed("GR", 4.95f,an1);
            EvtOrdenieAnimalDAO.createSimple(an1, new Date(101, 11, 20), msgs)
                    .addMed("LE", 4.4f,an1).addMed("GR", 5.25f,an1);

            EvtReproduccionDAO.create(an1.getEstablecimiento(), an1, new Date(102, 0, 4),
                    new Date(101, 2, 29), false, false, crias, msgs, null, false,0,new Date());

            System.out.println("madre DESPUES");
            System.out.println("===========");
            verAnimal(an1);
        } catch (ExcepcionIntegridad e) {
            System.out.println("Error de integridad:");
            System.out.println(e.getInfo());
        }
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
