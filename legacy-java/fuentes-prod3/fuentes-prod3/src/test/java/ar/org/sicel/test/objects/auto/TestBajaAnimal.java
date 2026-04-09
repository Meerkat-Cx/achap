//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.EvtBaja;
//import ar.org.sicel.persistence.EvtBajaDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import junit.framework.TestCase;
//
//public class TestBajaAnimal extends TestCase {
//	
//	public void testBaja() throws Exception {
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//		List msgs = new LinkedList();
//		
//	    Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1112", true);
//	    	      
//	    // es año( menos 1900), mes (0..11), dia (1..n)
//		Date fecha = new Date(99,3,20);
//		
//		
//		EvtBaja baja = EvtBajaDAO.create(an1.getEstablecimiento(),an1,"MATA","SANI","Nada interesante",fecha,msgs);
//		
//		assertEquals("MATA",baja.getDestino());
//		assertEquals("SANI",baja.getMotivo());
//		assertEquals(fecha,baja.getFecha());
//		
//		
//		Hembra an2 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1112", true);
//		assertEquals(Animal.BAJA, an2.getEstadoActual());
//		
//		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//		
//	}
//	
//
//}
