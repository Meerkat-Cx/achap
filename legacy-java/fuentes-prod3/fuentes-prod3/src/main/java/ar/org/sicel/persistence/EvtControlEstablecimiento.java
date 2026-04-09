package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.services.impl.TipoRegistroUtil;
import ar.org.sicel.proc.v1.lote.Control;
import ar.org.sicel.proc.v1.lote.Medicion;
import ar.org.sicel.proc.v1.lote.Ordenie;
import ar.org.sicel.proc.v1.lote.OrdenieAnimal;
import ar.org.sicel.proc.v1.lote.types.STProdMedObj;
import ar.org.sicel.util.DateUtils;

/**
 * @author pablo
 *
 * @hibernate.joined-subclass
 *    table="Ev_Ordenie"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvOrd_EvEstab"
 *
 */
public class EvtControlEstablecimiento extends EvtEstablecimiento {
	
	static Logger log = Logger.getLogger(EvtControlEstablecimiento.class);
	
    private java.lang.Integer numOrdenies;
    private MetodoControl metodoControl;
    private java.lang.Boolean controlEsAM;
    private java.lang.Boolean muestreoEsAM;
    private java.lang.String tipoMuestreo;
    
    private java.util.Date horaIniOrdenie1 = null;
    private java.util.Date horaFinOrdenie1 = null;
    private java.util.Date horaIniOrdenie2 = null;
    private java.util.Date horaFinOrdenie2 = null;
    private java.util.Date horaIniOrdenie3 = null;
    private java.util.Date horaFinOrdenie3 = null;
    
    private Establecimiento estab;
    
    private Set controlesAnimales;
    
    /**
     * 
     * @hibernate.property column="nroOrdenies"
     * @hibernate.column name="nroOrdenies" not-null="true"
     * 
     */
    public java.lang.Integer getNumOrdenies() {
        return this.numOrdenies;
    }

    protected void setNumOrdenies(java.lang.Integer numOrdenies) {
        this.numOrdenies = numOrdenies;
    }

    /**
     * 
     * @hibernate.property column="metodoControl"
     * @hibernate.column name="metodoControl" not-null="true" length="20"
     * 
     */
    public MetodoControl getMetodoControl() {
        return this.metodoControl;
    }

    protected void setMetodoControl(MetodoControl metodoControl) {
        this.metodoControl = metodoControl;
    }

    /**
     * 
     * @hibernate.property column="control_es_AM"
     * @hibernate.column name="control_es_AM" not-null="true"
     * 
     */
    public java.lang.Boolean getControlEsAM() {
        return this.controlEsAM;
    }

    protected void setControlEsAM(java.lang.Boolean controlEsAM) {
        this.controlEsAM = controlEsAM;
    }

    /**
     * 
     * 
     * @hibernate.property column="muestreo_es_AM"
     * @hibernate.column name="muestreo_es_AM" not-null="true"
     * 
     */
    public java.lang.Boolean getMuestreoEsAM() {
        return this.muestreoEsAM;
    }

    protected void setMuestreoEsAM(java.lang.Boolean muestreoEsAM) {
        this.muestreoEsAM = muestreoEsAM;
    }

    /**
     * 
     * 
     * @hibernate.property column="tipoMuestreo"
     * @hibernate.column name="tipoMuestreo" not-null="true" length="20"
     * 
     */
    public java.lang.String getTipoMuestreo() {
        return this.tipoMuestreo;
    }

    protected void setTipoMuestreo(java.lang.String tipoMuestreo) {
        this.tipoMuestreo = tipoMuestreo;
    }

    
    
    
    /**
    *
    * @hibernate.set
    *     lazy="true"
    *     inverse="true"
    *     cascade="all"
    * @hibernate.collection-key
    *     column="controlEstablecimiento"
    * @hibernate.collection-one-to-many
    *     class="ar.org.sicel.persistence.EvtControlAnimal"
    *
    */    
	public Set getControlesAnimales() {
		return controlesAnimales;
	}

	public void setControlesAnimales(Set controles) {
		this.controlesAnimales = controles;
	}

	protected EvtControlEstablecimiento() {
		super();
	}

	public EvtControlEstablecimiento(Establecimiento est, Date fecha) {
		super(est, fecha);
	}


	protected String getResumen() {
		return "Ordeñe";
	}


	public String getNombreTipo() {
		return EvtEstablecimiento.EVT_TIPO_CONTROL_EST;
	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public SortedSet recolectarTodosEventosDependientes() {
		//TODO: quisas no sea aca sino en EventosDependientes directos
		SortedSet result = new TreeSet(new EventosPorFechaYTipo());
		result.addAll(getControlesAnimales());
		return result;
		////TODO tiene dependientes?, podria buscar los de las producciones, pero no se..?
	}
	
	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		
	}
	public void ejecutarBaja(Long idOrd) throws ExcepcionIntegridad {
		if(idOrd.intValue()==0)
			throw new ExcepcionIntegridad(MENSAJES.NO_INFORMA_NUMERO_DE_ORDENIE, new String[]{});
			
		Set cont = this.getControlesAnimales();
		EvtControlAnimal evt = null;
		if(!cont.isEmpty()){
			Iterator it = cont.iterator();
			while(it.hasNext()){
				EvtControlAnimal ev = (EvtControlAnimal)it.next();
				if(ev.getIdOrdenie().equals(idOrd)){
					evt = ev;
					break;
				}
			}
			
		}
		if(evt==null)
			throw new ExcepcionIntegridad(MENSAJES.NO_EXISTE_ORDENIE_INFORMADO, new String[]{this.getId().toString(),idOrd.toString()});
		else{
			//evt.getAnimal().borrarEventoAnimal(evt);
			/*if(evt.getAnimal().getEvtAnimals().contains(evt))
				evt.getAnimal().getEvtAnimals().remove(evt);
			else{
				ArrayList arrayList2 =null;
				if(!evt.getAnimal().getEvtAnimals().isEmpty()){
					arrayList2 = new ArrayList();
					//evt.getAnimal().setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
				Iterator it = evt.getAnimal().getEvtAnimals().iterator();
				EvtAnimal evAux = null;
				while(it.hasNext()){
					EvtAnimal ev = (EvtAnimal)it.next();
					if(!ev.equals(evt))
						arrayList2.add(ev);
				}
				evt.getAnimal().setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
				evt.getAnimal().getEvtAnimals().addAll(arrayList2);
				
			}
			}	*/
			evt.ejecutarBaja();
			this.getControlesAnimales().remove(evt);
			if(this.getControlesAnimales().isEmpty())
				EvtEstablecimientoDAO.deleteEvtEstab(this);
			else
				HibernateFactory.getSession().update(this); 
		}
	}
	
