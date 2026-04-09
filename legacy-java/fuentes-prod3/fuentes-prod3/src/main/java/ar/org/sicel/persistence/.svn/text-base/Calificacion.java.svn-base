package ar.org.sicel.persistence;

import java.text.SimpleDateFormat;





/**
 *
 * @hibernate.class
 *     table="Ca_Calif"
 *     lazy="true"
 *
 */
public class Calificacion {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.Integer boleta;
    private java.util.Date fecha;
   
    private java.lang.Integer puntaje;
    private java.lang.Integer numeroLactancia;
    private java.lang.String comentarios;
    private java.util.Date inicioLactancia;
    private java.lang.Boolean especial = new Boolean(false);
    private java.lang.String catPuntaje;
    private ar.org.sicel.persistence.Calificador calificador;
    private ar.org.sicel.persistence.Establecimiento establecimiento;
    private ar.org.sicel.persistence.ModeloCalificacion modeloCalificacion;
    private ar.org.sicel.persistence.Animal animal;
    private java.util.Map calificacionDePartes;
    private java.util.Map calificacionDeDefectos;
    private java.util.Map calificacionDeCaracteristicas;
    private ar.org.sicel.persistence.Estancia estancia;
    private ar.org.sicel.persistence.Usuario dataEntry;
    private ar.org.sicel.persistence.Eclo eclo;
    private ar.org.sicel.persistence.Propietario propietario;

    public Calificacion() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_ca_calif"
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
     *     column="boleta"
     * @hibernate.column
     *     name="boleta"
     *     not-null="true"
     *
     */
    public java.lang.Integer getBoleta() {
        return this.boleta;
    }

    public void setBoleta(java.lang.Integer boleta) {
        this.boleta = boleta;
    }

