package ar.org.sicel.persistence;


//import ar.org.sicel.persistence.util.HibernateUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.proc.v1.lote.Alta;
import ar.org.sicel.proc.v1.lote.Control;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.util.DateUtils;



/**
 *
 * @hibernate.joined-subclass
 *    table="En_Establecimiento"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_Esta_Cont"
 *
 */
public class Establecimiento extends Contacto {
    private static ar.org.sicel.persistence.ConjuntoAtributos conjuntoAtributosEstab;
   // private String cuig;
    private Map map_parametros; 
    public static final String TIEMPO_POST_BAJA_TAMBO_PARA_INFORMAR_EVENTOS_ANTERIOR_A_BAJA = "Tiempo post desactivacion tambo para informar eventos anteriores a la desactivación del tambo";
    public static final String DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_OTRO_TAMBO = "Dias maximos de retroactividad que un evento se informa en otro tambo";
    public static final String DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_SERVICIO = "Dias maximos que un evento servicio se puede informar retroactivamente a la creacion de un tambo";
    public static final String DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_PARTO = "Dias maximos que un evento parto se puede informar retroactivamente a la creacion de un tambo";
    public static final String DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_CONTROL = "Dias maximos que un evento control se puede informar retroactivamente a la creacion de un tambo";
    
   /* public String getCuig() {
		return cuig;
	}

	public void setCuig(String cuig) {
		this.cuig = cuig;
	}*/

	private static ConjuntoAtributos getConjuntoAtributosEstab() {
    	if (conjuntoAtributosEstab == null) {
    		try {
    			conjuntoAtributosEstab = ConjuntoAtributosDAO.findByName(Establecimiento.class.getSimpleName());
    		} catch (HibernateException e) {
    			e.printStackTrace();
    		}
    	}
    	return conjuntoAtributosEstab;
    }


    // --------------- attributes ---------------------
    private java.lang.Integer s1Tbo;
    private java.lang.Integer s1Eclo;
    private java.lang.Integer s1Prop;
    private ar.org.sicel.persistence.Propietario propietario;
    private ar.org.sicel.persistence.Eclo eclo;
    private ar.org.sicel.persistence.AtrVariables atrVariablesEstab;
    private Estancia estancia;
    private Boolean activoPropietario = new Boolean(true);
    private Boolean activoEclo = new Boolean(true);
    private Boolean activoEstablecimiento = new Boolean(true);
    private Boolean activoEstancia = new Boolean(true);
    private CentroDeComputo centroComputo;
    
    private MetodoControl metodoControl;
    /**
     * metodo que se encarga de de determinar si el tambo se encuentra activo para la fecha
     * indicada como parametro.
     * primero se ordena la bitacora por fecha, luego se recorre la coleccion hasta determinar 
     * el log de estado (activo/inactivo) del tambo para la fecha indicada
     * En caso de que se recupere el log se consulta la accion y se retorna el estado.
     * en caso de que no se recupere el log, se consulta sobre el primer log de estado del tambo
     * en base a ese log se determina si puede ser aceptado el evento segun las reglas de negocio.
     * En caso de que el tambo no tenga log se retornara  el valor de estado del tambo actualmente
     * @param evento
     * @param fecha
     * @return
     */
    
