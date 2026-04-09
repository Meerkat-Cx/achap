package ar.org.sicel.persistence;

import java.util.Date;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.util.DateUtils;



/**
 * 
 * @hibernate.joined-subclass table="Ev_LactMigrada" lazy="true"
 *  
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvLactMig_EvAnim"
 * 
 */
public class EvtLactanciaMigrada extends ar.org.sicel.persistence.EvtAnimal implements Lactancia {
	// --------------- attributes ---------------------
	private java.lang.Integer nroLact;

	private java.lang.String tipoLact;

	private java.util.Date fechaInicio; 

	private java.lang.Integer dias;

	private java.lang.Integer anios;

	private java.lang.Integer meses;

	private java.lang.String tipoCert;

	private java.lang.Integer nroCert;

	private java.lang.Float leche;

	private java.lang.Float grasa;

	private java.lang.Float proteinas;

	private java.lang.Integer celulas;

	private java.lang.Float solidosNoGrasos;

	private java.lang.Float solidosTotales;

	private java.lang.Float urea;

	private java.lang.String tipoRegistro;

	private java.lang.String numeroRegistro;

	private java.lang.Integer ordenies;

	private java.lang.String control;

	private java.lang.String muestreo;

	private java.lang.String analisis;

	private java.lang.Integer epar;

	private java.lang.Integer eppar;

	private java.lang.Integer diasa;

	private java.lang.Integer ipar;

	private java.lang.String flac;

	private java.lang.Integer ilac;

	private java.lang.Integer dcon;

	private java.lang.Integer tlac;

	private java.lang.String eac;

	private java.lang.String cnor;

	protected EvtLactanciaMigrada() {
	}

	/**
	 * 
	 * @hibernate.property column="nroLact"
	 * 
	 */
	public java.lang.Integer getNroLact() {
		return this.nroLact;
	}

	public void setNroLact(java.lang.Integer nroLact) {
		this.nroLact = nroLact;
	}

	/**
	 * 
	 * @hibernate.property column="tipoLact"
	 * @hibernate.column name="tipoLact" not-null="false" length="4"
	 * 
	 */
	public java.lang.String getTipoLact() {
		return this.tipoLact;
	}

	protected void setTipoLact(java.lang.String tipoLact) {
		this.tipoLact = tipoLact;
	}

	/**
	 * 
	 * @hibernate.property column="inicio"
	 * 
	 */
	public java.util.Date getFechaInicio() {
		return this.fechaInicio;
	}

