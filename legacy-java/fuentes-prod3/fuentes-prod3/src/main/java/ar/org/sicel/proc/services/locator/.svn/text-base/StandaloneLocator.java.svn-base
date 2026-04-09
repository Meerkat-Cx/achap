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
import ar.org.sicel.proc.services.impl.ProcesadorAnimal;
import ar.org.sicel.proc.services.impl.ProcesadorEstablecimiento;
import ar.org.sicel.proc.services.impl.ProcesadorEventoAnimal;
import ar.org.sicel.proc.services.impl.ProcesadorEventoEstablecimiento;
import ar.org.sicel.proc.services.impl.ProcesadorLote;
import ar.org.sicel.proc.services.impl.ProcesadorXML;

/**
 * 
 * Devuelve servicios de procesamiento como objetos POJO.
 * Esta implementacion simplemente crea los objetos correspondientes ante cada
 * pedido y los devuelve.
 * 
 * @author pablo
 */
public class StandaloneLocator implements LocatorStrategy {
	
	private static StandaloneLocator instance;
	
	public static synchronized StandaloneLocator getInstance() {
		if (instance == null)
			instance = new StandaloneLocator();
		return instance;
	}
	
	private StandaloneLocator() {
		
	}
	
	
	/* (non-Javadoc)
	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorLote()
	 */
	public IProcesadorLote getProcesadorLote() {
		return new ProcesadorLote();
	}

	/* (non-Javadoc)
	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorEventoAnimal()
	 */
	public IProcesadorEventoAnimal getProcesadorEventoAnimal() {
		return new ProcesadorEventoAnimal();
	}

	/* (non-Javadoc)
	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorEstablecimiento()
	 */
	public IProcesadorEstablecimiento getProcesadorEstablecimiento() {
		return new ProcesadorEstablecimiento();
	}

	/* (non-Javadoc)
	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorAnimal()
	 */
	public IProcesadorAnimal getProcesadorAnimal() {
		return new ProcesadorAnimal();
	}

	/* (non-Javadoc)
	 * @see ar.org.sicel.proc.services.locator.LocatorStrategy#getProcesadorEventoEstablecimiento()
	 */
	public IProcesadorEventoEstablecimiento getProcesadorEventoEstablecimiento() {
		return new ProcesadorEventoEstablecimiento();
	}
	
	public  IProcesadorXML getProcesadorXML() {
		return new ProcesadorXML();
	}

}
