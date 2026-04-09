package ar.org.sicel.persistence;

import java.util.HashSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type PersonaOtro.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.PersonaOtro
 */
public abstract class PersonaOtroDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) PersonaOtro object.
     *
     * @param documento
     * @param tipoDocumento
     * @param nombrePersona
     * @param apellido
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return PersonaOtro the created object
     */
    public static PersonaOtro create(java.lang.Integer documento,
        java.lang.String tipoDocumento, java.lang.String nombrePersona,
        java.lang.String apellido, java.lang.String nombreContacto,
        java.lang.String comentario, byte[] foto) {
        PersonaOtro object = new PersonaOtro();

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
    
    public static PersonaOtro createPersistent(java.lang.Integer documento,
            java.lang.String tipoDocumento, java.lang.String nombrePersona,
            java.lang.String apellido, java.lang.String nombreContacto, 
            java.lang.String comentario) {
    	
    	PersonaOtro personaOtro = PersonaOtroDAO.create(documento, tipoDocumento, nombrePersona, apellido, nombreContacto, comentario, null);
    	HibernateFactory.getSession().save(personaOtro);
    	
    	return personaOtro;    	
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds PersonaOtro object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static PersonaOtro findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        PersonaOtro object = (PersonaOtro) session.get(PersonaOtro.class, id);

        return object;
    }
}
