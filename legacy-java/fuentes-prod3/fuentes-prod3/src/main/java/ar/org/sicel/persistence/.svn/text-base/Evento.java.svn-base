package ar.org.sicel.persistence;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;


/**
 * 
 * @hibernate.class table="Ev_Evento" lazy="true"
 * 
 */
@SuppressWarnings("unchecked")
public abstract class Evento {
    
    /*
     * un ev dep es aquel q necesita ser borrado antes de borrar el ev que lo ien ecomo dep
     * poner una sorted set en ev con los eventosDependientes
     * en la bd un atabla Ev_Dependientes con idevento dependiente
     * el elim ef colaterales llama a elim en orden los dependientes antes de llamar al que se redefine
     * 
     * un set, un get y un add (el adddependiente se llama por ej de trans autom para agregar el ev transf)
     *  
     * 
     */
    
    //TODO como hacer q se cntrolen en el create las reglas del negocio de todos los eventos
    //ciertas reglas q son comunes a ellos? en particular, no se deben aceptar eventos anteriores al alta
    //o mejor no ev ant al ultimo ev del animal
    // --------------- attributes ---------------------
    private java.lang.Long id;

    private java.util.Date fecha;

    private ar.org.sicel.persistence.Establecimiento establecimiento;
    
    private SortedSet dependientes = new TreeSet(new EventosPorFechaYTipo());
    
    private SortedSet modificaciones = new TreeSet(new EventosPorFechaYTipo());

    public static final String EVT_TIPO_LMI = "LMI";

    public static final String EVT_TIPO_ALT = "ALT";

    public static final String EVT_TIPO_BAJ = "BAJ";

    public static final String EVT_TIPO_CRI = "CRI";

    public static final String EVT_TIPO_EST = "EST";

    public static final String EVT_TIPO_INF = "INF";

    public static final String EVT_TIPO_LAC = "LAC";

    public static final String EVT_TIPO_PRO = "PRO";

    public static final String EVT_TIPO_REP = "REP";

    public static final String EVT_TIPO_SMN = "SMN";

    public static final String EVT_TIPO_SVC = "SVC";

    public static final String EVT_TIPO_TRA = "TRA";
    
    public static final String EVT_TIPO_SEC = "SEC";
    
    public static final String EVT_TIPO_PRE = "PRE";
    
    public static final String EVT_TIPO_ORD = "ORD";
    
    public static final String EVT_TIPO_CONTROL_EST = "COE";
    
    public static final String EVT_TIPO_CONTROL_ANIMAL = "COA";
    
    public static final String EVT_TIPO_BAJA_EVENTO = "BEV";
    
    public static final String EVT_TIPO_MODIFEST = "MODIFICACION EVENTO ESTABLECIMIENTO";
    
    public static final String EVT_TIPO_MODIFANI = "MODIFICACION EVENTO ANIMAL";
    
    public static final String EVT_TIPO_CAMBIO_RP = "CRP";
    

    protected Evento() {
    }
    
    @SuppressWarnings("unchecked")
	protected Evento(Establecimiento est, java.util.Date fecha) {
    	this.establecimiento = est;
    	this.fecha = fecha;
    	
    }
    
    /**
     * Posiblemente sea mejor cambiar estos nombres por do y reDo ,
     * porque el reValidar puede que no solo tenga que validar sino
     * hacer modificaciones en base a los nuevos datos de los que dispone
     * 
     * @param msgs
     * @throws ExcepcionIntegridad
     */
    public void validar(List msgs) throws ExcepcionIntegridad {
    	;
    }
    
    /**
     * 
     * @param msgs
     * @throws ExcepcionIntegridad
     */
    public void reValidar(List msgs) throws ExcepcionIntegridad {
    	;
    	// El principal problema que tenemos aca es con
    	// la matriz de transicion, porque ahora el addEventoAnimal esta todo ahi.
    	// Creo que podriamos hacerlo andar sin cambiar demasiado si en "MatrizTransiciones.estadoPrevio(animal)"
    	// no tomamos el ultimo evento sino el ultimo evento anterior a este (Lo de las fechas de eventos anteriores
    	// a la ultima tendriamos que tener cuidado, porque estamos tocando eventos que si tienen otros despues).
    	// creo que seria lo unico
    	
    	}
    

