package ar.org.sicel.persistence;

import java.util.Iterator;



/**
 *
 * @hibernate.class
 *     table="At_Atributo"
 *     lazy="true"
 *
 */
public class Atributo {
	
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.String nombre;
    private java.util.Set valoresAdmitidos;
    private ar.org.sicel.persistence.ValorAdmAtr valorPorDefecto;
    private String descripcion; 
    private ConjuntoAtributos conjuntoAt;

    protected Atributo() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_at_atributo"
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
    *     column="descripcion"
    *
    */      
    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	  // ------------- relations ------------------

	/**
     *
     * @hibernate.set
     *     lazy="false"
     *     cascade="all"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="atributo"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ValorAdmAtr"
     */
    public java.util.Set getValoresAdmitidos() {
        return this.valoresAdmitidos;
    }

    protected void setValoresAdmitidos(java.util.Set valoresAdmitidos) {
        this.valoresAdmitidos = valoresAdmitidos;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="valor_por_defecto"
     *     not-null="false"
     *     outer-join="auto"
     *     foreign-key="FK_Atri_vaad"
     *     cascade = "save-update"
     *
     */
    public ar.org.sicel.persistence.ValorAdmAtr getValorPorDefecto() {
        return this.valorPorDefecto;
    }

    public void setValorPorDefecto(
        ar.org.sicel.persistence.ValorAdmAtr valorPorDefecto) {
        //es un Set, asi que si ya esta no pasa nada
    	//this.valoresAdmitidos.add(valorPorDefecto);
    	//lo anterior no va porque sino hibernate lo nota cuando pone el valor desde la base,
    	//y quisas marca la coleccion de valores admitidos como dirty
    	
    	this.valorPorDefecto = valorPorDefecto;
    }

    // ---------------- business methods  ----------------------
    
    public ValorAdmAtr getValor(String valor) {
    	Iterator it = getValoresAdmitidos().iterator();
		ValorAdmAtr encontrado = null;
		while (it.hasNext() && (encontrado == null)) {
			ValorAdmAtr v = (ValorAdmAtr)it.next();
			if (v.getValor().equals(valor))
				encontrado = v;
		}
		return encontrado;
    }

	
	public boolean equals(Object o) {
		if (o.getClass() != Atributo.class)
			return false;
		Atributo otro = (Atributo)o;
		return nombre.equals(otro.nombre);
	}

	
	public int hashCode() {
		return nombre.hashCode();
	}
    
    @SuppressWarnings("unchecked")
	public void agregarValorPermitido(String valor) {
    	if (this.getValor(valor) == null) {
    		ValorAdmAtr object = new ValorAdmAtr();
            object.setValor(valor);
            object.setAtributo(this);
            getValoresAdmitidos().add(object);
    	}
    }

	public ConjuntoAtributos getConjuntoAt() {
		return conjuntoAt;
	}

	public void setConjuntoAt(ConjuntoAtributos conjuntoAt) {
		this.conjuntoAt = conjuntoAt;
	}
    
    
}
