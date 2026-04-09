package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Especie.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Especie
 */
public abstract class EspecieDAO {
	
		
	
    // ---------------- create method --------------------

    /**
     * Creates a(n) Especie object.
     *
     * @param nombre
     * @return Especie the created object
     */
    public static Especie create(String id, String nombre) {
        Especie object = new Especie();
        
        object.setNombre(nombre);
        object.setId(id);
        object.setRazas(new HashSet());
       
        
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Especie object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Especie findByPrimaryKey(
        java.lang.String id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Especie object = (Especie) session.get(Especie.class, id);

        return object;
    }

	public static Collection findAll() {
		Session session = HibernateFactory.getSession();
		return session.createQuery("from Especie").list();
	}

	public static List findNombre(String nombre){
		Session session = HibernateFactory.getSession();
        Criteria criterio = session.createCriteria(Especie.class);
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombre", "%"+nombre+"%").ignoreCase());
        return criterio.list();
	}
	public static List findNombreNotAtributos(Long atributoId, String nombreEspecie) throws HibernateException{
		Session session = HibernateFactory.getSession();
		List especiesWithAtribute = new ArrayList();
    	String	querya = "select esp.id from Especie esp, Valor v left join esp.atrVariables.valors val" +
		" where val.id = v.id and v.valorAdmAtr.atributo.id = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", atributoId, Hibernate.LONG);
	    especiesWithAtribute= query.list();
	    /*if(especiesWithAtribute.isEmpty())
	    	return (List) findAll();*/
		Criteria criterio = session.createCriteria(Especie.class);
		if(!especiesWithAtribute.isEmpty())
			criterio.add(Expression.not(Expression.in("id",especiesWithAtribute)));
        if (StringUtils.isNotEmpty(nombreEspecie))        	
        	criterio.add(Expression.like("nombre", "%"+nombreEspecie+"%").ignoreCase());
        return criterio.list();
	}

	public static Especie findAtrVariable(Long idAtrVarialbe) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        String	querya = "from Especie e where e.atrVariables = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", idAtrVarialbe, Hibernate.LONG);
        return (Especie) query.uniqueResult();
        /*Criteria criterio = session.createCriteria(Especie.class);
       	criterio.add(Expression.eq("atrVariables", Long.valueOf(idAtrVarialbe)));
       	return (Especie) criterio.uniqueResult();*/
	}}
