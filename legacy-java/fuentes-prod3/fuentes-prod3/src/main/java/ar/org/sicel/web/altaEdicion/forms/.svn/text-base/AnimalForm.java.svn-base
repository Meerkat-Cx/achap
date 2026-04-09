package ar.org.sicel.web.altaEdicion.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

import ar.org.sicel.persistence.Animal;


/**
 *
 * struts.form
 *      name="animalForm"
 *
 */
public class AnimalForm extends ValidatorForm  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7022513961522443704L;

	// Instance fields --------------------------------------------------------
    private Animal animal = null;

    private Long animalId = null;

    private String rp = null;
    private Long estabId = null;

    private String tipoReg = null;
    private String numReg = null;
    private String raza = null;
    private String sexo = null;
    private String nombreEstablecimiento = null;
    private String nombreEclo = null;
    private boolean esHembra;
    protected FormFile foto;
    private Long loteNum = null;
    private Long loteId = null;
    private Long ecloId = null;
    private Long sistema = null;
    private Long centroId = null;
    private String rolAdmin =null;
    private String rolProv =null;
    private String rolGeneral = null;
    private String rolRegional = null;
    private String rol =null;
    private String categoria = null;

    private Integer numLactDescarga =null;

    private String rp1 = null;
    private String propietId = null;
    private String idEclo = null;
    private String tamboId = null;
    private String razaEclo = null;
    private String sexoEclo = null;
    private String nombreProp = null;
    private String destinatario = null;
    private String firmante = null;
    private Boolean mostrarDescendencia = null;
    
    private String year;
    
    private List listaAnimal = null;
    private Boolean existeHBA = false;
    
    private Long idRegional;
    private String delSistema;

    public Long getIdRegional() {
		return idRegional;
	}

	public void setIdRegional(Long idRegional) {
		this.idRegional = idRegional;
	}

	// Constructors -----------------------------------------------------------
    public AnimalForm() {
    }

    // Methods ----------------------------------------------------------------
    /*
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {

        // Llamamos el validador de struts.
        ActionErrors errors = super.validate(mapping, request);

        if (errors == null) {
            errors = new ActionErrors();
        }

        if (errors.isEmpty()) {
            String method = request.getParameter("method");

            if (method.equalsIgnoreCase("porId")){
                validateForId(errors);
            } else if (method.equalsIgnoreCase("porRPyEstab")){
                validateForRPyEstab(errors);
            } else{
                validateForTipoyNumero(errors);
            }
        }

        if (errors.isEmpty()) {
            return null;
        }

        return errors;
    }
*/	public void reset(){
		this.setRp("");
		this.setTipoReg("");
		this.setNombreEclo("");
		this.setNumReg("");
		this.setNombreEstablecimiento("");
		this.setRaza("");
		this.setSexo("");
		this.setEcloId(null);
		this.setLoteNum(null);
		this.setSistema(null);
		this.setCentroId(null);
		this.setLoteId(null);
		//this.setRol("");
		//this.setRolAdmin("");
		this.setNumLactDescarga(null);
		this.setRp1("");
		//this.setPropietId(null);
		//this.setTamboId(null);
		this.setRazaEclo("");
		this.setSexoEclo("");
		this.setDestinatario("");
		this.setFirmante("");
		this.setCategoria("");
		this.setListaAnimal(new ArrayList());
		this.setExisteHBA(false);
		this.setIdRegional(null);
		this.setMostrarDescendencia(false);
		this.setDelSistema("no");
	}
    @SuppressWarnings("unused")
	private void validateForTipoyNumero(ActionErrors errors) {
        if (tipoReg == null | tipoReg.trim().equals("")){
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.tipoReg.required"));
        }
        if (numReg == null || numReg.trim().equals("")){
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.numReg.required"));
        }
    }

    @SuppressWarnings("unused")
	private void validateForRPyEstab(ActionErrors errors) {
        if (rp == null || rp.trim().equals("")){
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.rp.required"));
        }
        if (estabId == null){
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.estabId.required"));
        }
    }

    @SuppressWarnings("unused")
	private void validateForId(ActionErrors errors) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public Long getEstabId() {
        return estabId;
    }

    public void setEstabId(Long estabId) {
        this.estabId = estabId;
    }

    public String getTipoReg() {
        return tipoReg;
    }

    public void setTipoReg(String tipoReg) {
        this.tipoReg = tipoReg;
    }

    public String getNumReg() {
        return numReg;
    }

    public void setNumReg(String numReg) {
        this.numReg = numReg;
    }

	public FormFile getFoto() {
		return foto;
	}

	public void setFoto(FormFile foto) {
		this.foto = foto;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNombreEclo() {
		return nombreEclo;
	}

	public void setNombreEclo(String nombreEclo) {
		this.nombreEclo = nombreEclo;
	}

	public String getNombreEstablecimiento() {
		return nombreEstablecimiento;
	}

	public void setNombreEstablecimiento(String nombreEstablecimiento) {
		this.nombreEstablecimiento = nombreEstablecimiento;
	}

	public Long getEcloId() {
		return ecloId;
	}

	public void setEcloId(Long ecloId) {
		this.ecloId = ecloId;
	}

	

	public Long getLoteNum() {
		return loteNum;
	}

	public void setLoteNum(Long loteNum) {
		this.loteNum = loteNum;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getRolProv() {
		return rolProv;
	}

	public void setRolProv(String rolProv) {
		this.rolProv = rolProv;
	}

	public String getRolAdmin() {
		return rolAdmin;
	}

	public void setRolAdmin(String rolAdmin) {
		this.rolAdmin = rolAdmin;
	}
	public Integer getNumLactDescarga() {
		return numLactDescarga;
	}

	public void setNumLactDescarga(Integer numLactDescarga) {
		this.numLactDescarga = numLactDescarga;
	}

    public String getRp1() {
        return rp1;
    }

    public void setRp1(String rp1) {
        this.rp1 = rp1;
    }

  /*  public Long getPropietId() {
        return propietId;
    }

    public void setPropietId(Long propietId) {
        this.propietId= propietId;
    }*/

	public String getRazaEclo() {
		return razaEclo;
	}

	public void setRazaEclo(String razaEclo) {
		this.razaEclo = razaEclo;
	}

	public String getSexoEclo() {
		return sexoEclo;
	}

	public void setSexoEclo(String sexoEclo) {
		this.sexoEclo = sexoEclo;
	}

	public String getPropietId() {
		return propietId;
	}

	public void setPropietId(String propietId) {
		this.propietId = propietId;
	}

	public String getTamboId() {
		return tamboId;
	}

	public void setTamboId(String tamboId) {
		this.tamboId = tamboId;
	}

	public String getNombreProp() {
		return nombreProp;
	}

	public void setNombreProp(String nombreProp) {
		this.nombreProp = nombreProp;
	}

	public String getIdEclo() {
		return idEclo;
	}

	public void setIdEclo(String idEclo) {
		this.idEclo = idEclo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getFirmante() {
		return firmante;
	}

	public void setFirmante(String firmante) {
		this.firmante = firmante;
	}

	public Long getCentroId() {
		return centroId;
	}

	public void setCentroId(Long centroId) {
		this.centroId = centroId;
	}

	public Long getSistema() {
		return sistema;
	}

	public void setSistema(Long sistema) {
		this.sistema = sistema;
	}

	public List getListaAnimal() {
		return listaAnimal;
	}

	public void setListaAnimal(List listaAnimal) {
		this.listaAnimal = listaAnimal;
	}

	public Boolean getExisteHBA() {
		return existeHBA;
	}

	public void setExisteHBA(Boolean existeHBA) {
		this.existeHBA = existeHBA;
	}

	public boolean isEsHembra() {
		return esHembra;
	}

	public void setEsHembra(boolean esHembra) {
		this.esHembra = esHembra;
	}

	public String getRolGeneral() {
		return rolGeneral;
	}

	public void setRolGeneral(String rolGeneral) {
		this.rolGeneral = rolGeneral;
	}

	public Long getLoteId() {
		return loteId;
	}

	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Boolean getMostrarDescendencia() {
		return mostrarDescendencia;
	}

	public void setMostrarDescendencia(Boolean mostrarDescendencia) {
		this.mostrarDescendencia = mostrarDescendencia;
	}

	public String getDelSistema() {
		return delSistema;
	}

	public void setDelSistema(String delSistema) {
		this.delSistema = delSistema;
	}

	public String getRolRegional() {
		return rolRegional;
	}

	public void setRolRegional(String rolRegional) {
		this.rolRegional = rolRegional;
	}

	/*public Long getTamboId() {
		return tamboId;
	}

	public void setTamboId(Long tamboId) {
		this.tamboId = tamboId;
	}*/
}

