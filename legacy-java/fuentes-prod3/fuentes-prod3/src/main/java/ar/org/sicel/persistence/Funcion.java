package ar.org.sicel.persistence;

import java.util.Set;

public class Funcion {
    private Long id;
    private String link;
    private Set roles;

    public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public Set getRoles() {
		return roles;
	}
	
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
    public int hashCode(){
        if(id != null)
            return id.hashCode();
        else
            return super.hashCode();
    }
    
	public boolean equals(Object obj) {
		if(obj instanceof Funcion){
			if(((Funcion)obj).getId().equals(this.getId()))
				return true;
		}
		return false;
	}
}
