//package ar.org.sicel.test.old;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.PrintWriter;
//import java.rmi.RemoteException;
//
//import javax.ejb.CreateException;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import org.exolab.castor.xml.MarshalException;
//import org.exolab.castor.xml.ValidationException;
//
//import ar.org.sicel.proc.ejb.interfaces.SicelFacade;
//import ar.org.sicel.proc.ejb.interfaces.SicelFacadeHome;
//import ar.org.sicel.proc.v1.lote.Lote;
//
///*
// * Created on 14/04/2005
// */
//
///**
// * @author pablo
// */
//public class TestJBoss {
//
//	public static void main(String[] args) {
//		try {
//			InitialContext ctx = new InitialContext();
//			SicelFacadeHome sicelH = (SicelFacadeHome) ctx.lookup(SicelFacadeHome.JNDI_NAME);
//			SicelFacade sicel = sicelH.create();
//			File f = new File("resources/sicel/instance.xml");
//			Lote lote = (Lote)Lote.unmarshal(new FileReader(f));
//			lote = sicel.procesarLote(lote);
//			lote.marshal(new PrintWriter(System.out));
//		} catch (MarshalException e) {
//			e.printStackTrace();
//		} catch (ValidationException e) {
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (CreateException e) {
//			e.printStackTrace();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//}
