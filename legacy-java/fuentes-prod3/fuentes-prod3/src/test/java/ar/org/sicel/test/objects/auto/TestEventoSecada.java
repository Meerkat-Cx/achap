//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.EvtCria;
//import ar.org.sicel.persistence.EvtReproduccionDAO;
//import ar.org.sicel.persistence.EvtSecada;
//import ar.org.sicel.persistence.EvtSecadaDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import junit.framework.TestCase;
//
//public class TestEventoSecada extends TestCase {
//	
//	public void testSecada() throws Exception {
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//		
//		Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//	    List msgs = new LinkedList();
//	      
//	    // es año( menos 1900), mes (0..11), dia (1..n)
//		Date fecha = new Date(99,3,20);
//		
//		EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fecha,null,false,true,new EvtCria[0],msgs);
//		assertEquals(Animal.PRODUCCION_VACIA, an1.getEstadoActual());
//		
//		Date fecha2 = new Date(99,4,20);
//		EvtSecada secada = EvtSecadaDAO.create(an1.getEstablecimiento(),an1,fecha2,"Enfermedad",msgs);
//		assertEquals(Animal.SECA_VACIA,an1.getEstadoActual());
//		assertEquals("Enfermedad",secada.getMotivo());
//   
//		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//
//}
