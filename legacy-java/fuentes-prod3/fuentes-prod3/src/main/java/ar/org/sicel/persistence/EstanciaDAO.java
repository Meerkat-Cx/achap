package ar.org.sicel.persistence;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * 
 * @author Orona
 *
 */
public class EstanciaDAO {
	
	static Logger log = Logger.getLogger(EstanciaDAO.class);

	/**
	 * Obtiene los usuarios disponibles
	 * @return List
	 * @throws org.hibernate.HibernateException
	 */
	public static List findAll() throws org.hibernate.HibernateException {
		log.debug("buscando todas las estancias");
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Estancia");
		return query.list();
	}
	
	/**
     * Guarda en la base de datos una instancia de la entidad usuario
     * @param usuario - Usuario
     * @throws HibernateException
     */
    public static void createEstancia(Estancia estancia)
    	throws HibernateException {
    	HibernateFactory.getSession().save(estancia);   	
    	
    }
    public static List findEstancias(String nombre, String id) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Estancia.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(id))
        	criterio.add(Expression.eq("id", Long.valueOf(id)));
       
         
        return criterio.list();
		
		
	}
    public static List findEstanciasActivas(String nombre, String id) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Estancia.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(id))
        	criterio.add(Expression.eq("id", Long.valueOf(id)));
        criterio.add(Expression.eq("activo", true));
         
        return criterio.list();
		
		
	}

    /**
     * Dado un identificador devuelve la estacia correspondiente
     * @param id
     * @return
     * @throws org.hibernate.HibernateException
     */
	public static Estancia findByPrimaryKey(Long id) throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		return (Estancia) session.get(Estancia.class, id);
	}

	/**
	 * Actualiza una instancia de estancia dada
	 * @param estancia
	 * @throws HibernateException
	 */
	public static void updateEstancia(Estancia estancia) throws HibernateException {
		HibernateFactory.getSession().update(estancia);
		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}

	/**
	 * Dado el cuig de la estancia devuelve la estancia correspondiente
	 * @param cuig
	 * @return
	 */
	/*public static Estancia getEstanciaByCuig(String cuig) {
		Criteria criteria =  HibernateFactory.getSession().createCriteria(Estancia.class);
		criteria.add(Expression.eq("cuig", cuig));
		return (Estancia)criteria.uniqueResult();
	}*/
	
	public static List findPorFiltro(String nombre, String nombreEclo, String nombrePropietario) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Estancia.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(nombreEclo)) {
        	Criteria criterioRegional = criterio.createCriteria("eclo");
        	criterioRegional.add(Expression.like("nombreContacto", "%"+nombreEclo+"%").ignoreCase());
        }
        if (StringUtils.isNotEmpty(nombrePropietario)) {
        	Criteria criterioRegional = criterio.createCriteria("propietario");
        	criterioRegional.add(Expression.like("nombreContacto", "%"+nombrePropietario+"%").ignoreCase());
        }
        return criterio.list();
	}
	public static List findPorFiltroEstancia(String id, String nombre, String nombrePropietario) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Estancia.class);
        
        if (StringUtils.isNotEmpty(id))
        	criterio.add(Expression.eq("id",new Long(id)));
        		
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        
        if (StringUtils.isNotEmpty(nombrePropietario)) {
        	Criteria criterioRegional = criterio.createCriteria("propietario");
        	criterioRegional.add(Expression.like("nombreContacto", "%"+nombrePropietario+"%").ignoreCase());
        }
        return criterio.list();
	}
	
	public static List findByFilter(Long idEst, String nombre, String nombrePropietario, Long idEclo, Boolean activo,Long regional) {
		Session session = HibernateFactory.getSession();
		String	select = "select distinct e ";
		String from  = " from Estancia e ";
		String where = " where ";
		boolean andAgregado = false;
				
		if(idEclo != null && idEclo.longValue()!=0) {
			//from = " from Estancia e left join Establecimiento es on e.id = es.estancia";  
			from = " from Establecimiento as es right join es.estancia as e";
			where = " where (es.eclo = :idEclo) and ";
		}
		
		if(idEclo == null || idEclo.longValue()==0) {
			from = " from Estancia e ";			
		}
		
		if(StringUtils.isNotEmpty(nombrePropietario)) {
			where += " e.propietario in (select p.id from Propietario as p where (upper(p.nombreContacto) like :nombrePropietario ) ) and ";
		}
			
		if (StringUtils.isNotEmpty(nombre)) {
			where += " (upper(e.nombreContacto) like :nombre) and ";
			andAgregado = true;
		}
		
		if (idEst != null && idEst.longValue()!=0) {
			//from  += " , Estancia estab ";
			where += " (e.id = :idEst) and ";
			andAgregado = true;
		}
		if (regional != null){
			where += " (0 < (select count(*) from Establecimiento estab where estab.estancia is not null and estab.estancia.id = e.id and estab.eclo.regional.id = "+regional.toString()+" )) and ";
			andAgregado = true;
		}
				
		if (activo != null) {
			where += " (e.activo = :activo) and ";
			andAgregado = true;
		}
		
		if (andAgregado) { // sacar del where los 4 ultimos char
			where = where.substring(0, where.length() - 4);
		}
		else { // tengo que sacar el where
			where = " ";
		}		
	
		Query query = session.createQuery(select+from+where);
		if (StringUtils.isNotEmpty(nombre))
			query.setParameter("nombre", "%"+nombre.toUpperCase()+"%", Hibernate.STRING);		
		if (activo!=null)
			query.setParameter("activo", activo, Hibernate.BOOLEAN);		
		if (idEst != null && idEst.longValue()!=0)
			query.setParameter("idEst", idEst, Hibernate.LONG);
		if (idEclo != null && idEclo.longValue()!=0)
			query.setParameter("idEclo", idEclo, Hibernate.LONG);
		if (StringUtils.isNotEmpty(nombrePropietario))
			query.setParameter("nombrePropietario","%"+nombrePropietario.toUpperCase()+"%",Hibernate.STRING);
		
		
		return query.list();
	}
	
	public static List findByIdPropietariosOrderByIdEstancia(Long idProp){
		Criteria criteria = HibernateFactory.getSession().createCriteria(Estancia.class);
		criteria.createAlias("propietario", "propietario");
    	criteria.add(Expression.eq("propietario.id", idProp));
    	criteria.addOrder(Order.asc("id"));
    	return criteria.list();
	}

}

