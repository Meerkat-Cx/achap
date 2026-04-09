package ar.org.sicel.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * 
 * @author obascuas
 */
public class FichaAnimalPendiente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7833552302579332592L;

	private Long id;
	
	private Date fecha = new Date();
	
	private Animal animal;
	
	private Boolean pendiente = Boolean.TRUE;
	
	//private Long loteId;
	
	//private Long ecloId;
	
	//private Long animalId;
	
	private ProcLote procLote;
	

	

	/*public Long getEcloId() {
		return ecloId;
	}

	public void setEcloId(Long ecloId) {
		this.ecloId = ecloId;
	}*/

	/*public Long getLoteId() {
		return loteId;
	}

	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getPendiente() {
		return pendiente;
	}

	public void setPendiente(Boolean pendiente) {
		this.pendiente = pendiente;
	}

	public ProcLote getProcLote() {
		return procLote;
	}

	public void setProcLote(ProcLote procLote) {
		this.procLote = procLote;
	}
	
	
}
