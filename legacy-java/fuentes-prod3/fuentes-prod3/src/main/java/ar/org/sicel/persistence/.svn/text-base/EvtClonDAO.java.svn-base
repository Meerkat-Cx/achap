package ar.org.sicel.persistence;

import java.util.TreeSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtClon.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtClon
 */
public abstract class EvtClonDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtClon object.
     *
     * @param tejido
     * @param fecha
     * @return EvtClon the created object
     */
    @SuppressWarnings("unchecked")
	public static EvtClon create(java.lang.String tejido, java.util.Date fecha) {
        EvtClon object = new EvtClon();

        object.setTejido(tejido);
        object.setFecha(fecha);
        object.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtClon object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtClon findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtClon object = (EvtClon) session.get(EvtClon.class, id);

        return object;
    }
}
