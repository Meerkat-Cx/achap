package ar.org.sicel.persistence;



/**
 *
 * @hibernate.class
 *     table="Ca_Mdl_Parte"
 *     lazy="true"
 *
 */
public class ParteCalificacion {
    // --------------- attributes ---------------------
	 
	
	
	private java.lang.Long id;
    private java.lang.String codigo;
    private java.lang.String nombre;
    private java.lang.Boolean esPunteable;
    private ar.org.sicel.persistence.ModeloCalificacion modeloCalificacion;
    private java.util.Set caracteristicaCalificacions;
    private java.util.Set defectoCalificacions;

    

    

	protected ParteCalificacion() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_ca_mdl_parte"
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
     *     column="codigo"
     * @hibernate.column
     *     name="codigo"
     *     not-null="true"
     *     length="1"
     *
     */
    public java.lang.String getCodigo() {
        return this.codigo;
    }

    protected void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
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
     *     column="esPunteable"
     * @hibernate.column
     *     name="esPunteable"
     *     not-null="true"
     *
     */
    public java.lang.Boolean getEsPunteable() {
        return this.esPunteable;
    }

    protected void setEsPunteable(java.lang.Boolean esPunteable) {
        this.esPunteable = esPunteable;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="modelo"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Ca_Mdl_Parte"
     *
     */
    public ar.org.sicel.persistence.ModeloCalificacion getModeloCalificacion() {
        return this.modeloCalificacion;
    }

    protected void setModeloCalificacion(
        ar.org.sicel.persistence.ModeloCalificacion modeloCalificacion) {
        this.modeloCalificacion = modeloCalificacion;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="delete"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="parte"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.CaracteristicaCalificacion"
     *
     */
    public java.util.Set getCaracteristicaCalificacions() {
        return this.caracteristicaCalificacions;
    }

    protected void setCaracteristicaCalificacions(
        java.util.Set caracteristicaCalificacions) {
        this.caracteristicaCalificacions = caracteristicaCalificacions;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="delete"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="parte"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.DefectoCalificacion"
     *
     */
    public java.util.Set getDefectoCalificacions() {
        return this.defectoCalificacions;
    }

    protected void setDefectoCalificacions(java.util.Set defectoCalificacions) {
        this.defectoCalificacions = defectoCalificacions;
    }

    // ---------------- business methods  ----------------------
}
