package ar.org.sicel.persistence;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;

import ar.org.sicel.proc.v1.lote.types.STRaza;




/**
 *
 * @hibernate.class
 *     table="An_Raza"
 *     lazy="true"
 *
 */
public class Raza {
    private static ar.org.sicel.persistence.ConjuntoAtributos conjuntoAtributos;
    
    private static ConjuntoAtributos getConjuntoAtributos() {
    	if (conjuntoAtributos == null) {
    		try {
    			conjuntoAtributos = ConjuntoAtributosDAO.findByName(Raza.class.getSimpleName());
    		} catch (HibernateException e) {
    			e.printStackTrace();
    		}
    	}
    	return conjuntoAtributos;
    }
    
    public int hashCode()
    {
        if(nombre != null)
            return nombre.hashCode();
        else
            return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(!(obj instanceof Raza))
    		return false;
    	Raza r = (Raza) obj;
    	/*if(this == r)
    		return true;*/
    	return this.nombre.equals(r.getNombre());
    }

    // --------------- attributes ---------------------
    private java.lang.String id;
    private java.lang.String nombre;
    private ar.org.sicel.persistence.Especie especie;
    private ar.org.sicel.persistence.AtrVariables atrVariables;
    private java.lang.Boolean esCruza;
    private java.lang.Boolean esDesconocido;
    private String categoriaPura;
    private Foto foto;
    private String codigoRaza;
    private Integer idFicticio;
	
    
    //unicamente para hacer caching de los atributos de la raza
    private Map map_parametros; 
    
    
    public static final String EDAD_MINIMA_PRODUCIR_SEMEN = "EDAD_MINIMA_PRODUCIR_SEMEN";
    public static final String[] EDAD_MINIMA_PRODUCIR_SEMEN_VALS = {"1095"};
    public static final String EDAD_MINIMA_PRODUCIR_SEMEN_DESC = "Edad mínima de un toro para producir semen";
	
    public static final String EDAD_MINIMA_MONTA = "EDAD_MINIMA_MONTA";
	public static final String[] EDAD_MINIMA_MONTA_VALS = {"2190"};
    public static final String EDAD_MINIMA_MONTA_DESC = "Edad mínima de un toro para poder montar";	
	
	public static final String PERIODO_GESTACION_PROMEDIO = "PERIODO_GESTACION_PROMEDIO(dias)";
	public static final String[] PERIODO_GESTACION_PROMEDIO_VALS = {"270"};
	public static final String PERIODO_GESTACION_PROMEDIO_DESC = "Periodo de gestacion promedio, en dias";	
	
	public final static String EDAD_MINIMA_PRODUCIR_EMBRIONES = "EDAD_PROD_EMBR(dias)";
	public final static String[] EDAD_MINIMA_PRODUCIR_EMBRIONES_VALS = {"2190"};
	public final static String EDAD_MINIMA_PRODUCIR_EMBRIONES_DESC = "Edad mínima para poder producir embriones, en dias";

	public final static String EDAD_MINIMA_SERVICIO_ARTIFICIAL = "EDAD_MIN_SERV_ART(dias)";
	public final static String[] EDAD_MINIMA_SERVICIO_ARTIFICIAL_VALS = {"1095"};
	public final static String EDAD_MINIMA_SERVICIO_ARTIFICIAL_DESC = "Edad minima de la hembra para poder resivir un servicio artificial, en dias";
	
	
	public final static String EDAD_MINIMA_SERVICIO_NATURAL = "EDAD_SERV_NAT(dias)";
	public final static String[] EDAD_MINIMA_SERVICIO_NATURAL_VALS = {"2190"};
	public final static String EDAD_MINIMA_SERVICIO_NATURAL_DESC = "Edad minima de la hembra para poder resivir un servicio natural, en dias";
	
	
	public static final String CICLO_CELO_MINIMO = "CICLO_CELO_MINIMO(dias)";
	public static final String[] CICLO_CELO_MINIMO_VALS = {"27"};
	public static final String CICLO_CELO_MINIMO_DESC = "Ciclo de celo mínimo, en dias";
	public static final String DIAS_MAX_ENTRE_LOTE_EVENTO = "Dias maximo entre fechas de evento y de lote";
	
	
	public static final String DIAS_MIN_ENTRE_LACT = "Dias minimo entre lactancias";
	
