package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Raza.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Raza
 */
public abstract class RazaDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Raza object.
     *
     * @param nombre
     * @param esCruza
     * @param esDesconocido
     * @return Raza the created object
     */
     public static Raza create (java.lang.String nombre, java.lang.Boolean esCruza, java.lang.Boolean esDesconocido)
     {
         Raza object = new Raza();

         object.setNombre (nombre);
         object.setEsCruza (esCruza);
         object.setEsDesconocido (esDesconocido);
         

         return object;
     }
     
     public static Raza createPersistent (String id, java.lang.String nombre, java.lang.Boolean esCruza, java.lang.Boolean esDesconocido, Especie especie)
     {
         Raza raza = create(nombre, esCruza, esDesconocido);
         raza.setId(id);
         raza.setEspecie(especie);
         HibernateFactory.getSession().save(raza);
    	 return raza;
     }
     

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Raza object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Raza findByPrimaryKey(
        java.lang.String id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Raza object = (Raza) session.get(Raza.class, id);

        return object;
    }
    
    
    /**
	 * @return
	 */
	public static List findAll(){
		Session session = HibernateFactory.getSession();
		//Query query = session.createQuery("from Raza as r order by r.id asc");
		Query query = session.createQuery("from Raza order by idFicticio asc");
        return query.list();		
	}
	public static List findRazasPuras() throws HibernateException{
		Session session = HibernateFactory.getSession();
        Criteria criterio = session.createCriteria(Raza.class);
       criterio.add(Expression.eq("esCruza", new Boolean(false)));
       criterio.add(Expression.eq("esDesconocido", new Boolean(false)));
       criterio.addOrder(Order.asc("idFicticio"));
       return criterio.list();
	}
	
	public static void updateRaza(Raza raza) throws HibernateException {
		HibernateFactory.getSession().update(raza);	
	}

	
	public static List findNombreNotAtributos(Long atributoId, String nombreRaza) throws HibernateException{
		Session session = HibernateFactory.getSession();
		List razasWithAtribute = new ArrayList();
    	String	querya = "select raza.id from Raza raza, Valor v left join raza.atrVariables.valors val" +
		" where val.id = v.id and v.valorAdmAtr.atributo.id = :id";
		//"atrib.id = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", atributoId, Hibernate.LONG);
	    razasWithAtribute = query.list();
	    /*if(razasWithAtribute.isEmpty())
	    	return (List) findAll();*/
		Criteria criterio = session.createCriteria(Raza.class);
		if(!razasWithAtribute.isEmpty())
			criterio.add(Expression.not(Expression.in("id",razasWithAtribute)));
        if (StringUtils.isNotEmpty(nombreRaza))        	
        	criterio.add(Expression.like("nombre", "%"+nombreRaza+"%").ignoreCase());
        criterio.addOrder(Order.asc("idFicticio"));
        return criterio.list();
	}

	public static Raza findAtrVariable(Long idAtrVarialbe) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        String	querya = "from Raza r where r.atrVariables = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", idAtrVarialbe, Hibernate.LONG);
        return (Raza) query.uniqueResult();

        /*Criteria criterio = session.createCriteria(Raza.class);
       	criterio.add(Expression.eq("atrVariables", Long.valueOf(idAtrVarialbe)));
       	return (Raza) criterio.uniqueResult();*/
	}
	
    public static Integer findMayorIdFicticio() throws org.hibernate.HibernateException {
    	Query query = HibernateFactory.getSession().createQuery("select max(idFicticio) from Raza");
        return (Integer)query.uniqueResult();	
     }
}
