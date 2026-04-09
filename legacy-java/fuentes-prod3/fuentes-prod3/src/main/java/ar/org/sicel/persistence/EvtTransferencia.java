package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.Trans;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Transferencia" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvTrans_EvAnim"
 * 
 */
public class EvtTransferencia extends ar.org.sicel.persistence.EvtAnimal {
	// --------------- attributes ---------------------
	private java.lang.String nuevoRP;

	private ar.org.sicel.persistence.Establecimiento nuevoEstab;

	private ar.org.sicel.persistence.Propietario nuevoProp;

	private String antiguoRP;

	private Establecimiento antiguoEstab;

	private Propietario antiguoProp;
	
	private boolean implicita = false;
	
	

	protected EvtTransferencia() {
	}
	
	protected EvtTransferencia(Establecimiento antiguoEst, Date fecha, Animal an, Establecimiento nuevoEst, Propietario nuevoProp, String nuevoRp, List msgs ) throws ExcepcionIntegridad {
//		super(antiguoEst,fecha,an,msgs,Evento.EVT_TIPO_TRA);
		super(nuevoEst,fecha,an,msgs,Evento.EVT_TIPO_TRA);
		setNuevoEstab(nuevoEst);
		setNuevoProp(nuevoProp);
		setNuevoRP(nuevoRp);
		//setAntiguoEstab(an.getEstablecimiento());
		setAntiguoEstab(antiguoEst);
		setAntiguoProp(an.getPropietario());
		setAntiguoRP(an.getRP());
		
	}

	/**
	 * 
	 * @hibernate.property column="nuevoRP"
	 * @hibernate.column name="nuevoRP" not-null="false" length="12"
	 * 
	 */
	public java.lang.String getNuevoRP() {
		return this.nuevoRP;
	}

	protected void setNuevoRP(java.lang.String nuevoRP) {
		this.nuevoRP = nuevoRP;
	}
	
	
	/**
	 * @hibernate.property column="antiguoRP"
	 * @hibernate.column name="antiguoRP" not-null="false" length="12"
	 * 
	 */
	public String getAntiguoRP() {
		return antiguoRP;
	}

	public void setAntiguoRP(String antiguoRP) {
		this.antiguoRP = antiguoRP;
	}

	// ------------- relations ------------------

	/**
	 * 
	 * @hibernate.many-to-one column="nuevoEsta" not-null="false"
	 *                        outer-join="auto" foreign-key="FK_EvTrans_Estab"
	 * 
	 */
	public ar.org.sicel.persistence.Establecimiento getNuevoEstab() {
		return this.nuevoEstab;
	}

