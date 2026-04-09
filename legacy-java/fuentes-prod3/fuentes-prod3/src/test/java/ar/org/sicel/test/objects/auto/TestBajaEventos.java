//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.Establecimiento;
//import ar.org.sicel.persistence.EstablecimientoDAO;
//import ar.org.sicel.persistence.EventoDAO;
//import ar.org.sicel.persistence.EvtAlta;
//import ar.org.sicel.persistence.EvtAltaDAO;
//import ar.org.sicel.persistence.EvtCria;
//import ar.org.sicel.persistence.EvtCriaDAO;
//import ar.org.sicel.persistence.EvtInfo;
//import ar.org.sicel.persistence.EvtInfoDAO;
//import ar.org.sicel.persistence.EvtReproduccion;
//import ar.org.sicel.persistence.EvtReproduccionDAO;
//import ar.org.sicel.persistence.EvtServicio;
//import ar.org.sicel.persistence.EvtServicioDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.Macho;
//import ar.org.sicel.persistence.Raza;
//import ar.org.sicel.persistence.RazaDAO;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import ar.org.sicel.proc.v1.lote.types.STTipoServicio;
//import ar.org.sicel.util.DateUtils;
//import junit.framework.TestCase;
//
//public class TestBajaEventos extends TestCase {
//	
//	public void testBajaEventos() throws Exception {
//		StandaloneHibernateStrategy.getInstance().openNewSession();
//		
//		
//		List msgs = new LinkedList();
//		
//		//***************** Alta de una hembra  **********************
//		Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(new Long(8801));
////		 es año( menos 1900), mes (0..11), dia (1..n)
//		Date fechaAlta = new Date(99,3,10);
//		Date fechaNac = new Date(95,1,19);
//		String rp = "104545";
//		Map compRacial = new HashMap();
//		Raza hola = RazaDAO.findByPrimaryKey("HOLA");
//		Raza jy = RazaDAO.findByPrimaryKey("JY");
//		compRacial.put(hola,0.75f);
//		compRacial.put(jy,0.25f);
//		EvtAlta alta = EvtAltaDAO.create(estab,fechaAlta,null,true,rp,"HOLA",compRacial,"Prueba",fechaNac,msgs);
//		Hembra hembra1 = (Hembra)alta.getAnimal();
//		StandaloneHibernateStrategy.getInstance().getCurrentSession().save(alta); 
//		
//		
//		//***************** Inseminacion     **********************
//		Macho padreGen = (Macho) AnimalDAO.findExistentByRegistry("HBA","200000", false);
//		Date fechaInseminacion = DateUtils.mas(fechaAlta,20);
//		EvtServicio serv1 = EvtServicioDAO.createNuevoIndividuoServSemen(hembra1.getEstablecimiento(),hembra1,STTipoServicio.INAR.toString(),fechaInseminacion,padreGen,msgs);
//		StandaloneHibernateStrategy.getInstance().getCurrentSession().save(serv1); 
//				
//		//***************  Info, para probar no borrar todos los eventos *************************
//		Date fechaInfo1 = DateUtils.mas(fechaInseminacion,3);
//		EvtInfo info = EvtInfoDAO.create(hembra1.getEstablecimiento(),hembra1,fechaInfo1,"Este es el 1","Esta ok",msgs);
//		StandaloneHibernateStrategy.getInstance().getCurrentSession().save(info);
//		info = null;		
//		
//		//***************  Info, para probar no borrar todos los eventos *************************
//		Date fechaInfo11 = DateUtils.mas(fechaInseminacion,4);
//		EvtInfo info11 = EvtInfoDAO.create(hembra1.getEstablecimiento(),hembra1,fechaInfo11,"Este es el 11","Esta ok",msgs);
//		StandaloneHibernateStrategy.getInstance().getCurrentSession().save(info11);
//		info11= null;		
//				
//		//***************** parto     **********************
//		 // es año( menos 1900), mes (0..11), dia (1..n)
//		 Date fechaParto = DateUtils.mas(fechaInseminacion,240+30+2);
//		 EvtCria crias[] = new EvtCria[1];
//		 crias[0] = EvtCriaDAO.create("Cria1",true,true,"565656","GR",45.5f,1,true);	
//	 	 EvtReproduccion repr1 = EvtReproduccionDAO.create(hembra1.getEstablecimiento(),hembra1,fechaParto,null,false,false, crias,msgs);
//	 	StandaloneHibernateStrategy.getInstance().getCurrentSession().save(repr1);
//	 	 
//	 	 EvtCria evtCria = (EvtCria) repr1.getEvtCrias().get(0);
//		 Animal cria = evtCria.getCria();  
//		 		 
//	 	 Date fechaInfo2 = DateUtils.mas(fechaParto,23);
//		 EvtInfo infoCria = EvtInfoDAO.create(cria.getEstablecimiento(),cria,fechaInfo2,"PEPEee","Esta ok",msgs);
//		 StandaloneHibernateStrategy.getInstance().getCurrentSession().save(infoCria);
//		 
//		 
//		
//		 
//		 //*********** borro la inseminacion y todos los eventos posteriores a ella ***/////
//		 
//		 serv1.darDeBaja();
//		 
//		 assertEquals(0, AnimalDAO.findByRP(estab , "565656").size()); //rp de la cria
//		 
//		 assertNotNull(infoCria.getId()); //guardo este evento
//		 assertNull(EventoDAO.findByPrimaryKey(infoCria.getId())); //pero ya lo elimino
//		 
//		 StandaloneHibernateStrategy.getInstance().flushCurrentSession();
//		 assertNull(EventoDAO.findByPrimaryKey(infoCria.getId())); //y no aparece tampoco despues de un flush de la session
//		 
//		 
//		 StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//		
//	}
//
//}
