/**
 * 
 */
package ar.org.sicel.web.altaEdicion.forms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.util.DateUtils;

/**
 * @author Ramiro Trachsel
 *
 */
/**
*
* struts.form
*      name="establecimientoForm"
*
*/
public class EstablecimientoForm  extends ActionForm {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 862136956384589322L;
	//private Long propietarioId = new Long(0);
	//private Long ecloId = new Long(0);
	
	/*
	 * Para formulario de busquedas
	 */
	private String cuig;
	private String idEst;
	private String nombre;
	private String s1Propietario="";
	private String s1Eclo="";
	private String s1Tambo="";
	//private Boolean nombreContactoEcloActivo = new Boolean(false);	
	//private Boolean nombreContactoPropietarioActivo = new Boolean(false);
	//private Boolean ciudadActivo = new Boolean(false);
	//private Boolean s1Activo = new Boolean(false);
	//private String ciudad; 
	private String nombreContactoPropietario;
	private String idPropietario;
	private String nombreContactoEclo; 
	private String idEclo;
	private String estancia; 
	private String idEstancia;
	private Boolean activo = new Boolean(true);
	private Boolean activoPropietario = new Boolean(true);
	private Boolean activoEclo = new Boolean(true);
	private Boolean activoEstancia = new Boolean(true);
	private String rolAdmin;
	private String rolProv;
	private String rolProp;
	private String rol;
	private String metodoControl;
	private Long idCentro;
	private String cuit;	
	private String renspa;
	private String fechaLog;
	
	

	public String getFechaLog() {
		return fechaLog;
	}

	public void setFechaLog(String fechaLog) {
		this.fechaLog = fechaLog;
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

	
	public Boolean getActivoEclo() {
		return activoEclo;
	}
	public void setActivoEclo(Boolean activoEclo) {
		this.activoEclo = activoEclo;
	}
	public Boolean getActivoPropietario() {
		return activoPropietario;
	}
	public void setActivoPropietario(Boolean activoPropietario) {
		this.activoPropietario = activoPropietario;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public void reset(ActionMapping actionmapping, HttpServletRequest httpservletrequest) {
		//super.reset(actionmapping, httpservletrequest);
	//	this.setIdEst("");
//		this.setNombre("");
	}
	
	public void reset(){
		this.setIdEst("");
		this.setNombre("");
		this.setS1Eclo("");
		this.setS1Propietario("");
		this.setS1Tambo("");
		this.setEstancia("");
		this.setIdEstancia("");
		this.setNombreContactoEclo("");
		this.setNombreContactoPropietario("");
		this.setActivo(true);
		this.setMetodoControl("");
		this.setCuig("");
		this.setIdEclo("");
		this.setIdPropietario("");
		this.setIdCentro(null);
		this.setCuit("");
		this.setRenspa("");
		this.setFechaLog(DateUtils.format(new Date(),"dd/MM/yyyy"));
		
	}
	public String getIdEstancia() {
		return idEstancia;
	}


	public void setIdEstancia(String idEstancia) {
		this.idEstancia = idEstancia;
	}
	
	/*
	public Long getEcloId() {
		return ecloId;
	}


	public void setEcloId(Long ecloId) {
		this.ecloId = ecloId;
	}


	public Long getPropietarioId() {
		return propietarioId;
	}


	public void setPropietarioId(Long propietarioId) {
		this.propietarioId = propietarioId;
	}

	public Boolean getCiudadActivo() {
		return ciudadActivo;
	}

	public void setCiudadActivo(Boolean ciudadActivo) {
		this.ciudadActivo = ciudadActivo;
	}

	public Boolean getNombreContactoEcloActivo() {
		return nombreContactoEcloActivo;
	}

	public void setNombreContactoEcloActivo(Boolean nombreContactoEcloActivo) {
		this.nombreContactoEcloActivo = nombreContactoEcloActivo;
	}

	public Boolean getNombreContactoPropietarioActivo() {
		return nombreContactoPropietarioActivo;
	}

	public void setNombreContactoPropietarioActivo(
			Boolean nombreContactoPropietarioActivo) {
		this.nombreContactoPropietarioActivo = nombreContactoPropietarioActivo;
	}

	public Boolean getS1Activo() {
		return s1Activo;
	}

	public void setS1Activo(Boolean activo) {
		s1Activo = activo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
*/
	public String getNombreContactoEclo() {
		return nombreContactoEclo;
	}

	public void setNombreContactoEclo(String nombreContactoEclo) {
		this.nombreContactoEclo = nombreContactoEclo;
	}

	public String getNombreContactoPropietario() {
		return nombreContactoPropietario;
	}

	public void setNombreContactoPropietario(String nombreContactoPropietario) {
		this.nombreContactoPropietario = nombreContactoPropietario;
	}


	
	protected int getAlturaMax() {		
		return 0;
	}
	
	protected int getAnchoMax() {
		return 0;
	}


	public String getIdEst() {
		return idEst;
	}


	public void setIdEst(String idEst) {
		this.idEst = idEst;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getS1Eclo() {
		return s1Eclo;
	}


	public void setS1Eclo(String eclo) {
		s1Eclo = eclo;
	}


	public String getS1Propietario() {
		return s1Propietario;
	}


	public void setS1Propietario(String propietario) {
		s1Propietario = propietario;
	}


	public String getS1Tambo() {
		return s1Tambo;
	}


	public void setS1Tambo(String tambo) {
		s1Tambo = tambo;
	}
	public Boolean getActivoEstancia() {
		return activoEstancia;
	}
	public void setActivoEstancia(Boolean activoEstancia) {
		this.activoEstancia = activoEstancia;
	}
	public String getMetodoControl() {
		return metodoControl;
	}
	public void setMetodoControl(String metodoControl) {
		this.metodoControl = metodoControl;
	}
	public String getCuig() {
		return cuig;
	}
	public void setCuig(String cuig) {
		this.cuig = cuig;
	}
	public String getIdEclo() {
		return idEclo;
	}
	public void setIdEclo(String idEclo) {
		this.idEclo = idEclo;
	}
	public String getEstancia() {
		return estancia;
	}
	public void setEstancia(String estancia) {
		this.estancia = estancia;
	}
	public String getRolAdmin() {
		return rolAdmin;
	}
	public void setRolAdmin(String rolAdmin) {
		this.rolAdmin = rolAdmin;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getRolProp() {
		return rolProp;
	}
	public void setRolProp(String rolProp) {
		this.rolProp = rolProp;
	}
	public String getRolProv() {
		return rolProv;
	}
	public void setRolProv(String rolProv) {
		this.rolProv = rolProv;
	}
	public Long getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}
	public String getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(String idPropietario) {
		this.idPropietario = idPropietario;
	}
	
	
		
}
