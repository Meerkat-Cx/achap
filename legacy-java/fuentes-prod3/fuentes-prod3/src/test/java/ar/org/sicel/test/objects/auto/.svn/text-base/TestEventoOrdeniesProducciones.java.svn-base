package ar.org.sicel.test.objects.auto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.EvtControlEstablecimiento;
import ar.org.sicel.persistence.EvtControlEstablecimientoDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.v1.lote.Horarios;

public class TestEventoOrdeniesProducciones extends TestCase {
	
	@SuppressWarnings("deprecation")
	public void testOrdenie() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		
		List msgs = new LinkedList();
//		 es año( menos 1900), mes (0..11), dia (1..n)
		Date fecha = new Date(99,3,20);
		Establecimiento est = EstablecimientoDAO.findExistentByPrimaryKey(new Long(8801));
		
		EvtControlEstablecimiento or = EvtControlEstablecimientoDAO.create(est,fecha,"A4",true,"DOAN",true,2,msgs, new Horarios());
		HibernateFactory.getSession().save(or);
		
		Date mismaFecha = new Date(99,3,20);
		EvtControlEstablecimiento ordenie = EvtControlEstablecimientoDAO.findByFecha(est,mismaFecha);
		
		assertTrue(ordenie.getControlEsAM()	);
		assertTrue(ordenie.getMuestreoEsAM());
		assertEquals(new Integer(2),ordenie.getNumOrdenies());
		assertEquals("DOAN",ordenie.getTipoMuestreo());
		assertEquals("A4",ordenie.getMetodoControl());
		
		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
		
	}

	
	public void testProducciones() throws Exception {
		/*
		StandaloneHibernateStrategy.getInstance().openNewSession();
		
		List msgs = new LinkedList();
//		 es año( menos 1900), mes (0..11), dia (1..n)
		Date fecha = new Date(99,3,20);
		Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111", true);
		Establecimiento est = an1.getEstablecimiento();
		
		
		Date fechaAborto = new Date(99,3,15); //aborto largo, para que despues puedan venir producciones
		EvtCria crias[] = new EvtCria[1];
		crias[0] = EvtCriaDAO.create(null,	false,	false,null,	null,null,0,null);
	 	EvtReproduccion repr1 = EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fechaAborto,null,false,true,crias,msgs);
		msgs.clear();
		
		
		EvtControlEstablecimiento or = EvtControlEstablecimientoDAO.create(est,fecha,"A4",true,"DOAN",true,2,msgs);
		HibernateFactory.getSession().save(or);
		
				
		
		Date mismaFecha = new Date(99,3,20);
		EvtOrdenieAnimal prod = EvtOrdenieAnimalDAO.create(est,an1,mismaFecha,or,msgs);
		
		prod.addMed("GR",1.45f);
				
		assertTrue(prod.getOrdenie().getControlEsAM()	);
		assertTrue(prod.getOrdenie().getMuestreoEsAM());
		assertEquals(new Integer(2),prod.getOrdenie().getNumOrdenies());
		assertEquals("DOAN",prod.getOrdenie().getTipoMuestreo());
		assertEquals("A4",prod.getOrdenie().getMetodoControl());
		

		try {
			EvtOrdenieAnimal pr = EvtOrdenieAnimalDAO.create(est,an1,mismaFecha,null,msgs);
			fail("Produccion sin ordenie");
		} catch (ExcepcionIntegridad ee) {
			assertEquals(MENSAJES.PRODUCCION_SIN_ORDENIE,ee.getCodigoError());
		}

		
		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
		*/
	}
	

}
