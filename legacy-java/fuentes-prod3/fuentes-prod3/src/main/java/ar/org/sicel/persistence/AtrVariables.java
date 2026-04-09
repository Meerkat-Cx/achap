package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;



/**
 *
 * @hibernate.class
 *     table="At_Atr_Variables"
 *     lazy="true"
 *
 */
public class AtrVariables {
    // --------------- attributes ---------------------
    private java.lang.Long id;
    private ar.org.sicel.persistence.ConjuntoAtributos conjuntoAtributos;
    private java.util.Set valors;

    protected AtrVariables() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_at_atr_variables"
     *
     */
    public java.lang.Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
        this.id = id;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="all" 
     *     inverse = "true"
     * @hibernate.collection-key
     *     column="atr_variable"
     *     foreign-key="FK_Valo_AtVa"
     * @hibernate.collection-key-column
     *     name="atr_variable"
     *     not-null="true"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Valor"
     *
     */
    public java.util.Set getValors() {
        return this.valors;
    }

    protected void setValors(java.util.Set valors) {
        this.valors = valors;
    }

    // ---------------- business methods  ----------------------
    
    /**
     * Obtiene el valor del attributo atr en la fecha dada, o
     * el valor por defecto en caso de no tener ningun valor seteado
     * en esa fecha
     *  
     *@param atr
     *@param fecha 
     */
    public ValorAdmAtr getValor(Atributo atr, Date fecha) {
    	if (!this.conjuntoAtributos.getAtributos().contains(atr)) 
    		throw new ErrorFatal("El atributo " + atr.getId() +"(" + atr.getNombre()+ ")" + " no pertenece al conjunto de atributos " + conjuntoAtributos.getNombre() );
    	Iterator valores = null;
    	try {
    		valores = ValorDAO.findByAttributo(this,atr).iterator();
    	} catch (HibernateException he) {
    		throw new ErrorFatal(he);
    	}
    	Valor mayor = null;
    	while (valores.hasNext()) {
    		Valor v = (Valor) valores.next();
    		boolean anteriorFecha = v.esSeteadoAntesDe(fecha);
    		boolean esPosteriorAMayor = true;
    		if (mayor != null)
    			esPosteriorAMayor =  v.esPosteriorA(mayor);
    		if (anteriorFecha && esPosteriorAMayor) {
    			mayor = v; //si es seteado antes de la fecha pasada como parametro y despues de la fecha del mayor anterior
    		}
    	}
    	if (mayor == null)
    		return atr.getValorPorDefecto();
    	return mayor.getValorAdmAtr();
    }
    
    /**
     * Setea el valor de un attributo, desde la fecha indicada en adelante
     * (o desde tiempos prehistoricos si se pasa null)
     *  
     * @param atr
     * @param fechaFinal
     * @param valor
     */
    public void setValor(Atributo atr, java.util.Date fechaInicial, ValorAdmAtr valor) throws ExcepcionIntegridad{
    	if (!this.conjuntoAtributos.getAtributos().contains(atr)) 
    		throw new ErrorFatal("El atributo " + atr.getId() +"(" + atr.getNombre()+ ")" + " no pertenece al conjunto de atributos " + conjuntoAtributos.getNombre());
    	if (!this.conjuntoAtributos.getEsIntervalo())
    		throw new ErrorFatal("El conjunto de atributos " + conjuntoAtributos.getId() + " no soporta intervalos");
    	if (valor.getAtributo() != atr) 
    		throw new ExcepcionIntegridad(MENSAJES.VALOR_NO_ATR,new String[] {atr.getNombre(), valor.getValor()});
    	Valor v = ValorDAO.create(fechaInicial,valor);
    	agregarAValores(v);
    }
    
    public void setValor(Atributo atr, ValorAdmAtr valor) throws ExcepcionIntegridad{
    	if (this.conjuntoAtributos.getEsIntervalo())
    		throw new ErrorFatal("El conjunto de atributos " + conjuntoAtributos.getId() + " es de tipo intervalos");
    	this.valors.clear();
    	if (valor.getAtributo() == atr) {
    		Valor v = ValorDAO.create(null,valor);
    		agregarAValores(v);
    	} else {
    		throw new ExcepcionIntegridad(MENSAJES.VALOR_NO_ATR,new String[] {atr.getNombre(), valor.getValor()});
    	}
    }
    
    public ValorAdmAtr getValor(Atributo atr) throws ExcepcionIntegridad{
    	if (this.conjuntoAtributos.getEsIntervalo())
    		throw new ErrorFatal("El conjunto de atributos " + conjuntoAtributos.getId() + " es de tipo intervalos");
    	try {
			Iterator valores = ValorDAO.findByAttributo(this,atr).iterator();
			if (valores.hasNext()) {
				Valor v = (Valor)valores.next();
				return v.getValorAdmAtr();
			}
			else
				return atr.getValorPorDefecto();
		} catch (HibernateException e) {
			throw new ErrorFatal(e);
		}
    }
    
   
    @SuppressWarnings("unchecked")
	private void agregarAValores(Valor v) {
    //	Configuracion.suciaCache = true;
    	this.valors.add(v);
    	v.setAtrVariable(this);
    }
    
