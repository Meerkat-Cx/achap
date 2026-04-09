package ar.org.sicel.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtAnimal.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtAnimal
 */
public abstract class EvtAnimalDAO {
    // ---------------- create method --------------------

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtAnimal object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtAnimal findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtAnimal object = (EvtAnimal) session.get(EvtAnimal.class, id);
        return object;
    }
    /**
     * Dado un id de un animal retorna el primer evento
     * @param id
     * @return
     * @throws org.hibernate.HibernateException
     */
    public static EvtAnimal findPrimerEvento(Animal ani) throws org.hibernate.HibernateException {
            return ((ani==null)?null:(ani.getEvtAnimals().isEmpty())?null:((EvtAnimal)ani.getEvtAnimals().first()));
        }
    public static EvtLactancia getLactancia(EvtAnimal eva){
    	if(eva.isFinalizaLactancia()){
    		if(!eva.getDependientes().isEmpty()){
        		SortedSet depA = new TreeSet(new EventosPorFechaYTipo());
        		depA.addAll(eva.getDependientes());
        		Iterator it3 = depA.iterator();
    			while(it3.hasNext()){
    				Evento evt = (Evento)it3.next();
    				if(evt.getNombreTipo().equals(Evento.EVT_TIPO_LAC))
						return(EvtLactancia) evt;
				}
    		}
    		}
    	return null;
    	
    }
    
    public static void deleteEvtAnim(EvtAnimal ev) throws ExcepcionIntegridad{
    	Session se = HibernateFactory.getSession();
    	if(!ev.getDependientes().isEmpty()){
    		SortedSet depA = new TreeSet(new EventosPorFechaYTipo());
    		depA.addAll(ev.getDependientes());
    		Iterator it3 = depA.iterator();
			while(it3.hasNext()){
				Evento evt = (Evento)it3.next();
				if(evt.getNombreTipo().equals(Evento.EVT_TIPO_TRA)){
					EvtTransferencia eva = null;
					// eva = (EvtTransferencia) EventoDAO.findByPrimaryKey(evt.getId());
					try{
						eva = (EvtTransferencia) EventoDAO.findByPrimaryKey(evt.getId());
					}
					catch (Exception e) {
						eva = (EvtTransferencia) EvtAnimalDAO.findByPrimaryKey(evt.getId());
					}
					
					eva.ejecutarBaja();
				}
				else 
					if(evt.getNombreTipo().equals(Evento.EVT_TIPO_LAC)){
						
						EvtLactancia lacti =(EvtLactancia) EvtAnimalDAO.findByPrimaryKey(evt.getId());
						if(lacti!=null){
							//ev.getDependientes().remove(lacti);
							
							ev.getAnimal().getEvtAnimals().remove(lacti);
							if (lacti.isEsOficial())
				        		AnimalesLactanciasOficiales.getAnimales().remove(ev);
							if(lacti.getId()!=null)
								lacti.ejecutarBaja();
							
						}
					
					}
				}
			
			ev.setDependientes(new TreeSet(new EventosPorFechaYTipo()));		
			}
			

    	if(!ev.getModificaciones().isEmpty()){
    		SortedSet modifi = ev.getModificaciones();
    		ev.setModificaciones(new TreeSet(new EventosPorFechaYTipo()));
			Iterator it2 = modifi.iterator();
			while(it2.hasNext()){
				EvtAnimalModificacion evt = (EvtAnimalModificacion)it2.next();
				EvtAnimalModificacion eva = (EvtAnimalModificacion) EventoDAO.findByPrimaryKey(evt.getId());
				eva.ejecutarBaja();
			}
		}
		se.delete(ev);
    	
    }

    public static boolean tieneDependienteTipo(EvtAnimal evt, String tipo){
    	
    	if(!evt.getDependientes().isEmpty()){
    		SortedSet depen  = new TreeSet(new EventosPorFechaYTipo());
    		depen.addAll(evt.getDependientes());
    		Iterator it = depen.iterator();
    		Evento e;
    		while(it.hasNext()){
    			Object ob = it.next();
    			if(ob instanceof HibernateProxy){
    				if(ar.org.sicel.persistence.EvtLactancia.class.equals(HibernateProxyHelper.getClassWithoutInitializingProxy(ob)))
    					e = (EvtLactancia)ob;
    				else
    					if(ar.org.sicel.persistence.EvtTransferencia.class.equals(HibernateProxyHelper.getClassWithoutInitializingProxy(ob)))
    						e = (EvtTransferencia)ob;
    					else
    						if(ar.org.sicel.persistence.Evento.class.equals(HibernateProxyHelper.getClassWithoutInitializingProxy(ob)))
        						e = (Evento)ob;
    						else 
    							e = (EvtAnimal)ob;    
    			}
    			else  				
    				e = (EvtAnimal)ob;    
    			//EvtAnimal e = (EvtAnimal) it.next();
    			//EvtAnimal eva = (EvtAnimal) EventoDAO.findByPrimaryKey(e.getId());
    			if(e.getNombreTipo().equals(tipo))
    				return true;
    		}
    	}
    	return false;
    }

	public static void update(Evento ev) {
		Session se = HibernateFactory.getSession();
		se.update(ev);
	}
	
	public static void save(EvtAnimal ev) {
		Session se = HibernateFactory.getSession();
		saveDependientes(ev);
		/*if(!ev.getDependientes().isEmpty()){
			Iterator it = ev.getDependientes().iterator();
			while(it.hasNext()){
				Evento evt = (Evento)it.next();
				se.save(evt);
			}
		}*/
		System.out.println("GRABA Evento----->");
		se.save(ev);
	}
	public static void saveDependientes(EvtAnimal ev) {
		Session se = HibernateFactory.getSession();
		
		if(!ev.getDependientes().isEmpty()){
			System.out.println("GRABA DEP----->");
			Iterator it = ev.getDependientes().iterator();
			while(it.hasNext()){
				Evento evt = (Evento)it.next();
				se.save(evt);
			}
		}
	}
	
	/*select * 
	from (select animal,inicialactancia
	        from ev_animal
	        where animal >=4000000 and animal < 4002000) ev
	where ev.inicialactancia = 1
	order by ev.animal*/
	
	public static List findEventosEntreIntervalo(String raza,Integer idStart,Integer idEnd,Session session) throws org.hibernate.HibernateException {
   		Query query =null;
   		query = session.createQuery("select r from (select a from EvtAnimal as a where  a.animal.id >= "+idStart.toString()+" and a.animal.id < "+idEnd.toString()+") as r where r.iniciaLactancia = true " +
   				" and r.animal.composicionRacial.razaDeclarada.id = '"+raza+"' order by ");
   		return query.list();
        
	}
    
    
   
}