	protected void setFechaInicio(java.util.Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * 
	 * @hibernate.property column="dias"
	 * 
	 */
	public java.lang.Integer getDias() {
		return this.dias;
	}

	protected void setDias(java.lang.Integer dias) {
		this.dias = dias;
	}

	/**
	 * 
	 * @hibernate.property column="anios"
	 * 
	 */
	public java.lang.Integer getAnios() {
		return this.anios;
	}

	protected void setAnios(java.lang.Integer anios) {
		this.anios = anios;
	}

	/**
	 * 
	 * @hibernate.property column="meses"
	 * 
	 */
	public java.lang.Integer getMeses() {
		return this.meses;
	}

	protected void setMeses(java.lang.Integer meses) {
		this.meses = meses;
	}

	/**
	 * 
	 * @hibernate.property column="tipoCert"
	 * @hibernate.column name="tipoCert" not-null="false" length="4"
	 * 
	 */
	public java.lang.String getTipoCert() {
		return this.tipoCert;
	}

	protected void setTipoCert(java.lang.String tipoCert) {
		this.tipoCert = tipoCert;
	}

	/**
	 * 
	 * @hibernate.property column="nroCert"
	 * 
	 */
	public java.lang.Integer getNroCert() {
		return this.nroCert;
	}

	protected void setNroCert(java.lang.Integer nroCert) {
		this.nroCert = nroCert;
	}

	/**
	 * @hibernate.property column="leche"
	 * 
	 */
	public java.lang.Float getLeche() {
		return this.leche;
	}

	protected void setLeche(java.lang.Float leche) {
		this.leche = leche;
	}

	/**
	 * @hibernate.property column="grasa"
	 * 
	 * 
	 */
	public java.lang.Float getGrasaAbsoluto() {
		return this.grasa;
	}

	protected void setGrasaAbsoluto(java.lang.Float grasa) {
		this.grasa = grasa;
	}

	/** 
	 * @hibernate.property column="proteinas"
	 * 
	 * 
	 */
	public java.lang.Float getProteinasAbsoluto() {
		return this.proteinas;
	}

	protected void setProteinasAbsoluto(java.lang.Float proteinas) {
		this.proteinas = proteinas;
	}

	/**
	 * 
	 * @hibernate.property column="celulas"
	 * 
	 */
	public java.lang.Integer getCelulas() {
		return this.celulas;
	}

	protected void setCelulas(java.lang.Integer celulas) {
		this.celulas = celulas;
	}

	/**
	 * 
	 * @hibernate.property column="solidosNoGrasos"
	 * 
	 */
	public java.lang.Float getSolidosNoGrasos() {
		return this.solidosNoGrasos;
	}

	protected void setSolidosNoGrasos(java.lang.Float solidosNoGrasos) {
		this.solidosNoGrasos = solidosNoGrasos;
	}

	/**
	 * 
	 * @hibernate.property column="solidosTotales"
	 * 
	 */
	public java.lang.Float getSolidosTotalesAbsoluto() {
		return this.solidosTotales;
	}

	protected void setSolidosTotalesAbsoluto(java.lang.Float solidosTotales) {
		this.solidosTotales = solidosTotales;
	}

	/**
	 * 
	 * @hibernate.property column="urea"
	 * 
	 */
	public java.lang.Float getUrea() {
		return this.urea;
	}

	protected void setUrea(java.lang.Float urea) {
		this.urea = urea;
	}

	/**
	 * 
	 * @hibernate.property column="tipoRegistro"
	 * @hibernate.column name="tipoRegistro" not-null="false" length="4"
	 * 
	 */
	public java.lang.String getTipoRegistro() {
		return this.tipoRegistro;
	}

	protected void setTipoRegistro(java.lang.String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	/**
	 * 
	 * @hibernate.property column="numeroRegistro"
	 * @hibernate.column name="numeroRegistro" not-null="false" length="10"
	 * 
	 */
	public java.lang.String getNumeroRegistro() {
		return this.numeroRegistro;
	}

	protected void setNumeroRegistro(java.lang.String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	/** 
	 * @hibernate.property column="ordenies"
	 * 
	 */
	public java.lang.Integer getOrdenies() {
		return this.ordenies;
	}

	protected void setOrdenies(java.lang.Integer ordenies) {
		this.ordenies = ordenies;
	}

	/**
	 * @hibernate.property column="control"
	 * @hibernate.column name="control" not-null="false" length="4"
	 * 
	 */
	public java.lang.String getControl() {
		return this.control;
	}

	protected void setControl(java.lang.String control) {
		this.control = control;
	}

	/** 
	 * @hibernate.property column="muestreo"
	 * @hibernate.column name="muestreo" not-null="false" length="4"
	 * 
	 */
	public java.lang.String getMuestreo() {
		return this.muestreo;
	}

	protected void setMuestreo(java.lang.String muestreo) {
		this.muestreo = muestreo;
	}

	/**
	 * 
	 * @hibernate.property column="analisis"
	 * @hibernate.column name="analisis" not-null="false" length="4"
	 * 
	 */
	public java.lang.String getAnalisis() {
		return this.analisis;
	}

	protected void setAnalisis(java.lang.String analisis) {
		this.analisis = analisis;
	}

	/**
	 * 
	 * @hibernate.property column="epar"
	 * 
	 */
	public java.lang.Integer getEpar() {
		return this.epar;
	}

	protected void setEpar(java.lang.Integer epar) {
		this.epar = epar;
	}

	/**
	 * 
	 * @hibernate.property column="eppar"
	 * 
	 */
	public java.lang.Integer getEppar() {
		return this.eppar;
	}

	protected void setEppar(java.lang.Integer eppar) {
		this.eppar = eppar;
	}

	/**
	 * 
	 * @hibernate.property column="diasa"
	 * 
	 */
	public java.lang.Integer getDiasa() {
		return this.diasa;
	}

	protected void setDiasa(java.lang.Integer diasa) {
		this.diasa = diasa;
	}

	/**
	 * 
	 * @hibernate.property column="ipar"
	 * 
	 */
	public java.lang.Integer getIpar() {
		return this.ipar;
	}

	protected void setIpar(java.lang.Integer ipar) {
		this.ipar = ipar;
	}

	/**
	 * 
	 * @hibernate.property column="flac"
	 * @hibernate.column name="flac" not-null="false" length="1"
	 * 
	 */
	public java.lang.String getFlac() {
		return this.flac;
	}

	protected void setFlac(java.lang.String flac) {
		this.flac = flac;
	}

	/**
	 * 
	 * @hibernate.property column="ilac"
	 * 
	 */
	public java.lang.Integer getIlac() {
		return this.ilac;
	}

	protected void setIlac(java.lang.Integer ilac) {
		this.ilac = ilac;
	}

	/**
	 * 
	 * @hibernate.property column="dcon"
	 * 
	 */
	public java.lang.Integer getDcon() {
		return this.dcon;
	}

	protected void setDcon(java.lang.Integer dcon) {
		this.dcon = dcon;
	}

	/**
	 * 
	 * @hibernate.property column="tlac"
	 * 
	 */
	public java.lang.Integer getTlac() {
		return this.tlac;
	}

	protected void setTlac(java.lang.Integer tlac) {
		this.tlac = tlac;
	}

	/**
	 * 
	 * @hibernate.property column="eac"
	 * @hibernate.column name="eac" not-null="false" length="1"
	 * 
	 */
	public java.lang.String getEac() {
		return this.eac;
	}

	protected void setEac(java.lang.String eac) {
		this.eac = eac;
	}

	/**
	 * 
	 * 
	 * @hibernate.property column="cnor"
	 * @hibernate.column name="cnor" not-null="false" length="2"
	 * 
	 */
	public java.lang.String getCnor() {
		return this.cnor;
	}

	protected void setCnor(java.lang.String cnor) {
		this.cnor = cnor;
	}
	
	public Float getPorcentajeGrasa() {
		if ((getLeche() != null) && (getGrasaAbsoluto() != null))
			return (getGrasaAbsoluto() / getLeche()) * 100;
		else 
			return null;
	}
	
	public Float getPorcentajeProteinas() {
		if ((getLeche() != null) && (getProteinasAbsoluto() != null))
			return (getProteinasAbsoluto() / getLeche()) * 100;
		else
			return null;
	}
	
	public Float getPorcentajeSolidosTotales() {
		if ((getLeche() != null) && (getSolidosTotalesAbsoluto() != null))
			return (getSolidosTotalesAbsoluto() / getLeche()) *100;
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.org.sicel.persistence.Evento#getResumen()
	 */
	public String getResumen() {
		return null;
	}

	/*
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		return EVT_TIPO_LMI;
	}

	public Boolean isEsOficial() {
		// TODO como se calcula?
		return false;
	}
	
	// ------------- relations ------------------
	// ---------------- business methods ----------------------

	public String toString() {
		return getNroLact() + "-" + 
		       getTipoLact() + " " +
		       String.format("%tF",new Object[]{getFechaInicio()}) 	+ " " + 
		       getDias() + " " + 
		       getLeche() + " " + 
		       getGrasaAbsoluto() + " "	+ 
		       getProteinasAbsoluto() + ".";
	}
	
	/*
	public String getClazzParaTransicion() {
		return EvtLactanciaMigrada.class.getSimpleName(); 
	}
	*/

	
	public Lactancia getLactancia(Integer aXDias) {
		EvtLactanciaMigrada lactanciaMigrada = new EvtLactanciaMigrada();		
		Float proporcion = aXDias.floatValue()/this.getDias();
		
		lactanciaMigrada.setFechaInicio(this.getFechaInicio());
		lactanciaMigrada.setNroLact(this.getNroLact());
		lactanciaMigrada.setAnios(this.getAnios());
		lactanciaMigrada.setMeses(this.getMeses());
		lactanciaMigrada.setDias(aXDias);
		lactanciaMigrada.setOrdenies(this.getOrdenies());
			
		if(this.getLeche()!=null) 
			lactanciaMigrada.setLeche(this.getLeche()*proporcion);
		if(this.getGrasaAbsoluto()!=null)					
			lactanciaMigrada.setGrasaAbsoluto(this.getGrasaAbsoluto()*proporcion);			
		if(this.getProteinasAbsoluto()!=null)
			lactanciaMigrada.setProteinasAbsoluto(this.getProteinasAbsoluto()*proporcion);
			
		lactanciaMigrada.setControl(this.getControl());
		return lactanciaMigrada;			
	}

	public IProduccionLactancia getLactanciaA(String nombre) {
		if (nombre.equals("REAL"))
				if(this.getDias().intValue()<=305)
					return new ProduccionLactanciaMigrada(this, this.getDias());
				else return null;
		else
			{
				
				int dias = Integer.parseInt(nombre);
				if((dias == 305)&&(this.getDias().intValue()>=305)){
					return new ProduccionLactanciaMigrada(this,this.getDias());
				}
				return null;
			}
		
			
	}

	
	
	public String getCodigoInicio() {
		return "N/D";
	}

	public String getCausaFin() {
		return "N/D";
	}

	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		
	}
	public Date getFecha(){
		return DateUtils.mas(this.getFechaInicio(), this.getDias());
		
	}

	public boolean isSicel3() {
		
		return false;
	}

}
