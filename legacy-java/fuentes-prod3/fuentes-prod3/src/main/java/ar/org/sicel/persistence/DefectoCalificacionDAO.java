package ar.org.sicel.persistence;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type DefectoCalificacion.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.calificacion.DefectoCalificacion
 */
public abstract class DefectoCalificacionDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) DefectoCalificacion object.
     *
     * @param nombre
     * @return DefectoCalificacion the created object
     */
    public static DefectoCalificacion create(java.lang.String nombre) {
        DefectoCalificacion object = new DefectoCalificacion();

        object.setNombre(nombre);

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds DefectoCalificacion object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static DefectoCalificacion findByPrimaryKey( java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        DefectoCalificacion object = (DefectoCalificacion) session.get(DefectoCalificacion.class,
                id);

        return object;
    }
}
