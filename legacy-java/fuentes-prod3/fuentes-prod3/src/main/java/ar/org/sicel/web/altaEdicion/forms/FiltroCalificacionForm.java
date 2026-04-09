/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * @author jdivars
 *
 */
public class FiltroCalificacionForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	private String rp = null;
    private Long estabId = null;
    private String tipoReg = null;
    private String numReg = null;
    private String raza = null;
    private String sexo = null;
    private String year = null;
    private String numeroBoleta = null;
	private String modelo = null;
	private String fechaCalificacion = null;
	private String estanciaId = null;
	private String estanciaNombre = null;
	private String calificadorId = null;
	private String dataEntryId = null;
	private String rango = "";
	private String fechaDesde = null;
	private String fechaHasta = null;
	private String rol = null;
	   
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
    
    public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void reset(){
    	this.setEstabId(null);
    	this.setRp("");
    	this.setTipoReg(""); 
    	this.setNumReg("");
    	this.setRaza("");
    	this.setSexo("");
    	this.setYear("");
    	this.setNumeroBoleta("");
    	this.setModelo("");
    	this.setEstanciaId("");
    	this.setFechaCalificacion("");
    	this.setEstanciaNombre("");
    	this.setCalificadorId("");
    	this.setDataEntryId("");
    	this.setFechaDesde(null);
    	this.setFechaHasta(null);
    	this.setRol(null);
    }
    public Long getEstabId() {
		return estabId;
	}
	public void setEstabId(Long estabId) {
		this.estabId = estabId;
	}
	public String getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public String getNumReg() {
		return numReg;
	}
	public void setNumReg(String numReg) {
		this.numReg = numReg;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTipoReg() {
		return tipoReg;
	}
	public void setTipoReg(String tipoReg) {
		this.tipoReg = tipoReg;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getFechaCalificacion() {
		return fechaCalificacion;
	}
	public void setFechaCalificacion(String fechaCalificacion) {
		this.fechaCalificacion = fechaCalificacion;
	}
   
    public String getEstanciaNombre() {
		return estanciaNombre;
	}
	public void setEstanciaNombre(String estanciaNombre) {
		this.estanciaNombre = estanciaNombre;
	}
    public String getCalificadorId() {
		return calificadorId;
	}
	public void setCalificadorId(String calificadorId) {
		this.calificadorId = calificadorId;
	}
    public String getDataEntryId() {
		return dataEntryId;
	}
	public void setDataEntryId(String dataEntryId) {
		this.dataEntryId = dataEntryId;
	}
	public String getEstanciaId() {
		return estanciaId;
	}
	public void setEstanciaId(String estanciaId) {
		this.estanciaId = estanciaId;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}