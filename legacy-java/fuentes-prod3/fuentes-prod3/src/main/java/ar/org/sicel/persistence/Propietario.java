package ar.org.sicel.persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ErrorFatal;




/**
*
* @hibernate.joined-subclass
*    table="En_Propietario"
*    lazy="true"
*
* @hibernate.joined-subclass-key
*    column="id"
*    foreign-key="FK_Prop_Cont"
*
*/
public class Propietario extends ar.org.sicel.persistence.Contacto {
    // --------------- attributes ---------------------
    private Integer socio;
    private Integer har;
    private String prefijo;
   // private PropietarioExpd sraExpd;
    private Integer s1Eclo;
    private Integer s1Prop;
    private Boolean esPersonaFisica;
    private Set establecimientos; // tambos
    private Set animals;
    private Set evtTransferencias;
    private Boolean activo = new Boolean(true);
    private Set estancias = new HashSet(); // establecimientos
    private Set expds = new HashSet();
    
    public String getIdNombre(){    	
    	String nombre = (this.getNombreContacto().equals("<CREATED FOR INTEGRITY>"))?"sin nombre":this.getNombreContacto();
    	return this.getId().toString() + ", " +  nombre.toUpperCase() ;
    }
    public Set getEstancias() {
		return estancias;
	}
    public void setEstancias(Set estancias) {
		this.estancias = estancias;
	}
    public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
    public java.lang.Integer getSocio() {
        return this.socio;
    }

    public void setSocio(java.lang.Integer socio) {
        this.socio = socio;
    }

    /**
     *
     * @hibernate.property
     *     column="HAR"
     *
     */
    public java.lang.Integer getHar() {
        return this.har;
    }

    public void setHar(java.lang.Integer har) {
        this.har = har;
    }

    /**
     *
     * @hibernate.property
     *     column="S1_ECLO"
     *
     */
    public java.lang.Integer getS1Eclo() {
        return this.s1Eclo;
    }

    public void setS1Eclo(java.lang.Integer s1Eclo) {
        this.s1Eclo = s1Eclo;
    }

    /**
     *
     * @hibernate.property
     *     column="S1_PROP"
     *
     */
    public java.lang.Integer getS1Prop() {
        return this.s1Prop;
    }

    public void setS1Prop(java.lang.Integer s1Prop) {
        this.s1Prop = s1Prop;
    }

    /**
     *
     * @hibernate.property
     *     column="esPersonaFisica"
     *
     */
    public java.lang.Boolean getEsPersonaFisica() {
        return this.esPersonaFisica;
    }

    public void setEsPersonaFisica(java.lang.Boolean esPersonaFisica) {
        this.esPersonaFisica = esPersonaFisica;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="propietario"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Establecimiento"
     *
     */
    public java.util.Set getEstablecimientos() {
        return this.establecimientos;
    }

    protected void setEstablecimientos(java.util.Set establecimientos) {
        this.establecimientos = establecimientos;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="propietario"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Animal"
     *
     */
   /* public java.util.Set getAnimals() {
        return this.animals;
    }*/
    /**
     * retorna todos los animales de un propietario, se ralizo de esta manera pq si se pedia con un set
     * por hibernate tardaba mucho
     */
    public List getAnimals() {
    	try {
    		return AnimalDAO.findAnimalesPropietario(this);
    	} catch (HibernateException he) {
    		throw new ErrorFatal("No se pudo buscar los animales de un propietario - Propietario", he);
    	}
    }
   /* protected void setAnimals(java.util.Set animals) {
        this.animals = animals;
    }*/

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="nuevoProp"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.EvtTransferencia"
     */
    public java.util.Set getEvtTransferencias() {
        return this.evtTransferencias;
    }

    protected void setEvtTransferencias(java.util.Set evtTransferencias) {
        this.evtTransferencias = evtTransferencias;
    }
    
	public Set getExpds() {
		return expds;
	}
	
	public void setExpds(Set expds) {
		this.expds = expds;
	}
	
	public String getPrefijo() {
		return prefijo;
	}
	
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
	
	/*public PropietarioExpd getSraExpd() {
		return sraExpd;
	}

	public void setSraExpd(PropietarioExpd sraExpd) {
		this.sraExpd = sraExpd;
	}*/

	

	   
    
    // ---------------- business methods  ----------------------
}