    public boolean estaActivoEnFecha(Object evento,Date fecha){
    	List r =new ArrayList(this.getBitacora());
    	LogContacto log = null;
    	LogContacto primerlog = null;
    	LogContacto segundolog = null;
    	if(!r.isEmpty()){
			Collections.sort(r, new LogPorFecha());   
			 int desde = r.size();
			ListIterator lt = r.listIterator(desde);
			while(lt.hasPrevious()){
				LogContacto logEstado = (LogContacto)lt.previous();
				primerlog= logEstado;
				if((!logEstado.getAccion().trim().equals(LogContacto.MODIFICAR_METODO_CONTROL))&&(logEstado.getFecha().before(fecha) || new Date(logEstado.getFecha().getTime()).equals(fecha))){
					log= logEstado;
					segundolog = primerlog;
					break;
				}
				segundolog = primerlog;
			}
		}
    	if(log!=null){
    		if(log.getAccion().trim().equals(LogContacto.DESACTIVAR)){//acaaaaaaaaa
    			Date fecha2 = segundolog.getFecha();
    			if((evento instanceof Alta)){
    				Date feh = DateUtils.menos(fecha2,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_PARTO)));
    				if(fecha.before(feh))
    					return false;
    				return true;
    			}
    		  	if((evento instanceof Evt)){
					Evt evento2 = (Evt)evento;
	    			if(evento2.getReprod()!=null){
	    				Date feh = DateUtils.menos(fecha2,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_PARTO)));
	    				if(fecha.before(feh))
	    					return false;
	    				return true;
	    			}
	    			if(evento2.getServ()!=null){
	    				Date feh = DateUtils.menos(fecha2,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_SERVICIO)));
	    				if(fecha.before(feh))
	    					return false;
	    				return true;
	    			}
	    			if(evento2.getPrenez()!=null){
	    				Date feh = DateUtils.menos(fecha2,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_SERVICIO)));
	    				if(fecha.before(feh))
	    					return false;
	    				return true;
	    			}
				}
				else{
					if((evento instanceof Control)){
						Date feh = DateUtils.menos(fecha2,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_CONTROL)));
	    				if(fecha.before(feh))
	    					return false;
	    				return true;
					}
				}
	    		return false;
    		}
	    	 else
	    		return true;
    	}
    	else{
    		if(primerlog!=null){
    			Date fecha2 = primerlog.getFecha();
    			if((evento instanceof Alta)){
    				Date feh = DateUtils.mas(fecha,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_PARTO)));
    				if(feh.before(fecha2))
    					return false;
    				return true;
    			}
    		  	if((evento instanceof Evt)){
					Evt evento2 = (Evt)evento;
	    			if(evento2.getReprod()!=null){
	    				Date feh = DateUtils.mas(fecha,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_PARTO)));
	    				if(feh.before(fecha2))
	    					return false;
	    				return true;
	    			}
	    			if(evento2.getServ()!=null){
	    				Date feh = DateUtils.mas(fecha,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_SERVICIO)));
	    				if(feh.before(fecha2))
	    					return false;
	    				return true;
	    			}
	    			if(evento2.getPrenez()!=null){
	    				Date feh = DateUtils.menos(fecha2,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_SERVICIO)));
	    				if(fecha.before(feh))
	    					return false;
	    				return true;
	    			}
				}
				else{
					if((evento instanceof Control)){
						Date feh = DateUtils.mas(fecha,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_CONTROL)));
	    				if(feh.before(fecha2))
	    					return false;
	    				return true;
					}
				}
			
    		}
    		else{
    			return this.getActivoEstablecimiento();
    		}
    	}
    	return false;
    }
   /**
    * 
    * 
    * 
    * 
    * Retorna true en caso de que se encuentre activo para la fecha pasada como parametro
    */
    public boolean estaActivoEnFecha3(Object evento,Date fecha){//POR AHORA NO SE USA, SE ESTA USANDO EL DE ARRIBA!!!!
    	List r =new ArrayList(getBitacora());
    	Iterator it = r.iterator();
    	Date fecha1 = null;
    	Date fecha2 = null;
    	LogContacto log1 = null;
    	LogContacto log2 = null;
    	while(it.hasNext()){
    		LogContacto log = (LogContacto)it.next();
    		if(!log.getAccion().trim().equals(LogContacto.MODIFICAR_METODO_CONTROL)){
	    		if(fecha.after(log.getFecha())){
	    				fecha1 = log.getFecha();
	    				log1=log;
	    		}
	    		else{
	    				fecha2 = log.getFecha();
	    				log2=log;
	    				break;
	    		}
    	}
    	}
    	
    	if(fecha1!=null){
	    	if(log1.getAccion().trim().equals(LogContacto.DESACTIVAR))
	    		return false;
	    	 else
	    			return true;
	    	
    	}
    	if(fecha1==null)
    		//aca seria el caso de que el evento en una fecha q el tambo no existe(no se creo todavia pero hoy esta activo)
    		//entonces si fecha2 es activacion y el evento es un parto o un servicio se 
    		//debe chequear con las variables para ver si es aceptado igualmente
    		if(evento==null)
    			return false;
    		else{
    			if(fecha2!=null){
    			  	if((evento instanceof Evt)){
    					Evt evento2 = (Evt)evento;
		    			if(evento2.getReprod()!=null){
		    				Date feh = DateUtils.mas(fecha,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_PARTO)));
		    				if(feh.before(fecha2))
		    					return false;
		    				return true;
		    			}
		    			if(evento2.getServ()!=null){
		    				Date feh = DateUtils.mas(fecha,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_SERVICIO)));
		    				if(feh.before(fecha2))
		    					return false;
		    				return true;
		    			}
    				}
    				else{
    					if((evento instanceof Control)){
    						Date feh = DateUtils.mas(fecha,Integer.parseInt(this.getParametro(Establecimiento.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_CONTROL)));
		    				if(feh.before(fecha2))
		    					return false;
		    				return true;
    					}
    				}
    			}
    			else{//seria si no tiene logs fecha1 null y fecha2 null
    				return this.getActivoEstablecimiento();
    			}
    		}
    	
		return false;
   }
    /**
     * Este metodo se encarga de determinar el metodo de control que tiene asignado en el momento en que ocurre
     * el evento, primero se ordena la bitacora y se recorre en orden descendente hasta entontrar un log con 
     * fecha anterior o igual a la del evento, entonces ese metodo es el q tenia el tambo en el momento del
     * evento. En caso de que no se pueda determinar el metodo se retorna el metodo de control actual
     * @param fecha
     * @return
     */
    public MetodoControl getMetodoEnFecha(Date fecha){
    	List r =new ArrayList(this.getBitacora());
    	if(!r.isEmpty()){
			Collections.sort(r, new LogPorFecha());   
			 int desde = r.size();
			ListIterator lt = r.listIterator(desde);
			while(lt.hasPrevious()){
				LogContacto logEstado = (LogContacto)lt.previous();
				if((logEstado.getAccion().trim().equals(LogContacto.MODIFICAR_METODO_CONTROL))&&(logEstado.getFecha().before(fecha) || new Date(logEstado.getFecha().getTime()).equals(fecha))){
					return logEstado.getMetodoControlNuevo();
				}
				
			}
		}
    	return this.getMetodoControl();
    	
    	
    }
