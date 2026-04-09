package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Calificador.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Calificador
 */
public abstract class CalificadorDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Calificador object.
     *
     * @param documento
     * @param tipoDocumento
     * @param nombrePersona
     * @param apellido
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return Calificador the created object
     */
    public static Calificador create(java.lang.Integer documento,
        java.lang.String tipoDocumento, java.lang.String nombrePersona,
        java.lang.String apellido, java.lang.String nombreContacto,
        java.lang.String comentario, byte[] foto) {
        Calificador object = new Calificador();

        object.setDocumento(documento);
        object.setTipoDocumento(tipoDocumento);
        object.setNombrePersona(nombrePersona);
        object.setApellido(apellido);
        object.setNombreContacto(nombreContacto);
        object.setComentario(comentario);
        //object.setFoto(foto);

        object.setUbicacions(new HashSet());

        return object;
    }
    
    public static Calificador createPersistent(java.lang.Integer documento,
            java.lang.String tipoDocumento, java.lang.String nombrePersona,
            java.lang.String apellido, java.lang.String nombreContacto, 
            java.lang.String comentario) {
    	
    	Calificador calificador = CalificadorDAO.create(documento, tipoDocumento, 
    			nombrePersona, apellido, nombreContacto, comentario, null);
    	HibernateFactory.getSession().save(calificador);
    	
    	return calificador;    	
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Calificador object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Calificador findByPrimaryKey( java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Calificador object = (Calificador) session.get(Calificador.class, id);

        return object;
    }
    public static List findAll() throws HibernateException{
		Session session = HibernateFactory.getSession();
		
		Query query = session.createQuery("from Calificador as e order by e.nombreContacto asc");
        return query.list();		
	}
}