	/**
	 * Devuelve verdadero si ya se cargaron ordenies en este control para el animal que se pasa como parametro.
	 */
	public boolean yaHayControlParaAnimal(Animal animal){
		Iterator it = this.controlesAnimales.iterator();
		while (it.hasNext()) {
			EvtControlAnimal eca = (EvtControlAnimal) it.next();
			if(eca.getAnimal().equals(animal))
				return true;
		}
		return false;
	}

	/**
	 * Retorna verdadero si hay un control para un animal dado informado en la misma fecha
	 * @param animal - Animal
	 * @return boolean
	 */
	public boolean yaHayControlParaAnimalEnFecha(Animal animal){
		Date fechaControl = this.getFecha();
		return EvtControlEstablecimientoDAO.findByFecha(/*this.getEstab(),*/fechaControl,animal)!=null;
	}

	 /**
     * 
     * @hibernate.property column="horaFinOrdenie1"
     * @hibernate.column name="horaFinOrdenie1" not-null="true"
     * 
     */
	public java.util.Date getHoraFinOrdenie1() {
		return horaFinOrdenie1;
	}

	protected void setHoraFinOrdenie1(java.util.Date horaFinOrdenie1) {
		this.horaFinOrdenie1 = horaFinOrdenie1;
	}

	/**
     * 
     * @hibernate.property column="horaFinOrdenie2"
     * @hibernate.column name="horaFinOrdenie2" not-null="false"
     * 
     */
	public java.util.Date getHoraFinOrdenie2() {
		return horaFinOrdenie2;
	}

	protected void setHoraFinOrdenie2(java.util.Date horaFinOrdenie2) {
		this.horaFinOrdenie2 = horaFinOrdenie2;
	}	
	
	/**
     * 
     * @hibernate.property column="horaFinOrdenie3"
     * @hibernate.column name="horaFinOrdenie3" not-null="false"
     * 
     */
	public java.util.Date getHoraFinOrdenie3() {
		return horaFinOrdenie3;
	}

	protected void setHoraFinOrdenie3(java.util.Date horaFinOrdenie3) {
		this.horaFinOrdenie3 = horaFinOrdenie3;
	}
	
	/**
     * 
     * @hibernate.property column="horaIniOrdenie1"
     * @hibernate.column name="horaIniOrdenie1" not-null="true"
     * 
     */
	public java.util.Date getHoraIniOrdenie1() {
		return horaIniOrdenie1;
	}

	protected void setHoraIniOrdenie1(java.util.Date horaIniOrdenie1) {
		this.horaIniOrdenie1 = horaIniOrdenie1;
	}
	
	/**
     * 
     * @hibernate.property column="horaIniOrdenie2"
     * @hibernate.column name="horaIniOrdenie2" not-null="false"
     * 
     */
	public java.util.Date getHoraIniOrdenie2() {
		return horaIniOrdenie2;
	}

	protected void setHoraIniOrdenie2(java.util.Date horaIniOrdenie2) {
		this.horaIniOrdenie2 = horaIniOrdenie2;
	}
	
	/**
     * 
     * @hibernate.property column="horaIniOrdenie3"
     * @hibernate.column name="horaIniOrdenie3" not-null="false"
     * 
     */
	public java.util.Date getHoraIniOrdenie3() {
		return horaIniOrdenie3;
	}

	protected void setHoraIniOrdenie3(java.util.Date horaIniOrdenie3) {
		this.horaIniOrdenie3 = horaIniOrdenie3;
	}

	public Establecimiento getEstab() {
		return estab;
	}

	public void setEstab(Establecimiento estab) {
		this.estab = estab;
	}	
	
