package ar.org.sicel.proc;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * Tarea ant para hacer un "rollback" de un lote entero.
 * Espera que se le seteen dos parametros: 
 * <dl>
 * 	<dt>eclo</dt><dd>Numero de eclo</dd>
 *  <dt>lote</dt><dd>Numero de lote</dd>
 * </dl>
 * Con estos datos, se elimina el lote "numero de lote" informado por la eclo "numero de eclo".
 * Notar que el numero de evento para la eclo seguira su cuenta, es decir, si en el lote eliminado el ultimo
 * evento es el numero 1000, aunque el lote haya sido eliminado en lo susesivo no se podran informar eventos con
 * id menores o iguales 1000 para dicha eclo, porque esos id ya han sido "utilizados". 
 * 
 * @author user
 *
 */
public class RollbackProcessTask extends Task {
	
	long eclo;
	long lote;

	public long getEclo() {
		return eclo;
	}
	public void setEclo(long eclo) {
		this.eclo = eclo;
	}
	public long getLote() {
		return lote;
	}
	public void setLote(long lote) {
		this.lote = lote;
	}

	public void execute() throws BuildException {
		try {
			StandaloneHibernateStrategy.getInstance().openNewSession();
			ProcProces.elimProcesoYEventos(getEclo(),getLote());
			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		} catch (ExcepcionIntegridad e) {
			this.log("No se pudo eliminar el lote, causa: " + e.getMessage());
			StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
			StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
		}
	}
	
	

}
