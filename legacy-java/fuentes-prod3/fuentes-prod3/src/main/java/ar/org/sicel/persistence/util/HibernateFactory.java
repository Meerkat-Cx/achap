/*
 * Created on 12/04/2005
 */
package ar.org.sicel.persistence.util;

import org.hibernate.Session;


/**
 * @author pablo
 * 
 * Factory usada para obtener la Session de hibernate a utilizar.
 * De cualquier punto del sistema, se puede invocar a HibernateFactory.getSession() para obtener la session
 * adecuada, sin manejar aqui explicitamente las transacciones.
 * La manera en que se obtiene la Session varia dependiendo del ambiente. 
 * Si son manejadas por JBoss, entonces se utiliza una estrategia JBossHibernateStrategy, que redirecciona los pedidos de session
 * de forma adecuada a JBoss, y deja que este maneje las transacciones,etc.
 * Si no son manejadas por JBoss, por ejemplo porque se estan corriendo test, o cualquier cosa que se ejecute standalone
 * fuera del contenedor, entonces se usa una estrategia StandaloneHibernateStrategy, la cual se encarga de llevar el rastro
 * de las sessiones, cerrarlas,etc. 
 * Cual caso corresponde usar se especifica en sicel3.conf.xml
 * Notar que si se usa la estrategia Standalone, entonces se debera abrir explicitamente una session antes de utilizar
 * este Factory y cerrarla despues de su uso (ver como estan echos los test)  
 * 
 */
public class HibernateFactory {
    private static HibernateStrategy factory;


    public static Session getSession() {
        if (factory == null) {
            cargarFactory();
        }

        return factory.getCurrentSession();
    }

    private static void cargarFactory() {
//        try {
//            if (Sicel3Conf.getConf().getConfAmbiente().getAttribute("environment").equals(J2EE)) {
//                factory = JBossHibernateStrategy.getInstance();
//            } else if (Sicel3Conf.getConf().getConfAmbiente().getAttribute("environment").equals(J2EEJTA)){
//                factory = JBossHibernateStrategyJTA.getInstance();
//            } else {
//                factory = StandaloneHibernateStrategy.getInstance();
//            }
//        } catch (ConfigurationException e) {
//            log.error("Se debe especificar el atributo booleano j2ee en el elemento ambiente de la configuracion de sicel3",
//                e);
//        }
    	factory = StandaloneHibernateStrategy.getInstance();
    }
}
