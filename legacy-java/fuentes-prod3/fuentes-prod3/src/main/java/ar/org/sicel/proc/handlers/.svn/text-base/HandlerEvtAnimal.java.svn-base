/*
 * Created on 13/04/2005
 */
package ar.org.sicel.proc.handlers;

import java.util.Date;
import java.util.List;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Evt;


/**
 * @author pablo
 * 
 * Esta interface representa a un objeto capaz de procesar un determinado tipo de evento de animal.
 * Es su responsabilidad tomarlo en su formato XML(castor), interpretarlo y producir los objetos
 * que seran persistidos en la base de datos.
 * Existe un Handler especifico para cada tipo de evento de animal. Sicel3 se configura mapeando en
 * el archivo sicel3.conf.xml, dentro de la seccion <b>handlers</b>,cada posible evento a un Handler 
 * encargado de procesar esos tipos de eventos. El evento se especifica dando la clase Castor que lo representa,
 * y el handler dando la Clase del handler Correspondiente.
 * 
 * Si cambia el formato del xml de algun evento, es por el handler correspondiente por donde hay que empezar
 * a rastrear los cambios, ya que es la parte del sistema que trata con los eventos en XML. 
 * La parte de persistencia solo habra que cambiarla si se agregan/modifican datos o reglas, o si los cambios 
 * no se pueden resolver en los handlers.   
 * 
 * En los handlers, luego de producir los eventos, se guardan explicitamente en la base. Esto es asi para forzar
 * a Hibernate a que les asigne un ID, ya que el id del evento se devuelve en el XML resultado, y por lo tanto
 * tiene que estar disponible ni bien se termina de procesar ese evento, para poder incluirlo en el nodo correspondiente
 * del XML.  
 * 
 * 
 */
public interface HandlerEvtAnimal {
    /**
    * Procesa el evento <code>evento</code>(Castor), producido en el
    * animal objAnimal. Devuelve como resultado el Evento (Hibernate)
    * resultado del procesamiento
    *
    * @param objAnimal
    * @param evento
    * @return objEvento
    */
    public EvtAnimal handleEvtAnimal(Establecimiento establecimiento,
        Animal objAnimal, Evt evento, List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad;
}
