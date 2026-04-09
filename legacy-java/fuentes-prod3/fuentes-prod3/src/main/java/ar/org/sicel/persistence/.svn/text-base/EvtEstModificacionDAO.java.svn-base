package ar.org.sicel.persistence;

import java.util.Date;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.util.HibernateFactory;



/**
 * 
 * @author jorona
 * 
 */
public class EvtEstModificacionDAO {

	
	
	/**
	 * Crea una instancia de la clase EvtEstModificacion
	 * @param est
	 * @param fecha
	 * @param eventoModificado
	 * @return
	 */
	public static EvtEstModificacion create(Establecimiento est, Date fecha, EvtEstablecimiento eventoModificado) {
		return new EvtEstModificacion(est, fecha, eventoModificado);
	}
	
	public static void saveEvtEstModificacion(EvtEstModificacion eventoModificacionEstablecimiento) throws HibernateException{		
		HibernateFactory.getSession().save(eventoModificacionEstablecimiento);
	}
}
