package ar.org.sicel.persistence;

import java.util.Date;
import java.util.SortedSet;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;



/**
 * @author jorona
 */
public class EvtEstModificacion extends ar.org.sicel.persistence.EvtEstablecimiento {
    
	
	
	// --------------- attributes ---------------------
    private EvtEstablecimiento evtEstModificado;
	

    protected EvtEstModificacion() {
    }
    
    protected EvtEstModificacion(Establecimiento est, Date fecha, EvtEstablecimiento eventoModificado) {
    	super(est,fecha);
    	this.setEvtEstModificado(eventoModificado);
    	
    }

    public String getResumen(){
    	return "Evento modificado " +this.evtEstModificado.getNombreTipo()+". ";
    }

	/*
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		//return this.evtEstModificado.getNombreTipo();
		return EVT_TIPO_MODIFEST;
	}
	
	public EvtEstablecimiento getEvtEstModificado() {
		return evtEstModificado;
	}

	public void setEvtEstModificado(EvtEstablecimiento evtEstablecimiento) {
		this.evtEstModificado = evtEstablecimiento;
	}

	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
    }

    public void ejecutarBaja() throws ExcepcionIntegridad {
    	Evento ev = this.getEvtEstModificado();
		this.setEvtEstModificado(null);
		EvtEstablecimientoDAO.update(ev);
		EvtEstablecimientoDAO.deleteEvtEstab(this);
    }

    public SortedSet recolectarTodosEventosDependientes(){
    	return this.getDependientes();
    }
}
