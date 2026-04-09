/*
 * Created on 12/04/2005
 *
 */
package ar.org.sicel.proc.services.locator;

import ar.org.sicel.proc.services.IProcesadorAnimal;
import ar.org.sicel.proc.services.IProcesadorEstablecimiento;
import ar.org.sicel.proc.services.IProcesadorEventoAnimal;
import ar.org.sicel.proc.services.IProcesadorEventoEstablecimiento;
import ar.org.sicel.proc.services.IProcesadorLote;
import ar.org.sicel.proc.services.IProcesadorXML;

/**
 * Define una manera unica de obtener instancias de los servicios de Procesamiento.
 * Dependiendo de como se realize el deploy, estos se pueden generar y acceder de distinta
 * forma. 
 * Esta interfaz brinda un punto de acceso, de manera de no tener que utilizar formas especificas
 * si se cambia el deploy, la implementacion de los servicios,etc.
 * Ver JBossLocator, StandaloneLocator   
 *  
 * 
 * @author pablo
 */
public interface LocatorStrategy {

		
	public  IProcesadorLote getProcesadorLote() throws ServiceNotFound;
	
	public  IProcesadorEventoAnimal getProcesadorEventoAnimal() throws ServiceNotFound;
	
	public  IProcesadorEstablecimiento getProcesadorEstablecimiento() throws ServiceNotFound;
	
	public  IProcesadorAnimal getProcesadorAnimal() throws ServiceNotFound;

	public  IProcesadorEventoEstablecimiento getProcesadorEventoEstablecimiento() throws ServiceNotFound;

	public  IProcesadorXML getProcesadorXML() throws ServiceNotFound;
}
