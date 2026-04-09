/**
 * 
 */
package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import ar.org.sicel.util.DateUtils;

/**
 * @author jdivars
 *
 */
public class LogContacto {
	
	public static String ACTIVAR = "Activacion";
	public static String DESACTIVAR = "Desactivacion";
	public static String MODIFICAR_METODO_CONTROL = "Modificacion en el Metodo de Control";
	

	private Long id;
	private Usuario usuario;
	//private boolean activo;
	private String accion; // activacion, desactivacion, cambio de metodo de Control
	private Date fecha;
	private Contacto contacto;
	private CentroDeComputo centro;
	private MetodoControl metodoControl; // para el caso de que se un log de establecimiento
	private MetodoControl metodoControlNuevo; // para el caso de que se un log de establecimiento
	private Eclo eclo;
	

	public MetodoControl getMetodoControlNuevo() {
		return metodoControlNuevo;
	}
	public void setMetodoControlNuevo(MetodoControl metodoControlNuevo) {
		this.metodoControlNuevo = metodoControlNuevo;
	}
	public Contacto getContacto() {
		return contacto;
	}
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public MetodoControl getMetodoControl() {
		return metodoControl;
	}
	public void setMetodoControl(MetodoControl metodoControl) {
		this.metodoControl = metodoControl;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public CentroDeComputo getCentro() {
		return centro;
	}
	public void setCentro(CentroDeComputo centro) {
		this.centro = centro;
	}
	
	    @Override
	    public boolean equals(Object obj) {
	    	if(!(obj instanceof LogContacto))
	    		return false;
	    	LogContacto r = (LogContacto) obj;
	    	/*if(this == r)
	    		return true;*/
	    	if(r.getAccion().equals(this.accion))
	    		if(DateUtils.mismoDia(r.getFecha(),this.fecha))
	    			if(r.getContacto() instanceof Establecimiento){
	    				if((r.getMetodoControl()!=null && this.getMetodoControl()!=null && r.getMetodoControl().equals(this.getMetodoControl())||(r.getMetodoControl()==null && this.getMetodoControl()==null))
	    					&& ((r.getMetodoControlNuevo()!=null && this.getMetodoControlNuevo()!=null 
	    						&& r.getMetodoControlNuevo().equals(this.getMetodoControlNuevo()))||(r.getMetodoControlNuevo()==null && this.getMetodoControlNuevo()==null))
	    						&& ((r.getEclo() == null && this.getEclo() == null) ||
	    						   (r.getEclo() != null && this.getEclo() != null && r.getEclo().getId().equals(this.getEclo().getId()))))
	    					return true;
	    				
	    				/*if(r.getMetodoControlNuevo().equals(this.metodoControlNuevo))
	    					return true;*/
	    			}
	    			else
	    				return true;
	    	return false;
	    }
	    /**
	     * Retorna el ultimo logContacto en estado Desactivacion del contacto c
	     * lo primero que se hace es ordenar la bitacora por fecha y luego se comienza
	     * a recorrer de el log mas nuevo hacia atras hasta encontrar uno con accion Desactivacion
	     * @param c
	     * @return
	     */
	    public static LogContacto getUltimoLogDeBaja(Contacto c){
	    	List r =new ArrayList(c.getBitacora());
	    	if(!r.isEmpty()){
				Collections.sort(r, new LogPorFecha());   
				 int desde = r.size();
				ListIterator lt = r.listIterator(desde);
				while(lt.hasPrevious()){
					LogContacto logBaja = (LogContacto)lt.previous();
					if(logBaja.getAccion().trim().equals(LogContacto.DESACTIVAR)){
						return logBaja;
						
					}
					
				}
			}
	    	return null;
	    	
	    }
	    /**
	     * Retorna el ultimo logContacto en estado Desactivacion o Activacion del contacto c
	     * lo primero que se hace es ordenar la bitacora por fecha y luego se comienza
	     * a recorrer de el log mas nuevo hacia atras hasta encontrar uno con accion Desactivacion o Activacion
	     * @param c
	     * @return
	     */
	    public static LogContacto getUltimoLogDeEstado(Contacto c){
	    	List r =new ArrayList(c.getBitacora());
	    	if(!r.isEmpty()){
				Collections.sort(r, new LogPorFecha());   
				 int desde = r.size();
				ListIterator lt = r.listIterator(desde);
				while(lt.hasPrevious()){
					LogContacto logEstado = (LogContacto)lt.previous();
					if(!logEstado.getAccion().trim().equals(LogContacto.MODIFICAR_METODO_CONTROL)){
						return logEstado;
						
					}
					
				}
			}
	    	return null;
	    	
	    }
	    /**
	     * Retorna el ultimo logContacto en estado Desactivacion o Activacion del contacto c
	     * a la fecha de parametro lo primero que se hace es ordenar la bitacora por fecha y luego se comienza
	     * a recorrer de el log mas nuevo hacia atras hasta encontrar uno con accion Desactivacion o Activacion
	     * que sea menor que la del parametro
	     * @param c
	     * @return
	     */
	    public static LogContacto getUltimoLogAccionFecha(Contacto c,Date fecha){
	    	List r =new ArrayList(c.getBitacora());
	    	if(!r.isEmpty()){
				Collections.sort(r, new LogPorFecha());   
				 int desde = r.size();
				ListIterator lt = r.listIterator(desde);
				while(lt.hasPrevious()){
					LogContacto logEstado = (LogContacto)lt.previous();
					if((!logEstado.getAccion().trim().equals(LogContacto.MODIFICAR_METODO_CONTROL))&&(logEstado.getFecha().before(fecha) || new Date(logEstado.getFecha().getTime()).equals(fecha))){
						return logEstado;
						
					}
					
				}
			}
	    	return null;
	    	
	    }
	    /**
	     * Retorna el ultimo logContacto del contacto c, es posible que el ultimo log no sea de activacion/desactivacion
	     * a la fecha de parametro lo primero que se hace es ordenar la bitacora por fecha y luego se comienza
	     * a recorrer de el log mas nuevo hacia atras hasta encontrar uno con accion Desactivacion o Activacion
	     * que sea menor que la del parametro
	     * @param c
	     * @return
	     */
	    public static LogContacto getUltimoLogFecha(Contacto c,Date fecha){
	    	List r =new ArrayList(c.getBitacora());
	    	if(!r.isEmpty()){
				Collections.sort(r, new LogPorFecha());   
				 int desde = r.size();
				ListIterator lt = r.listIterator(desde);
				while(lt.hasPrevious()){
					LogContacto logEstado = (LogContacto)lt.previous();
					if(logEstado.getFecha().before(fecha)||(DateUtils.mismoDia(logEstado.getFecha(),fecha))){
						return logEstado;
						
					}
					
				}
			}
	    	return null;
	    	
	    }
		public Eclo getEclo() {
			return eclo;
		}
		public void setEclo(Eclo eclo) {
			this.eclo = eclo;
		}
	    
	   
	
}