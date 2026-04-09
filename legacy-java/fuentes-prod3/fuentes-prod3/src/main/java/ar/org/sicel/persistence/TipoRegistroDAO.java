package ar.org.sicel.persistence;

import java.util.List;

import org.apache.tools.ant.taskdefs.condition.Not;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.NotExpression;

import com.sun.org.apache.xpath.internal.operations.NotEquals;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type TipoRegistro.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.TipoRegistro
 */
public abstract class TipoRegistroDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) TipoRegistro object.
     *
     * @param descripcion
     * @param numero
     * @return TipoRegistro the created object
     */
    public static TipoRegistro create(String id,String descripcion) {
        TipoRegistro object = new TipoRegistro();
        object.setId(id);
        object.setDescripcion(descripcion);
        object.setNumero (0L);

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds TipoRegistro object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static TipoRegistro findByPrimaryKey(
         java.lang.String id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        TipoRegistro object = (TipoRegistro) session.get(TipoRegistro.class, id);

        return object;
    }
    public static List findTiposValidosPedigree() throws HibernateException{
		Session session = HibernateFactory.getSession();
        Criteria criterio = session.createCriteria(TipoRegistro.class);
      criterio.add(Expression.not(Expression.eq("id", "ARGP")));
      criterio.add(Expression.not(Expression.eq("id", "ARGT")));
      criterio.add(Expression.not(Expression.eq("id", "RC")));
      return criterio.list();
	}
    public static List findTiposValidosEditables() throws HibernateException{
		Session session = HibernateFactory.getSession();
        Criteria criterio = session.createCriteria(TipoRegistro.class);
      criterio.add(Expression.not(Expression.eq("id", "HBA")));
      criterio.add(Expression.not(Expression.eq("id", "RC")));
      return criterio.list();
	}
    
    public static List findAll(){
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from TipoRegistro");
        return query.list();		
	}
}
