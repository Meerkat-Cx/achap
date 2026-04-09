//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import junit.framework.TestCase;
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.EvtEstado;
//import ar.org.sicel.persistence.EvtEstadoDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.MENSAJES;
//import ar.org.sicel.persistence.ProcMsg;
//import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//
//public class TestEventoEstado extends TestCase {
//	
//	public void testSV_SV() throws Exception {
//		  StandaloneHibernateStrategy.getInstance().openNewSession();
//
//	      Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1112", true);
//	      List msgs = new LinkedList();
//	      
//	      // es año( menos 1900), mes (0..11), dia (1..n)
//		  Date fecha = new Date(99,3,20);
//			
//	      EvtEstado ev = EvtEstadoDAO.create(an1.getEstablecimiento(),an1,Animal.SECA_VACIA,1,fecha,msgs);
//	      assertEquals(an1.getEstadoActual(),Animal.SECA_VACIA);
//	      ProcMsg msg = (ProcMsg)msgs.get(0);
//	      assertEquals(msg.getCodigoMsg().getId(),MENSAJES.YaEstado);
//	      StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//	
//	public void testSV_SS() throws Exception {
//		  StandaloneHibernateStrategy.getInstance().openNewSession();
//
//	      Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//	      List msgs = new LinkedList();
//
//	      // es año( menos 1900), mes (0..11), dia (1..n)
//		  Date fecha = new Date(99,3,20);
//
//	      try {
//	    	  EvtEstado ev = EvtEstadoDAO.create(an1.getEstablecimiento(),an1,Animal.SECA_SERVIDA,1,fecha,msgs);
//	    	  fail("Deviera fallar");
//	      } catch (ExcepcionIntegridad ei) {
//	    	  assertEquals(MENSAJES.NoSvc, ei.getCodigoError());
//	      }
//	      StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//	
//	public void testSV_SP() throws Exception {
//		  StandaloneHibernateStrategy.getInstance().openNewSession();
//
//	      Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//	      List msgs = new LinkedList();
//	      
//	      // es año( menos 1900), mes (0..11), dia (1..n)
//		  Date fecha = new Date(99,3,20);
//			
//	      EvtEstado ev = EvtEstadoDAO.create(an1.getEstablecimiento(),an1,Animal.SECA_PRENIADA,1,fecha,msgs);
//	      assertEquals(an1.getEstadoActual(),Animal.SECA_PRENIADA);
//	      ProcMsg msg = (ProcMsg)msgs.get(0);
//	      assertEquals(msg.getCodigoMsg().getId(),MENSAJES.VacPre);
//	      StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//	
//	public void testSP_SV() throws Exception {
//		  StandaloneHibernateStrategy.getInstance().openNewSession();
//
//	      Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
//	      List msgs = new LinkedList();
//	      
//	      // Primer evento: pasa a SP
//	      // es año( menos 1900), mes (0..11), dia (1..n)
//		  Date fecha = new Date(99,3,20);
//	      EvtEstado ev = EvtEstadoDAO.create(an1.getEstablecimiento(),an1,Animal.SECA_PRENIADA,1,fecha,msgs);
//	      assertEquals(an1.getEstadoActual(),Animal.SECA_PRENIADA);
//	      ProcMsg msg = (ProcMsg)msgs.get(0);
//	      assertEquals(msg.getCodigoMsg().getId(),MENSAJES.VacPre);
//	      
//	      
//	      // Segundo evento: pasa de ahi a SV
//		  Date fecha2 = new Date(99,3,22);
//			
//	      EvtEstado ev2 = EvtEstadoDAO.create(an1.getEstablecimiento(),an1,Animal.SECA_VACIA,1,fecha2,msgs);
//	      assertEquals(an1.getEstadoActual(),Animal.SECA_VACIA);
//	      ProcMsg msg2 = (ProcMsg)msgs.get(1);
//	      assertEquals(msg2.getCodigoMsg().getId(),MENSAJES.FinPre);
//	      	      
//	      
//	      StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//	
//
//	
//	
//	
//	
//
//}
