/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.PropietarioExpd;
import ar.org.sicel.util.DateUtils;


/**
 * @author Ramiro Trachsel
 *
 */
/**
*
* struts.form
*      name="propietarioForm"
*
*/
public class PropietarioForm extends ActionForm  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7636677520161413214L;
	/*
	 * Para formulario de busquedas
	 */
	
	private String ciudad; 

	private String socRural=""; // para busqueda
	private String idProp="";
	private String nombre=""; // para busqueda
	private String socio="";
	private String har="";
	private String s1Eclo="";
	private String s1Prop="";
	protected Boolean esPersonaFisica = new Boolean(false);
	protected Boolean activo = new Boolean(false);
	
	private String prefijo = "";
	
	private PropietarioExpd propietarioSRA;	
	private String nroExpd = "";
	private String estab = "";
	private String raza = "";
	private String actionBack = ""; 
	private String idEstablecimiento = "";
	private String nombreEstancia = "";
	private Set expds = new HashSet();
	private Set ubicacions = new HashSet();
	
	private String mail;
	private String cuit;
	private String cuig = "";
	private String renspa;
	private String fechaLog;
	private String delSistema="";
	
	
	
	public String getFechaLog() {
		return fechaLog;
	}

	public void setFechaLog(String fechaLog) {
		this.fechaLog = fechaLog;
	}

	public String getCuig() {
		return cuig;
	}

	public void setCuig(String cuig) {
		this.cuig = cuig;
	}
	
	public String getRenspa() {
		return renspa;
	}

	public void setRenspa(String renspa) {
		this.renspa = renspa;
	}
	
	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getS1Eclo() {
		return s1Eclo;
	}

	public void setS1Eclo(String eclo) {
		s1Eclo = eclo;
	}

	public String getS1Prop() {
		return s1Prop;
	}

	public void setS1Prop(String prop) {
		s1Prop = prop;
	}

	public void reset(ActionMapping actionmapping, HttpServletRequest httpservletrequest) {
		
//		super.reset(actionmapping, httpservletrequest);
//		this.setIdProp("");
//		this.setNombre("");
	}

	public void reset(){
		this.setCiudad("");
		this.setSocRural("");
		this.setIdProp("");
		this.setNombre("");
		this.setSocio("");
		this.setHar("");
		this.setS1Eclo("");
		this.setS1Prop("");
		this.setEsPersonaFisica(false);
		this.setActivo(false);
		this.setExpds(new HashSet());
		this.setIdEstablecimiento("");
		this.setNombreEstancia("");
		this.setPrefijo("");
		this.setMail("");
		this.setCuig("");
		this.setCuit("");
		this.setRenspa("");
		this.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
		this.setRaza("");
		this.setDelSistema("no");
	}
	
	public String getHar() {
		return har;
	}

	public void setHar(String har) {
		this.har = har;
	}

	public String getSocio() {
		return socio;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdProp() {
		return idProp;
	}

	public void setIdProp(String idProp) {
		this.idProp = idProp;
	}

	public String getSocRural() {
		return socRural;
	}

	public void setSocRural(String socRural) {
		this.socRural = socRural;
	}

	
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	protected int getAlturaMax() {	
		return 0;
	}
	
	
	protected int getAnchoMax() {	
		return 0;
	}

	public Boolean getEsPersonaFisica() {
		return esPersonaFisica;
	}

	public void setEsPersonaFisica(Boolean esPersonaFisica) {
		this.esPersonaFisica = esPersonaFisica;
	}

	public PropietarioExpd getPropietarioSRA() {
		return propietarioSRA;
	}

	public void setPropietarioSRA(PropietarioExpd propietarioSRA) {
		this.propietarioSRA = propietarioSRA;
	}

	public String getEstab() {
		return estab;
	}

	public void setEstab(String estab) {
		this.estab = estab;
	}

	public String getNroExpd() {
		return nroExpd;
	}

	public void setNroExpd(String nroExpd) {
		this.nroExpd = nroExpd;
	}

	public String getActionBack() {
		return actionBack;
	}

	public void setActionBack(String actionBack) {
		this.actionBack = actionBack;
	}


	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(String idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public String getNombreEstancia() {
		return nombreEstancia;
	}

	public void setNombreEstancia(String nombreEstancia) {
		this.nombreEstancia = nombreEstancia;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Set getExpds() {
		return expds;
	}

	public void setExpds(Set expds) {
		this.expds = expds;
	}

	public Set getUbicacions() {
		return ubicacions;
	}

	public void setUbicacions(Set ubicacions) {
		this.ubicacions = ubicacions;
	}

	public String getDelSistema() {
		return delSistema;
	}

	public void setDelSistema(String delSistema) {
		this.delSistema = delSistema;
	}
}