	protected void setNuevoEstab(
			ar.org.sicel.persistence.Establecimiento nuevoEstab) {
		this.nuevoEstab = nuevoEstab;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="nuevoProp" not-null="false"
	 *                        outer-join="auto" foreign-key="FK_EvTrans_Prop"
	 * 
	 */
	public ar.org.sicel.persistence.Propietario getNuevoProp() {
		return this.nuevoProp;
	}

	protected void setNuevoProp(ar.org.sicel.persistence.Propietario nuevoProp) {
		this.nuevoProp = nuevoProp;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="antiguoEstab" not-null="false"
	 *                        outer-join="auto"
	 * 
	 */
	public Establecimiento getAntiguoEstab() {
		return antiguoEstab;
	}

	public void setAntiguoEstab(Establecimiento antiguoEstab) {
		this.antiguoEstab = antiguoEstab;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="antiguoProp" not-null="false"
	 *                        outer-join="auto"
	 * 
	 */
	public Propietario getAntiguoProp() {
		return antiguoProp;
	}

	public void setAntiguoProp(Propietario antiguoProp) {
		this.antiguoProp = antiguoProp;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.org.sicel.persistence.Evento#getResumen()
	 */
	public String getResumen() {
        String result = (this.implicita)?" Implicita":" Explicita " ;  
        
		result = result + " Del propietario " + this.getAntiguoProp().getId() + ":" + getAntiguoProp().getNombreContacto()+"\n";
       if( this.getAntiguoEstab()!=null)
    	   result = result + " tambo " + this.getAntiguoEstab().getId() + ":" + getAntiguoEstab().getNombreContacto()+"\n";
       else
    	   result = result + " sin tambo asignado \n";
        result = result + "al propietario " + this.getNuevoProp().getId() + ":" + getNuevoProp().getNombreContacto()+"\n";
        result = result + " tambo " + this.getNuevoEstab().getId() + ":" + getNuevoEstab().getNombreContacto()+"\n";
        if (getNuevoRP().compareTo(getAntiguoRP())!=0)
            result = result + " con cambio del RP de " + this.getAntiguoRP() + " a " + getNuevoRP();
        else
            result = result + " sin cambio del RP " + this.getAntiguoRP();
        result = result + "\n";
        return result;
	}

	/*
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		return EVT_TIPO_TRA;
	}

	// ---------------- business methods ----------------------
/*
	public String getClazzParaTransicion() {
		return EvtTransferencia.class.getSimpleName();
	}
	*/

	protected void eliminarColateralesPost() throws ExcepcionIntegridad {
		super.eliminarColateralesPost();
		this.getAnimal().setEstablecimiento(antiguoEstab);
		this.getAnimal().setEstancia(antiguoEstab.getEstancia());
		this.getAnimal().setPropietario(antiguoProp);
		this.getAnimal().setRP(antiguoRP);
	}


//	MODIF lanzo la exception, ya que este evento no permite distintos establecimientos con distintos dueños
	public void manejoDeExceptionNoTransferencia(String regId, String id, String idProp) throws ExcepcionIntegridad{
		//No lanzo la exception ya que precisamente este es el unico evento que puede tratar esta situacion
	}
	
	public boolean validarBaja() throws ExcepcionIntegridad {
    	SortedSet eventos = this.getAnimal().getEvtAnimals();
    	String listEventos = "";
    	for(Object evt : eventos){
    		Evento evento = (Evento)evt;
    		if(evento.getFecha().after(this.getFecha()) 
    				&& !evento.getNombreTipo().equals(Evento.EVT_TIPO_LMI)
    				&& !evento.getNombreTipo().equals(Evento.EVT_TIPO_LAC)
    				&& !evento.getNombreTipo().equals(Evento.EVT_TIPO_MODIFANI)
    				&& !evento.getDependientes().contains(this))
    			listEventos = listEventos.concat("Id evento: "+((Evento)evt).getId()+" Tipo del evento: "+((Evento)evt).getNombreTipo()+"\n");
    	}
    	if(!listEventos.equals("")){
    		ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_BAJA_ALTA_WITHDEP,
    								new String[] { "Baja de evento transferencia", listEventos });
    		throw e;
    	}
    	return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		if (this.validarBaja()) {
			this.eliminarColateralesPost();
			this.ejecutarBajaSinValidacion();
			/*Session session = HibernateFactory.getSession();

			try {
				this.eliminarColateralesPost();
				super.ejecutarBaja();
				Evento depend = EventoDAO.findEventoIdEventoDeDependiente(this.getAnimal(), this);
				if(depend!=null){
					depend.getDependientes().remove(this);
					session.update(depend);
					if(depend.getDependientes().isEmpty())
						this.getAnimal().borrarEventoAnimal((EvtAnimal)depend);
					
				}
				session.delete(this);
			} catch (HibernateException he) {
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.EVT_ELIM, new String[] {
								String.valueOf(getId()), he.toString() });
				throw e;
			}*/
		}
	}
	public void ejecutarBajaSinValidacion() throws ExcepcionIntegridad {
		
			
			Session session = HibernateFactory.getSession();

			try {
				//this.eliminarColateralesPost();
				super.ejecutarBaja();
				Evento depend = EventoDAO.findEventoIdEventoDeDependiente(this.getAnimal(), this);
				if(depend!=null){
					depend.getDependientes().remove(this);
					session.update(depend);
					if(depend.getDependientes().isEmpty())
						this.getAnimal().borrarEventoAnimal((EvtAnimal)depend);
					
				}
				session.delete(this);
			} catch (HibernateException he) {
				ExcepcionIntegridad e = new ExcepcionIntegridad(
						MENSAJES.EVT_ELIM, new String[] {
								String.valueOf(getId()), he.toString() });
				throw e;
			
		}
	}
	
	
	public EvtAnimalModificacion ejecutarModificacion(Establecimiento establecimiento, Animal objAnimal, Trans evtTransferencia,ProcLote procLote,String nuevoRp) throws ExcepcionIntegridad {
		objAnimal.setRP(nuevoRp);
		AnimalDAO.updateAnimal(objAnimal);
		this.setNuevoRP(nuevoRp);
		this.setFecha(evtTransferencia.getFecha());
		EvtAnimalModificacion evtMod = EvtAnimalModificacionDAO.create(establecimiento, new Date(),objAnimal, this);
		this.addModificaciones(evtMod);		
		//if(!objAnimal.getRegOrigen().getTipoRegistro().equals("HBA")){
		if(!objAnimal.getRegistroOrigen().contains("HBA")){
		FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
		fichaAnimal.setAnimal(objAnimal);
		procLote.agregarFichaAnimal(fichaAnimal);
		}
		EvtTransferenciaDAO.updateEventoTransferencia(this);
		return evtMod;
	}

	public boolean isImplicita() {
		//return true;
		return implicita;
	}

	public void setImplicita(boolean implicita) {
		this.implicita = implicita;
	}
}
