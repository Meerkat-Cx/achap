package ar.org.sicel.persistence;



/**
 *
 * @hibernate.joined-subclass
 *    table="En_PerRespSis"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_ReSi_Pers"
 *
 */
public class ResponsableSistema extends ar.org.sicel.persistence.Persona {
    // --------------- attributes ---------------------
    private ar.org.sicel.persistence.Sistema sistema;

    protected ResponsableSistema() {
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.one-to-one
     *     property-ref="responsable"
     *     outer-join="auto"
     *
     */
    public ar.org.sicel.persistence.Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(ar.org.sicel.persistence.Sistema sistema) {
        this.sistema = sistema;
    }

    // ---------------- business methods  ----------------------
}
