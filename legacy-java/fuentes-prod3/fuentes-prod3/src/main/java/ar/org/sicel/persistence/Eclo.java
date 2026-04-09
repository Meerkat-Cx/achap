package ar.org.sicel.persistence;

import java.util.Set;




/**
 *
 * @hibernate.joined-subclass
 *    table="En_ECLO"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_Eclo_Cont"
 *
 */
public class Eclo extends ar.org.sicel.persistence.Contacto {
    // --------------- attributes ---------------------
    private EntidadRegional regional;
    private Set establecimientos;
    private ResponsableEclo responsable;
   // private ar.org.sicel.persistence.Sistema sistema;
    private Set sistemas;
    private Set procLotes;   
    private Long ultimoNumeroEvento;
    private Boolean activo = new Boolean(true); 
    private Set estancias;
    
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

	

	protected Eclo() {
    }
    
    
    
   /**
    *
    * @hibernate.property
    *     column="ultimo_evento"
    * @hibernate.column
    *     name="ultimo_evento"
    *     not-null="true"
    *
    */
    public Long getUltimoNumeroEvento() {
    		return ultimoNumeroEvento;
    }
    
    public void setUltimoNumeroEvento(Long ultimo) {
    	this.ultimoNumeroEvento = ultimo;
    }
    

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="regional"
     *     outer-join="auto"
     *     foreign-key="FK_Eclo_Regi"
     *
     */
    public ar.org.sicel.persistence.EntidadRegional getRegional() {
        return this.regional;
    }

    public void setRegional(
        ar.org.sicel.persistence.EntidadRegional regional) {
        this.regional = regional;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="ECLO"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Establecimiento"
     *
     */
    public java.util.Set getEstablecimientos() {
        return this.establecimientos;
    }

    public void setEstablecimientos(java.util.Set establecimientos) {
        this.establecimientos = establecimientos;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="responsable"
     *     outer-join="auto"
     *     foreign-key="FK_Eclo_reec"
     *     unique="true"
     *
     * @hibernate.column
     *     name="responsable"
     *     unique="true"
     *     unique-key="UN_En_ECLO_responsable"
     *
     */
    public ar.org.sicel.persistence.ResponsableEclo getResponsable() {
        return this.responsable;
    }

    public void setResponsable(
        ar.org.sicel.persistence.ResponsableEclo responsable) {
        this.responsable = responsable;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="sistema"
     *     outer-join="auto"
     *     foreign-key="FK_Eclo_Sist"
     *
     */
  /*  public ar.org.sicel.persistence.Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(ar.org.sicel.persistence.Sistema sistema) {
        this.sistema = sistema;
    }*/

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="eclo"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ProcLote"
     */
    public java.util.Set getProcLotes() {
        return this.procLotes;
    }

    protected void setProcLotes(java.util.Set procLotes) {
        this.procLotes = procLotes;
    }
    
    @SuppressWarnings("unchecked")
	protected void addLote(ProcLote lote) {
    	procLotes.add(lote);
    	
    }
    
    protected void removeLote(ProcLote lote) {
    	procLotes.remove(lote);
    }

	public Set getSistemas() {
		return sistemas;
	}
 
	public void setSistemas(Set sistemas) {
		this.sistemas = sistemas;
	}
	public String toString(){	
	return this.getId()+" - "+this.getNombreContacto();
	}
public String getIdNombre(){
		String nombreId;
		if(getNombreContacto()!=null && !getNombreContacto().equals("")) nombreId = getId().toString() + ", " + getNombreContacto(); 
		else nombreId="Sin nombre";
		return nombreId;
	}

    
    // ---------------- business methods  ----------------------
    
    
}
