package ar.org.sicel.persistence;

import java.util.Date;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * @author jorona 
 */
public class EvtAnimalModificacion extends EvtAnimal {

	private EvtAnimal evtAnimalModificado;
	
	protected EvtAnimalModificacion() {
		super();
	}
	
	public EvtAnimalModificacion(Establecimiento est, Date fecha, Animal animal, EvtAnimal eventoModificado) throws ExcepcionIntegridad {
		super(est, fecha, animal, null,null);
		this.setEvtAnimalModificado(eventoModificado);
		this.setEstadoAnimal(eventoModificado.getEstadoAnimal());
	}


    // ---------------- business methods ----------------------	
	
	
	
	protected String getResumen() {
		return "Evento animal modificado: "+this.getNombreTipoEvtModif()+ " "+this.evtAnimalModificado.getId();
	}


	public String getNombreTipo() {
		//return this.evtAnimalModificado.getNombreTipo();
		return EVT_TIPO_MODIFANI;
	}
	public String getNombreTipoEvtModif() {
		return this.evtAnimalModificado.getNombreTipo();
		//return EVT_TIPO_MODIFANI;
	}

	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		Evento ev = this.getEvtAnimalModificado();
		this.setEvtAnimalModificado(null);
		EvtAnimalDAO.update(ev);
		EvtAnimalDAO.deleteEvtAnim(this);
	}

	public EvtAnimal getEvtAnimalModificado() {
		return evtAnimalModificado;
	}

	public void setEvtAnimalModificado(EvtAnimal evtAnimalModificado) {
		this.evtAnimalModificado = evtAnimalModificado;
	}
	/*public boolean equals(Object eve){
    	if(eve!=null){
    	if(!(eve instanceof EvtAnimalModificacion))
			return false;
		EvtAnimalModificacion ev = (EvtAnimalModificacion)eve;
    	
    		if((ev.getId()!=null && this.getId()!=null)&&(ev.getId().equals(this.getId())))
    			return true;
    	}
		return false;
    	
    }*/

}
