package ar.org.sicel.persistence;

import java.util.Set;

public class Rol {

	private Long id;
	private String nombre;
	private String descripcion;
	private Set usuarios;
    private Set funciones;

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set usuarios) {
		this.usuarios = usuarios;
	}

	public boolean equals(Object obj) {
        if(!(obj instanceof Rol))
            return false;
        else
            return nombre.equalsIgnoreCase(((Rol)obj).getNombre());
    }

    public int hashCode() {
        if(nombre != null)
            return nombre.hashCode();
        else
            return super.hashCode();
    }

	public Set getFunciones() {
		return funciones;
	}
	
	public void setFunciones(Set funciones) {
		this.funciones = funciones;
	}	
}
