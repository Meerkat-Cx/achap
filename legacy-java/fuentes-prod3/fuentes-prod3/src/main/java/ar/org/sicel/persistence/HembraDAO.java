package ar.org.sicel.persistence;

import java.util.HashSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Hembra.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Hembra
 */
public abstract class HembraDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Hembra object.
     *
     * @param nombre
     * @param rP
     * @param fechaNac
     * @param esActivo
     * @param esBaja
     * @param categoriaV3
     * @param sRAProp
     * @param sRAEsCreado
     * @param sRACbaj
     * @param sRAFesb
     * @return Hembra the created object
     
    public static Hembra create(java.lang.String nombre, java.lang.String rP,
        java.util.Date fechaNac, java.lang.Boolean esActivo,
        java.lang.Boolean esBaja, java.lang.String categoriaV3,
        java.lang.Integer sRAProp, java.lang.Boolean sRAEsCreado,
        java.lang.String sRACbaj, java.util.Date sRAFesb) {
        Hembra object = new Hembra();

        object.setNombre(nombre);
        object.setRP(rP);
        object.setFechaNac(fechaNac);
        object.setEsActivo(esActivo);
        object.setEsBaja(esBaja);
        object.setCategoriaV3(categoriaV3);
        object.setSRAProp(sRAProp);
        object.setSRAEsCreado(sRAEsCreado);
        object.setSRACbaj(sRACbaj);
        object.setSRAFesb(sRAFesb);

        object.setCalificacions(new HashSet());
        object.setEvtAnimals(new HashSet());
        object.setEvtClons(new HashSet());
        object.setEvtTransEmbs(new HashSet());
        object.setHijosGeneticos(new HashSet());
        object.setHijosParto(new HashSet());
        object.setProcAnimals(new HashSet());
        object.setRegistros(new HashSet());

        return object;
    }
*/
    /**
     * Los animales se crean por AnimalDAO, de ahi, dependiendo
     * si es macho o hembra se procede. 
     * No llamar directamente a este metodo.
     */
    public static Hembra create() throws ExcepcionIntegridad {
    	Hembra h = new Hembra();
    	h.setHijosGeneticos(new HashSet());
    	h.setHijosParto(new HashSet());
    	h.setEvtTransEmbs(new HashSet());
    	//h.setNroLactancia(0l);
    	//h.setFichasAnimal(new HashSet<FichaAnimalPendiente>());
    	return h;
    }
    
    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Hembra object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Hembra findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Hembra object = (Hembra) session.get(Hembra.class, id);

        return object;
    }
}
