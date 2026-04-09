/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import java.util.Map;

import org.apache.struts.action.ActionForm;

import ar.org.sicel.persistence.Boleta;
import ar.org.sicel.persistence.ModeloCalificacion;

/**
 * @author jdivars
 *
 */
public class CalificacionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String idAninal="";
	private String idProp="";
	private String nombreProp="";
	private String idEclo="";
	private String nombreEclo="";
	private String fecha="";
	private String idEstab="";
	private String rp ="";
	private String year="";
	private String numModel="";
	private String tipoReg = "";
    private String numReg = "";
    private String raza = "";
    private String sexo = "";
    private String fechaNac="";
    private String fechaPar="";
    private String puntAnt="";
    private String numParto="";
    private String idCalif="";
    private String nombreCalif="";
    private String nombreEstab="";
    private String numBoleta="";
    private Map partes=null;
    private Map caracteristicas=null;
    private Map defectos=null;
    private ModeloCalificacion modelo=null;
    private String idBol="";
    private Boleta boleta = new Boleta();
    private String comentarios ="";
    private Boolean especial = new Boolean(true);
    
    private String idEstabAn="";
    private String idEcloAn="";
    private String idPropAn="";
    private String idEstanAn = "";
    private String rango = "";
    private String idEstancia = "";
    private String nombreEstancia = "";
   
    
	
   
	public String getIdEstancia() {
		return idEstancia;
	}
	public void setIdEstancia(String idEstancia) {
		this.idEstancia = idEstancia;
	}
	public String getNombreEstancia() {
		return nombreEstancia;
	}
	public void setNombreEstancia(String nombreEstancia) {
		this.nombreEstancia = nombreEstancia;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getIdEcloAn() {
		return idEcloAn;
	}
	public void setIdEcloAn(String idEcloAn) {
		this.idEcloAn = idEcloAn;
	}
	public String getIdEstabAn() {
		return idEstabAn;
	}
	public void setIdEstabAn(String idEstabAn) {
		this.idEstabAn = idEstabAn;
	}
	public String getIdPropAn() {
		return idPropAn;
	}
	public void setIdPropAn(String idPropAn) {
		this.idPropAn = idPropAn;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Boleta getBoleta() {
		return boleta;
	}
	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}
	public ModeloCalificacion getModelo() {
		return modelo;
	}
	public void setModelo(ModeloCalificacion modelo) {
		this.modelo = modelo;
	}
	public void reset(){
		this.setIdEclo("");
		this.setIdProp("");
		this.setNombreEclo("");
		this.setNombreProp("");
		this.setFecha("");
		this.setIdEstab("");
		this.setRp("");
		this.setYear("");
		this.setTipoReg("");
		this.setNumReg("");
		this.setRaza("");
		this.setSexo("");
		this.setFechaNac("");
		this.setFechaPar("");
		this.setPuntAnt("");
		this.setNumParto("");
		this.setIdAninal("");
		this.setNombreEstab("");
		this.setNumBoleta("");
		this.setPartes(null);
		this.setDefectos(null);
		this.setCaracteristicas(null);
		this.setModelo(null);
		this.setBoleta(new Boleta());
		this.setIdBol("");
		this.setComentarios("");
		this.setEspecial(false);
		this.setIdEstabAn("");
		this.setIdEcloAn("");
		this.setIdPropAn("");
		//this.setNumModel("");
    }
	 public String getNumBoleta() {
			return numBoleta;
		}
		public void setNumBoleta(String numBoleta) {
			this.numBoleta = numBoleta;
		}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getFechaPar() {
		return fechaPar;
	}
	public void setFechaPar(String fechaPar) {
		this.fechaPar = fechaPar;
	}
	
	public String getNumParto() {
		return numParto;
	}
	public void setNumParto(String numParto) {
		this.numParto = numParto;
	}
	public String getPuntAnt() {
		return puntAnt;
	}
	public void setPuntAnt(String puntAnt) {
		this.puntAnt = puntAnt;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getNombreEclo() {
		return nombreEclo;
	}
	public void setNombreEclo(String nombreEclo) {
		this.nombreEclo = nombreEclo;
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
	public String getIdProp() {
		return idProp;
	}
	public void setIdProp(String idProp) {
		this.idProp = idProp;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getIdEstab() {
		return idEstab;
	}
	public void setIdEstab(String idEstab) {
		this.idEstab = idEstab;
	}
	public String getNumReg() {
		return numReg;
	}
	public void setNumReg(String numReg) {
		this.numReg = numReg;
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
	public String getTipoReg() {
		return tipoReg;
	}
	public void setTipoReg(String tipoReg) {
		this.tipoReg = tipoReg;
	}
	
	public String getNombreEstab() {
		return nombreEstab;
	}
	public void setNombreEstab(String nombreEstab) {
		this.nombreEstab = nombreEstab;
	}
	public String getIdCalif() {
		return idCalif;
	}
	public void setIdCalif(String idCalif) {
		this.idCalif = idCalif;
	}
	public Map getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(Map caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public Map getDefectos() {
		return defectos;
	}
	public void setDefectos(Map defectos) {
		this.defectos = defectos;
	}
	public Map getPartes() {
		return partes;
	}
	public void setPartes(Map partes) {
		this.partes = partes;
	}
	public String getIdBol() {
		return idBol;
	}
	public void setIdBol(String idBol) {
		this.idBol = idBol;
	}
	public Boolean getEspecial() {
		return especial;
	}
	public void setEspecial(Boolean especial) {
		this.especial = especial;
	}
	public String getIdAninal() {
		return idAninal;
	}
	public void setIdAninal(String idAninal) {
		this.idAninal = idAninal;
	}
	public String getNumModel() {
		return numModel;
	}
	public void setNumModel(String numModel) {
		this.numModel = numModel;
	}
	public String getNombreCalif() {
		return nombreCalif;
	}
	public void setNombreCalif(String nombreCalif) {
		this.nombreCalif = nombreCalif;
	}
	public String getIdEstanAn() {
		return idEstanAn;
	}
	public void setIdEstanAn(String idEstanAn) {
		this.idEstanAn = idEstanAn;
	}
	
	

}
