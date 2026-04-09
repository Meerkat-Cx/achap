/*
 * Created on 12/04/2005
 */
package ar.org.sicel.persistence.util;

import org.hibernate.Session;


/**
 * @author pablo
 */
public interface HibernateStrategy {
    Session getCurrentSession();
}
