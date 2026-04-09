package ar.org.sicel.test.objects;

import java.util.Date;

import junit.framework.TestCase;
import ar.org.sicel.util.DateUtils;

public class TestDateUtils extends TestCase {
	
	@SuppressWarnings("deprecation")
	public void testDiasEntre() {
//		es año( menos 1900), mes (0..11), dia (1..n)
		Date dia1 = new Date(97,3,20);
		Date dia2 = new Date(98,3,30);
		int dias = DateUtils.diasEntre(dia1,dia2);
		System.out.println(dias);
		assertEquals(375,dias);
		Date unoEnero = new Date(98,0,1);
		Date diezFebrero =new Date(98,1,10);
		Date unoOct =new Date(98,9,1);
		
		assertEquals(40,DateUtils.diasEntre(unoEnero,diezFebrero));
		assertEquals(273,DateUtils.diasEntre(unoEnero,unoOct));
		
		Date dosEnero = new Date(98,0,2,23,11);
		Date dosEnero2 = new Date(98,0,2,11,12);
		assertTrue(DateUtils.mismoDia(dosEnero,dosEnero2));
		Date tresEnero = new Date(98,0,3,23,11);
		assertFalse(DateUtils.mismoDia(dosEnero,tresEnero));
		
		
	}
	
	

}