    /**
     *
     * @hibernate.property
     *     column="fecha"
     * @hibernate.column
     *     name="fecha"
     *     not-null="true"
     *
     */
    public java.util.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }
	public String getFechaFormateada(){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");;
		return formato.format(this.fecha);
	}

    /**
     *
     * @hibernate.property
     *     column="puntaje"
     * @hibernate.column
     *     name="puntaje"
     *     not-null="true"
     *
     */
    public java.lang.Integer getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(java.lang.Integer puntaje) {
        this.puntaje = puntaje;
    }

    /**
     *
     * @hibernate.property
     *     column="numLact"
     * @hibernate.column
     *     name="numLact"
     *     not-null="true"
     *
     */
    public java.lang.Integer getNumeroLactancia() {
        return this.numeroLactancia;
    }

    public void setNumeroLactancia(java.lang.Integer numeroLactancia) {
        this.numeroLactancia = numeroLactancia;
    }

    /**
     *
     * @hibernate.property
     *     column="comentarios"
     *
     */
    public java.lang.String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(java.lang.String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     *
     * @hibernate.property
     *     column="iniLact"
     * @hibernate.column
     *     name="iniLact"
     *     not-null="true"
     *
     */
    public java.util.Date getInicioLactancia() {
        return this.inicioLactancia;
    }

    public void setInicioLactancia(java.util.Date inicioLactancia) {
        this.inicioLactancia = inicioLactancia;
    }

    /**
     *
     * @hibernate.property
     *     column="esEspecial"
     *
     */
    public java.lang.Boolean getEspecial() {
        return this.especial;
    }

    public void setEspecial(java.lang.Boolean especial) {
        this.especial = especial;
    }

    /**
     *
     * @hibernate.property
     *     column="catPuntaje"
     * @hibernate.column
     *     name="catPuntaje"
     *     not-null="true"
     *     length="2"
     *
     */
    public java.lang.String getCatPuntaje() {
        return this.catPuntaje;
    }

    public void setCatPuntaje(java.lang.String catPuntaje) {
        this.catPuntaje = catPuntaje;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="calificador"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Cali_Calidor"
     *
     */
    public ar.org.sicel.persistence.Calificador getCalificador() {
        return this.calificador;
    }

    public void setCalificador(
        ar.org.sicel.persistence.Calificador calificador) {
        this.calificador = calificador;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="establecimiento"
     *     not-null="false"
     *     outer-join="auto"
     *     foreign-key="FK_Cali_Estab"
     *
     */
    public ar.org.sicel.persistence.Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }

    public void setEstablecimiento(
        ar.org.sicel.persistence.Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="modelo"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Cali_Modelo"
     *
     */
    public ar.org.sicel.persistence.ModeloCalificacion getModeloCalificacion() {
        return this.modeloCalificacion;
    }

    public void setModeloCalificacion(
        ar.org.sicel.persistence.ModeloCalificacion modeloCalificacion) {
        this.modeloCalificacion = modeloCalificacion;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="animal"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Cali_Animal"
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
     *     column="estancia"
     *     not-null="false"
     *     outer-join="auto"
     *     foreign-key="FK_Cali_Estan"
     *
     */
    public ar.org.sicel.persistence.Estancia getEstancia() {
        return this.estancia;
    }

    public void setEstancia(
        ar.org.sicel.persistence.Estancia estancia) {
        this.estancia = estancia;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="data_entry"
     *     not-null="false"
     *     outer-join="auto"
     *     foreign-key="FK_Cali_Data_Ent"
     *
     */
    public ar.org.sicel.persistence.Usuario getDataEntry() {
        return this.dataEntry;
    }

    public void setDataEntry(
        ar.org.sicel.persistence.Usuario dataEntry) {
        this.dataEntry = dataEntry;
    }

    /**
     *
     * @hibernate.map
     *     table="Ca_Parte"
     *     lazy="true"
     *     cascade="none"
     * @hibernate.collection-key
     *     column="calificacion"
     *     foreign-key="FK_CaPa_cali"
     * @hibernate.index-many-to-many
     *     column="parte"
     *     class="ar.org.sicel.persistence.ParteCalificacion"
     *     foreign-key="FK_CaPa_mdl"
     * @hibernate.collection-composite-element
     *     class="ar.org.sicel.persistence.CatPuntajeValor"
     *
     */
    public java.util.Map getCalificacionDePartes() {
        return this.calificacionDePartes;
    }

    public void setCalificacionDePartes(java.util.Map calificacionDePartes) {
        this.calificacionDePartes = calificacionDePartes;
    }

    /**
     *
     * @hibernate.map
     *     table="Ca_Defecto"
     *     lazy="true"
     *     cascade="none"
     * @hibernate.collection-key
     *     column="calificacion"
     *     foreign-key="FK_Cade_cali"
     * @hibernate.index-many-to-many
     *     column="defecto"
     *     class="ar.org.sicel.persistence.DefectoCalificacion"
     *     foreign-key="FK_cade_mdl"
     * @hibernate.collection-element
     *     column="valor"
     *     type="integer"
     *     not-null="true"
     *
     */
    public java.util.Map getCalificacionDeDefectos() {
        return this.calificacionDeDefectos;
    }

    public void setCalificacionDeDefectos(java.util.Map calificacionDeDefectos) {
        this.calificacionDeDefectos = calificacionDeDefectos;
    }

    /**
     *
     * @hibernate.map
     *     table="Ca_Carac"
     *     lazy="true"
     *     cascade="none"
     * @hibernate.collection-key
     *     column="calificacion"
     *     foreign-key="FK_Cacr_cali"
     * @hibernate.index-many-to-many
     *     column="caracteristica"
     *     class="ar.org.sicel.persistence.CaracteristicaCalificacion"
     *     foreign-key="FK_Cacr_mdl"
     * @hibernate.collection-element
     *     column="valor"
     *     type="integer"
     *     not-null="true"
     *
     */
    public java.util.Map getCalificacionDeCaracteristicas() {
        return this.calificacionDeCaracteristicas;
    }

    public void setCalificacionDeCaracteristicas(
        java.util.Map calificacionDeCaracteristicas) {
        this.calificacionDeCaracteristicas = calificacionDeCaracteristicas;
    }

	public ar.org.sicel.persistence.Eclo getEclo() {
		return eclo;
	}

	public void setEclo(ar.org.sicel.persistence.Eclo eclo) {
		this.eclo = eclo;
	}

	public ar.org.sicel.persistence.Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(ar.org.sicel.persistence.Propietario propietario) {
		this.propietario = propietario;
	}

	

    // ---------------- business methods  ----------------------
}
