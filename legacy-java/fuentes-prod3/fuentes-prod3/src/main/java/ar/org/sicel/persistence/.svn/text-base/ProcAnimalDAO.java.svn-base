package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.services.impl.RazaUtil;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcAnimal.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcAnimal
 */
public abstract class ProcAnimalDAO {
	
    // ---------------- create method --------------------

    /**
     * Creates a(n) ProcAnimal object.
     *
     * @return ProcAnimal the created object
     */
    public static ProcAnimal create(ar.org.sicel.proc.v1.lote.Animal nodoAnimal, Establecimiento est, String tReg, String nReg,String rp) throws ExcepcionIntegridad{
    	String raza2 = RazaUtil.getRazaEnBase(nodoAnimal.getReg().getRaza());
    	//tendria que ser con sexo
    	Animal an = AnimalDAO.findExistentByRegistry(tReg,nReg,raza2,nodoAnimal.getReg().getSexo().toString()); 
       
        
        if ((an.getRP()==null) ||(! an.getRP().trim().equals(rp.trim()))) {
        	throw new ExcepcionIntegridad(MENSAJES.RP_NO_COINCIDE,new String[]{tReg,nReg,rp,an.getRP()});
        }
    	
        ProcAnimal object = new ProcAnimal();
        object.setProcEvtAnimals(new HashSet());
        object.setProcMsgsses(new HashSet());
        //object.addAnimal(an);
        object.setAnimal(an);
       
        return object;
    }
    
    
   

    

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcAnimal object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProcAnimal findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ProcAnimal object = (ProcAnimal) session.get(ProcAnimal.class, id);

        return object;
    }
    
    public static List findByAnimal(Long idAnimal) throws org.hibernate.HibernateException {
   		Query query =null;
   		
   		query = HibernateFactory.getSession().createQuery("from ProcAnimal where animal = "+ idAnimal);
        return query.list();
        
	}
    
    
    
 
}
