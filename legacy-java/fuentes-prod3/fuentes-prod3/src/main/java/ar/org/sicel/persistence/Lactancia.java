package ar.org.sicel.persistence;

import java.util.Date;

public interface Lactancia {

	public abstract Date getFechaInicio();
	
	public abstract Integer getNroLact();
	
	public abstract Integer getAnios();
	
	public abstract Integer getMeses();
	
	public abstract Animal getAnimal();
	/**
	 * 
	 * @return numero de dias TOTALES de esta lactancia. Por ejemplo si son 5 meses -> 150 dias +/-
	 */
	public abstract java.lang.Integer getDias();

	public abstract java.lang.Integer getOrdenies();
	
	public abstract java.lang.Float getLeche();

	public abstract java.lang.Float getGrasaAbsoluto();

	public abstract Float getPorcentajeGrasa();	
	
	public abstract java.lang.Float getProteinasAbsoluto();

	public abstract java.lang.Float getPorcentajeProteinas();
	
	public abstract java.lang.Float getPorcentajeSolidosTotales();
	
	public abstract java.lang.Float getSolidosTotalesAbsoluto();
	
	public abstract Boolean isEsOficial();
	
	
	public abstract java.lang.String getControl();
	
	
	
	public IProduccionLactancia getLactanciaA(String nombre);
	
	
	public String getCodigoInicio();
	
	public String getCausaFin();
	
	public abstract Date getFecha();
	
	public abstract boolean isSicel3();
	

}