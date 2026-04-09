//package ar.org.sicel.test.objects.auto;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import junit.framework.TestCase;
//import ar.org.sicel.persistence.Animal;
//import ar.org.sicel.persistence.AnimalDAO;
//import ar.org.sicel.persistence.EvtCria;
//import ar.org.sicel.persistence.EvtCriaDAO;
//import ar.org.sicel.persistence.EvtReproduccion;
//import ar.org.sicel.persistence.EvtReproduccionDAO;
//import ar.org.sicel.persistence.EvtServicio;
//import ar.org.sicel.persistence.EvtServicioDAO;
//import ar.org.sicel.persistence.Hembra;
//import ar.org.sicel.persistence.MENSAJES;
//import ar.org.sicel.persistence.Macho;
//import ar.org.sicel.persistence.ProcMsg;
//import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
//import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
//import ar.org.sicel.proc.v1.lote.types.STTipoServicio;
//import ar.org.sicel.util.DateUtils;
//
//public class TestIntervaloEntrePartos extends TestCase {
//	
//	
//		
//	public void testPartoMuyCercano() throws Exception{
//		 StandaloneHibernateStrategy.getInstance().openNewSession();
//
//		 Hembra an1 = (Hembra) AnimalDAO.findExistentByRegistry("RC", "1111",
//	                true);
//	       
//	     Macho padreGen = (Macho) AnimalDAO.findExistentByRegistry("HBA",
//	                "200000", false);
//     
//	     List msgs = new LinkedList();
//		 // es año( menos 1900), mes (0..11), dia (1..n)
//		 Date fecha1 = new Date(99,3,20);
//		 EvtCria crias[] = new EvtCria[1];
//		 crias[0] = EvtCriaDAO.create(null,	false,	false,null,	null,null,0,null);
//	 	 EvtReproduccion repr1 = EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fecha1,null,false,false, crias,msgs);
//		 msgs.clear();
//		 
//		 
//		 //------------Servicio en Periodo descanzo
//		 Date fecha2 = DateUtils.mas(fecha1,20);
//		 EvtServicio serv1 = EvtServicioDAO.createNuevoIndividuoServSemen(an1.getEstablecimiento(),an1,STTipoServicio.INAR.toString(),fecha2,padreGen,msgs);
//		 ProcMsg msg = (ProcMsg)msgs.get(0);
//		 assertEquals(MENSAJES.SERVICIO_EN_PERIODO_DESCANZO,msg.getCodigoMsg().getId());
//		 
//		 
//		 
//		 //------------Parto que no puede usar el servicio anterior
//		 // fecha1 + 20 = ultimo parto + gestacion Minima + periodod descanzo + un poquito mas
//		 Date fecha3 = DateUtils.mas(fecha1,20 + 240 +30 + 5); //nacimiento, el padre es desconocido, porque no se puede
//		 														//usar serv1 por estar dentro del periodo de descanzo
//		 EvtCria crias2[] = new EvtCria[1];
//		 crias2[0] = EvtCriaDAO.create("Cria2",true,true,"RPMIO2","GRANDE",4.5f,1,true);
//	 	 EvtReproduccion repr2 = EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fecha3,null,false,false, crias2,msgs);
//		 Animal cria = ((EvtCria)repr2.getEvtCrias().get(0)).getCria();
//		 assertNull(cria.getPadre());
//		 
//		 //--------------Parto demasiado cercano al anterior
//		 Date fecha4 = DateUtils.mas(fecha1,20 + 250 + 100); //segundo nacimiento, debe fallar porque no cumple el periodo minimo entre un evtReproduccion y un parto
//		 EvtCria crias3[] = new EvtCria[1];
//		 try {
//			 crias3[0] = EvtCriaDAO.create("Cria3",true,true,"RPMIO3","GRANDE",4.5f,1,true);
//			 EvtReproduccion repr3 = EvtReproduccionDAO.create(an1.getEstablecimiento(),an1,fecha4,null,false,false, crias3,msgs);
//			 fail("Se acepto un EvtReproduccion que no respeta el periodo minimo entre partos");
//		 } catch (ExcepcionIntegridad ei) {
//			 assertEquals(MENSAJES.PERIODO_PARTOS_INVALIDO,ei.getCodigoError());
//		 }
//		 
//		 
//		 
//
//		 StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
//	}
//	
//		
//
//}