public Boolean getActivoEstancia() {
		return activoEstancia;
	}

	public void setActivoEstancia(Boolean activoEstancia) {
		this.activoEstancia = activoEstancia;
	}

public Boolean getActivoEclo() {
		return activoEclo;
	}

	public void setActivoEclo(Boolean activoEclo) {
		this.activoEclo = activoEclo;
	}

	public Boolean getActivoEstablecimiento() {
		return activoEstablecimiento;
	}

	public void setActivoEstablecimiento(Boolean activoEstablecimiento) {
		this.activoEstablecimiento = activoEstablecimiento;
	}

	public Boolean getActivoPropietario() {
		return activoPropietario;
	}

	public void setActivoPropietario(Boolean activoPropietario) {
		this.activoPropietario = activoPropietario;
	}

/**
    *
    * @hibernate.property
    *     column="S1_Tbo"
    *
    */
    public java.lang.Integer getS1Tbo() {
        return this.s1Tbo;
    }

    public void setS1Tbo(java.lang.Integer s1Tbo) {
        this.s1Tbo = s1Tbo;
    }

    /**
    *
    * @hibernate.property
    *     column="S1_ECLO"
    *
    */
    public java.lang.Integer getS1Eclo() {
        return this.s1Eclo;
    }

    public void setS1Eclo(java.lang.Integer s1Eclo) {
        this.s1Eclo = s1Eclo;
    }

    /**
    *
    * @hibernate.property
    *     column="S1_Prop"
    *
    */
    public java.lang.Integer getS1Prop() {
        return this.s1Prop;
    }

    public void setS1Prop(java.lang.Integer s1Prop) {
        this.s1Prop = s1Prop;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="propietario"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Esta_Prop"
     *
     */
    public ar.org.sicel.persistence.Propietario getPropietario() {
        return this.propietario;
    }

    public void setPropietario(
        ar.org.sicel.persistence.Propietario propietario) {
        this.propietario = propietario;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="ECLO"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Esta_Eclo"
     *
     */
    public ar.org.sicel.persistence.Eclo getEclo() {
        return this.eclo;
    }

    public void setEclo(ar.org.sicel.persistence.Eclo eclo) {
        this.eclo = eclo;
    }

    /*
    **
     *
     * hibernate.set
     *     lazy="true"
     *     inverse="true"
     * hibernate.collection-key
     *     column="estab"
     * hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Animal"
     *
     *
    public java.util.Set getAnimals() {
        return this.animals;
    }

    protected void setAnimals(java.util.Set animals) {
        this.animals = animals;
    }
    */

    /**
     *
     * @hibernate.many-to-one
     *     column="atrVariables"
     *     outer-join="auto"
     *     foreign-key="FK_Esta_atrVariables"
     *     not-null="false"
     *     unique="true"
     *     cascade = "all"
     *
     * @hibernate.column
     *     name="atrVariables"
     *     not-null="false"
     *     unique="true"
     *     unique-key="UN_En_Esta_atrVariables"
     *
     */
    public ar.org.sicel.persistence.AtrVariables getAtrVariablesEstab() {
    	if (atrVariablesEstab == null) //para que siempre retorne uno valido, si no esta presente lo crea (vacio)
    		this.setAtrVariablesEstab(AtrVariablesDAO.create()); 
    	return this.atrVariablesEstab;
    }

    public void setAtrVariablesEstab(
        ar.org.sicel.persistence.AtrVariables atrVariablesEstab) {
    	if (atrVariablesEstab == null) //para que siempre retorne uno valido, si no esta presente lo crea (vacio)
    		atrVariablesEstab =AtrVariablesDAO.create();
		this.atrVariablesEstab = atrVariablesEstab;
    	if(atrVariablesEstab != null)
    		this.atrVariablesEstab.setConjuntoAtributos(getConjuntoAtributosEstab());
    }
    
    public String getParametro(String atributo) {
    	if (map_parametros == null)
    		buildCacheParametros();
    	String resultado = (String)map_parametros.get(atributo);
    	return resultado;
    }
    private void buildCacheParametros() {
    	map_parametros = new HashMap();
    	Iterator posibles = this.getAtrVariablesEstab().getConjuntoAtributos().getAtributos().iterator();
    	while (posibles.hasNext()) { //todos los posibles atributos
    		Atributo at = (Atributo) posibles.next();
    		map_parametros.put(at.getNombre(),at.getValorPorDefecto().getValor());
    	}
    	
    	Iterator seteados = this.getAtrVariablesEstab().getValors().iterator();
    	while (seteados.hasNext()) { //sobreescribo los que esten seteados
    		Valor v = (Valor) seteados.next();
    		map_parametros.put(v.getValorAdmAtr().getAtributo().getNombre(),v.getValorAdmAtr().getValor());
    	}
    }
    /*
     *
     * hibernate.set
     *     lazy="true"
     *     inverse="true"
     * hibernate.collection-key
     *     column="establecimiento"
     * hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.ProcEstablecimiento"
     
    public java.util.Set getProcEstablecimientos() {
        return this.procEstablecimientos;
    }

    protected void setProcEstablecimientos(java.util.Set procEstablecimientos) {
        this.procEstablecimientos = procEstablecimientos;
    }
    */
    
    /*
     *
     * hibernate.set
     *     lazy="true"
     *     inverse="true"
     * hibernate.collection-key
     *     column="nuevoEsta"
     * hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.EvtTransferencia"
     *
     
    public java.util.Set getEvtTransferencias() {
        return this.evtTransferencias;
    }

    protected void setEvtTransferencias(java.util.Set evtTransferencias) {
        this.evtTransferencias = evtTransferencias;
    }
    */
    
    /*
     *
     * hibernate.set
     *     lazy="true"
     *     inverse="true"
     * hibernate.collection-key
     *     column="establecimiento"
     * hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Evento"
     *
     
    public java.util.Set getEventos() {
        return this.eventos;
    }

    protected void setEventos(java.util.Set eventos) {
        this.eventos = eventos;
    }
    
    
    protected void addEventoEstablecimiento(EvtEstablecimiento evt) {
    	this.eventos.add(evt);
    }
    
    public void addProcEstablecimiento(ProcEstablecimiento proc) {
    	this.procEstablecimientos.add(proc);
    }
    protected void removeProcEstablecimiento(ProcEstablecimiento proc) {
    	this.procEstablecimientos.remove(proc);
    }
    */
    
    // ---------------- business methods  ----------------------
    
    public String toString() {
    	StringBuffer b = new StringBuffer();
    	b.append(this.getId());
    	b.append("( " + this.getNombreContacto() + ") {");
//    	Iterator itValores = getAtrVariablesEstab().getValors().iterator();
//    	while (itValores.hasNext()) {
//    		Valor v = (Valor)itValores.next();
//    		b.append(v);
//    		b.append(", ");
//    	}
    	b.append("}");
    	return b.toString();
    }
    
    
    @SuppressWarnings("unchecked")
	public List getAnimals() {
    	try {
    		return AnimalDAO.findAnimalesWithRCByEstablecimiento(this);
    	} catch (HibernateException he) {
    		throw new ErrorFatal("No se pudo buscar los animales de un establecimiento - Establecimiento", he);
    	}
    }
    
    
    public EvtControlEstablecimiento getOrdenieEnFecha(Date fecha) {
    	return EvtControlEstablecimientoDAO.findByFecha(this,fecha);
    }

	public Estancia getEstancia() {
		return estancia;
	}

	public void setEstancia(Estancia estancia) {
		this.estancia = estancia;
	}

	public MetodoControl getMetodoControl() {
		return metodoControl;
	}

	public void setMetodoControl(MetodoControl metodoControl) {
		this.metodoControl = metodoControl;
	}

	public CentroDeComputo getCentroComputo() {
		return centroComputo;
	}

	public void setCentroComputo(CentroDeComputo centroComputo) {
		this.centroComputo = centroComputo;
	}
	
    
}
