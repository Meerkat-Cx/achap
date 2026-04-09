package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.TreeSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Macho.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Macho
 */
public abstract class MachoDAO {
    // ---------------- create method --------------------

	/**
     * Los animales se crean por AnimalDAO, de ahi, dependiendo
     * si es macho o hembra se procede. 
     * No llamar directamente a este metodo.
     */
    public static Macho create() {
        Macho object = new Macho();
        object.setEvtSemens(new HashSet());
        object.setHijosGeneticos(new HashSet());
        object.setEvtNuevoInds(new TreeSet(new EventosPorFechaYTipo()));
        // object.setEvtNuevoInds(new HashSet());
        //object.setNroLactancia(0l);
       // object.setFichasAnimal(new HashSet<FichaAnimalPendiente>());
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Macho object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Macho findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Macho object = (Macho) session.get(Macho.class, id);

        return object;
    }
}
