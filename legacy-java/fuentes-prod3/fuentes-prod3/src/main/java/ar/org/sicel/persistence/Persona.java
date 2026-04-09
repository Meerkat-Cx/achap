package ar.org.sicel.persistence;




/**
 *
 * @hibernate.joined-subclass
 *    table="En_Persona"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_Pers_Cont"
 *
 */
public abstract class Persona extends ar.org.sicel.persistence.Contacto {
    // --------------- attributes ---------------------
    private java.lang.Integer documento;
    private java.lang.String tipoDocumento;
    private java.lang.String nombrePersona;
    private java.lang.String apellido;

    public Persona() {
    }

    /**
     *
     * @hibernate.property
     *     column="documento"
     *
     */
    public java.lang.Integer getDocumento() {
        return this.documento;
    }

    public void setDocumento(java.lang.Integer documento) {
        this.documento = documento;
    }

    /**
     *
     * @hibernate.property
     *     column="tipoDocumento"
     * @hibernate.column
     *     name="tipoDocumento"
     *     length="15"
     *
     */
    public java.lang.String getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(java.lang.String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
    public java.lang.String getNombrePersona() {
        return this.nombrePersona;
    }

    public void setNombrePersona(java.lang.String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    /**
     *
     * @hibernate.property
     *     column="apellido"
     * @hibernate.column
     *     name="apellido"
     *     length="60"
     *
     */
    public java.lang.String getApellido() {
        return this.apellido;
    }

    public void setApellido(java.lang.String apellido) {
        this.apellido = apellido;
    }

	public String getNombreCompleto(){
		if (this.apellido != null){
			return this.nombrePersona+" "+this.apellido;
		}else{
			return this.nombrePersona;
		}
	}

    // ------------- relations ------------------
    // ---------------- business methods  ----------------------
    
     
}
