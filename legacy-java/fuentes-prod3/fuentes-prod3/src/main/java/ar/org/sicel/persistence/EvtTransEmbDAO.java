package ar.org.sicel.persistence;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtTransEmb.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtTransEmb
 */
public abstract class EvtTransEmbDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtTransEmb object.
     *
     * @param diasGestacion
     * @param idEmbrion
     * @param tipoServ
     * @param fecha
     * @return EvtTransEmb the created object
     */
    public static EvtTransEmb create(java.lang.Integer diasGestacion,
        java.lang.String idEmbrion, java.lang.String tipoServ,
        java.util.Date fecha) {
        EvtTransEmb object = new EvtTransEmb();

        object.setDiasGestacion(diasGestacion);
        object.setIdEmbrion(idEmbrion);
        object.setTipoServ(tipoServ);
        object.setFecha(fecha);

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtTransEmb object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtTransEmb findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtTransEmb object = (EvtTransEmb) session.get(EvtTransEmb.class, id);

        return object;
    }
    
}