    public static final String DIAS_MIN_ABL = "Cantidad Dias Minima para considerar Aborto Largo";
    
    public static final String DIAS_MAXIMO_ABORTO_LARGO = "DIAS_MAXIMO_ABORTO_LARGO";
    
    public static final String DIAS_MIN_LACT_ABL = "Cantidad minima en dias lactando para considerar Aborto Largo";

	public static final String GESTACION_MINIMA = "Periodo de gestacion minimo, en dias";
	
	public static final String GESTACION_MAXIMA = "Peridodo de gestacion maximo, en dias";
	
	public static final String EDAD_MAX_FERTIL = "Edad Maxima Fertil, en dias";
	
	public static final String PERIDODO_DESCANZO_MINIMO = "Periodo minimo de descanzo, en dias";
	
	public static final String DIAS_MAX_RETROACIVIDAD = "Dias maximo de retroactividad";
	
	public static final String DIAS_MAX_RETROACIVIDAD_CONTROL = "Dias maximo de retroactividad control";

	public static final String DIAS_MAX_RETROACIVIDAD_SECADA = "Dias maximo de retroactividad secada";
	
	public static final String DIAS_MINIMOS_ENTRE_CONTROLES_X4 = "Dias minimos entre controles X4";
	public static final String DIAS_MAXIMOS_ENTRE_CONTROLES_X4 = "Dias maximos entre controles X4";
	public static final String DIAS_MINIMOS_ENTRE_CONTROLES_X6 = "Dias minimos entre controles X6";
	public static final String DIAS_MAXIMOS_ENTRE_CONTROLES_X6 = "Dias maximos entre controles X6";
	public static final String DIAS_MINIMOS_PRIMER_CONTROL = "Dias minimos primer control";
	
	public static final String DIAS_MAX_PORTERIORIDAD_INFORMAR_SERVICIO_A_PARTO = "Dias maximo de posterioridad del servicio al parto";
	//public static final String RANGO_DE_SERVICIO_VALIDO = "Distancia mínima entre Servicios, en dias";
	
	public static final String HOLA = "HOLA";
	public static final String DEBO = "DEBO";
	public static final String CRBO = "CRBO";
	public static final String JERY = "JERY";
	public static final String SWBO = "SWBO";
	public static final String SRBO = "SRBO";
	public static final String NORM = "NORM";
	public static final String AYSH = "AYSH";
	public static final String GUER = "GUER";
	public static final String MONT = "MONT";
	public static final String SHOR = "SHOR";
	public static final String BRAH = "BRAH";
	public static final String NELO = "NELO";
	public static final String SIMM = "SIMM";
	public static final String BICA = "BICA";
	public static final String DEBU = "DEBU";
	public static final String MUBU = "MUBU";
	public static final String MEBU = "MEBU";
	public static final String JABU = "JABU";
	public static final String CRBU = "CRBU";
	
