/*
 * Created on 13/04/2005
 */
package ar.org.sicel.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;


/**
 * @author pablo
 * 
 * Esta clase se usa para acceder a los parametros de configuracion de Sicel3 que reciden en <b>sicel3.conf.xml</b>.
 * Alli se encuentran, entre otros, la especificacion de los handlers a utilizar para cada evento (ver HandlerEvtAnimal),
 * los directorios usados para el procesamiento, etc.
 * 
 * 
 */
public class Sicel3Conf {
    private static Sicel3Conf instance;
    private static Logger log = Logger.getLogger(Sicel3Conf.class);
    private Configuration conf;

    private Sicel3Conf() {
        try {
            log.debug(
                "Cargando configuracion desde el recurso /sicel3.conf.xml");

            InputStream input = this.getClass().getResourceAsStream("/sicel3.conf.xml");
            DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
            conf = builder.build(input);
            log.info("Configuracion cargada");
        } catch (SAXException e) {
            log.error("Error al leer la configuracion, xml no valido", e);
        } catch (IOException e) {
            log.error("Error al leer la configuracion, compruebe que el archivo sicel3.conf.xml se encuentre en el path",
                e);
        } catch (ConfigurationException e) {
            log.error("Error al cargar la configuracion", e);
        }
    }

    public static Sicel3Conf getConf() {
        if (instance == null) {
            instance = new Sicel3Conf();
        }

        return instance;
    }

    public Configuration getConfProcEstablecimientos() {
        return conf.getChild("ProcEstablecimientos");
    }

    public Configuration getConfAmbiente() {
        return conf.getChild("ambiente");
    }

    public Configuration getConfProcesamiento() {
        return conf.getChild("procesamiento");
    }

    public Configuration getConfProcEventoAnimal() {
        Configuration cf = conf.getChild("ProcEventoAnimal");
        return cf;
    }
}
