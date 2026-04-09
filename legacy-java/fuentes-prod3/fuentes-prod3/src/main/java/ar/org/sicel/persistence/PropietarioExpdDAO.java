package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;


public class PropietarioExpdDAO {
	
	/**
	 * 
	 * @param numero
	 * @param estab
	 * @param cuig
	 * @param p
	 * @param user
	 * @return
	 */
	public static PropietarioExpd createPropietarioExpd(Long numero, String estab, String cuig, Usuario user,Raza raza) {
		PropietarioExpd expdNew = new PropietarioExpd();
		expdNew.setEstab(estab);
		expdNew.setNumero(numero);		
		expdNew.setFechaAlta(new Date());
		expdNew.setUsuario(user);
		expdNew.setCuig(cuig);
		expdNew.setRaza(raza);
		return expdNew;
	}
	
	public static void savePropietarioExpd(PropietarioExpd expd) {
		HibernateFactory.getSession().save(expd);
	}	
	
    public static PropietarioExpd findByPrimaryKey(Long id)
            throws org.hibernate.HibernateException {
        	Session session = HibernateFactory.getSession();
        	PropietarioExpd object = (PropietarioExpd) session.get(PropietarioExpd.class, id);            
        	return object;
    }
    public static  void remove(PropietarioExpd r) throws org.hibernate.HibernateException {
    	HibernateFactory.getSession().delete(r);
            
        }
	@SuppressWarnings("unchecked")
	public static List<PropietarioExpd> findByNumero(Long numeroExpd,Long eclo,String delSistema) {
		Session session = HibernateFactory.getSession();
		String query = "select p from PropietarioExpd as p where p.numero = "+numeroExpd.toString();
		if ((eclo != null))
			if (delSistema.equals("no"))
				query += " and 0 < (select count(*) from Establecimiento estab where estab.propietario is not null and estab.propietario = p.propietario and estab.eclo is not null and estab.eclo.id = "+eclo.toString()+")";
			else
				query += " and 0 < (select count(*) from Establecimiento estab where estab.propietario is not null and estab.propietario = p.propietario)";
		Query consulta = session.createQuery(query);
		return consulta.list();
	}
	public static PropietarioExpd findByNumeroyRaza(Long numeroExpd,Raza raza) {
		
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(PropietarioExpd.class);
		criterio.add(Expression.eq("numero", numeroExpd));
		criterio.add(Expression.eq("raza", raza));
		return (PropietarioExpd) criterio.uniqueResult();
	}

	
}
