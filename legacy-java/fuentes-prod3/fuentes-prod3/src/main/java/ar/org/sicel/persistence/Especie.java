package ar.org.sicel.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;


/**
 *
 * @hibernate.class
 *     table="An_Especie"
 *     lazy="true"
 *
 */
public class Especie {
    private static ar.org.sicel.persistence.ConjuntoAtributos conjuntoAtributos;
   
    // --------------- attributes ---------------------
    private java.lang.String id;
    private java.lang.String nombre;
    private java.util.Set razas;
    private ar.org.sicel.persistence.AtrVariables atrVariables;
    
    
    
    //unicamente para hacer caching de los atributos de la especie
    private Map map_parametros; 

    private static ConjuntoAtributos getConjuntoAtributos() {
    	if (conjuntoAtributos == null) {
    		try {
    			conjuntoAtributos = ConjuntoAtributosDAO.findByName(Especie.class.getSimpleName());
    		} catch (HibernateException e) {
    			e.printStackTrace();
    		}
    	}
    	return conjuntoAtributos;
    }
    
    protected Especie() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="assigned"
     *     column="id"
     * @hibernate.column
     *     name="id"
     *     length="4"
     *
     */
    public java.lang.String getId() {
        return this.id;
    }

    protected void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     *
     * @hibernate.property
     *     column="nombre"
     * @hibernate.column
     *     name="nombre"
     *     not-null="true"
     *     length="60"
     *
     */
    public java.lang.String getNombre() {
        return this.nombre;
    }

    protected void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     cascade="none"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="especie"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Raza"
     *
     */
    public java.util.Set getRazas() {
        return this.razas;
    }

    protected void setRazas(java.util.Set razas) {
        this.razas = razas;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="atrVariables"
     *     outer-join="auto"
     *     foreign-key="FK_Espe_AtrVar"
     *     not-null="false"
     *     unique="true"
     *     cascade = "all"
     *
     * @hibernate.column
     *     name="atrVariables"
     *     not-null="false"
     *     unique="true"
     *     unique-key="UN_An_Espe_atr_Variables"
     *
     */
    public ar.org.sicel.persistence.AtrVariables getAtrVariables() {
    	if (atrVariables == null) //para que siempre retorne uno valido, si no esta presente lo crea (vacio)
    		this.setAtrVariables(AtrVariablesDAO.create()); 
    	return this.atrVariables;
    }

    public void setAtrVariables(
        ar.org.sicel.persistence.AtrVariables atrVariables) {
    	if (atrVariables == null) //para que siempre retorne uno valido, si no esta presente lo crea (vacio)
    		atrVariables =AtrVariablesDAO.create(); 
        this.atrVariables = atrVariables;
        atrVariables.setConjuntoAtributos(getConjuntoAtributos());
    }

    // ---------------- business methods  ----------------------
    
    public Raza getDesconocida() throws ErrorFatal {
    	try {
			return checkDesconocidaUnica();
		} catch (ExcepcionIntegridad e) {
			throw new ErrorFatal(e);
		}
    }
    
    protected Raza checkDesconocidaUnica() throws ExcepcionIntegridad{
    	Raza result = null;
    	Iterator it =razas.iterator();
    	int cantidad = 0;
    	while (it.hasNext()) {
    		Raza raza = (Raza) it.next();
    		if (raza.getEsDesconocido()) {
    			result = raza;
    			cantidad++;
    		}
    	}
    	if (cantidad != 1) {
    		throw new ExcepcionIntegridad(MENSAJES.CANTIDAD_RAZAS_DESC_NO1, new String[]{String.valueOf(cantidad),this.getNombre()});
    	}
    	return result;
    }
    
    
    public Raza getCruza() throws ErrorFatal {
    	try {
			return checkCruzaUnica();
		} catch (ExcepcionIntegridad e) {
			throw new ErrorFatal(e);
		}
    }
    
    protected Raza checkCruzaUnica() throws ExcepcionIntegridad{
    	Raza result = null;
    	Iterator it =razas.iterator();
    	int cantidad = 0;
    	while (it.hasNext()) {
    		Raza raza = (Raza) it.next();
    		if (raza.getEsCruza()) {
    			result = raza;
    			cantidad++;
    		}
    	}
    	if (cantidad != 1) {
    		throw new ExcepcionIntegridad(MENSAJES.CANTIDAD_RAZAS_CRUZA_NO1, new String[]{String.valueOf(cantidad),this.getNombre()});
    	}
    	return result;
    }
    
    
    
    public String getParametro(String atributo) {
    	if (map_parametros == null)
    		buildCacheParametros();
    	String resultado = (String)map_parametros.get(atributo);
    	return resultado;
    }
    
    public float getParametroAsFloat(String at) {
    	return Float.parseFloat(getParametro(at));
    }
    
    public int getParametroAsInteger(String at) {
    	return Integer.parseInt(getParametro(at));
    }
    
    @SuppressWarnings({"unchecked","unchecked"})
	private void buildCacheParametros() {
    	map_parametros = new HashMap();
    	Iterator posibles = this.getAtrVariables().getConjuntoAtributos().getAtributos().iterator();
    	while (posibles.hasNext()) { //todos los posibles atributos
    		Atributo at = (Atributo) posibles.next();
    		map_parametros.put(at.getNombre(),at.getValorPorDefecto().getValor());
    	}
    	Iterator seteados = this.getAtrVariables().getValors().iterator();
    	while (seteados.hasNext()) { //sobreescribo los que esten seteados
    		Valor v = (Valor) seteados.next();
    		map_parametros.put(v.getValorAdmAtr().getAtributo().getNombre(),v.getValorAdmAtr().getValor());
    	}
    }
    
}
