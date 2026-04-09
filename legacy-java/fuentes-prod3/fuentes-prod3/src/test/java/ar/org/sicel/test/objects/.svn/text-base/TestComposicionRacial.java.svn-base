/*
 * Created on 30/05/2005
 */
package ar.org.sicel.test.objects;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.ComposicionRacial;
import ar.org.sicel.persistence.Especie;
import ar.org.sicel.persistence.EspecieDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author ala
 */
public class TestComposicionRacial extends TestCase {
	
	public void testCalculoHijo() throws Exception{
		StandaloneHibernateStrategy.getInstance().openNewSession();
		
		Animal an1 = AnimalDAO.findByPrimaryKey(new Long(1770454)); //100% holando
		Animal an2 = AnimalDAO.findByPrimaryKey(new Long(64070)); //0.5 h 0.5 desc
		//64724 (RC 1112) p 1770454 m 64070
		Especie especie = EspecieDAO.findByPrimaryKey("BOVI");
		ComposicionRacial hijo = ComposicionRacial.nuevaInstancia(especie.getDesconocida());
		hijo.recalcular(especie,an1.getComposicionRacial(),an2.getComposicionRacial());
		
		Float valor = (Float)hijo.getInmutableRazas().get(especie.getDesconocida());
		assertEquals(new Float(0.25),valor);
		Raza holando = (Raza) RazaDAO.findByPrimaryKey("HOLA"); 
		Float valor2 = (Float)hijo.getInmutableRazas().get(holando);
		assertEquals(new Float(0.75),valor2);

		System.out.println(an1.getComposicionRacial());
		System.out.println(an2.getComposicionRacial());
		System.out.println(hijo);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
	
	
	public void testPadresNULL() throws Exception{
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Especie especie = EspecieDAO.findByPrimaryKey("BOVI");
		ComposicionRacial hijo = ComposicionRacial.nuevaInstancia(especie.getCruza());
		hijo.recalcular(especie,null,null);
		Float valor = (Float)hijo.getInmutableRazas().get(especie.getDesconocida());
		assertEquals(new Float(1.0),valor);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
	
	/*
	public void testNuevoHijo() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Hembra madre = (Hembra)AnimalDAO.findByPrimaryKey(new Long(1));
		Macho padre = (Macho)AnimalDAO.findByPrimaryKey(new Long(3));
		
		Hembra hija = HembraDAO.create("HAR1" , "gtft", "epepe",padre.getEspecie(),  madre, padre); 
		System.out.println("Padre:" + padre.getComposicionRacial());
		System.out.println("Madre:" + madre.getComposicionRacial());
		System.out.println("***");
		System.out.println(hija.getComposicionRacial());
		
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
	
	
	
	public void testCambiosCascada() throws Exception {
		/*1 es daclarada HOLA, pero sin padres ni madres conocidos
		 2 es hija de 1 y de padre desconocido, declarada HOLA
		 3 es un toro de padres desconocidos, declarado DEBO
		 4 es hija de 2 y 3, declarada CRBO 
		
		StandaloneHibernateStrategy.getInstance().openNewSession();
		
		Animal an1 = AnimalDAO.findByPrimaryKey(new Long(1));
		Macho padre = (Macho)AnimalDAO.findByPrimaryKey(new Long(3));
		an1.cambiarPadreGen(padre);
		System.out.println("Composicion racial de 1:" + an1.getComposicionRacial());
		Animal an2 = AnimalDAO.findByPrimaryKey(new Long(2));
		System.out.println("Composicion racial de 2:" + an2.getComposicionRacial());
		Animal an4 = AnimalDAO.findByPrimaryKey(new Long(4));
		System.out.println("Composicion racial de 4:" + an4.getComposicionRacial());
		
		//Especie especie = EspecieDAO.findByPrimaryKey("BOVI");
		
		
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		
	}
	*/
	
	
	@SuppressWarnings({"unchecked","unchecked", "unchecked", "unchecked"})
	public void testRazaCalculada() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Raza hola = RazaDAO.findByPrimaryKey("HOLA");
		Raza debo = RazaDAO.findByPrimaryKey("DEBO");
		Raza crbo = RazaDAO.findByPrimaryKey("CRBO");
		Map mapa = new HashMap();
		Float unoEn32 = new Float(1.0/32.0);
		Float tEn32 = new Float(31.0/32.0);
		mapa.put(hola,unoEn32);
		mapa.put(debo,tEn32);
		ComposicionRacial comp = ComposicionRacial.nuevaInstancia(hola);
		comp.setearComposicionRacial(mapa);
		System.out.println(comp);
		assertEquals(crbo,comp.getRazaCalculada());
		
		
		mapa = new HashMap();
		mapa.put(debo,unoEn32);
		mapa.put(hola,tEn32);
		comp = ComposicionRacial.nuevaInstancia(hola);
		comp.setearComposicionRacial(mapa);
		System.out.println(comp);
		assertEquals(hola,comp.getRazaCalculada());
		
		
		
		Animal an1 = AnimalDAO.findByPrimaryKey(new Long(1770454)); //100% holando
		System.out.println(an1.getComposicionRacial());
		assertEquals(hola,an1.getComposicionRacial().getRazaCalculada());
		
		Animal an2 = AnimalDAO.findByPrimaryKey(new Long(64070));
		System.out.println(an2.getComposicionRacial());
		assertEquals(crbo,an2.getComposicionRacial().getRazaCalculada());

		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}

}

