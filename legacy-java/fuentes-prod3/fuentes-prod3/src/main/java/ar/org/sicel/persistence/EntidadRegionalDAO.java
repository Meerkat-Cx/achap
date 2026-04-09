package ar.org.sicel.persistence;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EntidadRegional.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EntidadRegional
 */
public abstract class EntidadRegionalDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EntidadRegional object.
     *
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return EntidadRegional the created object
     */
     public static EntidadRegional create (java.lang.String nombreContacto, java.lang.String comentario, byte[] foto)
     {
         EntidadRegional object = new EntidadRegional();

         object.setNombreContacto (nombreContacto);
         object.setComentario (comentario);
         
         if (foto != null) {
	         Foto imagen = FotoDAO.createPersistent(foto);
	         object.setFoto(imagen);
         }
         object.setAtrVariables(AtrVariablesDAO.create());
         return object;
     }
     
     /**
      * Persiste una entidad regional dada
      * @param entidadRegional
      * @throws HibernateException
      */
     public static void saveEntidadRegional(EntidadRegional entidadRegional)
 		throws HibernateException {
    	 HibernateFactory.getSession().save(entidadRegional);
     }
 	
 

     public static EntidadRegional createPersistent (java.lang.String nombreContacto, java.lang.String comentario) {
    	 EntidadRegional entidadRegional = EntidadRegionalDAO.create(nombreContacto, comentario, null);
    	 HibernateFactory.getSession().save(entidadRegional);
    	 return entidadRegional; 
     }
     
     
     
    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EntidadRegional object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EntidadRegional findByPrimaryKey(    java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EntidadRegional object = (EntidadRegional) session.get(EntidadRegional.class,
                id);

        return object;
    }

    public static List findAll() throws HibernateException {
    	Session session = HibernateFactory.getSession();
    	Query query = session.createQuery("from EntidadRegional as e order by e.nombreContacto");
    	return query.list();
    }
    
    
    public static Criteria getCriteria() {
		 Session session = HibernateFactory.getSession();
	     return session.createCriteria(EntidadRegional.class);
	}

	public static void updateEntidadRegional(EntidadRegional entidadRegional) 		 
	    	throws HibernateException {
	    	HibernateFactory.getSession().update(entidadRegional);
	}

	public static List findByNombreContacto(String nombreContacto) {
		Session session = HibernateFactory.getSession();		
		nombreContacto = "%" + nombreContacto.toUpperCase() + "%";
		Query query = session.createQuery("from EntidadRegional as r where upper(r.nombreContacto) like :nomContacto");
		query.setParameter("nomContacto",nombreContacto);
        return query.list();	
	}
}
