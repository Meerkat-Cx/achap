/*
 * Created on 18/05/2005
 */
package ar.org.sicel.test.objects;

import junit.framework.Test;
import junit.framework.TestSuite;
import ar.org.sicel.test.util.TestTipoRegistroUtil;

/**
 * @author pablo
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ar.org.sicel.test.objects");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestTipoRegistroUtil.class);
		//$JUnit-END$
		return suite;
	}
}
