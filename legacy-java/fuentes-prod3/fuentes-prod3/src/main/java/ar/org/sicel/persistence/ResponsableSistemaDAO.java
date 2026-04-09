package ar.org.sicel.persistence;

import java.util.HashSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ResponsableSistema.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ResponsableSistema
 */
public abstract class ResponsableSistemaDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ResponsableSistema object.
     *
     * @param documento
     * @param tipoDocumento
     * @param nombrePersona
     * @param apellido
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return ResponsableSistema the created object
     */
    public static ResponsableSistema create(java.lang.Integer documento,
        java.lang.String tipoDocumento, java.lang.String nombrePersona,
        java.lang.String apellido, java.lang.String nombreContacto,
        java.lang.String comentario, byte[] foto) {
        ResponsableSistema object = new ResponsableSistema();

        object.setDocumento(documento);
        object.setTipoDocumento(tipoDocumento);
        object.setNombrePersona(nombrePersona);
        object.setApellido(apellido);
        object.setNombreContacto(nombreContacto);
        object.setComentario(comentario);
        //object.setFoto(foto);
        object.setSistema(new Sistema());

        object.setUbicacions(new HashSet());

        return object;
    }
    
    public static ResponsableSistema createPersistent(java.lang.Integer documento,
            java.lang.String tipoDocumento, java.lang.String nombrePersona,
            java.lang.String apellido, java.lang.String nombreContacto, 
            java.lang.String comentario) {
    	
    	ResponsableSistema responsableSistema = ResponsableSistemaDAO.create(documento, 
    			tipoDocumento, nombrePersona, apellido, nombreContacto, comentario, null);
    	    	
    	HibernateFactory.getSession().save(responsableSistema);
    	return responsableSistema;  	
    }
            
    

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ResponsableSistema object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ResponsableSistema findByPrimaryKey(
        java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ResponsableSistema object = (ResponsableSistema) session.get(ResponsableSistema.class,
                id);

        return object;
    }
}
