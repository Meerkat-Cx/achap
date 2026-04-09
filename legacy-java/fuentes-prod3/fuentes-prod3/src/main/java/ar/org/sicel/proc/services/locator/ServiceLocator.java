/*
 * Created on 12/04/2005
 */
package ar.org.sicel.proc.services.locator;

import ar.org.sicel.proc.services.IProcesadorAnimal;
import ar.org.sicel.proc.services.IProcesadorEstablecimiento;
import ar.org.sicel.proc.services.IProcesadorEventoAnimal;
import ar.org.sicel.proc.services.IProcesadorEventoEstablecimiento;
import ar.org.sicel.proc.services.IProcesadorLote;
import ar.org.sicel.proc.services.IProcesadorXML;

/**
 * @author pablo
 */
public class ServiceLocator {
	//private static Logger log = Logger.getLogger(ServiceLocator.class); 
	
	private static LocatorStrategy locator;
	
	private static void buildLocator() {
		locator = StandaloneLocator.getInstance();
//		try {
//			if (Sicel3Conf.getConf().getConfAmbiente().getAttribute("environment").equals("standalone"))
//				locator = StandaloneLocator.getInstance();
//			else
//				locator = JBossLocator.getInstance();
//		} catch (ConfigurationException e) {
//			log.error("Se debe especificar el atributo booleano j2ee en el elemento ambiente de la configuracion de sicel3",e);
//		}
	}
	
	public static IProcesadorEventoEstablecimiento	getProcesadorEventoEstablecimiento() throws ServiceNotFound{
		if (locator == null)
			buildLocator();
		return locator.getProcesadorEventoEstablecimiento();
	}
	
	public static IProcesadorLote getProcesadorLote() throws ServiceNotFound{
		if (locator == null)
			buildLocator();
		return locator.getProcesadorLote();
	}
	
	public static IProcesadorEventoAnimal getProcesadorEventoAnimal() throws ServiceNotFound{
		if (locator == null)
			buildLocator();
		return locator.getProcesadorEventoAnimal();
	}
	
	public static IProcesadorEstablecimiento getProcesadorEstablecimiento()  throws ServiceNotFound{
		if (locator == null)
			buildLocator();
		return locator.getProcesadorEstablecimiento();
	}
	
	public  static IProcesadorAnimal getProcesadorAnimal()  throws ServiceNotFound{
		if (locator == null)
			buildLocator();
		return locator.getProcesadorAnimal();
	}
	
	public static IProcesadorXML getProcesadorXML() throws ServiceNotFound {
		if (locator == null)
			buildLocator();
		return locator.getProcesadorXML();
	}
}
