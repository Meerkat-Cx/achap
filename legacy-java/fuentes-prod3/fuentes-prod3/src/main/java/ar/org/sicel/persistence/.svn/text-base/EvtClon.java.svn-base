package ar.org.sicel.persistence;

import java.util.List;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Serv;


/**
 *
 * @hibernate.joined-subclass
 *    table="Ev_Clon"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvClon_EvServicio"
 *
 */
public class EvtClon extends ar.org.sicel.persistence.EvtServicio {
    // --------------- attributes ---------------------
    private java.lang.String tejido;
    private ar.org.sicel.persistence.Animal donante;

    protected EvtClon() {
    }

    /**
     *
     * @hibernate.property
     *     column="tejido"
     * @hibernate.column
     *     name="tejido"
     *     not-null="true"
     *
     */
    public java.lang.String getTejido() {
        return this.tejido;
    }

    protected void setTejido(java.lang.String tejido) {
        this.tejido = tejido;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="donante"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_EvClon_Anim"
     */
    public ar.org.sicel.persistence.Animal getDonante() {
        return this.donante;
    }

    protected void setDonante(ar.org.sicel.persistence.Animal donante) {
        this.donante = donante;
    }

	
    // ---------------- business methods  ----------------------
    
  
	/* (non-Javadoc)
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		return null;
	}

	
	//@Override
	public Hembra getMadreGenetica() {
		throw new ErrorFatal("Clones no implementados");
	}

	
	//@Override
	public Macho getPadreGenetico() {
		// TODO NOPRIMERAETAPA implementar los clones
		throw new ErrorFatal("Clones no implementados");
	}

	//@Override
	public int getCantidadDiasGestacionADescontar() {
		throw new ErrorFatal("Clones no implementados");
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see ar.org.sicel.persistence.Evento#getResumen()
     */
    public String getResumen() {
        String result = "no implementado";
        return result;
    }

	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		
	}

	@Override
	public EvtAnimalModificacion ejecutarModificacion(Serv servi, List mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		// TODO Auto-generated method stub
		return null;
	}

}
