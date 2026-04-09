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
public class EvtCambioRp extends EvtAnimal {
	
	private String rpNuevo;
	private String rpAnterior;
	
	public EvtCambioRp(Establecimiento est, Date fecha, Animal animal, List msgs)throws ExcepcionIntegridad {
			super(est, fecha, animal, msgs,Evento.EVT_TIPO_CAMBIO_RP);
	}
	
	protected EvtCambioRp() {
    	
    }
	public String getRpAnterior() {
		return rpAnterior;
	}


	public void setRpAnterior(String rpAnterior) {
		this.rpAnterior = rpAnterior;
	}


	public String getRpNuevo() {
		return rpNuevo;
	}

	public void setRpNuevo(String rpNuevo) {
		this.rpNuevo = rpNuevo;
	}

	@Override
	protected String getResumen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombreTipo() {
		return EVT_TIPO_CAMBIO_RP;
	}

	@Override
	public boolean validarBaja() throws ExcepcionIntegridad {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ejecutarBaja() throws ExcepcionIntegridad {
		// TODO Auto-generated method stub
		
	}

}