    /**
     * No lo maneja hibernate, lo seteamos nosotros
     * @param conj
     */
    public void setConjuntoAtributos(ConjuntoAtributos conj) {
    	this.conjuntoAtributos = conj;
    }

		
	/**
	 * @param string
	 * @param date
	 * @throws HibernateException 
	 */
	public ValorAdmAtr getValor(String nombreAt, Date date)  throws  ExcepcionIntegridad {
		Atributo at=null;
		try {
			at = AtributoDAO.findByNombre(nombreAt);
		} catch (HibernateException e) {
			throw new ErrorFatal("Atributo " + nombreAt + " no persistido. " + e );
		}
		if (at == null)
			throw new ErrorFatal("Atributo " + nombreAt + " no existe");
		return getValor(at,date);
		
	}
    
	
	    
	
	public ValorAdmAtr getValor(String nombreAt) throws  ExcepcionIntegridad { 
		Atributo at = null;
		try {
			at = AtributoDAO.findByNombre(nombreAt);
		} catch (HibernateException e) {
			throw new ErrorFatal("Atributo " + nombreAt + " no persistido. " + e );
		}
		if (at == null)
			throw new ErrorFatal("Atributo " + nombreAt + " no existe");
		return getValor(at);
	
}

	
	
	
	public boolean getBoolean(String nombreAt) throws ExcepcionIntegridad {
		return getValor(nombreAt).getValor().equals("1");
	}
	
	public int getInteger(String nombreAt) throws ExcepcionIntegridad {
		return Integer.parseInt(getValor(nombreAt).getValor());
	}
	
	public float getFloat(String nombreAt) throws ExcepcionIntegridad {
		return Float.parseFloat(getValor(nombreAt).getValor());
	}

	/**
	 * @param med_max_min
	 * @param date
	 * @return
	 * @throws ExcepcionIntegridad 
	 */
	public boolean getBoolean(String nombreAt, Date date) throws ExcepcionIntegridad {
		return getValor(nombreAt,date).getValor().equals("1");
	}
	
	/**
	 * para los atributos fechados
	 * @param nombreAt
	 * @param date
	 * @param valor
	 */
	public void setValor(String nombreAt, Date fecha, String valor) throws ExcepcionIntegridad {
		Atributo at = null;
		try {
			at = AtributoDAO.findByNombre(nombreAt);
		} catch (HibernateException e) {
			throw new ErrorFatal("Atributo " + nombreAt + " no persistido. " + e );
		}
		if (at == null)
			throw new ErrorFatal("Atributo " + nombreAt + " no existe");
		ValorAdmAtr v= ValorAdmAtrDAO.create(at,valor);
		/*
		 * ValorAdmAtr v = at.getValor(valor);
		
		//si no tiene ese valor como permitido, pero la regla es crearlo, lo crea
		if ((v == null) &&  Configuracion.getValorReglaProceso(CONF.CREACION_AUTOMATICA_VALORES_AT,new Date())) 
				v = ValorAdmAtrDAO.create(at,valor);
		*/		
		if (v == null)  //no lo encontro ni lo creo
			throw new ExcepcionIntegridad(MENSAJES.VALOR_NO_ATR,new String[] {at.getNombre(), valor});

		this.setValor(at,fecha,v);
	}
	
	
	
	/**
	 * para los atributos que no dependen de la fecha
	 * 
	 * @param nombreAt
	 * @param valorAt
	 */
	public void setValor(String nombreAt, String valor) throws ExcepcionIntegridad {
		Atributo at = null;
		try {
			at = AtributoDAO.findByNombre(nombreAt);
		} catch (HibernateException e) {
			throw new ErrorFatal("Atributo " + nombreAt + " no persistido. " + e );
		}
		if (at == null)
			throw new ErrorFatal("Atributo " + nombreAt + " no existe");
		
		ValorAdmAtr v= ValorAdmAtrDAO.create(at,valor);
		/*ValorAdmAtr v = at.getValor(valor);
		
		//si no tiene ese valor como permitido, pero la regla es crearlo, lo crea
		if ((v == null) &&  Configuracion.getValorReglaProceso(CONF.CREACION_AUTOMATICA_VALORES_AT,new Date())) 
				v = ValorAdmAtrDAO.create(at,valor);
		*/	
		if (v == null)  //no lo encontro ni lo creo
			throw new ExcepcionIntegridad(MENSAJES.VALOR_NO_ATR,new String[] {at.getNombre(), valor});

		this.setValor(at,v);
	}
	
	
	public ConjuntoAtributos getConjuntoAtributos() {
		return this.conjuntoAtributos;
	}
}
