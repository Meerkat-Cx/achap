package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.types.STTipoServicio;


/**
*
* @hibernate.subclass
*     discriminator-value="false"
*     lazy="true"
*/
public class Macho extends ar.org.sicel.persistence.Animal {
    // --------------- attributes ---------------------
    private java.util.Set hijosGeneticos;
    //private java.util.Set evtNuevoInds;
    private java.util.SortedSet evtNuevoInds;
    private java.util.Set evtSemens;

    public Macho() {
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     *     cascade="save-update"
     * @hibernate.collection-key
     *     column="padre"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.Animal"
     *
     */
    public java.util.Set getHijosGeneticos() {
        return this.hijosGeneticos;
    }

    protected void setHijosGeneticos(java.util.Set hijosGeneticos) {
        this.hijosGeneticos = hijosGeneticos;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="padreGen"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.EvtNuevoInd"
     *
     */
    public java.util.SortedSet getEvtNuevoInds() {
        return this.evtNuevoInds;
    }

    protected void setEvtNuevoInds(java.util.SortedSet evtNuevoInds) {
        this.evtNuevoInds = evtNuevoInds;
    }

    /**
     *
     * @hibernate.set
     *     lazy="true"
     *     inverse="true"
     * @hibernate.collection-key
     *     column="donante"
     * @hibernate.collection-one-to-many
     *     class="ar.org.sicel.persistence.EvtSemen"
     *
     */
    public java.util.Set getEvtSemens() {
        return this.evtSemens;
    }

    protected void setEvtSemens(java.util.Set evtSemens) {
        this.evtSemens = evtSemens;
    }

    // ---------------- business methods  ----------------------

    public boolean esHembra() {
    	return false;
    }
    
    /**
     * Agregar a este macho un nuevo servicio que realizo, puede ser una inseminacion
     * o una transferencia embrionaria. 
     * Ver comentario en EvtServicioDAO.createNuevoIndividuoPorTE
     * Verifica la edad minima del macho, la cual depende de la raza y de si es
     * con o sin monta (ej: CORRAL vs Ins. Artificial)
     * @param serv
     * @param msgs
     */
    @SuppressWarnings({"unchecked","unchecked", "unchecked"})
	public void addServicioEfectuado(Establecimiento est,EvtNuevoInd serv,List msgs) throws ExcepcionIntegridad {
    	//OJO!: La lista no esta con templates a ProcMsg, porque falla el xdoclet..
    	//no se porque, porque en Animal no falla, pero aca si... (horas pasaron..)
    	
    	if (serv.getTipoServ().equals(STTipoServicio.CAMP.toString()) ||  //si es inseminacion a campo o a corral  
    			serv.getTipoServ().equals(STTipoServicio.CORR.toString())) {
    		if(this.getEstablecimiento()==null){
	    		Animal.checkUnicidadRPEnEstabForUpdate(this.getId(),est,this.getRP(),msgs,this.getFechaNac(),this.getEstablecimiento(),this.getRegistroOrigen(),this.getCategoria());
				//this.setEstablecimiento(serv.getAnimal().getEstablecimiento());
    		}
    		if(serv.getAnimal().getEstablecimiento()!=null){ // la madre tiene establecimiento
    			if(getEstablecimiento()!=null){ // el toro tiene establecimiento
    				if(!serv.getAnimal().getEstablecimiento().equals(getEstablecimiento())){ // el estab de la madre no es igual al estab del toro
    					if(!serv.getAnimal().getEstablecimiento().getPropietario().equals(getEstablecimiento().getPropietario())){ // el propietario del estab de la madre no es igual al prop del estab del toro
			    			if (Configuracion.getValorReglaProceso(CONF.SERVICIO_MISMO_PROP_EST,serv.getFecha())) {
			    				throw new ExcepcionIntegridad(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,new String[0]);
			    			} else {
			    				ProcMsg msg = ProcMsgDAO.create(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,ProcMsg.WARNING,new String[0]);
			    				msgs.add(msg);
			    			}
    					}
    				}
    			}
    		}else{ // la madre no tiene establecimiento
    			if(getEstablecimiento()!=null){ // el toro tiene establecimiento
    				if(!est.equals(getEstablecimiento())){ // el estab que informa no es igual al del toro
    					if(!est.getPropietario().equals(getEstablecimiento().getPropietario())){ // el prop del estab que informa no es el prop del estab del toro
			    			if (Configuracion.getValorReglaProceso(CONF.SERVICIO_MISMO_PROP_EST,serv.getFecha())) {
			    				throw new ExcepcionIntegridad(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,new String[0]);
			    			} else {
			    				ProcMsg msg = ProcMsgDAO.create(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,ProcMsg.WARNING,new String[0]);
			    				msgs.add(msg);
			    			}
    					}
    				}
    			}
    		}
    		if(!this.getEventos(Evento.EVT_TIPO_BAJ).isEmpty()){
    			Iterator it = this.getEventos(Evento.EVT_TIPO_BAJ).iterator();
    			while(it.hasNext()){
    				EvtBaja baja = (EvtBaja)it.next();
    				if(!baja.getDestino().equals("VETA"))
    					throw new ExcepcionIntegridad(MENSAJES.TORO_NO_PUEDE_FORMAR_PARTE_ESTA_BAJA,new String[]{this.getRegistroOrigen(),baja.getFecha().toString()});
    			}
    		}
    	}
    	if (this.getFechaNac() != null) { //si no tenemos la fecha de nacimiento no controlamos nada?
    		int edadMinima;
    		boolean monto = serv.getTipoServ().equals(STTipoServicio.CAMP) || serv.getTipoServ().equals(STTipoServicio.CORR);
    		if (monto) {//monto a la hembra 
    			edadMinima = getParametroRazaAsInteger(Raza.EDAD_MINIMA_MONTA);
    		} else {   		// dio semen
    			edadMinima = getParametroRazaAsInteger(Raza.EDAD_MINIMA_PRODUCIR_SEMEN);
    		}
    	   	//esto no lo podemos controlar bien.. si es un embrion, el toro no dio semen ahi en el momento de la TE, sino antes
    		int edadEnDias =getEdadEnDiasAl(serv.getFecha()); 
    		if (edadEnDias < edadMinima) {
    			if (Configuracion.getValorReglaProceso(CONF.REGLA_EDAD_MINIMA_SERVICIO_TORO,serv.getFecha()))
    				throw new ExcepcionIntegridad(MENSAJES.EDAD_MINIMA_INVALIDA_SERVICIO_TORO, new String[] {String.valueOf(edadMinima),this.getRegistroOrigen(), String.valueOf(edadEnDias)});
    			else {
    				ProcMsg warn = ProcMsgDAO.create(MENSAJES.EDAD_MINIMA_INVALIDA_SERVICIO_TORO,ProcMsg.WARNING,new String[] {String.valueOf(edadMinima),this.getRegistroOrigen(), String.valueOf(edadEnDias)});
    				msgs.add(warn);
         	   		}
    			}
    	}
    	if(this.getRegOrigen().getCodigoBaja()!=null)
			if(this.getRegOrigen().getCodigoBaja().equals(new Integer(1)))
				throw new ExcepcionIntegridad(MENSAJES.TORO_INACTIVO,new String[]{this.getRegistroOrigen()});
		
    	serv.setPadreGenetico(this);
    	//this.getEvtNuevoInds().add(serv);
    }

    /* (non-Javadoc)
     * @see ar.org.sicel.persistence.Animal#agregarLactancia(java.util.SortedSet)
     */
    protected void agregarLactancia(SortedSet result) {
        // los machos no tienen lactancias
    }

	
	
    
	/*
	 * No se si los machos tienen calificacion	 
	 *  
	 * @see ar.org.sicel.persistence.Animal#getUltimaCalificacion()
	 */
	public Calificacion getUltimaCalificacion() {		
		return null;
	}

	public String getCategoriaCalculada() {
		/*cambio qeu solicito daniel el dia 7/7/2008
		if("CRIA".equals(this.getEstablecimiento().getMetodoControl().getCodigo()))
			return Animal.CAT_PB;
		else */
			return Animal.CAT_OT;
	}
	
	public String getCategoriaCalculadaSinGenealogia(){
		/*cambio qeu solicito daniel el dia 7/7/2008
		if("CRIA".equals(this.getEstablecimiento().getMetodoControl().getCodigo()))
			return Animal.CAT_PB;
		else */
			return Animal.CAT_OT;
	}

	@Override
	public ProcMsg chequearNumLactanciaYEdad(int numeroLac,Date fechaEvento){
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public EvtServicio getUltimoServicio() {
		return null;
	}


	
        
}
