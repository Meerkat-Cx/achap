/*
 * Created on 25/06/2005
 */
package ar.org.sicel.test.util;

import junit.framework.TestCase;
/**
 * @author ala
 */
public class TestStringUtil extends TestCase {

	public void testFormatoArgsNombre() throws Exception {
		String formato="ejemplo de formato con args %s[na] con nombre";
		String formateado = ar.org.sicel.util.StringUtils.format(formato,new Object[]{"hola"});
		System.out.println(formato);
		System.out.println(formateado);
		assertEquals(formateado,"ejemplo de formato con args hola con nombre");		
	}

}
