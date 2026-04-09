/**
 * 
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
 * @author jivars
 *
 */
public abstract class HandlerEvt {

	public abstract EvtAnimal handleEvtAnimal(Establecimiento est, Animal objAnimal,
			Evt evento, List<ProcMsg> mensajes,ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad;
	
	protected void setAnimalEntidades(Animal animal, Establecimiento tambo){
		if(animal.getEstablecimiento()== null){
			animal.setEstablecimiento(tambo);
			animal.setEstancia(tambo.getEstancia());
			animal.setPropietario(tambo.getPropietario());
		}
	}
}
