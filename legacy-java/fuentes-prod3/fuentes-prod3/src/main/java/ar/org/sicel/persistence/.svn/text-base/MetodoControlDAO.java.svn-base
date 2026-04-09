package ar.org.sicel.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;

public class MetodoControlDAO {

	public static MetodoControl findByPrimaryKey(String codigo) throws HibernateException {
		Session session = HibernateFactory.getSession();
		return (MetodoControl) session.get(MetodoControl.class, codigo);
	}
	
	public static List findAll() throws HibernateException {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from MetodoControl");
		return query.list();
	}
}
