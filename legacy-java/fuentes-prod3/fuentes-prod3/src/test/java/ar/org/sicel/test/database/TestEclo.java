/*
 * Created on 03/05/2005
 */
package ar.org.sicel.test.database;

import junit.framework.TestCase;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Sistema;
import ar.org.sicel.persistence.SistemaDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author pablo
 */
public class TestEclo extends TestCase {

	public void testEclo() throws HibernateException {
		StandaloneHibernateStrategy st = StandaloneHibernateStrategy.getInstance();
		st.openNewSession();
		
		Eclo eclo5 = EcloDAO.findByPrimaryKey(new Long(5));
		assertEquals("Asociacion la Vaca Contenta",eclo5.getNombreContacto());
		Sistema sist = SistemaDAO.findByName("SISECLO");
		assertTrue(eclo5.getSistemas().contains(sist));
		assertEquals(new Integer(28000001), eclo5.getResponsable().getDocumento());
		assertEquals("Regional1",eclo5.getRegional().getNombreContacto());
		assertEquals(0,eclo5.getEstablecimientos().size()); 
		Eclo eclo9 = EcloDAO.findByPrimaryKey(new Long(9));
		assertEquals(2,eclo9.getEstablecimientos().size());
		
		Eclo ecloNoExiste = EcloDAO.findByPrimaryKey(new Long(101010101));
		assertNull(ecloNoExiste);
		st.commitCurrentSession();
	}
}
