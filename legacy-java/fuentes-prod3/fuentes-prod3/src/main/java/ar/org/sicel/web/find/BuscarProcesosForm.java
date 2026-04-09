package ar.org.sicel.web.find;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class BuscarProcesosForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7082056268215267381L;
	
	private String nroLote;
	private String nroEclo1;
	private String nroEclo2;
	private String idEventoResultado;
	private String idEventoResultado2;
	private String idEventoInformado;
	private String metodo;
	private String ordenieInformado;
	private String controlInformado;
		
	public void reset() {
		this.setIdEventoInformado("");
		this.setIdEventoResultado("");
		this.setNroEclo1("");
		this.setNroEclo2("");
		this.setNroLote("");
		this.setMetodo("");
		this.setOrdenieInformado("");
		this.setControlInformado("");
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public String getNroEclo1() {
		return nroEclo1;
	}

	public void setNroEclo1(String nroEclo1) {
		this.nroEclo1 = nroEclo1;
	}
	
	public String getNroEclo2() {
		return nroEclo2;
	}

	public void setNroEclo2(String nroEclo2) {
		this.nroEclo2 = nroEclo2;
	}

	public String getIdEventoResultado() {
		return idEventoResultado;
	}

	public void setIdEventoResultado(String idEventoResultado) {
		this.idEventoResultado = idEventoResultado;
	}

	public String getIdEventoInformado() {
		return idEventoInformado;
	}

	public void setIdEventoInformado(String idEventoInformado) {
		this.idEventoInformado = idEventoInformado;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getOrdenieInformado() {
		return ordenieInformado;
	}

	public void setOrdenieInformado(String ordenieInformado) {
		this.ordenieInformado = ordenieInformado;
	}

	public String getControlInformado() {
		return controlInformado;
	}

	public void setControlInformado(String controlInformado) {
		this.controlInformado = controlInformado;
	}
	
	public String getIdEventoResultado2() {
		return idEventoResultado2;
	}

	public void setIdEventoResultado2(String idEventoResultado2) {
		this.idEventoResultado2 = idEventoResultado2;
	}

}
