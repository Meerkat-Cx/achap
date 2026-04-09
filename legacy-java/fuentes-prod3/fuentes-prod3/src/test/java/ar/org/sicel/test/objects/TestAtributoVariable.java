/*
 * Created on 03/06/2005
 */
package ar.org.sicel.test.objects;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.Especie;
import ar.org.sicel.persistence.EspecieDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.ValorAdmAtr;
import ar.org.sicel.persistence.ValorAdmAtrDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author pablo
 */
public class TestAtributoVariable extends TestCase {


	public void testGetValorEspecie() throws Exception {
		//Especie atr 9  15-20-25
		
		StandaloneHibernateStrategy.getInstance().openNewSession();
		
		Especie bovi = EspecieDAO.findByPrimaryKey("BOVI");
		
		
		Atributo criasXParto = AtributoDAO.findByPrimaryKey(new Long(9));
		ValorAdmAtr c15 = ValorAdmAtrDAO.findByPrimaryKey(new Long(3));
		ValorAdmAtr c20 = ValorAdmAtrDAO.findByPrimaryKey(new Long(4));
		ValorAdmAtr c25 = ValorAdmAtrDAO.findByPrimaryKey(new Long(5));
		
		//Antes del test no tiene que estar seteado este atributo, asi
		//se prueba que el valor por defecto ande
		assertEquals(c15.getValor(), bovi.getParametro(criasXParto.getNombre()));
		
		bovi.getAtrVariables().setValor(criasXParto,c20);
		assertEquals(c20.getValor(), bovi.getAtrVariables().getValor(criasXParto).getValor());
		
		bovi.getAtrVariables().setValor(criasXParto,c25);
		assertEquals(c25.getValor(), bovi.getAtrVariables().getValor(criasXParto).getValor());
		
		
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();		
	}

	
	
	
	public void testConexionConViejos() throws Exception{
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Establecimiento est = EstablecimientoDAO.findByPrimaryKey(new Long(8801));
		System.out.println(est);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
	
	@SuppressWarnings("deprecation")
	public void testConexionConNuevos() throws Exception{
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Establecimiento est = EstablecimientoDAO.findByPrimaryKey(new Long(13));
		//es año( menos 1900), mes (0..11), dia (1..n)
		assertEquals("0", est.getAtrVariablesEstab().getValor("Tambo activo",new java.sql.Date(97,3,20)).getValor());
		assertEquals("1", est.getAtrVariablesEstab().getValor("Tambo activo",new java.sql.Date(99,0,1)).getValor());
		assertEquals("0", est.getAtrVariablesEstab().getValor("Tambo activo",new java.sql.Date(104,4,1)).getValor());
		assertEquals("1",est.getAtrVariablesEstab().getValor("Tambo activo",new java.sql.Date(105,0,1)).getValor());
		System.out.println(est);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
	

	
	
	
}
