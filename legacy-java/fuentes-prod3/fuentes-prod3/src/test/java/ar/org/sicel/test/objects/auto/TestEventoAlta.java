package ar.org.sicel.test.objects.auto;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.EvtAlta;
import ar.org.sicel.persistence.EvtAltaDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

public class TestEventoAlta extends TestCase {
	
	@SuppressWarnings({"unchecked","unchecked", "deprecation"})
	public void testAltaConPadres() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		List msgs = new LinkedList();
		Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(new Long(8801));
//		 es año( menos 1900), mes (0..11), dia (1..n)
		Date fechaEvento = new Date(99,3,10);
		Date fechaNac = new Date(98,1,19);
		String rp = "104545";
		Map compRacial = new HashMap();
		Raza hola = RazaDAO.findByPrimaryKey("HOLA");
		Raza jy = RazaDAO.findByPrimaryKey("JY");
		compRacial.put(hola,0.75f);
		compRacial.put(jy,0.25f);
		
		//EvtAlta alta = EvtAltaDAO.create(estab,fechaEvento,null,true,rp,"HOLA",compRacial,"Prueba",fechaNac,msgs,null);
		
		Animal a = (Animal)AnimalDAO.findByRP(estab,rp).get(0);
		
		assertTrue(a.esHembra());
		//assertEquals(fechaEvento, alta.getFecha());
		assertEquals("Prueba",a.getNombre());
		assertEquals(fechaNac, a.getFechaNac());
		assertEquals(hola, a.getRaza());
		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void testAltaConComposicionRacial() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		List msgs = new LinkedList();
		Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(new Long(8801));
//		 es año( menos 1900), mes (0..11), dia (1..n)
		Date fechaEvento = new Date(99,3,10);
		Date fechaNac = new Date(98,1,19);
		String rp = "104545";
		
		Hembra madre = AnimalDAO.findExistentHembraByRegistry("RC", "1111","HOLA");
		EvtAltaDAO.create(estab,fechaEvento,null,false,rp,"JY",madre,null,"POP",fechaNac,msgs,null,null,true);
		
		Animal a = (Animal)AnimalDAO.findByRP(estab,rp).get(0);
		
		assertFalse(a.esHembra());
		assertEquals("JY",a.getRaza().getId());
		assertEquals(madre,a.getMadreGenetica());
		assertEquals(fechaNac,a.getFechaNac());
		
		
		StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
		
	}

}
