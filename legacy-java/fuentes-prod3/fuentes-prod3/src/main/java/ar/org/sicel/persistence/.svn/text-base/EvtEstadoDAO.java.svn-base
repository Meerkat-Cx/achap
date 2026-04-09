package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtEstado.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtEstado
 */
public abstract class EvtEstadoDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtEstado object.
     *
     * @param estado
     * @param cantLactTerminadas
     * @param fecha
     * @param fechaInicioUltimaLactancia 
     * @return EvtEstado the created object
     */
    public static EvtEstado create(Establecimiento est, Animal animal,java.lang.String estado,
        java.lang.Integer cantLactTerminadas, java.util.Date fecha, List msgs, 
        Date fechaInicioUltimaLactancia, Integer numeroLactancia) throws ExcepcionIntegridad {
        
    	EvtEstado object = new EvtEstado(est,fecha,animal,estado,cantLactTerminadas,msgs, fechaInicioUltimaLactancia,numeroLactancia);
    	
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtEstado object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtEstado findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtEstado object = (EvtEstado) session.get(EvtEstado.class, id);

        return object;
    }
    
    public static void updateEventoEstado(EvtEstado evtEstado) {
		HibernateFactory.getSession().update(evtEstado);
	}
    
}
