package ar.org.sicel.upload.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.upload.model.Bajada;



public class BajadaDAO {
	public void save(Bajada b) {
		HibernateFactory.getSession().save(b);
	}
	public Bajada obtenerBajadaMasReciente(ProcProces proc) {
		Criteria c = HibernateFactory.getSession().createCriteria(Bajada.class);
		c.add(Expression.eq("proceso", proc));
		c.addOrder(Order.desc("fecha"));
		List l = c.list();
		
		if((l!=null)&&(!l.isEmpty())){
			return (Bajada) l.get(0);
			
		}
		else{
			return null;
		}
	}
	public void borrarBajadas(ProcProces proc) {
			Criteria c = HibernateFactory.getSession().createCriteria(Bajada.class);
			c.add(Expression.eq("proceso", proc));
			c.addOrder(Order.desc("fecha"));
			List l = c.list();
			if(!l.isEmpty()){
				Iterator it = l.iterator();
				while(it.hasNext()){
					Bajada b = (Bajada)it.next();
					HibernateFactory.getSession().delete(b);
				}
			}
	}
}
			
		
	

