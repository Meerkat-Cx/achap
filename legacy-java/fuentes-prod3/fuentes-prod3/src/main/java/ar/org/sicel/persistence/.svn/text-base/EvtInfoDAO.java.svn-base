package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtInfo.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtInfo
 */
public abstract class EvtInfoDAO {
    // ---------------- create method --------------------

	public static EvtInfo create(Establecimiento establecimiento, Animal animal, Date fecha,  String info, String valores, List<ProcMsg> msgs) throws ExcepcionIntegridad{
		EvtInfo evt = new EvtInfo(establecimiento,fecha,animal,info,valores,msgs);
		
		
		return evt;
	}

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtInfo object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtInfo findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtInfo object = (EvtInfo) session.get(EvtInfo.class, id);
        return object;
    }
    
    public static void updateEventoInfo(EvtInfo evtInfo) {
		HibernateFactory.getSession().update(evtInfo);
	}

	
}
