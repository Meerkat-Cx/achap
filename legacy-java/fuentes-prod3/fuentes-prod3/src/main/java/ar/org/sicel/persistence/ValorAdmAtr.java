package ar.org.sicel.persistence;


/**
 *
 * @hibernate.class
 *     table="At_Valor_Admitido"
 *     lazy="true"
 *
 */
public class ValorAdmAtr {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.String valor;
    private ar.org.sicel.persistence.Atributo atributo;

    protected ValorAdmAtr() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_at_valor_admitido"
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
     *     column="valor"
     * @hibernate.column
     *     name="valor"
     *     length="20"
     *
     */
    public java.lang.String getValor() {
        return this.valor;
    }

    public void setValor(java.lang.String valor) {
    //	Configuracion.suciaCache = true;
        this.valor = valor;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="atributo"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Vaad_Atri"
     *
     */
    public ar.org.sicel.persistence.Atributo getAtributo() {
        return this.atributo;
    }

    protected void setAtributo(ar.org.sicel.persistence.Atributo atributo) {
        this.atributo = atributo;
    }

    // ---------------- business methods  ----------------------
}
