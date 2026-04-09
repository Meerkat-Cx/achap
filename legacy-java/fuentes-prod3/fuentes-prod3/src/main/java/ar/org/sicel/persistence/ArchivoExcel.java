/**
 * 
 */
package ar.org.sicel.persistence;

/**
 * @author jivars
 * Clase que se usa para mapear los archivos excel del padron de animales en pantalla 
 * para poder mostrar el nombre y la fecha de ultima modificacion
 * Tambien se usa para mapear los archivos doc de la documentacion
 */
public class ArchivoExcel {
	private String name;
	private String modificacion;
	
	
	public String getModificacion() {
		return modificacion;
	}
	public void setModificacion(String modificacion) {
		this.modificacion = modificacion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
