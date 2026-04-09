package ar.org.sicel.persistence;

import java.util.List;

import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtBaja.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtBaja
 */
public abstract class EvtBajaDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtBaja object.
     * @param animal 
     * @param establecimiento 
     *
     * @param destino
     * @param motivo
     * @param comentario
     * @param fecha
     * @return EvtBaja the created object
     */
    public static EvtBaja create(Establecimiento establecimiento, Animal animal, java.lang.String destino,
        java.lang.String motivo, java.lang.String comentario,
        java.util.Date fecha, List msgs) throws ExcepcionIntegridad{
        EvtBaja object = new EvtBaja(establecimiento,fecha,animal,msgs);

        object.setDestino(destino);
        object.setMotivo(motivo);
        object.setComentario(comentario);
        // Animales con HBA y animales con RC y numero 0 (toro que significa servicio robo)
        //ahora los animales con HBA como regori tambien se pueden eliminar
       // if("HBA".equals(animal.getRegOrigen().getTipoRegistro().getId())||
        //   ("RC".equals(animal.getRegOrigen().getTipoRegistro().getId())&&"0".equals(animal.getRegOrigen().getNumero())))
        if(("RC".equals(animal.getRegOrigen().getTipoRegistro().getId())&&"0".equals(animal.getRegOrigen().getNumero())))
        	throw new ExcepcionIntegridad(MENSAJES.BAJA_IMPOSIBLE,
        		new String[]{animal.getRegistroOrigen()});
        animal.addEventoAnimal(object,msgs);
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtBaja object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtBaja findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtBaja object = (EvtBaja) session.get(EvtBaja.class, id);

        return object;
    }

	public static void updateEventoBaja(EvtBaja evtBaja) {
		HibernateFactory.getSession().update(evtBaja);	
	}
}
