package ar.org.sicel.upload.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import ar.org.sicel.persistence.ProcProces;

public class Bajada {
	
	private Long id;
	private ProcProces proceso;
	private Date fecha;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public ProcProces getProceso() {
		return proceso;
	}
	public void setProceso(ProcProces proceso) {
		this.proceso = proceso;
	}
	public String getFechaString(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.getFecha());
	}
	public String getHoraString(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(this.getFecha());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
