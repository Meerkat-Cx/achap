/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


/**
 * @author jdivars
 * @since 8-05-2007
 */
public class PedigreeForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String rp;
	private String sexo ;
	private String fechaNac;
	protected FormFile foto;
	private String raza;
	private Boolean propSelect;
	private Boolean propCriSelect;
	private String tipoRegOri;
	private String tipoRegId;
	private String numRegOri;
	private String numRegId;
	private String nombreProp;
	private String nombreEstab;
	private String nombrePropCri;
	private String nombreEstabCri;
	private String padre;
	private String madre;
	private String idPadre;
	private String idMadre;
	private String idTam;
	private String idProp;
	private String idPropCri;
	private String idTamCriador;
	private String tipoReg;
	private String numReg;
	private String razaP;
	private String rpMadre;
	private String nombreMadre;
	private String rpPadre;
	private String nombrePadre;
	private String sexoP;
	private String nombrePM;
	private String coBajOri;
	private String coBajId;
	private String feBajOri;
	private String feBajId;
	private String coBaj;
	private String feBaj;
	private Set registros = new HashSet();
	private Boolean esOri = new Boolean(false);
	private Boolean esId = new Boolean(false);
	private String dado;
	private String tran;
	private String anls;
	private String dona;
	private String mell;
	private String trns;
	private String tserv;
	private String fesb;
	private String ftrt;
	private String fserv;
	private String fobs;
	private String apodo;
	private String idAnimal;
	private Boolean tieneHijos=new Boolean(false);
	private Boolean tieneEventos=new Boolean(false);
	private Boolean tieneEvt=new Boolean(false);
	private Boolean tieneCalif=new Boolean(false);
	private Long idFoto;
	private Set comentarios = new HashSet();
	private String comentario;
	private Boolean inter=new Boolean(false);
	private String idPropInt;
	private String nombrePropInt;
	private String desde;
	private String hasta;
	private String rpti;
	private String categoria;
	private String taraG;
	
	
	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public Boolean getInter() {
		return inter;
	}

	public void setInter(Boolean inter) {
		this.inter = inter;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public Set getRegistros() {
		return registros;
	}

	public void setRegistros(Set registros) {
		this.registros = registros;
	}

	public String getSexoP() {
		return sexoP;
	}

	public void setSexoP(String sexoP) {
		this.sexoP = sexoP;
	}

	public String getNumReg() {
		return numReg;
	}

	public void setNumReg(String numReg) {
		this.numReg = numReg;
	}

	public String getRazaP() {
		return razaP;
	}

	public void setRazaP(String razaP) {
		this.razaP = razaP;
	}

	public String getTipoReg() {
		return tipoReg;
	}

	public void setTipoReg(String tipoReg) {
		this.tipoReg = tipoReg;
	}

	public String getIdProp() {
		return idProp;
	}

	public void setIdProp(String idProp) {
		this.idProp = idProp;
	}

	public String getIdTam() {
		return idTam;
	}

	public void setIdTam(String idTam) {
		this.idTam = idTam;
	}

	public String getIdTamCriador() {
		return idTamCriador;
	}

	public void setIdTamCriador(String idTamCriador) {
		this.idTamCriador = idTamCriador;
	}

	public void reset(){
	//	super.reset(ac, req);
		this.setNombre("");
		this.setRp("");
	//	this.setSexo("");
		this.setFechaNac("");
		this.setRaza("");
		this.setTipoRegOri("");
		this.setTipoRegId("");
		this.setNumRegOri("");
		this.setNumRegId("");
		this.setNombreProp("");
		this.setNombrePropCri("");
		this.setNombreEstab("");
		this.setNombreEstabCri("");
		this.setPadre("");
		this.setMadre("");
		this.setIdPadre("");
		this.setIdMadre("");
		this.setIdProp("");
		this.setIdPropCri("");
		this.setIdTam("");
		this.setIdTamCriador("");
	//	this.setTipoReg("");
	//	this.setNumReg("");
	//	this.setRazaP("");
		this.setFoto(null);
		this.setPropSelect(false);
		this.setPropCriSelect(false);
		this.setRpMadre("");
		this.setNombreMadre("");
		this.setRpPadre("");
		this.setNombrePadre("");
		this.setSexoP("");
		this.setNombrePM("");
		this.setCoBajOri("");
		this.setCoBajId("");
		this.setFeBajOri("");
		this.setFeBajId("");
		this.setCoBaj("");
		this.setFeBaj("");
		this.setEsOri(false);
		this.setEsId(false);
		this.setDado("");
		this.setTran("");
		this.setAnls("");
		this.setDona("");
		this.setMell("");
		this.setTrns("");
		this.setTserv("");
		this.setFesb("");
		this.setFtrt("");
		this.setFserv("");
		this.setFobs("");
		this.setApodo("");
		this.setIdAnimal("");
		this.setTieneHijos(false);
		this.setTieneEventos(false);
		this.setTieneEvt(false);
		this.setTieneCalif(false);
		this.setIdFoto(null);
		this.setComentario("");
		this.setInter(false);
		this.setIdPropInt("");
		this.setNombrePropInt("");
		this.setDesde("");
		this.setHasta("");
		this.setRpti("");
		this.setCategoria("");
		this.setTaraG("");
		
	}
	
	
	public Boolean getPropCriSelect() {
		return propCriSelect;
	}

	public void setPropCriSelect(Boolean propCriSelect) {
		this.propCriSelect = propCriSelect;
	}

	public Boolean getPropSelect() {
		return propSelect;
	}

	public void setPropSelect(Boolean propSelect) {
		this.propSelect = propSelect;
	}

	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public FormFile getFoto() {
		return foto;
	}
	public void setFoto(FormFile foto) {
		this.foto = foto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreEstab() {
		return nombreEstab;
	}
	public void setNombreEstab(String nombreEstab) {
		this.nombreEstab = nombreEstab;
	}
	public String getNombreEstabCri() {
		return nombreEstabCri;
	}
	public void setNombreEstabCri(String nombreEstabCri) {
		this.nombreEstabCri = nombreEstabCri;
	}
	public String getNombreProp() {
		return nombreProp;
	}
	public void setNombreProp(String nombreProp) {
		this.nombreProp = nombreProp;
	}
	public String getNumRegId() {
		return numRegId;
	}
	public void setNumRegId(String numRegId) {
		this.numRegId = numRegId;
	}
	public String getNumRegOri() {
		return numRegOri;
	}
	public void setNumRegOri(String numRegOri) {
		this.numRegOri = numRegOri;
	}
	
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getIdMadre() {
		return idMadre;
	}

	public void setIdMadre(String idMadre) {
		this.idMadre = idMadre;
	}

	public String getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
	}

	public String getMadre() {
		return madre;
	}

	public void setMadre(String madre) {
		this.madre = madre;
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public String getTipoRegId() {
		return tipoRegId;
	}

	public void setTipoRegId(String tipoRegId) {
		this.tipoRegId = tipoRegId;
	}

	public String getTipoRegOri() {
		return tipoRegOri;
	}

	public void setTipoRegOri(String tipoRegOri) {
		this.tipoRegOri = tipoRegOri;
	}

	public String getNombreMadre() {
		return nombreMadre;
	}

	public void setNombreMadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}

	public String getNombrePadre() {
		return nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	public String getRpMadre() {
		return rpMadre;
	}

	public void setRpMadre(String rpMadre) {
		this.rpMadre = rpMadre;
	}

	public String getRpPadre() {
		return rpPadre;
	}

	public void setRpPadre(String rpPadre) {
		this.rpPadre = rpPadre;
	}

	public String getNombrePM() {
		return nombrePM;
	}

	public void setNombrePM(String nombrePM) {
		this.nombrePM = nombrePM;
	}

	public String getIdPropCri() {
		return idPropCri;
	}

	public void setIdPropCri(String idPropCri) {
		this.idPropCri = idPropCri;
	}

	public String getNombrePropCri() {
		return nombrePropCri;
	}

	public void setNombrePropCri(String nombrePropCri) {
		this.nombrePropCri = nombrePropCri;
	}

	

	

	public String getCoBaj() {
		return coBaj;
	}

	public void setCoBaj(String coBaj) {
		this.coBaj = coBaj;
	}

	public String getCoBajOri() {
		return coBajOri;
	}

	public void setCoBajOri(String coBajOri) {
		this.coBajOri = coBajOri;
	}

	public Boolean getEsId() {
		return esId;
	}

	public void setEsId(Boolean esId) {
		this.esId = esId;
	}

	public Boolean getEsOri() {
		return esOri;
	}

	public void setEsOri(Boolean esOri) {
		this.esOri = esOri;
	}

	public String getFeBaj() {
		return feBaj;
	}

	public void setFeBaj(String feBaj) {
		this.feBaj = feBaj;
	}

	public String getFeBajId() {
		return feBajId;
	}

	public void setFeBajId(String feBajId) {
		this.feBajId = feBajId;
	}

	public String getFeBajOri() {
		return feBajOri;
	}

	public void setFeBajOri(String feBajOri) {
		this.feBajOri = feBajOri;
	}

	public boolean isEsId() {
		return esId;
	}

	public void setEsId(boolean esId) {
		this.esId = esId;
	}

	public boolean isEsOri() {
		return esOri;
	}

	public void setEsOri(boolean esOri) {
		this.esOri = esOri;
	}

	public String getCoBajId() {
		return coBajId;
	}

	public void setCoBajId(String coBajId) {
		this.coBajId = coBajId;
	}

	public String getAnls() {
		return anls;
	}

	public void setAnls(String anls) {
		this.anls = anls;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}

	public String getDona() {
		return dona;
	}

	public void setDona(String dona) {
		this.dona = dona;
	}

	public String getFesb() {
		return fesb;
	}

	public void setFesb(String fesb) {
		this.fesb = fesb;
	}

	public String getFserv() {
		return fserv;
	}

	public void setFserv(String fserv) {
		this.fserv = fserv;
	}

	public String getFtrt() {
		return ftrt;
	}

	public void setFtrt(String ftrt) {
		this.ftrt = ftrt;
	}

	public String getMell() {
		return mell;
	}

	public void setMell(String mell) {
		this.mell = mell;
	}

	public String getTran() {
		return tran;
	}

	public void setTran(String tran) {
		this.tran = tran;
	}

	public String getTrns() {
		return trns;
	}

	public void setTrns(String trns) {
		this.trns = trns;
	}

	public String getTserv() {
		return tserv;
	}

	public void setTserv(String tserv) {
		this.tserv = tserv;
	}

	public String getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(String idAnimal) {
		this.idAnimal = idAnimal;
	}

	public Boolean getTieneCalif() {
		return tieneCalif;
	}

	public void setTieneCalif(Boolean tieneCalif) {
		this.tieneCalif = tieneCalif;
	}

	public Boolean getTieneEventos() {
		return tieneEventos;
	}

	public void setTieneEventos(Boolean tieneEventos) {
		this.tieneEventos = tieneEventos;
	}

	public Boolean getTieneEvt() {
		return tieneEvt;
	}

	public void setTieneEvt(Boolean tieneEvt) {
		this.tieneEvt = tieneEvt;
	}

	public Boolean getTieneHijos() {
		return tieneHijos;
	}

	public void setTieneHijos(Boolean tieneHijos) {
		this.tieneHijos = tieneHijos;
	}

	public Set getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set comentarios) {
		this.comentarios = comentarios;
	}

	public String getFobs() {
		return fobs;
	}

	public void setFobs(String fobs) {
		this.fobs = fobs;
	}

	public String getIdPropInt() {
		return idPropInt;
	}

	public void setIdPropInt(String idPropInt) {
		this.idPropInt = idPropInt;
	}

	public String getNombrePropInt() {
		return nombrePropInt;
	}

	public void setNombrePropInt(String nombrePropInt) {
		this.nombrePropInt = nombrePropInt;
	}

	public String getRpti() {
		return rpti;
	}

	public void setRpti(String rpti) {
		this.rpti = rpti;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTaraG() {
		return taraG;
	}

	public void setTaraG(String taraG) {
		this.taraG = taraG;
	}
}
