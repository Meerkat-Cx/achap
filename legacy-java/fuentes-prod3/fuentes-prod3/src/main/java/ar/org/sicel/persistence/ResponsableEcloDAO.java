package ar.org.sicel.persistence;

import java.util.HashSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ResponsableEclo.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ResponsableEclo
 */
public abstract class ResponsableEcloDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ResponsableEclo object.
     *
     * @param documento
     * @param tipoDocumento
     * @param nombrePersona
     * @param apellido
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return ResponsableEclo the created object
     */
    public static ResponsableEclo create(java.lang.Integer documento,
        java.lang.String tipoDocumento, java.lang.String nombrePersona,
        java.lang.String apellido, java.lang.String nombreContacto,
        java.lang.String comentario, byte[] foto) {
        ResponsableEclo object = new ResponsableEclo();

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
    
    
    public static ResponsableEclo createPersistent(java.lang.Integer documento,
            java.lang.String tipoDocumento, java.lang.String nombrePersona,
            java.lang.String apellido, java.lang.String nombreContacto,
            java.lang.String comentario, byte[] foto) {
    	
    	ResponsableEclo responsableEclo = ResponsableEcloDAO.create(documento, tipoDocumento, nombrePersona, apellido, nombreContacto, comentario, null);
    	if (foto != null) {
    		Foto imagen = FotoDAO.createPersistent(foto);
    		responsableEclo.setFoto(imagen);
    	}
    	
    	HibernateFactory.getSession().save(responsableEclo);
    	return responsableEclo;
    }
            
    	

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ResponsableEclo object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ResponsableEclo findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ResponsableEclo object = (ResponsableEclo) session.get(ResponsableEclo.class,
                id);

        return object;
    }
}
