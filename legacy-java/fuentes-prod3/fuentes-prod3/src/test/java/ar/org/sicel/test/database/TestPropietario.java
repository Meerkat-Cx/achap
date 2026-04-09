/*
 * Created on 12/05/2005
 */
package ar.org.sicel.test.database;


import junit.framework.TestCase;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author pablo
 */
public class TestPropietario extends TestCase {
	
	
	public void testAnimalesPropietario() throws Exception {
		System.out.println("TestAnimalesPropietario");
		StandaloneHibernateStrategy st = StandaloneHibernateStrategy.getInstance();
//		Session session = st.openNewSession();
		Propietario prop = PropietarioDAO.findByPrimaryKey(new Long(1001));
        System.out.println("AnimalesPropietario size = " + prop.getAnimals().size() ) ;
		assertEquals(prop.getAnimals().size(),prop.getAnimals().size());
		st.commitCurrentSession();
	}
	
	public void testEstablecimientosPropietario() throws Exception {
		StandaloneHibernateStrategy st = StandaloneHibernateStrategy.getInstance();
//		Session session = st.openNewSession();
		Propietario prop = PropietarioDAO.findByPrimaryKey(new Long(1001));
		assertEquals(prop.getEstablecimientos().size(),prop.getEstablecimientos().size());
		st.commitCurrentSession();
	}
	
	
}
