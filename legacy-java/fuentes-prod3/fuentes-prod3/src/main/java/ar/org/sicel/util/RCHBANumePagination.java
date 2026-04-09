package ar.org.sicel.util;

public class RCHBANumePagination {
	
	private Integer desde;
	private Integer hasta;
	private Integer id;/*este id sirve para identificarlo desde la jsp*/
	
	public RCHBANumePagination(Integer desde,Integer hasta,Integer id){
		setDesde(desde);
		setHasta(hasta);
		setId(id);
	}

	public Integer getDesde() {
		return desde;
	}

	public void setDesde(Integer desde) {
		this.desde = desde;
	}

	public Integer getHasta() {
		return hasta;
	}

	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}
	
	public String toString(){
		return desde.toString()+" a "+hasta.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
