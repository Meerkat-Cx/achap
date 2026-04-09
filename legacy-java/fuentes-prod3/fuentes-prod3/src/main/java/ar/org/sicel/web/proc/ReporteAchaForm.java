/**
 * 
 */
package ar.org.sicel.web.proc;

import org.apache.struts.action.ActionForm;

/**
 * @author jdivars
 *
 */
public class ReporteAchaForm extends ActionForm {
 
private static final long serialVersionUID = 1L;
private String idRegional;
 private String idEclo;
 private String idProp;
 private String nombreProp;
 private String nombreRegional;
 private String nombreEclo;
 private String mes;
 private String anio;
 private String anioMax;

public String getAnioMax() {
	return anioMax;
}

public void setAnioMax(String anioMax) {
	this.anioMax = anioMax;
}

public void reset(){
	this.setIdEclo("");
	this.setIdProp("");
	this.setIdRegional("");
	this.setNombreEclo("");
	this.setNombreProp("");
	this.setNombreRegional("");
	this.setAnio("");
	this.setMes("");
	
}

public String getMes() {
	return mes;
}

public void setMes(String mes) {
	this.mes = mes;
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
public String getIdRegional() {
	return idRegional;
}
public void setIdRegional(String idRegional) {
	this.idRegional = idRegional;
}
public String getNombreRegional() {
	return nombreRegional;
}
public void setNombreRegional(String nombreRegional) {
	this.nombreRegional = nombreRegional;
}
public String getNombreEclo() {
	return nombreEclo;
}
public void setNombreEclo(String nombreEclo) {
	this.nombreEclo = nombreEclo;
}
public String getAnio() {
	return anio;
}
public void setAnio(String anio) {
	this.anio = anio;
}
}
