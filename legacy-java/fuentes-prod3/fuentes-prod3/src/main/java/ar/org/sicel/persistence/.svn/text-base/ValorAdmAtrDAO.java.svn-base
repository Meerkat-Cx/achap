package ar.org.sicel.persistence;

import org.hibernate.Session;

import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ValorAdmAtr.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ValorAdmAtr
 */
public abstract class ValorAdmAtrDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ValorAdmAtr object.
     *
     * @param valor
     * @return ValorAdmAtr the created object
     */
    @SuppressWarnings("unchecked")
	public static ValorAdmAtr create(Atributo at, java.lang.String valor) {
        ValorAdmAtr object = new ValorAdmAtr();

        object.setValor(valor);
        object.setAtributo(at);
        at.getValoresAdmitidos().add(object);
        return object;
    }

    
    public static ValorAdmAtr create(String valor) {
    	ValorAdmAtr object = new ValorAdmAtr();  	
    	
    	return object;
    }
    
    
    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ValorAdmAtr object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ValorAdmAtr findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ValorAdmAtr object = (ValorAdmAtr) session.get(ValorAdmAtr.class, id);

        return object;
    }
}
