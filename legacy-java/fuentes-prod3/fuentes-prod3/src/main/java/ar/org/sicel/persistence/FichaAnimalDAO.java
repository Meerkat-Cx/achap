/**
 * 
 */
package ar.org.sicel.persistence;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * DAO de FichaAnimalPendiente
 * @author jdivars
 * @since 8-10-2006
 */
public class FichaAnimalDAO {
	static Logger log = Logger.getLogger(FichaAnimalDAO.class);
	
	public static FichaAnimalPendiente load(Long i) {
		return (FichaAnimalPendiente)HibernateFactory.getSession().load(FichaAnimalPendiente.class,i);
		
	}
	
	public static void save(FichaAnimalPendiente p) {
		HibernateFactory.getSession().save(p);
	}

	public static void update(FichaAnimalPendiente p) {
		HibernateFactory.getSession().update(p);
	}
	public static List getAllFichasAnimal() {
		Criteria c =HibernateFactory.getSession().createCriteria(FichaAnimalPendiente.class);
		c.add(Expression.eq("pendiente",true));
		c.addOrder(Order.desc("fecha"));
		return c.list();
		
	}
	/*public static List findExistentByLote(Long loteId)
				throws ExcepcionIntegridad {
			List results = null;
			
			try{
				Session session = HibernateFactory.getSession();
				//String queryText = "from FichaAnimalPendiente fp where fp.ecloId= :ecloId and fp.loteId = :loteId";
				String queryText = "from FichaAnimalPendiente fp where  fp.loteId = :loteId";
				Query query = session.createQuery(queryText);
				//query.setParameter("ecloId", ecloId, Hibernate.LONG);
				query.setParameter("loteId", loteId, Hibernate.LONG);
				results = query.list();
			} catch (HibernateException he) {
				he.printStackTrace();
				throw new ErrorFatal("Error al querer buscar una Ficha Pendiente - FichaAnimalPendiente",he);
			}
			if (results == null || results.isEmpty())
				throw new ExcepcionIntegridad(MENSAJES.FICHA_INEXISTENTE,new String[]{Long.toString(loteId)});	
			
			return results;
			}
	*/		
	public static List findExistentByState()
				throws ExcepcionIntegridad {
			List results = null;
			
			try{
				Session session = HibernateFactory.getSession();
				String queryText = "from FichaAnimalPendiente fp where fp.pendiente = 1";
				Query query = session.createQuery(queryText);
				
				results = query.list();
			} catch (HibernateException he) {
				he.printStackTrace();
				throw new ErrorFatal("Error al querer buscar una Ficha Pendiente - FichaAnimalPendiente",he);
			}
			if (results == null || results.isEmpty())
				throw new ExcepcionIntegridad(MENSAJES.NO_HAY_FICHAS,new String[] {});	
			
			return results;
			}
			public static List findExistentById(Long id)
				throws ExcepcionIntegridad {
			List results = null;
			
			try{
				Session session = HibernateFactory.getSession();
				String queryText = "from FichaAnimalPendiente fp where fp.id = :id";
				Query query = session.createQuery(queryText);
				query.setParameter("id", id, Hibernate.LONG);
				results = query.list();
			} catch (HibernateException he) {
				he.printStackTrace();
				throw new ErrorFatal("Error al querer buscar una Ficha Pendiente - FichaAnimalPendiente",he);
			}
			if (results == null || results.isEmpty())
				throw new ExcepcionIntegridad(MENSAJES.NO_HAY_FICHAS,new String[] {});	
			
			return results;
			}
			
	public static void borrarFichas(Set fichas){
			
			if (!fichas.isEmpty()){
				for(Object f:fichas){
					FichaAnimalPendiente ficha = (FichaAnimalPendiente)f;
					ficha.setPendiente(false);
				}
				
			}
				
			}
			}
