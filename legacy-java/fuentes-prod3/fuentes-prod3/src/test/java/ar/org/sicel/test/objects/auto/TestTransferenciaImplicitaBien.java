//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.Establecimiento;
//import ar.org.sicel.persistence.EstablecimientoDAO;
//import ar.org.sicel.persistence.EvtInfo;
//import ar.org.sicel.persistence.EvtInfoDAO;
//import ar.org.sicel.persistence.EvtTransferencia;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.Propietario;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import junit.framework.TestCase;
//
//public class TestTransferenciaImplicitaBien extends TestCase {
//	
//	public void testTransferenciaImplicita() throws Exception {
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//		Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1611306", true);
//		
//		Propietario antiguoProp = an1.getPropietario();
//		Establecimiento antiguoEst = an1.getEstablecimiento();
//		String antiguoRP = an1.getRP();
//		//animal 1463513
//		//establecimiento 8832
//		// propietario del est: 1025
//		
//		// establecimiento distinto (mismo prop) 14720
//		Establecimiento otro = EstablecimientoDAO.findByPrimaryKey(new Long(14720));
//		
//		List msgs = new LinkedList();
//		EvtInfo info = EvtInfoDAO.create(otro,an1,new Date(),"Esto es la nueva info","e",msgs); 
//		
//		Object object = info.getDependientes().first();
//		assertEquals(EvtTransferencia.class, object.getClass());
//		EvtTransferencia trans = (EvtTransferencia)object;
//		
//		assertEquals(antiguoEst,trans.getAntiguoEstab());
//		assertEquals(antiguoRP,trans.getAntiguoRP());
//		assertEquals(antiguoProp,trans.getAntiguoProp());
//		
//		assertEquals(otro,an1.getEstablecimiento());
//		assertEquals(antiguoRP,an1.getRP());
//		assertEquals(antiguoProp,an1.getPropietario());
//		
//		
//		
//		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//		
//	}
//
//}
