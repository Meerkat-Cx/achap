package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type AtrVariables.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.AtrVariables
 */
public abstract class AtrVariablesDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) AtrVariables object.
     *
     * @return AtrVariables the created object
     */
    public static AtrVariables create() {
        AtrVariables object = new AtrVariables();

        object.setValors(new HashSet());
        
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds AtrVariables object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static AtrVariables findByPrimaryKey(java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        AtrVariables object = (AtrVariables) session.get(AtrVariables.class, id);

        return object;
    }
    
    public static List findAll() throws HibernateException {
    	Session session = HibernateFactory.getSession();
        Query query = session.createQuery("from AtrVariables");
        return query.list();
    }
    
}
