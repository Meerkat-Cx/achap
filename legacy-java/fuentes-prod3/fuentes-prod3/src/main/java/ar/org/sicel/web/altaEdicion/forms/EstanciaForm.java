package ar.org.sicel.web.altaEdicion.forms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.util.DateUtils;

public class EstanciaForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String nombre;
	private String nombreEclo;
	private String nombrePropietario;
	//private String cuig;
	
	private Long idPropietario;
	//private Long idEclo;
	private Boolean activo = new Boolean(true);
	private Boolean activoPropietario = new Boolean(true);
	private String cuit;
	private String cuig = "";
	private String renspa;
	private String fechaLog;

	
	public String getFechaLog() {
		return fechaLog;
	}

	public void setFechaLog(String fechaLog) {
		this.fechaLog = fechaLog;
	}

	public String getCuig() {
		return cuig;
	}

	public void setCuig(String cuig) {
		this.cuig = cuig;
	}
	
	public String getRenspa() {
		return renspa;
	}

	public void setRenspa(String renspa) {
		this.renspa = renspa;
	}
	

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	
	//private Boolean activoEclo = new Boolean(true);
	/*public Boolean getActivoEclo() {
		return activoEclo;
	}
	public void setActivoEclo(Boolean activoEclo) {
		this.activoEclo = activoEclo;
	}*/
	public Boolean getActivoPropietario() {
		return activoPropietario;
	}
	public void setActivoPropietario(Boolean activoPropietario) {
		this.activoPropietario = activoPropietario;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/*public String getCuig() {
		return cuig;
	}
	public void setCuig(String cuig) {
		this.cuig = cuig;
	}*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreEclo() {
		return nombreEclo;
	}
	public void setNombreEclo(String nombreEclo) {
		this.nombreEclo = nombreEclo;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	/*public Long getIdEclo() {
		return idEclo;
	}
	public void setIdEclo(Long idEclo) {
		this.idEclo = idEclo;
	}*/
	public Long getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(Long idPropietario) {
		this.idPropietario = idPropietario;
	}
	 public void reset(ActionMapping actionmapping, HttpServletRequest httpservletrequest) {
		  super.reset(actionmapping, httpservletrequest);
		  this.setCuig("");
		  this.setRenspa("");
		  this.setCuit("");
		  this.setNombre("");
		  //this.setNombreEclo("");
		  this.setNombrePropietario("");
		 // this.setIdEclo(null);
		  this.setIdPropietario(null);
		  this.setId(null);
		  this.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
	 }
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	

}
