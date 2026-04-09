package ar.org.sicel.web.altaEdicion.forms;

import org.apache.struts.action.ActionForm;

public class LactanciasGeneticasForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -494485322055697337L;
	private String intervalo;
	private String continuar;
	private Long idAnimal;
	
	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public LactanciasGeneticasForm(){
		this.reset();
	}

	public String getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}
	
	public void reset(){
		setIntervalo("");
	}

	public String getContinuar() {
		return continuar;
	}

	public void setContinuar(String continuar) {
		this.continuar = continuar;
	}
}
