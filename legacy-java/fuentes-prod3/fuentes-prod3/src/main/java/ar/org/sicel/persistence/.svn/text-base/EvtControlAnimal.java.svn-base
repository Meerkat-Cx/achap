package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.types.STProdMedObj;
import ar.org.sicel.proc.v1.lote.types.STTipoMuestreo;

/**
 * @author pablo
 *
 * @hibernate.joined-subclass
 *    table="Ev_ControlAnimal"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvControl_EvAnimalEstab"
 *
 */
public class EvtControlAnimal extends EvtAnimal {
	private Set ordeniesAnimal;
	private EvtControlEstablecimiento controlEstablecimiento;
	private Long idOrdenie;
	
	protected EvtControlAnimal() {
		super();
	}

	public EvtControlAnimal(Establecimiento est, Date fecha, Animal animal,EvtControlEstablecimiento controlEstablecimiento,
			Long idOrdenie, List msgs) throws ExcepcionIntegridad {
		super(est, fecha, animal, msgs,Evento.EVT_TIPO_CONTROL_ANIMAL);
		this.idOrdenie = idOrdenie;
		this.controlEstablecimiento = controlEstablecimiento;
		this.ordeniesAnimal = new HashSet();
	}


	/**
    *
    * @hibernate.many-to-one
    *     column="controlEstablecimiento"
    *     outer-join="auto"
    *     not-null="true"
    *     foreign-key="FK_Control_animal_est"
    *
    */
	public EvtControlEstablecimiento getControlEstablecimiento() {
		return controlEstablecimiento;
	}



	public void setControlEstablecimiento(
			EvtControlEstablecimiento controlEstablecimiento) {
		this.controlEstablecimiento = controlEstablecimiento;
	}


	/**
    *
    * @hibernate.set
    *     lazy="true"
    *     cascade="all"
    *     inverse="true"
    * @hibernate.collection-key
    *     column="evt_control"
    * @hibernate.collection-one-to-many
    *     class="ar.org.sicel.persistence.EvtOrdenieAnimal"
    *
    */
	public Set getOrdeniesAnimal() {
		return ordeniesAnimal;
	     //hibernate.collection-composite-element class="ar.org.sicel.persistence.EvtOrdenieAnimal"
	}



	public void setOrdeniesAnimal(Set ordeniesAnimal) {
		this.ordeniesAnimal = ordeniesAnimal;
	}

	public Long getIdOrdenie() {
		return idOrdenie;
	}

	public void setIdOrdenie(Long idOrdenie) {
		this.idOrdenie = idOrdenie;
	}

	protected String getResumen() {
		return "Control Animal";
	}


	public String getNombreTipo() {
		return Evento.EVT_TIPO_CONTROL_ANIMAL;
	}

	/**
	 * 
	 * @return el metodo de control de este control, es decir el declarado en el control de establecimiento
	 */
	public String getMetodoControl() {
		return getControlEstablecimiento().getMetodoControl().getCodigo();
	}
	
	/**
	 * 
	 * @return el tipo de muestreo de este control, es decir el declarado en el control de establecimiento
	 */
	public String getTipoMuestreo() {
		return getControlEstablecimiento().getTipoMuestreo();
	}
	
	
	/**
	 * 
	 * @return el acumulado de leche para este control (suma todas los ordenies del control)
	 */
	public Float getLeche() {
		float leche = 0f; 
		Iterator it = this.ordeniesAnimal.iterator();
		while (it.hasNext()) {
			EvtOrdenieAnimal ord = (EvtOrdenieAnimal) it.next();
			if (!ord.getEsRechazo()) {
				Float lo = (Float)ord.getMediciones().get(STProdMedObj.LE.toString());
				if (lo != null)
					leche = leche + lo;
			}
		}
		return leche;
	}
	
