package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ConjuntoAtributos.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ConjuntoAtributos
 */
public abstract class ConjuntoAtributosDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ConjuntoAtributos object.
     *
     * @param nombre
     * @return ConjuntoAtributos the created object
     */
    public static ConjuntoAtributos create(java.lang.String nombre) {
        ConjuntoAtributos object = new ConjuntoAtributos();

        object.setNombre(nombre);

        object.setAtributos(new HashSet());

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ConjuntoAtributos object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ConjuntoAtributos findByPrimaryKey( java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ConjuntoAtributos object = (ConjuntoAtributos) session.get(ConjuntoAtributos.class,
                id);

        return object;
    }

    // ********************************************************** //
    public static ConjuntoAtributos findByName(     java.lang.String nombre)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Query q = session.createQuery(
                "from ConjuntoAtributos ca where ca.nombre = :nombre");
        q.setString("nombre", nombre);

        ConjuntoAtributos object = (ConjuntoAtributos) q.uniqueResult();

        return object;
    }

    /**
    *
    * @return
    * @throws org.hibernate.HibernateException
    */
   public static List findAll() throws org.hibernate.HibernateException {
       return HibernateFactory.getSession().createCriteria(ConjuntoAtributos.class).list();
   }

}
