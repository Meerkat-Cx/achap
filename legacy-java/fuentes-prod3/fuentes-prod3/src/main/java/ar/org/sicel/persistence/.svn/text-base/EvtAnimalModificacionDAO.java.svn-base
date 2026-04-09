package ar.org.sicel.persistence;

import java.util.Date;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;



/**
 * 
 * @author jorona
 * 
 */
public class EvtAnimalModificacionDAO {

	
	
	/**
	 * Crea una instancia de la clase EvtAnimalModificacion
	 * @param est
	 * @param fecha
	 * @param eventoModificado
	 * @return
	 */
	public static EvtAnimalModificacion create(Establecimiento est, Date fecha, Animal animal, EvtAnimal eventoModificado) throws ExcepcionIntegridad {
		return new EvtAnimalModificacion(est, fecha, animal, eventoModificado);
	}
	
	public static void saveEvtAnimalModificacion(EvtAnimalModificacion eventoModificacionAnimal) throws HibernateException{		
		HibernateFactory.getSession().save(eventoModificacionAnimal);
	}
}
