/**
 * Attention: Generated source (HibernateEntity.vsl)! Do not modify by hand!
 */
package ar.org.sicel.persistence;


/**
 *
 * @hibernate.class
 *     table="Pr_CodMsg"
 *     lazy="true"
 *
 */
public class ProcCodMsg {
    // --------------- attributes ---------------------
    private java.lang.String id;
    private java.lang.String descripcion;
    private String comentario;
    private java.util.Set procMsgsses;
    private String formato;

    protected ProcCodMsg() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="assigned"
     *     column="id"
     * @hibernate.column
     *     name="id"
     *     length="8"
     *     not-null="true"
     *
     */
    public java.lang.String getId() {
        return this.id;
    }

    protected void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     *
     * @hibernate.property
     *     column="descripcion"
     * @hibernate.column
     *     name="descripcion"
     *     not-null="true"
     *
     */
    public java.lang.String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @hibernate.property
     *     column="comentarios"
     *     type="ar.org.sicel.persistence.util.HibernateStringClobType"
     *
     */
    public String getComentario() {
        return this.comentario;
    }

    protected void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /**
     * @hibernate.property
     *     column="FORMATOMSG"
     *     not-null = "false"
     */
 	public String getFormato() {
		return formato;
	}
 	
	/**
	 * @param formato The formato to set.
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="codigoMensaje"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ProcMsg"
     *
     */
    public java.util.Set getProcMsgsses() {
        return this.procMsgsses;
    }

    protected void setProcMsgsses(java.util.Set procMsgsses) {
        this.procMsgsses = procMsgsses;
    }

    // ---------------- business methods  ----------------------
	public String toString()
    {
     return getId()+" ( "+this.getDescripcion()+" ) ";   
    }
}
