/*
 * Created on 17/06/2005
 */
package ar.org.sicel.test.objects;

import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.EvtLactanciaMigrada;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author ala
 */
public class TestLactanciasAnimal extends TestCase {
	public void testLactancia() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Animal an1 = AnimalDAO.findByPrimaryKey(new Long(715755));
		System.out.println(an1.toString());
		Set lacts=an1.getAllEventos();
		for (Iterator iter = lacts.iterator(); iter.hasNext();) {
			EvtLactanciaMigrada lact = (EvtLactanciaMigrada) iter.next();
			System.out.println(lact.toString());
		}
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}

}
