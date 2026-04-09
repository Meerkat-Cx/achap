package ar.org.sicel.persistence;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Persona.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Persona
 */
public abstract class PersonaDAO {
    // ---------------- create method --------------------

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Persona object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Persona findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Persona object = (Persona) session.get(Persona.class, id);

        return object;
    }
}
