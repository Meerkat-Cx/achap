package ar.org.sicel.persistence;



/**
 *
 * @hibernate.class
 *     table="At_Conjunto_At"
 *     lazy="true"
 *
 */
public class ConjuntoAtributos {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.String nombre;
    private java.util.Set atributos;
    private Boolean esIntervalo;

    protected ConjuntoAtributos() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_at_conjunto_at"
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
    public java.lang.String getNombre() {
        return this.nombre;
    }

    protected void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @hibernate.property
     *     column="esIntervalo"
     * @hibernate.column
     *     name="esIntervalo"
     *     not-null="true"
     *
     */
    public Boolean getEsIntervalo() {
        return esIntervalo;
    }

    public void setEsIntervalo(Boolean esIntervalo) {
        this.esIntervalo = esIntervalo;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="false"
     *     cascade="all"
     * @hibernate.collection-key
     *     column="conjunto_at"
     *     foreign-key="FK_Atri_Coat"
     * @hibernate.collection-key-column
     *     name="conjunto_at"
     *     not-null="true"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Atributo"
     *
     */
    public java.util.Set getAtributos() {
        return this.atributos;
    }

    protected void setAtributos(java.util.Set atributos) {
        this.atributos = atributos;
    }
    
    @SuppressWarnings("unchecked")
	protected void addAtributo(Atributo at) {
    	atributos.add(at);
    }

    // ---------------- business methods  ----------------------
    
    
}
