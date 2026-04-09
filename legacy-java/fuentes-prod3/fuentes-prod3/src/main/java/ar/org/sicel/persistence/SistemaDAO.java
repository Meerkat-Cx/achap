package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Sistema.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Sistema
 */
public abstract class SistemaDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Sistema object.
     *
     * @param nombre
     * @param version
     * @param comentario
     * @return Sistema the created object
     */
     public static Sistema create (java.lang.String nombre, java.lang.String version, java.lang.String comentario)
     {
         Sistema object = new Sistema();

         object.setNombre (nombre);
         object.setVersion (version);
         object.setComentario (comentario);

        object.setEclos(new HashSet());
        object.setProcLotes(new HashSet());
        
        
         return object;
     }

     public static Sistema createPersistent (String nombre, String version, String comentario, 
    		 ResponsableSistema responsableSistema) {
    	 
    	 Sistema sistema = SistemaDAO.create(nombre, version, comentario);
    	 sistema.setResponsable(responsableSistema);
    	 
    	 HibernateFactory.getSession().save(sistema);
    	 responsableSistema.setSistema(sistema);
    	 HibernateFactory.getSession().save(responsableSistema);
    	 return sistema;
    	 
     }
    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Sistema object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Sistema findByPrimaryKey(
        Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Sistema object = (Sistema) session.get(Sistema.class, id);

        return object;
    }

    
    public static Sistema findByName(
            String nombre) throws org.hibernate.HibernateException {
        	Session session = HibernateFactory.getSession();
            Criteria criteria = session.createCriteria(Sistema.class);
            criteria.add(Expression.eq("nombre",nombre).ignoreCase());
            Sistema sistema = (Sistema)criteria.uniqueResult();
            return sistema;
    }
    
    public static List findAll() throws HibernateException {
    	Session session = HibernateFactory.getSession();
    	Query query = session.createQuery("from Sistema as s order by s.nombre asc");
    	return query.list();
    }
    
}
