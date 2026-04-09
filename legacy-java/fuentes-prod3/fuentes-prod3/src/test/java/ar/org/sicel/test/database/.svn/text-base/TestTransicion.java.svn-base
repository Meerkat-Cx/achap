/*
 * Created on 29/04/2005
 */
package ar.org.sicel.test.database;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import ar.org.sicel.persistence.Transicion;
import ar.org.sicel.persistence.TransicionDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author ala
 */
public class TestTransicion extends TestCase {

    public void testFindAll() throws Exception {
        StandaloneHibernateStrategy st = StandaloneHibernateStrategy
                .getInstance();
//        Session session = st.openNewSession();
        List transiciones = TransicionDAO.findAll();
        for (Iterator iter = transiciones.iterator(); iter.hasNext();) {
            Transicion transicion = (Transicion) iter.next();
            System.out.println(transicion);
        }
        st.commitCurrentSession();
    }

    public void testFindByEstado() throws Exception {
        StandaloneHibernateStrategy st = StandaloneHibernateStrategy
                .getInstance();
//        Session session = st.openNewSession();
        Transicion transicion = TransicionDAO.findByOriEvt("SS", "EvtServicio");
        System.out.println(transicion);
        st.commitCurrentSession();
    }

    public void testFindByEstadoLact() throws Exception {
        StandaloneHibernateStrategy st = StandaloneHibernateStrategy
                .getInstance();
//        Session session = st.openNewSession();
        Transicion transicion = TransicionDAO.findByOriEvtIniciaLact("SV", "EvtReproduccion",true);
        System.out.println("Hallada");
        System.out.println(transicion);
        System.out.println("Buscada");
        System.out.println("( SV , EvtReproduccion )--> PV [ 1 , 0 ] CriaVaci ( 5 )");
        assertEquals(transicion.toString().compareTo("( SV , EvtReproduccion )--> PV [ 1 , 0 ] CriaVaci ( 5 )"),0);
        st.commitCurrentSession();
    }

}
