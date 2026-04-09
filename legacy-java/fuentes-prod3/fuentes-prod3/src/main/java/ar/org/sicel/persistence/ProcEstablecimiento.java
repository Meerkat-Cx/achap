package ar.org.sicel.persistence;



/**
 *
 * @hibernate.class
 *     table="Pr_Establecimiento"
 *     lazy="true"
 *
 */
public class ProcEstablecimiento {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.util.Set procMsgsses;
    private ar.org.sicel.persistence.Establecimiento establecimiento;
    private ar.org.sicel.persistence.ProcLote procLote;
    private java.util.Set procAnimals;
    private java.util.Set procEvtEsts;

    protected ProcEstablecimiento() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_pr_establecimiento"
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
     *
     * @hibernate.set
     *     lazy="true"
     *     table="Pr_MsgEstab"
     *     cascade="all"
     * @hibernate.collection-key
     *     column="pr_estab"
     *     foreign-key="FK_Pr_mest_pres"
     * @hibernate.collection-many-to-many
     *     column="msg"
     *     class="ar.org.sicel.persistence.ProcMsg"
     *     foreign-key="FK_Pr_mest_msg"
     *
     */
    public java.util.Set getProcMsgsses() {
        return this.procMsgsses;
    }

    protected void setProcMsgsses(java.util.Set procMsgsses) {
        this.procMsgsses = procMsgsses;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="establecimiento"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_esta_Esta"
     *
     */
    public ar.org.sicel.persistence.Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }

    protected void setEstablecimiento(
        ar.org.sicel.persistence.Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="lote"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_esta_Lote"
     *
     */
    public ar.org.sicel.persistence.ProcLote getProcLote() {
        return this.procLote;
    }

    protected void setProcLote(
        ar.org.sicel.persistence.ProcLote procLote) {
        this.procLote = procLote;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="all"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="pr_estab"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ProcAnimal"
     *
     */
    public java.util.Set getProcAnimals() {
        return this.procAnimals;
    }

    protected void setProcAnimals(java.util.Set procAnimals) {
        this.procAnimals = procAnimals;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="all"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="pr_estab"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ProcEvtEst"
     *
     */
    public java.util.Set getProcEvtEsts() {
        return this.procEvtEsts;
    }

    protected void setProcEvtEsts(java.util.Set procEvtEsts) {
        this.procEvtEsts = procEvtEsts;
    }

    @SuppressWarnings("unchecked")
	public void addProcEvtEsts(ProcEvtEst evtEst) {
        this.procEvtEsts.add(evtEst);
        evtEst.setProcEstablecimiento(this);
    }

    @SuppressWarnings("unchecked")
	public void addProcAnimal(ProcAnimal procAnim) {
        this.procAnimals.add(procAnim);
        procAnim.setProcEstablecimiento(this);
    }

    @SuppressWarnings({"unchecked","unchecked"})
	public void addMsg(ProcMsg msg) {
        this.procMsgsses.add(msg);
    }
    
    // tuve q dejarlo public porque daba un extraño error 
    // de protected en el test de altaanimal
    /*
    public void addEstablecimiento(Establecimiento est) {
    	
    	if (this.establecimiento != null)
    		establecimiento.removeProcEstablecimiento(this);
    	est.addProcEstablecimiento(this);
    	
    	this.setEstablecimiento(est);
    }
    */
    // ---------------- business methods  ----------------------
   }
