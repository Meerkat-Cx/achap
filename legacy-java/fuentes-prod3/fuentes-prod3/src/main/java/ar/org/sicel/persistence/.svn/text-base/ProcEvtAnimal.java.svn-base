package ar.org.sicel.persistence;



/**
 *
 * @hibernate.joined-subclass
 *     table="Pr_Ev_Animal"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_Pr_evan_prevt"
 *
 */
public class ProcEvtAnimal extends ProcEvt {
    // --------------- attributes ---------------------
    private ar.org.sicel.persistence.ProcAnimal procAnimal;
    private ar.org.sicel.persistence.EvtAnimal evtAnimal;
    private java.util.Set procMsgsses;

    protected ProcEvtAnimal() {
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="pr_animal"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_evan_pran"
     *
     */
    public ar.org.sicel.persistence.ProcAnimal getProcAnimal() {
        return this.procAnimal;
    }

    protected void setProcAnimal(
        ar.org.sicel.persistence.ProcAnimal procAnimal) {
        this.procAnimal = procAnimal;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="ev_anim"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_evan_EvAnim"
     *     not-null="true"
     *     unique="true"
     *     cascade="all"
     *
     * @hibernate.column
     *     name="ev_anim"
     *     not-null="true"
     *     unique="true"
     *     unique-key="UN_Pr_evan_EvAnim"
     *
     */
    public ar.org.sicel.persistence.EvtAnimal getEvtAnimal() {
        return this.evtAnimal;
    }

    protected void setEvtAnimal(
        ar.org.sicel.persistence.EvtAnimal evtAnimal) {
        this.evtAnimal = evtAnimal;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     table="Pr_MsgEvAnim"
     *     cascade="all"
     * @hibernate.collection-key
     *     column="pr_evAnim"
     *     foreign-key="FK_Pr_meva_prea"
     * @hibernate.collection-many-to-many
     *     column="msg"
     *     class="ar.org.sicel.persistence.ProcMsg"
     *     foreign-key="FK_Pr_meva_msg"
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
    	return this.procAnimal.getProcEstablecimiento().getProcLote();
    }
    @SuppressWarnings("unchecked")
	public void addMsg(ProcMsg msg) {
        this.procMsgsses.add(msg);
    }
    
    public void addEvento(EvtAnimal evt) {
        this.setEvtAnimal(evt);
        if (evt != null)
        	evt.setProcEvtAnimal(this);
        }
}