	/**
	 * 
	 * @return el valor final de SolidosTotales para este control. El calculo depende del tipo de muestreo
	 */
	public Float getSolidosTotales() {
		if (getTipoMuestreo().equals(STTipoMuestreo.DOAN.toString())) {
			return analisisPonderado(STProdMedObj.ST.toString());
		} else {
			EvtOrdenieAnimal conAnalisis =getControlConAnalisis();
			if (conAnalisis != null)
				return (Float)conAnalisis.getMediciones().get(STProdMedObj.ST.toString());
			else
				return null;
		}
	}
	
	public Float getProteinas() {
		if (getTipoMuestreo().equals(STTipoMuestreo.DOAN.toString())) {
			return analisisPonderado(STProdMedObj.PR.toString());
		} else {
			EvtOrdenieAnimal conAnalisis =getControlConAnalisis();
			if (conAnalisis != null)
				return (Float)conAnalisis.getMediciones().get(STProdMedObj.PR.toString());
			else
				return null;
		}
	}
	
	public Float getGrasas() {
		if (getTipoMuestreo().equals(STTipoMuestreo.DOAN.toString())) {
			return analisisPonderado(STProdMedObj.GR.toString());
		} else {
			EvtOrdenieAnimal conAnalisis =getControlConAnalisis();
			if (conAnalisis != null)
				return (Float)conAnalisis.getMediciones().get(STProdMedObj.GR.toString());
			else
				return null;
		}
	}
	
	/*public Float getCelulas() {
		return null;
	}*/
	public Float getCelulas() {
		//return null;
		if (getTipoMuestreo().equals(STTipoMuestreo.DOAN.toString())) {
			return analisisPonderado(STProdMedObj.CE.toString());
		} else {
			EvtOrdenieAnimal conAnalisis =getControlConAnalisis();
			if (conAnalisis != null)
				return (Float)conAnalisis.getMediciones().get(STProdMedObj.CE.toString());
			else
				return null;
		}
	}
	
	
	/**
	 * 
	 * @return el primer EvtOrdenieAnimal que tenga declarado valores porcentuales de analisis. Ojo
	 * 		   que para algun metodo de control o tipo de muestreo quisas puede haber mas de 1, retorna solo el primero 
	 */
	public EvtOrdenieAnimal getControlConAnalisis(){
		EvtOrdenieAnimal ordenie = null;
		Iterator ordenies = this.ordeniesAnimal.iterator();
		while (ordenies.hasNext() && (ordenie == null)) {
			EvtOrdenieAnimal ord = (EvtOrdenieAnimal) ordenies.next();
			if (ord.tieneAnalisis())
				ordenie = ord;
		}
		return ordenie;
	}
	
	
	
	/**
	 * Solo para compatibilidad por ahora con el calculo de lactancia.
	 * @return
	 */
	@SuppressWarnings({"unchecked","unchecked", "unchecked", "unchecked"})
	protected Map getMediciones() {
		Map mediciones = new HashMap();
		Float leche = getLeche();
		Float grasa = getGrasas();
		Float st = getSolidosTotales();
		Float proteinas = getProteinas();
		Float celulas = getCelulas();
		mediciones.put(STProdMedObj.LE.toString(),leche);
		if (grasa != null)
			mediciones.put(STProdMedObj.GR.toString(),grasa);
		
		if (proteinas != null)
			mediciones.put(STProdMedObj.PR.toString(),proteinas);
		if (st != null)
			mediciones.put(STProdMedObj.ST.toString(),st);
		if (celulas != null)
			mediciones.put(STProdMedObj.CE.toString(),celulas);
		return mediciones;
	}

