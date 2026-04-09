/*
 * Created on 13/04/2005
 */
package ar.org.sicel.proc.services.locator;

import ar.org.sicel.persistence.excepciones.ErrorFatal;

/**
 * Indica que no se pudo obtener el servicio pedido.
 * En general se trata de un error en la configuracion de JNDI si se esta usando el JBossServiceLocator
 * 
 * @author pablo
 */
public class ServiceNotFound extends ErrorFatal {
	static final long serialVersionUID = 1; //Sino es un warnning, porque por algun lado esto implementa Serializable  

	/**
	 * 
	 */
	public ServiceNotFound() {
		super();
	}

	/**
	 * @param message
	 */
	public ServiceNotFound(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceNotFound(Throwable cause) {
		super(cause);
	}

}
