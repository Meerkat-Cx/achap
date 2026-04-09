/*
 * Created on 15/06/2005
 */
package ar.org.sicel.persistence;

/**
 * @author pablo
 *
 * @hibernate.class
 *     table="An_Transicion"
 *     lazy="true"
 */
public class Transicion {
	private Long id;
	private String estadoOrigen;
    private String evento;
	private String estadoDestino;
    private Boolean finalizaLactancia;
    private Boolean iniciaLactancia;
	private ProcCodMsg codigoMensaje;
	private Byte nivelError;

    /*
alter table an_Transicion add finalizaLactancia smallint;
alter table an_Transicion add iniciaLactancia smallint;

Update an_transicion set inicialactancia=          0   ,set finalizalactancia= 0   Where estadoorigen =    'SV'    And evento= 'EvtReproduccion'   And estadodestino=  'SV'    ;
Update an_transicion set inicialactancia=          1   ,set finalizalactancia= 0   Where estadoorigen =    'SV'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          0   ,set finalizalactancia= 0   Where estadoorigen =    'SS'    And evento= 'EvtReproduccion'   And estadodestino=  'SV'    ;
Update an_transicion set inicialactancia=          1   ,set finalizalactancia= 0   Where estadoorigen =    'SS'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          0   ,set finalizalactancia= 0   Where estadoorigen =    'SP'    And evento= 'EvtReproduccion'   And estadodestino=  'SV'    ;
Update an_transicion set inicialactancia=          1   ,set finalizalactancia= 0   Where estadoorigen =    'SP'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          0   ,set finalizalactancia= 0   Where estadoorigen =    'PV'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          1   ,set finalizalactancia= 1   Where estadoorigen =    'PV'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          0   ,set finalizalactancia= 0   Where estadoorigen =    'PS'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          1   ,set finalizalactancia= 1   Where estadoorigen =    'PS'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          0   ,set finalizalactancia= 0   Where estadoorigen =    'PP'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
Update an_transicion set inicialactancia=          1   ,set finalizalactancia= 1   Where estadoorigen =    'PP'    And evento= 'EvtReproduccion'   And estadodestino=  'PV'    ;
     */
    
    /**
     * 
     * @hibernate.property column="finalizaLactancia"
     * @hibernate.column name="finalizaLactancia" not-null="false"
     * 
     */
    public java.lang.Boolean getFinalizaLactancia() {
        return this.finalizaLactancia;
    }

    protected void setFinalizaLactancia(java.lang.Boolean finalizaLactancia) {
        this.finalizaLactancia = finalizaLactancia;
    }
    
    /**
     * 
     * @hibernate.property column="iniciaLactancia"
     * @hibernate.column name="iniciaLactancia" not-null="false"
     * 
     */
    public java.lang.Boolean getIniciaLactancia() {
        return this.iniciaLactancia;
    }

    protected void setIniciaLactancia(java.lang.Boolean iniciaLactancia) {
        this.iniciaLactancia = iniciaLactancia;
    }
    
   /**
    *
    * @hibernate.property
    *     column="estadoDestino"
    * @hibernate.column
    *     name="estadoDestino"
    *     length="32"
    *
    */
	public String getEstadoDestino() {
		return estadoDestino;
	}
	
	/**
	 * @param estadoDestino The estadoDestino to set.
	 */
	public void setEstadoDestino(String estadoDestino) {
		this.estadoDestino = estadoDestino;
	}
	
   /**
    *
    * @hibernate.property
    *     column="estadoOrigen"
    * @hibernate.column
    *     name="estadoOrigen"
    *     length="32"
    *
    */
	public String getEstadoOrigen() {
		return estadoOrigen;
	}
	
	/**
	 * @param estadoOrigen The estadoOrigen to set.
	 */
	public void setEstadoOrigen(String estadoOrigen) {
		this.estadoOrigen = estadoOrigen;
	}
	
   /**
    *
    * @hibernate.property
    *     column="evento"
    * @hibernate.column
    *     name="evento"
    *     length="32"
    *
    */
	public String getEvento() {
		return evento;
	}
	
	/**
	 * @param evento The evento to set.
	 */
	public void setEvento(String evento) {
		this.evento = evento;
	}
	
   /**
    *
    * @hibernate.id
    *     generator-class="native"
    *     column="id"
    * @hibernate.generator-param
    *     name="sequence"
    *     value="gen_an_animal"
    *
    */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
   /**
    *
    * @hibernate.property
    *     column="nivelError"
    */
	public Byte getNivelError() {
		return nivelError;
	}
	
	/**
	 * @param nivelError The nivelError to set.
	 */
	public void setNivelError(Byte nivelError) {
		this.nivelError = nivelError;
	}
	
   
   /**
    *
    * @hibernate.many-to-one
    *     column="codMsg"
    *     not-null="true"
    *     outer-join="auto"
    *     
    *
    */
	public ProcCodMsg getCodigoMensaje() {
		return codigoMensaje;
	}
	
	/**
	 * @param codigoMensaje The codigoMensaje to set.
	 */
	public void setCodigoMensaje(ProcCodMsg codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}
	
    public String toString()
    {
        return "( " + getEstadoOrigen() + " , " + getEvento() + " )--> "
                + getEstadoDestino() + " [ "
                + (getIniciaLactancia() ? "1" : "0") + " , "
                + (getFinalizaLactancia() ? "1" : "0") + " ] "
                + getCodigoMensaje() + " ( " + getNivelError() + " )";
    }
   
}
