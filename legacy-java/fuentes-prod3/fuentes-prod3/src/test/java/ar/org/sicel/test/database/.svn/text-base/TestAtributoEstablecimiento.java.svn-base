package ar.org.sicel.test.database;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;




/**
 * @author ala
 */
public class TestAtributoEstablecimiento extends TestCase {



	public void testAttributoVariable() throws Exception{
		StandaloneHibernateStrategy st = StandaloneHibernateStrategy.getInstance();
		//Session session = st.openNewSession();
       // AtrVariables atr = AtrVariablesDAO.findByPrimaryKey(session,new Long(1));
       // Set valores = atr.getValors();
      //  assertEquals(2,valores.size());
		Establecimiento  es = EstablecimientoDAO.findByPrimaryKey(new Long(15));
        assertEquals(2,es.getAtrVariablesEstab().getValors().size());

		st.commitCurrentSession();
	}



}
