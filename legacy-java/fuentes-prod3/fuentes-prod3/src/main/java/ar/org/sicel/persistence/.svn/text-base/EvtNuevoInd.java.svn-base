package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.services.impl.TipoRegistroUtil;
import ar.org.sicel.proc.v1.lote.PadreGen;
import ar.org.sicel.proc.v1.lote.Serv;
import ar.org.sicel.proc.v1.lote.Servsemen;
import ar.org.sicel.proc.v1.lote.types.STTipoServicio;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;


/**
 *
 * @hibernate.joined-subclass
 *    table="Ev_NuevoInd"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvNuevoInd_EvServicio"
 *
 */
public class EvtNuevoInd extends ar.org.sicel.persistence.EvtServicio {
    // --------------- attributes ---------------------
    private java.lang.String tipoServ;
    private ar.org.sicel.persistence.Macho padreGenetico;
    private java.util.Date finCorral;

    
    /**
     * 
     * @hibernate.property column="finCorral"
     * @hibernate.column name="finCorral" not-null="false"
     * 
     */  
    public java.util.Date getFinCorral() {
		return finCorral;
	}
	protected void setFinCorral(Date finCorral) {
		this.finCorral = finCorral;
	}
	
	
	protected EvtNuevoInd() {
    }
    //TODO seguir esto
    protected EvtNuevoInd(Establecimiento est, Date fecha, Hembra madreParto, List msgs) throws ExcepcionIntegridad {
    	super(est,fecha,madreParto,msgs);
    }

    /**
     *
     * @hibernate.property
     *     column="tipoServ"
     * @hibernate.column
     *     name="tipoServ"
     *     not-null="true"
     *     length="4"
     *
     */
    public java.lang.String getTipoServ() {
        return this.tipoServ;
    }

