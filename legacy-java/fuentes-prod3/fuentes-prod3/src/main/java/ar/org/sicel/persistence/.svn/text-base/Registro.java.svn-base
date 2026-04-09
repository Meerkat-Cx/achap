package ar.org.sicel.persistence;

import java.util.Date;


/**
 *
 * @hibernate.class
 *     table="An_Registro"
 *     lazy="true"
 *
 */
public class Registro {
    // --------------- attributes ---------------------
    private Long id;
    private String numero;
    private TipoRegistro tipoRegistro;
    private Animal animal;
    private Integer codigoBaja;
    private Date fechaBaja;
    

    public Registro() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_an_registro"
     *
     */
    public java.lang.Long getId() {
        return this.id;
    }
    public boolean equals(Object ani){
		if(ani==null)
			return false;
    	if(!(ani instanceof Registro))
			return false;
		Registro re = (Registro)ani;
		if(this.getTipoRegistro().getId().equals(re.getTipoRegistro().getId()))
			if(this.getNumero().equals(re.getNumero()))
				return true;
		return false;
	}
    public int hashCode()
	{
    	if(tipoRegistro.getId() != null && numero!=null)
			return tipoRegistro.getId().hashCode() & numero.hashCode();
		else
			return super.hashCode();
	}

    @SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     *
     * @hibernate.property
     *     column="numero"
     * @hibernate.column
     *     name="numero"
     *     not-null="true"
     *     length="20"
     *
     */
    public java.lang.String getNumero() {
        return this.numero;
    }

    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="treg"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Regi_TReg"
     *
     */
    public ar.org.sicel.persistence.TipoRegistro getTipoRegistro() {
        return this.tipoRegistro;
    }

    public void setTipoRegistro(
        ar.org.sicel.persistence.TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="animal"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Regi_Anim"
     *
     */
    public ar.org.sicel.persistence.Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(ar.org.sicel.persistence.Animal animal) {
        this.animal = animal;
    }

    public Integer getCodigoBaja() {
		return codigoBaja;
	}

	public void setCodigoBaja(Integer codigoBaja) {
		this.codigoBaja = codigoBaja;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

    // ---------------- business methods  ----------------------
    
    public String toString() {
    	String tReg = getTipoRegistro().getId();
		String nReg = getNumero();
		return tReg + " " + nReg;
    }
    
}

