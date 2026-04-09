package ar.org.sicel.persistence;



/**
 *
 * @hibernate.joined-subclass
 *     table="Pr_Ev_Estab"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_Pr_eves_Prevt"
 *
 */
public class ProcEvtEst extends ProcEvt {
    // --------------- attributes ---------------------
    private ar.org.sicel.persistence.ProcEstablecimiento procEstablecimiento;
    private ar.org.sicel.persistence.EvtEstablecimiento evtEstablecimiento;
    private java.util.Set procMsgsses;

    protected ProcEvtEst() {
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="pr_estab"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_eves_prEs"
     *
     */
    public ar.org.sicel.persistence.ProcEstablecimiento getProcEstablecimiento() {
        return this.procEstablecimiento;
    }

    protected void setProcEstablecimiento(
        ar.org.sicel.persistence.ProcEstablecimiento procEstablecimiento) {
        this.procEstablecimiento = procEstablecimiento;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="ev_estab"
     *     outer-join="auto"
     *     not-null="true"
     *     foreign-key="FK_Pr_eves_EvEstab"
     *     unique="true"
     *     cascade = "all"
     *
     * @hibernate.column
     *     name="ev_estab"
     *     not-null="true"
     *     unique="true"
     *     unique-key="UN_Pr_eves_EvEstab"
     *
     */
    public ar.org.sicel.persistence.EvtEstablecimiento getEvtEstablecimiento() {
        return this.evtEstablecimiento;
    }

    protected void setEvtEstablecimiento(
        ar.org.sicel.persistence.EvtEstablecimiento evtEstablecimiento) {
        this.evtEstablecimiento = evtEstablecimiento;

    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     table="Pr_MsgEvEstab"
     *     cascade="all"
     * @hibernate.collection-key
     *     column="pr_evestab"
     *     foreign-key="FK_Pr_meve_pree"
     * @hibernate.collection-many-to-many
     *     column="msg"
     *     class="ar.org.sicel.persistence.ProcMsg"
     *     foreign-key="FK_Pr_meve_msg"
     *
     */
    public java.util.Set getProcMsgsses() {
        return this.procMsgsses;
    }

    protected void setProcMsgsses(java.util.Set procMsgsses) {
        this.procMsgsses = procMsgsses;
    }

    // ---------------- business methods  ----------------------
    
    public ProcLote getLote() {
    	return this.getProcEstablecimiento().getProcLote();
    }

    
    @SuppressWarnings("unchecked")
	public void addMsg(ProcMsg msg) {
        this.procMsgsses.add(msg);
    }
    
    public void addEventoEstablecimiento(EvtEstablecimiento evt) {
    setEvtEstablecimiento(evt);
    if (evt != null)
    	evt.setProcEvtEst(this);
    }
}
