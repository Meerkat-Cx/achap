/*
 * Created on 01/06/2005
 */
package ar.org.sicel.test.objects;

import java.util.LinkedList;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author ala
*/
public class TestUnicidadRP extends TestCase {

	public void testRPUnico() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();

		Animal an1 = AnimalDAO.findByPrimaryKey(new Long(1770454));

		// test1 un rp q existe

		try {
			Animal.checkUnicidadRPEnEstab(an1.getEstablecimiento(), an1
							.getRP(), new LinkedList<ProcMsg>(), an1.getFechaNac(),an1.getEstablecimiento(),an1.getRegistroOrigen());
			fail();
		} catch (ExcepcionIntegridad e) {
			assertEquals(e.getCodigoError(), MENSAJES.RP_NO_UNICO);
			assertEquals(e.getValores()[0], an1.getRegistroID());
		}

		// test 2 un rp q no existe

		try {
			Animal
					.checkUnicidadRPEnEstab(an1.getEstablecimiento(), "XX"+an1
							.getRP(), new LinkedList<ProcMsg>(), an1.getFechaNac(),an1.getEstablecimiento(),an1.getRegistroOrigen());
		} catch (ExcepcionIntegridad e) {
			fail();
		}

		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}

}
