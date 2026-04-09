//package ar.org.sicel.test.objects.auto;
//
//import java.util.Iterator;
//
//import junit.framework.Test;
//import junit.framework.TestSuite;
//import ar.org.sicel.persistence.Raza;
//import ar.org.sicel.persistence.RazaDAO;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//
//public class AllTests {
//
//	public static Test suite() {
//		TestSuite suite = new TestSuite(
//				"Test for ar.org.sicel.test.objects.auto");
//
//		//hack: ahora esta lazy, hasta sacar eso, esto va para obligar a hibernate a que lo inizialize
//		try {
//			StandaloneHibernateStrategy.getInstance().openNewSession();
//			/*	
//			 Iterator conjuntos = ConjuntoAtributosDAO.findAll().iterator();
//			 while (conjuntos.hasNext()) {
//			 ConjuntoAtributos conj = (ConjuntoAtributos)conjuntos.next();
//			 Iterator posibles = conj.getAtributos().iterator();
//			 while (posibles.hasNext()) { //todos los posibles atributos
//			 Atributo at = (Atributo) posibles.next();
//			 String valor = at.getValorPorDefecto().getValor();
//			 }
//			 }
//			 */
//			/*
//			Iterator it = RazaDAO.findAll().iterator();
//			while (it.hasNext()) {
//				Raza r = (Raza) it.next();
//				int edadMinima = r
//						.getParametroAsInteger(Raza.EDAD_MINIMA_MONTA);
//			}
//			*/
//			StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//fin hack
//
//		//$JUnit-BEGIN$
//		suite.addTestSuite(TestBajaAnimal.class);
//		suite.addTestSuite(TestLactanciaCerradaNuevo.class);
//		suite.addTestSuite(TestLactanciaAbiertaNuevo.class);
//		suite.addTestSuite(TestPropietarioAsignadoACrias.class);
//		suite.addTestSuite(TestEventoPrenez.class);
//		suite.addTestSuite(TestIntervaloEntrePartos.class);
//		suite.addTestSuite(TestTransferenciaImplicitaBien.class);
//		suite.addTestSuite(TestBajaEventos.class);
//		suite.addTestSuite(TestEventoEstado.class);
//		suite.addTestSuite(TestEventoOrdeniesProducciones.class);
//		suite.addTestSuite(TestEventoSecada.class);
//		suite.addTestSuite(TestEventoAlta.class);
//		suite.addTestSuite(TestEventosDependientesModificaciones.class);
//		suite.addTestSuite(TestTransferencia.class);
//		suite.addTestSuite(TestEstadoIntermedio.class);
//		//$JUnit-END$
//		return suite;
//	}
//
//}
