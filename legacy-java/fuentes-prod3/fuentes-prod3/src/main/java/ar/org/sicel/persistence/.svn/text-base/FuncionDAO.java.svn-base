package ar.org.sicel.persistence;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;

public class FuncionDAO {

    public static Funcion findByPrimaryKey(Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
       	Funcion object = (Funcion) session.get(Funcion.class, id);
        return object;
    }

    public static Funcion findNombre(String link){
    	Session session = HibernateFactory.getSession();
        Criteria criterio = session.createCriteria(Funcion.class);
        if (StringUtils.isNotEmpty(link))        	
            criterio.add(Expression.eq("link", link));        
        return (Funcion) criterio.uniqueResult();
    }
}
