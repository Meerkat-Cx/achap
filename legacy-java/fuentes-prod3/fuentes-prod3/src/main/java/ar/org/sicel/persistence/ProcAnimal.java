package ar.org.sicel.persistence;



/**
 *
 * @hibernate.class
 *     table="Pr_Animal"
 *     lazy="true"
 *
 */
public class ProcAnimal {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.util.Set procMsgsses;
    private ar.org.sicel.persistence.Animal animal;
    private ar.org.sicel.persistence.ProcEstablecimiento procEstablecimiento;
    private java.util.Set procEvtAnimals;

    protected ProcAnimal() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_pr_animal"
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
     *     table="Pr_MsgAnim"
     *     cascade="all"
     * @hibernate.collection-key
     *     column="pr_anim"
     *     foreign-key="FK_Pr_mani_pran"
     * @hibernate.collection-many-to-many
     *     column="msg"
     *     class="ar.org.sicel.persistence.ProcMsg"
     *     foreign-key="FK_Pr_mani_msg"
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
     *     column="animal"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_anim_anim"
     *
     */
    public ar.org.sicel.persistence.Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(ar.org.sicel.persistence.Animal animal) {
        this.animal = animal;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="pr_estab"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_Anim_PrEs"
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
     * @hibernate.set
     *     lazy="true"
     *     cascade="all"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="pr_animal"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ProcEvtAnimal"
     *
     */
    public java.util.Set getProcEvtAnimals() {
        return this.procEvtAnimals;
    }

    protected void setProcEvtAnimals(java.util.Set procEvtAnimals) {
        this.procEvtAnimals = procEvtAnimals;
    }

   /*
    public void addAnimal(Animal an) {
    	this.animal = an;
    	an.addProcAnimal(this);
    }
    */
    
    // ---------------- business methods  ----------------------
    @SuppressWarnings({"unchecked","unchecked"})
	public void addProcEvtAnimal(ProcEvtAnimal procEvt) {
        this.procEvtAnimals.add(procEvt);
        procEvt.setProcAnimal(this);
    }

    @SuppressWarnings("unchecked")
	public void addMsg(ProcMsg msg) {
        this.procMsgsses.add(msg);
    }
    
    
}
