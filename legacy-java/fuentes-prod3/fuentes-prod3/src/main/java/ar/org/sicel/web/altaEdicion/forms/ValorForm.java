package ar.org.sicel.web.altaEdicion.forms;


import org.apache.struts.validator.ValidatorActionForm;

public class ValorForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = -5819615032461301870L;
	//Valor valor = ValorDAO.create(null,null);
	private Long id = new Long(-1);
	private String nombre= "";
    private String nombreEntidad;
    private String inicio;
    private String fin;
	private String valorPorDefecto = "";
	private boolean esIntervalo= false;
	private String valorAdmitido = "";
	private String idEntidad= "";
	private Long idConjunto= new Long(-1);
	//private Long valorAdmitidoId = new Long(-1);


	public ValorForm() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre= nombre;
	}

	public String getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(String idEntidad) {
		this.idEntidad= idEntidad;
	}

	public Long getIdConjunto() {
		return idConjunto;
	}

	public void setIdConjunto(Long idConjunto) {
		this.idConjunto= idConjunto;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad= nombreEntidad;
	}

	public void setInicio(String inicio) {
		this.inicio= inicio;
	}

	public String getInicio() {
		return inicio;
	}

	public void setFin(String fin) {
		this.fin= fin;
	}

	public String getFin() {
		return fin;
	}

	public void setEsIntervalo(boolean esIntervalo) {
		this.esIntervalo= esIntervalo;
	}

	public boolean getEsIntervalo() {
		return esIntervalo;
	}

	public String getValorPorDefecto() {
		return valorPorDefecto;
	}

	public void setValorPorDefecto(String valorPorDefecto) {
		this.valorPorDefecto = valorPorDefecto;
	}

	public String getValorAdmitido() {
		return valorAdmitido;
	}

	public void setValorAdmitido(String valorAdmitido) {
		this.valorAdmitido = valorAdmitido;
	}

/*	public Long getValorAdmitidoId() {
		return valorAdmitidoId;
	}

	public void setValorAdmitidoId(Long valorAdmitidoId) {
		this.valorAdmitidoId = valorAdmitidoId;
	}*/
}