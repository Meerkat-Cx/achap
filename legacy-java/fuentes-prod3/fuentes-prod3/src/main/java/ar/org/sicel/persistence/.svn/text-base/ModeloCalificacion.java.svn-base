package ar.org.sicel.persistence;



/**
 *
 * @hibernate.class
 *     table="Ca_Mdl"
 *     lazy="true"
 *
 */
public class ModeloCalificacion {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.util.Date fecha;
    private java.lang.Boolean esHembra;
    private java.util.Set parteCalificacions;
    private ar.org.sicel.persistence.Raza raza;

    protected ModeloCalificacion() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_ca_mdl"
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
     *     column="fecha"
     * @hibernate.column
     *     name="fecha"
     *     not-null="true"
     *
     */
    public java.util.Date getFecha() {
        return this.fecha;
    }

    protected void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * @hibernate.property
     *     column="esHembra"
     * @hibernate.column
     *     name="esHembra"
     *     not-null="true"
     *
     */
    public java.lang.Boolean getEsHembra() {
        return this.esHembra;
    }

    protected void setEsHembra(java.lang.Boolean esHembra) {
        this.esHembra = esHembra;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="raza"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Ca_Mdl_Raza"
     *
     */
    public ar.org.sicel.persistence.Raza getRaza() {
        return this.raza;
    }

    protected void setRaza(ar.org.sicel.persistence.Raza raza) {
        this.raza = raza;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="delete"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="modelo"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ParteCalificacion"
     *
     */
    public java.util.Set getParteCalificacions() {
        return this.parteCalificacions;
    }

    protected void setParteCalificacions(java.util.Set parteCalificacions) {
        this.parteCalificacions = parteCalificacions;
    }

    // ---------------- business methods  ----------------------
}
