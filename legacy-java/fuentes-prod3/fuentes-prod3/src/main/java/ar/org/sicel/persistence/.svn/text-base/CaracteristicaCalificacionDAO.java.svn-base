package ar.org.sicel.persistence;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type CaracteristicaCalificacion.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.calificacion.CaracteristicaCalificacion
 */
public abstract class CaracteristicaCalificacionDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) CaracteristicaCalificacion object.
     *
     * @param nombre
     * @return CaracteristicaCalificacion the created object
     */
    public static CaracteristicaCalificacion create(java.lang.String nombre) {
        CaracteristicaCalificacion object = new CaracteristicaCalificacion();

        object.setNombre(nombre);

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds CaracteristicaCalificacion object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static CaracteristicaCalificacion findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        CaracteristicaCalificacion object = (CaracteristicaCalificacion) session.get(CaracteristicaCalificacion.class,
                id);

        return object;
    }
}
