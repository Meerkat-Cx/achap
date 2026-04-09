/*
 * Created on 08/06/2005
 */
package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * 
 * @hibernate.class table="Configuracion" lazy="true"
 * 
 */
public class Configuracion {
	
    private String nombre; // se usa tambien como id

    private String descripcion;

    private AtrVariables atrVariables;

    private static ar.org.sicel.persistence.ConjuntoAtributos conjuntoAtributos;

    private static Configuracion instancia;

    public static String DEFAULT_CONFIGURATION = "Defecto";
    
    public static boolean suciaCache =false;
    
    
    private static Map valores_reglas;
    private static Map atributos_por_nombre;

    // el uso tipico es agarrar un boolean cdo estamos comprobando reglas
    // asi es menos berborrajico y lo reemplazamos por el de abajo
    public static Configuracion getConfiguracionActual() {
        if (instancia == null || suciaCache == true){
                   useConfiguration(DEFAULT_CONFIGURATION);
                   suciaCache = false;
        }
        return instancia;
    }

    /*
    public static boolean getValorReglaProceso(String nombreRegla,Date fechaEvento) throws ExcepcionIntegridad {
        boolean usarFechaEvento = getConfiguracionActual().getAtrVariables().getBoolean(
                CONF.USAR_FECHA_EVENTO_AL_COMPROBAR_REGLAS , new Date() );
        Date fechaValorRegla = null; 
        if (usarFechaEvento)
            fechaValorRegla = fechaEvento;
        else
            fechaValorRegla = new Date();
        return getConfiguracionActual().getAtrVariables().getBoolean(
                nombreRegla, fechaValorRegla );
    }
    */
    
    public static void useConfiguration(String nombre) {
        try {
           
        	instancia = ConfiguracionDAO.findByNombre(nombre);
            buildCache();
        } catch (HibernateException he) {
            throw new ErrorFatal(he.getMessage(), he);
        }
    }

    private static ConjuntoAtributos getConjuntoAtributos() {
        if (conjuntoAtributos == null) {
            try {
                conjuntoAtributos = ConjuntoAtributosDAO
                        .findByName(Configuracion.class.getSimpleName());
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return conjuntoAtributos;
    }

    /**
     * 
     * @hibernate.id generator-class="assigned" column="nombre"
     * @hibernate.column name="nombre" length="32" not-null="true"
     * 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            The nombre to set.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @hibernate.property column="descripcion"
     * @hibernate.column name="descripcion" length="40"
     * 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            The descripcion to set.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @hibernate.many-to-one column="atrVariables" outer-join="auto"
     *                        foreign-key="FK_Config_atrVariables"
     *                        not-null="false" unique="true" cascade = "all"
     * 
     * @hibernate.column name="atrVariables" not-null="false" unique="true"
     *                   unique-key="UN_Conf_atrVariables"
     * 
     */
    public AtrVariables getAtrVariables() {
        return this.atrVariables;
    }

    public void setAtrVariables(AtrVariables atrVariables) {
        this.atrVariables = atrVariables;
        if(atrVariables!= null)
        	atrVariables.setConjuntoAtributos(getConjuntoAtributos());
    }
 
    
    
    
    /**
     * Prueba para ver si agiliza el procesamiento, utiliza
     * un cache de valores de reglas
     *  
     * @param at
     * @param fecha
     * @return
     */
    private static boolean reglaEn(Atributo at, Date fecha) {
    	List valores = (List)valores_reglas.get(at);
    	if (valores == null) //si no tiene valor, se usa el valor por defecto
    		return at.getValorPorDefecto().getValor().equals("1");
    	Iterator it = valores.iterator();
    	boolean encontrado = false;
    	Valor mayor = null;
    	while (it.hasNext() && ! encontrado) {
    		Valor v = (Valor) it.next();
    		boolean anteriorFecha = v.esSeteadoAntesDe(fecha);
    		boolean esPosteriorAMayor = true;
    		if (mayor != null)
    			esPosteriorAMayor =  v.esPosteriorA(mayor);
    		if (anteriorFecha && esPosteriorAMayor) {
    			mayor = v; //si es seteado antes de la fecha pasada como parametro y despues de la fecha del mayor anterior
    		}
    	}
    	if (mayor == null)
    		return at.getValorPorDefecto().getValor().equals("1");
    	return mayor.getValorAdmAtr().getValor().equals("1");
    }
    
    public static boolean getValorReglaProceso(String nombreRegla,Date fechaEvento) throws ExcepcionIntegridad {
    	getConfiguracionActual(); //solo para que la cree con la por defecto si nadie la seteo 
    	Atributo at = (Atributo)atributos_por_nombre.get(nombreRegla);
    	Atributo usarFecha = (Atributo) atributos_por_nombre.get(CONF.USAR_FECHA_EVENTO_AL_COMPROBAR_REGLAS);
    	boolean usarFechaEvento = reglaEn(usarFecha,new Date());
    	Date fechaValorRegla = null; 
        if (usarFechaEvento)
            fechaValorRegla = fechaEvento;
        else
            fechaValorRegla = new Date();		
        
        return reglaEn(at,fechaValorRegla);
    }
    
    @SuppressWarnings({"unchecked","unchecked"})
	public static void buildCache() {
    	conjuntoAtributos = null;
    	instancia.atrVariables.setConjuntoAtributos(getConjuntoAtributos());
    	Iterator it = instancia.getAtrVariables().getValors().iterator();
    	valores_reglas = new HashMap();
    	atributos_por_nombre = new HashMap();
    	
    	while (it.hasNext()) {
    		Valor v = (Valor) it.next();
    		Atributo at = v.getValorAdmAtr().getAtributo();
    		List valores = null;
    		if (!valores_reglas.containsKey(at)) {
    			valores = new LinkedList();
    			valores_reglas.put(at,valores);
    		} else {
    			valores = (List)valores_reglas.get(at);
    		}
    		valores.add(v);
    	}
    	Iterator posiblesAt = instancia.getAtrVariables().getConjuntoAtributos().getAtributos().iterator();
    	while (posiblesAt.hasNext()) {
    		Atributo at = (Atributo)posiblesAt.next();
    		atributos_por_nombre.put(at.getNombre(),at);
    	}
    }

}
