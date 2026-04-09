package ar.org.sicel.persistence;


//import ar.org.sicel.persistence.util.HibernateUtils;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;


/**
 *
 * @hibernate.class
 *     table="En_Contacto"
 *     lazy="true"
 *
 */
public abstract class Contacto {	
    private static ar.org.sicel.persistence.ConjuntoAtributos conjuntoAtributos;
    
    private static ConjuntoAtributos getConjuntoAtributos() {
    	if (conjuntoAtributos == null) {
    		try {
    			conjuntoAtributos = ConjuntoAtributosDAO.findByName(Contacto.class.getSimpleName());
    		} catch (HibernateException e) {
    			e.printStackTrace();
    		}
    	}
    	return conjuntoAtributos;
    }


    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.String nombreContacto;
    private String comentario;
    private Foto foto;
    private java.util.Set ubicacions;
    private ar.org.sicel.persistence.AtrVariables atrVariables;
    private Set usuarios= new HashSet();
    private Set bitacora=new HashSet();
    private String email;
    private String cuit;
    private String renspa;
    private String cuig;
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set getBitacora() {
		return bitacora;
	}
	public void setBitacora(Set bitacora) {
		this.bitacora = bitacora;
	}
	public Set getUsuarios() {
		return usuarios;
	}
    public void setUsuarios(Set usuarios) {
		this.usuarios = usuarios;
	}

   
    protected Contacto() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_en_contacto"
     *
     */
    public java.lang.Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     *
     * @hibernate.property
     *     column="nombre"
     * @hibernate.column
     *     name="nombre"
     *     not-null="true"
     *     length="60"
     *
     */
    public java.lang.String getNombreContacto() {
        return this.nombreContacto;
    }

    public void setNombreContacto(java.lang.String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    /**
    *
    * @hibernate.property
    *     column="comentario"
    *     type="ar.org.sicel.persistence.util.HibernateStringClobType"
    *
    */
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
    
    
    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="delete"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="contacto"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Ubicacion"
     *
     */
    public java.util.Set getUbicacions() {
        return this.ubicacions;
    }

    public void setUbicacions(java.util.Set ubicacions) {
        this.ubicacions = ubicacions;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="atrVariables"
     *     outer-join="auto"
     *     foreign-key="FK_Cont_atrVariables"
     *     not-null="false"
     *     unique="true"
     *     cascade = "all"
     *
     * @hibernate.column
     *     name="atrVariables"
     *     not-null="false"
     *     unique="true"
     *     unique-key="UN_En_Cont_atrVariables"
     *
     */
    public ar.org.sicel.persistence.AtrVariables getAtrVariables() {
   // 	if (atrVariables == null) //para que siempre retorne uno valido, si no esta presente lo crea (vacio)
   // 		this.setAtrVariables(AtrVariablesDAO.create()); 
    	return this.atrVariables;
    }

    public void setAtrVariables(
        ar.org.sicel.persistence.AtrVariables atrVariables) {
  //  	if (atrVariables == null) //para que siempre retorne uno valido, si no esta presente lo crea (vacio)
   // 		atrVariables =AtrVariablesDAO.create(); 
    	this.atrVariables = atrVariables;
    	if (atrVariables != null)
    		atrVariables.setConjuntoAtributos(getConjuntoAtributos());
    }
     

    /**
    *
    * @hibernate.many-to-one
    *     column="foto"
    *     not-null="false"
    *     outer-join="auto"
    *     foreign-key="FK_en_cont_foto"
    *
    */
   
   public Foto getFoto() {
       return foto;
   }

   public void setFoto(Foto foto) {
       this.foto = foto;
   }
public String getCuig() {
	return cuig;
}
public void setCuig(String cuig) {
	this.cuig = cuig;
}
public String getCuit() {
	return cuit;
}
public void setCuit(String cuit) {
	this.cuit = cuit;
}
public String getRenspa() {
	return renspa;
}
public void setRenspa(String renspa) {
	this.renspa = renspa;
}
public String getIdNombre(){
	String nombreId;
	if(nombreContacto!=null && !nombreContacto.equals("")) nombreId = id.toString() + ", " + nombreContacto; 
	else nombreId="Sin nombre";
	return nombreId;
}


    
   
   
    


    // ---------------- business methods  ----------------------
   
   
   
   
   
}
