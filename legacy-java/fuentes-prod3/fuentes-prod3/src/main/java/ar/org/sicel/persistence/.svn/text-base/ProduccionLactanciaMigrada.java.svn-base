package ar.org.sicel.persistence;

public class ProduccionLactanciaMigrada implements IProduccionLactancia {
	Lactancia l;
	
	
	public ProduccionLactanciaMigrada(EvtLactanciaMigrada l, int dias) {
		this.l = l.getLactancia(dias);
	}
	
	public Float getLeche() {
		return l.getLeche();
	
	}

	public Float getPorcentajeProteinas() {
		return l.getPorcentajeProteinas();
	}

	public Float getPorcentajeGrasa() {
		return l.getPorcentajeGrasa();
	}

	public Float getCelulas() {
		return null;
	}

	public Float getPorcentajeSolidosTotales() {
		return l.getPorcentajeSolidosTotales();
	}

}
