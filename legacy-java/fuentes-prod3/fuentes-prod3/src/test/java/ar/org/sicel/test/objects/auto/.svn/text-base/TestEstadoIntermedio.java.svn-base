//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.EvtCria;
//import ar.org.sicel.persistence.EvtCriaDAO;
//import ar.org.sicel.persistence.EvtEstado;
//import ar.org.sicel.persistence.EvtEstadoDAO;
//import ar.org.sicel.persistence.EvtReproduccion;
//import ar.org.sicel.persistence.EvtReproduccionDAO;
//import ar.org.sicel.persistence.EvtSecada;
//import ar.org.sicel.persistence.EvtSecadaDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.MENSAJES;
//import ar.org.sicel.persistence.MatrizTransiciones;
//import ar.org.sicel.persistence.ProcMsg;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import junit.framework.TestCase;
//
//public class TestEstadoIntermedio extends TestCase {
//	
//	/**
//	 * testea que se pueda pedir bien el estado en que se encuentra/encontraba
//	 * un animal en una determinada fecha.
//	 * 
//	 * @throws Exception
//	 */
//	public void testEstadoIntermedio() throws Exception {
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//		
//		
//		Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1112", true);
//		List msgs = new LinkedList();
//		
//		// es año( menos 1900), mes (0..11), dia (1..n)
//		Date fecha = new Date(99,3,20);
//		
//	    
//		EvtCria crias[] = new EvtCria[1];
//		crias[0] = EvtCriaDAO.create(null,	false,	false,null,	null,null,0,null);
//	 	EvtReproduccion repr1 = EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fecha,null,false,true,crias,msgs);
//		StandaloneHibernateStrategy.getInstance().getCurrentSession().save(repr1);
//		assertEquals(an1.getEstadoActual(),Animal.PRODUCCION_VACIA);
//		ProcMsg msg = (ProcMsg)msgs.get(0);
//		
//		
////		 es año( menos 1900), mes (0..11), dia (1..n)
//		Date fecha2 = new Date(99,5,20);
//		
//		EvtSecada secada = EvtSecadaDAO.create(an1.getEstablecimiento(),an1,fecha2,"Enfermedad",msgs);
//		StandaloneHibernateStrategy.getInstance().getCurrentSession().save(secada);
//		assertEquals(Animal.SECA_VACIA,an1.getEstadoActual());
//		
//		assertEquals(Animal.SECA_VACIA, MatrizTransiciones.estadoPrevio(an1,new Date(99,6,20)));
//		assertEquals(Animal.PRODUCCION_VACIA, MatrizTransiciones.estadoPrevio(an1,new Date(99,3,28)));
//		
//		
//		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//		
//	}
//
//}
