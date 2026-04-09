/*
 * Created on 11/04/2005
 */
package ar.org.sicel.test.util;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EntidadRegional;
import ar.org.sicel.persistence.EntidadRegionalDAO;


/**
 * @author ala
 */
public class TestHibernate {
    public static void main(String[] args) {
        try {
            Configuration cf = new Configuration();
            cf.configure();

//            SessionFactory factory = cf.buildSessionFactory();
//            Session session = factory.openSession();
            Iterator it = EntidadRegionalDAO.findAll().iterator();

            while (it.hasNext()) {
                EntidadRegional reg = (EntidadRegional) it.next();
                System.out.println(reg.getNombreContacto() + ":");

                Iterator eclos = reg.getEclos().iterator();

                while (eclos.hasNext()) {
                    Eclo eclo = (Eclo) eclos.next();
                    System.out.println("--" + eclo.getNombreContacto());
                }

                System.out.println("*********************");
            }

            //Eclo eclo = (Eclo) session.load(Eclo.class,id);
            //System.out.println("Nombre :" + eclo.getNombrePersona());
            //System.out.println("Regional: " +  eclo.getRegional().getNombrePersona());
            //System.out.println("Responsable:" + eclo.getResponsable());
            //System.out.println("Sistema:" + eclo.getSistema());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