	private static java.util.Hashtable<String,String> initCodigosRazas()
    {
        Hashtable<String,String> members = new Hashtable<String,String>();
        members.put(CRBO, STRaza.XB.toString());
        members.put(HOLA, STRaza.HO.toString());
        members.put(DEBO, "");
        members.put(JERY, STRaza.JY.toString());
        members.put(SWBO, STRaza.SW.toString());
        members.put(SRBO, STRaza.SB.toString());
        members.put(NORM, STRaza.NO.toString());
        members.put(AYSH, STRaza.AY.toString());
        members.put(GUER, STRaza.GU.toString());
        members.put(MONT, STRaza.MO.toString());
        members.put(SHOR, STRaza.SH.toString());
        members.put(BRAH, STRaza.BR.toString());
        members.put(NELO, STRaza.NE.toString());
        members.put(SIMM, STRaza.SI.toString());
        members.put(BICA, STRaza.BC.toString());
        members.put(DEBU, "");
        members.put(MUBU, STRaza.MU.toString());
        members.put(MEBU, STRaza.ME.toString());
        members.put(JABU, STRaza.JU.toString());
        members.put(CRBU, STRaza.XU.toString());
        return members;
    }
	
	java.util.Hashtable<String,String> codigosRazas = initCodigosRazas();
	
	public Raza() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="assigned"
     *     column="id"
     * @hibernate.column
     *     name="id"
     *     length="4"
     *     not-null="true"
     *
     */
    public java.lang.String getId() {
        return this.id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     *
     * @hibernate.property
     *     column="nombre"
     * @hibernate.column
     *     name="nombre"
     *     not-null="true"
     *     length="40"
     *
     */
    public java.lang.String getNombre() {
        return this.nombre; 
    }

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @hibernate.property
     *     column="esCruza"
     *
     */
    public java.lang.Boolean getEsCruza() {
        return this.esCruza;
    }

    public void setEsCruza(java.lang.Boolean esCruza) {
        this.esCruza = esCruza;
    }

    /**
     *
     * @hibernate.property
     *     column="esDesconocido"
     *     type="java.lang.Boolean"
     *
     */
    public java.lang.Boolean getEsDesconocido() {
        return this.esDesconocido;
    }

    public void setEsDesconocido(java.lang.Boolean esDesconocido) {
        this.esDesconocido = esDesconocido;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="especie"
     *     outer-join="auto"
     *     not-null="true"
     *     foreign-key="FK_Raza_Especie"
     *
     */
    public ar.org.sicel.persistence.Especie getEspecie() {
        return this.especie;
    }

    public void setEspecie(ar.org.sicel.persistence.Especie especie) {
        this.especie = especie;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="atrVariables"
     *     outer-join="auto"
     *     foreign-key="FK_Raza_AtrVar"
     *     not-null="false"
     *     unique="true"
     *     cascade = "all"
     *     
     * @hibernate.column
     *     name="atrVariables"
     *     not-null="false"
     *     unique="true"
     *     unique-key="UN_An_Raza_atr_Variables"
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
        this.atrVariables.setConjuntoAtributos(getConjuntoAtributos());
    }


    public String toString()
    {
    	return this.id;
    }
    
    
    public String getParametro(String atributo) {
    	if (map_parametros == null)
    		buildCacheParametrosMedicion();
    	String resultado = (String)map_parametros.get(atributo);
    	return resultado;
    }
    
    /*
    protected float getParametroAsFloat(String at) {
    	return Float.parseFloat(getParametro(at));
    }
    
    protected int getParametroAsInteger(String at) {
    	return Integer.parseInt(getParametro(at));
    }
    */
    
    @SuppressWarnings({"unchecked","unchecked"})
	private void buildCacheParametrosMedicion() {
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

    
    
    /**
    *
    * @hibernate.many-to-one
    *     column="foto"
    *     not-null="false"
    *     outer-join="auto"
    *     foreign-key="FK_an_raza_foto"
    *
    */
    
    
	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public String getCategoriaPura() {
		return categoriaPura;
	}

	public void setCategoriaPura(String categoriaPura) {
		this.categoriaPura = categoriaPura;
	}

	public String getCodigoRaza() {
		return this.codigosRazas.get(this.id);
	}

	public Integer getIdFicticio() {
		return idFicticio;
	}

	public void setIdFicticio(Integer idFicticio) {
		this.idFicticio = idFicticio;
	}

    
    
    
    
    // ---------------- business methods  ----------------------
}