    /**
     * 
     * @hibernate.id generator-class="native" column="id"
     * @hibernate.generator-param name="sequence" value="gen_ev_evento"
     * 
     */
    public java.lang.Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     * 
     * @hibernate.property column="fecha"
     * @hibernate.column name="fecha" not-null="true"
     * 
     */
    public java.util.Date getFecha() {
        if (this.fecha.getClass() == Timestamp.class)
            return new java.util.Date(fecha.getTime());
        else
            return this.fecha;
    }

    protected void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    // ------------- relations ------------------

    /**
     * 
     * @hibernate.many-to-one column="establecimiento" not-null="true"
     *                        outer-join="auto" foreign-key="FK_EvEstab_Estab"
     * 
     */
    public ar.org.sicel.persistence.Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }

    protected void setEstablecimiento(
            ar.org.sicel.persistence.Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    
    
    /**
     * 
     * @hibernate.set lazy="true" 
     *                sort="ar.org.sicel.persistence.EventosPorFechaYTipo"
     *                table="EV_DEPENDIENTES"
     * @hibernate.collection-key column="idevento"
     * @hibernate.collection-many-to-many class="ar.org.sicel.persistence.Evento" column="dependiente"
     * 
     */
    protected void setDependientes(SortedSet dependientes) {
    	this.dependientes = dependientes;
    }
    
    public SortedSet getDependientes() {
    	return dependientes;
    }
    

    @SuppressWarnings("unchecked")
	public void addDependiente(Evento ev) {
    	this.dependientes.add(ev);
    }

    
    protected void setModificaciones(SortedSet modif) {
    	this.modificaciones = modif;
    }
    
    public SortedSet getModificaciones() {
    	return modificaciones;
    }
    

    @SuppressWarnings("unchecked")
	public void addModificaciones(Evento ev) {
    	this.modificaciones.add(ev);
    }

    // ---------------- business methods ----------------------

    public String toString() {
        return getResumenYEncab();
    }

    /**
     * Encabezamiento del resumen del Evento
     * @return (ID) fecha tipo-ev 
     */
    protected String getResumenEncab() {
        String result = "("
                + String.format("%010d", new Object[] { this.getId() }) + ") ";
        result = result + String.format("%tF", new Object[] { getFecha() })
                + " " + this.getNombreTipo() + " ";
        return result;
    }

    /**
     * Encabezamiento y resumen del Evento
     * @return encab y resumen y proceso  
     */
    public String getResumenYEncab() {
        String result = "";
        /**
         * getResumentEstab mete un null, y si getResumen devuelve null tambien mete un null
         */
        result = result + getResumenEncab() + (getResumen()==null?"":getResumen());
        result = result + getResumenProceso(); 
        return result;
    }

    /**
     * Resumen del Evento
     * @return   
     */
    protected abstract String getResumen();

    /**
     * Resumen del proceso del Evento
     * @return   
     */
    protected abstract String getResumenProceso();

    /**
     * Nombre del tipo de evento
     * @return   
     */
    public abstract String getNombreTipo();

    public void eliminarYColaterales() throws ExcepcionIntegridad {
        Session session = HibernateFactory.getSession();
        Iterator dependientes = this.getDependientes().iterator();
        while (dependientes.hasNext()) {
        	Evento e = (Evento)dependientes.next();
        	e.eliminarYColaterales();
        }
        
        eliminarColateralesPre();
        try {
            session.delete(this);
        } catch (HibernateException he) {
            ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                    new String[] { String.valueOf(getId()), he.toString() });
            throw e;
        }
        eliminarColateralesPost();
    }

    protected void eliminarColateralesPre() throws ExcepcionIntegridad {
        // por defecto no hace nada
    }

    protected void eliminarColateralesPost() throws ExcepcionIntegridad {
        // por defecto no hace nada
    }

    /**
     * Cada evento pudo modificar (o no) al animal al cual se refiere, y a otros
     * por algun efecto colateral. Por ejemplo, el evt reprod genera animales,
     * los cuales forman parte, junto con la madre, de la lista. Por otro lado,
     * el evento semen no genera modificaciones ni siquiera en el animal
     * donante.
     * 
     * @return una lista con lso animales que fueron modificados por la
     *         inclusión de este evento en algun animal
     */
    public abstract Set getAnimalesModificados();

    public abstract void addAnimalesModificados(Set animalesModificados);
    
    
    public abstract String getLinkAProc();
    
    
    
    
    public abstract SortedSet recolectarTodosEventosDependientes();

	
    /**
     * Elimina este eventos y todos los dependientes en el tiempo con este.
     * Es decir, elimina todos los eventos que recolectarTodosEventosDependientes() retorne.
     * 
     * @throws ExcepcionIntegridad
     */
    @SuppressWarnings({"unchecked","unchecked"})
	public void darDeBaja() throws ExcepcionIntegridad {
		SortedSet eventos = recolectarTodosEventosDependientes();
		Stack stack = new Stack();
		stack.ensureCapacity(eventos.size());
		stack.addAll(eventos);
		while (!stack.empty()) {
			Evento evento = (Evento)stack.pop();
			evento.eliminarYColaterales();
		}
	}

    public abstract boolean validarBaja() throws ExcepcionIntegridad;
    
    public abstract void ejecutarBaja() throws ExcepcionIntegridad;
    
    public boolean equals(Object eve){
    	if(eve!=null){
    	//if(!(eve instanceof Evento))
		//	return false;
    	
		Evento ev = (Evento)eve;
    	
    		if((ev.getId()!=null && this.getId()!=null)&&(ev.getId().equals(this.getId())))
    			return true;
    		else{
    			 Date fecha1 = this.getFecha();
    		        Date fecha2 = ev.getFecha();
    		        String tipo1 = this.getNombreTipo();
    		        String tipo2 = ev.getNombreTipo();
    		        if(fecha1.equals(fecha2)&& tipo1.equals(tipo2))
    		        	return true;
    		}
    	}
		return false;
    	
    }
    /**
     * a.compareTo(b)
     * = 1 --> a>b
     * =-1 --> a<b
     * = 0 --> a=b
     * @param ev
     * @return
     */
    public int compareTo(Evento ev){
       
        Date fecha1 = this.getFecha();
        Date fecha2 = ev.getFecha();
        String tipo1 = this.getNombreTipo();
        String tipo2 = ev.getNombreTipo();
        Long id1 = this.getId();
        Long id2 = ev.getId();
        if(id1!=null && id2!=null && id1.equals(id2))//si tienen id y es el mismo entonces iguales
        	return 0;
        
        if (fecha1.compareTo(fecha2) != 0 
        		&& !((tipo1.equals(Evento.EVT_TIPO_SEC) && (tipo2.equals(Evento.EVT_TIPO_LAC) && DateUtils.mismoDia(fecha1, fecha2))))
        		&& !((tipo1.equals(Evento.EVT_TIPO_LAC) && (tipo2.equals(Evento.EVT_TIPO_SEC) && DateUtils.mismoDia(fecha1, fecha2))))
        		) 
        	 return fecha1.compareTo(fecha2);
        else{ //si las fechas son iguales, comparo tipos
        	if(tipo1.equals(Evento.EVT_TIPO_BAJ))
        		return 1;
        	if(tipo2.equals(Evento.EVT_TIPO_BAJ))
        		return -1;
        	if(tipo1.equals(Evento.EVT_TIPO_CONTROL_ANIMAL) && (tipo2.equals(Evento.EVT_TIPO_REP))) //si hay control y parto la misma fecha entonces es parto-control
        		return 1;
        	if(tipo1.equals(Evento.EVT_TIPO_REP) && (tipo2.equals(Evento.EVT_TIPO_CONTROL_ANIMAL))) //si hay control y parto la misma fecha entonces es parto-control
        		return -1;
        	if((tipo1.equals(Evento.EVT_TIPO_SEC) && (tipo2.equals(Evento.EVT_TIPO_LAC))))
        		return 1;
        	if((tipo1.equals(Evento.EVT_TIPO_LAC) && (tipo2.equals(Evento.EVT_TIPO_SEC))))
        		return -1;
        	if(tipo1.compareTo(tipo2)==0 && id1!=null && id2!=null)
        		return id1.compareTo(id2);
        		
        	return tipo1.compareTo(tipo2);
    }

    }
}
