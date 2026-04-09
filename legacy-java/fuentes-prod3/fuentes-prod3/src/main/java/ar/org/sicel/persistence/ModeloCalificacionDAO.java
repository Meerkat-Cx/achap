package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.web.Tokens;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ModeloCalificacion.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.calificacion.ModeloCalificacion
 */
public abstract class ModeloCalificacionDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ModeloCalificacion object.
     *
     * @param fecha
     * @param esHembra
     * @return ModeloCalificacion the created object
     */
    public static ModeloCalificacion create(java.util.Date fecha,
        java.lang.Boolean esHembra) {
        ModeloCalificacion object = new ModeloCalificacion();

        object.setFecha(fecha);
        object.setEsHembra(esHembra);

        object.setParteCalificacions(new HashSet());

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ModeloCalificacion object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ModeloCalificacion findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	ModeloCalificacion object = (ModeloCalificacion) session.get(ModeloCalificacion.class,
                id);

        return object;
    }
    public static List findAll(Usuario user) throws HibernateException{
		Session session = HibernateFactory.getSession();
		Query query;
		if (user.getRol().getNombre().equals(Tokens.NOMBREROLSRA))
			query = session.createQuery("from ModeloCalificacion where id != 98");//hardcodeo por id
		else
			query = session.createQuery("from ModeloCalificacion");
        return query.list();		
	}
}
