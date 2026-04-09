package ar.org.sicel.persistence;




/**
 *
 * @hibernate.joined-subclass
 *    table="En_Regional"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_Regi_Cont"
 *
 */
public class EntidadRegional extends ar.org.sicel.persistence.Contacto {
    // --------------- attributes ---------------------
    private java.util.Set eclos;

    protected EntidadRegional() {
    }
    
    
    

    // ------------- relations ------------------

	/**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="regional"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Eclo"
     *
     */
    public java.util.Set getEclos() {
        return this.eclos;
    }

    protected void setEclos(java.util.Set eclos) {
        this.eclos = eclos;
    }

    // ---------------- business methods  ----------------------
    
    
}