    protected void setTipoServ(java.lang.String tipoServ) {
        this.tipoServ = tipoServ;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="padreGen"
     *     outer-join="auto"
     *     foreign-key="FK_EvNuevoInd_Macho"
     *
     */
    public ar.org.sicel.persistence.Macho getPadreGenetico() {
        return this.padreGenetico;
    }

    protected void setPadreGenetico(
        ar.org.sicel.persistence.Macho padreGenetico) {
        this.padreGenetico = padreGenetico;
    }

    // ---------------- business methods  ----------------------
	/**
	 * En un servicio nuevoIndiduo simple (o sea no transf. embrionaria), la madre
	 * genetica es la hembra que recive el servicio 
	 */
	public ar.org.sicel.persistence.Hembra getMadreGenetica() {
		return (ar.org.sicel.persistence.Hembra) this.getAnimal();
	}

	/**
	 * en un servicio nuevoIndividuo simple (o sea no transf. embrionaria), no se
	 * tiene que descontar ningun dia al periodo de gestacion
	 */
	public int getCantidadDiasGestacionADescontar() {
		return 0;
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see ar.org.sicel.persistence.Evento#getResumen()
     */
    public String getResumen() {
        String result = "\n\t\tMacho = "+getPadreGenetico().getRegOrigen()+" nombre: "+getPadreGenetico().getNombre();
        return result;
    }
	
	/**
	 * Método que realiza la eliminaciones pertinentes, primero actualiza la composicion racial de las crias y de las 
	 * crias de las crias y asi sucesivamente luego si tiene algun evento posterior que sea una preñez se elimina
	 */
	public boolean validarBaja() throws ExcepcionIntegridad,HibernateException {
		
		/*EvtReproduccion rep = this.getEvtReproduccion();
		
		if(rep!=null && rep.getEvtCrias()!=null){
			Iterator crias = rep.getEvtCrias().iterator();
			   while (crias.hasNext()) {
		         EvtCria criaE = (EvtCria)crias.next();
		         Animal c =  criaE.getCria();
		         if(c!=null){
		        	 c.setPadre(null);
		        	 c.calcularComposicionRacial(c.getMadreGenetica().getEspecie());
		         }
		        }
		}
		
		List eventosPreñes = this.getAnimal().getEventos(EVT_TIPO_PRE);
		Iterator it = eventosPreñes.iterator();
		
		while(it.hasNext()){
			EvtPrenez ev = (EvtPrenez)it.next();
			if(ev.getFecha().after(this.getFecha()))
				if(rep==null)//si la preñez es posterior y no hay reproduccion
					ev.ejecutarBaja();
				else
					if((ev.getFecha().before(rep.getFecha())))//si la preñez esta dentro de el servicio y la reproduccion
						ev.ejecutarBaja();
					
				
		}*/
		
		/*if(this.getEvtReproduccion()!=null){
			this.getEvtReproduccion().setEvtServicio(null);
			Session session = HibernateFactory.getSession();
	    	session.update(this.getEvtReproduccion());
		}*/
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		if(validarBaja()){
			super.ejecutarBaja();
			EvtReproduccion rep = this.getEvtReproduccion();
			
			if(rep!=null && rep.getEvtCrias()!=null && (!rep.getAsociaSicel1())){
				Iterator crias = rep.getEvtCrias().iterator();
				   while (crias.hasNext()) {
			         EvtCria criaE = (EvtCria)crias.next();
			         Animal c =  criaE.getCria();
			         if(c!=null){
			        	c.cambiarPadreGen(null);//tambien recalcula la composicion racial
			        	//¿que pasa con la raza?
			        	 c.setearCategoria(true);
					        Raza razaDeclarada =null;
					        if (rep.getUsarRazaMadre()) { // si usarRazaMadre esta en true en el
					        	// xml, se usa la raza de la
								// madreGenetica (que es la madre de
								// parto salvo en las TE)
								razaDeclarada = rep.getAnimal().getRaza();
							}
							else//si no tengo servicio y usarRazaMadre es false entonces es cruza
								razaDeclarada = rep.getAnimal().getRaza().getEspecie().getCruza();
					        
					        c.getComposicionRacial().setRazaDeclarada(razaDeclarada);
							
			         }
			        }
			}
			
			/*List eventosPreñes = this.getAnimal().getEventos(EVT_TIPO_PRE);
			Iterator it = eventosPreñes.iterator();
			
			while(it.hasNext()){
				EvtPrenez ev = (EvtPrenez)it.next();
				if(ev.getFecha().after(this.getFecha()))
					if(rep==null)//si la preñez es posterior y no hay reproduccion
						ev.ejecutarBaja();
					else
						if((ev.getFecha().before(rep.getFecha())))//si la preñez esta dentro de el servicio y la reproduccion
							ev.ejecutarBaja();
						
					
			}*/
    		//Session session = HibernateFactory.getSession();
    		
    	try {
    		//session.delete(this);
    		if(rep!=null){
    			//EvtReproduccion rep = this.getEvtReproduccion(); 
    			rep.setEvtServicio(null);
    			this.setEvtReproduccion(null);
    			Session session = HibernateFactory.getSession();
    	    	session.update(this);
    	    	session.update(rep);
    	    	//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
    	    	Eclo ed = EcloDAO.findByPrimaryKey(9L);
    	    	System.out.print(ed.getId());
    		}
    		EvtAnimalDAO.deleteEvtAnim(this);
    		Eclo ed = EcloDAO.findByPrimaryKey(9L);
	    	System.out.print(ed.getId());
    	} catch (HibernateException he){
                ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                        new String[] { String.valueOf(getId()), he.toString() });
                throw e;
    		}
    	}
	}
	public boolean validarModificacion() throws ExcepcionIntegridad {
    	return true;
    }
    /**
     * Método que se encarga de realizar las validaciones y en caso que se pueda modificar el servicio
     * se procede a la modificacion
     */
    public EvtAnimalModificacion ejecutarModificacion(Serv servi, List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		if(!this.getAnimal().getId().equals(objAnimal.getId())){
			
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO,
                    new String[] { this.getAnimal().getRegistroOrigen(), objAnimal.getRegistroOrigen()});
            throw e;
		}   
		//Agregado por si modifica la fecha por el tema de FechaFinCorral
		//EvtServicioDAO.checkServiciosSolapados((Hembra)objAnimal, servi.getFecha(), mensajes, this.getFinCorral(), this);			
		if(servi.getNuevo().getServsemen()!=null){//no cambio de tipo de servicio			
			if(this.evaluarFechaInformadaSemen(servi, objAnimal,mensajes)){
				this.setFecha(servi.getFecha());
				Servsemen ss= servi.getNuevo().getServsemen();
				if(ss.getPadreGen()!=null)
					cambiarDatosPadreGenetico(ss.getPadreGen(),mensajes,ss.getTipoServ().toString());
				if(!(ss.getTipoServ().toString().equals(this.getTipoServ()))){
					this.setTipoServ(ss.getTipoServ().toString());	
					}
				
			}
			if(servi.getNuevo().getServsemen().getFinCorral() != null){
				//EvtServicioDAO.checkServiciosSolapados((Hembra)objAnimal, servi.getFecha(), mensajes,servi.getNuevo().getServsemen().getFinCorral().toDate(), this);
				this.setFinCorral(servi.getNuevo().getServsemen().getFinCorral().toDate());
			}
		}
		else{
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_CAMBIO_SERVICIO,
                    new String[] { this.getAnimal().getId().toString(), String.valueOf(objAnimal.getId().toString())});
            throw e;
		}		
		EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO.create(objAnimal.getEstablecimiento(), new Date(), objAnimal, this);
		this.addModificaciones(eventoModificacion);
		EvtServicioDAO.updateEvtNuevoInd(this);
		return eventoModificacion;
	
    }
    /**
     * si se cambio la padre genetico se hace un 
     * recalculo de la composicion racial de las crias
     * @param padreGen
     * @throws ExcepcionIntegridad
     */
    protected void cambiarDatosPadreGenetico(PadreGen padreGen,List<ProcMsg> mensajes,String tipoServicio) throws ExcepcionIntegridad {
    	String tRegPadre = TipoRegistroUtil.getTipoRegistroEnBase(padreGen.getTReg());
		String nRegPadre = padreGen.getNReg();
		String raza = RazaUtil.getRazaEnBase(padreGen.getRaza());
		Macho padre = AnimalDAO.findExistentMachoByRegistry(tRegPadre,nRegPadre,raza);
		
		Macho padreG = this.getPadreGenetico();
		padreG.getRegIdentificador().getTipoRegistro().getId();
		padreG.getRegIdentificador().getNumero();
		if(!padre.equals(padreG)){//si cambio el padre
			if (!Configuracion.getValorReglaProceso(CONF.ACEPTA_MACHO_CON_RC_PARA_SERVICIO,null) &&
					padreG.getRegIdentificador().getTipoRegistro().getId().equals(Animal.CAT_RC))
	 	   		throw new ExcepcionIntegridad(MENSAJES.NO_ACEPTA_PADRE_CON_RC,new String[] {padreG.getRegistroOrigen()});
			EvtReproduccion rep = this.getEvtReproduccion();
			padre.addServicioEfectuado(this.getEstablecimiento(),this,mensajes);
			this.setPadreGenetico(padre);
			if(rep!=null && rep.getEvtCrias()!=null && (!rep.getAsociaSicel1())){
				Iterator crias = rep.getEvtCrias().iterator();
				   while (crias.hasNext()) {
			         EvtCria criaE = (EvtCria)crias.next();
			         Animal c =  criaE.getCria();
			         if(c!=null){
					        c.cambiarPadreGen(padre);//tambien recalcula la composicion racial
					        //c.setearCategoria(true);
					        Raza razaDeclarada =null;
					       /* if (rep.getUsarRazaMadre()) { // si usarRazaMadre esta en true en el
					        	// xml, se usa la raza de la
								// madreGenetica (que es la madre de
								// parto salvo en las TE)
								razaDeclarada = rep.getAnimal().getRaza();
							}
							else//si no tengo servicio y usarRazaMadre es false entonces es cruza
								razaDeclarada = rep.getAnimal().getRaza().getEspecie().getCruza();*/
					        if(rep.getAnimal().getRaza().getId().equals(padre.getRaza().getId()))
								razaDeclarada = rep.getAnimal().getRaza();
					        else
					        	razaDeclarada = rep.getAnimal().getRaza().getEspecie().getCruza();
					        c.setearCategoria(true);
					        c.getComposicionRacial().setRazaDeclarada(razaDeclarada);
							if((rep.getAnimal()!=null && padre!=null)&&(!rep.getAnimal().getRaza().getId().equals(padre.getRaza().getId()))){
								if(!c.getComposicionRacial().getRazaCalculada().getEsDesconocido())
									c.getComposicionRacial().setRazaDeclarada(c.getComposicionRacial().getRazaCalculada());
								else
									c.getComposicionRacial().setRazaDeclarada(rep.getAnimal().getRaza().getEspecie().getCruza());
							}
					        
				   }
			        }
			}
		}
		else{
			if(tipoServicio!=null){
				if(!(tipoServicio.equals(this.getTipoServ()))){
					if (tipoServicio.equals(STTipoServicio.CAMP.toString()) ||  //si es inseminacion a campo o a corral  
							tipoServicio.equals(STTipoServicio.CORR.toString())) {
			    		if(padre.getEstablecimiento()==null){
				    		Animal.checkUnicidadRPEnEstabForUpdate(this.getId(),this.getEstablecimiento(),padre.getRP(),mensajes,padre.getFechaNac(),this.getEstablecimiento(),padre.getRegistroOrigen(),padre.getCategoria());
							//this.setEstablecimiento(serv.getAnimal().getEstablecimiento());
			    		}
			    		if(this.getAnimal().getEstablecimiento()!=null){ // la madre tiene establecimiento
			    			if(padre.getEstablecimiento()!=null){ // el toro tiene establecimiento
			    				if(!this.getAnimal().getEstablecimiento().equals(getEstablecimiento())){ // el estab de la madre no es igual al estab del toro
			    					if(!this.getAnimal().getEstablecimiento().getPropietario().equals(padre.getEstablecimiento().getPropietario())){ // el propietario del estab de la madre no es igual al prop del estab del toro
						    			if (Configuracion.getValorReglaProceso(CONF.SERVICIO_MISMO_PROP_EST,this.getFecha())) {
						    				throw new ExcepcionIntegridad(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,new String[0]);
						    			} else {
						    				ProcMsg msg = ProcMsgDAO.create(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,ProcMsg.WARNING,new String[0]);
						    				mensajes.add(msg);
						    			}
			    					}
			    				}
			    			}
			    		}else{ // la madre no tiene establecimiento
			    			if(padre.getEstablecimiento()!=null){ // el toro tiene establecimiento
			    				if(!this.getEstablecimiento().equals(padre.getEstablecimiento())){ // el estab que informa no es igual al del toro
			    					if(!this.getEstablecimiento().getPropietario().equals(padre.getEstablecimiento().getPropietario())){ // el prop del estab que informa no es el prop del estab del toro
						    			if (Configuracion.getValorReglaProceso(CONF.SERVICIO_MISMO_PROP_EST,this.getFecha())) {
						    				throw new ExcepcionIntegridad(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,new String[0]);
						    			} else {
						    				ProcMsg msg = ProcMsgDAO.create(MENSAJES.ANIMALES_NO_EST_PROP_PARA_SERVICIO,ProcMsg.WARNING,new String[0]);
						    				mensajes.add(msg);
						    			}
			    					}
			    				}
			    			}
			    		}
				}
				}
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private boolean evaluarFechaInformadaSemen(Serv servi, Animal objAnimal,List msgs) throws ExcepcionIntegridad {
    	if(this.getFecha().equals(servi.getFecha()))
    		return true;
    	else
    		if (servi.getNuevo().getServsemen().getTipoServ().toString().equals(STTipoServicio.INAR.toString())) 
    			EvtServicioDAO.checkEdadAdmisibleServArtificial((Hembra)objAnimal,servi.getFecha(),msgs);	
    		else
    			EvtServicioDAO.checkEdadAdmisibleServNatural((Hembra)objAnimal,servi.getFecha(),msgs);
    		return this.evaluarFechaInformada(servi,objAnimal,msgs);
	}
	/**
     * Realiza comparación de fechas entre la fecha del evento original y la nueva fecha informada para la
     * modificación de dicho evento. 
     * @param servi
     * @param objAnimal
     * @return boolean
     */
    
	@SuppressWarnings("unchecked")
	/**
	 * 
     * el campo fecha es la fecha real desde que se empezo con la gestacion de la cria
     * para el caso de servicio semen es la misma que servi.getFecha()(suma cero) pero para el caso de TE 
     * la gestacion del animal empezo en servi.getFecha() - cantidad dias de gestacion
      */
	protected boolean evaluarFechaInformada(Serv servi, Animal objAnimal,List msgs) throws ExcepcionIntegridad {
		long fe = servi.getFecha().getTime() - this.getCantidadDiasGestacionADescontar();
		Date fecha = new Date(fe);
		if(this.getFecha().equals(/*servi.getFecha()*/fecha))
    		return true;
    	else{
    		//TODO comentado porque daniel no sabe de donde salio
    		//EvtServicioDAO.checkServiciosSolapados((Hembra)objAnimal,/*servi.getFecha()*/fecha,msgs, null);
	    	if (this.getEvtReproduccion()!=null){
	    		//TODO el parametro paso a la Raza (29/03/2007)
	    		int gMin = Integer.parseInt(this.getAnimal().getRaza().getParametro(
	    				Raza.GESTACION_MINIMA));
	    		//TODO el parametro paso a la Raza (29/03/2007)
	    		int gMax = Integer.parseInt(this.getAnimal().getRaza().getParametro(
	    				Raza.GESTACION_MAXIMA));
	    		Date diaMasLejano = DateUtils.menos(this.getEvtReproduccion().getFecha(), gMax);
				Date diaMasCercano = DateUtils.menos(this.getEvtReproduccion().getFecha(), gMin);
				
				if (!DateUtils.entre(/*servi.getFecha()*/fecha, diaMasLejano,
						diaMasCercano))
					throw new ExcepcionIntegridad(
							MENSAJES.SERVICIO_ESPECIFICADO_FUERA_DE_PERIODO,
							new String[] { DateUtils.format(diaMasLejano,null),
									DateUtils.format(diaMasCercano,null) });
	
				Date fechaMejor = getServicioMasApropiadoParaParto(
						this.getEvtReproduccion(), gMax, gMin);
				if (!fechaMejor.equals(/*servi.getFecha()*/fecha)) { // el cambio de fecha hizo que no sea el mejor
					if (Configuracion.getValorReglaProceso(
							CONF.ACEPTA_SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO,
							this.getEvtReproduccion().getFecha())) {
						ProcMsg msg = ProcMsgDAO
								.create(
										MENSAJES.SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO,
										ProcMsg.WARNING,
										new String[] { StringUtils.formatDate(this.getEvtReproduccion().getFecha()),StringUtils.formatDate(fechaMejor) });
						msgs.add(msg);
					} else
						throw new ExcepcionIntegridad(
								MENSAJES.SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO,
								new String[] { StringUtils.formatDate(this.getEvtReproduccion().getFecha()),StringUtils.formatDate(fechaMejor) });
				}
				return true;
	    	}
    	return true;
    	}
    	
    }
    protected Date getServicioMasApropiadoParaParto(EvtReproduccion repr,
			int gMax, int gMin) {
		EvtServicio masApropiado = null;
		Date diaMasLejano = DateUtils.menos(repr.getFecha(), gMax);
		Date diaMasCercano = DateUtils.menos(repr.getFecha(), gMin);
		Iterator eventos = ((Hembra)this.getAnimal()).getServiciosEfectivosEntre(diaMasLejano,
				diaMasCercano).iterator();
		while (eventos.hasNext()) {
			EvtAnimal eAnimal = (EvtAnimal) eventos.next();
			if (eAnimal.getNombreTipo().equals(EvtAnimal.EVT_TIPO_SVC))
				if ((masApropiado == null)|| (Math.abs(DateUtils.diasEntre(diaMasCercano,
								masApropiado.getFecha())) > Math.abs(DateUtils
								.diasEntre(diaMasCercano, eAnimal.getFecha()))))
					masApropiado = (EvtServicio) eAnimal;
		}
		return masApropiado.getFecha();
	}
    /*public boolean equals(Object eve){
    	if(eve!=null){
    	if(!(eve instanceof EvtNuevoInd))
			return false;
		EvtNuevoInd ev = (EvtNuevoInd)eve;
    	
    		if((ev.getId()!=null && this.getId()!=null)&&(ev.getId().equals(this.getId())))
    			return true;
    	}
		return false;
    	
    }*/
}
