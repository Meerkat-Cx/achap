/*
 * Created on 18/05/2005
 */
package ar.org.sicel.test.proc;

import junit.framework.Test;
import junit.framework.TestSuite;
import ar.org.sicel.test.old.EcloNoValida;
import ar.org.sicel.test.old.EstablecimientoNoEnEclo;
import ar.org.sicel.test.old.FormatoArchivo;

/**
 * @author ala
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ar.org.sicel.test.proc");
		//$JUnit-BEGIN$
		suite.addTestSuite(EstablecimientoNoEnEclo.class);
		suite.addTestSuite(FormatoArchivo.class);
		suite.addTestSuite(EcloNoValida.class);
		//$JUnit-END$
		return suite;
	}
}
