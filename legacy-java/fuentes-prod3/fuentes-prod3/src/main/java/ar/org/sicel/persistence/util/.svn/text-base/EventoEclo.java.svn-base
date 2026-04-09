package ar.org.sicel.persistence.util;

import ar.org.sicel.persistence.Raza;
/**
 * Esta clase antes tenia como funcionalidad la identificacion univoca de numeracion de 
 * evento para la eclo, por razones funcionales la identificacion univoca cambio y es para 
 * eclo-sistema-centro de computo con lo que el nombre de la clase no se corresponde con la funcionalidad
 * que tiene
 * 
 * @author Usuario
 *
 */
public class EventoEclo {

	private Long idEclo;
	private Long idSistema;
	private Long idCentro;
	private Long idEvento;
	
	
	public EventoEclo(Long didEclo,Long didSistema,Long didCentro, Long didEvento){
		this.idEclo = didEclo;
		this.idEvento = didEvento;
		this.idSistema = didSistema;
		this.idCentro = didCentro;
	}
	
	
	public Long getIdEclo() {
		return idEclo;
	}
	
	public void setIdEclo(Long idEclo) {
		this.idEclo = idEclo;
	}
	
	public Long getIdEvento() {
		return idEvento;
	}
	
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
	
	public int hashCode(){
		if(idEclo != null && idEvento != null && idCentro !=null && idSistema!=null)
			return idEclo.hashCode() & idEvento.hashCode() & idCentro.hashCode() & idSistema.hashCode();
		if(idEclo != null && idEvento != null && idCentro ==null && idSistema!=null)
			return idEclo.hashCode() & idEvento.hashCode() &  idSistema.hashCode();
		else
	    	return super.hashCode();
	}
	    
    public boolean equals(Object obj) {
    	if(!(obj instanceof EventoEclo))
    		return false;
    	EventoEclo evtEclo = (EventoEclo) obj;
    	if(this.getIdCentro()!=null && evtEclo.getIdCentro() !=null)
    		return this.idCentro.equals(evtEclo.getIdCentro())&&this.idSistema.equals(evtEclo.getIdSistema())&&this.idEclo.equals(evtEclo.getIdEclo())&&this.idEvento.equals(evtEclo.getIdEvento());
    	if(this.getIdCentro()==null && evtEclo.getIdCentro() ==null)
    		return this.idSistema.equals(evtEclo.getIdSistema())&&this.idEclo.equals(evtEclo.getIdEclo())&&this.idEvento.equals(evtEclo.getIdEvento());
    	return false;
    }

	public Long getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}

	public Long getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}
	
}
