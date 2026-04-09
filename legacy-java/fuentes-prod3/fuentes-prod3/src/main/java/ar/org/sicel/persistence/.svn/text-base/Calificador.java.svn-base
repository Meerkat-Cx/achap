package ar.org.sicel.persistence;



/**
 *
 * @hibernate.joined-subclass
 *    table="En_PerCalif"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_PeCa_Pers"
 *
 */
public class Calificador extends ar.org.sicel.persistence.Persona {
    // --------------- attributes ---------------------
    private java.util.Set calificacions;

    protected Calificador() {
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="calificador"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Calificacion"
     *
     */
    public java.util.Set getCalificacions() {
        return this.calificacions;
    }

    protected void setCalificacions(java.util.Set calificacions) {
        this.calificacions = calificacions;
    }
    public String getConjunto(){
    	String d = this.getId().toString()+", "+this.getNombreContacto();
    	return d;
    }

    // ---------------- business methods  ----------------------
}
