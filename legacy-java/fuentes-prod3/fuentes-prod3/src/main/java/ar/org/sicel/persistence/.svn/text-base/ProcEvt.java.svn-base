/**
 * Attention: Generated source (HibernateEntity.vsl)! Do not modify by hand!
 */
package ar.org.sicel.persistence;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;


/**
 *
 * @hibernate.class
 *     table="Pr_Evt"
 *     lazy="true"
 *
 */
public abstract class ProcEvt {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.Long idEvtECLO;
    

    protected ProcEvt() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_pr_avt"
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
     *     column="id_ev_infote"
     *     type="java.lang.Long"
     *
     * @hibernate.column
     *     name="id_ev_infote"
     *     not-null="true"
     *
     */
    public java.lang.Long getIdEvtECLO() {
        return this.idEvtECLO;
    }

    protected void setIdEvtECLO(java.lang.Long idEvtECLO) {
        this.idEvtECLO = idEvtECLO;
    }

    // ------------- relations ------------------
    // ---------------- business methods  ----------------------
    
    public abstract ProcLote getLote();
    
    
    
    protected void checkIdEventoEcloUnico(Eclo eclo,Sistema sistema, CentroDeComputo centro,Long id) throws ExcepcionIntegridad {
    	/*como esta no llamar a este metodo, ya ue ahora el evento es unico por eclo-sistema-centro no como esta aca abajo*/
    	if (eclo.getUltimoNumeroEvento() != null && id <= eclo.getUltimoNumeroEvento()) 
    		throw new ExcepcionIntegridad(MENSAJES.EVT_FUERA_ORDEN, new String[]{eclo.getId().toString(),sistema.getNombre(),centro==null?" sin centro de computo":"centro de computo "+ centro.getId(), eclo.getUltimoNumeroEvento().toString()});
    }
}
