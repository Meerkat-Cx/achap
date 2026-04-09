/**
 * Attention: Generated source! Do not modify by hand!
 */
package ar.org.sicel.persistence;

import java.util.HashSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProveeSmn.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProveeSmn
 */
public abstract class ProveeSmnDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ProveeSmn object.
     *
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return ProveeSmn the created object
     */
    public static ProveeSmn create(java.lang.String nombreContacto,
        java.lang.String comentario, byte[] foto) {
        ProveeSmn object = new ProveeSmn();

        object.setNombreContacto(nombreContacto);
        object.setComentario(comentario);
        //object.setFoto(foto);

        object.setEvtSemens(new HashSet());
        object.setUbicacions(new HashSet());

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProveeSmn object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProveeSmn findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ProveeSmn object = (ProveeSmn) session.get(ProveeSmn.class, id);

        return object;
    }
}
