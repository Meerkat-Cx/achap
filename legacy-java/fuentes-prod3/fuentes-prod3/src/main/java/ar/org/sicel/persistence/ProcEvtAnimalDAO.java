package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.EventoEclo;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.ProcessWork;
import ar.org.sicel.proc.services.impl.ProcesadorLote;
import ar.org.sicel.util.StringUtils;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcEvtAnimal.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcEvtAnimal
 */
public abstract class ProcEvtAnimalDAO {
    // ---------------- create method --------------------
	static Logger log = Logger.getLogger(ProcEvtAnimalDAO.class);
    /**
     * Creates a(n) ProcEvtAnimal object.
     *
     * @return ProcEvtAnimal the created object
     */
    public static ProcEvtAnimal create(Date fechaEnvioLote,Date fechaEvt,Eclo eclo,Sistema sistema,CentroDeComputo centro, Long idEvento) throws ExcepcionIntegridad{
    	if (fechaEvt.after(fechaEnvioLote))
    		throw new ExcepcionIntegridad(MENSAJES.FECHA_EVENTO_INVALIDA, new String[]{StringUtils.formatDate(fechaEvt),StringUtils.formatDate(fechaEnvioLote)});
    	ProcEvtAnimal object = new ProcEvtAnimal();
    	if(idEvento.equals(59546L))
    		log.warn("id de evento animal informado: "+idEvento);
    	if(idEvento.equals(2917611L))
    		log.warn("id de evento animal informado: "+idEvento);
    	if(idEvento.equals(2917604L))
    		log.warn("id de evento animal informado: "+idEvento);
    	log.warn("id de evento animal informado: "+idEvento);
    	if(findByEclo(eclo,sistema,centro,idEvento)!=null)
    		throw new ExcepcionIntegridad(MENSAJES.EVT_FUERA_ORDEN, new String[]{eclo.getId().toString(),sistema.getNombre(),centro==null?" sin centro de computo":"centro de computo "+ centro.getId(), idEvento.toString()});
    	if(ProcEvtEstDAO.findByEclo(eclo,sistema,centro,idEvento)!=null)
    		throw new ExcepcionIntegridad(MENSAJES.EVT_FUERA_ORDEN, new String[]{eclo.getId().toString(),sistema.getNombre(),centro==null?" sin centro de computo":"centro de computo "+ centro.getId(), idEvento.toString()});
    	EventoEclo eventoEclo = new EventoEclo(eclo.getId(),sistema.getId(),centro==null?null:centro.getId(),idEvento);
    	if(!ProcesadorLote.conjuntoIdsEclo.isEmpty()){
    		if(ProcesadorLote.conjuntoIdsEclo.containsKey(eventoEclo))
    			throw new ExcepcionIntegridad(MENSAJES.EVT_FUERA_ORDEN, new String[]{eclo.getId().toString(),sistema.getNombre(),centro==null?" sin centro de computo":"centro de computo "+ centro.getId(), idEvento.toString()});
    		else
    			ProcesadorLote.conjuntoIdsEclo.put(eventoEclo,"nuevo");
    	}else
    		ProcesadorLote.conjuntoIdsEclo.put(eventoEclo,"nuevo");
    //	object.checkIdEventoEcloUnico(eclo,idEvento);
    	object.setIdEvtECLO(idEvento);
        object.setProcMsgsses(new HashSet());
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcEvtAnimal object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProcEvtAnimal findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ProcEvtAnimal object = (ProcEvtAnimal) session.get(ProcEvtAnimal.class,
                id);

        return object;
    }
    
    
    public static ProcEvtAnimal findByEclo(Eclo eclo,Sistema sistema,CentroDeComputo centro, Long idEvtEclo) throws org.hibernate.HibernateException{
//    	ProcEvt p = null;
    	String queryText = null;
    	Query query = null;
    	Session session = HibernateFactory.getSession();
    	if(centro!=null){
    	 queryText = "from ProcEvtAnimal pev where " +
    	 		"pev.procAnimal.procEstablecimiento.procLote.eclo = :eclo and " +
    	 		"pev.procAnimal.procEstablecimiento.procLote.sistema = :sistema and " +
    	 		"pev.procAnimal.procEstablecimiento.procLote.centroComputo = :centro " +
    	 		"and pev.idEvtECLO = :idEvtEclo";
    	 query = session.createQuery(queryText);
    	 query.setEntity("eclo",eclo);
    	 query.setEntity("sistema",sistema);
    	 query.setEntity("centro",centro);
    	 query.setLong("idEvtEclo",idEvtEclo);
    	}
    	else{
    		queryText = "from ProcEvtAnimal pev where " +
	 		"pev.procAnimal.procEstablecimiento.procLote.eclo = :eclo and " +
	 		"pev.procAnimal.procEstablecimiento.procLote.sistema = :sistema " +
	 		"and pev.idEvtECLO = :idEvtEclo";
		 query = session.createQuery(queryText);
		 query.setEntity("eclo",eclo);
		 query.setEntity("sistema",sistema);
		 query.setLong("idEvtEclo",idEvtEclo);
    	}
    	return (ProcEvtAnimal)query.uniqueResult();
    	
    }
    

}
