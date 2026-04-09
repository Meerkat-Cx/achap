package ar.org.sicel.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Ubicacion.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Ubicacion
 */
public abstract class UbicacionDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Ubicacion object.
     *
     * @param nombre
     * @param ciudad
     * @param provinciaRegion
     * @param pais
     * @param direccion
     * @param codigoPostal
     * @param mail
     * @param telefono
     * @return Ubicacion the created object
     */
     public static Ubicacion create (java.lang.String nombre, java.lang.String ciudad, java.lang.String provinciaRegion, java.lang.String pais, java.lang.String direccion, java.lang.String codigoPostal, java.lang.String mail, java.lang.String telefono)
     {
         Ubicacion object = new Ubicacion();

         object.setNombre (nombre);
         object.setCiudad (ciudad);
         object.setProvinciaRegion (provinciaRegion);
         object.setPais (pais);
         object.setDireccion (direccion);
         object.setCodigoPostal (codigoPostal);
         object.setMail (mail);
        object.setTelefono (telefono);
        
         return object;
     }
     
     public static Ubicacion createPersistent (java.lang.String nombre, java.lang.String ciudad, java.lang.String provinciaRegion, java.lang.String pais, java.lang.String direccion, java.lang.String codigoPostal, java.lang.String mail, java.lang.String telefono, Contacto contacto) {
    	 Ubicacion ubicacion = UbicacionDAO.create(nombre, ciudad, provinciaRegion, pais, direccion, codigoPostal, mail, telefono);
    	 ubicacion.setContacto(contacto);
    	 HibernateFactory.getSession().save(ubicacion);
    	 return ubicacion;   	 
     }
     

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Ubicacion object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Ubicacion findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Ubicacion object = (Ubicacion) session.get(Ubicacion.class, id);

        return object;
    }
    
	public static void updateUbicacion(Ubicacion ubicacion) 		 
	throws HibernateException {
		HibernateFactory.getSession().update(ubicacion);
	}

}
