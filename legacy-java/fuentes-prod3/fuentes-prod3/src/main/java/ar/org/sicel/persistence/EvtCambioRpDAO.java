/**
 * 
 */
package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * @author jdivars
 *
 */
public class EvtCambioRpDAO {

	public static EvtCambioRp create(Establecimiento est, Animal animal, Date fecha,List msgs,String rpNuevo,ProcLote procLote) throws ExcepcionIntegridad {
		EvtCambioRp cambio = new EvtCambioRp(est,fecha,animal,msgs);
		Animal.checkUnicidadRPEnEstabForUpdate(animal.getId(),est,rpNuevo,msgs,animal.getFechaNac(),animal.getEstablecimiento(),animal.getRegistroOrigen(),animal.getCategoria());
		cambio.setRpNuevo(rpNuevo);
		cambio.setRpAnterior(animal.getRP());
		//animal.setRP(rpNuevo);
		animal.addEventoAnimal(cambio,msgs);
		//if(!animal.getRegOrigen().getTipoRegistro().equals("HBA")){
		animal.setRP(rpNuevo);
		if(!animal.getRegistroOrigen().contains("HBA")){	
		FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
			fichaAnimal.setAnimal(animal);
			procLote.agregarFichaAnimal(fichaAnimal);
		}
		return cambio;
	}
}
