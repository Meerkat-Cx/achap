/**
 * 
 */
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

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * @author jdivars
 *
 */
public class CentroDeComputoDAO {
	
	static Logger log = Logger.getLogger(CentroDeComputoDAO.class);
	
	 public static CentroDeComputo findExistentByPrimaryKey(Long idCentro)
     throws ExcepcionIntegridad {
			 CentroDeComputo result = null;
			 try {
			     result = findByPrimaryKey(idCentro);
			 } catch (HibernateException he) {
			     throw new ErrorFatal(
			             "Error al querer buscar el centro de Computo - CentroDeComputoDAO",
			             he);
			 }
			 if (result == null)
			     throw new ExcepcionIntegridad(MENSAJES.CENTRO_DE_COMPUTO_NO_EXISTE,
			             new String[] { idCentro.toString() });
			 return result;
			}

	public static CentroDeComputo findByPrimaryKey(Long idCentro) {
		Session session = HibernateFactory.getSession();
        CentroDeComputo object = (CentroDeComputo) session.get(CentroDeComputo.class, idCentro);     

        return object;

	}
	
	public static List findCentrosDeComputo(Long id, String nombre) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(CentroDeComputo.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombre", "%"+nombre+"%").ignoreCase());
        if (id != null && id.longValue() !=0 )
        	criterio.add(Expression.eq("id", id));       
         
        return criterio.list();		
		
	}	
	
	public static void createCentroDeComputo(CentroDeComputo c) throws HibernateException {
		HibernateFactory.getSession().save(c);
	}
	
	public static void updateCentroDeComputo(CentroDeComputo c) throws HibernateException {
		HibernateFactory.getSession().update(c);
		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
	
	public static List findAllCentroDeComputo() throws HibernateException {
		Session session = HibernateFactory.getSession();        
        Criteria criterio = session.createCriteria(CentroDeComputo.class);
        return criterio.list();		
	}
	
	public static List findByFilter(Long idC, String nombre, Long idEclo, Boolean activo) {
		Session session = HibernateFactory.getSession();
		String	select = "select distinct c ";
		String from  = " from CentroDeComputo c ";
		String where = " where ";
		boolean andAgregado = false;
				
		if(idEclo != null && idEclo.longValue()!=0) {			
			from = " from Establecimiento as es right join es.centroComputo as c";
			where = " where (es.eclo = :idEclo) and ";
		}
		
		if(idEclo == null || idEclo.longValue()==0) {
			from = " from CentroDeComputo c ";			
		}
					
		if (StringUtils.isNotEmpty(nombre)) {
			where += " (upper(c.nombre) like :nombre) and ";
			andAgregado = true;
		}
		
		if (idC != null && idC.longValue()!=0) {			
			where += " (c.id = :idC) and ";
			andAgregado = true;
		}		
		
		if (activo != null) {
			where += " (c.activo = :activo) and ";
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
		if (idC != null && idC.longValue()!=0)
			query.setParameter("idC", idC, Hibernate.LONG);
		if (idEclo != null && idEclo.longValue()!=0)
			query.setParameter("idEclo", idEclo, Hibernate.LONG);	
		
		return query.list();
	}

}
