package ar.org.sicel.persistence;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtEstablecimiento.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtEstablecimiento
 */
public abstract class EvtEstablecimientoDAO {
    // ---------------- create method --------------------

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtEstablecimiento object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtEstablecimiento findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtEstablecimiento object = (EvtEstablecimiento) session.get(EvtEstablecimiento.class,
                id);

        return object;
    }
    public static EvtEstModificacion findEvtEstModificaByPrimaryKey(
            java.lang.Long id)
           throws org.hibernate.HibernateException {
       	Session session = HibernateFactory.getSession();
       	EvtEstModificacion object = (EvtEstModificacion) session.get(EvtEstModificacion.class,
                   id);

           return object;
       }
    public static void deleteEvtEstab2(EvtEstablecimiento ev) throws ExcepcionIntegridad{
    	Session se = HibernateFactory.getSession();
    	if(!ev.getModificaciones().isEmpty()){
    		SortedSet modifi = ev.getModificaciones();
    		ev.setModificaciones(new TreeSet(new EventosPorFechaYTipo()));
			Iterator it2 = modifi.iterator();
			while(it2.hasNext()){
				Evento evt = (Evento)it2.next();
				EvtEstModificacion eva = (EvtEstModificacion) findByPrimaryKey(evt.getId());
				//deleteEvtEstab(eva);
				eva.ejecutarBaja();
			}
		}
		se.delete(ev);
    	
    }
    public static void deleteEvtEstab(EvtEstablecimiento ev) throws ExcepcionIntegridad{
    	Session se = HibernateFactory.getSession();
    	if(!ev.getModificaciones().isEmpty()){
    		Hibernate.initialize(ev.getModificaciones());
    		SortedSet modifi = ev.getModificaciones();
    		ev.setModificaciones(new TreeSet(new EventosPorFechaYTipo()));
			Iterator it2 = modifi.iterator();
			while(it2.hasNext()){
				Evento evt = (Evento)it2.next();
				EvtEstModificacion eva = null;
				try{
					eva = (EvtEstModificacion) EventoDAO.findByPrimaryKey(evt.getId());
				}
				catch (Exception e) {
					eva = (EvtEstModificacion) findEvtEstModificaByPrimaryKey(evt.getId());
				}
				eva.ejecutarBaja();
			}
		}
		se.delete(ev);
    	
    }
    public static void update(Evento ev) {
		HibernateFactory.getSession().update(ev);
		
	}
}
