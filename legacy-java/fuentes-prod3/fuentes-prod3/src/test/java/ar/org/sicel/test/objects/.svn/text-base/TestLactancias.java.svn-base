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
public class TestLactancias extends TestCase {

    Hembra an1 = null;

    Macho padreGen = null;

    @SuppressWarnings("deprecation")
	public void iniciar() throws Exception {
        StandaloneHibernateStrategy.getInstance().openNewSession();
        an1 = (Hembra) AnimalDAO.findExistentHembraByRegistry("RC", "1111","HOLA");
        padreGen = (Macho) AnimalDAO.findExistentMachoByRegistry("HBA", "200000","HOLA"
                );

        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
        try {
            EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),
                            an1, STTipoServicio.INAR.toString(), new Date(100,
                                    5, 18), padreGen, msgs, new Date());
            // verMsgsYVaciar(evtServicio1, msgs);
            EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),
                            an1, STTipoServicio.INAR.toString(), new Date(101,
                                    1, 1), padreGen, msgs, new Date());
            // verMsgsYVaciar(evtServicio2, msgs);
            EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),
                            an1, STTipoServicio.INAR.toString(), new Date(101,
                                    2, 1), padreGen, msgs, new Date());
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
                                    2, 29), padreGen, msgs, new Date());
        } catch (ExcepcionIntegridad e) {
            System.out.println("Error de integridad, inicio:");
            System.out.println(e.getInfo());
        }
    }

    public void ponerEventosProduccion(Object[] prods) throws Exception {
        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
        try {
            for (int i = 0; i < prods.length; i++) {
                Object[] prod = (Object[]) prods[i];
                //String mc = (String) prod[0];
                Date fecha = (Date) prod[1];
                Float leche = (Float) prod[2];
                Float grasa = (Float) prod[3];
                EvtOrdenieAnimalDAO.createSimple(an1, fecha, msgs).addMed(
                        "LE", leche,an1).addMed("GR", grasa,an1);
            }
        } catch (ExcepcionIntegridad e) {
            System.out.println("Error de integridad, produccion:");
            System.out.println(e.getInfo());
        }
    }

    /**
     * @param date
     */
    @SuppressWarnings("deprecation")
	private void ponerEventoCierre(Date date) {
        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
        try {
            String esHembra = null;
            Float peso = null;
            EvtCria evtCria11 = EvtCriaDAO.create(null, false, false, null,
                    null, peso, null, esHembra,null, null, 0);
            EvtCria[] crias = new EvtCria[] { evtCria11 };
            EvtReproduccionDAO.create(an1.getEstablecimiento(), an1, date,
                    new Date(101, 2, 29), false, false, crias, msgs, null, false,0,new Date());
        } catch (ExcepcionIntegridad e) {
            System.out.println("Error de integridad, cierre:");
            System.out.println(e.getInfo());
        }
    }

    public void fin() {
        verAnimal(an1);
        StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
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

    @SuppressWarnings("deprecation")
	public void testAbierta() throws Exception {
        iniciar();
        Object[] prods = new Object[] {
                new Object[] { "A4", new Date(101, 3, 8), 28.2f, 3.65f },
                new Object[] { "A4", new Date(101, 4, 6), 24.8f, 3.45f },
                new Object[] { "A4", new Date(101, 5, 5), 26.6f, 3.4f },
                new Object[] { "A4", new Date(101, 6, 7), 23.2f, 3.55f },
                new Object[] { "A4", new Date(101, 7, 2), 20.2f, 3.85f },
                new Object[] { "A4", new Date(101, 7, 30), 17.8f, 4.05f },
                new Object[] { "A4", new Date(101, 8, 25), 13.2f, 4.45f },
                new Object[] { "A4", new Date(101, 9, 27), 9.6f, 4.65f },
                new Object[] { "A4", new Date(101, 10, 22), 5.8f, 4.95f },
                new Object[] { "A4", new Date(101, 11, 20), 4.4f, 5.25f } };
        ponerEventosProduccion(prods);
        fin();
    }

    @SuppressWarnings("deprecation")
	public void testCerrada1() throws Exception {
        iniciar();
        Object[] prods = new Object[] {
                new Object[] { "A4", new Date(101, 3, 8), 28.2f, 3.65f },
                new Object[] { "A4", new Date(101, 4, 6), 24.8f, 3.45f },
                new Object[] { "A4", new Date(101, 5, 5), 26.6f, 3.4f },
                new Object[] { "A4", new Date(101, 6, 7), 23.2f, 3.55f },
                new Object[] { "A4", new Date(101, 7, 2), 20.2f, 3.85f },
                new Object[] { "A4", new Date(101, 7, 30), 17.8f, 4.05f },
                new Object[] { "A4", new Date(101, 8, 25), 13.2f, 4.45f },
                new Object[] { "A4", new Date(101, 9, 27), 9.6f, 4.65f },
                new Object[] { "A4", new Date(101, 10, 22), 5.8f, 4.95f },
                new Object[] { "A4", new Date(101, 11, 20), 4.4f, 5.25f } };
        ponerEventosProduccion(prods);
        ponerEventoCierre(new Date(102, 0, 4));
        fin();
    }

    @SuppressWarnings("deprecation")
	public void testCerrada2() throws Exception {
        iniciar();
        Object[] prods = new Object[] {
                new Object[] { "A4", new Date(101, 3, 8), 28.2f, 3.65f },
                new Object[] { "A4", new Date(101, 4, 6), 24.8f, 3.45f },
                new Object[] { "A4", new Date(101, 5, 5), 26.6f, 3.4f },
                new Object[] { "A4", new Date(101, 6, 7), 23.2f, 3.55f },
                new Object[] { "A4", new Date(101, 7, 2), 20.2f, 3.85f },
                new Object[] { "A4", new Date(101, 7, 30), 17.8f, 4.05f },
                new Object[] { "A4", new Date(101, 8, 25), 13.2f, 4.45f },
                new Object[] { "A4", new Date(101, 9, 27), 9.6f, 4.65f },
                new Object[] { "A4", new Date(101, 11, 20), 4.4f, 5.25f } };
        ponerEventosProduccion(prods);
        ponerEventoCierre(new Date(102, 0, 4));
        fin();
    }
}
