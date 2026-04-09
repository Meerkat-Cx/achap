package ar.org.sicel.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;

public class RolDAO {

	/**
	 * Obtiene los roles disponibles
	 * @return List
	 * @throws org.hibernate.HibernateException
	 */
	public static List findAll() throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Rol");
		return query.list();
	}
	
	/**
	 * Retorna el rol dado su clave primaria
	 * @param id
	 * @return Rol
	 * @throws org.hibernate.HibernateException
	 */
	public static Rol findByPrimaryKey(Long id)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		return (Rol) session.get(Rol.class, id);
	}
	
	/**
	 * Retorna el rol dado su nombre
	 * @param nombre - String
	 * @return Rol
	 * @throws org.hibernate.HibernateException
	 */
	public static Rol findByNombre(String nombre)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
    	Criteria criteria = session.createCriteria(Rol.class);
        criteria.add(Expression.eq("nombre",nombre));
        criteria.setLockMode(LockMode.READ);
		return (Rol) criteria.uniqueResult();
	}
}