	/**
	 * Procesa los ordeñes de los animales informados bajo un control
	 * @param objEst
	 * @param msgs
	 * @param controlEst
	 * @param animales
	 * @param a
	 * @param controles
	 * @throws ExcepcionIntegridad
	 */
	public void procesarOrdeniesAnimal(Establecimiento objEst,
			List<ProcMsg> msgs, Control controlEst,
			Enumeration animales,
			OrdenieAnimal a, Enumeration controles) throws ExcepcionIntegridad {		
		
		Animal animal = chequeoExisteAnimal(a,objEst,msgs);
		log.warn("Ordenie: "+a.getIdOrdenie());
		if(a.getIdOrdenie()==234439)
			log.warn("Ordenie: "+a.getIdOrdenie());
		if(a.getIdOrdenie()==1904338)
			log.warn("Ordenie: "+a.getIdOrdenie());
		
		int numControles = a.getOrdenies().getOrdenieCount();
		Integer numHorarioOrdenies = this.getNumOrdenies();
		
		if(this.yaHayControlParaAnimal(animal))
			throw new ExcepcionIntegridad(MENSAJES.MAS_ORDENIES_QUE_CONTROLES,
					new String[] {
					animal.getRegistroOrigen(),
					DateUtils.format(controlEst.getFecha(),"dd/MM/yyyy") });
			/*throw new ExcepcionIntegridad(MENSAJES.NUM_ORDENIES_NO_COINCIDE,
					new String[] {
					String.valueOf(numControles),
					animal.getRegistroOrigen(),
					String.valueOf(numHorarioOrdenies) });*/
		
		if (numControles != numHorarioOrdenies.intValue())
			throw new ExcepcionIntegridad(MENSAJES.NUM_ORDENIES_NO_COINCIDE,
					new String[] {
					String.valueOf(numControles),
					animal.getRegistroOrigen(),
					String.valueOf(numHorarioOrdenies) });
		
		if(this.yaHayControlParaAnimalEnFecha(animal)){
			log.error("ENTRO EN CONTROL REPETIDO ANIMAL: "+animal.getRegistroOrigen());
			throw new ExcepcionIntegridad(MENSAJES.CONTROL_REPETIDO_ANIMAL,
					new String[] {
					animal.getRegistroOrigen(),
					DateUtils.format(controlEst.getFecha(),"dd/MM/yyyy") });
		}	
			
		EvtControlAnimal controlAnimal = new EvtControlAnimal(objEst, controlEst.getFecha(), animal, this, new Long(a.getIdOrdenie()), msgs);
		//this.getControlesAnimales().add(controlAnimal);
	
		EvtOrdenieAnimal prod = null;
		try {
			if("SM".equals(this.getTipoMuestreo()))
				procesarTipoMuestreoSM(controles,prod,a,animal,controlAnimal,msgs);
			else if("DOAN".equals(this.getTipoMuestreo()))
				procesarTipoMuestreoDOAN(controles,numControles,prod,a,animal,controlAnimal,msgs);
			else
				procesarRestoTipoMuestreo(controles,numControles,prod,a,animal,controlAnimal,msgs);
		} catch (ExcepcionIntegridad ei) {
			// DESHAGO TODO LO QUE SE HIZO, QUE NO TENDRIA QUE ESTAR
			List orde = new ArrayList();
			orde.addAll(controlAnimal.getOrdeniesAnimal());
			controlAnimal.getOrdeniesAnimal().removeAll(orde);
			boolean rrr = this.getControlesAnimales().remove(controlAnimal);
			animal.getEvtAnimals().remove(controlAnimal);
			Iterator ito = orde.iterator();
			while(ito.hasNext()){
				EvtOrdenieAnimal evo = (EvtOrdenieAnimal)ito.next();
				if(evo.getId()!=null)
					EvtOrdenieAnimalDAO.remove(evo);
			}
			List depend = new ArrayList();
			depend.addAll(controlAnimal.getDependientes());
			//controlAnimal.getDependientes().removeAll(depend);
			controlAnimal.getDependientes().clear();
			boolean ddd = false;
			Iterator it4 = depend.iterator();
				while(it4.hasNext()){
					EvtAnimal evv = (EvtAnimal)it4.next();
					ddd = animal.getEvtAnimals().remove(evv);
					if(evv.getId()!=null)
						EvtAnimalDAO.deleteEvtAnim(evv);
				}
			
			if(controlAnimal.getId()!=null){
				EvtAnimalDAO.deleteEvtAnim(controlAnimal);
				//HibernateFactory.getSession().delete(controlAnimal);
			}
			throw ei;
		}
		// me fijo si debe recalcular el estado del animal y la lactancia porque se agregó un evt control retroactivo
		//animal.setEstadoRetroactivo(controlAnimal,msgs);
		animal.setEstadoRetroactivo(controlAnimal.getFecha(),msgs,controlAnimal.getNombreTipo());
		//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
		if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {//si no es carga inicial	
			if("SM".equals(this.getTipoMuestreo())&&("A4".equals(this.getMetodoControl().getCodigo())||
				"C4".equals(this.getMetodoControl().getCodigo())||"A6".equals(this.getMetodoControl().getCodigo())||"C6".equals(this.getMetodoControl().getCodigo()))){
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.DISTINTO_MET_CONT_LACT1, ProcMsg.WARNING_SPECIAL, new String[] {
						this.getMetodoControl().getCodigo(),this.getTipoMuestreo(),
						this.getMetodoControl().getCodigo()+this.getTipoMuestreo()});
				msgs.add(msg);
				msg.setEstaAgregado(true);
				ResultadosUtil.addResultado(a,msg);
			}	
			boolean medicionesConAnalisis = true;
			Iterator it_prod = controlAnimal.getOrdeniesAnimal().iterator();
			while(it_prod.hasNext()){
				EvtOrdenieAnimal produc = (EvtOrdenieAnimal)it_prod.next();
				if(!produc.tieneAnalisis()){
					medicionesConAnalisis = false;
					break;
				}
			}	
			if (!medicionesConAnalisis&&("A4".equals(this.getMetodoControl().getCodigo())||
				"C4".equals(this.getMetodoControl().getCodigo())||"A6".equals(this.getMetodoControl().getCodigo())||"C6".equals(this.getMetodoControl().getCodigo()))){
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.DISTINTO_MET_CONT_LACT, ProcMsg.WARNING_SPECIAL, new String[] {this.getMetodoControl().getDescripcion()});
				msgs.add(msg);
				msg.setEstaAgregado(true);
				ResultadosUtil.addResultado(a,msg);
			}
		}	
		chequearControlesBienMaxMinDias(animal,controlAnimal,a,this.getMetodoControl().getCodigo(),msgs);
		ProcMsg msg = ProcMsgDAO.create(MENSAJES.EVT_ORDENIE_OK, ProcMsg.INFO, new String[] {
				animal.getRegOrigen().getTipoRegistro().getId(), animal.getRegOrigen().getNumero() });
		msgs.add(msg);
		msg.setEstaAgregado(true);
		ResultadosUtil.addResultado(a,msg);
		if(animal.getEstablecimiento()==null){
			animal.setEstablecimiento(objEst);
			animal.setEstancia(objEst.getEstancia());
		}
		this.getControlesAnimales().add(controlAnimal);
		EvtAnimalDAO.saveDependientes(controlAnimal);

	}
	
	/**
	 * Procesa los ordeñes de los animales informados como modificaciones ó nuevos ordeñes
	 * @param objEst
	 * @param msgs
	 * @param controlEst
	 * @param animales
	 * @param a
	 * @param controles
	 * @throws ExcepcionIntegridad
	 */
	public void procesarOrdeniesAnimalModificados(Establecimiento objEst,
			List<ProcMsg> msgs, Control controlEst,
			Enumeration animales,
			OrdenieAnimal a, Enumeration controles) throws ExcepcionIntegridad {
		log.warn("Control: "+controlEst.getIDEvt());
		//busco si ya existe este ordeñe en el control original 
		Long idOrdenie = new Long(a.getIdOrdenie());
		// busco el ordenie en el control con el identificador obtenido
		EvtControlAnimal evtControlAnimal = buscarControlAnimal(idOrdenie);
		if(evtControlAnimal==null)
			// es un nuevo evento ordeñe
			procesarOrdeniesAnimal(objEst,msgs,controlEst,animales,a,controles);
		else{
			//es una modificación de un evento ordeñe ya existente
			int numControles = a.getOrdenies().getOrdenieCount();
			Integer numHorarioOrdenies = this.getNumOrdenies();
			
			Animal animalOld = evtControlAnimal.getAnimal();
			String nReg = a.getReg().getNReg();
			String tReg = TipoRegistroUtil.getTipoRegistroEnBase(a.getReg()
					.getTReg());
			String raza = RazaUtil.getRazaEnBase(a.getReg().getRaza());
			Animal animal = AnimalDAO.findExistentHembraByRegistry(tReg, nReg,raza);
			boolean cambiarAnimal = false;
			if(!animalOld.equals(animal)){
				if(animal.getEstablecimiento()==null){
					Animal.checkUnicidadRPEnEstabForUpdate(animal.getId(),objEst,animal.getRP(),msgs,animal.getFechaNac(),animal.getEstablecimiento(),animal.getRegistroOrigen(),animal.getCategoria());
				}
			
				if (!animal.esHembra())
					throw new ExcepcionIntegridad(MENSAJES.NO_HEMBRA, new String[] {
							tReg, nReg });
				
				if (!animal.getRP().equals(a.getRp()))
					throw new ExcepcionIntegridad(MENSAJES.RP_NO_COINCIDE,
							new String[] { tReg, nReg, a.getRp(), animal.getRP() });
				
				if(this.yaHayControlParaAnimal(animal))
					throw new ExcepcionIntegridad(MENSAJES.NUM_ORDENIES_NO_COINCIDE,
							new String[] {
							String.valueOf(numControles),
							animal.getRegistroOrigen(),
							String.valueOf(numHorarioOrdenies) });

				if(this.yaHayControlParaAnimalEnFecha(animal)){
					log.error("ENTRO EN CONTROL REPETIDO ANIMAL: "+animal.getRegistroOrigen());
					throw new ExcepcionIntegridad(MENSAJES.CONTROL_REPETIDO_ANIMAL,
							new String[] {
							animal.getRegistroOrigen(),
							DateUtils.format(controlEst.getFecha(),"dd/MM/yyyy") });
				}	
				if (numControles != numHorarioOrdenies.intValue())
					throw new ExcepcionIntegridad(MENSAJES.NUM_ORDENIES_NO_COINCIDE,
							new String[] {
							String.valueOf(numControles),
							animal.getRegistroOrigen(),
							String.valueOf(numHorarioOrdenies) });
				
				cambiarAnimal = true;
			}else{
				if (numControles != numHorarioOrdenies.intValue())
					throw new ExcepcionIntegridad(MENSAJES.NUM_ORDENIES_NO_COINCIDE,
						new String[] {
						String.valueOf(numControles),
						animal.getRegistroOrigen(),
						String.valueOf(numHorarioOrdenies) });
			}
			Iterator it_ordeniesAnimales = evtControlAnimal.getOrdeniesAnimal().iterator();
			while(it_ordeniesAnimales.hasNext()){
				EvtOrdenieAnimal evtOrdAnimal = (EvtOrdenieAnimal)it_ordeniesAnimales.next();
				try{
					EvtOrdenieAnimalDAO.remove(evtOrdAnimal);
				}catch(HibernateException e){
					throw new ExcepcionIntegridad("ERRFATAL",new String[] {"No se puede eliminar uno de los ordeñe del " +
							"control animal "+evtControlAnimal.getIdOrdenie().toString()},msgs);
				}
			}
			evtControlAnimal.setOrdeniesAnimal(new HashSet());
			EvtOrdenieAnimal prod = null;
			try {
				if("SM".equals(this.getTipoMuestreo()))
					procesarTipoMuestreoSM(controles,prod,a,animal,evtControlAnimal,msgs);
				else if("DOAN".equals(this.getTipoMuestreo()))
					procesarTipoMuestreoDOAN(controles,numControles,prod,a,animal,evtControlAnimal,msgs);
				else
					procesarRestoTipoMuestreo(controles,numControles,prod,a,animal,evtControlAnimal,msgs);
			} catch (ExcepcionIntegridad ei) {
				// DESHAGO TODO LO QUE SE HIZO, QUE NO TENDRIA QUE ESTAR
				this.getControlesAnimales().remove(evtControlAnimal);
				evtControlAnimal.getOrdeniesAnimal().remove(prod);
				animal.getEvtAnimals().remove(evtControlAnimal);
				throw ei;
			}
			if(cambiarAnimal)
				// le actualizo el animal al ordeñe
				evtControlAnimal.setAnimal(animal);
			// me fijo si debe recalcular el estado del animal y la lactancia porque se agregó un evt control retroactivo
			//animal.setEstadoRetroactivo(evtControlAnimal,msgs);
			animal.setEstadoRetroactivo(evtControlAnimal.getFecha(),msgs,evtControlAnimal.getNombreTipo());
			//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
			if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
				if("SM".equals(this.getTipoMuestreo())&&("A4".equals(this.getMetodoControl().getCodigo())||
					"C4".equals(this.getMetodoControl().getCodigo())||"A6".equals(this.getMetodoControl().getCodigo())||"C6".equals(this.getMetodoControl().getCodigo()))){
					ProcMsg msg = ProcMsgDAO.create(MENSAJES.DISTINTO_MET_CONT_LACT1, ProcMsg.WARNING_SPECIAL, new String[] {
							this.getMetodoControl().getCodigo(),this.getTipoMuestreo(),
							this.getMetodoControl().getCodigo()+this.getTipoMuestreo()});
					msgs.add(msg);
				}	
				boolean medicionesConAnalisis = true;
				Iterator it_prod = evtControlAnimal.getOrdeniesAnimal().iterator();
				while(it_prod.hasNext()){
					EvtOrdenieAnimal produc = (EvtOrdenieAnimal)it_prod.next();
					if(!produc.tieneAnalisis()){
						medicionesConAnalisis = false;
						break;
					}
				}	
				if (!medicionesConAnalisis&&("A4".equals(this.getMetodoControl().getCodigo())||
					"C4".equals(this.getMetodoControl().getCodigo())||"A6".equals(this.getMetodoControl().getCodigo())||"C6".equals(this.getMetodoControl().getCodigo()))){
					ProcMsg msg = ProcMsgDAO.create(MENSAJES.DISTINTO_MET_CONT_LACT, ProcMsg.WARNING_SPECIAL, new String[] {this.getMetodoControl().getDescripcion()});
					msgs.add(msg);
				}
			}	
			chequearControlesBienMaxMinDias(animal,evtControlAnimal,a,this.getMetodoControl().getCodigo(),msgs);
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.EVT_ORDENIE_MODIF_OK, ProcMsg.INFO, new String[] {
					animal.getRegOrigen().getTipoRegistro().getId(), animal.getRegOrigen().getNumero() });
			msgs.add(msg);
			msg.setEstaAgregado(true);
			ResultadosUtil.addResultado(a,msg);
			if(animal.getEstablecimiento()==null){
				animal.setEstablecimiento(objEst);
				animal.setEstancia(objEst.getEstancia());
			}
		}
		

	}	
	
	/**
	 * Chequea que el animal que se informa en el ordeñe existe, que es hembra y además posee
	 * el mismo rp que se informa en el evento ordeñe 
	 * @param ordAnimal
	 * @param objEst
	 * @param msgs
	 * @return Animal
	 * @throws ExcepcionIntegridad
	 */
	private Animal chequeoExisteAnimal(OrdenieAnimal ordAnimal,Establecimiento objEst, List<ProcMsg> msgs) throws ExcepcionIntegridad {
		String nReg = ordAnimal.getReg().getNReg();
		String tReg = TipoRegistroUtil.getTipoRegistroEnBase(ordAnimal.getReg()
				.getTReg());
		String raza = RazaUtil.getRazaEnBase(ordAnimal.getReg().getRaza());
		Animal animal = AnimalDAO.findExistentHembraByRegistry(tReg, nReg,raza);
	
		if(animal.getEstablecimiento()==null){
			Animal.checkUnicidadRPEnEstabForUpdate(animal.getId(),objEst,animal.getRP(),msgs,animal.getFechaNac(),animal.getEstablecimiento(),animal.getRegistroOrigen(),animal.getCategoria());
		}
	
		if (!animal.esHembra())
			throw new ExcepcionIntegridad(MENSAJES.NO_HEMBRA, new String[] {
					tReg, nReg });
		
		//if (!animal.getRP().equals(ordAnimal.getRp()))
		if ((animal.getRP()==null)||(!animal.getRP().equals(ordAnimal.getRp())))
			throw new ExcepcionIntegridad(MENSAJES.RP_NO_COINCIDE,
					new String[] { tReg, nReg, ordAnimal.getRp(), animal.getRP() });
		
		return animal;
	}

	/**
	 * Buscar evento ordeñe dentro del control establecimiento por identificador de ordeñe
	 * @param idOrdenie
	 * @return EvtOrdenieAnimal
	 */
	private EvtControlAnimal buscarControlAnimal(Long idOrdenie){
		Iterator it_controlesAnimales = this.getControlesAnimales().iterator();
		while(it_controlesAnimales.hasNext()){
			EvtControlAnimal evtControlAnimal = (EvtControlAnimal)it_controlesAnimales.next();
			if(evtControlAnimal.getIdOrdenie().equals(idOrdenie))
				return evtControlAnimal;
		}
		return null;
	}
	
	/**
	 * Chequea la cantidad de días que separa el evento control informado del evento control anterior del animal
	 * ó del evento que inició la lactancia si fue el evento anterior.
	 * @param animal
	 * @param evtControlAnimal
	 * @param metControl
	 * @param msgs
	 */
	private void chequearControlesBienMaxMinDias(Animal animal,EvtControlAnimal evtControlAnimal,OrdenieAnimal ordAnimal,String metControl, List<ProcMsg> msgs){
		int icmin = 0;
		int icmax = 0;
		int icminX4 = Integer.parseInt(animal.getRaza().getParametro(Raza.DIAS_MINIMOS_ENTRE_CONTROLES_X4));
		int icmaxX6 = Integer.parseInt(animal.getRaza().getParametro(Raza.DIAS_MAXIMOS_ENTRE_CONTROLES_X6));
		if (metControl.charAt(1) == '4') {
			icmin = icminX4;
			icmax = Integer.parseInt(animal.getRaza().getParametro(Raza.DIAS_MAXIMOS_ENTRE_CONTROLES_X4));
			//icmin = 22;
            //icmax = 37;
        }
        if (metControl.charAt(1) == '6') {
        	icmin = Integer.parseInt(animal.getRaza().getParametro(Raza.DIAS_MINIMOS_ENTRE_CONTROLES_X6));
			icmax = icmaxX6;
            //icmin = 38;
           // icmax = 53;
        }
        List<Object> listaEventos = new ArrayList<Object>();
        listaEventos.addAll(animal.getEvtAnimals());
        if(!listaEventos.isEmpty()){
            EvtAnimal evtAnimal = null;
        	Collections.sort(listaEventos, new EventosPorFechaYTipo());
        	int desde = 0;
       		desde = listaEventos.indexOf(evtControlAnimal);
       		for (ListIterator iter = listaEventos.listIterator(desde); iter
                .hasPrevious();) {
       			evtAnimal = (EvtAnimal) iter.previous();
       			if (evtAnimal instanceof EvtControlAnimal)
       				break;
       			else if(evtAnimal.isIniciaLactancia())
       				break;
       		}
       		
       		if(evtAnimal!=null){
		       		int dias = DateUtils.diasEntre(evtAnimal.getFecha(),evtControlAnimal.getFecha());
		       		if(!evtAnimal.isIniciaLactancia()){//si no es el primer control control-14-08-2008
		       			// si no inicia lactancia entonces considero la separación entre controles común
		       			//if(dias<icmin||dias>icmax){
		       			//el mensaje de advertencia es si no cabe en el rango estipulado entre el minimo de X4 y el maximo de X6
		       			//if((dias < icmin && metControl.charAt(1) == '4')||(dias > icmax && metControl.charAt(1) == '6')){
		       			if(dias < icminX4 || dias > icmaxX6){
		       				ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_DIAS_ENTRE_CONTROLES, ProcMsg.WARNING_SPECIAL, new String[] {
		       						animal.getRegistroOrigen(),DateUtils.format(evtControlAnimal.getFecha(),"dd/MM/yyyy"),DateUtils.format(evtAnimal.getFecha(),"dd/MM/yyyy"),
		       						String.valueOf(dias), String.valueOf(icminX4),String.valueOf(icmaxX6)});
		       				msgs.add(msg);
		       				msg.setEstaAgregado(true);
							ResultadosUtil.addResultado(ordAnimal,msg);
		       			}	
		       		}else{//si es el primer control 14/08/2008
		       			// si inicia lactancia es el evento inicio considero la separación de > 4 dias
		       			int minInicCon = Integer.parseInt(animal.getRaza().getParametro(Raza.DIAS_MINIMOS_PRIMER_CONTROL));
		       			//if(dias<4||dias>icmax){
		       			if(dias<minInicCon||dias>icmax){
		       				ProcMsg msg = ProcMsgDAO.create(MENSAJES.ERROR_DIAS_ENTRE_CONTROLES, ProcMsg.WARNING_SPECIAL, new String[] {
		       						animal.getRegistroOrigen(),DateUtils.format(evtControlAnimal.getFecha(),"dd/MM/yyyy"),DateUtils.format(evtAnimal.getFecha(),"dd/MM/yyyy"),
		       						String.valueOf(dias), String.valueOf(minInicCon),String.valueOf(icmax)});
		       				msgs.add(msg);
		       				msg.setEstaAgregado(true);
							ResultadosUtil.addResultado(ordAnimal,msg);
		       			}	
		       		}
       		}
        } 
	}
	
	/**
	 * Se realiza el procesamiento de las mediciones para el tipo de muestreo SM
	 * @param controles
	 * @param prod
	 * @param animal
	 * @param controlAnimal
	 * @param evtControlEst
	 * @param msgs
	 * @throws ExcepcionIntegridad
	 */
	private void procesarTipoMuestreoSM(Enumeration controles,EvtOrdenieAnimal prod,OrdenieAnimal a, Animal animal,
			EvtControlAnimal controlAnimal, List<ProcMsg> msgs) throws ExcepcionIntegridad {
		while (controles.hasMoreElements()) {
			Ordenie ordenie = (Ordenie) controles.nextElement();
			prod = EvtOrdenieAnimalDAO.create(a,msgs,animal);
			if (ordenie.getValoresOrdenie() != null) {
				if(ordenie.getValoresOrdenie().getHoraIniCtrl()!=null)
					prod.setHoraIniOrdenie(ordenie.getValoresOrdenie().getHoraIniCtrl().toDate());
				if(ordenie.getValoresOrdenie().getHoraFinCtrl()!=null)
					prod.setHoraFinOrdenie(ordenie.getValoresOrdenie().getHoraFinCtrl().toDate());
				Enumeration mediciones = ordenie.getValoresOrdenie().getMeds()
						.enumerateMedicion();
				while (mediciones.hasMoreElements()) {
					Medicion med = (Medicion) mediciones.nextElement();
					String objeto = med.getObjeto().toString();
					//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
					if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {//si no es carga inicial 	
						if(!"LE".equals(objeto)){
							ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_SOLO_LECHE, ProcMsg.WARNING_SPECIAL, new String[] { animal.getRegistroOrigen(),"SM","El tipo de muestreo no acepta valores de mediciones de componentes de analisis" });
							msgs.add(msg);
							msg.setEstaAgregado(true);
							ResultadosUtil.addResultado(a,msg);
						}else{
							Float valor = med.getValor();
							prod.addMed(objeto, valor,animal);// throws, este si
						}
					}else{
						Float valor = med.getValor();
						prod.addMed(objeto, valor,animal);// throws, este si
					}
				}
			} else {
				prod.setCausaRechazo(ordenie.getRechazo().toString());
				prod.setEsRechazo(true);
			}
			prod.validar(this.getFecha(), animal, a, msgs); // throws
			controlAnimal.getOrdeniesAnimal().add(prod);
			prod.setControlAnimal(controlAnimal);
			animal.addEventoAnimal(controlAnimal, msgs); // throws, este genera una lactancia al final
		}
	}
	
	/**
	 * Se realiza el procesamiento de las mediciones para el tipo de muestreo DOAN
	 * @param controles
	 * @param cantControles
	 * @param prod
	 * @param animal
	 * @param controlAnimal
	 * @param evtControlEst
	 * @param msgs
	 * @throws ExcepcionIntegridad
	 */
	private void procesarTipoMuestreoDOAN(Enumeration controles,int cantControles, EvtOrdenieAnimal prod, OrdenieAnimal a,Animal animal,
			EvtControlAnimal controlAnimal, List<ProcMsg> msgs) throws ExcepcionIntegridad {
		Set produccionesAux = new HashSet();
		int cantOrdeniesComp = 0;
		int cantOrdeniesCompConError = 0;
		while (controles.hasMoreElements()) {
			Ordenie ordenie = (Ordenie) controles.nextElement();
			prod = EvtOrdenieAnimalDAO.create(a,msgs,animal);
			if (ordenie.getValoresOrdenie() != null) {
				if(ordenie.getValoresOrdenie().getHoraIniCtrl()!=null)
					prod.setHoraIniOrdenie(ordenie.getValoresOrdenie().getHoraIniCtrl().toDate());
				if(ordenie.getValoresOrdenie().getHoraFinCtrl()!=null)
					prod.setHoraFinOrdenie(ordenie.getValoresOrdenie().getHoraFinCtrl().toDate());
				Enumeration mediciones = ordenie.getValoresOrdenie().getMeds()
						.enumerateMedicion();
				while (mediciones.hasMoreElements()) {
					Medicion med = (Medicion) mediciones.nextElement();
					String objeto = med.getObjeto().toString();
					Float valor = med.getValor();
					prod.addMed(objeto, valor,animal);// throws, este si
				}
			} else {
				prod.setCausaRechazo(ordenie.getRechazo().toString());
				prod.setEsRechazo(true);
				
			}
			
			
			int resultadoValidacion = prod.validarTipoMuestreo(this.getFecha(), animal, a, msgs); 
			//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
			if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
				if (resultadoValidacion==3)
					cantOrdeniesComp ++;
				else if (resultadoValidacion==2){
					cantOrdeniesCompConError ++;
					eliminarMedComp(prod,animal);
				}
			}	
			prod.setControlAnimal(controlAnimal);
			produccionesAux.add(prod);
		}
		//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
		if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
			if(cantOrdeniesComp==3){
				// se informaron 3 mediciones con componentes, entonces se emite warning y solo se toma la leche
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_SOLO_LECHE, ProcMsg.WARNING_SPECIAL, new String[] { animal.getRegistroOrigen(),this.getTipoMuestreo(),"El tipo de muestreo no acepta más de dos mediciones con valores de componentes de analisis" });
				msgs.add(msg);
				msg.setEstaAgregado(true);
				ResultadosUtil.addResultado(a,msg);
				Iterator it_prod = produccionesAux.iterator();
				while(it_prod.hasNext()){
					EvtOrdenieAnimal produc = (EvtOrdenieAnimal)it_prod.next();
					eliminarMedComp(produc,animal);
					controlAnimal.getOrdeniesAnimal().add(produc);
				}
			}else if (cantOrdeniesComp==2){
				if(cantControles==3){
					if(cantOrdeniesCompConError==1){
						ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_SOLO_LECHE, ProcMsg.WARNING_SPECIAL, new String[] { animal.getRegistroOrigen(),this.getTipoMuestreo(),"El tipo de muestreo no acepta más de dos mediciones con valores de componentes de analisis" });
						msgs.add(msg);
						msg.setEstaAgregado(true);
						ResultadosUtil.addResultado(a,msg);
						Iterator it_prod = produccionesAux.iterator();
						while(it_prod.hasNext()){
							EvtOrdenieAnimal produc = (EvtOrdenieAnimal)it_prod.next();
							eliminarMedComp(produc,animal);
							controlAnimal.getOrdeniesAnimal().add(produc);
						}
					}else{
						// tengo que poner en la medición como valores de componentes el promedio de los valores de las otras mediciones
						calcularPromedioProd(produccionesAux,msgs,animal);
						controlAnimal.getOrdeniesAnimal().addAll(produccionesAux);
					}	
				}	
			}else if(cantOrdeniesComp==1){
				// resulta una medicion con componente, entonces se emite warning y solo se toma la leche
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_SOLO_LECHE, ProcMsg.WARNING_SPECIAL, new String[] { animal.getRegistroOrigen(),this.getTipoMuestreo(),"El tipo de muestreo debe tener por lo menos dos mediciones con valores de componentes de analisis" });
				msgs.add(msg);
				msg.setEstaAgregado(true);
				ResultadosUtil.addResultado(a,msg);
				Iterator it_prod = produccionesAux.iterator();
				while(it_prod.hasNext()){
					EvtOrdenieAnimal produc = (EvtOrdenieAnimal)it_prod.next();
					eliminarMedComp(prod,animal);
					controlAnimal.getOrdeniesAnimal().add(produc);
				}
			}else
				controlAnimal.getOrdeniesAnimal().addAll(produccionesAux);
		}else
			controlAnimal.getOrdeniesAnimal().addAll(produccionesAux);
		animal.addEventoAnimal(controlAnimal, msgs); // throws, este genera una lactancia al final
	}

	/**
	 * Se realiza el procesamiento para el resto de los tipos de muestreo: MUCO, MUPR y ALTE 
	 * @param controles
	 * @param cantControles
	 * @param prod
	 * @param animal
	 * @param controlAnimal
	 * @param evtControlEst
	 * @param msgs
	 * @throws ExcepcionIntegridad
	 */
	private void procesarRestoTipoMuestreo(Enumeration controles,int cantControles, EvtOrdenieAnimal prod, OrdenieAnimal a,Animal animal,
			EvtControlAnimal controlAnimal, List<ProcMsg> msgs) throws ExcepcionIntegridad {
		Set produccionesAux = new HashSet();
		int cantOrdeniesComp = 0;
		int cantOrdeniesCompConError = 0;
		while (controles.hasMoreElements()) {
			Ordenie ordenie = (Ordenie) controles.nextElement();
			prod = EvtOrdenieAnimalDAO.create(a,msgs,animal);
			if (ordenie.getValoresOrdenie() != null) {
				if(ordenie.getValoresOrdenie().getHoraIniCtrl()!=null)
					prod.setHoraIniOrdenie(ordenie.getValoresOrdenie().getHoraIniCtrl().toDate());
				if(ordenie.getValoresOrdenie().getHoraFinCtrl()!=null)
					prod.setHoraFinOrdenie(ordenie.getValoresOrdenie().getHoraFinCtrl().toDate());
				Enumeration mediciones = ordenie.getValoresOrdenie().getMeds()
						.enumerateMedicion();
				while (mediciones.hasMoreElements()) {
					Medicion med = (Medicion) mediciones.nextElement();
					String objeto = med.getObjeto().toString();
					Float valor = med.getValor();
					prod.addMed(objeto, valor,animal);// throws, este si
				}
			} else {
				prod.setCausaRechazo(ordenie.getRechazo().toString());
				prod.setEsRechazo(true);
			}
			int resultadoValidacion = prod.validarTipoMuestreo(this.getFecha(), animal, a, msgs); 
			//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
			if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
				if (resultadoValidacion==3)
					cantOrdeniesComp ++;
				else if(resultadoValidacion==2){
					cantOrdeniesCompConError ++;
					eliminarMedComp(prod,animal);
				}
			}	
			prod.setControlAnimal(controlAnimal);
			produccionesAux.add(prod);
		}
		//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
		if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
			if(cantOrdeniesComp>1){
				// se informaron más de una medicion con componentes, entonces se emite warning y solo se toma la leche
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_SOLO_LECHE, ProcMsg.WARNING_SPECIAL, new String[] { animal.getRegistroOrigen(),this.getTipoMuestreo(),"El tipo de muestreo no acepta más de una medición con valores de componentes de analisis" });
				msgs.add(msg);
				msg.setEstaAgregado(true);
				ResultadosUtil.addResultado(a,msg);
				Iterator it_prod = produccionesAux.iterator();
				while(it_prod.hasNext()){
					EvtOrdenieAnimal produc = (EvtOrdenieAnimal)it_prod.next();
					eliminarMedComp(produc,animal);
					controlAnimal.getOrdeniesAnimal().add(produc);
				}
			}else if(cantOrdeniesComp==1){
				if(cantControles==2||cantControles==3){
					if(cantOrdeniesCompConError>0){
						// se informaron más de una medicion con componentes, ademas alguna medicion con error, entonces se emite warning y solo se toma la leche
						ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_SOLO_LECHE, ProcMsg.WARNING_SPECIAL, new String[] { animal.getRegistroOrigen(),this.getTipoMuestreo(),"El tipo de muestreo no acepta más de una medición con valores de componentes de analisis" });
						msgs.add(msg);
						msg.setEstaAgregado(true);
						ResultadosUtil.addResultado(a,msg);
						Iterator it_prod = produccionesAux.iterator();
						while(it_prod.hasNext()){
							EvtOrdenieAnimal produc = (EvtOrdenieAnimal)it_prod.next();
							eliminarMedComp(produc,animal);
							controlAnimal.getOrdeniesAnimal().add(produc);
						}
					}else{
						// tengo que copiar en las mediciones que no tengan componentes los componentes de la que si los tiene
						copiarValoresProd(produccionesAux,msgs,animal);
						controlAnimal.getOrdeniesAnimal().addAll(produccionesAux);
					}	
				}	
			}else
				controlAnimal.getOrdeniesAnimal().addAll(produccionesAux);
		}else	
			controlAnimal.getOrdeniesAnimal().addAll(produccionesAux);
		animal.addEventoAnimal(controlAnimal, msgs); // throws, este genera una lactancia al final
	}
	
	/**
	 * Se calcula un promedio de los componentes de las dos mediciones para generar una tercera medición con el
	 * resultado
	 * @param produccionesAux
	 * @param msgs
	 * @param animal
	 * @return EvtOrdenieAnimal
	 * @throws ExcepcionIntegridad
	 * 
	 */
	private void calcularPromedioProd(Set produccionesAux,List<ProcMsg> msgs, Animal animal) throws ExcepcionIntegridad {
		float grasa = 0;
		float celulas = 0;
		float lactosa = 0;
		float proteinas = 0;
		float solidos = 0;
		float urea = 0;
		Iterator it_prod = produccionesAux.iterator();
		EvtOrdenieAnimal produc = null;
		EvtOrdenieAnimal producMed = null;
		while(it_prod.hasNext()){
			produc = (EvtOrdenieAnimal)it_prod.next();
			if(produc.tieneAnalisis()){
				if (produc.getMediciones().containsKey(STProdMedObj.GR.toString()))
					grasa += ((Float)produc.getMediciones().get(STProdMedObj.GR.toString())).floatValue();
				if (produc.getMediciones().containsKey(STProdMedObj.CE.toString()))
					celulas += ((Float)produc.getMediciones().get(STProdMedObj.CE.toString())).floatValue();
				if (produc.getMediciones().containsKey(STProdMedObj.LA.toString()))
					lactosa += ((Float)produc.getMediciones().get(STProdMedObj.LA.toString())).floatValue();
				if (produc.getMediciones().containsKey(STProdMedObj.PR.toString()))
					proteinas += ((Float)produc.getMediciones().get(STProdMedObj.PR.toString())).floatValue();
				if (produc.getMediciones().containsKey(STProdMedObj.ST.toString()))
					solidos += ((Float)produc.getMediciones().get(STProdMedObj.ST.toString())).floatValue();
				if (produc.getMediciones().containsKey(STProdMedObj.UR.toString()))
					urea += ((Float)produc.getMediciones().get(STProdMedObj.UR.toString())).floatValue();
			}else
				producMed = produc;
		}	
		if(grasa!=0)
			producMed.addMedicion(STProdMedObj.GR.toString(),grasa/2,animal);
		if(celulas!=0)
			producMed.addMedicion(STProdMedObj.CE.toString(),celulas/2,animal);
		if(lactosa!=0)
			producMed.addMedicion(STProdMedObj.LA.toString(),lactosa/2,animal);
		if(proteinas!=0)
			producMed.addMedicion(STProdMedObj.PR.toString(),proteinas/2,animal);
		if(solidos!=0)
			producMed.addMedicion(STProdMedObj.ST.toString(),solidos/2,animal);
		if(urea!=0)
			producMed.addMedicion(STProdMedObj.UR.toString(),urea/2,animal);
		
	}
	
	/**
	 * Copiar los valores de mediciones en un ordeñe informado de más
	 * @param produccionesAux
	 * @param msgs
	 * @param animal
	 * @return EvtOrdenieAnimal
	 * @throws ExcepcionIntegridad
	 */
	private void copiarValoresProd(Set produccionesAux,List<ProcMsg> msgs, Animal animal) throws ExcepcionIntegridad {
		Iterator it_prod = produccionesAux.iterator();
		EvtOrdenieAnimal produc = null;
		EvtOrdenieAnimal producConAnalisis = null;
		while(it_prod.hasNext()){
			produc = (EvtOrdenieAnimal)it_prod.next();
			if(produc.tieneAnalisis()){
				producConAnalisis = produc;
				break;
			}
		}	
		while(it_prod.hasNext()){
			produc = (EvtOrdenieAnimal)it_prod.next();
			if(!produc.tieneAnalisis()){
				if (producConAnalisis.getMediciones().containsKey(STProdMedObj.GR.toString()))
					produc.addMedicion(STProdMedObj.GR.toString(),((Float)producConAnalisis.getMediciones().get(STProdMedObj.GR.toString())).floatValue(),animal);
				if (producConAnalisis.getMediciones().containsKey(STProdMedObj.CE.toString()))
					produc.addMedicion(STProdMedObj.CE.toString(),((Float)producConAnalisis.getMediciones().get(STProdMedObj.CE.toString())).floatValue(),animal);
				if (producConAnalisis.getMediciones().containsKey(STProdMedObj.LA.toString()))
					produc.addMedicion(STProdMedObj.LA.toString(),((Float)producConAnalisis.getMediciones().get(STProdMedObj.LA.toString())).floatValue(),animal);
				if (producConAnalisis.getMediciones().containsKey(STProdMedObj.PR.toString()))
					produc.addMedicion(STProdMedObj.PR.toString(),((Float)producConAnalisis.getMediciones().get(STProdMedObj.PR.toString())).floatValue(),animal);
				if (producConAnalisis.getMediciones().containsKey(STProdMedObj.ST.toString()))
					produc.addMedicion(STProdMedObj.ST.toString(),((Float)producConAnalisis.getMediciones().get(STProdMedObj.ST.toString())).floatValue(),animal);
				if (producConAnalisis.getMediciones().containsKey(STProdMedObj.UR.toString()))
					produc.addMedicion(STProdMedObj.UR.toString(),((Float)producConAnalisis.getMediciones().get(STProdMedObj.UR.toString())).floatValue(),animal);
			}	
		}
	}
	
	/**
	 * Se eliminan los valores de los componentes de análisis para las mediciones que contengan estos datos
	 * @param prod
	 * @param animal
	 * @throws ExcepcionIntegridad
	 */
	private void eliminarMedComp(EvtOrdenieAnimal prod,Animal animal) {
		if (prod.getMediciones().containsKey(STProdMedObj.GR.toString()))
			prod.removeMedicion(STProdMedObj.GR.toString(),animal);
		if (prod.getMediciones().containsKey(STProdMedObj.CE.toString()))
			prod.removeMedicion(STProdMedObj.CE.toString(),animal);
		if (prod.getMediciones().containsKey(STProdMedObj.LA.toString()))
			prod.removeMedicion(STProdMedObj.LA.toString(),animal);
		if (prod.getMediciones().containsKey(STProdMedObj.PR.toString()))
			prod.removeMedicion(STProdMedObj.PR.toString(),animal);
		if (prod.getMediciones().containsKey(STProdMedObj.ST.toString()))
			prod.removeMedicion(STProdMedObj.ST.toString(),animal);
		if (prod.getMediciones().containsKey(STProdMedObj.UR.toString()))
			prod.removeMedicion(STProdMedObj.UR.toString(),animal);
	}

	/**
	 * Ejecuta la modificación de los ordeñes del animal contenidos bajo el evento control 
	 * @param controlEst - desde el xml
	 * @param msgs
	 * @param objEst
	 * @return EvtEstModificacion
	 * @throws ExcepcionIntegridad
	 */
	public EvtEstModificacion ejecutarModificacion(Control controlEst, List<ProcMsg> msgs, Establecimiento objEst)
		throws ExcepcionIntegridad {
		
		Enumeration animales = controlEst.getOrdeniesAnimal().enumerateOrdenieAnimal();
		while (animales.hasMoreElements()) {
			OrdenieAnimal a = (OrdenieAnimal) animales.nextElement();
			Enumeration controles = a.getOrdenies().enumerateOrdenie();
			try {
				this.procesarOrdeniesAnimalModificados(objEst, msgs, controlEst,
					animales, a, controles);
			} catch (ExcepcionIntegridad ei) {
				ProcMsg msg = ProcMsgDAO.create(ei.getCodigoError(),
				ProcMsg.ERROR, ei.getValores());
				msgs.add(msg);
				msg.setEstaAgregado(true);
				ResultadosUtil.addResultado(a,msg);
			}
		}
		// VER PARA RECALCULAR LA LACTANCIA
		EvtEstModificacion eventoModificacion = EvtEstModificacionDAO.create(objEst, new Date(), this);
		this.addModificaciones(eventoModificacion);		
		EvtControlEstablecimientoDAO.updateEventoControlEstablecimiento(this);
			
		return eventoModificacion;
	}
	/*public boolean equals(Object eve){
    	if(eve!=null){
    	if(!(eve instanceof EvtControlEstablecimiento))
			return false;
		EvtControlEstablecimiento ev = (EvtControlEstablecimiento)eve;
    	
    		if((ev.getId()!=null && this.getId()!=null)&&(ev.getId().equals(this.getId())))
    			return true;
    	}
		return false;
    	
    }*/

}
