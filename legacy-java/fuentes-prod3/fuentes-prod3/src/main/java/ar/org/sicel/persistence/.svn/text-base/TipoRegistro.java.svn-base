package ar.org.sicel.persistence;

import ar.org.sicel.persistence.excepciones.ErrorFatal;

/**
 * 
 * @hibernate.class table="An_Tipo_Registro" lazy="true"
 * 
 */
public class TipoRegistro {
	// --------------- attributes ---------------------
	private java.lang.String id;

	private java.lang.String descripcion;

	private java.lang.Long numero;

//	private java.lang.Integer prioridad_ori;
//
//	private java.lang.Integer prioridad_id;

	protected TipoRegistro() {
	}

	/**
	 * 
	 * @hibernate.id generator-class="assigned" column="id"
	 * @hibernate.column name="id" length="8" not-null="true"
	 * 
	 */
	public java.lang.String getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 
	 * @hibernate.property column="descripcion"
	 * @hibernate.column name="descripcion" not-null="true" length="40"
	 * 
	 */
	public java.lang.String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @hibernate.property column="numero"
	 * 
	 */
	public java.lang.Long getNumero() {
		return this.numero;
	}

	protected void setNumero(java.lang.Long numero) {
		this.numero = numero;
	}

//	/**
//	 * 
//	 * @hibernate.property column="prioridad_id"
//	 * 
//	 */
//	public java.lang.Integer getPrioridad_id() {
//		return prioridad_id;
//	}
//
//	private void setPrioridad_id(java.lang.Integer prioridad_id) {
//		this.prioridad_id = prioridad_id;
//	}

//	/**
//	 * 
//	 * @hibernate.property column="prioridad_ori"
//	 * 
//	 */
//	public java.lang.Integer getPrioridad_ori() {
//		return prioridad_ori;
//	}
//
//	private void setPrioridad_ori(java.lang.Integer prioridad_ori) {
//		this.prioridad_ori = prioridad_ori;
//	}

	/**
	 * Retorna el proximo numero de registro para 
	 * este tipo de registro, incrementa el contador
	 * 
	 * @return
	 */
	public String proximoNumero() throws ErrorFatal{
		if (numero == null)
			throw new ErrorFatal("Imposible calcular, e tipo de registro " + id + " no es administrado por acha");
		this.numero = numero +1;
		return numero.toString();
	}

	// ------------- relations ------------------
	// ---------------- business methods ----------------------
}
