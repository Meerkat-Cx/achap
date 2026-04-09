package ar.org.sicel.persistence;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Valor.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Valor
 */
public abstract class ValorDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Valor object.
     *
     * @return Valor the created object
     */
    public static Valor create(java.util.Date inicio, ValorAdmAtr valor) {
        Valor object = new Valor();
        object.setInicio(inicio);
        object.setValorAdmAtr(valor);
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Valor object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Valor findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Valor object = (Valor) session.get(Valor.class, id);

        return object;
    }
    
    /**
     * Obtiene el historial de valores de un Attributo para un
     * determinado AtrVariables (por ejemplo un Establecimiento determinado,
     * o una Especie determinada)
     * 
     * @param atVs
     * @param at
     * @return
     * @throws HibernateException
     */
    public static List findByAttributo(AtrVariables atVs, Atributo at) throws HibernateException {
    	Session session = HibernateFactory.getSession();
    	String queryText = "from Valor v where v.atrVariable = :atVs  and v.valorAdmAtr.atributo = :at";
    	Query query = session.createQuery(queryText);
    	query.setEntity("atVs",atVs);
    	query.setEntity("at",at);
    	return query.list();
    }

    public static List find(String idAtributo, String idEntidad) throws HibernateException {
    	Session session = HibernateFactory.getSession();
    	Criteria criterio = session.createCriteria(Valor.class);
    	Criteria c1 = criterio.createCriteria("valorAdmAtr");
		Criteria c2 = c1.createCriteria("atributo");
        if (StringUtils.isNotEmpty(idAtributo)){
        	c2.add(Expression.eq("id", Long.valueOf(idAtributo)));
        }
        if (StringUtils.isNotEmpty(idEntidad)){
    		Criteria c3 = c2.createCriteria("conjuntoAt");
        	c3.add(Expression.eq("id", Long.valueOf(idEntidad)));
        }
        return criterio.list();
    }

    
}

