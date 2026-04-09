package ar.org.sicel.proc;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import ar.org.sicel.persistence.CentroDeComputo;
import ar.org.sicel.persistence.CentroDeComputoDAO;
import ar.org.sicel.persistence.Eclo;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.ProcEvtAnimal;
import ar.org.sicel.persistence.ProcEvtAnimalDAO;
import ar.org.sicel.persistence.ProcEvtEst;
import ar.org.sicel.persistence.ProcEvtEstDAO;
import ar.org.sicel.persistence.Sistema;
import ar.org.sicel.persistence.SistemaDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * Tarea ant para dar de baja manualmente un evento.
 * Dar de baja un evento consiste en eliminar el evento junto a todos sus eventos dependientes.
 * Por ejemplo, al eliminar un parto en fecha 10/05/2000 se eliminan:
 * <ul>
 * <li>El evento parto en si</li>
 * <li>La lactancia cerrada que el parto cerro(en caso de que esta exista)</li>
 * <li>Todos los eventos con fecha posterior al 10/05/2000 del mismo animal</li>
 * <li>Todos los animales juntos con sus eventos que hayan sido consecuencia del evento dado de
 * baja o alguno de sus dependientes, en forma recursiva.Es decir, se dan de baja los hijos, nietos, etc,
 * que hayan nacido como consecuencia de eventos dependientes del dado de baja.</li>
 * </ul>
 * 
 * @author user
 *
 */
public class EliminarEventoTask extends Task {
	Long eclo;
	Long evento; 
	Long sistema;
	Long centro;
	public Long getCentro() {
		return centro;
	}
	public void setCentro(Long centro) {
		this.centro = centro;
	}
	public Long getSistema() {
		return sistema;
	}
	public void setSistema(Long sistema) {
		this.sistema = sistema;
	}
	public Long getEclo() {
		return eclo;
	}
	public void setEclo(Long eclo) {
		this.eclo = eclo;
	}
	public Long getEvento() {
		return evento;
	}
	public void setEvento(Long evento) {
		this.evento = evento;
	}
	
	
	public void execute() throws BuildException {
		Eclo eclo = EcloDAO.findByPrimaryKey(getEclo());
		Sistema sistema = SistemaDAO.findByPrimaryKey(getSistema());
		CentroDeComputo centro = CentroDeComputoDAO.findByPrimaryKey(getCentro());
		 eclo = EcloDAO.findByPrimaryKey(getEclo());
		ProcEvtAnimal procAnimal = ProcEvtAnimalDAO.findByEclo(eclo,sistema,centro,getEvento());
		Evento evt = null;
		if (procAnimal != null) {
			evt = procAnimal.getEvtAnimal();
		} else {
			ProcEvtEst procEst = ProcEvtEstDAO.findByEclo(eclo,sistema,centro,getEvento());
			if (procEst != null)
				evt = procEst.getEvtEstablecimiento();
		}
		if (evt!=null) {
			try {
				evt.darDeBaja();
			} catch (ExcepcionIntegridad e) {
				this.log("No se pudo eliminar el evento, causa: "  + e.getMessage());
			}
		} else {
			this.log("No se pudo eliminar el evento, causa: el evento no existe, compruebe el numero de eclo y de evento");
		}
	}
	
	
	

}
