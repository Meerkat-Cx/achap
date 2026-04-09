package ar.org.sicel.persistence;


import java.util.Date;


/**
 *
 * @hibernate.class
 *     table="At_Valor"
 *     lazy="true"
 *
 */
public class Valor {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private ar.org.sicel.persistence.ValorAdmAtr valorAdmAtr;
    private java.util.Date inicio;
    private java.util.Date fin;
    private ar.org.sicel.persistence.AtrVariables atrVariable;
    private String idEntidad;
    private String nombreEntidad;

	
    protected Valor() {
    }


    /**
     *
     * @hibernate.property
     *     column="inicio"
     *
     */
    public java.util.Date getInicio() {
        return this.inicio;
    }

    public void setInicio(java.util.Date inicio) {
        this.inicio = inicio;
    }

    /**
    *
    * @hibernate.property
    *     column="fin"
    *
    */
   public java.util.Date getFin() {
       return this.fin;
   }

   public void setFin(java.util.Date inicio) {
       this.fin = inicio;
   }

    
    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_at_valor"
     *
     */
    public java.lang.Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
        this.id = id;
    }

    

    // ------------- relations ------------------

    
    /**
	 * @hibernate.many-to-one 
	 * 		column = "atr_variable" 
	 */
	public AtrVariables getAtrVariable() {
		return atrVariable;
	}
	/**
	 * @param atrVariable The atrVariable to set.
	 * 
	 * OJO: Poner esto protected
	 * 
	 */
	public void setAtrVariable(AtrVariables atrVariable) {
		this.atrVariable = atrVariable;
	}

	
    
    /**
     *
     * @hibernate.many-to-one
     *     column="valor"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Valo_VaAd"
     *
     */
    public ar.org.sicel.persistence.ValorAdmAtr getValorAdmAtr() {
        return this.valorAdmAtr;
    }

    protected void setValorAdmAtr(
        ar.org.sicel.persistence.ValorAdmAtr valorAdmAtr) {
        this.valorAdmAtr = valorAdmAtr;
    }

    // ---------------- business methods  ----------------------
    
    
    public boolean esSeteadoAntesDe(Date fecha) {
    	if (inicio == null || inicio.before(fecha)) {
    		return true;
    	}
    	else
    		return false;
    }
    
    /**
     * Verdadero si es este valor valido en una fecha posterior al pasado como parametro 
     * @param otro
     * @return
     */
    public boolean esPosteriorA(Valor otro) {
    	if (this.inicio == null)
    		return false;
    	if (otro.inicio == null)
    		return true;
    	return this.inicio.after(otro.inicio);
    }
    

    public String getIdEntidad() {
        return this.idEntidad;
    }

	public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public void setNombreEntidad(String nombreEntidad){
    	this.nombreEntidad= nombreEntidad;
    }
    
    public String getNombreEntidad(){
    	return nombreEntidad;
    }
    
 public String toString() {
 	return "[" + inicio +" .. " + fin + ")" + this.valorAdmAtr.getAtributo().getNombre() + " = " + this.valorAdmAtr.getValor(); 
 }
}
