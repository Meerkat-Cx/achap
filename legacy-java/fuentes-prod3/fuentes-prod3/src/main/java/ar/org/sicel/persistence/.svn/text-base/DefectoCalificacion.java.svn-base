package ar.org.sicel.persistence;


/**
 *
 * @hibernate.class
 *     table="Ca_Mdl_Defecto"
 *     lazy="true"
 *
 */
public class DefectoCalificacion {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.String nombre;
    private ar.org.sicel.persistence.ParteCalificacion parteCalificacion;

    protected DefectoCalificacion() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_ca_mdl_defecto"
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

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="parte"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Ca_Mdl_Defecto"
     *
     */
    public ar.org.sicel.persistence.ParteCalificacion getParteCalificacion() {
        return this.parteCalificacion;
    }

    protected void setParteCalificacion(
        ar.org.sicel.persistence.ParteCalificacion parteCalificacion) {
        this.parteCalificacion = parteCalificacion;
    }

    // ---------------- business methods  ----------------------
}
