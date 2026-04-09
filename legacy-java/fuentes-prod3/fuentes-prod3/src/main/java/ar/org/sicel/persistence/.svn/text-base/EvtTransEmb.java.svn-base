package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.services.impl.TipoRegistroUtil;
import ar.org.sicel.proc.v1.lote.Embrion;
import ar.org.sicel.proc.v1.lote.MadreGen;
import ar.org.sicel.proc.v1.lote.Serv;


/**
 *
 * @hibernate.joined-subclass
 *    table="Ev_TransfEmbr"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvTransfEmbr_EvNuevoInd"
 *
 */
public class EvtTransEmb extends ar.org.sicel.persistence.EvtNuevoInd {
    // --------------- attributes ---------------------
    private java.lang.Integer diasGestacion;
    private java.lang.String idEmbrion;
    private ar.org.sicel.persistence.Hembra madreGenetica;

    protected EvtTransEmb() {
    }
    
    protected EvtTransEmb(Establecimiento est, Date fecha, Hembra madreParto, List msgs) throws ExcepcionIntegridad {
    	super(est,fecha,madreParto,msgs);
    }


    /**
     *
     * @hibernate.property
     *     column="diasGestacion"
     * @hibernate.column
     *     name="diasGestacion"
     *     not-null="true"
     *
     */
    public java.lang.Integer getDiasGestacion() {
        return this.diasGestacion;
    }

    protected void setDiasGestacion(java.lang.Integer diasGestacion) {
        this.diasGestacion = diasGestacion;
    }

    /**
     *
     *
     * @hibernate.property
     *     column="idEmbrion"
     * @hibernate.column
     *     name="idEmbrion"
     *     not-null="true"
     *
     */
    public java.lang.String getIdEmbrion() {
        return this.idEmbrion;
    }

    protected void setIdEmbrion(java.lang.String idEmbrion) {
        this.idEmbrion = idEmbrion;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="madreGen"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_EvTransfEmb_Hembra"
     *
     */
    public ar.org.sicel.persistence.Hembra getMadreGenetica() {
        return this.madreGenetica;
    }

    protected void setMadreGenetica(
        ar.org.sicel.persistence.Hembra madreGenetica) {
        this.madreGenetica = madreGenetica;
    }

	
	

    // ---------------- business methods  ----------------------
    public int getCantidadDiasGestacionADescontar() {
		return this.diasGestacion;
	}

    /*
     * (non-Javadoc)
     * 
     * @see ar.org.sicel.persistence.Evento#getResumen()
     */
    public String getResumen() {
        String result = "Macho = "+getPadreGenetico().getRegistroOrigen()+" nombre: "+getPadreGenetico().getNombre();
        result = result+"\nDonante de ovulo = "+getMadreGenetica().getRegistroOrigen()+" nombre: "+getMadreGenetica().getNombre();
        return result;
    }
    /**
     * Método que hace todas las validaciones y las posibles actualizaciones si es que hubo una modificacion
     * si cambio de animal excepcion-> no se puede cambiar el animal
     * si cambio el tipo de servicio a servicio semen excepcion -> no se puede cambiar de de tipo de servicio
     * si se cambiaron los dias de gestacion del embrion y ya se informo una reproduccion para la madreParto
     * se rechaza
     * se evalua si la fecha es valida
     */
    public EvtAnimalModificacion ejecutarModificacion(Serv servi, List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		if(!this.getAnimal().getId().equals(objAnimal.getId())){
		
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO,
                    new String[] { this.getAnimal().getRegistroOrigen(), objAnimal.getRegistroOrigen()});
            throw e;
		}  
		
		if(servi.getNuevo().getEmbrion()!=null){//no cambio de tipo de servicio
			Embrion em= servi.getNuevo().getEmbrion();
			if(em.getDias()!=this.getDiasGestacion().intValue()){
				EvtReproduccion rep = this.getEvtReproduccion();
				if(rep!=null && rep.getEvtCrias()!=null){
					
					//System.out.println("NO SE PUEDE CAMBIAR LA CANTIDAD DE DIAS DE GESTACION DEL EMBRION POR QUE HAY UNA REP ASOCIADA");
					ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_CAMBIO_DIAS_TE,
		                    new String[] { this.getAnimal().getId().toString(), String.valueOf(objAnimal.getId().toString())});
		            throw e;
				}
				else
					this.setDiasGestacion(em.getDias());
			}
		}
		else{
		
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_CAMBIO_SERVICIO,
                    new String[] { this.getAnimal().getId().toString(), String.valueOf(objAnimal.getId().toString())});
            throw e;
		}
		if(this.evaluarFechaInformadaEmbrion(servi, objAnimal,mensajes)){
			this.setFecha(servi.getFecha());
			Embrion em= servi.getNuevo().getEmbrion();
			if(em.getPadreGen()!=null)
				cambiarDatosPadreGenetico(em.getPadreGen(),mensajes,null);
			if(em.getMadreGen()!=null)
				cambiarDatosMadreGenetica(em.getMadreGen(),mensajes);
			if(em.getIdEmbrion()!=null)
				this.setIdEmbrion(em.getIdEmbrion());
		}
		
		EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO.create(objAnimal.getEstablecimiento(), new Date(), objAnimal, this);
		this.addModificaciones(eventoModificacion);
		EvtServicioDAO.updateEvtTransEmb(this);
		return eventoModificacion;
	
    }
    /**
     * si se cambio la madre genetica se ahce un 
     * recalculo de la composicion racial de las crias
     * @param madreGen
     * @throws ExcepcionIntegridad
     */
    protected void cambiarDatosMadreGenetica(MadreGen madreGen,List<ProcMsg> mensajes) throws ExcepcionIntegridad {
    	String tRegMadre = TipoRegistroUtil.getTipoRegistroEnBase(madreGen.getTReg());
		String nRegMadre = madreGen.getNReg();
		String raza = RazaUtil.getRazaEnBase(madreGen.getRaza());
		Hembra madre = AnimalDAO.findExistentHembraByRegistry(tRegMadre,nRegMadre,raza);
		if(!madre.equals(this.getMadreGenetica())){//si cambio la madre
			EvtReproduccion rep = this.getEvtReproduccion();
			madre.addEvtTransfEmbrinaria(this,mensajes);
			//this.setMadreGenetica(madre);
			if(rep!=null && rep.getEvtCrias()!=null &&  (!rep.getAsociaSicel1())){
				Iterator crias = rep.getEvtCrias().iterator();
				   while (crias.hasNext()) {
			         EvtCria criaE = (EvtCria)crias.next();
			         Animal c =  criaE.getCria();
			         c.cambiarMadreGen(madre);//tambien recalcula la composicion racial
			         c.setearCategoria(true);
			        }
			}
		}
		
	}
    @SuppressWarnings("unchecked")
   
	private boolean evaluarFechaInformadaEmbrion(Serv servi, Animal objAnimal,List msgs) throws ExcepcionIntegridad {
    	if(this.getFecha().equals(servi.getFecha()))
    		return true;
    	else
    		EvtServicioDAO.checkEdadAdmisibleServArtificial((Hembra)objAnimal,servi.getFecha(),msgs);
    		return this.evaluarFechaInformada(servi,objAnimal,msgs);
	}
    
}
