/*
 * Created on 18/05/2005
 */
package ar.org.sicel.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author pablo
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ar.org.sicel.test");
		//$JUnit-BEGIN$
		suite.addTest(ar.org.sicel.test.database.AllTests.suite());
		suite.addTest(ar.org.sicel.test.objects.AllTests.suite());
		suite.addTest(ar.org.sicel.test.proc.AllTests.suite());
		//$JUnit-END$
		return suite;
	}
}


