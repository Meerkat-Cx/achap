package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtTransferencia.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtTransferencia
 */
public abstract class EvtTransferenciaDAO {


    

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtTransferencia object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtTransferencia findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtTransferencia object = (EvtTransferencia) session.get(EvtTransferencia.class,
                id);

        return object;
    }

    
    // ---------------- create method --------------------
    public static EvtTransferencia createTransferenciaImplicita(Establecimiento est, Animal animal, Date fechaTransf, Establecimiento estabNuevo, String rpNuevo, Propietario propNuevo, List msgs, boolean restroactiva) throws ExcepcionIntegridad {
    	
    	if (propNuevo == null)
			//propNuevo = animal.getPropietario();
    		propNuevo = estabNuevo.getPropietario();
    	//EvtTransferencia evt = new EvtTransferencia(est,fechaTransf,animal,estabNuevo,propNuevo,rpNuevo,msgs);
    	EvtTransferencia evt = new EvtTransferencia(est,fechaTransf,animal,estabNuevo,propNuevo,rpNuevo,msgs);
		// evt.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
    	evt.setImplicita(true);
    	animal.addEventoAnimal(evt,msgs);
    	if(!restroactiva){
    		animal.transferirAPropietario(propNuevo);
    		animal.transferirAEstablecimiento(estabNuevo,rpNuevo,msgs, animal.getFechaNac());
    	}
		
		
		
		return evt;
		
	}
	public static EvtTransferencia create(Establecimiento est, Animal animal, Date fechaTransf, Establecimiento estabNuevo, String rpNuevo, Propietario propNuevo, List msgs,ProcLote procLote) throws ExcepcionIntegridad {
		
		if (propNuevo == null)
			//propNuevo = animal.getPropietario();
			propNuevo = estabNuevo.getPropietario();
		//EvtTransferencia evt = new EvtTransferencia(est,fechaTransf,animal,estabNuevo,propNuevo,rpNuevo,msgs);
		EvtTransferencia evt = new EvtTransferencia(animal.getEstablecimiento(),fechaTransf,animal,estabNuevo,propNuevo,rpNuevo,msgs);
				// evt.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
		animal.addEventoAnimal(evt,msgs);
		animal.transferirAPropietario(propNuevo);
		animal.transferirAEstablecimiento(estabNuevo,rpNuevo,msgs, animal.getFechaNac());
		if(!animal.getRegistroOrigen().contains("HBA")){
			FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
			fichaAnimal.setAnimal(animal);
			procLote.agregarFichaAnimal(fichaAnimal);
		}
		
		return evt;
		
	}
	
	public static void updateEventoTransferencia(EvtTransferencia evtTransf) {
		HibernateFactory.getSession().update(evtTransf);
	}
}
