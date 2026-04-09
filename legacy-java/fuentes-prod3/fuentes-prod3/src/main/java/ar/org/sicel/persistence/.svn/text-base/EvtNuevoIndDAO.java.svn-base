package ar.org.sicel.persistence;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtNuevoInd.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtNuevoInd
 */
public abstract class EvtNuevoIndDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtNuevoInd object.
     *
     * @param tipoServ
     * @param fecha
     * @return EvtNuevoInd the created object
     */
    public static EvtNuevoInd create(java.lang.String tipoServ,
        java.util.Date fecha) {
        EvtNuevoInd object = new EvtNuevoInd();

        object.setTipoServ(tipoServ);
        object.setFecha(fecha);

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtNuevoInd object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtNuevoInd findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtNuevoInd object = (EvtNuevoInd) session.get(EvtNuevoInd.class, id);

        return object;
    }
}
