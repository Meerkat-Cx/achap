//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import junit.framework.TestCase;
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.EvtCria;
//import ar.org.sicel.persistence.EvtCriaDAO;
//import ar.org.sicel.persistence.EvtReproduccion;
//import ar.org.sicel.persistence.EvtReproduccionDAO;
//import ar.org.sicel.persistence.EvtTransferenciaDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.MENSAJES;
//import ar.org.sicel.persistence.ProcMsg;
//import ar.org.sicel.persistence.Propietario;
//import ar.org.sicel.persistence.PropietarioDAO;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//
//public class TestPropietarioAsignadoACrias extends TestCase {
//	
//	public void testPropietarioMadreMismoEstablecimiento() throws Exception {
// 		 StandaloneHibernateStrategy.getInstance().openNewSession();
//
//		 Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111",
//	                true);
//		 
//		 List msgs = new LinkedList();
////		 es año( menos 1900), mes (0..11), dia (1..n)
//		 Date fecha = new Date(99,3,20);
//		 EvtCria crias[] = new EvtCria[1];
//		 crias[0] = EvtCriaDAO.create("Cria 1",	true,true,"RPCria","1",4.5f,0,true);
//		 EvtReproduccion evt = EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fecha,null,true,null,crias,msgs );
//		 Animal cria = ((EvtCria)evt.getEvtCrias().get(0)).getCria();
//		 assertEquals(an1.getPropietario(),cria.getPropietario());
//		 assertEquals(an1.getEstablecimiento().getPropietario(),cria.getPropietario());
//		 
//		 StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//	
//	public void testPropietarioMadreDistintoEstablecimiento() throws Exception {
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//
//  	    Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//  	    List msgs = new LinkedList();
//  	    
////		 es año( menos 1900), mes (0..11), dia (1..n)
//  	    Date fecha = new Date(99,3,20);
//  	    Propietario nuevoProp = PropietarioDAO.findExistentByPrimaryKey(new Long(1001));
//  	    EvtTransferenciaDAO.create(an1.getEstablecimiento(),an1,fecha,null,"NUEVORP",nuevoProp,msgs);
// 		 
//	
//  	    Date fecha2 = new Date(99,4,20);
//		EvtCria crias[] = new EvtCria[1];
//		crias[0] = EvtCriaDAO.create("Cria 1",	true,true,"RPCria","1",4.5f,0,true);
//		msgs.clear();
//		EvtReproduccion evt = EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fecha2,null,true,null,crias,msgs );		Animal cria = ((EvtCria)evt.getEvtCrias().get(0)).getCria();
//		
//		assertEquals(an1.getEstablecimiento().getPropietario(),cria.getPropietario());
//		assertNotSame(an1.getPropietario(),cria.getPropietario());
//		ProcMsg msg = (ProcMsg)msgs.get(0);
//		assertEquals(MENSAJES.CRIA_PROP,msg.getCodigoMsg().getId());
//		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//
//}
