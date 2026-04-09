package ar.org.sicel.persistence;


/**
 *
 * @hibernate.class
 *     table="En_Ubicacion"
 *     lazy="true"
 *
 */
public class Ubicacion {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.String nombre;
    private java.lang.String ciudad;
    private java.lang.String provinciaRegion;
    private java.lang.String pais;
    private java.lang.String direccion;
    private java.lang.String codigoPostal;
    private java.lang.String mail;
    private java.lang.String telefono;
    private ar.org.sicel.persistence.Contacto contacto;

    public Ubicacion() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_en_ubicacion"
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

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @hibernate.property
     *     column="ciudad"
     * @hibernate.column
     *     name="ciudad"
     *     not-null="true"
     *     length="60"
     *
     */
    public java.lang.String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(java.lang.String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     *
     * @hibernate.property
     *     column="prov_reg"
     * @hibernate.column
     *     name="prov_reg"
     *     not-null="true"
     *     length="15"
     *
     */
    public java.lang.String getProvinciaRegion() {
        return this.provinciaRegion;
    }

    public void setProvinciaRegion(java.lang.String provinciaRegion) {
        this.provinciaRegion = provinciaRegion;
    }

    /**
     *
     * @hibernate.property
     *     column="pais"
     * @hibernate.column
     *     name="pais"
     *     not-null="true"
     *     length="30"
     *
     */
    public java.lang.String getPais() {
        return this.pais;
    }

    public void setPais(java.lang.String pais) {
        this.pais = pais;
    }

    /**
     *
     * @hibernate.property
     *     column="direccion"
     * @hibernate.column
     *     name="direccion"
     *     length="60"
     *
     */
    public java.lang.String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @hibernate.property
     *     column="codigo_postal"
     * @hibernate.column
     *     name="codigo_postal"
     *     length="10"
     *
     */
    public java.lang.String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(java.lang.String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     *
     * @hibernate.property
     *     column="mail"
     * @hibernate.column
     *     name="email"
     *     length="40"
     *
     */
    public java.lang.String getMail() {
        return this.mail;
    }

    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }

    /**
     *
     * @hibernate.property
     *     column="telefono"
     * @hibernate.column
     *     name="telefono"
     *     length="80"
     *
     */
    public java.lang.String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="contacto"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Ubic_Cont"
     *
     */
    public ar.org.sicel.persistence.Contacto getContacto() {
        return this.contacto;
    }

    public void setContacto(ar.org.sicel.persistence.Contacto contacto) {
        this.contacto = contacto;
    }

    // ---------------- business methods  ----------------------
}
