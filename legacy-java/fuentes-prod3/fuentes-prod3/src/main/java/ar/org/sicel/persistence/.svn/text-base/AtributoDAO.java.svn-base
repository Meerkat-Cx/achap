package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Atributo.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Atributo
 */
public abstract class AtributoDAO {
    // ---------------- create method --------------------

	public static Atributo create(java.lang.String nombre) {
        Atributo object = new Atributo();
        object.setNombre(nombre);
        object.setValoresAdmitidos(new HashSet());        
        return object;
    }
   
	
	
	
    public static Atributo create(ConjuntoAtributos conjunto, java.lang.String nombre) {
        Atributo object = new Atributo();
        object.setNombre(nombre);
        object.setValoresAdmitidos(new HashSet());
        conjunto.addAtributo(object);
        //object.setValorPorDefecto(ValorAdmAtrDAO.create(object, ""));
        return object;
    }
    
    public static Atributo createPersistent(ConjuntoAtributos conjunto, String nombre, String descripcion,
    		String valorPorDefecto) {
    	Atributo atributo = AtributoDAO.create(conjunto, nombre);
    	atributo.setDescripcion(descripcion);   	
    	
    	HibernateFactory.getSession().save(atributo);
    	
    	ValorAdmAtr valorAdmAtr = ValorAdmAtrDAO.create(atributo, valorPorDefecto);
    	atributo.setValorPorDefecto(valorAdmAtr);	
    	
    	return atributo; 	
    }
    

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Atributo object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Atributo findByPrimaryKey( java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	Atributo object = (Atributo) session.get(Atributo.class, id);

        return object;
    }

	/**
	 * @param nombreAt
	 * @return
	 */
	public static Atributo findByNombre(String nombreAt) throws HibernateException{
		Session session = HibernateFactory.getSession();
    	String queryText = "from Atributo a where a.nombre = :nombre";
    	Query query = session.createQuery(queryText);
    	query.setString("nombre",nombreAt);
    	return (Atributo)query.uniqueResult();
	}
	
	public static List findPorEntidad(String idEntidad) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        Criteria criterio = session.createCriteria(Atributo.class);
        if (StringUtils.isNotEmpty(idEntidad))
        	criterio.add(Expression.eq("conjuntoAt.id", Long.valueOf(idEntidad)));
        return criterio.list();
	}	
}
