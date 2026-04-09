//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.EvtCria;
//import ar.org.sicel.persistence.EvtPrenez;
//import ar.org.sicel.persistence.EvtPrenezDAO;
//import ar.org.sicel.persistence.EvtReproduccionDAO;
//import ar.org.sicel.persistence.EvtSecada;
//import ar.org.sicel.persistence.EvtSecadaDAO;
//import ar.org.sicel.persistence.EvtServicioDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.Macho;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import junit.framework.TestCase;
//
//public class TestEventoPrenez extends TestCase {
//	
//	public void testPrenezPositiva() throws Exception {
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//		
//		Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//		Macho toro = AnimalDAO.findExistentMachoByRegistry("HBA","200000");
//	    List msgs = new LinkedList();
//	      
//	    // es año( menos 1900), mes (0..11), dia (1..n)
//		Date fecha = new Date(99,3,20);
//		
//		EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),an1,"INAR",fecha,toro,msgs);
//		assertEquals(Animal.SECA_SERVIDA, an1.getEstadoActual());
//		
//		Date fecha2 = new Date(99,4,20);
//		EvtPrenez secada = EvtPrenezDAO.create(an1.getEstablecimiento(),an1,fecha2,true,msgs);
//		assertEquals(Animal.SECA_PRENIADA,an1.getEstadoActual());
//		   
//		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//	
//	public void testPrenezNegativa() throws Exception {
//			StandaloneHibernateStrategy.getInstance().openNewSession();
//			
//			Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//			Macho toro = AnimalDAO.findExistentMachoByRegistry("HBA","200000");
//		    List msgs = new LinkedList();
//		      
//		    // es año( menos 1900), mes (0..11), dia (1..n)
//			Date fecha = new Date(99,3,20);
//			
//			EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),an1,"INAR",fecha,toro,msgs);
//			assertEquals(Animal.SECA_SERVIDA, an1.getEstadoActual());
//			
//			Date fecha2 = new Date(99,4,20);
//			EvtPrenez secada = EvtPrenezDAO.create(an1.getEstablecimiento(),an1,fecha2,false,msgs);
//			assertEquals(Animal.SECA_VACIA,an1.getEstadoActual());
//			   
//			StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//		}	
//
//}
