package ar.org.sicel.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class. Is able to find and create objects of type AnimalSinonimo. Hibernate
 * inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.AnimalSinonimo
 */
public class AnimalSinonimoDAO {
	
	static Logger log = Logger.getLogger(AnimalSinonimoDAO.class);
	// ---------------- finder methods ----------------------

   
	public static List findByAnimal(Long xAnimal) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
    	Query query =null;
    	query = session.createQuery("from AnimalSinonimo as p where sinonimo = :idAnimal");
   		query.setParameter("idAnimal",xAnimal);
        return query.list();	
    }
	

}