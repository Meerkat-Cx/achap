//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import junit.framework.TestCase;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.Establecimiento;
//import ar.org.sicel.persistence.EstablecimientoDAO;
//import ar.org.sicel.persistence.EvtTransferenciaDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.Propietario;
//import ar.org.sicel.persistence.PropietarioDAO;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//
//public class TestTransferencia extends TestCase {
//	
//	public void testTransferenciaPropietario() throws Exception {
//	 StandaloneHibernateStrategy.getInstance().openNewSession();
//
//	 Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111",
//                true);
//	 
//	 List msgs = new LinkedList();
////	 es año( menos 1900), mes (0..11), dia (1..n)
//	 Date fecha = new Date(99,3,20);
//	 Propietario nuevoProp = PropietarioDAO.findExistentByPrimaryKey(new Long(1001));
//	 EvtTransferenciaDAO.create(an1.getEstablecimiento(),an1,fecha,null,"NUEVORP",nuevoProp,msgs);
//	 
//	 assertEquals(nuevoProp,an1.getPropietario());
//	 assertEquals("NUEVORP",an1.getRP());
//	 
//	 Propietario nuevoProp2 = PropietarioDAO.findExistentByPrimaryKey(new Long(1002));
//	 EvtTransferenciaDAO.create(an1.getEstablecimiento(),an1,fecha,null,"NUEVORP",nuevoProp2,msgs);
//	 
//	 assertEquals(nuevoProp2,an1.getPropietario());
//	 assertEquals("NUEVORP",an1.getRP());
//	 
//	 StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	 
//	}
//	
//	
//	public void testTransferenciaEstablecimiento() throws Exception {
//	
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//		Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111",
//                true);
//	 
//		List msgs = new LinkedList();
//		
//		//es año( menos 1900), mes (0..11), dia (1..n)
//		Date fecha = new Date(99,3,20);
//		Establecimiento nuevoEstab = EstablecimientoDAO.findExistentByPrimaryKey(new Long(8816));
//		EvtTransferenciaDAO.create(an1.getEstablecimiento(),an1,fecha,nuevoEstab,"NUEVORP",null,msgs);
//	 
//		assertEquals("NUEVORP",an1.getRP());
//		assertEquals(nuevoEstab,an1.getEstablecimiento());
//	 
//		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//		}
//	
//	public void testTransferenciaPropietarioYEstablecimiento() throws Exception {
//		 StandaloneHibernateStrategy.getInstance().openNewSession();
//
//		 Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111",true);
//		 
//		 List msgs = new LinkedList();
////		 es año( menos 1900), mes (0..11), dia (1..n)
//		 Date fecha = new Date(99,3,20);
//		 Propietario nuevoProp = PropietarioDAO.findExistentByPrimaryKey(new Long(1012));
//		 Establecimiento nuevoEstab = EstablecimientoDAO.findExistentByPrimaryKey(new Long(8815));
//			
//		 EvtTransferenciaDAO.create(an1.getEstablecimiento(),an1,fecha,nuevoEstab,"NUEVORP",nuevoProp,msgs);
//		 
//		 assertEquals(nuevoProp,an1.getPropietario());
//		 assertEquals("NUEVORP",an1.getRP());
//		 assertEquals(nuevoEstab,an1.getEstablecimiento());
//		 
//		 StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//}
