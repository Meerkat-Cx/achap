package ar.org.sicel.persistence;


/**
 *
 * @hibernate.joined-subclass
 *    table="En_ProveeSmn"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_ProvSmn_Cont"
 *
 */
public class ProveeSmn extends ar.org.sicel.persistence.Contacto {
    // --------------- attributes ---------------------
    private java.util.Set evtSemens;

    protected ProveeSmn() {
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="proveedor"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.EvtSemen"
     *
     */
    public java.util.Set getEvtSemens() {
        return this.evtSemens;
    }

    protected void setEvtSemens(java.util.Set evtSemens) {
        this.evtSemens = evtSemens;
    }

    // ---------------- business methods  ----------------------
}
