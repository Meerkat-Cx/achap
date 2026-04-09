package ar.org.sicel.persistence.util.jasperReport;

public class ResumenEventos {
	private String tipoEvento;
	private int informados;
	private int aceptados;
	
	public ResumenEventos(String tipoEvento) {
		aceptados = 0;
		informados = 0;
		this.tipoEvento = tipoEvento; 
	}
	
	public int getAceptados() {
		return aceptados;
	}
	public void setAceptados(int aceptados) {
		this.aceptados = aceptados;
	}
	public int getInformados() {
		return informados;
	}
	public void setInformados(int informados) {
		this.informados = informados;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	public void incAceptados(int count) {
		aceptados = aceptados + count;
	}
	
	public void incInformados(int count) {
		informados = informados + count;
	}
	
	
	public String toString() {
		return tipoEvento + "[" + informados + "-"+ aceptados+ "]";
	}

}
