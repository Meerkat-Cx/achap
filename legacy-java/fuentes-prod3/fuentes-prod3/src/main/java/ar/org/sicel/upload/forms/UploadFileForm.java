/**
 * 
 */
package ar.org.sicel.upload.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * @author jdivars
 *
 */
public class UploadFileForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private FormFile fileUpload;
	private String comments="";
	private Long idP;
	private Long indiceCombo = null;
	private Long loteNum = null;
    private Long ecloId = null;
    private Long ecloIdF = null;
    private Long sistema = null;
    private Long centroId = null;
    private String nombre = "";
    private String username = "";
   private String rolUser = "";
    private Long idDeletedProcess = null;
    
	
	/*Agregado para el uso en la descargada de archivos entre fechas, en la parte de procesamiento ya sea por parte
	 * del administrador o de la eclo.
	 */
	private String fechaInicio;
	private String fechaFin;
	

	public FormFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FormFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getIdP() {
		return idP;
	}

	public void setIdP(Long idP) {
		this.idP = idP;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public void reset(){
		this.setFechaFin(null);
		this.setFechaInicio(null);
		this.setCentroId(null);
		//this.setEcloId(null);
		this.setLoteNum(null);
		this.setEcloIdF(null);
		this.setIdDeletedProcess(null);
		this.setIndiceCombo(new Long(0));
	}

	public Long getLoteNum() {
		return loteNum;
	}

	public void setLoteNum(Long loteNum) {
		this.loteNum = loteNum;
	}

	public Long getEcloId() {
		return ecloId;
	}

	public void setEcloId(Long ecloId) {
		this.ecloId = ecloId;
	}

	public Long getSistema() {
		return sistema;
	}

	public void setSistema(Long sistema) {
		this.sistema = sistema;
	}

	public Long getCentroId() {
		return centroId;
	}

	public void setCentroId(Long centroId) {
		this.centroId = centroId;
	}

	public Long getEcloIdF() {
		return ecloIdF;
	}

	public void setEcloIdF(Long ecloIdF) {
		this.ecloIdF = ecloIdF;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getIndiceCombo() {
		return indiceCombo;
	}

	public void setIndiceCombo(Long indiceCombo) {
		this.indiceCombo = indiceCombo;
	}

	public String getRolUser() {
		return rolUser;
	}

	public void setRolUser(String rolUser) {
		this.rolUser = rolUser;
	}

	public Long getIdDeletedProcess() {
		return idDeletedProcess;
	}

	public void setIdDeletedProcess(Long idDeletedProcess) {
		this.idDeletedProcess = idDeletedProcess;
	}

	

}

