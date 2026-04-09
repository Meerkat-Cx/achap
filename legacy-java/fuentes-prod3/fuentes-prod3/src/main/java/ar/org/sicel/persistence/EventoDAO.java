package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Evento.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Evento
 */
public abstract class EventoDAO {
    	
    // ---------------- create method --------------------

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Evento object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Evento findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Evento object = (Evento) session.get(Evento.class, id);

        return object;
    }
    public static Date findFechaUltimoEventoAnimal(Animal animal){
    	
    	Session session = HibernateFactory.getSession();
		String queryText = "select max(ev.fecha) from EvtAnimal an, Evento ev " +
				"where animal= :animal and an.id = ev.id";
		Query query = session.createQuery(queryText);
		query.setEntity("animal", animal);
		return (Date)query.uniqueResult();
    }
     /**
      * Metodo que recupera el ultimo evento animal del animal
      * informado por parametro. 
      * Primero recupera la fecha del ultimo evento y luego recupera
      * el evento en esa fecha
      * @param animal
      * @return
      */
   public static Evento findUltimoEventoAnimal(Animal animal){
	   Date fecha = null;
	   fecha = EventoDAO.findFechaUltimoEventoAnimal(animal);
	   if(fecha == null)
		   return null;
	   else{
		   Date fe= new Date(fecha.getTime());
		   Session session = HibernateFactory.getSession();
		String queryText = "select ea from Evento ea, EvtAnimal esa " +
				"where esa.animal = :animal " +
				"and ea.id = esa.id and " +
				"ea.fecha = :fe";
		Query query = session.createQuery(queryText);
		query.setEntity("animal", animal);
		 query.setParameter("fe", fe, Hibernate.DATE);
		return (Evento)query.uniqueResult();
	   }
   }
   
   
   /**
    * Metodo que recupera todas las lactancias en cierto rango de fechas
    * @param fecha_desde, fecha_hasta
    * @return
    */
	public static List findEventosEntreIntervalo(String raza,Integer idStart,Integer idEnd,Session session) throws org.hibernate.HibernateException {
   		Query query =null;
   		query = session.createQuery("select r from (select a from EvtAnimal as a where  a.animal.id >= "+idStart.toString()+" and a.animal.id < "+idEnd.toString()+") as r where r.iniciaLactancia = true " +
   				" and r.animal.composicionRacial.razaDeclarada.id = '"+raza+"' order by ");
   		return query.list();
        
	}
 public static List findLactanciasEntreFechas(String fecha_desde, String fecha_hasta, String raza){
		SQLQuery query =null;
		query = HibernateFactory.getSession().createSQLQuery("select el.id as ID from EV_LACTANCIA  el join EV_EVENTO ev on el.ID=ev.ID join EV_ANIMAL ea on ea.id=el.id join AN_ANIMAL an on an.ID=ea.ANIMAL where ev.FECHA between to_date('"+fecha_desde+"','yyyy/mm/dd') and to_date('"+fecha_hasta+"','yyyy/mm/dd')");
		query.addScalar("ID",Hibernate.LONG);
		return query.list();
		
	/*	("select a from EvtLactancia el join Evento as ev join EvtAnimal as ea join Animal as an " +
				"where ev.fecha >= "+DateUtils.format(fecha_desde, "yyyy/MM/dd")+" and ev.fecha < "+DateUtils.format(fecha_hasta, "yyyy/MM/dd")+" and an.raza='"+raza+"'");
		return query.list();
		SQLQuery query =null;
   		query = session.createSQLQuery("select min(animal) as MIN from animal_inicia_lactancia");
   		query.addScalar("MIN", Hibernate.LONG);
        return (Long)query.uniqueResult();*/
 }
   /**
    * metodo que retorna si "dependiente" es dependiente de algun evento, en dicho caso retorna ese evento.
    * @param an
    * @param dependiente
    * @return
    */
   public static Evento findEventoIdEventoDeDependiente(Animal an, Evento dependiente){
	   //List eventosPosibles = an.getEventosEntre(DateUtils.menos(dependiente.getFecha(), 20), DateUtils.mas(dependiente.getFecha(), 20));
	   if(dependiente.getNombreTipo().equals(Evento.EVT_TIPO_LAC)){
		   List eventosPosibles = an.getEventoFinalizanLactancia();
		   if(!eventosPosibles.isEmpty()){
			   Iterator it = eventosPosibles.iterator();
			   while(it.hasNext()){
				   Evento ev = (Evento)it.next();
				   if(!(ev.getDependientes().isEmpty()) &&(ev.getDependientes().contains(dependiente)))
					   return ev;
			   }
			}
	 }else
		   if(dependiente.getNombreTipo().equals(Evento.EVT_TIPO_TRA)){
			   List eventosPosibles = an.getEventosEntre(DateUtils.menos(dependiente.getFecha(), 1), DateUtils.mas(dependiente.getFecha(), 1));
			   //List eventosPosibles = an.getEventoFinalizanLactancia();
			   if(!eventosPosibles.isEmpty()){
				   Iterator it = eventosPosibles.iterator();
				   while(it.hasNext()){
					   Evento ev = (Evento)it.next();
					   if(!(ev.getDependientes().isEmpty()) &&(ev.getDependientes().contains(dependiente)))
						   return ev;
				   }
				}
		 }
	   return null;
   }
	  
   /* select ea.* from EV_EVENTO ea, EV_ANIMAL esa
    where esa.animal = 3771023 and ea.id = esa.id
         and ea.FECHA = (select max(e.fecha) from EV_ANIMAL a, EV_EVENTO e
         where animal = 3771023 and a.id = e.id)*/
	
}
