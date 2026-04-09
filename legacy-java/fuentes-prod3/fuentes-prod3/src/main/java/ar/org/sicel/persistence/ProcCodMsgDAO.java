/**
 * Attention: Generated source! Do not modify by hand!
 */
package ar.org.sicel.persistence;

import java.util.Collection;
import java.util.HashSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcCodMsg.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcCodMsg
 */
public abstract class ProcCodMsgDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ProcCodMsg object.
     *
     * @param descripcion
     * @param comentario
     * @return ProcCodMsg the created object
     */ 
    public static ProcCodMsg create(java.lang.String id, String descripcion, String comentario, String formato)
        {
        ProcCodMsg object = new ProcCodMsg();
        object.setId(id);
        object.setFormato(formato);
        object.setDescripcion(descripcion);
        object.setComentario(comentario);
        
        object.setProcMsgsses(new HashSet());

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcCodMsg object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProcCodMsg findByPrimaryKey(
         String id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ProcCodMsg object = (ProcCodMsg) session.get(ProcCodMsg.class, id);

        return object;
    }

	public static Collection findAll() {
		Session session = HibernateFactory.getSession();
		return session.createQuery("from ProcCodMsg order by id").list();
	}
}
