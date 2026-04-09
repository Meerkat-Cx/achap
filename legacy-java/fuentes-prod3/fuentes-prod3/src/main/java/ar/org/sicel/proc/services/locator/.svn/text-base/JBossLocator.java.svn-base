///*
// * Created on 12/04/2005
// */
//package ar.org.sicel.proc.services.locator;
//
//import javax.ejb.CreateException;
//import javax.naming.NamingException;
//
//import ar.org.sicel.proc.ejb.utils.ProcesadorAnimalesUtil;
//import ar.org.sicel.proc.ejb.utils.ProcesadorEstablecimientosUtil;
//import ar.org.sicel.proc.ejb.utils.ProcesadorEventoAnimalUtil;
//import ar.org.sicel.proc.ejb.utils.ProcesadorEventoEstablecimientoUtil;
//import ar.org.sicel.proc.ejb.utils.ProcesadorLotesUtil;
//import ar.org.sicel.proc.ejb.utils.ProcesadorXMLUtil;
//import ar.org.sicel.proc.services.IProcesadorAnimal;
//import ar.org.sicel.proc.services.IProcesadorEstablecimiento;
//import ar.org.sicel.proc.services.IProcesadorEventoAnimal;
//import ar.org.sicel.proc.services.IProcesadorEventoEstablecimiento;
//import ar.org.sicel.proc.services.IProcesadorLote;
//import ar.org.sicel.proc.services.IProcesadorXML;
//
///**
// * Devuelve servicios de procesamiento deployados como EJB(Stateless Session Beans) en un contenedor.
// * En esta implementacion, se accede a los servicios por medio de JNDI, el manejo del ciclo de vida
// * de los servicios (instanciacion, etc) se deja en manos del contenedor.
// * 
// * @author pablo
// */
//public class JBossLocator implements LocatorStrategy {
//	
//	private static JBossLocator instance;
//	
//	public static synchronized JBossLocator getInstance() {
//		if (instance == null)
//			instance = new JBossLocator();
//		return instance;
//	}
//	
//	private JBossLocator() {
//		
//	}
//	
//	
//	/* (non-Javadoc)
//	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorLote()
//	 */
//	public IProcesadorLote getProcesadorLote() throws ServiceNotFound{
//		try {
//			return ProcesadorLotesUtil.getLocalHome().create();
//		} catch (Exception e) {
//			throw new ServiceNotFound("No se pudo devolver el servicio", e);
//		}
//	}
//	
//	/* (non-Javadoc)
//	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorEventoAnimal()
//	 */
//	public IProcesadorEventoAnimal getProcesadorEventoAnimal()  throws ServiceNotFound{
//		try {
//			return ProcesadorEventoAnimalUtil.getLocalHome().create();
//		} catch (Exception e) {
//			throw new ServiceNotFound("No se pudo devolver el servicio", e);
//		}
//	}
//	
//	/* (non-Javadoc)
//	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorEstablecimiento()
//	 */
//	public IProcesadorEstablecimiento getProcesadorEstablecimiento()  throws ServiceNotFound {
//		
//		try{
//			return ProcesadorEstablecimientosUtil.getLocalHome().create();
//		} catch (Exception e) {
//			throw new ServiceNotFound("No se pudo devolver el servicio", e);
//		}
//	}
//	
//	/* (non-Javadoc)
//	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorAnimal()
//	 */
//	public IProcesadorAnimal getProcesadorAnimal()  throws ServiceNotFound{
//		try {
//			return ProcesadorAnimalesUtil.getLocalHome().create();
//		} catch (Exception e) {
//			throw new ServiceNotFound("No se pudo devolver el servicio", e);
//		}
//	}
//	
//	/* (non-Javadoc)
//	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorEventoEstablecimiento()
//	 */
//	public IProcesadorEventoEstablecimiento getProcesadorEventoEstablecimiento()  throws ServiceNotFound {
//		try {
//			return ProcesadorEventoEstablecimientoUtil.getLocalHome().create();
//		} catch (Exception e) {
//			throw new ServiceNotFound("No se pudo devolver el servicio", e);
//		}
//	}
//	
//	
//	
//	/* (non-Javadoc)
//	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorXML()
//	 */
//	public IProcesadorXML getProcesadorXML() throws ServiceNotFound {
//		try {
//			return ProcesadorXMLUtil.getLocalHome().create();
//		} catch (CreateException e) {
//			throw new ServiceNotFound("No se pudo encontrar el servicio",e);
//		} catch (NamingException e) {
//			throw new ServiceNotFound("No se pudo encontrar el servicio",e);
//		}
//	}
//}
