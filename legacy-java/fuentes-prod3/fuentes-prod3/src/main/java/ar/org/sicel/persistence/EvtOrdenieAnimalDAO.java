package ar.org.sicel.persistence;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.OrdenieAnimal;


/**
 * <p>
 * Factory class. Is able to find and create objects of type EvtProduccion.
 * Hibernate inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.EvtOrdenieAnimal
 */
public abstract class EvtOrdenieAnimalDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtProduccion object.
     * 
  
     * @param fecha
     * @param ordenie 
     * @return EvtProduccion the created object
     */
    public static EvtOrdenieAnimal create(OrdenieAnimal ordenieAnimal,List<ProcMsg> msgs, Animal animal) throws ExcepcionIntegridad {
        EvtOrdenieAnimal object = new EvtOrdenieAnimal(ordenieAnimal, new HashMap(),msgs,animal);
        // object.validar(msgs);
        // anim.addEventoAnimal(object, msgs);
        
        return object;
    }
    

    public static EvtOrdenieAnimal createSimple(Animal anim, java.util.Date fecha, List<ProcMsg> msg) throws ExcepcionIntegridad {
        return create(null,msg,anim);
    }

    public static  void remove(EvtOrdenieAnimal evtOrdAnimal) throws org.hibernate.HibernateException {
    	HibernateFactory.getSession().delete(evtOrdAnimal);
    }

    // ---------------- finder methods ----------------------

    /**
     * Finds EvtProduccion object by its primary key. In Hibernate, this is just
     * a call to get().
     */
    public static EvtOrdenieAnimal findByPrimaryKey(java.lang.Long id)
            throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        EvtOrdenieAnimal object = (EvtOrdenieAnimal) session.get(EvtOrdenieAnimal.class,
                id);
        return object;
    }
    
    public static Date fechaUltimoControlEnTambo(Establecimiento est){
    	Session session = HibernateFactory.getSession();    	
		String	query="select max(ev.fecha) as MAX "+
						"from ev_evento ev,ev_establecimiento ev_est,ev_controlanimal ev_con "+
						"where ev.establecimiento = "+est.getId().toString()+" and ev.id = ev_est.id and ev_est.ID = ev_con.CONTROLESTABLECIMIENTO "+
						"group by ev.establecimiento";
		SQLQuery querySQL =null;
   		querySQL = session.createSQLQuery(query);
   		querySQL.addScalar("MAX", Hibernate.DATE);
   		Object date = querySQL.uniqueResult();
   		if (date != null)
   			return (Date)date;
        return null;
    }
    public static Date fechaUltimoControlEnEstancia(Estancia est){
    	Session session = HibernateFactory.getSession();    	
		String	query="select /*+ USE_NL(ev_est ev ) */ max(ev.fecha) as MAX "+
						"from en_establecimiento est,ev_evento ev,ev_establecimiento ev_est,ev_controlanimal ev_con "+
						"where est.estancia = "+est.getId().toString()+" and ev.establecimiento = est.id and ev.id = ev_est.id and ev_est.ID = ev_con.CONTROLESTABLECIMIENTO ";
		SQLQuery querySQL =null;
   		querySQL = session.createSQLQuery(query);
   		querySQL.addScalar("MAX", Hibernate.DATE);
   		Object date = querySQL.uniqueResult();
   		if (date != null)
   			return (Date)date;
        return null;
    }
    
    public static Date fechaUltimaLactCerroEnEstancia(Estancia est){
    	Session session = HibernateFactory.getSession();    	
		String	query="select max(fechas.MAX) as MAX from (select /*+ USE_NL(ev_evento e ) */ max(l.inicio + l.dias) as MAX "+ 
                        "from en_establecimiento est,ev_lactmigrada l,ev_animal a,ev_evento e "+
                        "where est.estancia = "+est.getId().toString() +" and est.id = e.establecimiento and l.id = a.id and a.id = e.id and l.dias <> 999 "+
                        "union "+
                        "select /*+ USE_NL(ev_evento e ) */ max(l.FECHAINICIO + l.DIASTOTALES) as MAX "+ 
                        "from en_establecimiento est,ev_lactancia l,ev_animal a,ev_evento e "+
                        "where est.estancia = "+est.getId().toString() +" and est.id = e.establecimiento and l.id = a.id and a.id = e.id) fechas "+
						"where fechas.MAX is not null";
		SQLQuery querySQL =null;
   		querySQL = session.createSQLQuery(query);
   		querySQL.addScalar("MAX", Hibernate.DATE);
   		Object date = querySQL.uniqueResult();
   		if (date != null)
   			return (Date)date;
        return null;
    }
    
    
    
}
