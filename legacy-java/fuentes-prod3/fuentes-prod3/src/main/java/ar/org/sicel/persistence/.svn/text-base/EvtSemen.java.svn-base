package ar.org.sicel.persistence;

import java.util.SortedSet;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;


/**
 *
 * @hibernate.joined-subclass
 *    table="Ev_Semen"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvSemen_EvEstab"
 *
 */
public class EvtSemen extends ar.org.sicel.persistence.EvtEstablecimiento {
    // --------------- attributes ---------------------
    private java.lang.Integer cantDosis;
    private ar.org.sicel.persistence.Macho donante;
    private ar.org.sicel.persistence.ProveeSmn proveeSmn;

    protected EvtSemen() {
    }

    /**
     *
     * @hibernate.property
     *     column="cantDosis"
     * @hibernate.column
     *     name="cantDosis"
     *     not-null="true"
     *
     */
    public java.lang.Integer getCantDosis() {
        return this.cantDosis;
    }

    protected void setCantDosis(java.lang.Integer cantDosis) {
        this.cantDosis = cantDosis;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="donante"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_EvSemen_Macho"
     *
     */
    public ar.org.sicel.persistence.Macho getDonante() {
        return this.donante;
    }

    protected void setDonante(ar.org.sicel.persistence.Macho donante) {
        this.donante = donante;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="proveedor"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_EvSemen_ProvSmn"
     *
     */
    public ar.org.sicel.persistence.ProveeSmn getProveeSmn() {
        return this.proveeSmn;
    }

    protected void setProveeSmn(
        ar.org.sicel.persistence.ProveeSmn proveeSmn) {
        this.proveeSmn = proveeSmn;
    }

	/* (non-Javadoc)
	 * @see ar.org.sicel.persistence.Evento#getResumen()
	 */
	public String getResumen() {
		return null;
	}

	/*
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		return EVT_TIPO_SMN;
	}

	
	public SortedSet recolectarTodosEventosDependientes() {
		// TODO Implementar los eventos semen
		return null;
	}


    // ---------------- business methods  ----------------------
	

	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		
	}

}
