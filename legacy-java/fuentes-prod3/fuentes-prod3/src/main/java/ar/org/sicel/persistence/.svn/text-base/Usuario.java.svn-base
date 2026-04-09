package ar.org.sicel.persistence;

import java.util.Random;

public class Usuario {

	private Long id;
	private String nombre;
	private String apellido;
	private String username;
	private String clave;
	private String email;
	private boolean activo;
	private Rol rol;
	private Contacto contacto;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String generatePassword(){
		Random rand = new Random();
        StringBuffer sb = new StringBuffer();
        char c = 'A';
        for(int i = 0; i < 8; i++){
        	int code = rand.nextInt(61);
            if(code < 0)
                code *= -1;
            if(code <= 25)
                c = (char)(code + 65);
            else
            if(code > 25 && code <= 51)
                c = (char)(code + 65 + 6);
            else
            if(code > 51)
                c = (char)(code - 4);
            sb.append(c);
        }
        return sb.toString();
	}
	
	public boolean equals(Object obj) {
        if(!(obj instanceof Usuario))
            return false;
        else
            return username.equalsIgnoreCase(((Usuario)obj).getUsername());
    }

    public int hashCode() {
        if(username != null)
            return username.hashCode();
        else
            return super.hashCode();
    }

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
    public String getConjunto(){
    	String d = this.getId().toString()+", "+this.getNombre()+" "+this.getApellido();
    	return d;
    }
}
