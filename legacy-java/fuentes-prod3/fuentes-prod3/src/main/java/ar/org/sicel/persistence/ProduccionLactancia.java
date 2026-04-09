package ar.org.sicel.persistence;

public class ProduccionLactancia implements IProduccionLactancia {

	Float leche;
	Float porcentajeProteinas;
	Float porcentajeGrasa;
	Float porcentajeSolidosTotales;
	Float celulas;
	
	 /**
     * 
     * @hibernate.property column="leche"
     * 
     */
	public Float getLeche() {
		return leche;
	}

	 /**
     * 
     * @hibernate.property column="proteinas"
     * 
     */
	public Float getPorcentajeProteinas() {
		return porcentajeProteinas;
	}

	 /**
     * 
     * @hibernate.property column="grasa"
     * 
     */
	public Float getPorcentajeGrasa() {
		return porcentajeGrasa;
	}

	 /**
     * 
     * @hibernate.property column="celulas"
     * 
     */

	public Float getCelulas() {
		return celulas;
	}

	 /**
     * 
     * @hibernate.property column="solidos_totales"
     * 
     */
	public Float getPorcentajeSolidosTotales() {
		return porcentajeSolidosTotales;
	}

	public void setLeche(Float leche) {
		this.leche = leche;
	}

	public void setCelulas(Float celulas) {
		this.celulas = celulas;
	}

	public void setPorcentajeGrasa(Float porcentajeGrasa) {
		this.porcentajeGrasa = porcentajeGrasa;
	}

	public void setPorcentajeProteinas(Float porcentajeProteinas) {
		this.porcentajeProteinas = porcentajeProteinas;
	}

	public void setPorcentajeSolidosTotales(Float porcentajeSolidosTotales) {
		this.porcentajeSolidosTotales = porcentajeSolidosTotales;
	}
	
	

}
