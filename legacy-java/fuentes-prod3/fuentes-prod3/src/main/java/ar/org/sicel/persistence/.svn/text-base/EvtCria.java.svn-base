package ar.org.sicel.persistence;

import org.hibernate.proxy.HibernateProxy;


/**
 *
 *
 */
public class EvtCria {
    // --------------- attributes ---------------------
    private java.lang.String tamanio;
    private java.lang.Float pesoKg;
    private java.lang.Boolean estadoPerinatal;
    private java.lang.String dificultadNac;
    private ar.org.sicel.persistence.Animal cria;
    //private java.lang.Boolean esHembra;
    private String sexo;
    
    
    
    // -------attributos no persistentes -----/////
    //estos atributos se usan a la hora de dar de alta un EventoReproduccion,
    //pero no se guardan en la base, ya que van en el Animal si inscribir = true
    //y si no se descartan
    private Boolean inscribir;
    private String RP;
    private String nombre;
    private String rpSenasa;//solo a nivel obj
    private Integer codigo;//solo a nivel obj
    
    

    public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	protected EvtCria() {
    }

    /**
     *
     * @hibernate.property
     *     column="tamanio"
     *
     */
    public java.lang.String getTamanio() {
        return this.tamanio;
    }

    protected void setTamanio(java.lang.String tamanio) {
        this.tamanio = tamanio;
    }

    /**
     *
     * @hibernate.property
     *     column="peso"
     *
     */
    public java.lang.Float getPesoKg() {
        return this.pesoKg;
    }

    protected void setPesoKg(java.lang.Float pesoKg) {
        this.pesoKg = pesoKg;
    }

    /**
     *
     * @hibernate.property
     *     column="estadoPerinatal"
     * @hibernate.column
     *     name="estadoPerinatal"
     *     not-null="true"
     *
     */
    public java.lang.Boolean getEstadoPerinatalEsVivo() {
        return this.estadoPerinatal;
    }

    //public para poder llamarlo desde los test
    public void setEstadoPerinatalEsVivo(java.lang.Boolean estadoPerinatal) {
        this.estadoPerinatal = estadoPerinatal;
    }    
    
    
    

    /**
     *
     * @hibernate.property
     *     column="dificultadNac"
     *
     */
    public java.lang.String getDificultadNac() {
        return this.dificultadNac;
    }

    protected void setDificultadNac(java.lang.String dificultadNac) {
        this.dificultadNac = dificultadNac;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="cria"
     *     outer-join="auto"
     *     foreign-key="FK_EvCria_Anim"
     *
     */
    public ar.org.sicel.persistence.Animal getCria() {
    	
		/*if (cria instanceof HibernateProxy){
	         HibernateProxy proxy = (HibernateProxy) cria;
	         cria= (Animal) proxy.getHibernateLazyInitializer().getImplementation();
	      }*/
        return cria;
    }

    public void setCria(ar.org.sicel.persistence.Animal cria) {
        this.cria = cria;
    }

	
    // ---------------- business methods  ----------------------
    
	public Boolean inscribir() {
		if(inscribir!=null)
			return inscribir;
		else{
			return(this.getRP()!=null?true:false);
		}
		//return inscribir;
	}

	public void setInscribir(Boolean inscribir) {
		this.inscribir = inscribir;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRP() {
		return (RP!=null?RP:((this.getCria()!=null)?this.getCria().getRP():null));
	}

	public void setRP(String rp) {
		RP = rp;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	

	public String getRpSenasa() {
		return rpSenasa;
	}

	public void setRpSenasa(String rpSenasa) {
		this.rpSenasa = rpSenasa;
	}

    
}
