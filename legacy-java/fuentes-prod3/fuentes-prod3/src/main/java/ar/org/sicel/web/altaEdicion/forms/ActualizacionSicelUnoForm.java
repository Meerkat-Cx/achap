package ar.org.sicel.web.altaEdicion.forms;

import org.apache.struts.action.ActionForm;

public class ActualizacionSicelUnoForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nroRegistro;
	private String raza;
	private String razaActual;
	private String sexo;
	private String rp;
	private String nombre;
	private String regOri;
	private String categoriaAnimal;
	private String activoRazaMadre;
	private String usarRazaMadre;
	private String mostrarCombo;
	private String borrarMadre;
	private String borrarPadre;

	// MADRE ACTUAL
	private String rpMadreActual;
	private String regOriMadreActual;
	private String tipoRegOriMadreActual;
	private String fechaNacMadreActual;

	// PADRE ACTUAL
	private String rpPadreActual;
	private String regOriPadreActual;
	private String tipoRegOriPadreActual;
	private String fechaNacPadreActual;

	// MADRE
	private String rpMadre;
	private String regOriMadre;
	private String tipoRegOriMadre;
	private String razaMadre;
	private String fechaNacMadre;
	// PADRE
	private String rpPadre;
	private String regOriPadre;
	private String tipoRegOriPadre;
	private String razaPadre;
	private String fechaNacPadre;

	public ActualizacionSicelUnoForm() {
		this.reset();
	}

	public void reset() {
		setNroRegistro("");
		setRaza("");
		setSexo("");
		setRp("");
		setNombre("");
		setRegOri("");
		setCategoriaAnimal("");
		setRpMadre("");
		setRegOriMadre("");
		setRpPadre("");
		setRegOriPadre("");
		setTipoRegOriMadre("");
		setTipoRegOriPadre("");
		setRazaMadre("");
		setRazaPadre("");
		setActivoRazaMadre("");
		setRpMadreActual("");
		setRegOriMadreActual("");
		setTipoRegOriMadreActual("");
		setRpPadreActual("");
		setRegOriPadreActual("");
		setTipoRegOriPadreActual("");
		setFechaNacMadreActual("");
		setFechaNacPadreActual("");
		setFechaNacMadre("");
		setFechaNacPadre("");
		setRazaActual("");
		setMostrarCombo("");
		setBorrarPadre("");
		setBorrarMadre("");
	}

	public String getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRp() {
		return rp;
	}

	public void setRp(String rp) {
		this.rp = rp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRegOri() {
		return regOri;
	}

	public void setRegOri(String regOri) {
		this.regOri = regOri;
	}

	public String getCategoriaAnimal() {
		return categoriaAnimal;
	}

	public void setCategoriaAnimal(String categoria) {
		this.categoriaAnimal = categoria;
	}

	public String getRpMadre() {
		return rpMadre;
	}

	public void setRpMadre(String rpMadre) {
		this.rpMadre = rpMadre;
	}

	public String getRegOriMadre() {
		return regOriMadre;
	}

	public void setRegOriMadre(String regOriMadre) {
		this.regOriMadre = regOriMadre;
	}

	public String getRpPadre() {
		return rpPadre;
	}

	public void setRpPadre(String rpPadre) {
		this.rpPadre = rpPadre;
	}

	public String getRegOriPadre() {
		return regOriPadre;
	}

	public void setRegOriPadre(String regOriPadre) {
		this.regOriPadre = regOriPadre;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTipoRegOriMadre() {
		return tipoRegOriMadre;
	}

	public void setTipoRegOriMadre(String tipoRegOriMadre) {
		this.tipoRegOriMadre = tipoRegOriMadre;
	}

	public String getTipoRegOriPadre() {
		return tipoRegOriPadre;
	}

	public void setTipoRegOriPadre(String tipoRegOriPadre) {
		this.tipoRegOriPadre = tipoRegOriPadre;
	}

	public String getRazaMadre() {
		return razaMadre;
	}

	public void setRazaMadre(String razaMadre) {
		this.razaMadre = razaMadre;
	}

	public String getRazaPadre() {
		return razaPadre;
	}

	public void setRazaPadre(String razaPadre) {
		this.razaPadre = razaPadre;
	}

	public String getActivoRazaMadre() {
		return activoRazaMadre;
	}

	public void setActivoRazaMadre(String activoRazaMadre) {
		this.activoRazaMadre = activoRazaMadre;
	}

	public String getRpMadreActual() {
		return rpMadreActual;
	}

	public void setRpMadreActual(String rpMadreActual) {
		this.rpMadreActual = rpMadreActual;
	}

	public String getRegOriMadreActual() {
		return regOriMadreActual;
	}

	public void setRegOriMadreActual(String regOriMadreActual) {
		this.regOriMadreActual = regOriMadreActual;
	}

	public String getTipoRegOriMadreActual() {
		return tipoRegOriMadreActual;
	}

	public void setTipoRegOriMadreActual(String tipoRegOriMadreActual) {
		this.tipoRegOriMadreActual = tipoRegOriMadreActual;
	}

	public String getRpPadreActual() {
		return rpPadreActual;
	}

	public void setRpPadreActual(String rpPadreActual) {
		this.rpPadreActual = rpPadreActual;
	}

	public String getRegOriPadreActual() {
		return regOriPadreActual;
	}

	public void setRegOriPadreActual(String regOriPadreActual) {
		this.regOriPadreActual = regOriPadreActual;
	}

	public String getTipoRegOriPadreActual() {
		return tipoRegOriPadreActual;
	}

	public void setTipoRegOriPadreActual(String tipoRegOriPadreActual) {
		this.tipoRegOriPadreActual = tipoRegOriPadreActual;
	}

	public String getFechaNacMadreActual() {
		return fechaNacMadreActual;
	}

	public void setFechaNacMadreActual(String fechaNacMadreActual) {
		this.fechaNacMadreActual = fechaNacMadreActual;
	}

	public String getFechaNacPadreActual() {
		return fechaNacPadreActual;
	}

	public void setFechaNacPadreActual(String fechaNacPadreActual) {
		this.fechaNacPadreActual = fechaNacPadreActual;
	}

	public String getFechaNacMadre() {
		return fechaNacMadre;
	}

	public void setFechaNacMadre(String fechaNacMadre) {
		this.fechaNacMadre = fechaNacMadre;
	}

	public String getFechaNacPadre() {
		return fechaNacPadre;
	}

	public void setFechaNacPadre(String fechaNacPadre) {
		this.fechaNacPadre = fechaNacPadre;
	}

	public String getRazaActual() {
		return razaActual;
	}

	public void setRazaActual(String razaActual) {
		this.razaActual = razaActual;
	}

	public String getMostrarCombo() {
		return mostrarCombo;
	}

	public void setMostrarCombo(String mostrarCombo) {
		this.mostrarCombo = mostrarCombo;
	}

	public String getBorrarPadre() {
		return borrarPadre;
	}

	public void setBorrarPadre(String borrarPadre) {
		this.borrarPadre = borrarPadre;
	}

	public String getBorrarMadre() {
		return borrarMadre;
	}

	public void setBorrarMadre(String borrarMadre) {
		this.borrarMadre = borrarMadre;
	}

	public String getUsarRazaMadre() {
		return usarRazaMadre;
	}

	public void setUsarRazaMadre(String usarRazaMadre) {
		this.usarRazaMadre = usarRazaMadre;
	}

}
