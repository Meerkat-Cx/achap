package ar.org.sicel.persistence;

import java.util.Date;

/**
 * @author jdivars
 *
 */
public class PropietarioExpd {
	
	private Long id;
	private Long numero;
	private String cuig;
	private Usuario usuario;
	private String estab; 
	private Date fechaAlta;
	private Date fechaBaja;
	private Propietario propietario;
	private Raza raza;

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstab() {
		return estab;
	}

	public void setEstab(String estab) {
		this.estab = estab;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public String getCuig() {
		return cuig;
	}

	public void setCuig(String cuig) {
		this.cuig = cuig;
	}
	
	 public int compareTo(Object o) {
		  if (o instanceof PropietarioExpd) {
			  /*int rta = this.fechaAlta.compareTo(((PropietarioExpd)o).getFechaAlta());
			  if (rta == 0) {
				  //return rta;
				   rta = this.raza.getId().compareTo(((PropietarioExpd)o).getRaza().getId());
				   if(rta == 0)
					   return 0;
				   if (rta == -1)
						  return 1;
					if (rta == 1)
						  return -1;	
			  }
			  if (rta == -1)
				  return 1;
			  if (rta == 1)
				  return -1;*/
			 int rta = this.raza.getId().compareTo(((PropietarioExpd)o).getRaza().getId());
			 
			 if (rta == 0) {
				  rta = this.fechaAlta.compareTo(((PropietarioExpd)o).getFechaAlta());
				   if(rta == 0){
					   rta = this.numero.compareTo(((PropietarioExpd)o).getNumero());
				   		return(rta==-1?1:-1);
				   }
					  // return 0;
				   if (rta == -1)
						  return 1;
					if (rta == 1)
						  return -1;	
			  }
			 
			 /*
				  
			  if (rta == -1)
				  return 1;
			  if (rta == 1)
				  return -1;*/
		  }
		  return -1;
	 }
	 public int hashCode()
	    {
	        if(id != null)
	            return id.hashCode();
	        else
	            return super.hashCode();
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	    	if(!(obj instanceof PropietarioExpd))
	    		return false;
	    	PropietarioExpd r = (PropietarioExpd) obj;
	    	/*if(this == r)
	    		return true;*/
	    	if(raza.equals(r.getRaza())&&(this.numero.equals(r.getNumero())))
	    		return true;
	    	return false;
	    }

	
		
}