package ar.org.sicel.persistence;

import java.util.HashSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ParteCalificacion.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.calificacion.ParteCalificacion
 */
public abstract class ParteCalificacionDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ParteCalificacion object.
     *
     * @param codigo
     * @param nombre
     * @param esPunteable
     * @return ParteCalificacion the created object
     */
     public static ParteCalificacion create (java.lang.String codigo, java.lang.String nombre, java.lang.Boolean esPunteable)
     {
         ParteCalificacion object = new ParteCalificacion();

         object.setCodigo (codigo);
         object.setNombre (nombre);
         object.setEsPunteable (esPunteable);

        object.setCaracteristicaCalificacions(new HashSet());
        object.setDefectoCalificacions(new HashSet());

         return object;
     }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ParteCalificacion object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ParteCalificacion findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ParteCalificacion object = (ParteCalificacion) session.get(ParteCalificacion.class,
                id);

        return object;
    }
}
