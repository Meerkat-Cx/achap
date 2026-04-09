package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.EventoEclo;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.services.impl.ProcesadorLote;
import ar.org.sicel.util.StringUtils;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcEvtEst.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcEvtEst
 */
public abstract class ProcEvtEstDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ProcEvtEst object.
     *
     * @return ProcEvtEst the created object
     */
    public static ProcEvtEst create(Date fechaEnvioLote,Date fechaEvt,Eclo eclo,Sistema sistema, CentroDeComputo centro, Long idEvento) throws ExcepcionIntegridad{
    	if (fechaEvt.after(fechaEnvioLote))
    		throw new ExcepcionIntegridad(MENSAJES.FECHA_EVENTO_INVALIDA, new String[]{StringUtils.formatDate(fechaEvt),StringUtils.formatDate(fechaEnvioLote)});
    	
    	ProcEvtEst object = new ProcEvtEst();
    	if(findByEclo(eclo,sistema,centro,idEvento)!=null)
    		throw new ExcepcionIntegridad(MENSAJES.EVT_FUERA_ORDEN, new String[]{eclo.getId().toString(),sistema.getNombre(),centro==null?" sin centro de computo":"centro de computo "+ centro.getId(), idEvento.toString()});
    	if(ProcEvtAnimalDAO.findByEclo(eclo,sistema,centro,idEvento)!=null)
    		throw new ExcepcionIntegridad(MENSAJES.EVT_FUERA_ORDEN, new String[]{eclo.getId().toString(),sistema.getNombre(),centro==null?" sin centro de computo":"centro de computo "+ centro.getId(), idEvento.toString()});
    	EventoEclo eventoEclo = new EventoEclo(eclo.getId(),sistema.getId(),centro==null?null:centro.getId(),idEvento);
    	if(!ProcesadorLote.conjuntoIdsEclo.isEmpty()){
    		if(ProcesadorLote.conjuntoIdsEclo.containsKey(eventoEclo))
    			throw new ExcepcionIntegridad(MENSAJES.EVT_FUERA_ORDEN, new String[]{eclo.getId().toString(),sistema.getNombre(),centro==null?" sin centro de computo":"centro de computo "+ centro.getId(), idEvento.toString()});
    		else
    			ProcesadorLote.conjuntoIdsEclo.put(eventoEclo,"nuevo");
    	}else
    		ProcesadorLote.conjuntoIdsEclo.put(eventoEclo,"nuevo");
    	//object.checkIdEventoEcloUnico(eclo,idEvento);
    	object.setIdEvtECLO(idEvento);
        object.setProcMsgsses(new HashSet());
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcEvtEst object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProcEvtEst findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ProcEvtEst object = (ProcEvtEst) session.get(ProcEvtEst.class, id);

        return object;
    }
    
    public static ProcEvtEst findByEclo(Eclo eclo,Sistema sistema,CentroDeComputo centro, Long idEvtEclo) throws org.hibernate.HibernateException{
//    	ProcEvt p = null;
    	
    	String queryText = null;
    	Query query = null;
    	Session session = HibernateFactory.getSession();
    	if(centro!=null){
    	 queryText = "from ProcEvtEst pev where " +
    	 		"pev.procEstablecimiento.procLote.eclo = :eclo and " +
    	 		"pev.procEstablecimiento.procLote.sistema = :sistema and " +
    	 		"pev.procEstablecimiento.procLote.centroComputo = :centro " +
    	 		"and pev.idEvtECLO = :idEvtEclo";
    	 query = session.createQuery(queryText);
    	 query.setEntity("eclo",eclo);
    	 query.setEntity("sistema",sistema);
    	 query.setEntity("centro",centro);
    	 query.setLong("idEvtEclo",idEvtEclo);
    	}
    	else{
    		queryText = "from ProcEvtEst pev where " +
	 		"pev.procEstablecimiento.procLote.eclo = :eclo and " +
	 		"pev.procEstablecimiento.procLote.sistema = :sistema " +
	 		"and pev.idEvtECLO = :idEvtEclo";
		 query = session.createQuery(queryText);
		 query.setEntity("eclo",eclo);
		 query.setEntity("sistema",sistema);
		 query.setLong("idEvtEclo",idEvtEclo);
    	}
    	return (ProcEvtEst)query.uniqueResult();
    	
    }
    

}
