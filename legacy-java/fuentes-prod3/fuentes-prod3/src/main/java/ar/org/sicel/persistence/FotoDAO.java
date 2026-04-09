/**
 * 
 */
package ar.org.sicel.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * @author Ramiro Trachsel
 *
 */
public abstract class FotoDAO {
	
//	 ---------------- create method --------------------
	
	public static Foto create (byte[] contenido) {
		Foto object = new Foto();
		object.setContenido(contenido);
		
		return object;
	}
	
	public static Foto createPersistent (byte[] contenido) {
		Foto object = FotoDAO.create(contenido);
		HibernateFactory.getSession().save(object);
		
		return object;
	}
	
	
//	 ---------------- finder methods  ----------------------
	
	/**
    *
    * Finds a Photo object by its primary key.
    * In Hibernate, this is just a call to get().
    *
    */
   public static Foto findByPrimaryKey(
       java.lang.Long id) throws org.hibernate.HibernateException {
   	Session session = HibernateFactory.getSession();
       Foto object = (Foto) session.get(Foto.class, id);

       return object;
   }

	/**
	 * @return
	 */
	public static List findAll() throws HibernateException{
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Foto");
       return query.list();		
	}

	public static void deleteFoto(Foto foto) throws HibernateException {
		HibernateFactory.getSession().delete(foto);		
	}
	
	
	
	

}
