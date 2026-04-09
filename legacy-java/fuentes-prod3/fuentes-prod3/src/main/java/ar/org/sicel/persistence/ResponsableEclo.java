package ar.org.sicel.persistence;



/**
 *
 * @hibernate.joined-subclass
 *    table="En_PerRespEclo"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_ReEc_Pers"
 *
 */
public class ResponsableEclo extends ar.org.sicel.persistence.Persona {
    // --------------- attributes ---------------------
    private ar.org.sicel.persistence.Eclo eclo;

    protected ResponsableEclo() {
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.one-to-one
     *     property-ref="responsable"
     *     outer-join="auto"
     *
     */
    public Eclo getEclo() {
        return eclo;
    }

    public void setEclo(Eclo eclo) {
        this.eclo = eclo;
    }

    // ---------------- business methods  ----------------------
}
