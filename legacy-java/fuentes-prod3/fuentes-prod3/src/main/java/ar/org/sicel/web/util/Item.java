package ar.org.sicel.web.util;

public class Item {
	private String id;
	private String nombre;
	
	public void setId(String id){
		this.id= id;
	}
	
	public String getId(){
		return id;
	}

	public void setNombre(String nombre){
		this.nombre= nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
}
