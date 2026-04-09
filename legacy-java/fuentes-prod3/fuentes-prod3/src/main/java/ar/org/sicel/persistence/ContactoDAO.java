package ar.org.sicel.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Contacto.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Contacto
 */
public abstract class ContactoDAO {
    // ---------------- create method --------------------

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Contacto object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Contacto findByPrimaryKey( java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	Contacto object = (Contacto) session.get(Contacto.class, id);

        return object;
    }
    
    /**
     * Busca por "similitud" , por ejemplo "Los Alamos" busca "%Los%Alamos%" y sin
     * importar mayusculas/minusculas 
     * @param name
     * @return
     */
    public static List findBySimilar(String name) {
    	String pattern = "%" + name.toUpperCase().replace(" ","%") + "%"; 
    	String queryText = "from Contacto c where upper(c.nombreContacto) like :pattern";
    	Query query = HibernateFactory.getSession().createQuery(queryText);
    	query.setString("pattern",pattern);
    	return query.list();
    }
    
	public static void updateContacto(Contacto contacto) 		 
	throws HibernateException {
		HibernateFactory.getSession().update(contacto);
	}

    public static Collection findAll(){
        Session session = HibernateFactory.getSession();
        Query query = session.createQuery("from Contacto");
        return query.list();	
    }
    
}
