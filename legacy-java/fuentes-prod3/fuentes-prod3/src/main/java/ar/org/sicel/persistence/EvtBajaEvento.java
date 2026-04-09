package ar.org.sicel.persistence;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * @author pablo
 *
 * @hibernate.joined-subclass
 *    table="Ev_Baja_Ev"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvBajaEv_EvEstab"
 *
 *
 * Evento para indicar la baja de otro evento.
 * El evento a dar de baja es el indicado en idEventoBaja
 */
public class EvtBajaEvento extends EvtEstablecimiento {
	private Long idEventoBaja;
	

    public EvtBajaEvento() {
    }
    
    public EvtBajaEvento(Establecimiento est, Date fecha) {
    	super(est,fecha);
    }

	
    /**
     * 
     * @hibernate.property column="idEventoBaja"
     * @hibernate.column name="idEventoBaja" not-null="true"
     * 
     */
	public Long getIdEventoBaja() {
		return idEventoBaja;
	}

	public void setIdEventoBaja(Long idEventoBaja) {
		this.idEventoBaja = idEventoBaja;
	}
	
	
	//------------------------------------
	

	@Override
	protected String getResumen() {
		return "EvtBajaEvento, evento a dar de baja;" + idEventoBaja;
	}

	@Override
	public String getNombreTipo() {
		return EvtEstablecimiento.EVT_TIPO_BAJA_EVENTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SortedSet recolectarTodosEventosDependientes() {
		return new TreeSet(new EventosPorFechaYTipo());
	}

	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		
	}
	public boolean equals(Object eve){
    	if(eve!=null){
    	if(!(eve instanceof EvtBajaEvento))
			return false;
		EvtBajaEvento ev = (EvtBajaEvento)eve;
    	
    		if((ev.getId()!=null && this.getId()!=null)&&(ev.getId().equals(this.getId())))
    			return true;
    	}
		return false;
    	
    }

}
