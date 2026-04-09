//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Set;
//
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.Evento;
//import ar.org.sicel.persistence.EvtAnimal;
//import ar.org.sicel.persistence.EvtControlAnimal;
//import ar.org.sicel.persistence.EvtCria;
//import ar.org.sicel.persistence.EvtCriaDAO;
//import ar.org.sicel.persistence.EvtLactancia;
//import ar.org.sicel.persistence.EvtControlEstablecimiento;
//import ar.org.sicel.persistence.EvtControlEstablecimientoDAO;
//import ar.org.sicel.persistence.EvtOrdenieAnimal;
//import ar.org.sicel.persistence.EvtOrdenieAnimalDAO;
//import ar.org.sicel.persistence.EvtReproduccion;
//import ar.org.sicel.persistence.EvtReproduccionDAO;
//import ar.org.sicel.persistence.EvtSecada;
//import ar.org.sicel.persistence.EvtSecadaDAO;
//import ar.org.sicel.persistence.EvtServicio;
//import ar.org.sicel.persistence.EvtServicioDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.Macho;
//import ar.org.sicel.persistence.ProcMsg;
//import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import ar.org.sicel.proc.v1.lote.types.STTipoServicio;
//import junit.framework.TestCase;
///**
// * TODO: actualizar los valores del test, ya que ahora hay varios
// * ordenies  por control de animal. Ahora esta echo el test con 2
// * ordeñes exctamente iguales.
// * 
// * 
// * @author user
// *
// */
//public class TestLactanciaCerradaNuevo extends TestCase {
//	
//	
//	 Hembra an1 = null;
//     Macho padreGen = null;
//
//	    public void iniciar() throws Exception {
//	        StandaloneHibernateStrategy.getInstance().openNewSession();
//	        an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//	        padreGen = (Macho) AnimalDAO.findExistentByRegistry("HBA", "200000",
//	                false);
//
//	        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
//	        try {
//	            EvtServicio evtServicio1 = EvtServicioDAO
//	                    .createNuevoIndividuoServSemen(an1.getEstablecimiento(),
//	                            an1, STTipoServicio.INAR.toString(), new Date(100,
//	                                    5, 18), padreGen, msgs);
//	            // verMsgsYVaciar(evtServicio1, msgs);
//	            EvtServicio evtServicio2 = EvtServicioDAO
//	                    .createNuevoIndividuoServSemen(an1.getEstablecimiento(),
//	                            an1, STTipoServicio.INAR.toString(), new Date(101,
//	                                    1, 1), padreGen, msgs);
//	            // verMsgsYVaciar(evtServicio2, msgs);
//	            EvtServicio evtServicio3 = EvtServicioDAO
//	                    .createNuevoIndividuoServSemen(an1.getEstablecimiento(),
//	                            an1, STTipoServicio.INAR.toString(), new Date(101,
//	                                    2, 1), padreGen, msgs);
//	            // verMsgsYVaciar(evtServicio3, msgs);
//	            // aborto
//	            Boolean esHembra = null;
//	            Float peso = null;
//	            EvtCria evtCria11 = EvtCriaDAO.create(null, false, false, null,
//	                    null, peso, null, esHembra);
//	            EvtCria[] crias = new EvtCria[] { evtCria11 };
//	            EvtReproduccion evtReprod1 = EvtReproduccionDAO.create(an1
//	                    .getEstablecimiento(), an1, new Date(101, 2, 25),
//	                    new Date(100, 5, 18), false, false, crias, msgs);
//
//	            EvtServicio evtServicio4 = EvtServicioDAO
//	                    .createNuevoIndividuoServSemen(an1.getEstablecimiento(),
//	                            an1, STTipoServicio.INAR.toString(), new Date(101,
//	                                    2, 29), padreGen, msgs);
//	        } catch (ExcepcionIntegridad e) {
//	            System.out.println("Error de integridad, inicio:");
//	            System.out.println(e.getInfo());
//	        }
//	    }
//
//	    public void ponerEventosProduccion(Object[] prods) throws Exception {
//	        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
//	        try {
//	            for (int i = 0; i < prods.length; i++) {
//	                Object[] prod = (Object[]) prods[i];
//	                String mc = (String) prod[0];
//	                Date fecha = (Date) prod[1];
//	                Float leche = (Float) prod[2];
//	                Float grasa = (Float) prod[3];
//	                EvtControlEstablecimiento ordenie = EvtControlEstablecimientoDAO.create(an1.getEstablecimiento(),(Date)prod[1],"metodoC",true,"tipoM",true,3,msgs);
//	                
//	                EvtControlAnimal controlAnimal = new EvtControlAnimal(an1.getEstablecimiento(),(Date)prod[1],an1,ordenie,msgs);
//	                
//	                EvtOrdenieAnimal produccion = EvtOrdenieAnimalDAO.create(msgs);
//	                produccion.addMed("GR",grasa).addMed("LE",leche);  
//	                EvtOrdenieAnimal produccion2 = EvtOrdenieAnimalDAO.create(msgs);
//	                produccion2.addMed("GR",grasa).addMed("LE",leche);
//	                controlAnimal.getOrdeniesAnimal().add(produccion);
//	                controlAnimal.getOrdeniesAnimal().add(produccion2);
//	            	
//					produccion.setControlAnimal(controlAnimal);
//					produccion2.setControlAnimal(controlAnimal);
//	                
//	                ordenie.getControlesAnimales().add(controlAnimal);
//	                
//	                an1.addEventoAnimal(controlAnimal,msgs);
//	              
//	            }
//	        } catch (ExcepcionIntegridad e) {
//	            System.out.println("Error de integridad, produccion:");
//	            System.out.println(e.getInfo());
//	        }
//	    }
//
//	    /**
//	     * @param date
//	     */
//	    private void ponerEventoCierre(Date date) {
//	        List<ProcMsg> msgs = new LinkedList<ProcMsg>();
//	        try {
//	        EvtSecada secada = EvtSecadaDAO.create(an1.getEstablecimiento(),an1,date,"Enfermedad",msgs);
//	        } catch (ExcepcionIntegridad e) {
//	            System.out.println("Error de integridad, cierre:");
//	            System.out.println(e.getInfo());
//	        }
//	    }
//
//	    public void fin() {
//	      //  verAnimal(an1);
//	        StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	    }
//
//	    public void verAnimal(Animal an1) {
//	        // listar los eventos con su estado
//	        System.out.println(an1.toString());
//	        Set evs = an1.getAllEventos();
//	        if (evs.size() > 0) {
//	            System.out.println("EVTs.");
//	            System.out.println("=====");
//	            for (Iterator iter = evs.iterator(); iter.hasNext();) {
//	                Evento ev = (Evento) iter.next();
//	                System.out.println(ev.getResumenYEncab());
//	            }
//	        }
//	    }
//
//	    
//	    public void testCerrada1() throws Exception {
//	        iniciar();
//	        Object[] prods = new Object[] {
//	                new Object[] { "A4", new Date(101, 3, 8), 28.2f, 3.65f },
//	                new Object[] { "A4", new Date(101, 4, 6), 24.8f, 3.45f },
//	                new Object[] { "A4", new Date(101, 5, 5), 26.6f, 3.4f },
//	                new Object[] { "A4", new Date(101, 6, 7), 23.2f, 3.55f },
//	                new Object[] { "A4", new Date(101, 7, 2), 20.2f, 3.85f },
//	                new Object[] { "A4", new Date(101, 7, 30), 17.8f, 4.05f },
//	                new Object[] { "A4", new Date(101, 8, 25), 13.2f, 4.45f },
//	                new Object[] { "A4", new Date(101, 9, 27), 9.6f, 4.65f },
//	                new Object[] { "A4", new Date(101, 10, 22), 5.8f, 4.95f },
//	                new Object[] { "A4", new Date(101, 11, 20), 4.4f, 5.25f } };
//	        ponerEventosProduccion(prods);
//	        ponerEventoCierre(new Date(102, 0, 4));
//	      
//	        Iterator eventos = an1.getEvtAnimals().iterator();
//            Object anterior = null;
//            Object evento = null;
//            while (eventos.hasNext()) {
//            	anterior = evento;
//            	evento = eventos.next();
//            	 
//            }
//            assertEquals(EvtLactancia.class,anterior.getClass());
//            EvtLactancia evtLactancia = (EvtLactancia) anterior; an1.getEvtAnimals().last();
//            
//            assertEquals(new Integer(285),evtLactancia.getDias());
//            Float gr = (Float) evtLactancia.getMediciones().get("GR");
//            Float le = (Float)evtLactancia.getMediciones().get("LE");
//            
//            double gr_dif = Math.abs(gr - 3.82 ); 
//            float le_dif = Math.abs(le - 4973);
//            System.out.println("Diferencia grasa: " + gr_dif + " grasa: " + gr);
//            System.out.println("Diferencia Leche:" + le_dif  + " leche:" +  le);
//            assertTrue(gr_dif < 1);
//           // assertTrue(le_dif < 1);
//	        assertEquals(new Integer(2),evtLactancia.getNroLact()); //la hembra ya tenia una lactancia migrada en la base
//	        assertEquals(EvtAnimal.EVT_TIPO_REP ,evtLactancia.getCodigoInicio());
//	        assertEquals(EvtAnimal.EVT_TIPO_SEC, evtLactancia.getCausaFin());
//	        
//	        fin();
//	    }
//
//	   
//}
