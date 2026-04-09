/*
 * Created on 08/06/2005
 *
 */
package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Collection;
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
 * @author pablo
 *
 */
public class ConfiguracionDAO {
	

    // ---------------- finder methods  ----------------------
    public static Configuracion findByPrimaryKey(
    	java.lang.String id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	Configuracion object = (Configuracion) session.get(Configuracion.class, id);
    	
    	return object;
    }

   
    public static Configuracion findByNombre(
        String nombre) throws org.hibernate.HibernateException {
    	//HibernateFactory.getSession().flush();
    	Session session = HibernateFactory.getSession();
        //Configuracion object = (Configuracion) session.get(Configuracion.class, nombre);
        //return object;
    	String	querya = "from Configuracion conf where conf.nombre = :nombre";
    	Query query = session.createQuery(querya);
	    query.setParameter("nombre", nombre, Hibernate.STRING);
        return (Configuracion) query.uniqueResult();
    }

	public static Collection findAll() {
		Session session = HibernateFactory.getSession();
		return session.createQuery("from Configuracion").list();
	}

	public static List findNombreNotAtributos(Long atributoId, String nombre) throws HibernateException{
		Session session = HibernateFactory.getSession();
		List configWithAtribute = new ArrayList();
    	String	querya = "select conf.id from Configuracion conf, Valor v left join conf.atrVariables.valors val" +
		" where val.id = v.id and v.valorAdmAtr.atributo.id = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", atributoId, Hibernate.LONG);
	    configWithAtribute = query.list();
	    /*if(configWithAtribute.isEmpty())
	    	return (List) findAll();*/
	    
	    
		Criteria criterio = session.createCriteria(Configuracion.class);
		if(!configWithAtribute.isEmpty())
        criterio.add(Expression.not(Expression.in("id",configWithAtribute)));
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombre", "%"+nombre+"%").ignoreCase());
        return criterio.list();
	}

	public static Configuracion findAtrVariable(Long idAtrVarialbe) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        String	querya = "from Configuracion c where c.atrVariables = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", idAtrVarialbe, Hibernate.LONG);
        return (Configuracion) query.uniqueResult();
        /*Criteria criterio = session.createCriteria(Configuracion.class);
       	criterio.add(Expression.eq("atrVariables", Long.valueOf(idAtrVarialbe)));
       	return (Configuracion) criterio.uniqueResult();*/
	}}