	/**
	 * TODO: preguntar a Daniel si se puede no informar un analisis en un ordenie
	 * con muestreo DOble analisis 
	 * 
	 * @param objeto
	 * @return
	 */
	protected Float analisisPonderado(String objeto) {
		Float lecheTotal = getLeche();
		float objetoAnalisis = 0;
		Iterator itOrdenies = getOrdeniesAnimal().iterator();
		while (itOrdenies.hasNext() ) {
			EvtOrdenieAnimal ord = (EvtOrdenieAnimal )itOrdenies.next();
			Float lecheOrdenie = (Float)ord.getMediciones().get(STProdMedObj.LE.toString());
			Float medicion = (Float)ord.getMediciones().get(objeto);
			if (medicion != null)
				objetoAnalisis= objetoAnalisis + (lecheOrdenie / lecheTotal) * medicion; 
		}
		return objetoAnalisis;  
	}

	
	public boolean validarBaja() throws ExcepcionIntegridad {
		/*Evento ev = this.findEventoPosterior();
		if(ev.getNombreTipo().equals(EVT_TIPO_LAC)){
			ev = ((EvtLactancia)ev).findEventoPosterior();
		}
		if((ev.getNombreTipo().equals(EVT_TIPO_SEC))||
				(ev.getNombreTipo().equals(EVT_TIPO_REP) )||
				(ev.getNombreTipo().equals(EVT_TIPO_BAJ))||
				(ev.getNombreTipo().equals(EVT_TIPO_EST))||(ev.getNombreTipo().equals(EVT_TIPO_CONTROL_ANIMAL))) {
			String mensaje =" Id evento: "+ev.getId()+" Tipo del evento: "+ev.getNombreTipo()+"\n"; 
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_BAJA_ALTA_WITHDEP,
                    new String[] {mensaje});
            throw e;
		}
		*/
		String listEventos = "";
		List eventosLactancia = this.getAnimal().getEventos(EVT_TIPO_LAC);
		Iterator it = eventosLactancia.iterator();
		while(it.hasNext()){
			EvtLactancia ev = (EvtLactancia)it.next();
			if(ev.getFecha().after(this.getFecha())){//si hay un evento lactancia posterior
				//Evento poste = ev.findEventoPosterior();
				Evento poste = ev.getEventoFinalizaLactancia();
				if(poste!=null)
					listEventos = listEventos.concat("Id evento: "+poste.getId()+" Tipo del evento: "+poste.getNombreTipo()+"\n");
				/*Evento evAnte = ev.findEventoAnterior();
				if(evAnte!=null){
					EvtAnimal eAnt = (EvtAnimal)evAnte;
					while(!eAnt.getFecha().equals(this.getFecha())){
						listEventos = listEventos.concat("Id evento: "+eAnt.getId()+" Tipo del evento: "+eAnt.getNombreTipo()+"\n");
						eAnt = (EvtAnimal)eAnt.findEventoAnterior();
					}
				}*/
			}
		}
		if(!listEventos.equals("")){
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_BAJA_ALTA_WITHDEP,
	                new String[] { this.toString(),listEventos });
	        throw e;
		}
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		if(validarBaja()){
			super.ejecutarBaja();
			try{
			//HibernateFactory.getSession().delete(this);
			EvtAnimalDAO.deleteEvtAnim(this);
			
			}catch (HibernateException he){
                ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                        new String[] { String.valueOf(getId()), he.toString() });
                throw e;
    		}
		}
	}
	
	public boolean equals(Object evtControlAnimal){
		if(!(evtControlAnimal instanceof EvtControlAnimal))
			return false;
		EvtControlAnimal evtControlAni = (EvtControlAnimal)evtControlAnimal;
		if(evtControlAni.getIdOrdenie().equals(getIdOrdenie())&&
				evtControlAni.getControlEstablecimiento().getFecha().compareTo(getControlEstablecimiento().getFecha())==0)
			return true;
		return false;	
	}	
	
	 public int hashCode(){
		 if(idOrdenie != null && controlEstablecimiento.getFecha() !=null)
			 return idOrdenie.hashCode()& controlEstablecimiento.getFecha().hashCode();
	     else
	    	 return super.hashCode();
	}

	public boolean isCalostro() {
		Set ordenies = this.getOrdeniesAnimal();
		if(!ordenies.isEmpty()){
			Iterator it = ordenies.iterator();
			while(it.hasNext()){
				EvtOrdenieAnimal orde = (EvtOrdenieAnimal)it.next();
				if(orde.getEsRechazo())
					return true;
			}
		}
		return false;
	}	

}
