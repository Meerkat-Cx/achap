package ar.org.sicel.persistence;

import java.util.TreeSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtSemen.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtSemen
 */
public abstract class EvtSemenDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtSemen object.
     *
     * @param cantDosis
     * @param fecha
     * @return EvtSemen the created object
     */
    @SuppressWarnings("unchecked")
	public static EvtSemen create(java.lang.Integer cantDosis,
        java.util.Date fecha) {
        EvtSemen object = new EvtSemen();

        object.setCantDosis(cantDosis);
        object.setFecha(fecha);
        object.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtSemen object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtSemen findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtSemen object = (EvtSemen) session.get(EvtSemen.class, id);

        return object;
    }
}
