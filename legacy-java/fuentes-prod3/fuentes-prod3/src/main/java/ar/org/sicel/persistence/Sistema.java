package ar.org.sicel.persistence;



/**
 *
 * @hibernate.class
 *     table="En_Sistema"
 *     lazy="true"
 *
 */
public class Sistema {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.String nombre;
    private java.lang.String version;
    private String comentario;
    private ar.org.sicel.persistence.ResponsableSistema responsable;
    private java.util.Set eclos;
    private java.util.Set procLotes;

    public Sistema() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_en_sistema"
     *
     */
    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
	private void setId(Long id) {
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
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @hibernate.property
     *     column="version"
     * @hibernate.column
     *     name="version"
     *     not-null="true"
     *     length="20"
     *
     */
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
     * @hibernate.many-to-one
     *     column="responsable"
     *     outer-join="auto"
     *     foreign-key="FK_Sist_resi"
     *     unique="true"
     *     not-null="true"
     *
     * @hibernate.column
     *     name="responsable"
     *     not-null="true"
     *     unique="true"
     *     unique-key="UN_En_Sist_responsable"
     *
     */
    public ResponsableSistema getResponsable() {
        return responsable;
    }

    public void setResponsable(ResponsableSistema responsable) {
        this.responsable = responsable;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="sistema"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Eclo"
     *
     */
    public java.util.Set getEclos() {
        return this.eclos;
    }

    public void setEclos(java.util.Set eclos) {
        this.eclos = eclos;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="sistema"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ProcLote"
     *
     */
    public java.util.Set getProcLotes() {
        return this.procLotes;
    }

    public void setProcLotes(java.util.Set procLotes) {
        this.procLotes = procLotes;
    }

    // ---------------- business methods  ----------------------
    
	public boolean equals(Object obj)  {
		if(!(obj instanceof Sistema))
			return false;
		else
			return nombre.equalsIgnoreCase(((Sistema)obj).getNombre());
	}
	
	public int hashCode()
	{
		if(nombre != null)
			return nombre.hashCode();
		else
			return super.hashCode();
	}
}
